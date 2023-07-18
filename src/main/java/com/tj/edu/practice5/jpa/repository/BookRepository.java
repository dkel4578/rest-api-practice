package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Book;
import com.tj.edu.practice5.jpa.model.BookAndId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);

    @Query(value="select b from Book b where name = ?1")
    List<Book> findByMyBooks(String name);

    @Query(value="select b from Book b where name = ?2 and id =?1")
    List<Book> findByMyBooksAndMyId(Long id, String name);

    @Query(value="select b.name from Book b where name = ?1")
    List<String> findNameByMyBooks(String name);
// 에러코드
//    @Query(value="select b.id, b.name from Book b where name = ?1")
//    List<Book> findNameIdByMyBooks(String name);
// 성공코드, 별칭을 넣어줘야 맵으로 인식함
    @Query(value = "select b.id id, b.name name from Book b where name = ?1")
    List<Map<String, Object>> findNameIdByMyBooks(String name);

    @Query(value = "select b.id id, b.name name from Book b where name = :name")
    List<Map<String, Object>> findMyNamedNameIdByMyBooks(@Param("name") String name);

    @Query(value = "select b.id abc, b.name name2 from Book b where name = :name")
    List<BookAndId> findMyCustomNamedNameIdByMyBooks(@Param("name") String name);

    @Query(value = "select b.id id, b.name name from Book b where name = :name and id =:id")
    List<Map<String, Object>> nowhere(@Param("name") String name, @Param("id") Long id);

    @Query(value = "select b from Book b where name like %:name% and id = :id")
    List<Book> findByNameByMyBooksAndMyId(@Param("id") Long id, @Param("name") String name);

    @Query(value = "select * from book where name =?1", nativeQuery = true)
    List<Book> findByNativeByMyBooks(String name);

    @Query(value = "select * from book where name =:name2", nativeQuery = true)
    List<Book> findByNativeNameByMyBooks(@Param("name2") String name);

    @Transactional
    @Query(value = "update book set name = '이상한 자바' where id =:id", nativeQuery=true)
    @Modifying //수정, 삽입, 삭제에 필수
    int updateSpecificName(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Book b set b.name = '이상하고도 요상한 DB' where b.id = :id")
    int updateSpecificNameByJPQL(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = """
            Insert
                into Book(`id`,`name`, `publisher_id`, `create_at`, `update_at`) 
                values (5, :name, 2, now(), now());
            """, nativeQuery = true)
    int insertSpecificNameByJPQL(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = """
            Delete
            from Book
            where id =:id
            """, nativeQuery = true)
    int deleteSpecificIdByJPQL(@Param("id") Long id);
}
