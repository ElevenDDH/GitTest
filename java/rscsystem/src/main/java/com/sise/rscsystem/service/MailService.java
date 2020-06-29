package com.sise.rscsystem.service;

import com.sise.rscsystem.bean.Mail;
import com.sise.rscsystem.repository.MailRepository;
import com.sise.rscsystem.repository.PostRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MailService {
    @Resource
    private MailRepository mailRepository;
    @Resource
    private PostRepository postRepository;
    @Transactional
    public Mail clientSend(Mail mail){
        return mailRepository.save(mail);
    }
    public Iterable<Mail> findAllMail(){
        return mailRepository.findAll();
    }
    @Transactional
    public Iterable<Mail> findAllMailByAccount(String sender_account){
        return mailRepository.findAllBySenderaccount(sender_account);
    }
    @Transactional
    public Iterable<Mail> findAllByReceivername(String name){
        return mailRepository.findAllByReceivername(name);
    }
    @Transactional
    public Iterable<Mail> findAllByPostman_nameAndReceiveNull(String name){
        return mailRepository.findAllByPostmannameAndReceivetimeIsNullAndFailtimeIsNull(name);
    }
    @Transactional
    public Iterable<Mail> findAllBySendNullAndReceiveNotNull(String name){
        return mailRepository.findAllByPostmannameAndSendtimeIsNullAndReceivetimeIsNotNullAndFailtimeIsNull(name);
    }
    @Transactional
    public Mail findByMail_id(Long id){
        return mailRepository.findMailByMailid(id);
    }
    @Transactional
    public Mail mailChange(Mail mail){
        return mailRepository.saveAndFlush(mail);
    }


    @SuppressWarnings("serial")
    public List<Mail> getMailBySendTimeDayLikeSpecification(String time){
        List<Mail> mailList = mailRepository.findAll(new Specification<Mail>() {
            @Override
            public Predicate toPredicate(Root<Mail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.like(root.get("sendtime"), "%"+time+"%");
                return predicate;
            }
        });
        return mailList;
    }
    @SuppressWarnings("serial")
    public List<Mail> getMailByReceiveTimeDayLikeSpecification(String time){
        List<Mail> mailList = mailRepository.findAll(new Specification<Mail>() {
            @Override
            public Predicate toPredicate(Root<Mail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.like(root.get("receivetime"), "%"+time+"%");
                return predicate;
            }
        });
        return mailList;
    }
    @SuppressWarnings("serial")
    public List<Mail> getMailByFailTimeLikeDaySpecification(String time){
        List<Mail> mailList = mailRepository.findAll(new Specification<Mail>() {
            @Override
            public Predicate toPredicate(Root<Mail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.like(root.get("failtime"), "%"+time+"%");
                return predicate;
            }
        });
        return mailList;
    }


}
