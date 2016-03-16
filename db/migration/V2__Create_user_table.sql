CREATE TABLE user (
  id BIGINT NOT NULL,
  createdBy_id BIGINT,
  createdDate TIMESTAMP,
  lastmodifiedBy_id BIGINT,
  lastmodifiedDate TIMESTAMP
--    VERSION
);
