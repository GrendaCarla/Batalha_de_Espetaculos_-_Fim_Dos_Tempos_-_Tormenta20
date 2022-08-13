package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Rexthor {
	//                                   apelo,                interferência,          tipo interferência (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{4, 3, 6, 4, 4, 0, 1}, {3, 0, 2, 0, 2, 10, 4}, {2, -1, 5, -1, 0, 6, 1}, {1, 1, 1, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7};
	private String [] NomeApelos = {"Gancho da Dor", "Esquiva", "Briga", "Sentidos Aguçados", "Besuntar", "Sorte das Deusas", "A Tanga"};
	private String [][] ConteudoDescricao = {  {"De músculos contraídos", "Um gancho é disparado.", "Um rosto é re-esculpido", "Em um corpo desmaiado.", "Esta habilidade afeta o campo acima de você."},
											   {"Golpes são projetados", "Em sua direção.", "Com movimentos calculados", "Você esquiva em demonstração.", " "},
											   {"Agressão, barraco, conflito,", "Peleja, refrega, selvageria,", "Confronto, batalha, atrito,", "Sururu, requesta, pancadaria.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Com seus olhos encobertos", "Se concentre no ambiente,", "Com seus sentidos despertos", "Sobreviva aos ataques subsequentes.", " "},
											   {"Quando o calor já te confunde", "É bom dar uma hidratada,", "Com baby óleo se besunte", "Deixando sua matilha enjoada.", "Esta habilidade afeta todos campos acima de você."},
											   {"Jogando cinco d20 uma aposta você vai fazer.", "Não há repetição nos números contemplados.", "10 apelos você ganha se 20 o dado conceber.", "Então camarada, você está tentado?", "Esta habilidade te concede 10 apelos se o número 20 sair em um dos dados."},
											   {"Sua tanga guarda objetos", "Que nem sempre são sua propriedade,", "Uma vez das vestes liberto", "O dono cai para insanidade.", "Esta habilidade afeta todos os campos abaixo de você."}};
	
	private String [][] ConteudoEscolhaAdversario = {{"Estou vendo na sua cara que você está querendo arranjar briga.", "Quer vim pro fight?", " ", " "},
			   										 {"É melhor você ficar esperto mesmo.", " ", " ", " "},
			   										 {"Seguinte, que tal fazermos uma aposta?", "Vou jogar uma moeda, se você ganhar eu luto com você, mas se eu ganhar você vai ter que", "carregar esses sacos para mim, fechado?", " "},
			   										 {"Então, coroa ou sem coroa?", " ", " ", " "},
			   										 {"Ok, você venceu dessa vez, mas não vai ficar se achando.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
			   										 {"HA! As deusas estão do meu lado mermão.", "Pode começar levando esses sacos aqui.", " ", " "},
			   										 {"Ai esta você. Então, vamos começar logo com a minha revanche?", "Porque eu tenho hora pra limpar esse chão com a sua cara.", " ", " "},
	// 7º linha --------------------- teve o primeiro contato mas não batalhou = |1|0|0|0|0| -------------------
			   										 {"teve o primeiro contato mas não batalhou", " ", " ", " "},
	// ------------------------------ desistiu no meio da última luta = |1|0|0|1|3| -------------------
			   										 {"desistiuno no meio da última luta", " ", " ", " "},
	// ------------------------------ perdeu na última luta = |1|0|1|0|2| -------------------
			   										 {"perdeu na última luta", " ", " ", " "},
	// ------------------------------ 1º vitória na última luta = |1|1|0|0|1| -------------------
			   										 {"1º vitória na última luta", " ", " ", " "},
	// ------------------------------ perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
			   										 {"perdeu na última luta, mas tem 1 vitória", " ", " ", " "},
	// ------------------------------ 2º vitória na última luta com 3 ou menos derrotas = |1|2|3|0|1| -------------------
			   										 {"2º vitória na última luta com menos de 3 derrotas", " ", " ", " "},
	// ------------------------------ 2º vitória na última luta com mais de 3 derrotas = |1|2|4|0|1| -------------------
			   										 {"2º vitória na última luta com mais de 3 derrotas", " ", " ", " "},
	// ------------------------------ derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
			   										 {"derrota na última luta com 2 vitórias", " ", " ", " "},
	// ------------------------------ 3º ou mais vitórias na última luta = |1|3|0|0|1| -------------------
			   										 {"3º ou mais vitórias na última luta", " ", " ", " "},
	// ------------------------------ derrota na última luta com 3 ou mais vitórias = |1|3|1|0|2| -------------------
			   										 {"derrota na última luta com 3 ou mais vitórias", " ", " ", " "}};
	
	
	
	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Rexthor() {
		sorteio();
	}
	
	public void sorteio () {
		/*for(int i=0; i<7; i++) {
	        mylist.add(i);
		}*/
		
		mylist.add(0); mylist.add(2); mylist.add(5); mylist.add(6);
		
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
	
	public String[] getNomeApelos() {
		
		String[] provisorio = {NomeApelos[mylist.get(0)], NomeApelos[mylist.get(1)], NomeApelos[mylist.get(2)], NomeApelos[mylist.get(3)]};
		return provisorio;
	}
	
	public String[] getConteudoDescricao(int num) {
		
		String[][] provisorio = {ConteudoDescricao[mylist.get(0)], ConteudoDescricao[mylist.get(1)], ConteudoDescricao[mylist.get(2)], ConteudoDescricao[mylist.get(3)]};
		return provisorio[num];
	}
	
	public int[] getGifApelos() {
		
		int[] provisorio = {gifApelos[mylist.get(0)], gifApelos[mylist.get(1)], gifApelos[mylist.get(2)], gifApelos[mylist.get(3)]};
		return provisorio;
	}

	public String[][] getConteudoEscolhaAdversario() {
		return ConteudoEscolhaAdversario;
	}
	
}