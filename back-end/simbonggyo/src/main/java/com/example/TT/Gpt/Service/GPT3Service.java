package com.example.TT.Gpt.Service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class GPT3Service {
    private final String apiKey = "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"; // OpenAI API 키를 여기에 입력해주세요
    private final OkHttpClient client = new OkHttpClient();

    public String processRequest(String jsonInput) throws IOException {
        String url = "https://api.openai.com/v1/completions";
        MediaType mediaType = MediaType.parse("application/json; charset = UTF-8");
        RequestBody body = RequestBody.create(jsonInput, mediaType);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
        	if (response.isSuccessful()) {
        		String responseJson = null;
                responseJson = response.body().string();
                System.out.println("반응 성공");
                System.out.println(responseJson); // 응답 JSON 출력

                // 응답 JSON 파싱
                JsonObject responseObj = new Gson().fromJson(responseJson, JsonObject.class);
                if (responseObj.has("choices") && responseObj.get("choices").isJsonArray()) {
                    JsonArray choicesArray = responseObj.getAsJsonArray("choices");
                    if (!choicesArray.isJsonNull() && choicesArray.size() > 0) {
                        JsonObject choiceObject = choicesArray.get(0).getAsJsonObject();
                        if (choiceObject.has("text")) {
                            String generatedText = choiceObject.get("text").getAsString();
                            System.out.println("생성된 텍스트: " + generatedText);
                            return generatedText;
                        }
                    }
                }
                
            } else {
            	System.out.println("반응 실패");
            	System.out.println("메시지입니다."+ response.code());
                return jsonInput;
            }
        }
		return jsonInput;
    }
}
