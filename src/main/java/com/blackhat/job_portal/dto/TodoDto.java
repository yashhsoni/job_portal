package com.blackhat.job_portal.dto;

public record TodoDto(Long userId, Long id, String title, boolean completed) {
}
