package com.google.accounts.utils;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Classe que busca em uma planilha do excel, os dados que serão utilizados nos testes.
 * @author DouglasGolke
 *
 */
public class ExcelUtils {

	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String PATH_arquivosDeTeste = new File(
			"src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR + "arquivosTeste").getAbsolutePath()
			+ SEPARATOR;

	private static int linhaInicial = 1;
	private static int colunaInicial = 0;

	public static Object[][] carregaDados(String nomeArquivo) throws BiffException, IOException {

		Workbook workbook = Workbook.getWorkbook(new File(PATH_arquivosDeTeste + nomeArquivo + ".xls"));
		Sheet sheet = workbook.getSheet(0);
		int linhas = sheet.getRows();
		int colunas = 50;
		int i = 0;
		String[][] dados = null;

		for (int j = colunaInicial; j < colunas; j++) {
			try {
				Cell dado = sheet.getCell(j, i);
				dado.getContents().length();
			} catch (Exception e) {
				dados = new String[linhas - 1][j];
				break;
			}
		}

		int l = 0;
		for (i = linhaInicial; i < linhas; i++, l++) {
			for (int j = colunaInicial; j < colunas; j++) {
				String temp = "";
				try {
					Cell dado = sheet.getCell(j, i);
					temp = dado.getContents();
				} catch (Exception e) {
					colunas = j;
					break;
				}
				dados[l][j] = temp;
			}
		}
		return dados;
	}

}
