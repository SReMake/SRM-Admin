package com.sreMake.system.vo;

import com.sreMake.model.system.Resources;
import lombok.Data;

import java.util.Objects;

@Data
public class ResourcesVo {
    private long id;
    private String resources;
    private String action;
    private Resources.Type type;
    private Long parentId;
    private String path;
    private String name;

    public ResourcesVo(Resources resources) {
        this.id = resources.id();
        this.resources = resources.resources();
        this.action = resources.action();
        this.type = resources.type();
        this.parentId = Objects.isNull(resources.parent()) ? null : Objects.requireNonNull(resources.parent()).id();
        this.path = resources.path();
        this.name = resources.name();
    }
}
