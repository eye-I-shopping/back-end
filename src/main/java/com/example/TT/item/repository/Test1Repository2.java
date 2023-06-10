package com.example.TT.item.repository;

import com.example.TT.item.entity.itementity;
import com.example.TT.item.entity.itementity2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface Test1Repository2 extends JpaRepository<itementity2, Long>, QuerydslPredicateExecutor<itementity2> {

    Optional<itementity2> findByName(String name);
    
//    List<itementity2> findByCategory(String category); // findByCategory 메서드의 파라미터 이름 수정
//    List<itementity> findTop3ByOrderByConfidenceDesc();
    
    
}
