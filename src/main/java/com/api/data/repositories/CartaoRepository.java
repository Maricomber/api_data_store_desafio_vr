package com.api.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.data.entities.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
	Cartao findByIdCartao(Integer idCartao);

}
