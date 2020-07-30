package org.george.pm.mapper;

import org.george.pm.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    /**
     * 获取子部门信息
     * @return
     */
    List<Department> getAllDepartmentsByParentId(Integer parentId);

    int insertByParentId(Department department);

    List<Department> getAllDepartmentsNotRec();
}