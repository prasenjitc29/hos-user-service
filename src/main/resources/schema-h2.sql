DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS LOGINUSER;
  
CREATE TABLE USER (
  ID NUMBER(10) NOT NULL,
  FIRST_NAME VARCHAR(250) NOT NULL,
  LAST_NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) DEFAULT NULL
);

CREATE TABLE LOGINUSER (
  ID NUMBER(10) NOT NULL,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  refreshtoken VARCHAR(250) DEFAULT NULL
);

