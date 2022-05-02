package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ayla {
	//                                   apelo,                interferência,          tipo interferência (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{2, 5, 4, 4, 1, 6, 0, 1}, {4, 1, 0, 2, 3, 1, 0, 3}, {3, 0, -1, 1, 2, 0, 4, 5}, {0, 0, 0, 0, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Enganação", "Ilusão", "Disfarce Ilusório", "Aparência Inofensiva", "Adaga Mental", "Explosão de Chamas", "Imagem Espelhada", "Doces Sonhos"};
	private String [][] ConteudoDescricao = {  {"O que é capaz de vencer o mais forte dos seres", "Do que uma mentirinha bem contada com um bocado de enganação?", "Com uma mente afiada e planejamentos condizentes", "Até uma fadinha bem charmosa irá ter sua ascensão.", "Esta habilidade afeta o primeiro campo."},
											   {"Você gosta de cores, brilhos e pirotecnia?", "Então venha ver as maravilhosas ilusões do nosso empório!", "O público se encanta, vibra e urra de alegria", "Ao mesmo tempo que atrapalha seus amigos irrisórios.", "Esta habilidade afeta todos os campos acima de você."},
											   {"Borboleta, urubu ou uma senhora enrugada.", "Os produtos Mary Fay te dão uma aparência convincente.", "Então venha ver nossas revendedoras altamente requisitadas", "Para trazer novas possibilidades a sua mente.", "                                                                                                                      - Empório Purpúrea."}, 
											   {"Aylarianna Purpúrea, a fada mais honesta de Arton", "É encantadora, cheirosa e a própria perfeição.", "Sua aparência inofensiva com alguns toques de batom", "É capaz de ganha seu amor, seu afeto e sua devoção.", "Esta habilidade afeta todos os campos abaixo de você."},
											   {"Você manifesta disparando em seguida", "Uma adaga abstrata.", "Em uma mente desprotegida", "Permanecerá temporariamente cravada.", "Esta habilidade afeta o campo acima de você."},
											   {"Incinere os alvos que aparecem em sua frente,", "Faça a plateia urrar em fervor,", "Ofusque os oponentes com sua luz incandescente", "E inflame a torcida com seu show estarrecedor.", "Esta habilidade afeta todos os campos acima de você."},
											   {"Crie cópias de si mesma com uma perfeição impressionante", "E as espalhe por todos os lados.", "Instaure a confusão em seus atacantes", "Evitando seus golpes inesperados.", "Esta habilidade zera as interferências ganhadas antes da sua ação nessa rodada."}, 
											   {"Descansar é necessário depois de um dia corriqueiro.", "Por suas trocas de \"afeto\" e \"amizade\"", "Você deve um favor aos seus companheiros", "Dando-os um momento de paz e tranquilidade.", "Esta habilidade afeta um campo acima e um abaixo de você."}};

	private String [][] ConteudoEscolhaAdversario = {{"Minhas boas-vindas a Ayla Corporation, como posso ajudar?", "Gostaria de falar com a Srta. Ayla?", "Vejamos...", "- Ayla Corporation, em breve rebranding"},
													 {"Você tem hora marcada?", " ", " ", " "},
													 {"Muito bem! Me acompanhe por favor.", " ", " ", " "},
													 {"Minhas sinceras desculpas, mas a Srta. Ayla só recebe com hora marcada.", " ", " ", " "},
													 {"Olá, você é a pessoa que está desejando me ver?", "Deixe-me adivinhar, você quer se tornar uma consultora de beleza aqui no meu empório.", "Não?",  " "},
													 {"HiHiHIhIhIhiHi eu só estava brincando, você quer me desafiar para uma competição, certo?", "Eu aceito o desafio, mas durante o caminho deixa-me falar sobre os benefícios de se tornar", "consult...", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"Olá, como é bom te ver novamente, tem interesse em mais uma competição junto com", "um plano de carreira completo?", " ", " "}};

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