# Billing
Billing System

create database bs100;
CREATE TABLE customer (name VARCHAR(100), sm_id VARCHAR(20),address VARCHAR(200), email VARCHAR(20), phone VARCHAR(20));
insert into customer (name,sm_id, address, email, phone) values ('Kumar', '1234','Address Line 1 chennai','test@test.com', '9822221110');

CREATE TABLE sm_usage(sm_id VARCHAR(100), start_ts datetime,start_reading INTEGER(30), end_ts datetime, end_reading  INTEGER(30),status VARCHAR(30));
CREATE TABLE sm_usage(sm_id VARCHAR(100), start_ts datetime,start_reading VARCHAR(30), end_ts datetime, end_reading  VARCHAR(30),status VARCHAR(30));
CREATE TABLE invoice(sm_id VARCHAR(100), start_ts datetime,start_reading VARCHAR(30), end_ts datetime, end_reading  VARCHAR(30),total_reading VARCHAR(30),bill_amount VARCHAR(30));

CREATE TABLE slab (start_reading INTEGER(10), end_reading INTEGER(10), rate DECIMAL(5,2));

insert into slab (start_reading, end_reading, rate) values (0,300,1.25);
insert into slab (start_reading, end_reading, rate) values (301,600,1.75);
insert into slab (start_reading, end_reading, rate) values (601,1000,2.25);
insert into slab (start_reading, end_reading, rate) values (1000,99999,2.75);

insert into sm_usage (sm_id, start_ts,start_reading, end_ts, end_reading,status) values ('1234568','2015-06-30 05:00:00', 50012, '2015-06-30 05:15:00',50023,null);
insert into sm_usage (sm_id, start_ts,start_reading, end_ts, end_reading,status) values ('1234568','2015-06-30 05:32:00', 50023, '2015-06-30 05:39:00',50029,null);

insert into sm_usage (sm_id, start_ts,start_reading, end_ts, end_reading,status) values ('1234568','2015-06-30 05:00:00', '50012', '2015-06-30 05:15:00','50023',null);
insert into sm_usage (sm_id, start_ts,start_reading, end_ts, end_reading,status) values ('1234568','2015-06-30 05:32:00', '50023', '2015-06-30 05:39:00','50029',null);
insert into sm_usage (sm_id, start_ts,start_reading, end_ts, end_reading,status) values ('1234568','2015-06-30 06:09:00', '50029', '2015-06-30 06:22:00','50044',null);
insert into sm_usage (sm_id, start_ts,start_reading, end_ts, end_reading,status) values ('1234568','2015-06-30 07:10:00', '81234', '2015-06-30 09:25:00','81259',null);
insert into sm_usage (sm_id, start_ts,start_reading, end_ts, end_reading,status) values ('1234568','2015-06-30 09:45:00', '81259', '2015-06-30 11:04:00','81294',null);

select min(start_reading), max(end_reading), max(end_reading)-min(start_reading) as totalReading from sm_usage;

http://localhost:8080/BSWebService/rest/ConversionService/FeetToInch/2

