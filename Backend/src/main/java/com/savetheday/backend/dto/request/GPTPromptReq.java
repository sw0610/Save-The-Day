package com.savetheday.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savetheday.backend.dto.Messages;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GPTPromptReq {
    private String model;
    private String prompt;
//    private List<Messages> message;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;


    @Builder
    public GPTPromptReq(String model, String prompt,
                             Integer maxTokens, Double temperature,
                             Double topP) {
        this.model = model;
        this.prompt = prompt;
//        this.message = message;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
    }
}
