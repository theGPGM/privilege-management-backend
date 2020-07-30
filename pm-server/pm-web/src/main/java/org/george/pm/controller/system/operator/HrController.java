package org.george.pm.controller.system.operator;

import org.george.pm.model.Hr;
import org.george.pm.model.RespBean;
import org.george.pm.model.Role;
import org.george.pm.service.HrService;
import org.george.pm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/operator/")
public class HrController {
    @Autowired
    private HrService hrService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Hr> getAllHr(String keywords){
        return hrService.getAllHr(keywords);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if(hrService.updateHr(hr) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PutMapping("/roles")
    public RespBean updateRoles(Integer id, @RequestParam("rid") Integer[] rids){
        if(hrService.updateRoles(id, rids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHrByHrId(@PathVariable("id")Integer id){
        if(hrService.deleteHrByHrId(id) == 1)
            return RespBean.ok("删除成功");
        return RespBean.error("删除失败");
    }
}
