package com.api.data.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.data.dto.ContaDTO;

@Service
public interface ContaServices {
	List<ContaDTO>findAll() throws SQLException;
	
	ContaDTO findById(Integer idConta) throws SQLException;
	
	List<ContaDTO> save(List<ContaDTO>  contaDTO) throws SQLException;
	
	void delete(Integer idPessoa) throws SQLException;
}
