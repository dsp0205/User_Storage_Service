package com.example.userstorage.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.userstorage.service.StorageService;

class DownloadControllerTest {

   @Test
   void testDownloadFile() throws IOException {
      // Mock the StorageService dependency
      StorageService mockService = mock(StorageService.class);
      when(mockService.downloadFile("file.txt")).thenReturn("File content".getBytes());

      // Create the controller instance with the mock dependency

      
      DownloadController controller = new DownloadController();
      controller.setService(mockService);

      // Call the controller method and verify the response
      ResponseEntity<ByteArrayResource> response = controller.downloadFile("file.txt");
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(response.getHeaders().get("Content-type").get(0)).isEqualTo("application/octet-stream");
      assertThat(response.getHeaders().get("Content-disposition").get(0))
            .isEqualTo("attachment; filename=\"file.txt\"");
      assertThat(response.getBody()).isInstanceOf(ByteArrayResource.class);
      assertThat(response.getBody().getByteArray()).isEqualTo("File content".getBytes());
   }

}