CREATE TABLE clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    direccion VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(50),
    dni VARCHAR(20) UNIQUE,
    edad INT,
    tipoMembresia VARCHAR(20),
    estadoPago BOOLEAN DEFAULT FALSE,
    metodoPago VARCHAR(20)
);
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE gym_management.empleados (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    email VARCHAR(100),
    dni VARCHAR(20) NOT NULL,
    CONSTRAINT unique_dni UNIQUE (dni)
);