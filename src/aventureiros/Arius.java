package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Arius {
	//                                 apelo,              interferencia,           tipo interferencia (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] valores = {{4, 2, 5, 6, 3, 0, 2, 4}, {3, 0, 0, 0, 4, 10, 0, 3}, {2, -1, -1, -1, 3, 6, -1, 1}, {1, 0, 0, 0, 0, 0, 1, 1}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3", "apelo4"};
	private String [] NomeApelos = {"Chifrada", "Compreensão", "Strip-tease Tático", "Olimpíadas artonianas", "Diplomacia", "5 é 20", "Gládio e escudo", "Afanação de gorad"};
	private String [][] ConteudoDescricao = {  {"Use seus cornos para penetrar em um alvo e lança-lo ao ar.", " ", " ", " ", " Este poder afeta o adversário acima de você."},
											   {"A vitória não advêm somente através da violência física, compreender os adversários ao seu", "redor lhe dará uma vantagem sobre eles.", " ", " ", "Esse poder tem a capacidade de zerar suas interferências ganhadas antes da sua ação."},
											   {"Está na hora de usar seus poderes arcanos, dispa-se da sua couraça e lance a sua magia.", " ", " ", " ", " "},
											   {"Acenda a pira do espirito esportivo e demonstre sua aptidão através das extensas", "modalidades como: saltos pela paliçada, flechadas nos irmãos, esconde-esconde com", "baratas e arremesso com explosão.", " ", " "},
											   {"Use a nobre arte diplomática para argumentar e convencer a plateia que seu adversário não", "merece a posição que ocupa nessa rodada.", " ", " ", "Este poder afeta o primeiro colocado."},
											   {"Você fará uma aposta, jogue cinco d20 e torça para o número 5 sair, os resultados não se", "repetiram nos outros dados, então se você ganhar o apelo será 10, se você perder será zero.", "Tentador?", " ", " "},
											   {"Lembre-se do seu treinamento nas legiões do império taurico, com o gládio em mãos e o", "escudo bem posicionado demonstre as táticas e o conhecimento bélico de seu povo.", " ", " ", " "},
											   {"Gorad, uma delicia viciante no reinado, tão cheirosa e saborosa que você não consegue se", "conter. Para seu deleite e desgosto alguns dos seus companheiros as guardaram em suas", "vestes para comer depois. Você sabe que é errado, mas o seu perfume esta insuportável,", "você terá que rouba-las.", "Este poder afeta todos abaixo de você."}};	

	private String [][] ConteudoEscolhaAdversario = {{"Oi, você esta querendo falar comigo?", "Por acaso você também segue os preceitos de Tanna-Toh e veio para um debate sobre as", "maravilhas da- ", "Não?"},
													{" ", "Você quer me desafiar para uma competição?", " ", " "},
													{"Isso é ótimo, com certeza será um bom jeito de adquirir novos conhecimentos, a pratica e", "observação de técnicas de outras pessoas podem mostrar novas formas de ... blá, blá, blá-", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													{"Bem, eu estava indo registrar a ultima aventura que tive com os outros cães das colinas, mas", "isso pode esperar, sinta-se à vontade para acompanhar Kiki e eu durante esse tempo e nos conte", "mais sobre você. Afinal, o conhecimento vem em varias formas e de lugares diversos e creio que", "poderia acrescentar mais informações... blá, blá, blá-"},
													{" ", "Boa noite, Você gostaria de me desafiar novamente?", " ", " "}};


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

