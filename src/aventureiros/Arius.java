package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Arius {
	//                                 apelo,              interferencia,           tipo interferencia (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] valores = {{4, 2, 5, 6, 3, 0, 2, 4}, {3, 0, 0, 0, 4, 10, 0, 3}, {2, -1, -1, -1, 3, 6, -1, 1}, {1, 0, 0, 0, 0, 0, 1, 1}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3", "apelo4"};
	private String [] NomeApelos = {"Chifrada", "Compreens�o", "Strip-tease T�tico", "Olimp�adas artonianas", "Diplomacia", "5 � 20", "Gl�dio e escudo", "Afana��o de gorad"};
	private String [][] ConteudoDescricao = {  {"Use seus cornos para penetrar em um alvo e lan�a-lo ao ar.", " ", " ", " ", " Este poder afeta o advers�rio acima de voc�."},
											   {"A vit�ria n�o adv�m somente atrav�s da viol�ncia f�sica, compreender os advers�rios ao seu", "redor lhe dar� uma vantagem sobre eles.", " ", " ", "Esse poder tem a capacidade de zerar suas interfer�ncias ganhadas antes da sua a��o."},
											   {"Est� na hora de usar seus poderes arcanos, dispa-se da sua coura�a e lance a sua magia.", " ", " ", " ", " "},
											   {"Acenda a pira do espirito esportivo e demonstre sua aptid�o atrav�s das extensas", "modalidades como: saltos pela pali�ada, flechadas nos irm�os, esconde-esconde com", "baratas e arremesso com explos�o.", " ", " "},
											   {"Use a nobre arte diplom�tica para argumentar e convencer a plateia que seu advers�rio n�o", "merece a posi��o que ocupa nessa rodada.", " ", " ", "Este poder afeta o primeiro colocado."},
											   {"Voc� far� uma aposta, jogue cinco d20 e tor�a para o n�mero 5 sair, os resultados n�o se", "repetiram nos outros dados, ent�o se voc� ganhar o apelo ser� 10, se voc� perder ser� zero.", "Tentador?", " ", " "},
											   {"Lembre-se do seu treinamento nas legi�es do imp�rio taurico, com o gl�dio em m�os e o", "escudo bem posicionado demonstre as t�ticas e o conhecimento b�lico de seu povo.", " ", " ", " "},
											   {"Gorad, uma delicia viciante no reinado, t�o cheirosa e saborosa que voc� n�o consegue se", "conter. Para seu deleite e desgosto alguns dos seus companheiros as guardaram em suas", "vestes para comer depois. Voc� sabe que � errado, mas o seu perfume esta insuport�vel,", "voc� ter� que rouba-las.", "Este poder afeta todos abaixo de voc�."}};	

	private String [][] ConteudoEscolhaAdversario = {{"Oi, voc� esta querendo falar comigo?", "Por acaso voc� tamb�m segue os preceitos de Tanna-Toh e veio para um debate sobre as", "maravilhas da- ", "N�o?"},
													{" ", "Voc� quer me desafiar para uma competi��o?", " ", " "},
													{"Isso � �timo, com certeza ser� um bom jeito de adquirir novos conhecimentos, a pratica e", "observa��o de t�cnicas de outras pessoas podem mostrar novas formas de ... bl�, bl�, bl�-", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													{"Bem, eu estava indo registrar a ultima aventura que tive com os outros c�es das colinas, mas", "isso pode esperar, sinta-se � vontade para acompanhar Kiki e eu durante esse tempo e nos conte", "mais sobre voc�. Afinal, o conhecimento vem em varias formas e de lugares diversos e creio que", "poderia acrescentar mais informa��es... bl�, bl�, bl�-"},
													{" ", "Boa noite, Voc� gostaria de me desafiar novamente?", " ", " "}};


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

