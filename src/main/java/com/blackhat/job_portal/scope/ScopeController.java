package com.blackhat.job_portal.scope;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scope")
@RequiredArgsConstructor
public class ScopeController {
    private final RequestScopedBean requestScopedBean;
    private final SessionScopedBean sessionScopedBean;
    private final ApplicationScopedBean applicationScopedBean;

    @GetMapping("/request")
    public ResponseEntity<String> testRequestScope() {
        requestScopedBean.setUsername("admin");
        return  ResponseEntity.ok().body(requestScopedBean.getUsername());
    }

    @GetMapping("/session")
    public ResponseEntity<String> testSessionScope() {
        sessionScopedBean.setUsername("admin");
        return  ResponseEntity.ok().body(sessionScopedBean.getUsername());
    }

    @GetMapping("/test")
    public ResponseEntity<Integer> testScope() {
        return  ResponseEntity.ok().body(applicationScopedBean.getVisitorCount());
    }

    @GetMapping("/application")
    public ResponseEntity<Integer> testApplicationScope() {
        applicationScopedBean.incrementVisitorCount();
        return  ResponseEntity.ok().body(applicationScopedBean.getVisitorCount());
    }

}
