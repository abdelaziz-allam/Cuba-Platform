alter table VPAY_BANK_BRANCH add constraint FK_VPAY_BANK_BRANCH_ON_BANK foreign key (BANK_ID) references VPAY_BANK(ID);
create index IDX_VPAY_BANK_BRANCH_ON_BANK on VPAY_BANK_BRANCH (BANK_ID);
