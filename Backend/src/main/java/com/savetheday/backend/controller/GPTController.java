package com.savetheday.backend.controller;

import com.savetheday.backend.dto.request.AIResReq;
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
import java.time.format.DateTimeFormatter;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/open-ai")
public class GPTController {

    private final GPTService gptService;

    @GetMapping("/answer")
    public ResponseEntity<?> makeGPTAnswer(@RequestParam String type, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws URISyntaxException, IOException, InterruptedException {
        Long id = 1L;
        System.out.println(type);
        System.out.println(date);
        if(type.equals("image")){
            return ResponseEntity.ok(gptService.getDallEAnswer(date));
        } else {
            return ResponseEntity.ok(gptService.getGPTAnswer(type, date));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() throws URISyntaxException, IOException, InterruptedException {
        return null;
    }
    @GetMapping("/get-answer")
    public ResponseEntity<?> getGPTAnswer(@RequestParam String type, @RequestParam String date) {
        Long id = 1L;
        LocalDate localdate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        if(type.equals("image")){
            return ResponseEntity.ok(gptService.getDallERes(localdate));
        } else {
            return ResponseEntity.ok(gptService.getGPTRes(type, localdate));
        }
    }

    @PostMapping("/answer")
    public ResponseEntity<?> saveGPTAnswer(@RequestBody AIResReq req){
        gptService.saveGPTAnswer(req);
        return ResponseEntity.ok("SUCCESS");

    }

//    @PostMapping("/answer")
//    public ResponseEntity<String> createAnswerData(@RequestBody )

}
