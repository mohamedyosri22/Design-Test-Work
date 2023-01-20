package com.spring.controller;

import Utils.ImageUtil;
import com.spring.model.Image;
import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    private static Long userId;

    @GetMapping("/user-img/{id}")
    public ResponseEntity<?> getUserImg(@PathVariable("id") Long id){
        User user = userService.findUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(ImageUtil.decompressImage(user.getImage().getImageData()));
    }

    @GetMapping("/user-data/{id}")
    public ResponseEntity<?> getUserData(@PathVariable("id") Long id){
        User user = userService.findUser(id);

        Long dbUserId = user.getId();
        String email = user.getEmail();
        String phone = "01122310599";

        return ResponseEntity.status(HttpStatus.OK)
                .body("{" +
                        "\"id\":"+dbUserId+
                        ",\"email\":\""+email+"\""+
                        ",\"phone\":\""+phone+"\""+
                        "}");
    }

    @PostMapping("/user-data")
    public ResponseEntity<?> saveUserData(@RequestBody User user){
        String response = userService.saveUserData(user);

        userId = user.getId();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/user-img")
    public ResponseEntity<?> saveUserImg(@RequestParam("image")MultipartFile image) throws IOException {
        User user = userService.findUser(userId);


        user.setImage(Image.builder()
                .imageData(ImageUtil.compressImage(image.getBytes()))
                .build());

        String response = userService.saveUserData(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
