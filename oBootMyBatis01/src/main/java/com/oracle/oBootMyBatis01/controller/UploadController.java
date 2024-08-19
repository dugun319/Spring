package com.oracle.oBootMyBatis01.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UploadController {
		
	@GetMapping(value = "/uploadFormStart")
	public String uploadFormStart(Model model) {
		log.info("UploadController uploadFormStart(Model model) is started");		
		return "uploadFormStart";
	}
	
	@PostMapping(value = "/uploadForm")
	public String uploadForm(HttpServletRequest request, Model model) throws IOException, ServletException {

		Part image 				= request.getPart("file1");
		InputStream inputStream = image.getInputStream(); 
		
		//File extension 확인
		String fileName			= image.getSubmittedFileName();
		String [] split			= fileName.split("\\.");
		String originalName		= split[split.length - 2];
		String suffix			= split[split.length - 1];
		
		log.info("UploadController uploadForm() fileName {}", fileName);
		log.info("UploadController uploadForm() originalName {}", originalName);
		log.info("UploadController uploadForm() suffix {}", suffix);
		
		// Servlet 상속 받지 못 했을 때, realPath 불러 오는 방법
		String uploadPath		= request.getSession().getServletContext().getRealPath("/upload/");
		
		System.out.println("UploadController uploadForm() Post is started");
		String savedName 		= uploadFile(originalName, inputStream, uploadPath, suffix);
		
		log.info("UploadController uploadForm() savedName {}", savedName);
		model.addAttribute("savedName", savedName);
		
		return "uploadResult";
	}

	private String uploadFile(String 		originalName, 
							  InputStream 	inputStream, 
							  String 	uploadPath, 
							  String 	suffix) 
							  throws FileNotFoundException, IOException {
		
		//universally unique identifier(UUID)
		UUID uid = UUID.randomUUID();
		
		//requestPath = requestPath + "/resource/image";
		
		//Create Directory
		File fileDirectory 	= new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("Create Folder for UPLOAD : " + uploadPath);
		}
		
		String savedName 	= uid.toString() + "_" + originalName + "." + suffix;
		log.info("UploadController uploadFile() savedName {}", savedName);
		
		File tempFile		= new File(uploadPath + savedName);
		
		// Backup File request
		File tempFile3		= new File("C:/Backup/" + savedName);
		FileOutputStream outputStream3 = new FileOutputStream(tempFile3);
		
		try (FileOutputStream outputStream = new FileOutputStream(tempFile)){
			int read;
			byte [] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				//Target File request inputStream
				outputStream.write(bytes, 0, read);
				
				//Backup File request inputStream
				outputStream3.write(bytes, 0, read);
			}
		} finally {
			System.out.println("UPLOAD is ENDED");
		} 
		
		// while loop 안에서는 안 닫아줘도 됨.
		outputStream3.close();
		
		return savedName;
	}
	

	@GetMapping(value = "/uploadFileDelete")
	public String uploadFileDelete(HttpServletRequest request, Model model) {
		
		log.info("uploadFileDelete() is started");
		
		String uploadPath		= request.getSession().getServletContext().getRealPath("/upload/");
		String delFileName		= request.getParameter("delFileName");
		String deleteFile		= uploadPath + delFileName;
		String deleteFile3		= "c:/Backup/" + delFileName;
		
		System.out.println("uploadFileDelete() deleteFile -> " + deleteFile);
		
		int delResult = FileDelete(deleteFile);
		FileDelete(deleteFile3);
		
		model.addAttribute("delResult", delResult);
		model.addAttribute("deletFile", deleteFile);
		model.addAttribute("savedName", delFileName);
		
		return "uploadResult";
	}

	private int FileDelete(String delFileName) {
		log.info("uploadFileDelete() is started");
		System.out.println("FileDelete() delFileName -> " + delFileName);
		int result;
		
		File file	= new File(delFileName);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("File Delete is completed");
				result = 1;
			} else {
				System.out.println("File Delete is failed");
				result = 0;
			}
		} else {
			System.out.println("Check the name of FILE first");
			result = -1;
		}
				
		return result;
	}
}
