package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "TB_ROLE")
public class Role {

    @Id
    @Column(name = "ROLE_ID")
    private int roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

}
