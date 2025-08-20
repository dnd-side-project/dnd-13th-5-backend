package com.dnd.sub.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI waguWaguOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("WaguWagu API")
                .description("WaguWagu 백엔드 API 명세서 입니다")
                .version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi authGroup() {
        return GroupedOpenApi.builder()
            .group("Auth")
            .pathsToMatch("/api/auth/**")
            .build();
    }

    @Bean
    public GroupedOpenApi memberGroup() {
        return GroupedOpenApi.builder()
            .group("Member")
            .pathsToMatch("/api/member/**")
            .build();
    }

}
