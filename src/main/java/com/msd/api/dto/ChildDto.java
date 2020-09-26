package com.msd.api.dto;

import com.msd.api.domain.Child;
import lombok.Data;

@Data
public class ChildDto {

    private int id;
    private Double paidAmount = 0.00;
    private int parentId;

    public ChildDto(Child child) {
        this.id = child.getId();
        this.paidAmount = child.getPaidAmount();
    }
}
