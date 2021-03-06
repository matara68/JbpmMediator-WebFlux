package com.inova.bpm.mediator.jbpmmediator.container.controller;

import com.inova.bpm.mediator.jbpmmediator.container.service.ContainerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/server/containers")
public class ContainerController {

    private static final Log LOGGER = LogFactory.getLog(ContainerController.class);

    @Autowired
    private ContainerService containerService;


    @GetMapping("/")
    public Mono<String> getContainers() {
        Mono<String> response = containerService.getContainers();
        return response;
    }


    @GetMapping("/{containerId}")
    public Mono<String> getContainerByContainerId(@PathVariable String containerId) {
        Mono<String> response = containerService.getContainerByContainerId(containerId);
        return response;
    }

    
    @PostMapping("/instances/{containerId}")
    public Mono<String> executeContainer(@PathVariable String containerId, @RequestBody String requestBody) {
        Mono<String> response = containerService.executeContainer(containerId, requestBody);
        return response;
    }
}
