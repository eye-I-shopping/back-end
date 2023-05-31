package com.example.TT.Setting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
public class Settings {
    
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "token_id")
	private int id;   //토큰 아이디
	
//    private int volume;
    
    private int speed;
    
    private String format;

   
}