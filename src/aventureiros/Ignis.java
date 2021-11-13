package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ignis {
	//                                   apelo,                interferencia,          tipo interferencia (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] valores = {{7, 0, 6, 5, 4, 3, 3, 8}, {0, 0, 0, 2, 0, 0, 2, 0}, {-1, 4, -1, 0, -1, -1, 5, -1}, {0, 0, 1, 0, 1, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private String [] apelosEInterferencias = {"apelo1", "apelo2", "apelo3", "apelo4", "apelo5", "apelo6", "apelo7", "apelo8"};
	private String [] NomeApelos = {"Discurso no jutsu", "Canalizar Reparos", "Tapa em puristas", "Ignis bonitão", "Intocável", "Pé no peito e escudada na cara", "Provocação petulante", "Meca-Rito"};
	private String [][] ConteudoDescricao = {{"Faça um discurso moralista e inspirador que incentive seus adversários a", "mudarem de vida.", " ", " ", " "},
											 {"Esse poder tem a capacidade de zerar suas interferências ganhadas antes da sua ação.", " ", " ", " ", " "},
											 {"Você ofereceu-lhes a segunda chance,                                      Crianças, mulheres e idosos,", "Infelizmente o pedido foi recusado.                                             Ninguém conseguirá escapar.", "Para o bem de todos no alcance,                                                Com escudo e fuê apostos,", "O tapa em puristas está liberado.                                                Piedade tentaram clamar.", " "}, 
											 {"Exiba seu magnífico queixo para o mundo, algo tão perfeito, másculo e gracioso irá", "tocar o coração do reinado. ", " ", " ", "Este poder afeta todos acima de você."},
											 {"Com seu chassi reformado e um escudo em sua mão, mostre ao público", "que você é intocável.", " ", " ", " "},
											 {"A melhor defesa é sempre um bom ataque, então defenda-se levando seu escudo ao", "encontro da fusa do seu atacante.", " ", " ", " "},
											 {"Provoque todo mundo ao seu redor e incentive-os a te baterem.", " ", " ", " ", "Este poder afeta um campo acima e abaixo de você."}, 
											 {"O que seria do paladino das chamas sem seu parceiro animal. Deixe", "a plateia maravilhada com sua dinâmica em dupla.", " ", " ", " "}};
	
	private String [][] ConteudoEscolhaAdversario = {{" ", "Boa tarde, Elmer me disse que deseja falar comigo sobre algo?", " ", " "},
				 {"Ah, sim, as batalhas de espetáculos.", "Mesmo não as apreciando muito eu prometi que aceitaria todos os desafios feitos a mim.", " ", "As apresentações COM interferência ganharão -1 de apelo."},
				 {"Se não tem nada a tratar comigo fique a vontade para conhecer a cede da ordem da redenção.", "Me acompanhe e eu lhe apresentarei todos os membros.", "Aquele ali que você já conheceu é o Elmer, ele foi o primeiro membro que recrutei...", "blá, blá, blá..."},
				 {" ", "É bom ver seu rosto novamente aqui. Está querendo batalhar mais uma vez?", " ", " "}};

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
