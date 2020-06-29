package com.sise.db.jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping(value = "/studentinfo/{sid}")
    @ResponseBody
    public Student selectStudentBySid(@PathVariable(value = "sid") String sid) throws InterruptedException {
        return studentService.selectStudentBySid(sid);
    }

    @GetMapping(value = "/studentsinfo/{cid}")
    @ResponseBody
    public List<Student> selectStudentsByCid(@PathVariable(value = "cid") String cid) throws InterruptedException {
        return studentService.selectStudentsByCid(cid);
    }
}
