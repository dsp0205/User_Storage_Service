package com.example.userstorage.factory;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;



public class S3ClientFactory {

    public static S3Client createS3Client(String regionName) {
        Region region = Region.of(regionName);

        S3Client s3 = S3Client.builder()
            .region(region)
            .build();

        return s3;
    }
    
}
