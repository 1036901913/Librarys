package com.etc.maven.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Page implements Serializable {

    private Integer startNum;
    private Integer currPage;
}
