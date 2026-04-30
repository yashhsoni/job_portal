package com.blackhat.job_portal.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public record ContactRequestDto (
        @NotBlank(message = "email cannot be empty")
        @Email(message = "Invalid Email Format")
        String email,

        @NotBlank(message = "Message can not be empty")
        @Size(min = 5, max = 500, message = "Message must be between 5 and 500 characters")
        String message,

        @NotBlank(message = "Name can not be empty")
        @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
        String name,

        @NotBlank(message = "Subject can not be empty")
        @Size(min = 5, max = 150, message = "Subject must be between 5 and 150 characters")
        String subject,

        @NotBlank(message = "userType cannot be empty")
        @Pattern(regexp = "(?i)Job Seeker|jobseeker|Employer|Other", message = "UserType must be one of: Job Seeker, Employer, Other")
        String userType

) implements Serializable {
}