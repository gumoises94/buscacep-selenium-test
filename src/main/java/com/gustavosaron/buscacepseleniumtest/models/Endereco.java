package com.gustavosaron.buscacepseleniumtest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {
	
	private String logradouro;
	private String distrito;
	private String localidadeUf;
	private String cep;

}
