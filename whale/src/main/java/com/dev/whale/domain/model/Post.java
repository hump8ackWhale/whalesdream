package com.dev.whale.domain.model;

import com.dev.whale.BaseTimeEntity;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
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
    private Long postNo;

    @Column(name = "USERNAME")
    @Nullable
    private String username;

    @Column(name = "TITLE", length = 500)
    @NotNull
    private String title;

    @Column(name = "CONTENT", length = 50000)
    @NotNull
    private String content;

    @Column(name = "LOCK_YN")
    @Nullable
    private String lockYn;

    @Column(name = "STATUS")
    @NotNull
    private String status = "I";

    @Column(name = "END_DATE", updatable = false)
    @NotNull
    private LocalDateTime endDate;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLockYn(String lockYn) {
        this.lockYn = lockYn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @PrePersist
    public void endDateSetting() {
        this.endDate = this.getCreatedDate().plusMonths(1);
    }

    @PreRemove
    public void deletePost() {
        this.status = "D";
    }

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
