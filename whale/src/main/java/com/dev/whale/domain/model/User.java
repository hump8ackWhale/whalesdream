package com.dev.whale.domain.model;

import com.dev.whale.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "TB_USER")
@Where(clause = "enabled = true")
@Data
@AllArgsConstructor
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
    private Date termDate;

    @Column(name = "TERM_YN")
    private String termYn = "N";

    @Column(name = "ISSUE_YN")
    private String issueYn = "N";

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    private User() {

    }

    @Builder
    public User(String username, String email, String nickname, Role role) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public User update(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;

        return this;
    }
}
