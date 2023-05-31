package com.example.TT.item.repository;

import com.example.TT.item.entity.test2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface userSettingRepository extends JpaRepository<test2, Long>,JpaSpecificationExecutor<test2> {
	  // 기존 메소드

	  default test2 findByToken(String token) {
	    return findOne(test2.findByToken(token)).orElse(null);
	  }
	}