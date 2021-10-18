package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import java.util.List;

// JpaRepository에는 기본적인 엔티티 CRUD 메소드가 정의돼 있다.
public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item> { //<엔티티타입, 기본키타입>, 인터페이스 상속 추가

    List<Item> findByItemNm(String itemNm);
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);  // 상품을 상품명or 상세 설명으로 조회하는 쿼리 메소드
    List<Item> findByPriceLessThan(Integer price);   // 인자로 넘어온 값보다 값이 작은 상품 데이터를 조회하는 쿼리메소드
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // @Query 어노테이션 안에 JPQL로 작성한 쿼리를 넣는다.
    // @Param을 이용해서 파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정한다.
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc",
    nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}
