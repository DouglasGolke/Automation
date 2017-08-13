package com.google.accounts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.google.accounts.utils.LogUtils;

/**
 * Classe que possui os métodos para criar um gmail.
 */
public class PaginaCriarConta {

	private WebDriver driver;
	
	// Exemplo de mapeamento de mensagem de erro de tela
	public static final String VOCE_NAO_PODE_DEIXAR_ESSE_CAMPO_EM_BRANCO = "Você não pode deixar este campo em branco.";
	
	public PaginaCriarConta(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Preenche com uma String o campo nome.
	 * @param nome
	 * @return
	 */
	public PaginaCriarConta prencheCampoNome(String nome){
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(nome);
		LogUtils.info("Campo nome preenchido com -> "+nome);
		return this;
	}
	
	/**
	 * Preenche com uma String o campo Sobrenome.
	 * @param sobrenome
	 * @return
	 */
	public PaginaCriarConta prencheCampoSobrenome(String sobrenome){
//		driver.findElement(By.xpath("//div[@id='name-form-element']//label[@id='lastname-label']/input")).sendKeys(sobrenome);
		driver.findElement(By.id("LastName")).sendKeys(sobrenome);
		LogUtils.info("Campo preenchido com sobrenome -> "+sobrenome);
		return this;
	}
	
	/**
	 * Preenche com uma String o campo nome de usuário.
	 * @param email
	 * @return
	 */
	public PaginaCriarConta preencheNomeEmailUser(String nomeUser){
		driver.findElement(By.xpath("//div[@id='gmail-address-form-element']//input")).sendKeys(nomeUser);
		LogUtils.info("Campo de nome do usuário -> " +nomeUser);
		return this;
	}
	
	public PaginaCriarConta preencheNomeEmailUserUnico(String nomeUser){
		String idUnico = String.valueOf(System.currentTimeMillis());
		driver.findElement(By.xpath("//div[@id='gmail-address-form-element']//input")).sendKeys(nomeUser+idUnico);
		LogUtils.info("Campo de nome do usuário único -> " +nomeUser + idUnico);
		return this;
	}
	
	/**
	 * Preenche com uma String o campo criar uma senha.
	 * @param senha
	 * @return
	 */
	public PaginaCriarConta preencheSenha(String senha){
		driver.findElement(By.xpath("//div[@id='password-form-element']//input")).sendKeys(senha);
		LogUtils.info("Campo de senha preenchido com -> " +senha);
		return this;
	}
	
	/**
	 * Preenche com uma String o campo Confirme sua senha.
	 * @param confirmaSenha
	 * @return
	 */
	public PaginaCriarConta preencheConfirmaSenha(String confirmaSenha){
		driver.findElement(By.xpath("//div[@id='confirm-password-form-element']//input")).sendKeys(confirmaSenha);
		LogUtils.info("Campo de confirmação de senha preenchido com -> " +confirmaSenha);
		return this;
	}
	
	/**
	 * Preenche com uma String o campo dia, da data de nascimento.
	 * @param diaNasc
	 * @return
	 */
	public PaginaCriarConta preencheDiaNascimento(String diaNasc){
		driver.findElement(By.xpath("//fieldset//label[@id='day-label']//input")).sendKeys(diaNasc);
		LogUtils.info("Campo de dia do nascimento preenchido com -> " +diaNasc);
		return this;
	}
	
	/**
	 * Preenche com uma String o campo ano, da data de nascimento.
	 * @param anoNasc
	 * @return
	 */
	public PaginaCriarConta preencheAnoNascimento(String anoNasc){
		driver.findElement(By.xpath("//fieldset//label[@id='year-label']//input")).sendKeys(anoNasc);
		LogUtils.info("Campo de dia do ano do nascimento preenchido com -> " +anoNasc);
		return this;
	}
	
	/**
	 * Preenche com uma String o campo celular.
	 * @param telefone
	 * @return
	 */
	public PaginaCriarConta preencheCelular(String celular){
		driver.findElement(By.xpath("//div[@id='phone-form-element']//td//input[@id='RecoveryPhoneNumber']")).sendKeys(celular);
		LogUtils.info("Campo de celular preenchido com -> " +celular);
		return this;
	}
	
	/**
	 * Preenche com uma String o campo de endereço de e-mail atual.
	 * @param emailAtual
	 * @return
	 */
	public PaginaCriarConta preencheEmailAtual(String emailAtual){
		driver.findElement(By.xpath("//div[@id='recovery-email-form-element']//input[@id='RecoveryEmailAddress']")).sendKeys(emailAtual);
		LogUtils.info("Campo de email atual preenchido com -> " +emailAtual);
		return this;
	}
	
	/**
	 * Clica no botão Próxima etapa.
	 * @return
	 */
	public PaginaCriarConta clicaProximaEtapa(){
		driver.findElement(By.id("submitbutton")).click();
		LogUtils.info("Clicou no botão Próxima Etapa.");
		return this;
	}
	
	/**
	 * Seleciona com uma String o sexo.
	 * @param sexo
	 * @return
	 */
	public PaginaCriarConta selecionaSexo(String sexo){
		if (!sexo.equals("")) {
//			driver.findElement(By.xpath("//div[@id='Gender']")).click();
			driver.findElement(By.id("Gender")).click();
			driver.findElement(By.xpath("//div[@id='Gender']//div[text()='"+sexo+"']")).click();
		}
		LogUtils.info("Campo de selecionar o sexo selecionado com -> " +sexo);
		return this;			
	}
	
	/**
	 * Seleciona com uma String o mês, da data de nascimento.
	 * @param mes
	 * @return
	 */
	public PaginaCriarConta selecionaMes(String mes){
		if (!mes.equals("")) {
//			driver.findElement(By.xpath("//span[@id='BirthMonth']")).click();
			driver.findElement(By.id("BirthMonth")).click();
			driver.findElement(By.xpath("//label[@id='month-label']//div[contains(text(),'"+mes+"')]")).click();
		}
		LogUtils.info("Campo de selecionar o mês do aniversário selecionado com -> " +mes);
		return this;			
	}
	
	/**
	 * Retorna lista de elementos das mensagem de erro dos campos.
	 * @param msg
	 * @return
	 */
	public List<WebElement> retornaMensagensErro(String msg){
		List<WebElement> elements = driver.findElements(By.xpath("//span[@class='errormsg' and text()='"+msg+"']"));
		if (!elements.isEmpty()) {
			LogUtils.info("Mensagem de erro retornada com -> " +msg);			
		}
		return elements;
	}
	
	public boolean isMensagemErroApresentada(String msg){
		if (driver.findElements(By.xpath("//span[@class='errormsg' and text()='"+msg+"']")).size()==1) {
			LogUtils.info("Mensagem de erro retornada com -> " +msg);
			return true;
		}
		return false;
	}
	
	public String getMensagemErro(){
		return driver.findElement(By.xpath("//span[@class='errormsg' and contains(text(), ' ')]")).getText();
	}
	
	

}
