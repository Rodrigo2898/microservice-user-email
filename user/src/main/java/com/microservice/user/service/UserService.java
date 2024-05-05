package com.microservice.user.service;

import com.microservice.user.dto.UserRecordDTO;
import com.microservice.user.entity.User;
import com.microservice.user.producer.UserProducer;
import com.microservice.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public User save(UserRecordDTO userRecordDto) {
        var userDtoToEntity = dtoToUser(userRecordDto);
        User user = userRepository.save(userDtoToEntity);
        userProducer.publishMessageEmail(user);
        return user;
    }

    private User dtoToUser(UserRecordDTO userRecordDto) {
        User user = new User();
        user.setName(userRecordDto.name());
        user.setEmail(userRecordDto.email());
        return user;
    }
}
