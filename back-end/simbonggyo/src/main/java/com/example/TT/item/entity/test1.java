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
@javax.persistence.Table(name = "my_item2")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class test1 {
	
//이름 -> 검색 이퀄값 ...........
//딥러닝 -> json 
//전처리 ->  -> 쉽게? -- 학습한 데이터를 상세정보 뽑아와야돼
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "Item_id")
	private Long id;   //상품 코드
	
	@Column(nullable = false,length = 50)
	private String itemNm;  
//상품 이름 맛 닥터페퍼 제로 닥터페퍼	
	@Lob
	@Column(nullable = false)
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

//xmin,ymin,xmax,confidence,class,name 상세정보  // + 번역 -----> 포폴 
// +아두이노 포폴x    상 O
}
