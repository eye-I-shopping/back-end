package com.example.TT.Gpt.Service;

import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

@Service
public class GPT3Service {
private final String apiKey = "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";
private final OkHttpClient client = new OkHttpClient();

public String processRequest(String jsonInput) throws IOException {
    String url = "https://api.openai.com/v1/engines/davinci-codex/completions";
    RequestBody body = RequestBody.create(jsonInput.getBytes(StandardCharsets.UTF_8));
    
    Request request = new Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json; charsets=UTF-8")
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