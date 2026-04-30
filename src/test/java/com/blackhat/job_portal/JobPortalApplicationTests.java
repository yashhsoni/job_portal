package com.blackhat.job_portal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class JobPortalApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void generatePasswordHash() {
        System.out.println(new BCryptPasswordEncoder().encode("EazyBytes@1803"));
    }

}
