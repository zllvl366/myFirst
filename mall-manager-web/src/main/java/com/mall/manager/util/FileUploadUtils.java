package com.mall.manager.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zll on 2020/1/8.
 * 文件上传的工具类
 */
public class FileUploadUtils {

    /**
     *
     * @param multipartFile
     * @param rootDir 根目录
     * @param fileType 文件类型（区分文件夹）
     * @return
     * @throws Exception
     */
    public static Map<String,Object> uploadFile(MultipartFile multipartFile,String rootDir,String fileType) throws Exception {

        //根据附件上传的年月进行文件夹分类
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        String path = File.separator + fileType + File.separator
                + year + File.separator + month + File.separator + date + File.separator;
        //文件保存
        String filename = multipartFile.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf("."))
                .toLowerCase();
        //文件在服务器存储的名称
        String fileTempName = UUID.randomUUID().toString() + extName;
        if (!path.endsWith(java.io.File.separator)) {
            path = path + java.io.File.separator;
        }
        File temp = new File(rootDir+path);
        if (!temp.isDirectory()) {
            temp.mkdirs();
        }
        String fileFullPath = rootDir+path + fileTempName;
        FileCopyUtils.copy(multipartFile.getBytes(), new File(fileFullPath));
        Map<String,Object> map = new HashMap();
        //文件全路径
        if(rootDir.indexOf("D:")<0){//服务器
            fileFullPath = "http://47.95.219.27:8688" + path + fileTempName;
        }
        map.put("filePath",fileFullPath);
        map.put("fileName",filename);
        return map;
    }
}
