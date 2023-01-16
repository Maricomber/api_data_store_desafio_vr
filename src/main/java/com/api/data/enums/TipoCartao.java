package com.api.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCartao {

	REFEICAO(1),
	ALIMENTACAO(2),
	OUTROS(3);
	
	private int idTipoPessoa;
}
