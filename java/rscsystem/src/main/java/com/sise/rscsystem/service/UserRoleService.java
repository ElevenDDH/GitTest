package com.sise.rscsystem.service;

import com.sise.rscsystem.bean.UserRole;
import com.sise.rscsystem.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class UserRoleService {
    @Resource
    private UserRoleRepository userRoleRepository;
    @Transactional
    public UserRole insertRole(UserRole userRole){
        return userRoleRepository.save(userRole);
    }
    @Transactional
    public UserRole findByUserid(Long id){
        return userRoleRepository.findByUserid(id);
    }
}
