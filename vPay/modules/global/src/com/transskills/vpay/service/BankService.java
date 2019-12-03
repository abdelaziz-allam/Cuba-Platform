package com.transskills.vpay.service;

import com.transskills.vpay.entity.bank.BankBranch;

import java.util.List;
import java.util.UUID;

public interface BankService {
    String NAME = "vpay_BankService";

    List<BankBranch> loadBranchesByBankId(UUID bankId);
}