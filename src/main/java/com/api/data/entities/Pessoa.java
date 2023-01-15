package com.api.data.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.api.data.enums.TipoPessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa", unique = true, nullable = false)
	private Integer idPessoa;
	
	@Column(name = "nome_pessoa", length = 150)
	private String nome;
	
	@Column(name = "data_nasc_pessoa")
	private Date dataNascimento;
	
	@Column(name = "doc_pessoa", length = 20)
	private String documento;
	
	@Column(name = "tipo_pessoa", length = 2)
	private TipoPessoa tipoPessoa;
	
	@Column(name = "end_pessoa", length = 150)
	private String endereco;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
	private List<Telefone> telefone;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
	private List<Cartao> cartao;
}
