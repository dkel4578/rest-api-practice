package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import com.tj.edu.practice5.jpa.model.enums.Nation;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void crud() {
//        Member member1 = new Member(1L, "홍길동", null, LocalDateTime.now(), LocalDateTime.now());
        // insert문
//        Member copyMember = memberRepository.save(member1); // insert into Member values (~~~
//        System.out.println("copyMember -> " + copyMember);

//        Member member =
        // select all문
        System.out.println("select all문--------------------------------------------------------");
        List<Member> memberList = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "name")); // == select * from member
        // jdk 1.8에서 사용된 stream기술을 이용한 print찍는 방법
        memberList.forEach(System.out::println);
//        for(Member member : memberList) {
//            System.out.println(member.toString());
//        }

        // select where문
        System.out.println("select where문 --------------------------------------------------------");
        List<Member> memberList2 = memberRepository.findAllById(Lists.newArrayList(1L, 3L, 5L)); // == select * from member where id in (1, 3)
        memberList2.forEach(System.out::println);

        // update문
        System.out.println("update문 --------------------------------------------------------");
//        Member member1 = new Member(1L, "홍길동", "이메일 주소", LocalDateTime.now(), LocalDateTime.now(), null, null);
        Member member1 = Member.builder()
                        .id(1L).name("홍길동").email("이메일 주소").createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        memberRepository.save(member1);     // 1번을 가진 id가 있다면 update, 없으면 create문 발생
        List<Member> memberList3 = memberRepository.findAll();
        memberList3.forEach(System.out::println);

        // delete문
//        System.out.println("delete문 --------------------------------------------------------");
////        memberRepository.deleteAll(); //하나씩 삭제해서 느림
//        memberRepository.deleteAllInBatch(); // 한번에 삭제
//        List<Member> memberList4 = memberRepository.findAll();
//        memberList4.forEach(System.out::println);
    }

    @Test
    void crud2(){
        //create 문
//        Member member = Member.builder()
//                .name("이명박")
//                .createAt(LocalDateTime.now())
//                .build();
//        memberRepository.save(member);
//
//        // create 문 (update_at 컬럼이 null이 아닌 insert)
//        Member member2 = Member.builder()
//                .name("강정수")
//                .updateAt(LocalDateTime.now())
//                .build();
//        memberRepository.save(member2);
//
//        // create 문(id : 15, name : 박조은, email : parkjoeun@gmail.com, create_at : 현재시간)
        Member member3 = Member.builder()
                .id(15L)
                .name("박조은")
                .email("parkjoeun@gmail.com")
                .createAt(LocalDateTime.now())
                .build();
        memberRepository.save(member3);

        //select 문 (by)
        Optional<Member> memberOptional = memberRepository.findById(1L);
        System.out.println(memberOptional);
//        Member member = memberRepository.findById(1L).orElse(null);
//        if(member != null){
//            System.out.println(member);
//        }

        //id: 7, 3 을 가진 행값을 가져오는 select 문을 만들어주는 java jap 코드 작성 (findAll)
        List<Member> memberList4 = memberRepository.findAllById(Lists.newArrayList(3L, 7L));
        memberList4.forEach(System.out::println);

        //select count 함수
        System.out.println("회원 수는 " + memberRepository.count()+"명 입니다.");

        //select exist 함수
        if(memberRepository.existsById(5L)){
            System.out.println("5번 회원은 존재합니다.");
        } else{
            System.out.println("5번 회원은 존재하지 않습니다.");
        }

        if(memberRepository.existsById(90L)){
            System.out.println("90번 회원은 존재합니다.");
        } else{
            System.out.println("90번 회원은 존재하지 않습니다.");
        }

        //select page 함수
        Page<Member> membersPage = memberRepository.findAll(PageRequest.of(0, 4));
        System.out.println(membersPage);

        System.out.println("totalElements: " + membersPage.getTotalElements());
        System.out.println("totalPages: " + membersPage.getTotalPages());
        System.out.println("numberOfElement: " + membersPage.getNumberOfElements());
        System.out.println("sort: " + membersPage.getSort());
        System.out.println("size: " + membersPage.getSize());

        List<Member> memberList2 = membersPage.getContent();
        memberList2.forEach(System.out::println);

        // jpa find example 이용 (select)
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());
        Example<Member> memberExample = Example.of(
                Member.builder().name("박남순").email("@thejoeun.com").build(),
                matcher
        );
        memberRepository.findAll(memberExample).forEach(System.out::println);

        Example<Member> memberExample2 = Example.of(Member.builder().email("mars@thejoeun.com").build());
        memberRepository.findAll(memberExample2).forEach(System.out::println);



        }
    @DisplayName("semiProject jpa 자바코드 변환 테스트")
    @Test()
    void crudSemiSqlMapper(){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", endsWith());
        Example<Member> memberExample = Example.of(
                Member.builder()
                        .name("신")
                        .build(), matcher
        );

        memberRepository.findAll(memberExample).forEach((System.out::println));
    }

    @Test
    void jpaSchemaTest() throws InterruptedException {
//        Member member = Member.builder()
//                .name("이미라")
//                .male(false)
//                .email("imila@naver.com")
//                .createAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
//                .build();
//        member = memberRepository.saveAndFlush(member); // insert
//
//        Thread.sleep(100);
//        member.setName("김홍순");
//        member.setUpdateAt(LocalDateTime.now());
//        memberRepository.saveAndFlush(member);          // update
    }

    @Test
    void jpaEnumTest(){
        Member member = Member.builder()
                .name("이미라")
                .male(false)
                .email("imila@naver.com")
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .nation(Nation.JAPAN)
                .build();
        memberRepository.saveAndFlush(member);
    }
}