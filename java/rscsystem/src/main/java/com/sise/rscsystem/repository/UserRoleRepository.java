package com.sise.rscsystem.repository;

import com.sise.rscsystem.bean.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByUserid(Long id);
}
