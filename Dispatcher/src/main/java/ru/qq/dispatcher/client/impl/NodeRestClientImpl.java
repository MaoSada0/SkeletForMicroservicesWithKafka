package ru.qq.dispatcher.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import ru.qq.dispatcher.client.NodeRestClient;

@Component
@RequiredArgsConstructor
public class NodeRestClientImpl implements NodeRestClient {

    private final WebClient restClient;

    @Override
    public boolean checkIsActive(Long userId) {
        Boolean isActive = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user/activation")
                        .queryParam("userid", userId)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return Boolean.TRUE.equals(isActive);
    }

}
