package com.dev.whale.domain.model;

import com.dev.whale.BaseTimeEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@Entity
@Table(name = "TB_USER")
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

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
