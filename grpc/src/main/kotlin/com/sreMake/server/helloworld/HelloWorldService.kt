package com.sreMake.server.helloworld

import com.sreMake.conf.GrpcServer
import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloRequest
import io.grpc.examples.helloworld.helloReply

@GrpcServer
class HelloWorldService : GreeterGrpcKt.GreeterCoroutineImplBase() {
    override suspend fun sayHello(request: HelloRequest) =
        helloReply {
            message = "Hello ${request.name}"
        }
}