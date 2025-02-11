package com.demo.filesharing.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.filesharing.entity.FileMetadata;
import com.demo.filesharing.repository.FileRepository;

@Component
public class FileCleanupScheduler {
    private final FileRepository fileRepository;

    public FileCleanupScheduler(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    
//    @Scheduled(fixedRate = 60) // Runs every 1 minutes
    @Scheduled(fixedRate = 900000) // Runs every 15 minutes
    public void cleanOldFiles() {
//        LocalDateTime thresholdTime = LocalDateTime.now().minusMinutes(1);
    	LocalDateTime thresholdTime = LocalDateTime.now().minusHours(48);
        List<FileMetadata> oldFiles = fileRepository.findByUploadTimeBefore(thresholdTime);
        fileRepository.deleteAll(oldFiles);
    }
}