package com.spring.imfind.el.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.spring.imfind.el.domain.AttachVO;
import com.spring.mapper.NoticeAttachMapper;

public class FileCheckTask {

	@Autowired
	private SqlSession sqlSession;
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() throws Exception {
		System.out.println("File Check Task run------------------------------");
		System.out.println(new Date());
		
		// file list in database
		NoticeAttachMapper attachMapper = sqlSession.getMapper(NoticeAttachMapper.class);
		List<AttachVO> fileList = attachMapper.getOldFiles();
		
		// ready for check file in directory with database file list
		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("/Users/hongmac/Documents/upload/temp/", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName())).collect(Collectors.toList());
		
		// image file has thumbnail file
		fileList.stream().filter(vo -> vo.isFileType() == true).map(vo -> Paths.get("/Users/hongmac/Documents/upload/temp/", "s_" + vo.getUuid() + "_" + vo.getFileName())).forEach(p -> fileListPaths.add(p));
		
		System.out.println("==================================================");
		
		fileListPaths.forEach(p -> System.out.println(p));
		
		
		// files in yesterday directory
		File targetDir = Paths.get("/Users/hongmac/Documents/upload/temp/", getFolderYesterDay()).toFile();
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		System.out.println("==================================================");
		for (File file : removeFiles) {
			System.out.println("get AbsolutePath:: " + file.getAbsolutePath());
			file.delete();
			
		}
		
	}
	
}
