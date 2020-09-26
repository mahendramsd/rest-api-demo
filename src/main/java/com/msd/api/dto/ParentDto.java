package com.msd.api.dto;

import com.msd.api.domain.Parent;
import lombok.Data;

@Data
public class ParentDto {

    private int id;
    private String sender;
    private String receiver;
    private Double totalAmount;
    private Double totalPaidAmount = 0.00;

    public ParentDto(Parent parent) {
        this.id = parent.getId();
        this.sender = parent.getSender();
        this.receiver =parent.getReceiver();
        this.totalAmount = parent.getTotalAmount();
        setPaidTotal(parent);
    }

    public void setPaidTotal(Parent parent) {
        parent.getChildList().forEach(child -> {
            this.totalPaidAmount =+ child.getPaidAmount();
        });
    }
}
