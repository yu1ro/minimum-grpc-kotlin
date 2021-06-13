import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins

group = "me.yu1ro"
version = "1.0-SNAPSHOT"
val grpcKotlinVer = "1.1.0"
val grpcProtobufVer = "1.38.0"

plugins {
    kotlin("jvm") version "1.5.10"
    id("com.google.protobuf") version "0.8.15"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVer")
    implementation("io.grpc:grpc-protobuf:$grpcProtobufVer")
    runtimeOnly("io.grpc:grpc-netty:$grpcProtobufVer")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.8.0"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcProtobufVer"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVer:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

sourceSets {
    main {
        resources {
            srcDir("src/main/proto")
        }
        java {
            srcDir("build/generated/source/proto/main/grpc")
            srcDir("build/generated/source/proto/main/grpckt")
            srcDir("build/generated/source/proto/main/java")
        }
    }
}