package org.george.pm.controller.hr;

import org.george.pm.model.Hr;
import org.george.pm.model.RespBean;
import org.george.pm.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hr")
public class HrInfoController {
    @Autowired
    HrService hrService;

    @GetMapping("/info")
    public Hr getHrInfo(Authentication authentication){
        return ((Hr) authentication.getPrincipal());
    }

    @PutMapping("/info")
    public RespBean updateHrInfo(@RequestBody Hr hr, Authentication authentication){
        if(hrService.updateHr(hr) == 1){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr, authentication.getCredentials(), authentication.getAuthorities()));
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @PutMapping("/pass")
    public RespBean updateHrPassword(@RequestBody Map<String, Object> changePassInfo){
        String oldPass = (String) changePassInfo.get("oldPass");
        String newPass = (String) changePassInfo.get("newPass");
        Integer id = (Integer) changePassInfo.get("id");
        if(hrService.updateHrPassword(id, oldPass, newPass)){
            return RespBean.ok("更新密码成功");
        }
        return RespBean.error("更新密码失败");
    }
}
