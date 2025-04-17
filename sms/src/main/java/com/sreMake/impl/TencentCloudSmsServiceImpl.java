package com.sreMake.impl;

import cn.hutool.json.JSONUtil;
import com.sreMake.SmsService;
import com.sreMake.conf.SmsAutoConfig;
import com.sreMake.exception.SmsException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedHashMap;

@Slf4j
public class TencentCloudSmsServiceImpl implements SmsService {
    private final SmsClient client;
    private final SmsAutoConfig.TencentCloudSmsConfig tencentCloudSmsConfig;

    public TencentCloudSmsServiceImpl(SmsAutoConfig.TencentCloudSmsConfig tencentCloudSmsConfig) {
        this.tencentCloudSmsConfig = tencentCloudSmsConfig;
        Credential cred = new Credential(tencentCloudSmsConfig.getSecretId(), tencentCloudSmsConfig.getSecretKey());
        client = new SmsClient(cred, "ap-guangzhou");
    }

    @Override
    @SneakyThrows
    public void sendTemplateSms(@NotNull String to, @NotNull String templateCode, LinkedHashMap<String, String> templateParam) {
        SendSmsRequest req = new SendSmsRequest();

        req.setSmsSdkAppId(tencentCloudSmsConfig.getAppId());

        req.setSignName(tencentCloudSmsConfig.getSignName());

        req.setTemplateId(templateCode);

        if (!templateParam.isEmpty()) {
            req.setTemplateParamSet(templateParam.values().toArray(new String[0]));
        }


        String[] phoneNumberSet = {"+86" + to};
        req.setPhoneNumberSet(phoneNumberSet);


        SendSmsResponse res = client.SendSms(req);

        log.info("sms sending results：{}", res);
        if (Arrays.stream(res.getSendStatusSet()).anyMatch(item -> !item.getCode().equals("Ok"))) {
            throw new SmsException(JSONUtil.toJsonStr(res));
        }
    }
}
