package com.sise.rscsystem.service;

import com.sise.rscsystem.bean.Postman;
import com.sise.rscsystem.bean.PostmanWorkload;
import com.sise.rscsystem.bean.PostmanWorkstate;
import com.sise.rscsystem.repository.PostRepository;
import com.sise.rscsystem.repository.PostmanWorkloadRepository;
import com.sise.rscsystem.repository.PostmanWorkstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {
    @Resource
    @Autowired
    PostRepository postRepository;
    @Resource
    @Autowired
    PostmanWorkloadRepository postmanWorkloadRepository;
    @Resource
    @Autowired
    PostmanWorkstateRepository postmanWorkstateRepository;

    @Transactional
    public Postman postmanRegister(Postman postman){
        return postRepository.save(postman);
    }
    @Transactional
    public Iterable<Postman> checkAllPost(){
        return  postRepository.findAll();
    }
    @Transactional
    public Postman postmanChange(Postman postman){
        return postRepository.saveAndFlush(postman);
    }
    @Transactional
    public void removeOnePost(Postman postman){
        postRepository.delete(postman);
    }

    //分配邮差
    @Transactional
    public Iterable<Postman> postmanAssign(){
        return postRepository.findAllByOrderByFailnumberAscReceivenumberAscSendnumberAsc();
    }
    @Transactional
    public Postman findPostmanByName(String name){
        return postRepository.findPostmanByPostmanaccount(name);
    }

    //根据某月按区域地址查询所有邮差
    @SuppressWarnings("serial")
    public List<PostmanWorkload> findByPostman_PostmanareaAndSave_time(String area, String time){
        List<PostmanWorkload> postmanWorkloads = postmanWorkloadRepository.findByPostman_PostmanareaAndSavetimeLike(area, "%"+time+"%");
//        new Specification<PostmanWorkload>(){
//            @Override
//            public Predicate toPredicate(Root<PostmanWorkload> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                return null;
//            }
//        }
        return postmanWorkloads;
    }

    public PostmanWorkstate findWorkstateByPostmanaccountAndSavetimeLike(String account, String time){
        PostmanWorkstate postmanWorkstate = postmanWorkstateRepository.findByPostmanaccountAndSavetimeLike(account, "%"+time+"%");
        return postmanWorkstate;
    }

}
