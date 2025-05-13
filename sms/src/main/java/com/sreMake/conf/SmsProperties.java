package com.sreMake.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sms")
@Data
@Component
public class SmsProperties {
    private AliyunSmsConfig aliyun;

    private TencentCloudSmsConfig tencentCloud;
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
