package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Kiki {
	//                                   apelo,                interferencia,          tipo interferencia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{4, 5, 2, 1, 1, 4, 3, 5}, {0, 0, 5 , 9, 3, 4, 2, 2}, {-1, -1, 3, 3, 5, 2, 0, 1}, {0, 0, 0, 0, 0, 1, 1, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3", "apelo4"};
	private String [] NomeApelos = {"Oficio Culinário", "Talentosa", "Enganação", "Mestre das fofocas", "Olhar atordoante", "Desarmar armadilha", "Pinga, Cachaça e Tequila", "Suvaqueira"};
	private String [][] ConteudoDescricao = {  {"Como já é conveniente", "O ofício culinário salva o dia novamente.", "Todos ficam sedentos com a refeição de qualidade.", "PS: Impeça o Arius de roubar outra xícara de gorad.", " "},
											   {"Como uma pessoa de puro talento", "Mostre sua maestria com vários instrumentos.", "Deixe as colinas e o mundo ouvirem sua voz melodiosa", "Esfregando na cara dos que duvidaram que seria famosa.", " "},
											   {"Dizer que você está enganando alguém é uma acusação infundada.", "Tu apenas ajudou a ver que nessa situação complicada", "Um aproveitamento pode vir para ambas as partes,", "Você ficando como peso da vitória enquanto o outro com a leveza da irresponsabilidade.", "Esta habilidade afeta o primeiro campo."},
											   {"Você é capaz de fazer o primeiro colocado", "Ser de seu pedestal rebaixado.", "Usando sua espetacular e refinada técnica", "De espalhar fofocas de maneira frenética.", "Esta habilidade afeta o primeiro campo."},
											   {"Quando olham para você fica difícil quebrar o contato,", "Não pela sua presença marcante ou por ser a alma de todo espetáculo,", "E sim pelo penetrante olhar que lanças ao seu redor", "Atordoando todos que de fortitude é o que tem de pior.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Então, você não é a melhor nesse quesito,", "Mas o bom é que tem vários melhores amigos", "Que ao menor sinal de um perigo mortal", "Entraria na frente do golpe fatal.", "Esta habilidade afeta o campo acima de você."},
											   {"Desafie quem quiser para uma competição de birita", "Mostrando a sua habilidade de maior conquista.", "Ao vencer a partida ficando com o tanque cheio", "Vomite como um sátiro bem em cima de seus companheiros.", "Esta habilidade afeta todos os campos acima de você."},
											   {"Seu estilo de higiene é bem questionável,", "Adquirindo assim um poder incomensurável.", "Não se pode fazer desfeita com o que tens em mãos,", "Então levante o braço e libere a putrefação.", "Esta habilidade afeta todos os campos abaixo de você."}};	

	private String [][] ConteudoEscolhaAdversario = {{"Boa tarde! Você esta querendo me enfrentar em uma competição de apresentações?", "Você acredita que possui MAIS TALENTO DO QUE EU a ponto de me desafiar?", " ", " "},
													{"Sério?", "Eu só estava brincando com você, adoraria um duelo que obviamente ganharia, afinal, sou uma", "barda de muitos, muitos talentos e habilidades como meus fãs podem atestar.", "Pois bem, nossa disputa será adiada por enquanto, mas ela IRÁ acontecer em algum momento."},
													{"Mas, ei.", "Eu percebo que você não é daqui, de onde você veio, que negócios você tem a tratar nas colinas", "centrais para percorrer todo esse caminho, talvez um amor antigo ou um segredo terrível.", "Me conte tudo e não poupe detalhes."},
													{"A D O R E I seu entusiasmo.", "Que vença a apresentação MAIS APAIXONANTE capaz de inflamar as emoções do público.", " ", "As apresentações COM interferência ganharão +1 de apelo."},
													{"Daquela vez você me derrotou, mas essa história ainda não chegou ao seu fim, eu quero uma", "R E V A N C H E. Vai aceitar ou vai arregar?", " ", " "}};

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

