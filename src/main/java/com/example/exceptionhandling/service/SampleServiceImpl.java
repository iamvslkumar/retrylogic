package com.example.exceptionhandling.service;

import com.example.exceptionhandling.controller.SampleController;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class SampleServiceImpl implements SampleService {

    private final RestTemplate restTemplate;

    public SampleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Retryable(value = {HttpServerErrorException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public String callExternalApi(SampleController.SampleRequest request) {
        // Simulating an external API call
        try {
            System.out.println("trying");
            return restTemplate.postForObject("https://httpstat.us/500", request, String.class);
        } catch (HttpServerErrorException e) {
            throw e;
        }
    }
}
