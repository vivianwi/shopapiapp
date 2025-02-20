-- liquibase formatted sql
-- Создание таблицы product

-- changeset osmushin_dv:create_product_table

CREATE TABLE product
(
    id               UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name             VARCHAR(255)   NOT NULL,
    category         VARCHAR(255)   NOT NULL,
    price            DECIMAL(12, 2) NOT NULL,
    available_stock  INT            NOT NULL DEFAULT 0,
    supplier_id      UUID REFERENCES supplier(id) ON DELETE SET NULL,
    image_id         UUID REFERENCES images(id) ON DELETE SET NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);

-- rollback DROP TABLE product;
