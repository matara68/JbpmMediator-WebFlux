package com.inova.bpm.mediator.jbpmmediator.process.service;


import reactor.core.publisher.Mono;

public interface ProcessService {


    public Mono<String> startProcessInstance(String containerId,
                                             String processId,
                                             String requestBody);


    public Mono<String> abortProcessInstance(String containerId,
                                             String processInstanceId);


    public Mono<String> getProcessInstances(String containerId);


    public Mono<String> getProcessInstanceByInstanceId(String containerId,
                                                       String processInstanceId);


    public Mono<String> getProcessInstanceImageByInstanceId(String containerId,
                                                            String processInstanceId);

}
