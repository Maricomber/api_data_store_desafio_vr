package com.api.data.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.data.dto.CartaoDTO;
import com.api.data.entities.Cartao;
import com.api.data.repositories.CartaoRepository;
import com.api.data.services.CartaoService;

@Service
public class CartaoServiceImpl implements CartaoService{

	@Autowired
	CartaoRepository repository;
	
	private String msgErro;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(CartaoServiceImpl.class);
	
	@Override
	public List<CartaoDTO> findAll() throws SQLException {
		log.info("Buscando todas os registros de cartaos.");
		List<Cartao> cartaos = new ArrayList<>();
		List<CartaoDTO> cartaosRetorno = new ArrayList<>();
		
		try {
			cartaos = this.repository.findAll();
			cartaos.stream().forEach(cartao->cartaosRetorno.add(mapper.map(cartao, CartaoDTO.class)));
			log.info("Busca realizada com sucesso");
			return cartaosRetorno;
		}catch (Exception e) {
			msgErro = "Erro ao buscar cartaos. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public CartaoDTO findById(Integer idCartao) throws SQLException {
		log.info("Buscando cartao.");
		Cartao cartao = new Cartao();
		try {
			cartao = this.repository.findByIdCartao(idCartao);
			if(cartao == null) {
				throw new NoResultException("Sem resultados.");
			}
			log.info("Cartao encontrado.");
			return mapper.map(cartao, CartaoDTO.class);
		}catch (Exception e) {
			msgErro = "Erro ao buscar cartao. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public CartaoDTO save(CartaoDTO cartaoDTO) throws SQLException {
		if(cartaoDTO == null){
			throw new NoResultException("Pesquisa em branco. ");
		}
		log.info("Salvando cartao");
		Cartao cartao = new Cartao();
		try {
			cartao = this.repository.save(mapper.map(cartaoDTO, Cartao.class));
			return mapper.map(cartao, CartaoDTO.class);
		}catch (Exception e) {
			msgErro = "Erro ao salvar cartao. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public void delete(Integer idCartao) throws SQLException {
		Cartao cartao = new Cartao();
		log.info("Deletando cartao...");
		
		try{
			cartao = this.repository.findByIdCartao(idCartao);
			this.repository.delete(cartao);
		}catch (Exception e) {
			msgErro = "Erro cartao não pode ser deletado. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

}
