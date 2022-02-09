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
@Table(name = "TB_QNA")
@SequenceGenerator(
        name = "QNA_SEQ_GEN",
        sequenceName = "QNA_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QNA_NO")
    private int qnaNo;

    @Column(name = "USER_NO")
    private int userNo;

    @Column(name = "CATEGORY_NO")
    private int categoryNo;

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
