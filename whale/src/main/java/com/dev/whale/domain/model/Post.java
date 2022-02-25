package com.dev.whale.domain.model;

import com.dev.whale.BaseTimeEntity;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
@SQLDelete(sql = "UPDATE TB_POST SET status = 'D' WHERE POST_NO=?")
@Where(clause = "status != 'D'")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_NO", updatable = false)
    private int postNo;

    @Column(name = "USERNAME", updatable = false)
    @NotNull
    private String username;

    @Column(name = "TITLE", length = 500)
    @NotNull
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Column(name = "CONTENT", length = 50000)
    @NotNull
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Column(name = "LOCK_YN")
    @Nullable
    private String lockYn = "Y";

    @Column(name = "STATUS")
    @NotNull
    private String status = "I";

    @Column(name = "END_DATE", updatable = false)
    @NotNull
    private LocalDateTime endDate;

    @PrePersist
    public void endDateSetting() {
        this.endDate = this.getCreatedDate().plusMonths(1);
    }

    @PreRemove
    public void deletePost() {
        this.status = "D";
    }

}
