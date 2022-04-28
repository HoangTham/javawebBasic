package com.example.basic.service;

import com.example.basic.dto.User;
import com.example.basic.dto.UserSearch;

import java.util.List;

public interface UserService{
    User save(User user);
    List<User> getList();
    User update(Long id, User user);
    List<User> search(UserSearch value);
    User findById(Long id);
    String remove(Long id);
}

