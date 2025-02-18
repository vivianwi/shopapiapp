-- liquibase formatted sql
-- Создание таблицы images

-- changeset osmushin_dv:create_images_table

CREATE TABLE images
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    image      BYTEA NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);

-- rollback DROP TABLE images;
