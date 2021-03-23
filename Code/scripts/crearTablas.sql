#///// CREACIÓN DE LA BASE DE DATOS ///// 
#CREATE DATABASE rest DEFAULT CHARACTER SET utf8;

#///// BORRAR LA BASE DE DATOS ///// 
#DROP DATABASE rest

#///// CREACIÓN DE TABLAS //////
CREATE TABLE categoria
(
  id_categoria INT auto_increment NOT NULL,
  nombre VARCHAR(30) NOT NULL,
  descripcion VARCHAR(150) NOT NULL,
  PRIMARY KEY (id_categoria)
);

CREATE TABLE menu
(
  id_menu INT auto_increment NOT NULL,
  menu_actual boolean NOT NULL,
  PRIMARY KEY (id_menu)
);

CREATE TABLE plato
(
  id_plato INT auto_increment NOT NULL,
  nombre VARCHAR(30) NOT NULL,
  descripcion VARCHAR(150) NOT NULL,
  precio FLOAT NOT NULL,
  num_plato INT NOT NULL,
  id_categoria INT NOT NULL,
  PRIMARY KEY (id_plato),
  FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE cliente
(
  id_cliente INT auto_increment NOT NULL,
  usuario VARCHAR(50) NOT NULL,
  contrasenya VARCHAR(50) NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  gasto_total FLOAT NOT NULL,
  PRIMARY KEY (id_cliente)
);

CREATE TABLE estado
(
  id_estado INT auto_increment NOT NULL,
  estado VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_estado)
);

CREATE TABLE temporada
(
  id_temporada INT auto_increment NOT NULL,
  nombre VARCHAR(30) NOT NULL,
  PRIMARY KEY (id_temporada)
);

CREATE TABLE rol
(
  id_rol INT auto_increment NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_rol)
);

CREATE TABLE plato_menu
(
  id_plato INT NOT NULL,
  id_menu INT NOT NULL,
  PRIMARY KEY (id_plato, id_menu),
  FOREIGN KEY (id_plato) REFERENCES plato(id_plato),
  FOREIGN KEY (id_menu) REFERENCES menu(id_menu)
);

CREATE TABLE carta
(
  id_carta INT auto_increment NOT NULL,
  carta_actual BOOLEAN NOT NULL,
  id_temporada INT NOT NULL,
  PRIMARY KEY (id_carta),
  FOREIGN KEY (id_temporada) REFERENCES temporada(id_temporada)
);

CREATE TABLE pedido
(
  id_pedido INT auto_increment NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  fecha DATE NOT NULL,
  hora_entrega DATE NOT NULL,
  importe FLOAT NOT NULL,
  id_estado INT NOT NULL,
  PRIMARY KEY (id_pedido),
  FOREIGN KEY (id_estado) REFERENCES estado(id_estado)
);

CREATE TABLE empleado
(
  id_empleado INT auto_increment NOT NULL,
  usuario VARCHAR(50) NOT NULL,
  contrasenya VARCHAR(50) NOT NULL,
  id_rol INT NOT NULL,
  PRIMARY KEY (id_empleado),
  FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE plato_carta
(
  id_carta INT NOT NULL,
  id_plato INT NOT NULL,
  PRIMARY KEY (id_carta, id_plato),
  FOREIGN KEY (id_carta) REFERENCES carta(id_carta),
  FOREIGN KEY (id_plato) REFERENCES plato(id_plato)
);

CREATE TABLE plato_pedido
(
  cantidad INT NOT NULL,
  id_pedido INT NOT NULL,
  id_plato INT NOT NULL,
  PRIMARY KEY (id_pedido, id_plato),
  FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
  FOREIGN KEY (id_plato) REFERENCES plato(id_plato)
);

CREATE TABLE cliente_pedido
(
  id_cliente INT NOT NULL,
  id_pedido INT NOT NULL,
  PRIMARY KEY (id_cliente, id_pedido),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
  FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
);

CREATE TABLE empleado_pedido
(
  id_empleado INT NOT NULL,
  id_pedido INT NOT NULL,
  PRIMARY KEY (id_empleado, id_pedido),
  FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
  FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
);

