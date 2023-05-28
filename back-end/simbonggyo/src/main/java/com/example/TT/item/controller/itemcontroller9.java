package com.example.TT.item.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TT.item.dto.jsonDto2;

@RestController
public class itemcontroller9 {

    @PostMapping(value = "/api/testitemcontroller9.javaitemcontroller9.java", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleJSONData(@RequestBody(required = false) List<jsonDto2> jsonData) {
        System.out.println(jsonData);
    	if (jsonData == null || jsonData.isEmpty()) {
            return new ResponseEntity<>("JSON 데이터가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        // 핸들러의 나머지 로직을 구현하세요.

        return new ResponseEntity<>("JSON 데이터가 성공적으로 처리되었습니다.", HttpStatus.OK);
    }
}
