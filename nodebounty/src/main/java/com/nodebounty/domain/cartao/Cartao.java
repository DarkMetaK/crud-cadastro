package com.nodebounty.domain.cartao;

import java.time.LocalDate;
import java.util.Random;

import com.nodebounty.domain.contacorrente.ContaCorrente;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Nenhuma mudança relevante. Apenas mudou o tipo do id pra String, pra deixar igual o da classe Cliente, um id do tipo UUID.
 * Como o H2 vai salvar as propriedades tudo em maiúsculo, preciso dizer que no BD a propriedade 'titularCartao' vai ser 'TITULARCARTAO'
 * Por isso uso o @Column em todas. O @Table faz o mesmo mas com a tabela em si. */

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CARTOES")
public class Cartao {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "IDCARTAO")
	private String idCartao;
	@Column(name = "VALIDADECARTAO")
	private LocalDate validadeCartao;
	@Column(name = "NUMEROCARTAO")
	private String numeroCartao;
	@Column(name = "CVCCARTAO")
	private Short cvcCartao;
	
	@OneToOne // Indica o relacionamento 1:1
	@JoinColumn(name = "PLANONOME", referencedColumnName = "IDPLANO") // Cria a coluna no banco chamada "plano_nome" e faz a referência ao id do plano
	private Plano plano; // Adiciona um campo para representar o Plano atrelado ao cartão
	
	// Associando um cartão a uma conta
	@OneToOne
	@JoinColumn(name = "CARTAOCONTA", referencedColumnName = "IDCONTA")
	private ContaCorrente conta;
	
	@PrePersist
	public void gerarDados() {
        Random random = new Random();
        
        // Gerando numero do cartao
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        this.numeroCartao = sb.toString();
        
        // Gerando cvc do cartao
        this.cvcCartao = (short) (100 + random.nextInt(900));
        
        // Gerando validade do cartao
        LocalDate dataAtual = LocalDate.now();
        this.validadeCartao = dataAtual.plusYears(8); // Adicione 8 anos à data atual
	}

}
