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
);