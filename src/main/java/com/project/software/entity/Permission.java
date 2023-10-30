package com.project.software.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CLIENT_READ("client:read"),
    CLIENT_UPDATE("client:update"),
    CLIENT_CREATE("client:create"),
    CLIENT_DELETE("client:delete"),
    FREELANCER_READ("freelancer:read"),
    FREELANCER_UPDATE("freelancer:update"),
    FREELANCER_CREATE("freelancer:create"),
    FREELANCER_DELETE("freelancer:delete")

    ;

    @Getter
    private final String permission;
}
