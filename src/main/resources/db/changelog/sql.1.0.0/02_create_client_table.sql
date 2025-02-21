-- liquibase formatted sql
-- Создание таблицы client

-- changeset osmushin_dv:create_client_table

CREATE TABLE client
(
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_name       VARCHAR(100)                            NOT NULL,
    client_surname    VARCHAR(100)                            NOT NULL,
    birthday          DATE                                    NOT NULL,
    gender            VARCHAR(1) CHECK (gender IN ('M', 'F')) NOT NULL,
    address_id        UUID                                    REFERENCES address (id) ON DELETE SET NULL,
    created_at        TIMESTAMP WITH TIME ZONE,
    updated_at        TIMESTAMP WITH TIME ZONE
);

-- rollback DROP TABLE client;
