package com.dev.whale.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Reply {

    private int replyNo;
    private String content;
    private Date modifyDate;
    private String status;
}
