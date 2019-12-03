-- begin VPAY_BANK
alter table VPAY_BANK add constraint FK_VPAY_BANK_ON_COUNTRY foreign key (COUNTRY_ID) references VPAY_COUNTRY(ID)^
create index IDX_VPAY_BANK_ON_COUNTRY on VPAY_BANK (COUNTRY_ID)^
-- end VPAY_BANK
-- begin VPAY_BANK_BRANCH
alter table VPAY_BANK_BRANCH add constraint FK_VPAY_BANK_BRANCH_ON_BANK foreign key (BANK_ID) references VPAY_BANK(ID)^
create index IDX_VPAY_BANK_BRANCH_ON_BANK on VPAY_BANK_BRANCH (BANK_ID)^
-- end VPAY_BANK_BRANCH
