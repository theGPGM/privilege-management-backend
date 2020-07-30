package org.george.pm.controller.system.basic;

import org.george.pm.model.Department;
import org.george.pm.model.RespBean;
import org.george.pm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/dep")
public class DepController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department){
        if(departmentService.addDepartment(department)){
        return RespBean.ok("添加成功", department);
    }
        return RespBean.error("添加失败");
}

    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable("id") Integer id){
        if(departmentService.deleteDepartment(id)){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
