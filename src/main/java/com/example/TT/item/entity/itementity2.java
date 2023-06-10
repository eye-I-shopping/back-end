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
@javax.persistence.Table(name = "my_detail")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class itementity2 {
	
//이름 -> 검색 이퀄값 ...........
//딥러닝 -> json 
//전처리 ->  -> 쉽게? -- 학습한 데이터를 상세정보 뽑아와야돼
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "Item_id")
	private Long id;   //상품 코드
	
	@Column(name = "Item_nm", nullable = false,length = 50)
	private String name; 
	
	@Column(name = "Item_Hnm", nullable = false,length = 50)
	private String Hname; 
	
	@Column(name = "Item_detail", nullable = false)
	private String itemDetail; 

	@Column(name = "Item_detail1", nullable = false)
	private String itemDetail1;
	
	@Column(name = "Item_detail2", nullable = false)
	private String itemDetail2;
	
	@Column(name = "Item_detail3", nullable = false)
	private String itemDetail3;
	
	@Column(name = "Item_detail4", nullable = false)
	private String itemDetail4; 

	@Column(name = "Item_detail5", nullable = false)
	private String itemDetail5; 
	
	@Column(name = "Item_detail6", nullable = false)
	private String itemDetail6; 
	
	@Column(name = "Item_detail7", nullable = false)
	private String itemDetail7; 
	
	@Column(name = "Item_detail8", nullable = false)
	private String itemDetail8; 
	
	@Column(name = "Item_detail9", nullable = false)
	private String itemDetail9; 
	
	@Column(name = "Item_detail10", nullable = false)
	private String itemDetail10; 
	@Column(name = "Item_detail11", nullable = false)
	private String itemDetail11; 
	
	@Column(name = "Item_detail12", nullable = false)
	private String itemDetail12; 
	
	@Column(name = "Item_detail13", nullable = false)
	private String itemDetail13; 
	
	@Column(name = "Item_detail14", nullable = false)
	private String itemDetail14;
	
	@Column(name = "Item_detail15", nullable = false)
	private String itemDetail15; 
//xmin,ymin,xmax,confidence,class,name 상세정보  // + 번역 -----> 포폴 
// +아두이노 포폴x    상 O
}
