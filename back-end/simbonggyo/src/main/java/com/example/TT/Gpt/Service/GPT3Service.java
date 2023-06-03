package com.example.TT.Gpt.Service;

import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

@Service
public class GPT3Service {
    private final String apiKey = "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"; // OpenAI API 키를 여기에 입력해주세요
    private final OkHttpClient client = new OkHttpClient();

    public String processRequest(String jsonInput) throws IOException {
        String url = "https://api.openai.com/v1/models/text-davinci-003/completions";
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonInput, mediaType);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return jsonInput;
            }
        }
    }
}
//package com.example.TT.Gpt.Service;
//
//import okhttp3.*;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class GPT3Service {
//private final String apiKey = "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";
//private final OkHttpClient client = new OkHttpClient();
//
//public String processRequest(String jsonInput) throws IOException {
//    String url = "https://api.openai.com/v1/models/text-davinci-003";
//    RequestBody body = RequestBody.create(jsonInput, MediaType.get("application/json; charset=utf-8"));
//    
//    Request request = new Request.Builder()
//            .url(url)
//            .addHeader("Content-Type", "application/json; charsets=UTF-8")
//            .addHeader("Authorization", "Bearer " + apiKey)
//            .post(body)
//            .build();
//
//    try (Response response = client.newCall(request).execute()) {
//        if (response.isSuccessful()) {
//            return response.body().string();
//        } else {
//            return jsonInput;
//        }
//    }
//}
//}