package com.mall.manager.controller;

import com.mall.common.utils.JsonUtils;
import com.mall.manager.util.FileUploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zll on 2020/1/8.
 * 图片上传的Controller
 */
@Controller
@RequestMapping("/pic")
public class PicController {

    /**
     * 图片地址
     */
    @Value("${rootDir}")
    private String rootDir;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile uploadFile){
        Map<String,Object> map = new HashMap<>();
        map.put("error",1);
        // 1、 上传文件校验，包括上传文件是否为空、文件名称是否为空、文件格式是否为JPG。
        if(null == uploadFile){
            map.put("message","上传文件不能为空");
            return JsonUtils.objectToJson(map);
        }
        //  文件名
        String fileName = uploadFile.getOriginalFilename();
        if(StringUtils.isBlank(fileName)){
            map.put("message","上传文件名不能为空");
            return JsonUtils.objectToJson(map);
        }
        // 文件扩展名
        String extName = fileName.substring(fileName.lastIndexOf(".")+1);
        if(extName.toUpperCase().indexOf("JPG")<0){
            map.put("message","上传文件格式必须为JPG");
            return JsonUtils.objectToJson(map);
        }
        Map<String, Object> result = new HashMap<>();
        try {
            result = FileUploadUtils.uploadFile(uploadFile, rootDir, "product");
            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","文件上传失败");
            return JsonUtils.objectToJson(map);
        }
        map.put("error",0);
        map.put("url",result.get("filePath"));
        return JsonUtils.objectToJson(map);
    }

}
