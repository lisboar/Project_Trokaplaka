-- C = INSERT
-- R = SELECT
-- U = UPDATE
-- D = DELETE

CREATE DATABASE dbtrokaplaka;

CREATE TABLE tbusuarios(
iduser INT PRIMARY KEY,
usuario VARCHAR(50) NOT NULL,
telefone VARCHAR(15),
login VARCHAR(15) NOT NULL UNIQUE,
senha VARCHAR(15) NOT NULL
);

CREATE TABLE tbclientes(
idcli INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
endereco VARCHAR(100),
telefone VARCHAR(50) NOT NULL,
email VARCHAR(50)
);

CREATE TABLE tbos(
os INT PRIMARY KEY AUTO_INCREMENT,
data_os TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
equipamento VARCHAR(150) NOT NULL,
defeito VARCHAR(150) NOT NULL,
servico VARCHAR(150),
tecnico VARCHAR(30),
valor DECIMAL(10,2),
idcli INT NOT NULL,
FOREIGN KEY(idcli) REFERENCES tbclientes(idcli)
);

SELECT O.os,data_os, C.nome, telefone, equipamento, defeito, situacao, valor FROM tbos AS O 
INNER JOIN tbclientes AS C ON (O.idcli = C.idcli);


insert into tbusuarios (iduser,usuario,telefone,login,senha)
values(4,'Ciclano','00000-0000','ciclano','123456');

alter table tbusuarios add column perfil varchar(20) not null;

alter table tbclientes drop column situacao;

select * from tbos where equipamento="Smartphone LG XPower" and defeito="Não presta";

describe tbos;

select*from tbclientes;
