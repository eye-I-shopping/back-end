//package com.example.TT.item.dto;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class itemDto3 {
//    private double xmin;
//    private double ymin;
//    private double xmax;
//    private double ymax;
//    private double confidence;
//    private int clazz;
//    private String name;
//
//    @JsonCreator
//    public itemDto3(@JsonProperty("xmin") double xmin, @JsonProperty("ymin") double ymin,
//                    @JsonProperty("xmax") double xmax, @JsonProperty("ymax") double ymax,
//                    @JsonProperty("confidence") double confidence,
//                    @JsonProperty("class") int clazz,
//                    @JsonProperty("name") String name) {
//        this.xmin = xmin;
//        this.ymin = ymin;
//        this.xmax = xmax;
//        this.ymax = ymax;
//        this.confidence = confidence;
//        this.clazz = clazz;
//        this.name = name;
//    }
//
//    // 게터 및 세터 메소드
//}
