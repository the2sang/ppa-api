DROP TABLE IF EXISTS CUSTOMER_INPUT;
DROP TABLE IF EXISTS CUSTOMER_OUTPUT;

CREATE SEQUENCE comstom_input_seq START WITH 1000;
CREATE SEQUENCE comstom_output_seq START WITH 1000;

CREATE TABLE CUSTOMER_INPUT (
                                ID        NUMBER(10)    DEFAULT comstom_input_seq.nextval NOT NULL,
                                FIRSTNAME varchar2(255) default NULL,
                                LASTNAME  varchar2(255) default NULL,
                                PRIMARY KEY (ID)
);

CREATE TABLE CUSTOMER_OUTPUT (
                                 ID        NUMBER(10)    DEFAULT comstom_output_seq.nextval NOT NULL,
                                 FIRSTNAME varchar2(255) default NULL,
                                 LASTNAME varchar2(255) default NULL,
                                 PRIMARY KEY (ID)
);



# 오라클(12) 참고 일련번호 생성 참고
#
#
# CREATE TABLE CUSTOMER_INPUT  (
#                              ID           NUMBER(10)    DEFAULT comstom_input_seq.nextval NOT NULL,
#                              DESCRIPTION  VARCHAR2(50)  NOT NULL);
#
# ALTER TABLE CUSTOMER_INPUT ADD (
#     CONSTRAINT dept_pk PRIMARY KEY (ID));