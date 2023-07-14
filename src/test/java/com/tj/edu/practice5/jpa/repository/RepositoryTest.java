package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Address;
import com.tj.edu.practice5.jpa.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("간단한 jpa테스트1")
    @Test
    void jpaTest1() {

    }

    @DisplayName("간단한 JPA MemberRepository 테스트")
    @Test
    void memberRepositoryTest1() {

    }


    @Test
    void bookRepositoryTest() throws InterruptedException {
        Book book = Book.builder()
                .name("표준orm JPA 프로그래밍")
                .build();
        Book book2 = bookRepository.save(book);

        Thread.sleep(1000);


        bookRepository.save(book2);

    }
    @Test
    void addressRepositoryTest() throws InterruptedException {
        Address address = Address.builder()
                .zipcode("101-101")
                .build();
        addressRepository.save(address);
    }

    @AfterEach
    void tearDown() {
    }
}