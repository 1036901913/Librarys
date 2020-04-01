package com.etc.maven.dao;

import com.etc.maven.domain.MyInfo;

import java.util.List;

public interface MyInfoMapper {

    int insertInfo(MyInfo info);

    List<MyInfo> queryInfo();

    int delInfo(Integer mid);
}