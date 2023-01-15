package com.api.data.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.api.data.enums.TipoMovimentacao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class ContaDTO {

	private int idConta;
	
	@NotNull
	private int idCartao;
	
	@NotNull
	private int valor;
	
	@NotNull
	private TipoMovimentacao tipoMovimentacao;
	
	@NotNull
	private Date dataOperacao;
}
