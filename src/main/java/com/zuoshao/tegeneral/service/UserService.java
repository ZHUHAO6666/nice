package com.zuoshao.tegeneral.service;

import com.zuoshao.tegeneral.bean.Menu;
import com.zuoshao.tegeneral.bean.Role;
import com.zuoshao.tegeneral.bean.UsRo;
import com.zuoshao.tegeneral.bean.User;
import com.zuoshao.tegeneral.bean.beanexa.UserCple;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zuoshao
 * @date 2019/9/11 - 8:44
 */

public interface UserService {
    //用户登录查看用户是否存在
    public User userlogin(User user);
    //查看当前用户拥有的角色
    public List<Menu> userMenu(User user);
    //查询当前所有用户的信息
    public List<UserCple> selectuserall();
    //根据用户id查询当前用户信息
    public UserCple selectuserforid(User user);
    //模糊查询当前列表中的用户
    public List<UserCple> selectuserforexa(User user);
}
