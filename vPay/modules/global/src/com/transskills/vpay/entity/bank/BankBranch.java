package com.transskills.vpay.entity.bank;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;

@NamePattern("%s|name")
@Table(name = "VPAY_BANK_BRANCH")
@Entity(name = "vpay_BankBranch")
public class BankBranch extends StandardEntity {
    private static final long serialVersionUID = -8231451394077257903L;

    @Column(name = "NAME", length = 150)
    protected String name;

    @Column(name = "BBIC", length = 20)
    protected String bbic;

    @Column(name = "CBBIC", length = 20)
    protected String cbbic;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BANK_ID")
    protected Bank bank;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getCbbic() {
        return cbbic;
    }

    public void setCbbic(String cbbic) {
        this.cbbic = cbbic;
    }

    public String getBbic() {
        return bbic;
    }

    public void setBbic(String bbic) {
        this.bbic = bbic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}