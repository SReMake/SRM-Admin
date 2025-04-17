package com.sreMake.impl;


import cn.hutool.json.JSONUtil;
import com.aliyun.dysmsapi20180501.Client;
import com.aliyun.dysmsapi20180501.models.SendMessageWithTemplateRequest;
import com.aliyun.dysmsapi20180501.models.SendMessageWithTemplateResponse;
import com.aliyun.teaopenapi.models.Config;
import com.sreMake.SmsService;
import com.sreMake.conf.SmsAutoConfig;
import com.sreMake.exception.SmsException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Slf4j
public class AliyunSmsServiceImpl implements SmsService {
    private final Client client;
    private final SmsAutoConfig.AliyunSmsConfig aliyunSmsConfig;

    @SneakyThrows
    public AliyunSmsServiceImpl(SmsAutoConfig.AliyunSmsConfig aliyunSmsConfig) {
        this.aliyunSmsConfig = aliyunSmsConfig;
        Config config = new Config()
                .setAccessKeyId(aliyunSmsConfig.getAccessKeyId())
                .setAccessKeySecret(aliyunSmsConfig.getAccessKeySecret());
        config.endpoint = aliyunSmsConfig.getEndpoint();

        client = new Client(config);
    }


    @Override
    @SneakyThrows
    public void sendTemplateSms(@NotNull String to, @NotNull String templateCode, Map<String, String> templateParam) {
        SendMessageWithTemplateRequest req = new SendMessageWithTemplateRequest()
                .setTo(to)
                .setFrom(aliyunSmsConfig.getSignName())
                .setTemplateCode(templateCode)
                .setTemplateParam(JSONUtil.toJsonStr(templateParam));
        SendMessageWithTemplateResponse resp = client.sendMessageWithTemplate(req);
        log.info("sms sending results：{}", resp);
        if (resp.getStatusCode() != 200 || !resp.getBody().getResponseCode().equals("OK")) {
            throw new SmsException(JSONUtil.toJsonStr(req));
        }
    }
}
