-- liquibase formatted sql
-- Создание таблицы supplier

-- changeset osmushin_dv:create_supplier_table

CREATE TABLE supplier
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name         VARCHAR(255) NOT NULL,
    address_id   UUID REFERENCES address(id) ON DELETE SET NULL,
    phone_number VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);

-- rollback DROP TABLE supplier;
