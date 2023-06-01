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
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
        System.out.println(dbItemOpt.get());
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
    	System.out.println("---------------------------------------------");
    	System.out.println(test1);
        // Gson 객체 생성 시 setPrettyPrinting()을 호출하여 pretty 형태로 출력하도록 설정
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // 주어진 필드를 사용하여 GPT-3 프롬프트를 생성합니다.
        String input = String.format("Please generate a sentence using the following details: " +
                        "Name: %s, Category: %s, Item Detail: %s, Allegori: %s, Shape: %s, Make: %s",
                test1.getName(),
                test1.getCategori(),
                test1.getItemDetail(),
                test1.getAllegori(),
                test1.getShape(),
                test1.getMake()
        );

        try {
            // 입력을 GPT-3에 전송하고 응답을 받습니다.
            String responseJson = gpt3Service.processRequest(input);

            // 간단하게 pretty 출력으로 변환하기 위해 JsonObject로 파싱 후 반환
            JsonObject responseObj = gson.fromJson(responseJson, JsonObject.class);
            return gson.toJson(responseObj);

        } catch (IOException e) {
            // API 요청이 실패할 때 예외 처리를 합니다.
            return input;
        }
    }

    
    class GPT3Prompt {
        String prompt;
        int max_tokens;
        Double temperature;
        Double top_p;
        Integer n;
        Boolean stream;

        public GPT3Prompt(String prompt) {
            this.prompt = "당신은 시각장애인에게 상품 상세정보를 알려주는 선생님입니다. "
            		+ "\n당신은 {식품코드번호,\"식품이름\",\"식품의 맛\",\"식품 카테고리\",\"알레르기 정보\",\"식품포장분류\",\"조리방법, 주의할 점\"} 의 형식으로 정보를 제공 받습니다."
            		+ "\n당신은 위 형식대로 주어진 단어 및 문장을 조합하여 한글 문법과 문맥을 잘지켜서 간결한 문장을 잘 만듭니다. "
            		+ "당신은 새로운 정보를 덧 붙이지 않습니다. 제공받은 단어와문장을 조합하기 위한 조사와 서술어를 알맞게 기술하는 게 할 일입니다."
            		+ " 당신의 이름은 아이쇼핑입니다. 주어진 단어와 문장은 마음대로 삭제 시키거나 줄이지 않습니다. ";
            this.max_tokens = 100; //최대 토큰 수 지정
            this.temperature = 0.0; //온도 =낮을수록 일관된 출력
            this.top_p = 1.0; //큰 확률을 갖는 결과 리턴
            this.n = 1; //출력 수 
     this.stream = false; // 불완전한 결과 허용 안함
        }

        public String getPrompt() {
            return prompt;
        }

        public int getMax_tokens() {
            return max_tokens;
        }

        // 추가된 옵션에 대한 getter 메서드를 정의합니다.
        public Double getTemperature() {
            return temperature;
        }

        public Double getTop_p() {
            return top_p;
        }

        public Integer getN() {
            return n;
        }

        public Boolean getStream() {
            return stream;
        }
    }

}


//	  "model": "text-davinci-003",
//	  "prompt": "당신은 시각장애인에게 상품 상세정보를 알려주는 선생님입니다. \n당신은 {식품코드번호,\"식품이름\",\"식품의 맛\",\"식품 카테고리\",\"알레르기 정보\",\"식품포장분류\",\"조리방법, 주의할 점\"} 의 형식으로 정보를 제공 받습니다.\n당신은 위 형식대로 주어진 단어 및 문장을 조합하여 한글 문법과 문맥을 잘지켜서 간결한 문장을 잘 만듭니다. 당신은 새로운 정보를 덧 붙이지 않습니다. 제공받은 단어와문장을 조합하기 위한 조사와 서술어를 알맞게 기술하는 게 할 일입니다. 당신의 이름은 아이쇼핑입니다. 주어진 단어와 문장은 마음대로 삭제 시키거나 줄이지 않습니다. ",
//	  "temperature": 0,
//	  "max_tokens": 119,
//	  "top_p": 1,
//	  "frequency_penalty": 0,
//	  "presence_penalty": 0
//	
