package com.savetheday.backend.service;

import com.google.gson.Gson;
import com.savetheday.backend.config.GPTConfig;
import com.savetheday.backend.db.entity.AIResult;
import com.savetheday.backend.db.entity.Task;
import com.savetheday.backend.db.repository.AIResultRepository;
import com.savetheday.backend.db.repository.TaskRepository;
import com.savetheday.backend.dto.request.AIResReq;
import com.savetheday.backend.dto.request.DallEPromptReq;
import com.savetheday.backend.dto.request.GPTPromptReq;
import com.savetheday.backend.dto.response.DallEPromptRes;
import com.savetheday.backend.dto.response.GPTPromptRes;
import com.savetheday.backend.dto.response.ImageRes;
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


    @Override
    public String getGPTAnswer(String type, LocalDate date) {
        System.out.println(type);
        String res = null;

        if(type.equals("quote")){
            res = getTodayQuote(date).getChoices().get(0).getText();

        }
        else if(type.equals("fiction")){
            res = getTodayFiction(date);

        }
        return res;
    }

    @Override
    public ImageRes getDallEAnswer(LocalDate date) throws URISyntaxException, IOException, InterruptedException {
        return getTodayImage(date);
    }

    @Override
    public void saveGPTAnswer(AIResReq req) {
        if(req.getType().equals("image")){
            AIResult aiResult = aiResultRepository.findByTaskDate(req.getDate()).orElse(null);
            if(aiResult==null || aiResult.getImgUrl()==null){
                aiResultRepository.save(
                        AIResult.builder()
                                .taskDate(req.getDate())
                                .imgUrl(req.getImgUrl())
                                .imgTask(req.getTitle())
                                .imgEmotion(req.getEmotion())
                                .build()
                );
            }else{
                aiResult.updateImg(req.getImgUrl(), req.getTitle(), req.getEmotion());
            }



        }else if(req.getType().equals("quote")){
            AIResult aiResult = aiResultRepository.findByTaskDate(req.getDate()).orElse(null);
            if(aiResult==null || aiResult.getImgUrl()==null) {

                aiResultRepository.save(
                        AIResult.builder()
                                .taskDate(req.getDate())
                                .quote(req.getQuote())
                                .build()
                );
            }else{
                aiResult.updateQuote(req.getQuote());
            }
        }else if(req.getType().equals("fiction")){
            AIResult aiResult = aiResultRepository.findByTaskDate(req.getDate()).orElse(null);
            if(aiResult==null || aiResult.getImgUrl()==null) {

                aiResultRepository.save(
                        AIResult.builder()
                                .taskDate(req.getDate())
                                .fiction(req.getFiction())
                                .build()
                );
            }
            else{
                aiResult.updateFiction(req.getFiction());
            }
        }
    }

    @Override
    public ImageRes getDallERes(LocalDate date) {
        AIResult aiResult = aiResultRepository.findByTaskDate(date).orElse(null);

        return ImageRes.builder()
                        .imgUrl(aiResult.getImgUrl())
                .imgEmotion(aiResult.getImgEmotion())
                .imgTask(aiResult.getImgTask())
                .build();

    }

    @Override
    public String getGPTRes(String type, LocalDate date) {
        System.out.println(date);
        AIResult aiResult = aiResultRepository.findByTaskDate(date).orElse(null);
        if(aiResult==null){
            return null;
        }else{
            if (type.equals("quote")) {
                return aiResult.getQuote();
            } else {
                System.out.println(aiResult.getFiction());
                return aiResult.getFiction();
            }
        }


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

    public String getTodayFiction(LocalDate today) {
        String prompt =
//                "오늘 나는 다음과 같은 일들을 했어.\n"
//                        +"오늘 한 일- 그 일의 세부 내용, 그 일을 할 때의 감정\n"
//                        +"이 순서로 제시할 거야.\n"
                        makePrompt(today)+"\n"
                        +"Refer to this, write a short fiction no more than 10 sentences";
//                +"영어라면 한국말로 번역해서 줘";
        GPTPromptRes res =  this.getGPTResponse(
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

        return translateInKorean(res.getChoices().get(0).getText());

    }

    public String translateInKorean(String fiction) {
        String prompt =
                fiction+"\n"+
                "Translate this into Korean. Very naturally.";
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
        ).getChoices().get(0).getText();
    }




    public String getTodayMusic(LocalDate today) {
        return null;
    }

    public Task getRandomFinishedTask(LocalDate date){
        List<Task> tasks = taskRepository.findAllByMemberIdAndDueDateAndProcessStatus(1L, date, "Finished")
                .orElse(new ArrayList<>());
        return tasks.get((int) (Math.random()*tasks.size()));
    }

    public String getDallERes(String prompt) throws URISyntaxException, IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        String promptEscape =prompt
                .replace("\n", "\\n");

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

        return response.body();

    }

    public DallEPromptRes getImageData(String json){
        Gson gson = new Gson();
        DallEPromptRes res = gson.fromJson(json, DallEPromptRes.class);
        return res;

    }

    public ImageRes getTodayImage(LocalDate date) throws URISyntaxException, IOException, InterruptedException {

        Task task = getRandomFinishedTask(date);
        String title = task.getTitle();
        String emotion = task.getEmotion();

        String prompt = "고양이가 "+emotion+"의 감정으로 "+title+"를 하고있는 모습";
        String jsonRes = getDallERes(prompt);
        DallEPromptRes res = getImageData(jsonRes);



        return ImageRes.builder()
                .imgUrl(res.getData().get(0).getUrl())
                .imgTask(title)
                .imgEmotion(emotion)
                .build();

    }


}
