package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
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
    @NotNull
    private int replyNo;

    @Column(name = "QNA_NO")
    @NotNull
    private int qnaNo;

    @Column(name = "CONTENT", length = 5000)
    @NotNull
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Column(name = "INSERT_DATE", updatable = false)
    @NotNull
    private Date insertDate;

    @Column(name = "MODIFY_DATE", insertable = false)
    private Date modifyDate;

    @Column(name = "STATUS")
    @NotNull
    private String status;

    @PrePersist
    public void prePersist() {
        this.insertDate = this.insertDate == null ? new Date() : new Date();
    }
}
