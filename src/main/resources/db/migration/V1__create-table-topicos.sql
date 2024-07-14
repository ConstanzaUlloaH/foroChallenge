create table topicos(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    titulo varchar(100) not null unique,
    mensaje varchar(200) not null unique,
    fecha DATE not null,
    status varchar(100) not null,

    primary key(id)
)