package com.zuoshao.tegeneral.controller;

import com.zuoshao.tegeneral.bean.Curriculum;
import com.zuoshao.tegeneral.bean.User;
import com.zuoshao.tegeneral.service.StudentClassAndRelationShipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: luquanlin
 * @Date: 2019/10/15 10:54
 * @VERSION: 1.0
 **/
@Api(description = "授课信息和学生班级管理接口",value = "")
@Controller
public class StudentClassAndRelationShipController {
    @Autowired
    private StudentClassAndRelationShipService studentClassAndRelationShipService;

    @ApiOperation(value="直接返回没有班级的学生用户数据", notes="直接返回没有班级的学生用户数据",httpMethod = "POST")
    @RequestMapping("/selectstudents")
    @ResponseBody
    public Map selectstudents(){
        Map result = new HashMap();
        List<User> users = studentClassAndRelationShipService.selectstudents();
        result.put("data",users);
        return result;
    }

    @ApiOperation(value="直接返回所有老师的用户数据", notes="直接返回所有老师的用户数据",httpMethod = "POST")
    @RequestMapping("/selectAllTeacher")
    @ResponseBody
    public Map selectAllTeacher(){
        Map result = new HashMap();
        List<User> teacher = studentClassAndRelationShipService.selectAllTeacher();
        result.put("data",teacher);
        return result;
    }

    @ApiOperation(value="直接返回所有课程数据", notes="直接返回所有课程数据",httpMethod = "POST")
    @RequestMapping("/selectAllCurriculum")
    @ResponseBody
    public Map selectAllCurriculum(){
        Map result = new HashMap();
        List<Curriculum> curricula = studentClassAndRelationShipService.selectAllCurriculum();
        result.put("data",curricula);
        return result;
    }


}
