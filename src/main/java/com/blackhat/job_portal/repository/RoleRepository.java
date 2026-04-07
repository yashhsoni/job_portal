package com.blackhat.job_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.blackhat.job_portal.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(String name);

}
