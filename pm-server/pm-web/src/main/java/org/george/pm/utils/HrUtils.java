package org.george.pm.utils;

import org.george.pm.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {

    public static Hr getCurrentHr(){
        Hr principal = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
}
