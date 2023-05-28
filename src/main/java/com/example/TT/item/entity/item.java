package com.example.TT.item.entity;
import java.time.LocalDateTime;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.example.TT.item.constant.*;
import javax.persistence.*;
@javax.persistence.Entity
@javax.persistence.Table(name = "my_item")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class item {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "Item_id")
	private Long id;   //상품 코드
	
	@Column(nullable = false,length = 50)
	private String itemNm;  //상품 이름
	
	@Column(nullable = false)
	private int price;   //상품 가격
	
	@Column(nullable = false,name = "number")
	private int stockNumber; //재고 수량
	
	@Enumerated(javax.persistence.EnumType.STRING)
	private itemSellStatus itemSellStatus;
	
	@Lob
	@Column(nullable = false)
	private String itemDetail; //상품 상세 설명

	private LocalDateTime regTime;   //등록 시간
	
	private LocalDateTime updateTime; //수정 시간

	
}
