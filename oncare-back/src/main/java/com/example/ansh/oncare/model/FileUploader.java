package com.example.ansh.oncare.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pdfs")
public class FileUploader {
    @Id
    private String id;
    private String filename;
    private String contentType;
    private byte[] data;
}
