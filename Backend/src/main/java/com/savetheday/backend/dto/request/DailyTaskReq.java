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
public class DailyTaskReq {
    private String title;
    private LocalDate dueDate;
    private String importance;
    private String content;
}
