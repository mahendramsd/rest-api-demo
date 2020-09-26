package com.msd.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Parent {

    @Id
    private int id;
    private String sender;
    private String receiver;
    private Double totalAmount;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parentId")
    private List<Child> childList;
}
