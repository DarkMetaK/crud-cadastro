CREATE TABLE contacorrente (
	idConta TEXT PRIMARY KEY UNIQUE NOT NULL,
	idCliente TEXT NOT NULL UNIQUE,
	idPlano TEXT NOT NULL,
	saldoConta DOUBLE NOT NULL,
	numeroConta TEXT UNIQUE NOT NULL
);

CREATE TABLE cartoes (
	idCartao TEXT PRIMARY KEY UNIQUE NOT NULL,
	validadeCartao DATE NOT NULL,
	numeroCartao VARCHAR(16) NOT NULL UNIQUE,
	cvcCartao INT NOT NULL,
	planoNome TEXT NOT NULL,
	cartaoConta TEXT NOT NULL,
    FOREIGN KEY (planoNome) REFERENCES planos (idPlano),
    FOREIGN KEY (cartaoConta) REFERENCES contacorrente (idConta)
);