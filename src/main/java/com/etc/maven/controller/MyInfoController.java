package com.etc.maven.controller;

import com.etc.maven.domain.MyInfo;
import com.etc.maven.domain.Page;
import com.etc.maven.service.MyInfoService;
import com.etc.maven.util.CacheUtil;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MyInfoController {
    @Autowired
    private MyInfoService infoService;

    //@RestController  代替 @Controller和@ResponseBody

    @RequestMapping("/insert_info")
    public Map<String, Object> insertInfo(MyInfo info) {
        int result = infoService.insertInfo(info);
        Map<String, Object> map = new HashMap<>();
        if (result != -1) {
            CacheUtil.del("infos");
            map.put("code", "200");
            map.put("msg", "success");
        } else {
            map.put("code", "201");
            map.put("msg", "fail");
        }
        return map;
    }

    @RequestMapping("/queryAllInfo")
    public Map<String, Object> queryAllInfo() {

        Map<String, Object> map = new HashMap<>();


        //if (CacheUtil.getList("infos").isEmpty()) {
        //CacheUtil.setList("infos", infoService.queryInfo(param));
        //}
        //List<MyInfo> infos = CacheUtil.getList("infos");
        //List<MyInfo> infos = infoService.queryInfo(page);
        List<MyInfo> infos = infoService.queryInfo();

        if (infos.size() > 0) {
            map.put("code", "200");
            map.put("msg", "success");
            map.put("result", infos);
        } else {
            map.put("code", "201");
            map.put("msg", "success,no data");
        }
        return map;
    }

    @RequestMapping("/del_info/{mid}")
    public Map<String, Object> delInfo(@PathVariable("mid") Integer mid) {
        Map<String, Object> map = new HashMap<>();
        int delResult = infoService.delInfo(mid);
        if (delResult != -1) {
            CacheUtil.del("infos");
            map.put("code", "200");
            map.put("msg", "success");
        } else {
            map.put("code", "201");
            map.put("msg", "fail");
        }
        return map;
    }

    @RequestMapping("/update_info")
    public Map<String, Object> updateInfo(MyInfo info) {
        Map<String, Object> map = new HashMap<>();
        int updateResult = infoService.updateInfo(info);
        if (updateResult != -1) {
            CacheUtil.del("infos");
            map.put("code", "200");
            map.put("msg", "success");
        } else {
            map.put("code", "201");
            map.put("msg", "fail");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        response.reset(); //清除buffer缓存
        //Map<String,Object> map=new HashMap<String,Object>();
        //指定响应的内容类型
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        try {
            // 指定下载的文件名
            String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis()) + "_用户表.xlsx";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes(), "iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            //导出Excel对象
            XSSFWorkbook workbook = infoService.exportExcel();
            OutputStream output;
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.flush();
            workbook.write(bufferedOutput);
            bufferedOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
