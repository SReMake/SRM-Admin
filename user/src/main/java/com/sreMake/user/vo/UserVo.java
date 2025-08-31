package com.sreMake.user.vo;

import com.sreMake.model.user.Role;
import com.sreMake.model.user.User;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserVo {
    private long id;
    private String username;
    private String phone;
    private String avatar;
    private User.Status status;
    private OffsetDateTime createAt;
    private OffsetDateTime updateAt;
    private List<RoleVo> roles;
    private User createBy;
    private User updateBy;

    public UserVo(User user, List<Role> role) {
        this.id = user.id();
        this.username = user.username();
        this.phone = user.phone();
        this.avatar = user.avatar();
        this.status = user.status();
        this.createAt = user.createAt();
        this.updateAt = user.updateAt();
        this.createBy = user.createBy();
        this.updateBy = user.updateBy();
        if (role != null) {
            this.roles = role.stream().map(RoleVo::new).toList();
        }
    }

}
