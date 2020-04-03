package com.etc.maven.dao;

import com.etc.maven.domain.MyInfo;
import com.etc.maven.domain.Page;

import java.util.List;
import java.util.Map;

public interface MyInfoMapper {

    int insertInfo(MyInfo info);

    List<MyInfo> queryInfo();

    int delInfo(Integer mid);

    int updateInfo(MyInfo info);

    List<Map<String, Object>> queryAllInfo();
}