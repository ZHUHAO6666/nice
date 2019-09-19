package com.zuoshao.tegeneral.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zuoshao.tegeneral.bean.Menu;
import com.zuoshao.tegeneral.bean.Role;
import com.zuoshao.tegeneral.bean.UsRo;
import com.zuoshao.tegeneral.bean.User;
import com.zuoshao.tegeneral.bean.beanexa.UserCple;
import com.zuoshao.tegeneral.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuoshao
 * @date 2019/9/9 - 12:09
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String,Object> userlogin(@Param("password")String password, @Param("username")String username, HttpSession session) throws Exception{

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Map<String,Object> menuss=new HashMap<>();

        User userlogin = userService.userlogin(user);

        if (userlogin != null)
        {
            List<List<Menu>> lists = new ArrayList<>();
            List<Menu> menus = userService.userMenu(userlogin);
            menuss.put("menu",menus);
            menuss.put("code",1);

        }else {
            menuss.put("mags","用户名或者密码不正确");
            menuss.put("code",0);

        }
        return menuss;
    }

    @RequestMapping(value = "/getmenu")
    @ResponseBody
    public Map<String,Object> userlogin(@Param("username")String username, HttpSession session){
        User user = new User();
        user.setUsername(username);
        User userlogin = userService.userlogin(user);
        Map<String,Object> menuss=new HashMap<>();

        if (user == null)
        {
            menuss.put("code",0);
            menuss.put("msg","没有查询结果");

        }else {
            List<List<Menu>> lists = new ArrayList<>();
            List<Menu> menus = userService.userMenu(userlogin);
            menuss.put("menu",menus);
            menuss.put("code",1);
            session.setAttribute("user",userlogin);

        }
        return menuss;
    }


    @GetMapping("/getuserall")
    @ResponseBody
    public PageInfo<UserCple> getAllPerson(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@Param("pageSize")Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserCple> list = userService.selectuserall();
        PageInfo<UserCple> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping("/getuserforid")
    @ResponseBody
    public Map<String, Object> getuserfotid(@Param("id")Integer id){
        User user = new User();
        user.setId(id);
        UserCple selectuserforid = userService.selectuserforid(user);
        Map<String,Object> menuss=new HashMap<>();
        menuss.put("code",1);
        menuss.put("userforid",selectuserforid);
        return menuss;
    }

    @RequestMapping("/getuserforuserexa")
    @ResponseBody
    public Map<String, Object> getuserforuserexa(@Param("exa")String exa){
        User user = new User();
        user.setName(exa);
        List<UserCple> selectuserforexa = userService.selectuserforexa(user);
        Map<String,Object> menuss=new HashMap<>();
        menuss.put("code",1);
        menuss.put("userforexa",selectuserforexa);
        return menuss;
    }

    @RequestMapping("/adduser")
    @ResponseBody
    public Map<String,Object> adduser(@RequestBody Map map) throws Exception{

        String username =(String) map.get("username");
        String name =(String) map.get("name");
        String colleges = (String) map.get("college");
        Integer collegei =Integer.parseInt(colleges);

        ArrayList<Integer> listss= (ArrayList<Integer>) map.get("role");



        Integer[] rolei = new Integer[]{};
        int i=0;
        for (Integer role:listss) {
            rolei[i]=role;
            i++;
        }

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword("111111");
        user.setCollid(collegei);

        Integer adduser = userService.adduser(user);
        Map<String,Object> menuss=new HashMap<>();

        if (adduser==1){

            User user1 = new User();
            user1.setUsername(username);
            User userlogin = userService.userlogin(user1);
            UsRo usro = new UsRo();
            usro.setUid(userlogin.getId());

            List<Object> list = new ArrayList<>();
            for (Integer roleid:rolei) {
                usro.setRid(roleid);
                Integer integer = userService.adduserrole(usro);
                list.add(integer);
            }
            menuss.put("code",1);
            return menuss;
        }else {
            menuss.put("code",0);
            return menuss;
        }

    }

}
