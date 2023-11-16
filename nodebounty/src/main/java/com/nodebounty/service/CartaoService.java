package com.nodebounty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nodebounty.config.errors.RegistroNaoEncontradoException;
import com.nodebounty.domain.cartao.Cartao;
import com.nodebounty.domain.cartao.CartaoRepository;
import com.nodebounty.domain.cliente.ClienteRepository;
import com.nodebounty.domain.contacorrente.ContaCorrenteRepository;


@Service
public class CartaoService implements ICartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaCorrenteRepository contaRepository;
	
	private final String Contanaoencontrada = "Conta não encontrada no sistema";

	
	// Ja que optamos por separar o cliente da conta, pra facilitar nas requisições, invés de receber
	// o id da conta diretamente, vamos usar o do cliente, e com o do cliente buscar os dados da conta
	// para ai sim associar a conta ao cartao
    public Cartao cadastrarCartao(String idCliente){
    	var cliente = clienteRepository.findById(idCliente);
    	
    	// Verificando se o cliente existe
    	if (!cliente.isPresent()) {
    		throw new RegistroNaoEncontradoException("Cliente não encontrado no sistema");
    	}
    	
    	var conta = contaRepository.findByCliente(cliente.get());
    	
    	// Verificando se a conta existe
    	if (conta == null) {
    		throw new RegistroNaoEncontradoException(Contanaoencontrada);
    	}
    	
    	// Gerando o cartão, validações bem sucedidas
    	var cartao = new Cartao();
    	cartao.setConta(conta);
    	cartao.setPlano(conta.getPlano());
    	
    	return cartaoRepository.save(cartao);
    }
    
    // Listando TODOS cartões associado a uma conta
    public List<Cartao> consultarCartoesDoCliente(String idCliente) {
    	var cliente = clienteRepository.findById(idCliente);
    	
    	// Verificando se o cliente existe
    	if (!cliente.isPresent()) {
    		throw new RegistroNaoEncontradoException("Cliente não encontrado no sistema");
    	}
    	
    	var conta = contaRepository.findByCliente(cliente.get());
    	
    	// Verificando se a conta existe
    	if (conta == null) {
    		throw new RegistroNaoEncontradoException(Contanaoencontrada);
    	}
    	
    	// Buscando e retornando todos cartoes associados a conta
    	return cartaoRepository.findAllByConta(conta);
    }
    
    // Excluindo um cartão pelo id
    public void excluirCartao(String idCartao) {
    	var cartaoExisteNoSistema = cartaoRepository.existsById(idCartao);
    	
    	// Retornando erro caso o id do cartao não exista no banco
    	if (!cartaoExisteNoSistema) {
    		throw new RegistroNaoEncontradoException(Contanaoencontrada);
    	}
    	
    	// Excluindo cartao
    	
    	cartaoRepository.deleteById(idCartao);
    }
    
}