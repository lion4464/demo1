CREATE EXTENSION IF NOT EXISTS "pgcrypto";

INSERT INTO users (
    id,
    username,
    password,
    email,
    role,
    created_date,
    modified_date
) VALUES (
             gen_random_uuid(),
             'super_admin',
             '$2a$10$56qmNUwVsIhf.ma5zQheX.PyC4tPUww0pwYM4.YlzNXFcuMfgabuiy',
             'admin@system.com',
             'ROLE_ADMIN',
             EXTRACT(EPOCH FROM NOW()) * 1000,
             EXTRACT(EPOCH FROM NOW()) * 1000
         );