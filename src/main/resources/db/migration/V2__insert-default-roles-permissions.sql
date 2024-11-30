-- V2__Insert_default_roles_permissions.sql

-- Insere roles, se não existirem
INSERT INTO roles (id, name)
VALUES
    (gen_random_uuid(), 'ADMIN'),
    (gen_random_uuid(), 'USER')
ON CONFLICT (name) DO NOTHING;
