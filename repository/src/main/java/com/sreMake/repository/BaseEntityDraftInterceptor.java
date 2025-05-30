package com.sreMake.repository;

import com.sreMake.model.BaseEntity;
import com.sreMake.model.BaseEntityDraft;
import com.sreMake.model.BaseEntityProps;
import com.sreMake.model.security.CustomUserDetails;
import com.sreMake.model.user.User;
import org.babyfish.jimmer.ImmutableObjects;
import org.babyfish.jimmer.sql.DraftInterceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Objects;

@Component
public class BaseEntityDraftInterceptor
        implements DraftInterceptor<BaseEntity, BaseEntityDraft> {


    @Override
    public void beforeSave(@NotNull BaseEntityDraft draft, @Nullable BaseEntity original) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!ImmutableObjects.isLoaded(draft, BaseEntityProps.UPDATE_AT)) {
            draft.setUpdateAt(OffsetDateTime.now());
        }

        if (!Objects.isNull(authentication)) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = customUserDetails.getUser();
            if (!ImmutableObjects.isLoaded(draft, BaseEntityProps.UPDATE_BY)) {
                draft.setUpdateById(user.id());
            }
            if (original == null && !ImmutableObjects.isLoaded(draft, BaseEntityProps.CREATE_BY)) {
                draft.setCreateById(user.id());
            }
        }

    }
}