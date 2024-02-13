package com.savetheday.backend.service;

import com.savetheday.backend.dto.request.DallEPromptReq;
import com.savetheday.backend.dto.request.GPTPromptReq;
import com.savetheday.backend.dto.response.GPTPromptRes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

public interface GPTService {

    HttpHeaders makeHeader();
    HttpEntity<GPTPromptReq> buildGPTHttpEntity(GPTPromptReq req);
    HttpEntity<DallEPromptReq> buildDalleHttpEntity(DallEPromptReq req);
    String getGPTAnswer(String type, LocalDate date) throws URISyntaxException, IOException, InterruptedException;


}
