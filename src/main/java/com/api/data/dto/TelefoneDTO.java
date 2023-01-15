package com.api.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO {

	private Integer idTelefone;
	
	@NotEmpty(message = "Campo numero não pode ficar vazio")
	private String numero;
	
	@NotNull(message = "Campo idPessoa não pode ficar vazio")
	private Integer idPessoa;
}
