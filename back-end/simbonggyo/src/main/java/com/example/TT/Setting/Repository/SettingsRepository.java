package com.example.TT.Setting.Repository;
import org.springframework.data.repository.CrudRepository;

import com.example.TT.Setting.entity.Settings;

public interface SettingsRepository extends CrudRepository<Settings, Integer> {
}