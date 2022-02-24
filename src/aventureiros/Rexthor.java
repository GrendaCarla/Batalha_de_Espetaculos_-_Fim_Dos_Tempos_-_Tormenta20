package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Rexthor {
	//                                   apelo,                interferencia,          tipo interferencia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{4, 2, 6, 6, 5, 0, 3}, {2, 3, 0, 0, 0, 10, 1}, {2, 5, -1, -1, -1, 6, 1}, {1, 1, 1, 0, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7"};
	private String [] gifApelos = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo1", "apelo2", "apelo3"};
	private String [] NomeApelos = {"Punho da dor", "Esquiva", "Briga", "Sentidos agu�ados", "Besuntar", "Sorte das deusas", "A tanga"};
	private String [][] ConteudoDescricao = {  {"De m�sculos contra�dos", "Um gancho � disparado.", "Um rosto � re-esculpido", "Em um corpo desmaiado.", "Esta habilidade afeta o campo acima de voc�."},
											   {"Golpes s�o projetados", "Em sua dire��o.", "Com movimentos calculados", "Voc� esquiva em demonstra��o.", " "},
											   {"Agress�o, barraco, conflito,", "Peleja, refrega, selvageria,", "Confronto, batalha, atrito,", "Sururu, requesta, pancadaria.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Com seus olhos encobertos", "Se concentre no ambiente,", "Com seus sentidos despertos", "Sobreviva aos ataques subsequentes.", " "},
											   {"Quando o calor j� te confunde", "� bom dar uma hidratada,", "Com baby �leo se besunte", "Deixando sua matilha enjoada.", "Esta habilidade afeta todos campos acima de voc�."},
											   {"Jogando cinco d20 uma aposta voc� vai fazer.", "N�o h� repeti��o nos n�meros contemplados.", "10 apelos voc� ganha se 20 o dado conceber.", "Ent�o camarada, voc� est� tentado?", "Esta habilidade te concede 10 apelos se o n�mero 20 sair em um dos dados."},
											   {"Sua tanga guarda objetos", "Que nem sempre s�o sua propriedade,", "Uma vez das vestes liberto", "O dono cai para insanidade.", "Esta habilidade afeta todos os campos abaixo de voc�."}};	
	
	private String [][] ConteudoEscolhaAdversario = {{"Estou vendo na sua cara que voc� est� querendo arranjar briga.", "Quer vim pro fight?", " ", " "},
			   										 {"� melhor voc� ficar esperto mesmo.", " ", " ", " "},
			   										 {"Seguinte, que tal fazermos uma aposta?", "Vou jogar uma moeda, se voc� ganhar eu luto com voc�, mas se eu ganhar voc� vai ter que", "carregar esses sacos para mim, fechado?", " "},
			   										 {"Ent�o, coroa ou sem coroa?", " ", " ", " "},
			   										 {"Ok, voc� venceu dessa vez, mas n�o vai ficar se achando.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
			   										 {"HA! As deusas est�o do meu lado merm�o.", "Pode come�ar levando esses sacos aqui.", " ", " "},
			   										 {"Ai esta voc�. Ent�o, vamos come�ar logo com a minha revanche?", "Porque eu tenho hora pra limpar esse ch�o com a sua cara.", " ", " "}};

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

