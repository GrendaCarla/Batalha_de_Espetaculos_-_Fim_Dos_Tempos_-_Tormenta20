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

import aventureiros.Arius;
import aventureiros.Ayla;
import aventureiros.Ignis;
import aventureiros.Kiki;
import aventureiros.Rexthor;

import java.util.Random;

import batalha_de_Espetaculos.Modelo.Icones_interativos;
import batalha_de_Espetaculos.Modelo.Texto;

public class Batalha extends JPanel implements ActionListener {
	
	/* ---------------------------------------------------------------------------------------- \
	|  Batalha é a tela a onde o jogo acontece.													|
	\ ---------------------------------------------------------------------------------------- */
	
	private Escolha_de_adversario paginaAnterior;
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	private int tamanhoContorno = 20;
	private Icones_interativos parabenizacaoVencedor;
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private int redimLarg, redimAlt;
	
	private int contTempoApelo = 0;
	private int contTempoInter = 10;
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(41,66);
	private Icones_interativos bntRegras = new Icones_interativos(41,202);
	private Icones_interativos bntVoltar = new Icones_interativos(41,338);
	
	private boolean mostrarMenu = false;
	private int contMenu = 0;
	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------

	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(1234/2 - 706/2 + 110, 640/2 - 278/2 + 190);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(1234/2 - 706/2 + 480, 640/2 - 278/2 + 190);
	
	
	private Texto txtDialogoAviso = new Texto(1234/2 - 706/2 + 110, 548/2 - 28, " ");
	private Texto txtDialogoAviso2 = new Texto(1234/2 - 706/2 + 250, 548/2 + 52, " ");
	
	private Boolean bntSimNaoDialgoAviso = true;
	
	// ------------------------ informações dos aventureiros ------------------------------
	
	private Ignis ignis = new Ignis();
	private Ayla ayla = new Ayla();
	private Rexthor rexthor = new Rexthor();
	private Kiki kiki = new Kiki();
	private Arius arius = new Arius();
	
	private String [] nomeAventureiro = {"Ignis", "Ayla", "Rexthor", "Kiki", "Arius"};
	
	private int [][] apeloIgnis = ignis.getValores();
	private int [][] apeloAyla = ayla.getValores();
	private int [][] apeloRexthor = rexthor.getValores();
	private int [][] apeloKiki = kiki.getValores();
	private int [][] apeloArius = arius.getValores();
	
	private String [][] gifApelos = {ignis.getGifApelos(), ayla.getGifApelos(), rexthor.getGifApelos(), kiki.getGifApelos(), arius.getGifApelos()};
	private String [][] NomeApelos = {ignis.getNomeApelos(), ayla.getNomeApelos(), rexthor.getNomeApelos(), kiki.getNomeApelos(), arius.getNomeApelos()};
	private String [][] ConteudoDescricao = {ignis.getConteudoDescricao(0), ignis.getConteudoDescricao(1), ignis.getConteudoDescricao(2), ignis.getConteudoDescricao(3), 
											 ayla.getConteudoDescricao(0), ayla.getConteudoDescricao(1), ayla.getConteudoDescricao(2), ayla.getConteudoDescricao(3), 
											 rexthor.getConteudoDescricao(0), rexthor.getConteudoDescricao(1), rexthor.getConteudoDescricao(2), rexthor.getConteudoDescricao(3),
											 kiki.getConteudoDescricao(0), kiki.getConteudoDescricao(1), kiki.getConteudoDescricao(2), kiki.getConteudoDescricao(3),
											arius.getConteudoDescricao(0), arius.getConteudoDescricao(1), arius.getConteudoDescricao(2), arius.getConteudoDescricao(3)};

	//									   posição aventu,    apelo atual      apelo gerado      dano dado      efeito do dano   pontos atuais   poder anterior usado   tipo do ataque
	private int [][] matrizAventureiros = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {-1, -1, -1, -1, -1}, {0, 0, 0, 0, 0}}; 
	
	//quantidade interferência q recebem de cada um  ignis             ayla            rexthor           kiki            arius
	private int [][] interferenciasRecebidas = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

	private int [] apeloRepetido = {0, 0, 0, 0, 0};
	
	private boolean [] somaDeApelo = {false, false, false, false, false};
	
	 //       						dano, efeito, posicaoNaLista
	private int [] valoresInterferencia = {-1,-1,-1};
	
	private int aventureiro;
	private int adversario;
	private int posicaoAventureiro;
	int vezDoAventureiro = 0;
	
	private int contEtapasBatalha = 0;
	
	private int TerminouLoopEfeitoInterf = 0;
	
	private int [] efeitoChefeDeFase = {0, 0, 0 ,0 ,0};
	
	private int [] danoEfeito4 = {0, 0, 0, 0, 0};
	private int Efeito4 = 0;
	
						// rex , arius
	private int [] Efeito6 = {0, 0};

	private Random aleatorioHabAdver = new Random();
	private int [] arrayAleatorioHabAdver = {aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4)};

	// ------------------------ divisões da tela de batalha -------------------------
	
	private Icones_interativos animacao = new Icones_interativos(tamanhoContorno, tamanhoContorno);
	
	private Icones_interativos campoBatalha1 = new Icones_interativos(tamanhoContorno + 760 + 4, tamanhoContorno);
	private Icones_interativos campoBatalha2 = new Icones_interativos(campoBatalha1.getX(), campoBatalha1.getY() + 70 + 4);
	private Icones_interativos campoBatalha3 = new Icones_interativos(campoBatalha1.getX(), campoBatalha2.getY() + 70 + 4);
	private Icones_interativos campoBatalha4 = new Icones_interativos(campoBatalha1.getX(), campoBatalha3.getY() + 70 + 4);
	private Icones_interativos campoBatalha5 = new Icones_interativos(campoBatalha1.getX(), campoBatalha4.getY() + 70 + 4);
		
	private Icones_interativos nomeHabilidade1 = new Icones_interativos(tamanhoContorno, animacao.getY() + 366 + 6);
	private Icones_interativos nomeHabilidade2 = new Icones_interativos(tamanhoContorno, nomeHabilidade1.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade3 = new Icones_interativos(tamanhoContorno, nomeHabilidade2.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade4 = new Icones_interativos(tamanhoContorno, nomeHabilidade3.getY() + 54 + 4);
	
	private Icones_interativos apelo = new Icones_interativos(nomeHabilidade1.getX() + 330 + 4, animacao.getY() + 366 + 6);
	
	private Icones_interativos descricao = new Icones_interativos(apelo.getX(), apelo.getY() + 54 + 4);
	
	// ------------------------------------------- animação ----------------------------------------

	private int intervaloAnimacao = 5; //10;
	private int intervaloAnimacaoGif = 100; //200;
	
	private int comecarAnimacaoCoracao = 0;
	private int animacaoFileira = -1;
	
	private boolean Iniciargif = false;
	
	private boolean [] animacaoEfeitoConcluido = {false, false, false, false, false};

	// --------------------------------- campo batalha e habilidades usadas -----------------------------------------
	
	private Texto txtEfeitoFase = new Texto(tamanhoContorno + 700, campoBatalha1.getY() + 70/2 + 7, " ");
	private Icones_interativos efeitoFase = new Icones_interativos(txtEfeitoFase.getX() + 30, txtEfeitoFase.getY() - 17);
	
	private Icones_interativos iconeCampoBatalha1 = new Icones_interativos(tamanhoContorno + 760 + 4 + 5, tamanhoContorno + 5);
	private Icones_interativos iconeCampoBatalha2 = new Icones_interativos(campoBatalha1.getX() + 5, campoBatalha1.getY() + 70 + 4 + 5);
	private Icones_interativos iconeCampoBatalha3 = new Icones_interativos(campoBatalha1.getX() + 5, campoBatalha2.getY() + 70 + 4 + 5);
	private Icones_interativos iconeCampoBatalha4 = new Icones_interativos(campoBatalha1.getX() + 5, campoBatalha3.getY() + 70 + 4 + 5);
	private Icones_interativos iconeCampoBatalha5 = new Icones_interativos(campoBatalha1.getX() + 5, campoBatalha4.getY() + 70 + 4 + 5);
	
	private Icones_interativos seletorAventureiro = new Icones_interativos(iconeCampoBatalha1.getX() - 3, iconeCampoBatalha1.getY() - 3);
	
	// corações que mede o total de apelo e interferência de todas as rodadas 
	private Icones_interativos coracao01 = new Icones_interativos(campoBatalha1.getX() + 100, campoBatalha1.getY() + 70/2);
	private Icones_interativos coracao02 = new Icones_interativos(campoBatalha2.getX() + 100, campoBatalha2.getY() + 70/2);
	private Icones_interativos coracao03 = new Icones_interativos(campoBatalha3.getX() + 100, campoBatalha3.getY() + 70/2);
	private Icones_interativos coracao04 = new Icones_interativos(campoBatalha4.getX() + 100, campoBatalha4.getY() + 70/2);
	private Icones_interativos coracao05 = new Icones_interativos(campoBatalha5.getX() + 100, campoBatalha5.getY() + 70/2);
	
	// corações que mede o apelo e interferência da rodada
	private Icones_interativos coracao11 = new Icones_interativos(campoBatalha1.getX() + 100, campoBatalha1.getY() + 10); 
	private Icones_interativos coracao12 = new Icones_interativos(coracao11.getX() + 25, coracao11.getY()); 
	private Icones_interativos coracao13 = new Icones_interativos(coracao12.getX() + 25, coracao12.getY()); 
	private Icones_interativos coracao14 = new Icones_interativos(coracao13.getX() + 25, coracao13.getY()); 
	private Icones_interativos coracao15 = new Icones_interativos(coracao14.getX() + 25, coracao14.getY()); 
	private Icones_interativos coracao16 = new Icones_interativos(coracao15.getX() + 25, coracao15.getY()); 
	private Icones_interativos coracao17 = new Icones_interativos(coracao16.getX() + 25, coracao16.getY()); 
	private Icones_interativos coracao18 = new Icones_interativos(coracao17.getX() + 25, coracao17.getY()); 
	private Icones_interativos coracao19 = new Icones_interativos(coracao18.getX() + 25, coracao18.getY()); 
	private Icones_interativos coracao110 = new Icones_interativos(coracao19.getX() + 25, coracao19.getY()); 
	
	private Icones_interativos coracao21 = new Icones_interativos(campoBatalha2.getX() + 100, campoBatalha2.getY() + 10); 
	private Icones_interativos coracao22 = new Icones_interativos(coracao21.getX() + 25, coracao21.getY()); 
	private Icones_interativos coracao23 = new Icones_interativos(coracao22.getX() + 25, coracao22.getY()); 
	private Icones_interativos coracao24 = new Icones_interativos(coracao23.getX() + 25, coracao23.getY()); 
	private Icones_interativos coracao25 = new Icones_interativos(coracao24.getX() + 25, coracao24.getY()); 
	private Icones_interativos coracao26 = new Icones_interativos(coracao25.getX() + 25, coracao25.getY()); 
	private Icones_interativos coracao27 = new Icones_interativos(coracao26.getX() + 25, coracao26.getY()); 
	private Icones_interativos coracao28 = new Icones_interativos(coracao27.getX() + 25, coracao27.getY()); 
	private Icones_interativos coracao29 = new Icones_interativos(coracao28.getX() + 25, coracao28.getY()); 
	private Icones_interativos coracao210 = new Icones_interativos(coracao29.getX() + 25, coracao29.getY()); 
	
	private Icones_interativos coracao31 = new Icones_interativos(campoBatalha3.getX() + 100, campoBatalha3.getY() + 10); 
	private Icones_interativos coracao32 = new Icones_interativos(coracao31.getX() + 25, coracao31.getY()); 
	private Icones_interativos coracao33 = new Icones_interativos(coracao32.getX() + 25, coracao32.getY()); 
	private Icones_interativos coracao34 = new Icones_interativos(coracao33.getX() + 25, coracao33.getY()); 
	private Icones_interativos coracao35 = new Icones_interativos(coracao34.getX() + 25, coracao34.getY()); 
	private Icones_interativos coracao36 = new Icones_interativos(coracao35.getX() + 25, coracao35.getY()); 
	private Icones_interativos coracao37 = new Icones_interativos(coracao36.getX() + 25, coracao36.getY()); 
	private Icones_interativos coracao38 = new Icones_interativos(coracao37.getX() + 25, coracao37.getY()); 
	private Icones_interativos coracao39 = new Icones_interativos(coracao38.getX() + 25, coracao38.getY()); 
	private Icones_interativos coracao310 = new Icones_interativos(coracao39.getX() + 25, coracao39.getY()); 
	
	private Icones_interativos coracao41 = new Icones_interativos(campoBatalha4.getX() + 100, campoBatalha4.getY() + 10); 
	private Icones_interativos coracao42 = new Icones_interativos(coracao41.getX() + 25, coracao41.getY()); 
	private Icones_interativos coracao43 = new Icones_interativos(coracao42.getX() + 25, coracao42.getY()); 
	private Icones_interativos coracao44 = new Icones_interativos(coracao43.getX() + 25, coracao43.getY()); 
	private Icones_interativos coracao45 = new Icones_interativos(coracao44.getX() + 25, coracao44.getY()); 
	private Icones_interativos coracao46 = new Icones_interativos(coracao45.getX() + 25, coracao45.getY()); 
	private Icones_interativos coracao47 = new Icones_interativos(coracao46.getX() + 25, coracao46.getY()); 
	private Icones_interativos coracao48 = new Icones_interativos(coracao47.getX() + 25, coracao47.getY()); 
	private Icones_interativos coracao49 = new Icones_interativos(coracao48.getX() + 25, coracao48.getY()); 
	private Icones_interativos coracao410 = new Icones_interativos(coracao49.getX() + 25, coracao49.getY()); 
	
	private Icones_interativos coracao51 = new Icones_interativos(campoBatalha5.getX() + 100, campoBatalha5.getY() + 10); 
	private Icones_interativos coracao52 = new Icones_interativos(coracao51.getX() + 25, coracao51.getY()); 
	private Icones_interativos coracao53 = new Icones_interativos(coracao52.getX() + 25, coracao52.getY()); 
	private Icones_interativos coracao54 = new Icones_interativos(coracao53.getX() + 25, coracao53.getY()); 
	private Icones_interativos coracao55 = new Icones_interativos(coracao54.getX() + 25, coracao54.getY()); 
	private Icones_interativos coracao56 = new Icones_interativos(coracao55.getX() + 25, coracao55.getY()); 
	private Icones_interativos coracao57 = new Icones_interativos(coracao56.getX() + 25, coracao56.getY()); 
	private Icones_interativos coracao58 = new Icones_interativos(coracao57.getX() + 25, coracao57.getY()); 
	private Icones_interativos coracao59 = new Icones_interativos(coracao58.getX() + 25, coracao58.getY()); 
	private Icones_interativos coracao510 = new Icones_interativos(coracao59.getX() + 25, coracao59.getY()); 

	// ----------------------- itens relacionado com nome habilidade -----------------------------------

	private Texto nomeApelo1, nomeApelo2, nomeApelo3, nomeApelo4;
	
	private boolean atualizarNomeHabili = false;
	private int selecaoNomeHab = 0;
	private int nomeHabAnterior = 4;
	
	// ----------------------- itens relacionado com Apelo -----------------------------------
	
	private Icones_interativos tipoDoApelo = new Icones_interativos(apelo.getX() + 8, apelo.getY() + 4);
	
	private Texto apeloQuantidade = new Texto(apelo.getX() + 136, apelo.getY() + 54/2 + 8, "Apelo:");
	
	private Icones_interativos apeloApelo1 = new Icones_interativos(apeloQuantidade.getX() + 65, apelo.getY() + 54/2 - 5);
	private Icones_interativos apeloApelo2 = new Icones_interativos(apeloApelo1.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo3 = new Icones_interativos(apeloApelo2.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo4 = new Icones_interativos(apeloApelo3.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo5 = new Icones_interativos(apeloApelo4.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo6 = new Icones_interativos(apeloApelo5.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo7 = new Icones_interativos(apeloApelo6.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo8 = new Icones_interativos(apeloApelo7.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo9 = new Icones_interativos(apeloApelo8.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo10 = new Icones_interativos(apeloApelo9.getX() + 25, apeloApelo1.getY());
	
	private Texto InterferenciaQuantidade = new Texto(apeloQuantidade.getX() + 330, apeloQuantidade.getY(), "Interferencia:");
	
	private Icones_interativos apeloInterf1 = new Icones_interativos(InterferenciaQuantidade.getX() + 126, apelo.getY() + 54/2 - 5);
	private Icones_interativos apeloInterf2 = new Icones_interativos(apeloInterf1.getX() + 25, apeloInterf1.getY());
	private Icones_interativos apeloInterf3 = new Icones_interativos(apeloInterf2.getX() + 25, apeloInterf1.getY());
	private Icones_interativos apeloInterf4 = new Icones_interativos(apeloInterf3.getX() + 25, apeloInterf1.getY());
	private Icones_interativos apeloInterf5 = new Icones_interativos(apeloInterf4.getX() + 25, apeloInterf1.getY());
	private Icones_interativos apeloInterf6 = new Icones_interativos(apeloInterf5.getX() + 25, apeloInterf1.getY());
	private Icones_interativos apeloInterf7 = new Icones_interativos(apeloInterf6.getX() + 25, apeloInterf1.getY());
	private Icones_interativos apeloInterf8 = new Icones_interativos(apeloInterf7.getX() + 25, apeloInterf1.getY());
	private Icones_interativos apeloInterf9 = new Icones_interativos(apeloInterf8.getX() + 25, apeloInterf1.getY());
	private Icones_interativos apeloInterf10 = new Icones_interativos(apeloInterf9.getX() + 25, apeloInterf1.getY());
	
		// ----------------------- itens relacionado com Descrição -----------------------------------

	private Texto textoDescricao1, textoDescricao2, textoDescricao3, textoDescricao4;
	
	// ------------------------------------------- d20 ----------------------------------------

	private int [] dados = {0, 0, 0, 0, 0};
	private boolean vezDados = false;
	
	private Icones_interativos imgDado1 = new Icones_interativos(tamanhoContorno + 760/2 - 578/2, tamanhoContorno + 30);
	private Icones_interativos imgDado2 = new Icones_interativos(imgDado1.getX() + 120, imgDado1.getY());
	private Icones_interativos imgDado3 = new Icones_interativos(imgDado2.getX() + 120, imgDado1.getY());
	private Icones_interativos imgDado4 = new Icones_interativos(imgDado3.getX() + 120, imgDado1.getY());
	private Icones_interativos imgDado5 = new Icones_interativos(imgDado4.getX() + 120, imgDado1.getY());
	
	// ------------------------------------------------------------------

	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7, tl8, tl15, tl16, tl17, tl20, tl21;
	
	private Timer timer;
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	
	public Batalha(int numAventureiro, int numAdversario, Escolha_de_adversario PaginaAnterior) {
		
		this.paginaAnterior = PaginaAnterior;
		aventureiro = numAventureiro;
		adversario = numAdversario;
		
		arrumarListaAventureiros();
		
		contorno.load("res\\contorno.png");
		
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\fundo.png");
		
		parabenizacaoVencedor = new Icones_interativos(16, 16);
		
		// ------------------------ textos do diálogo de aviso ------------------------------		
		
		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		txtDialogoAviso2.setFonte(new Font("Arial", Font.PLAIN, 28));
		
		// ------------------------ divisões da tela de batalha -------------------------

		animacao.load("res\\batalha\\animacao.png");
		
		campoBatalha1.load("res\\batalha\\campoBatalha.png");
		campoBatalha2.load("res\\batalha\\campoBatalha.png");
		campoBatalha3.load("res\\batalha\\campoBatalha.png");
		campoBatalha4.load("res\\batalha\\campoBatalha.png");
		campoBatalha5.load("res\\batalha\\campoBatalha.png");
		
		nomeHabilidade1.load("res\\batalha\\nomeHabilidadeSelecionado.png");
		nomeHabilidade2.load("res\\batalha\\nomeHabilidade.png");
		nomeHabilidade3.load("res\\batalha\\nomeHabilidade.png");
		nomeHabilidade4.load("res\\batalha\\nomeHabilidade.png");
		
		apelo.load("res\\batalha\\infoApelo.png");

		descricao.load("res\\batalha\\descricao.png");
		
		// ---------------------- itens relacionados com campo de batalha --------------------------
		
		repintarCampoBatalha();
		seletorAventureiro.load("res\\batalha\\seletorAventureiro.png");
		
		apagarCoracoes();
		coracao01.load("res\\batalha\\apelo.png");
		coracao02.load("res\\batalha\\apelo.png");
		coracao03.load("res\\batalha\\apelo.png");
		coracao04.load("res\\batalha\\apelo.png");
		coracao05.load("res\\batalha\\apelo.png");
		
		// ----------------------- itens relacionado com nome habilidade -----------------------------------
		
		nomeApelo1 = new Texto(nomeHabilidade1.getX() + 20, nomeHabilidade1.getY() + 54/2 + 8, NomeApelos[aventureiro][0]);
		nomeApelo2 = new Texto(nomeHabilidade2.getX() + 20, nomeHabilidade2.getY() + 54/2 + 8, NomeApelos[aventureiro][1]);
		nomeApelo3 = new Texto(nomeHabilidade3.getX() + 20, nomeHabilidade3.getY() + 54/2 + 8, NomeApelos[aventureiro][2]);
		nomeApelo4 = new Texto(nomeHabilidade4.getX() + 20, nomeHabilidade4.getY() + 54/2 + 8, NomeApelos[aventureiro][3]);
		
		// ----------------------- itens relacionado com Apelo -----------------------------------
		
		for(int i=0; i<4; i++) {
			switch (aventureiro) {
				case 0:
					matrizAventureiros[7][i] = apeloIgnis[3][i];
					break;
				case 1:
					matrizAventureiros[7][i] = apeloAyla[3][i];
					break;
				case 2:
					matrizAventureiros[7][i] = apeloRexthor[3][i];
					break;
				case 3:
					matrizAventureiros[7][i] = apeloKiki[3][i];
					break;
				case 4:
					matrizAventureiros[7][i] = apeloArius[3][i];
					break;
			}
		}
				
		apeloQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		InterferenciaQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		itensDoApelo();
		
		// ----------------------- itens relacionado com Descrição -----------------------------------

		textoDescricao1 = new Texto(descricao.getX() + 20, descricao.getY() + 30, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
		textoDescricao2 = new Texto(textoDescricao1.getX(), textoDescricao1.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
		textoDescricao3 = new Texto(textoDescricao2.getX(), textoDescricao2.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
		textoDescricao4 = new Texto(textoDescricao3.getX(), textoDescricao3.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
		
		textoDescricao1.setFonte(new Font("Arial", Font.PLAIN, 20));
		textoDescricao2.setFonte(new Font("Arial", Font.PLAIN, 20));
		textoDescricao3.setFonte(new Font("Arial", Font.PLAIN, 20));
		textoDescricao4.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		// ------------------------------------------------------------------------

		timer = new Timer(5, this);
		timer.start();
		
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									volta para tela anterior								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoVoltar(int codigo) {
		if(dialogoAviso.getImagem() == null && codigo == KeyEvent.VK_Z) {
			
			dialogoAviso.load("res\\dialogo.png");
			bntSimDialogoAviso.load("res\\bntSim.png");
			bntNaoDialogoAviso.load("res\\bntNao2.png");
			bntSimNaoDialgoAviso = true;
			
			txtDialogoAviso.setTexto("Se você voltar a luta será encerrada.");
			txtDialogoAviso2.setTexto("Deseja continuar?");
			
		}else if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) {
			bntSimNaoDialgoAviso = !bntSimNaoDialgoAviso;
			
			bntSimDialogoAviso.load("res\\bntsim" + (bntSimNaoDialgoAviso == true ? "" : "2") + ".png");
			bntNaoDialogoAviso.load("res\\bntnao" + (bntSimNaoDialgoAviso == true ? "2" : "") + ".png");
			
		} else if(dialogoAviso.getImagem() != null && (codigo == KeyEvent.VK_X || (codigo == KeyEvent.VK_Z && bntSimNaoDialgoAviso == false))) {
			
			dialogoAviso.setImagem(null);
			bntSimDialogoAviso.setImagem(null);
			bntNaoDialogoAviso.setImagem(null);
			
			txtDialogoAviso.setTexto(" ");
			txtDialogoAviso2.setTexto(" ");
			
		
		}else if(codigo == KeyEvent.VK_Z && dialogoAviso.getImagem() != null) {
			
			JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        janelaPrincipal.add(paginaAnterior);
	        janelaPrincipal.setTitle("Escolha de Adversário");
	        janelaPrincipal.revalidate();
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						mostra a quantidade de apelo e interferência						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void itensDoApelo() {
		tipoDoApelo.load("res\\batalha\\" + (matrizAventureiros[7][selecaoNomeHab] == 0 ? "tipoDoApelo1.png" : "tipoDoApelo2.png"));
		
		apeloApelo1.setImagem(null); apeloApelo2.setImagem(null); apeloApelo3.setImagem(null); apeloApelo4.setImagem(null); apeloApelo5.setImagem(null);
		apeloApelo6.setImagem(null); apeloApelo7.setImagem(null); apeloApelo8.setImagem(null); apeloApelo9.setImagem(null); apeloApelo10.setImagem(null);
		
		apeloInterf1.setImagem(null); apeloInterf2.setImagem(null); apeloInterf3.setImagem(null); apeloInterf4.setImagem(null); apeloInterf5.setImagem(null);
		apeloInterf6.setImagem(null); apeloInterf7.setImagem(null); apeloInterf8.setImagem(null); apeloInterf9.setImagem(null); apeloInterf10.setImagem(null);
		
		if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 1) {
			apeloApelo1.load("res\\batalha\\apelo.png");
			if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 2) {
				apeloApelo2.load("res\\batalha\\apelo.png");
				if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 3) {
					apeloApelo3.load("res\\batalha\\apelo.png");
					if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 4) {
						apeloApelo4.load("res\\batalha\\apelo.png");
						if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 5) {
							apeloApelo5.load("res\\batalha\\apelo.png");
							if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 6) {
								apeloApelo6.load("res\\batalha\\apelo.png");
								if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 7) {
									apeloApelo7.load("res\\batalha\\apelo.png");
									if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 8) {
										apeloApelo8.load("res\\batalha\\apelo.png");
										if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 9) {
											apeloApelo9.load("res\\batalha\\apelo.png");
											if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 10) {
												apeloApelo10.load("res\\batalha\\apelo.png");
		}}}}}}}}}}
		
		if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 1) {
			apeloInterf1.load("res\\batalha\\interferencia.png");
			if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 2) {
				apeloInterf2.load("res\\batalha\\interferencia.png");
				if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 3) {
					apeloInterf3.load("res\\batalha\\interferencia.png");
					if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 4) {
						apeloInterf4.load("res\\batalha\\interferencia.png");
						if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 5) {
							apeloInterf5.load("res\\batalha\\interferencia.png");
							if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 6) {
								apeloInterf6.load("res\\batalha\\interferencia.png");
								if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 7) {
									apeloInterf7.load("res\\batalha\\interferencia.png");
									if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 8) {
										apeloInterf8.load("res\\batalha\\interferencia.png");
										if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 9) {
											apeloInterf9.load("res\\batalha\\interferencia.png");
											if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 10) {
												apeloInterf10.load("res\\batalha\\interferencia.png");
		}}}}}}}}}}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							dispara quando as teclas são  pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		Efeito6[0] = 0; Efeito6[1] = 0;
		
		// -------------------- abre e fecha o menu -------------------- \
		if(codigo == KeyEvent.VK_ESCAPE && dialogoAviso.getImagem() == null) {
			mostrarMenu = !mostrarMenu;
			
			if(mostrarMenu == true) {
				contMenu = 0;
				sombreadorMenu.load("res\\sombreador.png");
				fundoMenu.load("res\\menu.png");
				bntMenu.load("res\\bntMenu2.png");
				bntRegras.load("res\\bntRegras1.png");
				 bntVoltar.load("res\\bntVoltar1.png");
				
			} else {
				sombreadorMenu.setImagem(null);
				fundoMenu.setImagem(null);
				bntMenu.setImagem(null);
				bntRegras.setImagem(null);
				 bntVoltar.setImagem(null);
			}
			
		// ----------------------- muda a seleção das opções do menu -------------------------- \
		}else if((codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) && mostrarMenu == true && dialogoAviso.getImagem() == null) {
			if(codigo == KeyEvent.VK_UP) {
				if(contMenu == 0) {contMenu = 2;} else {contMenu --;}
			}else if(codigo == KeyEvent.VK_DOWN) {
				if(contMenu == 2) {contMenu = 0;} else {contMenu ++;}
			}
			
			bntMenu.load("res\\bntMenu1.png");
			bntRegras.load("res\\bntRegras1.png");
			bntVoltar.load("res\\bntVoltar1.png");
			
			switch (contMenu) {
				case 0:
					bntMenu.load("res\\bntMenu2.png");
					break;
				case 1:
					bntRegras.load("res\\bntRegras2.png");
					break;
				case 2:
					bntVoltar.load("res\\bntVoltar2.png");
					break;
			}
			
		// ---------- encaminha para a função que controla o botão voltar do menu --------- \
		}else if(mostrarMenu == true && contMenu == 2) {
			dialogoVoltar(codigo);
		
		// ---------- muda a seleção dos ícones dos personagens no mapa para cima e para baixo --------- \
		} else if((codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) && mostrarMenu == false && dialogoAviso.getImagem() == null && comecarAnimacaoCoracao == 0) {
			
			if(codigo == KeyEvent.VK_UP) {
				if(selecaoNomeHab == 0) {selecaoNomeHab = 3;} 
				else{selecaoNomeHab --;}
				
			} else if(codigo == KeyEvent.VK_DOWN) {
				if(selecaoNomeHab == 3) {selecaoNomeHab = 0;} 
				else{selecaoNomeHab ++;}
			}
			
			itensDoApelo();
			
			nomeHabilidade1.load("res\\batalha\\" + (nomeHabAnterior == 0 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade2.load("res\\batalha\\" + (nomeHabAnterior == 1 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade3.load("res\\batalha\\" + (nomeHabAnterior == 2 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade4.load("res\\batalha\\" + (nomeHabAnterior == 3 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			
			switch (selecaoNomeHab) {
			case 0:
				nomeHabilidade1.load("res\\batalha\\nomeHabilidadeSelecionado.png");
			    break;
			case 1:
				nomeHabilidade2.load("res\\batalha\\nomeHabilidadeSelecionado.png");
				break;
			case 2:
				nomeHabilidade3.load("res\\batalha\\nomeHabilidadeSelecionado.png");
			    break;
			case 3:
				nomeHabilidade4.load("res\\batalha\\nomeHabilidadeSelecionado.png");
			    break;
			}
			
			textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][0]);
			textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][1]);
			textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][2]);
			textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][3]);
		
		// ------------------------------- seleciona a habilidade ---------------------------------
		} else if(codigo == KeyEvent.VK_Z && contEtapasBatalha < 5 && comecarAnimacaoCoracao == 0 && mostrarMenu == false && dialogoAviso.getImagem() == null) {
			contEtapasBatalha++;
			atualizarNomeHabili = false;
			
			arrayAleatorioHabAdver[0] = aleatorioHabAdver.nextInt(4); arrayAleatorioHabAdver[1] = aleatorioHabAdver.nextInt(4); arrayAleatorioHabAdver[2] = aleatorioHabAdver.nextInt(4); arrayAleatorioHabAdver[3] = aleatorioHabAdver.nextInt(4); arrayAleatorioHabAdver[4] = aleatorioHabAdver.nextInt(4);
			
			// ------------------------------- soma pontos ---------------------------------

			// --------------------------- pega todas as informações iniciais e coloca as informações de cada cão --------------------
			for(int i=0; i<5;i++) {
			
				matrizAventureiros[2][i] = (matrizAventureiros[0][i] == 0 ? apeloIgnis : (matrizAventureiros[0][i] == 1 ? apeloAyla : (matrizAventureiros[0][i] == 2 ? apeloRexthor : (matrizAventureiros[0][i] == 3 ? apeloKiki : apeloArius))))[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
				matrizAventureiros[3][i] = (matrizAventureiros[0][i] == 0 ? apeloIgnis : (matrizAventureiros[0][i] == 1 ? apeloAyla : (matrizAventureiros[0][i] == 2 ? apeloRexthor : (matrizAventureiros[0][i] == 3 ? apeloKiki : apeloArius))))[1][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
				matrizAventureiros[4][i] = (matrizAventureiros[0][i] == 0 ? apeloIgnis : (matrizAventureiros[0][i] == 1 ? apeloAyla : (matrizAventureiros[0][i] == 2 ? apeloRexthor : (matrizAventureiros[0][i] == 3 ? apeloKiki : apeloArius))))[2][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
				matrizAventureiros[5][i] = (matrizAventureiros[0][i] == 0 ? apeloIgnis : (matrizAventureiros[0][i] == 1 ? apeloAyla : (matrizAventureiros[0][i] == 2 ? apeloRexthor : (matrizAventureiros[0][i] == 3 ? apeloKiki : apeloArius))))[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab] + matrizAventureiros[5][i];
				
				apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab) ? 1 : 0);
				
				matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab);
				matrizAventureiros[7][i] = (matrizAventureiros[0][i] == 0 ? apeloIgnis : (matrizAventureiros[0][i] == 1 ? apeloAyla : (matrizAventureiros[0][i] == 1 ? apeloRexthor : (matrizAventureiros[0][i] == 1 ? apeloKiki : apeloArius))))[3][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
				
				// ------------------ coloca o dano de -2 no apelo atual da rodada se a habilidade for repetida -------------
				if(apeloRepetido[i] == 1) {
					matrizAventureiros[5][i] = matrizAventureiros[5][i] - 2;
				}
				
				// ------------- coloca o dano de -1 no apelo atual da rodada se a habilidade conflitar com o efeitodo chefe da fase ----------------
				if((adversario == 0 && matrizAventureiros[4][i] != -1) || (adversario == 1 && matrizAventureiros[4][i] == -1) || (adversario == 2 && matrizAventureiros[7][i] == 1) || (adversario == 3 && matrizAventureiros[4][i] != -1) || (adversario == 4 && matrizAventureiros[4][i] == -1)) {
					efeitoChefeDeFase[i] = 1;
					
					if(adversario == 0 || adversario == 1) {
						matrizAventureiros[5][i] --;
					}
					else {
						matrizAventureiros[5][i] ++;
					}
				}
			}
			
			// --------------------------- subtrair efeito ----------------------------------
			
			for(int i=0; i<5; i++) {
				
				//0: todos acima
				if(matrizAventureiros[4][i] == 0 ) {
					if(i > 0) {
						matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][i];
						if(i > 1) {
							 matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][i];
							 if(i > 2) {
								 matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][i];
								 if(i > 3) {
									 matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][i];
				}}}}}
				
				//1: todos abaixo
				if(matrizAventureiros[4][i] == 1 ) {
					if(i < 4) {
						matrizAventureiros[5][4] = matrizAventureiros[5][4] - matrizAventureiros[3][i];
						if(i < 3) {
							matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][i];
							if(i < 2) {
								matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][i];
								if(i == 1) {
									matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][i];
				}}}}}
				
				//2: um acima,
				if(matrizAventureiros[4][i] == 2 ) {
					switch (i) {
						case 1:
							matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][i];
							break;
						case 2:
							matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][i];
							break;
						case 3:
							matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][i];
							break;
						case 4:
							matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][i];
							break;
					}
				}
				
				//3: primeiro,
				if(matrizAventureiros[4][i] == 3 ) {
					matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][i];
				}
				
				//4: zera seus pontos negativos,
				if(matrizAventureiros[4][i] == 4 && matrizAventureiros[5][i] < 0) {
					danoEfeito4[i] = matrizAventureiros[5][i]; matrizAventureiros[5][i] = 0;
				}
				
				//5: um acima e um abaixo,
				if(matrizAventureiros[4][i] == 5 ) {
					if(i > 0) {
						matrizAventureiros[5][i - 1] = matrizAventureiros[5][i - 1] - matrizAventureiros[3][i];
					}else if(i < 4) {
						matrizAventureiros[5][i + 1] = matrizAventureiros[5][i + 1] - matrizAventureiros[3][i];
					}
				}
			}
						
			// --------------------------- soma o apelo atual ----------------------------------

			matrizAventureiros[1][0] = matrizAventureiros[1][0] + (matrizAventureiros[5][0] < -10 ? -10 : (matrizAventureiros[5][0] > 10 ? 10 : matrizAventureiros[5][0]));
			matrizAventureiros[1][1] = matrizAventureiros[1][1] + (matrizAventureiros[5][1] < -10 ? -10 : (matrizAventureiros[5][1] > 10 ? 10 : matrizAventureiros[5][1]));
			matrizAventureiros[1][2] = matrizAventureiros[1][2] + (matrizAventureiros[5][2] < -10 ? -10 : (matrizAventureiros[5][2] > 10 ? 10 : matrizAventureiros[5][2]));
			matrizAventureiros[1][3] = matrizAventureiros[1][3] + (matrizAventureiros[5][3] < -10 ? -10 : (matrizAventureiros[5][3] > 10 ? 10 : matrizAventureiros[5][3]));
			matrizAventureiros[1][4] = matrizAventureiros[1][4] + (matrizAventureiros[5][4] < -10 ? -10 : (matrizAventureiros[5][4] > 10 ? 10 : matrizAventureiros[5][4]));
			
			matrizAventureiros[1][0] = matrizAventureiros[1][0] < 0 ? (matrizAventureiros[4][0] == 6 ?  matrizAventureiros[1][0] : 0) : matrizAventureiros[1][0]; 
			matrizAventureiros[1][1] = matrizAventureiros[1][1] < 0 ? (matrizAventureiros[4][1] == 6 ?  matrizAventureiros[1][1] : 0) : matrizAventureiros[1][1]; 
			matrizAventureiros[1][2] = matrizAventureiros[1][2] < 0 ? (matrizAventureiros[4][2] == 6 ?  matrizAventureiros[1][2] : 0) : matrizAventureiros[1][2]; 
			matrizAventureiros[1][3] = matrizAventureiros[1][3] < 0 ? (matrizAventureiros[4][3] == 6 ?  matrizAventureiros[1][3] : 0) : matrizAventureiros[1][3]; 
			matrizAventureiros[1][4] = matrizAventureiros[1][4] < 0 ? (matrizAventureiros[4][4] == 6 ?  matrizAventureiros[1][4] : 0) : matrizAventureiros[1][4]; 
			
			
			System.out.println("pessoa: " +matrizAventureiros[0][0] + "  " + matrizAventureiros[0][1] + "  " + matrizAventureiros[0][2] + "  " + matrizAventureiros[0][3] + "  " + matrizAventureiros[0][4]);
			System.out.println("apelo:  " +matrizAventureiros[2][0] + "  " + matrizAventureiros[2][1] + "  " + matrizAventureiros[2][2] + "  " + matrizAventureiros[2][3] + "  " + matrizAventureiros[2][4]);
			System.out.println("dano dado:   " + matrizAventureiros[3][0] + "  " + matrizAventureiros[3][1] + "  " + matrizAventureiros[3][2] + "  " + matrizAventureiros[3][3] + "  " + matrizAventureiros[3][4]);
			System.out.println("efeito dano:   " + matrizAventureiros[4][0] + "  " + matrizAventureiros[4][1] + "  " + matrizAventureiros[4][2] + "  " + matrizAventureiros[4][3] + "  " + matrizAventureiros[4][4]);
			System.out.println("repetido?: " +apeloRepetido[0] + "  " + apeloRepetido[1] + "  " + apeloRepetido[2] + "  " + apeloRepetido[3] + "  " + apeloRepetido[4]);
			System.out.println("total:  " +matrizAventureiros[1][0] + "  " + matrizAventureiros[1][1] + "  " + matrizAventureiros[1][2] + "  " + matrizAventureiros[1][3] + "  " + matrizAventureiros[1][4]);

			comecarAnimacaoCoracao = 1;
			animacaoFileira = 0;
			
		// --------------------- termina a parabenizarão para a escolha de adversário ------------------
		} else if(codigo == KeyEvent.VK_Z && contEtapasBatalha == 6 ) {
		
			JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        janelaPrincipal.add(paginaAnterior);
	        janelaPrincipal.setTitle("Escolha de Adversário");
	        
	        if(matrizAventureiros[0][0] == aventureiro) {
        		if(paginaAnterior.derrotados[adversario] == false) {
        			paginaAnterior.derrotados[adversario] = true;
        		}
	        }

	        paginaAnterior.mostrarEstrela();
	        janelaPrincipal.revalidate();
		}
	}

	/* ---------------------------------------------------------------------------------------- \
	|  							coloca os efeitos nos cães										|
	\ ---------------------------------------------------------------------------------------- */
	
	public void interferirNosAdversarios(int dano, int efeito, int posicaoNaLista) {
		//(-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro)
		
		valoresInterferencia[0] = dano; valoresInterferencia[1] = efeito; valoresInterferencia[2] = posicaoNaLista;
		contTempoApelo = 0;
		contTempoInter = 10;
	
		// ------------------ 0: todos acima ------------------------------
		if(efeito == 0 && posicaoNaLista > 0 && TerminouLoopEfeitoInterf == 0) {
			for(int i2=0; i2<5; i2++) {
				if(animacaoEfeitoConcluido[i2] == true) {i2++;}
				if(animacaoEfeitoConcluido[posicaoNaLista-1] == true && TerminouLoopEfeitoInterf == 0) {
					TerminouLoopEfeitoInterf = 1;
					vezDoAventureiro = vezDoAventureiro +1;
					animacaoFileira = vezDoAventureiro * 2; 
					if(vezDoAventureiro == 5) {vezDoAventureiro = 0; animacaoFileira = 20;}
					animacaoEfeitoConcluido[0]=false;
					animacaoEfeitoConcluido[1]=false;
					animacaoEfeitoConcluido[2]=false;
					animacaoEfeitoConcluido[3]=false;
					animacaoEfeitoConcluido[4]=false;
					break;
				}
				if(i2 < posicaoNaLista && animacaoEfeitoConcluido[i2] == false && (i2 - 1 != -1 ? animacaoEfeitoConcluido[i2-1] == true: true)) {
					interferenciasRecebidas[i2][posicaoNaLista] = dano;
					switch (i2) {
						case 0: animacaoFileira = 10; break;
						case 1: animacaoFileira = 12; break;
						case 2: animacaoFileira = 14; break;
						case 3: animacaoFileira = 16; break;
						case 4: animacaoFileira = 18; break;
					}
				}
			}
		
		// ----------------------------- 1: todos abaixo ---------------------
		} else if(efeito == 1 && posicaoNaLista < 4 && TerminouLoopEfeitoInterf == 0) {
			for(int i3=0; i3<5; i3++) {
				if(animacaoEfeitoConcluido[i3] == true) {i3++;}
				if(animacaoEfeitoConcluido[4] == true && TerminouLoopEfeitoInterf == 0) {
					TerminouLoopEfeitoInterf = 1;
					vezDoAventureiro = vezDoAventureiro +1;
					animacaoFileira = vezDoAventureiro * 2;
					if(vezDoAventureiro == 5) {vezDoAventureiro = 0; animacaoFileira = 20;}
					animacaoEfeitoConcluido[0]=false;
					animacaoEfeitoConcluido[1]=false;
					animacaoEfeitoConcluido[2]=false;
					animacaoEfeitoConcluido[3]=false;
					animacaoEfeitoConcluido[4]=false;
					break;
				}
				if(i3 > posicaoNaLista && i3 < 5 && animacaoEfeitoConcluido[i3] == false && (i3 - 1 != 0 ? (i3-1 == posicaoNaLista ? true: animacaoEfeitoConcluido[i3-1] == true): true)) {
					interferenciasRecebidas[i3][posicaoNaLista] = dano;
					switch (i3) {
						case 0: animacaoFileira = 10; break;
						case 1: animacaoFileira = 12; break;
						case 2: animacaoFileira = 14; break;
						case 3: animacaoFileira = 16; break;
						case 4: animacaoFileira = 18; break;
					}
				}
			}
		
		// ----------------------------- 2: um acima ----------------------------------
		} else if(efeito == 2 && posicaoNaLista -1 >= 0  && TerminouLoopEfeitoInterf == 0) {
			if(animacaoEfeitoConcluido[posicaoNaLista -1] == true && TerminouLoopEfeitoInterf == 0) {
				animacaoEfeitoConcluido[0]=false;
				animacaoEfeitoConcluido[1]=false;
				animacaoEfeitoConcluido[2]=false;
				animacaoEfeitoConcluido[3]=false;
				animacaoEfeitoConcluido[4]=false;
				
				TerminouLoopEfeitoInterf = 1; 
				
				vezDoAventureiro = vezDoAventureiro +1;
				animacaoFileira = vezDoAventureiro * 2;
				if(vezDoAventureiro == 5) {vezDoAventureiro = 0; animacaoFileira = 20;}
			}else {
				interferenciasRecebidas[posicaoNaLista -1][posicaoNaLista] = dano;
				switch (posicaoNaLista -1) {
					case 0: animacaoFileira = 10; break;
					case 1: animacaoFileira = 12; break;
					case 2: animacaoFileira = 14; break;
					case 3: animacaoFileira = 16; break;
					case 4: animacaoFileira = 18; break;
				}
			}
			
		// ---------------------------- 3: primeiro ---------------------------------
		} else if(efeito == 3 && posicaoNaLista != 0  && TerminouLoopEfeitoInterf == 0) {
			if(animacaoEfeitoConcluido[0] == true && TerminouLoopEfeitoInterf == 0) {
				animacaoEfeitoConcluido[0]=false;
				animacaoEfeitoConcluido[1]=false;
				animacaoEfeitoConcluido[2]=false;
				animacaoEfeitoConcluido[3]=false;
				animacaoEfeitoConcluido[4]=false;
				
				TerminouLoopEfeitoInterf = 1; 
				
				vezDoAventureiro = vezDoAventureiro +1;
				animacaoFileira = vezDoAventureiro * 2;
				if(vezDoAventureiro == 5) {vezDoAventureiro = 0; animacaoFileira = 20;}
			}else {
				interferenciasRecebidas[0][posicaoNaLista] = dano;
				animacaoFileira = 10;
			}
		//4: zera seus pontos negativos,
		}else if(efeito == 4 && ((posicaoNaLista == 0 ? coracao11 : (posicaoNaLista == 1 ? coracao21 : (posicaoNaLista == 2 ? coracao31 : (posicaoNaLista == 3 ? coracao41 : coracao51)))).getImagem() != null && ((posicaoNaLista == 0 ? coracao11 : (posicaoNaLista == 1 ? coracao21 : (posicaoNaLista == 2 ? coracao31 : (posicaoNaLista == 3 ? coracao41 : coracao51))))).getReferencia().toString() == "res\\batalha\\interferencia.png")) { 
			matrizAventureiros[2][posicaoNaLista] = 0 - (danoEfeito4[posicaoNaLista]);
			
			switch (posicaoNaLista) {
				case 0: animacaoFileira = 0; break;
				case 1: animacaoFileira = 2; break;
				case 2: animacaoFileira = 4; break;
				case 3: animacaoFileira = 6; break;
				case 4: animacaoFileira = 8; break;
			}
		
		// -------------------- 5: um acima e um abaixo -----------------------------
		} else if(efeito == 5 && TerminouLoopEfeitoInterf == 0) {
			if((posicaoNaLista -1 >= 0 ? animacaoEfeitoConcluido[posicaoNaLista -1] == true : true) && (posicaoNaLista + 1 <= 4 ? animacaoEfeitoConcluido[posicaoNaLista +1] == true : true) && TerminouLoopEfeitoInterf == 0) {
				
				TerminouLoopEfeitoInterf = 1; 
				vezDoAventureiro = vezDoAventureiro +1;
				animacaoFileira = vezDoAventureiro * 2;
				if(vezDoAventureiro == 5) {vezDoAventureiro = 0; animacaoFileira = 20;}
				animacaoEfeitoConcluido[0]=false;
				animacaoEfeitoConcluido[1]=false;
				animacaoEfeitoConcluido[2]=false;
				animacaoEfeitoConcluido[3]=false;
				animacaoEfeitoConcluido[4]=false;
				
			} else if(posicaoNaLista -1 >= 0 && animacaoEfeitoConcluido[posicaoNaLista -1] == false) {
				interferenciasRecebidas[posicaoNaLista -1][posicaoNaLista] = dano;
				switch (posicaoNaLista -1) {
					case 0: animacaoFileira = 10; break;
					case 1: animacaoFileira = 12; break;
					case 2: animacaoFileira = 14; break;
					case 3: animacaoFileira = 16; break;
					case 4: animacaoFileira = 18; break;
				}
			} else if(posicaoNaLista + 1 <= 4 && animacaoEfeitoConcluido[posicaoNaLista +1] == false) {
				interferenciasRecebidas[posicaoNaLista +1][posicaoNaLista] = dano;
				switch (posicaoNaLista +1) {
					case 0: animacaoFileira = 10; break;
					case 1: animacaoFileira = 12; break;
					case 2: animacaoFileira = 14; break;
					case 3: animacaoFileira = 16; break;
					case 4: animacaoFileira = 18; break;
				}
			}
						
		// ------------------------------- 6: jogar d20 ----------------------------------
		}else if(efeito == 6 && (Efeito6[matrizAventureiros[0][posicaoNaLista] == 2 ? 0 : 1] == 0)) {
			Efeito6[matrizAventureiros[0][posicaoNaLista] == 2 ? 0 : 1] = 1;

			dados[0] = 0; dados[1] = 0; dados[2] = 0; dados[3] = 0; dados[4] = 0;
			int temporario;
			int conta = 0;
			
			dados[0]= aleatorioHabAdver.nextInt(20)+1; 
			
			for(int i=0; i<4; i++) {
				conta = 0;
				
				while(conta == 0) {
					temporario = aleatorioHabAdver.nextInt(20)+1;
					
					if(temporario != dados[0] && temporario != dados[1] && temporario != dados[2] && temporario != dados[3]) {
						dados[i+1]= temporario;
						conta = 1;
					}
				}
			}
			
			System.out.println(dados[0] + " " + dados[1] + " " + dados[2] + " " + dados[3] + " " + dados[4]);
			
			vezDados = true;
			
			if((matrizAventureiros[0][posicaoNaLista] == 2 && (dados[0] == 20 || dados[1] == 20 || dados[2] == 20 || dados[3] == 20 || dados[4] == 20)) || (matrizAventureiros[0][posicaoNaLista] == 4 && (dados[0] == 5 || dados[1] == 5 || dados[2] == 5 || dados[3] == 5 || dados[4] == 5))) {
				
				matrizAventureiros[2][posicaoNaLista] = matrizAventureiros[3][posicaoNaLista];
				matrizAventureiros[1][posicaoNaLista] = matrizAventureiros[1][posicaoNaLista] + matrizAventureiros[3][posicaoNaLista];
				matrizAventureiros[5][posicaoNaLista] = matrizAventureiros[5][posicaoNaLista] + matrizAventureiros[3][posicaoNaLista];
				matrizAventureiros[1][posicaoNaLista] = matrizAventureiros[1][posicaoNaLista] < 0 ? 0 : matrizAventureiros[1][posicaoNaLista]; 
				
				switch (posicaoNaLista) {
					case 0: animacaoFileira = 0; break;
					case 1: animacaoFileira = 2; break;
					case 2: animacaoFileira = 4; break;
					case 3: animacaoFileira = 6; break;
					case 4: animacaoFileira = 8; break;
				}
			}
			else {
				switch (posicaoNaLista) {
					case 0: animacaoFileira = 0; break;
					case 1: animacaoFileira = 2; break;
					case 2: animacaoFileira = 4; break;
					case 3: animacaoFileira = 6; break;
					case 4: animacaoFileira = 8; break;
				}
			}
			
		// ------------------------- -1 nenhum --------------------------------
		} else if(TerminouLoopEfeitoInterf == 0 || (efeito == 4 && ((posicaoNaLista == 0 ? coracao11 : (posicaoNaLista == 1 ? coracao21 : (posicaoNaLista == 2 ? coracao31 : (posicaoNaLista == 3 ? coracao41 : coracao51)))).getImagem() == null))){
			System.out.println("efeito -1");
			animacaoEfeitoConcluido[0]=false;
			animacaoEfeitoConcluido[1]=false;
			animacaoEfeitoConcluido[2]=false;
			animacaoEfeitoConcluido[3]=false;
			animacaoEfeitoConcluido[4]=false;
			
			TerminouLoopEfeitoInterf = 1;
			Efeito4 =0;
			vezDoAventureiro = vezDoAventureiro +1;
			animacaoFileira = vezDoAventureiro*2;
			if(vezDoAventureiro == 5) {vezDoAventureiro = 0; animacaoFileira = 20;}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						coloca as imagens dos Cães na ordem correta							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void repintarCampoBatalha() {
		
		for(int i=0; i<5; i++) {
			(i == 0 ? iconeCampoBatalha1 : (i == 1 ? iconeCampoBatalha2 : (i == 2 ? iconeCampoBatalha3 : (i == 3 ? iconeCampoBatalha4 : iconeCampoBatalha5)))).load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][i]] + "\\iconeCampoBatalha.png");
		}
		
		seletorAventureiro.setY((posicaoAventureiro == 0 ? iconeCampoBatalha1.getY() : (posicaoAventureiro == 1 ? iconeCampoBatalha2.getY() : (posicaoAventureiro == 2 ? iconeCampoBatalha3.getY() : (posicaoAventureiro == 3 ? iconeCampoBatalha4.getY() : iconeCampoBatalha5.getY())))) - 3);		
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						apaga os apelos adquiridos no campo de batalha						|
	\ ---------------------------------------------------------------------------------------- */
	
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
	
	/* ---------------------------------------------------------------------------------------- \
   	|  								organiza os Cães antes da batalha							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void arrumarListaAventureiros() {
		
		if(aventureiro == adversario) {
			matrizAventureiros[0][0] = aventureiro;
			matrizAventureiros[0][1] = adversario + 1 < 5 ? adversario + 1 : adversario + 1 - 5 ;
			matrizAventureiros[0][2] = adversario + 2 < 5 ? adversario + 2 : adversario + 2 - 5 ;
			matrizAventureiros[0][3] = adversario + 3 < 5 ? adversario + 3 : adversario + 3 - 5 ;
			matrizAventureiros[0][4] = adversario != 0 ? adversario - 1 : 4;
			posicaoAventureiro = 0;
			
		} else {
			int adv = adversario;
			int ave = aventureiro;
			
			for(int i=0; i<4; i++) {
				matrizAventureiros[0][i] = adv;
				
				adv = adv + 1 < 5 ? adv +1 : adv + 1 - 5;
				if(adv == ave) {
					adv = adv+1 < 5 ? adv+1 : adv+1 -5;
				}
			}
			
			matrizAventureiros[0][4] = ave;
			posicaoAventureiro = 4;
		}		
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		
		// ------------------------ divisões da tela de batalha -------------------------
		graficos.drawImage(animacao.getImagem(), animacao.getX(), animacao.getY(), this);
		
		graficos.drawImage(campoBatalha1.getImagem(), campoBatalha1.getX(), campoBatalha1.getY(), this);
		graficos.drawImage(campoBatalha2.getImagem(), campoBatalha2.getX(), campoBatalha2.getY(), this);
		graficos.drawImage(campoBatalha3.getImagem(), campoBatalha3.getX(), campoBatalha3.getY(), this);
		graficos.drawImage(campoBatalha4.getImagem(), campoBatalha4.getX(), campoBatalha4.getY(), this);
		graficos.drawImage(campoBatalha5.getImagem(), campoBatalha5.getX(), campoBatalha5.getY(), this);
		
		graficos.drawImage(nomeHabilidade1.getImagem(), nomeHabilidade1.getX(), nomeHabilidade1.getY(), this);
		graficos.drawImage(nomeHabilidade2.getImagem(), nomeHabilidade2.getX(), nomeHabilidade2.getY(), this);
		graficos.drawImage(nomeHabilidade3.getImagem(), nomeHabilidade3.getX(), nomeHabilidade3.getY(), this);
		graficos.drawImage(nomeHabilidade4.getImagem(), nomeHabilidade4.getX(), nomeHabilidade4.getY(), this);
		
		graficos.drawImage(apelo.getImagem(), apelo.getX(), apelo.getY(), this);
		
		graficos.drawImage(descricao.getImagem(), descricao.getX(), descricao.getY(), this);

		// --------------------------------- campo batalha e habilidades usadas -----------------------------------------
		tl8 = new TextLayout(txtEfeitoFase.getTexto(), txtEfeitoFase.getFonte(), frc);
	    tl8.draw(graficos, txtEfeitoFase.getX(), txtEfeitoFase.getY());
	    graficos.drawImage(efeitoFase.getImagem(), efeitoFase.getX(), efeitoFase.getY(), this);
		
		graficos.drawImage(iconeCampoBatalha1.getImagem(), iconeCampoBatalha1.getX(), iconeCampoBatalha1.getY(), this);
		graficos.drawImage(iconeCampoBatalha2.getImagem(), iconeCampoBatalha2.getX(), iconeCampoBatalha2.getY(), this);
		graficos.drawImage(iconeCampoBatalha3.getImagem(), iconeCampoBatalha3.getX(), iconeCampoBatalha3.getY(), this);
		graficos.drawImage(iconeCampoBatalha4.getImagem(), iconeCampoBatalha4.getX(), iconeCampoBatalha4.getY(), this);
		graficos.drawImage(iconeCampoBatalha5.getImagem(), iconeCampoBatalha5.getX(), iconeCampoBatalha5.getY(), this);

		graficos.drawImage(seletorAventureiro.getImagem(), seletorAventureiro.getX(), seletorAventureiro.getY(), this);
		
		graficos.drawImage(coracao01.getImagem(), coracao01.getX(), coracao01.getY(), this);
		graficos.drawImage(coracao02.getImagem(), coracao02.getX(), coracao02.getY(), this);
		graficos.drawImage(coracao03.getImagem(), coracao03.getX(), coracao03.getY(), this);
		graficos.drawImage(coracao04.getImagem(), coracao04.getX(), coracao04.getY(), this);
		graficos.drawImage(coracao05.getImagem(), coracao05.getX(), coracao05.getY(), this);
		
		graficos.drawImage(coracao11.getImagem(), coracao11.getX(), coracao11.getY(), this);
		graficos.drawImage(coracao12.getImagem(), coracao12.getX(), coracao12.getY(), this);
		graficos.drawImage(coracao13.getImagem(), coracao13.getX(), coracao13.getY(), this);
		graficos.drawImage(coracao14.getImagem(), coracao14.getX(), coracao14.getY(), this);
		graficos.drawImage(coracao15.getImagem(), coracao15.getX(), coracao15.getY(), this);
		graficos.drawImage(coracao16.getImagem(), coracao16.getX(), coracao16.getY(), this);
		graficos.drawImage(coracao17.getImagem(), coracao17.getX(), coracao17.getY(), this);
		graficos.drawImage(coracao18.getImagem(), coracao18.getX(), coracao18.getY(), this);
		graficos.drawImage(coracao19.getImagem(), coracao19.getX(), coracao19.getY(), this);
		graficos.drawImage(coracao110.getImagem(), coracao110.getX(), coracao110.getY(), this);
		
		graficos.drawImage(coracao21.getImagem(), coracao21.getX(), coracao21.getY(), this);
		graficos.drawImage(coracao22.getImagem(), coracao22.getX(), coracao22.getY(), this);
		graficos.drawImage(coracao23.getImagem(), coracao23.getX(), coracao23.getY(), this);
		graficos.drawImage(coracao24.getImagem(), coracao24.getX(), coracao24.getY(), this);
		graficos.drawImage(coracao25.getImagem(), coracao25.getX(), coracao25.getY(), this);
		graficos.drawImage(coracao26.getImagem(), coracao26.getX(), coracao26.getY(), this);
		graficos.drawImage(coracao27.getImagem(), coracao27.getX(), coracao27.getY(), this);
		graficos.drawImage(coracao28.getImagem(), coracao28.getX(), coracao28.getY(), this);
		graficos.drawImage(coracao29.getImagem(), coracao29.getX(), coracao29.getY(), this);
		graficos.drawImage(coracao210.getImagem(), coracao210.getX(), coracao210.getY(), this);
		
		graficos.drawImage(coracao31.getImagem(), coracao31.getX(), coracao31.getY(), this);
		graficos.drawImage(coracao32.getImagem(), coracao32.getX(), coracao32.getY(), this);
		graficos.drawImage(coracao33.getImagem(), coracao33.getX(), coracao33.getY(), this);
		graficos.drawImage(coracao34.getImagem(), coracao34.getX(), coracao34.getY(), this);
		graficos.drawImage(coracao35.getImagem(), coracao35.getX(), coracao35.getY(), this);
		graficos.drawImage(coracao36.getImagem(), coracao36.getX(), coracao36.getY(), this);
		graficos.drawImage(coracao37.getImagem(), coracao37.getX(), coracao37.getY(), this);
		graficos.drawImage(coracao38.getImagem(), coracao38.getX(), coracao38.getY(), this);
		graficos.drawImage(coracao39.getImagem(), coracao39.getX(), coracao39.getY(), this);
		graficos.drawImage(coracao310.getImagem(), coracao310.getX(), coracao310.getY(), this);
		
		graficos.drawImage(coracao41.getImagem(), coracao41.getX(), coracao41.getY(), this);
		graficos.drawImage(coracao42.getImagem(), coracao42.getX(), coracao42.getY(), this);
		graficos.drawImage(coracao43.getImagem(), coracao43.getX(), coracao43.getY(), this);
		graficos.drawImage(coracao44.getImagem(), coracao44.getX(), coracao44.getY(), this);
		graficos.drawImage(coracao45.getImagem(), coracao45.getX(), coracao45.getY(), this);
		graficos.drawImage(coracao46.getImagem(), coracao46.getX(), coracao46.getY(), this);
		graficos.drawImage(coracao47.getImagem(), coracao47.getX(), coracao47.getY(), this);
		graficos.drawImage(coracao48.getImagem(), coracao48.getX(), coracao48.getY(), this);
		graficos.drawImage(coracao49.getImagem(), coracao49.getX(), coracao49.getY(), this);
		graficos.drawImage(coracao410.getImagem(), coracao410.getX(), coracao410.getY(), this);
		
		graficos.drawImage(coracao51.getImagem(), coracao51.getX(), coracao51.getY(), this);
		graficos.drawImage(coracao52.getImagem(), coracao52.getX(), coracao52.getY(), this);
		graficos.drawImage(coracao53.getImagem(), coracao53.getX(), coracao53.getY(), this);
		graficos.drawImage(coracao54.getImagem(), coracao54.getX(), coracao54.getY(), this);
		graficos.drawImage(coracao55.getImagem(), coracao55.getX(), coracao55.getY(), this);
		graficos.drawImage(coracao56.getImagem(), coracao56.getX(), coracao56.getY(), this);
		graficos.drawImage(coracao57.getImagem(), coracao57.getX(), coracao57.getY(), this);
		graficos.drawImage(coracao58.getImagem(), coracao58.getX(), coracao58.getY(), this);
		graficos.drawImage(coracao59.getImagem(), coracao59.getX(), coracao59.getY(), this);
		graficos.drawImage(coracao510.getImagem(), coracao510.getX(), coracao510.getY(), this);
		
		// --------------------------------- campo batalha e habilidades usadas -----------------------------------------

	    tl1 = new TextLayout(nomeApelo1.getTexto(), nomeApelo1.getFonte(), frc);
	    tl2 = new TextLayout(nomeApelo2.getTexto(), nomeApelo2.getFonte(), frc);
	    tl3 = new TextLayout(nomeApelo3.getTexto(), nomeApelo3.getFonte(), frc);
	    tl4 = new TextLayout(nomeApelo4.getTexto(), nomeApelo4.getFonte(), frc);
	    
	    graficos.setColor(nomeApelo1.getCorTexto());
	    tl1.draw(graficos, nomeApelo1.getX(), nomeApelo1.getY());
	    graficos.setColor(nomeApelo2.getCorTexto());
	    tl2.draw(graficos, nomeApelo2.getX(), nomeApelo2.getY());
	    graficos.setColor(nomeApelo3.getCorTexto());
	    tl3.draw(graficos, nomeApelo3.getX(), nomeApelo3.getY());
	    graficos.setColor(nomeApelo4.getCorTexto());
	    tl4.draw(graficos, nomeApelo4.getX(), nomeApelo4.getY());
	    graficos.setColor(Color.BLACK);
		
		// ----------------------- itens relacionado com Apelo -----------------------------------
		graficos.drawImage(tipoDoApelo.getImagem(), tipoDoApelo.getX(), tipoDoApelo.getY(), this);
		
	    tl6 = new TextLayout(apeloQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);
	    tl7 = new TextLayout(InterferenciaQuantidade.getTexto(), InterferenciaQuantidade.getFonte(), frc);

	    tl6.draw(graficos, apeloQuantidade.getX(), apeloQuantidade.getY());
	    tl7.draw(graficos, InterferenciaQuantidade.getX(), InterferenciaQuantidade.getY());
	    
		graficos.drawImage(apeloApelo1.getImagem(), apeloApelo1.getX(), apeloApelo1.getY(), this);
		graficos.drawImage(apeloApelo2.getImagem(), apeloApelo2.getX(), apeloApelo2.getY(), this);
		graficos.drawImage(apeloApelo3.getImagem(), apeloApelo3.getX(), apeloApelo3.getY(), this);
		graficos.drawImage(apeloApelo4.getImagem(), apeloApelo4.getX(), apeloApelo4.getY(), this);
		graficos.drawImage(apeloApelo5.getImagem(), apeloApelo5.getX(), apeloApelo5.getY(), this);
		graficos.drawImage(apeloApelo6.getImagem(), apeloApelo6.getX(), apeloApelo6.getY(), this);
		graficos.drawImage(apeloApelo7.getImagem(), apeloApelo7.getX(), apeloApelo7.getY(), this);
		graficos.drawImage(apeloApelo8.getImagem(), apeloApelo8.getX(), apeloApelo8.getY(), this);
		graficos.drawImage(apeloApelo9.getImagem(), apeloApelo9.getX(), apeloApelo9.getY(), this);
		graficos.drawImage(apeloApelo10.getImagem(), apeloApelo10.getX(), apeloApelo10.getY(), this);
		
		graficos.drawImage(apeloInterf1.getImagem(), apeloInterf1.getX(), apeloInterf1.getY(), this);
		graficos.drawImage(apeloInterf2.getImagem(), apeloInterf2.getX(), apeloInterf2.getY(), this);
		graficos.drawImage(apeloInterf3.getImagem(), apeloInterf3.getX(), apeloInterf3.getY(), this);
		graficos.drawImage(apeloInterf4.getImagem(), apeloInterf4.getX(), apeloInterf4.getY(), this);
		graficos.drawImage(apeloInterf5.getImagem(), apeloInterf5.getX(), apeloInterf5.getY(), this);
		graficos.drawImage(apeloInterf6.getImagem(), apeloInterf6.getX(), apeloInterf6.getY(), this);
		graficos.drawImage(apeloInterf7.getImagem(), apeloInterf7.getX(), apeloInterf7.getY(), this);
		graficos.drawImage(apeloInterf8.getImagem(), apeloInterf8.getX(), apeloInterf8.getY(), this);
		graficos.drawImage(apeloInterf9.getImagem(), apeloInterf9.getX(), apeloInterf9.getY(), this);
		graficos.drawImage(apeloInterf10.getImagem(), apeloInterf10.getX(), apeloInterf10.getY(), this);
		
		// ----------------------- itens relacionado com Descrição -----------------------------------
	    tl5 = new TextLayout(textoDescricao1.getTexto(), textoDescricao1.getFonte(), frc);
	    tl15 = new TextLayout(textoDescricao2.getTexto(), textoDescricao2.getFonte(), frc);
	    tl16 = new TextLayout(textoDescricao3.getTexto(), textoDescricao3.getFonte(), frc);
	    tl17 = new TextLayout(textoDescricao4.getTexto(), textoDescricao4.getFonte(), frc);
	    
	    tl5.draw(graficos, textoDescricao1.getX(), textoDescricao1.getY());
	    tl15.draw(graficos, textoDescricao2.getX(), textoDescricao2.getY());
	    tl16.draw(graficos, textoDescricao3.getX(), textoDescricao3.getY());
	    tl17.draw(graficos, textoDescricao4.getX(), textoDescricao4.getY());
	    
		// ------------------------------------------- d20 ----------------------------------------
		graficos.drawImage(imgDado1.getImagem(), imgDado1.getX(), imgDado1.getY(), this);
		graficos.drawImage(imgDado2.getImagem(), imgDado2.getX(), imgDado2.getY(), this);
		graficos.drawImage(imgDado3.getImagem(), imgDado3.getX(), imgDado3.getY(), this);
		graficos.drawImage(imgDado4.getImagem(), imgDado4.getX(), imgDado4.getY(), this);
		graficos.drawImage(imgDado5.getImagem(), imgDado5.getX(), imgDado5.getY(), this);
		
		// -----------------------------------------------------------------------------------------------
		graficos.drawImage(parabenizacaoVencedor.getImagem(), parabenizacaoVencedor.getX(), parabenizacaoVencedor.getY(), this);
		
		// ------------------------------------- imagens do menu ---------------------------------------
		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getX(), sombreadorMenu.getY(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getX(), fundoMenu.getY(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getX(), bntMenu.getY(), this);
		graficos.drawImage(bntRegras.getImagem(), bntRegras.getX(), bntRegras.getY(), this);
		graficos.drawImage( bntVoltar.getImagem(),  bntVoltar.getX(),  bntVoltar.getY(), this);
		
		// ------------------------ imagens e textos do diálogo de aviso ------------------------------
		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getX(), dialogoAviso.getY(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getX(), bntSimDialogoAviso.getY(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getX(), bntNaoDialogoAviso.getY(), this);
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl20 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl21 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso2.getFonte(), frc);
		
		tl20.draw(graficos, txtDialogoAviso.getX(), txtDialogoAviso.getY());
		tl21.draw(graficos, txtDialogoAviso2.getX(), txtDialogoAviso2.getY());
		
		// -----------------------------------------------------------------------------------------------
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);

		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// ----------- aparece os d20 ou começa a mostrar os apelos ganhos ----------------
		if(vezDados == true) {
			aparecerDados();
		}else if(animacaoFileira != -1 && animacaoFileira < 10) {
			aparecerCoracoes();
		}
		
		// --------------------- começa a colocar as interferências ---------------------
		if(animacaoFileira >= 10 && animacaoFileira < 20) {
			sumirCoracoes();
		}
		
		// -------------- mexe o medidor de ganho de apelo das etapa da batalha ----------------
		if(animacaoFileira == 20) {
			mexerMedidorApelos();
		}
		
		// -------------- organiza os cães de acordo com a performance  na batalha ----------------
		if(animacaoFileira == 21) {
			OrganizarCampos();
		}
		
		// -------------- mostra a parabenizarão no final da batalha ----------------
		if(animacaoFileira == 22) {
			vencedor();
		}
		
		repaint();
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  								aciona as animações das habilidades							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void gifApresentacao(int avent, int numApelo) {
		if(Iniciargif == false) {
			Iniciargif = true;
			
			if(avent == 0) {
				animacao.load("res\\batalha\\Ignis\\animacao\\" + (numApelo == 0? gifApelos[0][0] : (numApelo == 1? gifApelos[0][1] : (numApelo == 2? gifApelos[0][2] : gifApelos[0][3]))) + ".gif");
			}else if(avent == 1) {
				animacao.load("res\\batalha\\Ayla\\animacao\\" + (numApelo == 0? gifApelos[1][0] : (numApelo == 1? gifApelos[1][1] : (numApelo == 2? gifApelos[1][2] : gifApelos[1][3]))) + ".gif");
			}else if(avent == 2) {
				animacao.load("res\\batalha\\Rexthor\\animacao\\" + (numApelo == 0? gifApelos[2][0] : (numApelo == 1? gifApelos[2][1] : (numApelo == 2? gifApelos[2][2] : gifApelos[2][3]))) + ".gif");
			}else if(avent == 3) {
				animacao.load("res\\batalha\\Kiki\\animacao\\" + (numApelo == 0? gifApelos[3][0] : (numApelo == 1? gifApelos[3][1] : (numApelo == 2? gifApelos[3][2] : gifApelos[3][3]))) + ".gif");
			}else if(avent == 4) {
				animacao.load("res\\batalha\\Arius\\animacao\\" + (numApelo == 0? gifApelos[4][0] : (numApelo == 1? gifApelos[4][1] : (numApelo == 2? gifApelos[4][2] : gifApelos[4][3]))) + ".gif");
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  										mostra os d20										|
	\ ---------------------------------------------------------------------------------------- */
	
	public void aparecerDados() {
		
		for(int i=0; i<5; i++) {
			(i == 0 ? imgDado1 : (i == 1 ? imgDado2 : (i == 2 ? imgDado3 : (i == 3 ? imgDado4 : imgDado5)))).load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[i] + ".png");
		}
		
		imgDado1.setDx(imgDado1.getDx() + comecarAnimacaoCoracao);
		
		if(imgDado1.getDx() > 150) {
			imgDado1.setImagem(null); imgDado2.setImagem(null); imgDado3.setImagem(null); imgDado4.setImagem(null); imgDado5.setImagem(null);
			imgDado1.setDx(0);
			vezDados = false;
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									mostra os apelos ganhos									|
	\ ---------------------------------------------------------------------------------------- */
	
	public void aparecerCoracoes() {
	
		if((animacaoFileira == 0 && (matrizAventureiros[2][0] > 0 || matrizAventureiros[4][0] == 4 || matrizAventureiros[4][0] == 6)) || animacaoFileira % 2 == 0) {
			contTempoApelo = 0;
			
			if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
				(animacaoFileira == 0 ? coracao110 : (animacaoFileira == 2 ? coracao210 : (animacaoFileira == 4 ? coracao310 : (animacaoFileira == 6 ? coracao410 : coracao510)))).setDx(intervaloAnimacao);
			}else {
				
				TerminouLoopEfeitoInterf =0;
				
				if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
				
				animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);

				if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
					gifApresentacao(matrizAventureiros[0][animacaoFileira == 0 ? 0 : animacaoFileira/2], (animacaoFileira == 0 ? 0 : animacaoFileira/2) != posicaoAventureiro ? arrayAleatorioHabAdver[animacaoFileira == 0 ? 0 : animacaoFileira/2] : selecaoNomeHab);
				}
				
				if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
					(animacaoFileira == 0 ? coracao110 : (animacaoFileira == 2 ? coracao210 : (animacaoFileira == 4 ? coracao310 : (animacaoFileira == 6 ? coracao410 : coracao510)))).setDx((animacaoFileira == 0 ? coracao110 : (animacaoFileira == 2 ? coracao210 : (animacaoFileira == 4 ? coracao310 : (animacaoFileira == 6 ? coracao410 : coracao510)))).getDx() + comecarAnimacaoCoracao);
					animacao.load("res\\batalha\\animacao.png");
					if(Efeito4 ==1) {Efeito4 ++;}
					
					if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
						interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
					}
				}
			}
			
			//------------------------------------- limpa interferecia --------------------------------------------
			
			switch (animacaoFileira) {
				case 0:
					if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
						
						(contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11)))))))).setDx((contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11)))))))).getDx() + comecarAnimacaoCoracao);

						if((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
							
							if(contTempoInter!= 1) {
								matrizAventureiros[2][0] = matrizAventureiros[2][0] - ((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getImagem() != null &&(contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png") {
									(contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).setImagem(null);
								}
								(contTempoInter== 10 ? coracao19 : (contTempoInter== 9 ? coracao18 : (contTempoInter== 8 ? coracao17 : (contTempoInter== 7 ? coracao16 : (contTempoInter== 6 ? coracao15 : (contTempoInter== 5 ? coracao14 : (contTempoInter== 4 ? coracao13 : (contTempoInter== 3 ? coracao12 : coracao11)))))))).setDx((contTempoInter== 10 ? coracao19 : (contTempoInter== 9 ? coracao18 : (contTempoInter== 8 ? coracao17 : (contTempoInter== 7 ? coracao16 : (contTempoInter== 6 ? coracao15 : (contTempoInter== 5 ? coracao14 : (contTempoInter== 4 ? coracao13 : (contTempoInter== 3 ? coracao12 : coracao11)))))))).getDx() + comecarAnimacaoCoracao);
							} else {
								matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao11.getImagem() == null ? 0 : (coracao11.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if(coracao11.getImagem() != null && coracao11.getReferencia().toString() == "res\\batalha\\interferencia.png") {
									coracao11.setImagem(null);
								} 
								animacaoFileira=1;
								zerarDx();
							}
							contTempoInter--;
						}
						if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
					}
					break;
				case 2:

					if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
						
						(contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21)))))))).setDx((contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21)))))))).getDx() + comecarAnimacaoCoracao);
					
						if((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
							
							if(contTempoInter!= 1) {
								matrizAventureiros[2][1] = matrizAventureiros[2][1] - ((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getImagem() != null &&(contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png") {
									(contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).setImagem(null);
								}
								(contTempoInter== 10 ? coracao29 : (contTempoInter== 9 ? coracao28 : (contTempoInter== 8 ? coracao27 : (contTempoInter== 7 ? coracao26 : (contTempoInter== 6 ? coracao25 : (contTempoInter== 5 ? coracao24 : (contTempoInter== 4 ? coracao23 : (contTempoInter== 3 ? coracao22 : coracao21)))))))).setDx((contTempoInter== 10 ? coracao29 : (contTempoInter== 9 ? coracao28 : (contTempoInter== 8 ? coracao27 : (contTempoInter== 7 ? coracao26 : (contTempoInter== 6 ? coracao25 : (contTempoInter== 5 ? coracao24 : (contTempoInter== 4 ? coracao23 : (contTempoInter== 3 ? coracao22 : coracao21)))))))).getDx() + comecarAnimacaoCoracao);
							} else {
								matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao21.getImagem() == null ? 0 : (coracao21.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if(coracao21.getImagem() != null && coracao21.getReferencia().toString() == "res\\batalha\\interferencia.png") {
									coracao21.setImagem(null);
								} 
								animacaoFileira=3;
								zerarDx();
							}
							contTempoInter--;
						}
						if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
					}
					
					break;
				case 4:

					if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
						
						(contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31)))))))).setDx((contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31)))))))).getDx() + comecarAnimacaoCoracao);

						if((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
							
							if(contTempoInter!= 1) {
								matrizAventureiros[2][2] = matrizAventureiros[2][2] - ((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getImagem() != null &&(contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png") {
									(contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).setImagem(null);
								}
								(contTempoInter== 10 ? coracao39 : (contTempoInter== 9 ? coracao38 : (contTempoInter== 8 ? coracao37 : (contTempoInter== 7 ? coracao36 : (contTempoInter== 6 ? coracao35 : (contTempoInter== 5 ? coracao34 : (contTempoInter== 4 ? coracao33 : (contTempoInter== 3 ? coracao32 : coracao31)))))))).setDx((contTempoInter== 10 ? coracao39 : (contTempoInter== 9 ? coracao38 : (contTempoInter== 8 ? coracao37 : (contTempoInter== 7 ? coracao36 : (contTempoInter== 6 ? coracao35 : (contTempoInter== 5 ? coracao34 : (contTempoInter== 4 ? coracao33 : (contTempoInter== 3 ? coracao32 : coracao31)))))))).getDx() + comecarAnimacaoCoracao);
							} else {
								matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao31.getImagem() == null ? 0 : (coracao31.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if(coracao31.getImagem() != null && coracao31.getReferencia().toString() == "res\\batalha\\interferencia.png") {
									coracao31.setImagem(null);
								} 
								animacaoFileira=5;
								zerarDx();
							}
							contTempoInter--;
						}
						if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
					}		
					
					break;
				case 6:

					if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
						
						(contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41)))))))).setDx((contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41)))))))).getDx() + comecarAnimacaoCoracao);

						if((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
							
							if(contTempoInter!= 1) {
								matrizAventureiros[2][3] = matrizAventureiros[2][3] - ((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getImagem() != null &&(contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png") {
									(contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).setImagem(null);
								}
								(contTempoInter== 10 ? coracao49 : (contTempoInter== 9 ? coracao48 : (contTempoInter== 8 ? coracao47 : (contTempoInter== 7 ? coracao46 : (contTempoInter== 6 ? coracao45 : (contTempoInter== 5 ? coracao44 : (contTempoInter== 4 ? coracao43 : (contTempoInter== 3 ? coracao42 : coracao41)))))))).setDx((contTempoInter== 10 ? coracao49 : (contTempoInter== 9 ? coracao48 : (contTempoInter== 8 ? coracao47 : (contTempoInter== 7 ? coracao46 : (contTempoInter== 6 ? coracao45 : (contTempoInter== 5 ? coracao44 : (contTempoInter== 4 ? coracao43 : (contTempoInter== 3 ? coracao42 : coracao41)))))))).getDx() + comecarAnimacaoCoracao);
							} else {
								matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao41.getImagem() == null ? 0 : (coracao41.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if(coracao41.getImagem() != null && coracao41.getReferencia().toString() == "res\\batalha\\interferencia.png") {
									coracao41.setImagem(null);
								} 
								animacaoFileira=7;
								zerarDx();
							}
							contTempoInter--;
						}
						if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
					}	
					
					break;
				case 8:
					if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
						
						(contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51)))))))).setDx((contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51)))))))).getDx() + comecarAnimacaoCoracao);

						if((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
							
							if(contTempoInter!= 1) {
								matrizAventureiros[2][4] = matrizAventureiros[2][4] - ((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getImagem() != null &&(contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png") {
									(contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).setImagem(null);
								}
								(contTempoInter== 10 ? coracao59 : (contTempoInter== 9 ? coracao58 : (contTempoInter== 8 ? coracao57 : (contTempoInter== 7 ? coracao56 : (contTempoInter== 6 ? coracao55 : (contTempoInter== 5 ? coracao54 : (contTempoInter== 4 ? coracao53 : (contTempoInter== 3 ? coracao52 : coracao51)))))))).setDx((contTempoInter== 10 ? coracao59 : (contTempoInter== 9 ? coracao58 : (contTempoInter== 8 ? coracao57 : (contTempoInter== 7 ? coracao56 : (contTempoInter== 6 ? coracao55 : (contTempoInter== 5 ? coracao54 : (contTempoInter== 4 ? coracao53 : (contTempoInter== 3 ? coracao52 : coracao51)))))))).getDx() + comecarAnimacaoCoracao);
							} else {
								matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao51.getImagem() == null ? 0 : (coracao51.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0));
								if(coracao51.getImagem() != null && coracao51.getReferencia().toString() == "res\\batalha\\interferencia.png") {
									coracao51.setImagem(null);
								} 
								animacaoFileira=9;
								zerarDx();
							}
							contTempoInter--;
						}
						if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
					}			
					break;
			}
		} else if((animacaoFileira == 1 || (animacaoFileira - 1) % 2 == 0) || matrizAventureiros[2][animacaoFileira == 1 ? 0 : ((animacaoFileira - 1) % 2)] == 0) {
			
			contTempoInter = 10;

			if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
			if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
			
			Iniciargif = false; animacao.setDx(0);
			
			
			//------------------------------------- aparece apelos  --------------------------------------------
			switch (animacaoFileira) {
				case 1:
				
					(contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).setDx((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
						
						if(contTempoApelo!= 9) {
							matrizAventureiros[2][0] = matrizAventureiros[2][0] - ((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getImagem() == null ? 1 : 0);
							(contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).load("res\\batalha\\apelo.png");
						} else {
							 matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao110.getImagem() == null ? 1 : 0);
							 coracao110.load("res\\batalha\\apelo.png");
						}
						
						contTempoApelo++;
					}
					break;
			
				case 3:
					(contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).setDx((contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
						
						if(contTempoApelo!= 9) {
							matrizAventureiros[2][1] = matrizAventureiros[2][1] - ((contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).getImagem() == null ? 1 : 0);
							(contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).load("res\\batalha\\apelo.png");
						} else {
							 matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao210.getImagem() == null ? 1 : 0);
							 coracao210.load("res\\batalha\\apelo.png");
						}
						
						contTempoApelo++;
					}
					
					break;
				case 5:
					
					(contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).setDx((contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
						
						if(contTempoApelo!= 9) {
							matrizAventureiros[2][2] = matrizAventureiros[2][2] - ((contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).getImagem() == null ? 1 : 0);
							(contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).load("res\\batalha\\apelo.png");
						} else {
							 matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao310.getImagem() == null ? 1 : 0);
							 coracao310.load("res\\batalha\\apelo.png");
						}
						
						contTempoApelo++;
					}
					
					break;
				case 7:
					
					(contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).setDx((contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
						
						if(contTempoApelo!= 9) {
							matrizAventureiros[2][3] = matrizAventureiros[2][3] - ((contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).getImagem() == null ? 1 : 0);
							(contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).load("res\\batalha\\apelo.png");
						} else {
							 matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao410.getImagem() == null ? 1 : 0);
							 coracao410.load("res\\batalha\\apelo.png");
						}
						
						contTempoApelo++;
					}
					
					break;
				case 9:
					
					(contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).setDx((contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
						
						if(contTempoApelo!= 9) {
							matrizAventureiros[2][4] = matrizAventureiros[2][4] - ((contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).getImagem() == null ? 1 : 0);
							(contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).load("res\\batalha\\apelo.png");
						} else {
							 matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao510.getImagem() == null ? 1 : 0);
							 coracao510.load("res\\batalha\\apelo.png");
						}
						
						contTempoApelo++;
					} 
					break;
			}
			
			if((matrizAventureiros[2][animacaoFileira == 0 ? 0: ((animacaoFileira - 1)/2)] == 0 && TerminouLoopEfeitoInterf != 1) ||contTempoApelo== 10) { 
				
				if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(animacaoFileira == 0 ? 0: ((animacaoFileira - 1)/2));} 
				else if(efeitoChefeDeFase[animacaoFileira == 0 ? 0: ((animacaoFileira - 1)/2)] == 1) {efeitoChefeDeFase(animacaoFileira == 0 ? 0: ((animacaoFileira - 1)/2));} 
				else{interferirNosAdversarios(matrizAventureiros[3][animacaoFileira == 0 ? 0: ((animacaoFileira - 1)/2)], matrizAventureiros[4][animacaoFileira == 0 ? 0: ((animacaoFileira - 1)/2)], animacaoFileira == 0 ? 0: ((animacaoFileira - 1)/2)); zerarDx();}
			}
				
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						reseta o contador de tempo para aparecer apelos						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void zerarDx() {
		coracao01.setDx(0);coracao02.setDx(0);coracao03.setDx(0);coracao04.setDx(0);coracao05.setDx(0);
		
		coracao11.setDx(0);coracao12.setDx(0);coracao13.setDx(0);coracao14.setDx(0);coracao15.setDx(0);
		coracao16.setDx(0);coracao17.setDx(0);coracao18.setDx(0);coracao19.setDx(0);coracao110.setDx(0);
		
		coracao21.setDx(0);coracao22.setDx(0);coracao23.setDx(0);coracao24.setDx(0);coracao25.setDx(0);
		coracao26.setDx(0);coracao27.setDx(0);coracao28.setDx(0);coracao29.setDx(0);coracao210.setDx(0);
		
		coracao31.setDx(0);coracao32.setDx(0);coracao33.setDx(0);coracao34.setDx(0);coracao35.setDx(0);
		coracao36.setDx(0);coracao37.setDx(0);coracao38.setDx(0);coracao39.setDx(0);coracao310.setDx(0);
		
		coracao41.setDx(0);coracao42.setDx(0);coracao43.setDx(0);coracao44.setDx(0);coracao45.setDx(0);
		coracao46.setDx(0);coracao47.setDx(0);coracao48.setDx(0);coracao49.setDx(0);coracao410.setDx(0);
		
		coracao51.setDx(0);coracao52.setDx(0);coracao53.setDx(0);coracao54.setDx(0);coracao55.setDx(0);
		coracao56.setDx(0);coracao57.setDx(0);coracao58.setDx(0);coracao59.setDx(0);coracao510.setDx(0);
		
		efeitoFase.setDx(0);
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  								mostra as interferências ganhas								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void sumirCoracoes() {
		//------------------------------------- 1 --------------------------------------------
		if(animacaoFileira - 10 == 0 || (animacaoFileira - 10) % 2 == 0) {
			contTempoApelo = 0;
			
			//------------------------------------- limpa apelo --------------------------------------------
			switch (animacaoFileira) {
				case 10:
					(contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).setDx((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getDx() >= intervaloAnimacao  && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
						
						if(contTempoInter!= 1) {
							interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - ((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getImagem() != null && (contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getReferencia().toString() != "res\\batalha\\interferencia.png") {
								(contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).setImagem(null);
								} 
							(contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11)))))))).setDx((contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao11.getImagem() == null ? 0 : (coracao11.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if(coracao11.getImagem() != null && coracao11.getReferencia().toString() != "res\\batalha\\interferencia.png") {
								coracao11.setImagem(null);
							}
							animacaoFileira=11; zerarDx();
						}
						contTempoInter--;
					}
					break;
				case 12:
					
					(contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).setDx((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getDx() >= intervaloAnimacao  && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
						
						if(contTempoInter!= 1) {
							interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - ((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if((contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getImagem() != null && (contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).getReferencia().toString() != "res\\batalha\\interferencia.png") {
								(contTempoInter== 10 ? coracao210 : (contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21))))))))).setImagem(null);
								} 
							(contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21)))))))).setDx((contTempoInter== 9 ? coracao29 : (contTempoInter== 8 ? coracao28 : (contTempoInter== 7 ? coracao27 : (contTempoInter== 6 ? coracao26 : (contTempoInter== 5 ? coracao25 : (contTempoInter== 4 ? coracao24 : (contTempoInter== 3 ? coracao23 : (contTempoInter== 2 ? coracao22 : coracao21)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao21.getImagem() == null ? 0 : (coracao21.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if(coracao21.getImagem() != null && coracao21.getReferencia().toString() != "res\\batalha\\interferencia.png") {
								coracao21.setImagem(null);
							}
							animacaoFileira=13; zerarDx();
						}
						contTempoInter--;
					}
					
					break;
				case 14:
					
					(contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).setDx((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getDx() >= intervaloAnimacao  && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
						
						if(contTempoInter!= 1) {
							interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - ((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if((contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getImagem() != null && (contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).getReferencia().toString() != "res\\batalha\\interferencia.png") {
								(contTempoInter== 10 ? coracao310 : (contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31))))))))).setImagem(null);
								} 
							(contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31)))))))).setDx((contTempoInter== 9 ? coracao39 : (contTempoInter== 8 ? coracao38 : (contTempoInter== 7 ? coracao37 : (contTempoInter== 6 ? coracao36 : (contTempoInter== 5 ? coracao35 : (contTempoInter== 4 ? coracao34 : (contTempoInter== 3 ? coracao33 : (contTempoInter== 2 ? coracao32 : coracao31)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao31.getImagem() == null ? 0 : (coracao31.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if(coracao31.getImagem() != null && coracao31.getReferencia().toString() != "res\\batalha\\interferencia.png") {
								coracao31.setImagem(null);
							}
							animacaoFileira=15; zerarDx();
						}
						contTempoInter--;
					}
					
					break;
				case 16:
					
					(contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).setDx((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getDx() >= intervaloAnimacao  && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
						
						if(contTempoInter!= 1) {
							interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - ((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if((contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getImagem() != null && (contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).getReferencia().toString() != "res\\batalha\\interferencia.png") {
								(contTempoInter== 10 ? coracao410 : (contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41))))))))).setImagem(null);
								} 
							(contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41)))))))).setDx((contTempoInter== 9 ? coracao49 : (contTempoInter== 8 ? coracao48 : (contTempoInter== 7 ? coracao47 : (contTempoInter== 6 ? coracao46 : (contTempoInter== 5 ? coracao45 : (contTempoInter== 4 ? coracao44 : (contTempoInter== 3 ? coracao43 : (contTempoInter== 2 ? coracao42 : coracao41)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao41.getImagem() == null ? 0 : (coracao41.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if(coracao41.getImagem() != null && coracao41.getReferencia().toString() != "res\\batalha\\interferencia.png") {
								coracao41.setImagem(null);
							}
							animacaoFileira=17; zerarDx();
						}
						contTempoInter--;
					}
					
					break;
				case 18:
					
					(contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).setDx((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getDx() >= intervaloAnimacao  && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
						
						if(contTempoInter!= 1) {
							interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - ((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getImagem() == null ? 0 : ((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if((contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getImagem() != null && (contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).getReferencia().toString() != "res\\batalha\\interferencia.png") {
								(contTempoInter== 10 ? coracao510 : (contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51))))))))).setImagem(null);
								} 
							(contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51)))))))).setDx((contTempoInter== 9 ? coracao59 : (contTempoInter== 8 ? coracao58 : (contTempoInter== 7 ? coracao57 : (contTempoInter== 6 ? coracao56 : (contTempoInter== 5 ? coracao55 : (contTempoInter== 4 ? coracao54 : (contTempoInter== 3 ? coracao53 : (contTempoInter== 2 ? coracao52 : coracao51)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao51.getImagem() == null ? 0 : (coracao51.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));
							if(coracao51.getImagem() != null && coracao51.getReferencia().toString() != "res\\batalha\\interferencia.png") {
								coracao51.setImagem(null);
							}
							animacaoFileira=19; zerarDx();
						}
						contTempoInter--;
					}
					break;
			}
			
			if(interferenciasRecebidas[animacaoFileira - 10 == 0 ? 0 : (animacaoFileira-10)/2][vezDoAventureiro] == 0) {animacaoFileira ++; zerarDx();}
			
		}else if(animacaoFileira - 11 == 0 || (animacaoFileira - 11) % 2 == 0) {
			contTempoInter = 10;
			
			//------------------------------------- aparece interferencia  --------------------------------------------
			
			switch (animacaoFileira) {
				case 11:
				
					(contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).setDx((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
						
						if(contTempoApelo!= 9) {
							interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - ((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getImagem() == null ? 1 : ((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							(contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).load("res\\batalha\\interferencia.png");
							(contTempoApelo== 0 ? coracao12 : (contTempoApelo== 1 ? coracao13 : (contTempoApelo== 2 ? coracao14 : (contTempoApelo== 3 ? coracao15 : (contTempoApelo== 4 ? coracao16 : (contTempoApelo== 5 ? coracao17 : (contTempoApelo== 6 ? coracao18 : (contTempoApelo== 7 ? coracao19 : coracao110)))))))).setDx((contTempoApelo== 0 ? coracao12 : (contTempoApelo== 1 ? coracao13 : (contTempoApelo== 2 ? coracao14 : (contTempoApelo== 3 ? coracao15 : (contTempoApelo== 4 ? coracao16 : (contTempoApelo== 5 ? coracao17 : (contTempoApelo== 6 ? coracao18 : (contTempoApelo== 7 ? coracao19 : coracao110)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao110.getImagem() == null ? 1 : (coracao110.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							coracao110.load("res\\batalha\\interferencia.png");
							animacaoEfeitoConcluido[0] = true;
							zerarDx();
							if(apeloRepetido[vezDoAventureiro] == 3) {
								animacaoFileira = 1;
							} else{
								interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);
							}
						}
						
						contTempoApelo++;
					}
				
					break;
				case 13:
					
					(contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).setDx((contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
						
						if(contTempoApelo!= 9) {
							interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - ((contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).getImagem() == null ? 1 : ((contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							(contTempoApelo== 0 ? coracao21 : (contTempoApelo== 1 ? coracao22 : (contTempoApelo== 2 ? coracao23 : (contTempoApelo== 3 ? coracao24 : (contTempoApelo== 4 ? coracao25 : (contTempoApelo== 5 ? coracao26 : (contTempoApelo== 6 ? coracao27 : (contTempoApelo== 7 ? coracao28 : (contTempoApelo== 8 ? coracao29 : coracao210))))))))).load("res\\batalha\\interferencia.png");
							(contTempoApelo== 0 ? coracao22 : (contTempoApelo== 1 ? coracao23 : (contTempoApelo== 2 ? coracao24 : (contTempoApelo== 3 ? coracao25 : (contTempoApelo== 4 ? coracao26 : (contTempoApelo== 5 ? coracao27 : (contTempoApelo== 6 ? coracao28 : (contTempoApelo== 7 ? coracao29 : coracao210)))))))).setDx((contTempoApelo== 0 ? coracao22 : (contTempoApelo== 1 ? coracao23 : (contTempoApelo== 2 ? coracao24 : (contTempoApelo== 3 ? coracao25 : (contTempoApelo== 4 ? coracao26 : (contTempoApelo== 5 ? coracao27 : (contTempoApelo== 6 ? coracao28 : (contTempoApelo== 7 ? coracao29 : coracao210)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao210.getImagem() == null ? 1 : (coracao210.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							coracao210.load("res\\batalha\\interferencia.png");
							animacaoEfeitoConcluido[1] = true;
							zerarDx();
							if(apeloRepetido[vezDoAventureiro] == 3) {
								animacaoFileira = 3;
							} else{
								interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);
							}
						}
						
						contTempoApelo++;
					}
					
					break;
				case 15:
					
					(contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).setDx((contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
						
						if(contTempoApelo!= 9) {
							interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - ((contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).getImagem() == null ? 1 : ((contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							(contTempoApelo== 0 ? coracao31 : (contTempoApelo== 1 ? coracao32 : (contTempoApelo== 2 ? coracao33 : (contTempoApelo== 3 ? coracao34 : (contTempoApelo== 4 ? coracao35 : (contTempoApelo== 5 ? coracao36 : (contTempoApelo== 6 ? coracao37 : (contTempoApelo== 7 ? coracao38 : (contTempoApelo== 8 ? coracao39 : coracao310))))))))).load("res\\batalha\\interferencia.png");
							(contTempoApelo== 0 ? coracao32 : (contTempoApelo== 1 ? coracao33 : (contTempoApelo== 2 ? coracao34 : (contTempoApelo== 3 ? coracao35 : (contTempoApelo== 4 ? coracao36 : (contTempoApelo== 5 ? coracao37 : (contTempoApelo== 6 ? coracao38 : (contTempoApelo== 7 ? coracao39 : coracao310)))))))).setDx((contTempoApelo== 0 ? coracao32 : (contTempoApelo== 1 ? coracao33 : (contTempoApelo== 2 ? coracao34 : (contTempoApelo== 3 ? coracao35 : (contTempoApelo== 4 ? coracao36 : (contTempoApelo== 5 ? coracao37 : (contTempoApelo== 6 ? coracao38 : (contTempoApelo== 7 ? coracao39 : coracao310)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao310.getImagem() == null ? 1 : (coracao310.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							coracao310.load("res\\batalha\\interferencia.png");
							animacaoEfeitoConcluido[2] = true;
							zerarDx();
							if(apeloRepetido[vezDoAventureiro] == 3) {
								animacaoFileira = 5;
							} else{
								interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);
							}
						}
						
						contTempoApelo++;
					}
					
					break;
				case 17:
					
					(contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).setDx((contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
						
						if(contTempoApelo!= 9) {
							interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - ((contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).getImagem() == null ? 1 : ((contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							(contTempoApelo== 0 ? coracao41 : (contTempoApelo== 1 ? coracao42 : (contTempoApelo== 2 ? coracao43 : (contTempoApelo== 3 ? coracao44 : (contTempoApelo== 4 ? coracao45 : (contTempoApelo== 5 ? coracao46 : (contTempoApelo== 6 ? coracao47 : (contTempoApelo== 7 ? coracao48 : (contTempoApelo== 8 ? coracao49 : coracao410))))))))).load("res\\batalha\\interferencia.png");
							(contTempoApelo== 0 ? coracao42 : (contTempoApelo== 1 ? coracao43 : (contTempoApelo== 2 ? coracao44 : (contTempoApelo== 3 ? coracao45 : (contTempoApelo== 4 ? coracao46 : (contTempoApelo== 5 ? coracao47 : (contTempoApelo== 6 ? coracao48 : (contTempoApelo== 7 ? coracao49 : coracao410)))))))).setDx((contTempoApelo== 0 ? coracao42 : (contTempoApelo== 1 ? coracao43 : (contTempoApelo== 2 ? coracao44 : (contTempoApelo== 3 ? coracao45 : (contTempoApelo== 4 ? coracao46 : (contTempoApelo== 5 ? coracao47 : (contTempoApelo== 6 ? coracao48 : (contTempoApelo== 7 ? coracao49 : coracao410)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao410.getImagem() == null ? 1 : (coracao410.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							coracao410.load("res\\batalha\\interferencia.png");
							animacaoEfeitoConcluido[3] = true;
							zerarDx();
							if(apeloRepetido[vezDoAventureiro] == 3) {
								animacaoFileira = 7;
							} else{
								interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);
							}
						}
						
						contTempoApelo++;
					}
					
					break;
				case 19:
					
					(contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).setDx((contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).getDx() + comecarAnimacaoCoracao);

					if((contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
						
						if(contTempoApelo!= 9) {
							interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - ((contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).getImagem() == null ? 1 : ((contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							(contTempoApelo== 0 ? coracao51 : (contTempoApelo== 1 ? coracao52 : (contTempoApelo== 2 ? coracao53 : (contTempoApelo== 3 ? coracao54 : (contTempoApelo== 4 ? coracao55 : (contTempoApelo== 5 ? coracao56 : (contTempoApelo== 6 ? coracao57 : (contTempoApelo== 7 ? coracao58 : (contTempoApelo== 8 ? coracao59 : coracao510))))))))).load("res\\batalha\\interferencia.png");
							(contTempoApelo== 0 ? coracao52 : (contTempoApelo== 1 ? coracao53 : (contTempoApelo== 2 ? coracao54 : (contTempoApelo== 3 ? coracao55 : (contTempoApelo== 4 ? coracao56 : (contTempoApelo== 5 ? coracao57 : (contTempoApelo== 6 ? coracao58 : (contTempoApelo== 7 ? coracao59 : coracao510)))))))).setDx((contTempoApelo== 0 ? coracao52 : (contTempoApelo== 1 ? coracao53 : (contTempoApelo== 2 ? coracao54 : (contTempoApelo== 3 ? coracao55 : (contTempoApelo== 4 ? coracao56 : (contTempoApelo== 5 ? coracao57 : (contTempoApelo== 6 ? coracao58 : (contTempoApelo== 7 ? coracao59 : coracao510)))))))).getDx() + comecarAnimacaoCoracao);
						} else {
							interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao510.getImagem() == null ? 1 : (coracao510.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1));
							coracao510.load("res\\batalha\\interferencia.png");
							animacaoEfeitoConcluido[4] = true;
							zerarDx();
							if(apeloRepetido[vezDoAventureiro] == 3) {
								animacaoFileira = 9;
							} else{
								interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);
							}
						}
						contTempoApelo++;
					}
					
					break;
			}
			
			if(interferenciasRecebidas[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {
				animacaoEfeitoConcluido[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2] = true;
				zerarDx();
				
				if(apeloRepetido[vezDoAventureiro] == 3) {
					animacaoFileira = animacaoFileira - 10;
				} else{
					interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);
				}
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							aciona o efeito de habilidade repetida							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void habilidadeRepetido(int posicaoCao) {
		efeitoFase.setDx(efeitoFase.getDx() + comecarAnimacaoCoracao);
		
		if(efeitoFase.getDx() >= 20 && efeitoFase.getDx() < 150) {
			txtEfeitoFase.setTexto("- 2");
			efeitoFase.load("res\\batalha\\apelo.png");
			txtEfeitoFase.setY((posicaoCao == 0 ? campoBatalha1.getY() : (posicaoCao == 1 ? campoBatalha2.getY() : (posicaoCao == 2 ? campoBatalha3.getY() : (posicaoCao == 3 ? campoBatalha4.getY() : campoBatalha5.getY())))) + 70/2 + 7);
			efeitoFase.setY(txtEfeitoFase.getY() - 17);
		}
		
		if(efeitoFase.getDx() == 150) {
			 
			efeitoFase.setImagem(null);
			txtEfeitoFase.setTexto(" ");
			valoresInterferencia[0] = matrizAventureiros[3][posicaoCao];
			valoresInterferencia[1] = matrizAventureiros[4][posicaoCao];
			valoresInterferencia[2] = posicaoCao;
			
			TerminouLoopEfeitoInterf =0;
			apeloRepetido[posicaoCao] = 3;
			zerarDx();
			
			interferenciasRecebidas[posicaoCao][vezDoAventureiro] = interferenciasRecebidas[posicaoCao][vezDoAventureiro] + 2;
			animacaoFileira = 10 + posicaoCao*2;
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							aciona o efeito de do chefe da fase								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void efeitoChefeDeFase (int posicaoCao) {

		if(((adversario == 0 || adversario == 3) && matrizAventureiros[4][posicaoCao] != -1) || ((adversario == 1 || adversario == 4) && matrizAventureiros[4][posicaoCao] == -1) || (adversario == 2 && matrizAventureiros[7][posicaoCao] == 1)) {

			int efeitoBoss = (adversario == 0 || adversario == 1? -1 : 1);
			
			efeitoFase.setDx(efeitoFase.getDx() + comecarAnimacaoCoracao);
			
			if(efeitoFase.getDx() >= 20 && efeitoFase.getDx() < 150) {
				
				txtEfeitoFase.setTexto((efeitoBoss == -1 ? "- 1" : "+ 1"));
				efeitoFase.load("res\\batalha\\apelo.png");
				txtEfeitoFase.setY((posicaoCao == 0 ? campoBatalha1.getY() : (posicaoCao == 1 ? campoBatalha2.getY() : (posicaoCao == 2 ? campoBatalha3.getY() : (posicaoCao == 3 ? campoBatalha4.getY() : campoBatalha5.getY())))) + 70/2 + 7);
				efeitoFase.setY(txtEfeitoFase.getY() - 17);
			}
			if(efeitoFase.getDx() == 150) {

				efeitoFase.setImagem(null);
				txtEfeitoFase.setTexto(" ");
				valoresInterferencia[0] = matrizAventureiros[3][posicaoCao];
				valoresInterferencia[1] = matrizAventureiros[4][posicaoCao];
				valoresInterferencia[2] = posicaoCao;
				
					
				efeitoChefeDeFase[posicaoCao] = 3;
				zerarDx();
				TerminouLoopEfeitoInterf =0;
				
				for(int i=0; i<5; i++) {
					if(posicaoCao == i) {
						if(efeitoBoss < 0) {
							interferenciasRecebidas[i][vezDoAventureiro] = interferenciasRecebidas[i][vezDoAventureiro] - efeitoBoss;
							matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] - efeitoBoss;
							
							animacaoFileira = 10 + i*2;
						} else {
							matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] + efeitoBoss;
							animacaoFileira = 0 + i*2;
						}
					}
				}
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							mexe o medidor de apelos ganhos da batalha						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void mexerMedidorApelos() {
	
		
		
		for(int i=0; i<5;) {
			(i == 0 ? coracao01 : (i == 1 ? coracao02 : (i == 2 ? coracao03 : (i == 3 ? coracao04 : coracao05)))).setDx((i == 0 ? coracao01 : (i == 1 ? coracao02 : (i == 2 ? coracao03 : (i == 3 ? coracao04 : coracao05)))).getDx() + comecarAnimacaoCoracao);

			if((i == 0 ? coracao01 : (i == 1 ? coracao02 : (i == 2 ? coracao03 : (i == 3 ? coracao04 : coracao05)))).getDx() >= intervaloAnimacao) {
				(i == 0 ? coracao01 : (i == 1 ? coracao02 : (i == 2 ? coracao03 : (i == 3 ? coracao04 : coracao05)))).setX((i == 0 ? campoBatalha1 : (i == 1 ? campoBatalha2 : (i == 2 ? campoBatalha3 : (i == 3 ? campoBatalha4 : campoBatalha5)))).getX() + 100 + (4 * matrizAventureiros[1][i] < 0? 0 : 4 * matrizAventureiros[1][i]));
				if(i < 4) {
					(i == 0 ? coracao02 : (i == 1 ? coracao03 : (i == 2 ? coracao04 : coracao05))).setDx((i == 0 ? coracao02 : (i == 1 ? coracao03 : (i == 2 ? coracao04 : coracao05))).getDx() + comecarAnimacaoCoracao);
				} else {
					animacaoFileira = 21; zerarDx();
				}
				i++;
			}
		}
	
		System.out.println("total real: " + matrizAventureiros[1][0] + ", " + matrizAventureiros[1][1] + ", " + matrizAventureiros[1][2] + ", " + matrizAventureiros[1][3] + ", " + matrizAventureiros[1][4] + "\n");
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  			organiza os Cães  de acordo com sua performance na rodada da batalha			|
	\ ---------------------------------------------------------------------------------------- */
	
	public void OrganizarCampos() {

		int [][] listaProvisoria = new int[3][5];
					
		for(int i=0; i<5; i++) {
			listaProvisoria[0][i] = matrizAventureiros[0][i];
			listaProvisoria[1][i] = matrizAventureiros[1][i];
			listaProvisoria[2][i] = matrizAventureiros[6][i];
		}
		
		for(int i2=0; i2<5; i2++) {
			for(int i=0; i<5; i++) {
				if(listaProvisoria[1][i] >= listaProvisoria[1][0] && listaProvisoria[1][i] >= listaProvisoria[1][1] && listaProvisoria[1][i] >= listaProvisoria[1][2] && listaProvisoria[1][i] >= listaProvisoria[1][3] && listaProvisoria[1][i] >= listaProvisoria[1][4]) {
					matrizAventureiros[0][i2] = listaProvisoria[0][i];
					matrizAventureiros[1][i2] = listaProvisoria[1][i];
					matrizAventureiros[6][i2] = listaProvisoria[2][i];
					listaProvisoria[1][i] = -100;
					break;
				}
			}	
		}
	
		coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
		
		if(coracao11.getDx() >= intervaloAnimacao * 2) {
			apagarCoracoes();
			coracao01.setX(campoBatalha1.getX() + 100 + (4 * matrizAventureiros[1][0] < 0? 0 : 4 * matrizAventureiros[1][0]));
			coracao02.setX(campoBatalha2.getX() + 100 + (4 * matrizAventureiros[1][1] < 0? 0 : 4 * matrizAventureiros[1][1]));
			coracao03.setX(campoBatalha3.getX() + 100 + (4 * matrizAventureiros[1][2] < 0? 0 : 4 * matrizAventureiros[1][2]));
			coracao04.setX(campoBatalha4.getX() + 100 + (4 * matrizAventureiros[1][3] < 0? 0 : 4 * matrizAventureiros[1][3]));
			coracao05.setX(campoBatalha5.getX() + 100 + (4 * matrizAventureiros[1][4] < 0? 0 : 4 * matrizAventureiros[1][4]));
			
			System.out.println("total  5 : " + matrizAventureiros[5][0] + " " + matrizAventureiros[5][1] + " " + matrizAventureiros[5][2] + " " + matrizAventureiros[5][3] + " " + matrizAventureiros[5][4]);

			posicaoAventureiro = (matrizAventureiros[0][0] == aventureiro ? 0 : (matrizAventureiros[0][1] == aventureiro ? 1 : (matrizAventureiros[0][2] == aventureiro ? 2 : (matrizAventureiros[0][3] == aventureiro ? 3 : 4)))); comecarAnimacaoCoracao = 0;
			matrizAventureiros[5][0] = 0; matrizAventureiros[5][1] = 0; matrizAventureiros[5][2] = 0; matrizAventureiros[5][3] = 0; matrizAventureiros[5][4] = 0;
			danoEfeito4[0]=0; danoEfeito4[1]=0; danoEfeito4[2]=0; danoEfeito4[3]=0; danoEfeito4[4]=0;
			System.out.println("total  5 : " + matrizAventureiros[5][0] + " " + matrizAventureiros[5][1] + " " + matrizAventureiros[5][2] + " " + matrizAventureiros[5][3] + " " + matrizAventureiros[5][4]);

			zerarDx();
			
			repintarCampoBatalha();
			
			if(contEtapasBatalha == 5) {
				vezDoAventureiro=0;
				animacaoFileira = 22;
				
			} else {
				animacaoFileira = -1;
				vezDoAventureiro=0;
			}
		}
		
		if(atualizarNomeHabili == false) {
			atualizarNomeHabili = true;
			
			nomeHabAnterior = selecaoNomeHab;
			
			nomeHabilidade2.load("res\\batalha\\" + (nomeHabAnterior == 1 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade3.load("res\\batalha\\" + (nomeHabAnterior == 2 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade4.load("res\\batalha\\" + (nomeHabAnterior == 3 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade1.load("res\\batalha\\nomeHabilidadeSelecionado.png");
			
			nomeApelo1.setCorTexto((selecaoNomeHab == 0 ? Color.GRAY : Color.BLACK));
			nomeApelo2.setCorTexto((selecaoNomeHab == 1 ? Color.GRAY : Color.BLACK));
			nomeApelo3.setCorTexto((selecaoNomeHab == 2 ? Color.GRAY : Color.BLACK));
			nomeApelo4.setCorTexto((selecaoNomeHab == 3 ? Color.GRAY : Color.BLACK));
			
			selecaoNomeHab =0;
		}
		
		itensDoApelo();
		
		textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
		textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
		textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
		textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
		
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						determina o vencedor da batalha e o parabeniza						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void vencedor() {
		comecarAnimacaoCoracao =1;
		coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
		
		if(coracao11.getDx() >= intervaloAnimacao * 5) {
			
			parabenizacaoVencedor.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][0]] + (matrizAventureiros[0][0] == aventureiro ? "\\vencedor.png" : "\\perdedor.png") );
			
			coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
			if(coracao11.getDx() >= intervaloAnimacao * 5) {
				zerarDx();
				comecarAnimacaoCoracao =0;
				contEtapasBatalha =6;
			}	
		}
	}
	
}
