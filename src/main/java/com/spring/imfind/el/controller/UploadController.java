package com.spring.imfind.el.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.imfind.el.domain.AttachFileDTO;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadController {

	@GetMapping("/uploadAjax")
	public String uploadAjax() {
		System.out.println("<--upload ajax-->");

		return "el/Board/uploadAjax";
	}
	/*
	 * private String getFolder() { SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd"); Date date = new Date(); String str =
	 * sdf.format(date); return str.replace("-", File.separator); }
	 * 
	 * private boolean checkImageType(File file) { try { String contentType =
	 * Files.probeContentType(file.toPath());
	 * 
	 * return contentType.startsWith("image"); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * return false; }
	 * 
	 * @PostMapping(value = "/uploadAjaxAction", produces =
	 * MediaType.APPLICATION_JSON_UTF8_VALUE)
	 * 
	 * @ResponseBody public ResponseEntity<List<AttachFileDTO>>
	 * uploadAjaxPost(MultipartFile[] uploadFile) {
	 * 
	 * List<AttachFileDTO> list = new ArrayList<>(); String uploadFolder =
	 * "/Users/hongmac/Documents/upload/temp";
	 * 
	 * System.out.println("<--upload ajax post-->");
	 * 
	 * String uploadFolderPath = getFolder(); // 폴더 생성 File uploadPath = new
	 * File(uploadFolder, uploadFolderPath);
	 * 
	 * if (uploadPath.exists() == false) { uploadPath.mkdirs(); }
	 * 
	 * for (MultipartFile multipartFile : uploadFile) {
	 * System.out.println("-----------------------------");
	 * System.out.println("upload File Name : " + multipartFile);
	 * System.out.println("upload File Size : " + multipartFile.getSize());
	 * 
	 * AttachFileDTO attachDTO = new AttachFileDTO();
	 * 
	 * String uploadFileName = multipartFile.getOriginalFilename();
	 * 
	 * uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") +
	 * 1); attachDTO.setFileName(uploadFileName);
	 * 
	 * System.out.println("only file name : " + uploadFileName);
	 * 
	 * // 파일 이름 중복 방지 UUID uuid = UUID.randomUUID(); uploadFileName =
	 * uuid.toString() + "_" + uploadFileName;
	 * 
	 * try { File saveFile = new File(uploadPath, uploadFileName);
	 * multipartFile.transferTo(saveFile);
	 * 
	 * attachDTO.setUuid(uuid.toString());
	 * attachDTO.setUploadPath(uploadFolderPath);
	 * 
	 * if (checkImageType(saveFile)) {
	 * 
	 * attachDTO.setImage(true);
	 * 
	 * FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" +
	 * uploadFileName));
	 * Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100,
	 * 100); thumbnail.close(); }
	 * 
	 * list.add(attachDTO);
	 * 
	 * } catch (Exception e) { // e.getMessage(); e.printStackTrace(); }
	 * 
	 * } return new ResponseEntity<>(list, HttpStatus.OK); }
	 * 
	 * @GetMapping("/display")
	 * 
	 * @ResponseBody public ResponseEntity<byte[]> getFile(String fileName) {
	 * System.out.println("fileName : " + fileName);
	 * 
	 * File file = new File("/Users/hongmac/Documents/upload/temp");
	 * 
	 * System.out.println("file : " + file);
	 * 
	 * ResponseEntity<byte[]> result = null;
	 * 
	 * try { HttpHeaders header = new HttpHeaders();
	 * 
	 * header.add("Content-Type", Files.probeContentType(file.toPath())); result =
	 * new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header,
	 * HttpStatus.OK); } catch (IOException e) { e.printStackTrace(); } return
	 * result; }
	 */
	 private String getFolder() { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	 	Date date = new Date(); 
		String str = sdf.format(date); 
		
		return str.replace("-", File.separator); 
	}
	
	 private boolean checkImageType(File file) { 
		 
		 try { 
			 String contentType = Files.probeContentType(file.toPath());
			 
			 return contentType.startsWith("image"); 
		 
		 } catch (IOException e) {
			 e.printStackTrace(); 
		 }
		 return false; 
	} 
	 										 
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/uploadAjaxAction",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		System.out.println("update ajax post");
		
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "/Users/hongmac/Documents/upload/temp";
		
		String uploadFolderPath = getFolder();
		// 폴더 생성 
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		System.out.println("upload path: " + uploadPath);
		
			if (uploadPath.exists() == false) { 
				uploadPath.mkdirs(); 
			
			} 
			
		
		for (MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			System.out.println("-----------------------------");
			System.out.println("upload File Name : " + multipartFile.getOriginalFilename());
			System.out.println("upload File Size : " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName= uuid.toString() + "_" + uploadFileName;
			
			System.out.println("only file name : " + uploadFileName);
			
			//File saveFile = new File(uploadFolder, uploadFileName);

			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				// check image type file
				if (checkImageType(saveFile)) {
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				
			} catch (Exception e) {
				//e.getMessage();
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}