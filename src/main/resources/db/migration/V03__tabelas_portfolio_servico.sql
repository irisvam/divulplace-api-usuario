CREATE SEQUENCE public.tb_portfolio_servico_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE SEQUENCE public.tb_ramo_atividade_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE SEQUENCE public.tb_servico_imagem_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE TABLE public.tb_portfolio_servico
(
	psev_id_servico bigint NOT NULL DEFAULT nextval('tb_portfolio_servico_id_seq'::regclass),
	psev_nm_empresa character varying(150),
	psev_nm_descricao character varying(500),
	psev_id_funcional character varying(100),
	psev_ur_divulgacao character varying(200),
	psev_ur_video character varying(200),
	psev_id_afiliado bigint NOT NULL,
	CONSTRAINT tb_portfolio_servico_pkey PRIMARY KEY (psev_id_servico),
	CONSTRAINT fk_tb_portfolio_servico_x_tb_afiliado_id FOREIGN KEY (psev_id_afiliado)
      REFERENCES public.tb_afiliado (afld_id_afiliado) MATCH SIMPLE
);

CREATE TABLE public.tb_ramo_atividade (
	ratv_id_ramo_atividade bigint NOT NULL DEFAULT nextval('tb_ramo_atividade_id_seq'::regclass),
	ratv_nm_ramo_atividade character varying(50),
	CONSTRAINT tb_ramo_atividade_pkey PRIMARY KEY (ratv_id_ramo_atividade)
);

CREATE TABLE public.tb_servico_ramo_atividade (
	satv_id_ramo_atividade bigint NOT NULL,
	satv_id_servico bigint NOT NULL,
	CONSTRAINT tb_servico_ramo_atividade_pkey PRIMARY KEY (satv_id_ramo_atividade, satv_id_servico),
	CONSTRAINT fk_tb_servico_ramo_atividade_x_tb_ramo_atividade_id FOREIGN KEY (satv_id_ramo_atividade)
      REFERENCES public.tb_ramo_atividade (ratv_id_ramo_atividade) MATCH SIMPLE,
	CONSTRAINT fk_tb_servico_ramo_atividade_x_tb_portfolio_servico_id FOREIGN KEY (satv_id_servico)
      REFERENCES public.tb_portfolio_servico (psev_id_servico) MATCH SIMPLE
);

CREATE TABLE public.tb_servico_imagem (
	simg_id_servico_imagem bigint NOT NULL DEFAULT nextval('tb_servico_imagem_id_seq'::regclass),
	simg_ur_imagem character varying(250),
	simg_id_servico bigint NOT NULL,
	CONSTRAINT tb_servico_imagem_pkey PRIMARY KEY (simg_id_servico_imagem),
	CONSTRAINT fk_tb_servico_imagem_x_tb_portfolio_servico_id FOREIGN KEY (simg_id_servico)
      REFERENCES public.tb_portfolio_servico (psev_id_servico) MATCH SIMPLE
);
