package com.dev.whale.domain.model;

import com.dev.whale.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE TB_REPLY R SET R.STATUS = 'D' WHERE R.REPLY_NO=?")
@Where(clause = "status != 'D'")
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_NO")
    @NotNull
    private int replyNo;

    @Column(name = "QNA_NO")
    @NotNull
    private int qnaNo;

    @Column(name = "USERNAME")
    @NotNull
    private String username;

    @Column(name = "CONTENT", length = 5000)
    @NotNull
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Column(name = "STATUS")
    @NotNull
    private String status = "I";

}
