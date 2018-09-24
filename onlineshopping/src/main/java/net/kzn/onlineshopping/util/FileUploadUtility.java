package net.kzn.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtility {
	private static final String ABS_PATH="F:\\java project\\spring\\online-shopping\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH="";
	private static final Logger logger=LoggerFactory.getLogger(FileUploadUtility.class);
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		//get the real part
		REAL_PATH=request.getSession().getServletContext().getRealPath("assets/images/");
		logger.info(REAL_PATH);
		//to make sure all directory are exit
		//if not make the directory
		
		if(!new File(ABS_PATH).exists()) {
			//create the new directory
			new File(ABS_PATH).mkdirs();
		}
		if(!new File(REAL_PATH).exists()) {
			//create the new directory
			new File(REAL_PATH).mkdirs();
		}
		try { 
			//this is for server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			//this is for project dir
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			
		}catch(IOException ex) {
			
		}
	}

}
