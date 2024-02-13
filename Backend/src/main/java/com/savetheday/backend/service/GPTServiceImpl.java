package com.savetheday.backend.service;

import com.savetheday.backend.config.GPTConfig;
import com.savetheday.backend.db.entity.AIResult;
import com.savetheday.backend.db.entity.Task;
import com.savetheday.backend.db.repository.AIResultRepository;
import com.savetheday.backend.db.repository.TaskRepository;
import com.savetheday.backend.dto.request.DallEPromptReq;
import com.savetheday.backend.dto.request.GPTPromptReq;
import com.savetheday.backend.dto.response.DallEPromptRes;
import com.savetheday.backend.dto.response.GPTPromptRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GPTServiceImpl implements GPTService{

    @Value("${gpt.key}")
    private String API_KEY;
    private final TaskRepository taskRepository;
    private final AIResultRepository aiResultRepository;

    private static final RestTemplate restTemplate = new RestTemplate();


    @Override
    public HttpHeaders makeHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(GPTConfig.MEDIA_TYPE));
        headers.add(GPTConfig.AUTHORIZATION, GPTConfig.BEARER + API_KEY);
        return headers;
    }

    @Override
    public HttpEntity<GPTPromptReq> buildGPTHttpEntity(GPTPromptReq req) {
        return new HttpEntity<>(req, makeHeader());
    }

    @Override
    public HttpEntity<DallEPromptReq> buildDalleHttpEntity(DallEPromptReq req) {
        return new HttpEntity<>(req, makeHeader());
    }

    public GPTPromptRes getGPTResponse(HttpEntity<GPTPromptReq> req) {

        ResponseEntity<GPTPromptRes> responseEntity = restTemplate.postForEntity(
                GPTConfig.URL,
                req,
                GPTPromptRes.class);

        return responseEntity.getBody();
    }

    public DallEPromptRes getDallEResponse(HttpEntity<DallEPromptReq> req) {

        return null;
    }


    @Override
    public String getGPTAnswer(String type, LocalDate date) throws URISyntaxException, IOException, InterruptedException {
        System.out.println(type);
        String res = null;

        if(type.equals("quote")){
            res = getTodayQuote(date).getChoices().get(0).getText();
            System.out.println(res);
            aiResultRepository.save(
                    AIResult.builder()
                            .taskDate(date)
                            .quote(res)
                            .build()
            );
        }
        else if(type.equals("fiction")){
            res = getTodayFiction(date).getChoices().get(0).getText();
            System.out.println(res);
            aiResultRepository.save(
                    AIResult.builder()
                            .taskDate(date)
                            .fiction(res)
                            .build()
            );
        } else if (type.equals("image")) {

            getTodayImage(date);
        }
        return res;
    }

    public String makePrompt(LocalDate date) {

        List<Task> tasks = taskRepository.findAllByMemberIdAndDueDateAndProcessStatus(1L, date, "Finished")
                .orElse(new ArrayList<>());

        return tasks.stream()
                .map(task -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(task.getTitle());
                    if(task.getContent()!=null){
                        sb.append("- ");
                        sb.append(task.getContent());
                    }
                    sb.append(", ");
                    sb.append(task.getEmotion());
                    return sb.toString();
                })
                .collect(Collectors.joining("\n"));
    }

    public GPTPromptRes getTodayQuote(LocalDate today) {
        String prompt =
                "오늘 나는 다음과 같은 일들을 했어.\n"
                +"오늘 한 일- 그 일의 세부 내용, 그 일을 할 때의 감정\n"
                +"이 순서로 제시할 거야.\n"
                + makePrompt(today)
                +"이를 바탕으로 나에게 가장 적합한 명언을 제공해줘.";
//                +"영어라면 한국말로 번역해서 줘";
        return this.getGPTResponse(
                this.buildGPTHttpEntity(
                        new GPTPromptReq(
                                GPTConfig.MODEL_GPT,
                                prompt,
                                GPTConfig.MAX_TOKEN,
                                GPTConfig.TEMPERATURE,
                                GPTConfig.TOP_P

                        )
                )
        );
    }

    public GPTPromptRes getTodayFiction(LocalDate today) {
        String prompt =
                "오늘 나는 다음과 같은 일들을 했어.\n"
                        +"오늘 한 일- 그 일의 세부 내용, 그 일을 할 때의 감정\n"
                        +"이 순서로 제시할 거야.\n"
                        + makePrompt(today)
                        +"이를 참고해서 짧은 소설을 써줘.";
//                +"영어라면 한국말로 번역해서 줘";
        return this.getGPTResponse(
                this.buildGPTHttpEntity(
                        new GPTPromptReq(
                                GPTConfig.MODEL_GPT,
                                prompt,
                                GPTConfig.MAX_TOKEN,
                                GPTConfig.TEMPERATURE,
                                GPTConfig.TOP_P

                        )
                )
        );
    }

    public GPTPromptRes getImagePrompt(LocalDate today) {
        String prompt =
                "오늘 나는 다음과 같은 일들을 했어.\n"
                        +"오늘 한 일- 그 일의 세부 내용, 그 일을 할 때의 감정\n"
                        +"이 순서로 제시할 거야.\n"
                        + makePrompt(today)
                        +"이게 영화 줄거리라고 생각하고 고양이가 주인공인 영화의 한 장면을 그려줘";
//                +"영어라면 한국말로 번역해서 줘";
        return this.getGPTResponse(
                this.buildGPTHttpEntity(
                        new GPTPromptReq(
                                GPTConfig.MODEL_GPT,
                                prompt,
                                GPTConfig.MAX_TOKEN,
                                GPTConfig.TEMPERATURE,
                                GPTConfig.TOP_P

                        )
                )
        );
    }

    public String getTodayMusic(LocalDate today) {
        return null;
    }

    public String getTodayImage(LocalDate today) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("---------");
        HttpClient client = HttpClient.newHttpClient();

        String prompt =
//                "고양이가 주인공인 하나의 그림을 만들어줘.\n"
//                        +"그림의 배경은 다음과 같은 일들을 기반으로 해.\n"
//                "Draw a storybook illustration"
                        makePrompt(today)+"\n"
                                +"이게 영화 줄거리라고 생각하고 내용을 요약하고 그 장면을 고양이를 주인공으로 해서 그려줘";

//                        +"Draw a scene that reminds me of what I did today";
        String promptEscape =prompt
                .replace("\n", "\\n");
        System.out.println(promptEscape);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openai.com/v1/images/generations")) // DALL-E API URL
                .header("Authorization", "Bearer "+API_KEY) // OpenAI key
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{"
                        + "\"model\": \"dall-e-3\","
                        + "\"prompt\": \"" + promptEscape + "\","
                        + "\"n\": 1,"
                        + "\"size\": \"1024x1024\""
                        + "}"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        return response.body();
    }
}
