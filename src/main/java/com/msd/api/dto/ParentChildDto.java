package com.msd.api.dto;

import com.msd.api.domain.Child;
import com.msd.api.domain.Parent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ParentChildDto {

    private int id;
    private String sender;
    private String receiver;
    private Double totalAmount = 0.00;
    private Double paidAmount = 0.00;


    public ParentChildDto(Child child) {
        this.id = child.getId();
        this.sender = child.getParentId().getSender();
        this.receiver = child.getParentId().getReceiver();
        this.totalAmount = child.getParentId().getTotalAmount();
        this.paidAmount = child.getPaidAmount();
    }
}
