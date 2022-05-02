package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Kiki {
	//                                   apelo,                interfer�ncia,          tipo interfer�ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{4, 6, 2, 1, 1, 2, 3, 1}, {0, 0, 3 , 5, 3, 4, 2, 5}, {-1, -1, 3, 3, 5, 2, 0, 1}, {0, 0, 0, 0, 0, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Oficio Culin�ria", "Talentosa", "Engana��o", "Mestre das Fofocas", "Olhar Atordoante", "Desarmar Armadilha", "Pinga, Cacha�a e Tequila", "Suvaqueira"};
	private String [][] ConteudoDescricao = {  {"Como j� � conveniente", "O of�cio culin�rio salva o dia novamente.", "Ao ficarem sedentos com a refei��o de alta qualidade,", "Nem imaginam o trabalho que d� para impedir os furtos de gorad.", " "},
											   {"Como uma pessoa de puro talento", "Mostre sua maestria com v�rios instrumentos.", "Deixe as colinas e o mundo ouvirem sua voz melodiosa", "Esfregando na cara dos que duvidaram que seria famosa.", " "},
											   {"Dizer que voc� est� enganando algu�m � uma acusa��o infundada.", "Tu apenas ajudou a ver que nessa situa��o complicada", "Um aproveitamento pode vir para ambas as partes,", "Voc� ficando como peso da vit�ria enquanto o outro com a leveza da irresponsabilidade.", "Esta habilidade afeta o primeiro campo."},
											   {"Voc� � capaz de fazer o primeiro colocado", "Ser de seu pedestal rebaixado.", "Usando sua espetacular e refinada t�cnica", "De espalhar fofocas de maneira fren�tica.", "Esta habilidade afeta o primeiro campo."},
											   {"Quando olham para voc� fica dif�cil quebrar o contato,", "N�o pela sua presen�a marcante ou por ser a alma de todo espet�culo,", "E sim pelo penetrante olhar que lan�as ao seu redor", "Atordoando todos que de fortitude � o que tem de pior.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Ent�o, voc� n�o � a melhor nesse quesito,", "Mas o bom � que tem v�rios melhores amigos", "Que ao menor sinal de um perigo mortal", "Entraria na frente do golpe fatal.", "Esta habilidade afeta o campo acima de voc�."},
											   {"Desafie quem quiser para uma competi��o de birita", "Mostrando a sua habilidade de maior conquista.", "Ao ser vitori�sa aproveite seu bucho cheio", "Vomitando como um s�tiro bem em cima de seus companheiros.", "Esta habilidade afeta todos os campos acima de voc�."},
											   {"Seu estilo de higiene � bem question�vel,", "Adquirindo assim um poder incomensur�vel.", "N�o se pode fazer desfeita com o que tens em m�os,", "Ent�o levante o bra�o e libere a putrefa��o.", "Esta habilidade afeta todos os campos abaixo de voc�."}};	
	
	private String [][] ConteudoEscolhaAdversario = {{"Boa tarde! Voc� esta querendo me enfrentar em uma competi��o de apresenta��es?", "Voc� acredita que possui MAIS TALENTO DO QUE EU a ponto de me desafiar?", " ", " "},
													{"S�rio?", "Eu s� estava brincando com voc�, adoraria um duelo que obviamente ganharia, afinal, sou uma", "barda de muitos, muitos talentos e habilidades como meus f�s podem atestar.", "Pois bem, nossa disputa ser� adiada por enquanto, mas ela IR� acontecer em algum momento."},
													{"Mas, ei.", "Eu percebo que voc� n�o � daqui, de onde voc� veio, que neg�cios voc� tem a tratar nas colinas", "centrais para percorrer todo esse caminho, talvez um amor antigo ou um segredo terr�vel.", "Me conte tudo e n�o poupe detalhes."},
													{"A D O R E I seu entusiasmo.", "Que venha a apresenta��o MAIS APAIXONANTE capaz de inflamar as emo��es do p�blico.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
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