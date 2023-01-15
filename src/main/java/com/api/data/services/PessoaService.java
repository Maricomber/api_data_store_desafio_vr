package com.api.data.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.data.dto.PessoaDTO;

@Service
public interface PessoaService {

	List<PessoaDTO>findAll() throws SQLException;
	
	PessoaDTO findById(Integer idPessoa) throws SQLException;
	
	PessoaDTO save(PessoaDTO pessoaDTO) throws SQLException;
	
	void delete(Integer idPessoa) throws SQLException;
	
}
