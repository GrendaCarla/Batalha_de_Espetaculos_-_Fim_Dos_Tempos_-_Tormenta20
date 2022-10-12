package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Ignis {
	//									 apelo,                interfer�ncia,          tipo interfer�ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{6, 0, 7, 4, 5, 3, 3, 5}, {0, 0, 0, 2, 0, 0, 2, 0}, {-1, 4, -1, 0, -1, -1, 5, -1}, {0, 0, 1, 0, 1, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Discurso no Jutsu", "Canalizar Reparos", "Tapa em Puristas", "Ignis Bonit�o", "Intoc�vel", "P� no Peito e Escudada na Cara", "Provoca��o Petulante", "Meca-Rito"};
	private String [][] ConteudoDescricao = {{"Esta � tua deixa, � paladino redentor das chamas.", "Em caminhos tortos e aos trope�os teus inimigos percorrem.", "Reflexivo �s teu canto que nova oportunidade proclamas.", "Reverbere-o para que os terr�veis futuros impossibilidade apenas se tornem.", " "},
											 {"Se em teu ferroso corpo a��es forem desferidas", "O marcando com ranhuras e amassados,", "Ent�o em seu cerne a escaldante chama deve ser expelida", "Para regredir as lacunas at� teu completo reparo.", "Esta habilidade zera as interfer�ncias ganhada antes da sua a��o nessa rodada."},
											 {"De ideias distorcidas e atos vis s�o alimentados.", "Mesmo com tamanha ojeriza ofereceu-lhes a reden��o.", "Ao se recusarem crian�as e idosos n�o foram poupados.", "Brandindo o escudo e o fu� sucedeu-lhes somente a r�spida puni��o.", " "}, 
											 {"N�o h� tempo a ser perdido,", "Todos anseiam sua apari��o.", "Bem formoso, harm�nico e esculpido", "Ficou teu queixo, � bonit�o.", "Esta habilidade afeta todos os campos acima de voc�."},
											 {"Desafie a todos que quiserem a prova tirar pessoalmente", "Das hist�rias sobre sua defesa robusta e imaculada.", "Venham ferozes ao teu escudo de encontro a ave ardente", "Para que a contagem cont�nua possa em um ser aumentada.", " "},
											 {"V�s sabeis que preferis a defesa aos ataques danosos,", "Mas em alguns casos a for�a h� de ser usada,", "Como em zumbis da Tormenta e puristas odiosos,", "Mas em outras situa��es s� machuca, n�o mata.", " "},
											 {"Provoque todos ao alcance que inimizades tens com vosco.", "Fa�a gestos chamativos e gritos arrogantes.", "Defenda-se de seus pontap�s, golpes e socos", "Protegendo tua matilha um tanto petulante.", "Esta habilidade afeta um campo acima e um abaixo de voc�."}, 
											 {"�s um ador�vel mecaniculo bestial que se juntou a tua patota.", "Saltita e corre arrancando-te sorrisos radiantes,", "Quando agitado e en�rgico destr�i o que est� em vossa volta", "E ao lado sempre pode v�-lo nas rinhas e masmorras inconstantes.", " "}};

	// 0---------------------------- primeira intera��o se o Ignis for o escolhido ---------------------- 
	private String [][] ConteudoEscolhaAdversario = {{"- Aqui estamos, essa � a cede da Ordem da Reden��o.", "- Voc� gostaria de um pouco de leite ou queijo das cabras do rebanho da ordem?", "- Ou gostaria de descansar um pouco, j� que percorremos um longo caminho?", " "},
													 {"- Vejo que ainda disp�em de muita energia, talvez voc� prefira participar de uma daquelas", "competi��es que a Ayla criou?", " ", " "},
													 {"- Voc� me parece gostar bastante dessa atividade, devo dizer que eu mesmo n�o compreendo", "ou concordo totalmente com os m�todos utilizados pela fada, mas admiro muito a alegria que", "ela traz para o povo dessas colinas.", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Ent�o fique a vontade para explorar o local.", "- Se me acompanhar poderei lhe apesentar aos membros ou se preferir pode ficar aqui e fazer", "carinho no rebanho.", " "},
	// 4---------------------------- se o Ignis for o escolhido e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
													 {"...", " ", " ", " "},
													 {"- Vejo que voc� reparou na reforma que eu fiz.", "- O que achou?", "- Meu �ltimo procedimento n�o estava mais me valorizando, ent�o resolvi dar uma aumentada.", " "},
													 {"- Voc� est� dizendo que preciso de mais queixos em outros lugares do meu corpo?", "- Vou pensar a respeito.", " ", " "},
													 {"- Agora que retornamos, seria um bom momento para darmos o pr�ximo passo da sua jornada.", "- O que me diz?", " ", " "},
													 {"- Ent�o conte comigo para ser seu escudo.", " ", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Compreendo, voc� deve ter outros objetivos em mente primeiro.", " ", " ", " "},
    // 10--------------------------- se o Ignis for o escolhido e desistiu == 1 no meio da �ltima luta = |1|0|0|1|3| -------------------
													 {"- Precisamos ter uma conversa s�ria agora.", " ", " ", " "},
													 {"- O que voc� fez foi totalmente desrespeitoso.", "- Voc� abandonou seus deveres no meio do evento por um motivo t�o simpl�rio e nem cogitou", "como isso afetaria todos que separaram uma parte de seu tempo e energia para comparecer e", "realizar as apresenta��es."},
													 {"- Como paladino de Thyatis eu lhe darei outra chance para que se redima de seus atos, mas", "lembre, Thyatis � o deus das segundas chances, n�o das terceiras.", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
	// 13--------------------------- se o Ignis for o escolhido e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
													 {"- Sua primeira estadia aqui foi proveitosa, voc� conheceu alguns integrantes da ordem, se", "divertiu brincando com as cabras e provou os nossos queijos, mas creio que chegou a hora de", "levarmos essa competi��o a outro n�vel.", " "},
													 {"- Eu apresento a voc� meu MEGA HIPER BLASTER PLANO DE COMBOS SUPREMO.", "- Com ele seremos capazes de maximizar a nossa efic�cia nas batalhas aproveitando todo o", "nosso potencial.", " "},
													 {"- Embarque comigo nesse caminho sem volta e fa�a eles sentirem o verdadeiro significado do", "apavoro.", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
	// 16--------------------------- se o Ignis for o escolhido e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
													 {"- Parab�ns, voc� conseguiu, digo, n�s conseguimos nossa primeira vit�ria.", "- Devo dizer que n�o estava muito empolgado com essa brincadeira no come�o, mas agora", "entendo porque todos acham t�o divertido essas apresenta��es e batalha despropositadas.", " "},
													 {"- A nobre e refinada arte do combo realmente � uma ferramenta incr�vel, revigorante e devo", " dizer at� viciante, agora entendo o porqu� de exercit�-las nesses jogos.", " ", " "},
													 {"- Ent�o depois da nossa vit�ria voc� ainda deseja outra disputa?", " ", " ", " "},
													 {"- Ent�o vamos, ainda temos muitas combina��es para p�r em pr�tica.", " ", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Bem, talvez com esse tempo dever�amos nos preparar mais.", "- Gostaria de ver os novos estratagemas que elaborei? Garanto que s�o desafios de n�vel", "mitol�gico.", " "},
	// 21--------------------------- se o Ignis for o escolhido e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
													 {"- N�s perdemos nossa ultima batalha como anfitri�es e eu sei que a perda pode desanimar e", "afetar sua vontade de prosseguir, mas lembre-se que teremos muito mais derrotas pela frente", "e isso n�o � ruim.", " "},
													 {"- Perder n�o � algo bom ou ruim, ela s� � a consequ�ncia de in�meros fatores que envolve at�", "mesmo a sorte, Nimb ou as deusas, como preferir.", " ", " "},
													 {"- Por agora sugiro que analisemos nossa batalha para podermos aprender com nossos erros e", "depois daremos in�cio a outra tentativa.", " ", " "},
													 {"- O que me diz, vamos dar mais uma chance a nossa vit�ria?", " ", " ", " "},
													 {"- Que bom, fico feliz com sua atitude.", " ", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Bem, talvez voc� precise de mais tempo.", " ", " ", " "},
	// 27--------------------------- se o Ignis for o escolhido e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
													 {"- Rexthor, acredito que dever�amos achar o Meia-Pata ao inv�s do alambiqueiro, podemos", "localiz�-lo mais facilmente com ajuda do Jonid.", " ", " "},
													 {"- Ignis, voc� n�o sabe o que diz, eu e o Arius somos os melhores rastreadores da equipe, deixa", "comigo.", "- O Mungo Joe s� fabrica bebida e n�o vale a pena ir atr�s de calorias vazias.", " "},
													 {"- Temos que encontrar o Meia-Pata primeiro e caso ele n�o seja muito amig�vel teremos", "bastante prote�na para a semana.", "- E voc� n�o est� com o Homem-Bode? Ele pode saber aonde o animal costuma ficar.", " "},
													 {"- FOI O QUE EU ACABEI DE FALAR!", " ", " ", " "},
													 {"- Se voc� n�o consegue compreender um simples dialogo, eu posso de dar algumas aulas de ", "orat�ria.", "- Creio que esse comportamento � normal para pessoas que n�o estudaram desde cedo, ent�o", "eu te dou uma segunda chance."},
													 {"- Me desculpa se eu n�o pude ter uma educa��o porque fui roubado da minha vila e for�ado a", "lutar desde moleque.", " ", " "},
													 {"- Vamos encerrar nossa discuss�o por enquanto, eu tenho tarefas mais importantes agora, com", "licen�a.", " ", " "},
													 {"- Me desculpe pela balb�rdia que presenciou.", "- Contudo, voc� veio em boa hora para continuarmos a nossa jornada, o que voc� acha?", " ", " "},{"- Vamos, nosso caminho n�o ser� f�cil.", "- Rexthor! Eu vou fazer os preparativos para a pr�xima luta, � melhor voc� n�o assar nenhuma", "cabra na minha aus�ncia.", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Se � o que juga melhor.", " ", " ", " "},
													 {"- Voc� sabe que eu te amo n�o � Ignis?", "- Hahahahaha.", " ", " ", "- Opa! Me veio uma ideia agora."},
													 {"- Que tal apresentarmos a Kiki ao Mungo Joe?", "- Talvez ao ver a quantidade de cacha�a que ela consegue beber, ele tenha uma crise existencial", "e decida se juntar a sua ordem para fazer algo mais significativo, h�stia, por exemplo, n�o �", "uma ideia brilhante?"},
													 {"- N�s n�o vamos destruir a vida desse homem.", " ", " ", " "},
													 {"- Ele ir� te agradecer depois, pensa bem.", " ", " ", " "},
	// 41--------------------------- se o Ignis for o escolhido e derrotado na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
													 {"- Sagrada Fenix, lucide minha mente com seu dom prof�tico, devemos buscar como primeira", "op��o o ser conhecido como Meia-Pata?", "- N�o?!", " "},
													 {"- N�o? Como assim?", "- Queima mais PM ai Ignis, agora temos que saber as informa��es por completo.", " ", " "},
													 {"- Ave das chamas eternas, voc� me aconselhou a ir primeiro at� Mungo Joe, por favor revele-me", "o porqu�.", "- Ele consegue preparar bebidas que recuperam PM e tem um estoque cheio delas!", " ", " "},
													 {"- Que se dane o bode, n�s vamos � buscar a Kiki para acabar com a mente dele � agora!", " ", " ", " "},
													 {"- Calma Ignis, eu estou com voc� nesse plano, mas voc� tem outras obriga��es agora, veja.", " ", " ", " "},
													 {"- Oh! Eu n�o reparei que estava aqui.", "- Desculpe minha exalta��o, eu deixei me levar pela empolga��o.", " ", " "},
													 {"- Voc� j� se decidiu?", "- �timo, ent�o eu presumo que iremos competir, certo?", " ", " "},
													 {"- Ent�o vamos, temos muito o que fazer.", "- Rexthor, voc� j� sabe.", " ", " "},
													 {"- Eu n�o estava esperando essa resposta.", "- Mas se � isso que deseja n�o vejo porque continuarmos estagnados aqui, vamos buscar a Kiki.", " ", " "},
													 {"- Eu estou parando aqui para pensar Ignis.", "- Talvez n�o seja a melhor ideia levarmos ela, essas bebidas com PM v�o acabar levando a Kiki", "mais cedo para cova com a cirrose que ela vai ganhar.", " "},
	// 51--------------------------- se o Ignis for o escolhido e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
													 {"- Venha c�, tenho algu�m para lhe apresentar.", " ", " ", " "},
													 {" ", " ", " ", " "},
													 {"- Esse aqui � o Rito, meu parceiro mec�nico.", "- Da oi filho.", " ", " "},
													 {"- Au!", " ", " ", " "},
													 {"- Parece que ele gostou de voc�.", "- Eu n�o tinha apresentado-o ainda porque ele esteve fora em miss�o com Nico Luriel, o", "mec�niculo do Arius.", " "},
													 {"- Agora que conseguimos as nossas tr�s vit�rias n�o dever�amos deixar esse momento passar", "em branco.", "- Ent�o... o que voc� acha de n�s tr�s irmos at� Dornbach bater em puristas?", " "},
													 {"- �timo, eu fico com as crian�as e idosos!", " ", " ", " "},
	// 58--------------------------- se o Ignis [[[N�O]]] for o escolhido e primeira intera��o |0|0|0|0|0 ---------------------------------
													 {"- Boa-tarde!", "- Elmer me disse que voc�s desejam falar comigo sobre algo?", "- Gostariam de um pouco de leite ou queijo das cabras do rebanho da ordem?", "- Ou gostariam de descansar um pouco, j� que percorreram um longo caminho?"},
													 {"- Vejo que voc�s ainda disp�em de muita energia, talvez prefiram participar de uma daquelas", "competi��es que a Ayla criou?", "- Vejo que voc�s ainda disp�em de muita energia, talvez prefiram participar de uma das", "competi��es que voc� criou Ayla?"},
													 {"- Voc� me parece gostar bastante dessa atividade, devo dizer que eu mesmo n�o compreendo", "ou concordo totalmente com os m�todos utilizados pela fada, mas admiro muito a alegria que", "ela traz para o povo dessas colinas.", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Ent�o fiquem a vontade para explorar o local.", "- Se me acompanharem poderei lhes apesentar aos membros ou se preferirem podem ficar aqui", "e fazer carinho no rebanho.", " "},
	// 62--------------------------- se o Ignis [[[N�O]]] for o escolhido e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
													 {"...", " ", " ", " "},
													 {"- Vejo que voc�s repararam na reforma que eu fiz.", "- O que acharam?", "- Meu �ltimo procedimento n�o estava mais me valorizando, ent�o resolvi dar uma aumentada.", " "},
													 {"- Voc�s est�o dizendo que preciso de mais queixos em outros lugares do meu corpo?", "- Vou pensar a respeito.", " ", " "},
													 {"- Agora que retornaram, seria um bom momento para darem o pr�ximo passo da jornada.", "- O que me dizem?", " ", " "},
													 {"- Boa escolha.", "- Mas antes de tudo um conselho, evitem olhar diretamente para o meu belo maxilar com os", "olhos desprotegidos, a ofusca��o pode ser mais dif�cil de lidar do que o meu escudo.", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Compreendo, voc�s devem ter outros objetivos em mente primeiro.", " ", " ", " "},
	// 68-------------------------- se o Ignis [[[N�O]]] for o escolhido e desistiu == 1 no meio da �ltima luta = |1|0|0|1|3| -------------------	
													 {"- Precisamos ter uma conversa s�ria agora.", " ", " ", " "},
													 {"- O que voc� fez foi totalmente desrespeitoso.", "- Voc� abandonou seus deveres no meio do evento por um motivo t�o simpl�rio e nem cogitou", "como isso afetaria todos que separaram uma parte de seu tempo e energia para comparecer e", "realizar as apresenta��es."},
													 {"- Como paladino de Thyatis eu lhe darei outra chance para que se redima de seus atos, mas", "lembre, Thyatis � o deus das segundas chances, n�o das terceiras.", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
	// 71-------------------------- se o Ignis [[[N�O]]] for o escolhido e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
													 {"- A primeira estadia de voc�s aqui foi proveitosa, conheceram alguns dos integrantes da ordem,", "se divertiram brincando com as cabras e provaram os nossos queijos, mas creio que chegou a", "hora de levarem essa competi��o a outro n�vel.", " "},
													 {"- Eu apresento a voc�s meu MEGA HIPER BLASTER PLANO DE COMBOS SUPREMO.", "- Com ele serei capaz de maximizar a minha efic�cia nas batalhas aproveitando todo o meu", "potencial.", " "},
													 {"- Embarquem comigo nesse caminho sem volta e sintam o verdadeiro significado do apavoro.", " ", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
	// 74-------------------------- se o Ignis [[[N�O]]] for o escolhido e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
													 {"- Parab�ns, conseguiram a primeira vit�ria de voc�s.", "- Devo dizer que n�o estava muito empolgado com essa brincadeira no come�o, mas agora", "entendo porque todos acham t�o divertido essas apresenta��es e batalha despropositadas.", " "},
													 {"- A nobre e refinada arte do combo realmente � uma ferramenta incr�vel, revigorante e devo", " dizer at� viciante, agora entendo o porqu� de exercit�-las nesses jogos.", " ", " "},
													 {"- Ent�o depois dessa vit�ria voc�s ainda desejam outra disputa?", " ", " ", " "},
													 {"- Ent�o avante, pois ainda tenho muitas combina��es para p�r em pr�tica contra voc�s.", " ", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Bem, talvez com esse tempo voc�s deveriam se preparar mais.", "- Os novos estratagemas que elaborei ser�o um desafio n�vel mitol�gico.", " ", " "},
	// 79-------------------------- se o Ignis [[[N�O]]] for o escolhido e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
													 {"- Na �ltima batalha contra mim voc�s perderam feio e eu sei que a perda pode desanimar e", "afetar a vontade de prosseguir, mas lembre-se que ter�o muito mais derrotas pela frente e isso", "n�o � ruim.", " "},
													 {"- Perder n�o � algo bom ou ruim, ela s� � a consequ�ncia de in�meros fatores que envolve at�", "mesmo a sorte, Nimb ou as deusas, como preferirem.", " ", " "},
													 {"- Por agora sugiro que analisem nossa �ltima batalha para que possam aprender com os seus", "e os meus erros.", " ", " "},
													 {"- O que me dizem, v�o dar mais uma chance a vit�ria de voc�s?", " ", " ", " "},
													 {"- Que bom, fico feliz com a atitude que tomaram.", " ", " ", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Bem, talvez voc�s precisem de mais tempo.", " ", " ", " "},
	// 85-------------------------- se o Ignis [[[N�O]]] for o escolhido e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
													 {"- Rexthor, acredito que dever�amos achar o Meia-Pata ao inv�s do alambiqueiro, podemos", "localiz�-lo mais facilmente com ajuda do Jonid.", " ", " "},
													 {"- Ignis, voc� n�o sabe o que diz, eu e o Arius somos os melhores rastreadores da equipe, deixa", "comigo.", "- O Mungo Joe s� fabrica bebida e n�o vale a pena ir atr�s de calorias vazias.", " "},
													 {"- Temos que encontrar o Meia-Pata primeiro e caso ele n�o seja muito amig�vel teremos", "bastante prote�na para a semana.", "- E voc� n�o est� com o Homem-Bode? Ele pode saber aonde o animal costuma ficar.", " "},
													 {"- FOI O QUE EU ACABEI DE FALAR!", " ", " ", " "},
													 {"- Se voc� n�o consegue compreender um simples dialogo, eu posso de dar algumas aulas de ", "orat�ria.", "- Creio que esse comportamento � normal para pessoas que n�o estudaram desde cedo, ent�o", "eu te dou uma segunda chance."},
													 {"- Me desculpa se eu n�o pude ter uma educa��o porque fui roubado da minha vila e for�ado a", "lutar desde moleque.", " ", " "},
													 {"- Vamos encerrar nossa discuss�o por enquanto, eu tenho tarefas mais importantes agora, com", "licen�a.", " ", " "},
													 {"- Me desculpem pela balb�rdia que tiveram que presenciar.", "- Contudo, voc�s vieram em boa hora para continuarmos a nossa disputa, o que acham?", " ", " "},
													 {"- Vamos, temos muito o que arrumar.", "- Rexthor! Eu vou fazer os preparativos para a pr�xima luta, � melhor voc� n�o assar nenhuma", "cabra na minha aus�ncia.", "As apresenta��es COM interfer�ncia ganhar�o -1 de apelo."},
													 {"- Se � o que jugam melhor.", " ", " ", " "},
													 {"- Voc� sabe que eu te amo n�o � Ignis?", "- Hahahahaha.", " ", " ", "- Opa! Me veio uma ideia agora."},
													 {"- Que tal apresentarmos a Kiki ao Mungo Joe?", "- Talvez ao ver a quantidade de cacha�a que ela consegue beber, ele tenha uma crise existencial", "e decida se juntar a sua ordem para fazer algo mais significativo, h�stia, por exemplo, n�o �", "uma ideia brilhante?"},
													 {"- N�s n�o vamos destruir a vida desse homem.", " ", " ", " "},
													 {"- Ele ir� te agradecer depois, pensa bem.", " ", " ", " "},
	// 99-------------------------- se o Ignis [[[N�O]]] for o escolhido e derrota na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
													 {"- Sagrada Fenix, lucide minha mente com seu dom prof�tico, devemos buscar como primeira", "op��o o ser conhecido como Meia-Pata?", "- N�o?!", " "},
													 {"- N�o? Como assim?", "- Queima mais PM ai Ignis, agora temos que saber as informa��es por completo.", " ", " "},
													 {"- Ave das chamas eternas, voc� me aconselhou a ir primeiro at� Mungo Joe, por favor revele-me", "o porqu�.", "- Ele consegue preparar bebidas que recuperam PM e tem um estoque cheio delas!", " ", " "},
													 {"- Que se dane o bode, n�s vamos � buscar a Kiki para acabar com a mente dele � agora!", " ", " ", " "},
													 {"- Calma Ignis, eu estou com voc� nesse plano, mas voc� tem outras obriga��es agora, veja.", " ", " ", " "},
													 {"- Oh! Eu n�o reparei que estavam aqui.", "- Desculpem minha exalta��o, eu deixei me levar pelas possibilidades.", " ", " "},
													 {"- Voc�s j� se decidiram?", "- �timo, ent�o eu presumo que iremos competir, certo?", " ", " "},
													 {"- Ent�o vamos, temos muito o que fazer.", "- Rexthor, voc� j� sabe.", " ", " "},
													 {"- Eu n�o estava esperando essa resposta.", "- Mas se � isso que desejam n�o vejo porque continuarmos estagnados aqui, vamos buscar a", "- Mas se � isso que desejam n�o vejo porque continuarmos estagnados aqui, vamos Kiki.", "Kiki.", " "},
													 {"- Eu estou parando aqui para pensar Ignis.", "- Talvez n�o seja a melhor ideia levarmos ela, essas bebidas com PM v�o acabar levando a Kiki", "mais cedo para cova com a cirrose que ela vai ganhar.", " "},
	// 109-------------------------- se o Ignis [[[N�O]]] for o escolhido e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
													 {"- Venha c�, tenho algu�m para lhe apresentar.", " ", " ", " "},
													 {" ", " ", " ", " "},
													 {"- Esse aqui � o Rito, meu parceiro mec�nico.", "- Da oi filho.", " ", " "},
													 {"- Au!", " ", " ", " "},
													 {"- Parece que ele gostou de voc�.", "- Eu n�o tinha apresentado-o ainda porque ele esteve fora em miss�o com Nico Luriel, o", "mec�niculo do Arius.", " "},
													 {"- Agora que conseguiram as tr�s vit�rias n�o dever�amos deixar esse momento passar", "em branco.", "- Ent�o... o que voc�s acham de n�s quatro irmos at� Dornbach bater em puristas?", " "},
													 {"- �timo, eu fico com as crian�as e idosos!", " ", " ", " "},
	// 116--------------------------- se j� venceu 3 vezes o Ignis e falou com ele -------------------
													 {"- Por que voltamos? Pensei que iriamos desafiar os outros C�es.", "- Voc�s voltaram? Pensei que iriam desafiar os outros C�es.", " ", " "}};

	
	

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