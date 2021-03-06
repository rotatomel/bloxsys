--Script for MySQL

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `activo` tinyint(1) not null DEFAULT '1',
  `version` integer not null default 0,

  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `usuarios_roles`;
CREATE TABLE IF NOT EXISTS `usuarios_roles` (
  `id_usuario_rol` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(10) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `version` integer not null default 0,
  PRIMARY KEY (`id_usuario_rol`),
  KEY `fk_Usuario_Role_Usuario1_idx` (`id_usuario`),
  CONSTRAINT `fk_Usuario_Role_Usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into usuarios (id_usuario,login,password,nombre,email,activo,version) values (1,'admin','admin','ADMINISTRADOR','admin@admin.com',1,1);

insert into usuarios_roles (role,id_usuario,version) values ('ROLE_ADMIN',1,1);


DROP TABLE IF EXISTS `choferes`;
CREATE TABLE IF NOT EXISTS `choferes` (
  `id_chofer` int(11) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `celular_personal` varchar(255) ,
  `celular_corporativo` varchar(255) ,
  `cuil` bigint NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_vencimiento_licencia` date NOT NULL,
  `activo` tinyint(1) not null DEFAULT '1',
  `email` varchar(45) ,
  `id_supervisor` int(11) NOT NULL,
  `version` integer not null default 0,
  PRIMARY KEY (`id_chofer`),
  UNIQUE KEY `chofer_cuil_UNIQUE` (`cuil`),
  CONSTRAINT `fk_choferes_usuario` FOREIGN KEY (`id_supervisor`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `vehiculos_tipos`;
CREATE TABLE IF NOT EXISTS `vehiculos_tipos` (
  `id_tipo_vehiculo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo` varchar(45) NOT NULL,
  `version` integer not null default 0,
  PRIMARY KEY (`id_tipo_vehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `vehiculos_tipos` (`nombre_tipo`) VALUES ('FURGON');
INSERT INTO `vehiculos_tipos` (`nombre_tipo`) VALUES ('CAMIONETA');
INSERT INTO `vehiculos_tipos` (`nombre_tipo`) VALUES ('AUTOELEVADOR');
INSERT INTO `vehiculos_tipos` (`nombre_tipo`) VALUES ('CAMION');
INSERT INTO `vehiculos_tipos` (`nombre_tipo`) VALUES ('AUTOMOVIL');


DROP TABLE IF EXISTS `vehiculos`;
CREATE TABLE IF NOT EXISTS `vehiculos` (
  `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT,
  `dominio` varchar(8) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `nro_chasis` varchar(45) ,
  `nro_motor` varchar(45) ,
  `fecha_compra` date NOT NULL,
  `capacidad_carga` int(11),
  `observaciones` text,
  `id_tipo_vehiculo` int(11) not null,
  `activo` tinyint(1) not null DEFAULT '1',
  `version` integer not null default 0,
  PRIMARY KEY (`id_vehiculo`),
  UNIQUE KEY `vehiculo_dominio_UNIQUE` (`dominio`),
  CONSTRAINT `fk_vehiculos_id_tipo_vehiculo` FOREIGN KEY (`id_tipo_vehiculo`) REFERENCES `vehiculos_tipos` (`id_tipo_vehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `vehiculos_novedades`;
CREATE TABLE IF NOT EXISTS `vehiculos_novedades`(
    `id_novedad` int(11) NOT NULL AUTO_INCREMENT,
    `id_vehiculo` int(11) NOT NULL,
    `id_chofer` int(11) null,
    `fecha_novedad` DATETIME not null,
    `id_usuario` int(11) NOT NULL,
    `kilometros_realizados` decimal(19,2) default 0,
    `horas_uso` decimal(19,2) default 0,
    `costo` decimal(19,2) default 0,
    `observaciones` text,
    `tipo_novedad` varchar(45) not null,
    `version` integer not null default 0,
    PRIMARY KEY (`id_novedad`),
    CONSTRAINT `fk_vehiculos_novedades_vehiculo` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_vehiculos_novedades_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_vehiculos_novedades_chofer` FOREIGN KEY (`id_chofer`) REFERENCES `choferes` (`id_chofer`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `contoles_carga`;
CREATE TABLE IF NOT EXISTS `contoles_carga` (
  `id_control` int(11) NOT NULL AUTO_INCREMENT,
  `dominio` varchar(8) NOT NULL,
  `nro_remito` varchar(45) NOT NULL,
  `fecha_control` DATETIME NOT NULL,
  `peso_declarado` decimal(19,2) not null,
  `peso_censado` decimal(19,2) not null,
  `diferencia` decimal(19,2) not null,
  `observaciones` text,
  `id_usuario` int(11) NOT NULL,
  `version` integer not null default 0,
  PRIMARY KEY (`id_control`),
  CONSTRAINT `fk_contoles_carga_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
