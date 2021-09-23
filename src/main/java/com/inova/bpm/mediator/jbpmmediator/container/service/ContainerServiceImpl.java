package com.inova.bpm.mediator.jbpmmediator.container.service;

import com.inova.bpm.mediator.jbpmmediator.util.ExceptionHandler;
import com.inova.bpm.mediator.jbpmmediator.util.WebFluxConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ContainerServiceImpl implements ContainerService {

    private static final Log LOGGER = LogFactory.getLog(ContainerServiceImpl.class);

    @Autowired
    WebFluxConfig webFluxConfig;


    @Override
    public Mono<String> getContainers() {

        LOGGER.info("Running getContainers");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.get()
                .uri("/server/containers")
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed getContainers");

        return response;
    }


    @Override
    public Mono<String> getContainerByContainerId(String containerId) {

        LOGGER.info("Running getContainerByContainerId");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.get()
                .uri("/server/containers/" + containerId)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed getContainerByContainerId");

        return response;
    }


    @Override
    public Mono<String> executeContainer(String containerId, String requestBody) {

        LOGGER.info("Running executeContainer");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.post()
                .uri("/server/containers/instances/" + containerId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed executeContainer");

        return response;
    }
}
