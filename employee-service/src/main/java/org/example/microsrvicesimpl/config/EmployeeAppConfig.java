package org.example.microsrvicesimpl.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeAppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebClient webClient(@Value("${addressservice.base.url}") String addressBaseURL) {
        return WebClient.builder()
                .baseUrl(addressBaseURL)
                .build();
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
