package com.example.TT.Setting.controller;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.TT.Setting.Service.SettingsService;
import com.example.TT.Setting.dto.SettingsDTO;
import com.example.TT.Setting.entity.Settings;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SettingsController {
    private final SettingsService service;

    @Autowired
    public SettingsController(SettingsService service) {
        this.service = service;
    }

    @PostMapping("/settings")
    public ResponseEntity<Settings> updateOrCreate(@RequestParam(required = false) String id, @RequestBody SettingsDTO dto) {
    	System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        if (id != null) {
            dto.setId(id);
        }
//        log.info(dto.getId());
//        log.info(dto.getFormat());
//        log.info(dto.getId());
        	System.out.println(dto);
//        log.info(dto.getFilter());

//        log.info(dto.getClass());
//        log.info(dto.getSpeed());

        Settings settings = service.updateOrCreate(dto);
        return ResponseEntity.ok(settings);
    }
    
    @PostMapping("/settings/get")
    public ResponseEntity<Settings> getSettings(@RequestBody SettingsDTO dto) {
        if (dto != null) {
        	Settings settings = service.getSettingsById(dto.getId());
            return ResponseEntity.ok(settings);
        } else {
        	System.out.println(dto);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}




