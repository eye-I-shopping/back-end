package com.example.TT.item.service;
import com.example.TT.item.entity.itementity;
import com.example.TT.item.entity.itementity2;
import com.example.TT.item.repository.Test1Repository;
import com.example.TT.item.repository.Test1Repository2;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Test1Service2 {

    private final Test1Repository2 test1Repository;

    public Test1Service2(Test1Repository2 test1Repository){
        this.test1Repository = test1Repository;
    }
//    public List<itementity2> findByCategory(String category) {
//        return test1Repository.findByCategory(category);
////    }
//    public String getCategoryByName(String name) {
//        Optional<itementity2> itemOpt = test1Repository.findByName(name);
//        return itemOpt.map(itementity2::getCategory).orElse("");
//    }

//    public List<itementity> findTop3ByConfidenceDesc() {
//        return test1Repository.findTop3ByOrderByConfidenceDesc();
//    }

    public String getItemDetailByName(String name) {
        Optional<itementity2> itemOpt = test1Repository.findByName(name);
        return itemOpt.map(itementity2::getItemDetail).orElse("");
    }

    public void saveItem(itementity2 item) {
    	test1Repository.save(item);
    }

    public Optional<itementity2> findByName(String name){
        return test1Repository.findByName(name);
    }
    
//    public void saveTest1() {
//        itementity testItem = new itementity();
//        testItem.setName("사이다");
//        testItem.setItemDetail("맛있는 사이다");
//        testItem.setAllegori("몰라");
//        testItem.setCategory("음료수");
//        testItem.setMake("칠성");
//        testItem.setId(1L);
//        testItem.setShape("캔");
//        test1Repository.save(testItem);
//    }
}
