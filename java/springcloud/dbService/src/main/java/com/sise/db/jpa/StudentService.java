package com.sise.db.jpa;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {
    @Resource
    private StudentRepository studentRepository;

    public Student selectStudentBySid(String sid) {
        return studentRepository.findAllBySid(sid);
    }

    public List<Student> selectStudentsByCid(String cid) {
        return studentRepository.findStudentsByClassSchool_Cid(cid);
    }
}
