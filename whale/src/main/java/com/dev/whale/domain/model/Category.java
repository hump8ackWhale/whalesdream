package com.dev.whale.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TB_CATEGORY")
public class Category {

    @Id
    @Column(name = "CATEGORY_NO")
    private int categoryNo;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;
}
