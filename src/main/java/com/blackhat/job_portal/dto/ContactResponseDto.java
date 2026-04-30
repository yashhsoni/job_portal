package com.blackhat.job_portal.dto;

import jakarta.validation.constraints.Pattern;

import java.time.Instant;

public record ContactResponseDto(Long id,
                                 String name,
                                 String email,
                                 String userType,
                                 String subject,
                                 String message,
                                 String status,
                                 Instant createdAt) {
}
