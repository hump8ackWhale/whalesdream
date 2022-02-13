package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
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
    @NotNull
    private int postNo;

    @Column(name = "ID")
    @NotNull
    private String id;

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
    @Nullable
    private String lockYn = "N";

    @Column(name = "STATUS")
    @NotNull
    private String status;

    @PrePersist
    public void prePersist() {
        this.insertDate = this.insertDate == null ? new Date() : new Date();
    }
}
