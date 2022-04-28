package com.example.basic.controller;

import com.example.basic.dto.User;
import com.example.basic.dto.UserSearch;
import com.example.basic.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user){
        User u= userService.save(user);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/list")
    public ResponseEntity<List> getList(){
        List listUsers = userService.getList();
        return ResponseEntity.ok(listUsers);
    }

    @GetMapping("/search")
    public ResponseEntity<List> search(UserSearch search){
        List listUsers = userService.search(search);
        return ResponseEntity.ok(listUsers);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable(name ="id") Long id, @RequestBody User user){
        User u = userService.update(id, user);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(name ="id") Long id){
        User u = userService.findById(id);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable(name ="id") Long id){
        String result = userService.remove(id);
        return result;
//        return ResponseEntity.noContent().build();
    }

}
