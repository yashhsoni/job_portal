package com.blackhat.job_portal.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public record ContactRequestDto (
        @NotBlank(message = "email cannot be empty")
        @Email(message = "Invalid Email Format")
        String email,

        @NotBlank(message = "message cannot be empty")
        @Size(min = 5, max = 500, message = "message should be in 5 to 500 words")
        String message,

        @NotBlank(message = "name cannot be empty")
        @Size(min = 1, max = 20, message = "name should be in 1 to 3 words")
        String name,

        @NotBlank(message = "subject cannot be empty")
        @Size(min = 5, max = 500, message = "message should be in 5 to 500 words")
        String subject,

        @NotBlank(message = "userType cannot be empty")
        @Pattern(regexp = "Job Seeker|Employer|Other", message = "UserType must be one of: Job Seeker, Employer, Other")
        String userType

) implements Serializable {
}
