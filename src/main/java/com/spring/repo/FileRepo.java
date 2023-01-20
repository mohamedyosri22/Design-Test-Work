package com.spring.repo;

import com.spring.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<File,Integer> {
}
