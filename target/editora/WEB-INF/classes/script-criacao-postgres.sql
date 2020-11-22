CREATE SEQUENCE public.departamentos_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.departamentos_sequence
    OWNER TO postgres;

-- ---------------------------------------------

CREATE SEQUENCE public.cargos_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.cargos_sequence
    OWNER TO postgres;

-- ---------------------------------------------

CREATE SEQUENCE public.enderecos_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.enderecos_sequence
    OWNER TO postgres;

-- ---------------------------------------------

CREATE SEQUENCE public.funcionarios_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.funcionarios_sequence
    OWNER TO postgres;

-- ---------------------------------------------

CREATE TABLE public.departamentos
(
    id_departamento bigint NOT NULL DEFAULT nextval('departamentos_sequence'::regclass),
    departamento character varying COLLATE pg_catalog."default",
    CONSTRAINT departamentos_pkey PRIMARY KEY (id_departamento)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.departamentos
    OWNER to postgres;

-- ---------------------------------------------

CREATE TABLE public.cargos
(
    id_cargo bigint NOT NULL DEFAULT nextval('cargos_sequence'::regclass),
    cargo character varying(80) COLLATE pg_catalog."default" NOT NULL,
    id_departamento bigint NOT NULL,
    CONSTRAINT cargos_pkey PRIMARY KEY (id_cargo),
    CONSTRAINT departamentos_fkey FOREIGN KEY (id_departamento)
        REFERENCES public.departamentos (id_departamento) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cargos
    OWNER to postgres;

-- ---------------------------------------------

CREATE TABLE public.enderecos
(
    id_endereco bigint NOT NULL DEFAULT nextval('enderecos_sequence'::regclass),
    logradouro character varying(80) COLLATE pg_catalog."default",
	numero character varying(20) COLLATE pg_catalog."default",
	complemento character varying(20) COLLATE pg_catalog."default",
    bairro character varying(45) COLLATE pg_catalog."default",
    cidade character varying(45) COLLATE pg_catalog."default",
    estado character varying(45) COLLATE pg_catalog."default",
	cep character varying(45) COLLATE pg_catalog."default",
	CONSTRAINT enderecos_pkey PRIMARY KEY (id_endereco)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.enderecos
    OWNER to postgres;


-- ---------------------------------------------

CREATE TABLE public.funcionarios
(
    id_funcionario bigint NOT NULL DEFAULT nextval('funcionarios_sequence'::regclass),
    nome character varying(500) COLLATE pg_catalog."default",
    data_entrada date ,
    data_saida date ,
    salario numeric(10,4),
    id_cargo bigint NOT NULL,
	id_endereco bigint NOT NULL,
    estado character varying(200) COLLATE pg_catalog."default",
	CONSTRAINT funcionarios_pkey PRIMARY KEY (id_funcionario),
    CONSTRAINT cargos_fkey FOREIGN KEY (id_cargo)
        REFERENCES public.cargos (id_cargo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT enderecos_fkey FOREIGN KEY (id_endereco)
        REFERENCES public.enderecos (id_endereco) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.funcionarios
    OWNER to postgres;

