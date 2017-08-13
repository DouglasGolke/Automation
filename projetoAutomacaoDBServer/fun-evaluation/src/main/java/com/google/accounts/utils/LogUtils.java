package com.google.accounts.utils;

import com.google.accounts.testes.AbstractTeste;

/**
 * Solução simples para gerenciamento de log.
 * Essa solução não se aplica à testes paralelos, devido a estar usando métodos estáticos
 * Classe responsável por gerenciar os logs dos testes.
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
