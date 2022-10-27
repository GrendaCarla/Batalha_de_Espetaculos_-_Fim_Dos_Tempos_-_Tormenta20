package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Kiki {
	//                                   apelo,                interfer�ncia,          tipo interfer�ncia (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{4, 6, 2, 1, 1, 2, 3, 1}, {0, 0, 3 , 5, 3, 4, 2, 5}, {-1, -1, 3, 3, 5, 2, 0, 1}, {0, 0, 0, 0, 0, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Oficio Culin�ria", "Talentosa", "Engana��o", "Mestre das Fofocas", "Olhar Atordoante", "Desarmar Armadilha", "Pinga, Cacha�a e Tequila", "Suvaqueira"};
	private String [][] ConteudoDescricao = {  {"Como j� � conveniente", "O of�cio culin�rio salva o dia novamente.", "Voc� os tornas sedentos pela sua refei��o de alta qualidade,", "Mas n�o se distraia em nenhum momento, pois est� havendo furtos de gorad.", " "},
											   {"Como uma pessoa de puro talento", "Mostre sua maestria com v�rios instrumentos.", "Deixe as colinas e o mundo ouvirem sua voz melodiosa", "Esfregando na cara dos que duvidaram que seria famosa.", " "},
											   {"Dizer que voc� est� enganando algu�m � uma acusa��o infundada.", "Tu apenas ajudou a ver que nessa situa��o complicada", "Um aproveitamento pode vir para ambas as partes,", "Voc� ficando como peso da vit�ria enquanto o outro com a leveza da irresponsabilidade.", "Esta habilidade afeta o primeiro campo."},
											   {"Voc� � capaz de fazer o primeiro colocado", "Ser de seu pedestal rebaixado.", "Usando sua espetacular e refinada t�cnica", "De espalhar fofocas de maneira fren�tica.", "Esta habilidade afeta o primeiro campo."},
											   {"Quando olham para voc� fica dif�cil quebrar o contato,", "N�o pela sua presen�a marcante ou por ser a alma de todo espet�culo,", "E sim pelo penetrante olhar que lan�as ao seu redor", "Atordoando todos que de fortitude � o que tem de pior.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Ent�o, voc� n�o � a melhor nesse quesito,", "Mas o bom � que tem v�rios melhores amigos", "Que ao menor sinal de um perigo mortal", "Entraria na frente do golpe fatal.", "Esta habilidade afeta o campo acima de voc�."},
											   {"Desafie todos os cachaceiros e pingu�os que na plateia avista", "Para uma competi��o de constitui��o na qual �s especialista.", "Ao final, quando for vitoriosa, seu estomago deve domar,", "Porque sen�o como um bode em seus companheiros ira vomitar.", "Esta habilidade afeta todos os campos acima de voc�."},
											   {"Seu estilo de higiene � bem question�vel,", "Adquirindo assim um poder incomensur�vel.", "N�o se pode fazer desfeita com o que tens em m�os,", "Ent�o levante o bra�o e libere a putrefa��o.", "Esta habilidade afeta todos os campos abaixo de voc�."}};	
	
			// 0---------------------------- primeira intera��o se a Kiki for a escolhida ---------------------- 
			private String [][] ConteudoEscolhaAdversario = {{"- Finalmente Chegamos!", "- Como j� deve conhecer das minhas in�meras can��es, esta � a famosa aldeia Orc onde os", "admir�veis C�es das Colinas tiveram seu in�cio em uma memor�vel batalha contra 500 de seus", "mais poderosos e profanos guerreiros canibais."},
															 {"- Ei, est� vendo aquele ali de amarelo?", "- Ele � o Lala, assim, o nome real � Lena, mas temos que concordar que Lala combina muito", "mais com sua personalidade.", " "},
															 {"- Vamos at� l�, o cheiro da comida est� delicioso!", "- Claro, n�o tanto quanto a minha, porque, como j� deve ter ouvido falar, eu levo refei��es", "muito a s�rio e sou extremamente talentosa com v�rias coisas incluindo culin�ria.", "- Um dia farei algo especial para voc� provar, eu prometo."},
															 {"- Ah, lembrei! Nossa, como eu pude esquecer.", "- Eu ia te perguntar se voc� vai querer competir nas batalhas de apresenta��es, o que me diz?", " ", " "},
															 {"- Ha! � dessa energia que precisamos.", "- Voc� vai ver, vamos ofuscar cada um deles com a soma de todo o nosso carisma.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Olha, estou extremamente surpresa que voc� n�o queira competir, poderia me dizer o porqu�?", "- Verdade! Voc� tem toda raz�o, como n�o poder�amos passar a noite inteira bebendo e", "comemorando com meus amigos que n�o via h� tanto tempo, falha minha.", " "},
			// 6---------------------------- se a Kiki for a escolhida e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
															 {"- Shiiii! Fala mais baixo, a ressaca bateu forte e esse sol n�o est� colaborando.", "- O qu�? Ah, sim, eu vou ficar bem, j� bebi muito nessa vida para um contratempo simples", "como esse me parar.", "- S� me d� uns minutinhos, sim."},
															 {"- Pronto! Estou nova em folha.", "- J� comi, achei minha bota e at� lavei meu rosto, o que j� � muita coisa para algu�m que ainda", "est� a tr�s dias sem tomar banho, porque vamos combinar, lavar a cara s� � aceit�vel depois", "do oitavo."},
															 {"- Ent�o, bora batalhar?", " ", " ", " "},
															 {"- Esse � o espirito!", "- No caminho deixa eu te contar a fofoca que Tanna-Toh me falou ontem, essa � das boas.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Ok, talvez seja melhor darmos uma volta primeiro.", "- Ah! Se voc� ver um homem de chap�u e roupas vermelhas me avisa para a gente passar longe.", "- Eu n�o suporto aquele bardo e o ego inflado dele, um dia desses eu vou mostrar quem � a", "melhor barda que estas colinas j� viram."},
		    // 11--------------------------- se a Kiki for a escolhida e desistiu <= 2 no meio da �ltima luta = |1|0|0|1|3| -------------------
															 {"- Oi, quer saber o que eu estou fazendo?", "- Eu estou transformando todos meus traumas e arrependimentos em um novo sucesso do", "�lbum Kiki Summer Eletrohits, porque afinal, boas obras s�o feitas de p�ssimos sentimentos e", "se tratando de ang�stias e abandono eu sou uma especialista."},
															 {"- Voc� quer me falar alguma coisa?", "- Ah, sim, voc� quer competir em outro espet�culo, estou correta?", " ", " "},
															 {"- Ent�o, na �ltima vez que nos apresentamos aqui voc� desistiu da competi��o de uma hora", "para outra. Aquilo foi um total desrespeito com a plateia, com meus amigos e comigo.", "- Eu pe�o por favor que n�o fa�a mais.", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- N�o? Ok ent�o.", "- Ei, por acaso voc� teria alguma garrafa de bebida para uma barda triste com bloqueio criativo?", " ", " "},
			// 15--------------------------- se a Kiki for a escolhida e desistiu == 3 no meio da �ltima luta = |1|0|0|6|3| -------------------
															 {"- O que foi, voc� est� querendo competir?", " ", " ", " "},
															 {"- Mais voc� � muito cara de pau mesmo, resolveu sair v�rias vezes no meio dos shows e vem", "agora me pedir para come�ar um novo.", "- Talvez voc� n�o tenha notado, mas eu, Kiki, sou uma barda e nenhuma artista que se preste", "sairia no meio da apresenta��o por um motivo besta como o seu."},
															 {"- Aham, voc� est� me dizendo que vai se comportar?", "- Pois bem, saiba que eu guardo rancor e se me humilhar mais uma vez voc� tera um destino", "pior do que a morte.", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Ok, espero que nada de inusitado te aconte�a durante as nossas andan�as, passar bem.", " ", " ", " "},
			// 19--------------------------- se a Kiki for a escolhida e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
															 {"- Eu ainda n�o acredito que aqueles trapaceiros conseguiram tirar a vit�ria de n�s.", "- Sim, eu sei que tomamos uma bela de uma surra, mas n�o foi por m�rito nosso, foram", "aqueles caluniadores que armaram para n�s.", " "},
															 {"- Eu, Kiki a barda do grupo, nunca perco uma competi��o quando envolve meus in�meros", "talentos.", "- Aquilo ali foi sabotagem, sabotagem da criatura mais trapaceira e mentirosa com a qual eu j�", "convivi. Aquela galinha de metal flamejante n�o nos enganar� mais eu prometo."},
															 {"- Ent�o, vamos dar o troco em cada um desses safados usando todo o nosso potencial criativo?", " ", " ", " "},
															 {"- � exatamente isso que eu gosto de ouvir!", "- J� estou at� bolando um novo plano para deix�-los comendo a nossa poeira.", "- Vem c�, o qu�o bom voc� � em espalhar boatos, s� para eu saber?", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Que estraga prazeres voc� �, me avisa quando quiser dar o troco neles ok.", "- Ah! Um conselho para voc�. Sempre que tiver a oportunidade converse com cabras, elas s�o", "senhorinhas muito s�bias.", " "},
			// 24--------------------------- se a Kiki for a escolhida e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
															 {"- Aeeeeeeeeee! Hoje � dia de comemora��o.", "- Eu falei para voc� que somos uma dupla incr�vel e que todos reconheceriam o nosso talento", "inato.", " ", " "},
															 {"- Principalmente aqueles bob�es, feiosos, chatos que ficam querendo separar o grupo por", "motivos bestas.", "- Mas agora, que cada um levou uma surra, v�o querer se unir novamente e todos vamos ficar", "felizes e contentes juntos vivendo grandes aventuras sem ser abandonada mais uma vez."},
															 {"*Sons de v�mito*", "- Eu... acho que posso ter exagerado um pouquinho.", " ", " "},
															 {"- O que voc� queria falar comigo mesmo?", "- Ah, voc� quer dar uma surra neles de novo?", " ", " "},
															 {"- Certo.","- Amanha de manh� n�s resolveremos isso, umas 14h talvez ou voc� prefere �s 15h?", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- � melhor mesmo, duvido que a ressaca ser� leve.", "- An, voc� acha que eu bebo demais?", "- Hahahahaha, espere para conhecer o Cletus, ele sim, � um po�o sem fundo. Se bem que esta", "� uma p�ssima ideia, da �ltima vez ele bebeu todo o meu vinho para viagens."},
															 {"- Mas tem um neg�cio que eu estou curiosa para saber a que fim levou.", "- Uma hora dessas o Seu Cletus come�ou a arrastar uma assa para a Dona Mabel LeBlanc", "sendo que era da Raguria que ele gostava. E sabe como � paquera de gente velha, horr�vel,", "nojento, mas n�o d� para desviar o olhar, tipo acidente de carro�a."}, 		// 31--------------------------- se a Kiki for a escolhida e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
			// 31--------------------------- se a Kiki for a escolhida e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
															 {"- Me deixe em paz, eu n�o quero me apresentar novamente, a �ltima derrota foi humilha��o", "o suficiente para mim hoje.", "- Do que voc� est� falando?", "- Voc� est� tentando me animar?", "- Pois continue, se me contar alguma hist�ria sua talvez funcione, quem sabe."},
															 {"*Um tempo depois*", "- HaHaHa, n�o acredito que tudo isso ocorreu quando ele foi tomar banho.", "- Eu me lembro de uma vez que meus amigos tentaram me dar um banho. No final de tudo eu", "quase fui levada pela correnteza e acabei sendo batizada pela igreja de Tyathis, foi horr�vel."},
															 {"- Eu acho melhor n�o ficar chorando as pitangas aqui, vai que outro paladino apare�a e queira", "me batizar.", "- E dessa vez eu n�o poderei contar com Ayla e nem o mutuca para reverter a limpeza.", " "},
															 {"- Falando neles, voc� ficou sabendo que o filho do Mutuca, o Carlos Mutuca Jr., formou uma", "banda com o Pascal LeBlanc e o, o... ai, esqueci o nome do outro.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
			// 35--------------------------- se a Kiki for a escolhida e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
															 {"- Surpreendentemente n�o tenho do que me vangloriar.", "- Como esperado ganhamos outra competi��o sem fazer o m�nimo esfor�o e ganharemos a", "pr�xima da mesma forma, nada de novo.", " "},
															 {"- HAHAHA voc� realmente acreditou no que eu falei?", "- N�o te culpo, afinal, sou uma atriz brilhante.", "- Mas � muita ingenuidade sua pensar que eu n�o comemoraria outra vit�ria emocionante que", "tivemos unindo todo o nosso potencial."},
															 {"- Um brinde a nossa conquista. Ent�o, acho que agora � uma boa hora para propor outra", "partida, o que acha?", " ", " "},
															 {"- Ent�o se prepare porque vamos batalhar!", " ", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- HaHa, vejo que tamb�m gosta de fugir das responsabilidades tanto quanto eu.", "- Ei, vamos ver o que o Arius e a Nada Mais que a Bala est�o fazendo? Eles andam passando", "muito tempo junto se � que voc� me entende.", " "},
			// 40--------------------------- se a Kiki for a escolhida e derrota na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
															 {"- Mais tinha que ser aquele projeto de fisiculturista!", "- � s� eu me distrair por um segundo que ele vai e estraga tudo. Queria ver ele sem toda aquela", "sorte descomunal.", " "},
															 {"- Voc� est� rindo de mim?", "- Ei, eu n�o fico horas falando sem parar. Ok eu fico, mas todo mundo n�o cala a boca tamb�m.", "- Voc� j� viu o Arius quando encontra algum assunto chato para falar? Ele fica pau a pau com", "o Ignis quando resolve dar bronca em todos n�s."},
															 {"- An? Ah sim, o espet�culo, voc� quer a nossa terceira vit�ria agora?", " ", " ", " "},
															 {"- Ent�o vamos depressa, desta vez precisamos nos focar completamente na nossa vit�ria, olho", "no ouro ouviu, olho no ouro.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Bem, uma pausa sempre traz bons frutos.", "- Ou! deixa eu te levar para conhecer o Irevash, ele � outro amigo meu, voc� vai am�-lo, se tem", "algu�m que gosta de fofocar tanto quanto eu � ele.", " "},
			// 45--------------------------- se a Kiki for a escolhida e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
															 {"- Voc� est� me perguntando se eu estou contente?", "- Eu estou radiante! n�s conseguimos tr�s vit�rias, tr�s espetaculares vit�rias.", "- Veja! eu j� at� comecei a escrever uma nova m�sica sobre o nosso brilhantismo no palco.", " "},
															 {"- E para comemorar, esta noite n�s n�o teremos ra��o de viagem coisa nenhuma.", "- N�s vamos saborear um delicioso churrasco com tudo que tem direito feito por esta excelente", "mestre cuca.", "- Aqui, comece a encher o bucho com p�o de alho e farofa que logo logo a carne sai.", "- Hoje � um dia para ser aproveitado, porque daremos adeus a Lala, Tanna-Toh e a esse lugar."},
			// 47--------------------------- se a Kiki [[[N�O]]] for a escolhida e primeira intera��o |0|0|0|0|0 ---------------------------------
															 {"- Olha s�, finalmente chegaram aqui!", "- Creio que voc� j� deve ter reconhecido a famosa aldeia Orc das minhas in�meras can��es.", "- Esse lugar � onde os admir�veis C�es das Colinas tiveram seu in�cio com uma memor�vel", "batalha contra 500 de seus mais poderosos e profanos guerreiros canibais."},
															 {"- Est� vendo aquele ali de amarelo?", "- Ele � o Lala, assim, o nome real � Lena, mas temos que concordar que Lala combina muito", "mais com sua personalidade.", " "},
															 {"- Vamos at� l�, o cheiro da comida est� delicioso!", "- Claro, n�o tanto quanto a minha, porque, como j� devem ter ouvido falar, eu levo refei��es", "muito a s�rio e sou extremamente talentosa com v�rias coisas incluindo culin�ria.", "- Eu prometo que um dia desses farei algo especial para voc�s provarem."},
															 {"- Ent�o, voc�s est�o aqui para me desafiar para uma batalha de apresenta��es?", " ", " ", " "},
															 {"- Ha! � dessa energia que precisam.", "- Voc�s v�o ver, eu vou ofuscar todo mundo s� com a for�a do meu carisma.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Olha, estou extremamente surpresa que voc�s n�o queiram competir, poderiam me dizer o", "porqu�?", "- Verdade! Voc�s t�m toda raz�o, como n�o poder�am passar a noite inteira bebendo e", "comemorando com amigos que n�o via h� tanto tempo, falha minha."},
			// 53--------------------------- se a Kiki [[[N�O]]] for a escolhida e teve o primeiro contato, mas n�o batalhou = |1|0|0|0|0| -------------------
															 {"- Shiiii! Fala mais baixo, a ressaca bateu forte e esse sol n�o est� colaborando.", "- O qu�? Ah, sim, eu vou ficar bem, j� bebi muito nessa vida para um contratempo simples", "como esse me parar.", "- S� me d� uns minutinhos, sim."},
															 {"- Pronto! Estou nova em folha.", "- J� comi, achei minha bota e at� lavei meu rosto, o que j� � muita coisa para algu�m que ainda", "est� a tr�s dias sem tomar banho, porque vamos combinar, lavar a cara s� � aceit�vel depois", "do oitavo."},
															 {"- Ent�o, bora batalhar?", " ", " ", " "},
															 {"- Esse � o espirito!", "- No caminho deixa eu contar para voc�s a fofoca que Tanna-Toh me falou ontem, essa � das", "boas.", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Ok, talvez seja melhor voc�s darem uma volta primeiro.", "- Ah! Se voc�s virem um homem de chap�u e roupas vermelhas passem longe.", "- Eu n�o suporto aquele bardo e o ego inflado dele, um dia desses eu vou mostrar quem � a", "melhor barda que estas colinas j� viram."},
		    // 58--------------------------- se a Kiki [[[N�O]]] for a escolhida e desistiu <= 5 no meio da �ltima luta = |1|0|0|1|3| -------------------
															 {"- Oi, querem saber o que eu estou fazendo?", "- Eu estou transformando todos meus traumas e arrependimentos em um novo sucesso do", "�lbum Kiki Summer Eletrohits, porque afinal, boas obras s�o feitas de p�ssimos sentimentos e", "se tratando de ang�stias e abandono eu sou uma especialista."},
															 {"- Voc�s querem me falar alguma coisa?", "- Ah, sim, voc�s est�o querendo competir em outro espet�culo, estou correta?", " ", " "},
															 {"- Ent�o, na �ltima vez que competiram aqui voc� desistiu de uma hora para outra. Aquilo foi um", "total desrespeito com a plateia, com meus amigos e comigo.", "- Eu pe�o por favor que n�o fa�a mais.", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- N�o? Ok ent�o.", "- Ei, por acaso voc� teria alguma garrafa de bebida para uma barda triste com bloqueio criativo?", " ", " "},
			// 62--------------------------- se a Kiki [[[N�O]]] for a escolhida e desistiu == 6 no meio da �ltima luta = |1|0|0|6|3| -------------------
															 {"- O que foi, voc�s est�o querendo competir?", " ", " ", " "},
															 {"- Mais voc� � muito cara de pau mesmo, resolveu sair v�rias vezes no meio dos shows e vem", "agora me pedir para come�ar um novo.", "- Talvez voc� n�o tenha notado, mas eu, Kiki, sou uma barda e nenhuma artista que se preste", "encerra um evento no meio por um motivo besta como o seu."},
															 {"- Aham, voc� est� me dizendo que vai se comportar?", "- Pois bem, saiba que eu guardo rancor e se me humilhar mais uma vez voc� tera um destino", "pior do que a morte.", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Ok, espero que nada de inusitado te aconte�a durante as suas andan�as, passar bem.", " ", " ", " "},
			// 66--------------------------- se a Kiki [[[N�O]]] for a escolhida e perdeu na �ltima luta com 0 vit�rias = |1|0|1|0|2| -------------------
															 {"- Eu ainda n�o acredito que voc�s foram t�o facilmente trapaceados na nossa �ltima disputa.", "- Sim, eu sei que tomaram uma bela de uma surra dentro das regras, porem a sua derrota n�o", "foi totalmente por m�rito seu, foram aqueles caluniadores que armaram para cima de voc�s.", " "},
															 {"- Aquilo ali foi sabotagem, sabotagem da criatura mais trapaceira e mentirosa com a qual eu j�", "convivi.", "- Se eu fosse voc� n�o deixava passar em branco essa humilha��o na frente de todo mundo.", "- Aquela galinha de metal flamejante tem que pagar.", "- Aquela vaca abobada sem rabo tem que pagar."},
															 {"- Ent�o, voc� quer dar o troco em cada um desses safados usando todo o seu potencial criativo?", " ", " ", " "},
															 {"- � exatamente isso que eu gosto de ouvir!", "- J� estou at� bolando um plano para voc�s deix�-los comendo a sua poeira.", "- Vem c�, o qu�o bom voc� � em espalhar boatos, s� para eu saber?", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Que estraga prazeres voc� �, me avisa quando quiser dar o troco neles ok.", "- Ah! Um conselho para voc�s. Sempre que tiverem a oportunidade conversem com cabras, elas", "s�o senhorinhas muito s�bias.", " "},
			// 71--------------------------- se a Kiki [[[N�O]]] for a escolhida e 1� vit�ria na �ltima luta = |1|1|0|0|1| -------------------
															 {"- Aeeeeeeeeee! Hoje � dia de comemora��o.", "- Eu posso at� ter perdido, mas voc�s conseguiram sua vit�ria e momentos como esses devem", "ser apreciados em companhia de muita comida e �lcool."},
															 {"- Como eu j� falei antes, voc�s formam uma dupla incr�vel e todos reconheceram os seus", "talentos inatos.", "- Principalmente aqueles bob�es, feiosos, chatos que ficam querendo separar o grupo por", "motivos bestas."},
															 {"- Mas agora, que cada um levou uma surra, v�o querer se unir novamente e todos vamos ficar", "felizes e contentes juntos vivendo grandes aventuras sem ser abandonada mais uma vez.", " ", " "},
															 {"*Sons de v�mito*", "- Eu... acho que posso ter exagerado um pouquinho.", " ", " "},
															 {"- O que voc�s queriam falar comigo mesmo?", "- Ah, voc�s querem me dar uma surra de novo?", " ", " "},
															 {"- Certo, vai sonhando que isso vai se repetir.","- Amanha de manh� n�s resolveremos os detalhes, umas 14h talvez ou voc� prefere �s 15h?", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- � bom mesmo e eu tamb�m duvido que a minha ressaca ser� leve.", "- An, voc�s acham que eu bebo demais?", "- Hahahahaha, espere para conhecer o Cletus, ele sim, � um po�o sem fundo. Se bem que esta", "� uma p�ssima ideia, da �ltima vez ele bebeu todo o meu vinho para viagens."},
															 {"- Mas tem um neg�cio que eu estou curiosa para saber a que fim levou.", "- Uma hora dessas o Seu Cletus come�ou a arrastar uma assa para a Dona Mabel LeBlanc", "sendo que era da Raguria que ele gostava. E sabe como � paquera de gente velha, horr�vel,", "nojento, mas n�o d� para desviar o olhar, tipo acidente de carro�a."},
			// 79--------------------------- se a Kiki [[[N�O]]] for a escolhida e perdeu na �ltima luta, mas tem 1 vit�ria = |1|1|1|0|2| -------------------
															 {"- Surpreendentemente n�o tenho o que comentar.", "- Como esperado voc�s perderam a competi��o sem que eu fizesse esfor�o algum e perderam", "a pr�xima da mesma forma, nada de novo.", " "},
															 {"- HAHAHA voc�s realmente acreditaram? N�o lhes culpo, afinal, sou uma atriz brilhante.", "- Mas seria muita maldade minha se eu estivesse falando s�rio sobre tudo isso.", "- Um brinde ao fracasso, porque quando sentimos a derrota tamb�m sentimos a vontade de nos", "levantar e mostrar do que somos realmente capazes."},
															 {"- Ent�o, acho que agora � uma boa hora para propor outra partida, o que acham?", " ", " ", " "},
															 {"- Ent�o se preparem porque vamos batalhar!", " ", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Desculpa, devo ter pegado pesado com voc�s.", "- Que tau n�s tr�s fugirmos um pouco das responsabilidades e irmos ver o que o Arius e a Nada", "Mais que a Bala est�o fazendo?", "- Eles andam passando muito tempo junto se � que voc�s me entende.", "- Que tau n�s tr�s fugirmos um pouco das responsabilidades e irmos ver o que a Ayla e o", "Mutuca est�o fazendo?", "- Eles andam passando muito tempo junto se � que voc�s me entende.", "- Que isso! Eu estava me referindo a bombas."}, // < --------
			// 84--------------------------- se a Kiki [[[N�O]]] for a escolhida e 2� vit�ria na �ltima luta  = |1|2|4|0|1| -------------------
															 {"- Me deixem em paz, eu n�o quero me apresentar novamente, a �ltima derrota foi humilha��o", "o suficiente para mim hoje.", "- Do que voc� est� falando?", "- Voc� est� tentando me animar?", "- Pois continue, se me contar alguma hist�ria sua talvez funcione, quem sabe."},
															 {"*Um tempo depois*", "- HaHaHa, n�o acredito que tudo isso ocorreu quando ele foi tomar banho.", "- Eu me lembro de uma vez que todos os C�es tentaram me dar um banho. No final de tudo eu", "quase fui levada pela correnteza e acabei sendo batizada pela igreja de Tyathis, foi horr�vel."},
															 {"- Eu acho melhor n�o ficar chorando as pitangas aqui, vai que outro paladino queira me batizar.", "- Eu acho melhor n�o ficar chorando as pitangas aqui, vai que o Ignis queira me batizar de novo.", "E dessa vez n�o poderei contar com a Ayla e nem o mutuca para reverter a limpeza.", "E se isso acabar acontecendo eu conto com voc�s e o mutuca para reverter a limpeza."}, // <--------
															 {"- Falando neles, voc� ficou sabendo que o filho do Mutuca, o Carlos Mutuca Jr., formou uma", "- Falando nele, voc� ficou sabendo que o filho do Mutuca, o Carlos Mutuca Jr., formou uma", "banda com o Pascal LeBlanc e o, o... ai, esqueci o nome do outro.", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."}, // <-------- 
			// 88--------------------------- se a Kiki [[[N�O]]] for a escolhida e derrota na �ltima luta com 2 vit�rias = |1|2|1|0|2| -------------------
															 {"- Hahaha, tinha que ser aquele projeto de fisiculturista!", "- � s� voc�s se distra�rem por um segundo que ele vai e estraga tudo. Queria ver ele sem toda", "aquela sorte descomunal.", "- Hahaha, tinha que ser a Ayla!", "- � s� voc�s se distra�rem por um segundo que ela vai e vira o jogo. Queria ve-la sem toda", "aquela l�bia descomunal."}, // < ------
															 {"- Voc�s est�o bravos comigo?", "- Ei, eu n�o fico horas falando sem parar. Ok eu fico, mas todo mundo n�o cala a boca tamb�m.", "- Voc� j� viu o Arius quando encontra algum assunto chato para falar? Ele fica pau a pau com", "- Voc� j� viu o Arius quando encontra algum assunto chato para falar, ele fica pau a pau com", "o Ignis quando resolve dar bronca em todos n�s."},
															 {"- An? Ah sim, o espet�culo, voc�s querem tentar conseguir sua terceira vit�ria agora?", " ", " ", " "},
															 {"- Ent�o v�o depressa, porque dessa vez voc�s precisam focar completamente na sua vit�ria,", "olho no ouro ouviu, sempre mantenha o olho no ouro.", " ", "As apresenta��es COM interfer�ncia ganhar�o +1 de apelo."},
															 {"- Bem, uma pausa sempre traz bons frutos.", "- Ou! deixa eu te levar para conhecer o Irevash, ele � outro amigo meu, voc� vai am�-lo, se tem", "algu�m que gosta de fofocar tanto quanto eu � ele.", " "},
			// 93--------------------------- se a Kiki [[[N�O]]] for a escolhida e 3� vit�ria na �ltima luta = |1|3|0|0|1| -------------------
															 {"- Est� me perguntando se eu estou contente por voc�s?", "- Eu estou radiante! conseguiram tr�s vit�rias, tr�s espetaculares vit�rias.", "- Veja! eu j� at� comecei a escrever uma nova m�sica sobre o brilhantismo no palco de voc�s.", " "},
															 {"- E para comemorar, esta noite voc�s n�o ter�o ra��o de viagem coisa nenhuma.", "- N�s vamos saborear um delicioso churrasco com tudo que tem direito feito por esta excelente", "mestre cuca.", "- Aqui, comece a encher o bucho com p�o de alho e farofa que logo logo a carne sai.", "- Hoje � um dia para ser aproveitado, porque voc�s dar�o adeus a Lala, Tanna-Toh e a mim."},
			// 95--------------------------- se j� venceu 3 vezes a Kiki e falou com ela -------------------
															 {"- Por que voltamos? Pensei que iriamos desafiar os outros C�es.", "- Voc�s voltaram? Pensei que iriam desafiar os outros C�es.", " ", " "}};

	
		
	private ArrayList<Integer>  mylist = new ArrayList<Integer>();
	
	public Kiki() {
		sorteio();
	}
	
	public void sorteio () {
		/*for(int i=0; i<8; i++) {
	        mylist.add(i);
		}*/
		
		mylist.add(0); mylist.add(1); mylist.add(3); mylist.add(6);
		
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