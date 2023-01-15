package com.api.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.data.entities.Pessoa;



@Transactional(readOnly = true)
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	Pessoa findByIdPessoa(Integer idPessoa);
}