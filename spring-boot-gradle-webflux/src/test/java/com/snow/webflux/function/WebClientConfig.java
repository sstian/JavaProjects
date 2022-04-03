//package com.snow.webflux.function;
//
//import io.netty.channel.ChannelOption;
//import io.netty.handler.ssl.SslContext;
//import io.netty.handler.ssl.SslContextBuilder;
//import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
//import io.netty.handler.timeout.ReadTimeoutHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.reactive.ClientHttpConnector;
//import org.springframework.http.client.reactive.ReactorClientHttpConnector;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.netty.http.client.HttpClient;
//
//import javax.net.ssl.SSLException;
//import java.util.concurrent.TimeUnit;
//
//
//@Slf4j
//@Configuration
//public class WebClientConfig {
//
//    private String assembleBaseUrl = "http://localhost:8080";
//    private int assembleConnectionTimeout = 3000;
//    private int assembleReadTimeout = 30000;
//
//    @Bean
//    public WebClient assembleBaseWebClient() {
//        try {
//            SslContext sslContext = SslContextBuilder.forClient()
//                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
//            HttpClient httpClient = HttpClient.create()
//                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, assembleConnectionTimeout)
//                    .doOnConnected(connection -> connection.addHandlerLast(
//                            new ReadTimeoutHandler(assembleReadTimeout, TimeUnit.MILLISECONDS)))
//                    .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));
//            ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
//            return WebClient.builder()
//                    .baseUrl(this.assembleBaseUrl)
//                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .clientConnector(connector)
//                    .build();
//        } catch (SSLException e) {
//            log.error("Caught exception in creating Assemble base WebClient bean.", e);
//            return null;
//        }
//    }
//}
