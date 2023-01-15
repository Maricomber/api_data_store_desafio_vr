package com.api.data.dto;

import java.sql.Date;
import java.util.List;

import com.api.data.enums.TipoPessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

	private int idPessoa;
	private Date dataNascimento;
	private String nome;
	private String endereco;
	private String documento;
	private TipoPessoa tipoPessoa;
	private List<TelefoneDTO> telefone;
	private List<CartaoDTO> cartao;
}
