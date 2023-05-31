package com.example.TT.Setting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.TT.Setting.Service.SettingsService;
import com.example.TT.Setting.dto.SettingsDTO;
import com.example.TT.Setting.entity.Settings;
import com.example.TT.item.controller.itemcontroller10;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SettingsController {
    private final SettingsService service;

    @Autowired
    public SettingsController(SettingsService service) {
        this.service = service;
    }   
    
}