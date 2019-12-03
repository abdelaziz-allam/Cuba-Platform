alter table VPAY_BANK add constraint FK_VPAY_BANK_ON_COUNTRY foreign key (COUNTRY_ID) references VPAY_COUNTRY(ID);
create index IDX_VPAY_BANK_ON_COUNTRY on VPAY_BANK (COUNTRY_ID);
