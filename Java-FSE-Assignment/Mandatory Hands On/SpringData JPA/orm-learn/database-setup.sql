-- Create Database
CREATE DATABASE ormlearn;

-- Use Database
USE ormlearn;

-- Create Table
CREATE TABLE country(
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50)
);

-- Insert Sample Data
INSERT INTO country VALUES('IN','India');
INSERT INTO country VALUES('US','United States of America');
