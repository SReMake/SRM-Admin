package com.sreMake.user.vo;

import com.sreMake.model.user.Role;
import lombok.Data;

@Data
public class RoleVo {
    private long id;
    private String name;

    public RoleVo(Role role) {
        this.id = role.id();
        this.name = role.name();
    }


    public RoleVo(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
