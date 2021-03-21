CREATE TABLE CUSTOMER_INPUT (
                                ID NUMBER(10) default NULL ,
                                FIRSTNAME varchar2(255) default NULL,
                                LASTNAME  varchar2(255) default NULL
);

CREATE TABLE CUSTOMER_OUTPUT (
                                 ID NUMBER(10) default NULL,
                                 FIRSTNAME varchar2(255) default NULL,
                                 LASTNAME  varchar2(255) default NULL
);


CREATE SEQUENCE customer_input_seq START WITH 1000;
CREATE SEQUENCE customer_output_seq START WITH 1000;


CREATE TRIGGER CUSTOMER_INPUT_ID_GENERATOR
    BEFORE INSERT ON CUSTOMER_INPUT
    FOR EACH ROW
BEGIN
    SELECT customer_input_seq.nextval
    INTO :new.id
    FROM dual;
END;

CREATE TRIGGER CUSTOMER_OUTPUT_ID_GENERATOR
    BEFORE INSERT ON CUSTOMER_OUTPUT
    FOR EACH ROW
BEGIN
    SELECT customer_output_seq.nextval
    INTO :new.id
    FROM dual;
END;