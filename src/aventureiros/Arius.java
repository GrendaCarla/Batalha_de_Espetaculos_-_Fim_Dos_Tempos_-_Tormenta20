package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Arius {
	//                                   apelo,                interferência,          tipo interferência (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{3, 0, 3, 5, 1, 0, 5, 5}, {2, 0, 1, 0, 5, 10, 0, 0}, {2, 4, 0, -1, 3, 6, -1, -1}, {1, 0, 0, 1, 0, 0, 1, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Chifrada", "Estratégia em Combate", "O Mamilo Rosa", "Olimpíada Artoniana", "Diplomacia", "5 é 20", "Gládio e Escudo", "Declamar Poema"};
	private String [][] ConteudoDescricao = {  {"Com teus cornos perfure a tora resistente.", "Com tua força taurina erga-a no ar.", "O mais longe que puder lançe-a na tua frente,", "Então torça para o tronco ninguém acertar.", "Esta habilidade afeta o campo acima de você."},
											   {"Somente através da violência a vitória não advêm.", "Ao compreender aqueles que acima estão", "Vantagem poderá ganhar também", "Ao ver seus movimentos com antecipação.", "Esta habilidade zera as interferências ganhadas antes da sua ação nessa rodada."},
											   {"Ao tirar sua couraça em meio ao espetáculo", "Todos podem vê-lo e é impossível de desviar.", "Por mais que seja um órgão prosaico", "O único mamilo rosa consegue a todos hipnotizar.", "Esta habilidade afeta todos os campos acima de você."},
											   {"Acenda sua pira do espírito esportivo", "Participando de diversos jogos consecutivos", "Como salto pela paliçada, flechada nos irmãos,", "Esconde-esconde com baratas e arremesso com explosão.", " "},
											   {"Use a perícia diplomática na audiência", "Para que possa com ela argumentar e convencer", "Que o primeiro colocado deve apresentar a imponência", "Em sua demonstração, o que dessa vez não conseguiu fazer.", "Esta habilidade afeta o primeiro campo."},
											   {"Jogando cinco d20 uma aposta você vai fazer.", "Não há repetição nos números contemplados.", "10 apelos você ganha se 5 o dado conceber.", "Então camarada, você está tentado?", "Esta habilidade te concede 10 apelos se o número 5 sair em um dos dados."},
											   {"Lembre-se do seu treinamento nas legiões do império.", "Com o gládio em mãos e o escudo bem posicionado", "Demonstre suas táticas e o conhecimento bélico", "Evitando se queimar caso for derrubado.", " "},
											   {"Se quiseres emocionar a plateia", "Terás que revelar seus sentimentos dolorosos.", "Recita-los em forma de prosa poética", "Parece certamente um dos modos mais graciosos.", " "}};
	
	
	// 0---------------------------- primeira interação se o Arius for o escolhido ---------------------- 
	private String [][] ConteudoEscolhaAdversario = {{"- Chegamos!", "- Este é o local do qual lhe falei durante o nosso percurso.", "- Garanto que não existe lugar mais belo nas Colinas Centrais do que a plantação de gorad da", "família Gallobalt."},
													 {"- Bem, talvez o Cemitério Errante ou o Templo do Vácuo, porem suas belezas advém de eventos", "devastadores ao contrário deste local.", "- E você sabe me dizer o porquê?", " "}, 
													 {"- Não, não é porque anteriormente era uma enorme plantação de archibold, uma droga terrível", "que destrói inúmeras vidas por toda Arton, e Ignis, o paladino de Thyatis, deu-lhes uma", "segunda chance para consertarem suas ações, isso é pura bobagem.", " "},
													 {"- Este sim é um belo lugar porque agora poderei desfrutar do doce sabor do gorad novamente!", "- Nossos anfitriões foram muitos gentis e me deixaram pegar um pouquinho dessa iguaria por", "toda a ajuda que os Cães das Colinas disponibilizaram ao resolver o problema com Syvarian e", "Gog-Magog, o seu peixe navio voador."},
													 {"- E assim que for possível visitaremos Kiki para que ela use seus dons culinários e prepare para", "mim mais desse doce irresistível.", " ", " "},
													 {"- Como assim! Eu não sou um aproveitador egoísta, ao contrário, sou um devoto de Tanna-Toh", "e ela ensina que devemos sempre compartilhar todo tipo de conhecimento para que possamos", "crescer e evoluir juntos.", "- Mas isso não se aplica ao meu gorad!"},
													 {"- Venha, vamos nos juntar a Nada Mais que a Verdade, ela adoraria participar do nosso debate.", "- O que foi, algo está te incomodando?", " ", " "}, 
													 {"- Ah! Você quer me propor uma disputa amistosa para compartilharmos nossas habilidades", "com os outros Cães das Colinas?", " ", " "},
													 {"- Que ideia maravilhosa!", "- Vamos, se aprese, eu quero muito saber o quão bem podemos ir se começarmos a disputa", "com a vantagem.", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Então segure isso aqui para mim enquanto eu pego um pouquinho mais.", " ", " ", " "},
	// 10---------------------------- se o Arius for o escolhido e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
													 {"- Eu não poderia imaginar que os Gallobalts ficariam tão zangados com a quantidade de gorad", "que peguei.", " ", " "},
													 {"- Uma vez quando os Cães das Colinas estavam partindo de Placides eu vi Ayla levando uma", "grande quantidade de suprimentos, nesse momento eu a questionei e ela me contou que aqui", "nas Colinas Centrais pouco significa tudo que puder carregar e que se recusar a fazer isso é", "uma grande desfeita com a cultura local."},
													 {"- Talvez os Gallobalts só não estejam familiarizados com esse costume... mesmo morando aqui", "há muito tempo...", "- O que estou cogitando em minha mente!", "- Ayla nunca mentiria ou pegaria algo que não lhe pertencesse, ela é um ser de índole pura!"},
													 {"- Agora, deixando os mal-entendidos de lado, eu ainda gostaria de ver o quão bom poderíamos", "ir em um combate em que começássemos com a vantagem.", "- Pense nas possibilidades matemáticas dessa variação!", " "},
													 {"- Você se juntaria a mim nesse experimento?", " ", " ", " "},
													 {"- Maravilha! Teremos altos debates depois da luta, vamos, vamos.", " ", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Bem, devo dizer que estou um pouco desapontado, estava muito curioso para aprender mais", "e Mestre Luriel sempre me falou que a curiosidade é a fagulha da mente e que devemos... blá,", "blá, blá.", " "},												 
    // 17--------------------------- se o Arius for o escolhido e desistiu <= 2 no meio da última luta = |1|0|0|1|3| -------------------
													 {"- Saudações! não percebi sua aproximação, estava absorto em meus pensamentos relembrando", "alguns dos eventos que me trouxeram até aqui.", " ", " "},
													 {"- Senador Glavus, meu pai, me mandou para XIV da Grande Savana, uma legião afastada na", "divisa da fronteira do Império, enquanto meu irmão mais novo ficava para combater a", "tempestade rubra cumprindo seus deveres como minotauro.", " "},
													 {"- Lembro-me da falta de fé que possuía em mim e como só se preocupava em esconder a falha,", "eu, de nossa família.", "- De como em meio a dor e a vergonha eu prometi que iria para longe, mas voltaria com o", "conhecimento capaz de sobrepujar a Tormenta."},
													 {"- Deixando meus pensamentos de lado por um instante, há algo que queira discutir?", "- Uma nova disputa eu presumo?", " ", " "},
													 {"- Agora deixe-me lhe dar um conselho, quando você se propõem a participar de um espetáculo", "ou apresentação você não deve faltar com sua palavra abandonando seus deveres e", "responsabilidades.", "- Não siga meu exemplo indo para longe do forte e do Sargento Morgan para evitar de usar um"}, 
													 {"uniforme ridículo e para não precisar encarar um futuro em que não poderei evitar de me tornar", "o espelho de meu pai.", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Bem, se não tem nada a tratar comigo, peço que me deixe a sós, mais tarde eu me reunirei", "a você novamente.", " ", " "},	
	// 24--------------------------- se o Arius for o escolhido e desistiu == 3 no meio da última luta = |1|0|0|6|3| -------------------
													 {"- Vamos direto ao assunto em questão. Você realmente deseja batalhar nesse exato momento?", " ", " ", " "},
													 {"- Ouça bem porque não irei me repetir.", "- Mesmo após meus constantes avisos, você continuou faltando com sua palavra e nos", "abandonando em meio ao espetáculo.", "- Já basta, não irei mais tolerar tau comportamento desonroso de sua parte, essa será a última"},
													 {"vez que dividiremos o campo de batalha se você prosseguir com tau comportamento, esteja", "ciente.", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Então não desperdice meu tempo com bobagens, se não iremos batalhar aqui, então vamos", "logo para o local de nossa disputa.", " ", " "},	
	// 28--------------------------- se o Arius for o escolhido e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
													 {"- Shiiii, fale baixo.", "- Percebo que está se questionando se estou tentando ouvir uma conversa.", "- Bem, quando Kiki diz \"o que uni os cães das Colinas na realidade é a fofoca\" ela não está", "totalmente errada."},
													 {"- Arg, já se afastaram muito para eu poder ouvi-los, agora terei que me contentar com a metade", "do ocorrido, que dor terrível!", "- Sim, é de meu conhecimento que ouvir a conversa alheia é falta de educação, porem em", "muitas ocasiões é necessário esse subterfúgio."},
													 {"- Por exemplo, na grande maioria das missões investigativas a obtenção de informações advém", "dessa forma, se Ayla, um ser tão puro com nobres intenções, se utiliza dessa prática para fazer", "seus atos de bondade, não vejo como seria estritamente mau essa maneira de se obter", "informações."},
													 {"- Sim, sim, você também possui bons argumentos, todavia não detemos de mais tempo para", "prosseguirmos com esse debate.", " ", " "},
													 {"- Preciso saber se você gostaria que começássemos uma competição amistosa agora ou não?", " ", " ", " "},
													 {"- Estou confuso, o ", "sim", "não", " foi para agora ou para não?", "- Veja o que acontece quando ouço histórias pela metade, minha mente se divide entre a", "curiosidade e o assunto atual e começo a me embananar todo.", " "},
													 {"- Deixe-me reformular a pergunta.", "- Você gostaria de se juntar a mim em uma competição amistosa?", " ", " "},
													 {"- Ótimo! Durante os preparativos deixe-me dividir o que eu consegui escutar antes da sua", "chegada.", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Uma lástima devo dizer, contudo, com esse tempo de sobra poderemos retornar ao nosso", "debate sobre a moralidade em se obter informações sigilosas.", " ", " "},
	// 37--------------------------- se o Arius for o escolhido e 1º vitória na última luta = |1|1|0|0|1| -------------------
													 {"- Vencemos, vencemos, vencemos nossa primeira disputa e eu não acabei estirado no chão", "ardendo em chamas!", "- Deveríamos apreciar esse momento raro comemorando com músicas e poesias... Já sei,", "vamos realizar um sarau!"},
													 {"- Infelizmente planejar tau evento mesmo que pequeno é demorado e trabalhoso, então teremos", "que realizá-lo em outra ocasião de comemoração.", "- Claro, terei que convidar Kiki, pois ela nunca me perdoaria se eu fizesse um sarau sem sua", "presença, não importando o quão ruim fosse."},
													 {"- Creio que Ayla também viria para poder cobrar entradas, já que ainda estou profundamente", "endividado com ela.", "- Por Tanna-Toh! Esse planejamento já está começando a ficar muito complicado, pensarei", "melhor mais tarde."},
													 {"- Por enquanto vejo que tem algo a me perguntar, o que foi? Você já quer desfrutar de outra", "competição?", " ", " "},
													 {"- Gosto que sua confiança em nossa vitória continua a crescer, quase me esqueço de todas as", "quedas e queimaduras que já tive, quase.", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Então peço sua ajuda para obter algumas ideias, pois pretendo declamar poesias de minha", "autoria na festividade e bem, alguns dos meus trabalhos vindouros não são adequados para o", "público geral.", " "},
	// 43--------------------------- se o Arius for o escolhido e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
													 {"- Devo dizer que você não parece muito bem, o que ocorreu? Está borocoxô por nossa última", "derrota aqui?", "- Horas, não se deprima após uma falha, Mestre Luriel sempre me falava que a falha é parte", "crucial do processo de aprendizagem e que cada tentativa minha me levaria rumo ao acerto.", " ", " "},	
													 {"- Veja, uma parte importante de mim fora roubada por uma entidade folclórica das Colinas", "Centrais, ele me induziu a fazer coisas que de outrora jamais faria.", " ", " "},
													 {"- Entretanto, com a ajuda de meus companheiros, minha matilha, consegui recuperar o controle", "do meu corpo e da minha vida por completo e a criatura recebeu seu castigo e sua redenção,", "outrora conhecido como Ladrão de Rabos hoje é o Guardião de Rabos.", " "},
													 {"- Eu constantemente caia em combate enquanto ardia em chamas, mas graças a minha", "perseverança eu contínuo caindo, porem meus companheiros não.", "- Então arrume sua postura, encha o peito com determinação e vamos tentar mais uma vez!", "As apresentações SEM interferência ganharão +1 de apelo."},
	// 47--------------------------- se o Arius for o escolhido e 2º vitória na última luta  = |1|2|4|0|1| -------------------
													 {"*Sussurros*", "- O que está achando de nossa leitura?", " ", " "},
													 {"- Para falar a verdade, eu estou bem intrigada com a utilização de armas de pólvora e, que os", "deuses benignos me perdoe, estou com vontade de adquiri-las.", " ", " "},	
													 {"- Nada Mais que a Verdade, armas de pólvora são ilegais no reinado e você sabe.", " ", " ", " "},
													 {"- Sei, sei sim.", "- E inclusive penso que seria engraçado se você aderi-se a elas se tornando uma vaca cowboy.", " ", " "},
													 {"- Nada Mais que a Verdade!", "- Bem, nesse caso, se eu seguisse o rumo cômico de nossa conversa, eu diria que se você", "conseguisse adquiri-las, o seu nome deveria mudar de Nada Mais que a Verdade Reston para", "Nada Mais que a Bala Reston."},
													 {"- Arius!", "- Sinceramente não sei o que sentir com o trocadilho do meu nome, mas sei que estou muito", "sem jeito agora.", " "},
													 {"- Você que começou.", " ", " ", " "},
													 {"- AAAAAAH!", "- Você nos assustou chegando assim de fininho... pela nossa frente... e sem se esconder, por", "favor não faça mais isso.", "- Você quer saber o que estamos fazendo?"},
													 {"- Nós estamos lendo um livro proibido que o Arius roubou dos Gallobalts há muito tempo atrás.", " ", " ", " "},
													 {"- Nada Mais que a Verdade!", " ", " ", " "},
													 {"- Desculpe Arius, mas você sabe que eu voluntario informações mesmo não sendo solicitadas.", " ", " ", " "},
													 {"- Ela tem razão, quando nós, os Cães das Colinas, estivemos aqui pela primeira vez, eu fui", "influenciado pelo Ladrão de Rabos a roubar esse livro da biblioteca da família Gallobalt.", "- Mas juro que o devolvi! Não sei como ele veio parar em meus equipamentos de viagem", "novamente e junto com mais dois livros."},
													 {"- Pergunte a Kiki se não acredita em minhas palavras, ela estava presente quando o devolvi.", "- Acredito que seja mais uma das artimanhas do Ladrão de Rabos.", "- Só um ser devoto de Hyninn poderia fazer tau ato de escárnio, sorte a minha que não temos", "ninguém assim nos Cães das Colinas."},
													 {"- Deixando esse assunto de lado por agora, você gostaria de desfrutar de uma animada", "competição entre companheiros de equipe?", " ", " "},
													 {"- Ótimo! Entretanto,", "- Se esse é o caso,", " antes de prosseguirmos, eu gostaria de devolver o livro novamente,", "contudo, eu estou muito envergonhado de meus atos passados para encara-los.", "- Se me permite perguntar Nada Mais que a Verdade, o que faria em meu lugar?", " "},
													 {"- Fácil, eu teria ido até eles e avisado que cometeria um crime, assim como eu fiz com os meus", "pais quando avisei que fugiria para não participar do suicídio coletivo da minha família.", "- Creio que minha resposta não te ajudou com o seu problema, desculpe Arius.", "As apresentações SEM interferência ganharão +1 de apelo.", " "},
	// 63--------------------------- se o Arius for o escolhido e derrotado na última luta com 2 vitórias = |1|2|1|0|2| -------------------
													 {"- Olá, eu sei que você está querendo falar com o Arius e não comigo, o que faz todo o sentido", "já que não tenho muito trato social e falo demais, mas ele está ocupado agora terminando de", "escrever um pergaminho, então ao invés dele você conversaria comigo?", "- Estou genuinamente feliz com sua resposta!"},
													 {"- Olha, eu estava aqui debatendo com Arius e a nossa conversa acabou passando pelos", "Gallobalts e rumou em outra direção, porem teve algo que eu não entendi direito e não tive", "tempo de perguntar, já que ele não consegue parar de falar igual a mim.", " "},
													 {"- Ele mencionou alguns dos laços sanguíneos da família e eu acabei me perdendo um pouco,", "então me veio a pergunta, afinal, os Gallobalts são ou não incestuosos?", "- Devo dizer que estou constrangida por fazer essa pergunta, mas eu quero realmente saber.", "- Se for possível com deseinhos."},
													 {" ", " ", " ", " "},
													 {"- Não são?", "- Obrigada por me responder, fico extremamente aliviada, essa questão estava me matando", "metaforicamente falando e se ela persistisse eu não aquentaria e teria que ir perguntar aos", "Gallobalts o que seria uma situação muito desagradável para todos nós."},
													 {"- Saudações, já terminei meus afazeres e vejo que você e Nada Mais que a Verdade estão tendo", "um debate bem interessante, posso me juntar a vocês?", " ", " "},
													 {"- Infelizmente Arius já terminamos, mas você está certo, foi realmente interessante, que pena", "que você perdeu.", "- Tenho que ir agora, nos vemos depois para conversarmos mais.", " "},
													 {"- Como assim! Já é a quarta vez que não consigo chegar a tempo em um debate, o que está", "acontecendo comigo?", " ", " "},
													 {"- Então, você veio me chamar para um novo confronto com os Cães das Colinas?", " ", " ", " "},
													 {"- Vamos depressa, não posso arriscar perder mais nada interessante por aqui.", " ", " ", " "},
													 {"- Então... você pode me falar sobre o que vocês estavam debatendo?", " ", " ", " "},											 
	// 74--------------------------- se o Arius for o escolhido e 3º vitória na última luta = |1|3|0|0|1| -------------------
													 {"- Devemos nos apresar!", "- O espetáculo já está atrasado e terei que recitar minhas poesias sozin-", " ", " "},
													 {"- A plateia não quer escutar somente suas poesias ou você esqueceu com quem dividirá o palco", "hoje?", " ", " "},
													 {"- Kiki! É bom vê-la a salvo, já estava deveras preocupado com sua demora.", " ", " ", " "},
													 {"- Arius, uma barda nunca chega antes ou depois, ela chega quando ela tiver que chegar.", "- E Ignis não consegue andar muito rápido como você já sabe.", " ", " "},
													 {"- Ufa, comecei até cogitar que não quisesse mais realizar um sarau comigo e-", " ", " ", " "},
													 {"- Mas é claro que eu quero fazer um sarau com você!", "- Eu nunca te perdoaria se você fizesse um sem mim.", " ", " "},
													 {"- Não importando o quão ruim fosse!?", " ", " ", " "},
													 {"- Ai você já está se excedendo, vai com calma, principalmente porque não tem como um evento", "ser ruim quando estou presente.", "- Menos aquele na vila purista, assim, eu fui excepcional, o problema era a plateia.", "- Vamos Arius, se você continuar falando nós não poderemos começar a apresentação."},
													 {"- Mas eu não est-", " ", " ", " "},
													 {"- Shiii, vem logo.", " ", " ", " "},		
	// 84--------------------------- se o Arius [[[NÃO]]] for o escolhido e primeira interação |0|0|0|0|0 ---------------------------------
													 {"- Saudações! Devo dizer que sinto-me afortunado em te conhecer finalmente.", "- Creio que vocês possam estar estranhando o local de nosso encontro, contudo, garanto-lhes", "que não existe lugar mais belo nas Colinas Centrais do que a plantação de gorad da família", "Gallobalt."},
													 {"- Bem, talvez o Cemitério Errante ou o Templo do Vácuo, porem suas belezas advém de eventos", "devastadores ao contrário deste local.", "- E você sabe me dizer o porquê?", " "}, 
													 {"- Não, não é porque anteriormente era uma enorme plantação de archibold, uma droga terrível", "que destrói inúmeras vidas por toda Arton, e Ignis, o paladino de Thyatis, deu-lhes uma segunda", "chance para consertarem suas ações, isso é pura bobagem.", " "},
													 {"- Este sim é um belo lugar porque agora poderei desfrutar do doce sabor do gorad novamente!", "- Nossos anfitriões foram muitos gentis e me deixaram pegar um pouquinho dessa iguaria por", "toda a ajuda que os Cães das Colinas disponibilizaram ao resolver o problema com Syvarian e", "Gog-Magog, o seu peixe navio voador."},
													 {"- E assim que for possível ", "peço a você, Kiki, que", "visitarei Kiki para que ela", " use seus dons culinários e prepare para", "mim mais desse doce irresistível.", " ", " "},
													 {"- Como assim! Eu não sou um aproveitador egoísta, ao contrário, sou um devoto de Tanna-Toh", "e ela ensina que devemos sempre compartilhar todo tipo de conhecimento para que possamos", "crescer e evoluir juntos.", "- Mas isso não se aplica ao meu gorad!"},
													 {"- Venham, vamos nos juntar a Nada Mais que a Verdade, ela adoraria participar do nosso", "debate.", "- O que foi, estou esquecendo de algo?", " "}, 
													 {"- Suponho que vocês tenham vindo me propor uma disputa amistosa para compartilharmos", "nossas habilidades no campo de batalha?", " ", " "},
													 {"- Que proposta maravilhosa, vamos!", " ", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Bom, se esse é o caso, segure isso aqui para mim por favor enquanto eu pego um pouquinho", "mais.", " ", " "},
	// 94--------------------------- se o Arius [[[NÃO]]] for o escolhido e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
													 {"- Saudações!", "- Obrigado novamente pela ajuda que me cederam.", "- Infelizmente eu não poderia imaginar que os Gallobalts ficariam tão zangados com a", "quantidade de gorad que peguei."},
													 {"- Uma vez quando os Cães das Colinas estavam partindo de Placides eu vi Ayla levando uma", "- Uma vez quando os Cães das Colinas estavam partindo de Placides eu vi você, Ayla, levando", "grande quantidade de suprimentos, nesse momento eu a questionei e ela me contou que", "uma grande quantidade de suprimentos, nesse momento eu a questionei e você me contou que", "aqui nas Colinas Centrais pouco significa tudo que puder carregar e que se recusar a fazer isso", "é uma grande desfeita com a cultura local."},
													 {"- Talvez os Gallobalts só não estejam familiarizados com esse costume... mesmo morando aqui", "há muito tempo...", "- O que estou pensando!", "- Ayla nunca mentiria ou pegaria algo que não lhe pertencesse, ela é um ser de índole pura!", "- Você nunca mentiria para mim ou me faria pegar algo que não me pertencesse, tolices!"},
													 {"- Agora, deixando os mal-entendidos de lado, eu gostaria de propor uma disputa amistosa para", "compartilharmos nossas habilidades no campo de batalha.", " ", " "},
													 {"- Você aceita minha proposta?", " ", " ", " "},
													 {"- Maravilha! Teremos altos debates depois da luta, vamos, vamos.", " ", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Bem, devo dizer que estou um pouco desapontado, estava muito curioso para aprender mais", "e Mestre Luriel sempre me falou que a curiosidade é a fagulha da mente e que devemos... blá,", "blá, blá.", " "},												 
	// 101-------------------------- se o Arius [[[NÃO]]] for o escolhido e desistiu <= 2 no meio da última luta = |1|0|0|1|3| -------------------	
													 {"- Saudações!", "- Não percebi a aproximação de vocês, estava absorto em meus pensamentos relembrando", "alguns dos eventos que me trouxeram até aqui.", " "},
													 {"- Senador Glavus, meu pai, me mandou para XIV da Grande Savana, uma legião afastada na", "divisa da fronteira do Império, enquanto meu irmão mais novo ficava para combater a", "tempestade rubra cumprindo seus deveres como minotauro.", " "},
													 {"- Lembro-me da falta de fé que possuía em mim e como só se preocupava em esconder a falha,", "eu, de nossa família.", "- De como em meio a dor e a vergonha eu prometi que iria para longe, mas voltaria com o", "conhecimento capaz de sobrepujar a Tormenta."},
													 {"- Deixando meus pensamentos de lado por um instante, há algo que queiram discutir?", "- Uma nova disputa eu presumo, estou certo?", " ", " "},
													 {"- Agora deixe-me lhe dar um conselho, quando você se propõem a participar de um espetáculo", "ou apresentação você não deve faltar com sua palavra abandonando seus deveres e", "responsabilidades.", "- Não siga meu exemplo indo para longe do forte e do Sargento Morgan para evitar de usar um"}, 
													 {"uniforme ridículo e para não precisar encarar um futuro em que não poderei evitar de me tornar", "o espelho de meu pai.", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Bem, se vocês não têm nada a tratar comigo, peço que me deixem a sós.", " ", " ", " "},	
	// 108-------------------------- se o Arius [[[NÃO]]] for o escolhido e desistiu == 3 no meio da última luta = |1|0|0|6|3| -------------------
													 {"- Dessa vez vamos direto ao assunto em questão.", "- Vocês realmente desejam batalhar nesse exato momento?", " ", " "},
													 {"- Ouçam bem porque não irei me repetir.", "- Mesmo após meus constantes avisos, você continuou faltando com sua palavra e nos", "abandonando em meio ao espetáculo.", "- Já basta, não irei mais tolerar tau comportamento desonroso de sua parte, essa será a última"},
													 {"vez que dividiremos o campo de batalha se você prosseguir com tau comportamento, esteja", "ciente.", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Então não desperdicem meu tempo com bobagens.", " ", " ", " "},	
	// 112-------------------------- se o Arius [[[NÃO]]] for o escolhido e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
													 {"- Shiiii, falem baixo.", "- Percebo que vocês estão se questionando se estou tentando ouvir uma conversa.", "- Bem, quando Kiki diz \"o que uni os cães das Colinas na realidade é a fofoca\" ela não está", "- Bem, quando você, Kiki, diz \"o que uni os cães das Colinas na realidade é a fofoca\" não está", "totalmente errada."},
													 {"- Arg, já se afastaram muito para eu poder ouvi-los, agora terei que me contentar com a metade", "do ocorrido, que dor terrível!", "- Sim, é de meu conhecimento que ouvir a conversa alheia é falta de educação, porem em", "muitas ocasiões é necessário esse subterfúgio."},
													 {"- Por exemplo, na grande maioria das missões investigativas a obtenção de informações advém", "dessa forma, se Ayla, um ser tão puro com nobres intenções, se utiliza dessa prática para fazer", "dessa forma, se você, Ayla, um ser puro e com nobres intenções utiliza esta prática para fazer", "seus atos de bondade, não vejo como seria estritamente mau essa maneira de se obter", "informações."},
													 {"- Sim, sim, você também possui bons argumentos, todavia não detemos de mais tempo para", "prosseguirmos com esse debate.", " ", " "},
													 {"- Preciso saber se vocês gostariam que começássemos uma competição amistosa agora ou", "não?", " ", " "},
													 {"- Estou confuso, o ", "sim", "não", " foi para agora ou para não?", "- Vejam o que acontece quando ouço histórias pela metade, minha mente se divide entre a", "curiosidade e o assunto atual e começo a me embananar todo.", " "},
													 {"- Deixe-me reformular a pergunta.", "- Vocês gostariam de se juntar a mim em uma competição amistosa?", " ", " "},
													 {"- Ótimo! Durante os preparativos deixe-me dividir o que eu consegui escutar antes de chegarem.", " ", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Uma lástima devo dizer, contudo, com esse tempo de sobra poderemos retornar ao nosso", "debate sobre a moralidade em se obter informações sigilosas.", " ", " "},
	// 121-------------------------- se o Arius [[[NÃO]]] for o escolhido e 1º vitória na última luta = |1|1|0|0|1| -------------------
													 {"- Meus parabéns! Vocês venceram a sua primeira disputa e eu milagrosamente não acabei", "estirado no chão ardendo em chamas.", "- Deveríamos apreciar esse momento raro comemorando com músicas e poesias... Já sei, vou", "realizar um sarau!"},
													 {"- Infelizmente planejar tau evento mesmo que pequeno é demorado e trabalhoso, então terei", "que realizá-lo em outra ocasião de comemoração.", "- Claro, convidarei Kiki, pois ela nunca me perdoaria se eu fizesse um sarau sem sua presença,", "não importando o quão ruim fosse.", "- Claro Kiki, você será minha convidada de honra. Eu nunca poderia fazer um sarau sem sua", "presença no palco."},
													 {"- Creio que Ayla também viria para poder cobrar entradas, já que ainda estou profundamente", "- Creio que você, Ayla, gostaria de vim para cobrar entradas, já que ainda estou profundamente", "endividado com ela.", "endividado com você.", "- Por Tanna-Toh! Esse planejamento já está começando a ficar muito complicado, pensarei", "melhor mais tarde."},
													 {"- Por enquanto devo perguntar se vocês querem desfrutar de outra competição, o que me", "dizem?", " ", " "},
													 {"- Gosto que sua confiança na vitória continua a crescer, quase me esqueço de todas as", "derrotas, quedas e queimaduras que já tive, quase.", " ", "As apresentações SEM interferência ganharão +1 de apelo."},
													 {"- Então peço a ajuda de vocês para obter algumas ideias, pois pretendo declamar poesias de", "minha autoria na festividade e bem, alguns dos meus trabalhos vindouros não são adequados", "para o público geral.", " "},
	// 127-------------------------- se o Arius [[[NÃO]]] for o escolhido e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
													 {"- Que bom ver vocês novamente, contudo, devo dizer que não me parecem muito bem, o que", "ocorreu?", "- Estão borocoxô por sua última derrota aqui?", "- Horas, não se deprimam após uma falha, Mestre Luriel sempre me falava que a falha é parte", "crucial do processo de aprendizagem e que cada tentativa minha me levaria rumo ao acerto."},	
													 {"- Veja, uma parte importante de mim fora roubada por uma entidade folclórica das Colinas", "Centrais, ele me induziu a fazer coisas que de outrora jamais faria.", " ", " "},
													 {"- Entretanto, com a ajuda de meus companheiros, minha matilha, consegui recuperar o controle", "do meu corpo e da minha vida por completo e a criatura recebeu seu castigo e sua redenção,", "outrora conhecido como Ladrão de Rabos hoje é o Guardião de Rabos.", " "},
													 {"- Eu constantemente caia em combate enquanto ardia em chamas, mas graças a minha", "perseverança eu contínuo caindo, porem meus companheiros não.", "- Então arrumem suas posturas, encham o peito com determinação e vão tentar mais uma vez!", "As apresentações SEM interferência ganharão +1 de apelo."},
	// 131-------------------------- se o Arius [[[NÃO]]] for o escolhido e 2º vitória na última luta  = |1|2|4|0|1| -------------------
													 {"*Sussurros*", "- O que está achando de nossa leitura?", " ", " "},
													 {"- Para falar a verdade, eu estou bem intrigada com a utilização de armas de pólvora e, que os", "deuses benignos me perdoe, estou com vontade de adquiri-las.", " ", " "},	
													 {"- Nada Mais que a Verdade armas de pólvora são ilegais no reinado e você sabe.", " ", " ", " "},
													 {"- Sei, sei sim.", "- E inclusive penso que seria engraçado se você aderi-se a elas se tornando uma vaca cowboy.", " ", " "},
													 {"- Nada Mais que a Verdade!", "- Bem, nesse caso, se eu seguisse o rumo cômico de nossa conversa, eu diria que se você", "conseguisse adquiri-las, o seu nome deveria mudar de Nada Mais que a Verdade Reston para", "Nada Mais que a Bala Reston."},
													 {"- Arius!", "- Sinceramente não sei o que sentir com o trocadilho do meu nome, mas sei que estou muito", "sem jeito agora.", " "},
													 {"- Você que começou.", " ", " ", " "},
													 {"- AAAAAAH!", "- Vocês nos assustaram chegando assim de fininho... pela nossa frente... e sem se esconder,", "por favor não façam mais isso.", "- Vocês querem saber o que estamos fazendo?"},
													 {"- Nós estamos lendo um livro proibido que o Arius roubou dos Gallobalts há muito tempo atrás.", " ", " ", " "},
													 {"- Nada Mais que a Verdade!", " ", " ", " "},
													 {"- Desculpe Arius, mas você sabe que eu voluntario informações mesmo não sendo solicitadas.", " ", " ", " "},
													 {"- Ela tem razão, quando nós, os Cães das Colinas, estivemos aqui pela primeira vez, eu fui", "influenciado pelo Ladrão de Rabos a roubar esse livro da biblioteca da família Gallobalt.", "- Mas juro que o devolvi! Não sei como ele veio parar em meus equipamentos de viagem", "novamente e junto com mais dois livros."},
													 {"- Perguntem a Kiki se não acreditam em minhas palavras, ela estava presente quando o devolvi.", "- Você, Kiki, estava presente quando o devolvi, pode confirmar o que digo.", "- Acredito que seja mais uma das artimanhas do Ladrão de Rabos.", "- Só um ser devoto de Hyninn poderia fazer tau ato de escárnio, sorte a minha que não temos", "ninguém assim nos Cães das Colinas."},
													 {"- Deixando esse assunto de lado por agora, vocês gostariam de desfrutar de uma animada", "competição entre companheiros de equipe?", " ", " "},
													 {"- Ótimo! Entretanto,", "- Se esse é o caso,", " antes de prosseguirmos, eu gostaria de devolver o livro novamente,", "contudo, eu estou muito envergonhado de meus atos passados para encara-los.", "- Se me permite perguntar Nada Mais que a Verdade, o que faria em meu lugar?", " "},
													 {"- Fácil, primeiro eu teria ido até eles e avisado que cometeria um crime, assim como eu fiz com", "os meus pais quando avisei que fugiria para não participar do suicídio coletivo da minha família.", "- Creio que minha resposta não te ajudou com o seu problema, desculpe Arius.", "As apresentações SEM interferência ganharão +1 de apelo.", " "},
	// 147-------------------------- se o Arius [[[NÃO]]] for o escolhido e derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
													 {"- Olá, eu sei que vocês estão querendo falar com o Arius e não comigo, o que faz todo o sentido", "já que não tenho muito trato social e falo demais, mas ele está ocupado agora terminando de", "escrever um pergaminho, então ao invés dele vocês conversariam comigo?", "- Fico genuinamente feliz com a resposta de vocês."},
													 {"- Olha, eu estava aqui debatendo com Arius e a nossa conversa acabou passando pelos", "Gallobalts e rumou em outra direção, porem teve algo que eu não entendi direito e não tive", "tempo de perguntar, já que ele não consegue parar de falar igual a mim.", " "},
													 {"- Ele mencionou alguns dos laços sanguíneos da família e eu acabei me perdendo um pouco,", "então me veio a pergunta, afinal, os Gallobalts são ou não incestuosos?", "- Devo dizer que estou constrangida por fazer essa pergunta, mas eu quero realmente saber.", "- Se for possível vocês podem desenhar para mim?"},
													 {" ", " ", " ", " "},
													 {"- Não são?", "- Obrigada por me responder, fico extremamente aliviada, essa questão estava me matando", "metaforicamente falando e se ela persistisse eu não aquentaria e teria que ir perguntar aos", "Gallobalts o que seria uma situação muito desagradável para todos nós."},
													 {"- Saudações, já terminei meus afazeres e vejo que vocês e Nada Mais que a Verdade estão", "tendo um debate bem interessante, posso me juntar?", " ", " "},
													 {"- Infelizmente Arius já terminamos, mas você está certo, foi realmente interessante, que pena", "que você perdeu.", "- Tenho que ir agora, nos vemos depois para conversarmos mais.", " "},
													 {"- Como assim! Já é a quarta vez que não consigo chegar a tempo em um debate, o que está", "acontecendo comigo?", " ", " "},
													 {"- Então, vocês vieram me chamar para um novo confronto?", " ", " ", " "},
													 {"- Vamos depressa, não posso arriscar perder mais nada interessante por aqui.", " ", " ", " "},
													 {"- Então... vocês podem me falar sobre o que estavam debatendo?", " ", " ", " "},											 
	// 158-------------------------- se o Arius [[[NÃO]]] for o escolhido e 3º vitória na última luta = |1|3|0|0|1| -------------------
													 {"- Devo me apresar!", "- O espetáculo já está atrasado e terei que recitar minhas poesias sozin-", " ", " "},
													 {"- A plateia não quer escutar somente suas poesias ou você esqueceu com quem dividirá o palco", "hoje?", " ", " "},
													 {"- Kiki! É bom vê-la a salvo, já estava deveras preocupado com sua demora.", " ", " ", " "},
													 {"- Arius, uma barda nunca chega antes ou depois, ela chega quando ela tiver que chegar.", "- E Ignis não consegue andar muito rápido como você já sabe.", " ", " "},
													 {"- Ufa, comecei até cogitar que não quisesse mais realizar um sarau comigo e-", " ", " ", " "},
													 {"- Mas é claro que eu quero fazer um sarau com você!", "- Eu nunca te perdoaria se você fizesse um sem mim.", " ", " "},
													 {"- Não importando o quão ruim fosse!?", " ", " ", " "},
													 {"- Ai você já está se excedendo, vai com calma, principalmente porque não tem como um evento", "ser ruim quando estou presente.", "- Menos aquele na vila purista, assim, eu fui excepcional, o problema era a plateia.", "- Vamos Arius, se você continuar falando nós não poderemos começar a apresentação."},
													 {"- Mas eu não est-", " ", " ", " "},
													 {"- Shiii, vem logo.", " ", " ", " "},		
	// 168--------------------------- se já venceu 3 vezes o Arius e falou com ele -------------------
													 {"- Por que voltamos? Pensei que iriamos desafiar os outros Cães das Colinas.", "- Vocês voltaram? Pensei que iriam desafiar os outros Cães das Colinas.", " ", " "}};




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