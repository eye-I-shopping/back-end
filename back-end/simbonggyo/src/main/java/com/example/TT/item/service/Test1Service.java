package com.example.TT.item.service;
import com.example.TT.item.entity.itementity;
import com.example.TT.item.repository.Test1Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Test1Service {

    private final Test1Repository test1Repository;

    public Test1Service(Test1Repository test1Repository){
        this.test1Repository = test1Repository;
    }
    public List<itementity> findByCategory(String category) {
        return test1Repository.findByCategory(category);
    }
    public String getCategoryByName(String name) {
        Optional<itementity> itemOpt = test1Repository.findByName(name);
        return itemOpt.map(itementity::getCategory).orElse("");
    }

//    public List<itementity> findTop3ByConfidenceDesc() {
//        return test1Repository.findTop3ByOrderByConfidenceDesc();
//    }

    public String getItemDetailByName(String name) {
        Optional<itementity> itemOpt = test1Repository.findByName(name);
        return itemOpt.map(itementity::getItemDetail).orElse("");
    }

    public void saveItem(itementity item) {
    	test1Repository.save(item);
    }

    public Optional<itementity> findByName(String name){
        return test1Repository.findByName(name);
    }
    public void saveTest1() {
        itementity testItem = new itementity();
        testItem.setName("사이다");
        testItem.setItemDetail("맛있는 사이다");
        testItem.setAllegori("몰라");
        testItem.setCategory("음료수");
        testItem.setMake("칠성");
        testItem.setId(1L);
        testItem.setShape("캔");
        test1Repository.save(testItem);
    }
}
