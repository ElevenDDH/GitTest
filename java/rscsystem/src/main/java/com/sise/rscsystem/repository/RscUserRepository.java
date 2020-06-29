package com.sise.rscsystem.repository;

import com.sise.rscsystem.bean.RscUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RscUserRepository extends JpaRepository<RscUser, Long> {
    RscUser findByName(String name);
}
