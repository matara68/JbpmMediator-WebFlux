package com.inova.bpm.mediator.jbpmmediator.task.controller;

import com.inova.bpm.mediator.jbpmmediator.dto.TaskSummary;
import com.inova.bpm.mediator.jbpmmediator.task.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/server")
public class TaskController {

    private static final Log LOGGER = LogFactory.getLog(TaskController.class);

    @Autowired
    TaskService taskService;


    @ApiOperation(value = "Claims (reserves) a specified task instance for the user sending the request.")
    @PutMapping("/containers/{containerId}/tasks/{taskInstanceId}/states/claimed")
    public Mono<String> claimTask(@PathVariable String containerId,
                                  @PathVariable String taskInstanceId,
                                  @RequestParam String user) {
        Mono<String> response = taskService.claimTask(containerId, taskInstanceId, user);
        return response;
    }


    @ApiOperation(value = "Starts a specified task instance.")
    @PutMapping("/containers/{containerId}/tasks/{taskInstanceId}/states/started")
    public Mono<String> startTask(@PathVariable String containerId,
                                  @PathVariable String taskInstanceId,
                                  @RequestParam String user) {
        Mono<String> response = taskService.startTask(containerId, taskInstanceId, user);
        return response;
    }


    @ApiOperation(value = "Completes a specified task instance.")
    @PutMapping("/containers/{containerId}/tasks/{taskInstanceId}/states/completed")
    public Mono<String> completeTask(@PathVariable String containerId,
                                     @PathVariable String taskInstanceId,
                                     @RequestBody String requestBody,
                                     @RequestParam String user) {
        Mono<String> response = taskService.completeTask(containerId, taskInstanceId, requestBody, user);
        return response;
    }


    @ApiOperation(value = "Delegates a specified task instance to a specified target user as the new task owner.")
    @PutMapping("/containers/{containerId}/tasks/{taskInstanceId}/states/delegated")
    public Mono<String> delegateTask(@PathVariable String containerId,
                                     @PathVariable String taskInstanceId,
                                     @RequestParam String targetUser,
                                     @RequestParam String user) {
        Mono<String> response = taskService.delegateTask(containerId, taskInstanceId, targetUser, user);
        return response;
    }


    @ApiOperation(value = "Releases a specified task instance from being claimed by the task owner.")
    @PutMapping("/containers/{containerId}/tasks/{taskInstanceId}/states/released")
    public Mono<String> releaseTask(@PathVariable String containerId,
                                    @PathVariable String taskInstanceId,
                                    @RequestParam String user) {
        Mono<String> response = taskService.releaseTask(containerId, taskInstanceId, user);
        return response;
    }


    @ApiOperation(value = "Returns task instances that the querying user owns.")
    @GetMapping("/queries/tasks/instances/owners")
    public Mono<String> getOwnedTasks(@RequestParam String user) {
        Mono<String> response = taskService.getOwnedTasks(user);
        return response;
    }


    @ApiOperation(value = "Returns tasks with a user defined as a potential owner.")
    @GetMapping("/queries/tasks/instances/pot-owners")
    public Mono<String> getPotOwnerTasks(@RequestParam String user) {
        Mono<String> response = taskService.getPotOwnerTasks(user);
        return response;
    }



}
