package com.yuki.springbootjpaauditexample.aware

import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing
class UserAware: AuditorAware<String> {

    /**
     *
     * 获取当前用户名
     *
     */
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("TAKO");
    }
}
