package ru.qq.dispatcher.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import ru.qq.dispatcher.component.TelegramBot;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${project.node.api}")
    private String QR_API_URI;

    @Bean
    public WebClient restClient(){
        return WebClient.builder()
                .baseUrl(QR_API_URI)
                .build();
    }

}
