package com.api.data.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.data.dto.CartaoDTO;

@Service
public interface CartaoService {

	List<CartaoDTO>findAll() throws SQLException;
	
	CartaoDTO findById(Integer idPessoa) throws SQLException;
	
	CartaoDTO save(CartaoDTO cartaoDTO) throws SQLException;
	
	void delete(Integer idPessoa) throws SQLException;
	
}

