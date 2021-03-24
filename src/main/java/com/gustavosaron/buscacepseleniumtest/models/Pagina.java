package com.gustavosaron.buscacepseleniumtest.models;

public abstract class Pagina {
	
	private static final String PATH_CHROMEDRIVER  = "E:/WebDriver/bin/chromedriver.exe";
	
	public Pagina() {
		System.setProperty("webdriver.chrome.driver", PATH_CHROMEDRIVER);
	}
	
	public abstract void fecharPagina();

}
