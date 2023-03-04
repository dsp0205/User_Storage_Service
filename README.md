# User Document Storage Service
 This is a Spring Boot application that creates a REST API to search, upload and download the file in a user storage account.
 
 ## API Reference to Search File

```http
  GET http://localhost:8080/searchFiles?userName=iamdsp&searchTerm=logistics
```

 ## API Reference to Upload File

```http
  POST http://localhost:8080/searchFiles?userName=<username>&searchTerm=<filename>
```

## API Reference to Download File

```http
  GET http://localhost:8080/download/<filename>
```
