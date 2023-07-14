package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Address;
import com.tj.edu.practice5.jpa.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AddressRepositoryTest {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    MemberRepository memberRepository;

    private Member getGivenMember(){
        Member member = Member.builder()
                .name("황상수")
                .email("hwangsang@naver.com")
                .build();

        return memberRepository.save(member);
    }

    private Address getGivenAddress(Member member){
        Address address = Address.builder()
                .member(member)
                .zipcode("302-412")
                .build();

        return addressRepository.save(address);
    }

    @Test
    void manyToOneTest1(){
        Member member = getGivenMember();
        Address address = getGivenAddress(member);

        System.out.println("주소 : "+addressRepository.findById(1L));
        System.out.println(address.getMember().getId());




    }
}