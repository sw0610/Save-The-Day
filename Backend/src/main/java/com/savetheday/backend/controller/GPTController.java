package com.savetheday.backend.controller;

import com.savetheday.backend.dto.response.DailyTaskListRes;
import com.savetheday.backend.service.GPTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/open-ai")
public class GPTController {

    private final GPTService gptService;

    @GetMapping("/answer")
    public ResponseEntity<String> getGPTAnswer(@RequestParam String type, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws URISyntaxException, IOException, InterruptedException {
        Long id = 1L;
        System.out.println(type);
        return ResponseEntity.ok(gptService.getGPTAnswer(type, date));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() throws URISyntaxException, IOException, InterruptedException {
        return null;
    }

}
