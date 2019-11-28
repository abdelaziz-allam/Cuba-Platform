package com.transskills.vpay.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.CaseConversion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|unitName")
@Table(name = "VPAY_CURRENCY")
@Entity(name = "vpay_Currency")
public class Currency extends StandardEntity {
    private static final long serialVersionUID = 217312816973698995L;

    @CaseConversion
    @Column(name = "CODE", length = 3)
    protected String code;

    @Column(name = "UNIT_NAME", length = 50)
    protected String unitName;

    @Column(name = "PRECISION_")
    protected Integer precision;

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}