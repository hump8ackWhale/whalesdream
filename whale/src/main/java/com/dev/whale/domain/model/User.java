package com.dev.whale.domain.model;

import com.dev.whale.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "TB_USER")
@Where(clause = "enabled = true")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", unique = true, length = 50, updatable = false)
    private String username;

    @Column(name = "NICKNAME", length = 50)
    private String nickname;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled = true;

    @Column(name = "EMAIL")
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;

    @Column(name = "TERM_DATE", insertable = false)
    private LocalDateTime termDate;

    @Column(name = "ISSUE_YN")
    private String issueYn = "N";

    @Column(name = "NAVER_LOGIN_YN")
    private String naverLoginYn;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
/*
    @ManyToOne
    @JoinColumn(name = "ROLE_ID" , insertable = false, updatable = false)
    @ToString.Exclude
    private Role role;
*/
    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Builder
    public User(String username, String email, String nickname, Role role) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public User update(String username, String nickname, String naverLoginYn) {
        this.username = username;
        this.nickname = nickname;
        this.naverLoginYn = naverLoginYn;

        return this;
    }
}
