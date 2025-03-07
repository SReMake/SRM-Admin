package com.SReMake.system.vo;

import com.SReMake.model.system.Resources;
import com.SReMake.model.system.Type;
import lombok.Data;

import java.util.Objects;

@Data
public class ResourcesVo {
    private long id;
    private String resources;
    private String action;
    private Type type;
    private Long parentId;

    public ResourcesVo(Resources resources) {
        this.id = resources.getId();
        this.resources = resources.getResources();
        this.action = resources.getAction();
        this.type = resources.getType();
        this.parentId = Objects.isNull(resources.getParent()) ? null : resources.getParent().getId();
    }
}
