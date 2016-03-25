ALTER TABLE CITY DROP CONSTRAINT FK_CITY_COUNTRY_ID
ALTER TABLE CITY DROP CONSTRAINT CITY_PROVINCE_ID
ALTER TABLE ZIPCODE DROP CONSTRAINT ZIPCODE_COUNTRY_ID
ALTER TABLE BUILDING DROP CONSTRAINT BUILDING_STREET_ID
ALTER TABLE STREET DROP CONSTRAINT STREET_ZIPCODE_ID
ALTER TABLE STREET DROP CONSTRAINT FK_STREET_CITY_ID
ALTER TABLE STREET DROP CONSTRAINT STREET_COUNTRY_ID
ALTER TABLE COM_ADDRESS DROP CONSTRAINT CMADDRESSCONTACTID
ALTER TABLE CONTACTS DROP CONSTRAINT CNTACTSSALUTAIONID
ALTER TABLE CONTACTS DROP CONSTRAINT FK_CONTACTS_COM_ID
ALTER TABLE CONTACTS DROP CONSTRAINT CONTACTSADDRESS_ID
ALTER TABLE ASSOCIATED_BUILDING_ADR DROP CONSTRAINT SSCTDBLDNGDRCNTCTD
ALTER TABLE ASSOCIATED_BUILDING_ADR DROP CONSTRAINT SSCTDBLDNGDRBLDNGD
ALTER TABLE PROVINCE DROP CONSTRAINT PROVINCECOUNTRY_ID
DROP INDEX CITY_NAME_IDX
DROP INDEX CITY_COUNTRY_NAME_IDX
DROP TABLE CITY
DROP INDEX ZIP_NAME_IDX
DROP INDEX ZIP_COUNTRY_NAME_IDX
DROP TABLE ZIPCODE
DROP INDEX COUNTRY_NAME_IDX
DROP INDEX COUNTRY_ISO2_IDX
DROP INDEX COUNTRY_ISO3_IDX
DROP INDEX COUNTRY_NUMBER_IDX
DROP TABLE COUNTRY
DROP INDEX BUILDING_NAME_IDX
DROP INDEX BUILDING_NUMBER_IDX
DROP INDEX BUILDING_STREET_ID_IDX
DROP TABLE BUILDING
DROP INDEX ADR_NAME_IDX
DROP INDEX ADR_ADD_NAME_IDX
DROP INDEX ADR_CITY_NAME_IDX
DROP INDEX ADR_COUNTRY_NAME_IDX
DROP INDEX ADR_ZIPCODE_NAME_IDX
DROP TABLE STREET
DROP INDEX COM_ADDRESS_NAME_IDX
DROP INDEX COM_ADDRESS_QUALIFIER_IDX
DROP INDEX COM_ADDRESS_TYPE_IDX
DROP TABLE COM_ADDRESS
DROP INDEX CONTACT_NAME_IDX
DROP INDEX CONTACT_ADDITIONAL_NAME_IDX
DROP INDEX CONTACT_FIRST_NAME_IDX
DROP INDEX CONTACT_REGISTRATION_NUMBER_IDX
DROP TABLE CONTACTS
DROP INDEX SALUTATIONS_NAME_IDX
DROP INDEX SALUTATIONS_FOR_LETTER_IDX
DROP INDEX SALUTATIONS_FOR_ADDRESS_IDX
DROP TABLE SALUTATIONS
DROP INDEX ASS_ADR_QUALIFIER_IDX
DROP INDEX ASS_ADR_BUILDING_ADR_TXT_IDX
DROP INDEX ASS_ADR_BUILDING_ID_IDX
DROP INDEX ASS_ADR_CONTACT_ID_IDX
DROP TABLE ASSOCIATED_BUILDING_ADR
DROP INDEX PROVINCE_NAME_IDX
DROP TABLE PROVINCE
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
