package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import com.tj.edu.practice5.jpa.model.Users;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class EntityManagerTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UsersRepository usersRepository;


    @Test
    void entityManagerTest() {
        em.createQuery("select u from Member u").getResultList().forEach(s -> System.out.println(s));
    }

    @Test
//    @Transactional
    void cacheEntityFindTest(){
//        System.out.println(memberRepository.findByEmail("namsun2@thejoeun.com"));
//        System.out.println(memberRepository.findByEmail("namsun2@thejoeun.com"));
//        System.out.println(memberRepository.findByEmail("namsun2@thejoeun.com"));
//        System.out.println(memberRepository.findById(1L));
//        System.out.println(memberRepository.findById(1L));
//        System.out.println(memberRepository.findById(1L));
        memberRepository.deleteById(1L);
    }

    @Test
    @Transactional
    void cacheEntityFindTest2(){
        Member member = memberRepository.findById(1L).get();
        member.setName("아이언");
        memberRepository.save(member);
        memberRepository.flush();
        System.out.println("---------------------------------------1");
        member.setEmail("iron@gmail.com");
        memberRepository.save(member);
        memberRepository.flush();
        System.out.println("---------------------------------------2");
    }

    @Test
    @Transactional //OSIV (Open Session in View) -> transaction
    void entityMangerTest2(){
        Member member = Member.builder()
                .name("아냐")
                .email("anya@email.com")
                .build();

        em.persist(member); // jpa 영속성 상태 존재

        memberRepository.findAll().forEach(System.out::println);
        //영속성 상태에 있는 것만 가져옴
    }

    @Test
    @Transactional
    @Commit
//    @Rollback(false)
    void entityManagerTest3(){
       Users users= Users.builder() //비영속성 상태
                .name("또길동")
                .build();
        em.persist(users); //영속성상태 (managed)
        users.setEmail("ho10@abc.com");
//
        em.detach(users); //분리상태, 준영속성상태. entity 에서 더이상 관리를 하지 않는 상태
//        em.merge(users);//준영속성 상태에서 영속성 상태로 변경
//        em.remove(users); //비영속성으로 삭제 상태
    usersRepository.findAll().forEach(System.out::println);
//        System.out.println(usersRepository.findByName("또길동").get(0));
    }

    @Test
    @Transactional
    @Commit
    void persistCacheDelayInsertUpdate(){
        Users user = usersRepository.findById(1L).get(); //select, dirty check(변경감지)
        user.setName("이함마");

        usersRepository.save(user); //select, update
//
//        user.setEmail("lee1@abc.com");
//
//        usersRepository.save(user); //select, update

    }
}
