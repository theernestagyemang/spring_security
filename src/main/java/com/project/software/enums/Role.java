package com.project.software.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_CREATE,
            Permission.MANAGER_READ,
            Permission.MANAGER_UPDATE,
            Permission.MANAGER_DELETE,
            Permission.MANAGER_CREATE,
            Permission.SUPERVISOR_READ,
            Permission.SUPERVISOR_UPDATE,
            Permission.SUPERVISOR_DELETE,
            Permission.SUPERVISOR_CREATE,
            Permission.EMPLOYEE_READ,
            Permission.EMPLOYEE_UPDATE,
            Permission.EMPLOYEE_DELETE,
            Permission.EMPLOYEE_CREATE,
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.USER_CREATE
    )),
    MANAGER(Set.of(
            Permission.MANAGER_READ,
            Permission.MANAGER_UPDATE,
            Permission.MANAGER_DELETE,
            Permission.MANAGER_CREATE,
            Permission.SUPERVISOR_READ,
            Permission.SUPERVISOR_UPDATE,
            Permission.SUPERVISOR_DELETE,
            Permission.SUPERVISOR_CREATE,
            Permission.EMPLOYEE_READ,
            Permission.EMPLOYEE_UPDATE,
            Permission.EMPLOYEE_DELETE,
            Permission.EMPLOYEE_CREATE,
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.USER_CREATE
    )),
    SUPERVISOR(Set.of(
            Permission.SUPERVISOR_READ,
            Permission.SUPERVISOR_UPDATE,
            Permission.SUPERVISOR_DELETE,
            Permission.SUPERVISOR_CREATE,
            Permission.EMPLOYEE_READ,
            Permission.EMPLOYEE_UPDATE,
            Permission.EMPLOYEE_DELETE,
            Permission.EMPLOYEE_CREATE,
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.USER_CREATE
    )),
    EMPLOYEE(Set.of(
            Permission.EMPLOYEE_READ,
            Permission.EMPLOYEE_UPDATE,
            Permission.EMPLOYEE_DELETE,
            Permission.EMPLOYEE_CREATE,
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.USER_CREATE
    )),
    USER(Set.of(
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.USER_CREATE
    ));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
