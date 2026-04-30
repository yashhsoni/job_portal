package com.blackhat.job_portal;

import com.blackhat.job_portal.security.util.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableCaching
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(value = {CorsProperties.class})
public class JobPortalApplication {

    public static void main(String[] args) {SpringApplication.run(JobPortalApplication.class, args);
    }

}
