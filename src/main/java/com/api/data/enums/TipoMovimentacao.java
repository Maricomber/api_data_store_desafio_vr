package com.api.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoMovimentacao {

	ENTRADA(0),
	SAIDA(1);
	
	private int idTipoPessoa;
}
