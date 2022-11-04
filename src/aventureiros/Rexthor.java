package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Rexthor {
	//                                   apelo,                interferência,          tipo interferência (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{5, 3, 6, 4, 4, 0, 1}, {3, 0, 2, 0, 2, 10, 4}, {2, -1, 5, -1, 0, 6, 1}, {1, 1, 1, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7};
	private String [] NomeApelos = {"Gancho da Dor", "Esquiva", "Briga", "Sentidos Aguçados", "Besuntar", "Sorte das Deusas", "A Tanga"};
	private String [][] ConteudoDescricao = {  {"De músculos contraídos", "Um gancho é disparado.", "Um rosto é re-esculpido", "Em um corpo desmaiado.", "Esta habilidade afeta o campo acima de você."},
											   {"Golpes são projetados", "Em sua direção.", "Com movimentos calculados", "Você esquiva em demonstração.", " "},
											   {"Agressão, barraco, conflito,", "Peleja, refrega, selvageria,", "Confronto, batalha, atrito,", "Sururu, requesta, pancadaria.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Com seus olhos encobertos", "Se concentre no ambiente,", "Com seus sentidos despertos", "Sobreviva aos ataques subsequentes.", " "},
											   {"Quando o calor já te confunde", "É bom dar uma hidratada,", "Com baby óleo se besunte", "Deixando sua matilha enjoada.", "Esta habilidade afeta todos campos acima de você."},
											   {"Jogando cinco d20 uma aposta você vai fazer.", "Não há repetição nos números contemplados.", "10 apelos você ganha se 20 o dado conceber.", "Então camarada, você está tentado?", "Esta habilidade te concede 10 apelos se o número 20 sair em um dos dados."},
											   {"Sua tanga guarda objetos", "Que nem sempre são sua propriedade,", "Uma vez das vestes liberto", "O dono cai para insanidade.", "Esta habilidade afeta todos os campos abaixo de você."}};
	
	// 0---------------------------- primeira interação se o Rexthor for o escolhido ---------------------- 
	private String [][] ConteudoEscolhaAdversario = {{"- Olha, eu mal te conheço, mas já consigo ver pela sua cara que você gosta de criar confusão,", "se meter em briga e dar tapa na cara de freira.", "- Então se você quiser lutar ao meu lado vai ter que primeiro me vencer em uma aposta.", " "},
			 										 {"- Está dentro?", " ", " ", " "},
				   									 {"- Você não consegue nem se garantir em uma aposta e ainda queria que eu lutasse ao seu lado.", "- Desse jeito não dá!", " ", " "},
				   									 {"- Seguinte, eu vou jogar uma moeda, se você ganhar eu luto ao seu lado, mas se eu ganhar", "você terá que carregar todos esses sacos para mim, fechado?", " ", " "},
				   									 {"- Então, coroa ou sem coroa?", " ", " ", " "},
				   									 {"- Sorte de principiante, não vai ficar se achando.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- HA! As deusas estão do meu lado!", "- Já pode ir começando a levar esses sacos perto de você.", " ", " "},
	// 7---------------------------- se o Rexthor for o escolhido e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
				   									 {"- Ainda está com medo de perder para mim? Vamos lá, um pouco de exercício nunca matou", "ninguém.", "- Ele quase te matou da última vez?", "- Então pensa o seguinte, o que não te mata só te fortalece hahahaha."},
				   									 {"- Vamos?", " ", " ", " "},
				   									 {"- De novo essa resposta, se continuar desse jeito, ninguém irá te respeitar.", " ", " ", " "},
			   										 {"- Então, coroa ou sem coroa?", " ", " ", " "},
			   										 {"- Ok, você venceu dessa vez, mas não vai ficar se achando.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
			   										 {"- HA! As deusas estão do meu lado!", "- Já pode ir começando a levar esses sacos perto de você.", " ", " "},
	// 13--------------------------- se o Rexthor for o escolhido e desistiu <= 2 no meio da última luta = |1|0|0|1|3| -------------------
			   										 {"- Camarada, o negócio deve ter ficado feio para você ter saído daquele jeito no meio da luta.", "- Mas não esquenta a cabeça com isso não, de vez em quando é preciso parar tudo antes que", "a merda aconteça.", " "},
			   										 {"- Só tente comer mais fibra da próxima vez hahahaha.", " ", " ", " "},
			   										 {"- Falando da próxima vez, que tau resculpirmos alguns rostos agora?", " ", " ", " "},
			   										 {"- Bora lá! Dessa vez o Ignis não me escapa.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
			   										 {"- Não?", "- Olha, vou te dar um conselho de colega.", "- É melhor não dar oportunidades para o azar ficando fora de forma, se você não colaborar com", "as deusas elas iram pisar em você sem piedade."},
    // 18--------------------------- se o Rexthor for o escolhido e desistiu == 3 no meio da última luta = |1|0|0|3|3| -------------------
				   									 {"- Que história é essa de que você estava fugindo da luta esse tempo todo?", "- Eu pensei que você estava se cagando e não que você estava sendo um covarde de merda.", " ", " "},
				   									 {"- Você tem que encarar de frente as suas derrotas como combatente.", "- Honre sua força, seu legado e quem você é.", "- estamos entendidos?", " "},
				   									 {"- Ótimo, porque se você fizer isso de novo vou fazer questão de te ajudar a adubar a terra de", "verdade dessa vez.", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
	// 21--------------------------- se o Rexthor for o escolhido e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
				   									 {"- Você quer uma revanche?", " ", " ", " "},
				   									 {"- Então a gente veio fazer o que aqui?", "- Me besuntar de óleo?", " ", " ", "- Hahahahaha."},
				   									 {"- Pelo visto você gostou de 'puxar sacos' em?", "- Então, que tau deixar de ser um 'pé no saco' e escolher logo qual lado você vai querer?", "- Eu já estou de 'saco cheio' de tanto esperar e eu quero comer logo, porque afinal...", "'saco vazio não para em pé', hahahahaha."},
				   									 {"- Então, vai ser coroa ou sem coroa?", " ", " ", " "},
				   									 {"- Até que enfim.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- O que foi, vai chorar? Hahaha.", " ", " ", " "},
	// 27--------------------------- se o Rexthor for o escolhido e 1º vitória na última luta = |1|1|0|0|1| -------------------
				   									 {"- Você foi bem na batalha anterior, conseguimos nossa primeira vitória, mas ainda não vou com", "a sua cara.", "- Você é mau-caráter e tenho certeza que coloca o feijão antes do arroz!", " "},
				   									 {"- Se você quiser ser minha dupla nessa competição vai ter que me mostrar o seu valor vencendo","alguns dos meus testes.", " ", " "},
				   									 {"- O primeiro foi um jogo de sorte, fácil.", "- Dessa vez será um jogo de percepção, um gladiador como eu sempre mantêm a guarda alta e", "fica atento ao seu arredor.", " "},
				   									 {"- Vamos começar?", " ", " ", " "},
				   									 {"- Você não vai chegar a lugar nenhum nessa vida se continuar fugindo assim.", " ", " ", " "},
				   									 {"- Preste atenção!", " ", " ", " "},
				   									 {"- Até que você foi bem dessa vez.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- Eu te avisei para prestar atenção.", " ", " ", " "},
	// 35--------------------------- se o Rexthor for o escolhido e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
				   									 {"- O que foi, já está querendo desistir?", "- Se você não aguenta passar por algumas repetições e derrotas, nunca conseguirá encarar as", "próximas batalhas da sua vida.", " "},
				   									 {"- Então, vamos continuar?", " ", " ", " "},
				   									 {"- Se prepare!", " ", " ", " "},
				   									 {"- Que não o que, presta atenção que eu vou começar.", " ", " ", " "},
				   									 {"- Parabéns, até que você foi bem dessa vez.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- Eu te avisei para não desviar o olhar.", " ", " ", " "},
	// 41--------------------------- se o Rexthor for o escolhido e 2º vitória na última luta  = |1|2|4|0|1| -------------------
				   									 {"- Meus parabéns, você realmente conseguiu se provar.", "- Porem ainda não acabou, ainda tem mais um teste que precisa vencer para conseguir disputar", "a última batalha.", " "},
				   									 {"- O desafio testará sua agilidade em bloquear golpes, o que é fundamental para prevenir que os", "pontos vitais sejam atingidos.", "- E para sua sorte eu não irei te bater, só vou arremessar algumas bolas quase inofensivas em", "você, então trate de bloquear todas."},
				   									 {"- Vamos começar?", " ", " ", " "},
				   									 {"- Ok, eu vou deixar passar dessa vez, mas lembre-se, sem dor, sem ganho.", " ", " ", " "},
				   									 {"- Se prepare!", " ", " ", " "},
				   									 {"- Mandou bem!", "- Se continuar assim, logo, logo vai estar segurando flechas em pleno ar.", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- Hahahaha você ficou todo elamead...", " ", " ", " "},
				   									 {"- Uou!", "- Seja o que for que tinha naqueles montinhos de terra, fedem muito.", "- Está tão ruim que você já pode até fazer dupla sertaneja com a Kiki hahaha.", " "},
	// 49--------------------------- se o Rexthor for o escolhido e derrotado na última luta com 2 vitórias = |1|2|1|0|2| -------------------
				   									 {"- Já cansou?", " ", " ", " "},
				   									 {"- Tudo bem, vamos fazer uma pausa agora.", "- Mas se você está pensando em fugir do treinamento, é melhor esquecer.", "- Eu estou te vigiando atentamente e para sua informação os meus sentidos são bem aguçados.", " "},
				   									 {"- Ótimo! Vamos ver quantas vezes você aguenta apanhar feio e continuar.", " ", " ", " "},
				   									 {"- Se prepara.", " ", " ", " "},
				   									 {"- Uma verdadeira dadiva dos ninjas.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- Você chama isso de bloqueio?! Está tirando com a minha cara!", "- Eu já vi você fazer melhor!", "- Vai, bloqueia! Bloqueia! Bloqueia!", " "},
	// 55--------------------------- se o Rexthor for o escolhido e 3º vitória na última luta = |1|3|0|0|1| -------------------
				   									 {"- Escolhe uma mão, sem perguntas, só escolhe.", " ", " ", " "},
				   									 {"- Boa!", "- Agora ela é toda sua para ser seu amuleto de sorte contra os outros Cães das Colinas.", " ", " "},
				   									 {"- Vamos, ainda tenho algo para te mostrar.", "- Como a vida não é feita só de sorte, você tem que criar meios para que ela possa agir.", "- Por isso vou lhe apresentar algo fenomenal que vai te dar uma injetada de ânimo e te ajudar a", "se tornar cada vez mais forte."},
				   									 {"- Ah, não precisa se preocupar, a primeira é de graça hahaha.", "- Vem.", " ", " "},
	// 59--------------------------- se o Rexthor [[[NÃO]]] for o escolhido e primeira interação |0|0|0|0|0 ---------------------------------
	 												 {"- Olha, eu mal te conheço, mas já consigo ver pela sua cara que você gosta de criar confusão,", "se meter em briga e dar tapa na cara de freira.", "- Então se vocês quiserem lutar vão ter que primeiro me vencer em uma aposta.", " "},
	 												 {"- Estão dentro?", " ", " ", " "},
				   									 {"- Vocês não conseguem nem se garantir em uma aposta e ainda queriam lutar contra mim.", "- Desse jeito não dá!", " ", " "},
				   									 {"- Seguinte, eu vou jogar uma moeda, se vocês ganharem eu luto, mas se eu ganhar você terá", "que carregar todos esses sacos para mim, fechado?", " ", " "},
				   									 {"- Então, coroa ou sem coroa?", " ", " ", " "},
				   									 {"- Sorte de principiante, não vão ficar se achando.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- HA! As deusas estão do meu lado!", "- Já pode ir começando a levar esses sacos perto de você.", " ", " "},
	// 66--------------------------- se o Rexthor [[[NÃO]]] for o escolhido e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
				   									 {"- Ainda estão com medo de perder para mim? Vamos lá, um pouco de exercício nunca matou", "ninguém.", "- Ele quase te matou da última vez?", "- Então pensa o seguinte, o que não te mata só te fortalece hahahaha."},
				   									 {"- Vamos?", " ", " ", " "},
				   									 {"- De novo essa resposta, se continuarem desse jeito, ninguém irá respeitar vocês.", " ", " ", " "},
			   										 {"- Então, coroa ou sem coroa?", " ", " ", " "},
			   										 {"- Ok, vocês venceram dessa vez, mas não vão ficar se achando.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
			   										 {"- HA! As deusas estão do meu lado!", "- Já pode ir começando a levar esses sacos perto de você.", " ", " "},
	// 72-------------------------- se o Rexthor [[[NÃO]]] for o escolhido e desistiu <= 2 no meio da última luta = |1|0|0|1|3| -------------------	
			   										 {"- Camarada, o negócio deve ter ficado feio para você ter saído daquele jeito no meio da luta.", "- Mas não esquenta a cabeça com isso não, de vez em quando é preciso parar tudo antes que", "a merda aconteça.", " "},
			   										 {"- Só tente comer mais fibra da próxima vez hahahaha.", " ", " ", " "},
			   										 {"- Falando da próxima vez, que tau eu resculpir os rostos de vocês agora?", " ", " ", " "},
			   										 {"- Bora lá! Dessa vez você não me escapa.", "- Bora lá! Dessa vez você não me escapa Ignis.", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
			   										 {"- Não?", "- Olha, vou te dar um conselho de rival.", "- É melhor não dar oportunidades para o azar ficando fora de forma, se você não colaborar com", "as deusas elas iram pisar em você sem piedade."},
	// 77-------------------------- se o Rexthor [[[NÃO]]] for o escolhido e desistiu == 3 no meio da última luta = |1|0|0|6|3| -------------------
				   									 {"- Que história é essa de que você estava fugindo da luta esse tempo todo?", "- Eu pensei que você estava se cagando e não que você estava sendo um covarde de merda.", " ", " "},
				   									 {"- Você tem que encarar de frente as suas derrotas como combatente.", "- Honre sua força, seu legado e quem você é.", "- estamos entendidos?", " "},
				   									 {"- Ótimo, mas se você fizer isso de novo eu vou fazer questão de ajudar o Ignis a adubar a terra", "- Ótimo, mas se você fizer isso de novo eu vou fazer questão de ajudar a Ayla a adubar a terra", "- Ótimo, mas se você fizer isso de novo eu vou fazer questão de ajudar a Kiki a adubar a terra", "- Ótimo, mas se você fizer isso de novo eu vou fazer questão de ajudar o Arius a adubar a terra", "com você.", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
	// 80-------------------------- se o Rexthor [[[NÃO]]] for o escolhido e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
				   									 {"- Vocês querem uma revanche?", " ", " ", " "},
				   									 {"- Então vieram fazer o que aqui?", "- Me besuntar de óleo?", " ", " ", "- Hahahahaha."},
				   									 {"- Pelo visto você gostou de 'puxar sacos' em?", "- Então, que tau deixarem de ser um 'pé no saco' e escolherem logo qual lado vocês vão querer?", "- Eu já estou de 'saco cheio' de tanto esperar e eu quero comer logo, porque afinal...", "'saco vazio não para em pé', hahahahaha."},
				   									 {"- Então, vai ser coroa ou sem coroa?", " ", " ", " "},
				   									 {"- Até que enfim.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- O que foi, vão chorar? Hahaha.", " ", " ", " "},
	// 86-------------------------- se o Rexthor [[[NÃO]]] for o escolhido e 1º vitória na última luta = |1|1|0|0|1| -------------------
				   									 {"- Vocês foram bem na batalha anterior, conseguiram a primeira vitória, mas ainda não vou com", "a sua cara.", "- Você é mau-caráter e tenho certeza que coloca o feijão antes do arroz!", " "},
				   									 {"- Se vocês quiserem me desafiar nessa competição, vão ter que me mostrar o valor de vocês", "vencendo alguns dos meus testes.", " ", " "},
				   									 {"- O primeiro foi um jogo de sorte, fácil.", "- Dessa vez será um jogo de percepção, um gladiador como eu sempre mantêm a guarda alta e", "fica atento ao seu arredor.", " "},
				   									 {"- Vamos começar?", " ", " ", " "},
				   									 {"- Você não vai chegar a lugar nenhum nessa vida se continuar fugindo assim.", " ", " ", " "},
				   									 {"- Prestem atenção!", " ", " ", " "},
				   									 {"- Até que vocês foram bem dessa vez.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- Eu avisei para prestarem atenção.", " ", " ", " "},
	// 94-------------------------- se o Rexthor [[[NÃO]]] for o escolhido e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
				   									 {"- O que foi, já estão querendo desistir?", "- Se vocês não aguentam passar por algumas repetições e derrotas, nunca conseguiram encarar", "as próximas batalhas da vida de vocês.", " "},
				   									 {"- Então, vamos continuar?", " ", " ", " "},
				   									 {"- Se preparem!", " ", " ", " "},
				   									 {"- Que não o que, prestem atenção que eu vou começar.", " ", " ", " "},
				   									 {"- Parabéns, até que vocês foram bem dessa vez.", " ", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {"- Eu avisei para não desviarem o olhar.", " ", " ", " "},
	// 100-------------------------- se o Rexthor [[[NÃO]]] for o escolhido e 2º vitória na última luta  = |1|2|4|0|1| -------------------
				   									 {"- Meus parabéns, você realmente conseguiu se provar.", "- Porem ainda não acabou, ainda tem mais um teste que precisam vencer para conseguirem", "disputar a última batalha.", " "},
				   									 {"- O desafio testará sua agilidade em bloquear golpes, o que é fundamental para prevenir que os", "pontos vitais sejam atingidos.", "- E para sua sorte eu não irei te bater, só vou arremessar algumas bolas quase inofensivas em", "você, então trate de bloquear todas."},
				   									 {"- Vamos começar?", " ", " ", " "},
				   									 {"- Ok, eu vou deixar passar dessa vez, mas lembrem que sem dor, sem ganho.", " ", " ", " "},
				   									 {"- Se prepare!", " ", " ", " "},
				   									 {"- Mandou bem!", "- Se continuar assim, logo, logo vai estar segurando flechas em pleno ar.", " ", "As apresentações do TIPO Físico ganharão +1 de apelo."},
				   									 {" ", " ", " ", " "},
				   									 {" ", " ", " ", " "},
	// 108-------------------------- se o Rexthor [[[NÃO]]] for o escolhido e derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
				   									 {"- Já cansaram?", " ", " ", " "},
				   									 {"- Tudo bem, vamos fazer uma pausa agora.", "- Mas se vocês estão pensando em fugir do treinamento, é melhor esquecerem.", "- Eu estou vigiando atentamente e para a informação de vocês os meus sentidos são bem", "aguçados."},
				   									 {"- Ótimo! Vamos ver quantas vezes você aguenta apanhar feio e continuar.", " ", " ", " "},
				   									 {"- Se prepara.", " ", " ", " "},
				   									 {" ", " ", " ", " "},
				   									 {" ", " ", " ", " "},
	// 114-------------------------- se o Rexthor [[[NÃO]]] for o escolhido e 3º vitória na última luta = |1|3|0|0|1| -------------------
				   									 {"- Escolhe uma mão, sem perguntas, só escolhe.", " ", " ", " "},
				   									 {"- Boa!", "- Agora ela é toda sua para ser seu amuleto de sorte contra os outros Cães das Colinas.", " ", " "},
				   									 {"- Vamos, eu tenho algo para mostrar somente para você.", "- Como a vida não é feita só de sorte, você tem que criar meios para que ela possa agir.", "- Por isso vou lhe apresentar algo fenomenal que vai te dar uma injetada de ânimo e te ajudar a", "se tornar cada vez mais forte."},
				   									 {"- Ah, não precisa se preocupar, a primeira é de graça hahaha.", "- Vem.", " ", " "},
	// 118--------------------------- se já venceu 3 vezes o Rexthor e falou com ele -------------------
													 {"- Por que voltamos? Temos que ir desafiar os outros cães!", "- Vocês voltaram? Pensei que iriam desafiar os outros cães.", "- Já sei! Você está querendo um pouco mais daquela parada, não mesmo?", " "}};

	
	
	
	
	
	
	
	
	
	
	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Rexthor() {
		sorteio();
	}
	
	public void sorteio () {
		/*for(int i=0; i<7; i++) {
	        mylist.add(i);
		}*/
		
		mylist.add(0); mylist.add(2); mylist.add(5); mylist.add(6);
		
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