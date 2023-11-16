package com.qdev.domain.user.service;

import com.qdev.domain.user.request.UserCreateRequest;

public interface UserService {
    void createUser(UserCreateRequest request);
}
