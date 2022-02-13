package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TB_CATEGORY")
public class Category {

    @Id
    @Column(name = "CATEGORY_NO")
    @NotNull
    private int categoryNo;

    @Column(name = "CATEGORY_NAME")
    @NotNull
    private String categoryName;
}
