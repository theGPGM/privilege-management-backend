package org.george.pm.mapper;

import org.apache.ibatis.annotations.Param;
import org.george.pm.model.Hr;
import org.george.pm.model.Role;
import org.george.pm.utils.HrUtils;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Role> getHrRolesById(Integer id);

    List<Hr> getAllHr(@Param("id")Integer id, @Param("keywords") String keywords);
}