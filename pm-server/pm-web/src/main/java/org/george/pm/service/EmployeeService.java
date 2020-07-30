package org.george.pm.service;

import org.george.pm.mapper.CurrentWorkSequenceMapper;
import org.george.pm.mapper.EmployeeMapper;
import org.george.pm.model.Employee;
import org.george.pm.model.RespBean;
import org.george.pm.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CurrentWorkSequenceMapper currentWorkSequenceMapper;

    public RespPageBean getEmployeesByPage(Integer page, Integer size, String keywords) {
        Integer offset = null;
        if (page != null && size != null) {
            offset = (page - 1) * size;
        }
        Long total = employeeMapper.getTotal(keywords);
        List<Employee> employees = employeeMapper.getEmployeesByPage(offset, size, keywords);
        RespPageBean bean = new RespPageBean();
        bean.setData(employees);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmployee(Employee e) {
        return employeeMapper.insertSelective(e);
    }

    @Transactional
    public RespBean generateWorkId() {
        Integer workId = currentWorkSequenceMapper.getCurrentMaxWorkId();
        String incId = String.format("%08d", workId + 1);
        if (currentWorkSequenceMapper.incWorkId(incId) == 1) {
            return RespBean.build()
                    .setStatus(200)
                    .setObj(incId);
        }
        return RespBean.error("生成工号失败");
    }

    public Integer addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }
}
