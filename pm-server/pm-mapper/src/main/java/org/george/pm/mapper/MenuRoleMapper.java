package org.george.pm.mapper;

import org.apache.ibatis.annotations.Param;
import org.george.pm.model.MenuRole;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    void deleteByRoleId(Integer rid);

    int insertRoleWithMultiMenus(@Param("rid") Integer rid, @Param("menuIds") Integer[] menuIds);
}