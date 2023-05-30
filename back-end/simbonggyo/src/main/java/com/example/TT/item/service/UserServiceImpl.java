package com.example.TT.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TT.item.dto.userDto2;
import com.example.TT.item.entity.test2;
import com.example.TT.item.repository.userSettingRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private userSettingRepository userRepository;

    @Override
    public test2 createUser(userDto2 userDto) {
        test2 user = new test2(userDto);
        return userRepository.save(user);
    }
}
