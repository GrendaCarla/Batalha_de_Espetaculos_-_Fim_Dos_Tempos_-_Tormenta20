package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Kiki {
	//                                 apelo,              interferencia,           tipo interferencia (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] valores = {{4, 5, 2, 1, 1, 4, 3, 5}, {0, 0, 5 , 9, 3, 4, 2, 2}, {-1, -1, 3, 3, 5, 2, 0, 1}, {0, 0, 0, 0, 0, 1, 1, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3", "apelo4"};
	private String [] NomeApelos = {"Oficio Culin�rio", "Cantar", "Engana��o", "Mestre das fofocas", "Olhar atordoante", "Desarmar armadilha", "Pinga, Cacha�a e Tequila", "Suvaqueira"};
	private String [][] ConteudoDescricao = {  {"Como � de se esperar, o oficio culin�rio salvando o dia novamente. De um Show digno do", "MasterChef e conquiste a plateia pela barriga.", " ", "PS: N�o deixe o Arius roubar o gorad.", " "},
											   {"Como uma pessoa de puro talento, mostre as colinas e ao mundo sua poderosa voz e sua", "maestria com os instrumentos.", " ", " ", " "},
											   {"Eu, enganando algu�m, imagina. Tudo que estou tentando fazer � ajuda-lo a enxergar a", "verdade amb�gua dessa situa��o em que se encontra, para poder gerar uma situa��o de", "proveito mutuo, no qual algumas partes ganham o beneficio material e as outras o espiritual.", "Eu estou lhe dando paz de espirito, de nada.", "Esta habilidade afeta o primeiro campo."},
											   {"Com sua espetacular e refinada t�cnica de propagar historias particulares voc� � capaz de", "retirar o primeiro colocado do seu pedestal causando uma alta interfer�ncia nele.", " ", " ", "Esta habilidade afeta o primeiro campo."},
											   {"Ao fixarem os olhares em voc� se torna dif�cil quebra-los. Use seu olhar penetrante nos", "advers�rios ao seu redor e fa�a-os ficarem atordoados.", " ", " ", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Ent�o, voc� n�o � a melhor nesse quesito, mas o bom � que tem v�rios amigos que se", "jogariam na sua frente para protege-la.", " ", " ", "Esta habilidade afeta o campo acima de voc�."},
											   {"Desafie seus oponentes para uma competi��o de birita e os ven�a facilmente enquanto o", "vomito rola souto.", " ", " ", "Esta habilidade afeta todos os campos acima de voc�."},
											   {"Digamos que seu estilo de higiene � question�vel, mas gra�as a isso voc� desenvolveu uma", "poderosa arma biol�gica, use-a a seu favor.", " ", " ", "Esta habilidade afeta todos os campos abaixo de voc�."}};	

	private String [][] ConteudoEscolhaAdversario = {{"Boa tarde! Voc� esta querendo me enfrentar em uma competi��o de apresenta��es?", "Voc� acredita que possui MAIS TALENTO DO QUE EU a ponto de me desafiar?", " ", " "},
													{"S�rio?", "Eu s� estava brincando com voc�, adoraria um duelo que obviamente ganharia, afinal, sou uma", "barda de muitos, muitos talentos e habilidades como meus f�s podem atestar.", "Pois bem, nossa disputa ser� adiada por enquanto, mas ela IR� acontecer em algum momento."},
													{"Mas, ei.", "Eu percebo que voc� n�o � daqui, de onde voc� veio, que neg�cios voc� tem a tratar nas colinas", "centrais para percorrer todo esse caminho, talvez um amor antigo ou um segredo terr�vel.", "Me conte tudo e n�o poupe detalhes."},
													{"A D O R E I seu entusiasmo.", "Que ven�a a apresenta��o MAIS APAIXONANTE capaz de inflamar as emo��es do p�blico.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
													{"Daquela vez voc� me derrotou, mas essa hist�ria ainda n�o chegou ao seu fim, eu quero uma", "R E V A N C H E. Vai aceitar ou vai arregar?", " ", " "}};

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
		
		int[][] provisorio = {{valores[0][7], valores[0][7], valores[0][7], valores[0][7]},
					 		  {valores[1][7], valores[1][7], valores[1][7], valores[1][7]},
							  {valores[2][7], valores[2][7], valores[2][7], valores[2][7]},
							  {valores[3][7], valores[3][7], valores[3][7], valores[3][7]},
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

