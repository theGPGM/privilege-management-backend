package org.george.pm.mapper;

import org.apache.ibatis.annotations.Param;
import org.george.pm.model.HrRole;

public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

    /**
     * 删除 id = #{id} 的用户的所有角色
     * @param id
     * @return
     */
    Integer deleteHrRolesById(Integer id);

    /**
     * 通过用户 id 和角色 id 数组批量插入
     * @param hrid, rids
     * @param rids
     * @return
     */
    Integer insertHrRoles(@Param("hrid") Integer hrid, @Param("rids") Integer[] rids);
}