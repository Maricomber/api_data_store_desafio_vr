package com.api.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.data.entities.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
	Telefone findByIdTelefone(Integer idTelefone);
}
