package com.etc.maven.service;

import com.etc.maven.domain.MyInfo;

import java.util.List;

public interface MyInfoService {
    int insertInfo(MyInfo info);

    List<MyInfo> queryInfo();

    int delInfo(Integer mid);
}
