CREATE TABLE persons
(
    ID          SERIAL PRIMARY KEY,
    LAST_NAME   VARCHAR(50) NOT NULL,
    FIRST_NAME  VARCHAR(50) NOT NULL,
    SECOND_NAME VARCHAR(100),
    B_DATE      TIMESTAMP,
    CREATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE documents
(
    ID              SERIAL PRIMARY KEY,
    DOCUMENT_NUMBER BIGINT       NOT NULL,
    NAME            VARCHAR(255) NOT NULL,
    DATA            BYTEA,
    DOCUMENT_ACTIVE BOOLEAN      NOT NULL DEFAULT TRUE,
    PERSON_ID       BIGINT       NOT NULL,
    CREATED_AT      TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT      TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (PERSON_ID) REFERENCES PERSONS (ID)
);