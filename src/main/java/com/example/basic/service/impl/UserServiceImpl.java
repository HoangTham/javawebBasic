package com.example.basic.service.impl;

import com.example.basic.domain.UserEntity;
import com.example.basic.dto.User;
import com.example.basic.dto.UserSearch;
import com.example.basic.repository.UserRepository;
import com.example.basic.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        UserEntity u = new UserEntity();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        userRepository.save(u);
        user.setId(u.getId());
        return user;
    }

    @Override
    public List<User> getList() {
        List<UserEntity> list = userRepository.findAll();
        List<User> l = new ArrayList<>();
        list.stream().forEach(it->{
            User u = new User();
            u.setId(it.getId());
            u.setFirstName(it.getFirstName());
            u.setLastName(it.getLastName());
            l.add(u);
        });
        return l;
    }

    @Override
    public User update(Long id, User user) {
        Optional<UserEntity> u = userRepository.findById(id);
        if(u.isPresent()){
            UserEntity userEntity = u.get();
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userRepository.save(userEntity);
            return user;
        }
        return null;
    }

    @Override
    public List<User> search(UserSearch value) {
        List<UserEntity> list = userRepository.search(value);
        List<User> l = new ArrayList<>();
        list.stream().forEach(it->{
            User u = new User();
            u.setId(it.getId());
            u.setFirstName(it.getFirstName());
            u.setLastName(it.getLastName());
            l.add(u);
        });
        System.out.println(userRepository.count(value));
        return l;
    }

    @Override
    public User findById(Long id) {
        Optional<UserEntity> ue = userRepository.findById(id);
        User u = new User();
        if(ue.isPresent()){
            UserEntity userEntity = ue.get();
            u.setId(id);
            u.setFirstName(userEntity.getFirstName());
            u.setLastName(userEntity.getLastName());
        }
        return u;
    }

    @Override
    public String remove(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(userEntity.isPresent()){
            userRepository.deleteById(id);
            return "Success";
        }
        return "Fail";
    }
}

