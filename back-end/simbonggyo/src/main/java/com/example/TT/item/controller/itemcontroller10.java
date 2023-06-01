//package com.example.TT.item.controller;
//
//import com.example.TT.Gpt.Service.GPT3Service;
//import com.example.TT.item.dto.itemDto3;
//import com.example.TT.item.entity.test1;
//import com.example.TT.item.service.Test1Service;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.Optional;
//import com.google.gson.Gson;
//
//@Slf4j
//@RestController
//public class itemcontroller10 {
//
//    private final Test1Service test1Service;
//
//    public itemcontroller10(Test1Service test1Service) {
//        this.test1Service = test1Service;
//    }
//
//    @PostMapping(value = "/api/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> handleJSONData(@RequestBody(required = false) itemDto3[] itemsList) {
//        // Check if itemsList is not null or empty
//        if (itemsList == null || itemsList.length == 0) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        // Initialize maxConfidence and maxConfidenceItem
//        double maxConfidence = 0.0;
//        itemDto3 maxConfidenceItem = null;
//
//        // Iterate through all items and log their values
//        for (itemDto3 item : itemsList) {
//            log.info("Item Info:");
//            log.info("xmin: {}", item.getXmin());
//            log.info("ymin: {}", item.getYmin());
//            log.info("xmax: {}", item.getXmax());
//            log.info("ymax: {}", item.getYmax());
//            log.info("confidence: {}", item.getConfidence());
//            log.info("class: {}", item.getClazz());
//            log.info("name: {}", item.getName());
//            log.info("---");
//
//            // Check if this item's confidence is greater than maxConfidence
//            if(item.getConfidence() > maxConfidence){
//                maxConfidence = item.getConfidence();
//                maxConfidenceItem = item;
//            }
//        }
//
//        // If no item found with confidence more than 0, return bad request
//        if(maxConfidenceItem == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        // Lookup the test1 item in the database
//        Optional<test1> dbItemOpt = test1Service.findByName(maxConfidenceItem.getName());
//        if(dbItemOpt.isEmpty()){
//            // If the item's detail is not in the database, return the item's name
//            return new ResponseEntity<>(maxConfidenceItem.getName(), HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(dbItemOpt.get(), HttpStatus.OK);
//    }
//    
//
//    public String ToGPT3(test1 test1) {
//        Gson gson = new Gson();
//
//        // 주어진 필드를 사용하여 GPT-3에 전송할 입력입니다. 이를 필요에 따라 수정하세요.
//        String input = String.format("Please generate a sentence using the following details: " +
//                "Name: %s, Category: %s, Item Detail: %s, Allegori: %s, Shape: %s, Make: %s",
//                test1.getName(),
//                test1.getCategori(),
//                test1.getItemDetail(),
//                test1.getAllegori(),
//                test1.getShape(),
//                test1.getMake()
//        );
//
//        String json = gson.toJson(new GPT3Prompt(input));
//
//        try {
//            String responseJson = new GPT3Service().processRequest(json);
//            return responseJson;
//            
//        } catch (IOException e) {
//        	System.out.println(test1.getName() + " " +
//                    test1.getCategori() + " " +
//                    test1.getItemDetail() + " " +
//                    test1.getAllegori() + " " +
//                    test1.getShape() + " " +
//                    test1.getMake());
//        }
//		return json;
//    }
//
//    class GPT3Prompt {
//        String prompt;
//        int max_tokens;
//        Double temperature;
//        Double top_p;
//        Integer n;
//        Boolean stream;
//
//        public GPT3Prompt(String prompt) {
//            this.prompt = prompt;
//            this.max_tokens = 100; //최대 토큰 수 지정
//            this.temperature = 0.0; //온도 =낮을수록 일관된 출력
//            this.top_p = 1.0; //큰 확률을 갖는 결과 리턴
//            this.n = 1; //출력 수 
//     this.stream = false; // 불완전한 결과 허용 안함
//        }
//
//        public String getPrompt() {
//            return prompt;
//        }
//
//        public int getMax_tokens() {
//            return max_tokens;
//        }
//
//        // 추가된 옵션에 대한 getter 메서드를 정의합니다.
//        public Double getTemperature() {
//            return temperature;
//        }
//
//        public Double getTop_p() {
//            return top_p;
//        }
//
//        public Integer getN() {
//            return n;
//        }
//
//        public Boolean getStream() {
//            return stream;
//        }
//    }
//
//
//}
