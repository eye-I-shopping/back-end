package com.example.TT.item.dto;

import java.time.LocalDateTime;
import com.example.TT.item.constant.itemSellStatus;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class itemDto2 {
	
	  private double xmin;
	    private double ymin;
	    private double xmax;
	    private double ymax;
	    private double confidence;
	    private int clazz;
	    private String name;
	
}
