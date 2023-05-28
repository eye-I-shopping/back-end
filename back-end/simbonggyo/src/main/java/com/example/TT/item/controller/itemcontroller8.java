package com.example.TT.item.controller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TT.item.dto.itemDto4;

import java.util.List;

@RestController
@RequestMapping("/api")
public class itemcontroller8 {

	@PostMapping(value = "/data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleJSONData(@RequestBody List<itemDto4> itemList) {
        try {
            // Item 객체 내용 출력
            for (itemDto4 item : itemList) {
                System.out.println(item.getName());
            }

            return ResponseEntity.ok("JSON 데이터를 성공적으로 받았습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("잘못된 JSON 형식입니다.");
        }
    }
}
