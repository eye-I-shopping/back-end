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

        Gson gson2 = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("input", input);
        String jsonInput = gson2.toJson(jsonObject);
        try {
            // 입력을 GPT-3에 전송하고 응답을 받습니다.
            String responseJson = gpt3Service.processRequest(jsonInput);

            // 간단하게 pretty 출력으로 변환하기 위해 JsonObject로 파싱 후 반환
            JsonObject responseObj = gson.fromJson(responseJson, JsonObject.class);
            return gson.toJson(responseObj);

        } catch (IOException e) {
            // API 요청이 실패할 때 예외 처리를 합니다.
            return input;
        }
    }

}
