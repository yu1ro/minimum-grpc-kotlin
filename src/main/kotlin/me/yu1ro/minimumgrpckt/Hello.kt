package me.yu1ro.minimumgrpckt

import io.grpc.Server
import io.grpc.ServerBuilder
import me.yu1ro.minimumgrpckt.helloworld.GreeterGrpcKt
import me.yu1ro.minimumgrpckt.helloworld.HelloReply
import me.yu1ro.minimumgrpckt.helloworld.HelloRequest

class Hello (private val port: Int) {
    val server: Server = ServerBuilder
        .forPort(port)
        .addService(HelloWorldService())
        .build()

    private class HelloWorldService : GreeterGrpcKt.GreeterCoroutineImplBase() {
        override suspend fun sayHello(request: HelloRequest) = HelloReply
            .newBuilder()
            .setMessage("Hello ${request.name}")
            .build()
    }
}