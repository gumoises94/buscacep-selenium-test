package com.gustavosaron.buscacepseleniumtest.models;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuscaCepPaginaTest {

	
	private BuscaCepPagina buscaCepPagina;
	
	@BeforeEach
	public void setUp() {
		buscaCepPagina = new BuscaCepPagina();
	}
	
	@AfterEach
	public void tearDown() {
		buscaCepPagina.fecharPagina();
	}
	
	@Test
	public void deveRetornarEnderecoEspecifico_QuandoBuscarPorCepConhecidoTest() {
		Endereco enderecoConhecido = Endereco.builder()
				.logradouro("Rua Areias")
				.distrito("Cambuí")
				.localidadeUf("Campinas/SP")
				.cep("13024-530")
				.build();
		
		List<Endereco> resultado = 
				buscaCepPagina.buscarAte50PrimeirosResultados(enderecoConhecido.getCep());
		
		assertThat(resultado.get(0), is(equalTo(enderecoConhecido)));
	}
	
	@Test
	public void deveRetornarListaPreenchida_QuandoBuscarPorParametroGeneralistaTest() {
		List<Endereco> resultado = 
				buscaCepPagina.buscarAte50PrimeirosResultados("Rua a");
		
		assertTrue(resultado.size() > 0);
	}
	
	@Test
	public void deveRetornarListaVazia_QuandoParamentroNaoEncontradoTest() {
		String parametro = "Endereço que não deverá ser encontrado";
		List<Endereco> resultado = 
				buscaCepPagina.buscarAte50PrimeirosResultados(parametro);
		
		assertTrue(resultado.isEmpty());
	}
	
}
