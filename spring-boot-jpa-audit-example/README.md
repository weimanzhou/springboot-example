# SpringBoot 开启 Spring Data Jpa 的审计功能

## 实现审计功能

### 第一步（允许审计）

在启动类（也可以是其他配置类）添加如下注解
```kotlin
@SpringBootApplication
@EnableJpaAuditing
class SpringBootJpaAuditExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringBootJpaAuditExampleApplication>(*args)
}
```

### 第二步（创建实体类）

Spring Data Jpa 的审计功能，通过 
- @CreatedDate 
- @LastModifiedDate 
- @CreatedBy 
- @LastModifiedBy 

四个（属性级别）注解实现

并且在**类**添加 @EntityListener(AuditingEntityListener::class)

例如:

```kotlin
package com.yuki.springbootjpaauditexample.model.`do`

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id
import javax.persistence.Table

@EntityListeners(AuditingEntityListener::class)
@Table
@Entity
class User {

    @Id
    var id: Long = 0L
    var username: String? = null
    var password: String? = null

    @CreatedBy
    var createdBy: String? = null

    @LastModifiedBy
    var lastModifiedBy: String? = null

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    var createdDate: LocalDateTime? = null

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    var lastModifyDate: LocalDateTime? = null

    override fun toString(): String {
        return "User(id=$id, username=$username, password=$password, createdBy='$createdBy', lastModifiedBy='$lastModifiedBy', createdDate=$createdDate, lastModifyDate=$lastModifyDate)"
    }

}
```

### 第三步（设置创建者）

@CreatedDate 和 @LastModifiedDate 可以由 Spring Data Jpa 自动生成
但是 @CreatedBy 和 @LastModifiedBy 不能够由 Spring Data Jpa 自动生成，
需要手动实现 @AuditAware 接口

代码如下：
```kotlin
package com.yuki.springbootjpaauditexample.aware

import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import java.util.*

@Configuration
class UserAware: AuditorAware<String> {

    /**
     * 获取当前用户名
     */
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("TAKO");
    }
}
```

## 使用 SpringBootTest 测试审计功能

### 步骤一（创建 REPOSITORY）

```kotlin
package com.yuki.springbootjpaauditexample.repository

import com.yuki.springbootjpaauditexample.model.`do`.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface UserRepository: JpaRepository<User, Long> {}
```

### 步骤一（创建一个测试文件）

```kotlin
package com.yuki.springbootjpaauditexample.repository

import com.yuki.springbootjpaauditexample.SpringBootJpaAuditExampleApplication
import com.yuki.springbootjpaauditexample.aware.UserAware
import com.yuki.springbootjpaauditexample.model.`do`.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    private val entityManager: TestEntityManager? = null

    @Test
    fun save() {
        val user = User()
        user.username = "TAKO"
        user.password = "TAKO"

        userRepository.save(user)

        println(userRepository.findAll())
        Thread.sleep(2000)

        user.username = "TAKO2"
        userRepository.save(user)
        println(userRepository.findAll())
    }

}
```

### 步骤三（测试运行）

输出结果：
```log
[User(id=1, username=TAKO, password=TAKO, createdBy='TAKO', lastModifiedBy='TAKO', createdDate=2021-06-10T17:11:17.112433500, lastModifyDate=2021-06-10T17:11:17.112433500)]
Hibernate: 
    update
        user 
    set
        created_by=?,
        created_date=?,
        last_modified_by=?,
        last_modify_date=?,
        password=?,
        username=? 
    where
        id=?
Hibernate: 
    select
        user0_.id as id1_0_,
        user0_.created_by as created_2_0_,
        user0_.created_date as created_3_0_,
        user0_.last_modified_by as last_mod4_0_,
        user0_.last_modify_date as last_mod5_0_,
        user0_.password as password6_0_,
        user0_.username as username7_0_ 
    from
        user user0_
[User(id=1, username=TAKO2, password=TAKO, createdBy='TAKO', lastModifiedBy='TAKO', createdDate=2021-06-10T17:11:17.112433500, lastModifyDate=2021-06-10T17:11:19.411677600)]
```
