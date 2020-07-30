package org.george.pm.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 该类用于校验用户的角色列表是否与请求资源所需角色匹配
 */
@Component
public class CustomRequestUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute();
            //请求资源只需登录即可访问
            if("ROLE_LOGIN".equals(needRole)){
                if(authentication instanceof AnonymousAuthenticationToken) throw new AccessDeniedException("用户未登录！");
                else return;
            }

            /**
             * 比较用户拥有的角色和所需的角色
             */
            Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
            for (GrantedAuthority role : roles) {
                if(role.getAuthority().equals(needRole))
                    return;
            }
        }
        throw new AccessDeniedException("用户无权限！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
