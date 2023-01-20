package com.spring.service;

import com.spring.model.File;
import com.spring.model.User;
import com.spring.repo.FileRepo;
import com.spring.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FileRepo fileRepo;
    private final String folderPath = "C:\\Users\\mhmdy\\Desktop\\test-files\\";

    public String saveImageToSystem(MultipartFile userFile) throws IOException{
        String filePath = folderPath+userFile.getOriginalFilename();
        File file = fileRepo.save(File.builder()
                        .type(userFile.getContentType())
                        .filePath(filePath)
                .build());

        userFile.transferTo(new java.io.File(filePath));

        if(file != null){
            return "successfully added !!";
        }

        return null;
    }

    public byte[] getImageFromSystem(int id) throws IOException {

        Optional<File> dbFile = fileRepo.findById(id);

        String filepath = dbFile.get().getFilePath();
        byte[] image = Files.readAllBytes(new java.io.File(filepath).toPath());
        return image;
    }



    @Transactional
    public String saveUserData(User user) {
        User u = userRepo.save(user);

        return "successfull !!";
    }

    @Transactional
    public User findUser(Long id) {
        return userRepo.findById(id).get();
    }


}
