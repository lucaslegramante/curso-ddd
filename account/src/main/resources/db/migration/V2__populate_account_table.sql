INSERT INTO account (id, name, email, cpf, car_plate, is_passenger, is_driver, created_at, updated_at)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'John Doe', 'john@example.com', '12345678909', 'ABC1234', true, false, current_timestamp, current_timestamp),
    ('223e4567-e89b-12d3-a456-426614174001', 'Jane Doe', 'jane@example.com', '98765432198', 'XYZ5678', false, true, current_timestamp, current_timestamp),
    ('323e4567-e89b-12d3-a456-426614174002', 'Alice Smith', 'alice@example.com', '11122233344', 'DEF5678', true, true, current_timestamp, current_timestamp),
    ('423e4567-e89b-12d3-a456-426614174003', 'Bob Johnson', 'bob@example.com', '55566677788', 'GHI9012', false, false, current_timestamp, current_timestamp),
    ('523e4567-e89b-12d3-a456-426614174004', 'Emily Brown', 'emily@example.com', '99988877766', 'JKL3456', true, false, current_timestamp, current_timestamp);
