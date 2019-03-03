-- init script

drop database if exists sampledb;
drop user if exists sampledbuser;

create user sampledbuser password 'samplepassword';
create database sampledb owner=sampledbuser encoding='UTF-8';
