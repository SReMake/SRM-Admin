import com.google.protobuf.gradle.id

group = "com.sreMake"
version = "0.0.1-SNAPSHOT"

plugins {
    alias(plugins.plugins.protobuf)
}

dependencies {
    implementation(project(":common"))

    implementation(starter.spring.boot.starter.web)
    implementation(grpc.grpc.core)
    runtimeOnly(grpc.grpc.netty)
    implementation(grpc.grpc.stub)
    implementation(grpc.grpc.kotlin.stub)
    implementation(grpc.grpc.protobuf)
    implementation(grpc.protobuf.java.util)
    implementation(grpc.protobuf.kotlin)
    implementation(grpc.protobuf.java)
}

protobuf {
    protoc {
        artifact = "${grpc.protoc.lib.get().group}:${grpc.protoc.lib.get().name}:${grpc.protoc.lib.get().version}"
    }
    plugins {
        id("grpc") {

            artifact = "${grpc.protoc.gen.grpc.java.get().group}:${grpc.protoc.gen.grpc.java.get().name}:${grpc.protoc.gen.grpc.java.get().version}"
        }
        id("grpckt") {
            artifact = "${grpc.protoc.gen.grpc.kotlin.get().group}:${grpc.protoc.gen.grpc.kotlin.get().name}:${grpc.protoc.gen.grpc.kotlin.get().version}:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }

    sourceSets {
        main {
            proto {
                srcDir("src/main/resources/protos") // 模块下的proto文件夹
//        include("**/*.proto")
            }
        }
    }

}