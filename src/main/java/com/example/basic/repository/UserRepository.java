package com.example.basic.repository;

import com.example.basic.domain.UserEntity;
import com.example.basic.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {

}
