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


insert into usuarios (id_usuario,login,password,nombre,email,activo,version) values (1,'admin','admin','Administrador','admin@admin.com',1,1);

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
  `version` integer not null default 0,
  PRIMARY KEY (`id_chofer`),
  UNIQUE KEY `chofer_cuil_UNIQUE` (`cuil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `activo` tinyint(1) not null DEFAULT '1',
  `version` integer not null default 0,
  PRIMARY KEY (`id_vehiculo`),
  UNIQUE KEY `vehiculo_dominio_UNIQUE` (`dominio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `vehiculos_novedades`;
CREATE TABLE IF NOT EXISTS `vehiculos_novedades`(
    `id_novedad` int(11) NOT NULL AUTO_INCREMENT,
    `id_vehiculo` int(11) NOT NULL,
    `fecha_novedad` DATETIME not null,
    `id_usuario` int(11) NOT NULL,
    `kilometros_realizados` decimal(19,2),
    `horas_uso` decimal(19,2),
    `observaciones` text,
    `tipo_novedad` varchar(45) not null,
    `version` integer not null default 0,
    PRIMARY KEY (`id_novedad`),
    CONSTRAINT `fk_vehiculos_novedades_vehiculo` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_vehiculos_novedades_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
