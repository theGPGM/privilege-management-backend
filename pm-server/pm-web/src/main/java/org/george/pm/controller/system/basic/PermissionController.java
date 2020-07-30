package org.george.pm.controller.system.basic;

import org.george.pm.model.Menu;
import org.george.pm.model.RespBean;
import org.george.pm.model.Role;
import org.george.pm.service.MenuService;
import org.george.pm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/per")
public class PermissionController {
    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/menus/{rid}")
    public List<Integer> getMenusIdWithRoleId(@PathVariable("rid") Integer rid){
        return menuService.getMenusIdWithRoleId(rid);
    }

    /**
     * 更新角色的可访问资源
     * @param rid
     * @param menuIds
     * @return
     */
    @PutMapping("/")
    public RespBean updateRoleWithMultiMenus(Integer rid, @RequestParam("menuId") Integer[] menuIds){
        if(menuService.updateRoleWithMultiMenus(rid, menuIds)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role) == 1)
            return RespBean.ok("添加成功");
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/{rid}")
    public RespBean deleteRole(@PathVariable("rid") Integer rid){
        if(roleService.deleteRole(rid) == 1)
            return RespBean.ok("删除成功");
        return RespBean.error("删除失败");
    }
}
