package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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


    @Test
    void jpqlTest1(){
        List<Book> bookList = bookRepository.findByMyBooks("재미있는 자바");
        bookList.forEach(System.out::println);

        System.out.println("--------------------------------------------------------");

        List<Book> bookList1 = bookRepository.findByMyBooksAndMyId(2L, "재미있는 자바");
        bookList1.forEach(System.out::println);

        System.out.println("--------------------------------------------------------");

        List<String> bookList2 = bookRepository.findNameByMyBooks("재미있는 자바");
        bookList2.forEach(System.out::println);

        System.out.println("--------------------------------------------------------");
//        //에러
//        List<Book> bookList4 = bookRepository.findNameIdByMyBooks("재미있는 자바");
//        bookList4.forEach(System.out::println);
// 성공
        List<Map<String, Object>> listMap1 = bookRepository.findNameIdByMyBooks("재미있는 자바");
        listMap1.forEach(x -> System.out.println(x.entrySet()));
//        listMap1.forEach(x -> System.out.println(x.values()));

        System.out.println("--------------------------------------------------------");

        List<Map<String, Object>> listMap2 = bookRepository.findMyNamedNameIdByMyBooks("재미있는 자바");
        listMap2.forEach(x -> System.out.println(x.entrySet()));

        System.out.println("--------------------------------------------------------");

        List<Map<String, Object>> listMap3 = bookRepository.nowhere("재미있는 자바", 1L);
        listMap3.forEach(x -> System.out.println(x.entrySet()));

        System.out.println("--------------------------------------------------------");

        List<Book> bookList3 = bookRepository.findByNameByMyBooksAndMyId(1L, "자바");
        bookList3.forEach(System.out::println);
    }

    @Test
    void nativeSqlTest1(){
        List<Book> bookListByNative = bookRepository.findByNativeByMyBooks("재미있는 자바");
        bookListByNative.forEach(System.out::println);

        List<Book> bookListByNativeName = bookRepository.findByNativeNameByMyBooks("재미있는 자바");
        bookListByNativeName.forEach(System.out::println);
    }

    @Test
    void customModelJpaTest1() {
        List<BookAndId> bookAndIdList = bookRepository.findMyCustomNamedNameIdByMyBooks( "재미있는 자바책");
        bookAndIdList.forEach(s -> System.out.println(s.getAbc() + " : " + s.getName2()));
    }

    @Test
    void updateJpaTest1(){
        int isUpdate = bookRepository.updateSpecificName(2L);
        System.out.println("2번 id를 가진 book의 이름이" + (isUpdate > 0 ? "변경됨" : "변경되지 않음"));

        int isUpdate2 = bookRepository.updateSpecificNameByJPQL(1L);
        System.out.println("1번 id를 가진 book의 이름이" + (isUpdate > 0 ? "변경됨" : "변경되지 않음"));
    }

    @Test
    void insertJpaTest1(){
        int isInsert = bookRepository.insertSpecificNameByJPQL("http css");
        System.out.println("삽입완료");
    }

    @Test
    void deleteJpaTest1(){
        int isDelete = bookRepository.deleteSpecificIdByJPQL(4L);
        System.out.println("삭제완료");
    }

}