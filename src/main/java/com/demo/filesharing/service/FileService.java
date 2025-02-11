package com.demo.filesharing.service;

import org.springframework.web.multipart.MultipartFile;

import com.demo.filesharing.bean.ResponseBean;
import com.demo.filesharing.entity.FileMetadata;

public interface FileService {
	
	FileMetadata saveFile(MultipartFile file, String passcode);
	ResponseBean loadFile(String fileName, String passcode);

}
