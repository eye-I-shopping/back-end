package com.example.TT.item.controller;

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
import java.util.Optional;

@Slf4j
@RestController
public class itemcontroller10 {

    private final Test1Service test1Service;

    public itemcontroller10(Test1Service test1Service) {
        this.test1Service = test1Service;
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
            if(item.getConfidence() > maxConfidence){
                maxConfidence = item.getConfidence();
                maxConfidenceItem = item;
            }
        }

        // If no item found with confidence more than 0, return bad request
        if(maxConfidenceItem == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Lookup the test1 item in the database
        Optional<test1> dbItemOpt = test1Service.findByName(maxConfidenceItem.getName());
        if(dbItemOpt.isEmpty()){
            // If the item's detail is not in the database, return the item's name
            return new ResponseEntity<>(maxConfidenceItem.getName(), HttpStatus.OK);
        }

        return new ResponseEntity<>(dbItemOpt.get(), HttpStatus.OK);
    }
}
