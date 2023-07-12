package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
