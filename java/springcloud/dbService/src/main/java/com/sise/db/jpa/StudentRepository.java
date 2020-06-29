package com.sise.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findAllBySid(String sid);
    List<Student> findStudentsByClassSchool_Cid(String cid);
}
