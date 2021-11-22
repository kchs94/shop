package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@Transactional  // 로직을 처리하다가 에러가 발상하면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백 해준다.
@RequiredArgsConstructor// final이나 @NotNull이 붙은 필드에 생서자를 생성해준다.
public class MemberService implements UserDetailsService {

    // 빈에 생성자가 1개이고 생성자의 파라미터 타입이 빈으로 등록가능하면 @Autowired 없이 의존성 주입 가능
    private final MemberRepository memberRepository;    

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){    // 가입된 회원이 있을 경우 IllegalStateException 예외 발생
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}