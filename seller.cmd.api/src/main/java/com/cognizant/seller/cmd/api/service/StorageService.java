package com.cognizant.seller.cmd.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
	byte[] store(MultipartFile file, Path uploadPath);
}
