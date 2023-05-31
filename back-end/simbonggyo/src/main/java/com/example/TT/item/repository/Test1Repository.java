package com.example.TT.item.repository;

import com.example.TT.item.entity.test1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface Test1Repository extends JpaRepository<test1, Long>, QuerydslPredicateExecutor<test1> {

    Optional<test1> findByName(String name);
}
