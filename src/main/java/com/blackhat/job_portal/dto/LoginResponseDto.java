package com.blackhat.job_portal.dto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}
