package com.demo.filesharing.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.filesharing.bean.ResponseBean;
import com.demo.filesharing.entity.FileMetadata;
import com.demo.filesharing.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, @RequestParam String passcode) {
        FileMetadata saveFile = fileService.saveFile(file, passcode);
        String fileName = saveFile.getFileName();
        String url= "http://localhost:8080/files/download/"+fileName+"?passcode="+passcode;
        return ResponseEntity.ok(url);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName, @RequestParam String passcode) {
    	ResponseBean fileData = fileService.loadFile(fileName, passcode);
     // Step 2: Set the proper headers for file download
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        headers.add("Content-Type", "application/pdf");

        return  ResponseEntity.ok().header("Content-Type", "application/"+fileData.getFileExtension())
        		.header("Content-Disposition", "attachment; filename=" + fileName).body(new ByteArrayResource(fileData.getFileData()));
    }
}
