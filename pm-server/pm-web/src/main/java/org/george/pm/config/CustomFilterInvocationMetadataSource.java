package org.george.pm.config;

import org.george.pm.model.Menu;
import org.george.pm.model.Role;
import org.george.pm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 该类用于根据用户的请求资源，分析请求所需角色
 */
@Component
public class CustomFilterInvocationMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    /**
     * 返回每个菜单项所需的所有角色
     * 每次请求都需要查询数据库，可以使用缓存存储
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    //@Cacheable
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        AntPathMatcher matcher = new AntPathMatcher();
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> menus = menuService.getAllMenuWithRole();
        for (Menu menu : menus) {
            /**
             * 如果请求的路径与菜单项的路径匹配，返回所需的角色集合
             */
            if(matcher.match(menu.getUrl(), requestUrl)){
                List<Role> roles = menu.getRoles();
                String[] rolesStr = new String[roles.size()];
                for(int i = 0; i < rolesStr.length; i++) rolesStr[i] = roles.get(i).getName();
                return SecurityConfig.createList(rolesStr);
            }
        }
        //都不匹配，设置 ROLE_LOGIN 特殊标志
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
