package org.george.pm.service;

import org.george.pm.mapper.MenuMapper;
import org.george.pm.mapper.MenuRoleMapper;
import org.george.pm.model.Hr;
import org.george.pm.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 通过用户 id 查询用户的所有可访问菜单项
     * @return
     */
    public List<Menu> getMenu() {
        Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuMapper.getMenuByHrId(hr.getId());
    }

    /**
     * 获取所有菜单项
     * @return
     */
    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }

    /**
     * 获得菜单项所需所有角色
     * @return
     */
    public List<Menu> getAllMenuWithRole() {
        return menuMapper.getAllMenuWithRole();
    }

    public List<Integer> getMenusIdWithRoleId(Integer rid) {
        return menuMapper.getMenusIdWithRoleId(rid);
    }

    @Transactional
    public boolean updateRoleWithMultiMenus(Integer rid, Integer[] menuIds) {
        menuRoleMapper.deleteByRoleId(rid);
        if(menuIds.length == 0 || menuIds == null) return true;
        if(menuRoleMapper.insertRoleWithMultiMenus(rid, menuIds) == menuIds.length)
            return true;
        return false;
    }
}
