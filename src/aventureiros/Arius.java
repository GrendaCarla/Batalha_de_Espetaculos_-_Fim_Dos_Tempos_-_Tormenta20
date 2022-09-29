package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Arius {
	//                                   apelo,                interfer�ncia,          tipo interfer�ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{3, 0, 3, 5, 1, 0, 5, 5}, {2, 0, 1, 0, 5, 10, 0, 0}, {2, 4, 0, -1, 3, 6, -1, -1}, {1, 0, 0, 1, 0, 0, 1, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Chifrada", "Estrat�gia em Combate", "O Mamilo Rosa", "Olimp�ada Artoniana", "Diplomacia", "5 � 20", "Gl�dio e Escudo", "Declamar Poema"};
	private String [][] ConteudoDescricao = {  {"Com teus cornos perfure a tora resistente.", "Com tua for�a taurina erga-a no ar.", "O mais longe que puder lan�e-a na tua frente,", "Ent�o tor�a para o tronco ningu�m acertar.", "Esta habilidade afeta o campo acima de voc�."},
											   {"Somente atrav�s da viol�ncia a vit�ria n�o adv�m.", "Ao compreender aqueles que acima est�o", "Vantagem poder� ganhar tamb�m", "Ao ver seus movimentos com antecipa��o.", "Esta habilidade zera as interfer�ncias ganhadas antes da sua a��o nessa rodada."},
											   {"Ao tirar sua coura�a em meio ao espet�culo", "Todos podem v�-lo e � imposs�vel de desviar.", "Por mais que seja um �rg�o prosaico", "O �nico mamilo rosa consegue a todos hipnotizar.", "Esta habilidade afeta todos os campos acima de voc�."},
											   {"Acenda sua pira do esp�rito esportivo", "Participando de diversos jogos consecutivos", "Como salto pela pali�ada, flechada nos irm�os,", "Esconde-esconde com baratas e arremesso com explos�o.", " "},
											   {"Use a per�cia diplom�tica na audi�ncia", "Para que possa com ela argumentar e convencer", "Que o primeiro colocado deve apresentar a impon�ncia", "Em sua demonstra��o, o que dessa vez n�o conseguiu fazer.", "Esta habilidade afeta o primeiro campo."},
											   {"Jogando cinco d20 uma aposta voc� vai fazer.", "N�o h� repeti��o nos n�meros contemplados.", "10 apelos voc� ganha se 5 o dado conceber.", "Ent�o camarada, voc� est� tentado?", "Esta habilidade te concede 10 apelos se o n�mero 5 sair em um dos dados."},
											   {"Lembre-se do seu treinamento nas legi�es do imp�rio.", "Com o gl�dio em m�os e o escudo bem posicionado", "Demonstre suas t�ticas e o conhecimento b�lico", "Evitando se queimar caso for derrubado.", " "},
											   {"Se quiseres emocionar a plateia", "Ter�s que revelar seus sentimentos dolorosos.", "Recita-los em forma de prosa po�tica", "Parece certamente um dos modos mais graciosos.", " "}};
	
	
	// 0---------------------------- primeira intera��o se o Arius for o escolhido ---------------------- 
	private String [][] ConteudoEscolhaAdversario = {{"- Chegamos!", "- Este � o local do qual lhe falei durante o nosso percurso.", "- Garanto que n�o existe lugar mais belo nas Colinas Centrais do que a planta��o de gorad da", "fam�lia Gallobalt."},
													 {"- Bem, talvez o Cemit�rio Errante ou o Templo do V�cuo, porem suas belezas adv�m de eventos", "devastadores ao contr�rio deste local.", "- E voc� sabe me dizer o porqu�?", " "}, 
													 {"- N�o, n�o � porque anteriormente era uma enorme planta��o de archibold, uma droga terr�vel", "que destr�i in�meras vidas por toda Arton, e Ignis, o paladino de Thyatis, deu-lhes uma", "segunda chance para consertarem suas a��es, isso � pura bobagem.", " "},
													 {"- Este sim � um belo lugar porque agora poderei desfrutar do doce sabor do gorad novamente!", "- Nossos anfitri�es foram muitos gentis e me deixaram pegar um pouquinho dessa iguaria por", "toda a ajuda que os C�es das Colinas disponibilizaram ao resolver o problema com Syvarian e", "Gog-Magog, o seu peixe navio voador."},
													 {"- E assim que for poss�vel visitaremos Kiki para que ela use seus dons culin�rios e prepare para", "mim mais desse doce irresist�vel.", " ", " "},
													 {"- Como assim! Eu n�o sou um aproveitador ego�sta, ao contr�rio, sou um devoto de Tanna-Toh", "e ela ensina que devemos sempre compartilhar todo tipo de conhecimento para que possamos", "crescer e evoluir juntos.", "- Mas isso n�o se aplica ao meu gorad!"},
													 {"- Venha, vamos nos juntar a Nada Mais que a Verdade, ela adoraria participar do nosso debate.", "- O que foi, algo est� te incomodando?", " ", " "}, 
													 {"- Ah! Voc� quer me propor uma disputa amistosa para compartilharmos nossas habilidades", "com os outros C�es das Colinas?", " ", " "},
													 {"- Que ideia maravilhosa!", "- Vamos, se aprese, eu quero muito saber o qu�o bem podemos ir se come�armos a disputa", "com a vantagem.", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Ent�o segure isso aqui para mim enquanto eu pego um pouquinho mais.", " ", " ", " "},
	// 10---------------------------- se o Arius for o escolhido e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
													 {"- Eu n�o poderia imaginar que os Gallobalts ficariam t�o zangados com a quantidade de gorad", "que peguei.", " ", " "},
													 {"- Uma vez quando os C�es das Colinas estavam partindo de Placides eu vi Ayla levando uma", "grande quantidade de suprimentos, nesse momento eu a questionei e ela me contou que aqui", "nas Colinas Centrais pouco significa tudo que puder carregar e que se recusar a fazer isso �", "uma grande desfeita com a cultura local."},
													 {"- Talvez os Gallobalts s� n�o estejam familiarizados com esse costume... mesmo morando aqui", "h� muito tempo...", "- O que estou cogitando em minha mente!", "- Ayla nunca mentiria ou pegaria algo que n�o lhe pertencesse, ela � um ser de �ndole pura!"},
													 {"- Agora, deixando os mal-entendidos de lado, eu ainda gostaria de ver o qu�o bom poder�amos", "ir em um combate em que come��ssemos com a vantagem.", "- Pense nas possibilidades matem�ticas dessa varia��o!", " "},
													 {"- Voc� se juntaria a mim nesse experimento?", " ", " ", " "},
													 {"- Maravilha! Teremos altos debates depois da luta, vamos, vamos.", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Bem, devo dizer que estou um pouco desapontado, estava muito curioso para aprender mais", "e Mestre Luriel sempre me falou que a curiosidade � a fagulha da mente e que devemos... bl�,", "bl�, bl�.", " "},												 
    // 17--------------------------- se o Arius for o escolhido e desistiu <= 2 no meio da �ltima luta = |1|0|0|1|3| -------------------
													 {"- Sauda��es! n�o percebi sua aproxima��o, estava absorto em meus pensamentos relembrando", "alguns dos eventos que me trouxeram at� aqui.", " ", " "},
													 {"- Senador Glavus, meu pai, me mandou para XIV da Grande Savana, uma legi�o afastada na", "divisa da fronteira do Imp�rio, enquanto meu irm�o mais novo ficava para combater a", "tempestade rubra cumprindo seus deveres como minotauro.", " "},
													 {"- Lembro-me da falta de f� que possu�a em mim e como s� se preocupava em esconder a falha,", "eu, de nossa fam�lia.", "- De como em meio a dor e a vergonha eu prometi que iria para longe, mas voltaria com o", "conhecimento capaz de sobrepujar a Tormenta."},
													 {"- Deixando meus pensamentos de lado por um instante, h� algo que queira discutir?", "- Uma nova disputa eu presumo?", " ", " "},
													 {"- Agora deixe-me lhe dar um conselho, quando voc� se prop�em a participar de um espet�culo", "ou apresenta��o voc� n�o deve faltar com sua palavra abandonando seus deveres e", "responsabilidades.", "- N�o siga meu exemplo indo para longe do forte e do Sargento Morgan para evitar de usar um"}, 
													 {"uniforme rid�culo e para n�o precisar encarar um futuro em que n�o poderei evitar de me tornar", "o espelho de meu pai.", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Bem, se n�o tem nada a tratar comigo, pe�o que me deixe a s�s, mais tarde eu me reunirei", "a voc� novamente.", " ", " "},	
	// 24--------------------------- se o Arius for o escolhido e desistiu == 3 no meio da �ltima luta = |1|0|0|6|3| -------------------
													 {"- Vamos direto ao assunto em quest�o. Voc� realmente deseja batalhar nesse exato momento?", " ", " ", " "},
													 {"- Ou�a bem porque n�o irei me repetir.", "- Mesmo ap�s meus constantes avisos, voc� continuou faltando com sua palavra e nos", "abandonando em meio ao espet�culo.", "- J� basta, n�o irei mais tolerar tau comportamento desonroso de sua parte, essa ser� a �ltima"},
													 {"vez que dividiremos o campo de batalha se voc� prosseguir com tau comportamento, esteja", "ciente.", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Ent�o n�o desperdice meu tempo com bobagens, se n�o iremos batalhar aqui, ent�o vamos", "logo para o local de nossa disputa.", " ", " "},	
	// 28--------------------------- se o Arius for o escolhido e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
													 {"- Shiiii, fale baixo.", "- Percebo que est� se questionando se estou tentando ouvir uma conversa.", "- Bem, quando Kiki diz \"o que uni os c�es das Colinas na realidade � a fofoca\" ela n�o est�", "totalmente errada."},
													 {"- Arg, j� se afastaram muito para eu poder ouvi-los, agora terei que me contentar com a metade", "do ocorrido, que dor terr�vel!", "- Sim, � de meu conhecimento que ouvir a conversa alheia � falta de educa��o, porem em", "muitas ocasi�es � necess�rio esse subterf�gio."},
													 {"- Por exemplo, na grande maioria das miss�es investigativas a obten��o de informa��es adv�m", "dessa forma, se Ayla, um ser t�o puro com nobres inten��es, se utiliza dessa pr�tica para fazer", "seus atos de bondade, n�o vejo como seria estritamente mau essa maneira de se obter", "informa��es."},
													 {"- Sim, sim, voc� tamb�m possui bons argumentos, todavia n�o detemos de mais tempo para", "prosseguirmos com esse debate.", " ", " "},
													 {"- Preciso saber se voc� gostaria que come��ssemos uma competi��o amistosa agora ou n�o?", " ", " ", " "},
													 {"- Estou confuso, o ", "sim", "n�o", " foi para agora ou para n�o?", "- Veja o que acontece quando ou�o hist�rias pela metade, minha mente se divide entre a", "curiosidade e o assunto atual e come�o a me embananar todo.", " "},
													 {"- Deixe-me reformular a pergunta.", "- Voc� gostaria de se juntar a mim em uma competi��o amistosa?", " ", " "},
													 {"- �timo! Durante os preparativos deixe-me dividir o que eu consegui escutar antes da sua", "chegada.", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Uma l�stima devo dizer, contudo, com esse tempo de sobra poderemos retornar ao nosso", "debate sobre a moralidade em se obter informa��es sigilosas.", " ", " "},
	// 37--------------------------- se o Arius for o escolhido e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
													 {"- Vencemos, vencemos, vencemos nossa primeira disputa e eu n�o acabei estirado no ch�o", "ardendo em chamas!", "- Dever�amos apreciar esse momento raro comemorando com m�sicas e poesias... J� sei,", "vamos realizar um sarau!"},
													 {"- Infelizmente planejar tau evento mesmo que pequeno � demorado e trabalhoso, ent�o teremos", "que realiz�-lo em outra ocasi�o de comemora��o.", "- Claro, terei que convidar Kiki, pois ela nunca me perdoaria se eu fizesse um sarau sem sua", "presen�a, n�o importando o qu�o ruim fosse."},
													 {"- Creio que Ayla tamb�m viria para poder cobrar entradas, j� que ainda estou profundamente", "endividado com ela.", "- Por Tanna-Toh! Esse planejamento j� est� come�ando a ficar muito complicado, pensarei", "melhor mais tarde."},
													 {"- Por enquanto vejo que tem algo a me perguntar, o que foi? Voc� j� quer desfrutar de outra", "competi��o?", " ", " "},
													 {"- Gosto que sua confian�a em nossa vit�ria continua a crescer, quase me esque�o de todas as", "quedas e queimaduras que j� tive, quase.", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Ent�o pe�o sua ajuda para obter algumas ideias, pois pretendo declamar poesias de minha", "autoria na festividade e bem, alguns dos meus trabalhos vindouros n�o s�o adequados para o", "p�blico geral.", " "},
	// 43--------------------------- se o Arius for o escolhido e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
													 {"- Devo dizer que voc� n�o parece muito bem, o que ocorreu? Est� borocox� por nossa �ltima", "derrota aqui?", "- Horas, n�o se deprima ap�s uma falha, Mestre Luriel sempre me falava que a falha � parte", "crucial do processo de aprendizagem e que cada tentativa minha me levaria rumo ao acerto.", " ", " "},	
													 {"- Veja, uma parte importante de mim fora roubada por uma entidade folcl�rica das Colinas", "Centrais, ele me induziu a fazer coisas que de outrora jamais faria.", " ", " "},
													 {"- Entretanto, com a ajuda de meus companheiros, minha matilha, consegui recuperar o controle", "do meu corpo e da minha vida por completo e a criatura recebeu seu castigo e sua reden��o,", "outrora conhecido como Ladr�o de Rabos hoje � o Guardi�o de Rabos.", " "},
													 {"- Eu constantemente caia em combate enquanto ardia em chamas, mas gra�as a minha", "perseveran�a eu cont�nuo caindo, porem meus companheiros n�o.", "- Ent�o arrume sua postura, encha o peito com determina��o e vamos tentar mais uma vez!", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
	// 47--------------------------- se o Arius for o escolhido e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
													 {"*Sussurros*", "- O que est� achando de nossa leitura?", " ", " "},
													 {"- Para falar a verdade, eu estou bem intrigada com a utiliza��o de armas de p�lvora e, que os", "deuses benignos me perdoe, estou com vontade de adquiri-las.", " ", " "},	
													 {"- Nada Mais que a Verdade, armas de p�lvora s�o ilegais no reinado e voc� sabe.", " ", " ", " "},
													 {"- Sei, sei sim.", "- E inclusive penso que seria engra�ado se voc� aderi-se a elas se tornando uma vaca cowboy.", " ", " "},
													 {"- Nada Mais que a Verdade!", "- Bem, nesse caso, se eu seguisse o rumo c�mico de nossa conversa, eu diria que se voc�", "conseguisse adquiri-las, o seu nome deveria mudar de Nada Mais que a Verdade Reston para", "Nada Mais que a Bala Reston."},
													 {"- Arius!", "- Sinceramente n�o sei o que sentir com o trocadilho do meu nome, mas sei que estou muito", "sem jeito agora.", " "},
													 {"- Voc� que come�ou.", " ", " ", " "},
													 {"- AAAAAAH!", "- Voc� nos assustou chegando assim de fininho... pela nossa frente... e sem se esconder, por", "favor n�o fa�a mais isso.", "- Voc� quer saber o que estamos fazendo?"},
													 {"- N�s estamos lendo um livro proibido que o Arius roubou dos Gallobalts h� muito tempo atr�s.", " ", " ", " "},
													 {"- Nada Mais que a Verdade!", " ", " ", " "},
													 {"- Desculpe Arius, mas voc� sabe que eu voluntario informa��es mesmo n�o sendo solicitadas.", " ", " ", " "},
													 {"- Ela tem raz�o, quando n�s, os C�es das Colinas, estivemos aqui pela primeira vez, eu fui", "influenciado pelo Ladr�o de Rabos a roubar esse livro da biblioteca da fam�lia Gallobalt.", "- Mas juro que o devolvi! N�o sei como ele veio parar em meus equipamentos de viagem", "novamente e junto com mais dois livros."},
													 {"- Pergunte a Kiki se n�o acredita em minhas palavras, ela estava presente quando o devolvi.", "- Acredito que seja mais uma das artimanhas do Ladr�o de Rabos.", "- S� um ser devoto de Hyninn poderia fazer tau ato de esc�rnio, sorte a minha que n�o temos", "ningu�m assim nos C�es das Colinas."},
													 {"- Deixando esse assunto de lado por agora, voc� gostaria de desfrutar de uma animada", "competi��o entre companheiros de equipe?", " ", " "},
													 {"- �timo! Entretanto,", "- Se esse � o caso,", " antes de prosseguirmos, eu gostaria de devolver o livro novamente,", "contudo, eu estou muito envergonhado de meus atos passados para encara-los.", "- Se me permite perguntar Nada Mais que a Verdade, o que faria em meu lugar?", " "},
													 {"- F�cil, eu teria ido at� eles e avisado que cometeria um crime, assim como eu fiz com os meus", "pais quando avisei que fugiria para n�o participar do suic�dio coletivo da minha fam�lia.", "- Creio que minha resposta n�o te ajudou com o seu problema, desculpe Arius.", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo.", " "},
	// 63--------------------------- se o Arius for o escolhido e derrotado na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
													 {"- Ol�, eu sei que voc� est� querendo falar com o Arius e n�o comigo, o que faz todo o sentido", "j� que n�o tenho muito trato social e falo demais, mas ele est� ocupado agora terminando de", "escrever um pergaminho, ent�o ao inv�s dele voc� conversaria comigo?", "- Estou genuinamente feliz com sua resposta!"},
													 {"- Olha, eu estava aqui debatendo com Arius e a nossa conversa acabou passando pelos", "Gallobalts e rumou em outra dire��o, porem teve algo que eu n�o entendi direito e n�o tive", "tempo de perguntar, j� que ele n�o consegue parar de falar igual a mim.", " "},
													 {"- Ele mencionou alguns dos la�os sangu�neos da fam�lia e eu acabei me perdendo um pouco,", "ent�o me veio a pergunta, afinal, os Gallobalts s�o ou n�o incestuosos?", "- Devo dizer que estou constrangida por fazer essa pergunta, mas eu quero realmente saber.", "- Se for poss�vel com deseinhos."},
													 {" ", " ", " ", " "},
													 {"- N�o s�o?", "- Obrigada por me responder, fico extremamente aliviada, essa quest�o estava me matando", "metaforicamente falando e se ela persistisse eu n�o aquentaria e teria que ir perguntar aos", "Gallobalts o que seria uma situa��o muito desagrad�vel para todos n�s."},
													 {"- Sauda��es, j� terminei meus afazeres e vejo que voc� e Nada Mais que a Verdade est�o tendo", "um debate bem interessante, posso me juntar a voc�s?", " ", " "},
													 {"- Infelizmente Arius j� terminamos, mas voc� est� certo, foi realmente interessante, que pena", "que voc� perdeu.", "- Tenho que ir agora, nos vemos depois para conversarmos mais.", " "},
													 {"- Como assim! J� � a quarta vez que n�o consigo chegar a tempo em um debate, o que est�", "acontecendo comigo?", " ", " "},
													 {"- Ent�o, voc� veio me chamar para um novo confronto com os C�es das Colinas?", " ", " ", " "},
													 {"- Vamos depressa, n�o posso arriscar perder mais nada interessante por aqui.", " ", " ", " "},
													 {"- Ent�o... voc� pode me falar sobre o que voc�s estavam debatendo?", " ", " ", " "},											 
	// 74--------------------------- se o Arius for o escolhido e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
													 {"- Devemos nos apresar!", "- O espet�culo j� est� atrasado e terei que recitar minhas poesias sozin-", " ", " "},
													 {"- A plateia n�o quer escutar somente suas poesias ou voc� esqueceu com quem dividir� o palco", "hoje?", " ", " "},
													 {"- Kiki! � bom v�-la a salvo, j� estava deveras preocupado com sua demora.", " ", " ", " "},
													 {"- Arius, uma barda nunca chega antes ou depois, ela chega quando ela tiver que chegar.", "- E Ignis n�o consegue andar muito r�pido como voc� j� sabe.", " ", " "},
													 {"- Ufa, comecei at� cogitar que n�o quisesse mais realizar um sarau comigo e-", " ", " ", " "},
													 {"- Mas � claro que eu quero fazer um sarau com voc�!", "- Eu nunca te perdoaria se voc� fizesse um sem mim.", " ", " "},
													 {"- N�o importando o qu�o ruim fosse!?", " ", " ", " "},
													 {"- Ai voc� j� est� se excedendo, vai com calma, principalmente porque n�o tem como um evento", "ser ruim quando estou presente.", "- Menos aquele na vila purista, assim, eu fui excepcional, o problema era a plateia.", "- Vamos Arius, se voc� continuar falando n�s n�o poderemos come�ar a apresenta��o."},
													 {"- Mas eu n�o est-", " ", " ", " "},
													 {"- Shiii, vem logo.", " ", " ", " "},		
	// 84--------------------------- se o Arius [[[N�O]]] for o escolhido e primeira intera��o |0|0|0|0|0 ---------------------------------
													 {"- Sauda��es! Devo dizer que sinto-me afortunado em te conhecer finalmente.", "- Creio que voc�s possam estar estranhando o local de nosso encontro, contudo, garanto-lhes", "que n�o existe lugar mais belo nas Colinas Centrais do que a planta��o de gorad da fam�lia", "Gallobalt."},
													 {"- Bem, talvez o Cemit�rio Errante ou o Templo do V�cuo, porem suas belezas adv�m de eventos", "devastadores ao contr�rio deste local.", "- E voc� sabe me dizer o porqu�?", " "}, 
													 {"- N�o, n�o � porque anteriormente era uma enorme planta��o de archibold, uma droga terr�vel", "que destr�i in�meras vidas por toda Arton, e Ignis, o paladino de Thyatis, deu-lhes uma segunda", "chance para consertarem suas a��es, isso � pura bobagem.", " "},
													 {"- Este sim � um belo lugar porque agora poderei desfrutar do doce sabor do gorad novamente!", "- Nossos anfitri�es foram muitos gentis e me deixaram pegar um pouquinho dessa iguaria por", "toda a ajuda que os C�es das Colinas disponibilizaram ao resolver o problema com Syvarian e", "Gog-Magog, o seu peixe navio voador."},
													 {"- E assim que for poss�vel ", "pe�o a voc�, Kiki, que", "visitarei Kiki para que ela", " use seus dons culin�rios e prepare para", "mim mais desse doce irresist�vel.", " ", " "},
													 {"- Como assim! Eu n�o sou um aproveitador ego�sta, ao contr�rio, sou um devoto de Tanna-Toh", "e ela ensina que devemos sempre compartilhar todo tipo de conhecimento para que possamos", "crescer e evoluir juntos.", "- Mas isso n�o se aplica ao meu gorad!"},
													 {"- Venham, vamos nos juntar a Nada Mais que a Verdade, ela adoraria participar do nosso", "debate.", "- O que foi, estou esquecendo de algo?", " "}, 
													 {"- Suponho que voc�s tenham vindo me propor uma disputa amistosa para compartilharmos", "nossas habilidades no campo de batalha?", " ", " "},
													 {"- Que proposta maravilhosa, vamos!", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Bom, se esse � o caso, segure isso aqui para mim por favor enquanto eu pego um pouquinho", "mais.", " ", " "},
	// 94--------------------------- se o Arius [[[N�O]]] for o escolhido e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
													 {"- Sauda��es!", "- Obrigado novamente pela ajuda que me cederam.", "- Infelizmente eu n�o poderia imaginar que os Gallobalts ficariam t�o zangados com a", "quantidade de gorad que peguei."},
													 {"- Uma vez quando os C�es das Colinas estavam partindo de Placides eu vi Ayla levando uma", "- Uma vez quando os C�es das Colinas estavam partindo de Placides eu vi voc�, Ayla, levando", "grande quantidade de suprimentos, nesse momento eu a questionei e ela me contou que", "uma grande quantidade de suprimentos, nesse momento eu a questionei e voc� me contou que", "aqui nas Colinas Centrais pouco significa tudo que puder carregar e que se recusar a fazer isso", "� uma grande desfeita com a cultura local."},
													 {"- Talvez os Gallobalts s� n�o estejam familiarizados com esse costume... mesmo morando aqui", "h� muito tempo...", "- O que estou pensando!", "- Ayla nunca mentiria ou pegaria algo que n�o lhe pertencesse, ela � um ser de �ndole pura!", "- Voc� nunca mentiria para mim ou me faria pegar algo que n�o me pertencesse, tolices!"},
													 {"- Agora, deixando os mal-entendidos de lado, eu gostaria de propor uma disputa amistosa para", "compartilharmos nossas habilidades no campo de batalha.", " ", " "},
													 {"- Voc� aceita minha proposta?", " ", " ", " "},
													 {"- Maravilha! Teremos altos debates depois da luta, vamos, vamos.", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Bem, devo dizer que estou um pouco desapontado, estava muito curioso para aprender mais", "e Mestre Luriel sempre me falou que a curiosidade � a fagulha da mente e que devemos... bl�,", "bl�, bl�.", " "},												 
	// 101-------------------------- se o Arius [[[N�O]]] for o escolhido e desistiu <= 2 no meio da �ltima luta = |1|0|0|1|3| -------------------	
													 {"- Sauda��es!", "- N�o percebi a aproxima��o de voc�s, estava absorto em meus pensamentos relembrando", "alguns dos eventos que me trouxeram at� aqui.", " "},
													 {"- Senador Glavus, meu pai, me mandou para XIV da Grande Savana, uma legi�o afastada na", "divisa da fronteira do Imp�rio, enquanto meu irm�o mais novo ficava para combater a", "tempestade rubra cumprindo seus deveres como minotauro.", " "},
													 {"- Lembro-me da falta de f� que possu�a em mim e como s� se preocupava em esconder a falha,", "eu, de nossa fam�lia.", "- De como em meio a dor e a vergonha eu prometi que iria para longe, mas voltaria com o", "conhecimento capaz de sobrepujar a Tormenta."},
													 {"- Deixando meus pensamentos de lado por um instante, h� algo que queiram discutir?", "- Uma nova disputa eu presumo, estou certo?", " ", " "},
													 {"- Agora deixe-me lhe dar um conselho, quando voc� se prop�em a participar de um espet�culo", "ou apresenta��o voc� n�o deve faltar com sua palavra abandonando seus deveres e", "responsabilidades.", "- N�o siga meu exemplo indo para longe do forte e do Sargento Morgan para evitar de usar um"}, 
													 {"uniforme rid�culo e para n�o precisar encarar um futuro em que n�o poderei evitar de me tornar", "o espelho de meu pai.", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Bem, se voc�s n�o t�m nada a tratar comigo, pe�o que me deixem a s�s.", " ", " ", " "},	
	// 108-------------------------- se o Arius [[[N�O]]] for o escolhido e desistiu == 3 no meio da �ltima luta = |1|0|0|6|3| -------------------
													 {"- Dessa vez vamos direto ao assunto em quest�o.", "- Voc�s realmente desejam batalhar nesse exato momento?", " ", " "},
													 {"- Ou�am bem porque n�o irei me repetir.", "- Mesmo ap�s meus constantes avisos, voc� continuou faltando com sua palavra e nos", "abandonando em meio ao espet�culo.", "- J� basta, n�o irei mais tolerar tau comportamento desonroso de sua parte, essa ser� a �ltima"},
													 {"vez que dividiremos o campo de batalha se voc� prosseguir com tau comportamento, esteja", "ciente.", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Ent�o n�o desperdicem meu tempo com bobagens.", " ", " ", " "},	
	// 112-------------------------- se o Arius [[[N�O]]] for o escolhido e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
													 {"- Shiiii, falem baixo.", "- Percebo que voc�s est�o se questionando se estou tentando ouvir uma conversa.", "- Bem, quando Kiki diz \"o que uni os c�es das Colinas na realidade � a fofoca\" ela n�o est�", "- Bem, quando voc�, Kiki, diz \"o que uni os c�es das Colinas na realidade � a fofoca\" n�o est�", "totalmente errada."},
													 {"- Arg, j� se afastaram muito para eu poder ouvi-los, agora terei que me contentar com a metade", "do ocorrido, que dor terr�vel!", "- Sim, � de meu conhecimento que ouvir a conversa alheia � falta de educa��o, porem em", "muitas ocasi�es � necess�rio esse subterf�gio."},
													 {"- Por exemplo, na grande maioria das miss�es investigativas a obten��o de informa��es adv�m", "dessa forma, se Ayla, um ser t�o puro com nobres inten��es, se utiliza dessa pr�tica para fazer", "dessa forma, se voc�, Ayla, um ser puro e com nobres inten��es utiliza esta pr�tica para fazer", "seus atos de bondade, n�o vejo como seria estritamente mau essa maneira de se obter", "informa��es."},
													 {"- Sim, sim, voc� tamb�m possui bons argumentos, todavia n�o detemos de mais tempo para", "prosseguirmos com esse debate.", " ", " "},
													 {"- Preciso saber se voc�s gostariam que come��ssemos uma competi��o amistosa agora ou", "n�o?", " ", " "},
													 {"- Estou confuso, o ", "sim", "n�o", " foi para agora ou para n�o?", "- Vejam o que acontece quando ou�o hist�rias pela metade, minha mente se divide entre a", "curiosidade e o assunto atual e come�o a me embananar todo.", " "},
													 {"- Deixe-me reformular a pergunta.", "- Voc�s gostariam de se juntar a mim em uma competi��o amistosa?", " ", " "},
													 {"- �timo! Durante os preparativos deixe-me dividir o que eu consegui escutar antes de chegarem.", " ", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Uma l�stima devo dizer, contudo, com esse tempo de sobra poderemos retornar ao nosso", "debate sobre a moralidade em se obter informa��es sigilosas.", " ", " "},
	// 121-------------------------- se o Arius [[[N�O]]] for o escolhido e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
													 {"- Meus parab�ns! Voc�s venceram a sua primeira disputa e eu milagrosamente n�o acabei", "estirado no ch�o ardendo em chamas.", "- Dever�amos apreciar esse momento raro comemorando com m�sicas e poesias... J� sei, vou", "realizar um sarau!"},
													 {"- Infelizmente planejar tau evento mesmo que pequeno � demorado e trabalhoso, ent�o terei", "que realiz�-lo em outra ocasi�o de comemora��o.", "- Claro, convidarei Kiki, pois ela nunca me perdoaria se eu fizesse um sarau sem sua presen�a,", "n�o importando o qu�o ruim fosse.", "- Claro Kiki, voc� ser� minha convidada de honra. Eu nunca poderia fazer um sarau sem sua", "presen�a no palco."},
													 {"- Creio que Ayla tamb�m viria para poder cobrar entradas, j� que ainda estou profundamente", "- Creio que voc�, Ayla, gostaria de vim para cobrar entradas, j� que ainda estou profundamente", "endividado com ela.", "endividado com voc�.", "- Por Tanna-Toh! Esse planejamento j� est� come�ando a ficar muito complicado, pensarei", "melhor mais tarde."},
													 {"- Por enquanto devo perguntar se voc�s querem desfrutar de outra competi��o, o que me", "dizem?", " ", " "},
													 {"- Gosto que sua confian�a na vit�ria continua a crescer, quase me esque�o de todas as", "derrotas, quedas e queimaduras que j� tive, quase.", " ", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
													 {"- Ent�o pe�o a ajuda de voc�s para obter algumas ideias, pois pretendo declamar poesias de", "minha autoria na festividade e bem, alguns dos meus trabalhos vindouros n�o s�o adequados", "para o p�blico geral.", " "},
	// 127-------------------------- se o Arius [[[N�O]]] for o escolhido e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
													 {"- Que bom ver voc�s novamente, contudo, devo dizer que n�o me parecem muito bem, o que", "ocorreu?", "- Est�o borocox� por sua �ltima derrota aqui?", "- Horas, n�o se deprimam ap�s uma falha, Mestre Luriel sempre me falava que a falha � parte", "crucial do processo de aprendizagem e que cada tentativa minha me levaria rumo ao acerto."},	
													 {"- Veja, uma parte importante de mim fora roubada por uma entidade folcl�rica das Colinas", "Centrais, ele me induziu a fazer coisas que de outrora jamais faria.", " ", " "},
													 {"- Entretanto, com a ajuda de meus companheiros, minha matilha, consegui recuperar o controle", "do meu corpo e da minha vida por completo e a criatura recebeu seu castigo e sua reden��o,", "outrora conhecido como Ladr�o de Rabos hoje � o Guardi�o de Rabos.", " "},
													 {"- Eu constantemente caia em combate enquanto ardia em chamas, mas gra�as a minha", "perseveran�a eu cont�nuo caindo, porem meus companheiros n�o.", "- Ent�o arrumem suas posturas, encham o peito com determina��o e v�o tentar mais uma vez!", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo."},
	// 131-------------------------- se o Arius [[[N�O]]] for o escolhido e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
													 {"*Sussurros*", "- O que est� achando de nossa leitura?", " ", " "},
													 {"- Para falar a verdade, eu estou bem intrigada com a utiliza��o de armas de p�lvora e, que os", "deuses benignos me perdoe, estou com vontade de adquiri-las.", " ", " "},	
													 {"- Nada Mais que a Verdade armas de p�lvora s�o ilegais no reinado e voc� sabe.", " ", " ", " "},
													 {"- Sei, sei sim.", "- E inclusive penso que seria engra�ado se voc� aderi-se a elas se tornando uma vaca cowboy.", " ", " "},
													 {"- Nada Mais que a Verdade!", "- Bem, nesse caso, se eu seguisse o rumo c�mico de nossa conversa, eu diria que se voc�", "conseguisse adquiri-las, o seu nome deveria mudar de Nada Mais que a Verdade Reston para", "Nada Mais que a Bala Reston."},
													 {"- Arius!", "- Sinceramente n�o sei o que sentir com o trocadilho do meu nome, mas sei que estou muito", "sem jeito agora.", " "},
													 {"- Voc� que come�ou.", " ", " ", " "},
													 {"- AAAAAAH!", "- Voc�s nos assustaram chegando assim de fininho... pela nossa frente... e sem se esconder,", "por favor n�o fa�am mais isso.", "- Voc�s querem saber o que estamos fazendo?"},
													 {"- N�s estamos lendo um livro proibido que o Arius roubou dos Gallobalts h� muito tempo atr�s.", " ", " ", " "},
													 {"- Nada Mais que a Verdade!", " ", " ", " "},
													 {"- Desculpe Arius, mas voc� sabe que eu voluntario informa��es mesmo n�o sendo solicitadas.", " ", " ", " "},
													 {"- Ela tem raz�o, quando n�s, os C�es das Colinas, estivemos aqui pela primeira vez, eu fui", "influenciado pelo Ladr�o de Rabos a roubar esse livro da biblioteca da fam�lia Gallobalt.", "- Mas juro que o devolvi! N�o sei como ele veio parar em meus equipamentos de viagem", "novamente e junto com mais dois livros."},
													 {"- Perguntem a Kiki se n�o acreditam em minhas palavras, ela estava presente quando o devolvi.", "- Voc�, Kiki, estava presente quando o devolvi, pode confirmar o que digo.", "- Acredito que seja mais uma das artimanhas do Ladr�o de Rabos.", "- S� um ser devoto de Hyninn poderia fazer tau ato de esc�rnio, sorte a minha que n�o temos", "ningu�m assim nos C�es das Colinas."},
													 {"- Deixando esse assunto de lado por agora, voc�s gostariam de desfrutar de uma animada", "competi��o entre companheiros de equipe?", " ", " "},
													 {"- �timo! Entretanto,", "- Se esse � o caso,", " antes de prosseguirmos, eu gostaria de devolver o livro novamente,", "contudo, eu estou muito envergonhado de meus atos passados para encara-los.", "- Se me permite perguntar Nada Mais que a Verdade, o que faria em meu lugar?", " "},
													 {"- F�cil, primeiro eu teria ido at� eles e avisado que cometeria um crime, assim como eu fiz com", "os meus pais quando avisei que fugiria para n�o participar do suic�dio coletivo da minha fam�lia.", "- Creio que minha resposta n�o te ajudou com o seu problema, desculpe Arius.", "As apresenta��es SEM interfer�ncia ganhar�o +1 de apelo.", " "},
	// 147-------------------------- se o Arius [[[N�O]]] for o escolhido e derrota na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
													 {"- Ol�, eu sei que voc�s est�o querendo falar com o Arius e n�o comigo, o que faz todo o sentido", "j� que n�o tenho muito trato social e falo demais, mas ele est� ocupado agora terminando de", "escrever um pergaminho, ent�o ao inv�s dele voc�s conversariam comigo?", "- Fico genuinamente feliz com a resposta de voc�s."},
													 {"- Olha, eu estava aqui debatendo com Arius e a nossa conversa acabou passando pelos", "Gallobalts e rumou em outra dire��o, porem teve algo que eu n�o entendi direito e n�o tive", "tempo de perguntar, j� que ele n�o consegue parar de falar igual a mim.", " "},
													 {"- Ele mencionou alguns dos la�os sangu�neos da fam�lia e eu acabei me perdendo um pouco,", "ent�o me veio a pergunta, afinal, os Gallobalts s�o ou n�o incestuosos?", "- Devo dizer que estou constrangida por fazer essa pergunta, mas eu quero realmente saber.", "- Se for poss�vel voc�s podem desenhar para mim?"},
													 {" ", " ", " ", " "},
													 {"- N�o s�o?", "- Obrigada por me responder, fico extremamente aliviada, essa quest�o estava me matando", "metaforicamente falando e se ela persistisse eu n�o aquentaria e teria que ir perguntar aos", "Gallobalts o que seria uma situa��o muito desagrad�vel para todos n�s."},
													 {"- Sauda��es, j� terminei meus afazeres e vejo que voc�s e Nada Mais que a Verdade est�o", "tendo um debate bem interessante, posso me juntar?", " ", " "},
													 {"- Infelizmente Arius j� terminamos, mas voc� est� certo, foi realmente interessante, que pena", "que voc� perdeu.", "- Tenho que ir agora, nos vemos depois para conversarmos mais.", " "},
													 {"- Como assim! J� � a quarta vez que n�o consigo chegar a tempo em um debate, o que est�", "acontecendo comigo?", " ", " "},
													 {"- Ent�o, voc�s vieram me chamar para um novo confronto?", " ", " ", " "},
													 {"- Vamos depressa, n�o posso arriscar perder mais nada interessante por aqui.", " ", " ", " "},
													 {"- Ent�o... voc�s podem me falar sobre o que estavam debatendo?", " ", " ", " "},											 
	// 158-------------------------- se o Arius [[[N�O]]] for o escolhido e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
													 {"- Devo me apresar!", "- O espet�culo j� est� atrasado e terei que recitar minhas poesias sozin-", " ", " "},
													 {"- A plateia n�o quer escutar somente suas poesias ou voc� esqueceu com quem dividir� o palco", "hoje?", " ", " "},
													 {"- Kiki! � bom v�-la a salvo, j� estava deveras preocupado com sua demora.", " ", " ", " "},
													 {"- Arius, uma barda nunca chega antes ou depois, ela chega quando ela tiver que chegar.", "- E Ignis n�o consegue andar muito r�pido como voc� j� sabe.", " ", " "},
													 {"- Ufa, comecei at� cogitar que n�o quisesse mais realizar um sarau comigo e-", " ", " ", " "},
													 {"- Mas � claro que eu quero fazer um sarau com voc�!", "- Eu nunca te perdoaria se voc� fizesse um sem mim.", " ", " "},
													 {"- N�o importando o qu�o ruim fosse!?", " ", " ", " "},
													 {"- Ai voc� j� est� se excedendo, vai com calma, principalmente porque n�o tem como um evento", "ser ruim quando estou presente.", "- Menos aquele na vila purista, assim, eu fui excepcional, o problema era a plateia.", "- Vamos Arius, se voc� continuar falando n�s n�o poderemos come�ar a apresenta��o."},
													 {"- Mas eu n�o est-", " ", " ", " "},
													 {"- Shiii, vem logo.", " ", " ", " "},		
	// 168--------------------------- se j� venceu 3 vezes o Arius e falou com ele -------------------
													 {"- Por que voltamos? Pensei que iriamos desafiar os outros C�es das Colinas.", "- Voc�s voltaram? Pensei que iriam desafiar os outros C�es das Colinas.", " ", " "}};




	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Arius() {
		sorteio();
	}
	
	public void sorteio () {
		/*for(int i=0; i<8; i++) {
	        mylist.add(i);
		}*/
		
		mylist.add(2); mylist.add(5); mylist.add(6); mylist.add(3);
		
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