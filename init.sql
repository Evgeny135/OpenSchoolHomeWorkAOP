CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL CHECK (email ~ '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    description VARCHAR NOT NULL,
    status VARCHAR CHECK(status IN ('ACCEPTED','CREATE','REJECTED')) NOT NULL,
    user_id INTEGER REFERENCES users (id)
)