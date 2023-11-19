-- Define ENUM types
CREATE TYPE user_status AS ENUM ('free', 'paid', 'trial')
CREATE TYPE device_type AS ENUM ('iOS', 'Android', 'desktop');

-- Create appuser table
CREATE TABLE IF NOT EXISTS public.appuser
(
    user_id bigint NOT NULL DEFAULT nextval('appuser_user_id_seq'::regclass),
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    friend_list bigint[],
    user_status user_status,
    device_types device_type[],
    sign_up_date date,
    last_login timestamp without time zone,
    CONSTRAINT appuser_pkey PRIMARY KEY (user_id)
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.appuser
    OWNER to postgres;
