package com.example.userstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.userstorage.service.StorageService;

@RestController
public class DownloadController {

   @Autowired
   private StorageService service;
   public void setService(StorageService service) {
      this.service = service;
   }

   

   @GetMapping("/download/{fileName}")
   public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
      byte[] data = service.downloadFile(fileName);
      ByteArrayResource resource = new ByteArrayResource(data);
      return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
   }

}
