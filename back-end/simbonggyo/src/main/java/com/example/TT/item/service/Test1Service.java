package com.example.TT.item.service;
import com.example.TT.item.entity.test1;
import com.example.TT.item.repository.Test1Repository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class Test1Service {

    private final Test1Repository test1Repository;

    public Test1Service(Test1Repository test1Repository){
        this.test1Repository = test1Repository;
    }

    public Optional<test1> findByName(String name){
        return test1Repository.findByName(name);
    }
    public void saveTest1() {
        test1 testItem = new test1();
        testItem.setName("사이다");
        testItem.setItemDetail("맛있는 사이다");
        testItem.setAllegori("몰라");
        testItem.setCategori("음료수");
        testItem.setMake("칠성");
        testItem.setId(1L);
        testItem.setShape("캔");
        test1Repository.save(testItem);
    }
}
