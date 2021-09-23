package com.inova.bpm.mediator.jbpmmediator.process.service;

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
public class ProcessServiceImpl implements ProcessService {

    private static final Log LOGGER = LogFactory.getLog(ProcessServiceImpl.class);

    @Autowired
    WebFluxConfig webFluxConfig;

    @Override
    public Mono<String> startProcessInstance(String containerId, String processId, String requestBody) {

        LOGGER.info("Running startProcessInstance");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.post()
                .uri("/server/containers/" + containerId + "/processes/" + processId + "/instances")
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

        LOGGER.info("Successfully completed startProcessInstance");

        return response;
    }


    @Override
    public Mono<String> abortProcessInstance(String containerId, String processInstanceId) {

        LOGGER.info("Running abortProcessInstance");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.delete()
                .uri("/server/containers/" + containerId + "/processes/instances/" + processInstanceId)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed abortProcessInstance");

        return response;
    }


    @Override
    public Mono<String> getProcessInstances(String containerId) {

        LOGGER.info("Running getProcessInstances");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.get()
                .uri("/server/containers/" + containerId + "/processes/instances")
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed getProcessInstances");

        return response;
    }


    @Override
    public Mono<String> getProcessInstanceByInstanceId(String containerId, String processInstanceId) {

        LOGGER.info("Running getProcessInstanceByInstanceId");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.get()
                .uri("/server/containers/" + containerId + "/processes/instances/" + processInstanceId)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed getProcessInstanceByInstanceId");

        return response;
    }


    @Override
    public Mono<String> getProcessInstanceImageByInstanceId(String containerId, String processInstanceId) {

        LOGGER.info("Running getProcessInstanceImageByInstanceId");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.get()
                .uri("/server/containers/" + containerId + "/images/processes/instances/" + processInstanceId)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed getProcessInstanceImageByInstanceId");

        return response;
    }

}
