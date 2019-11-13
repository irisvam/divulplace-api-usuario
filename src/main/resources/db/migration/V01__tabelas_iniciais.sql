CREATE SEQUENCE public.tb_perfil_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE SEQUENCE public.tb_usuario_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE TABLE tb_perfil (
	role_id_perfil bigint NOT NULL DEFAULT nextval('tb_perfil_id_seq'::regclass),
	role_tp_perfil character varying(15),
	CONSTRAINT tb_perfil_pkey PRIMARY KEY (role_id_perfil),
	CONSTRAINT uk_perfil_tp_perfil UNIQUE (role_tp_perfil)
);

CREATE TABLE public.tb_usuario
(
	user_id_usuario bigint NOT NULL DEFAULT nextval('tb_usuario_id_seq'::regclass),
	user_nm_login character varying(200),
	user_cr_password character varying(100),
	user_nm_email character varying(200),
	user_nm_nome character varying(150),
	user_tp_situacao character varying(15),
	CONSTRAINT tb_usuario_pkey PRIMARY KEY (user_id_usuario),
	CONSTRAINT uk_usuario_nm_email UNIQUE (user_nm_email),
	CONSTRAINT uk_usuario_nm_login UNIQUE (user_nm_login)
);

CREATE TABLE tb_usuario_tb_perfil (
	usro_id_usuario bigint NOT NULL,
	usro_id_perfil bigint NOT NULL,
	CONSTRAINT tb_user_roles_pkey PRIMARY KEY (usro_id_usuario, usro_id_perfil),
	CONSTRAINT fk_tb_perfil_id FOREIGN KEY (usro_id_perfil)
      REFERENCES public.tb_perfil (role_id_perfil) MATCH SIMPLE,
	CONSTRAINT fk_tb_usuario_id FOREIGN KEY (usro_id_usuario)
      REFERENCES public.tb_usuario (user_id_usuario) MATCH SIMPLE
);

INSERT INTO tb_perfil(role_tp_perfil) VALUES('ROLE_USER');
INSERT INTO tb_perfil(role_tp_perfil) VALUES('ROLE_ADMIN');
