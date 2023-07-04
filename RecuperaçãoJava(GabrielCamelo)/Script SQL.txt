CREATE DATABASE IF NOT EXISTS locadora;

USE locadora;

CREATE TABLE IF NOT EXISTS filme(
    id_filme int PRIMARY KEY NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    genero VARCHAR(40) NOT NULL,
    data_estreia DATE NOT NULL
);
Insert into filme values(1,'Shrek 2','Comedia','2004-06-18');
Insert into filme values(2,'Shrek Terceiro', 'Comedia','2007-05-18');
Insert into filme values(3,'Akira','Anime','1991-08-09');
Insert into filme values(4,'Panico','Terror','1997,01,31');
Insert into filme values(5,'Vidas ao Vento','Romance', '2014-02-21');
Insert into filme values(6,'A lenda de Hei','Aventura','2019-08-27');
Insert into filme values(7,'Velozes e Furiosos 10','Ação','2023-05-19');

CREATE TABLE IF NOT EXISTS pessoa(
    id_pessoa int  PRIMARY KEY NOT NULL,
    nome VARCHAR(20) NOT Null,
    sobrenome VARCHAR(20) NOT NULL,
    data_nascimento DATE NOT NULL
);
Insert into pessoa values(01,'Michael','Jackson','1958-08-29');
Insert into pessoa values(02,'Marilyn','Monroe','1926-06-01');
Insert into pessoa values(03,'Freddie','Mercury','1946-07-24');
Insert into pessoa values(04,'Madona','Louise','1958-08-16');
Insert into pessoa values(05,'Samus','Aran','2000-10-31');

CREATE TABLE IF NOT EXISTS alugado(
    id_alugado int PRIMARY KEY NOT NULL,
    id_pessoa int NOT NULL,
    id_filme int NOT NULL,
    data_alocacao DATE NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
    FOREIGN KEY (id_filme) REFERENCES filme(id_filme)
);

Insert into alugado values(01,01,1,'2023-06-29');
Insert into alugado values(02,01,2,'2023-06-29');
Insert into alugado values(03,05,3,'2023-07-01');

