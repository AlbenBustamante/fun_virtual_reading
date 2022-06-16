package com.alnicode.funvirtualreading.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The role types enum.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
@Getter
public enum RoleType {
    ROLE_USER(1, "USER"),
    ROLE_ADMIN(2, "ADMIN");

    private final int id;
    private final String name;

}
