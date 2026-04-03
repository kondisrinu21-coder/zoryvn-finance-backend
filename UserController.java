package com.zorvyn.controller;
import com.zorvyn.model.User;
import com.zorvyn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/users")
public class UserController {
    @Autowired UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody CreateUserRequest req){
        return ResponseEntity.ok(userService.createUser(req.email(),req.role()));
    }
}
record CreateUserRequest(String email,String role){}
