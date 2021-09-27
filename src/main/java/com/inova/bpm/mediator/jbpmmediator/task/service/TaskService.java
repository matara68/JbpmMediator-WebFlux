package com.inova.bpm.mediator.jbpmmediator.task.service;

import com.inova.bpm.mediator.jbpmmediator.dto.TaskSummary;
import reactor.core.publisher.Mono;

public interface TaskService {

    public Mono<String> claimTask(String containerId,
                                  String taskInstanceId,
                                  String user);

    public Mono<String> startTask(String containerId,
                                  String taskInstanceId,
                                  String user);

    public Mono<String> completeTask(String containerId,
                                     String taskInstanceId,
                                     String requestBody,
                                     String user);

    public Mono<String> delegateTask(String containerId,
                                     String taskInstanceId,
                                     String targetUser,
                                     String user);

    public Mono<String> releaseTask(String containerId,
                                    String taskInstanceId,
                                    String user);

    public Mono<String> getOwnedTasks(String user);

    public Mono<String> getPotOwnerTasks(String user);


}
