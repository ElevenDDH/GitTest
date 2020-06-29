package com.sise.db.jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ClassController {
    @Resource
    private ClassService classService;

    @GetMapping(value = "/classinfo/{cid}")
    @ResponseBody
    public ClassSchool selectClassByCid(@PathVariable(value = "cid") String cid) throws InterruptedException {
        return classService.selectClassByCid(cid);
    }
}
