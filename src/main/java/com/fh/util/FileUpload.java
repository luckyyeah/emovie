package com.fh.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 上传文件
 * 创建人：FH 创建时间：2014年12月23日
 * @version
 */
public class FileUpload {

	/**
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}
	
	/**
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	   /**
     * @param resourceFile
     * @return file path
	 * @throws IOException 
     * @throws UnsupportedFileTypeException
     * @throws WriteFileException
     */
    public static String saveFile( CommonsMultipartFile resourceFile) throws IOException {
     
    	if(resourceFile.getSize() <=0)
    		return "";
    		
        String contentType = resourceFile.getContentType();
        String originalFilename =resourceFile.getOriginalFilename();
      
        String suffix_name=".jpg";
        //取得后缀名
        if(originalFilename!=null&& originalFilename.lastIndexOf(".") >=0){
        	suffix_name = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        if (contentType.equals("image/jpg")) {
            suffix_name = ".jpg";
        } else if (contentType.equals("image/jpeg")) {
            suffix_name = ".jpeg";
        } else if (contentType.equals("image/png")) {
            suffix_name = ".png";
        } else if (contentType.equals("video/mp4")) {
            suffix_name = ".mp4";
        } else if (contentType.equals("image/gif")) {
            suffix_name = ".gif";
        } else if (contentType.equals("application/pdf")) {
            suffix_name = ".pdf";
        } else if (resourceFile.getOriginalFilename().toUpperCase().endsWith("APK")) {
            suffix_name = ".apk";
        } else if (contentType.equals("application/octet-stream") && resourceFile.getOriginalFilename().toUpperCase().endsWith("JPEG")) {
            suffix_name = ".jpeg";
        } else if (contentType.equals("application/octet-stream") && resourceFile.getOriginalFilename().toUpperCase().endsWith("PNG")) {
            suffix_name = ".png";
        } 

        String filename = UUID.randomUUID().toString() + suffix_name;
        String writeFilePath = Const.UPLOAD_PATH+ filename;
        File file = new File(writeFilePath);
        try {
            FileUtils.writeByteArrayToFile(file, resourceFile.getBytes());
        } catch (IOException e) {
                  throw new IOException("Write uploaded file error");
        }

        return Const.ACCESS_PATH + filename;
    }
    public static boolean  isNotBlank(String inputString){
    	if(inputString ==null || "".equals(inputString)){
    		return false;
    	}
    	return true;
    }
    public static boolean  isBlank(String inputString){
    	if(inputString ==null || "".equals(inputString)){
    		return true;
    	}
    	return false;
    }
	public static void isExistDir(String path) {
		  File file = new File(path);
		  //判断文件夹是否存在,如果不存在则创建文件夹
		  if (!file.exists()) {
		   file.mkdir();
		  }
	}
	public static void writeFile(String filePath,String content){

		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(content);      
	        writer.close(); 

	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean isWindowOs(){
		String OS = System.getProperty("os.name").toLowerCase();
		// 如果是window操作系统取window目录
		if (OS.indexOf("WINDOW") >= 0) {
			return true;
		}
		return false;
	}
    public static boolean checkMobileOS( HttpServletRequest request) {

        String userAgent = request.getHeader("user-agent");
        String[] keywords = {"Android", "iPhone", "iPod", "iPad", "Windows Phone"};
        for (String keyword : keywords) {
            if (userAgent.contains(keyword)) {

                return true;
            }
        }

        return false;
    }
}
