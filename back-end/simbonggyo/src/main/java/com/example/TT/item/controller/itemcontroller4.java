//package com.example.TT.item.controller;
//import com.example.TT.item.dto.itemDto2;
//import com.example.TT.item.dto.jsonDto2;
//import com.example.TT.item.dto.userDto;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class itemcontroller4{
//
//	@PostMapping("/data")
//	public ResponseEntity<String> handleJSONData(@RequestBody jsonDto2[] jsonDtoArray) {
//	    for (jsonDto2 jsonDto : jsonDtoArray) {
//	        itemDto2[] users = jsonDto.getUsers();
//
//	        // JSON 데이터 처리 로직 작성
//	        for (itemDto2 user : users) {
//	            // 각 사용자 정보에 접근하여 필요한 작업 수행
//	            String Xmin = user.getXmin();
//	            double Ymin = user.getYmin();
//	            double Xmax = user.getXmax();
//	            double Ymax = user.getYmax();
//	            double Confidence = user.getConfidence();
//	            double classname = user.getClassname();
//	            String name = user.getName();
//	            System.out.println(Xmin);
//	            // 필요한 로직 수행
//	        }
//	    }
//	    return ResponseEntity.ok("JSON 데이터를 성공적으로 받았습니다.");
//	}
//}