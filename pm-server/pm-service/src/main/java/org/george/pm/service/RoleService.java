package org.george.pm.service;

import org.george.pm.mapper.RoleMapper;
import org.george.pm.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuService menuService;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public int addRole(Role role) {
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.insert(role);
    }

    public int deleteRole(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
