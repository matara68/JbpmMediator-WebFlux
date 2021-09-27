package com.inova.bpm.mediator.jbpmmediator.task.service;

import com.inova.bpm.mediator.jbpmmediator.dto.TaskSummary;
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
public class TaskServiceImpl implements TaskService {

    private static final Log LOGGER = LogFactory.getLog(TaskServiceImpl.class);

    @Autowired
    WebFluxConfig webFluxConfig;


    @Override
    public Mono<String> claimTask(String containerId, String taskInstanceId, String user) {

        LOGGER.info("Running claimTask");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.put()
                .uri("/server/containers/" + containerId + "/tasks/" + taskInstanceId + "/states/claimed?user=" + user)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed claimTask");

        return response;
    }


    @Override
    public Mono<String> startTask(String containerId, String taskInstanceId, String user) {

        LOGGER.info("Running startTask");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.put()
                .uri("/server/containers/" + containerId + "/tasks/" + taskInstanceId + "/states/started?user=" + user)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed startTask");

        return response;
    }


    @Override
    public Mono<String> completeTask(String containerId, String taskInstanceId, String requestBody, String user) {

        LOGGER.info("Running completeTask");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.put()
                .uri("/server/containers/" + containerId + "/tasks/" + taskInstanceId + "/states/completed?user=" + user)
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

        LOGGER.info("Successfully completed completeTask");

        return response;
    }


    @Override
    public Mono<String> delegateTask(String containerId, String taskInstanceId, String targetUser, String user) {

        LOGGER.info("Running delegateTask");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.put()
                .uri("/server/containers/" + containerId + "/tasks/" + taskInstanceId + "/states/delegated?targetUser=" + targetUser + "&user=" + user)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed delegateTask");

        return response;
    }


    @Override
    public Mono<String> releaseTask(String containerId, String taskInstanceId, String user) {

        LOGGER.info("Running releaseTask");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.put()
                .uri("/server/containers/" + containerId + "/tasks/" + taskInstanceId + "/states/released?user=" + user)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed releaseTask");

        return response;
    }


    @Override
    public Mono<String> getOwnedTasks(String user) {

        LOGGER.info("Running getOwnedTasks");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.get()
                .uri("/server/queries/tasks/instances/owners?user=" + user)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed getOwnedTasks");

        return response;
    }


    @Override
    public Mono<String> getPotOwnerTasks(String user) {

        LOGGER.info("Running getPotOwnerTasks");

        WebClient webClient = webFluxConfig.getWebClient();
        Mono<String> response = webClient.get()
                .uri("/server/queries/tasks/instances/pot-owners?user=" + user)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new ExceptionHandler("4xx Client Error"));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new ExceptionHandler("5xx Server Error"));
                    } else {
                        return clientResponse.bodyToMono(String.class);
                    }
                });

        LOGGER.info("Successfully completed getPotOwnerTasks");

        return response;
    }





}
