package com.sise.db.jpa;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClassService {
    @Resource
    private ClassRepository classRepository;

    public ClassSchool selectClassByCid(String cid){
        return classRepository.findById(cid).get();
    }
}
