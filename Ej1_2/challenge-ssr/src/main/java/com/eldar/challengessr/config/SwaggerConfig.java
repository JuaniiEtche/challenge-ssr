package com.eldar.challengessr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi openApiDatabase() {
        return GroupedOpenApi.builder()
                .group("DATABASE")
                .packagesToScan("com.eldar.challengessr.api.controllers.database")
                .build();
    }

    @Bean
    public GroupedOpenApi openApiCSV() {
        return GroupedOpenApi.builder()
                .group("CSV")
                .packagesToScan("com.eldar.challengessr.api.controllers.csv")
                .build();
    }

    @Bean
    public GroupedOpenApi openApiHibryd() {
        return GroupedOpenApi.builder()
                .group("Hibryd")
                .packagesToScan("com.eldar.challengessr.api.controllers.hibryd")
                .build();
    }

    @Bean
    public OpenAPI springOpenApi() {
        return new OpenAPI()
                .info(new Info().title("ELDAR - Challenge SSR")
                        .contact(new Contact().name("Juan Ignacio Etcheverry").email("juan.etcheverry@eldars.com.ar"))
                        .version("1.0"));

    }
}