package com.zorvyn.service;
import com.zorvyn.model.User;
import com.zorvyn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired UserRepository userRepo;
    public User createUser(String email,String role){
        return userRepo.save(new User(email,role));
    }
    public User findByEmail(String email){
        return userRepo.findByEmail(email).orElse(null);
    }
}
