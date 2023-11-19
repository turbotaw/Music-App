-- Table: public.token

-- DROP TABLE IF EXISTS public.token;

CREATE TABLE IF NOT EXISTS public.token
(
    user_id bigint NOT NULL,
    access_token character varying(255) COLLATE pg_catalog."default",
    auth_token character varying(255) COLLATE pg_catalog."default",
    refresh_token character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT token_pkey PRIMARY KEY (user_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.appuser (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.token
    OWNER to toddwaterman;