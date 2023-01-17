package com.spring.service;

import com.spring.model.User;
import com.spring.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String saveUserData(User user){
        User u = userRepo.save(user);

        return "successfull !!";
    }

    public User findUser(Long id){
        return userRepo.findById(id).get();
    }
}
