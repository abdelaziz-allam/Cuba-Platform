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
);