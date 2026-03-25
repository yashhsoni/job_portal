package com.blackhat.job_portal.scope;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
@Getter
@Setter
public class ApplicationScopedBean {

    private int visitorCount;

    public ApplicationScopedBean() {
        System.out.println("ApplicationScopedBean Created");
    }

    public void incrementVisitorCount() {
        visitorCount++;
    }
}
