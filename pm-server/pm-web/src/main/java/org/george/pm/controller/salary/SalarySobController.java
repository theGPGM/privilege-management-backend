package org.george.pm.controller.salary;

import org.george.pm.model.RespBean;
import org.george.pm.model.Salary;
import org.george.pm.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sob")
public class SalarySobController {
    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        if(salaryService.addSalary(salary) == 1){
            return RespBean.ok("添加账套成功");
        }
        return RespBean.error("添加账套失败");
    }

    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary){
        if(salaryService.updateSalary(salary) == 1){
            return RespBean.ok("更新账套成功");
        }
        return RespBean.error("更新账套失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable("id") Integer id){
        if(salaryService.deleteSalaryById(id) == 1)
            return RespBean.ok("删除帐套成功");
        return RespBean.error("删除帐套失败");
    }
}
