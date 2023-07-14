package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Address;
import com.tj.edu.practice5.jpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {


}
