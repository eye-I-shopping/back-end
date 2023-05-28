package com.example.TT.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.example.TT.item.entity.item;

public interface ItemRepository extends JpaRepository<item,Long>,
			QuerydslPredicateExecutor<item>{

	List<item> findByItemNm(String ItemNm);
	
	List<item> findByItemNmOrItemDetail(String itemNm,String itemDetail);
	
	
	@Query("Select i from item i where i.itemDetail like %:itemDetail% "
			+ "order by i.price")
	List<item> findByitemDetail(@Param("itemDetail")String itemDetail);

	@Query(value = "Select * from my_item i where i.item_Detail like %:itemDetail% "
			+ "order by i.price",nativeQuery = true)
	List<item> findByitemDetailNative(@Param("itemDetail")String itemDetail);
}
