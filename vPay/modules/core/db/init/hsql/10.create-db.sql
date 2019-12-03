-- begin VPAY_COUNTRY
create table VPAY_COUNTRY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(150),
    CODE varchar(3),
    --
    primary key (ID)
)^
-- end VPAY_COUNTRY
-- begin VPAY_BANK
create table VPAY_BANK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(150),
    COUNTRY_ID varchar(36) not null,
    BIC varchar(20),
    CBIC varchar(20),
    --
    primary key (ID)
)^
-- end VPAY_BANK
-- begin VPAY_BANK_BRANCH
create table VPAY_BANK_BRANCH (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(150),
    BBIC varchar(20),
    CBBIC varchar(20),
    BANK_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end VPAY_BANK_BRANCH
