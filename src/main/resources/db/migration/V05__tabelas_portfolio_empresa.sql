CREATE SEQUENCE public.tb_portfolio_empresa_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE SEQUENCE public.tb_empresa_imagem_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE TABLE public.tb_portfolio_empresa
(
	pemp_id_empresa bigint NOT NULL DEFAULT nextval('tb_portfolio_empresa_id_seq'::regclass),
	pemp_nm_empresa character varying(150),
	pemp_nm_descricao character varying(500),
	pemp_dc_cnpj character varying(14),
	pemp_ur_divulgacao character varying(200),
	pemp_ur_video character varying(200),
	pemp_id_afiliado bigint NOT NULL,
	pemp_id_endereco bigint,
	pemp_id_contato bigint,
	CONSTRAINT tb_portfolio_empresa_pkey PRIMARY KEY (pemp_id_empresa),
	CONSTRAINT fk_tb_portfolio_empresa_x_tb_afiliado_id FOREIGN KEY (pemp_id_afiliado)
      REFERENCES public.tb_afiliado (afld_id_afiliado) MATCH SIMPLE,
    CONSTRAINT fk_tb_portfolio_empresa_x_tb_endereco_id FOREIGN KEY (pemp_id_endereco)
      REFERENCES public.tb_endereco (ende_id_endereco) MATCH SIMPLE,
    CONSTRAINT fk_tb_portfolio_empresa_x_tb_contato_id FOREIGN KEY (pemp_id_contato)
      REFERENCES public.tb_contato (cont_id_contato) MATCH SIMPLE
);

CREATE TABLE public.tb_empresa_ramo_atividade (
	eatv_id_ramo_atividade bigint NOT NULL,
	eatv_id_empresa bigint NOT NULL,
	CONSTRAINT tb_empresa_ramo_atividade_pkey PRIMARY KEY (eatv_id_ramo_atividade, eatv_id_empresa),
	CONSTRAINT fk_tb_empresa_ramo_atividade_x_tb_ramo_atividade_id FOREIGN KEY (eatv_id_ramo_atividade)
      REFERENCES public.tb_ramo_atividade (ratv_id_ramo_atividade) MATCH SIMPLE,
	CONSTRAINT fk_tb_empresa_ramo_atividade_x_tb_portfolio_empresa_id FOREIGN KEY (eatv_id_empresa)
      REFERENCES public.tb_portfolio_empresa (pemp_id_empresa) MATCH SIMPLE
);

CREATE TABLE public.tb_empresa_imagem (
	eimg_id_empresa_imagem bigint NOT NULL DEFAULT nextval('tb_empresa_imagem_id_seq'::regclass),
	eimg_ur_imagem character varying(250),
	eimg_id_empresa bigint NOT NULL,
	CONSTRAINT tb_empresa_imagem_pkey PRIMARY KEY (eimg_id_empresa_imagem),
	CONSTRAINT fk_tb_empresa_imagem_x_tb_portfolio_empresa_id FOREIGN KEY (eimg_id_empresa)
      REFERENCES public.tb_portfolio_empresa (pemp_id_empresa) MATCH SIMPLE
);
