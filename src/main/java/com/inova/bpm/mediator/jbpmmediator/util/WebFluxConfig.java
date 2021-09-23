package com.inova.bpm.mediator.jbpmmediator.util;

import io.netty.handler.logging.LogLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebFlux
@PropertySource("classpath:/config/kie-server.config.properties")
public class WebFluxConfig implements WebFluxConfigurer {

    private static final Log LOGGER = LogFactory.getLog(WebFluxConfig.class);

    @Value("${kie-server.baseUrl}")
    private String kie_serverBaseUrl;

    @Value("${kie-server.adminUserName}")
    private String kie_serverAdminUserName;

    @Value("${kie-server.adminUserPassword}")
    private String kie_serverAdminUserPassword;

    private WebClient webClient;


    @PostConstruct
    private void createWebClient() {
        LOGGER.info("Creating WebClient.");

        HttpClient httpClient = HttpClient
                .create()
                .wiretap(this.getClass().getCanonicalName(),
                        LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);

        this.webClient = WebClient
                .builder()
                .baseUrl(kie_serverBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(kie_serverAdminUserName, kie_serverAdminUserPassword))
                .build();

        LOGGER.info("WebClient successfully created.");
    }


    public WebClient getWebClient() {
        return this.webClient;
    }

}
