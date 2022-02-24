package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Arius {
	//                                   apelo,                interferencia,          tipo interferencia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{4, 2, 5, 6, 3, 0, 2, 4}, {3, 0, 3, 0, 4, 10, 0, 0}, {2, 4, 5, -1, 3, 6, -1, -1}, {1, 0, 0, 0, 0, 0, 1, 1}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3", "apelo4"};
	private String [] NomeApelos = {"Chifrada", "Estratégia em Combate", "O Mamilo Rosa", "Olimpíadas das Centrais", "Diplomacia", "5 é 20", "Gládio e escudo", "Declamar Poema"};
	private String [][] ConteudoDescricao = {  {"Com teus cornos perfure a tora resistente.", "Com tua força taurina erga-a no ar.", "O mais longe que puder lançe-a na tua frente,", "Então torça para o tronco ninguém acertar.", "Esta habilidade afeta o campo acima de você."},
											   {"Somente através da violência a vitória não advêm.", "Ao compreender aqueles que acima estão", "Vantagem poderá ganhar também", "Ao ver seus movimentos com antecipação.", "Esta habilidade zera as interferências ganhadas antes da sua ação nessa rodada."},
											   {"Ao tirar sua couraça em meio ao espetáculo", "Todos podem vê-lo e é impossível de desviar.", "Por mais que seja um órgão prosaico", "O único mamilo rosa consegue a todos hipnotizar.", "Esta habilidade afeta um campo acima e um abaixo de você."},
											   {"Acenda sua pira do espírito esportivo", "Participando de diversos jogos consecutivos", "Como salto pela paliçada, flechada nos irmãos,", "Esconde-esconde com baratas e arremesso com explosão.", " "},
											   {"Use a perícia diplomática na audiência", "Para que possa com ela argumentar e convencer", "Que o primeiro colocado deve apresentar a imponência", "Em sua demonstração, o que dessa vez não conseguiu fazer.", "Esta habilidade afeta o primeiro campo."},
											   {"Jogando cinco d20 uma aposta você vai fazer.", "Não há repetição nos números contemplados.", "10 apelos você ganha se 5 o dado conceber.", "Então camarada, você está tentado?", "Esta habilidade te concede 10 apelos se o número 5 sair em um dos dados."},
											   {"Lembre-se do seu treinamento nas legiões do império.", "Com o gládio em mãos e o escudo bem posicionado", "Demonstre suas táticas e o conhecimento bélico", "Evitando se queimar enquanto estiver derrubado.", " "},
											   {"Se quiseres emocionar a plateia", "Terás que revelar seus sentimentos dolorosos.", "Recita-los em forma de prosa poética", "Parece certamente um dos modos mais graciosos.", " "}};	

	private String [][] ConteudoEscolhaAdversario = {{"Saudações andarilho, gostaria de falar comigo?", "Por acaso você também segue os preceitos de Tanna-Toh e veio para um debate sobre as", "maravilhas da...", "Não?"},
													{"Você esta querendo me desafiar para uma disputa amistosa onde demonstraremos nossas", "habilidades?", " ", " "},
													{"Isso é ótimo, obviamente será uma boa maneira de adquirir novos conhecimentos, como mestre", "Luriel me dizia 'a prática e a observação de técnicas de outrem contribui muito para o", "aprimoramento... blá, blá, blá'.", "As apresentações SEM interferência ganharão +1 de apelo."},
													{"Bem, eu estava indo mostrar a nada-mais-que-a-verdade a maravilhosa plantação de gorad que", "os Gallobalt cultivaram aqui nas centrais, sinta-se à vontade para acompanhar-nos durante esse", "tempo. Poderia nos contar sobre os diversos lugares em que esteve? ", "Creio que nada-mais-que-a-verdade se interessará bastante no que tem a dizer."},
													{"Saudações, você esta querendo me desafiar para uma disputa amistosa novamente?", " ", " ", " "}};


	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Arius() {
		sorteio();
	}
	
	public void sorteio () {
		for(int i=0; i<8; i++) {
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

