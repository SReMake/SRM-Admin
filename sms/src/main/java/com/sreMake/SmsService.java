package com.sreMake;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface SmsService {

    void sendTemplateSms(@NotNull String to, @NotNull String templateCode, Map<String, String> templateParam);
}
