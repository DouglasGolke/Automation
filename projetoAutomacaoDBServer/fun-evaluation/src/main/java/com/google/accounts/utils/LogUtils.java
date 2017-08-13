package com.google.accounts.utils;

import com.google.accounts.testes.AbstractTeste;

/**
 * Solu��o simples para gerenciamento de log.
 * Essa solu��o n�o se aplica � testes paralelos, devido a estar usando m�todos est�ticos
 * Classe respons�vel por gerenciar os logs dos testes.
 */
public class LogUtils {
	 
	public static void info(String info){
		AbstractTeste.log.info(info);
		AbstractTeste.passos.add("INFO - "+info);
	}
	
	public static void error(String erro){
		AbstractTeste.log.error(erro);
		AbstractTeste.passos.add("ERRO - "+erro);
	}
	
	public static void exibeLog(){
		for(String passo : AbstractTeste.passos){
			System.out.println(passo);
		}
	}
}
