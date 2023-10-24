package com.nodebounty.domain.cliente;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

@Table(name = "CLIENTES")
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "idCliente") /* Seleciona a chave primária da tabela em questão */

/* Implementando classe necessária para configurar autenticação */
public class Cliente implements UserDetails {

	/*
	 * Indica que o id é gerado automaticamente e não é um valor atribuido pelo
	 * usuário
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "IDCLIENTE")
	private String idCliente;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "ENDERECO")
	private String endereco;
	@Column(name = "CEP")
	private String cep;
	@Column(name = "NUMERO")
	private Integer numero;
	@Column(name = "RG")
	private String rg;
	@Column(name = "CPF")
	private String cpf;
	@Column(name = "DATANASCIMENTO")
	private String dataNascimento;
	@Column(name = "TELEFONE")
	private String telefone;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "SENHA")
	private String senha;
	
	private static int ContadorDeClientes = 1;

	/*
	 * Construtor para cadastrar novo cliente com base nos dados recebidos pelo
	 * controller
	 */

	public Cliente(DadosCadastroCliente requestClientes) {
		this.nome = requestClientes.nome();
		this.endereco = requestClientes.endereco();
		this.cep = requestClientes.cep();
		this.numero = requestClientes.numero();
		this.rg = requestClientes.rg();
		this.cpf = requestClientes.cpf();
		this.dataNascimento = requestClientes.dataNascimento();
		this.telefone = requestClientes.telefone();
		this.email = requestClientes.email();
		this.senha = requestClientes.senha();
		ContadorDeClientes += 1;
	}
	
	public String ListarCliente() {
		return "\nNome: " + this.getNome() +  
				"\nCpf: " + this.getCpf() +
				"\nEmail: " + this.getEmail() + 
				"\nId: " + this.getIdCliente();
	}
	
	/*
	 * Método para atualizar cliente com base nos dados recebidos pelo controller
	 * Como todos os campos são opcionais, verifico primeiro se foi enviado algo. Se
	 * tiver sido, ai eu atualizo
	 */
	public void atualizarDados(DadosAtualizacaoCliente data) {
		if (data.nome() != null) {
			this.nome = data.nome();
		}
		if (data.endereco() != null) {
			this.endereco = data.endereco();
		}
		if (data.cep() != null) {
			this.cep = data.cep();
		}
		if (data.numero() != null) {
			this.numero = data.numero();
		}
		if (data.telefone() != null) {
			this.telefone = data.telefone();
		}
		if (data.senha() != null) {
			this.senha = data.senha();
		}
	}

	/* TODOS OS MÉTODOS ABAIXO SÃO REFERENTES A AUTENTICAÇÃO */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Aqui seria referentes as permissões do usuário. Como nosso sistema não tem
	 * essa coisa de permissão então é retornado uma lista de permissão genérica pq
	 * o Spring exige algo.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	/*
	 * Somente esses dois métodos abaixos são de fato relevantes pro nosso sistema.
	 * Um deve retornar a senha, o outro o e-mail
	 */

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	/*
	 * Essa parte para baixo seria referente a conta estar ativa ou não. Lembra do
	 * sistema do professor Fabio, onde se o usuário não pagar a conta fica
	 * desativada? Pelo que eu entendi, caso nosso sistema tivesse algo similar
	 * então seria necessário configurar aqui. Como não temos, só retorno true em
	 * tudo pra liberar tudo.
	 */

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
