package com.sreMake.conf

import com.sreMake.common.utils.logger
import io.grpc.BindableService
import io.grpc.Server
import io.grpc.ServerBuilder
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.SmartLifecycle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GrpcProperties::class)
@ConditionalOnClass(BindableService::class)
open class GrpcAutoConfigure() {
    @Bean
    open fun grpcServer(grpcProperties: GrpcProperties, bindableServices: List<BindableService>): Server {
        return ServerBuilder
            .forPort(grpcProperties.port)
            .apply {
                bindableServices.forEach {
                    logger().info("gRPC service registered: ${it.javaClass.simpleName}")
                    addService(it)
                }
            }
            .build()
    }

    @Bean()
    open fun grpcLifecycle(grpcServer: Server): SmartLifecycle {
        return object : SmartLifecycle {
            private var running = false

            override fun start() {
                if (!running) {
                    logger().info("gRPC server starting")
                    grpcServer.start()
                    logger().info("gRPC server started on port ${grpcServer.port}")
                    running = true
                }
            }

            override fun stop() {
                if (running) {
                    logger().info("gRPC server shutdown initiated")
                    grpcServer.shutdown()
                    logger().info("gRPC server shutdown completed")
                    running = false
                }
            }

            override fun isRunning(): Boolean = running
        }
    }
}