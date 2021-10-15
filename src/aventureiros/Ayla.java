package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ayla {
	//                             apelo,                         interferencia,              tipo interferencia (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] valores = {{3, 5, 6, 6, 1, 8, 0, 3}, {4, 2, 0, 1, 3, 1, 0, 3}, {3, 0, -1, 1, 2, 0, 4, 5}, {0, 0, 0, 0, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3", "apelo4"};
	private String [] NomeApelos = {"Engana��o", "Ilus�o", "Disfarce ilus�rio", "Apar�ncia inofensiva", "Adaga mental", "Explos�o de chamas", "Imagem espelhada", "Sono"};
	private String [][] ConteudoDescricao = {  {"Conte mentiras e engane os mais fortes, use sua expertise para se sobressair dos demais.", " ", " ", " ", "Este poder afeta o primeiro colocado."},
											   {"Cores, sons e brilhos, nada como usar sua magia fe�rica para maravilhar o p�blico. � claro", "que isso n�o significa n�o poder atrapalhar um pouquinho os seus amigos.", " ", " ", "Este poder afeta todos acima de voc�."},
											   {"Borboleta, urubu ou uma nobre senhora enrugada, voc� pode ser o que quiser com os", "produtos Mary Fay, traga mais possibilidades para sua vida.", " ", " ", "                                                                                            - Emp�rio Purp�rea."}, 
											   {"A fada mais honesta de Arton tamb�m � encantadora e ador�vel, use sua apar�ncia", "purpurinadamente inofensiva para ganhar o afeto da plateia e ofuscar os demais", "concorrentes.", " ", "Este poder afeta todos abaixo de voc�."},
											   {"Voc� manifesta e dispara uma adaga imaterial contra a mente do alvo.", " ", " ", " ", "Este poder afeta o advers�rio acima de voc�."},
											   {"Fogo, calor e pirotecnia, incendeie o palco com o seu poder e inflame atorcida a seu favor.", " ", " ", " ", "Este poder afeta todos acima de voc�."},
											   {"Fa�a c�pias perfeitas de si mesma dificultando seus amigos a te atrapalharem.", " ", " ", " ", "Esse poder tem a capacidade de zerar suas interfer�ncias ganhadas antes da sua a��o."}, 
											   {"Depois de tanta agita��o e divers�o � sempre bom descansar, fa�a um favor a seus", "companheiros e de um momento de paz e tranquilidade no meio da batalha.", " ", " ", "Este poder afeta um campo acima e abaixo de voc�."}};
	
	private String [][] ConteudoEscolhaAdversario = {{"Minhas boas vindas a Ayla Corporation. Como posso ajudar?", "Gostaria de falar com a Srta. Ayla?", "Vejamos", "- Ayla Corporation, em breve rebranding"},
													 {" ", "Voc� tem hora marcada?", " ", " "},
													 {"Muito bem, me acompanhe por favor.", " ", " ", " "},
													 {"Minhas sinceras desculpas, mas a Srta. Ayla s� recebe com hora marcada.", " ", " ", " "},
													 {"Ol�, voc� esta querendo falar comigo?", "Deixe-me adivinhar, voc� quer se tornar uma consultora da Ayla Corporation?", "N�o?",  "- Ayla Corporation, em breve rebranding"},
													 {"HiHiHIhIhIhiHi eu s� estava brincando, voc� quer me desafiar para uma batalha, certo?", "Eu aceito o desafio, mas durante o caminho deixa eu te falar sobre os benef�cios de ser consulto...", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {" ", "Ol� de novo, voc� quer batalhar mais uma vez?", " ", " "}};

	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Ayla() {
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

