package com.sise.rscsystem.repository;

import com.sise.rscsystem.bean.PostmanWorkstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostmanWorkstateRepository extends JpaRepository<PostmanWorkstate, Long>, JpaSpecificationExecutor<PostmanWorkstate> {
    PostmanWorkstate findByPostmanaccountAndSavetimeLike(String name, String time);
}
