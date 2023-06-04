package com.example.TT.Setting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Settings {
    
	@Id
    @Column(name = "token_id", length = 255, nullable = true)
    private String id;   //토큰 아이디 
	
    @Column(nullable = true)
    private int speed;
    
    private String format;
    
    private int filter;
}
