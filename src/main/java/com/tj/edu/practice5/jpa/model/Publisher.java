package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@EntityListeners(value={AuditingEntityListener.class})
public class Publisher extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private List<Book> books;
}
