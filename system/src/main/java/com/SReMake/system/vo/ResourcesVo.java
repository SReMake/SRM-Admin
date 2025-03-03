package com.SReMake.system.vo;

import com.SReMake.model.system.Resources;
import lombok.Data;

import java.util.Objects;

@Data
public class ResourcesVo {
    private long id;
    private String resources;
    private String action;
    private Resources.Type type;
    private Long parentId;

    public ResourcesVo(Resources resources) {
        this.id = resources.id();
        this.resources = resources.resources();
        this.action = resources.action();
        this.type = resources.type();
        this.parentId = Objects.isNull(resources.parent()) ? null : resources.parent().id();
    }
}
