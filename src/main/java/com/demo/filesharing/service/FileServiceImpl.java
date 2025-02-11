package com.demo.filesharing.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.filesharing.bean.ResponseBean;
import com.demo.filesharing.entity.FileMetadata;
import com.demo.filesharing.repository.FileRepository;
import com.demo.filesharing.utility.Utility;

@Service
public class FileServiceImpl implements FileService {
//    private final String UPLOAD_DIR = "uploads/";

	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private EncryptionService encryptionService;
	
	
	public FileMetadata saveFile(MultipartFile file, String passcode) {
		String encryptedFileName = UUID.randomUUID().toString();
		String fileName = file.getOriginalFilename();
		String fileExtension = Utility.getFileExtension(fileName);
//        Path filePath = Paths.get(UPLOAD_DIR + encryptedFileName);
		byte[] encryptedData = null;
		try {
			encryptedData = encryptionService.encrypt(file.getBytes(), passcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// to save data in folder but not in db
//        try {
//			Path write = Files.write(filePath, encryptedData);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		FileMetadata metadata = new FileMetadata();
		metadata.setFileName(encryptedFileName);
//        metadata.setFilePath(filePath.toString());
//        metadata.setPasscodeHash(Base64.getEncoder().encodeToString(passcode.getBytes()));
		metadata.setUploadTime(LocalDateTime.now());
		metadata.setFileExtension(fileExtension);
		metadata.setFileData(encryptedData);

		return fileRepository.save(metadata);
	}

	public ResponseBean loadFile(String fileName, String passcode) {
		FileMetadata metadata = fileRepository.findByFileName(fileName)
				.orElseThrow(() -> new RuntimeException("File not found"));

//         Path filePath = Paths.get(metadata.getFilePath());
		byte[] encryptedData = null;
		byte[] decryptedData = null;
		try {
//			encryptedData = Files.readAllBytes(filePath);
			encryptedData = metadata.getFileData();
			decryptedData = encryptionService.decrypt(encryptedData, passcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResponseBean bean = new ResponseBean();
		bean.setFileData(decryptedData);
		bean.setFileExtension(metadata.getFileExtension());

		return bean;
	}
}
