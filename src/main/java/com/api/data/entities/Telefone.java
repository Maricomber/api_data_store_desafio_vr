package com.api.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "telefone")
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_telefone", nullable = true)
	private Integer idTelefone;
	
	@Column(name = "num_telefone", nullable = false)
	private String numero;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)  
    @JoinColumn(name="id_pessoa", nullable = false)
	private Pessoa pessoa;
}
