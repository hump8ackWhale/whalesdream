package com.dev.whale.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {

    private int userNo;
    private String userId;
    private String userName;
    private String userMail;
    private Date joinDate;
    private Date termDate;
    private Date modifyDate;
    private String termYn;
}
