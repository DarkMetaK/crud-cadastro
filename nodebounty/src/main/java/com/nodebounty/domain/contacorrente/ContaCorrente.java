package com.nodebounty.domain.contacorrente;

import java.util.Random;

import com.nodebounty.domain.cliente.Cliente;
import com.nodebounty.domain.plano.Plano;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CONTACORRENTE")
public class ContaCorrente {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "IDCONTA")
	private String idConta;
	
	/* Associação 1:1, uma conta corrente possui somente um cliente, a ligação ocorre por meio do atributo de Cliente.java -> IDCLIENTE */
	@OneToOne
	@JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")
	private Cliente cliente;

	/* Associação 1:1, uma conta corrente possui somente um plano, a ligação ocorre por meio do atributo de Plano.java -> IDPLANO */
	@OneToOne
	@JoinColumn(name = "IDPLANO", referencedColumnName = "IDPLANO") 
	private Plano plano;

	@Column(name = "SALDOCONTA")
	private double saldoConta;
	
	@Column(name = "CASHBACKCONTA")
	private double cashbackConta;
	
	/* Como a gente não vai usar o número para nenhuma operação matématica, acho que String é mais performático */
	@Column(name = "NUMEROCONTA")
	private String numeroConta;
	
	/* Método que será executado ANTES de salvar a conta no banco. Ele vai gerar um número aleatório de 20 digitos
	 * para a conta corrente e converter para string*/
    @PrePersist
    protected void onCreate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            sb.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        this.numeroConta = sb.toString();
    }
    
    public void depositar(double valor) {
    	this.saldoConta += valor;
    }
    
    public void sacar(double valor) {
    	this.saldoConta -= valor;
    }
    
    public void cashback(double valor) {
    	this.cashbackConta += valor;
    }
    
    public void resgatarCashback() {
    	this.saldoConta += this.cashbackConta;
    	this.cashbackConta = 0;
    }
    
    /* public void transferir(ContaCorrente clienteEmissor, ContaCorrente clienteReceptor, double valor) {
    	if (valor <= 0) {
    		System.out.println("O valor da transferência deverá ser maior que 0");
    		return;
    	}
    	if (this.saldoConta < valor) {
    		System.out.println("O saldo da conta é insuficiente para realizar a transferência");
    		return;
    	}
    		this.sacar(valor);
    		clienteReceptor.depositar(valor);
    		System.out.println("A transferência foi realizada com suscesso");
    }
    
    */ 
	
}