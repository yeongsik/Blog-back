package com.qdev.domain.user.service;

import com.qdev.domain.user.repository.UserRepository;
import com.qdev.domain.user.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public void createUser(UserCreateRequest request) {

    }
}
