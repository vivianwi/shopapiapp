-- liquibase formatted sql
-- Создание таблицы address

-- changeset osmushin-dv:create_address_table

CREATE TABLE address
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    country VARCHAR(100) NOT NULL,
    city    VARCHAR(100) NOT NULL,
    street  VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);

-- rollback DROP TABLE address;
