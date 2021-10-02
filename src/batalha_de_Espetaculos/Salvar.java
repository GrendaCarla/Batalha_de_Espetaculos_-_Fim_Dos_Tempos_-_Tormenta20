package batalha_de_Espetaculos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Salvar {
	
	public String SalvarDados (boolean [] etapa) {
		
		try {
			
			File file1 = new File("src\\batalha_de_Espetaculos\\Save.txt");
			
			if(!file1.exists()){
				file1.createNewFile();
			}
			
			FileWriter caminhoTxt = new FileWriter(file1);
			PrintWriter salvarTxt = new PrintWriter(caminhoTxt);
			
			for(int i=0; i<5; i++) {
				salvarTxt.printf(etapa[i] == false ? "0" : "1");
			}
		    
		    salvarTxt.close();
		    
		    return "Jogo salvo!";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro ao salvar!";
		}
	   
	}
}
