package com.savetheday.backend.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DallEPromptReq {
    private String model;
    private String prompt;
    private int n;
    private String size;
}
