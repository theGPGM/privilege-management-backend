package org.george.pm.mapper;

import org.george.pm.model.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenuByHrId(Integer id);

    List<Menu> getAllMenuWithRole();

    List<Menu> getAllMenus();

    List<Integer> getMenusIdWithRoleId(Integer rid);
}