package com.example.TT.Setting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        if (id != null) {
            dto.setToken_id(id);
        }
        log.info(dto.getFormat());
        Settings settings = service.updateOrCreate(dto);
        return ResponseEntity.ok(settings);
    }
    
    @GetMapping("/settings/{id}")
    public ResponseEntity<Settings> getSettings(@PathVariable String id) {
        Settings settings = service.getSettingsById(id);
        if (settings != null) {
            return ResponseEntity.ok(settings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
