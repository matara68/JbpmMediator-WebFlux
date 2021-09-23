package com.inova.bpm.mediator.jbpmmediator.process.controller;

import com.inova.bpm.mediator.jbpmmediator.process.service.ProcessService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/server/containers")
public class ProcessController {

    private static final Log LOGGER = LogFactory.getLog(ProcessController.class);

    @Autowired
    ProcessService processService;

    @ApiOperation(value = "Starts a new process instance of the specified process.")
    @PostMapping("/{containerId}/processes/{processId}/instances")
    public Mono<String> startProcessInstance(@PathVariable String containerId,
                                             @PathVariable String processId,
                                             @RequestBody String requestBody) {
        Mono<String> response = processService.startProcessInstance(containerId, processId, requestBody);
        return response;
    }


    @ApiOperation(value = "Aborts a specified process instance in a specified KIE container.")
    @DeleteMapping("/{containerId}/processes/instances/{processInstanceId}")
    public Mono<String> abortProcessInstance(@PathVariable String containerId,
                                             @PathVariable String processInstanceId) {
        Mono<String> response = processService.abortProcessInstance(containerId, processInstanceId);
        return response;
    }


    @ApiOperation(value = "Returns a list of process instances in a specified KIE container.")
    @GetMapping("/{containerId}/processes/instances")
    public Mono<String> getProcessInstances(@PathVariable String containerId) {
        Mono<String> response = processService.getProcessInstances(containerId);
        return response;
    }


    @ApiOperation(value = "Returns information about a specified process instance in a specified KIE container.")
    @GetMapping("/{containerId}/processes/instances/{processInstanceId}")
    public Mono<String> getProcessInstanceByInstanceId(@PathVariable String containerId,
                                                       @PathVariable String processInstanceId) {
        Mono<String> response = processService.getProcessInstanceByInstanceId(containerId, processInstanceId);
        return response;
    }


    @ApiOperation(value = "Returns an annotated SVG image file of a specified process instance diagram.")
    @GetMapping("/{containerId}/images/processes/instances/{processInstanceId}")
    public Mono<String> getProcessInstanceImageByInstanceId(@PathVariable String containerId,
                                                            @PathVariable String processInstanceId) {
        Mono<String> response = processService.getProcessInstanceImageByInstanceId(containerId, processInstanceId);
        return response;
    }
}
