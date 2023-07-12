package com.tj.edu.practice5.jpa.model;


import com.tj.edu.practice5.jpa.model.enums.Nation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue
    private Long id;
    //    @NonNull
    @Column(nullable = false)
    private String name;
    private String email;
    //    private long test1;
    @Column(name = "colTest2", unique = true)
    private Integer test2;
//    private int test3;
//    @NonNull
    @Column(updatable = false)
    private LocalDateTime createAt;
    @Column(columnDefinition = "datetime(6) DEFAULT now() comment '수정시간'", insertable = false)
    private LocalDateTime updateAt;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    private Boolean male;

    @Transient
    private String testData;

    @Enumerated(value = EnumType.STRING)
    private Nation nation;
}