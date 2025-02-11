package com.demo.filesharing.bean;

import lombok.Data;

@Data
public class ResponseBean {
	
	public ResponseBean(){
		
	}
	private byte[] fileData;
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	private String fileExtension;
}
