package com.etc.maven.service.impl;

import com.etc.maven.dao.MyInfoMapper;
import com.etc.maven.domain.MyInfo;
import com.etc.maven.service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
