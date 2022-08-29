package com.digital.republic.donus.dto;

import com.digital.republic.donus.domain.Account;

public class TransferenceDto {
    private Integer userAccount;
    private Integer depositAccount;
    private double valueDeposit;

    public TransferenceDto(Integer userAccount, Integer depositAccount, double valueDeposit) {
        this.userAccount = userAccount;
        this.depositAccount = depositAccount;
        this.valueDeposit = valueDeposit;
    }

    public Integer getUserAccount() {
        return userAccount;
    }

    public Integer getDepositAccount() {
        return depositAccount;
    }

    public double getValueDeposit() {
        return valueDeposit;
    }
}
