package com.api.data.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.api.data.enums.TipoMovimentacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private int idConta;
	
	@ManyToOne(cascade = CascadeType.MERGE)  
    @JoinColumn(name="id_cartao", nullable = false)
	private Cartao cartao;
	
	@Column(name = "valor", nullable = false)
	private int valor;
	
	@Column(name = "tipo_movimentacao", nullable = false, length = 2)
	private TipoMovimentacao tipoMovimentacao;
	
	@Column(name = "data_operacao", nullable = false)
	private Date dataOperacao;
}
