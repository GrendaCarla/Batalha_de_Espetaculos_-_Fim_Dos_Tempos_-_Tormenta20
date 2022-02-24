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
	
	// ------------------------------------ fundo contorno --------------------------------------

	private Icones_interativos fundo = new Icones_interativos(-20, 10);
	private Icones_interativos tapaResto = new Icones_interativos(1234/2 - 4936/2, 640/2 - 2560/2);

	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1120, -12);
	private Icones_interativos contorno = new Icones_interativos(0, 0);
			
	private int contEngranagem1 = 1;
	private boolean contEngranagem2;
	
	private String caminho; 
	
	private Timer timer;
	
	private TextLayout tl2, tl3, tl4, tl5, tl7, tl8, tl9, tl10, tl11, tl12, tl13,
	tl14,  tl15, tl16, tl17, tl18, tl19, tl20, tl21, tl22, tl23, tl24, tl25, tl26, tl27,
	tl29, tl31, tl32, tl33, tl34,  tl35, tl36, tl37, tl38, tl40, tl41, tl42, tl43,
	tl44, tl45, tl46, tl47, tl48, tl49, tl50, tl51, tl52, tl53, tl54, tl55, tl56;
	
	/* ------------------------------- Barra de rolagem ---------------------------------*/
	
	private int rolagemTela = 0;
	
	private Icones_interativos bntBarraBaixo = new Icones_interativos(1183, 590);
	private Icones_interativos bntBarraCima = new Icones_interativos(1183, 32);
	
	/* ------------------------------- Controles ---------------------------------*/
	
	private Icones_interativos teclaEsquerda = new Icones_interativos(290, 140);
	private Icones_interativos teclaBaixo = new Icones_interativos(teclaEsquerda.getX() + 40, teclaEsquerda.getY() + 46);
	private Icones_interativos teclaDireita = new Icones_interativos(teclaBaixo.getX() + 46, teclaEsquerda.getY());
	private Icones_interativos teclaCima = new Icones_interativos(teclaBaixo.getX(), teclaBaixo.getY() - 86);
	private Icones_interativos teclaZ = new Icones_interativos(teclaDireita.getX() + 170, teclaDireita.getY());
	private Icones_interativos teclaX = new Icones_interativos(teclaZ.getX() + 170, teclaZ.getY());
	private Icones_interativos teclaEsc = new Icones_interativos(teclaX.getX() + 170, teclaX.getY());

	
	private Icones_interativos controles = new Icones_interativos(92, 60);
	private Texto txtLn2 = new Texto(290, 248, "Direcionais");
	private Texto txtLn3 = new Texto(txtLn2.getX() + 212, txtLn2.getY() - 38, "Selecionar");
	private Texto txtLn4 = new Texto(txtLn3.getX() + 196, txtLn3.getY(), "Voltar");
	private Texto txtLn5 = new Texto(txtLn4.getX() + 184, txtLn4.getY(), "Menu");
	
	private int contTempoControles = 0;
	
	private int espacoParagrafo = 56;
	private int espacoLinha = 30;
	/* ------------------------------- Batalha ---------------------------------*/
	
	private Icones_interativos batalhas = new Icones_interativos(92, 300);
	private Texto txtLn7 = new Texto(90, batalhas.getY() + 110, "1. As batalhas são divididas em cinco turnos.");
	private Texto txtLn8 = new Texto(txtLn7.getX(), txtLn7.getY() + espacoParagrafo, "2. Em cada turno você escolhe uma habilidade entre quatro.");
	
		/* ------------------------------- Nome apelo ---------------------------------*/

	private Icones_interativos nomeHabilidade1 = new Icones_interativos(1234 - 330 - 80, batalhas.getY() + 30);
	private Icones_interativos nomeHabilidade2 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade1.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade3 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade2.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade4 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade3.getY() + 54 + 4);
	
	private int contTempoNomeApelo = 0;
	private Texto nomeApelo1, nomeApelo2, nomeApelo3, nomeApelo4;
	
	private Icones_interativos painel1 = new Icones_interativos(nomeHabilidade1.getX() - 2, nomeHabilidade1.getY() - 8);
	private Icones_interativos fundoPainel1 = new Icones_interativos(painel1.getX() - 13, painel1.getY() - 9);

		/* ------------------------------- info apelo ---------------------------------*/

	private Texto txtLn9 = new Texto(txtLn7.getX(), 610, "3. Os apelos      são pontos positivos ganhados pelas apresentações.");
	private Icones_interativos apelo1 = new Icones_interativos(txtLn7.getX() + 134, 595);
	private Texto txtLn10 = new Texto(txtLn7.getX(), txtLn9.getY() + espacoParagrafo, "4. Quem obtem mais apelo no turno fica em 1º lugar no próximo e quem obtem mais até o final da");
	private Texto txtLn11 = new Texto(txtLn7.getX() + 26, txtLn10.getY() + espacoLinha, "batalha ganha.");

	private Texto txtLn12 = new Texto(txtLn7.getX(), txtLn11.getY() + espacoParagrafo, "5. As interferencias      são pontos negativos usados nos adversários para retirar os apelos ganhados");
	private Icones_interativos apelo2 = new Icones_interativos(txtLn7.getX() + 206, txtLn11.getY() + espacoParagrafo - 17);
	private Texto txtLn13 = new Texto(txtLn7.getX() + 26, txtLn12.getY() + espacoLinha, "durante toda a batalha.");

	private Texto txtLn56 = new Texto(txtLn7.getX(), txtLn13.getY() + espacoParagrafo, "6. A luz acesa com o símbolo de um punho informa que a habilidade é do tipo físico.");

	private Icones_interativos apelo = new Icones_interativos(1234/2 - 860/2, 880);

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
	private Icones_interativos fundoPainel2 = new Icones_interativos(painel2.getX() - 15, painel2.getY() - 15);

	
	private int contTempoInfoApelo = 0;
	
		// ----------------------- Descrição -----------------------------------

	private Texto txtLn14 = new Texto(txtLn7.getX(), 1010, "7. A descrição da habilidade é puramente enrrolação. O importante é a ultima linha colorida, que diz");
	private Texto txtLn15 = new Texto(txtLn7.getX() + 26, txtLn14.getY() + espacoLinha, "o efeito da interferencia.");	
	
	private Icones_interativos descricao = new Icones_interativos(1234/2 - 860/2, txtLn15.getY() + 40);
	
	private Texto textoDescricao1, textoDescricao2, textoDescricao3, textoDescricao4, textoDescricao5;
	
	private int contTempoDescricao = 1;
	private String conteudoDescricao = "O que é capaz de vencer o mais forte dos seres"
			+ "Do que uma mentirinha bem contada com um bocado de enganação?"
			+ "Com uma mente afiada e planejamentos condizentes" 
			+ "Até uma fadinha bem charmosa irá ter sua ascensão."
			+ "Esta habilidade afeta o primeiro campo.";
	
	private Icones_interativos painel3 = new Icones_interativos(descricao.getX(), descricao.getY());
	private Icones_interativos fundoPainel3 = new Icones_interativos(painel3.getX() - 15, painel3.getY() - 15);

	// ----------------------------------- campo batalha -----------------------------------

	private Texto txtLn16 = new Texto(txtLn7.getX(), 1340, "8. Quando a habilidade for escolhida a batalha começa.");	
	private Texto txtLn18 = new Texto(txtLn7.getX(), txtLn16.getY() + espacoParagrafo, "9. A animação aparece seguida da quantidade de apelo.");
	private Texto txtLn20 = new Texto(txtLn7.getX(), txtLn18.getY() + espacoParagrafo, "10. Se repetir a habilidade duas vezes seguida,");
	private Texto txtLn21 = new Texto(txtLn7.getX() + 40, txtLn20.getY() + espacoLinha, "receberá uma punição de -2     .");	
	private Icones_interativos apelo3 = new Icones_interativos(txtLn7.getX() + 332, txtLn20.getY() + espacoLinha - 16);
	private Texto txtLn22 = new Texto(txtLn7.getX(), txtLn21.getY() + espacoParagrafo, "11. Se a habilidade cumprir o efeito de chefe da");
	private Texto txtLn23 = new Texto(txtLn7.getX() + 40, txtLn22.getY() + espacoLinha, "batalha, receberá um +1     ou um -1     de");
	private Icones_interativos apelo4 = new Icones_interativos(txtLn7.getX() + 290, txtLn22.getY() + espacoLinha - 16);
	private Icones_interativos apelo5 = new Icones_interativos(txtLn7.getX() + 412, txtLn22.getY() + espacoLinha - 16);
	private Texto txtLn24 = new Texto(txtLn7.getX() + 40, txtLn23.getY() + espacoLinha, "acordo com o efeito.");
	private Texto txtLn25 = new Texto(txtLn7.getX(), txtLn24.getY() + espacoParagrafo, "12. Caso a habilidade tenha interferência ela aparecerá");
	private Texto txtLn26 = new Texto(txtLn7.getX() + 40, txtLn25.getY() + espacoLinha, "por último na vez do aventureiro que a lançou.");
	
	private Icones_interativos campoBatalha1 = new Icones_interativos(1234 - 430 - 80, txtLn16.getY() - 14);
	private Icones_interativos campoBatalha2 = new Icones_interativos(campoBatalha1.getX(), campoBatalha1.getY() + 70 + 4);
	private Icones_interativos campoBatalha3 = new Icones_interativos(campoBatalha1.getX(), campoBatalha2.getY() + 70 + 4);
	private Icones_interativos campoBatalha4 = new Icones_interativos(campoBatalha1.getX(), campoBatalha3.getY() + 70 + 4);
	private Icones_interativos campoBatalha5 = new Icones_interativos(campoBatalha1.getX(), campoBatalha4.getY() + 70 + 4);
	
	private Texto txtEfeitoFase = new Texto(campoBatalha1.getX() - 74, campoBatalha3.getY() + 70/2 + 20/2, " ");
	private Icones_interativos efeitoFase = new Icones_interativos(txtEfeitoFase.getX() + 34, txtEfeitoFase.getY() - 17);
	
	private Icones_interativos iconeCampoBatalha1 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha1.getY() + 3);
	private Icones_interativos iconeCampoBatalha2 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha2.getY() + 3);
	private Icones_interativos iconeCampoBatalha3 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha3.getY() + 3);
	private Icones_interativos iconeCampoBatalha4 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha4.getY() + 3);
	private Icones_interativos iconeCampoBatalha5 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha5.getY() + 3);
	
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
	private Icones_interativos fundoPainel4 = new Icones_interativos(painel4.getX() - 15, painel4.getY() - 15);

	private int contTempoCampoBatalha = 0;
	
	// ----------------------- Considerações finais -----------------------------------

	private Texto txtLn39 = new Texto(txtLn7.getX(), txtLn26.getY() + espacoParagrafo, "13. Depois de cada turno os campos se reorganiza ficando em ordem decrescente de acordo com os");
	private Texto txtLn40 = new Texto(txtLn7.getX() + 40, txtLn39.getY() + espacoLinha, "pontos. No final do 5º turno a batalha acaba e o aventureiro com mais pontos ganha.");
	
	// ----------------------- Tabela de efeito de chefe -----------------------------------

	private Icones_interativos efeitoDeChefe = new Icones_interativos(1234/2 - 300/2, txtLn40.getY() + 66);

	private Icones_interativos tabela = new Icones_interativos(1234/2 - 620/2, efeitoDeChefe.getY() + 70);
	
	private Texto txtLn41 = new Texto(332, efeitoDeChefe.getY() + 106, "       ||=============================================||");
	private Texto txtLn28 = new Texto(txtLn41.getX() - 2, txtLn41.getY() + 30, "||===||=============================================||===||");
	private Texto txtLn29 = new Texto(txtLn28.getX(), txtLn28.getY() + 25, "||      ||      IGNIS      ||   -1   ||   Em habilidades com interferência    ||      ||");
	private Texto txtLn30 = new Texto(txtLn28.getX(), txtLn29.getY() + 25, "||      || ----------------- || ----._ || ------------------------------------------------- ||      ||");
	private Texto txtLn31 = new Texto(txtLn28.getX(), txtLn30.getY() + 25, "||      ||_     AYLA    _||   -1   ||   Em habilidades sem interferência    ||      ||");
	private Texto txtLn32 = new Texto(txtLn28.getX(), txtLn31.getY() + 25, "||      || ----------------- || ----._ || ------------------------------------------------- ||      ||");
	private Texto txtLn33 = new Texto(txtLn28.getX(), txtLn32.getY() + 25, "||      ||.  REXTHOR  ||   +1  ||   Em habilidades físicas                     ||      ||");
	private Texto txtLn34 = new Texto(txtLn28.getX(), txtLn33.getY() + 25, "||      || ----------------- || ----._ || ------------------------------------------------- ||      ||");
	private Texto txtLn35 = new Texto(txtLn28.getX(), txtLn34.getY() + 25, "||      ||_      KIKI       ||   +1  ||   Em habilidades com interferência    ||      ||");
	private Texto txtLn36 = new Texto(txtLn28.getX(), txtLn35.getY() + 25, "||      || ----------------- || ----._ || ------------------------------------------------- ||      ||");
	private Texto txtLn37 = new Texto(txtLn28.getX(), txtLn36.getY() + 25, "||      ||.     ARIUS     ||   +1  ||   Em habilidades sem interferência    ||      ||");
	private Texto txtLn38 = new Texto(txtLn28.getX(), txtLn37.getY() + 28, "||===||=============================================||===||");
	private Texto txtLn42 = new Texto(txtLn28.getX() + 4, txtLn38.getY() + 28, "       ||=============================================||");
	private Texto txtLn43 = new Texto(txtLn28.getX(), txtLn42.getY() + 38, "    __||__||                                                                                ||__||__");

	
	
	public Manual (boolean Engrenagem2, String Caminho) {
		contEngranagem2 = Engrenagem2;
		this.caminho = Caminho;
		
		fundo.load(caminho + "res\\Manual\\fundo.png");
		tapaResto.load(caminho + "res\\fundo2.png");
		
		engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem1.png");
		
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");

		contorno.load(caminho + "res\\contorno.png");
		
		/* ------------------------------- Barra de rolagem ---------------------------------*/

		bntBarraBaixo.load(caminho + "res\\Manual\\teclaBaixo1.png");
		bntBarraCima.load(caminho + "res\\Manual\\teclaCima1.png");
		
		/* ------------------------------- Controles ---------------------------------*/
		
		teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda1.png");
		teclaDireita.load(caminho + "res\\Teclado\\setaDireita1.png");
		teclaCima.load(caminho + "res\\Teclado\\setaCima1.png");
		teclaBaixo.load(caminho + "res\\Teclado\\setaBaixo1.png");
		
		teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
		teclaX.load(caminho + "res\\Teclado\\teclaX1.png");
		teclaEsc.load(caminho + "res\\Teclado\\teclaEsc1.png");
		
		controles.load(caminho + "res\\Manual\\controles.png");
		txtLn2.setFonte(new Font("Arial", Font.BOLD, 24));
		txtLn2.setCorTexto(new Color (235, 230, 233));
		
		/* ------------------------------- Batalha ---------------------------------*/
		
		txtLn7.setFonte(new Font("Arial", Font.BOLD, 22));
		
		nomeHabilidade1.load(caminho + "res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		nomeHabilidade2.load(caminho + "res\\batalha\\nomeHabilidade.png");
		nomeHabilidade3.load(caminho + "res\\batalha\\nomeHabilidade.png");
		nomeHabilidade4.load(caminho + "res\\batalha\\nomeHabilidade.png");
		
		nomeApelo1 = new Texto(nomeHabilidade1.getX() + 20, nomeHabilidade1.getY() + 54/2 + 8, "Tapa em puristas");
		nomeApelo2 = new Texto(nomeHabilidade2.getX() + 20, nomeHabilidade2.getY() + 54/2 + 8, "Ignis bonitão");
		nomeApelo3 = new Texto(nomeHabilidade3.getX() + 20, nomeHabilidade3.getY() + 54/2 + 8, "Provocação petulante");
		nomeApelo4 = new Texto(nomeHabilidade4.getX() + 20, nomeHabilidade4.getY() + 54/2 + 8, "Meca-Rito");
		
		nomeApelo1.setFonte(new Font("Arial", Font.PLAIN, 20));
		nomeApelo1.setCorTexto(new Color (255, 60, 0));
		nomeApelo2.setCorTexto(new Color (235, 148, 150));
		
		batalhas.load(caminho + "res\\manual\\batalhas.png");
		
		painel1.load(caminho + "res\\batalha\\painel1.png");
		fundoPainel1.load(caminho + "res\\manual\\fundoPainel1.png");
		
		/* ------------------------------- info apelo ---------------------------------*/
		
		apelo1.load(caminho + "res\\batalha\\apelo.png");
		apelo2.load(caminho + "res\\batalha\\interferencia.png");
		
		apelo.load(caminho + "res\\batalha\\infoApelo.png");

		apeloQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		apeloQuantidade.setCorTexto(new Color (240, 148, 150));
		
		tipoDoApelo.load(caminho + "res\\batalha\\tipoDoApelo1.png");
		
		apeloApelo1.load(caminho + "res\\batalha\\apelo.png");
		apeloApelo2.load(caminho + "res\\batalha\\apelo.png");
		apeloApelo3.load(caminho + "res\\batalha\\apelo.png");
		apeloApelo4.load(caminho + "res\\batalha\\apelo.png");
		apeloApelo5.load(caminho + "res\\batalha\\apelo.png");
		
		apeloInterf1.load(caminho + "res\\batalha\\interferencia.png");
		apeloInterf2.load(caminho + "res\\batalha\\interferencia.png");
		
		painel2.load(caminho + "res\\batalha\\painel2.png");
		fundoPainel2.load(caminho + "res\\manual\\fundoPainel2.png");
		
		// ----------------------- Descrição -----------------------------------

		descricao.load(caminho + "res\\batalha\\descricao.png");

		textoDescricao1 = new Texto(descricao.getX() + 25, descricao.getY() + 36, " ");
		textoDescricao2 = new Texto(textoDescricao1.getX(), textoDescricao1.getY() + 28, " ");
		textoDescricao3 = new Texto(textoDescricao2.getX(), textoDescricao2.getY() + 28, " ");
		textoDescricao4 = new Texto(textoDescricao3.getX(), textoDescricao3.getY() + 28, " ");
		textoDescricao5 = new Texto(textoDescricao4.getX() + 238, textoDescricao4.getY() + 28, "Esta habilidade afeta o primeiro campo.");

		textoDescricao1.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		textoDescricao1.setCorTexto(new Color (239, 182, 202));
		textoDescricao2.setCorTexto(textoDescricao1.getCorTexto());
		textoDescricao3.setCorTexto(textoDescricao1.getCorTexto());
		textoDescricao4.setCorTexto(textoDescricao1.getCorTexto()); 
		textoDescricao5.setCorTexto(new Color (165, 1, 67));
		
		painel3.load(caminho + "res\\batalha\\painel3.png");
		fundoPainel3.load(caminho + "res\\manual\\fundoPainel3.png");
		
		// ----------------------------------- campo batalha -----------------------------------
		
		apelo3.load(caminho + "res\\batalha\\apelo.png");
		apelo4.load(caminho + "res\\batalha\\apelo.png");
		apelo5.load(caminho + "res\\batalha\\apelo.png");
		
		campoBatalha1.load(caminho + "res\\batalha\\campoBatalha.png");
		campoBatalha2.load(caminho + "res\\batalha\\campoBatalha.png");
		campoBatalha3.load(caminho + "res\\batalha\\campoBatalha.png");
		campoBatalha4.load(caminho + "res\\batalha\\campoBatalha.png");
		campoBatalha5.load(caminho + "res\\batalha\\campoBatalha.png");
		
		luzCampoBatalha1.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha2.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha3.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha4.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha5.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		
		efeitoFase.setImagem(null);
		
		iconeCampoBatalha1.load(caminho + "res\\batalha\\Ignis\\iconeCampoBatalha2.png");
		iconeCampoBatalha2.load(caminho + "res\\batalha\\Ayla\\iconeCampoBatalha1.png");
		iconeCampoBatalha3.load(caminho + "res\\batalha\\Rexthor\\iconeCampoBatalha1.png");
		iconeCampoBatalha4.load(caminho + "res\\batalha\\Kiki\\iconeCampoBatalha1.png");
		iconeCampoBatalha5.load(caminho + "res\\batalha\\Arius\\iconeCampoBatalha1.png");
				
		coracao01.load(caminho + "res\\batalha\\apelo.png");
		coracao02.load(caminho + "res\\batalha\\apelo.png");
		coracao03.load(caminho + "res\\batalha\\apelo.png");
		coracao04.load(caminho + "res\\batalha\\apelo.png");
		coracao05.load(caminho + "res\\batalha\\apelo.png");
		
		painel4.load(caminho + "res\\batalha\\painel4.png");
		fundoPainel4.load(caminho + "res\\manual\\fundoPainel4.png");
		
		apagarCoracoes();
		
		// ----------------------- Tabela de efeito de chefe -----------------------------------

		efeitoDeChefe.load(caminho + "res\\Manual\\efeitoDeChefe.png");
		tabela.load(caminho + "res\\Manual\\tabela.png");
		
		txtLn28.setFonte(new Font("Arial", Font.PLAIN, 17));
		
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
			teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda1.png");
			teclaDireita.load(caminho + "res\\Teclado\\setaDireita1.png");
			teclaCima.load(caminho + "res\\Teclado\\setaCima1.png");
			teclaBaixo.load(caminho + "res\\Teclado\\setaBaixo1.png");
			teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
			teclaX.load(caminho + "res\\Teclado\\teclaX1.png");
			teclaEsc.load(caminho + "res\\Teclado\\teclaEsc1.png");
		}
		
		if(contTempoControles == 15) {
			teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda2.png");
		} else if(contTempoControles == 30) {
			teclaDireita.load(caminho + "res\\Teclado\\setaDireita2.png");
		} else if(contTempoControles == 45) {
			teclaCima.load(caminho + "res\\Teclado\\setaCima2.png");
		} else if(contTempoControles == 60) {
			teclaBaixo.load(caminho + "res\\Teclado\\setaBaixo2.png");
		} else if(contTempoControles == 75) {
			teclaZ.load(caminho + "res\\Teclado\\teclaZ2.png");
		} else if(contTempoControles == 90) {
			teclaX.load(caminho + "res\\Teclado\\teclaX2.png");
		} else if(contTempoControles == 105) {
			teclaEsc.load(caminho + "res\\Teclado\\teclaEsc2.png");
		}
		
	}
	
	public void AnimarNomeApelo() {
		contTempoNomeApelo ++;
		
		if(contTempoNomeApelo == 100) {
			contTempoNomeApelo = 0;
		}
		
		if(contTempoNomeApelo == 0 || contTempoNomeApelo == 25 || contTempoNomeApelo == 50 || contTempoNomeApelo == 75) {
			nomeHabilidade1.load(caminho + "res\\batalha\\nomeHabilidade.png");
			nomeHabilidade2.load(caminho + "res\\batalha\\nomeHabilidade.png");
			nomeHabilidade3.load(caminho + "res\\batalha\\nomeHabilidade.png");
			nomeHabilidade4.load(caminho + "res\\batalha\\nomeHabilidade.png");
		}
		
		if(contTempoNomeApelo == 0) {
			nomeHabilidade1.load(caminho + "res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 25) {
			nomeHabilidade2.load(caminho + "res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 50) {
			nomeHabilidade3.load(caminho + "res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 75) {
			nomeHabilidade4.load(caminho + "res\\batalha\\Ignis\\nomeHabilidadeSelecionado.png");
		}
		
	}
	
	public void AnimarInfoApelo() {
		contTempoInfoApelo ++;
		
		if(contTempoInfoApelo == 80) {
			contTempoInfoApelo = 0;
		}
		
		
		if(contTempoInfoApelo == 0 || contTempoInfoApelo == 40 || contTempoInfoApelo == 80) {
			tipoDoApelo.load(caminho + "res\\batalha\\tipoDoApelo1.png");

			apeloApelo1.load(caminho + "res\\batalha\\apelo.png");
			apeloApelo2.load(caminho + "res\\batalha\\apelo.png");
			apeloApelo3.load(caminho + "res\\batalha\\apelo.png");
			apeloApelo4.load(caminho + "res\\batalha\\apelo.png");
			apeloApelo5.load(caminho + "res\\batalha\\apelo.png");
			
			apeloInterf1.load(caminho + "res\\batalha\\interferencia.png");
			apeloInterf2.load(caminho + "res\\batalha\\interferencia.png");
		}
		

		if(contTempoInfoApelo == 20) {
			tipoDoApelo.load(caminho + "res\\batalha\\tipoDoApelo2.png");

			apeloApelo1.load(caminho + "res\\batalha\\apelo.png");
			apeloApelo2.setImagem(null);
			apeloApelo3.load(caminho + "res\\batalha\\apelo.png");
			apeloApelo4.setImagem(null);
			apeloApelo5.load(caminho + "res\\batalha\\apelo.png");
			
			apeloInterf1.load(caminho + "res\\batalha\\interferencia.png");
			apeloInterf2.setImagem(null);
		}
		
		if(contTempoInfoApelo == 60) {
			tipoDoApelo.load(caminho + "res\\batalha\\tipoDoApelo2.png");

			apeloApelo1.setImagem(null);
			apeloApelo2.load(caminho + "res\\batalha\\apelo.png");
			apeloApelo3.setImagem(null);
			apeloApelo4.load(caminho + "res\\batalha\\apelo.png");
			apeloApelo5.setImagem(null);
			
			apeloInterf1.setImagem(null); 
			apeloInterf2.load(caminho + "res\\batalha\\interferencia.png");
		}
		
	}
	
	public void AnimarDescricao() {
		contTempoDescricao ++;
		
		if(contTempoDescricao == 420) {
			contTempoDescricao = -18;
			textoDescricao1.setTexto(" ");
			textoDescricao2.setTexto(" ");
			textoDescricao3.setTexto(" ");
			textoDescricao4.setTexto(" ");
		}
		
		if(contTempoDescricao > 0 && contTempoDescricao < 47) {
			textoDescricao1.setTexto(conteudoDescricao.substring(0, contTempoDescricao));
		} else if(contTempoDescricao > 0 && contTempoDescricao < 108) {
			textoDescricao2.setTexto(conteudoDescricao.substring(46, contTempoDescricao));
		} else if(contTempoDescricao > 0 && contTempoDescricao < 156) {
			textoDescricao3.setTexto(conteudoDescricao.substring(107, contTempoDescricao));
		} else if(contTempoDescricao > 0 && contTempoDescricao < 206) {
			textoDescricao4.setTexto(conteudoDescricao.substring(155, contTempoDescricao));
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
			luzCampoBatalha1.load(caminho + "res\\batalha\\Ignis\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 100 && contTempoCampoBatalha < 200) {
			luzCampoBatalha1.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha2.load(caminho + "res\\batalha\\Ayla\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 300 && contTempoCampoBatalha < 400) {
			luzCampoBatalha2.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha3.load(caminho + "res\\batalha\\Rexthor\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 400 && contTempoCampoBatalha < 500) {
			luzCampoBatalha3.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha4.load(caminho + "res\\batalha\\Kiki\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 500 && contTempoCampoBatalha < 600) {
			luzCampoBatalha4.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha5.load(caminho + "res\\batalha\\Arius\\luzCampoBatalha2.png");
		} else if(contTempoCampoBatalha > 700) {
			luzCampoBatalha1.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha2.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha3.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha4.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
			luzCampoBatalha5.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		}
		
		for(int i=1; i<6; i++) {
			if(contTempoCampoBatalha == i * 10) {
				(i == 1 ? coracao11 : coracao12).load(caminho + "res\\batalha\\apelo.png");
			} 
		}
		
		for(int i=1; i<7; i++) {
			if(contTempoCampoBatalha == i * 10 + 100) {
				(i == 1 ? coracao21 : (i == 2 ? coracao22 : (i == 3 ? coracao23 : (i == 4 ? coracao24 : coracao25)))).load(caminho + "res\\batalha\\apelo.png");
			} 
		}
		
		if(contTempoCampoBatalha == 190) {
			txtEfeitoFase.setY(campoBatalha2.getY() + 70/2 + 20/2);
			efeitoFase.setY(txtEfeitoFase.getY() - 17);
			txtEfeitoFase.setTexto("- 2");
			efeitoFase.load(caminho + "res\\batalha\\apelo.png");
			
		} else if(contTempoCampoBatalha == 240) {
			txtEfeitoFase.setTexto(" ");
			efeitoFase.setImagem(null);
		}
		

		for(int i=5; i<7; i++) {
			if(contTempoCampoBatalha == i * 10 + 200) {
				(i == 5 ? coracao25 : coracao24).load(caminho + "res\\batalha\\losango.png");
			} 
		}
		
		for(int i=1; i<6; i++) {
			if(contTempoCampoBatalha == i * 10 + 300) {
				(i == 1 ? coracao31 : (i == 2 ? coracao32 : (i == 3 ? coracao33 : coracao34))).load(caminho + "res\\batalha\\apelo.png");
			} 
		}
		
		for(int i=1; i<7; i++) {
			if(contTempoCampoBatalha == i * 10 + 400) {
				(i == 1 ? coracao41 : (i == 2 ? coracao42 : (i == 3 ? coracao43 : coracao44))).load(caminho + "res\\batalha\\apelo.png");
			} 
		}
		
		if(contTempoCampoBatalha == 450) {
			txtEfeitoFase.setY(campoBatalha4.getY() + 70/2 + 20/2);
			efeitoFase.setY(txtEfeitoFase.getY() - 17);
			txtEfeitoFase.setTexto("+ 1");
			efeitoFase.load(caminho + "res\\batalha\\apelo.png");
			
		} else if(contTempoCampoBatalha == 480) {
			txtEfeitoFase.setTexto(" ");
			efeitoFase.setImagem(null);
		}

		if(contTempoCampoBatalha == 490) {
			coracao45.load(caminho + "res\\batalha\\apelo.png");
		} 
		
		for(int i=1; i<6; i++) {
			if(contTempoCampoBatalha == i * 10 + 500) {
				(i == 1 ? coracao51 : (i == 2 ? coracao52 : (i == 3 ? coracao53 : (i == 4 ? coracao54 : coracao55)))).load(caminho + "res\\batalha\\apelo.png");
			} 
		}
		
		for(int i=1; i<5; i++) {
			if(contTempoCampoBatalha == i * 20 + 600) {
				(i == 1 ? coracao12 : (i == 2 ? coracao23 : (i == 3 ? coracao34 : coracao45))).load(caminho + "res\\batalha\\losango.png");
			} 
		}
		
		for(int i=2; i<7; i++) {
			if(contTempoCampoBatalha == (i-1) * 20 + 700) {

				(i == 2 ? coracao01 : (i == 3 ? coracao02 : (i == 4 ? coracao03 : (i == 5 ? coracao04 : coracao05)))).setX((i == 2 ? campoBatalha1 : (i == 3 ? campoBatalha2 : (i == 4 ? campoBatalha3 : (i == 5 ? campoBatalha4 : campoBatalha5)))).getX() + 116 + (5 * i));
			}
		}
	}
	
	public void apagarCoracoes() {
		coracao11.load(caminho + "res\\batalha\\losango.png"); coracao12.load(caminho + "res\\batalha\\losango.png"); coracao13.load(caminho + "res\\batalha\\losango.png"); coracao14.load(caminho + "res\\batalha\\losango.png"); coracao15.load(caminho + "res\\batalha\\losango.png");
		coracao16.load(caminho + "res\\batalha\\losango.png"); coracao17.load(caminho + "res\\batalha\\losango.png"); coracao18.load(caminho + "res\\batalha\\losango.png"); coracao19.load(caminho + "res\\batalha\\losango.png"); coracao110.load(caminho + "res\\batalha\\losango.png");
		
		coracao21.load(caminho + "res\\batalha\\losango.png"); coracao22.load(caminho + "res\\batalha\\losango.png"); coracao23.load(caminho + "res\\batalha\\losango.png"); coracao24.load(caminho + "res\\batalha\\losango.png"); coracao25.load(caminho + "res\\batalha\\losango.png");
		coracao26.load(caminho + "res\\batalha\\losango.png"); coracao27.load(caminho + "res\\batalha\\losango.png"); coracao28.load(caminho + "res\\batalha\\losango.png"); coracao29.load(caminho + "res\\batalha\\losango.png"); coracao210.load(caminho + "res\\batalha\\losango.png");
		
		coracao31.load(caminho + "res\\batalha\\losango.png"); coracao32.load(caminho + "res\\batalha\\losango.png"); coracao33.load(caminho + "res\\batalha\\losango.png"); coracao34.load(caminho + "res\\batalha\\losango.png"); coracao35.load(caminho + "res\\batalha\\losango.png");
		coracao36.load(caminho + "res\\batalha\\losango.png"); coracao37.load(caminho + "res\\batalha\\losango.png"); coracao38.load(caminho + "res\\batalha\\losango.png"); coracao39.load(caminho + "res\\batalha\\losango.png"); coracao310.load(caminho + "res\\batalha\\losango.png");
		
		coracao41.load(caminho + "res\\batalha\\losango.png"); coracao42.load(caminho + "res\\batalha\\losango.png"); coracao43.load(caminho + "res\\batalha\\losango.png"); coracao44.load(caminho + "res\\batalha\\losango.png"); coracao45.load(caminho + "res\\batalha\\losango.png");
		coracao46.load(caminho + "res\\batalha\\losango.png"); coracao47.load(caminho + "res\\batalha\\losango.png"); coracao48.load(caminho + "res\\batalha\\losango.png"); coracao49.load(caminho + "res\\batalha\\losango.png"); coracao410.load(caminho + "res\\batalha\\losango.png");
		
		coracao51.load(caminho + "res\\batalha\\losango.png"); coracao52.load(caminho + "res\\batalha\\losango.png"); coracao53.load(caminho + "res\\batalha\\losango.png"); coracao54.load(caminho + "res\\batalha\\losango.png"); coracao55.load(caminho + "res\\batalha\\losango.png");
		coracao56.load(caminho + "res\\batalha\\losango.png"); coracao57.load(caminho + "res\\batalha\\losango.png"); coracao58.load(caminho + "res\\batalha\\losango.png"); coracao59.load(caminho + "res\\batalha\\losango.png"); coracao510.load(caminho + "res\\batalha\\losango.png");
	}
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_DOWN && rolagemTela > -1690 * fundo.getTamanhoTela()) {
			if(contEngranagem1 == 2) {
				contEngranagem1 = 1;
			} else {contEngranagem1 ++;}
			
			engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
			
			rolagemTela = rolagemTela - 10;
			bntBarraBaixo.load(caminho + "res\\Manual\\teclaBaixo2.png");
			
		}else if(codigo == KeyEvent.VK_UP && rolagemTela < 0) {
			if(contEngranagem1 == 2) {
				contEngranagem1 = 1;
			} else {contEngranagem1 ++;}
			
			engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
			
			rolagemTela = rolagemTela + 10;
			bntBarraCima.load(caminho + "res\\Manual\\teclaCima2.png");

		}else if(codigo == KeyEvent.VK_X) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
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
		bntBarraBaixo.load(caminho + "res\\Manual\\teclaBaixo1.png");
		bntBarraCima.load(caminho + "res\\Manual\\teclaCima1.png");
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
		
		graficos.drawImage(fundo.getImagem(), fundo.getRedX(), fundo.getRedY(), fundo.getLarg(), fundo.getAlt(), this);
		
		/* ------------------------------- Controles ---------------------------------*/
		
		graficos.drawImage(controles.getImagem(), controles.getRedX(), controles.getRedY() + rolagemTela, controles.getLarg(), controles.getAlt(), this);

		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getRedX(), teclaEsquerda.getRedY() + rolagemTela, teclaEsquerda.getLarg(), teclaEsquerda.getAlt(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getRedX(), teclaDireita.getRedY() + rolagemTela, teclaDireita.getLarg(), teclaDireita.getAlt(), this);
		graficos.drawImage(teclaCima.getImagem(), teclaCima.getRedX(), teclaCima.getRedY() + rolagemTela, teclaCima.getLarg(), teclaCima.getAlt(), this);
		graficos.drawImage(teclaBaixo.getImagem(), teclaBaixo.getRedX(), teclaBaixo.getRedY() + rolagemTela, teclaBaixo.getLarg(), teclaBaixo.getAlt(), this);
		
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getRedX(), teclaZ.getRedY() + rolagemTela, teclaZ.getLarg(), teclaZ.getAlt(), this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getRedX(), teclaX.getRedY() + rolagemTela, teclaX.getLarg(), teclaX.getAlt(), this);
		graficos.drawImage(teclaEsc.getImagem(), teclaEsc.getRedX(), teclaEsc.getRedY() + rolagemTela, teclaEsc.getLarg(), teclaEsc.getAlt(), this);
		
		graficos.setColor(txtLn2.getCorTexto());
		tl2 = new TextLayout(txtLn2.getTexto(), txtLn2.getFonte(), frc);
		tl3 = new TextLayout(txtLn3.getTexto(), txtLn2.getFonte(), frc);
		tl4 = new TextLayout(txtLn4.getTexto(), txtLn2.getFonte(), frc);
		tl5 = new TextLayout(txtLn5.getTexto(), txtLn2.getFonte(), frc);

		tl2.draw(graficos, txtLn2.getRedX(), txtLn2.getRedY() + rolagemTela);
		tl3.draw(graficos, txtLn3.getRedX(), txtLn3.getRedY() + rolagemTela);
		tl4.draw(graficos, txtLn4.getRedX(), txtLn4.getRedY() + rolagemTela);
		tl5.draw(graficos, txtLn5.getRedX(), txtLn5.getRedY() + rolagemTela);
		
		/* ------------------------------- Batalha ---------------------------------*/
		
		graficos.drawImage(batalhas.getImagem(), batalhas.getRedX(), batalhas.getRedY() + rolagemTela, batalhas.getLarg(), batalhas.getAlt(), this);
		graficos.drawImage(fundoPainel1.getImagem(), fundoPainel1.getRedX(), fundoPainel1.getRedY() + rolagemTela, fundoPainel1.getLarg(), fundoPainel1.getAlt(), this);

		tl7 = new TextLayout(txtLn7.getTexto(), txtLn7.getFonte(), frc);
		tl8 = new TextLayout(txtLn8.getTexto(), txtLn7.getFonte(), frc);
		tl9 = new TextLayout(txtLn9.getTexto(), txtLn7.getFonte(), frc);

		tl7.draw(graficos, txtLn7.getRedX(), txtLn7.getRedY() + rolagemTela);
		tl8.draw(graficos, txtLn8.getRedX(), txtLn8.getRedY() + rolagemTela);	
		tl9.draw(graficos, txtLn9.getRedX(), txtLn9.getRedY() + rolagemTela);	
		
		graficos.drawImage(nomeHabilidade1.getImagem(), nomeHabilidade1.getRedX(), nomeHabilidade1.getRedY() + rolagemTela, nomeHabilidade1.getLarg(), nomeHabilidade1.getAlt(), this);
		graficos.drawImage(nomeHabilidade2.getImagem(), nomeHabilidade2.getRedX(), nomeHabilidade2.getRedY() + rolagemTela, nomeHabilidade2.getLarg(), nomeHabilidade2.getAlt(), this);
		graficos.drawImage(nomeHabilidade3.getImagem(), nomeHabilidade3.getRedX(), nomeHabilidade3.getRedY() + rolagemTela, nomeHabilidade3.getLarg(), nomeHabilidade3.getAlt(), this);
		graficos.drawImage(nomeHabilidade4.getImagem(), nomeHabilidade4.getRedX(), nomeHabilidade4.getRedY() + rolagemTela, nomeHabilidade4.getLarg(), nomeHabilidade4.getAlt(), this);
		
		tl10 = new TextLayout(nomeApelo1.getTexto(), nomeApelo1.getFonte(), frc);
		tl11 = new TextLayout(nomeApelo2.getTexto(), nomeApelo1.getFonte(), frc);
		tl12 = new TextLayout(nomeApelo3.getTexto(), nomeApelo1.getFonte(), frc);
		tl13 = new TextLayout(nomeApelo4.getTexto(), nomeApelo1.getFonte(), frc);

	    
	    graficos.setColor(contTempoNomeApelo >= 0 && contTempoNomeApelo < 25 ? nomeApelo1.getCorTexto() : nomeApelo2.getCorTexto());
	    tl10.draw(graficos, nomeApelo1.getRedX(), nomeApelo1.getRedY() + rolagemTela);
	    graficos.setColor(contTempoNomeApelo >= 25 && contTempoNomeApelo < 50 ? nomeApelo1.getCorTexto() : nomeApelo2.getCorTexto());
	    tl11.draw(graficos, nomeApelo2.getRedX(), nomeApelo2.getRedY() + rolagemTela);
	    graficos.setColor(contTempoNomeApelo >= 50 && contTempoNomeApelo < 75 ? nomeApelo1.getCorTexto() : nomeApelo2.getCorTexto());
	    tl12.draw(graficos, nomeApelo3.getRedX(), nomeApelo3.getRedY() + rolagemTela);
	    graficos.setColor(contTempoNomeApelo >= 75 ? nomeApelo1.getCorTexto() : nomeApelo2.getCorTexto());
	    tl13.draw(graficos, nomeApelo4.getRedX(), nomeApelo4.getRedY() + rolagemTela);
	    graficos.setColor(txtLn2.getCorTexto());
		
		graficos.drawImage(painel1.getImagem(), painel1.getRedX(), painel1.getRedY() + rolagemTela, painel1.getLarg(), painel1.getAlt(), this);

		/* ------------------------------- info apelo ---------------------------------*/
	   
		tl14 = new TextLayout(txtLn10.getTexto(), txtLn7.getFonte(), frc);
		tl15 = new TextLayout(txtLn11.getTexto(), txtLn7.getFonte(), frc);
		tl16 = new TextLayout(txtLn12.getTexto(), txtLn7.getFonte(), frc);
		tl17 = new TextLayout(txtLn13.getTexto(), txtLn7.getFonte(), frc);
		tl56 = new TextLayout(txtLn56.getTexto(), txtLn7.getFonte(), frc);

		tl14.draw(graficos, txtLn10.getRedX(), txtLn10.getRedY() + rolagemTela);
		tl15.draw(graficos, txtLn11.getRedX(), txtLn11.getRedY() + rolagemTela);
		tl16.draw(graficos, txtLn12.getRedX(), txtLn12.getRedY() + rolagemTela);
		tl17.draw(graficos, txtLn13.getRedX(), txtLn13.getRedY() + rolagemTela);
		tl56.draw(graficos, txtLn56.getRedX(), txtLn56.getRedY() + rolagemTela);
		
		graficos.drawImage(apelo1.getImagem(), apelo1.getRedX(), apelo1.getRedY() + rolagemTela, apelo1.getLarg(), apelo1.getAlt(), this);
		graficos.drawImage(apelo2.getImagem(), apelo2.getRedX(), apelo2.getRedY() + rolagemTela, apelo2.getLarg(), apelo2.getAlt(), this);

		graficos.drawImage(apelo.getImagem(), apelo.getRedX(), apelo.getRedY() + rolagemTela, apelo.getLarg(), apelo.getAlt(), this);
		graficos.drawImage(tipoDoApelo.getImagem(), tipoDoApelo.getRedX(), tipoDoApelo.getRedY() + rolagemTela, tipoDoApelo.getLarg(), tipoDoApelo.getAlt(), this);
		
	    tl18 = new TextLayout(apeloQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);
		tl19 = new TextLayout(InterferenciaQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);

	    graficos.setColor(apeloQuantidade.getCorTexto());
	    tl18.draw(graficos, apeloQuantidade.getRedX(), apeloQuantidade.getRedY() + rolagemTela);
	    tl19.draw(graficos, InterferenciaQuantidade.getRedX(), InterferenciaQuantidade.getRedY() + rolagemTela);
	    graficos.setColor(txtLn2.getCorTexto());
	    
		graficos.drawImage(apeloApelo1.getImagem(), apeloApelo1.getRedX(), apeloApelo1.getRedY() + rolagemTela, apeloApelo1.getLarg(), apeloApelo1.getAlt(), this);
		graficos.drawImage(apeloApelo2.getImagem(), apeloApelo2.getRedX(), apeloApelo2.getRedY() + rolagemTela, apeloApelo2.getLarg(), apeloApelo2.getAlt(), this);
		graficos.drawImage(apeloApelo3.getImagem(), apeloApelo3.getRedX(), apeloApelo3.getRedY() + rolagemTela, apeloApelo3.getLarg(), apeloApelo3.getAlt(), this);
		graficos.drawImage(apeloApelo4.getImagem(), apeloApelo4.getRedX(), apeloApelo4.getRedY() + rolagemTela, apeloApelo4.getLarg(), apeloApelo4.getAlt(), this);
		graficos.drawImage(apeloApelo5.getImagem(), apeloApelo5.getRedX(), apeloApelo5.getRedY() + rolagemTela, apeloApelo5.getLarg(), apeloApelo5.getAlt(), this);
		
		graficos.drawImage(apeloInterf1.getImagem(), apeloInterf1.getRedX(), apeloInterf1.getRedY() + rolagemTela, apeloInterf1.getLarg(), apeloInterf1.getAlt(), this);
		graficos.drawImage(apeloInterf2.getImagem(), apeloInterf2.getRedX(), apeloInterf2.getRedY() + rolagemTela, apeloInterf2.getLarg(), apeloInterf2.getAlt(), this);
	
		graficos.drawImage(painel2.getImagem(), painel2.getRedX(), painel2.getRedY() + rolagemTela, painel2.getLarg(), painel2.getAlt(), this);
		graficos.drawImage(fundoPainel2.getImagem(), fundoPainel2.getRedX(), fundoPainel2.getRedY() + rolagemTela, fundoPainel2.getLarg(), fundoPainel2.getAlt(), this);

		// ----------------------- Descrição -----------------------------------

		graficos.drawImage(fundoPainel3.getImagem(), fundoPainel3.getRedX(), fundoPainel3.getRedY() + rolagemTela, fundoPainel3.getLarg(), fundoPainel3.getAlt(), this);

		tl20 = new TextLayout(txtLn14.getTexto(), txtLn7.getFonte(), frc);
		tl21 = new TextLayout(txtLn15.getTexto(), txtLn7.getFonte(), frc);

	    tl20.draw(graficos, txtLn14.getRedX(), txtLn14.getRedY() + rolagemTela);
	    tl21.draw(graficos, txtLn15.getRedX(), txtLn15.getRedY() + rolagemTela);
	    
		graficos.drawImage(descricao.getImagem(), descricao.getRedX(), descricao.getRedY() + rolagemTela, descricao.getLarg(), descricao.getAlt(), this);

		tl22 = new TextLayout(textoDescricao1.getTexto(), textoDescricao1.getFonte(), frc);
		tl23 = new TextLayout(textoDescricao2.getTexto(), textoDescricao1.getFonte(), frc);
		tl24 = new TextLayout(textoDescricao3.getTexto(), textoDescricao1.getFonte(), frc);
		tl25 = new TextLayout(textoDescricao4.getTexto(), textoDescricao1.getFonte(), frc);
		tl26 = new TextLayout(textoDescricao5.getTexto(), textoDescricao1.getFonte(), frc);

	    graficos.setColor(textoDescricao1.getCorTexto());
	    tl22.draw(graficos, textoDescricao1.getRedX(), textoDescricao1.getRedY() + rolagemTela);
	    tl23.draw(graficos, textoDescricao2.getRedX(), textoDescricao2.getRedY() + rolagemTela);
	    tl24.draw(graficos, textoDescricao3.getRedX(), textoDescricao3.getRedY() + rolagemTela);
	    tl25.draw(graficos, textoDescricao4.getRedX(), textoDescricao4.getRedY() + rolagemTela);
	    graficos.setColor(textoDescricao5.getCorTexto());
	    tl26.draw(graficos, textoDescricao5.getRedX(), textoDescricao5.getRedY() + rolagemTela);
	    graficos.setColor(txtLn2.getCorTexto());
	    
		graficos.drawImage(painel3.getImagem(), painel3.getRedX(), painel3.getRedY() + rolagemTela, painel3.getLarg(), painel3.getAlt(), this);
	    
		// ----------------------- campo batalha -----------------------------------

		graficos.drawImage(fundoPainel4.getImagem(), fundoPainel4.getRedX(), fundoPainel4.getRedY() + rolagemTela, fundoPainel4.getLarg(), fundoPainel4.getAlt(), this);

		tl27 = new TextLayout(txtLn16.getTexto(), txtLn7.getFonte(), frc);
		tl29 = new TextLayout(txtLn18.getTexto(), txtLn7.getFonte(), frc);
		tl31 = new TextLayout(txtLn20.getTexto(), txtLn7.getFonte(), frc);
		tl32 = new TextLayout(txtLn21.getTexto(), txtLn7.getFonte(), frc);
		tl33 = new TextLayout(txtLn22.getTexto(), txtLn7.getFonte(), frc);
		tl34 = new TextLayout(txtLn23.getTexto(), txtLn7.getFonte(), frc);
		tl35 = new TextLayout(txtLn24.getTexto(), txtLn7.getFonte(), frc);
		tl36 = new TextLayout(txtLn25.getTexto(), txtLn7.getFonte(), frc);
		tl37 = new TextLayout(txtLn26.getTexto(), txtLn7.getFonte(), frc);

	    tl27.draw(graficos, txtLn16.getRedX(), txtLn16.getRedY() + rolagemTela);
	    tl29.draw(graficos, txtLn18.getRedX(), txtLn18.getRedY() + rolagemTela);
	    tl31.draw(graficos, txtLn20.getRedX(), txtLn20.getRedY() + rolagemTela);
	    tl32.draw(graficos, txtLn21.getRedX(), txtLn21.getRedY() + rolagemTela);
	    tl33.draw(graficos, txtLn22.getRedX(), txtLn22.getRedY() + rolagemTela);
	    tl34.draw(graficos, txtLn23.getRedX(), txtLn23.getRedY() + rolagemTela);
	    tl35.draw(graficos, txtLn24.getRedX(), txtLn24.getRedY() + rolagemTela);
	    tl36.draw(graficos, txtLn25.getRedX(), txtLn25.getRedY() + rolagemTela);
	    tl37.draw(graficos, txtLn26.getRedX(), txtLn26.getRedY() + rolagemTela);
	    
	    graficos.drawImage(apelo3.getImagem(), apelo3.getRedX(), apelo3.getRedY() + rolagemTela, apelo3.getLarg(), apelo3.getAlt(), this);
	    graficos.drawImage(apelo4.getImagem(), apelo4.getRedX(), apelo4.getRedY() + rolagemTela, apelo4.getLarg(), apelo4.getAlt(), this);
	    graficos.drawImage(apelo5.getImagem(), apelo5.getRedX(), apelo5.getRedY() + rolagemTela, apelo5.getLarg(), apelo5.getAlt(), this);

	    graficos.drawImage(campoBatalha1.getImagem(), campoBatalha1.getRedX(), campoBatalha1.getRedY() + rolagemTela, campoBatalha1.getLarg(), campoBatalha1.getAlt(), this);
		graficos.drawImage(campoBatalha2.getImagem(), campoBatalha2.getRedX(), campoBatalha2.getRedY() + rolagemTela, campoBatalha2.getLarg(), campoBatalha2.getAlt(), this);
		graficos.drawImage(campoBatalha3.getImagem(), campoBatalha3.getRedX(), campoBatalha3.getRedY() + rolagemTela, campoBatalha3.getLarg(), campoBatalha3.getAlt(), this);
		graficos.drawImage(campoBatalha4.getImagem(), campoBatalha4.getRedX(), campoBatalha4.getRedY() + rolagemTela, campoBatalha4.getLarg(), campoBatalha4.getAlt(), this);
		graficos.drawImage(campoBatalha5.getImagem(), campoBatalha5.getRedX(), campoBatalha5.getRedY() + rolagemTela, campoBatalha5.getLarg(), campoBatalha5.getAlt(), this);
		
		graficos.drawImage(iconeCampoBatalha1.getImagem(), iconeCampoBatalha1.getRedX(), iconeCampoBatalha1.getRedY() + rolagemTela, iconeCampoBatalha1.getLarg(), iconeCampoBatalha1.getAlt(), this);
		graficos.drawImage(iconeCampoBatalha2.getImagem(), iconeCampoBatalha2.getRedX(), iconeCampoBatalha2.getRedY() + rolagemTela, iconeCampoBatalha2.getLarg(), iconeCampoBatalha2.getAlt(), this);
		graficos.drawImage(iconeCampoBatalha3.getImagem(), iconeCampoBatalha3.getRedX(), iconeCampoBatalha3.getRedY() + rolagemTela, iconeCampoBatalha3.getLarg(), iconeCampoBatalha3.getAlt(), this);
		graficos.drawImage(iconeCampoBatalha4.getImagem(), iconeCampoBatalha4.getRedX(), iconeCampoBatalha4.getRedY() + rolagemTela, iconeCampoBatalha4.getLarg(), iconeCampoBatalha4.getAlt(), this);
		graficos.drawImage(iconeCampoBatalha5.getImagem(), iconeCampoBatalha5.getRedX(), iconeCampoBatalha5.getRedY() + rolagemTela, iconeCampoBatalha5.getLarg(), iconeCampoBatalha5.getAlt(), this);
		
		tl38 = new TextLayout(txtEfeitoFase.getTexto(), txtLn7.getFonte(), frc);
	    tl38.draw(graficos, txtEfeitoFase.getRedX(), txtEfeitoFase.getRedY() + rolagemTela);
	    graficos.drawImage(efeitoFase.getImagem(), efeitoFase.getRedX(), efeitoFase.getRedY() + rolagemTela, efeitoFase.getLarg(), efeitoFase.getAlt(), this);
		
		graficos.drawImage(painel4.getImagem(), painel4.getRedX(), painel4.getRedY() + rolagemTela, painel4.getLarg(), painel4.getAlt(), this);
		
		graficos.drawImage(coracao01.getImagem(), coracao01.getRedX(), coracao01.getRedY() + rolagemTela, coracao01.getLarg(), coracao01.getAlt(), this);
		graficos.drawImage(coracao02.getImagem(), coracao02.getRedX(), coracao02.getRedY() + rolagemTela, coracao02.getLarg(), coracao02.getAlt(), this);
		graficos.drawImage(coracao03.getImagem(), coracao03.getRedX(), coracao03.getRedY() + rolagemTela, coracao03.getLarg(), coracao03.getAlt(), this);
		graficos.drawImage(coracao04.getImagem(), coracao04.getRedX(), coracao04.getRedY() + rolagemTela, coracao04.getLarg(), coracao04.getAlt(), this);
		graficos.drawImage(coracao05.getImagem(), coracao05.getRedX(), coracao05.getRedY() + rolagemTela, coracao05.getLarg(), coracao05.getAlt(), this);

		graficos.drawImage(coracao11.getImagem(), coracao11.getRedX(), coracao11.getRedY() + rolagemTela, coracao11.getLarg(), coracao11.getAlt(), this);
		graficos.drawImage(coracao12.getImagem(), coracao12.getRedX(), coracao12.getRedY() + rolagemTela, coracao12.getLarg(), coracao12.getAlt(), this);
		graficos.drawImage(coracao13.getImagem(), coracao13.getRedX(), coracao13.getRedY() + rolagemTela, coracao13.getLarg(), coracao13.getAlt(), this);
		graficos.drawImage(coracao14.getImagem(), coracao14.getRedX(), coracao14.getRedY() + rolagemTela, coracao14.getLarg(), coracao14.getAlt(), this);
		graficos.drawImage(coracao15.getImagem(), coracao15.getRedX(), coracao15.getRedY() + rolagemTela, coracao15.getLarg(), coracao15.getAlt(), this);
		graficos.drawImage(coracao16.getImagem(), coracao16.getRedX(), coracao16.getRedY() + rolagemTela, coracao16.getLarg(), coracao16.getAlt(), this);
		graficos.drawImage(coracao17.getImagem(), coracao17.getRedX(), coracao17.getRedY() + rolagemTela, coracao17.getLarg(), coracao17.getAlt(), this);
		graficos.drawImage(coracao18.getImagem(), coracao18.getRedX(), coracao18.getRedY() + rolagemTela, coracao18.getLarg(), coracao18.getAlt(), this);
		graficos.drawImage(coracao19.getImagem(), coracao19.getRedX(), coracao19.getRedY() + rolagemTela, coracao19.getLarg(), coracao19.getAlt(), this);
		graficos.drawImage(coracao110.getImagem(), coracao110.getRedX(), coracao110.getRedY() + rolagemTela, coracao110.getLarg(), coracao110.getAlt(), this);
		
		graficos.drawImage(coracao21.getImagem(), coracao21.getRedX(), coracao21.getRedY() + rolagemTela, coracao21.getLarg(), coracao21.getAlt(), this);
		graficos.drawImage(coracao22.getImagem(), coracao22.getRedX(), coracao22.getRedY() + rolagemTela, coracao22.getLarg(), coracao22.getAlt(), this);
		graficos.drawImage(coracao23.getImagem(), coracao23.getRedX(), coracao23.getRedY() + rolagemTela, coracao23.getLarg(), coracao23.getAlt(), this);
		graficos.drawImage(coracao24.getImagem(), coracao24.getRedX(), coracao24.getRedY() + rolagemTela, coracao24.getLarg(), coracao24.getAlt(), this);
		graficos.drawImage(coracao25.getImagem(), coracao25.getRedX(), coracao25.getRedY() + rolagemTela, coracao25.getLarg(), coracao25.getAlt(), this);
		graficos.drawImage(coracao26.getImagem(), coracao26.getRedX(), coracao26.getRedY() + rolagemTela, coracao26.getLarg(), coracao26.getAlt(), this);
		graficos.drawImage(coracao27.getImagem(), coracao27.getRedX(), coracao27.getRedY() + rolagemTela, coracao27.getLarg(), coracao27.getAlt(), this);
		graficos.drawImage(coracao28.getImagem(), coracao28.getRedX(), coracao28.getRedY() + rolagemTela, coracao28.getLarg(), coracao28.getAlt(), this);
		graficos.drawImage(coracao29.getImagem(), coracao29.getRedX(), coracao29.getRedY() + rolagemTela, coracao29.getLarg(), coracao29.getAlt(), this);
		graficos.drawImage(coracao210.getImagem(), coracao210.getRedX(), coracao210.getRedY() + rolagemTela, coracao210.getLarg(), coracao210.getAlt(), this);
		
		graficos.drawImage(coracao31.getImagem(), coracao31.getRedX(), coracao31.getRedY() + rolagemTela, coracao31.getLarg(), coracao31.getAlt(), this);
		graficos.drawImage(coracao32.getImagem(), coracao32.getRedX(), coracao32.getRedY() + rolagemTela, coracao32.getLarg(), coracao32.getAlt(), this);
		graficos.drawImage(coracao33.getImagem(), coracao33.getRedX(), coracao33.getRedY() + rolagemTela, coracao33.getLarg(), coracao33.getAlt(), this);
		graficos.drawImage(coracao34.getImagem(), coracao34.getRedX(), coracao34.getRedY() + rolagemTela, coracao34.getLarg(), coracao34.getAlt(), this);
		graficos.drawImage(coracao35.getImagem(), coracao35.getRedX(), coracao35.getRedY() + rolagemTela, coracao35.getLarg(), coracao35.getAlt(), this);
		graficos.drawImage(coracao36.getImagem(), coracao36.getRedX(), coracao36.getRedY() + rolagemTela, coracao36.getLarg(), coracao36.getAlt(), this);
		graficos.drawImage(coracao37.getImagem(), coracao37.getRedX(), coracao37.getRedY() + rolagemTela, coracao37.getLarg(), coracao37.getAlt(), this);
		graficos.drawImage(coracao38.getImagem(), coracao38.getRedX(), coracao38.getRedY() + rolagemTela, coracao38.getLarg(), coracao38.getAlt(), this);
		graficos.drawImage(coracao39.getImagem(), coracao39.getRedX(), coracao39.getRedY() + rolagemTela, coracao39.getLarg(), coracao39.getAlt(), this);
		graficos.drawImage(coracao310.getImagem(), coracao310.getRedX(), coracao310.getRedY() + rolagemTela, coracao310.getLarg(), coracao310.getAlt(), this);
		
		graficos.drawImage(coracao41.getImagem(), coracao41.getRedX(), coracao41.getRedY() + rolagemTela, coracao41.getLarg(), coracao41.getAlt(), this);
		graficos.drawImage(coracao42.getImagem(), coracao42.getRedX(), coracao42.getRedY() + rolagemTela, coracao42.getLarg(), coracao42.getAlt(), this);
		graficos.drawImage(coracao43.getImagem(), coracao43.getRedX(), coracao43.getRedY() + rolagemTela, coracao43.getLarg(), coracao43.getAlt(), this);
		graficos.drawImage(coracao44.getImagem(), coracao44.getRedX(), coracao44.getRedY() + rolagemTela, coracao44.getLarg(), coracao44.getAlt(), this);
		graficos.drawImage(coracao45.getImagem(), coracao45.getRedX(), coracao45.getRedY() + rolagemTela, coracao45.getLarg(), coracao45.getAlt(), this);
		graficos.drawImage(coracao46.getImagem(), coracao46.getRedX(), coracao46.getRedY() + rolagemTela, coracao46.getLarg(), coracao46.getAlt(), this);
		graficos.drawImage(coracao47.getImagem(), coracao47.getRedX(), coracao47.getRedY() + rolagemTela, coracao47.getLarg(), coracao47.getAlt(), this);
		graficos.drawImage(coracao48.getImagem(), coracao48.getRedX(), coracao48.getRedY() + rolagemTela, coracao48.getLarg(), coracao48.getAlt(), this);
		graficos.drawImage(coracao49.getImagem(), coracao49.getRedX(), coracao49.getRedY() + rolagemTela, coracao49.getLarg(), coracao49.getAlt(), this);
		graficos.drawImage(coracao410.getImagem(), coracao410.getRedX(), coracao410.getRedY() + rolagemTela, coracao410.getLarg(), coracao410.getAlt(), this);
		
		graficos.drawImage(coracao51.getImagem(), coracao51.getRedX(), coracao51.getRedY() + rolagemTela, coracao51.getLarg(), coracao51.getAlt(), this);
		graficos.drawImage(coracao52.getImagem(), coracao52.getRedX(), coracao52.getRedY() + rolagemTela, coracao52.getLarg(), coracao52.getAlt(), this);
		graficos.drawImage(coracao53.getImagem(), coracao53.getRedX(), coracao53.getRedY() + rolagemTela, coracao53.getLarg(), coracao53.getAlt(), this);
		graficos.drawImage(coracao54.getImagem(), coracao54.getRedX(), coracao54.getRedY() + rolagemTela, coracao54.getLarg(), coracao54.getAlt(), this);
		graficos.drawImage(coracao55.getImagem(), coracao55.getRedX(), coracao55.getRedY() + rolagemTela, coracao55.getLarg(), coracao55.getAlt(), this);
		graficos.drawImage(coracao56.getImagem(), coracao56.getRedX(), coracao56.getRedY() + rolagemTela, coracao56.getLarg(), coracao56.getAlt(), this);
		graficos.drawImage(coracao57.getImagem(), coracao57.getRedX(), coracao57.getRedY() + rolagemTela, coracao57.getLarg(), coracao57.getAlt(), this);
		graficos.drawImage(coracao58.getImagem(), coracao58.getRedX(), coracao58.getRedY() + rolagemTela, coracao58.getLarg(), coracao58.getAlt(), this);
		graficos.drawImage(coracao59.getImagem(), coracao59.getRedX(), coracao59.getRedY() + rolagemTela, coracao59.getLarg(), coracao59.getAlt(), this);
		graficos.drawImage(coracao510.getImagem(), coracao510.getRedX(), coracao510.getRedY() + rolagemTela, coracao510.getLarg(), coracao510.getAlt(), this);
		
		graficos.drawImage(luzCampoBatalha1.getImagem(), luzCampoBatalha1.getRedX(), luzCampoBatalha1.getRedY() + rolagemTela, luzCampoBatalha1.getLarg(), luzCampoBatalha1.getAlt(), this);
		graficos.drawImage(luzCampoBatalha2.getImagem(), luzCampoBatalha2.getRedX(), luzCampoBatalha2.getRedY() + rolagemTela, luzCampoBatalha2.getLarg(), luzCampoBatalha2.getAlt(), this);
		graficos.drawImage(luzCampoBatalha3.getImagem(), luzCampoBatalha3.getRedX(), luzCampoBatalha3.getRedY() + rolagemTela, luzCampoBatalha3.getLarg(), luzCampoBatalha3.getAlt(), this);
		graficos.drawImage(luzCampoBatalha4.getImagem(), luzCampoBatalha4.getRedX(), luzCampoBatalha4.getRedY() + rolagemTela, luzCampoBatalha4.getLarg(), luzCampoBatalha4.getAlt(), this);
		graficos.drawImage(luzCampoBatalha5.getImagem(), luzCampoBatalha5.getRedX(), luzCampoBatalha5.getRedY() + rolagemTela, luzCampoBatalha5.getLarg(), luzCampoBatalha5.getAlt(), this);
		
		// ----------------------- Tabela de efeito de chefe -----------------------------------

		graficos.drawImage(efeitoDeChefe.getImagem(), efeitoDeChefe.getRedX(), efeitoDeChefe.getRedY() + rolagemTela, efeitoDeChefe.getLarg(), efeitoDeChefe.getAlt(), this);
		graficos.drawImage(tabela.getImagem(), tabela.getRedX(), tabela.getRedY() + rolagemTela, tabela.getLarg(), tabela.getAlt(), this);

		tl40 = new TextLayout(txtLn28.getTexto(), txtLn28.getFonte(), frc);
		tl41 = new TextLayout(txtLn29.getTexto(), txtLn28.getFonte(), frc);
		tl42 = new TextLayout(txtLn30.getTexto(), txtLn28.getFonte(), frc);
		tl43 = new TextLayout(txtLn31.getTexto(), txtLn28.getFonte(), frc);
		tl44 = new TextLayout(txtLn32.getTexto(), txtLn28.getFonte(), frc);
		tl45 = new TextLayout(txtLn33.getTexto(), txtLn28.getFonte(), frc);
		tl46 = new TextLayout(txtLn34.getTexto(), txtLn28.getFonte(), frc);
		tl47 = new TextLayout(txtLn35.getTexto(), txtLn28.getFonte(), frc);
		tl48 = new TextLayout(txtLn36.getTexto(), txtLn28.getFonte(), frc);
		tl49 = new TextLayout(txtLn37.getTexto(), txtLn28.getFonte(), frc);
		tl50 = new TextLayout(txtLn38.getTexto(), txtLn28.getFonte(), frc);
		tl53 = new TextLayout(txtLn41.getTexto(), txtLn28.getFonte(), frc);
		tl54 = new TextLayout(txtLn42.getTexto(), txtLn28.getFonte(), frc);
		tl55 = new TextLayout(txtLn43.getTexto(), txtLn28.getFonte(), frc);

	    tl40.draw(graficos, txtLn28.getRedX(), txtLn28.getRedY() + rolagemTela);
	    tl41.draw(graficos, txtLn29.getRedX(), txtLn29.getRedY() + rolagemTela);
	    tl42.draw(graficos, txtLn30.getRedX(), txtLn30.getRedY() + rolagemTela);
	    tl43.draw(graficos, txtLn31.getRedX(), txtLn31.getRedY() + rolagemTela);
	    tl44.draw(graficos, txtLn32.getRedX(), txtLn32.getRedY() + rolagemTela);
	    tl45.draw(graficos, txtLn33.getRedX(), txtLn33.getRedY() + rolagemTela);
	    tl46.draw(graficos, txtLn34.getRedX(), txtLn34.getRedY() + rolagemTela);
	    tl47.draw(graficos, txtLn35.getRedX(), txtLn35.getRedY() + rolagemTela);
	    tl48.draw(graficos, txtLn36.getRedX(), txtLn36.getRedY() + rolagemTela);
	    tl49.draw(graficos, txtLn37.getRedX(), txtLn37.getRedY() + rolagemTela);
	    tl50.draw(graficos, txtLn38.getRedX(), txtLn38.getRedY() + rolagemTela);
	    tl53.draw(graficos, txtLn41.getRedX(), txtLn41.getRedY() + rolagemTela);
	    tl54.draw(graficos, txtLn42.getRedX(), txtLn42.getRedY() + rolagemTela);
	    tl55.draw(graficos, txtLn43.getRedX(), txtLn43.getRedY() + rolagemTela);

		// ----------------------- Considerações finais -----------------------------------

		tl51 = new TextLayout(txtLn39.getTexto(), txtLn7.getFonte(), frc);
		tl52 = new TextLayout(txtLn40.getTexto(), txtLn7.getFonte(), frc);

	    tl51.draw(graficos, txtLn39.getRedX(), txtLn39.getRedY() + rolagemTela);
	    tl52.draw(graficos, txtLn40.getRedX(), txtLn40.getRedY() + rolagemTela);
	    
		/* ------------------------------- Barra de rolagem ---------------------------------*/

		graficos.drawImage(bntBarraBaixo.getImagem(), bntBarraBaixo.getRedX(), bntBarraBaixo.getRedY(), bntBarraBaixo.getLarg(), bntBarraBaixo.getAlt(), this);
		graficos.drawImage(bntBarraCima.getImagem(), bntBarraCima.getRedX(), bntBarraCima.getRedY(), bntBarraCima.getLarg(), bntBarraCima.getAlt(), this);
		
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getRedX(), engrenagem1.getRedY(), engrenagem1.getLarg(), engrenagem1.getAlt(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getRedX(), engrenagem2.getRedY(), engrenagem2.getLarg(), engrenagem2.getAlt(), this);
		graficos.drawImage(contorno.getImagem(), contorno.getRedX(), contorno.getRedY(), contorno.getLarg(), contorno.getAlt(), this);
		
		graficos.drawImage(tapaResto.getImagem(), tapaResto.getRedX(), tapaResto.getRedY(), tapaResto.getLarg(), tapaResto.getAlt(), this);

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
