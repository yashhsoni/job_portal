package com.blackhat.job_portal.repository;

import com.blackhat.job_portal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
