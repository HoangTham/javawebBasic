package com.example.basic.repository.custom;

import com.example.basic.domain.UserEntity;
import com.example.basic.dto.UserSearch;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> search(UserSearch search);
    Long count(UserSearch search);
}
