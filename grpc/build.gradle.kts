import com.google.protobuf.gradle.id

group = "com.sreMake"
version = "0.0.1-SNAPSHOT"

plugins {
    alias(plugins.plugins.protobuf)
}

dependencies {
    implementation(starter.spring.boot.starter.web)
    implementation(grpc.grpc.core)
    runtimeOnly(grpc.grpc.netty)
    implementation(grpc.grpc.stub)
    implementation(grpc.grpc.kotlin.stub)
    implementation(grpc.grpc.protobuf)
    implementation(grpc.protoc.gen.grpc.kotlin)
    implementation(grpc.protobuf.java.util)
    implementation(grpc.protobuf.kotlin)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.27.0"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.64.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
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