package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", unique = true, length = 50)
    @NotNull
    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled = true;

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

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
