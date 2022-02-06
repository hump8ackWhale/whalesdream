package com.dev.whale.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Post {

    private int postNo;
    private String title;
    private String content;
    private Date modifyDate;
    private String lockYn;
    private String status;
}
