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

import com.api.data.dto.PessoaDTO;
import com.api.data.entities.Pessoa;
import com.api.data.repositories.CartaoRepository;
import com.api.data.repositories.PessoaRepository;
import com.api.data.services.PessoaService;


@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	PessoaRepository repository;
	
	@Autowired
	CartaoRepository repositoryCartao;
	
	private String msgErro;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);
	
	@Override
	public List<PessoaDTO> findAll() throws SQLException {
		log.info("Buscando todas os registros de pessoas.");
		List<PessoaDTO> pessoasRetorno = new ArrayList<>();
		
		try {
			this.repository.findAll().forEach(pessoa->pessoasRetorno.add(mapper.map(pessoa, PessoaDTO.class)));
			log.info("Busca realizada com sucesso");
			return pessoasRetorno;
		}catch (Exception e) {
			msgErro = "Erro ao buscar pessoas. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public PessoaDTO findById(Integer idPessoa) throws SQLException {
		log.info("Buscando pessoa.");
		Pessoa pessoa = new Pessoa();
		try {
			pessoa = this.repository.findByIdPessoa(idPessoa);
			if(pessoa == null) {
				throw new NoResultException("Sem resultados.");
			}
			log.info("Pessoa encontrado.");
			return mapper.map(pessoa, PessoaDTO.class);
		}catch (Exception e) {
			msgErro = "Erro ao buscar pessoa. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public PessoaDTO save(PessoaDTO pessoaDTO) throws SQLException  {
		if(pessoaDTO.getDocumento() == null){
			throw new NoResultException("Pesquisa em branco.");
		}
		
		try {
			log.info("Salvando pessoa");
			Pessoa pessoa = mapper.map(pessoaDTO, Pessoa.class);
			pessoa.getCartao().stream().forEach(cartao->cartao.setPessoa(pessoa));
			pessoa.getTelefone().stream().forEach(telefone->telefone.setPessoa(pessoa));
			return mapper.map(this.repository.save(pessoa), PessoaDTO.class);
		}catch (Exception e) {
			msgErro = "Erro ao salvar pessoa. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public void delete(Integer idPessoa) throws SQLException {
		Pessoa pessoa = new Pessoa();
		log.info("Deletando pessoa...");
		
		try{
			pessoa = this.repository.findByIdPessoa(idPessoa);
			this.repository.delete(pessoa);
		}catch (Exception e) {
			msgErro = "Erro pessoa não pode ser deletado. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

}
