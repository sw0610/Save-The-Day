package com.savetheday.backend.dto.response;

import com.savetheday.backend.dto.Choice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class GPTPromptRes {
    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;
}
