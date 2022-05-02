package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ayla {
	//                                   apelo,                interfer�ncia,          tipo interfer�ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{2, 5, 4, 4, 1, 6, 0, 1}, {4, 1, 0, 2, 3, 1, 0, 3}, {3, 0, -1, 1, 2, 0, 4, 5}, {0, 0, 0, 0, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Engana��o", "Ilus�o", "Disfarce Ilus�rio", "Apar�ncia Inofensiva", "Adaga Mental", "Explos�o de Chamas", "Imagem Espelhada", "Doces Sonhos"};
	private String [][] ConteudoDescricao = {  {"O que � capaz de vencer o mais forte dos seres", "Do que uma mentirinha bem contada com um bocado de engana��o?", "Com uma mente afiada e planejamentos condizentes", "At� uma fadinha bem charmosa ir� ter sua ascens�o.", "Esta habilidade afeta o primeiro campo."},
											   {"Voc� gosta de cores, brilhos e pirotecnia?", "Ent�o venha ver as maravilhosas ilus�es do nosso emp�rio!", "O p�blico se encanta, vibra e urra de alegria", "Ao mesmo tempo que atrapalha seus amigos irris�rios.", "Esta habilidade afeta todos os campos acima de voc�."},
											   {"Borboleta, urubu ou uma senhora enrugada.", "Os produtos Mary Fay te d�o uma apar�ncia convincente.", "Ent�o venha ver nossas revendedoras altamente requisitadas", "Para trazer novas possibilidades a sua mente.", "                                                                                                                      - Emp�rio Purp�rea."}, 
											   {"Aylarianna Purp�rea, a fada mais honesta de Arton", "� encantadora, cheirosa e a pr�pria perfei��o.", "Sua apar�ncia inofensiva com alguns toques de batom", "� capaz de ganha seu amor, seu afeto e sua devo��o.", "Esta habilidade afeta todos os campos abaixo de voc�."},
											   {"Voc� manifesta disparando em seguida", "Uma adaga abstrata.", "Em uma mente desprotegida", "Permanecer� temporariamente cravada.", "Esta habilidade afeta o campo acima de voc�."},
											   {"Incinere os alvos que aparecem em sua frente,", "Fa�a a plateia urrar em fervor,", "Ofusque os oponentes com sua luz incandescente", "E inflame a torcida com seu show estarrecedor.", "Esta habilidade afeta todos os campos acima de voc�."},
											   {"Crie c�pias de si mesma com uma perfei��o impressionante", "E as espalhe por todos os lados.", "Instaure a confus�o em seus atacantes", "Evitando seus golpes inesperados.", "Esta habilidade zera as interfer�ncias ganhadas antes da sua a��o nessa rodada."}, 
											   {"Descansar � necess�rio depois de um dia corriqueiro.", "Por suas trocas de \"afeto\" e \"amizade\"", "Voc� deve um favor aos seus companheiros", "Dando-os um momento de paz e tranquilidade.", "Esta habilidade afeta um campo acima e um abaixo de voc�."}};

	private String [][] ConteudoEscolhaAdversario = {{"Minhas boas-vindas a Ayla Corporation, como posso ajudar?", "Gostaria de falar com a Srta. Ayla?", "Vejamos...", "- Ayla Corporation, em breve rebranding"},
													 {"Voc� tem hora marcada?", " ", " ", " "},
													 {"Muito bem! Me acompanhe por favor.", " ", " ", " "},
													 {"Minhas sinceras desculpas, mas a Srta. Ayla s� recebe com hora marcada.", " ", " ", " "},
													 {"Ol�, voc� � a pessoa que est� desejando me ver?", "Deixe-me adivinhar, voc� quer se tornar uma consultora de beleza aqui no meu emp�rio.", "N�o?",  " "},
													 {"HiHiHIhIhIhiHi eu s� estava brincando, voc� quer me desafiar para uma competi��o, certo?", "Eu aceito o desafio, mas durante o caminho deixa-me falar sobre os benef�cios de se tornar", "consult...", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"Ol�, como � bom te ver novamente, tem interesse em mais uma competi��o junto com", "um plano de carreira completo?", " ", " "}};

	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Ayla() {
		sorteio();
	}
	
	public void sorteio () {

		/*for(int i=0; i<8; i++) {
	        mylist.add(i);
		}*/
		
		mylist.add(0); mylist.add(3); mylist.add(5); mylist.add(7);
		
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