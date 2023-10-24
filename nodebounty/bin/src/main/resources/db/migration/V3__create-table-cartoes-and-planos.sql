CREATE TABLE planos (
	idPlano TEXT PRIMARY KEY UNIQUE NOT NULL,
	porcentagemCashback DOUBLE NOT NULL,
	parcerias TEXT NOT NULL
);

CREATE TABLE cartoes (
	idCartao TEXT PRIMARY KEY UNIQUE NOT NULL,
	validadeCartao DATE NOT NULL,
	numeroCartao VARCHAR(16) NOT NULL UNIQUE,
	cvcCartao INT NOT NULL,
	planoNome TEXT NOT NULL,
    FOREIGN KEY (planoNome) REFERENCES planos (idPlano)
);
