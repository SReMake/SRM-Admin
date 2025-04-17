package com.sreMake;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

public interface SmsService {
    /**
     * 发送短信
     *
     * @param to            接收短信的手机号
     * @param templateCode  短信模板id
     * @param templateParam 短信模板参数 请按照短信模板中的顺序填入并且使用 LinkedHashMap
     */
    void sendTemplateSms(@NotNull String to, @NotNull String templateCode, LinkedHashMap<String, String> templateParam);
}
