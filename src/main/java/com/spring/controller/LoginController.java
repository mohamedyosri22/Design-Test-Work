package com.spring.controller;

import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/img")
    public ResponseEntity<?> saveFile(@RequestParam("image")MultipartFile file) throws IOException {
        String response = userService.saveImageToSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/img")
    public ResponseEntity<?> getFile(@RequestParam("id")int id) throws IOException {
        byte[] dbImage = userService.getImageFromSystem(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(dbImage);
    }


}
