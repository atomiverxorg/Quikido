package com.quikido.auth.utils;

import com.quikido.auth.model.Role;
import jakarta.servlet.http.HttpServletRequest;

public class RoleUtil {
    public boolean hasRole(HttpServletRequest request, Role requiredRole) {
        String role = (String) request.getAttribute("role");
        return requiredRole.name().equals(role);
    }
}
