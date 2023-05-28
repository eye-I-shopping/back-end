//package com.example.TT.item.controller;
//import com.example.TT.item.dto.userDto;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
////@RestController
////@RequestMapping("/api")
//public class itemcontroller3{
//
////    @PostMapping("/data")
//    public ResponseEntity<String> handleJSONData(@RequestBody com.example.TT.item.dto.jsonDto jsonDto) {
//        userDto[] users = jsonDto.getUsers();
//
//        // JSON 데이터 처리 로직 작성
//        for (userDto user : users) {
//            // 각 사용자 정보에 접근하여 필요한 작업 수행
//            Long userId = user.getUserId();
//            String firstName = user.getFirstName();
//            String lastName = user.getLastName();
//            String phoneNumber = user.getPhoneNumber();
//            String homepage = user.getHomepage();
//
//            System.out.println(userId+firstName+lastName+phoneNumber+homepage);
//            // 필요한 로직 수행
//        }
//        return ResponseEntity.ok("JSON 데이터를 성공적으로 받았습니다.");
//    }
//}