package com.example.TT.item.controller;

import com.example.TT.Gpt.Service.GPT3Service;
import com.example.TT.item.dto.itemDto3;
import com.example.TT.item.entity.test1;
import com.example.TT.item.service.Test1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

@Slf4j
@RestController
public class itemcontroller11 {
    private final Test1Service test1Service;
    private final GPT3Service gpt3Service;

    public itemcontroller11(Test1Service test1Service, GPT3Service gpt3Service) {
        this.test1Service = test1Service;
        this.gpt3Service = gpt3Service;
    }

    @PostMapping(value = "/api/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleJSONData(@RequestBody(required = false) itemDto3[] itemsList) {
        // Check if itemsList is not null or empty
        if (itemsList == null || itemsList.length == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        System.setOut(new PrintStream(System.out,true,StandardCharsets.UTF_8));
        
        // Initialize maxConfidence and maxConfidenceItem
        double maxConfidence = 0.0;
        itemDto3 maxConfidenceItem = null;

        // Iterate through all items and log their values
        for (itemDto3 item : itemsList) {
            log.info("Item Info:");
            log.info("xmin: {}", item.getXmin());
            log.info("ymin: {}", item.getYmin());
            log.info("xmax: {}", item.getXmax());
            log.info("ymax: {}", item.getYmax());
            log.info("confidence: {}", item.getConfidence());
            log.info("class: {}", item.getClazz());
            log.info("name: {}", item.getName());
            log.info("---");

            // Check if this item's confidence is greater than maxConfidence
            if (item.getConfidence() > maxConfidence) {
                maxConfidence = item.getConfidence();
                maxConfidenceItem = item;
            }
        }

        // If no item found with confidence more than 0, return bad request
        if (maxConfidenceItem == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Lookup the test1 item in the database
        Optional<test1> dbItemOpt = test1Service.findByName(maxConfidenceItem.getName());
        if (dbItemOpt.isEmpty()) {
            // If the item's detail is not in the database, return the item's name
            return new ResponseEntity<>(maxConfidenceItem.getName(), HttpStatus.OK);
        }

        // Generate GPT-3 sentence using the test1 item
        String generatedSentence = ToGPT3(dbItemOpt.get());

        // Return the generated sentence
        return new ResponseEntity<>(generatedSentence, HttpStatus.OK);
    }

    public String ToGPT3(test1 test1) {
        // Gson 객체 생성 시 setPrettyPrinting()을 호출하여 pretty 형태로 출력하도록 설정
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // GPT3Prompt 객체 생성
        GPT3Prompt prompt = new GPT3Prompt();
        prompt.setModel("text-davinci-003");
        prompt.setPrompt(String.format("다음의 정보를 활용하여 문장을 만들어주세요." +
                "이름: %s, 카테고리: %s, %s, 알레르기: %s, %s, %s ",
                test1.getName(),
                test1.getCategori(),
                test1.getItemDetail(),
                test1.getAllegori(),
                test1.getShape(),
                test1.getMake()
        ));
        prompt.setMax_tokens(100);
        prompt.setTemperature(0.0);
        prompt.setTop_p(1.0);
        prompt.setN(1);
        prompt.setStream(false);
        prompt.setLogprobs(null);
        prompt.setStop("\n");

        // GPT3Prompt 객체를 JSON 문자열로 변환
        
        String jsonInput="";
      try {
         jsonInput = new String(gson.toJson(prompt).getBytes("UTF-8"), "UTF-8");
     
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
        System.out.println("jsonInput: " + jsonInput);

        try {
            // 입력을 GPT-3에 전송하고 응답을 받습니다.
            String responseJson = gpt3Service.processRequest(jsonInput);
            System.out.println("responseJson: " + responseJson);

            // 응답을 JsonObject로 파싱합니다.
            JsonObject responseObj = new Gson().fromJson(responseJson, JsonObject.class);
            System.out.println("responseObj: " + responseObj);
            
            // choices[0].message.content를 가져옵니다.
            if (responseObj.has("choices") && responseObj.get("choices").isJsonArray()) {
                JsonArray choicesArray = responseObj.getAsJsonArray("choices");
                if (!choicesArray.isJsonNull() && choicesArray.size() > 0) {
                    JsonObject choiceObject = choicesArray.get(0).getAsJsonObject();
                    if (choiceObject.has("message") && choiceObject.get("message").isJsonObject()) {
                        JsonObject messageObject = choiceObject.getAsJsonObject("message");
                        if (messageObject.has("content")) {
                            String generatedText = messageObject.get("content").getAsString();
                            return generatedText;
                        }
                    }
                }
            }

            // choices 키나 중첩된 JSON 객체/배열이 존재하지 않는 경우 null을 반환하거나 추가적인 에러 핸들링을 수행할 수 있습니다.
            return "Error ";

        } catch (IOException e) {
            // API 요청이 실패할 때 예외 처리를 합니다.
            String noinput = String.format("이 제품의 이름" +
                    "item_nm: %s, categori: %s, item_detail: %s, allegori: %s, shape: %s, make: %s",
                    test1.getName(),
                    test1.getCategori(),
                    test1.getItemDetail(),
                    test1.getAllegori(),
                    test1.getShape(),
                    test1.getMake());
            return noinput;

        } catch (JsonSyntaxException e) {
            // JSON 처리 중에 문제가 발생했을 때의 예외 처리
            log.error("Error processing GPT-3 response", e);
            return "Error processing GPT-3 response";
        }
    }

    class GPT3Prompt {
        String model;
        String prompt;
        int max_tokens;
        double temperature;
        double top_p;
        int n;
        boolean stream;
        String logprobs;
        String stop;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public int getMax_tokens() {
            return max_tokens;
        }

        public void setMax_tokens(int max_tokens) {
            this.max_tokens = max_tokens;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getTop_p() {
            return top_p;
        }

        public void setTop_p(double top_p) {
            this.top_p = top_p;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public boolean isStream() {
            return stream;
        }

        public void setStream(boolean stream) {
            this.stream = stream;
        }

        public String getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(String logprobs) {
            this.logprobs = logprobs;
        }

        public String getStop() {
            return stop;
        }

        public void setStop(String stop) {
            this.stop = stop;
        }
    }
}