CREATE SEQUENCE public.tb_portfolio_produto_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE SEQUENCE public.tb_produto_imagem_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE TABLE public.tb_portfolio_produto
(
	ppro_id_produto bigint NOT NULL DEFAULT nextval('tb_portfolio_produto_id_seq'::regclass),
	ppro_nm_produto character varying(150),
	ppro_nm_descricao character varying(500),
	ppro_tp_modelo character varying(8),
	ppro_ur_divulgacao character varying(200),
	ppro_id_afiliado bigint NOT NULL,
	CONSTRAINT tb_portfolio_produto_pkey PRIMARY KEY (ppro_id_produto),
	CONSTRAINT fk_tb_portfolio_produto_x_tb_afiliado_id FOREIGN KEY (ppro_id_afiliado)
      REFERENCES public.tb_afiliado (afld_id_afiliado) MATCH SIMPLE
);

CREATE TABLE public.tb_produto_imagem (
	pimg_id_produto_imagem bigint NOT NULL DEFAULT nextval('tb_produto_imagem_id_seq'::regclass),
	pimg_ur_imagem character varying(250),
	pimg_id_produto bigint NOT NULL,
	CONSTRAINT tb_produto_imagem_pkey PRIMARY KEY (pimg_id_produto_imagem),
	CONSTRAINT fk_tb_produto_imagem_x_tb_portfolio_produto_id FOREIGN KEY (pimg_id_produto)
      REFERENCES public.tb_portfolio_produto (ppro_id_produto) MATCH SIMPLE
);
