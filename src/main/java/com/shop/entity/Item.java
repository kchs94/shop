package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import com.shop.dto.ItemFormDto;

@Entity // JPA에서 관리한다.
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id // 기본키(Primary Key) 설정
    @Column(name="item_id") // 필드와 컬럼 매핑. 컬럼의 다양한 제약조건 추가.
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 속성 지정:값 자동 증가
    private Long id;       //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)    // enum 타입 매핑
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    // 상품의 재고를 증가시키는 메소드
    public void addStock(int stockNumber) {
        this.stockNumber += stockNumber;
    }

}