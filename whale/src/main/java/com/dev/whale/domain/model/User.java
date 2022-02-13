package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Data
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @Column(name = "ID", updatable = false)
    @NotNull
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @Column(name = "PASSWORD")
    @NotNull
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Column(name = "USERNAME")
    @NotNull
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 1, max = 10, message = "이름은 1 ~ 10자 이여야 합니다.")
    private String username;

    @Column(name = "ENABLED")
    @NotNull
    private int enabled;

    @Column(name = "EMAIL")
    @NotNull
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;

    @Column(name = "JOIN_DATE", updatable = false)
    @NotNull
    private Date joinDate;

    @Column(name = "TERM_DATE", insertable = false)
    private Date termDate;

    @Column(name = "MODIFY_DATE", insertable = false)
    private Date modifyDate;

    @Column(name = "TERM_YN")
    @NotNull
    private String termYn = "N";

    @PrePersist
    public void prePersist() {
        this.joinDate = this.joinDate == null ? new Date() : new Date();
    }

}
