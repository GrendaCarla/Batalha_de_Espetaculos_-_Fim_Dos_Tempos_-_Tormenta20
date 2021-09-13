package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Kiki {
	//                                 apelo,              interferencia,           tipo interferencia (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] valores = {{4, 5, 2, 1, 1, 4, 3, 5}, {0, 0, 5 , 9, 3, 4, 2, 2}, {-1, -1, 3, 3, 5, 2, 0, 1}, {0, 0, 0, 0, 0, 1, 1, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3", "apelo4"};
	private String [] NomeApelos = {"Oficio Culinaria", "Cantar", "Engana��o", "Mestre das fofocas", "Olhar artodoante", "Desarmar armadilha", "Pinga, Cacha�a e Tequila", "Suvaqueira"};
	private String [][] ConteudoDescricao = {  {"Como � de se esperar, o oficio culanaria salvando o dia novamente.", " ", " ", " "},
											   {"Como uma pessoa de puro talento, mostre as colinas e ao mundo sua", "linda voz e maestria com os instrumentos.", " ", " "},
											   {"Eu, enganando alguem, imagina. Tudo que estou tentando fazer � ajuda-lo a enchegar a verdade ambigua dessa situa��o em", "que se encontra, para poder gerar uma situa��o de proveito mutuo, no qual algumas partes ganham", "o beneficio material e as outras o espiritual, no sentindo de n�o se preocupar com alguma situa��o que poderia calsar", "incomodo se viese por saber em que se encontra no momento. EU estou lhe dando paz de espirito, denada. afeta o primeiro colocado"},
											   {"Com sua espetacular e refinada tecnica de propagar historias particulares voc� � capas", "de retirar o primeiro colocado do seu pedestal calsando uma alta interferencia nele.", " ", " "},
											   {"use seus olhares penetrantes nos adversarios ao seu redor e fa�a-os ficarem atordoados.", "afeta um oponente acima e abaixo.", " ", " "},
											   {"Ent�o, voc� n�o � a melhor nesse quesito, mas o bom � que tem amigos que se joguem na frente delas por voc�.", "Este poder afeta o oponente acima de voc�.", " ", " "},
											   {"Desafie seus oponentes para uma competi��o e ven�a-os facilmente. afetar� todos acima de voc�.", " ", " ", " "},
											   {"Digamos que seu estilo de vida � questionavel, mas gra�as a isso voc� desenvolveu uma poderosa", "arma biologica. Esse efeito afetar� todos abaixo de voc�.", " ", " "}};	

	private String [][] ConteudoEscolhaAdversario = {{"Voc� gostaria de me enfrentar em um espet�culo?", "Voc� acha que possui MAIS TALENTO DO QUE EU a ponto de me desafiar?", " ", " "},
													{"S�rio?", "Eu estava brincando com voc�, adoraria um desafio que obviamente eu iria ganhar, pois sou uma", "pessoa de muitos talentos e habilidades como pode ver.", "Pois bem, nossa disputa ser� adiada por enquanto, mas ela vai acontecer."},
													{"Mas, ei.", "Eu estou vendo que voc� n�o � daqui, de onde voc� veio, que neg�cios voc� tem a tratar no", "forte cabe�a-de-martelo para percorrer todo esse caminho, talvez um amor antigo ou um segredo", "terr�vel. Me conte tudo e n�o poupe detalhes."},
													{"Pois bem, EU ADOREI sua ousadia.", "Que ven�a a apresenta��o MAIS APAIXONANTE capaz de fervilhar as emo��es do p�blico.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."}};

	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Kiki() {
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

