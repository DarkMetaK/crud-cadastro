package com.nodebounty.domain.contacorrente;

public class Transacao {

    public void TesteSaque() {
    	// Cria duas instâncias da classe ContaCorrente e define o saldo inicial de cada uma para fins de teste
    	// Futuramente, precisamos dar um jeito de automatizar isso e chamar pelo ID, baseado no login realizado pelo
    	// cliente.
        ContaCorrente conta1 = new ContaCorrente();
        conta1.setSaldoConta(150.00);

        ContaCorrente conta2 = new ContaCorrente();
        conta2.setSaldoConta(100.00);

        // As linhas abaixos exibem o saldo inicial das contas, para que a gente não se perca no teste
        System.out.println("Saldo da conta 1: " + conta1.getSaldoConta());
        System.out.println("Saldo da conta 2: " + conta2.getSaldoConta());

        //Operações de saque e depósito definidas anteriormente na classe ContaCorrente
        conta1.sacar(50.00);
        
        
        conta2.depositar(50.00);

        //Operação de transferência definida enteriormente na classe ContaCorrente
        conta1.transferir(conta2, 30.00);

        System.out.println("Saldo da conta 1 após transferência: " + conta1.getSaldoConta());
        System.out.println("Saldo da conta 2 após transferência: " + conta2.getSaldoConta());
    }
}

