package com.blackhat.job_portal.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class UserDto {
    private long userId;
    private String name;
    private String email;
    private String mobileNumber;
    private String role;
    private Long companyId;
    private String companyName;
    private Instant createdAt;
}
