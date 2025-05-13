package com.sreMake.conf;

import com.sreMake.SmsService;
import com.sreMake.impl.AliyunSmsServiceImpl;
import com.sreMake.impl.TencentCloudSmsServiceImpl;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SmsAutoConfig {
    @Bean
    @SneakyThrows
    public SmsService smsService(SmsProperties smsProperties) {
        if (smsProperties.getAliyun() != null && !smsProperties.getAliyun().isEmpty()) {
            return new AliyunSmsServiceImpl(smsProperties.getAliyun());
        } else if (smsProperties.getTencentCloud() != null && !smsProperties.getTencentCloud().isEmpty()) {
            return new TencentCloudSmsServiceImpl(smsProperties.getTencentCloud());
        }
        throw new RuntimeException("the sms service is not configured");
    }


}
