CREATE DATABASE IF NOT EXISTS rh_db DEFAULT CHARACTER SET utf8;

USE rh_db;

CREATE TABLE departamentos (
  id_departamento INT PRIMARY KEY AUTO_INCREMENT,
  departamento VARCHAR(80) NOT NULL,
  UNIQUE INDEX UK_departamento(departamento)
);

CREATE TABLE cargos (
  id_cargo INT PRIMARY KEY AUTO_INCREMENT,
  cargo VARCHAR(80) NOT NULL,
  id_departamento INT NOT NULL,
  UNIQUE INDEX UK_cargo (cargo),
  CONSTRAINT FK_id_departamento
    FOREIGN KEY (id_departamento)
    REFERENCES departamentos (id_departamento)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE enderecos (
  id_endereco INT PRIMARY KEY AUTO_INCREMENT,
  logradouro VARCHAR(80) NOT NULL,
  numero VARCHAR(20) NOT NULL,
  complemento VARCHAR(20) NULL,
  bairro VARCHAR(45) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  estado VARCHAR(45) NOT NULL,
  cep VARCHAR(45) NOT NULL
);

 CREATE TABLE funcionarios (
  id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(80) NOT NULL,
  data_entrada DATE NOT NULL,
  data_saida DATE NULL,
  salario DOUBLE(8,2) NOT NULL,
  id_cargo INT NOT NULL,
  id_endereco INT NOT NULL,
  CONSTRAINT FK_id_cargo
    FOREIGN KEY (id_cargo)
    REFERENCES cargos (id_cargo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_id_endereco
    FOREIGN KEY (id_endereco)
    REFERENCES enderecos (id_endereco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);