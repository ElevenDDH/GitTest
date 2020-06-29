package com.sise.rscsystem.repository;

import com.sise.rscsystem.bean.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Long>, JpaSpecificationExecutor<Mail> {
    Iterable<Mail> findAllBySenderaccount(String sender_account);
    Iterable<Mail> findAllByReceivername(String name);
    Iterable<Mail> findAllByPostmannameAndReceivetimeIsNullAndFailtimeIsNull(String name);
    Mail findMailByMailid(Long id);
    Iterable<Mail> findAllByPostmannameAndSendtimeIsNullAndReceivetimeIsNotNullAndFailtimeIsNull(String name);
    List<Mail> findAllByPostmanname(String name);
}
