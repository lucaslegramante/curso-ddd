CREATE TABLE account (
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    cpf VARCHAR NOT NULL,
    car_plate VARCHAR NOT NULL,
    is_passenger BOOLEAN NOT NULL,
    is_driver BOOLEAN NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
