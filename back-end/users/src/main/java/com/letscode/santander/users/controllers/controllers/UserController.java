package com.letscode.santander.users.controllers.controllers;

import com.letscode.santander.users.controllers.requests.UserRequest;
import com.letscode.santander.users.controllers.responses.UserResponse;
import com.letscode.santander.users.domains.User;
import com.letscode.santander.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserResponse converter;

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll().stream().map(converter::fromDomain).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Integer id) {
        return converter.fromDomain(userService.getById(id));
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest){
        return  converter.fromDomain(userService.create(userRequest.toDomain()));
    }

    @PutMapping("/{id}")
    public UserResponse update(@RequestBody UserRequest userRequest, @PathVariable Integer id){
        User userToUpdate = userRequest.toDomain();
        userToUpdate.setId(id);
        return converter.fromDomain(userService.update(userToUpdate));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer userId){
        userService.delete(userId);
    }
}
