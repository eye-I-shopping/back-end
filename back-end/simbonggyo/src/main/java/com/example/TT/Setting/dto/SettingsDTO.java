package com.example.TT.Setting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class SettingsDTO {
    
	private String id;
    
//    private int volume;
    
    private int speed;
    
    private String format;
    
}