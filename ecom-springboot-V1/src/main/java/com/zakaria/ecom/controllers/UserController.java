package com.zakaria.ecom.controllers;

import com.zakaria.ecom.dtos.request.UserRequest;
import com.zakaria.ecom.models.User;
import com.zakaria.ecom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getAllUsers(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value = "size",defaultValue = "15") int size) {

        return userService.getAll(page,size);
    }
    @GetMapping("/{id}")
    public User getUser(@RequestParam Long id) {
        return userService.get(id);
    }
    @PostMapping("")
    public User addUser(@Valid @RequestBody UserRequest user) {
        return userService.add(user);
    }
    @PutMapping("/{id}")
    public User addUser(@RequestBody User user,@RequestParam Long id) {
        return userService.update(id,user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@RequestParam Long id) {
        return userService.delete(id);
    }


}
