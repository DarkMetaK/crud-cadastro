/*
package com.nodebounty.domain.transacao;

import com.nodebounty.domain.contacorrente.ContaCorrente;

public class TransacaoController extends ContaCorrente {

	public void Depositar(Double valor) {
		if (valor > 0 ) {
			setSaldoConta(getSaldoConta() + valor);
			System.out.println("Seu depósito foi realizado com sucesso!");
		} else 
			System.out.println("Não foi possível realizar o seu depósito. Verifique o valor atribuído.");
	}
	
	public void Sacar(Double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldoConta(getSaldoConta() - valor);
			System.out.println("Saque concluído com sucesso");
		} else 
			System.out.println("Não foi possível realizar o seu saque. Verifique o valor atribuído.");
	}
	
	public void Transferir(String idConta, Double valor) {
		if (valor > 0 && this.getSaldo() >= valor) {
			setSaldoConta(getSaldoConta() - valor);
			idConta.saldoConta = idConta.getSaldoConta() + valor;
			System.out.println("Sua transferência foi concluída com sucesso");
	} else {
		System.out.println("O sistema não foi capaz de processar sua transferência. Tente novamente.");
		}
	}
}
*/