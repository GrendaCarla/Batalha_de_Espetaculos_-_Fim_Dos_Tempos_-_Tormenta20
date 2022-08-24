package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ignis {
	//									 apelo,                interferência,          tipo interferência (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{6, 0, 7, 4, 5, 3, 3, 5}, {0, 0, 0, 2, 0, 0, 2, 0}, {-1, 4, -1, 0, -1, -1, 5, -1}, {0, 0, 1, 0, 1, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Discurso no Jutsu", "Canalizar Reparos", "Tapa em Puristas", "Ignis Bonitão", "Intocável", "Pé no Peito e Escudada na Cara", "Provocação Petulante", "Meca-Rito"};
	private String [][] ConteudoDescricao = {{"Esta é tua deixa, ó paladino redentor das chamas.", "Em caminhos tortos e aos tropeços teus inimigos percorrem.", "Reflexivo és teu canto que nova oportunidade proclamas.", "Reverbere-o para que os terríveis futuros impossibilidade apenas se tornem.", " "},
											 {"Se em teu ferroso corpo ações forem desferidas", "O marcando com ranhuras e amassados,", "Então em seu cerne a escaldante chama deve ser expelida", "Para regredir as lacunas até teu completo reparo.", "Esta habilidade zera as interferências ganhada antes da sua ação nessa rodada."},
											 {"De ideias distorcidas e atos vis são alimentados.", "Mesmo com tamanha ojeriza ofereceu-lhes a redenção.", "Ao se recusarem crianças e idosos não foram poupados.", "Brandindo o escudo e o fuê sucedeu-lhes somente a ríspida punição.", " "}, 
											 {"Não há tempo a ser perdido,", "Todos anseiam sua aparição.", "Bem formoso, harmônico e esculpido", "Ficou teu queixo, ó bonitão.", "Esta habilidade afeta todos os campos acima de você."},
											 {"Desafie a todos que quiserem a prova tirar pessoalmente", "Das histórias sobre sua defesa robusta e imaculada.", "Venham ferozes ao teu escudo de encontro a ave ardente", "Para que a contagem contínua possa em um ser aumentada.", " "},
											 {"Vós sabeis que preferis a defesa aos ataques danosos,", "Mas em alguns casos a força há de ser usada,", "Como em zumbis da Tormenta e puristas odiosos,", "Mas em outras situações só machuca, não mata.", " "},
											 {"Provoque todos ao alcance que inimizades tens com vosco.", "Faça gestos chamativos e gritos arrogantes.", "Defenda-se de seus pontapés, golpes e socos", "Protegendo tua matilha um tanto petulante.", "Esta habilidade afeta um campo acima e um abaixo de você."}, 
											 {"És um adorável mecaniculo bestial que se juntou a tua patota.", "Saltita e corre arrancando-te sorrisos radiantes,", "Quando agitado e enérgico destrói o que está em vossa volta", "E ao lado sempre pode vê-lo nas rinhas e masmorras inconstantes.", " "}};

	// ------------------------------ primeira interação ---------------------------------
	private String [][] ConteudoEscolhaAdversario = {{"Boa tarde! Elmer me disse que deseja falar comigo sobre algo?", " ", " ", " "},
				 {"Ah! Sim, as competições que a Ayla criou para aumentar o engajamento com povo das centrais,", "mesmo não compreendendo totalmente seu real propósito prometi que aceitaria todos os", "desafios feitos a mim.", "As apresentações COM interferência ganharão -1 de apelo."},
				 {"Se não tem nada a tratar comigo, fique a vontade para conhecer a cede da ordem da redenção.", "Me acompanhe e eu lhe apresentarei a todos os membros.", "Aquele ali que você já conheceu é o Elmer, ele foi o primeiro membro que recrutei...", " "},
				 {"Boa tarde! É bom te encontrar novamente por aqui.", "Está querendo outra competição amistosa comigo?", " ", " "},
	// 4º linha --------------------- teve o primeiro contato mas não batalhou = |1|0|0|0|0| -------------------
				 {"teve o primeiro contato mas não batalhou", " ", " ", " "},
	// ------------------------------ desistiu no meio da última luta = |1|0|0|1|3| -------------------
				 {"desistiuno no meio da última luta", " ", " ", " "},
	// ------------------------------ perdeu na última luta = |1|0|1|0|2| -------------------
				 {"perdeu na última luta", " ", " ", " "},
	// ------------------------------ 1º vitória na última luta = |1|1|0|0|1| -------------------
				 {"1º vitória na última luta", " ", " ", " "},
	// ------------------------------ perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
				 {"perdeu na última luta, mas tem 1 vitória", " ", " ", " "},
	// ------------------------------ 2º vitória na última luta com 3 ou menos derrotas = |1|2|3|0|1| -------------------
				 {"2º vitória na última luta com menos de 3 derrotas", " ", " ", " "},
	// ------------------------------ 2º vitória na última luta com mais de 3 derrotas = |1|2|4|0|1| -------------------
				 {"2º vitória na última luta com mais de 3 derrotas", " ", " ", " "},
	// ------------------------------ derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
				 {"derrota na última luta com 2 vitórias", " ", " ", " "},
	// ------------------------------ 3º ou mais vitórias na última luta = |1|3|0|0|1| -------------------
				 {"3º ou mais vitórias na última luta", " ", " ", " "},
	// ------------------------------ derrota na última luta com 3 ou mais vitórias = |1|3|1|0|2| -------------------
				 {"derrota na última luta com 3 ou mais vitórias", " ", " ", " "}};


	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Ignis() {
		sorteio();
	}
	
	public void sorteio () {
		for(int i=0; i<4; i++) {
	        mylist.add(i);
		}
		Collections.shuffle(mylist);
		
	}
	
	public int[][] getValores() {
		
		int[][] provisorio = {{valores[0][mylist.get(0)], valores[0][mylist.get(1)], valores[0][mylist.get(2)], valores[0][mylist.get(3)]},
					 		  {valores[1][mylist.get(0)], valores[1][mylist.get(1)], valores[1][mylist.get(2)], valores[1][mylist.get(3)]},
							  {valores[2][mylist.get(0)], valores[2][mylist.get(1)], valores[2][mylist.get(2)], valores[2][mylist.get(3)]},
							  {valores[3][mylist.get(0)], valores[3][mylist.get(1)], valores[3][mylist.get(2)], valores[3][mylist.get(3)]}};
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