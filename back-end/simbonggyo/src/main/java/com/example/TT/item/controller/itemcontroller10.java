package com.example.TT.item.controller;
import com.example.TT.item.dto.itemDto3;
import com.example.TT.item.dto.jsonDto2;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class itemcontroller10 {

    @PostMapping(value = "/api/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> handleJSONData(@RequestBody(required = false) itemDto3[] itemsList) {
        // Check if itemsList is not null or empty

        List<String> return2 = new ArrayList<String>();
        if (itemsList == null || itemsList.length == 0) {
            return new ResponseEntity<>(return2, HttpStatus.BAD_REQUEST);
        }

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
            return2.add(item.getName());
            log.info("---");
        }

        return new ResponseEntity<>(return2, HttpStatus.OK);
    }
}