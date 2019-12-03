package com.transskills.vpay.entity.country;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|name")
@Table(name = "VPAY_COUNTRY")
@Entity(name = "vpay_Country")
public class Country extends StandardEntity {
    private static final long serialVersionUID = 8713960206838444464L;

    @Column(name = "NAME", length = 150)
    protected String name;

    @Column(name = "CODE", length = 3)
    protected String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}