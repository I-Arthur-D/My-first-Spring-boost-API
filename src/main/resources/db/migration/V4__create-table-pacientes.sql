create table pacientes (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(15) not null unique,
    bairro varchar(100) not null,
    cep char(9) not null,
    cidade varchar(100) not null,
    numero varchar(20),

    primary key(id)
);