package com.sreMake.user.vo;

import com.sreMake.model.user.User;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserVo {
    private long id;
    private String username;
    private String phone;
    private String avatar;
    private User.Status status;
    private OffsetDateTime createAt;
    private OffsetDateTime updateAt;
    private User createBy;
    private User updateBy;

    public UserVo(User user) {
        this.id = user.id();
        this.username = user.username();
        this.phone = user.phone();
        this.avatar = user.avatar();
        this.status = user.status();
        this.createAt = user.createAt();
        this.updateAt = user.updateAt();
        this.createBy = user.createBy();
        this.updateBy = user.updateBy();
    }

}
