package com.sise.rscsystem.repository;

import com.sise.rscsystem.bean.Postman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostRepository extends JpaRepository<Postman, Long>, JpaSpecificationExecutor<Postman> {
    Iterable<Postman> findAllByOrderByFailnumberAscReceivenumberAscSendnumberAsc();
    Postman findPostmanByPostmanaccount(String account);
    List<Postman> findAllByPostmanarea(String area);
}
