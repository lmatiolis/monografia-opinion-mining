DROP DATABASE IF EXISTS dbPrototipo;
CREATE DATABASE dbPrototipo;
USE dbPrototipo;


CREATE TABLE GRAFICO
(
 CODIGO				   INTEGER NOT NULL AUTO_INCREMENT,
 ARQUIVO		       TEXT,
 ASSUNTO		       VARCHAR(255),
 QTDCLASSE0            INTEGER,
 QTDCLASSE1            INTEGER,
 TIPOCLASSIFICACAO     VARCHAR(255),
 DATACLASSIFICACAO	   DATE,
 
 PRIMARY KEY (CODIGO)
 
)TYPE=INNODB;


