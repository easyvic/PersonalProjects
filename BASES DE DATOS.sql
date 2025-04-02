CREATE DATABASE ALTAS;
use ALTAS;

CREATE TABLE PRODUCTO (
    codProducto INT PRIMARY KEY,
    descripcion VARCHAR(50),
    precio DECIMAL(10, 2)
);

CREATE TABLE CLIENTE (
    id_cliente INT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    direccion VARCHAR(100),
    telefono VARCHAR(15),
    poblacion VARCHAR(50)
);

CREATE TABLE VENTAS (
    idVend INT PRIMARY KEY,
    codProd INT,
    numCliente INT,
    cantidad INT,
    FOREIGN KEY (codProd) REFERENCES PRODUCTO(codProducto),
    FOREIGN KEY (numCliente) REFERENCES CLIENTE(id_cliente)
);

INSERT INTO PRODUCTO (codProducto, descripcion, precio) VALUES
(100, 'Boiler de paso', 1500),
(143, 'Boiler 15 g', 2000),
(146, 'Calentador 20 g', 1400),
(150, 'Boiler 30 g', 3000),
(157, 'Calentor Español', 2530);

INSERT INTO CLIENTE (id_cliente, nombre, apellido, direccion, telefono, poblacion) VALUES
(10, 'Hugo', 'Gonzalez', 'F. valladolid 123', '61144567890', 'Chihuahua'),
(12, 'Francisco', 'Rodriguez', 'F. Libertadores 236', '6142469873', 'Chihuahua'),
(14, 'Luis', 'Perez', 'F. San Miguel 9856', '6397854621', 'Delicias'),
(16, 'Pedro', 'Gonzalez', 'San Martin 1065', '6368946217', 'Cuauhtemoc');

INSERT INTO VENTAS (idVend, codProd, numCliente, cantidad) VALUES
(3465, 157, 14, 2),
(3466, 146, 10, 1),
(3467, 150, 12, 1),
(3468, 143, 12, 1),
(3469, 157, 12, 1),
(3470, 157, 12, 1);

SELECT * FROM CLIENTE;
UPDATE CLIENTE
SET telefono = '6391957655'
WHERE id_cliente = 16;






