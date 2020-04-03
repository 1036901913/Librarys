package com.etc.maven.service.impl;

import com.etc.maven.dao.MyInfoMapper;
import com.etc.maven.domain.ExcelBean;
import com.etc.maven.domain.MyInfo;
import com.etc.maven.domain.Page;
import com.etc.maven.service.MyInfoService;
import com.etc.maven.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyInfoServiceImpl implements MyInfoService {

    @Autowired
    private MyInfoMapper infoMapper;

    @Override
    public int insertInfo(MyInfo info) {
        return infoMapper.insertInfo(info);
    }

    @Override
    public List<MyInfo> queryInfo() {
        return infoMapper.queryInfo();
    }

    @Override
    public int delInfo(Integer mid) {
        return infoMapper.delInfo(mid);
    }

    @Override
    public int updateInfo(MyInfo info) {
        return infoMapper.updateInfo(info);
    }

    @Override
    public XSSFWorkbook exportExcel() throws Exception {
        //根据条件查询数据
        List<Map<String, Object>> list = infoMapper.queryAllInfo();
        //System.out.println(list);
        List<ExcelBean> excel = new ArrayList<>();
        Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
        //设置标题栏
        excel.add(new ExcelBean("序号", "mid", 0));
        excel.add(new ExcelBean("姓名", "mname", 0));
        excel.add(new ExcelBean("手机号", "mphone", 0));
        excel.add(new ExcelBean("图片路径", "mimg", 0));
        map.put(0, excel);
        String sheetName = "用户信息表11";
        //调用ExcelUtil方法
        XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(MyInfo.class, list, map, sheetName);
        System.out.println(xssfWorkbook);
        return xssfWorkbook;

    }

}
