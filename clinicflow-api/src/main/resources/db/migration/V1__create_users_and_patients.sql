CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(30) NOT NULL,
                       active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE patients (
                          id UUID PRIMARY KEY,
                          full_name VARCHAR(255) NOT NULL,
                          phone VARCHAR(20) NOT NULL UNIQUE,
                          birth_date DATE NOT NULL,
                          user_id UUID NOT NULL UNIQUE,

                          CONSTRAINT fk_patient_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
);