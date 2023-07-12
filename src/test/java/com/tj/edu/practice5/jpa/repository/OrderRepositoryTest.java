package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void orderTest() {
        Order order = Order.builder()
                .guestNo(1)
                .userId("test1234")
                .productNo(1)
                .oCdatetime(LocalDateTime.now())
//                .oCnt(1)
//                .oAddrNo(120345)
//                .oAddr1("서울")
//                .oAddr2("102호")
//                .oPhone("01023046721")
//                .oMessage("좋아요")
//                .oPurchase("T")
                .deliveryNo(1)
                .srcImgNo(1)
                .build();

        orderRepository.save(order);
    }
}