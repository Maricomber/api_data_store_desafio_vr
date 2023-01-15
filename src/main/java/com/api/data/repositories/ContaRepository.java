package com.api.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.data.entities.Conta;

@Repository
public interface ContaRepository  extends JpaRepository<Conta, Integer>{
	Conta findByIdConta(Integer idConta);

}
