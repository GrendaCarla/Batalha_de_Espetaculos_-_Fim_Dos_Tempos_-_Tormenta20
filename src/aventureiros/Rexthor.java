package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Rexthor {
	//                                   apelo,                interfer�ncia,          tipo interfer�ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{5, 3, 6, 4, 4, 0, 1}, {3, 0, 2, 0, 2, 10, 4}, {2, -1, 5, -1, 0, 6, 1}, {1, 1, 1, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7};
	private String [] NomeApelos = {"Gancho da Dor", "Esquiva", "Briga", "Sentidos Agu�ados", "Besuntar", "Sorte das Deusas", "A Tanga"};
	private String [][] ConteudoDescricao = {  {"De m�sculos contra�dos", "Um gancho � disparado.", "Um rosto � re-esculpido", "Em um corpo desmaiado.", "Esta habilidade afeta o campo acima de voc�."},
											   {"Golpes s�o projetados", "Em sua dire��o.", "Com movimentos calculados", "Voc� esquiva em demonstra��o.", " "},
											   {"Agress�o, barraco, conflito,", "Peleja, refrega, selvageria,", "Confronto, batalha, atrito,", "Sururu, requesta, pancadaria.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Com seus olhos encobertos", "Se concentre no ambiente,", "Com seus sentidos despertos", "Sobreviva aos ataques subsequentes.", " "},
											   {"Quando o calor j� te confunde", "� bom dar uma hidratada,", "Com baby �leo se besunte", "Deixando sua matilha enjoada.", "Esta habilidade afeta todos campos acima de voc�."},
											   {"Jogando cinco d20 uma aposta voc� vai fazer.", "N�o h� repeti��o nos n�meros contemplados.", "10 apelos voc� ganha se 20 o dado conceber.", "Ent�o camarada, voc� est� tentado?", "Esta habilidade te concede 10 apelos se o n�mero 20 sair em um dos dados."},
											   {"Sua tanga guarda objetos", "Que nem sempre s�o sua propriedade,", "Uma vez das vestes liberto", "O dono cai para insanidade.", "Esta habilidade afeta todos os campos abaixo de voc�."}};
	
	// 0---------------------------- primeira intera��o se o Rexthor for o escolhido ---------------------- 
	private String [][] ConteudoEscolhaAdversario = {{"- Olha, eu mal te conhe�o, mas j� consigo ver pela sua cara que voc� gosta de criar confus�o,", "se meter em briga e dar tapa na cara de freira.", "- Ent�o se voc� quiser lutar ao meu lado vai ter que primeiro me vencer em uma aposta.", " "},
			 										 {"- Est� dentro?", " ", " ", " "},
				   									 {"- Voc� n�o consegue nem se garantir em uma aposta e ainda queria que eu lutasse ao seu lado.", "- Desse jeito n�o d�!", " ", " "},
				   									 {"- Seguinte, eu vou jogar uma moeda, se voc� ganhar eu luto ao seu lado, mas se eu ganhar", "voc� ter� que carregar todos esses sacos para mim, fechado?", " ", " "},
				   									 {"- Ent�o, coroa ou sem coroa?", " ", " ", " "},
				   									 {"- Sorte de principiante, n�o vai ficar se achando.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- HA! As deusas est�o do meu lado!", "- J� pode ir come�ando a levar esses sacos perto de voc�.", " ", " "},
	// 7---------------------------- se o Rexthor for o escolhido e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
				   									 {"- Ainda est� com medo de perder para mim? Vamos l�, um pouco de exerc�cio nunca matou", "ningu�m.", "- Ele quase te matou da �ltima vez?", "- Ent�o pensa o seguinte, o que n�o te mata s� te fortalece hahahaha."},
				   									 {"- Vamos?", " ", " ", " "},
				   									 {"- De novo essa resposta, se continuar desse jeito, ningu�m ir� te respeitar.", " ", " ", " "},
			   										 {"- Ent�o, coroa ou sem coroa?", " ", " ", " "},
			   										 {"- Ok, voc� venceu dessa vez, mas n�o vai ficar se achando.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
			   										 {"- HA! As deusas est�o do meu lado!", "- J� pode ir come�ando a levar esses sacos perto de voc�.", " ", " "},
	// 13--------------------------- se o Rexthor for o escolhido e desistiu <= 2 no meio da �ltima luta = |1|0|0|1|3| -------------------
			   										 {"- Camarada, o neg�cio deve ter ficado feio para voc� ter sa�do daquele jeito no meio da luta.", "- Mas n�o esquenta a cabe�a com isso n�o, de vez em quando � preciso parar tudo antes que", "a merda aconte�a.", " "},
			   										 {"- S� tente comer mais fibra da pr�xima vez hahahaha.", " ", " ", " "},
			   										 {"- Falando da pr�xima vez, que tau resculpirmos alguns rostos agora?", " ", " ", " "},
			   										 {"- Bora l�! Dessa vez o Ignis n�o me escapa.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
			   										 {"- N�o?", "- Olha, vou te dar um conselho de colega.", "- � melhor n�o dar oportunidades para o azar ficando fora de forma, se voc� n�o colaborar com", "as deusas elas iram pisar em voc� sem piedade."},
    // 18--------------------------- se o Rexthor for o escolhido e desistiu == 3 no meio da �ltima luta = |1|0|0|3|3| -------------------
				   									 {"- Que hist�ria � essa de que voc� estava fugindo da luta esse tempo todo?", "- Eu pensei que voc� estava se cagando e n�o que voc� estava sendo um covarde de merda.", " ", " "},
				   									 {"- Voc� tem que encarar de frente as suas derrotas como combatente.", "- Honre sua for�a, seu legado e quem voc� �.", "- estamos entendidos?", " "},
				   									 {"- �timo, porque se voc� fizer isso de novo vou fazer quest�o de te ajudar a adubar a terra de", "verdade dessa vez.", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
	// 21--------------------------- se o Rexthor for o escolhido e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
				   									 {"- Voc� quer uma revanche?", " ", " ", " "},
				   									 {"- Ent�o a gente veio fazer o que aqui?", "- Me besuntar de �leo?", " ", " ", "- Hahahahaha."},
				   									 {"- Pelo visto voc� gostou de 'puxar sacos' em?", "- Ent�o, que tau deixar de ser um 'p� no saco' e escolher logo qual lado voc� vai querer?", "- Eu j� estou de 'saco cheio' de tanto esperar e eu quero comer logo, porque afinal...", "'saco vazio n�o para em p�', hahahahaha."},
				   									 {"- Ent�o, vai ser coroa ou sem coroa?", " ", " ", " "},
				   									 {"- At� que enfim.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- O que foi, vai chorar? Hahaha.", " ", " ", " "},
	// 27--------------------------- se o Rexthor for o escolhido e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
				   									 {"- Voc� foi bem na batalha anterior, conseguimos nossa primeira vit�ria, mas ainda n�o vou com", "a sua cara.", "- Voc� � mau-car�ter e tenho certeza que coloca o feij�o antes do arroz!", " "},
				   									 {"- Se voc� quiser ser minha dupla nessa competi��o vai ter que me mostrar o seu valor vencendo","alguns dos meus testes.", " ", " "},
				   									 {"- O primeiro foi um jogo de sorte, f�cil.", "- Dessa vez ser� um jogo de percep��o, um gladiador como eu sempre mant�m a guarda alta e", "fica atento ao seu arredor.", " "},
				   									 {"- Vamos come�ar?", " ", " ", " "},
				   									 {"- Voc� n�o vai chegar a lugar nenhum nessa vida se continuar fugindo assim.", " ", " ", " "},
				   									 {"- Preste aten��o!", " ", " ", " "},
				   									 {"- At� que voc� foi bem dessa vez.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- Eu te avisei para prestar aten��o.", " ", " ", " "},
	// 35--------------------------- se o Rexthor for o escolhido e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
				   									 {"- O que foi, j� est� querendo desistir?", "- Se voc� n�o aguenta passar por algumas repeti��es e derrotas, nunca conseguir� encarar as", "pr�ximas batalhas da sua vida.", " "},
				   									 {"- Ent�o, vamos continuar?", " ", " ", " "},
				   									 {"- Se prepare!", " ", " ", " "},
				   									 {"- Que n�o o que, presta aten��o que eu vou come�ar.", " ", " ", " "},
				   									 {"- Parab�ns, at� que voc� foi bem dessa vez.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- Eu te avisei para n�o desviar o olhar.", " ", " ", " "},
	// 41--------------------------- se o Rexthor for o escolhido e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
				   									 {"- Meus parab�ns, voc� realmente conseguiu se provar.", "- Porem ainda n�o acabou, ainda tem mais um teste que precisa vencer para conseguir disputar", "a �ltima batalha.", " "},
				   									 {"- O desafio testar� sua agilidade em bloquear golpes, o que � fundamental para prevenir que os", "pontos vitais sejam atingidos.", "- E para sua sorte eu n�o irei te bater, s� vou arremessar algumas bolas quase inofensivas em", "voc�, ent�o trate de bloquear todas."},
				   									 {"- Vamos come�ar?", " ", " ", " "},
				   									 {"- Ok, eu vou deixar passar dessa vez, mas lembre-se, sem dor, sem ganho.", " ", " ", " "},
				   									 {"- Se prepare!", " ", " ", " "},
				   									 {"- Mandou bem!", "- Se continuar assim, logo, logo vai estar segurando flechas em pleno ar.", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- Hahahaha voc� ficou todo elamead...", " ", " ", " "},
				   									 {"- Uou!", "- Seja o que for que tinha naqueles montinhos de terra, fedem muito.", "- Est� t�o ruim que voc� j� pode at� fazer dupla sertaneja com a Kiki hahaha.", " "},
	// 49--------------------------- se o Rexthor for o escolhido e derrotado na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
				   									 {"- J� cansou?", " ", " ", " "},
				   									 {"- Tudo bem, vamos fazer uma pausa agora.", "- Mas se voc� est� pensando em fugir do treinamento, � melhor esquecer.", "- Eu estou te vigiando atentamente e para sua informa��o os meus sentidos s�o bem agu�ados.", " "},
				   									 {"- �timo! Vamos ver quantas vezes voc� aguenta apanhar feio e continuar.", " ", " ", " "},
				   									 {"- Se prepara.", " ", " ", " "},
				   									 {"- Uma verdadeira dadiva dos ninjas.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- Voc� chama isso de bloqueio?! Est� tirando com a minha cara!", "- Eu j� vi voc� fazer melhor!", "- Vai, bloqueia! Bloqueia! Bloqueia!", " "},
	// 55--------------------------- se o Rexthor for o escolhido e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
				   									 {"- Escolhe uma m�o, sem perguntas, s� escolhe.", " ", " ", " "},
				   									 {"- Boa!", "- Agora ela � toda sua para ser seu amuleto de sorte contra os outros C�es das Colinas.", " ", " "},
				   									 {"- Vamos, ainda tenho algo para te mostrar.", "- Como a vida n�o � feita s� de sorte, voc� tem que criar meios para que ela possa agir.", "- Por isso vou lhe apresentar algo fenomenal que vai te dar uma injetada de �nimo e te ajudar a", "se tornar cada vez mais forte."},
				   									 {"- Ah, n�o precisa se preocupar, a primeira � de gra�a hahaha.", "- Vem.", " ", " "},
	// 59--------------------------- se o Rexthor [[[N�O]]] for o escolhido e primeira intera��o |0|0|0|0|0 ---------------------------------
	 												 {"- Olha, eu mal te conhe�o, mas j� consigo ver pela sua cara que voc� gosta de criar confus�o,", "se meter em briga e dar tapa na cara de freira.", "- Ent�o se voc�s quiserem lutar v�o ter que primeiro me vencer em uma aposta.", " "},
	 												 {"- Est�o dentro?", " ", " ", " "},
				   									 {"- Voc�s n�o conseguem nem se garantir em uma aposta e ainda queriam lutar contra mim.", "- Desse jeito n�o d�!", " ", " "},
				   									 {"- Seguinte, eu vou jogar uma moeda, se voc�s ganharem eu luto, mas se eu ganhar voc� ter�", "que carregar todos esses sacos para mim, fechado?", " ", " "},
				   									 {"- Ent�o, coroa ou sem coroa?", " ", " ", " "},
				   									 {"- Sorte de principiante, n�o v�o ficar se achando.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- HA! As deusas est�o do meu lado!", "- J� pode ir come�ando a levar esses sacos perto de voc�.", " ", " "},
	// 66--------------------------- se o Rexthor [[[N�O]]] for o escolhido e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
				   									 {"- Ainda est�o com medo de perder para mim? Vamos l�, um pouco de exerc�cio nunca matou", "ningu�m.", "- Ele quase te matou da �ltima vez?", "- Ent�o pensa o seguinte, o que n�o te mata s� te fortalece hahahaha."},
				   									 {"- Vamos?", " ", " ", " "},
				   									 {"- De novo essa resposta, se continuarem desse jeito, ningu�m ir� respeitar voc�s.", " ", " ", " "},
			   										 {"- Ent�o, coroa ou sem coroa?", " ", " ", " "},
			   										 {"- Ok, voc�s venceram dessa vez, mas n�o v�o ficar se achando.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
			   										 {"- HA! As deusas est�o do meu lado!", "- J� pode ir come�ando a levar esses sacos perto de voc�.", " ", " "},
	// 72-------------------------- se o Rexthor [[[N�O]]] for o escolhido e desistiu <= 2 no meio da �ltima luta = |1|0|0|1|3| -------------------	
			   										 {"- Camarada, o neg�cio deve ter ficado feio para voc� ter sa�do daquele jeito no meio da luta.", "- Mas n�o esquenta a cabe�a com isso n�o, de vez em quando � preciso parar tudo antes que", "a merda aconte�a.", " "},
			   										 {"- S� tente comer mais fibra da pr�xima vez hahahaha.", " ", " ", " "},
			   										 {"- Falando da pr�xima vez, que tau eu resculpir os rostos de voc�s agora?", " ", " ", " "},
			   										 {"- Bora l�! Dessa vez voc� n�o me escapa.", "- Bora l�! Dessa vez voc� n�o me escapa Ignis.", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
			   										 {"- N�o?", "- Olha, vou te dar um conselho de rival.", "- � melhor n�o dar oportunidades para o azar ficando fora de forma, se voc� n�o colaborar com", "as deusas elas iram pisar em voc� sem piedade."},
	// 77-------------------------- se o Rexthor [[[N�O]]] for o escolhido e desistiu == 3 no meio da �ltima luta = |1|0|0|6|3| -------------------
				   									 {"- Que hist�ria � essa de que voc� estava fugindo da luta esse tempo todo?", "- Eu pensei que voc� estava se cagando e n�o que voc� estava sendo um covarde de merda.", " ", " "},
				   									 {"- Voc� tem que encarar de frente as suas derrotas como combatente.", "- Honre sua for�a, seu legado e quem voc� �.", "- estamos entendidos?", " "},
				   									 {"- �timo, mas se voc� fizer isso de novo eu vou fazer quest�o de ajudar o Ignis a adubar a terra", "- �timo, mas se voc� fizer isso de novo eu vou fazer quest�o de ajudar a Ayla a adubar a terra", "- �timo, mas se voc� fizer isso de novo eu vou fazer quest�o de ajudar a Kiki a adubar a terra", "- �timo, mas se voc� fizer isso de novo eu vou fazer quest�o de ajudar o Arius a adubar a terra", "com voc�.", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
	// 80-------------------------- se o Rexthor [[[N�O]]] for o escolhido e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
				   									 {"- Voc�s querem uma revanche?", " ", " ", " "},
				   									 {"- Ent�o vieram fazer o que aqui?", "- Me besuntar de �leo?", " ", " ", "- Hahahahaha."},
				   									 {"- Pelo visto voc� gostou de 'puxar sacos' em?", "- Ent�o, que tau deixarem de ser um 'p� no saco' e escolherem logo qual lado voc�s v�o querer?", "- Eu j� estou de 'saco cheio' de tanto esperar e eu quero comer logo, porque afinal...", "'saco vazio n�o para em p�', hahahahaha."},
				   									 {"- Ent�o, vai ser coroa ou sem coroa?", " ", " ", " "},
				   									 {"- At� que enfim.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- O que foi, v�o chorar? Hahaha.", " ", " ", " "},
	// 86-------------------------- se o Rexthor [[[N�O]]] for o escolhido e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
				   									 {"- Voc�s foram bem na batalha anterior, conseguiram a primeira vit�ria, mas ainda n�o vou com", "a sua cara.", "- Voc� � mau-car�ter e tenho certeza que coloca o feij�o antes do arroz!", " "},
				   									 {"- Se voc�s quiserem me desafiar nessa competi��o, v�o ter que me mostrar o valor de voc�s", "vencendo alguns dos meus testes.", " ", " "},
				   									 {"- O primeiro foi um jogo de sorte, f�cil.", "- Dessa vez ser� um jogo de percep��o, um gladiador como eu sempre mant�m a guarda alta e", "fica atento ao seu arredor.", " "},
				   									 {"- Vamos come�ar?", " ", " ", " "},
				   									 {"- Voc� n�o vai chegar a lugar nenhum nessa vida se continuar fugindo assim.", " ", " ", " "},
				   									 {"- Prestem aten��o!", " ", " ", " "},
				   									 {"- At� que voc�s foram bem dessa vez.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- Eu avisei para prestarem aten��o.", " ", " ", " "},
	// 94-------------------------- se o Rexthor [[[N�O]]] for o escolhido e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
				   									 {"- O que foi, j� est�o querendo desistir?", "- Se voc�s n�o aguentam passar por algumas repeti��es e derrotas, nunca conseguiram encarar", "as pr�ximas batalhas da vida de voc�s.", " "},
				   									 {"- Ent�o, vamos continuar?", " ", " ", " "},
				   									 {"- Se preparem!", " ", " ", " "},
				   									 {"- Que n�o o que, prestem aten��o que eu vou come�ar.", " ", " ", " "},
				   									 {"- Parab�ns, at� que voc�s foram bem dessa vez.", " ", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {"- Eu avisei para n�o desviarem o olhar.", " ", " ", " "},
	// 100-------------------------- se o Rexthor [[[N�O]]] for o escolhido e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
				   									 {"- Meus parab�ns, voc� realmente conseguiu se provar.", "- Porem ainda n�o acabou, ainda tem mais um teste que precisam vencer para conseguirem", "disputar a �ltima batalha.", " "},
				   									 {"- O desafio testar� sua agilidade em bloquear golpes, o que � fundamental para prevenir que os", "pontos vitais sejam atingidos.", "- E para sua sorte eu n�o irei te bater, s� vou arremessar algumas bolas quase inofensivas em", "voc�, ent�o trate de bloquear todas."},
				   									 {"- Vamos come�ar?", " ", " ", " "},
				   									 {"- Ok, eu vou deixar passar dessa vez, mas lembrem que sem dor, sem ganho.", " ", " ", " "},
				   									 {"- Se prepare!", " ", " ", " "},
				   									 {"- Mandou bem!", "- Se continuar assim, logo, logo vai estar segurando flechas em pleno ar.", " ", "As apresenta��es do TIPO F�sico ganhar�o +1 de apelo."},
				   									 {" ", " ", " ", " "},
				   									 {" ", " ", " ", " "},
	// 108-------------------------- se o Rexthor [[[N�O]]] for o escolhido e derrota na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
				   									 {"- J� cansaram?", " ", " ", " "},
				   									 {"- Tudo bem, vamos fazer uma pausa agora.", "- Mas se voc�s est�o pensando em fugir do treinamento, � melhor esquecerem.", "- Eu estou vigiando atentamente e para a informa��o de voc�s os meus sentidos s�o bem", "agu�ados."},
				   									 {"- �timo! Vamos ver quantas vezes voc� aguenta apanhar feio e continuar.", " ", " ", " "},
				   									 {"- Se prepara.", " ", " ", " "},
				   									 {" ", " ", " ", " "},
				   									 {" ", " ", " ", " "},
	// 114-------------------------- se o Rexthor [[[N�O]]] for o escolhido e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
				   									 {"- Escolhe uma m�o, sem perguntas, s� escolhe.", " ", " ", " "},
				   									 {"- Boa!", "- Agora ela � toda sua para ser seu amuleto de sorte contra os outros C�es das Colinas.", " ", " "},
				   									 {"- Vamos, eu tenho algo para mostrar somente para voc�.", "- Como a vida n�o � feita s� de sorte, voc� tem que criar meios para que ela possa agir.", "- Por isso vou lhe apresentar algo fenomenal que vai te dar uma injetada de �nimo e te ajudar a", "se tornar cada vez mais forte."},
				   									 {"- Ah, n�o precisa se preocupar, a primeira � de gra�a hahaha.", "- Vem.", " ", " "},
	// 118--------------------------- se j� venceu 3 vezes o Rexthor e falou com ele -------------------
													 {"- Por que voltamos? Temos que ir desafiar os outros c�es!", "- Voc�s voltaram? Pensei que iriam desafiar os outros c�es.", "- J� sei! Voc� est� querendo um pouco mais daquela parada, n�o mesmo?", " "}};

	
	
	
	
	
	
	
	
	
	
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