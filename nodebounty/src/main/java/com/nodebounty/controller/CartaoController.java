package com.nodebounty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nodebounty.config.errors.ErroCustomizadoDTO;
import com.nodebounty.config.errors.RegistroNaoEncontradoException;
import com.nodebounty.service.CartaoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cartoes")
@SuppressWarnings("rawtypes")
public class CartaoController {
	
	@Autowired
	private CartaoService service;

	// Criando um novo cartao, pegando o id do cliente pelo token
	@PostMapping
	public ResponseEntity gerarCartao(HttpServletRequest request) {
		var idCliente = request.getAttribute("idCliente");
		
		try {
			var cartao = service.gerarNovoCartao((String) idCliente);
			return ResponseEntity.ok(cartao);
		}
		catch(RegistroNaoEncontradoException e) {
			// Tratando exceções quando não encontra o registro no banco, para retornar um código HTTP 404 - Not found
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroCustomizadoDTO(e.getMessage()));
		}
		catch (Exception e) {
			// Tratando qualquer outro tipo de erro, retornando um 400 - Bad request
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErroCustomizadoDTO(e.getMessage()));
		}
	}

	// Listando todos cartoes pelo id da conta
	@GetMapping
	public ResponseEntity consultaTodos(HttpServletRequest request) {
		var idCliente = request.getAttribute("idCliente");
		
		try {
			var cartoes = service.consultaTodosCartoesPeloIdDoCliente((String) idCliente);
			return ResponseEntity.ok(cartoes);
		}
		catch(RegistroNaoEncontradoException e) {
			// Tratando exceções quando não encontra o registro no banco, para retornar um código HTTP 404 - Not found
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroCustomizadoDTO(e.getMessage()));
		}
		catch (Exception e) {
			// Tratando qualquer outro tipo de erro, retornando um 400 - Bad request
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErroCustomizadoDTO(e.getMessage()));
		}
	}
	
	// Deletando cartao pelo id
	@DeleteMapping("/{cartaoId}")
	public ResponseEntity excluiCartao(@PathVariable String cartaoId) {
		try {
			service.excluiCartao(cartaoId);
			return ResponseEntity.noContent().build();
		}
		catch(RegistroNaoEncontradoException e) {
			// Tratando exceções quando não encontra o registro no banco, para retornar um código HTTP 404 - Not found
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroCustomizadoDTO(e.getMessage()));
		}
		catch (Exception e) {
			// Tratando qualquer outro tipo de erro, retornando um 400 - Bad request
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErroCustomizadoDTO(e.getMessage()));
		}
	}
}