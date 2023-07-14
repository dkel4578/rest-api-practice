package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Book;
import com.tj.edu.practice5.jpa.model.Member;
import com.tj.edu.practice5.jpa.model.Publisher;
import com.tj.edu.practice5.jpa.model.Review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void bookTest(){
        Book book = givenBook();
        bookRepository.findAll();
    }

    @Test
    @Transactional
    void relationTest1(){
        givenBookAndReview();

        Member member = memberRepository.findByEmail("namsun2@thejoeun.com");
        System.out.println(">>>>>>>>>>>>>>> member : "+member);
//        System.out.println(">>>>>>>>>>>>>>> review : "+member.getReviews());
        System.out.println(">>>>>>>>>>>>>>> book : "+member.getReviews().get(0).getBook());
        System.out.println(">>>>>>>>>>>>>>> publisher : " +member.getReviews().get(0).getBook().getPublisher());
    }


    private Book givenBook(){
        Book book = Book.builder()
                .name("React와 스프링 부트로 프로젝트 만들기")
                .build();
        return bookRepository.save(book);
    }
    private Book givenBook(Publisher publisher){
        Book book = Book.builder()
                .name("React와 스프링 부트로 프로젝트 만들기 2")
                .publisher(publisher)
                .build();
        return bookRepository.save(book);
    }


    private Publisher givenPublisher(){
        Publisher publisher = Publisher.builder()
                .name("조은출판사")
                .build();
         return publisherRepository.save(publisher);
    }

    private void givenBookAndReview(){
        givenReview(givenMember(), givenBook(givenPublisher()));
    }

    private Review givenReview(Member member, Book book){
        Review review = Review.builder()
                .title("재미가 아주 많다")
                .content("리뷰를 쓰기엔 공간이 부족해서 쓰지 않는다.")
                .member(member)
                .book(book)
                .build();
        return reviewRepository.save(review);
    }

    private Member givenMember(){
        return memberRepository.findByEmail("namsun2@thejoeun.com");
    }


}