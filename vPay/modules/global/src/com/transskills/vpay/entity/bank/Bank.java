package com.transskills.vpay.entity.bank;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.transskills.vpay.entity.country.Country;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "VPAY_BANK")
@Entity(name = "vpay_Bank")
public class Bank extends StandardEntity {
    private static final long serialVersionUID = 8029815395478733273L;

    @Column(name = "NAME", length = 150)
    protected String name;

    @OneToMany(mappedBy = "bank")
    protected List<BankBranch> branches;

    @NotNull(message = "{msg://vpay_Bank.country.validation.NotNull}")
    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COUNTRY_ID")
    protected Country country;

    @Column(name = "BIC", length = 20)
    protected String bic;

    @Column(name = "CBIC", length = 20)
    protected String cbic;

    public List<BankBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<BankBranch> branches) {
        this.branches = branches;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCbic() {
        return cbic;
    }

    public void setCbic(String cbic) {
        this.cbic = cbic;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}