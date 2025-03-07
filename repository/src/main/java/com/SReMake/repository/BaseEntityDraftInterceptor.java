package com.SReMake.repository;

import com.SReMake.model.BaseEntity;
import com.SReMake.model.BaseEntityDraft;
import com.SReMake.model.BaseEntityProps;
import com.SReMake.model.security.CustomUserDetails;
import com.SReMake.model.user.User;
import org.babyfish.jimmer.ImmutableObjects;
import org.babyfish.jimmer.sql.DraftInterceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class BaseEntityDraftInterceptor
        implements DraftInterceptor<BaseEntity, BaseEntityDraft> {


    @Override
    public void beforeSave(@NotNull BaseEntityDraft draft, @Nullable BaseEntity original) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!ImmutableObjects.isLoaded(draft, BaseEntityProps.INSTANCE.getUPDATE_AT())) {
            draft.setUpdateAt(LocalDateTime.now());
        }

        if (!Objects.isNull(authentication)) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = customUserDetails.getUser();
            if (!ImmutableObjects.isLoaded(draft, BaseEntityProps.INSTANCE.getUPDATE_BY())) {
                draft.setUpdateById(user.getId());
            }
            if (original == null && !ImmutableObjects.isLoaded(draft, BaseEntityProps.INSTANCE.getCREATE_BY())) {
                draft.setCreateById(user.getId());
            }
        }

    }
}