package com.agilemonkeys.crmservice.audition.configuration;

import com.agilemonkeys.crmservice.audition.service.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    AuditorAware<Long> auditorAware() {
        return new AuditorAwareImpl();
    }
}
