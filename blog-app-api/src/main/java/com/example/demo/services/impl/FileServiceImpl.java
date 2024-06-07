package com.example.demo.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.services.FileService;

public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String name = file.getOriginalFilename();
		
		String randomId = UUID.randomUUID().toString();
		String filname = randomId.concat(name.substring(name.lastIndexOf(".")));
		String filePath = path + File.separator + filname;
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getReource(String path, String filename) throws FileNotFoundException {
		String fullPath = path + File.separator + filename;
		InputStream i = new FileInputStream(fullPath);
		return i;
	}
}
