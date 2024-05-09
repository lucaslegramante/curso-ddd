CREATE TABLE loan_blockings (
    id UUID NOT NULL,
    credit_certificate_number VARCHAR(100) NOT NULL,
    reason VARCHAR NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT loan_blockings_pk PRIMARY KEY (id)
);
