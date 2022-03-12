package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_ROLE")
public class Role {

    @Id
    @Column(name = "ROLE_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "ROLE", length = 50)
    @NotNull
    private String role;

/*    @ManyToMany(mappedBy = "roles")
    private List<User> users;*/
}
