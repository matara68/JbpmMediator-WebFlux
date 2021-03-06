package com.inova.bpm.mediator.jbpmmediator.container.service;

import reactor.core.publisher.Mono;

import java.util.List;

public interface ContainerService {

    public Mono<String> getContainers();

    public Mono<String> getContainerByContainerId(String containerId);

    public Mono<String> executeContainer(String containerId, String requestBody);

}
