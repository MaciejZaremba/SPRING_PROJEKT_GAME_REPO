package com.pjait.Games_Client.config;

import com.pjait.Games_Data.exceptions.EntityAlreadyExists;
import com.pjait.Games_Data.exceptions.EntityNotFound;
import com.pjait.Games_Data.exceptions.InvalidInput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@ComponentScan
@Configuration
public class RestClientConfig {
    @Bean
    public RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8083/api")
                .defaultStatusHandler(
                        HttpStatusCode::is4xxClientError,
                        (request, response) -> {
                            HttpStatus status = HttpStatus.valueOf(response.getStatusCode().value());
                            switch (status) {
                                case CONFLICT -> throw new EntityAlreadyExists();
                                case NOT_FOUND -> throw new EntityNotFound();
                                case BAD_REQUEST -> throw new InvalidInput();
                                default -> throw new RuntimeException("Unexpected status: " + status);
                            }
                        }
                )
                .build();
    }
}