package com.demo.filesharing.utility;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

public class Utility {

	 // Method to extract file extension from the file name
    public static String getFileExtension(String fileName) {
        // Check if file name is not null and contains a dot
        if (fileName != null && fileName.lastIndexOf('.') > 0) {
            // Extract the substring after the last dot
            return fileName.substring(fileName.lastIndexOf('.') + 1);
        }
        return ""; // If no extension is found
    }
	
}
