package com.example.TT.item.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@lombok.ToString
@NoArgsConstructor
public class tes1Dto {


	private Long id;   //상품 코드
	
	private String itemNm;  
	//상품 이름 맛 닥터페퍼 제로 닥터페퍼	
	
	private String itemDetail; 
	//상품 상세 설명 (1.맛-(조금 매운맛,많이 매운맛- 객관적인걸로)
    
	//2.상품 카테고리 
	private String categori;
	
	//3.알레르기 정보(대두,복숭아 함유가 되있으므로 주의해야된다) 설정?
    private String Allegori;
    
    //4.모양(캔 , 병, 팩트병) 
    private String shape;
    
    //5. 조리 방법,주의 할점
    private String make;
}
