package com.myboot.controller;

import com.myboot.util.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author hui
 * @date 2022/11/29 18:46
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class DriverController {

    /**
     * 上传
     *
     * @return ResultInfo
     */
    @RequestMapping("/upload")
    public ResultInfo upload( @RequestParam("file") MultipartFile file){
        //获取原始名称
        String fileName = file.getOriginalFilename();
        //文件保存路径
        String filePath = "C:/iis_jxc/sharepic_path/ruilida/";
        //文件重命名,防止重复
        filePath = filePath + fileName;
        //文件对象
        File dest = new File(filePath);
        //判断路径是否存在,如果不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存到服务器中
            file.transferTo(dest);
            return ResultInfo.success("上传成功", filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除
     *
     * @return ResultInfo
     */
    @RequestMapping("/delete")
    public ResultInfo delete(HttpServletRequest request) throws IOException {
        //获取原始名称
        String orderNumber = request.getParameter("order_number");
        String path = request.getParameter("path");
        for (int i=1; i<=14; i++) {
            String filepath = "";
            if(i < 10){
                filepath = "C:/iis_jxc/sharepic_path" + path + orderNumber + "-0" + i + ".jpg";
            }else{
                filepath = "C:/iis_jxc/sharepic_path" + path + orderNumber + "-" + i + ".jpg";
            }
            File dir = new File(filepath);
            if(dir.isFile() && dir.exists()) {
                dir.delete();
            }
        }
        return ResultInfo.success("删除成功",orderNumber);
    }


}
