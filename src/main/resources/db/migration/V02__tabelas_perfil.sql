CREATE SEQUENCE public.tb_afiliado_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE SEQUENCE public.tb_endereco_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE SEQUENCE public.tb_contato_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE SEQUENCE public.tb_rede_social_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE TABLE public.tb_afiliado
(
	afld_id_afiliado bigint NOT NULL DEFAULT nextval('tb_afiliado_id_seq'::regclass),
	afld_nm_tratamento character varying(100),
	afld_nm_nome character varying(200),
	afld_nm_apelido character varying(150),
	afld_dt_nascimento date,
	afld_nm_cpf character varying(11),
	afld_tp_estado_civil character varying(10),
	afld_tp_sexo character varying(15),
	afld_ur_imagem character varying(200),
	afld_ur_link character varying(200),
	afld_nm_email character varying(200),
	afld_id_usuario bigint NOT NULL,
	afld_id_endereco bigint,
	afld_id_contato bigint,
	CONSTRAINT tb_afiliado_pkey PRIMARY KEY (afld_id_afiliado),
	CONSTRAINT fk_tb_afiliado_x_tb_usuario_id FOREIGN KEY (afld_id_usuario)
      REFERENCES public.tb_usuario (user_id_usuario) MATCH SIMPLE,
    CONSTRAINT fk_tb_afiliado_x_tb_endereco_id FOREIGN KEY (afld_id_endereco)
      REFERENCES public.tb_endereco (ende_id_endereco) MATCH SIMPLE,
    CONSTRAINT fk_tb_afiliado_x_tb_contato_id FOREIGN KEY (afld_id_contato)
      REFERENCES public.tb_contato (cont_id_contato) MATCH SIMPLE
);

CREATE TABLE public.tb_endereco (
	ende_id_endereco bigint NOT NULL DEFAULT nextval('tb_endereco_id_seq'::regclass),
	ende_nm_endereco character varying(200),
	ende_nu_numero smallint,
	ende_nm_complemento character varying(100),
	ende_cd_cep character varying(9),
	ende_nm_bairro character varying(150),
	ende_cd_pais character varying(3),
	ende_uf_estado character varying(2),
	ende_cd_cidade integer,
	ende_nm_cidade character varying(100),
	ende_id_afiliado bigint NOT NULL,
	CONSTRAINT tb_endereco_pkey PRIMARY KEY (ende_id_endereco)
);

CREATE TABLE public.tb_contato (
	cont_id_contato bigint NOT NULL DEFAULT nextval('tb_contato_id_seq'::regclass),
	cont_tl_fixo character varying(12),
	cont_tl_celular_primeiro character varying(12),
	cont_tl_celular_segundo character varying(12),
	cont_ic_whatsapp_primeiro boolean,
	cont_ic_whatsapp_segundo boolean,
	cont_nm_email_skype character varying(200),
	cont_id_afiliado bigint NOT NULL,
	CONSTRAINT tb_contato_pkey PRIMARY KEY (cont_id_contato)
);

CREATE TABLE public.tb_rede_social (
	rdso_id_social bigint NOT NULL DEFAULT nextval('tb_rede_social_id_seq'::regclass),
	rdso_tp_tipo character varying(15),
	rdso_ur_link character varying(200),
	rdso_id_afiliado bigint NOT NULL,
	CONSTRAINT tb_rede_social_pkey PRIMARY KEY (rdso_id_social),
	CONSTRAINT fk_tb_rede_social_x_tb_afiliado_id FOREIGN KEY (rdso_id_afiliado)
      REFERENCES public.tb_afiliado (afld_id_afiliado) MATCH SIMPLE
);

