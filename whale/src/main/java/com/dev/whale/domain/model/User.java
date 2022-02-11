package com.dev.whale.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_MAIL")
    private String mail;

    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @Column(name = "TERM_DATE")
    private Date termDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    @Column(name = "TERM_YN")
    private String termYn;
}
