package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ignis {
	//                                   apelo,                interferencia,          tipo interferencia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{7, 0, 6, 5, 4, 3, 3, 8}, {0, 0, 0, 2, 0, 0, 2, 0}, {-1, 4, -1, 0, -1, -1, 5, -1}, {0, 0, 1, 0, 1, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] NomeApelos = {"Discurso no jutsu", "Canalizar Reparos", "Tapa em puristas", "Ignis bonit�o", "Intoc�vel", "P� no peito e escudada na cara", "Provoca��o petulante", "Meca-Rito"};
	private String [][] ConteudoDescricao = {{"Esta � tua�deixa,�� paladino redentor das chamas.", "Em caminhos tortos e aos trope�os teus inimigos percorrem.", "Reflexivo �s teu canto que mais uma oportunidade�proclamas.", "Reverbere-o para que os sinuosos sempre a frente impossibilidade se tornem.", " "},
											 {"Se em teu ferroso corpo a��es forem desferidas", "O marcando com ranhuras e amassados,", "Ent�o em seu cerne a escaldante chama deve ser expelida", "Para regredir as lacunas at� teu completo reparo.", "Esta habilidade zera as interfer�ncias ganhada antes da sua a��o nessa rodada."},
											 {"De ideias distorcidas e atos vis s�o alimentados.", "Mesmo com tamanha ojeriza ofereceu-lhes a reden��o.", "Ao se recusarem crian�as e idosos n�o foram poupados.", "Brandindo o escudo e o fu� sucedeu-lhes somente a r�spida puni��o.", " "}, 
											 {"N�o h� tempo a ser perdido,", "Todos anseiam sua apari��o.", "Bem formoso, harm�nico e esculpido", "Ficou teu queixo, � BONIT�O.", "Esta habilidade afeta todos os campos acima de voc�."},
											 {"Desafie a todos que quiserem a prova tirar pessoalmente", "Das hist�rias sobre sua defesa robusta e imaculada.", "Venham ferozes ao teu escudo de encontro a ave ardente", "Para que a contagem continua possa em um ser aumentada.", " "},
											 {"V�s sabeis que preferis a defesa aos ataques danosos,", "Mas em alguns casos a for�a h� de ser usada,", "Como em zumbis da Tormenta e puristas odiosos,", "Mas em outras situa��es s� machuca, n�o mata.", " "},
											 {"Provoque todos ao alcance que�inimizades tens�com�vosco.", "Fa�a gestos chamativos e gritos arrogantes.", "Defenda-se de seus pontap�s, golpes e socos", "Protegendo tua matilha um tanto petulante.", "Esta habilidade afeta um campo acima e um abaixo de voc�."}, 
											 {"�s um ador�vel mecaniculo bestial que se juntou a tua patota.", "Saltita e corre arrancando-te sorrisos radiantes,", "Quando agitado e en�rgico destr�i o que est� em vossa volta", "E ao lado sempre pode v�-lo nas rinhas e masmorras inconstantes.", " "}};
	
	private String [][] ConteudoEscolhaAdversario = {{"Boa tarde!�Elmer�me disse que deseja falar comigo sobre algo?", " ", " ", " "},
				 {"Ah! Sim, as competi��es que a�Ayla�criou para aumentar o engajamento com povo das centrais,", "mesmo n�o compreendendo totalmente seu real prop�sito�prometi que aceitaria todos os", "desafios feitos a mim.", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
				 {"Se n�o tem nada a tratar comigo, fique a vontade para conhecer a cede da ordem da reden��o.", "Me acompanhe e eu lhe apresentarei a todos os membros.", "Aquele ali que voc� j� conheceu � o�Elmer, ele foi o primeiro membro que recrutei...", " "},
				 {"Boa tarde! � bom te encontrar novamente por aqui.", "Est� querendo outra competi��o amistosa comigo?", " ", " "}};

	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Ignis() {
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

	public String[] getApelosEInterferencias() {
		
		String[] provisorio = {apelosEInterferencias[mylist.get(0)], apelosEInterferencias[mylist.get(1)], apelosEInterferencias[mylist.get(2)], apelosEInterferencias[mylist.get(3)]};
		return provisorio;
	}
	
	public String[][] getConteudoEscolhaAdversario() {
		return ConteudoEscolhaAdversario;
	}
	
}
