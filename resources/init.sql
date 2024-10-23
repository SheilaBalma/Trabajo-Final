CREATE TABLE gym_management.clientes (
	nombre varchar(100) NOT NULL,
	idCliente INT auto_increment NOT NULL,
	apellido varchar(100) NOT NULL,
	direccion varchar(100) NOT NULL,
	telefono varchar(100) NULL,
	email varchar(100) NULL,
	dni varchar(100) NOT NULL,
	edad INT NOT NULL UNIQUE,
	tipoMembresia varchar(100) NOT NULL,
	estadoPago varchar(100) NULL,
	CONSTRAINT clientes_PK PRIMARY KEY (idCliente)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
