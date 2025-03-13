package com.example.ansh.oncare.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploaderService {
    @Autowired
    private GridFSBucket gridFSBucket;

    public String uploadPdf(MultipartFile file) throws IOException {
        GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);
        ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), file.getInputStream(), options);
        return fileId.toHexString();
    }

    public byte[] getPdf(String id) {
        GridFSDownloadStream stream = gridFSBucket.openDownloadStream(new ObjectId(id));
        byte[] data = new byte[(int) stream.getGridFSFile().getLength()];
        stream.read(data);
        stream.close();
        return data;
    }
}
