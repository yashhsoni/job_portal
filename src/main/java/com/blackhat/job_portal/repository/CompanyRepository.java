package com.blackhat.job_portal.repository;

import com.blackhat.job_portal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("""
    SELECT c FROM Company c
    LEFT JOIN FETCH c.jobs
""")
    List<Company> findAllWithJobs();


}
