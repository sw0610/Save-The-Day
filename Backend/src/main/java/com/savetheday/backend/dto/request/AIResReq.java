package com.savetheday.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AIResReq {
    private LocalDate date;
    private String type;
    private String title;
    private String imgUrl;
    private String emotion;
    private String quote;
    private String fiction;
}
