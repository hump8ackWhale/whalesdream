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
@Table(name = "TB_POST")
@SequenceGenerator(
        name = "POST_SEQ_GEN",
        sequenceName = "POST_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_NO")
    private int postNo;

    @Column(name = "USER_NO")
    private int userNo;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "INSERT_DATE")
    private Date insertDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    @Column(name = "LOCK_YN")
    private String lockYn;

    @Column(name = "STATUS")
    private String status;
}
