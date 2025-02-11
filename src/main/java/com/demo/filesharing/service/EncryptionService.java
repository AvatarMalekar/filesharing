package com.demo.filesharing.service;

public interface EncryptionService {

	byte[] encrypt(byte[] bs, String key) throws Exception;
	byte[] decrypt(byte[] encryptedData, String key) throws Exception;
}
