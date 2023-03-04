package com.example.userstorage.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;

import com.amazonaws.services.s3.model.S3ObjectSummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
public class FileController {

    public void setS3Client(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }
    
   @Autowired
   private AmazonS3 s3Client;
   @Value("${aws.s3.bucketName}")
    private String bucketName;

    @GetMapping("/searchFiles")
    public List<String> searchFiles(@RequestParam String userName, @RequestParam String searchTerm) {
       List<String> fileList = new ArrayList<>();
       String prefix = userName + "/";
       ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(prefix).withMaxKeys(1000);
       ListObjectsV2Result result;
    
       do {
           result = s3Client.listObjectsV2(req);
    
           if (result != null) {
               for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                   String key = objectSummary.getKey();
                   if (key.contains(searchTerm)) {
                       fileList.add(key);
                   }
               }
    
               req.setContinuationToken(result.getNextContinuationToken());
           }
       } while (result != null && result.isTruncated());
    
       return fileList;
    }
}