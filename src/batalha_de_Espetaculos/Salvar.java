package batalha_de_Espetaculos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Salvar {
	
	private int aventureiro;
	private int [][] tabelaInteracao = new int[5][5];
	private String caminho;
	private int velocidade = 2;
	
	public int SalvarDados (int [][] tabela, int aventureiro, String Caminho, int velocidade) {
		this.caminho = Caminho;
		
		try {
			
			File file1 = new File(caminho + "res\\Save.txt");
			
			//se o arquivo de save não existir ele cria um
			if(!file1.exists()){
				file1.createNewFile();
			}
			
			FileWriter caminhoTxt = new FileWriter(file1);
			PrintWriter salvarTxt = new PrintWriter(caminhoTxt);
			
			salvarTxt.println(aventureiro+"");
			

			for(int lin=0; lin<5; lin++) {
					
				for(int col=0; col<5; col++) {
					if(col != 4) {
						salvarTxt.printf(tabela[lin][col] + "");
					} else {
						salvarTxt.println(tabela[lin][col] + "");
					}
				}
			}
			
			salvarTxt.println(velocidade+"");
			
		    salvarTxt.close();
		    
		    return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	   
	}
		
	public int LerDados(String Caminho) {
		this.caminho = Caminho;
			
		try {
			File file1 = new File(caminho + "res\\Save.txt");
			
			//se o arquivo de save não existir
			if(!file1.exists()){
				return -1;
			} else {
				
				BufferedReader buffRead = new BufferedReader(new FileReader(file1));
				String linha = buffRead.readLine();
				
				//se não tiver nada no arquivo
				if(linha == null) {
					return -1;
				}
				
				//se tiver algum caracter não numerico
				if(linha.matches("-?\\d+") == false) {
					return -4;
				}
				
				//ve qual aventureiro foi escolido anteriormente, se não o resultado é -1
				if(Integer.parseInt(linha) >= 0 && Integer.parseInt(linha) <= 4) {
					aventureiro = Integer.parseInt(linha);
					
				} else {
					return -3;
				}
				
				//pega os valores das linhas e colunas.
				
				/* 			 primeira| vitórias | derrotas | abandono | ultimo resutado			   |
				 * ignis	| 0 ou 1 |   >=0	|   >=0	   |   >=0	  | 0=nao, 1=vit, 2=der, 3=aba |
				 * ayla		|		 |			|		   |		  |							   |
				 * rexthor	|		 |			|		   |		  |							   |
				 * kiki		|		 |			|		   |		  |							   |
				 * arius	|		 |			|		   |		  |							   |
				 */
				
				for(int lin=0; lin<5; lin++) {

					linha = buffRead.readLine();
					
					if(linha.length() == 5) {
						
						for(int col=0; col<5; col++) {
							
							if(col == 0 && (Integer.parseInt(linha.substring(0, 1)) == 0 || Integer.parseInt(linha.substring(0, 1)) == 1)) {
								tabelaInteracao[lin][col] = Integer.parseInt(linha.substring(0, 1));
								
							} else if(col != 0 && Integer.parseInt(linha.substring(col, col+1)) >= 0) {
								tabelaInteracao[lin][col] = Integer.parseInt(linha.substring(col, col+1));
							
							} else {
								return -3;
							}
						}
					} else {
						return -3;
					}
				}
				
				linha = buffRead.readLine();
				
				//pega velocidade
				if(Integer.parseInt(linha) >= 1 && Integer.parseInt(linha) <= 5) {
					velocidade = Integer.parseInt(linha);
					
				} else {
					return -3;
				}
				
				buffRead.close();
				
				return 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			return -2;
		}
	}
	
	public void ApagarDados (String Caminho) {
		this.caminho = Caminho;
		
		File file1 = new File(caminho + "res\\Save.txt");
		
		if(file1.exists()){
			file1.delete();
		}
	}

	
	public int getAventureiro() {
		return aventureiro;
	}
	
	public int[][] getTabelaInteracao() {
		return tabelaInteracao;
	}
	
	public int getVelocidade() {
		return velocidade;
	}

	
}