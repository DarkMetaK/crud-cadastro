package com.nodebounty.domain.cartao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nodebounty.domain.contacorrente.ContaCorrente;

/* Aqui eu removi o findAllByIdCartao pois já existe o findById().
 * Mudei o tipo de id pra UUID, igual o da Eloah, portanto o id agora é uma string
 */

public interface CartaoRepository extends JpaRepository<Cartao,String> {

	List<Cartao> findAllByConta(ContaCorrente conta);

}