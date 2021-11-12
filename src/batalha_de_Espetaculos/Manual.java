package batalha_de_Espetaculos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import batalha_de_Espetaculos.Modelo.Icones_interativos;
import batalha_de_Espetaculos.Modelo.Texto;

public class Manual extends JPanel implements ActionListener {
	
	private Escolha_de_personagem tela1;
	private Escolha_de_adversario tela2;
	private Batalha tela3;
	private Menu telaMenu;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	private Icones_interativos fundo3 = new Icones_interativos(-20, 10);

	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1130, -12);

	
	private int contEngranagem1 = 1;
	private boolean contEngranagem2;

	private Timer timer;
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7, tl8, tl9, tl10, tl11, tl12, tl13,
	tl14,  tl15, tl16, tl17, tl18, tl19, tl20, tl21, tl22, tl23, tl24, tl25, tl26, tl27, tl28,
	tl29, tl30, tl31, tl32, tl33, tl34,  tl35, tl36, tl37, tl38, tl39, tl40, tl41, tl42, tl43,
	tl44, tl45, tl46, tl47, tl48, tl49, tl50, tl51, tl52, tl53, tl54, tl55, tl56;
	
	/* ------------------------------- Barra de rolagem ---------------------------------*/
	
	private int rolagemTela = 0;
	
	private Icones_interativos bntBarraBaixo = new Icones_interativos(1183, 590);
	private Icones_interativos bntBarraCima = new Icones_interativos(1183, 32);
	
	/* ------------------------------- Controles ---------------------------------*/
	
	private Icones_interativos teclaEsquerda = new Icones_interativos(268, 124);
	private Icones_interativos teclaBaixo = new Icones_interativos(teclaEsquerda.getX() + 40, teclaEsquerda.getY() + 46);
	private Icones_interativos teclaDireita = new Icones_interativos(teclaBaixo.getX() + 46, teclaEsquerda.getY());
	private Icones_interativos teclaCima = new Icones_interativos(teclaBaixo.getX(), teclaBaixo.getY() - 86);
	private Icones_interativos teclaZ = new Icones_interativos(teclaDireita.getX() + 186, teclaDireita.getY());
	private Icones_interativos teclaX = new Icones_interativos(teclaZ.getX() + 200, teclaZ.getY());
	private Icones_interativos teclaEsc = new Icones_interativos(teclaX.getX() + 190, teclaX.getY());

	
	private Texto txtLn1 = new Texto(126, 80, "Controles");
	private Texto txtLn2 = new Texto(270, 248, "Direcionais");
	private Texto txtLn3 = new Texto(txtLn2.getX() + 230, txtLn2.getY() - 38, "Selecionar");
	private Texto txtLn4 = new Texto(txtLn3.getX() + 228, txtLn3.getY(), "Voltar");
	private Texto txtLn5 = new Texto(txtLn4.getX() + 201, txtLn4.getY(), "Menu");
	
	private int contTempoControles = 0;
	
	/* ------------------------------- Batalha ---------------------------------*/
	
	private Texto txtLn6 = new Texto(80, 310, "Batalhas");
	private Texto txtLn7 = new Texto(90, txtLn6.getY() + 90, "* As batalhas são divididas em cinco turnos.");
	private Texto txtLn8 = new Texto(txtLn7.getX(), txtLn7.getY() + 44, "* Em cada turno você escolhe uma apresentação para ganhar");
	private Texto txtLn9 = new Texto(txtLn7.getX() + 8, txtLn8.getY() + 25, " pontos com a platéia ou prejudicar seus adversários.");
	
		/* ------------------------------- Nome apelo ---------------------------------*/

	private Icones_interativos nomeHabilidade1 = new Icones_interativos(1234 - 330 - 80, txtLn6.getY());
	private Icones_interativos nomeHabilidade2 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade1.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade3 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade2.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade4 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade3.getY() + 54 + 4);
	
	private int contTempoNomeApelo = 0;
	private Texto nomeApelo1, nomeApelo2, nomeApelo3, nomeApelo4;
	
	private Icones_interativos painel1 = new Icones_interativos(nomeHabilidade1.getX() - 2, nomeHabilidade1.getY() - 2);
	
		/* ------------------------------- info apelo ---------------------------------*/

	private Texto txtLn10 = new Texto(txtLn7.getX(), 610, "* Os apelos são pontos ganhos pelas apresentações. Quem obtem mais apelos na rodada fica em");
	private Texto txtLn11 = new Texto(txtLn7.getX() + 14, txtLn10.getY() + 25, "1º lugar no próximo turno e quem obtem mais apelos durante toda a batalha ganha a partida.");

	private Texto txtLn12 = new Texto(txtLn7.getX(), txtLn11.getY() + 44, "* As interferencias são pontos negativos usados nos seus adversários para faze-los perderem");
	private Texto txtLn13 = new Texto(txtLn7.getX() + 14, txtLn12.getY() + 25, "os apelos ganhados no turno atual ou nos anteriores.");

	private Texto txtLn56 = new Texto(txtLn7.getX(), txtLn13.getY() + 44, "* A luz acesa com o símbolo de um punho informa que a habilidade é do tipo físico.");

	private Icones_interativos apelo = new Icones_interativos(1234/2 - 860/2, 790);
	
	private Icones_interativos tipoDoApelo = new Icones_interativos(apelo.getX() + 19, apelo.getY() + 2);
	
	private Texto apeloQuantidade = new Texto(apelo.getX() + 90, apelo.getY() + 54/2 + 8, "Apelo:");
	
	private Icones_interativos apeloApelo1 = new Icones_interativos(apeloQuantidade.getX() + 66, apelo.getY() + 54/2 - 8);
	private Icones_interativos apeloApelo2 = new Icones_interativos(apeloApelo1.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo3 = new Icones_interativos(apeloApelo2.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo4 = new Icones_interativos(apeloApelo3.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo5 = new Icones_interativos(apeloApelo4.getX() + 27, apeloApelo1.getY());
	
	private Texto InterferenciaQuantidade = new Texto(apeloQuantidade.getX() + 348, apeloQuantidade.getY(), "Interferencia:");
	
	private Icones_interativos apeloInterf1 = new Icones_interativos(InterferenciaQuantidade.getX() + 127, apelo.getY() + 54/2 - 10);
	private Icones_interativos apeloInterf2 = new Icones_interativos(apeloInterf1.getX() + 27, apeloInterf1.getY());
	
	private Icones_interativos painel2 = new Icones_interativos(apelo.getX(), apelo.getY() - 2);
	
	
	private int contTempoInfoApelo = 0;
	
		// ----------------------- Descrição -----------------------------------

	private Texto txtLn14 = new Texto(txtLn7.getX(), 930, "* As descrições das apresentações são puramente enrrolações. A unica coisa importante é a ultima linha");
	private Texto txtLn15 = new Texto(txtLn7.getX() + 14, txtLn14.getY() + 25, "colorida, nela é descrito o efeito das apresentações que possuem interferencias.");	
	
	private Icones_interativos descricao = new Icones_interativos(1234/2 - 860/2, txtLn15.getY() + 50);
	
	private Texto textoDescricao1, textoDescricao2, textoDescricao3, textoDescricao4, textoDescricao5;
	
	private int contTempoDescricao = 1;
	private String conteudoDescricao = "Com sua espetacular e refinada técnica de propagar historias particulares"
			+ " você é capaz deretirar o primeiro colocado do seu pedestal causando uma alta interferência nele."
			+ "Este poder afeta o primeiro colocado.";
	
	private Icones_interativos painel3 = new Icones_interativos(descricao.getX(), descricao.getY() - 4);
	
	// ----------------------------------- campo batalha -----------------------------------

	private Texto txtLn16 = new Texto(txtLn7.getX(), 1260, "* Quando a apresentação for escolhida a batalha começa.");
	private Texto txtLn17 = new Texto(txtLn7.getX(), txtLn16.getY() + 44, "* A ordem do espetáculo é de cima para baixo.");	
	private Texto txtLn18 = new Texto(txtLn7.getX(), txtLn17.getY() + 44, "* A animação aparece seguida da quantidade de apelo");
	private Texto txtLn19 = new Texto(txtLn7.getX() + 14, txtLn18.getY() + 25, "ganhada com ela.");
	private Texto txtLn20 = new Texto(txtLn7.getX(), txtLn19.getY() + 44, "* Caso o poder for repetido duas rodadas seguidas 2");
	private Texto txtLn21 = new Texto(txtLn7.getX() + 14, txtLn20.getY() + 25, "apelos serram retirados do aventureiro como punição.");	
	private Texto txtLn22 = new Texto(txtLn7.getX(), txtLn21.getY() + 44, "* Caso o ataque cumpra o requisito do efeito do chefe");
	private Texto txtLn23 = new Texto(txtLn7.getX() + 14, txtLn22.getY() + 25, "da batalha um bônus ou uma punição de 1 apelo");
	private Texto txtLn24 = new Texto(txtLn7.getX() + 14, txtLn23.getY() + 25, "aparece de acordo com o efeito do chefe.");
	private Texto txtLn25 = new Texto(txtLn7.getX(), txtLn24.getY() + 44, "* Caso a habilidade tenha interferência ela será quem");
	private Texto txtLn26 = new Texto(txtLn7.getX() + 14, txtLn25.getY() + 25, "aparece por último na vez do aventureiro que a lançou.");
	
	private Icones_interativos campoBatalha1 = new Icones_interativos(1234 - 430 - 80, txtLn16.getY() - 24);
	private Icones_interativos campoBatalha2 = new Icones_interativos(campoBatalha1.getX(), campoBatalha1.getY() + 70 + 4);
	private Icones_interativos campoBatalha3 = new Icones_interativos(campoBatalha1.getX(), campoBatalha2.getY() + 70 + 4);
	private Icones_interativos campoBatalha4 = new Icones_interativos(campoBatalha1.getX(), campoBatalha3.getY() + 70 + 4);
	private Icones_interativos campoBatalha5 = new Icones_interativos(campoBatalha1.getX(), campoBatalha4.getY() + 70 + 4);
	
	private Texto txtEfeitoFase = new Texto(campoBatalha1.getX() - 74, campoBatalha3.getY() + 70/2 + 20/2, " ");
	private Icones_interativos efeitoFase = new Icones_interativos(txtEfeitoFase.getX() + 34, txtEfeitoFase.getY() - 17);
	
	private Icones_interativos iconeCampoBatalha1 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha1.getY() + 4);
	private Icones_interativos iconeCampoBatalha2 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha2.getY() + 4);
	private Icones_interativos iconeCampoBatalha3 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha3.getY() + 4);
	private Icones_interativos iconeCampoBatalha4 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha4.getY() + 4);
	private Icones_interativos iconeCampoBatalha5 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha5.getY() + 4);
	
	private Icones_interativos seletorAventureiro = new Icones_interativos(iconeCampoBatalha1.getX(), iconeCampoBatalha1.getY() - 1);
	
	// corações que mede o total de apelo e interferência de todas as rodadas 
	private Icones_interativos coracao01 = new Icones_interativos(campoBatalha1.getX() + 116, campoBatalha1.getY() + 70/2);
	private Icones_interativos coracao02 = new Icones_interativos(campoBatalha2.getX() + 116, campoBatalha2.getY() + 70/2);
	private Icones_interativos coracao03 = new Icones_interativos(campoBatalha3.getX() + 116, campoBatalha3.getY() + 70/2);
	private Icones_interativos coracao04 = new Icones_interativos(campoBatalha4.getX() + 116, campoBatalha4.getY() + 70/2);
	private Icones_interativos coracao05 = new Icones_interativos(campoBatalha5.getX() + 116, campoBatalha5.getY() + 70/2);
	
	// corações que mede o apelo e interferência da rodada
	private Icones_interativos coracao11 = new Icones_interativos(campoBatalha1.getX() + 128, campoBatalha1.getY() + 10); 
	private Icones_interativos coracao12 = new Icones_interativos(coracao11.getX() + 25, coracao11.getY()); 
	private Icones_interativos coracao13 = new Icones_interativos(coracao12.getX() + 25, coracao12.getY()); 
	private Icones_interativos coracao14 = new Icones_interativos(coracao13.getX() + 25, coracao13.getY()); 
	private Icones_interativos coracao15 = new Icones_interativos(coracao14.getX() + 25, coracao14.getY()); 
	private Icones_interativos coracao16 = new Icones_interativos(coracao15.getX() + 25, coracao15.getY()); 
	private Icones_interativos coracao17 = new Icones_interativos(coracao16.getX() + 25, coracao16.getY()); 
	private Icones_interativos coracao18 = new Icones_interativos(coracao17.getX() + 25, coracao17.getY()); 
	private Icones_interativos coracao19 = new Icones_interativos(coracao18.getX() + 25, coracao18.getY()); 
	private Icones_interativos coracao110 = new Icones_interativos(coracao19.getX() + 25, coracao19.getY()); 
	
	private Icones_interativos coracao21 = new Icones_interativos(campoBatalha2.getX() + 128, campoBatalha2.getY() + 10); 
	private Icones_interativos coracao22 = new Icones_interativos(coracao21.getX() + 25, coracao21.getY()); 
	private Icones_interativos coracao23 = new Icones_interativos(coracao22.getX() + 25, coracao22.getY()); 
	private Icones_interativos coracao24 = new Icones_interativos(coracao23.getX() + 25, coracao23.getY()); 
	private Icones_interativos coracao25 = new Icones_interativos(coracao24.getX() + 25, coracao24.getY()); 
	private Icones_interativos coracao26 = new Icones_interativos(coracao25.getX() + 25, coracao25.getY()); 
	private Icones_interativos coracao27 = new Icones_interativos(coracao26.getX() + 25, coracao26.getY()); 
	private Icones_interativos coracao28 = new Icones_interativos(coracao27.getX() + 25, coracao27.getY()); 
	private Icones_interativos coracao29 = new Icones_interativos(coracao28.getX() + 25, coracao28.getY()); 
	private Icones_interativos coracao210 = new Icones_interativos(coracao29.getX() + 25, coracao29.getY()); 
	
	private Icones_interativos coracao31 = new Icones_interativos(campoBatalha3.getX() + 128, campoBatalha3.getY() + 10); 
	private Icones_interativos coracao32 = new Icones_interativos(coracao31.getX() + 25, coracao31.getY()); 
	private Icones_interativos coracao33 = new Icones_interativos(coracao32.getX() + 25, coracao32.getY()); 
	private Icones_interativos coracao34 = new Icones_interativos(coracao33.getX() + 25, coracao33.getY()); 
	private Icones_interativos coracao35 = new Icones_interativos(coracao34.getX() + 25, coracao34.getY()); 
	private Icones_interativos coracao36 = new Icones_interativos(coracao35.getX() + 25, coracao35.getY()); 
	private Icones_interativos coracao37 = new Icones_interativos(coracao36.getX() + 25, coracao36.getY()); 
	private Icones_interativos coracao38 = new Icones_interativos(coracao37.getX() + 25, coracao37.getY()); 
	private Icones_interativos coracao39 = new Icones_interativos(coracao38.getX() + 25, coracao38.getY()); 
	private Icones_interativos coracao310 = new Icones_interativos(coracao39.getX() + 25, coracao39.getY()); 
	
	private Icones_interativos coracao41 = new Icones_interativos(campoBatalha4.getX() + 128, campoBatalha4.getY() + 10); 
	private Icones_interativos coracao42 = new Icones_interativos(coracao41.getX() + 25, coracao41.getY()); 
	private Icones_interativos coracao43 = new Icones_interativos(coracao42.getX() + 25, coracao42.getY()); 
	private Icones_interativos coracao44 = new Icones_interativos(coracao43.getX() + 25, coracao43.getY()); 
	private Icones_interativos coracao45 = new Icones_interativos(coracao44.getX() + 25, coracao44.getY()); 
	private Icones_interativos coracao46 = new Icones_interativos(coracao45.getX() + 25, coracao45.getY()); 
	private Icones_interativos coracao47 = new Icones_interativos(coracao46.getX() + 25, coracao46.getY()); 
	private Icones_interativos coracao48 = new Icones_interativos(coracao47.getX() + 25, coracao47.getY()); 
	private Icones_interativos coracao49 = new Icones_interativos(coracao48.getX() + 25, coracao48.getY()); 
	private Icones_interativos coracao410 = new Icones_interativos(coracao49.getX() + 25, coracao49.getY()); 
	
	private Icones_interativos coracao51 = new Icones_interativos(campoBatalha5.getX() + 128, campoBatalha5.getY() + 10); 
	private Icones_interativos coracao52 = new Icones_interativos(coracao51.getX() + 25, coracao51.getY()); 
	private Icones_interativos coracao53 = new Icones_interativos(coracao52.getX() + 25, coracao52.getY()); 
	private Icones_interativos coracao54 = new Icones_interativos(coracao53.getX() + 25, coracao53.getY()); 
	private Icones_interativos coracao55 = new Icones_interativos(coracao54.getX() + 25, coracao54.getY()); 
	private Icones_interativos coracao56 = new Icones_interativos(coracao55.getX() + 25, coracao55.getY()); 
	private Icones_interativos coracao57 = new Icones_interativos(coracao56.getX() + 25, coracao56.getY()); 
	private Icones_interativos coracao58 = new Icones_interativos(coracao57.getX() + 25, coracao57.getY()); 
	private Icones_interativos coracao59 = new Icones_interativos(coracao58.getX() + 25, coracao58.getY()); 
	private Icones_interativos coracao510 = new Icones_interativos(coracao59.getX() + 25, coracao59.getY()); 
	
	private Icones_interativos luzCampoBatalha1 = new Icones_interativos(campoBatalha1.getX() + 88, campoBatalha1.getY() + 12);
	private Icones_interativos luzCampoBatalha2 = new Icones_interativos(campoBatalha2.getX() + 88, campoBatalha2.getY() + 12);
	private Icones_interativos luzCampoBatalha3 = new Icones_interativos(campoBatalha3.getX() + 88, campoBatalha3.getY() + 12);
	private Icones_interativos luzCampoBatalha4 = new Icones_interativos(campoBatalha4.getX() + 88, campoBatalha4.getY() + 12);
	private Icones_interativos luzCampoBatalha5 = new Icones_interativos(campoBatalha5.getX() + 88, campoBatalha5.getY() + 12);
	
	private Icones_interativos painel4 = new Icones_interativos(campoBatalha1.getX() - 8, campoBatalha1.getY() - 4);

	private int contTempoCampoBatalha = 0;
	
	// ----------------------- Considerações finais -----------------------------------

	private Texto txtLn39 = new Texto(txtLn7.getX(), txtLn26.getY() + 44, "* Depois de cada turno o campo de batalha se reorganiza ficando em ordem decrescente de acordo com os");
	private Texto txtLn40 = new Texto(txtLn7.getX() + 14, txtLn39.getY() + 25, "pontos no total. No final do 5º turno a batalha acaba e o aventureiro com mais pontos ganha.");
	
	// ----------------------- Tabela de efeito de chefe -----------------------------------

	private Texto txtLn27 = new Texto(390, txtLn40.getY() + 120, "-*_ Tabela de efeito de chefe _*-");
	private Texto txtLn41 = new Texto(340, txtLn27.getY() + 60, "||==================================================||");
	private Texto txtLn28 = new Texto(txtLn41.getX(), txtLn41.getY() + 34, "||      ||===========================================||      ||");
	private Texto txtLn29 = new Texto(txtLn28.getX(), txtLn28.getY() + 25, "||      ||      IGNIS      ||   -1   ||   Em ataques com interferência     ||      ||");
	private Texto txtLn30 = new Texto(txtLn28.getX(), txtLn29.getY() + 25, "||      || ----------------- || ----._ || --------------------------------------------- ||      ||");
	private Texto txtLn31 = new Texto(txtLn28.getX(), txtLn30.getY() + 25, "||      ||_     AYLA    _||   -1   ||   Em ataques sem interferência     ||      ||");
	private Texto txtLn32 = new Texto(txtLn28.getX(), txtLn31.getY() + 25, "||      || ----------------- || ----._ || --------------------------------------------- ||      ||");
	private Texto txtLn33 = new Texto(txtLn28.getX(), txtLn32.getY() + 25, "||      ||.  REXTHOR  ||   +1  ||   Em ataques físicos                     ||      ||");
	private Texto txtLn34 = new Texto(txtLn28.getX(), txtLn33.getY() + 25, "||      || ----------------- || ----._ || --------------------------------------------- ||      ||");
	private Texto txtLn35 = new Texto(txtLn28.getX(), txtLn34.getY() + 25, "||      ||_      KIKI       ||   +1  ||   Em ataques com efeito               ||      ||");
	private Texto txtLn36 = new Texto(txtLn28.getX(), txtLn35.getY() + 25, "||      || ----------------- || ----._ || --------------------------------------------- ||      ||");
	private Texto txtLn37 = new Texto(txtLn28.getX(), txtLn36.getY() + 25, "||      ||.     ARIUS     ||   +1  ||   Em ataques sem interferência     ||      ||");
	private Texto txtLn38 = new Texto(txtLn28.getX(), txtLn37.getY() + 25, "||      ||===========================================||      ||");
	private Texto txtLn42 = new Texto(txtLn28.getX(), txtLn38.getY() + 34, "||      ||===========================================||      ||");
	private Texto txtLn43 = new Texto(txtLn28.getX(), txtLn42.getY() + 34, "||___||                                                                                       ||___||");

	
	
	public Manual (boolean Engrenagem2) {
		contEngranagem2 = Engrenagem2;
		
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\fundo.png");
		fundo3.load("res\\Manual\\fundo6.png");
		
		engrenagem1.load("res\\engrenagem1.png");
		
		engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");

		contorno.load("res\\contorno.png");
		
		/* ------------------------------- Barra de rolagem ---------------------------------*/

		bntBarraBaixo.load("res\\Manual\\teclaBaixo.png");
		bntBarraCima.load("res\\Manual\\teclaCima.png");
		
		/* ------------------------------- Controles ---------------------------------*/
		
		teclaEsquerda.load("res\\Menu\\setaEsquerda.png");
		teclaDireita.load("res\\Menu\\setaDireita.png");
		teclaCima.load("res\\Menu\\setaCima.png");
		teclaBaixo.load("res\\Menu\\setaBaixo.png");
		
		teclaZ.load("res\\Menu\\teclaZ.png");
		teclaX.load("res\\Menu\\teclaX.png");
		teclaEsc.load("res\\Menu\\teclaEsc.png");
		
		txtLn1.setFonte(new Font("Arial", Font.BOLD, 30));
		txtLn1.setCorTexto(new Color (235, 230, 233));
		txtLn2.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn3.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn4.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn5.setFonte(new Font("Arial", Font.PLAIN, 24));
		
		/* ------------------------------- Batalha ---------------------------------*/
		
		nomeHabilidade1.load("res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		nomeHabilidade2.load("res\\batalha\\nomeHabilidade.png");
		nomeHabilidade3.load("res\\batalha\\nomeHabilidade.png");
		nomeHabilidade4.load("res\\batalha\\nomeHabilidade.png");
		
		nomeApelo1 = new Texto(nomeHabilidade1.getX() + 20, nomeHabilidade1.getY() + 54/2 + 8, "Tapa em puristas");
		nomeApelo2 = new Texto(nomeHabilidade2.getX() + 20, nomeHabilidade2.getY() + 54/2 + 8, "Ignis bonitão");
		nomeApelo3 = new Texto(nomeHabilidade3.getX() + 20, nomeHabilidade3.getY() + 54/2 + 8, "Provocação petulante");
		nomeApelo4 = new Texto(nomeHabilidade4.getX() + 20, nomeHabilidade4.getY() + 54/2 + 8, "Meca-Rito");
		
		nomeApelo1.setFonte(new Font("Arial", Font.PLAIN, 20));
		nomeApelo1.setCorTexto(new Color (255, 60, 0));
		nomeApelo2.setFonte(new Font("Arial", Font.PLAIN, 20));
		nomeApelo2.setCorTexto(new Color (235, 148, 150));
		nomeApelo3.setFonte(new Font("Arial", Font.PLAIN, 20));
		nomeApelo4.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		txtLn6.setFonte(new Font("Arial", Font.BOLD, 30));
		txtLn7.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn8.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn9.setFonte(new Font("Arial", Font.PLAIN, 24));
		
		painel1.load("res\\batalha\\painel1.png");
		
		/* ------------------------------- info apelo ---------------------------------*/
		
		txtLn10.setFonte(new Font("Arial", Font.PLAIN, 24));
		
		apelo.load("res\\batalha\\infoApelo.png");

		apeloQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		apeloQuantidade.setCorTexto(new Color (240, 148, 150));
		InterferenciaQuantidade.setFonte(apeloQuantidade.getFonte());
		
		tipoDoApelo.load("res\\batalha\\tipoDoApelo1.png");
		
		apeloApelo1.load("res\\batalha\\apelo.png");
		apeloApelo2.load("res\\batalha\\apelo.png");
		apeloApelo3.load("res\\batalha\\apelo.png");
		apeloApelo4.load("res\\batalha\\apelo.png");
		apeloApelo5.load("res\\batalha\\apelo.png");
		
		apeloInterf1.load("res\\batalha\\interferencia.png");
		apeloInterf2.load("res\\batalha\\interferencia.png");
		
		painel2.load("res\\batalha\\painel2.png");
		
		// ----------------------- Descrição -----------------------------------

		descricao.load("res\\batalha\\descricao.png");

		textoDescricao1 = new Texto(descricao.getX() + 20, descricao.getY() + 40, " ");
		textoDescricao2 = new Texto(textoDescricao1.getX(), textoDescricao1.getY() + 28, " ");
		textoDescricao3 = new Texto(textoDescricao2.getX(), textoDescricao2.getY() + 28, " ");
		textoDescricao4 = new Texto(textoDescricao3.getX(), textoDescricao3.getY() + 28, " ");
		textoDescricao5 = new Texto(textoDescricao4.getX() + 238, textoDescricao4.getY() + 18, "Este poder afeta o primeiro colocado.");

		textoDescricao1.setFonte(new Font("Arial", Font.PLAIN, 20));
		textoDescricao2.setFonte(textoDescricao1.getFonte());
		textoDescricao3.setFonte(textoDescricao1.getFonte());
		textoDescricao4.setFonte(textoDescricao1.getFonte());
		textoDescricao5.setFonte(textoDescricao1.getFonte());
		
		textoDescricao1.setCorTexto(new Color (239, 182, 202));
		textoDescricao2.setCorTexto(textoDescricao1.getCorTexto());
		textoDescricao3.setCorTexto(textoDescricao1.getCorTexto());
		textoDescricao4.setCorTexto(textoDescricao1.getCorTexto()); 
		textoDescricao5.setCorTexto(new Color (165, 1, 67));
		
		painel3.load("res\\batalha\\painel3.png");
		
		// ----------------------------------- campo batalha -----------------------------------
		
		campoBatalha1.load("res\\batalha\\campoBatalha.png");
		campoBatalha2.load("res\\batalha\\campoBatalha.png");
		campoBatalha3.load("res\\batalha\\campoBatalha.png");
		campoBatalha4.load("res\\batalha\\campoBatalha.png");
		campoBatalha5.load("res\\batalha\\campoBatalha.png");
		
		luzCampoBatalha1.load("res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha2.load("res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha3.load("res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha4.load("res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha5.load("res\\batalha\\luzCampoBatalha1.png");
		
		efeitoFase.setImagem(null);
		
		iconeCampoBatalha1.load("res\\batalha\\Ignis\\iconeCampoBatalha.png");
		iconeCampoBatalha2.load("res\\batalha\\Ayla\\iconeCampoBatalha.png");
		iconeCampoBatalha3.load("res\\batalha\\Rexthor\\iconeCampoBatalha.png");
		iconeCampoBatalha4.load("res\\batalha\\Kiki\\iconeCampoBatalha.png");
		iconeCampoBatalha5.load("res\\batalha\\Arius\\iconeCampoBatalha.png");
				
		seletorAventureiro.load("res\\batalha\\seletorAventureiro.png");

		coracao01.load("res\\batalha\\apelo.png");
		coracao02.load("res\\batalha\\apelo.png");
		coracao03.load("res\\batalha\\apelo.png");
		coracao04.load("res\\batalha\\apelo.png");
		coracao05.load("res\\batalha\\apelo.png");
		
		painel4.load("res\\batalha\\painel4.png");
		
		apagarCoracoes();
		
		// ----------------------- Tabela de efeito de chefe -----------------------------------

		txtLn27.setFonte(new Font("Arial", Font.BOLD, 30));
		txtLn28.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn29.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn30.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn31.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn32.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn33.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn34.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn35.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn36.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn37.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn38.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn41.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn42.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtLn43.setFonte(new Font("Arial", Font.PLAIN, 18));
		
		// ----------------------- Considerações finais -----------------------------------
		
		txtLn39.setFonte(new Font("Arial", Font.PLAIN, 22));
		txtLn40.setFonte(new Font("Arial", Font.PLAIN, 22));
		
		// ------------------------------------------------------------------------------

		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void AnimarControles() {
		contTempoControles ++;
		
		if(contTempoControles == 121) {
			contTempoControles = 0;
		}
		
		if(contTempoControles % 10 == 0) {
			teclaEsquerda.load("res\\Menu\\setaEsquerda.png");
			teclaDireita.load("res\\Menu\\setaDireita.png");
			teclaCima.load("res\\Menu\\setaCima.png");
			teclaBaixo.load("res\\Menu\\setaBaixo.png");
			teclaZ.load("res\\Menu\\teclaZ.png");
			teclaX.load("res\\Menu\\teclaX.png");
			teclaEsc.load("res\\Menu\\teclaEsc.png");
		}
		
		if(contTempoControles == 15) {
			teclaEsquerda.load("res\\Menu\\setaEsquerda2.png");
		} else if(contTempoControles == 30) {
			teclaDireita.load("res\\Menu\\setaDireita2.png");
		} else if(contTempoControles == 45) {
			teclaCima.load("res\\Menu\\setaCima2.png");
		} else if(contTempoControles == 60) {
			teclaBaixo.load("res\\Menu\\setaBaixo2.png");
		} else if(contTempoControles == 75) {
			teclaZ.load("res\\Menu\\teclaZ2.png");
		} else if(contTempoControles == 90) {
			teclaX.load("res\\Menu\\teclaX2.png");
		} else if(contTempoControles == 105) {
			teclaEsc.load("res\\Menu\\teclaEsc2.png");
		}
		
	}
	
	public void AnimarNomeApelo() {
		contTempoNomeApelo ++;
		
		if(contTempoNomeApelo == 100) {
			contTempoNomeApelo = 0;
		}
		
		if(contTempoNomeApelo == 0 || contTempoNomeApelo == 25 || contTempoNomeApelo == 50 || contTempoNomeApelo == 75) {
			nomeHabilidade1.load("res\\batalha\\nomeHabilidade.png");
			nomeHabilidade2.load("res\\batalha\\nomeHabilidade.png");
			nomeHabilidade3.load("res\\batalha\\nomeHabilidade.png");
			nomeHabilidade4.load("res\\batalha\\nomeHabilidade.png");
		}
		
		if(contTempoNomeApelo == 0) {
			nomeHabilidade1.load("res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 25) {
			nomeHabilidade2.load("res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 50) {
			nomeHabilidade3.load("res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 75) {
			nomeHabilidade4.load("res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		}
		
	}
	
	public void AnimarInfoApelo() {
		contTempoInfoApelo ++;
		
		if(contTempoInfoApelo == 80) {
			contTempoInfoApelo = 0;
		}
		
		
		if(contTempoInfoApelo == 0 || contTempoInfoApelo == 40 || contTempoInfoApelo == 80) {
			tipoDoApelo.load("res\\batalha\\tipoDoApelo1.png");

			apeloApelo1.load("res\\batalha\\apelo.png");
			apeloApelo2.load("res\\batalha\\apelo.png");
			apeloApelo3.load("res\\batalha\\apelo.png");
			apeloApelo4.load("res\\batalha\\apelo.png");
			apeloApelo5.load("res\\batalha\\apelo.png");
			
			apeloInterf1.load("res\\batalha\\interferencia.png");
			apeloInterf2.load("res\\batalha\\interferencia.png");
		}
		

		if(contTempoInfoApelo == 20) {
			tipoDoApelo.load("res\\batalha\\tipoDoApelo2.png");

			apeloApelo1.load("res\\batalha\\apelo.png");
			apeloApelo2.setImagem(null);
			apeloApelo3.load("res\\batalha\\apelo.png");
			apeloApelo4.setImagem(null);
			apeloApelo5.load("res\\batalha\\apelo.png");
			
			apeloInterf1.load("res\\batalha\\interferencia.png");
			apeloInterf2.setImagem(null);
		}
		
		if(contTempoInfoApelo == 60) {
			tipoDoApelo.load("res\\batalha\\tipoDoApelo2.png");

			apeloApelo1.setImagem(null);
			apeloApelo2.load("res\\batalha\\apelo.png");
			apeloApelo3.setImagem(null);
			apeloApelo4.load("res\\batalha\\apelo.png");
			apeloApelo5.setImagem(null);
			
			apeloInterf1.setImagem(null); 
			apeloInterf2.load("res\\batalha\\interferencia.png");
		}
		
	}
	
	public void AnimarDescricao() {
		contTempoDescricao ++;
		
		if(contTempoDescricao == 250) {
			contTempoDescricao = -18;
			textoDescricao1.setTexto(" ");
			textoDescricao2.setTexto(" ");
		}
		
		if(contTempoDescricao > 0 && contTempoDescricao < 90) {
			textoDescricao1.setTexto(conteudoDescricao.substring(0, contTempoDescricao));
		} else if(contTempoDescricao > 0 && contTempoDescricao < 171) {
			textoDescricao2.setTexto(conteudoDescricao.substring(89, contTempoDescricao));
		}
		
	}
	
	public void AnimarCampoBatalha() {
		contTempoCampoBatalha ++;
		
		if(contTempoCampoBatalha == 820) {
			contTempoCampoBatalha = -20;
			apagarCoracoes();
			
			coracao01.setX(campoBatalha1.getX() + 116);
			coracao02.setX(campoBatalha2.getX() + 116);
			coracao03.setX(campoBatalha3.getX() + 116);
			coracao04.setX(campoBatalha4.getX() + 116);
			coracao05.setX(campoBatalha5.getX() + 116);
		}
		
		if(contTempoCampoBatalha > 0 && contTempoCampoBatalha < 100) {
			luzCampoBatalha1.load("res\\batalha\\Ignis\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 100 && contTempoCampoBatalha < 200) {
			luzCampoBatalha1.load("res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha2.load("res\\batalha\\Ayla\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 200 && contTempoCampoBatalha < 300) {
			luzCampoBatalha2.load("res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha3.load("res\\batalha\\Rexthor\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 400 && contTempoCampoBatalha < 500) {
			luzCampoBatalha3.load("res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha4.load("res\\batalha\\Kiki\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 500 && contTempoCampoBatalha < 600) {
			luzCampoBatalha4.load("res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha5.load("res\\batalha\\Arius\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 700) {
			luzCampoBatalha1.load("res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha2.load("res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha3.load("res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha4.load("res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha5.load("res\\batalha\\luzCampoBatalha1.png");
		}
		
		for(int i=1; i<6; i++) {
			if(contTempoCampoBatalha == i * 10) {
				(i == 1 ? coracao11 : coracao12).load("res\\batalha\\apelo.png");
			} 
		}
		
		for(int i=1; i<6; i++) {
			if(contTempoCampoBatalha == i * 10 + 100) {
				(i == 1 ? coracao21 : (i == 2 ? coracao22 : coracao23)).load("res\\batalha\\apelo.png");
			} 
		}
		
		for(int i=1; i<7; i++) {
			if(contTempoCampoBatalha == i * 10 + 200) {
				(i == 1 ? coracao31 : (i == 2 ? coracao32 : (i == 3 ? coracao33 : (i == 4 ? coracao34 : (i == 5 ? coracao35 : coracao36))))).load("res\\batalha\\apelo.png");
			} 
		}
		
		if(contTempoCampoBatalha == 290) {
			txtEfeitoFase.setY(campoBatalha3.getY() + 70/2 + 20/2);
			efeitoFase.setY(txtEfeitoFase.getY() - 17);
			txtEfeitoFase.setTexto("- 2");
			efeitoFase.load("res\\batalha\\apelo.png");
			
		} else if(contTempoCampoBatalha == 340) {
			txtEfeitoFase.setTexto(" ");
			efeitoFase.setImagem(null);
		}
		
		for(int i=5; i<7; i++) {
			if(contTempoCampoBatalha == i * 10 + 300) {
				(i == 5 ? coracao36 : coracao35).setImagem(null);
			} 
		}
		
		for(int i=1; i<7; i++) {
			if(contTempoCampoBatalha == i * 10 + 400) {
				(i == 1 ? coracao41 : (i == 2 ? coracao42 : (i == 3 ? coracao43 : coracao44))).load("res\\batalha\\apelo.png");
			} 
		}
		
		if(contTempoCampoBatalha == 450) {
			txtEfeitoFase.setY(campoBatalha4.getY() + 70/2 + 20/2);
			efeitoFase.setY(txtEfeitoFase.getY() - 17);
			txtEfeitoFase.setTexto("+ 1");
			efeitoFase.load("res\\batalha\\apelo.png");
			
		} else if(contTempoCampoBatalha == 480) {
			txtEfeitoFase.setTexto(" ");
			efeitoFase.setImagem(null);
		}

		if(contTempoCampoBatalha == 490) {
			coracao45.load("res\\batalha\\apelo.png");
		} 
		
		for(int i=1; i<6; i++) {
			if(contTempoCampoBatalha == i * 10 + 500) {
				(i == 1 ? coracao51 : (i == 2 ? coracao52 : (i == 3 ? coracao53 : (i == 4 ? coracao54 : coracao55)))).load("res\\batalha\\apelo.png");
			} 
		}
		
		for(int i=1; i<5; i++) {
			if(contTempoCampoBatalha == i * 20 + 600) {
				(i == 1 ? coracao12 : (i == 2 ? coracao23 : (i == 3 ? coracao34 : coracao45))).setImagem(null);
			} 
		}
		
		for(int i=2; i<7; i++) {
			if(contTempoCampoBatalha == (i-1) * 20 + 700) {

				(i == 2 ? coracao01 : (i == 3 ? coracao02 : (i == 4 ? coracao03 : (i == 5 ? coracao04 : coracao05)))).setX((i == 2 ? campoBatalha1 : (i == 3 ? campoBatalha2 : (i == 4 ? campoBatalha3 : (i == 5 ? campoBatalha4 : campoBatalha5)))).getX() + 116 + (5 * i));
			}
		}
	}
	
	public void apagarCoracoes() {
		coracao11.setImagem(null); coracao12.setImagem(null); coracao13.setImagem(null); coracao14.setImagem(null); coracao15.setImagem(null);
		coracao16.setImagem(null); coracao17.setImagem(null); coracao18.setImagem(null); coracao19.setImagem(null); coracao110.setImagem(null);
		
		coracao21.setImagem(null); coracao22.setImagem(null); coracao23.setImagem(null); coracao24.setImagem(null); coracao25.setImagem(null);
		coracao26.setImagem(null); coracao27.setImagem(null); coracao28.setImagem(null); coracao29.setImagem(null); coracao210.setImagem(null);
		
		coracao31.setImagem(null); coracao32.setImagem(null); coracao33.setImagem(null); coracao34.setImagem(null); coracao35.setImagem(null);
		coracao36.setImagem(null); coracao37.setImagem(null); coracao38.setImagem(null); coracao39.setImagem(null); coracao310.setImagem(null);
		
		coracao41.setImagem(null); coracao42.setImagem(null); coracao43.setImagem(null); coracao44.setImagem(null); coracao45.setImagem(null);
		coracao46.setImagem(null); coracao47.setImagem(null); coracao48.setImagem(null); coracao49.setImagem(null); coracao410.setImagem(null);
		
		coracao51.setImagem(null); coracao52.setImagem(null); coracao53.setImagem(null); coracao54.setImagem(null); coracao55.setImagem(null);
		coracao56.setImagem(null); coracao57.setImagem(null); coracao58.setImagem(null); coracao59.setImagem(null); coracao510.setImagem(null);
	}
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_DOWN && rolagemTela > -1640) {
			if(contEngranagem1 == 2) {
				contEngranagem1 = 1;
			} else {contEngranagem1 ++;}
			
			engrenagem1.load("res\\engrenagem" + contEngranagem1 + ".png");
			
			rolagemTela = rolagemTela - 7;
			bntBarraBaixo.load("res\\Manual\\teclaBaixo2.png");
			
		}else if(codigo == KeyEvent.VK_UP && rolagemTela < 0) {
			if(contEngranagem1 == 2) {
				contEngranagem1 = 1;
			} else {contEngranagem1 ++;}
			
			engrenagem1.load("res\\engrenagem" + contEngranagem1 + ".png");
			
			rolagemTela = rolagemTela + 7;
			bntBarraCima.load("res\\Manual\\teclaCima2.png");

		}else if(codigo == KeyEvent.VK_X) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			if(tela3 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela3);
		        janelaPrincipal.setTitle("Batalha");
		        tela3.setContEngranagem2(contEngranagem2);
		        janelaPrincipal.revalidate();
		        timer.stop();
				
			} else if(tela2 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela2);
		        janelaPrincipal.setTitle("Escolha de Adversário");
		        tela2.setContEngranagem2(contEngranagem2);
		        janelaPrincipal.revalidate();
		        timer.stop();
				
			} else if(tela1 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela1);
		        janelaPrincipal.setTitle("Escolha de Personagem");
		        tela1.setContEngranagem2(contEngranagem2);
		        janelaPrincipal.revalidate();
		        timer.stop();
		        
			} else if(telaMenu != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(telaMenu);
		        janelaPrincipal.setTitle("Menu");
		        telaMenu.setContEngranagem2(contEngranagem2);
		        janelaPrincipal.revalidate();
		        timer.stop();
			}
		}
	}
	
	public void KeyReleased (java.awt.event.KeyEvent tecla){
		bntBarraBaixo.load("res\\Manual\\teclaBaixo.png");
		bntBarraCima.load("res\\Manual\\teclaCima.png");
	}
	
	
	public void setTelaMenu(Menu menu) {
		this.telaMenu = menu;
	}

	public void setTela2(Escolha_de_adversario tela2) {
		this.tela2 = tela2;
	}

	public void setTela1(Escolha_de_personagem tela1) {
		this.tela1 = tela1;
	}

	public void setTela3(Batalha tela3) {
		this.tela3 = tela3;
	}

	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		graficos.drawImage(fundo3.getImagem(), fundo3.getX(), fundo3.getY() + rolagemTela, this);
		
		/* ------------------------------- Controles ---------------------------------*/
		
		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getX(), teclaEsquerda.getY() + rolagemTela, this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getX(), teclaDireita.getY() + rolagemTela, this);
		graficos.drawImage(teclaCima.getImagem(), teclaCima.getX(), teclaCima.getY() + rolagemTela, this);
		graficos.drawImage(teclaBaixo.getImagem(), teclaBaixo.getX(), teclaBaixo.getY() + rolagemTela, this);
		
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getX(), teclaZ.getY() + rolagemTela, this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getX(), teclaX.getY() + rolagemTela, this);
		graficos.drawImage(teclaEsc.getImagem(), teclaEsc.getX(), teclaEsc.getY() + rolagemTela, this);
		
		graficos.setColor(txtLn1.getCorTexto());
		tl1 = new TextLayout(txtLn1.getTexto(), txtLn1.getFonte(), frc);
		tl2 = new TextLayout(txtLn2.getTexto(), txtLn2.getFonte(), frc);
		tl3 = new TextLayout(txtLn3.getTexto(), txtLn3.getFonte(), frc);
		tl4 = new TextLayout(txtLn4.getTexto(), txtLn4.getFonte(), frc);
		tl5 = new TextLayout(txtLn5.getTexto(), txtLn5.getFonte(), frc);
		
		tl1.draw(graficos, txtLn1.getX(), txtLn1.getY() + rolagemTela);
		tl2.draw(graficos, txtLn2.getX(), txtLn2.getY() + rolagemTela);
		tl3.draw(graficos, txtLn3.getX(), txtLn3.getY() + rolagemTela);
		tl4.draw(graficos, txtLn4.getX(), txtLn4.getY() + rolagemTela);
		tl5.draw(graficos, txtLn5.getX(), txtLn5.getY() + rolagemTela);
		
		/* ------------------------------- Batalha ---------------------------------*/
		
		tl6 = new TextLayout(txtLn6.getTexto(), txtLn6.getFonte(), frc);
		tl7 = new TextLayout(txtLn7.getTexto(), txtLn7.getFonte(), frc);
		tl8 = new TextLayout(txtLn8.getTexto(), txtLn8.getFonte(), frc);
		tl9 = new TextLayout(txtLn9.getTexto(), txtLn9.getFonte(), frc);
		
		tl6.draw(graficos, txtLn6.getX(), txtLn6.getY() + rolagemTela);
		tl7.draw(graficos, txtLn7.getX(), txtLn7.getY() + rolagemTela);
		tl8.draw(graficos, txtLn8.getX(), txtLn8.getY() + rolagemTela);
		tl9.draw(graficos, txtLn9.getX(), txtLn9.getY() + rolagemTela);		
		
		graficos.drawImage(nomeHabilidade1.getImagem(), nomeHabilidade1.getX(), nomeHabilidade1.getY() + rolagemTela, this);
		graficos.drawImage(nomeHabilidade2.getImagem(), nomeHabilidade2.getX(), nomeHabilidade2.getY() + rolagemTela, this);
		graficos.drawImage(nomeHabilidade3.getImagem(), nomeHabilidade3.getX(), nomeHabilidade3.getY() + rolagemTela, this);
		graficos.drawImage(nomeHabilidade4.getImagem(), nomeHabilidade4.getX(), nomeHabilidade4.getY() + rolagemTela, this);
		
		tl10 = new TextLayout(nomeApelo1.getTexto(), nomeApelo1.getFonte(), frc);
	    tl11 = new TextLayout(nomeApelo2.getTexto(), nomeApelo2.getFonte(), frc);
	    tl12 = new TextLayout(nomeApelo3.getTexto(), nomeApelo3.getFonte(), frc);
	    tl13 = new TextLayout(nomeApelo4.getTexto(), nomeApelo4.getFonte(), frc);
	
	    
	    graficos.setColor(contTempoNomeApelo >= 0 && contTempoNomeApelo < 25 ? nomeApelo1.getCorTexto() : nomeApelo2.getCorTexto());
	    tl10.draw(graficos, nomeApelo1.getX(), nomeApelo1.getY() + rolagemTela);
	    graficos.setColor(contTempoNomeApelo >= 25 && contTempoNomeApelo < 50 ? nomeApelo1.getCorTexto() : nomeApelo2.getCorTexto());
	    tl11.draw(graficos, nomeApelo2.getX(), nomeApelo2.getY() + rolagemTela);
	    graficos.setColor(contTempoNomeApelo >= 50 && contTempoNomeApelo < 75 ? nomeApelo1.getCorTexto() : nomeApelo2.getCorTexto());
	    tl12.draw(graficos, nomeApelo3.getX(), nomeApelo3.getY() + rolagemTela);
	    graficos.setColor(contTempoNomeApelo >= 75 ? nomeApelo1.getCorTexto() : nomeApelo2.getCorTexto());
	    tl13.draw(graficos, nomeApelo4.getX(), nomeApelo4.getY() + rolagemTela);
	    graficos.setColor(txtLn1.getCorTexto());
		
		graficos.drawImage(painel1.getImagem(), painel1.getX(), painel1.getY() + rolagemTela, this);

		/* ------------------------------- info apelo ---------------------------------*/
	    
	    tl14 = new TextLayout(txtLn10.getTexto(), txtLn10.getFonte(), frc);
	    tl15 = new TextLayout(txtLn11.getTexto(), txtLn11.getFonte(), frc);
	    tl16 = new TextLayout(txtLn12.getTexto(), txtLn12.getFonte(), frc);
	    tl17 = new TextLayout(txtLn13.getTexto(), txtLn13.getFonte(), frc);
	    tl56 = new TextLayout(txtLn56.getTexto(), txtLn56.getFonte(), frc);
	    
		tl14.draw(graficos, txtLn10.getX(), txtLn10.getY() + rolagemTela);
		tl15.draw(graficos, txtLn11.getX(), txtLn11.getY() + rolagemTela);
		tl16.draw(graficos, txtLn12.getX(), txtLn12.getY() + rolagemTela);
		tl17.draw(graficos, txtLn13.getX(), txtLn13.getY() + rolagemTela);
		tl56.draw(graficos, txtLn56.getX(), txtLn56.getY() + rolagemTela);
		
		graficos.drawImage(apelo.getImagem(), apelo.getX(), apelo.getY() + rolagemTela, this);
		graficos.drawImage(tipoDoApelo.getImagem(), tipoDoApelo.getX(), tipoDoApelo.getY() + rolagemTela, this);
		
	    tl18 = new TextLayout(apeloQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);
	    tl19 = new TextLayout(InterferenciaQuantidade.getTexto(), InterferenciaQuantidade.getFonte(), frc);

	    graficos.setColor(apeloQuantidade.getCorTexto());
	    tl18.draw(graficos, apeloQuantidade.getX(), apeloQuantidade.getY() + rolagemTela);
	    tl19.draw(graficos, InterferenciaQuantidade.getX(), InterferenciaQuantidade.getY() + rolagemTela);
	    graficos.setColor(txtLn1.getCorTexto());
	    
		graficos.drawImage(apeloApelo1.getImagem(), apeloApelo1.getX(), apeloApelo1.getY() + rolagemTela, this);
		graficos.drawImage(apeloApelo2.getImagem(), apeloApelo2.getX(), apeloApelo2.getY() + rolagemTela, this);
		graficos.drawImage(apeloApelo3.getImagem(), apeloApelo3.getX(), apeloApelo3.getY() + rolagemTela, this);
		graficos.drawImage(apeloApelo4.getImagem(), apeloApelo4.getX(), apeloApelo4.getY() + rolagemTela, this);
		graficos.drawImage(apeloApelo5.getImagem(), apeloApelo5.getX(), apeloApelo5.getY() + rolagemTela, this);
		
		graficos.drawImage(apeloInterf1.getImagem(), apeloInterf1.getX(), apeloInterf1.getY() + rolagemTela, this);
		graficos.drawImage(apeloInterf2.getImagem(), apeloInterf2.getX(), apeloInterf2.getY() + rolagemTela, this);
	
		graficos.drawImage(painel2.getImagem(), painel2.getX(), painel2.getY() + rolagemTela, this);

		// ----------------------- Descrição -----------------------------------

		tl20 = new TextLayout(txtLn14.getTexto(), txtLn14.getFonte(), frc);
	    tl21 = new TextLayout(txtLn15.getTexto(), txtLn15.getFonte(), frc);
	    
	    tl20.draw(graficos, txtLn14.getX(), txtLn14.getY() + rolagemTela);
	    tl21.draw(graficos, txtLn15.getX(), txtLn15.getY() + rolagemTela);
	    
		graficos.drawImage(descricao.getImagem(), descricao.getX(), descricao.getY() + rolagemTela, this);

		tl22 = new TextLayout(textoDescricao1.getTexto(), textoDescricao1.getFonte(), frc);
	    tl23 = new TextLayout(textoDescricao2.getTexto(), textoDescricao2.getFonte(), frc);
	    tl24 = new TextLayout(textoDescricao3.getTexto(), textoDescricao3.getFonte(), frc);
	    tl25 = new TextLayout(textoDescricao4.getTexto(), textoDescricao4.getFonte(), frc);
	    tl26 = new TextLayout(textoDescricao5.getTexto(), textoDescricao5.getFonte(), frc);
	    
	    graficos.setColor(textoDescricao1.getCorTexto());
	    tl22.draw(graficos, textoDescricao1.getX(), textoDescricao1.getY() + rolagemTela);
	    tl23.draw(graficos, textoDescricao2.getX(), textoDescricao2.getY() + rolagemTela);
	    tl24.draw(graficos, textoDescricao3.getX(), textoDescricao3.getY() + rolagemTela);
	    tl25.draw(graficos, textoDescricao4.getX(), textoDescricao4.getY() + rolagemTela);
	    graficos.setColor(textoDescricao5.getCorTexto());
	    tl26.draw(graficos, textoDescricao5.getX(), textoDescricao5.getY() + rolagemTela);
	    graficos.setColor(txtLn1.getCorTexto());
	    
		graficos.drawImage(painel3.getImagem(), painel3.getX(), painel3.getY() + rolagemTela, this);
	    
		// ----------------------- campo batalha -----------------------------------

	    tl27 = new TextLayout(txtLn16.getTexto(), txtLn16.getFonte(), frc);
	    tl28 = new TextLayout(txtLn17.getTexto(), txtLn17.getFonte(), frc);
	    tl29 = new TextLayout(txtLn18.getTexto(), txtLn18.getFonte(), frc);
	    tl30 = new TextLayout(txtLn19.getTexto(), txtLn19.getFonte(), frc);
	    tl31 = new TextLayout(txtLn20.getTexto(), txtLn20.getFonte(), frc);
	    tl32 = new TextLayout(txtLn21.getTexto(), txtLn21.getFonte(), frc);
	    tl33 = new TextLayout(txtLn22.getTexto(), txtLn22.getFonte(), frc);
	    tl34 = new TextLayout(txtLn23.getTexto(), txtLn23.getFonte(), frc);
	    tl35 = new TextLayout(txtLn24.getTexto(), txtLn24.getFonte(), frc);
	    tl36 = new TextLayout(txtLn25.getTexto(), txtLn25.getFonte(), frc);
	    tl37 = new TextLayout(txtLn26.getTexto(), txtLn26.getFonte(), frc);

	    tl27.draw(graficos, txtLn16.getX(), txtLn16.getY() + rolagemTela);
	    tl28.draw(graficos, txtLn17.getX(), txtLn17.getY() + rolagemTela);
	    tl29.draw(graficos, txtLn18.getX(), txtLn18.getY() + rolagemTela);
	    tl30.draw(graficos, txtLn19.getX(), txtLn19.getY() + rolagemTela);
	    tl31.draw(graficos, txtLn20.getX(), txtLn20.getY() + rolagemTela);
	    tl32.draw(graficos, txtLn21.getX(), txtLn21.getY() + rolagemTela);
	    tl33.draw(graficos, txtLn22.getX(), txtLn22.getY() + rolagemTela);
	    tl34.draw(graficos, txtLn23.getX(), txtLn23.getY() + rolagemTela);
	    tl35.draw(graficos, txtLn24.getX(), txtLn24.getY() + rolagemTela);
	    tl36.draw(graficos, txtLn25.getX(), txtLn25.getY() + rolagemTela);
	    tl37.draw(graficos, txtLn26.getX(), txtLn26.getY() + rolagemTela);
	    
	    graficos.drawImage(campoBatalha1.getImagem(), campoBatalha1.getX(), campoBatalha1.getY() + rolagemTela, this);
		graficos.drawImage(campoBatalha2.getImagem(), campoBatalha2.getX(), campoBatalha2.getY() + rolagemTela, this);
		graficos.drawImage(campoBatalha3.getImagem(), campoBatalha3.getX(), campoBatalha3.getY() + rolagemTela, this);
		graficos.drawImage(campoBatalha4.getImagem(), campoBatalha4.getX(), campoBatalha4.getY() + rolagemTela, this);
		graficos.drawImage(campoBatalha5.getImagem(), campoBatalha5.getX(), campoBatalha5.getY() + rolagemTela, this);
		
		graficos.drawImage(iconeCampoBatalha1.getImagem(), iconeCampoBatalha1.getX(), iconeCampoBatalha1.getY() + rolagemTela, this);
		graficos.drawImage(iconeCampoBatalha2.getImagem(), iconeCampoBatalha2.getX(), iconeCampoBatalha2.getY() + rolagemTela, this);
		graficos.drawImage(iconeCampoBatalha3.getImagem(), iconeCampoBatalha3.getX(), iconeCampoBatalha3.getY() + rolagemTela, this);
		graficos.drawImage(iconeCampoBatalha4.getImagem(), iconeCampoBatalha4.getX(), iconeCampoBatalha4.getY() + rolagemTela, this);
		graficos.drawImage(iconeCampoBatalha5.getImagem(), iconeCampoBatalha5.getX(), iconeCampoBatalha5.getY() + rolagemTela, this);
		
		tl38 = new TextLayout(txtEfeitoFase.getTexto(), txtEfeitoFase.getFonte(), frc);
	    tl38.draw(graficos, txtEfeitoFase.getX(), txtEfeitoFase.getY() + rolagemTela);
	    graficos.drawImage(efeitoFase.getImagem(), efeitoFase.getX(), efeitoFase.getY() + rolagemTela, this);
		
		graficos.drawImage(painel4.getImagem(), painel4.getX(), painel4.getY() + rolagemTela, this);
		graficos.drawImage(seletorAventureiro.getImagem(), seletorAventureiro.getX(), seletorAventureiro.getY() + rolagemTela, this);
		
		graficos.drawImage(coracao01.getImagem(), coracao01.getX(), coracao01.getY() + rolagemTela, this);
		graficos.drawImage(coracao02.getImagem(), coracao02.getX(), coracao02.getY() + rolagemTela, this);
		graficos.drawImage(coracao03.getImagem(), coracao03.getX(), coracao03.getY() + rolagemTela, this);
		graficos.drawImage(coracao04.getImagem(), coracao04.getX(), coracao04.getY() + rolagemTela, this);
		graficos.drawImage(coracao05.getImagem(), coracao05.getX(), coracao05.getY() + rolagemTela, this);

		graficos.drawImage(coracao11.getImagem(), coracao11.getX(), coracao11.getY() + rolagemTela, this);
		graficos.drawImage(coracao12.getImagem(), coracao12.getX(), coracao12.getY() + rolagemTela, this);
		graficos.drawImage(coracao13.getImagem(), coracao13.getX(), coracao13.getY() + rolagemTela, this);
		graficos.drawImage(coracao14.getImagem(), coracao14.getX(), coracao14.getY() + rolagemTela, this);
		graficos.drawImage(coracao15.getImagem(), coracao15.getX(), coracao15.getY() + rolagemTela, this);
		graficos.drawImage(coracao16.getImagem(), coracao16.getX(), coracao16.getY() + rolagemTela, this);
		graficos.drawImage(coracao17.getImagem(), coracao17.getX(), coracao17.getY() + rolagemTela, this);
		graficos.drawImage(coracao18.getImagem(), coracao18.getX(), coracao18.getY() + rolagemTela, this);
		graficos.drawImage(coracao19.getImagem(), coracao19.getX(), coracao19.getY() + rolagemTela, this);
		graficos.drawImage(coracao110.getImagem(), coracao110.getX(), coracao110.getY() + rolagemTela, this);
		
		graficos.drawImage(coracao21.getImagem(), coracao21.getX(), coracao21.getY() + rolagemTela, this);
		graficos.drawImage(coracao22.getImagem(), coracao22.getX(), coracao22.getY() + rolagemTela, this);
		graficos.drawImage(coracao23.getImagem(), coracao23.getX(), coracao23.getY() + rolagemTela, this);
		graficos.drawImage(coracao24.getImagem(), coracao24.getX(), coracao24.getY() + rolagemTela, this);
		graficos.drawImage(coracao25.getImagem(), coracao25.getX(), coracao25.getY() + rolagemTela, this);
		graficos.drawImage(coracao26.getImagem(), coracao26.getX(), coracao26.getY() + rolagemTela, this);
		graficos.drawImage(coracao27.getImagem(), coracao27.getX(), coracao27.getY() + rolagemTela, this);
		graficos.drawImage(coracao28.getImagem(), coracao28.getX(), coracao28.getY() + rolagemTela, this);
		graficos.drawImage(coracao29.getImagem(), coracao29.getX(), coracao29.getY() + rolagemTela, this);
		graficos.drawImage(coracao210.getImagem(), coracao210.getX(), coracao210.getY() + rolagemTela, this);
		
		graficos.drawImage(coracao31.getImagem(), coracao31.getX(), coracao31.getY() + rolagemTela, this);
		graficos.drawImage(coracao32.getImagem(), coracao32.getX(), coracao32.getY() + rolagemTela, this);
		graficos.drawImage(coracao33.getImagem(), coracao33.getX(), coracao33.getY() + rolagemTela, this);
		graficos.drawImage(coracao34.getImagem(), coracao34.getX(), coracao34.getY() + rolagemTela, this);
		graficos.drawImage(coracao35.getImagem(), coracao35.getX(), coracao35.getY() + rolagemTela, this);
		graficos.drawImage(coracao36.getImagem(), coracao36.getX(), coracao36.getY() + rolagemTela, this);
		graficos.drawImage(coracao37.getImagem(), coracao37.getX(), coracao37.getY() + rolagemTela, this);
		graficos.drawImage(coracao38.getImagem(), coracao38.getX(), coracao38.getY() + rolagemTela, this);
		graficos.drawImage(coracao39.getImagem(), coracao39.getX(), coracao39.getY() + rolagemTela, this);
		graficos.drawImage(coracao310.getImagem(), coracao310.getX(), coracao310.getY() + rolagemTela, this);
		
		graficos.drawImage(coracao41.getImagem(), coracao41.getX(), coracao41.getY() + rolagemTela, this);
		graficos.drawImage(coracao42.getImagem(), coracao42.getX(), coracao42.getY() + rolagemTela, this);
		graficos.drawImage(coracao43.getImagem(), coracao43.getX(), coracao43.getY() + rolagemTela, this);
		graficos.drawImage(coracao44.getImagem(), coracao44.getX(), coracao44.getY() + rolagemTela, this);
		graficos.drawImage(coracao45.getImagem(), coracao45.getX(), coracao45.getY() + rolagemTela, this);
		graficos.drawImage(coracao46.getImagem(), coracao46.getX(), coracao46.getY() + rolagemTela, this);
		graficos.drawImage(coracao47.getImagem(), coracao47.getX(), coracao47.getY() + rolagemTela, this);
		graficos.drawImage(coracao48.getImagem(), coracao48.getX(), coracao48.getY() + rolagemTela, this);
		graficos.drawImage(coracao49.getImagem(), coracao49.getX(), coracao49.getY() + rolagemTela, this);
		graficos.drawImage(coracao410.getImagem(), coracao410.getX(), coracao410.getY() + rolagemTela, this);
		
		graficos.drawImage(coracao51.getImagem(), coracao51.getX(), coracao51.getY() + rolagemTela, this);
		graficos.drawImage(coracao52.getImagem(), coracao52.getX(), coracao52.getY() + rolagemTela, this);
		graficos.drawImage(coracao53.getImagem(), coracao53.getX(), coracao53.getY() + rolagemTela, this);
		graficos.drawImage(coracao54.getImagem(), coracao54.getX(), coracao54.getY() + rolagemTela, this);
		graficos.drawImage(coracao55.getImagem(), coracao55.getX(), coracao55.getY() + rolagemTela, this);
		graficos.drawImage(coracao56.getImagem(), coracao56.getX(), coracao56.getY() + rolagemTela, this);
		graficos.drawImage(coracao57.getImagem(), coracao57.getX(), coracao57.getY() + rolagemTela, this);
		graficos.drawImage(coracao58.getImagem(), coracao58.getX(), coracao58.getY() + rolagemTela, this);
		graficos.drawImage(coracao59.getImagem(), coracao59.getX(), coracao59.getY() + rolagemTela, this);
		graficos.drawImage(coracao510.getImagem(), coracao510.getX(), coracao510.getY() + rolagemTela, this);
		
		graficos.drawImage(luzCampoBatalha1.getImagem(), luzCampoBatalha1.getX(), luzCampoBatalha1.getY() + rolagemTela, this);
		graficos.drawImage(luzCampoBatalha2.getImagem(), luzCampoBatalha2.getX(), luzCampoBatalha2.getY() + rolagemTela, this);
		graficos.drawImage(luzCampoBatalha3.getImagem(), luzCampoBatalha3.getX(), luzCampoBatalha3.getY() + rolagemTela, this);
		graficos.drawImage(luzCampoBatalha4.getImagem(), luzCampoBatalha4.getX(), luzCampoBatalha4.getY() + rolagemTela, this);
		graficos.drawImage(luzCampoBatalha5.getImagem(), luzCampoBatalha5.getX(), luzCampoBatalha5.getY() + rolagemTela, this);
		
		// ----------------------- Tabela de efeito de chefe -----------------------------------

		tl39 = new TextLayout(txtLn27.getTexto(), txtLn27.getFonte(), frc);
	    tl40 = new TextLayout(txtLn28.getTexto(), txtLn28.getFonte(), frc);
	    tl41 = new TextLayout(txtLn29.getTexto(), txtLn29.getFonte(), frc);
	    tl42 = new TextLayout(txtLn30.getTexto(), txtLn30.getFonte(), frc);
	    tl43 = new TextLayout(txtLn31.getTexto(), txtLn31.getFonte(), frc);
	    tl44 = new TextLayout(txtLn32.getTexto(), txtLn32.getFonte(), frc);
	    tl45 = new TextLayout(txtLn33.getTexto(), txtLn33.getFonte(), frc);
	    tl46 = new TextLayout(txtLn34.getTexto(), txtLn34.getFonte(), frc);
	    tl47 = new TextLayout(txtLn35.getTexto(), txtLn35.getFonte(), frc);
	    tl48 = new TextLayout(txtLn36.getTexto(), txtLn36.getFonte(), frc);
	    tl49 = new TextLayout(txtLn37.getTexto(), txtLn37.getFonte(), frc);
	    tl50 = new TextLayout(txtLn38.getTexto(), txtLn38.getFonte(), frc);
	    tl53 = new TextLayout(txtLn41.getTexto(), txtLn41.getFonte(), frc);
	    tl54 = new TextLayout(txtLn42.getTexto(), txtLn42.getFonte(), frc);
	    tl55 = new TextLayout(txtLn43.getTexto(), txtLn43.getFonte(), frc);

	    tl39.draw(graficos, txtLn27.getX(), txtLn27.getY() + rolagemTela);
	    tl40.draw(graficos, txtLn28.getX(), txtLn28.getY() + rolagemTela);
	    tl41.draw(graficos, txtLn29.getX(), txtLn29.getY() + rolagemTela);
	    tl42.draw(graficos, txtLn30.getX(), txtLn30.getY() + rolagemTela);
	    tl43.draw(graficos, txtLn31.getX(), txtLn31.getY() + rolagemTela);
	    tl44.draw(graficos, txtLn32.getX(), txtLn32.getY() + rolagemTela);
	    tl45.draw(graficos, txtLn33.getX(), txtLn33.getY() + rolagemTela);
	    tl46.draw(graficos, txtLn34.getX(), txtLn34.getY() + rolagemTela);
	    tl47.draw(graficos, txtLn35.getX(), txtLn35.getY() + rolagemTela);
	    tl48.draw(graficos, txtLn36.getX(), txtLn36.getY() + rolagemTela);
	    tl49.draw(graficos, txtLn37.getX(), txtLn37.getY() + rolagemTela);
	    tl50.draw(graficos, txtLn38.getX(), txtLn38.getY() + rolagemTela);
	    tl53.draw(graficos, txtLn41.getX(), txtLn41.getY() + rolagemTela);
	    tl54.draw(graficos, txtLn42.getX(), txtLn42.getY() + rolagemTela);
	    tl55.draw(graficos, txtLn43.getX(), txtLn43.getY() + rolagemTela);


		// ----------------------- Considerações finais -----------------------------------

	    tl51 = new TextLayout(txtLn39.getTexto(), txtLn39.getFonte(), frc);
	    tl52 = new TextLayout(txtLn40.getTexto(), txtLn40.getFonte(), frc);
		
	    tl51.draw(graficos, txtLn39.getX(), txtLn39.getY() + rolagemTela);
	    tl52.draw(graficos, txtLn40.getX(), txtLn40.getY() + rolagemTela);
	    
		/* ------------------------------- Barra de rolagem ---------------------------------*/

		graficos.drawImage(bntBarraBaixo.getImagem(), bntBarraBaixo.getX(), bntBarraBaixo.getY(), this);
		graficos.drawImage(bntBarraCima.getImagem(), bntBarraCima.getX(), bntBarraCima.getY(), this);
		
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getX(), engrenagem1.getY(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getX(), engrenagem2.getY(), this);
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		AnimarControles();
		AnimarNomeApelo();
		AnimarInfoApelo();
		AnimarDescricao();
		AnimarCampoBatalha();
		
		repaint();
		
	}
}
