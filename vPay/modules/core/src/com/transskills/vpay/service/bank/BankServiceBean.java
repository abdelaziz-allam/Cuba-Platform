package com.transskills.vpay.service.bank;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.transskills.vpay.entity.bank.BankBranch;
import org.springframework.stereotype.Service;
import com.transskills.vpay.service.BankService;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Service(BankService.NAME)
public class BankServiceBean implements BankService {

    @Inject
    private DataManager dataManager;

    @Override
    public List<BankBranch> loadBranchesByBankId(UUID bankId) {
        LoadContext<BankBranch> lc = LoadContext.create(BankBranch.class);
        LoadContext.Query query =
                LoadContext.createQuery("  select bb from vpay_BankBranch bb where bb.bank.id =:bankId")
                        .setParameter("bankId", bankId);
        lc.setView("_local");
        lc.setQuery(query);
        return dataManager.loadList(lc);
    }
}