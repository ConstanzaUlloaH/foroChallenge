create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(200) not null unique,
    fecha DATE not null,
    nombre_curso varchar(200) not null,
    status varchar(200) not null,
    usuario_id BIGINT NOT NULL,

    primary key(id)
)