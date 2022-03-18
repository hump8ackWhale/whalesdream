package com.dev.whale.domain.model;

import com.dev.whale.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "TB_QNA")
@SequenceGenerator(
        name = "QNA_SEQ_GEN",
        sequenceName = "QNA_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@SQLDelete(sql = "UPDATE TB_QNA Q SET Q.STATUS = 'D' WHERE Q.QNA_NO=?")
@Where(clause = "status != 'D'")
public class Qna extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QNA_NO")
    @NotNull
    private int qnaNo;

    @Column(name = "USERNAME")
    @NotNull
    private String username;

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

    @Column(name = "LOCK_YN")
    @NotNull
    private String lockYn = "N";

    @Column(name = "STATUS")
    @NotNull
    private String status = "I";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_NO" , insertable = false, updatable = false) // 읽기 전용
    @ToString.Exclude
    private Category category;

}
