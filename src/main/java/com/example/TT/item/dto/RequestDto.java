package com.example.TT.item.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestDto {
    private itemDto3[] itemsList;
    private filterDto filter;
    
    @JsonCreator
    public RequestDto(@JsonProperty("itemsList") itemDto3[] itemsList, @JsonProperty("filter") filterDto filter) {
        this.itemsList = itemsList;
        this.filter = filter;
    }
}