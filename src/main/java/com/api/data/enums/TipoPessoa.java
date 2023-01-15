package com.api.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoPessoa {
	PF(1),
	PJ(2);
	
	private int idTipoPessoa;
}
