package org.george.pm.service;

import org.george.pm.mapper.DepartmentMapper;
import org.george.pm.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    @Transactional
    public boolean addDepartment(Department department) {
        Department parentDep = departmentMapper.selectByPrimaryKey(department.getParentId());
        parentDep.setParent(true);
        if(departmentMapper.updateByPrimaryKeySelective(parentDep) != 1) return false;
        department.setEnabled(true);
        if(departmentMapper.insertByParentId(department) != 1) return false;
        department.setDepPath(parentDep.getDepPath() + "." + department.getId());
        if(departmentMapper.updateByPrimaryKeySelective(department) != 1) return false;
        return true;
    }

    @Transactional
    public boolean deleteDepartment(Integer id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        Department parentDep = departmentMapper.selectByPrimaryKey(department.getParentId());
        if(departmentMapper.deleteByPrimaryKey(id) != 1) return false;
        List<Department> children = departmentMapper.getAllDepartmentsByParentId(parentDep.getId());
        if(children.size() <= 1) {
            parentDep.setParent(false);
            if(departmentMapper.updateByPrimaryKey(parentDep) != 1)
                return false;
        }
        return true;
    }

    public List<Department> getAllDepartmentsNotRec() {
        return departmentMapper.getAllDepartmentsNotRec();
    }
}
