package com.sise.rscsystem.repository;

import com.sise.rscsystem.bean.PostmanWorkload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostmanWorkloadRepository extends JpaRepository<PostmanWorkload, Long>, JpaSpecificationExecutor<PostmanWorkload> {
    List<PostmanWorkload> findByPostman_PostmanareaAndSavetimeLike(String area, String time);

}
