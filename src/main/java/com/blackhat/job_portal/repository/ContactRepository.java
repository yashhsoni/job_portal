package com.blackhat.job_portal.repository;

import com.blackhat.job_portal.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}