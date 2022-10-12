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

	// 0---------------------------- primeira interação se o Ignis for o escolhido ---------------------- 
	private String [][] ConteudoEscolhaAdversario = {{"- Aqui estamos, essa é a cede da Ordem da Redenção.", "- Você gostaria de um pouco de leite ou queijo das cabras do rebanho da ordem?", "- Ou gostaria de descansar um pouco, já que percorremos um longo caminho?", " "},
													 {"- Vejo que ainda dispõem de muita energia, talvez você prefira participar de uma daquelas", "competições que a Ayla criou?", " ", " "},
													 {"- Você me parece gostar bastante dessa atividade, devo dizer que eu mesmo não compreendo", "ou concordo totalmente com os métodos utilizados pela fada, mas admiro muito a alegria que", "ela traz para o povo dessas colinas.", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Então fique a vontade para explorar o local.", "- Se me acompanhar poderei lhe apesentar aos membros ou se preferir pode ficar aqui e fazer", "carinho no rebanho.", " "},
	// 4---------------------------- se o Ignis for o escolhido e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
													 {"...", " ", " ", " "},
													 {"- Vejo que você reparou na reforma que eu fiz.", "- O que achou?", "- Meu último procedimento não estava mais me valorizando, então resolvi dar uma aumentada.", " "},
													 {"- Você está dizendo que preciso de mais queixos em outros lugares do meu corpo?", "- Vou pensar a respeito.", " ", " "},
													 {"- Agora que retornamos, seria um bom momento para darmos o próximo passo da sua jornada.", "- O que me diz?", " ", " "},
													 {"- Então conte comigo para ser seu escudo.", " ", " ", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Compreendo, você deve ter outros objetivos em mente primeiro.", " ", " ", " "},
    // 10--------------------------- se o Ignis for o escolhido e desistiu == 1 no meio da última luta = |1|0|0|1|3| -------------------
													 {"- Precisamos ter uma conversa séria agora.", " ", " ", " "},
													 {"- O que você fez foi totalmente desrespeitoso.", "- Você abandonou seus deveres no meio do evento por um motivo tão simplório e nem cogitou", "como isso afetaria todos que separaram uma parte de seu tempo e energia para comparecer e", "realizar as apresentações."},
													 {"- Como paladino de Thyatis eu lhe darei outra chance para que se redima de seus atos, mas", "lembre, Thyatis é o deus das segundas chances, não das terceiras.", " ", "As apresentações COM interferência ganharão -1 de apelo."},
	// 13--------------------------- se o Ignis for o escolhido e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
													 {"- Sua primeira estadia aqui foi proveitosa, você conheceu alguns integrantes da ordem, se", "divertiu brincando com as cabras e provou os nossos queijos, mas creio que chegou a hora de", "levarmos essa competição a outro nível.", " "},
													 {"- Eu apresento a você meu MEGA HIPER BLASTER PLANO DE COMBOS SUPREMO.", "- Com ele seremos capazes de maximizar a nossa eficácia nas batalhas aproveitando todo o", "nosso potencial.", " "},
													 {"- Embarque comigo nesse caminho sem volta e faça eles sentirem o verdadeiro significado do", "apavoro.", " ", "As apresentações COM interferência ganharão -1 de apelo."},
	// 16--------------------------- se o Ignis for o escolhido e 1º vitória na última luta = |1|1|0|0|1| -------------------
													 {"- Parabéns, você conseguiu, digo, nós conseguimos nossa primeira vitória.", "- Devo dizer que não estava muito empolgado com essa brincadeira no começo, mas agora", "entendo porque todos acham tão divertido essas apresentações e batalha despropositadas.", " "},
													 {"- A nobre e refinada arte do combo realmente é uma ferramenta incrível, revigorante e devo", " dizer até viciante, agora entendo o porquê de exercitá-las nesses jogos.", " ", " "},
													 {"- Então depois da nossa vitória você ainda deseja outra disputa?", " ", " ", " "},
													 {"- Então vamos, ainda temos muitas combinações para pôr em prática.", " ", " ", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Bem, talvez com esse tempo deveríamos nos preparar mais.", "- Gostaria de ver os novos estratagemas que elaborei? Garanto que são desafios de nível", "mitológico.", " "},
	// 21--------------------------- se o Ignis for o escolhido e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
													 {"- Nós perdemos nossa ultima batalha como anfitriões e eu sei que a perda pode desanimar e", "afetar sua vontade de prosseguir, mas lembre-se que teremos muito mais derrotas pela frente", "e isso não é ruim.", " "},
													 {"- Perder não é algo bom ou ruim, ela só é a consequência de inúmeros fatores que envolve até", "mesmo a sorte, Nimb ou as deusas, como preferir.", " ", " "},
													 {"- Por agora sugiro que analisemos nossa batalha para podermos aprender com nossos erros e", "depois daremos início a outra tentativa.", " ", " "},
													 {"- O que me diz, vamos dar mais uma chance a nossa vitória?", " ", " ", " "},
													 {"- Que bom, fico feliz com sua atitude.", " ", " ", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Bem, talvez você precise de mais tempo.", " ", " ", " "},
	// 27--------------------------- se o Ignis for o escolhido e 2º vitória na última luta  = |1|2|4|0|1| -------------------
													 {"- Rexthor, acredito que deveríamos achar o Meia-Pata ao invés do alambiqueiro, podemos", "localizá-lo mais facilmente com ajuda do Jonid.", " ", " "},
													 {"- Ignis, você não sabe o que diz, eu e o Arius somos os melhores rastreadores da equipe, deixa", "comigo.", "- O Mungo Joe só fabrica bebida e não vale a pena ir atrás de calorias vazias.", " "},
													 {"- Temos que encontrar o Meia-Pata primeiro e caso ele não seja muito amigável teremos", "bastante proteína para a semana.", "- E você não está com o Homem-Bode? Ele pode saber aonde o animal costuma ficar.", " "},
													 {"- FOI O QUE EU ACABEI DE FALAR!", " ", " ", " "},
													 {"- Se você não consegue compreender um simples dialogo, eu posso de dar algumas aulas de ", "oratória.", "- Creio que esse comportamento é normal para pessoas que não estudaram desde cedo, então", "eu te dou uma segunda chance."},
													 {"- Me desculpa se eu não pude ter uma educação porque fui roubado da minha vila e forçado a", "lutar desde moleque.", " ", " "},
													 {"- Vamos encerrar nossa discussão por enquanto, eu tenho tarefas mais importantes agora, com", "licença.", " ", " "},
													 {"- Me desculpe pela balbúrdia que presenciou.", "- Contudo, você veio em boa hora para continuarmos a nossa jornada, o que você acha?", " ", " "},{"- Vamos, nosso caminho não será fácil.", "- Rexthor! Eu vou fazer os preparativos para a próxima luta, é melhor você não assar nenhuma", "cabra na minha ausência.", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Se é o que juga melhor.", " ", " ", " "},
													 {"- Você sabe que eu te amo não é Ignis?", "- Hahahahaha.", " ", " ", "- Opa! Me veio uma ideia agora."},
													 {"- Que tal apresentarmos a Kiki ao Mungo Joe?", "- Talvez ao ver a quantidade de cachaça que ela consegue beber, ele tenha uma crise existencial", "e decida se juntar a sua ordem para fazer algo mais significativo, hóstia, por exemplo, não é", "uma ideia brilhante?"},
													 {"- Nós não vamos destruir a vida desse homem.", " ", " ", " "},
													 {"- Ele irá te agradecer depois, pensa bem.", " ", " ", " "},
	// 41--------------------------- se o Ignis for o escolhido e derrotado na última luta com 2 vitórias = |1|2|1|0|2| -------------------
													 {"- Sagrada Fenix, lucide minha mente com seu dom profético, devemos buscar como primeira", "opção o ser conhecido como Meia-Pata?", "- Não?!", " "},
													 {"- Não? Como assim?", "- Queima mais PM ai Ignis, agora temos que saber as informações por completo.", " ", " "},
													 {"- Ave das chamas eternas, você me aconselhou a ir primeiro até Mungo Joe, por favor revele-me", "o porquê.", "- Ele consegue preparar bebidas que recuperam PM e tem um estoque cheio delas!", " ", " "},
													 {"- Que se dane o bode, nós vamos é buscar a Kiki para acabar com a mente dele é agora!", " ", " ", " "},
													 {"- Calma Ignis, eu estou com você nesse plano, mas você tem outras obrigações agora, veja.", " ", " ", " "},
													 {"- Oh! Eu não reparei que estava aqui.", "- Desculpe minha exaltação, eu deixei me levar pela empolgação.", " ", " "},
													 {"- Você já se decidiu?", "- Ótimo, então eu presumo que iremos competir, certo?", " ", " "},
													 {"- Então vamos, temos muito o que fazer.", "- Rexthor, você já sabe.", " ", " "},
													 {"- Eu não estava esperando essa resposta.", "- Mas se é isso que deseja não vejo porque continuarmos estagnados aqui, vamos buscar a Kiki.", " ", " "},
													 {"- Eu estou parando aqui para pensar Ignis.", "- Talvez não seja a melhor ideia levarmos ela, essas bebidas com PM vão acabar levando a Kiki", "mais cedo para cova com a cirrose que ela vai ganhar.", " "},
	// 51--------------------------- se o Ignis for o escolhido e 3º vitória na última luta = |1|3|0|0|1| -------------------
													 {"- Venha cá, tenho alguém para lhe apresentar.", " ", " ", " "},
													 {" ", " ", " ", " "},
													 {"- Esse aqui é o Rito, meu parceiro mecânico.", "- Da oi filho.", " ", " "},
													 {"- Au!", " ", " ", " "},
													 {"- Parece que ele gostou de você.", "- Eu não tinha apresentado-o ainda porque ele esteve fora em missão com Nico Luriel, o", "mecâniculo do Arius.", " "},
													 {"- Agora que conseguimos as nossas três vitórias não deveríamos deixar esse momento passar", "em branco.", "- Então... o que você acha de nós três irmos até Dornbach bater em puristas?", " "},
													 {"- Ótimo, eu fico com as crianças e idosos!", " ", " ", " "},
	// 58--------------------------- se o Ignis [[[NÃO]]] for o escolhido e primeira interação |0|0|0|0|0 ---------------------------------
													 {"- Boa-tarde!", "- Elmer me disse que vocês desejam falar comigo sobre algo?", "- Gostariam de um pouco de leite ou queijo das cabras do rebanho da ordem?", "- Ou gostariam de descansar um pouco, já que percorreram um longo caminho?"},
													 {"- Vejo que vocês ainda dispõem de muita energia, talvez prefiram participar de uma daquelas", "competições que a Ayla criou?", "- Vejo que vocês ainda dispõem de muita energia, talvez prefiram participar de uma das", "competições que você criou Ayla?"},
													 {"- Você me parece gostar bastante dessa atividade, devo dizer que eu mesmo não compreendo", "ou concordo totalmente com os métodos utilizados pela fada, mas admiro muito a alegria que", "ela traz para o povo dessas colinas.", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Então fiquem a vontade para explorar o local.", "- Se me acompanharem poderei lhes apesentar aos membros ou se preferirem podem ficar aqui", "e fazer carinho no rebanho.", " "},
	// 62--------------------------- se o Ignis [[[NÃO]]] for o escolhido e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
													 {"...", " ", " ", " "},
													 {"- Vejo que vocês repararam na reforma que eu fiz.", "- O que acharam?", "- Meu último procedimento não estava mais me valorizando, então resolvi dar uma aumentada.", " "},
													 {"- Vocês estão dizendo que preciso de mais queixos em outros lugares do meu corpo?", "- Vou pensar a respeito.", " ", " "},
													 {"- Agora que retornaram, seria um bom momento para darem o próximo passo da jornada.", "- O que me dizem?", " ", " "},
													 {"- Boa escolha.", "- Mas antes de tudo um conselho, evitem olhar diretamente para o meu belo maxilar com os", "olhos desprotegidos, a ofuscação pode ser mais difícil de lidar do que o meu escudo.", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Compreendo, vocês devem ter outros objetivos em mente primeiro.", " ", " ", " "},
	// 68-------------------------- se o Ignis [[[NÃO]]] for o escolhido e desistiu == 1 no meio da última luta = |1|0|0|1|3| -------------------	
													 {"- Precisamos ter uma conversa séria agora.", " ", " ", " "},
													 {"- O que você fez foi totalmente desrespeitoso.", "- Você abandonou seus deveres no meio do evento por um motivo tão simplório e nem cogitou", "como isso afetaria todos que separaram uma parte de seu tempo e energia para comparecer e", "realizar as apresentações."},
													 {"- Como paladino de Thyatis eu lhe darei outra chance para que se redima de seus atos, mas", "lembre, Thyatis é o deus das segundas chances, não das terceiras.", " ", "As apresentações COM interferência ganharão -1 de apelo."},
	// 71-------------------------- se o Ignis [[[NÃO]]] for o escolhido e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
													 {"- A primeira estadia de vocês aqui foi proveitosa, conheceram alguns dos integrantes da ordem,", "se divertiram brincando com as cabras e provaram os nossos queijos, mas creio que chegou a", "hora de levarem essa competição a outro nível.", " "},
													 {"- Eu apresento a vocês meu MEGA HIPER BLASTER PLANO DE COMBOS SUPREMO.", "- Com ele serei capaz de maximizar a minha eficácia nas batalhas aproveitando todo o meu", "potencial.", " "},
													 {"- Embarquem comigo nesse caminho sem volta e sintam o verdadeiro significado do apavoro.", " ", " ", "As apresentações COM interferência ganharão -1 de apelo."},
	// 74-------------------------- se o Ignis [[[NÃO]]] for o escolhido e 1º vitória na última luta = |1|1|0|0|1| -------------------
													 {"- Parabéns, conseguiram a primeira vitória de vocês.", "- Devo dizer que não estava muito empolgado com essa brincadeira no começo, mas agora", "entendo porque todos acham tão divertido essas apresentações e batalha despropositadas.", " "},
													 {"- A nobre e refinada arte do combo realmente é uma ferramenta incrível, revigorante e devo", " dizer até viciante, agora entendo o porquê de exercitá-las nesses jogos.", " ", " "},
													 {"- Então depois dessa vitória vocês ainda desejam outra disputa?", " ", " ", " "},
													 {"- Então avante, pois ainda tenho muitas combinações para pôr em prática contra vocês.", " ", " ", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Bem, talvez com esse tempo vocês deveriam se preparar mais.", "- Os novos estratagemas que elaborei serão um desafio nível mitológico.", " ", " "},
	// 79-------------------------- se o Ignis [[[NÃO]]] for o escolhido e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
													 {"- Na última batalha contra mim vocês perderam feio e eu sei que a perda pode desanimar e", "afetar a vontade de prosseguir, mas lembre-se que terão muito mais derrotas pela frente e isso", "não é ruim.", " "},
													 {"- Perder não é algo bom ou ruim, ela só é a consequência de inúmeros fatores que envolve até", "mesmo a sorte, Nimb ou as deusas, como preferirem.", " ", " "},
													 {"- Por agora sugiro que analisem nossa última batalha para que possam aprender com os seus", "e os meus erros.", " ", " "},
													 {"- O que me dizem, vão dar mais uma chance a vitória de vocês?", " ", " ", " "},
													 {"- Que bom, fico feliz com a atitude que tomaram.", " ", " ", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Bem, talvez vocês precisem de mais tempo.", " ", " ", " "},
	// 85-------------------------- se o Ignis [[[NÃO]]] for o escolhido e 2º vitória na última luta  = |1|2|4|0|1| -------------------
													 {"- Rexthor, acredito que deveríamos achar o Meia-Pata ao invés do alambiqueiro, podemos", "localizá-lo mais facilmente com ajuda do Jonid.", " ", " "},
													 {"- Ignis, você não sabe o que diz, eu e o Arius somos os melhores rastreadores da equipe, deixa", "comigo.", "- O Mungo Joe só fabrica bebida e não vale a pena ir atrás de calorias vazias.", " "},
													 {"- Temos que encontrar o Meia-Pata primeiro e caso ele não seja muito amigável teremos", "bastante proteína para a semana.", "- E você não está com o Homem-Bode? Ele pode saber aonde o animal costuma ficar.", " "},
													 {"- FOI O QUE EU ACABEI DE FALAR!", " ", " ", " "},
													 {"- Se você não consegue compreender um simples dialogo, eu posso de dar algumas aulas de ", "oratória.", "- Creio que esse comportamento é normal para pessoas que não estudaram desde cedo, então", "eu te dou uma segunda chance."},
													 {"- Me desculpa se eu não pude ter uma educação porque fui roubado da minha vila e forçado a", "lutar desde moleque.", " ", " "},
													 {"- Vamos encerrar nossa discussão por enquanto, eu tenho tarefas mais importantes agora, com", "licença.", " ", " "},
													 {"- Me desculpem pela balbúrdia que tiveram que presenciar.", "- Contudo, vocês vieram em boa hora para continuarmos a nossa disputa, o que acham?", " ", " "},
													 {"- Vamos, temos muito o que arrumar.", "- Rexthor! Eu vou fazer os preparativos para a próxima luta, é melhor você não assar nenhuma", "cabra na minha ausência.", "As apresentações COM interferência ganharão -1 de apelo."},
													 {"- Se é o que jugam melhor.", " ", " ", " "},
													 {"- Você sabe que eu te amo não é Ignis?", "- Hahahahaha.", " ", " ", "- Opa! Me veio uma ideia agora."},
													 {"- Que tal apresentarmos a Kiki ao Mungo Joe?", "- Talvez ao ver a quantidade de cachaça que ela consegue beber, ele tenha uma crise existencial", "e decida se juntar a sua ordem para fazer algo mais significativo, hóstia, por exemplo, não é", "uma ideia brilhante?"},
													 {"- Nós não vamos destruir a vida desse homem.", " ", " ", " "},
													 {"- Ele irá te agradecer depois, pensa bem.", " ", " ", " "},
	// 99-------------------------- se o Ignis [[[NÃO]]] for o escolhido e derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
													 {"- Sagrada Fenix, lucide minha mente com seu dom profético, devemos buscar como primeira", "opção o ser conhecido como Meia-Pata?", "- Não?!", " "},
													 {"- Não? Como assim?", "- Queima mais PM ai Ignis, agora temos que saber as informações por completo.", " ", " "},
													 {"- Ave das chamas eternas, você me aconselhou a ir primeiro até Mungo Joe, por favor revele-me", "o porquê.", "- Ele consegue preparar bebidas que recuperam PM e tem um estoque cheio delas!", " ", " "},
													 {"- Que se dane o bode, nós vamos é buscar a Kiki para acabar com a mente dele é agora!", " ", " ", " "},
													 {"- Calma Ignis, eu estou com você nesse plano, mas você tem outras obrigações agora, veja.", " ", " ", " "},
													 {"- Oh! Eu não reparei que estavam aqui.", "- Desculpem minha exaltação, eu deixei me levar pelas possibilidades.", " ", " "},
													 {"- Vocês já se decidiram?", "- Ótimo, então eu presumo que iremos competir, certo?", " ", " "},
													 {"- Então vamos, temos muito o que fazer.", "- Rexthor, você já sabe.", " ", " "},
													 {"- Eu não estava esperando essa resposta.", "- Mas se é isso que desejam não vejo porque continuarmos estagnados aqui, vamos buscar a", "- Mas se é isso que desejam não vejo porque continuarmos estagnados aqui, vamos Kiki.", "Kiki.", " "},
													 {"- Eu estou parando aqui para pensar Ignis.", "- Talvez não seja a melhor ideia levarmos ela, essas bebidas com PM vão acabar levando a Kiki", "mais cedo para cova com a cirrose que ela vai ganhar.", " "},
	// 109-------------------------- se o Ignis [[[NÃO]]] for o escolhido e 3º vitória na última luta = |1|3|0|0|1| -------------------
													 {"- Venha cá, tenho alguém para lhe apresentar.", " ", " ", " "},
													 {" ", " ", " ", " "},
													 {"- Esse aqui é o Rito, meu parceiro mecânico.", "- Da oi filho.", " ", " "},
													 {"- Au!", " ", " ", " "},
													 {"- Parece que ele gostou de você.", "- Eu não tinha apresentado-o ainda porque ele esteve fora em missão com Nico Luriel, o", "mecâniculo do Arius.", " "},
													 {"- Agora que conseguiram as três vitórias não deveríamos deixar esse momento passar", "em branco.", "- Então... o que vocês acham de nós quatro irmos até Dornbach bater em puristas?", " "},
													 {"- Ótimo, eu fico com as crianças e idosos!", " ", " ", " "},
	// 116--------------------------- se já venceu 3 vezes o Ignis e falou com ele -------------------
													 {"- Por que voltamos? Pensei que iriamos desafiar os outros Cães.", "- Vocês voltaram? Pensei que iriam desafiar os outros Cães.", " ", " "}};

	
	

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