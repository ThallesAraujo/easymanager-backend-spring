CREATE TABLE pessoa(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(10),
    complemento VARCHAR(20),
    bairro VARCHAR(50),
    cep VARCHAR(20),
    cidade VARCHAR(25),
    estado VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Tainara Alves de Souza', true, null, null, null, null, null, null);
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('João Carlos de Oliveira', true, 'Quadra 27','21', 'Setor Central', null, 'Gama', 'DF');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Rodrigo Almeida Santos', true, 'Av. Hilton Souto Maior','43', 'Centro', null, 'João Pessoa', 'PB');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Carolina Dias Campelo', true, 'Av. Agamenom Magalhães','45', 'Centro', null, 'Arcoverde', 'PE');
