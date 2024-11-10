package com.example.exceptionhandling.service;

import com.example.exceptionhandling.controller.SampleController;

public interface SampleService {
    String callExternalApi(SampleController.SampleRequest request);
}
