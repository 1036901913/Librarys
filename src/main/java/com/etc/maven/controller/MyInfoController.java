package com.etc.maven.controller;

import com.etc.maven.domain.MyInfo;
import com.etc.maven.service.MyInfoService;
import com.etc.maven.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        if (CacheUtil.getList("infos").isEmpty()) {
            CacheUtil.setList("infos", infoService.queryInfo());
        }
        List<MyInfo> infos = CacheUtil.getList("infos");

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
}
