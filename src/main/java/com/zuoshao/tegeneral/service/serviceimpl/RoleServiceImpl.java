package com.zuoshao.tegeneral.service.serviceimpl;

import com.zuoshao.tegeneral.bean.Role;
import com.zuoshao.tegeneral.mapper.RoleMapper;
import com.zuoshao.tegeneral.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

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

}