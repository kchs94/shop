package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // 클래스를 엔티티로 선언
@Table(name="item") // 이 엔티티 클래스는 item 테이블과 맵핑된다.
@Getter
@Setter
@ToString
public class Item  extends BaseEntity {

    @Id // 기본키로 사용할 필드 지정
    @Column(name="item_id") // 필드와 컬럼 맵핑
    @GeneratedValue(strategy = GenerationType.AUTO) // 키 값생성방법
    private Long id;    // 상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; // 상품명

    @Column(name="price", nullable = false)
    private int price;  // 가격

    @Column(nullable = false)
    private int stockNumber;    // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  //상품 상세 설명

    @Enumerated(EnumType.STRING)    // enum 타입 맵핑
    private ItemSellStatus itemSellStatus;  // 상품 판매 상태
}
