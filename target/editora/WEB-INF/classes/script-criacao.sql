CREATE SCHEMA `rh-db` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `rh-db`.`departamentos` (
  `id_departamento` INT NOT NULL AUTO_INCREMENT,
  `departamento` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_departamento`),
  UNIQUE INDEX `departamento_UNIQUE` (`departamento` ASC));


CREATE TABLE `rh-db`.`cargos` (
  `id_cargo` INT NOT NULL AUTO_INCREMENT,
  `cargos` VARCHAR(80) NOT NULL,
  `id_departamento` INT NOT NULL,
  PRIMARY KEY (`id_cargo`),
  INDEX `fk_id_departamento_idx` (`id_departamento` ASC),
  CONSTRAINT `fk_id_departamento`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `rh-db`.`departamentos` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `rh-db`.`enderecos` (
  `id_endereco` INT NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(80) NOT NULL,
  `numero` VARCHAR(20) NOT NULL,
  `complemento` VARCHAR(20) NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_endereco`));

 CREATE TABLE `rh-db`.`funcionarios` (
  `id_funcionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `data_entrada` DATE NOT NULL,
  `data_saida` DATE NULL,
  `salario` DOUBLE(8,2) NOT NULL,
  `id_cargo` INT NOT NULL,
  `id_endereco` INT NOT NULL,
  PRIMARY KEY (`id_funcionario`),
  INDEX `fk_id_cargo_idx` (`id_cargo` ASC),
  INDEX `fk_id_endereco_idx` (`id_endereco` ASC),
  CONSTRAINT `fk_id_cargo`
    FOREIGN KEY (`id_cargo`)
    REFERENCES `rh-db`.`cargos` (`id_cargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_endereco`
    FOREIGN KEY (`id_endereco`)
    REFERENCES `rh-db`.`enderecos` (`id_endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

