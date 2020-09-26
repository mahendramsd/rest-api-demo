package com.msd.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Child {

    @Id
    private int id;
    private Double paidAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Parent parentId;


}
