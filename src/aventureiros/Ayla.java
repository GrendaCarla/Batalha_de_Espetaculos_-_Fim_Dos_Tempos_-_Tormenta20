package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ayla {
	//                                   apelo,                interfer�ncia,          tipo interfer�ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{2, 5, 4, 4, 1, 6, 0, 1}, {4, 1, 0, 2, 3, 1, 0, 3}, {3, 0, -1, 1, 2, 0, 4, 5}, {0, 0, 0, 0, 0, 0, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Engana��o", "Ilus�o", "Disfarce Ilus�rio", "Apar�ncia Inofensiva", "Adaga Mental", "Explos�o de Chamas", "Imagem Espelhada", "Doces Sonhos"};
	private String [][] ConteudoDescricao = {  {"O que � capaz de vencer o mais forte dos seres", "Do que uma mentirinha bem contada com um bocado de engana��o?", "Com uma mente afiada e planejamentos condizentes", "At� uma fadinha bem charmosa ir� ter sua ascens�o.", "Esta habilidade afeta o primeiro campo."},
											   {"Voc� gosta de cores, brilhos e pirotecnia?", "Ent�o venha ver as maravilhosas ilus�es do nosso emp�rio!", "O p�blico se encanta, vibra e urra de alegria", "Ao mesmo tempo que atrapalha seus amigos irris�rios.", "Esta habilidade afeta todos os campos acima de voc�."},
											   {"Borboleta, urubu ou uma senhora enrugada.", "Os produtos Mary Fay te d�o uma apar�ncia convincente.", "Ent�o venha ver nossas revendedoras altamente requisitadas", "Para trazer novas possibilidades a sua mente.", "                                                                                                                      - Emp�rio Purp�rea."}, 
											   {"Aylarianna Purp�rea, a fada mais honesta de Arton", "� encantadora, cheirosa e a pr�pria perfei��o.", "Sua apar�ncia inofensiva com alguns toques de batom", "� capaz de ganha seu amor, seu afeto e sua devo��o.", "Esta habilidade afeta todos os campos abaixo de voc�."},
											   {"Voc� manifesta disparando em seguida", "Uma adaga abstrata.", "Em uma mente desprotegida", "Permanecer� temporariamente cravada.", "Esta habilidade afeta o campo acima de voc�."},
											   {"Incinere os alvos que aparecem em sua frente,", "Fa�a a plateia urrar em fervor,", "Ofusque os oponentes com sua luz incandescente", "E inflame a torcida com seu show estarrecedor.", "Esta habilidade afeta todos os campos acima de voc�."},
											   {"Crie c�pias de si mesma com uma perfei��o impressionante", "E as espalhe por todos os lados.", "Instaure a confus�o em seus atacantes", "Evitando seus golpes inesperados.", "Esta habilidade zera as interfer�ncias ganhadas antes da sua a��o nessa rodada."}, 
											   {"Descansar � necess�rio depois de um dia corriqueiro.", "Por suas trocas de \"afeto\" e \"amizade\"", "Voc� deve um favor aos seus companheiros", "Dando-os um momento de paz e tranquilidade.", "Esta habilidade afeta um campo acima e um abaixo de voc�."}};

	// 0----------------------------- primeira intera��o se a Ayla for a escolhida -------------------
	private String [][] ConteudoEscolhaAdversario = {{"- Bom-dia Srta. Ayla e companhia!", "- Os relat�rios da produ��o de bom- batons e a nova vers�o dos contratos com as \"fontes de", "tamanhos variados\" j� est�o prontas e na sua mesa como solicitou.", " "},
													 {"- Obrigada Mutuca, continue com o bom trabalho.", "- Venha, vamos para o meu escrit�rio.", " ", " "},
													 {"- Ent�o, eu estava pensando, j� que estamos aqui na magn�fica cede do Emp�rio Purp�rea, o", "que voc� acha de realizarmos uma competi��o de apresenta��es?", " ", " "},
													 {"- Ah! Que bom que voc� concordou.", "- Quem sabe, se ganharmos esta partida eu torne voc� uma das prestigiosas consultoras", "Mary Fay.", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Que pena, eu estava animada para isso.", "- O Mutuca pode fazer um tur pela empresa com voc� se quiser, tenho certeza que ir� se", "maravilhar com os produtos fe�ricos que vendemos.", "- Ah! E se decidir que quer batalhar � s� voltarmos aqui novamente."},
	// 5----------------------------- se a Ayla for a escolhida e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
													 {"- Essas poltronas s�o t�o confort�veis, estava morrendo de vontade de descansar as minhas", "asas.", "- Falando em vontade, j� que voltamos, voc� decidiu se quer competir agora?", " "},
													 {"- Ah! Que bom que voc� concordou.", "- Quem sabe se ganharmos esta partida eu torne voc� uma das prestigiosas consultoras", "Mary Fay.", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Que pena, eu estava animada pela batalha.", "- Bem, Vamos passar pela lojinha de lembran�as na sa�da, quero te mostrar as mudan�as de", "decora��es que fizemos e as medalhas dos deuses que chegaram.", " "},
    // 8----------------------------- se a Ayla for a escolhida e desistiu <= 2 no meio da �ltima luta = |1|0|0|1|3| -------------------
													 {"- Como � bom estar de volta!", "- N�o vejo a hora de botar a m�o na massa e fazer minhas alqu- po��es cheirosas.", "- Depois que eu terminar voc� vai querer batalhar de novo?", " "},
													 {"- �timo! Mas posso te pedir um favorzinho?","- Dessa vez n�o desista no meio da luta, � meio chato reunir todo mundo daqui das colinas e", "ter o evento encerrado sem conclus�o.", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Certo, vamos dar uma volta pelas centrais depois que eu terminar.", "- Fique a vontade para visitar a loja e falar com o Carlos Mutuca Jr., tenho certeza que ele ira", "conseguir te conv- entreter!", " "},
	// 11---------------------------- se a Ayla for a escolhida e desistiu == 3 no meio da �ltima luta = |1|0|0|6|3| -------------------
													 {"- Estamos aqui novamente.","- Dessa vez voc� realmente quer batalhar?", " ", " "},
													 {"- Vou falar sinceramente agora, ent�o � melhor aproveitar porque esta oportunidade � rara.", "- N�o teste minha paci�ncia, posso ser pequena e ador�vel, mas se voc� me decepcionar mais", "uma vez... acabou.", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Certo.", " ", " ", " "},
	// 14---------------------------- se a Ayla for a escolhida e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
													 {"- Eu vou falar de uma vez, nossa �ltima disputa em casa n�o foi muito boa.", "- Cometemos alguns errinhos aqui e ali, mas mesmo n�o vencendo eu...", "- Tenho boas not�cias!", "- EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE!"},
													 {"- Ouve um aumento colossal no interesse pelos produtos Purp�rea e, como uma fada que", "tomou a caridosa miss�o de compartilhar por um pre�o a beleza com o mundo, estou muito", "feliz de ter deixado minha terra natal, Pondsm�nia, e feito artonianos virem aos montes gastar", "seus tibares no meu emp�rio, fazerem filas quilom�tricas dando voltas no forte e abrir novas"},
													 {"filiais em toda Arton que eng- ganh�o ainda mais.", "- Mas se algu�m disser que lucramos 3% menos do que esper�vamos e que perdemos feio na", "�ltima luta n�o acredita porque � tudo mentira.", "- Provavelmente da minha irm� gemia maligna e invejosa."},
													 {"- Ent�o, voc� vai me ajudar a completar minha miss�o de pura caridade com fins lucrativos?", " ", " ", " "},
													 {"- EEEEEEEEEEEEEEEEEEEEE!", "- Sabia que poderia contar com voc�!", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Tudo bem, eu estou bem.", "- Sua resposta n�o me decepcionou nem um pouco, n�o sei do que voc� est� falando.", " ", " "},
	// 20---------------------------- se a Ayla for a escolhida e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
													 {"- Pela esplendorosa vit�ria que conquistamos, eu, Ayla, resolvi te dar A oportunidade da sua", "vida, nesse caso � se tornar parte da empresa que mais vem crescendo nas colinas centrais!", "- Mas para isso se concretizar voc� tera que se por a prova e mostrar todo o seu potencial", "vencendo junto a mim tr�s vezes cada um dos C�es das Colinas."},
													 {"- O que me diz, ter� orgulho de anunciar para todos que faz parte dessa grandiosa fam�lia ou", "dir� que n�o sabe apreciar uma d�diva quando ela aparece em sua frente?", " ", " "},
													 {"- Que bom que escolheu se juntar a n�s!", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
	// 23---------------------------- se a Ayla for a escolhida e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
													 {"- Sente-se por favor.", "- Olha, eu estou come�ando a suspeitar que me enganei sobre voc�.", "- Nossa parceria n�o est� dando certo, at� que �amos bem, mas de repente, BUM, perdemos", "horrivelmente."},
													 {"- Espera, voc� est� me dizendo que quer outra chance?", "- Nessa empresa damos 110%, n�s tramam- trabalhamos enquanto os outros dormem, n�o", "existe barreiras f�sicas ou morais que n�o voamos por cima.", "- Diga-me, voc� tem o que � preciso?", "- �timo, ent�o levanta esta cabe�a e grave nossa marca no cora��o do povo!"},
													 {"- Mas assim, eu to com uma lista de po��es para fazer e preciso saber se eu coloco a �gua", "para ferver agora ou n�o. Ent�o, vamos ganhar algumas lutas?", " ", " "},
													 {"- Maravilha!", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Tudo bem, te vejo depois para dar uma volta.", " ", " ", " "},
	// 28---------------------------- se a Ayla for a escolhida e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
													 {"- Nossa �ltima apresenta��o foi incr�vel! Voc� viu todos aqueles olhares deslumbrados na", "plateia. Nossas vendas aumentaram tanto que Elmer, Grena e Mutuca passaram o dia inteiro", "ocupados.", "- Eu estou t�o feliz!"},
													 {"- Entretanto esta quantidade n�o � o suficiente.", "- Segundo a sub-se��o 7 do meu plano de domi- expans�o parte 1, o Emp�rio Purp�rea", "precisa de mais 120% de aumento nas vendas.",  " "},
													 {"- Ent�o eu pergunto, vamos dar mais um passo para o seu futuro como parte dessa empresa?", "- Vamos competir mais uma vez em colabora��o?", " ", " "},
													 {"- Perfeito!", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Certo.", "- Acredito que me exaltei um pouco hihihihi.", "- O dia est� sendo cansativo, ent�o vamos nos falar de novo outra hora.", " "},
	// 33---------------------------- se a Ayla for a escolhida e derrota na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
													 {"- Nossa, dessa vez quase conseguimos, faltou s� um pouquinho para ganharmos de todos.", "- Talvez algo mais escandaloso da pr�xima vez, o que voc� acha?", " ", " ", " "},
													 {"- QUE ISSO!", "- Eu n�o esperava tanta mal�cia saindo de voc�. Olha, eu gostei.", "- Vamos p�r em pr�tica?", " "},
													 {"- Hihihihihi, sabia que poderia contar com voc�!", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- S�rio?", "- Poxa! Me desanimou, outra hora ent�o.", " ", " "},
	// 37---------------------------- se a Ayla for a escolhida e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
													 {"- Eu ainda estou euf�rica com a nossa �ltima luta, demos um show e agora todos, todos", "mesmos, est�o ainda mais loucos pelos produtos Purp�rea.", "- Nossa parceria foi uma das melhores ideias que eu j� tive e olha que eu tenho v�rias ideias", "geniais diariamente."},
													 {"- Ent�o, depois de toda essa agita��o que tal focarmos em enfrentar os outros c�es das Colinas", "nos territ�rios deles?", "- �timo! Vou avisar para o Mutuca que n�o voltaremos t�o cedo.", " "},
	// 39---------------------------- se a Ayla [[[N�O]]] for a escolhida e primeira intera��o |0|0|0|0|0 ---------------------------------
													 {"- Minhas boas-vindas a Ayla Corporation, como posso ajudar?", "- Gostariam de falar com a Srta. Ayla?", "- Vejamos...", "- Ayla Corporation, em breve rebranding"},
													 {"- Voc�s t�m hora marcada?", " ", " ", " "},
													 {"- Muito bem! Me acompanhem por favor.", " ", " ", " "},
													 {"- Minhas sinceras desculpas, mas a Srta. Ayla s� recebe com hora marcada.", " ", " ", " "},
													 {"- Ol�, voc�s est�o querendo me ver?", "- Deixe-me adivinhar, voc� quer se tornar uma consultora de beleza aqui no meu emp�rio.", "- N�o?",  " "},
													 {"- HiHiHIhIhIhiHi eu s� estava brincando, voc� quer me desafiar para uma competi��o, certo?", "- Eu aceito o desafio, mas durante o caminho deixa-me falar sobre os benef�cios de se tornar", "consult...", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
	// 45----------------------------- se a Ayla [[[N�O]]] for a escolhida e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
													 {"- Na sa�da voc�s podem visitar a nossa loja de lembran�as, as medalhas dos deuses s�o bem", "populares com v�rios clientes.", " ", " "},
    // 46----------------------------- se a Ayla [[[N�O]]] for a escolhida e desistiu <= 5 no meio da �ltima luta = |1|0|0|1|3| -------------------
													 {"- Olha s� voc�s aqui de novo! Vieram para mais uma disputa ou est�o espionando como eu", "fa�o minhas alqu- po��es lindas e cheirosas que realmente funcionam?", "- Hahahaha a primeira op��o, claro.", " "},
													 {"- Eu posso te pedir um favorzinho?","- Dessa vez n�o desista no meio da luta, � meio chato reunir todo mundo daqui das colinas e", "ter o evento encerrado sem conclus�o.", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Mas voc�s podem ficar a vontade para olhar os famosos produtos do Emp�rio Purp�rea.", "- Temos uma cole��o bem completa de solu��es alqu�micas para problemas aventurescos,", "tenho certeza que encontraram algo que nem sabiam que precisavam.", " "},
	// 49---------------------------- se a Ayla [[[N�O]]] for a escolhida e desistiu == 6 no meio da �ltima luta = |1|0|0|6|3| -------------------
													 {"- J� que estamos na minha sala, eu devo perguntar se realmente voc�s querem batalhar?", " ", " ", " "},
													 {"- Vou ser sincera agora, ent�o � melhor aproveitarem porque esta oportunidade � rara.", "- N�o testem minha paci�ncia, posso ser pequena e ador�vel, mas se voc�s me decepcionarem", "mais uma vez... acabou, sem luta e sem visita.", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Se me permitem, eu gostaria de dar um conselho a voc�s.", "- Ningu�m gosta muito quando os espet�culos s�o encerrados no meio sem conclus�o, pense", "bem antes de come�ar um novo.", " "},
	// 52---------------------------- se a Ayla [[[N�O]]] for a escolhida e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
													 {"- Ol� novamente! Voc�s est�o querendo uma revanche?", "- A �ltima disputa n�o foi muito boa para o seu lado n�o �?", "- Diferente de mim, que fui super bem gra�as ao meu truque secreto.", "- Que truque � esse voc�s devem estar se perguntando, bem, � segredo! Hihihihi.", "- Est� bem, eu conto. O truque para se dar bem nas lutas �..."},
													 {"- Usar os produtos Purp�rea!", "- O qu�? Voc�s n�o est�o acreditando em mim, a fada mais honesta de toda Arton.", "- Pois saiba que seres e criaturas das mais simples as mais sofisticadas vem de todo o", "continente gastar seus tibares no meu emp�rio e minhas filiais arreb- arrecadam  ainda mais."},
													 {"- Ah! Que bom que mudou de ideia.", "- O Emp�rio Purp�rea agradece sua prefer�ncia e espera que tenham uma boa batalha.", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Mas voc�s podem ficar a vontade para conferir nossas po��es, unguento e alquimias.", "- A variedade � t�o extensa que do plebeu ao arquimago, temos a solu��o para seu embara�o!", " ", " "},
	// 56---------------------------- se a Ayla [[[N�O]]] for a escolhida e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
													 {"- Pela esplendorosa vit�ria que conquistaram, eu, Ayla, resolvi dar a voc� A oportunidade da", "sua vida, nesse caso � se tornar parte da empresa que mais vem crescendo nas colinas", "centrais!", "- Mas para isso se concretizar voc�s ter�o que se por a prova e mostrar todo o seu potencial", "vencendo tr�s vezes cada um dos C�es das Colinas."},
													 {"- O que me diz, ter� orgulho de anunciar para todos que faz parte dessa grandiosa fam�lia ou", "dir� que n�o sabe apreciar uma d�diva quando ela aparece em sua frente?", " ", " "},
													 {"- Que bom que escolheu se juntar a n�s!", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Ah! E meus parab�ns pela vit�ria, espero que traga novos desafios a voc�s.", "- Aceitam um caf�?", " ", " "},
	// 60---------------------------- se a Ayla [[[N�O]]] for a escolhida e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
													 {"- Sentem-se por favor.", "- Olha, eu estou suspeitando que me enganei sobre voc�s. At� que estavam indo bem, mas de", "repente, perderam, horrivelmente. E eu sei que sou incr�vel e uma oponente dif�cil de derrotar,", "mas se n�o forem capazes de passar por mim voc� n�o conseguir� um lugar nessa fam�lia."},
													 {"- Espera, voc� est� me dizendo que quer outra chance?", "- Nessa empresa damos 110%, n�s tramam- trabalhamos enquanto os outros dormem, n�o", "existe barreiras f�sicas ou morais que n�o voamos por cima.", "- Diga-me, voc� tem o que � preciso?", "- �timo, ent�o levanta esta cabe�a e me mostrem do que s�o capazes!", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Mas fiquem a vontade, temos caf� e uma nova cole��o maravilhosa de produtos faciais para", "masmorras e bailes.", "- Veja esse daqui, � o nosso batom mais famoso, se chama \"trilha da alma, seja voc� um", "espet�culo\", conhecem?"},
	// 63---------------------------- se a Ayla [[[N�O]]] for a escolhida e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
													 {"- Parab�ns! Nosso �ltimo confronto foi espetacular!", "- Voc�s viram todos aqueles olhares deslumbrados na plateia? Gra�as ao seu uso dos meus", "produtos durante as batalhas nossas vendas aumentaram muito, tanto que Elmer, Grena e", "Mutuca passaram o dia inteiro ocupados. Eu estou t�o feliz!"},
													 {"- Entretanto esta quantidade n�o � o suficiente.", "- Segundo a sub-se��o 7 do meu plano de domin- expans�o parte 1, o Emp�rio Purp�rea", "precisa de mais 120% de aumento nas vendas.",  " "},
													 {"- Ent�o vamos dar mais um passo para o seu futuro como parte dessa empresa!", "- Vamos competir mais uma vez e explodir de emo��es os cora��es �vidos da plateia!", " ", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Ah! E meus parab�ns pela segunda vit�ria de voc�s contra a Srta. Ayla, n�o � todo mundo", "que � capaz de venc�-la.", "- Antes de irem, aceitam um caf�, ch� ou bolinhos? Quem sabe querem ver nossas novidades?", "- Temos tudo para sua beleza, sa�de e bem-estar."},
	// 67---------------------------- se a Ayla [[[N�O]]] for a escolhida e derrota na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
													 {"- Nossa! Dessa vez voc�s quase conseguiram.", "- Faltou s� um pouquinho para ganharem de todos.", "- Talvez algo mais escandaloso da pr�xima vez.", "- Ent�o vamos l�?", "- Eu tenho um p�blico avido me esperando e o trabalho de uma vigar- artista n�o pode parar.", "As apresenta��es SEM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Mas em fim, aceitam um caf�, ch� ou bolinhos?", "- Ou gostariam de conhecer o nosso relan�amento de po��es super concentradas?", "- Agora com a nossa nova f�rmula elas s�o capazes at� de fazer goblin virar elfo e b�rbaro", "virar bardo."},
	// 69---------------------------- se a Ayla [[[N�O]]] for a escolhida e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
													 {"- Minhas boas-vindas ao Emp�rio Purp�rea!", "- A Srta. Ayla est� aguardando a presen�a de voc�s em sua sala, prossigam.", " ", " "},
													 {"- Eu ainda estou euf�rica com a �ltima luta, voc�s deram um show e agora todos, todos", "mesmos, est�o ainda mais loucos pelos produtos Purp�rea.", "- Nossa parceria foi uma das melhores ideias que eu j� tive e olha que eu tenho v�rias ideias", "geniais diariamente."},
													 {"- Agora, depois de toda essa agita��o eu sugiro que foquem em enfrentar os outros C�es das", "Colinas.", "- Como eu falei anteriormente voc� s� se juntar� oficialmente a mundialmente famosa Ayla", "Corporation quando derrotarem todos os cinco C�es das Colinas tr�s vezes.", "- Boa sorte!"},
	// 72---------------------------- se vencer 3 vezes todos os c�es das Colinas -------------------
													 {"- Ap�s enfrentar Ignis Crae o paladino de Thyatis, Rexthor o lutador mais sortudo que existe,", "Kiki a barda de puro talento, Arius o fil�sofo devoto de Tanna-toh e Ayla a fada empreendedora", "mais honesta de Arton, voc� retorna at� n�s exibindo suas vit�rias com orgulho.", " "},
													 {"- E como prometido, voc� se tornar� um de n�s, EEEEEEEEEEEEEEEEE!", "- Agora vamos assinar o pac- contrato.", "- N�o precisa ler tudo se n�o quiser, as letrinhas mi�das s�o apenas formalidade, HIhiHihi.", "Assinar o contrato?", "- Agora oficialmente voc� faz parte da fam�lia Emp�rio Purp�rea, meus parab�ns!"},
	// 74---------------------------- se j� venceu 3 vezes a ayla e falou com ela -------------------
													 {"- Por que voltamos? Pensei que iriamos desafiar os outros C�es.", "- Voc�s voltaram? Pensei que iriam desafiar os outros C�es.", " ", " "}};


	
	
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