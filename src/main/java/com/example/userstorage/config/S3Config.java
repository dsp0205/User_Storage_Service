package com.example.userstorage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


@Configuration
public class S3Config {

    @Autowired
   @Value("${aws.s3.accesskey}")
   private String accessKey;
   
    @Autowired
   @Value("${aws.s3.secretkey}")
   private String secretKey;

    @Autowired
   @Value("${aws.s3.region}")
   private String region;

    @Autowired
   @Value("${aws.s3.bucketName}")
    private String bucketName;

   @Bean
   public AmazonS3 s3client() {
       BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
       AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(region).build();
       return s3Client;
   }
}