package com.example.ansh.oncare.controller;

import com.example.ansh.oncare.service.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/docs")
public class FileUploaderController {
    @Autowired
    private FileUploaderService fileUploaderService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            String fileId = fileUploaderService.uploadPdf(file);
            return ResponseEntity.ok(fileId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String id) {
        byte[] pdf = fileUploaderService.getPdf(id);

        if (pdf == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}


