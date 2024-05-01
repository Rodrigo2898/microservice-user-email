package com.microservice.user.service;

import com.microservice.user.dto.UserRecordDto;
import com.microservice.user.entity.User;
import com.microservice.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(UserRecordDto userRecordDto) {
        User user = dtoToUser(userRecordDto);
        return userRepository.save(user);
    }

    private User dtoToUser(UserRecordDto userRecordDto) {
        User user = new User();
        user.setName(userRecordDto.name());
        user.setEmail(userRecordDto.email());
        return user;
    }
}
