package com.google.accounts.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Classe que retorna o driver do navegador escolhido.
 * @author DouglasGolke
 *
 */
public class FactoryDriver {

	public WebDriver getDriver(String navegador){

//		switch(navegador.toUpperCase()){
//				
//			case "CHROME":
//				String exePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
//				System.setProperty("webdriver.chrome.driver", exePath);
//				return new ChromeDriver();
//			
//			case "FIREFOX":
//				return new FirefoxDriver();
//					
//			default:
//				System.out.println("Nome do navegador invalido: " + navegador);
//		       	throw new IllegalArgumentException("Nome do navegador invalido: " + navegador);
		
		String exePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--start-maximized");
		options.addArguments("--enable-automation");
		return new ChromeDriver(options);
			
//		}
	}
}
