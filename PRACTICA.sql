USE mascotas;
CREATE TABLE RESPONSABLE (
    CveResponsable INT PRIMARY KEY,
    NomResp VARCHAR(50),
    EdadResp INT,
    Telefono VARCHAR(10)
);

CREATE TABLE MASCOTAS (
    NoRegMasc INT PRIMARY KEY,
    NomMasc VARCHAR(50),
    Raza VARCHAR(50),
    EdadMasc INT,
    Tipo VARCHAR(20),
    Peso DECIMAL(5,2),
    CveResponsable INT,
    FOREIGN KEY (CveResponsable) REFERENCES RESPONSABLE(CveResponsable)
);

CREATE TABLE CONCURSO (
    NoConcurso INT PRIMARY KEY,
    Fecha DATE,
    Premio VARCHAR(20),
    NoRegMasc INT,
    FOREIGN KEY (NoRegMasc) REFERENCES MASCOTAS(NoRegMasc)
);

INSERT INTO RESPONSABLE VALUES 
(1, 'Adrian Peinado', 35, '6114456790'),
(3, 'Paulina Flores', 17, '6142469873'),
(5, 'Valentin Eliozalde', 29, '6397854621'),
(7, 'Silvestre', 16, '6368946217');

INSERT INTO MASCOTAS VALUES 
(100, 'Beky', 'Labrador', 4, 'Canino', 12.0, 1),
(200, 'Rocky', 'Shitsu', 5, 'Canino', 3.5, 3),
(300, 'Chiken Litgter', 'Pelea', 7, 'Ave', 1.3, 5),
(400, 'Pilolin', 'Canario', 12, 'Ave', 0.2, 7),
(500, 'Dolly', 'Esnauzer', 4, 'Canino', 2.0, 3),
(600, 'Dolly', 'Chihuahua', 7, 'Canino', 0.8, 1);

INSERT INTO CONCURSO VALUES 
(1, '2021-12-15', 'oro', 200),
(2, '2021-12-18', 'plata', 500),
(3, '2021-12-15', 'plata', 100),
(4, '2021-12-15', 'bronce', 600),
(5, '2021-12-19', 'oro', 400);

CREATE VIEW Vista_Mascotas AS
SELECT NomMasc, Raza, EdadMasc
FROM MASCOTAS;

SELECT * FROM Vista_Mascotas;
