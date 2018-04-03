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

