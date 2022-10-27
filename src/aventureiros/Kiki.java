package aventureiros;

import java.util.ArrayList;
import java.util.Collections;

public class Kiki {
	//                                   apelo,                interferência,          tipo interferência (-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
	private int [][] valores = {{4, 6, 2, 1, 1, 2, 3, 1}, {0, 0, 3 , 5, 3, 4, 2, 5}, {-1, -1, 3, 3, 5, 2, 0, 1}, {0, 0, 0, 0, 0, 1, 0, 0}}; //tipo apelo 0= nao fisico, 1= fisico
	private int [] gifApelos = {1, 2, 3, 4, 5, 6, 7, 8};
	private String [] NomeApelos = {"Oficio Culinária", "Talentosa", "Enganação", "Mestre das Fofocas", "Olhar Atordoante", "Desarmar Armadilha", "Pinga, Cachaça e Tequila", "Suvaqueira"};
	private String [][] ConteudoDescricao = {  {"Como já é conveniente", "O ofício culinário salva o dia novamente.", "Você os tornas sedentos pela sua refeição de alta qualidade,", "Mas não se distraia em nenhum momento, pois está havendo furtos de gorad.", " "},
											   {"Como uma pessoa de puro talento", "Mostre sua maestria com vários instrumentos.", "Deixe as colinas e o mundo ouvirem sua voz melodiosa", "Esfregando na cara dos que duvidaram que seria famosa.", " "},
											   {"Dizer que você está enganando alguém é uma acusação infundada.", "Tu apenas ajudou a ver que nessa situação complicada", "Um aproveitamento pode vir para ambas as partes,", "Você ficando como peso da vitória enquanto o outro com a leveza da irresponsabilidade.", "Esta habilidade afeta o primeiro campo."},
											   {"Você é capaz de fazer o primeiro colocado", "Ser de seu pedestal rebaixado.", "Usando sua espetacular e refinada técnica", "De espalhar fofocas de maneira frenética.", "Esta habilidade afeta o primeiro campo."},
											   {"Quando olham para você fica difícil quebrar o contato,", "Não pela sua presença marcante ou por ser a alma de todo espetáculo,", "E sim pelo penetrante olhar que lanças ao seu redor", "Atordoando todos que de fortitude é o que tem de pior.", "Esta habilidade afeta um campo acima e um abaixo."},
											   {"Então, você não é a melhor nesse quesito,", "Mas o bom é que tem vários melhores amigos", "Que ao menor sinal de um perigo mortal", "Entraria na frente do golpe fatal.", "Esta habilidade afeta o campo acima de você."},
											   {"Desafie todos os cachaceiros e pinguços que na plateia avista", "Para uma competição de constituição na qual és especialista.", "Ao final, quando for vitoriosa, seu estomago deve domar,", "Porque senão como um bode em seus companheiros ira vomitar.", "Esta habilidade afeta todos os campos acima de você."},
											   {"Seu estilo de higiene é bem questionável,", "Adquirindo assim um poder incomensurável.", "Não se pode fazer desfeita com o que tens em mãos,", "Então levante o braço e libere a putrefação.", "Esta habilidade afeta todos os campos abaixo de você."}};	
	
			// 0---------------------------- primeira interação se a Kiki for a escolhida ---------------------- 
			private String [][] ConteudoEscolhaAdversario = {{"- Finalmente Chegamos!", "- Como já deve conhecer das minhas inúmeras canções, esta é a famosa aldeia Orc onde os", "admiráveis Cães das Colinas tiveram seu início em uma memorável batalha contra 500 de seus", "mais poderosos e profanos guerreiros canibais."},
															 {"- Ei, está vendo aquele ali de amarelo?", "- Ele é o Lala, assim, o nome real é Lena, mas temos que concordar que Lala combina muito", "mais com sua personalidade.", " "},
															 {"- Vamos até lá, o cheiro da comida está delicioso!", "- Claro, não tanto quanto a minha, porque, como já deve ter ouvido falar, eu levo refeições", "muito a sério e sou extremamente talentosa com várias coisas incluindo culinária.", "- Um dia farei algo especial para você provar, eu prometo."},
															 {"- Ah, lembrei! Nossa, como eu pude esquecer.", "- Eu ia te perguntar se você vai querer competir nas batalhas de apresentações, o que me diz?", " ", " "},
															 {"- Ha! É dessa energia que precisamos.", "- Você vai ver, vamos ofuscar cada um deles com a soma de todo o nosso carisma.", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Olha, estou extremamente surpresa que você não queira competir, poderia me dizer o porquê?", "- Verdade! Você tem toda razão, como não poderíamos passar a noite inteira bebendo e", "comemorando com meus amigos que não via há tanto tempo, falha minha.", " "},
			// 6---------------------------- se a Kiki for a escolhida e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
															 {"- Shiiii! Fala mais baixo, a ressaca bateu forte e esse sol não está colaborando.", "- O quê? Ah, sim, eu vou ficar bem, já bebi muito nessa vida para um contratempo simples", "como esse me parar.", "- Só me dê uns minutinhos, sim."},
															 {"- Pronto! Estou nova em folha.", "- Já comi, achei minha bota e até lavei meu rosto, o que já é muita coisa para alguém que ainda", "está a três dias sem tomar banho, porque vamos combinar, lavar a cara só é aceitável depois", "do oitavo."},
															 {"- Então, bora batalhar?", " ", " ", " "},
															 {"- Esse é o espirito!", "- No caminho deixa eu te contar a fofoca que Tanna-Toh me falou ontem, essa é das boas.", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Ok, talvez seja melhor darmos uma volta primeiro.", "- Ah! Se você ver um homem de chapéu e roupas vermelhas me avisa para a gente passar longe.", "- Eu não suporto aquele bardo e o ego inflado dele, um dia desses eu vou mostrar quem é a", "melhor barda que estas colinas já viram."},
		    // 11--------------------------- se a Kiki for a escolhida e desistiu <= 2 no meio da última luta = |1|0|0|1|3| -------------------
															 {"- Oi, quer saber o que eu estou fazendo?", "- Eu estou transformando todos meus traumas e arrependimentos em um novo sucesso do", "álbum Kiki Summer Eletrohits, porque afinal, boas obras são feitas de péssimos sentimentos e", "se tratando de angústias e abandono eu sou uma especialista."},
															 {"- Você quer me falar alguma coisa?", "- Ah, sim, você quer competir em outro espetáculo, estou correta?", " ", " "},
															 {"- Então, na última vez que nos apresentamos aqui você desistiu da competição de uma hora", "para outra. Aquilo foi um total desrespeito com a plateia, com meus amigos e comigo.", "- Eu peço por favor que não faça mais.", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Não? Ok então.", "- Ei, por acaso você teria alguma garrafa de bebida para uma barda triste com bloqueio criativo?", " ", " "},
			// 15--------------------------- se a Kiki for a escolhida e desistiu == 3 no meio da última luta = |1|0|0|6|3| -------------------
															 {"- O que foi, você está querendo competir?", " ", " ", " "},
															 {"- Mais você é muito cara de pau mesmo, resolveu sair várias vezes no meio dos shows e vem", "agora me pedir para começar um novo.", "- Talvez você não tenha notado, mas eu, Kiki, sou uma barda e nenhuma artista que se preste", "sairia no meio da apresentação por um motivo besta como o seu."},
															 {"- Aham, você está me dizendo que vai se comportar?", "- Pois bem, saiba que eu guardo rancor e se me humilhar mais uma vez você tera um destino", "pior do que a morte.", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Ok, espero que nada de inusitado te aconteça durante as nossas andanças, passar bem.", " ", " ", " "},
			// 19--------------------------- se a Kiki for a escolhida e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
															 {"- Eu ainda não acredito que aqueles trapaceiros conseguiram tirar a vitória de nós.", "- Sim, eu sei que tomamos uma bela de uma surra, mas não foi por mérito nosso, foram", "aqueles caluniadores que armaram para nós.", " "},
															 {"- Eu, Kiki a barda do grupo, nunca perco uma competição quando envolve meus inúmeros", "talentos.", "- Aquilo ali foi sabotagem, sabotagem da criatura mais trapaceira e mentirosa com a qual eu já", "convivi. Aquela galinha de metal flamejante não nos enganará mais eu prometo."},
															 {"- Então, vamos dar o troco em cada um desses safados usando todo o nosso potencial criativo?", " ", " ", " "},
															 {"- É exatamente isso que eu gosto de ouvir!", "- Já estou até bolando um novo plano para deixá-los comendo a nossa poeira.", "- Vem cá, o quão bom você é em espalhar boatos, só para eu saber?", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Que estraga prazeres você é, me avisa quando quiser dar o troco neles ok.", "- Ah! Um conselho para você. Sempre que tiver a oportunidade converse com cabras, elas são", "senhorinhas muito sábias.", " "},
			// 24--------------------------- se a Kiki for a escolhida e 1º vitória na última luta = |1|1|0|0|1| -------------------
															 {"- Aeeeeeeeeee! Hoje é dia de comemoração.", "- Eu falei para você que somos uma dupla incrível e que todos reconheceriam o nosso talento", "inato.", " ", " "},
															 {"- Principalmente aqueles bobões, feiosos, chatos que ficam querendo separar o grupo por", "motivos bestas.", "- Mas agora, que cada um levou uma surra, vão querer se unir novamente e todos vamos ficar", "felizes e contentes juntos vivendo grandes aventuras sem ser abandonada mais uma vez."},
															 {"*Sons de vômito*", "- Eu... acho que posso ter exagerado um pouquinho.", " ", " "},
															 {"- O que você queria falar comigo mesmo?", "- Ah, você quer dar uma surra neles de novo?", " ", " "},
															 {"- Certo.","- Amanha de manhã nós resolveremos isso, umas 14h talvez ou você prefere às 15h?", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- É melhor mesmo, duvido que a ressaca será leve.", "- An, você acha que eu bebo demais?", "- Hahahahaha, espere para conhecer o Cletus, ele sim, é um poço sem fundo. Se bem que esta", "é uma péssima ideia, da última vez ele bebeu todo o meu vinho para viagens."},
															 {"- Mas tem um negócio que eu estou curiosa para saber a que fim levou.", "- Uma hora dessas o Seu Cletus começou a arrastar uma assa para a Dona Mabel LeBlanc", "sendo que era da Raguria que ele gostava. E sabe como é paquera de gente velha, horrível,", "nojento, mas não dá para desviar o olhar, tipo acidente de carroça."}, 		// 31--------------------------- se a Kiki for a escolhida e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
			// 31--------------------------- se a Kiki for a escolhida e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
															 {"- Me deixe em paz, eu não quero me apresentar novamente, a última derrota foi humilhação", "o suficiente para mim hoje.", "- Do que você está falando?", "- Você está tentando me animar?", "- Pois continue, se me contar alguma história sua talvez funcione, quem sabe."},
															 {"*Um tempo depois*", "- HaHaHa, não acredito que tudo isso ocorreu quando ele foi tomar banho.", "- Eu me lembro de uma vez que meus amigos tentaram me dar um banho. No final de tudo eu", "quase fui levada pela correnteza e acabei sendo batizada pela igreja de Tyathis, foi horrível."},
															 {"- Eu acho melhor não ficar chorando as pitangas aqui, vai que outro paladino apareça e queira", "me batizar.", "- E dessa vez eu não poderei contar com Ayla e nem o mutuca para reverter a limpeza.", " "},
															 {"- Falando neles, você ficou sabendo que o filho do Mutuca, o Carlos Mutuca Jr., formou uma", "banda com o Pascal LeBlanc e o, o... ai, esqueci o nome do outro.", " ", "As apresentações COM interferência ganharão +1 de apelo."},
			// 35--------------------------- se a Kiki for a escolhida e 2º vitória na última luta  = |1|2|4|0|1| -------------------
															 {"- Surpreendentemente não tenho do que me vangloriar.", "- Como esperado ganhamos outra competição sem fazer o mínimo esforço e ganharemos a", "próxima da mesma forma, nada de novo.", " "},
															 {"- HAHAHA você realmente acreditou no que eu falei?", "- Não te culpo, afinal, sou uma atriz brilhante.", "- Mas é muita ingenuidade sua pensar que eu não comemoraria outra vitória emocionante que", "tivemos unindo todo o nosso potencial."},
															 {"- Um brinde a nossa conquista. Então, acho que agora é uma boa hora para propor outra", "partida, o que acha?", " ", " "},
															 {"- Então se prepare porque vamos batalhar!", " ", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- HaHa, vejo que também gosta de fugir das responsabilidades tanto quanto eu.", "- Ei, vamos ver o que o Arius e a Nada Mais que a Bala estão fazendo? Eles andam passando", "muito tempo junto se é que você me entende.", " "},
			// 40--------------------------- se a Kiki for a escolhida e derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
															 {"- Mais tinha que ser aquele projeto de fisiculturista!", "- É só eu me distrair por um segundo que ele vai e estraga tudo. Queria ver ele sem toda aquela", "sorte descomunal.", " "},
															 {"- Você está rindo de mim?", "- Ei, eu não fico horas falando sem parar. Ok eu fico, mas todo mundo não cala a boca também.", "- Você já viu o Arius quando encontra algum assunto chato para falar? Ele fica pau a pau com", "o Ignis quando resolve dar bronca em todos nós."},
															 {"- An? Ah sim, o espetáculo, você quer a nossa terceira vitória agora?", " ", " ", " "},
															 {"- Então vamos depressa, desta vez precisamos nos focar completamente na nossa vitória, olho", "no ouro ouviu, olho no ouro.", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Bem, uma pausa sempre traz bons frutos.", "- Ou! deixa eu te levar para conhecer o Irevash, ele é outro amigo meu, você vai amá-lo, se tem", "alguém que gosta de fofocar tanto quanto eu é ele.", " "},
			// 45--------------------------- se a Kiki for a escolhida e 3º vitória na última luta = |1|3|0|0|1| -------------------
															 {"- Você está me perguntando se eu estou contente?", "- Eu estou radiante! nós conseguimos três vitórias, três espetaculares vitórias.", "- Veja! eu já até comecei a escrever uma nova música sobre o nosso brilhantismo no palco.", " "},
															 {"- E para comemorar, esta noite nós não teremos ração de viagem coisa nenhuma.", "- Nós vamos saborear um delicioso churrasco com tudo que tem direito feito por esta excelente", "mestre cuca.", "- Aqui, comece a encher o bucho com pão de alho e farofa que logo logo a carne sai.", "- Hoje é um dia para ser aproveitado, porque daremos adeus a Lala, Tanna-Toh e a esse lugar."},
			// 47--------------------------- se a Kiki [[[NÃO]]] for a escolhida e primeira interação |0|0|0|0|0 ---------------------------------
															 {"- Olha só, finalmente chegaram aqui!", "- Creio que você já deve ter reconhecido a famosa aldeia Orc das minhas inúmeras canções.", "- Esse lugar é onde os admiráveis Cães das Colinas tiveram seu início com uma memorável", "batalha contra 500 de seus mais poderosos e profanos guerreiros canibais."},
															 {"- Está vendo aquele ali de amarelo?", "- Ele é o Lala, assim, o nome real é Lena, mas temos que concordar que Lala combina muito", "mais com sua personalidade.", " "},
															 {"- Vamos até lá, o cheiro da comida está delicioso!", "- Claro, não tanto quanto a minha, porque, como já devem ter ouvido falar, eu levo refeições", "muito a sério e sou extremamente talentosa com várias coisas incluindo culinária.", "- Eu prometo que um dia desses farei algo especial para vocês provarem."},
															 {"- Então, vocês estão aqui para me desafiar para uma batalha de apresentações?", " ", " ", " "},
															 {"- Ha! É dessa energia que precisam.", "- Vocês vão ver, eu vou ofuscar todo mundo só com a força do meu carisma.", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Olha, estou extremamente surpresa que vocês não queiram competir, poderiam me dizer o", "porquê?", "- Verdade! Vocês têm toda razão, como não poderíam passar a noite inteira bebendo e", "comemorando com amigos que não via há tanto tempo, falha minha."},
			// 53--------------------------- se a Kiki [[[NÃO]]] for a escolhida e teve o primeiro contato, mas não batalhou = |1|0|0|0|0| -------------------
															 {"- Shiiii! Fala mais baixo, a ressaca bateu forte e esse sol não está colaborando.", "- O quê? Ah, sim, eu vou ficar bem, já bebi muito nessa vida para um contratempo simples", "como esse me parar.", "- Só me dê uns minutinhos, sim."},
															 {"- Pronto! Estou nova em folha.", "- Já comi, achei minha bota e até lavei meu rosto, o que já é muita coisa para alguém que ainda", "está a três dias sem tomar banho, porque vamos combinar, lavar a cara só é aceitável depois", "do oitavo."},
															 {"- Então, bora batalhar?", " ", " ", " "},
															 {"- Esse é o espirito!", "- No caminho deixa eu contar para vocês a fofoca que Tanna-Toh me falou ontem, essa é das", "boas.", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Ok, talvez seja melhor vocês darem uma volta primeiro.", "- Ah! Se vocês virem um homem de chapéu e roupas vermelhas passem longe.", "- Eu não suporto aquele bardo e o ego inflado dele, um dia desses eu vou mostrar quem é a", "melhor barda que estas colinas já viram."},
		    // 58--------------------------- se a Kiki [[[NÃO]]] for a escolhida e desistiu <= 5 no meio da última luta = |1|0|0|1|3| -------------------
															 {"- Oi, querem saber o que eu estou fazendo?", "- Eu estou transformando todos meus traumas e arrependimentos em um novo sucesso do", "álbum Kiki Summer Eletrohits, porque afinal, boas obras são feitas de péssimos sentimentos e", "se tratando de angústias e abandono eu sou uma especialista."},
															 {"- Vocês querem me falar alguma coisa?", "- Ah, sim, vocês estão querendo competir em outro espetáculo, estou correta?", " ", " "},
															 {"- Então, na última vez que competiram aqui você desistiu de uma hora para outra. Aquilo foi um", "total desrespeito com a plateia, com meus amigos e comigo.", "- Eu peço por favor que não faça mais.", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Não? Ok então.", "- Ei, por acaso você teria alguma garrafa de bebida para uma barda triste com bloqueio criativo?", " ", " "},
			// 62--------------------------- se a Kiki [[[NÃO]]] for a escolhida e desistiu == 6 no meio da última luta = |1|0|0|6|3| -------------------
															 {"- O que foi, vocês estão querendo competir?", " ", " ", " "},
															 {"- Mais você é muito cara de pau mesmo, resolveu sair várias vezes no meio dos shows e vem", "agora me pedir para começar um novo.", "- Talvez você não tenha notado, mas eu, Kiki, sou uma barda e nenhuma artista que se preste", "encerra um evento no meio por um motivo besta como o seu."},
															 {"- Aham, você está me dizendo que vai se comportar?", "- Pois bem, saiba que eu guardo rancor e se me humilhar mais uma vez você tera um destino", "pior do que a morte.", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Ok, espero que nada de inusitado te aconteça durante as suas andanças, passar bem.", " ", " ", " "},
			// 66--------------------------- se a Kiki [[[NÃO]]] for a escolhida e perdeu na última luta com 0 vitórias = |1|0|1|0|2| -------------------
															 {"- Eu ainda não acredito que vocês foram tão facilmente trapaceados na nossa última disputa.", "- Sim, eu sei que tomaram uma bela de uma surra dentro das regras, porem a sua derrota não", "foi totalmente por mérito seu, foram aqueles caluniadores que armaram para cima de vocês.", " "},
															 {"- Aquilo ali foi sabotagem, sabotagem da criatura mais trapaceira e mentirosa com a qual eu já", "convivi.", "- Se eu fosse você não deixava passar em branco essa humilhação na frente de todo mundo.", "- Aquela galinha de metal flamejante tem que pagar.", "- Aquela vaca abobada sem rabo tem que pagar."},
															 {"- Então, você quer dar o troco em cada um desses safados usando todo o seu potencial criativo?", " ", " ", " "},
															 {"- É exatamente isso que eu gosto de ouvir!", "- Já estou até bolando um plano para vocês deixá-los comendo a sua poeira.", "- Vem cá, o quão bom você é em espalhar boatos, só para eu saber?", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Que estraga prazeres você é, me avisa quando quiser dar o troco neles ok.", "- Ah! Um conselho para vocês. Sempre que tiverem a oportunidade conversem com cabras, elas", "são senhorinhas muito sábias.", " "},
			// 71--------------------------- se a Kiki [[[NÃO]]] for a escolhida e 1º vitória na última luta = |1|1|0|0|1| -------------------
															 {"- Aeeeeeeeeee! Hoje é dia de comemoração.", "- Eu posso até ter perdido, mas vocês conseguiram sua vitória e momentos como esses devem", "ser apreciados em companhia de muita comida e álcool."},
															 {"- Como eu já falei antes, vocês formam uma dupla incrível e todos reconheceram os seus", "talentos inatos.", "- Principalmente aqueles bobões, feiosos, chatos que ficam querendo separar o grupo por", "motivos bestas."},
															 {"- Mas agora, que cada um levou uma surra, vão querer se unir novamente e todos vamos ficar", "felizes e contentes juntos vivendo grandes aventuras sem ser abandonada mais uma vez.", " ", " "},
															 {"*Sons de vômito*", "- Eu... acho que posso ter exagerado um pouquinho.", " ", " "},
															 {"- O que vocês queriam falar comigo mesmo?", "- Ah, vocês querem me dar uma surra de novo?", " ", " "},
															 {"- Certo, vai sonhando que isso vai se repetir.","- Amanha de manhã nós resolveremos os detalhes, umas 14h talvez ou você prefere às 15h?", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- É bom mesmo e eu também duvido que a minha ressaca será leve.", "- An, vocês acham que eu bebo demais?", "- Hahahahaha, espere para conhecer o Cletus, ele sim, é um poço sem fundo. Se bem que esta", "é uma péssima ideia, da última vez ele bebeu todo o meu vinho para viagens."},
															 {"- Mas tem um negócio que eu estou curiosa para saber a que fim levou.", "- Uma hora dessas o Seu Cletus começou a arrastar uma assa para a Dona Mabel LeBlanc", "sendo que era da Raguria que ele gostava. E sabe como é paquera de gente velha, horrível,", "nojento, mas não dá para desviar o olhar, tipo acidente de carroça."},
			// 79--------------------------- se a Kiki [[[NÃO]]] for a escolhida e perdeu na última luta, mas tem 1 vitória = |1|1|1|0|2| -------------------
															 {"- Surpreendentemente não tenho o que comentar.", "- Como esperado vocês perderam a competição sem que eu fizesse esforço algum e perderam", "a próxima da mesma forma, nada de novo.", " "},
															 {"- HAHAHA vocês realmente acreditaram? Não lhes culpo, afinal, sou uma atriz brilhante.", "- Mas seria muita maldade minha se eu estivesse falando sério sobre tudo isso.", "- Um brinde ao fracasso, porque quando sentimos a derrota também sentimos a vontade de nos", "levantar e mostrar do que somos realmente capazes."},
															 {"- Então, acho que agora é uma boa hora para propor outra partida, o que acham?", " ", " ", " "},
															 {"- Então se preparem porque vamos batalhar!", " ", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Desculpa, devo ter pegado pesado com vocês.", "- Que tau nós três fugirmos um pouco das responsabilidades e irmos ver o que o Arius e a Nada", "Mais que a Bala estão fazendo?", "- Eles andam passando muito tempo junto se é que vocês me entende.", "- Que tau nós três fugirmos um pouco das responsabilidades e irmos ver o que a Ayla e o", "Mutuca estão fazendo?", "- Eles andam passando muito tempo junto se é que vocês me entende.", "- Que isso! Eu estava me referindo a bombas."}, // < --------
			// 84--------------------------- se a Kiki [[[NÃO]]] for a escolhida e 2º vitória na última luta  = |1|2|4|0|1| -------------------
															 {"- Me deixem em paz, eu não quero me apresentar novamente, a última derrota foi humilhação", "o suficiente para mim hoje.", "- Do que você está falando?", "- Você está tentando me animar?", "- Pois continue, se me contar alguma história sua talvez funcione, quem sabe."},
															 {"*Um tempo depois*", "- HaHaHa, não acredito que tudo isso ocorreu quando ele foi tomar banho.", "- Eu me lembro de uma vez que todos os Cães tentaram me dar um banho. No final de tudo eu", "quase fui levada pela correnteza e acabei sendo batizada pela igreja de Tyathis, foi horrível."},
															 {"- Eu acho melhor não ficar chorando as pitangas aqui, vai que outro paladino queira me batizar.", "- Eu acho melhor não ficar chorando as pitangas aqui, vai que o Ignis queira me batizar de novo.", "E dessa vez não poderei contar com a Ayla e nem o mutuca para reverter a limpeza.", "E se isso acabar acontecendo eu conto com vocês e o mutuca para reverter a limpeza."}, // <--------
															 {"- Falando neles, você ficou sabendo que o filho do Mutuca, o Carlos Mutuca Jr., formou uma", "- Falando nele, você ficou sabendo que o filho do Mutuca, o Carlos Mutuca Jr., formou uma", "banda com o Pascal LeBlanc e o, o... ai, esqueci o nome do outro.", "As apresentações COM interferência ganharão +1 de apelo."}, // <-------- 
			// 88--------------------------- se a Kiki [[[NÃO]]] for a escolhida e derrota na última luta com 2 vitórias = |1|2|1|0|2| -------------------
															 {"- Hahaha, tinha que ser aquele projeto de fisiculturista!", "- É só vocês se distraírem por um segundo que ele vai e estraga tudo. Queria ver ele sem toda", "aquela sorte descomunal.", "- Hahaha, tinha que ser a Ayla!", "- É só vocês se distraírem por um segundo que ela vai e vira o jogo. Queria ve-la sem toda", "aquela lábia descomunal."}, // < ------
															 {"- Vocês estão bravos comigo?", "- Ei, eu não fico horas falando sem parar. Ok eu fico, mas todo mundo não cala a boca também.", "- Você já viu o Arius quando encontra algum assunto chato para falar? Ele fica pau a pau com", "- Você já viu o Arius quando encontra algum assunto chato para falar, ele fica pau a pau com", "o Ignis quando resolve dar bronca em todos nós."},
															 {"- An? Ah sim, o espetáculo, vocês querem tentar conseguir sua terceira vitória agora?", " ", " ", " "},
															 {"- Então vão depressa, porque dessa vez vocês precisam focar completamente na sua vitória,", "olho no ouro ouviu, sempre mantenha o olho no ouro.", " ", "As apresentações COM interferência ganharão +1 de apelo."},
															 {"- Bem, uma pausa sempre traz bons frutos.", "- Ou! deixa eu te levar para conhecer o Irevash, ele é outro amigo meu, você vai amá-lo, se tem", "alguém que gosta de fofocar tanto quanto eu é ele.", " "},
			// 93--------------------------- se a Kiki [[[NÃO]]] for a escolhida e 3º vitória na última luta = |1|3|0|0|1| -------------------
															 {"- Está me perguntando se eu estou contente por vocês?", "- Eu estou radiante! conseguiram três vitórias, três espetaculares vitórias.", "- Veja! eu já até comecei a escrever uma nova música sobre o brilhantismo no palco de vocês.", " "},
															 {"- E para comemorar, esta noite vocês não terão ração de viagem coisa nenhuma.", "- Nós vamos saborear um delicioso churrasco com tudo que tem direito feito por esta excelente", "mestre cuca.", "- Aqui, comece a encher o bucho com pão de alho e farofa que logo logo a carne sai.", "- Hoje é um dia para ser aproveitado, porque vocês darão adeus a Lala, Tanna-Toh e a mim."},
			// 95--------------------------- se já venceu 3 vezes a Kiki e falou com ela -------------------
															 {"- Por que voltamos? Pensei que iriamos desafiar os outros Cães.", "- Vocês voltaram? Pensei que iriam desafiar os outros Cães.", " ", " "}};

	
		
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