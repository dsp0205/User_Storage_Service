package com.example.userstorage.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@RestController
public class UploadController {
    
    
   @Autowired
   private AmazonS3 s3Client;
   @Autowired
   @Value("${aws.s3.bucketName}")
    private String bucketName;

   @PostMapping("/uploadFile")
   public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String userName) {
       try {
           String key = userName + "/" + file.getOriginalFilename();
           ObjectMetadata metadata = new ObjectMetadata();
           metadata.setContentLength(file.getSize());
           s3Client.putObject("userstoragebucket", key, file.getInputStream(), metadata);
           return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
       } catch (IOException e) {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + file.getOriginalFilename());
       }
   }
}
