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
@Table(name = "TB_REPLY")
@SequenceGenerator(
        name = "REPLY_SEQ_GEN",
        sequenceName = "REPLY_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_NO")
    private int replyNo;

    @Column(name = "QNA_NO")
    private int qnaNo;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "INSERT_DATE")
    private Date insertDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    @Column(name = "STATUS")
    private String status;
}
