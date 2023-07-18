package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Publisher publisher;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private List<Review> reviews;

    @ManyToMany
    @ToString.Exclude
    private List<Author> authors;
}