package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// Member 엔티티를 DB에 저장해주는
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);   // 회원 가입 시 중복된 회원이 있는지 검사하기 위해 이메일로 회원을 검사
                        //할 수 있도록 쿼리해주는 쿼리 메소드

}