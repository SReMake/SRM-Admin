package com.SReMake.system.vo;


import lombok.Data;

import java.util.Set;

@Data
public class ApiVo {
    private Set<String> method;
    private Set<String> path;

    public ApiVo(Set<String> method, Set<String> path) {
        this.method = method;
        this.path = path;
    }
}
