package com.tj.edu.practice5.jpa.Service;

import com.tj.edu.practice5.jpa.model.Users;
import com.tj.edu.practice5.jpa.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    @Transactional
    public void put(){
        Users users = Users.builder()
                .name("김삿갓")
                .build();
        usersRepository.save(users);
    }
}
