CREATE SEQUENCE public.tb_usuario_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

CREATE TABLE public.tb_usuario
(
	user_id_usuario bigint NOT NULL DEFAULT nextval('tb_usuario_seq'::regclass),
	user_nm_login character varying(20),
	user_cr_password character varying(100),
	user_nm_email character varying(200),
	user_nm_name character varying(100),
	user_tp_situacao character varying(15),
	CONSTRAINT tb_usuario_pkey PRIMARY KEY (user_id_usuario)
);
