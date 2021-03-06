package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Arius {
	//                                   apelo,                interfer?ncia,          tipo interfer?ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{3, 0, 3, 5, 1, 0, 5, 5}, {2, 0, 1, 0, 5, 10, 0, 0}, {2, 4, 0, -1, 3, 6, -1, -1}, {1, 0, 0, 1, 0, 0, 1, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Chifrada", "Estrat?gia em Combate", "O Mamilo Rosa", "Olimp?ada Artoniana", "Diplomacia", "5 ? 20", "Gl?dio e Escudo", "Declamar Poema"};
	private String [][] ConteudoDescricao = {  {"Com teus cornos perfure a tora resistente.", "Com tua for?a taurina erga-a no ar.", "O mais longe que puder lan?e-a na tua frente,", "Ent?o tor?a para o tronco ningu?m acertar.", "Esta habilidade afeta o campo acima de voc?."},
											   {"Somente atrav?s da viol?ncia a vit?ria n?o adv?m.", "Ao compreender aqueles que acima est?o", "Vantagem poder? ganhar tamb?m", "Ao ver seus movimentos com antecipa??o.", "Esta habilidade zera as interfer?ncias ganhadas antes da sua a??o nessa rodada."},
											   {"Ao tirar sua coura?a em meio ao espet?culo", "Todos podem v?-lo e ? imposs?vel de desviar.", "Por mais que seja um ?rg?o prosaico", "O ?nico mamilo rosa consegue a todos hipnotizar.", "Esta habilidade afeta todos os campos acima de voc?."},
											   {"Acenda sua pira do esp?rito esportivo", "Participando de diversos jogos consecutivos", "Como salto pela pali?ada, flechada nos irm?os,", "Esconde-esconde com baratas e arremesso com explos?o.", " "},
											   {"Use a per?cia diplom?tica na audi?ncia", "Para que possa com ela argumentar e convencer", "Que o primeiro colocado deve apresentar a impon?ncia", "Em sua demonstra??o, o que dessa vez n?o conseguiu fazer.", "Esta habilidade afeta o primeiro campo."},
											   {"Jogando cinco d20 uma aposta voc? vai fazer.", "N?o h? repeti??o nos n?meros contemplados.", "10 apelos voc? ganha se 5 o dado conceber.", "Ent?o camarada, voc? est? tentado?", "Esta habilidade te concede 10 apelos se o n?mero 5 sair em um dos dados."},
											   {"Lembre-se do seu treinamento nas legi?es do imp?rio.", "Com o gl?dio em m?os e o escudo bem posicionado", "Demonstre suas t?ticas e o conhecimento b?lico", "Evitando se queimar caso for derrubado.", " "},
											   {"Se quiseres emocionar a plateia", "Ter?s que revelar seus sentimentos dolorosos.", "Recita-los em forma de prosa po?tica", "Parece certamente um dos modos mais graciosos.", " "}};
	
	// ------------------------------ primeira intera??o ---------------------------------
	private String [][] ConteudoEscolhaAdversario = {{"Sauda??es andarilho, gostaria de falar comigo?", "Por acaso voc? tamb?m segue os preceitos de Tanna-Toh e veio para um debate sobre as", "maravilhas da...", "N?o?"},
													{"Voc? esta querendo me desafiar para uma disputa amistosa onde demonstraremos nossas", "habilidades?", " ", " "},
													{"Isso ? ?timo, obviamente ser? uma boa maneira de adquirir novos conhecimentos, como mestre", "Luriel me dizia 'a pr?tica e a observa??o de t?cnicas de outrem contribui muito para o", "aprimoramento... bl?, bl?, bl?'.", "As apresenta??es SEM interfer?ncia ganhar?o +1 de apelo."},
													{"Bem, eu estava indo mostrar a nada-mais-que-a-verdade a maravilhosa planta??o de gorad que", "os Gallobalt cultivaram aqui nas centrais, sinta-se ? vontade para acompanhar-nos durante esse", "tempo. Poderia nos contar sobre os diversos lugares em que esteve? ", "Creio que nada-mais-que-a-verdade se interessar? bastante no que tem a dizer."},
													{"Sauda??es, voc? esta querendo me desafiar para uma disputa amistosa novamente?", " ", " ", " "},
	// 5? linha --------------------- teve o primeiro contato mas n?o batalhou = |1|0|0|0|0| -------------------
													{"teve o primeiro contato mas n?o batalhou", " ", " ", " "},
    // ------------------------------ desistiu no meio da ?ltima luta = |1|0|0|1|3| -------------------
													{"desistiuno no meio da ?ltima luta", " ", " ", " "},
	// ------------------------------ perdeu na ?ltima luta = |1|0|1|0|2| -------------------
													{"perdeu na ?ltima luta", " ", " ", " "},
	// ------------------------------ 1? vit?ria na ?ltima luta = |1|1|0|0|1| -------------------
													{"1? vit?ria na ?ltima luta", " ", " ", " "},
	// ------------------------------ perdeu na ?ltima luta, mas tem 1 vit?ria = |1|1|1|0|2| -------------------
													{"perdeu na ?ltima luta, mas tem 1 vit?ria", " ", " ", " "},
	// ------------------------------ 2? vit?ria na ?ltima luta com 3 ou menos derrotas = |1|2|3|0|1| -------------------
													{"2? vit?ria na ?ltima luta com menos de 3 derrotas", " ", " ", " "},
	// ------------------------------ 2? vit?ria na ?ltima luta com mais de 3 derrotas = |1|2|4|0|1| -------------------
													{"2? vit?ria na ?ltima luta com mais de 3 derrotas", " ", " ", " "},
	// ------------------------------ derrota na ?ltima luta com 2 vit?rias = |1|2|1|0|2| -------------------
													{"derrota na ?ltima luta com 2 vit?rias", " ", " ", " "},
	// ------------------------------ 3? ou mais vit?rias na ?ltima luta = |1|3|0|0|1| -------------------
													{"3? ou mais vit?rias na ?ltima luta", " ", " ", " "},
	// ------------------------------ derrota na ?ltima luta com 3 ou mais vit?rias = |1|3|1|0|2| -------------------
													{"derrota na ?ltima luta com 3 ou mais vit?rias", " ", " ", " "}};


	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Arius() {
		sorteio();
	}
	
	public void sorteio () {
		/*for(int i=0; i<8; i++) {
	        mylist.add(i);
		}*/
		
		mylist.add(2); mylist.add(5); mylist.add(6); mylist.add(3);
		
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