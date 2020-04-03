package com.etc.maven.service;

import com.etc.maven.domain.MyInfo;
import com.etc.maven.domain.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;

public interface MyInfoService {
    int insertInfo(MyInfo info);

    List<MyInfo> queryInfo();

    int delInfo(Integer mid);

    int updateInfo(MyInfo info);

    XSSFWorkbook exportExcel() throws Exception;

}
