package com.sreMake.conf

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "grpc")
data class GrpcProperties(
    val port: Int = 50051,
)