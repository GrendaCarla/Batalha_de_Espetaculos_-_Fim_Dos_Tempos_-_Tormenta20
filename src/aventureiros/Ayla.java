package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ayla {
	//                                   apelo,                interferência,          tipo interferência (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{2, 5, 4, 4, 1, 6, 0, 1}, {4, 1, 0, 2, 3, 1, 0, 3}, {3, 0, -1, 1, 2, 0, 4, 5}, {0, 0, 0, 0, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Enganação", "Ilusão", "Disfarce Ilusório", "Aparência Inofensiva", "Adaga Mental", "Explosão de Chamas", "Imagem Espelhada", "Doces Sonhos"};
	private String [][] ConteudoDescricao = {  {"O que é capaz de vencer o mais forte dos seres", "Do que uma mentirinha bem contada com um bocado de enganação?", "Com uma mente afiada e planejamentos condizentes", "Até uma fadinha bem charmosa irá ter sua ascensão.", "Esta habilidade afeta o primeiro campo."},
											   {"Você gosta de cores, brilhos e pirotecnia?", "Então venha ver as maravilhosas ilusões do nosso empório!", "O público se encanta, vibra e urra de alegria", "Ao mesmo tempo que atrapalha seus amigos irrisórios.", "Esta habilidade afeta todos os campos acima de você."},
											   {"Borboleta, urubu ou uma senhora enrugada.", "Os produtos Mary Fay te dão uma aparência convincente.", "Então venha ver nossas revendedoras altamente requisitadas", "Para trazer novas possibilidades a sua mente.", "                                                                                                                      - Empório Purpúrea."}, 
											   {"Aylarianna Purpúrea, a fada mais honesta de Arton", "É encantadora, cheirosa e a própria perfeição.", "Sua aparência inofensiva com alguns toques de batom", "É capaz de ganha seu amor, seu afeto e sua devoção.", "Esta habilidade afeta todos os campos abaixo de você."},
											   {"Você manifesta disparando em seguida", "Uma adaga abstrata.", "Em uma mente desprotegida", "Permanecerá temporariamente cravada.", "Esta habilidade afeta o campo acima de você."},
											   {"Incinere os alvos que aparecem em sua frente,", "Faça a plateia urrar em fervor,", "Ofusque os oponentes com sua luz incandescente", "E inflame a torcida com seu show estarrecedor.", "Esta habilidade afeta todos os campos acima de você."},
											   {"Crie cópias de si mesma com uma perfeição impressionante", "E as espalhe por todos os lados.", "Instaure a confusão em seus atacantes", "Evitando seus golpes inesperados.", "Esta habilidade zera as interferências ganhadas antes da sua ação nessa rodada."}, 
											   {"Descansar é necessário depois de um dia corriqueiro.", "Por suas trocas de \"afeto\" e \"amizade\"", "Você deve um favor aos seus companheiros", "Dando-os um momento de paz e tranquilidade.", "Esta habilidade afeta um campo acima e um abaixo de você."}};

	// 0----------------------------- primeira interação se a Ayla for a escolhida -------------------
	private String [][] ConteudoEscolhaAdversario = {{"- Bom-dia Srta. Ayla e companhia!", "- Os relatórios da produção de bom- batons e a nova versão dos contratos com as \"fontes de", "tamanhos variados\" já estão prontas e na sua mesa como solicitou.", " "},
													 {"- Obrigada Mutuca, continue com o bom trabalho.", "- Venha, vamos para o meu escritório.", " ", " "},
													 {"- Então, eu estava pensando, já que estamos aqui na magnífica cede do Empório Purpúrea, o", "que você acha de realizarmos uma competição de apresentações?", " ", " "},
													 {"- Ah! Que bom que você concordou.", "- Quem sabe, se ganharmos esta partida eu torne você uma das prestigiosas consultoras", "Mary Fay.", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Que pena, eu estava animada para isso.", "- O Mutuca pode fazer um tur pela empresa com você se quiser, tenho certeza que irá se", "maravilhar com os produtos feéricos que vendemos.", "- Ah! E se decidir que quer batalhar é só voltarmos aqui novamente."},
	// 5----------------------------- se a Ayla for a escolhida e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
													 {"- Essas poltronas são tão confortáveis, estava morrendo de vontade de descansar as minhas", "asas.", "- Falando em vontade, já que voltamos, você decidiu se quer competir agora?", " "},
													 {"- Ah! Que bom que você concordou.", "- Quem sabe se ganharmos esta partida eu torne você uma das prestigiosas consultoras", "Mary Fay.", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Que pena, eu estava animada pela batalha.", "- Bem, Vamos passar pela lojinha de lembranças na saída, quero te mostrar as mudanças de", "decorações que fizemos e as medalhas dos deuses que chegaram.", " "},
    // 8----------------------------- se a Ayla for a escolhida e desistiu <= 2 no meio da última luta = |1|0|0|1|3| -------------------
													 {"- Como é bom estar de volta!", "- Não vejo a hora de botar a mão na massa e fazer minhas alqu- poções cheirosas.", "- Depois que eu terminar você vai querer batalhar de novo?", " "},
													 {"- Ótimo! Mas posso te pedir um favorzinho?","- Dessa vez não desista no meio da luta, é meio chato reunir todo mundo daqui das colinas e", "ter o evento encerrado sem conclusão.", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Certo, vamos dar uma volta pelas centrais depois que eu terminar.", "- Fique a vontade para visitar a loja e falar com o Carlos Mutuca Jr., tenho certeza que ele ira", "conseguir te conv- entreter!", " "},
	// 11---------------------------- se a Ayla for a escolhida e desistiu == 3 no meio da última luta = |1|0|0|6|3| -------------------
													 {"- Estamos aqui novamente.","- Dessa vez você realmente quer batalhar?", " ", " "},
													 {"- Vou falar sinceramente agora, então é melhor aproveitar porque esta oportunidade é rara.", "- Não teste minha paciência, posso ser pequena e adorável, mas se você me decepcionar mais", "uma vez... acabou.", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Certo.", " ", " ", " "},
	// 14---------------------------- se a Ayla for a escolhida e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
													 {"- Eu vou falar de uma vez, nossa última disputa em casa não foi muito boa.", "- Cometemos alguns errinhos aqui e ali, mas mesmo não vencendo eu...", "- Tenho boas notícias!", "- EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE!"},
													 {"- Ouve um aumento colossal no interesse pelos produtos Purpúrea e, como uma fada que", "tomou a caridosa missão de compartilhar por um preço a beleza com o mundo, estou muito", "feliz de ter deixado minha terra natal, Pondsmânia, e feito artonianos virem aos montes gastar", "seus tibares no meu empório, fazerem filas quilométricas dando voltas no forte e abrir novas"},
													 {"filiais em toda Arton que eng- ganhão ainda mais.", "- Mas se alguém disser que lucramos 3% menos do que esperávamos e que perdemos feio na", "última luta não acredita porque é tudo mentira.", "- Provavelmente da minha irmã gemia maligna e invejosa."},
													 {"- Então, você vai me ajudar a completar minha missão de pura caridade com fins lucrativos?", " ", " ", " "},
													 {"- EEEEEEEEEEEEEEEEEEEEE!", "- Sabia que poderia contar com você!", " ", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Tudo bem, eu estou bem.", "- Sua resposta não me decepcionou nem um pouco, não sei do que você está falando.", " ", " "},
	// 20---------------------------- se a Ayla for a escolhida e 1º vitória na última luta = |1|1|0|0|1| -------------------
													 {"- Pela esplendorosa vitória que conquistamos, eu, Ayla, resolvi te dar A oportunidade da sua", "vida, nesse caso é se tornar parte da empresa que mais vem crescendo nas colinas centrais!", "- Mas para isso se concretizar você tera que se por a prova e mostrar todo o seu potencial", "vencendo junto a mim três vezes cada um dos Cães das Colinas."},
													 {"- O que me diz, terá orgulho de anunciar para todos que faz parte dessa grandiosa família ou", "dirá que não sabe apreciar uma dádiva quando ela aparece em sua frente?", " ", " "},
													 {"- Que bom que escolheu se juntar a nós!", " ", " ", "As apresentações SEM interferência ganharão -1 de apelo."},
	// 23---------------------------- se a Ayla for a escolhida e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
													 {"- Sente-se por favor.", "- Olha, eu estou começando a suspeitar que me enganei sobre você.", "- Nossa parceria não está dando certo, até que íamos bem, mas de repente, BUM, perdemos", "horrivelmente."},
													 {"- Espera, você está me dizendo que quer outra chance?", "- Nessa empresa damos 110%, nós tramam- trabalhamos enquanto os outros dormem, não", "existe barreiras físicas ou morais que não voamos por cima.", "- Diga-me, você tem o que é preciso?", "- Ótimo, então levanta esta cabeça e grave nossa marca no coração do povo!"},
													 {"- Mas assim, eu to com uma lista de poções para fazer e preciso saber se eu coloco a água", "para ferver agora ou não. Então, vamos ganhar algumas lutas?", " ", " "},
													 {"- Maravilha!", " ", " ", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Tudo bem, te vejo depois para dar uma volta.", " ", " ", " "},
	// 28---------------------------- se a Ayla for a escolhida e 2º vitória na última luta  = |1|2|4|0|1| -------------------
													 {"- Nossa última apresentação foi incrível! Você viu todos aqueles olhares deslumbrados na", "plateia. Nossas vendas aumentaram tanto que Elmer, Grena e Mutuca passaram o dia inteiro", "ocupados.", "- Eu estou tão feliz!"},
													 {"- Entretanto esta quantidade não é o suficiente.", "- Segundo a sub-seção 7 do meu plano de domi- expansão parte 1, o Empório Purpúrea", "precisa de mais 120% de aumento nas vendas.",  " "},
													 {"- Então eu pergunto, vamos dar mais um passo para o seu futuro como parte dessa empresa?", "- Vamos competir mais uma vez em colaboração?", " ", " "},
													 {"- Perfeito!", " ", " ", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Certo.", "- Acredito que me exaltei um pouco hihihihi.", "- O dia está sendo cansativo, então vamos nos falar de novo outra hora.", " "},
	// 33---------------------------- se a Ayla for a escolhida e derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
													 {"- Nossa, dessa vez quase conseguimos, faltou só um pouquinho para ganharmos de todos.", "- Talvez algo mais escandaloso da próxima vez, o que você acha?", " ", " ", " "},
													 {"- QUE ISSO!", "- Eu não esperava tanta malícia saindo de você. Olha, eu gostei.", "- Vamos pôr em prática?", " "},
													 {"- Hihihihihi, sabia que poderia contar com você!", " ", " ", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Sério?", "- Poxa! Me desanimou, outra hora então.", " ", " "},
	// 37---------------------------- se a Ayla for a escolhida e 3º vitória na última luta = |1|3|0|0|1| -------------------
													 {"- Eu ainda estou eufórica com a nossa última luta, demos um show e agora todos, todos", "mesmos, estão ainda mais loucos pelos produtos Purpúrea.", "- Nossa parceria foi uma das melhores ideias que eu já tive e olha que eu tenho várias ideias", "geniais diariamente."},
													 {"- Então, depois de toda essa agitação que tal focarmos em enfrentar os outros cães das Colinas", "nos territórios deles?", "- Ótimo! Vou avisar para o Mutuca que não voltaremos tão cedo.", " "},
	// 39---------------------------- se a Ayla [[[NÃO]]] for a escolhida e primeira interação |0|0|0|0|0 ---------------------------------
													 {"- Minhas boas-vindas a Ayla Corporation, como posso ajudar?", "- Gostariam de falar com a Srta. Ayla?", "- Vejamos...", "- Ayla Corporation, em breve rebranding"},
													 {"- Vocês têm hora marcada?", " ", " ", " "},
													 {"- Muito bem! Me acompanhem por favor.", " ", " ", " "},
													 {"- Minhas sinceras desculpas, mas a Srta. Ayla só recebe com hora marcada.", " ", " ", " "},
													 {"- Olá, vocês estão querendo me ver?", "- Deixe-me adivinhar, você quer se tornar uma consultora de beleza aqui no meu empório.", "- Não?",  " "},
													 {"- HiHiHIhIhIhiHi eu só estava brincando, você quer me desafiar para uma competição, certo?", "- Eu aceito o desafio, mas durante o caminho deixa-me falar sobre os benefícios de se tornar", "consult...", "As apresentações SEM interferência ganharão -1 de apelo."},
	// 45----------------------------- se a Ayla [[[NÃO]]] for a escolhida e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
													 {"- Na saída vocês podem visitar a nossa loja de lembranças, as medalhas dos deuses são bem", "populares com vários clientes.", " ", " "},
    // 46----------------------------- se a Ayla [[[NÃO]]] for a escolhida e desistiu <= 5 no meio da última luta = |1|0|0|1|3| -------------------
													 {"- Olha só vocês aqui de novo! Vieram para mais uma disputa ou estão espionando como eu", "faço minhas alqu- poções lindas e cheirosas que realmente funcionam?", "- Hahahaha a primeira opção, claro.", " "},
													 {"- Eu posso te pedir um favorzinho?","- Dessa vez não desista no meio da luta, é meio chato reunir todo mundo daqui das colinas e", "ter o evento encerrado sem conclusão.", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Mas vocês podem ficar a vontade para olhar os famosos produtos do Empório Purpúrea.", "- Temos uma coleção bem completa de soluções alquímicas para problemas aventurescos,", "tenho certeza que encontraram algo que nem sabiam que precisavam.", " "},
	// 49---------------------------- se a Ayla [[[NÃO]]] for a escolhida e desistiu == 6 no meio da última luta = |1|0|0|6|3| -------------------
													 {"- Já que estamos na minha sala, eu devo perguntar se realmente vocês querem batalhar?", " ", " ", " "},
													 {"- Vou ser sincera agora, então é melhor aproveitarem porque esta oportunidade é rara.", "- Não testem minha paciência, posso ser pequena e adorável, mas se vocês me decepcionarem", "mais uma vez... acabou, sem luta e sem visita.", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Se me permitem, eu gostaria de dar um conselho a vocês.", "- Ninguém gosta muito quando os espetáculos são encerrados no meio sem conclusão, pense", "bem antes de começar um novo.", " "},
	// 52---------------------------- se a Ayla [[[NÃO]]] for a escolhida e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
													 {"- Olá novamente! Vocês estão querendo uma revanche?", "- A última disputa não foi muito boa para o seu lado não é?", "- Diferente de mim, que fui super bem graças ao meu truque secreto.", "- Que truque é esse vocês devem estar se perguntando, bem, é segredo! Hihihihi.", "- Está bem, eu conto. O truque para se dar bem nas lutas é..."},
													 {"- Usar os produtos Purpúrea!", "- O quê? Vocês não estão acreditando em mim, a fada mais honesta de toda Arton.", "- Pois saiba que seres e criaturas das mais simples as mais sofisticadas vem de todo o", "continente gastar seus tibares no meu empório e minhas filiais arreb- arrecadam  ainda mais."},
													 {"- Ah! Que bom que mudou de ideia.", "- O Empório Purpúrea agradece sua preferência e espera que tenham uma boa batalha.", " ", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Mas vocês podem ficar a vontade para conferir nossas poções, unguento e alquimias.", "- A variedade é tão extensa que do plebeu ao arquimago, temos a solução para seu embaraço!", " ", " "},
	// 56---------------------------- se a Ayla [[[NÃO]]] for a escolhida e 1º vitória na última luta = |1|1|0|0|1| -------------------
													 {"- Pela esplendorosa vitória que conquistaram, eu, Ayla, resolvi dar a você A oportunidade da", "sua vida, nesse caso é se tornar parte da empresa que mais vem crescendo nas colinas", "centrais!", "- Mas para isso se concretizar vocês terão que se por a prova e mostrar todo o seu potencial", "vencendo três vezes cada um dos Cães das Colinas."},
													 {"- O que me diz, terá orgulho de anunciar para todos que faz parte dessa grandiosa família ou", "dirá que não sabe apreciar uma dádiva quando ela aparece em sua frente?", " ", " "},
													 {"- Que bom que escolheu se juntar a nós!", " ", " ", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Ah! E meus parabéns pela vitória, espero que traga novos desafios a vocês.", "- Aceitam um café?", " ", " "},
	// 60---------------------------- se a Ayla [[[NÃO]]] for a escolhida e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
													 {"- Sentem-se por favor.", "- Olha, eu estou suspeitando que me enganei sobre vocês. Até que estavam indo bem, mas de", "repente, perderam, horrivelmente. E eu sei que sou incrível e uma oponente difícil de derrotar,", "mas se não forem capazes de passar por mim você não conseguirá um lugar nessa família."},
													 {"- Espera, você está me dizendo que quer outra chance?", "- Nessa empresa damos 110%, nós tramam- trabalhamos enquanto os outros dormem, não", "existe barreiras físicas ou morais que não voamos por cima.", "- Diga-me, você tem o que é preciso?", "- Ótimo, então levanta esta cabeça e me mostrem do que são capazes!", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Mas fiquem a vontade, temos café e uma nova coleção maravilhosa de produtos faciais para", "masmorras e bailes.", "- Veja esse daqui, é o nosso batom mais famoso, se chama \"trilha da alma, seja você um", "espetáculo\", conhecem?"},
	// 63---------------------------- se a Ayla [[[NÃO]]] for a escolhida e 2º vitória na última luta  = |1|2|4|0|1| -------------------
													 {"- Parabéns! Nosso último confronto foi espetacular!", "- Vocês viram todos aqueles olhares deslumbrados na plateia? Graças ao seu uso dos meus", "produtos durante as batalhas nossas vendas aumentaram muito, tanto que Elmer, Grena e", "Mutuca passaram o dia inteiro ocupados. Eu estou tão feliz!"},
													 {"- Entretanto esta quantidade não é o suficiente.", "- Segundo a sub-seção 7 do meu plano de domin- expansão parte 1, o Empório Purpúrea", "precisa de mais 120% de aumento nas vendas.",  " "},
													 {"- Então vamos dar mais um passo para o seu futuro como parte dessa empresa!", "- Vamos competir mais uma vez e explodir de emoções os corações ávidos da plateia!", " ", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Ah! E meus parabéns pela segunda vitória de vocês contra a Srta. Ayla, não é todo mundo", "que é capaz de vencê-la.", "- Antes de irem, aceitam um café, chá ou bolinhos? Quem sabe querem ver nossas novidades?", "- Temos tudo para sua beleza, saúde e bem-estar."},
	// 67---------------------------- se a Ayla [[[NÃO]]] for a escolhida e derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
													 {"- Nossa! Dessa vez vocês quase conseguiram.", "- Faltou só um pouquinho para ganharem de todos.", "- Talvez algo mais escandaloso da próxima vez.", "- Então vamos lá?", "- Eu tenho um público avido me esperando e o trabalho de uma vigar- artista não pode parar.", "As apresentações SEM interferência ganharão -1 de apelo."},
													 {"- Mas em fim, aceitam um café, chá ou bolinhos?", "- Ou gostariam de conhecer o nosso relançamento de poções super concentradas?", "- Agora com a nossa nova fórmula elas são capazes até de fazer goblin virar elfo e bárbaro", "virar bardo."},
	// 69---------------------------- se a Ayla [[[NÃO]]] for a escolhida e 3º vitória na última luta = |1|3|0|0|1| -------------------
													 {"- Minhas boas-vindas ao Empório Purpúrea!", "- A Srta. Ayla está aguardando a presença de vocês em sua sala, prossigam.", " ", " "},
													 {"- Eu ainda estou eufórica com a última luta, vocês deram um show e agora todos, todos", "mesmos, estão ainda mais loucos pelos produtos Purpúrea.", "- Nossa parceria foi uma das melhores ideias que eu já tive e olha que eu tenho várias ideias", "geniais diariamente."},
													 {"- Agora, depois de toda essa agitação eu sugiro que foquem em enfrentar os outros Cães das", "Colinas.", "- Como eu falei anteriormente você só se juntará oficialmente a mundialmente famosa Ayla", "Corporation quando derrotarem todos os cinco Cães das Colinas três vezes.", "- Boa sorte!"},
	// 72---------------------------- se vencer 3 vezes todos os cães das Colinas -------------------
													 {"- Após enfrentar Ignis Crae o paladino de Thyatis, Rexthor o lutador mais sortudo que existe,", "Kiki a barda de puro talento, Arius o filósofo devoto de Tanna-toh e Ayla a fada empreendedora", "mais honesta de Arton, você retorna até nós exibindo suas vitórias com orgulho.", " "},
													 {"- E como prometido, você se tornará um de nós, EEEEEEEEEEEEEEEEE!", "- Agora vamos assinar o pac- contrato.", "- Não precisa ler tudo se não quiser, as letrinhas miúdas são apenas formalidade, HIhiHihi.", "Assinar o contrato?", "- Agora oficialmente você faz parte da família Empório Purpúrea, meus parabéns!"},
	// 74---------------------------- se já venceu 3 vezes a ayla e falou com ela -------------------
													 {"- Por que voltamos? Pensei que iriamos desafiar os outros Cães.", "- Vocês voltaram? Pensei que iriam desafiar os outros Cães.", " ", " "}};


	
	
	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Ayla() {
		sorteio();
	}
	
	public void sorteio () {

		/*for(int i=0; i<8; i++) {
	        mylist.add(i);
		}*/
		
		mylist.add(0); mylist.add(3); mylist.add(5); mylist.add(7);
		
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