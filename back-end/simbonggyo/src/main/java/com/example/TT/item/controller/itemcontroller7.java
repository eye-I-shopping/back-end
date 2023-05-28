//package com.example.TT.item.controller;
//
//import com.example.TT.item.dto.itemDto3;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class itemcontroller7 {
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @PostMapping("/data")
//    public ResponseEntity<String> handleJSONData(@RequestBody List<itemDto3> itemList) {
//        try {
//            itemDto3[] itemArray = itemList.toArray(new itemDto3[itemList.size()]);
//
//            // itemDto3 객체 내용 출력
//            for (itemDto3 item : itemArray) {
//                System.out.println(item.getName());
//            }
//
//            return ResponseEntity.ok("JSON 데이터를 성공적으로 받았습니다.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("잘못된 JSON 형식입니다.");
//        }
//    }
//}
