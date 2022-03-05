package batalha_de_Espetaculos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Salvar {
	
	private int aventureiro;
	private boolean [] vitorias = new boolean[5];
	private String caminho;
	
	public int SalvarDados (boolean [] etapa, int aventureiro, String Caminho) {
		this.caminho = Caminho;
		
		try {
			
			File file1 = new File(caminho + "res\\Save.txt");
			
			if(!file1.exists()){
				file1.createNewFile();
			}
			
			FileWriter caminhoTxt = new FileWriter(file1);
			PrintWriter salvarTxt = new PrintWriter(caminhoTxt);
			
			for(int i=0; i<5; i++) {
				salvarTxt.printf(etapa[i] == false ? "0" : "1");
			}
			salvarTxt.printf(aventureiro+"");
		    
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
			
			if(!file1.exists()){
				return -1;
			} else {
				
				BufferedReader buffRead = new BufferedReader(new FileReader(file1));
				String linha = buffRead.readLine();
				
				buffRead.close();
				
				if(linha == null) {
					return -1;
				}
				if(linha.matches("-?\\d+") == false) {
					return -4;
				}
				
				for(int i=0; i<5; i++) {
					
					if(Integer.parseInt(linha.substring(i, i+1)) == 0 || Integer.parseInt(linha.substring(i, i+1)) == 1) {
						vitorias[i] = (Integer.parseInt(linha.substring(i, i+1)) == 0 ? false : true);
					} else {
						return -3;
					}
				}
				
				if(Integer.parseInt(linha.substring(5, 6)) >= 0 && Integer.parseInt(linha.substring(5, 6)) < 6) {
					aventureiro = Integer.parseInt(linha.substring(5, 6));
				} else {
					return -3;
				}
				
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
		
		if(!file1.exists()){
			file1.delete();
		}
	}

	
	public int getAventureiro() {
		return aventureiro;
	}
	
	public boolean[] getVitorias() {
		return vitorias;
	}

	
}
