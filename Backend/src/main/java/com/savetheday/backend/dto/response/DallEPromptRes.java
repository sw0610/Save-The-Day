package com.savetheday.backend.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DallEPromptRes {
    private Long created;
    private List<DataRes> data;

}
