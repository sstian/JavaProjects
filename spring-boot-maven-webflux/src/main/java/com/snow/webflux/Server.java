package com.snow.webflux;

import com.snow.webflux.handler.UserHandler;
import com.snow.webflux.service.UserService;
import com.snow.webflux.service.impl.UserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;


public class Server {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    // 1. 创建Router路由
    public RouterFunction<ServerResponse> routingFunction() {
        // 创建Router对象
        UserService userService = new UserServiceImpl();
        UserHandler handler = new UserHandler(userService);
        // 设置路由
        return RouterFunctions
                .route(GET("/user/{id}").and(accept(APPLICATION_JSON)), handler::gerUserById)
                .andRoute(GET("/user").and(accept(APPLICATION_JSON)), handler::getUsers);
    }

    // 2. 创建服务器完成适配
    public void createReactorServer() {
        // 路由和handler适配
        HttpHandler httpHandler = toHttpHandler(routingFunction());
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        //创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }
}
