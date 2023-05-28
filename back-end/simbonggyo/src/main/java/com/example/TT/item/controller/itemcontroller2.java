//package com.example.TT.item.controller;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.TT.item.dto.jsonDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
////@RestController
////@RequestMapping("/api")
//public class itemcontroller2{
//
//   private final ObjectMapper objectMapper;
//
//   public itemcontroller2(ObjectMapper objectMapper) {
//       this.objectMapper = objectMapper;
//   }
//
////   @PostMapping(value = "/data", consumes = MediaType.APPLICATION_JSON_VALUE)
//   public void handleJSONData(@RequestBody jsonDto dto) {
//       try {
//           String json = objectMapper.writeValueAsString(dto);
//           System.out.println(json);
//       } catch (Exception e) {
//           // 예외 처리
//       }
//   }
//}