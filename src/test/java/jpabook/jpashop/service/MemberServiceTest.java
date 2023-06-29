package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    //회원가입
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;
    @Test
    public void join() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("kim2");

        //when
        Long join = memberService.join(member);
        //then
        assertEquals(member, memberRepository.findOne(join));
    }

    @Test
    public void 중복_회원() throws Exception{
        //given
        Member member1 = new Member();
        member1.setUsername("kim");

        Member member2 = new Member();
        member1.setUsername("kim");

        memberService.join(member1);
        memberService.join(member2);

        //then
//        fail("예외 발생함.");
    }
}