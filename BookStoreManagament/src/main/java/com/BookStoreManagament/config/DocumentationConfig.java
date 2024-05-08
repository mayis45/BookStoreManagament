package com.BookStoreManagament.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class DocumentationConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Mayis Qarayev Back-end Developer")
                        .version("0.1.0")
                        .description("BookStore projectinin API leri")
                        .contact(
                                new Contact()
                                        .url("www.linkedin.com/in/mayis-qarayev75")
                                        .email("mayisqarayev75@gmail.com")
                                        .name("Mayis Qarayev")
                        )
        );

    }
}
