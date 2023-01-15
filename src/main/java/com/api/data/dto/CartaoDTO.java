package com.api.data.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.api.data.enums.TipoCartao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoDTO {

	@NotNull
	private Integer idCartao;
	
	@NotBlank
	private String numero;
	
	@NotNull
	private Date dataVencimento;
	
	@NotNull
	private Date dataEmissao;
	
	@NotBlank
	private String codSeguranca;
	
	@NotBlank
	private TipoCartao tipoCartao;
	
	@NotNull
	private float saldo;
	
	@NotNull
	private Boolean isAtivo;
	
	@NotNull
	private int idPessoa;
}