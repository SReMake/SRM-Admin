package com.sreMake.conf;

import com.sreMake.SmsService;
import com.sreMake.impl.AliyunSmsServiceImpl;
import com.sreMake.impl.TencentCloudSmsServiceImpl;
import lombok.Data;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sms")
@Setter

public class SmsAutoConfig {

    private AliyunSmsConfig aliyun;

    private TencentCloudSmsConfig tencentCloud;

    @Bean
    @SneakyThrows
    public SmsService smsService() {
        if (aliyun != null && !aliyun.isEmpty()) {
            return new AliyunSmsServiceImpl(aliyun);
        } else if (tencentCloud != null && !tencentCloud.isEmpty()) {
            return new TencentCloudSmsServiceImpl(tencentCloud);
        }
        throw new RuntimeException("the sms service is not configured");
    }

    @Data
    public static class AliyunSmsConfig {
        private String accessKeyId;
        private String accessKeySecret;
        private String endpoint;
        private String signName;

        public boolean isEmpty() {
            return accessKeyId == null || accessKeyId.isEmpty() || accessKeySecret == null || accessKeySecret.isEmpty() || endpoint == null || endpoint.isEmpty() || signName == null || signName.isEmpty();
        }
    }

    @Data
    public static class TencentCloudSmsConfig {
        private String secretId;
        private String secretKey;
        private String appId;
        private String signName;

        public boolean isEmpty() {
            return secretId == null || secretId.isEmpty() || secretKey == null || secretKey.isEmpty() || appId == null || appId.isEmpty() || signName == null || signName.isEmpty();
        }
    }
}
