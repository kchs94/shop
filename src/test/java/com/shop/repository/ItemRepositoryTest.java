package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션
@TestPropertySource(locations="classpath:application-test.properties")  // application-test에 더 높은 우선순위 부여
class ItemRepositoryTest {

    @Autowired  // Bean을 주입합니다.
    ItemRepository itemRepository;

    @Test   // 테스트 메소드로 지정합니다.
    @DisplayName("상품 저장 테스트")   // 지정한 테스트명이 노출됩니다.
    public void createItemTest() {
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setReTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }
}