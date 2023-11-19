-- Database: music-app-db

-- DROP DATABASE IF EXISTS "music-app-db";

CREATE DATABASE "music-app-db"
    WITH
    OWNER = toddwaterman
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    ICU_LOCALE = 'en-US'
    LOCALE_PROVIDER = 'icu'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;