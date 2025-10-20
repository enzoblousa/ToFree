-- Inserir usuários iniciais (senhas são: admin123, freelancer123, cliente123)
INSERT INTO users (id, name, email, password, role, created_at) VALUES
(1, 'Administrador', 'admin@email.com', '$2a$10$ABCDE12345ABCDE12345uB7QK9S6c5u1Q6a5Z1Y2X3W4V5U6T7R8E9Q0W', 'ADMIN', NOW()),
(2, 'João Freelancer', 'freelancer@email.com', '$2a$10$FGHIJ67890FGHIJ67890uB7QK9S6c5u1Q6a5Z1Y2X3W4V5U6T7R8E9Q0W', 'FREELANCER', NOW()),
(3, 'Maria Cliente', 'cliente@email.com', '$2a$10$KLMNO12345KLMNO12345uB7QK9S6c5u1Q6a5Z1Y2X3W4V5U6T7R8E9Q0W', 'CLIENT', NOW())
ON CONFLICT (id) DO NOTHING;

-- Inserir brinquedos infláveis
INSERT INTO inflatables (id, name, description, price_per_day, size, available, created_by) VALUES
(1, 'Castelo Inflável Grande', 'Castelo inflável para festas infantis', 150.00, '3x3m', true, 1),
(2, 'Tobogã Aquático', 'Tobogã inflável para piscina', 200.00, '4x2m', true, 1),
(3, 'Piscina de Bolinhas', 'Piscina inflável com bolinhas', 100.00, '2x2m', true, 1)
ON CONFLICT (id) DO NOTHING;