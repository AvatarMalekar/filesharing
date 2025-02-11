package com.demo.filesharing.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.filesharing.entity.FileMetadata;

@Repository
public interface FileRepository extends JpaRepository<FileMetadata, Long> {
    Optional<FileMetadata> findByFileName(String fileName);
    void deleteByUploadTimeBefore(LocalDateTime time);
    @Query("SELECT f FROM FileMetadata f WHERE f.uploadTime < :time")
    List<FileMetadata> findByUploadTimeBefore(LocalDateTime time);
}
