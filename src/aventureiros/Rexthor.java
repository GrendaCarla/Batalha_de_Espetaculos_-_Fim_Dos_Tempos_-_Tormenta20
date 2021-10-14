package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Rexthor {
	//                                 apelo,              interferencia,           tipo interferencia (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] valores = {{6,6,6,6,6,6,6}, {2, 0, 3, 0, 3, 10, 1}, {2, -1, 5, -1, 0, 6, 1}, {1, 1, 1, 0, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3"};
	private String [] NomeApelos = {"Punho da dor", "Esquiva", "Briga", "Sentidos aguçados", "Besuntar", "Sorte das deusas", "A tanga"};
	private String [][] ConteudoDescricao = {  {"Ao se posicionar e contrair seus músculos, um poderoso gancho é disparado rumo ao", "adversário adjacente.", " ", " ", "Este poder afeta o oponente acima de você."},
											   {"Uma excelente demonstração de reflexos em combate digna de um boxeador de primeira.", " ", " ", " ", " "},
											   {"Quebra pau, peleja, arranca-rabo, dar uma coça, sururu, pancadaria, barraco, rixa, pugna", "e testilha.", " ", " ", "Este poder afeta um oponente acima e abaixo."},
											   {"Cubra seus olhos e concentre-se no ambiente a sua volta, com apenas o seus sentidos" ,"sobreviva a onda de ataques.", " ", " ", " "},
											   {"Em tempos seco como este é sempre bom dar uma hidratada, besunte-se com baby óleo", "e deixe sua matilha enojada.", " ", " ", "Este poder afeta todos acima de você."},
											   {"Você fará uma aposta, jogue cinco d20 e torça para o número 20 sair, os resultados não se", "repetiram nos outros dados, então se você ganhar o apelo será 10, se você perder será zero.", "Tentador?", " ", " "},
											   {"Sua tanga guarda vários objetos que muitas vezes não são seus. Retire da suas vestes itens", "relevantes para seus companheiros e distraia-los do combate.", " ", " ", "Este poder afeta todos abaixo de você."}};	
	
	private String [][] ConteudoEscolhaAdversario = {{"Estou vendo na sua cara que você esta querendo arranjar briga.", "Quer vim pro fight?", " ", " "},
			   										 {"Então é melhor você ficar esperto.", " ", " ", " "},
			   										 {"Seguinte, vamos fazer uma aposta.", "Vou jogar uma moeda, se você ganhar eu luto com você, mas se eu ganhar você vai ter que", "carregar esses sacos para mim, fechado?", " "},
			   										 {"Então, coroa ou sem coroa?", " ", " ", " "},
			   										 {"Ok, você venceu dessa vez, mas não vai se achando não rapa.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
			   										 {"HA! as deusas estão do meu lado mermão.", "Pode começar levando esses sacos aqui.", " ", " "},
			   										 {" ", "Achei você. Então, vamos começar com a minha revanche?", " ", " "}};

	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Rexthor() {
		sorteio();
	}
	
	public void sorteio () {
		for(int i=0; i<7; i++) {
	        mylist.add(i);
		}
		Collections.shuffle(mylist);
		
	}
	
	public int[][] getValores() {
		
		int[][] provisorio = {{valores[0][mylist.get(0)], valores[0][mylist.get(1)], valores[0][mylist.get(2)], valores[0][mylist.get(3)]},
		 		  {valores[1][mylist.get(0)], valores[1][mylist.get(1)], valores[1][mylist.get(2)], valores[1][mylist.get(3)]},
				  {valores[2][mylist.get(0)], valores[2][mylist.get(1)], valores[2][mylist.get(2)], valores[2][mylist.get(3)]},
				  {valores[3][mylist.get(0)], valores[3][mylist.get(1)], valores[3][mylist.get(2)], valores[3][mylist.get(3)]},
				 };
		
		return provisorio;
	}
	public String[] getGifApelos() {
		
		String[] provisorio = {gifApelos[mylist.get(0)], gifApelos[mylist.get(1)], gifApelos[mylist.get(2)], gifApelos[mylist.get(3)]};
		return provisorio;
	}
	public String[] getNomeApelos() {
		
		String[] provisorio = {NomeApelos[mylist.get(0)], NomeApelos[mylist.get(1)], NomeApelos[mylist.get(2)], NomeApelos[mylist.get(3)]};
		return provisorio;
	}
	public String[] getConteudoDescricao(int num) {
		
		String[][] provisorio = {ConteudoDescricao[mylist.get(0)], ConteudoDescricao[mylist.get(1)], ConteudoDescricao[mylist.get(2)], ConteudoDescricao[mylist.get(3)]};
		
		return provisorio[num];
	}
	
	public String[] getApelosEInterferencias() {
		
		String[] provisorio = {apelosEInterferencias[mylist.get(0)], apelosEInterferencias[mylist.get(1)], apelosEInterferencias[mylist.get(2)], apelosEInterferencias[mylist.get(3)]};
		return provisorio;
	}

	public String[][] getConteudoEscolhaAdversario() {
		return ConteudoEscolhaAdversario;
	}
	
}

