//package com.example.TT.item.controller;
//
//import com.example.TT.item.dto.itemDto2;
//import com.example.TT.item.dto.jsonDto2;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class itemcontroller5 {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @PostMapping("/data")
//    public ResponseEntity<String> handleJSONData(@RequestBody String json) {
//        try {
////            jsonDto2[] jsonDtoArray = objectMapper.readValue(json, jsonDto2[].class);
//            jsonDto2[] jsonDtoArray = objectMapper.readValue(json, jsonDto2[].class);
//
//            for (jsonDto2 jsonDto : jsonDtoArray) {
//                itemDto2[] items = jsonDto.getUsers();
//                // JSON 데이터 처리 로직 작성
//                for (itemDto2 item : items) {
//                	System.out.println(item);
//                    // 각 아이템 정보에 접근하여 필요한 작업 수행
////                    double xmin = item.getXmin();
////                    System.out.println("xmin: " + xmin);
////                    double ymin = item.getYmin();
////                    double xmax = item.getXmax();
////                    double ymax = item.getYmax();
////                    double confidence = item.getConfidence();
////                    double classname = item.getClazz();
////                    String name = item.getName();
//
////                    System.out.println("xmin: " + xmin);
////                    System.out.println("ymin: " + ymin);
////                    System.out.println("xmax: " + xmax);
////                    System.out.println("ymax: " + ymax);
////                    System.out.println("confidence: " + confidence);
////                    System.out.println("classname: " + classname);
////                    System.out.println("name: " + name);
//                }
//            }         
//            return ResponseEntity.ok("JSON 데이터를 성공적으로 받았습니다.");
//        } catch (JsonProcessingException  e) {
//        	System.out.println(json);
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("잘못된 JSON 형식입니다.");
//        }
////상세정보 컬럼 -> GPT -> 하나의 문장을  
//    }
//}