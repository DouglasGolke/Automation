package com.google.accounts.testes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.google.accounts.factory.FactoryDriver;
import com.google.accounts.utils.LogUtils;

/**
 * Classe que auxilia as classes testes, com os métodos before e after.
 * @author DouglasGolke
 *
 */
public class AbstractTeste {

	protected WebDriver driver;
	public static Logger log;
	private FactoryDriver factoryDriver;
	
	public static ArrayList<String> passos;
	
	@BeforeSuite
	public void beforeSuite(){
		//TODO
	}
	
	@BeforeClass
	public void beforeClass(){
		//TODO
	}
	
	/**
	 * Método que inicia o navegador escolhido, maximiza a tela e insere a url.
	 */
	@BeforeMethod
	public void beforeMethod(Method m){
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		factoryDriver = new FactoryDriver();
		driver = factoryDriver.getDriver("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		passos = new ArrayList<String>();
		driver.get("https://accounts.google.com/signup?hl=pt-BR");
	}
	
	/**
	 * Método que fecha o navegador.
	 */
	@AfterMethod
	public void afterMethod(){
		LogUtils.exibeLog();
		driver.quit();
	}
	
	@AfterClass
	public void afterClass(){
		//TODO
	}
	
	@AfterSuite
	public void afterSuite(){
		//TODO
	}

}
