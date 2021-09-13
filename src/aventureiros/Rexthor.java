package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Rexthor {
	//                                 apelo,              interferencia,           tipo interferencia (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] valores = {{4, 2, 6, 6, 5, 0, 3}, {2, 3, 0, 0, 0, 10, 1}, {2, 5, -1, -1, -1, 6, 1}, {1, 1, 1, 0, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3"};
	private String [] NomeApelos = {"Punho da dor", "Esquiva", "Briga", "Besuntar de �leo", "Flexionar m�sculos", "Sorte das deusas", "A tanga"};
	private String [][] ConteudoDescricao = {  {"Um gancho bem dado no queixo", " ", " ", " "},
											   {"Uma excelente demonstra��o de reflexos em combate", " ", " ", " "},
											   {"Quebra pau, montinho do soco, clube da luta", " ", " ", " "},
											   {"Rexthor n�o seria Rexthor sem o seu �leo corporal, fa�a uma demonstra��o", "p�blica do seu belo corpo para incitar a torcida", " ", " "},
											   {"Voc� n�o malha atoa, flexione seus m�sculos tonificados e ofusque os seus", "advers�rios", " ", " "},
											   {"Voc� far� uma aposta, jogue cinco d20 e tor�a para o n�mero 20 sair, os", "resultados n�o se repetiram nos outros dados, ent�o se voc� ganhar o apelo ser� 10, se voc� perder ser� zero. Tentador?", " ", " "},
											   {"Sua tanga esconde v�rias coisas, muitas delas s�o dos seus amigos. Enfie", "a m�o e retire algo que os surpreendam.", " ", " "}};	
	
	private String [][] ConteudoEscolhaAdversario = {{"Estou vendo na sua cara que voc� esta querendo arranjar briga.", "Quer vim pro fight?", " ", " "},
			   										 {"Ent�o � melhor voc� ficar esperto.", " ", " ", " "},
			   										 {"Seguinte, vamos fazer uma aposta.", "Vou jogar uma moeda, se voc� ganhar eu luto com voc�, mas se eu ganhar voc� vai ter que", "carregar esses sacos para mim, fechado?", " "},
			   										 {"Ent�o, coroa ou sem coroa?", " ", " ", " "},
			   										 {"Ok, voc� venceu dessa vez, mas n�o vai se achando n�o rapa.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
			   										 {"HA! as deusas est�o do meu lado merm�o.", "Pode come�ar levando esses sacos aqui.", " ", " "}};

	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Rexthor() {
		sorteio();
	}
	
	public void sorteio () {
		for(int i=0; i<7; i++) {
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

