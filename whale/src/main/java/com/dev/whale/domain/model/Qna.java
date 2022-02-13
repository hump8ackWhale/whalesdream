package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
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
    @NotNull
    private int qnaNo;

    @Column(name = "ID")
    @NotNull
    private String id;

    @Column(name = "CATEGORY_NO")
    @NotNull
    private int categoryNo;

    @Column(name = "TITLE", length = 500)
    @NotNull
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Column(name = "CONTENT", length = 50000)
    @NotNull
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Column(name = "INSERT_DATE", updatable = false)
    @NotNull
    private Date insertDate;

    @Column(name = "MODIFY_DATE", insertable = false)
    private Date modifyDate;

    @Column(name = "LOCK_YN")
    @NotNull
    private String lockYn = "N";

    @Column(name = "STATUS")
    @NotNull
    private String status;

    @PrePersist
    public void prePersist() {
        this.insertDate = this.insertDate == null ? new Date() : new Date();
    }
}
