package com.example.TT.item.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TT.item.dto.userDto2;
import com.example.TT.item.entity.test2;
import com.example.TT.item.service.UserService;


@RestController
@RequestMapping("/api/V1")
public class userSettingController {

	  @Autowired
	    com.example.TT.item.repository.userSettingRepository userSettingRepository;

    @PostMapping("/user-settings")
    public <userSettingRepository> ResponseEntity<?> createUserSetting(@RequestBody userDto2 userDto) {
        test2 user = new test2(userDto);
        test2 savedUser = userSettingRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
