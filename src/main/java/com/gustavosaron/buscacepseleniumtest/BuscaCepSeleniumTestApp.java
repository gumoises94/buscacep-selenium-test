package com.gustavosaron.buscacepseleniumtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BuscaCepSeleniumTestApp {
	
	private static final String PATH_CHROMEDRIVER  = "E:/WebDriver/bin/chromedriver.exe";

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", PATH_CHROMEDRIVER);
		
		try {
			WebDriver driver = new ChromeDriver();
			driver.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
