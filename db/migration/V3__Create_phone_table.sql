CREATE TABLE phone (
  id BIGINT NOT NULL,
  externalId VARCHAR(100) NOT NULL,
  name varchar(100) NOT NULL,
  snippet varchar(400) NOT NULL,
  createdBy_id BIGINT,
  createdDate TIMESTAMP,
  lastmodifiedBy_ID BIGINT,
  lastmodifiedDate TIMESTAMP,
--    VERSION
);
ALTER TABLE phone ADD CONSTRAINT UNIQUE_phone_externalId UNIQUE(externalId);
ALTER TABLE phone ADD CONSTRAINT UNIQUE_phone_name UNIQUE(name);

