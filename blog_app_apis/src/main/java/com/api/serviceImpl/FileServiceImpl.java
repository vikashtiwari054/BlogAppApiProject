package com.api.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
		
		// File name
		String name=multipartFile.getOriginalFilename();
		
		// random name generator file
		String randomID=UUID.randomUUID().toString();
		String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));
		
		// full path
		String filePath=path + File.separator + fileName1;
		
        // create folder if not created  
		File f=new File(path);
		
		if(!f.exists())
		{
			f.mkdir();
		}
		
		// file copy 
		Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
        String fullPath=path+ File.separator + fileName;
        		
		InputStream  inputStream=new FileInputStream(fullPath);
		
		return inputStream;
	}

	
	
}