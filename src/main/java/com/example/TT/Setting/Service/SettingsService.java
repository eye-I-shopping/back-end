package com.example.TT.Setting.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TT.Setting.Repository.SettingsRepository;
import com.example.TT.Setting.dto.SettingsDTO;
import com.example.TT.Setting.entity.Settings;

@Service
public class SettingsService {
    private final SettingsRepository repository;

    @Autowired
    public SettingsService(SettingsRepository repository) {
        this.repository = repository;
    }

    public Settings updateOrCreate(SettingsDTO dto) {
        Settings settings = repository.findById(dto.getId()).orElse(new Settings());
        settings.setId(dto.getId());
//        settings.setVolume(dto.getVolume());
        settings.setSpeed(dto.getSpeed());
        settings.setSpeaker(dto.getSpeaker());
        settings.setUserSettings(dto.getUserSettings());
        return repository.save(settings);
    }
    public Settings getSettingsById(String id) {
        return repository.findById(id).orElse(null);
    }
}