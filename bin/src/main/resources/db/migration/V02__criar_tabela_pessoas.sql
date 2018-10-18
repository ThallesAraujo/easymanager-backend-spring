CREATE TABLE pessoa(
    id BIGINT(20) PRIMARY KEY,
    nome VARCHAR(25) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(10),
    complemento VARCHAR(20),
    bairro VARCHAR(20),
    cep VARCHAR(8),
    cidade VARCHAR(20),
    estado VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (id, nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values (1, 'Tainara Alves de Souza', true, null, null, null, null, null, null);
INSERT INTO pessoa (id, nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values (2, 'João Carlos de Oliveira', true, 'Quadra 27','21', 'Setor Central', null, 'Gama', 'DF');
INSERT INTO pessoa (id, nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values (3, 'Rodrigo Almeida Santos', true, 'Av. Hilton Souto Maior','43', 'Centro', null, 'João Pessoa', 'PB');
INSERT INTO pessoa (id, nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values (4, 'Carolina Dias Campelo', true, 'Av. Agamenom Magalhães','45', 'Centro', null, 'Arcoverde', 'PE');
