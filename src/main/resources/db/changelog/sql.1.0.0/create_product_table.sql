-- liquibase formatted sql
-- Создание таблицы product

-- changeset osmushin-dv:create_product_table

CREATE TABLE product
(
    id               UUID PRIMARY KEY,
    name             VARCHAR(255)   NOT NULL,
    category         VARCHAR(255)   NOT NULL,
    price            DECIMAL(12, 2) NOT NULL,
    available_stock  INT            NOT NULL DEFAULT 0,
    last_update_date TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    supplier_id      UUID,
    image_id         UUID
);

-- rollback DROP TABLE product;
