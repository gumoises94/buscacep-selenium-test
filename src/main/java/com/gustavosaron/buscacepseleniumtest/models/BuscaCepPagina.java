package com.gustavosaron.buscacepseleniumtest.models;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuscaCepPagina extends Pagina {
	
	private final String URL_BUSCA_CEP = "https://buscacepinter.correios.com.br/app/endereco/index.php?t";
	private final String TITLE_BUSCA_CEP = "Busca CEP";
	private final int TIMEOUT_PADRAO = 3;
	
	private WebDriver driver;
	
	public BuscaCepPagina() {
		super();
		
		driver = new ChromeDriver();
		driver.get(URL_BUSCA_CEP);
		
		if(isAcessoPaginaNaoOk())
			throw new IllegalStateException("Erro ao se conectar com a página do Busca CEP");
	}
	
	private boolean isAcessoPaginaNaoOk() {
		return !driver.getTitle().equals(TITLE_BUSCA_CEP);
	}
	
	public List<Endereco> buscarAte50PrimeirosResultados(String parametroBusca) {
		WebElement form = driver.findElement(By.tagName("form"));
		WebElement inputEndereco = form.findElement(By.id("endereco"));
		inputEndereco.sendKeys(parametroBusca + Keys.ENTER);
		
		return getResultadoBusca(form);
	}
	
	private List<Endereco> getResultadoBusca(WebElement form) {
		List<Endereco> enderecosEncontrados = new ArrayList<Endereco>();
		
		if(isEnderecoEncontrado(form)) {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_PADRAO);
			WebElement tabelaResultado = 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultado-DNEC")));
			
			WebElement corpo = tabelaResultado.findElement(By.tagName("tbody"));
			List<WebElement> linhas = corpo.findElements(By.tagName("tr"));
			for(WebElement linha : linhas) {
				List<WebElement> colunas = linha.findElements(By.tagName("td"));
				
				enderecosEncontrados.add(Endereco.builder()
							.logradouro(colunas.get(0).getText())
							.distrito(colunas.get(1).getText())
							.localidadeUf(colunas.get(2).getText())
							.cep(colunas.get(3).getText())
							.build());
			}
		}
		
		return enderecosEncontrados;
	}
	
	private boolean isEnderecoEncontrado(WebElement form) {
		String msgDadosNaoEncontrados = "Não há dados a serem exibidos";
		WebElement mensagemResultado = form.findElement(By.id("mensagem-resultado"));
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_PADRAO);
		
		try {
			wait.until(
					ExpectedConditions.textToBePresentInElement(mensagemResultado, msgDadosNaoEncontrados));
		}catch(Exception e) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void fecharPagina() {
		driver.close();
	}
	
}
