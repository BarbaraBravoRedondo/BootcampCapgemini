DROP TABLE personas IF EXISTS;
CREATE TABLE personas (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    nombre VARCHAR(250),
    correo VARCHAR(250),
    ip VARCHAR(20)
);