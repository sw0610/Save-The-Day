package com.savetheday.backend.config;

public class GPTConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String MODEL_GPT = "gpt-3.5-turbo-instruct";
    public static final String MODEL_DALLE = "dall-e-3";
    public static final Integer MAX_TOKEN = 1000;
    public static final Double TEMPERATURE = 1.0;
    public static final Double TOP_P = 1.0;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String URL = "https://api.openai.com/v1/completions";
    public static final String DALLE_URL = "https://api.openai.com/v1/completions";

}
