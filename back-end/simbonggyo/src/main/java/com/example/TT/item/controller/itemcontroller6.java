//package com.example.TT.item.controller;
//import com.example.TT.item.dto.itemDto2;
//import com.example.TT.item.dto.jsonDto2;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class itemcontroller6 {
//
//    public static void main(String[] args) {
//
//        // JSON 데이터 예제
//        String jsonString = "{\"users\":[" +
//            "{\"xmin\":1,\"ymin\":2,\"xmax\":3,\"ymax\":4,\"confidence\":0.8,\"clazz\":1,\"name\":\"class1\"}," +
//            "{\"xmin\":5,\"ymin\":6,\"xmax\":7,\"ymax\":8,\"confidence\":0.9,\"clazz\":2,\"name\":\"class2\"}" +
//            "]}";
//
//        // ObjectMapper 객체 생성
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            // JSON 데이터를 읽고 jsonDto2 객체로 변환
//            jsonDto2 jsonData = objectMapper.readValue(jsonString, jsonDto2.class);
//
//            // jsonDto2 객체의 내용 출력
//            itemDto2[] users = jsonData.getUsers();
//            for (itemDto2 user : users) {
//                System.out.println(user.getName());
//            }
//        } catch (Exception e) {
//            System.err.println("JSON 변환 중 오류가 발생했습니다: " + e.getMessage());
//        }
//    }
//}