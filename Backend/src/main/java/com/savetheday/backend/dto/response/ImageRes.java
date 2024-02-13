package com.savetheday.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageRes {
    private String imgUrl;
    private String imgTask;
    private String imgEmotion;

}
