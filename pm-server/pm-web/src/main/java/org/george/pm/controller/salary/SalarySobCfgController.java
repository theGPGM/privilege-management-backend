package org.george.pm.controller.salary;

import org.george.pm.model.Employee;
import org.george.pm.model.RespBean;
import org.george.pm.model.RespPageBean;
import org.george.pm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sobCfg")
public class SalarySobCfgController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public RespPageBean getEmployees(@RequestParam(defaultValue = "1") Integer page, @RequestParam (defaultValue = "10") Integer size){
        return employeeService.getEmployeesWithSalaryByPage(page, size);
    }

    @PutMapping("/")
    public RespBean updateEmployeeWithSalary(Integer id, Integer sid){
        if(employeeService.updateEmployeeWithSalary(id, sid) == 1)
            return RespBean.ok("更新员工工资账套成功");
        return RespBean.error("更新员工工资账套失败");
    }
}
