package com.google.accounts.testes;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.accounts.PaginaCriarConta;
import com.google.accounts.utils.ExcelUtils;
import com.google.accounts.utils.LogUtils;

/**
 * Essa classe � respos�vel por organizar o cen�rio de cadastro de usu�rios e seus casos de teste.
 */
public class CadastroUsuario extends AbstractTeste {

	/**
	 * Busca os dados no excel e insere no teste
	 */
	@DataProvider(name = "novoUserNOK")
	public static Object[][] dataProviderPadrao(Method m) {
		Object[][] dados;
		try {
			dados = ExcelUtils.carregaDados(m.getName());
			System.out.println(dados.length);
			return dados;
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel abrir os dados do teste " + m.getName() + " - " + e.getStackTrace());
		}
		return null;
	}
	
	@Override
	@BeforeMethod
	public void beforeMethod(Method m){
		super.beforeMethod(m);
		log = Logger.getLogger(this.getClass().getSimpleName());
		LogUtils.info("CEN�RIO DE TESTE: "+this.getClass().getSimpleName());
		LogUtils.info("CASO DE TESTE: "+m.getName());
	}
	
	@Test(dataProvider = "novoUserNOK")
	public void novoUserNOK(String nome, String sobrenome, String nomeUser, String senha, String confirmaSenha,
			String diaNasc, String mesNasc, String anoNasc, String sexo, String celular, String emailAtual, String mensagemErro, String qtd, String uid){
		
		PaginaCriarConta conta = new PaginaCriarConta(driver);
		
		conta.prencheCampoNome(nome)			
		.prencheCampoSobrenome(sobrenome);	
		
		if (!nomeUser.isEmpty()) {
			if (uid.equals("n")) {
				conta.preencheNomeEmailUser(nomeUser);				
			} else {
				conta.preencheNomeEmailUserUnico(nomeUser);
			}
		}
		
		conta.preencheSenha(senha)		
		.preencheConfirmaSenha(confirmaSenha)	
		.preencheDiaNascimento(diaNasc)
		.selecionaMes(mesNasc)
		.preencheAnoNascimento(anoNasc)
		.selecionaSexo(sexo)		
		.preencheCelular(celular)		
		.preencheEmailAtual(emailAtual)
		.clicaProximaEtapa();
		
		Assert.assertTrue(conta.retornaMensagensErro(mensagemErro).size()==Integer.parseInt(qtd));
		
	}
	
	@Test
	public void CadastroSemSenha(){
				
		PaginaCriarConta conta = new PaginaCriarConta(driver);
		
		conta.prencheCampoNome("Douglas")
		.prencheCampoSobrenome("Golke")
		.preencheNomeEmailUserUnico("douglas")
		.preencheConfirmaSenha("SenhaDeConfirmacao123")
		.preencheDiaNascimento("01")
		.selecionaMes("Janeiro")
		.preencheAnoNascimento("1985")
		.selecionaSexo("Masculino")
		.preencheCelular("51997435572")
		.preencheEmailAtual("douglas_golke@hotmail.com")
		.clicaProximaEtapa();
		
		// Poderia ser assim a valida��o \/
//		Assert.assertTrue(conta.isMensagemErroApresentada(PaginaCriarConta.VOCE_NAO_PODE_DEIXAR_ESSE_CAMPO_EM_BRANCO));
		// OU \/
		Assert.assertTrue(conta.isMensagemErroApresentada("Voc� n�o pode deixar este campo em branco."));
		Assert.assertTrue(conta.isMensagemErroApresentada("Essas senhas n�o coincidem. Tentar novamente?"));
	}
	
	@Test
	public void CadastroComCelularInvalido(){
				
		PaginaCriarConta conta = new PaginaCriarConta(driver);
		
		conta.prencheCampoNome("Douglas")
		.prencheCampoSobrenome("Golke")
		.preencheNomeEmailUserUnico("douglas")
		.preencheSenha("SenhaDeConfirmacao123")
		.preencheConfirmaSenha("SenhaDeConfirmacao123")
		.preencheDiaNascimento("01")
		.selecionaMes("Janeiro")
		.preencheAnoNascimento("1985")
		.selecionaSexo("Masculino")
		.preencheCelular("1111111111")
		.preencheEmailAtual("douglas_golke@hotmail.com")
		.clicaProximaEtapa();
		
		Assert.assertTrue(conta.retornaMensagensErro("Este formato de n�mero de telefone n�o � v�lido. Verifique o pa�s e o n�mero.").size()==1);
		// OU \/
		Assert.assertEquals(conta.getMensagemErro(), "Este formato de n�mero de telefone n�o � v�lido. Verifique o pa�s e o n�mero.");
		// OU \/
		Assert.assertTrue(conta.isMensagemErroApresentada("Este formato de n�mero de telefone n�o � v�lido. Verifique o pa�s e o n�mero."));
	}
	
	@Test
	public void CadastroComUserCorreto(){
				
		PaginaCriarConta conta = new PaginaCriarConta(driver);
		
		conta.prencheCampoNome("Douglas")
		.prencheCampoSobrenome("Golke")
		.preencheNomeEmailUserUnico("douglas")
		.preencheSenha("SenhaDeConfirmacao123")
		.preencheConfirmaSenha("SenhaDeConfirmacao123")
		.preencheDiaNascimento("01")
		.selecionaMes("Julho")
		.preencheAnoNascimento("1985")
		.selecionaSexo("Masculino")
		.preencheCelular("51997435572")
		.preencheEmailAtual("douglas_golke@hotmail.com")
		.clicaProximaEtapa();
		
		Assert.assertTrue(conta.retornaMensagensErro("").size()==0);
	}
	
	@Test
	public void CadastroComEmailAtualInvalido(){
				
		PaginaCriarConta conta = new PaginaCriarConta(driver);
		
		conta.prencheCampoNome("Douglas")
		.prencheCampoSobrenome("Golke")
		.preencheNomeEmailUserUnico("douglas")
		.preencheSenha("SenhaDeConfirmacao123")
		.preencheConfirmaSenha("SenhaDeConfirmacao123")
		.preencheDiaNascimento("01")
		.selecionaMes("Julho")
		.preencheAnoNascimento("1985")
		.selecionaSexo("Masculino")
		.preencheCelular("51997435572")
		.preencheEmailAtual("12")
		.clicaProximaEtapa();
		
		Assert.assertTrue(conta.retornaMensagensErro("Digite seu endere�o de e-mail completo, incluindo o \"@\".").size()==1);
	}
	
	@Test
	public void CadastroDeEmailDeUsuarioInvalido(){
				
		PaginaCriarConta conta = new PaginaCriarConta(driver);
		
		conta.prencheCampoNome("Douglas")
		.prencheCampoSobrenome("Golke")
		.preencheNomeEmailUser("#11111")
		.preencheSenha("SenhaDeConfirmacao123")
		.preencheConfirmaSenha("SenhaDeConfirmacao123")
		.preencheDiaNascimento("01")
		.selecionaMes("Julho")
		.preencheAnoNascimento("1985")
		.selecionaSexo("Masculino")
		.preencheCelular("51997435572")
		.preencheEmailAtual("douglas_golke@hotmail.com")
		.clicaProximaEtapa();
		
		Assert.assertTrue(conta.retornaMensagensErro("Use apenas letras (a-z), n�meros e pontos.").size()==1);
	}
	
	@Test
	public void CadastroDeDiaNascInvalido(){
				
		PaginaCriarConta conta = new PaginaCriarConta(driver);
		
		conta.prencheCampoNome("Douglas")
		.prencheCampoSobrenome("Golke")
		.preencheNomeEmailUserUnico("douglas")
		.preencheSenha("SenhaDeConfirmacao123")
		.preencheConfirmaSenha("SenhaDeConfirmacao123")
		.preencheDiaNascimento("31")
		.selecionaMes("Fevereiro")
		.preencheAnoNascimento("1985")
		.selecionaSexo("Masculino")
		.preencheCelular("51997435572")
		.preencheEmailAtual("douglas_golke@hotmail.com")
		.clicaProximaEtapa();
		
		Assert.assertTrue(conta.retornaMensagensErro("O dia parece estar incorreto. Use um n�mero de dois d�gitos que corresponda a um dia do m�s.").size()==1);
	}
	
	@Override
	@AfterMethod
	public void afterMethod(){
		super.afterMethod();
	}
}
