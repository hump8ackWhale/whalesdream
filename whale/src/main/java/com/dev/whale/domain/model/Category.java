package com.dev.whale.domain.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
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

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Qna> qna = new ArrayList<>();

}
