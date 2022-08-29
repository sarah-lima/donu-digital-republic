package com.digital.republic.donus.dto;


public class DepositDto {

    private Integer accountId;
    private double newValue;

    public Integer getAccountId() {
        return accountId;
    }

    public DepositDto(Integer accountId, double newValue) {
        this.accountId = accountId;
        this.newValue = newValue;
    }

    public double getNewValue() {
        return newValue;
    }
}
