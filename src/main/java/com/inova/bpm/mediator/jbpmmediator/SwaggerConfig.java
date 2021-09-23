package com.inova.bpm.mediator.jbpmmediator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Log LOGGER = LogFactory.getLog(SwaggerConfig.class);

    ApiInfo apiInfo() {

        LOGGER.info("Setting up ApiInfo for Swagger configuration.");

        return new ApiInfoBuilder()
                .title("BPM Mediator for JBPM")
                .description("These APIs facilitate integration of JBPM.")
                .version("V1.0.0")
                .build();
    }

    @Bean
    public Docket docket() {

        LOGGER.info("Setting up Swagger configuration.");

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .build()
                .apiInfo(apiInfo());
    }

}
