package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Rexthor {
	//                                   apelo,                interfer�ncia,          tipo interfer�ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{4, 3, 6, 4, 4, 0, 1}, {3, 0, 2, 0, 2, 10, 4}, {2, -1, 5, -1, 0, 6, 1}, {1, 1, 1, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7};
	private String [] NomeApelos = {"Gancho da Dor", "Esquiva", "Briga", "Sentidos Agu�ados", "Besuntar", "Sorte das Deusas", "A Tanga"};
	private String [][] ConteudoDescricao = {  {"De m�sculos contra�dos", "Um gancho � disparado.", "Um rosto � re-esculpido", "Em um corpo desmaiado.", "Esta habilidade afeta o campo acima de voc�."},
											   {"Golpes s�o projetados", "Em sua dire��o.", "Com movimentos calculados", "Voc� esquiva em demonstra��o.", " "},
											   {"Agress�o, barraco, conflito,", "Peleja, refrega, selvageria,", "Confronto, batalha, atrito,", "Sururu, requesta, pancadaria.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Com seus olhos encobertos", "Se concentre no ambiente,", "Com seus sentidos despertos", "Sobreviva aos ataques subsequentes.", " "},
											   {"Quando o calor j� te confunde", "� bom dar uma hidratada,", "Com baby �leo se besunte", "Deixando sua matilha enjoada.", "Esta habilidade afeta todos campos acima de voc�."},
											   {"Jogando cinco d20 uma aposta voc� vai fazer.", "N�o h� repeti��o nos n�meros contemplados.", "10 apelos voc� ganha se 20 o dado conceber.", "Ent�o camarada, voc� est� tentado?", "Esta habilidade te concede 10 apelos se o n�mero 20 sair em um dos dados."},
											   {"Sua tanga guarda objetos", "Que nem sempre s�o sua propriedade,", "Uma vez das vestes liberto", "O dono cai para insanidade.", "Esta habilidade afeta todos os campos abaixo de voc�."}};
	
	private String [][] ConteudoEscolhaAdversario = {{"Estou vendo na sua cara que voc� est� querendo arranjar briga.", "Quer vim pro fight?", " ", " "},
			   										 {"� melhor voc� ficar esperto mesmo.", " ", " ", " "},
			   										 {"Seguinte, que tal fazermos uma aposta?", "Vou jogar uma moeda, se voc� ganhar eu luto com voc�, mas se eu ganhar voc� vai ter que", "carregar esses sacos para mim, fechado?", " "},
			   										 {"Ent�o, coroa ou sem coroa?", " ", " ", " "},
			   										 {"Ok, voc� venceu dessa vez, mas n�o vai ficar se achando.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
			   										 {"HA! As deusas est�o do meu lado merm�o.", "Pode come�ar levando esses sacos aqui.", " ", " "},
			   										 {"Ai esta voc�. Ent�o, vamos come�ar logo com a minha revanche?", "Porque eu tenho hora pra limpar esse ch�o com a sua cara.", " ", " "},
	// 7� linha --------------------- teve o primeiro contato mas n�o batalhou = |1|0|0|0|0| -------------------
			   										 {"teve o primeiro contato mas n�o batalhou", " ", " ", " "},
	// ------------------------------ desistiu no meio da �ltima luta = |1|0|0|1|3| -------------------
			   										 {"desistiuno no meio da �ltima luta", " ", " ", " "},
	// ------------------------------ perdeu na �ltima luta = |1|0|1|0|2| -------------------
			   										 {"perdeu na �ltima luta", " ", " ", " "},
	// ------------------------------ 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
			   										 {"1� vit�ria na �ltima luta", " ", " ", " "},
	// ------------------------------ perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
			   										 {"perdeu na �ltima luta, mas tem 1 vit�ria", " ", " ", " "},
	// ------------------------------ 2� vit�ria na �ltima luta com 3 ou menos derrotas = |1|2|3|0|1| -------------------
			   										 {"2� vit�ria na �ltima luta com menos de 3 derrotas", " ", " ", " "},
	// ------------------------------ 2� vit�ria na �ltima luta com mais de 3 derrotas = |1|2|4|0|1| -------------------
			   										 {"2� vit�ria na �ltima luta com mais de 3 derrotas", " ", " ", " "},
	// ------------------------------ derrota na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
			   										 {"derrota na �ltima luta com 2 vit�rias", " ", " ", " "},
	// ------------------------------ 3� ou mais vit�rias na �ltima luta = |1|3|0|0|1| -------------------
			   										 {"3� ou mais vit�rias na �ltima luta", " ", " ", " "},
	// ------------------------------ derrota na �ltima luta com 3 ou mais vit�rias = |1|3|1|0|2| -------------------
			   										 {"derrota na �ltima luta com 3 ou mais vit�rias", " ", " ", " "}};
	
	
	
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