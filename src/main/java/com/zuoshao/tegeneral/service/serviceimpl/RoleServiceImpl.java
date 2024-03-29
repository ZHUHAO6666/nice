package com.zuoshao.tegeneral.service.serviceimpl;

import com.zuoshao.tegeneral.bean.Menu;
import com.zuoshao.tegeneral.bean.Role;
import com.zuoshao.tegeneral.bean.beanexa.RoleMenu;
import com.zuoshao.tegeneral.bean.beanexa.UserCple;
import com.zuoshao.tegeneral.mapper.MenuMapper;
import com.zuoshao.tegeneral.mapper.RoleMapper;
import com.zuoshao.tegeneral.mapper.Userrolemapper;
import com.zuoshao.tegeneral.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    Userrolemapper userrolemapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public Integer saveRole(Role role) {
        int insert = roleMapper.insert(role);
        return insert;
    }

    @Override
    public Integer deleteRole(Role id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateRole(Role id) {
        return roleMapper.updateByPrimaryKey(id);
    }

    @Override
    public List<Role> getRole() {
        return roleMapper.selectAll();
    }

    @Override
    public List<RoleMenu> getrolemenu() {

        List<RoleMenu> selectrolemenu = userrolemapper.selectuserrolemeun();
        return selectrolemenu;
    }

    @Override
    public List<Menu> getmenuall() {
        List<Menu> menus = menuMapper.selectAll();
        return menus;
    }

    @Override
    public List<Menu> getmenuforrole(Role role) {
        List<Menu> selectmenuforrole = userrolemapper.selectmenuforrole(role);
        return selectmenuforrole;
    }

    @Override
    public Integer insertRole(String rolename){                //以下新增
        return roleMapper.insertRole(rolename);
    }
    @Override
    public Integer insertRo_Me(@Param("rolename") String rolename, @Param("menuId") Integer menuId){
        return roleMapper.insertRo_Me(rolename,menuId);
    }
    @Override
    public Role selectRole(String rolename){
        return roleMapper.selectRole(rolename);
    }

    @Override
    public Integer insertMenu(@Param("name") String name, @Param("img") String img,@Param("path") String path){
        return menuMapper.insertMenu(name,img,path);
    }
}
