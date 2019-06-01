
insert into tbl_fabricante(nome) values('GM');
insert into tbl_fabricante(nome) values('VW');

select * from tbl_fabricante;
select * from tbl_veiculo;
select * from tbl_movimentacao;
select * from tbl_preco;
desc tbl_movimentacao;
delete 

insert into tbl_veiculo(placa, modelo, cod_fabricante) values('ABC1234', 'GOL', 1);
insert into tbl_veiculo(placa, modelo, cod_fabricante) values('CBA4321', 'CHEVETTE', 1);
insert into tbl_veiculo(placa, modelo, cod_fabricante) values('AAA1111', 'BRASILIA', 1);




create table tbl_veiculo(
	cod_veiculo int not null auto_increment primary key,
    placa varchar(8) not null,
    modelo varchar(30) not null,
    cod_fabricante int not null,
    foreign key (cod_fabricante) references tbl_fabricante(cod_fabricante)
    
);

create table tbl_fabricante(
	cod_fabricante int not null auto_increment primary key,
    nome varchar(30) not null
);


create table tbl_movimentacao(
	id int not null auto_increment primary key,
    placa varchar(10) not null,
    modelo varchar(50) not null,
    horario_entrada datetime null,
    horario_saida datetime null,
    tempo int null,
    valor_pago float
);

create table tbl_preco(
	id int not null auto_increment primary key,
    valor_primeira_hora float not null,
    valor_demais_horas float not null,
    data_fim date,
    tolerancia int
);

CREATE TABLE tbl_mensalista(
	id int not null primary key auto_increment,
    nome varchar(100) not null,
    cpf varchar(15) not null,
    celular varchar(20) not null,
    qtd_vagas int    
);


CREATE TABLE tbl_mensalista_veiculo(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    id_mensalista INT NOT NULL,
    id_veiculo INT NOT NULL,
    FOREIGN KEY (id_mensalista) REFERENCES tbl_mensalista(id),
    FOREIGN KEY (id_veiculo) REFERENCES tbl_veiculo(id)
);

desc tbl_veiculo;
desc tbl_mensalista;

SELECT tbl_mensalista.nome, tbl_veiculo.modelo FROM tbl_mensalista INNER JOIN tbl_mensalista_veiculo ON tbl_mensalista.id_mensalista_veiculo = tbl_mensalista_veiculo.id_mensalista_veiculo INNER JOIN tbl_veiculo ON tbl_veiculo.id = tbl_mensalista_veiculo.id_veiculo;