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
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(41,66);
	private Icones_interativos bntRegras = new Icones_interativos(41,202);
	private Icones_interativos bntVoltar = new Icones_interativos(41,338);
	
	boolean mostrarMenu = false;
	int contMenu = 0;
	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------

	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(1234/2 - 706/2 + 110, 640/2 - 278/2 + 190);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(1234/2 - 706/2 + 480, 640/2 - 278/2 + 190);
	
	
	Texto txtDialogoAviso = new Texto(1234/2 - 706/2 + 110, 548/2 - 28, " ");
	Texto txtDialogoAviso2 = new Texto(1234/2 - 706/2 + 250, 548/2 + 52, " ");
	
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

							    //posição aventu,    apelo atual      apelo gerado       dano dado     efeito do dano    pontos atuiais  poder anterior usado  tipo do ataque
	int [][] matrizAventureiros = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {-1, -1, -1, -1, -1}, {0, 0, 0, 0, 0}}; 
	
	//quantidade de interferencia q recebem de cada um  ignis             ayla            rexthor           kiki            arius
	private int [][] interferenciasRecebidas = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

	int [] apeloRepetido = {0, 0, 0, 0, 0};
	
	boolean [] somaDeApelo = {false, false, false, false, false};
	
	 //       dano, efeito, posicaoNaLista
	int [] valoresInterferencia = {-1,-1,-1};
	
	private int aventureiro;
	private int adversario;
	private int posicaoAventureiro;
	int vezDoAventureiro = 0;
	
	private int contEtapasBatalha = 0;
	
	int TerminouLoopEfeitoInterf = 0;
	
	int [] efeitoChefeDeFase = {0, 0, 0 ,0 ,0};
	
	int [] danoEfeito4 = {0, 0, 0, 0, 0};
	int Efeito4 = 0;
			     // rex , arius
	int [] Efeito6 = {0, 0};

	private Random aleatorioHabAdver = new Random();
	int [] arrayAleatorioHabAdver = {aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4)};

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
	
		int intervaloAnimacao = 10;
		int intervaloAnimacaoGif = 200;
		
		private int comecarAnimacaoCoracao = 0;
		private int animacaoFileira = -1;
		
		boolean Iniciargif = false;
		
		boolean [] animacaoEfeitoConcluido = {false, false, false, false, false};
	
	// --------------------------------- campo batalha e hablidades usadas -----------------------------------------
	
	private Texto txtEfeitoFase = new Texto(tamanhoContorno + 700, campoBatalha1.getY() + 70/2 + 7, " ");
	private Icones_interativos efeitoFase = new Icones_interativos(txtEfeitoFase.getX() + 30, txtEfeitoFase.getY() - 17);
	
	private Icones_interativos iconeCampoBatalha1 = new Icones_interativos(tamanhoContorno + 760 + 4 + 5, tamanhoContorno + 5);
	private Icones_interativos iconeCampoBatalha2 = new Icones_interativos(campoBatalha1.getX() + 5, campoBatalha1.getY() + 70 + 4 + 5);
	private Icones_interativos iconeCampoBatalha3 = new Icones_interativos(campoBatalha1.getX() + 5, campoBatalha2.getY() + 70 + 4 + 5);
	private Icones_interativos iconeCampoBatalha4 = new Icones_interativos(campoBatalha1.getX() + 5, campoBatalha3.getY() + 70 + 4 + 5);
	private Icones_interativos iconeCampoBatalha5 = new Icones_interativos(campoBatalha1.getX() + 5, campoBatalha4.getY() + 70 + 4 + 5);
	
	private Icones_interativos seletorAventureiro = new Icones_interativos(iconeCampoBatalha1.getX() - 3, iconeCampoBatalha1.getY() - 3);
	
	// corações que mede o total de apelo e interferencia de todas as rodadas 
	private Icones_interativos coracao01 = new Icones_interativos(campoBatalha1.getX() + 100, campoBatalha1.getY() + 70/2);
	private Icones_interativos coracao02 = new Icones_interativos(campoBatalha2.getX() + 100, campoBatalha2.getY() + 70/2);
	private Icones_interativos coracao03 = new Icones_interativos(campoBatalha3.getX() + 100, campoBatalha3.getY() + 70/2);
	private Icones_interativos coracao04 = new Icones_interativos(campoBatalha4.getX() + 100, campoBatalha4.getY() + 70/2);
	private Icones_interativos coracao05 = new Icones_interativos(campoBatalha5.getX() + 100, campoBatalha5.getY() + 70/2);
	
	// corações que mede o apelo e interferencia da rodada
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
	
	boolean atualizarNomeHabili = false;
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

	int [] dados = {0, 0, 0, 0, 0};
	boolean vezDados = false;
	
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
			bntSimDialogoAviso.load("res\\bntsim.png");
			bntNaoDialogoAviso.load("res\\bntnao2.png");
			
			txtDialogoAviso.setTexto(" ");
			txtDialogoAviso2.setTexto("Você deseja voltar?");
			bntSimNaoDialgoAviso = true;
			
		} else if(dialogoAviso.getImagem() != null && (codigo == KeyEvent.VK_X || (codigo == KeyEvent.VK_Z && bntSimNaoDialgoAviso == false))) {
			
			dialogoAviso.setImagem(null);
			bntSimDialogoAviso.setImagem(null);
			bntNaoDialogoAviso.setImagem(null);
			
			txtDialogoAviso.setTexto(" ");
			txtDialogoAviso2.setTexto(" ");
			
		}else if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) {
			bntSimNaoDialgoAviso = !bntSimNaoDialgoAviso;
			
			bntSimDialogoAviso.load("res\\bntsim" + (bntSimNaoDialgoAviso == true ? "" : "2") + ".png");
			bntNaoDialogoAviso.load("res\\bntnao" + (bntSimNaoDialgoAviso == true ? "2" : "") + ".png");
			
		}else if(codigo == KeyEvent.VK_Z && dialogoAviso.getImagem() != null) {
			
			JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        janelaPrincipal.add(paginaAnterior);
	        janelaPrincipal.setTitle("Escolha de Adversário");
	        janelaPrincipal.revalidate();
		
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						mostra a quantidade de apelo e interferencia						|
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
			
			switch (contMenu) {
				case 0:
					bntMenu.load("res\\bntMenu2.png");
					bntRegras.load("res\\bntRegras1.png");
					bntVoltar.load("res\\bntVoltar1.png");
					break;
				case 1:
					bntRegras.load("res\\bntRegras2.png");
					bntVoltar.load("res\\bntVoltar1.png");
					bntMenu.load("res\\bntMenu1.png");
					break;
				case 2:
					bntVoltar.load("res\\bntVoltar2.png");
					bntMenu.load("res\\bntMenu1.png");
					bntRegras.load("res\\bntRegras1.png");
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
			
			for(int i=0; i<5;i++) {
				if(matrizAventureiros[0][i] == 0) {
					matrizAventureiros[7][i] = apeloIgnis[3][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[5][i] = apeloIgnis[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloIgnis[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[3][i] = apeloIgnis[1][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[4][i] = apeloIgnis[2][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab);
					
					if(apeloRepetido[i] == 1) {
						matrizAventureiros[5][i] = matrizAventureiros[5][i] - 2;
					}
					
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
				else if(matrizAventureiros[0][i] == 1) {
					matrizAventureiros[7][i] = apeloAyla[3][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[5][i] = apeloAyla[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloAyla[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[3][i] = apeloAyla[1][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[4][i] = apeloAyla[2][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab);

					if(apeloRepetido[i] == 1) {
						matrizAventureiros[5][i] = matrizAventureiros[5][i] - 2;
					}
					
					if((adversario == 0 && matrizAventureiros[4][i] != -1) || (adversario == 1 && matrizAventureiros[4][i] == -1) || (adversario == 2 && matrizAventureiros[7][i] == 1) || (adversario == 3 && matrizAventureiros[4][i] != -1) || (adversario == 4 && matrizAventureiros[4][i] == -1)) {
						efeitoChefeDeFase[i] = 1;
						
						if(adversario == 0 || adversario == 1) {
							matrizAventureiros[5][i]--;
						}
						else {
							matrizAventureiros[5][i]++;
						}
					}
				}
				else if(matrizAventureiros[0][i] == 2) {
					matrizAventureiros[7][i] = apeloRexthor[3][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[5][i] = apeloRexthor[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloRexthor[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[3][i] = apeloRexthor[1][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[4][i] = apeloRexthor[2][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab);

					if(apeloRepetido[i] == 1) {
						matrizAventureiros[5][i] = matrizAventureiros[5][i] - 2;
					}

					if((adversario == 0 && matrizAventureiros[4][i] != -1) || (adversario == 1 && matrizAventureiros[4][i] == -1) || (adversario == 2 && matrizAventureiros[7][i] == 1) || (adversario == 3 && matrizAventureiros[4][i] != -1) || (adversario == 4 && matrizAventureiros[4][i] == -1)) {
						efeitoChefeDeFase[i] = 1;
						
						if(adversario == 0 || adversario == 1) {
							matrizAventureiros[5][i]--;
						}
						else {
							matrizAventureiros[5][i]++;
						}
					}
				}
				else if(matrizAventureiros[0][i] == 3) {
					matrizAventureiros[7][i] = apeloKiki[3][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[5][i] = apeloKiki[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloKiki[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[3][i] = apeloKiki[1][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[4][i] = apeloKiki[2][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab);

					if(apeloRepetido[i] == 1) {
						matrizAventureiros[5][i] = matrizAventureiros[5][i] - 2;
					}
					
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
				else if(matrizAventureiros[0][i] == 4) {
					matrizAventureiros[7][i] = apeloArius[3][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[5][i] = apeloArius[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloArius[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[3][i] = apeloArius[1][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					matrizAventureiros[4][i] = apeloArius[2][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab);

					if(apeloRepetido[i] == 1) {
						System.out.println("matriz 5 atual:" + matrizAventureiros[5][i]);
						matrizAventureiros[5][i] = matrizAventureiros[5][i] - 2;
						System.out.println("matriz 5 depois do apelo repetido:" + matrizAventureiros[5][i]);
					}
					
					if((adversario == 0 && matrizAventureiros[4][i] != -1) || (adversario == 1 && matrizAventureiros[4][i] == -1) || (adversario == 2 && matrizAventureiros[7][i] == 1) || (adversario == 3 && matrizAventureiros[4][i] != -1) || (adversario == 4 && matrizAventureiros[4][i] == -1)) {
						efeitoChefeDeFase[i] = 1;
						
						if(adversario == 0 || adversario == 1) {
							matrizAventureiros[5][i]--;
						}
						else {
							matrizAventureiros[5][i]++;
						}
					}
				}
			}
			
			// --------------------------- subtrair efeito ----------------------------------
			
			//1: todos abaixo
			if(matrizAventureiros[4][0] == 1 ) {matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][0]; matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][0]; matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][0];matrizAventureiros[5][4] = matrizAventureiros[5][4] - matrizAventureiros[3][0]; } 
			//4: zera seus pontos negativos,
			if(matrizAventureiros[4][0] == 4 && matrizAventureiros[5][0] < 0) {danoEfeito4[0] = matrizAventureiros[5][0]; matrizAventureiros[5][0] = 0;}
			//5: um acima e um abaixo,
			if(matrizAventureiros[4][0] == 5 ) {matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][0];}

			//0: todos acima
			if(matrizAventureiros[4][1] == 0 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][1];}
			//1: todos abaixo
			if(matrizAventureiros[4][1] == 1 ) {matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][1]; matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][1]; matrizAventureiros[5][4] = matrizAventureiros[5][4] - matrizAventureiros[3][1];}
			//2: um acima,
			if(matrizAventureiros[4][1] == 2 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][1];}
			//3: primeiro,
			if(matrizAventureiros[4][1] == 3 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][1];}
			//4: zera seus pontos negativos,
			if(matrizAventureiros[4][1] == 4 && matrizAventureiros[5][1] < 0) {danoEfeito4[1] = matrizAventureiros[5][1]; matrizAventureiros[5][1] = 0;}
			//5: um acima e um abaixo,
			if(matrizAventureiros[4][1] == 5 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][1]; matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][1];}
			
			//0: todos acima
			if(matrizAventureiros[4][2] == 0 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][2]; matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][2];}
			//1: todos abaixo
			if(matrizAventureiros[4][2] == 1 ) {matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][2]; matrizAventureiros[5][4] = matrizAventureiros[5][4] - matrizAventureiros[3][2];}
			//2: um acima,
			if(matrizAventureiros[4][2] == 2 ) {matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][2];}
			//3: primeiro,
			if(matrizAventureiros[4][2] == 3 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][2];}
			//4: zera seus pontos negativos,
			if(matrizAventureiros[4][2] == 4 && matrizAventureiros[5][2] < 0) {danoEfeito4[2] = matrizAventureiros[5][2]; matrizAventureiros[5][2] = 0;}
			//5: um acima e um abaixo,
			if(matrizAventureiros[4][2] == 5 ) {matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][2]; matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][2];}
			
			//0: todos acima
			if(matrizAventureiros[4][3] == 0 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][3]; matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][3]; matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][3];}
			//1: todos abaixo
			if(matrizAventureiros[4][3] == 1 ) {matrizAventureiros[5][4] = matrizAventureiros[5][4] - matrizAventureiros[3][3];}
			//2: um acima,
			if(matrizAventureiros[4][3] == 2 ) {matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][3];}
			//3: primeiro,
			if(matrizAventureiros[4][3] == 3 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][3];}
			//4: zera seus pontos negativos,
			if(matrizAventureiros[4][3] == 4 && matrizAventureiros[5][3] < 0) {danoEfeito4[3] = matrizAventureiros[5][3]; matrizAventureiros[5][3] = 0;}
			//5: um acima e um abaixo,
			if(matrizAventureiros[4][3] == 5 ) {matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][3]; matrizAventureiros[5][4] = matrizAventureiros[5][4] - matrizAventureiros[3][3];}
			
			//0: todos acima
			if(matrizAventureiros[4][4] == 0 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][4]; matrizAventureiros[5][1] = matrizAventureiros[5][1] - matrizAventureiros[3][4]; matrizAventureiros[5][2] = matrizAventureiros[5][2] - matrizAventureiros[3][4]; matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][4];}
			//2: um acima,
			if(matrizAventureiros[4][4] == 2 ) {matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][4];}
			//3: primeiro,
			if(matrizAventureiros[4][4] == 3 ) {matrizAventureiros[5][0] = matrizAventureiros[5][0] - matrizAventureiros[3][4];}
			//4: zera seus pontos negativos,
			if(matrizAventureiros[4][4] == 4 && matrizAventureiros[5][4] < 0) {danoEfeito4[4] = matrizAventureiros[5][4]; System.out.println("pontos atuais da matriz 5: " + matrizAventureiros[5][4]); matrizAventureiros[5][4] = 0;}
			//5: um acima e um abaixo,
			if(matrizAventureiros[4][4] == 5 ) {matrizAventureiros[5][3] = matrizAventureiros[5][3] - matrizAventureiros[3][4];}
			
			
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
			
		// --------------------- termina a parabenização para a escolha de adversario ------------------
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
			
			System.out.println("restaurar: " + matrizAventureiros[2][posicaoNaLista]);

			switch (posicaoNaLista) {
				case 0: animacaoFileira = 0; break;
				case 1: animacaoFileira = 2; break;
				case 2: animacaoFileira = 4; break;
				case 3: animacaoFileira = 6; break;
				case 4: animacaoFileira = 8; break;
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
				
				System.out.println("veiooooo");
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
			
		// ------------------------- -1 nenhum --------------------------------
		} else if(TerminouLoopEfeitoInterf == 0 || (efeito == 4 && ((posicaoNaLista == 0 ? coracao11 : (posicaoNaLista == 1 ? coracao12 : (posicaoNaLista == 2 ? coracao13 : (posicaoNaLista == 3 ? coracao14 : coracao15)))).getImagem() == null))){

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
		
		iconeCampoBatalha1.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][0]] + "\\iconeCampoBatalha.png");
		iconeCampoBatalha2.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][1]] + "\\iconeCampoBatalha.png");
		iconeCampoBatalha3.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][2]] + "\\iconeCampoBatalha.png");
		iconeCampoBatalha4.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][3]] + "\\iconeCampoBatalha.png");
		iconeCampoBatalha5.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][4]] + "\\iconeCampoBatalha.png");
		
		seletorAventureiro.setY((posicaoAventureiro == 0 ? iconeCampoBatalha1.getY() : (posicaoAventureiro == 1 ? iconeCampoBatalha2.getY() : (posicaoAventureiro == 2 ? iconeCampoBatalha3.getY() : (posicaoAventureiro == 3 ? iconeCampoBatalha4.getY() : iconeCampoBatalha5.getY()))) ) - 3);		
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
	|  							organiza os Cães apos as etapas da batalha						|
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
			boolean encontro = false;
			int adv = adversario;
			int ave = aventureiro;
			
			matrizAventureiros[0][0] = adv;
			matrizAventureiros[0][4] = ave;
			adv = adv + 1 < 5 ? adv +1 : adv + 1 - 5;
			encontro = adv == ave ? true : encontro;
			adv = adv == ave ? (adv+1 < 5 ? adv+1 : adv+1 -5) : adv;
			matrizAventureiros[0][1] = adv;
			adv = adv + 1 < 5 ? adv +1 : adv + 1 - 5;
			encontro = adv == ave ? true : encontro;
			adv = adv == ave ? (adv+1 < 5 ? adv+1 : adv+1 -5) : adv;
			matrizAventureiros[0][2] = adv;
			adv = adv + 1 < 5 ? adv +1 : adv + 1 - 5;
			encontro = adv == ave ? true : encontro;
			adv = adv == ave ? (adv+1 < 5 ? adv+1 : adv+1 -5) : adv;
			matrizAventureiros[0][3] = adv;
			
			posicaoAventureiro = 4;
		}		
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		
		graficos.drawImage(animacao.getImagem(), animacao.getX(), animacao.getY(), this);
		
		graficos.drawImage(campoBatalha1.getImagem(), campoBatalha1.getX(), campoBatalha1.getY(), this);
		graficos.drawImage(campoBatalha2.getImagem(), campoBatalha2.getX(), campoBatalha2.getY(), this);
		graficos.drawImage(campoBatalha3.getImagem(), campoBatalha3.getX(), campoBatalha3.getY(), this);
		graficos.drawImage(campoBatalha4.getImagem(), campoBatalha4.getX(), campoBatalha4.getY(), this);
		graficos.drawImage(campoBatalha5.getImagem(), campoBatalha5.getX(), campoBatalha5.getY(), this);
		
		graficos.drawImage(apelo.getImagem(), apelo.getX(), apelo.getY(), this);
		
		graficos.drawImage(descricao.getImagem(), descricao.getX(), descricao.getY(), this);

		graficos.drawImage(imgDado1.getImagem(), imgDado1.getX(), imgDado1.getY(), this);
		graficos.drawImage(imgDado2.getImagem(), imgDado2.getX(), imgDado2.getY(), this);
		graficos.drawImage(imgDado3.getImagem(), imgDado3.getX(), imgDado3.getY(), this);
		graficos.drawImage(imgDado4.getImagem(), imgDado4.getX(), imgDado4.getY(), this);
		graficos.drawImage(imgDado5.getImagem(), imgDado5.getX(), imgDado5.getY(), this);
		
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
		
		graficos.drawImage(nomeHabilidade1.getImagem(), nomeHabilidade1.getX(), nomeHabilidade1.getY(), this);
		graficos.drawImage(nomeHabilidade2.getImagem(), nomeHabilidade2.getX(), nomeHabilidade2.getY(), this);
		graficos.drawImage(nomeHabilidade3.getImagem(), nomeHabilidade3.getX(), nomeHabilidade3.getY(), this);
		graficos.drawImage(nomeHabilidade4.getImagem(), nomeHabilidade4.getX(), nomeHabilidade4.getY(), this);
		
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
		
	    tl5 = new TextLayout(textoDescricao1.getTexto(), textoDescricao1.getFonte(), frc);
	    tl15 = new TextLayout(textoDescricao2.getTexto(), textoDescricao2.getFonte(), frc);
	    tl16 = new TextLayout(textoDescricao3.getTexto(), textoDescricao3.getFonte(), frc);
	    tl17 = new TextLayout(textoDescricao4.getTexto(), textoDescricao4.getFonte(), frc);
	    
	    tl5.draw(graficos, textoDescricao1.getX(), textoDescricao1.getY());
	    tl15.draw(graficos, textoDescricao2.getX(), textoDescricao2.getY());
	    tl16.draw(graficos, textoDescricao3.getX(), textoDescricao3.getY());
	    tl17.draw(graficos, textoDescricao4.getX(), textoDescricao4.getY());
	    
		graficos.drawImage(parabenizacaoVencedor.getImagem(), parabenizacaoVencedor.getX(), parabenizacaoVencedor.getY(), this);
		
		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getX(), sombreadorMenu.getY(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getX(), fundoMenu.getY(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getX(), bntMenu.getY(), this);
		graficos.drawImage(bntRegras.getImagem(), bntRegras.getX(), bntRegras.getY(), this);
		graficos.drawImage( bntVoltar.getImagem(),  bntVoltar.getX(),  bntVoltar.getY(), this);
		
		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getX(), dialogoAviso.getY(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getX(), bntSimDialogoAviso.getY(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getX(), bntNaoDialogoAviso.getY(), this);
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl20 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl21 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso2.getFonte(), frc);
		
		tl20.draw(graficos, txtDialogoAviso.getX(), txtDialogoAviso.getY());
		tl21.draw(graficos, txtDialogoAviso2.getX(), txtDialogoAviso2.getY());
		
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);

		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// ----------- aparece os d20 ou começa a mostrar os apelos ganhos ----------------
		if(vezDados == true) {
			aparecerDados();
		}else if(animacaoFileira != -1) {
			aparecerCoracoes();
		}
		
		// --------------------- começa a colocar as interferencias ---------------------
		if(animacaoFileira >= 10 && animacaoFileira < 20) {
			sumirCoracoes();
		}
		
		// -------------- mexe o medidor de ganho de apelo das etapa da batalha ----------------
		if(animacaoFileira == 20) {
			mexerMedidorApelos();
		}
		
		// -------------- organiza os cães de acordo com a performace na batalha ----------------
		if(animacaoFileira == 21) {
			OrganizarCampos();
		}
		
		// -------------- mostra a parabenização no final da batalha ----------------
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
		imgDado1.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[0] + ".png");
		imgDado2.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[1] + ".png");
		imgDado3.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[2] + ".png");
		imgDado4.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[3] + ".png");
		imgDado5.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[4] + ".png");
		
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
			
		if(animacaoFileira == 0 || animacaoFileira == 1 ) {
			if(animacaoFileira == 0 && (matrizAventureiros[2][0] > 0 || matrizAventureiros[4][0] == 4 || matrizAventureiros[4][0] == 6)) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao110.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoopEfeitoInterf =0;
					
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][0], 0 != posicaoAventureiro ? arrayAleatorioHabAdver[0] : selecaoNomeHab);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao110.setDx(coracao110.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0  && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
				
				
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
					
					if(coracao110.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
						matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao110.getImagem() == null ? 0 : (coracao110.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao110.getImagem() != null && coracao110.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao110.setImagem(null);} coracao19.setDx(coracao19.getDx() + comecarAnimacaoCoracao);
						if(coracao19.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
							matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao19.getImagem() == null ? 0 : (coracao19.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao19.getImagem() != null && coracao19.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao19.setImagem(null);} coracao18.setDx(coracao18.getDx() + comecarAnimacaoCoracao);
							if(coracao18.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
								matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao18.getImagem() == null ? 0 : (coracao18.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao18.getImagem() != null && coracao18.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao18.setImagem(null);} coracao17.setDx(coracao17.getDx() + comecarAnimacaoCoracao);
								if(coracao17.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
									matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao17.getImagem() == null ? 0 : (coracao17.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao17.getImagem() != null && coracao17.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao17.setImagem(null);} coracao16.setDx(coracao16.getDx() + comecarAnimacaoCoracao);
									if(coracao16.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
										matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao16.getImagem() == null ? 0 : (coracao16.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao16.getImagem() != null && coracao16.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao16.setImagem(null);} coracao15.setDx(coracao15.getDx() + comecarAnimacaoCoracao);
										if(coracao15.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
											matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao15.getImagem() == null ? 0 : (coracao15.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao15.getImagem() != null && coracao15.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao15.setImagem(null);} coracao14.setDx(coracao14.getDx() + comecarAnimacaoCoracao);
											if(coracao14.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
												matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao14.getImagem() == null ? 0 : (coracao14.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao14.getImagem() != null && coracao14.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao14.setImagem(null);} coracao13.setDx(coracao13.getDx() + comecarAnimacaoCoracao);
												if(coracao13.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
													matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao13.getImagem() == null ? 0 : (coracao13.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao13.getImagem() != null && coracao13.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao13.setImagem(null);} coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
													if(coracao12.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
														matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao12.getImagem() == null ? 0 : (coracao12.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao12.getImagem() != null && coracao12.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao12.setImagem(null);} coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
														if(coracao11.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {
															matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao11.getImagem() == null ? 0 : (coracao11.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao11.getImagem() != null && coracao11.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao11.setImagem(null);} animacaoFileira=1; zerarDx();
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
														}if(matrizAventureiros[2][0] == 0 && Efeito4 != 1) {animacaoFileira = 1; zerarDx();}
													}
			
				if((matrizAventureiros[0][0] == 0 || matrizAventureiros[0][0] == 1) && matrizAventureiros[4][0] == 4 && matrizAventureiros[2][0] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {animacaoFileira = 1; zerarDx();}
				
			}else if(animacaoFileira == 1 || matrizAventureiros[2][0] == 0) {
				
				coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}

				Iniciargif = false;
				animacao.setDx(0);
						
				if(coracao11.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao11.getImagem() == null ? 1 : 0); coracao11.load("res\\batalha\\apelo.png"); coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
					if(coracao12.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao12.getImagem() == null ? 1 : 0); coracao12.load("res\\batalha\\apelo.png");coracao13.setDx(coracao13.getDx() + comecarAnimacaoCoracao);
						if(coracao13.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao13.getImagem() == null ? 1 : 0); coracao13.load("res\\batalha\\apelo.png");coracao14.setDx(coracao14.getDx() + comecarAnimacaoCoracao);
							if(coracao14.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao14.getImagem() == null ? 1 : 0); coracao14.load("res\\batalha\\apelo.png");coracao15.setDx(coracao15.getDx() + comecarAnimacaoCoracao);
								if(coracao15.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao15.getImagem() == null ? 1 : 0); coracao15.load("res\\batalha\\apelo.png");coracao16.setDx(coracao16.getDx() + comecarAnimacaoCoracao);
									if(coracao16.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao16.getImagem() == null ? 1 : 0); coracao16.load("res\\batalha\\apelo.png");coracao17.setDx(coracao17.getDx() + comecarAnimacaoCoracao);
										if(coracao17.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao17.getImagem() == null ? 1 : 0); coracao17.load("res\\batalha\\apelo.png");coracao18.setDx(coracao18.getDx() + comecarAnimacaoCoracao);
											if(coracao18.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao18.getImagem() == null ? 1 : 0); coracao18.load("res\\batalha\\apelo.png");coracao19.setDx(coracao19.getDx() + comecarAnimacaoCoracao);
												if(coracao19.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao19.getImagem() == null ? 1 : 0); coracao19.load("res\\batalha\\apelo.png");coracao110.setDx(coracao110.getDx() + comecarAnimacaoCoracao);
													if(coracao110.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) { matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao110.getImagem() == null ? 1 : 0); coracao110.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else {interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoopEfeitoInterf != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {habilidadeRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
			}
				
		}else if(animacaoFileira == 2 || animacaoFileira == 3 ) {
			if(animacaoFileira == 2) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao210.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoopEfeitoInterf =0;
					
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][1], 1 != posicaoAventureiro ? arrayAleatorioHabAdver[1] : selecaoNomeHab);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao210.setDx(coracao210.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
				
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
					
					if(coracao210.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
						matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao210.getImagem() == null ? 0 : (coracao210.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao210.getImagem() != null && coracao210.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao210.setImagem(null);} coracao29.setDx(coracao29.getDx() + comecarAnimacaoCoracao);
						if(coracao29.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
							matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao29.getImagem() == null ? 0 : (coracao29.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao29.getImagem() != null && coracao29.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao29.setImagem(null);} coracao28.setDx(coracao28.getDx() + comecarAnimacaoCoracao);
							if(coracao28.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
								matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao28.getImagem() == null ? 0 : (coracao28.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao28.getImagem() != null && coracao28.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao28.setImagem(null);} coracao27.setDx(coracao27.getDx() + comecarAnimacaoCoracao);
								if(coracao27.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
									matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao27.getImagem() == null ? 0 : (coracao27.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao27.getImagem() != null && coracao27.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao27.setImagem(null);} coracao26.setDx(coracao26.getDx() + comecarAnimacaoCoracao);
									if(coracao26.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
										matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao26.getImagem() == null ? 0 : (coracao26.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao26.getImagem() != null && coracao26.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao26.setImagem(null);} coracao25.setDx(coracao25.getDx() + comecarAnimacaoCoracao);
										if(coracao25.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
											matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao25.getImagem() == null ? 0 : (coracao25.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao25.getImagem() != null && coracao25.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao25.setImagem(null);} coracao24.setDx(coracao24.getDx() + comecarAnimacaoCoracao);
											if(coracao24.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
												matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao24.getImagem() == null ? 0 : (coracao24.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao24.getImagem() != null && coracao24.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao24.setImagem(null);} coracao23.setDx(coracao23.getDx() + comecarAnimacaoCoracao);
												if(coracao23.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
													matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao23.getImagem() == null ? 0 : (coracao23.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao23.getImagem() != null && coracao23.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao23.setImagem(null);} coracao22.setDx(coracao22.getDx() + comecarAnimacaoCoracao);
													if(coracao22.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
														matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao22.getImagem() == null ? 0 : (coracao22.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao22.getImagem() != null && coracao22.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao22.setImagem(null);} coracao21.setDx(coracao21.getDx() + comecarAnimacaoCoracao);
														if(coracao21.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {
															matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao21.getImagem() == null ? 0 : (coracao21.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao21.getImagem() != null && coracao21.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao21.setImagem(null);} zerarDx(); animacaoFileira=3;
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
														}if(matrizAventureiros[2][1] == 0 && Efeito4 != 1) {animacaoFileira = 3; zerarDx();}
													}
													
			}else if(animacaoFileira == 3 || matrizAventureiros[2][1] == 0) {
				coracao21.setDx(coracao21.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
				
				Iniciargif = false; animacao.setDx(0);
				
				if(coracao21.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao21.getImagem() == null ? 1 : 0); coracao21.load("res\\batalha\\apelo.png"); coracao22.setDx(coracao22.getDx() + comecarAnimacaoCoracao);
					if(coracao22.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao22.getImagem() == null ? 1 : 0); coracao22.load("res\\batalha\\apelo.png");coracao23.setDx(coracao23.getDx() + comecarAnimacaoCoracao);
						if(coracao23.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao23.getImagem() == null ? 1 : 0); coracao23.load("res\\batalha\\apelo.png");coracao24.setDx(coracao24.getDx() + comecarAnimacaoCoracao);
							if(coracao24.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao24.getImagem() == null ? 1 : 0); coracao24.load("res\\batalha\\apelo.png");coracao25.setDx(coracao25.getDx() + comecarAnimacaoCoracao);
								if(coracao25.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao25.getImagem() == null ? 1 : 0); coracao25.load("res\\batalha\\apelo.png");coracao26.setDx(coracao26.getDx() + comecarAnimacaoCoracao);
									if(coracao26.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao26.getImagem() == null ? 1 : 0); coracao26.load("res\\batalha\\apelo.png");coracao27.setDx(coracao27.getDx() + comecarAnimacaoCoracao);
										if(coracao27.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao27.getImagem() == null ? 1 : 0); coracao27.load("res\\batalha\\apelo.png");coracao28.setDx(coracao28.getDx() + comecarAnimacaoCoracao);
											if(coracao28.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao28.getImagem() == null ? 1 : 0); coracao28.load("res\\batalha\\apelo.png");coracao29.setDx(coracao29.getDx() + comecarAnimacaoCoracao);
												if(coracao29.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao29.getImagem() == null ? 1 : 0); coracao29.load("res\\batalha\\apelo.png");coracao210.setDx(coracao210.getDx() + comecarAnimacaoCoracao);
													if(coracao210.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao210.getImagem() == null ? 1 : 0); coracao210.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
			}
				
		}else if(animacaoFileira == 4 || animacaoFileira == 5 ) {

			if(animacaoFileira == 4) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao310.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoopEfeitoInterf =0;
	
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][2], 2 != posicaoAventureiro ? arrayAleatorioHabAdver[2] : selecaoNomeHab);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao310.setDx(coracao310.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
				
				
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
				
					if(coracao310.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
						matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao310.getImagem() == null ? 0 : (coracao310.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao310.getImagem() != null && coracao310.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao310.setImagem(null);} coracao39.setDx(coracao39.getDx() + comecarAnimacaoCoracao);
						if(coracao39.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
							matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao39.getImagem() == null ? 0 : (coracao39.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao39.getImagem() != null && coracao39.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao39.setImagem(null);} coracao38.setDx(coracao38.getDx() + comecarAnimacaoCoracao);
							if(coracao38.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
								matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao38.getImagem() == null ? 0 : (coracao38.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao38.getImagem() != null && coracao38.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao38.setImagem(null);} coracao37.setDx(coracao37.getDx() + comecarAnimacaoCoracao);
								if(coracao37.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
									matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao37.getImagem() == null ? 0 : (coracao37.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao37.getImagem() != null && coracao37.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao37.setImagem(null);} coracao36.setDx(coracao36.getDx() + comecarAnimacaoCoracao);
									if(coracao36.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
										matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao36.getImagem() == null ? 0 : (coracao36.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao36.getImagem() != null && coracao36.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao36.setImagem(null);} coracao35.setDx(coracao35.getDx() + comecarAnimacaoCoracao);
										if(coracao35.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
											matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao35.getImagem() == null ? 0 : (coracao35.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao35.getImagem() != null && coracao35.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao35.setImagem(null);} coracao34.setDx(coracao34.getDx() + comecarAnimacaoCoracao);
											if(coracao34.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
												matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao34.getImagem() == null ? 0 : (coracao34.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao34.getImagem() != null && coracao34.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao34.setImagem(null);} coracao33.setDx(coracao33.getDx() + comecarAnimacaoCoracao);
												if(coracao33.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
													matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao33.getImagem() == null ? 0 : (coracao33.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao33.getImagem() != null && coracao33.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao33.setImagem(null);} coracao32.setDx(coracao32.getDx() + comecarAnimacaoCoracao);
													if(coracao32.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
														matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao32.getImagem() == null ? 0 : (coracao32.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao32.getImagem() != null && coracao32.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao32.setImagem(null);} coracao31.setDx(coracao31.getDx() + comecarAnimacaoCoracao);
														if(coracao31.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {
															matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao31.getImagem() == null ? 0 : (coracao31.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao31.getImagem() != null && coracao31.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao31.setImagem(null);} zerarDx(); animacaoFileira=5;
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
														}if(matrizAventureiros[2][2] == 0 && Efeito4 != 1) {animacaoFileira = 5; zerarDx();}
													}
													
			}else if(animacaoFileira == 5 || matrizAventureiros[2][2] == 0) {
				coracao31.setDx(coracao31.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
				
				Iniciargif = false; animacao.setDx(0);
				
				if(coracao31.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao31.getImagem() == null ? 1 : 0); coracao31.load("res\\batalha\\apelo.png"); coracao32.setDx(coracao32.getDx() + comecarAnimacaoCoracao);
					if(coracao32.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao32.getImagem() == null ? 1 : 0); coracao32.load("res\\batalha\\apelo.png");coracao33.setDx(coracao33.getDx() + comecarAnimacaoCoracao);
						if(coracao33.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao33.getImagem() == null ? 1 : 0); coracao33.load("res\\batalha\\apelo.png");coracao34.setDx(coracao34.getDx() + comecarAnimacaoCoracao);
							if(coracao34.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao34.getImagem() == null ? 1 : 0); coracao34.load("res\\batalha\\apelo.png");coracao35.setDx(coracao35.getDx() + comecarAnimacaoCoracao);
								if(coracao35.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao35.getImagem() == null ? 1 : 0); coracao35.load("res\\batalha\\apelo.png");coracao36.setDx(coracao36.getDx() + comecarAnimacaoCoracao);
									if(coracao36.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao36.getImagem() == null ? 1 : 0); coracao36.load("res\\batalha\\apelo.png");coracao37.setDx(coracao37.getDx() + comecarAnimacaoCoracao);
										if(coracao37.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao37.getImagem() == null ? 1 : 0); coracao37.load("res\\batalha\\apelo.png");coracao38.setDx(coracao38.getDx() + comecarAnimacaoCoracao);
											if(coracao38.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao38.getImagem() == null ? 1 : 0); coracao38.load("res\\batalha\\apelo.png");coracao39.setDx(coracao39.getDx() + comecarAnimacaoCoracao);
												if(coracao39.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao39.getImagem() == null ? 1 : 0); coracao39.load("res\\batalha\\apelo.png");coracao310.setDx(coracao310.getDx() + comecarAnimacaoCoracao);
													if(coracao310.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao310.getImagem() == null ? 1 : 0); coracao310.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
			}
				
		}else if(animacaoFileira == 6 || animacaoFileira == 7 ) {
			if(animacaoFileira == 6) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao410.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoopEfeitoInterf =0;
					
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][3], 3 != posicaoAventureiro ? arrayAleatorioHabAdver[3] : selecaoNomeHab);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao410.setDx(coracao410.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
				
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
				
					if(coracao410.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
						matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao410.getImagem() == null ? 0 : (coracao410.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao410.getImagem() != null && coracao410.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao410.setImagem(null);} coracao49.setDx(coracao49.getDx() + comecarAnimacaoCoracao);
						if(coracao49.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
							matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao49.getImagem() == null ? 0 : (coracao49.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao49.getImagem() != null && coracao49.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao49.setImagem(null);} coracao48.setDx(coracao48.getDx() + comecarAnimacaoCoracao);
							if(coracao48.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
								matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao48.getImagem() == null ? 0 : (coracao48.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao48.getImagem() != null && coracao48.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao48.setImagem(null);} coracao47.setDx(coracao47.getDx() + comecarAnimacaoCoracao);
								if(coracao47.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
									matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao47.getImagem() == null ? 0 : (coracao47.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao47.getImagem() != null && coracao47.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao47.setImagem(null);} coracao46.setDx(coracao46.getDx() + comecarAnimacaoCoracao);
									if(coracao46.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
										matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao46.getImagem() == null ? 0 : (coracao46.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao46.getImagem() != null && coracao46.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao46.setImagem(null);} coracao45.setDx(coracao45.getDx() + comecarAnimacaoCoracao);
										if(coracao45.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
											matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao45.getImagem() == null ? 0 : (coracao45.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao45.getImagem() != null && coracao45.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao45.setImagem(null);} coracao44.setDx(coracao44.getDx() + comecarAnimacaoCoracao);
											if(coracao44.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
												matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao44.getImagem() == null ? 0 : (coracao44.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao44.getImagem() != null && coracao44.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao44.setImagem(null);} coracao43.setDx(coracao43.getDx() + comecarAnimacaoCoracao);
												if(coracao43.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
													matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao43.getImagem() == null ? 0 : (coracao43.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao43.getImagem() != null && coracao43.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao43.setImagem(null);} coracao42.setDx(coracao42.getDx() + comecarAnimacaoCoracao);
													if(coracao42.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
														matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao42.getImagem() == null ? 0 : (coracao42.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao42.getImagem() != null && coracao42.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao42.setImagem(null);} coracao41.setDx(coracao41.getDx() + comecarAnimacaoCoracao);
														if(coracao41.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {
															matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao41.getImagem() == null ? 0 : (coracao41.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao41.getImagem() != null && coracao41.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao41.setImagem(null);} zerarDx(); animacaoFileira=7;
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
														}if(matrizAventureiros[2][3] == 0 && Efeito4 != 1) {animacaoFileira = 7; zerarDx();}
													}
													
			}else if(animacaoFileira == 7 || matrizAventureiros[2][3] == 0) {
				coracao41.setDx(coracao41.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
				
				Iniciargif = false; animacao.setDx(0);
				
				if(coracao41.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao41.getImagem() == null ? 1 : 0); coracao41.load("res\\batalha\\apelo.png"); coracao42.setDx(coracao42.getDx() + comecarAnimacaoCoracao);
					if(coracao42.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao42.getImagem() == null ? 1 : 0); coracao42.load("res\\batalha\\apelo.png");coracao43.setDx(coracao43.getDx() + comecarAnimacaoCoracao);
						if(coracao43.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao43.getImagem() == null ? 1 : 0); coracao43.load("res\\batalha\\apelo.png");coracao44.setDx(coracao44.getDx() + comecarAnimacaoCoracao);
							if(coracao44.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao44.getImagem() == null ? 1 : 0); coracao44.load("res\\batalha\\apelo.png");coracao45.setDx(coracao45.getDx() + comecarAnimacaoCoracao);
								if(coracao45.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao45.getImagem() == null ? 1 : 0); coracao45.load("res\\batalha\\apelo.png");coracao46.setDx(coracao46.getDx() + comecarAnimacaoCoracao);
									if(coracao46.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao46.getImagem() == null ? 1 : 0); coracao46.load("res\\batalha\\apelo.png");coracao47.setDx(coracao47.getDx() + comecarAnimacaoCoracao);
										if(coracao47.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao47.getImagem() == null ? 1 : 0); coracao47.load("res\\batalha\\apelo.png");coracao48.setDx(coracao48.getDx() + comecarAnimacaoCoracao);
											if(coracao48.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao48.getImagem() == null ? 1 : 0); coracao48.load("res\\batalha\\apelo.png");coracao49.setDx(coracao49.getDx() + comecarAnimacaoCoracao);
												if(coracao49.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao49.getImagem() == null ? 1 : 0); coracao49.load("res\\batalha\\apelo.png");coracao410.setDx(coracao410.getDx() + comecarAnimacaoCoracao);
													if(coracao410.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao410.getImagem() == null ? 1 : 0); coracao410.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
			}
				
		}else if(animacaoFileira == 8 || animacaoFileira == 9 ) {
			if(animacaoFileira == 8) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao510.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoopEfeitoInterf =0;
					
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][4], 4 != posicaoAventureiro ? arrayAleatorioHabAdver[4] : selecaoNomeHab);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao510.setDx(coracao510.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
					
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
					
					if(coracao510.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
						matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao510.getImagem() == null ? 0 : (coracao510.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao510.getImagem() != null && coracao510.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao510.setImagem(null);} coracao59.setDx(coracao59.getDx() + comecarAnimacaoCoracao);
						if(coracao59.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
							matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao59.getImagem() == null ? 0 : (coracao59.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao59.getImagem() != null && coracao59.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao59.setImagem(null);} coracao58.setDx(coracao58.getDx() + comecarAnimacaoCoracao);
							if(coracao58.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
								matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao58.getImagem() == null ? 0 : (coracao58.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao58.getImagem() != null && coracao58.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao58.setImagem(null);} coracao57.setDx(coracao57.getDx() + comecarAnimacaoCoracao);
								if(coracao57.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
									matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao57.getImagem() == null ? 0 : (coracao57.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao57.getImagem() != null && coracao57.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao57.setImagem(null);} coracao56.setDx(coracao56.getDx() + comecarAnimacaoCoracao);
									if(coracao56.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
										matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao56.getImagem() == null ? 0 : (coracao56.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao56.getImagem() != null && coracao56.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao56.setImagem(null);} coracao55.setDx(coracao55.getDx() + comecarAnimacaoCoracao);
										if(coracao55.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
											matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao55.getImagem() == null ? 0 : (coracao55.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao55.getImagem() != null && coracao55.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao55.setImagem(null);} coracao54.setDx(coracao54.getDx() + comecarAnimacaoCoracao);
											if(coracao54.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
												matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao54.getImagem() == null ? 0 : (coracao54.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao54.getImagem() != null && coracao54.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao54.setImagem(null);} coracao53.setDx(coracao53.getDx() + comecarAnimacaoCoracao);
												if(coracao53.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
													matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao53.getImagem() == null ? 0 : (coracao53.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao53.getImagem() != null && coracao53.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao53.setImagem(null);} coracao52.setDx(coracao52.getDx() + comecarAnimacaoCoracao);
													if(coracao52.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
														matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao52.getImagem() == null ? 0 : (coracao52.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao52.getImagem() != null && coracao52.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao52.setImagem(null);} coracao51.setDx(coracao51.getDx() + comecarAnimacaoCoracao);
														if(coracao51.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {
															matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao51.getImagem() == null ? 0 : (coracao51.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0)); if(coracao51.getImagem() != null && coracao51.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao51.setImagem(null);} zerarDx(); animacaoFileira=9;
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
														}if(matrizAventureiros[2][4] == 0 && Efeito4 != 1) {animacaoFileira = 9; zerarDx();}
													}
													
			}else if(animacaoFileira == 9 || matrizAventureiros[2][4] == 0) {
				coracao51.setDx(coracao51.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
				
				Iniciargif = false; animacao.setDx(0);
				
				if(coracao51.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao51.getImagem() == null ? 1 : 0); coracao51.load("res\\batalha\\apelo.png"); coracao52.setDx(coracao52.getDx() + comecarAnimacaoCoracao);
					if(coracao52.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao52.getImagem() == null ? 1 : 0); coracao52.load("res\\batalha\\apelo.png");coracao53.setDx(coracao53.getDx() + comecarAnimacaoCoracao);
						if(coracao53.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao53.getImagem() == null ? 1 : 0); coracao53.load("res\\batalha\\apelo.png");coracao54.setDx(coracao54.getDx() + comecarAnimacaoCoracao);
							if(coracao54.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao54.getImagem() == null ? 1 : 0); coracao54.load("res\\batalha\\apelo.png");coracao55.setDx(coracao55.getDx() + comecarAnimacaoCoracao);
								if(coracao55.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao55.getImagem() == null ? 1 : 0); coracao55.load("res\\batalha\\apelo.png");coracao56.setDx(coracao56.getDx() + comecarAnimacaoCoracao);
									if(coracao56.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao56.getImagem() == null ? 1 : 0); coracao56.load("res\\batalha\\apelo.png");coracao57.setDx(coracao57.getDx() + comecarAnimacaoCoracao);
										if(coracao57.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao57.getImagem() == null ? 1 : 0); coracao57.load("res\\batalha\\apelo.png");coracao58.setDx(coracao58.getDx() + comecarAnimacaoCoracao);
											if(coracao58.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao58.getImagem() == null ? 1 : 0); coracao58.load("res\\batalha\\apelo.png");coracao59.setDx(coracao59.getDx() + comecarAnimacaoCoracao);
												if(coracao59.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao59.getImagem() == null ? 1 : 0); coracao59.load("res\\batalha\\apelo.png");coracao510.setDx(coracao510.getDx() + comecarAnimacaoCoracao);
													if(coracao510.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao510.getImagem() == null ? 1 : 0); coracao510.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {habilidadeRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						rezeta o contador de tempo para aparecer apelos						|
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
	|  								mostra as interferencias ganhas								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void sumirCoracoes() {
		
		if((animacaoFileira == 10 || animacaoFileira == 11) && vezDoAventureiro != 5) {
			if(animacaoFileira == 10) {
				coracao110.setDx(coracao110.getDx() + comecarAnimacaoCoracao);
				
				if(coracao110.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
					interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao110.getImagem() == null ? 0 : (coracao110.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao110.getImagem() != null && coracao110.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao110.setImagem(null);} coracao19.setDx(coracao19.getDx() + comecarAnimacaoCoracao);
					if(coracao19.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
						interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao19.getImagem() == null ? 0 : (coracao19.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao19.getImagem() != null && coracao19.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao19.setImagem(null);} coracao18.setDx(coracao18.getDx() + comecarAnimacaoCoracao);
						if(coracao18.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
							interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao18.getImagem() == null ? 0 : (coracao18.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao18.getImagem() != null && coracao18.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao18.setImagem(null);} coracao17.setDx(coracao17.getDx() + comecarAnimacaoCoracao);
							if(coracao17.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
								interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao17.getImagem() == null ? 0 : (coracao17.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao17.getImagem() != null && coracao17.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao17.setImagem(null);} coracao16.setDx(coracao16.getDx() + comecarAnimacaoCoracao);
								if(coracao16.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
									interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao16.getImagem() == null ? 0 : (coracao16.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao16.getImagem() != null && coracao16.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao16.setImagem(null);} coracao15.setDx(coracao15.getDx() + comecarAnimacaoCoracao);
									if(coracao15.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
										interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao15.getImagem() == null ? 0 : (coracao15.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao15.getImagem() != null && coracao15.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao15.setImagem(null);}  coracao14.setDx(coracao14.getDx() + comecarAnimacaoCoracao);
										if(coracao14.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
											interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao14.getImagem() == null ? 0 : (coracao14.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao14.getImagem() != null && coracao14.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao14.setImagem(null);} coracao13.setDx(coracao13.getDx() + comecarAnimacaoCoracao);
											if(coracao13.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
												interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao13.getImagem() == null ? 0 : (coracao13.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao13.getImagem() != null && coracao13.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao13.setImagem(null);} coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
												if(coracao12.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
													interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao12.getImagem() == null ? 0 : (coracao12.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao12.getImagem() != null && coracao12.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao12.setImagem(null);} coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
													if(coracao11.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
														interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao11.getImagem() == null ? 0 : (coracao11.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao11.getImagem() != null && coracao11.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao11.setImagem(null);} zerarDx(); animacaoFileira=11;
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													}if(interferenciasRecebidas[0][vezDoAventureiro] == 0) {animacaoFileira = 11; zerarDx();}
													
			}else if(animacaoFileira == 11) {
				coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
									
				if(coracao11.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao11.getImagem() == null ? 1 : (coracao11.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao11.load("res\\batalha\\interferencia.png"); coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
					if(coracao12.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao12.getImagem() == null ? 1 : (coracao12.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao12.load("res\\batalha\\interferencia.png");coracao13.setDx(coracao13.getDx() + comecarAnimacaoCoracao);
						if(coracao13.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao13.getImagem() == null ? 1 : (coracao13.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao13.load("res\\batalha\\interferencia.png");coracao14.setDx(coracao14.getDx() + comecarAnimacaoCoracao);
							if(coracao14.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao14.getImagem() == null ? 1 : (coracao14.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao14.load("res\\batalha\\interferencia.png");coracao15.setDx(coracao15.getDx() + comecarAnimacaoCoracao);
								if(coracao15.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao15.getImagem() == null ? 1 : (coracao15.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao15.load("res\\batalha\\interferencia.png");coracao16.setDx(coracao16.getDx() + comecarAnimacaoCoracao);
									if(coracao16.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao16.getImagem() == null ? 1 : (coracao16.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao16.load("res\\batalha\\interferencia.png");coracao17.setDx(coracao17.getDx() + comecarAnimacaoCoracao);
										if(coracao17.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao17.getImagem() == null ? 1 : (coracao17.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao17.load("res\\batalha\\interferencia.png");coracao18.setDx(coracao18.getDx() + comecarAnimacaoCoracao);
											if(coracao18.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao18.getImagem() == null ? 1 : (coracao18.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao18.load("res\\batalha\\interferencia.png");coracao19.setDx(coracao19.getDx() + comecarAnimacaoCoracao);
												if(coracao19.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao19.getImagem() == null ? 1 : (coracao19.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao19.load("res\\batalha\\interferencia.png");coracao110.setDx(coracao110.getDx() + comecarAnimacaoCoracao);
													if(coracao110.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0 && TerminouLoopEfeitoInterf != 1) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao110.getImagem() == null ? 1 : (coracao110.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao110.load("res\\batalha\\interferencia.png");
														animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
				
		}else if(animacaoFileira == 12 || animacaoFileira == 13 ) {
			if(animacaoFileira == 12) {
				coracao210.setDx(coracao210.getDx() + comecarAnimacaoCoracao);
				
				if(coracao210.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
					interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao210.getImagem() == null ? 0 : (coracao210.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao210.getImagem() != null && coracao210.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao210.setImagem(null);} coracao29.setDx(coracao29.getDx() + comecarAnimacaoCoracao);
					if(coracao29.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
						interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao29.getImagem() == null ? 0 : (coracao29.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao29.getImagem() != null && coracao29.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao29.setImagem(null);} coracao28.setDx(coracao28.getDx() + comecarAnimacaoCoracao);
						if(coracao28.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
							interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao28.getImagem() == null ? 0 : (coracao28.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao28.getImagem() != null && coracao28.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao28.setImagem(null);}  coracao27.setDx(coracao27.getDx() + comecarAnimacaoCoracao);
							if(coracao27.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
								interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao27.getImagem() == null ? 0 : (coracao27.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao27.getImagem() != null && coracao27.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao27.setImagem(null);} coracao26.setDx(coracao26.getDx() + comecarAnimacaoCoracao);
								if(coracao26.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
									interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao26.getImagem() == null ? 0 : (coracao26.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao26.getImagem() != null && coracao26.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao26.setImagem(null);} coracao25.setDx(coracao25.getDx() + comecarAnimacaoCoracao);
									if(coracao25.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
										interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao25.getImagem() == null ? 0 : (coracao25.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao25.getImagem() != null && coracao25.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao25.setImagem(null);}  coracao24.setDx(coracao24.getDx() + comecarAnimacaoCoracao);
										if(coracao24.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
											interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao24.getImagem() == null ? 0 : (coracao24.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao24.getImagem() != null && coracao24.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao24.setImagem(null);}  coracao23.setDx(coracao23.getDx() + comecarAnimacaoCoracao);
											if(coracao23.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
												interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao23.getImagem() == null ? 0 : (coracao23.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao23.getImagem() != null && coracao23.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao23.setImagem(null);} coracao22.setDx(coracao22.getDx() + comecarAnimacaoCoracao);
												if(coracao22.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
													interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao22.getImagem() == null ? 0 : (coracao22.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao22.getImagem() != null && coracao22.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao22.setImagem(null);} coracao21.setDx(coracao21.getDx() + comecarAnimacaoCoracao);
													if(coracao21.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
														interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao21.getImagem() == null ? 0 : (coracao21.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao21.getImagem() != null && coracao21.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao21.setImagem(null);} zerarDx(); animacaoFileira=13;
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													}if(interferenciasRecebidas[1][vezDoAventureiro] == 0) {animacaoFileira = 13; zerarDx();}
													
			}else if(animacaoFileira == 13) {
				coracao21.setDx(coracao21.getDx() + comecarAnimacaoCoracao);
				
				if(coracao21.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao21.getImagem() == null ? 1 : (coracao21.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao21.load("res\\batalha\\interferencia.png"); coracao22.setDx(coracao22.getDx() + comecarAnimacaoCoracao);
					if(coracao22.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao22.getImagem() == null ? 1 : (coracao22.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao22.load("res\\batalha\\interferencia.png");coracao23.setDx(coracao23.getDx() + comecarAnimacaoCoracao);
						if(coracao23.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao23.getImagem() == null ? 1 : (coracao23.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao23.load("res\\batalha\\interferencia.png");coracao24.setDx(coracao24.getDx() + comecarAnimacaoCoracao);
							if(coracao24.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao24.getImagem() == null ? 1 : (coracao24.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao24.load("res\\batalha\\interferencia.png");coracao25.setDx(coracao25.getDx() + comecarAnimacaoCoracao);
								if(coracao25.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao25.getImagem() == null ? 1 : (coracao25.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao25.load("res\\batalha\\interferencia.png");coracao26.setDx(coracao26.getDx() + comecarAnimacaoCoracao);
									if(coracao26.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao26.getImagem() == null ? 1 : (coracao26.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao26.load("res\\batalha\\interferencia.png");coracao27.setDx(coracao27.getDx() + comecarAnimacaoCoracao);
										if(coracao27.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao27.getImagem() == null ? 1 : (coracao27.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao27.load("res\\batalha\\interferencia.png");coracao28.setDx(coracao28.getDx() + comecarAnimacaoCoracao);
											if(coracao28.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao28.getImagem() == null ? 1 : (coracao28.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao28.load("res\\batalha\\interferencia.png");coracao29.setDx(coracao29.getDx() + comecarAnimacaoCoracao);
												if(coracao29.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao29.getImagem() == null ? 1 : (coracao29.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao29.load("res\\batalha\\interferencia.png");coracao210.setDx(coracao210.getDx() + comecarAnimacaoCoracao);
													if(coracao210.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0 && TerminouLoopEfeitoInterf != 1) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao210.getImagem() == null ? 1 : (coracao210.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao210.load("res\\batalha\\interferencia.png");animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
				
		}else if(animacaoFileira == 14 || animacaoFileira == 15) {
			if(animacaoFileira == 14) {
				coracao310.setDx(coracao310.getDx() + comecarAnimacaoCoracao);
				
				if(coracao310.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
					interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao310.getImagem() == null ? 0 : (coracao310.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao310.getImagem() != null && coracao310.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao310.setImagem(null);} coracao39.setDx(coracao39.getDx() + comecarAnimacaoCoracao);
					if(coracao39.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
						interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao39.getImagem() == null ? 0 : (coracao39.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao39.getImagem() != null && coracao39.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao39.setImagem(null);} coracao38.setDx(coracao38.getDx() + comecarAnimacaoCoracao);
						if(coracao38.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
							interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao38.getImagem() == null ? 0 : (coracao38.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao38.getImagem() != null && coracao38.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao38.setImagem(null);} coracao37.setDx(coracao37.getDx() + comecarAnimacaoCoracao);
							if(coracao37.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
								interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao37.getImagem() == null ? 0 : (coracao37.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao37.getImagem() != null && coracao37.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao37.setImagem(null);} coracao36.setDx(coracao36.getDx() + comecarAnimacaoCoracao);
								if(coracao36.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
									interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao36.getImagem() == null ? 0 : (coracao36.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao36.getImagem() != null && coracao36.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao36.setImagem(null);} coracao35.setDx(coracao35.getDx() + comecarAnimacaoCoracao);
									if(coracao35.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
										interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao35.getImagem() == null ? 0 : (coracao35.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao35.getImagem() != null && coracao35.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao35.setImagem(null);} coracao34.setDx(coracao34.getDx() + comecarAnimacaoCoracao);
										if(coracao34.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
											interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao34.getImagem() == null ? 0 : (coracao34.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao34.getImagem() != null && coracao34.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao34.setImagem(null);}  coracao33.setDx(coracao33.getDx() + comecarAnimacaoCoracao);
											if(coracao33.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
												interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao33.getImagem() == null ? 0 : (coracao33.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao33.getImagem() != null && coracao33.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao33.setImagem(null);}  coracao32.setDx(coracao32.getDx() + comecarAnimacaoCoracao);
												if(coracao32.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
													interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao32.getImagem() == null ? 0 : (coracao32.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao32.getImagem() != null && coracao32.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao32.setImagem(null);} coracao31.setDx(coracao31.getDx() + comecarAnimacaoCoracao);
													if(coracao31.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
														interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao31.getImagem() == null ? 0 : (coracao31.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao31.getImagem() != null && coracao31.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao31.setImagem(null);} zerarDx(); animacaoFileira=15;
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													}if(interferenciasRecebidas[2][vezDoAventureiro] == 0) {animacaoFileira = 15; zerarDx();}
													
			}else if(animacaoFileira == 15) {
				coracao31.setDx(coracao31.getDx() + comecarAnimacaoCoracao);
				
				if(coracao31.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao31.getImagem() == null ? 1 : (coracao31.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao31.load("res\\batalha\\interferencia.png"); coracao32.setDx(coracao32.getDx() + comecarAnimacaoCoracao);
					if(coracao32.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao32.getImagem() == null ? 1 : (coracao32.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao32.load("res\\batalha\\interferencia.png");coracao33.setDx(coracao33.getDx() + comecarAnimacaoCoracao);
						if(coracao33.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao33.getImagem() == null ? 1 : (coracao33.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao33.load("res\\batalha\\interferencia.png");coracao34.setDx(coracao34.getDx() + comecarAnimacaoCoracao);
							if(coracao34.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao34.getImagem() == null ? 1 : (coracao34.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao34.load("res\\batalha\\interferencia.png");coracao35.setDx(coracao35.getDx() + comecarAnimacaoCoracao);
								if(coracao35.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao35.getImagem() == null ? 1 : (coracao35.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao35.load("res\\batalha\\interferencia.png");coracao36.setDx(coracao36.getDx() + comecarAnimacaoCoracao);
									if(coracao36.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao36.getImagem() == null ? 1 : (coracao36.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao36.load("res\\batalha\\interferencia.png");coracao37.setDx(coracao37.getDx() + comecarAnimacaoCoracao);
										if(coracao37.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao37.getImagem() == null ? 1 : (coracao37.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao37.load("res\\batalha\\interferencia.png");coracao38.setDx(coracao38.getDx() + comecarAnimacaoCoracao);
											if(coracao38.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao38.getImagem() == null ? 1 : (coracao38.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao38.load("res\\batalha\\interferencia.png");coracao39.setDx(coracao39.getDx() + comecarAnimacaoCoracao);
												if(coracao39.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao39.getImagem() == null ? 1 : (coracao39.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao39.load("res\\batalha\\interferencia.png");coracao310.setDx(coracao310.getDx() + comecarAnimacaoCoracao);
													if(coracao310.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0 && TerminouLoopEfeitoInterf != 1) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao310.getImagem() == null ? 1 : (coracao310.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao310.load("res\\batalha\\interferencia.png");animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
				
		}else if(animacaoFileira == 16 || animacaoFileira == 17 ) {
			if(animacaoFileira == 16) {
				coracao410.setDx(coracao410.getDx() + comecarAnimacaoCoracao);
				
				if(coracao410.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
					interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao410.getImagem() == null ? 0 : (coracao410.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao410.getImagem() != null && coracao410.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao410.setImagem(null);}  coracao49.setDx(coracao49.getDx() + comecarAnimacaoCoracao);
					if(coracao49.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
						interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao49.getImagem() == null ? 0 : (coracao49.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao49.getImagem() != null && coracao49.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao49.setImagem(null);} coracao48.setDx(coracao48.getDx() + comecarAnimacaoCoracao);
						if(coracao48.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
							interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao48.getImagem() == null ? 0 : (coracao48.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao48.getImagem() != null && coracao48.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao48.setImagem(null);} coracao47.setDx(coracao47.getDx() + comecarAnimacaoCoracao);
							if(coracao47.getDx() >= intervaloAnimacao  && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
								interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao47.getImagem() == null ? 0 : (coracao47.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao47.getImagem() != null && coracao47.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao47.setImagem(null);}  coracao46.setDx(coracao46.getDx() + comecarAnimacaoCoracao);
								if(coracao46.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
									interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao46.getImagem() == null ? 0 : (coracao46.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao46.getImagem() != null && coracao46.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao46.setImagem(null);} coracao45.setDx(coracao45.getDx() + comecarAnimacaoCoracao);
									if(coracao45.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
										interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao45.getImagem() == null ? 0 : (coracao45.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao45.getImagem() != null && coracao45.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao45.setImagem(null);}  coracao44.setDx(coracao44.getDx() + comecarAnimacaoCoracao);
										if(coracao44.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
											interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao44.getImagem() == null ? 0 : (coracao44.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao44.getImagem() != null && coracao44.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao44.setImagem(null);}  coracao43.setDx(coracao43.getDx() + comecarAnimacaoCoracao);
											if(coracao43.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
												interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao43.getImagem() == null ? 0 : (coracao43.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao43.getImagem() != null && coracao43.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao43.setImagem(null);} coracao42.setDx(coracao42.getDx() + comecarAnimacaoCoracao);
												if(coracao42.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
													interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao42.getImagem() == null ? 0 : (coracao42.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao42.getImagem() != null && coracao42.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao42.setImagem(null);} coracao41.setDx(coracao41.getDx() + comecarAnimacaoCoracao);
													if(coracao41.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
														interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao41.getImagem() == null ? 0 : (coracao41.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao41.getImagem() != null && coracao41.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao41.setImagem(null);} zerarDx(); animacaoFileira=17;
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													}if(interferenciasRecebidas[3][vezDoAventureiro] == 0) {animacaoFileira = 17; zerarDx();}
													
			}else if(animacaoFileira == 17) {
				coracao41.setDx(coracao41.getDx() + comecarAnimacaoCoracao);
				
				if(coracao41.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao41.getImagem() == null ? 1 : (coracao41.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao41.load("res\\batalha\\interferencia.png"); coracao42.setDx(coracao42.getDx() + comecarAnimacaoCoracao);
					if(coracao42.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao42.getImagem() == null ? 1 : (coracao42.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao42.load("res\\batalha\\interferencia.png");coracao43.setDx(coracao43.getDx() + comecarAnimacaoCoracao);
						if(coracao43.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao43.getImagem() == null ? 1 : (coracao43.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao43.load("res\\batalha\\interferencia.png");coracao44.setDx(coracao44.getDx() + comecarAnimacaoCoracao);
							if(coracao44.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao44.getImagem() == null ? 1 : (coracao44.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao44.load("res\\batalha\\interferencia.png");coracao45.setDx(coracao45.getDx() + comecarAnimacaoCoracao);
								if(coracao45.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao45.getImagem() == null ? 1 : (coracao45.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao45.load("res\\batalha\\interferencia.png");coracao46.setDx(coracao46.getDx() + comecarAnimacaoCoracao);
									if(coracao46.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao46.getImagem() == null ? 1 : (coracao46.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao46.load("res\\batalha\\interferencia.png");coracao47.setDx(coracao47.getDx() + comecarAnimacaoCoracao);
										if(coracao47.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao47.getImagem() == null ? 1 : (coracao47.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao47.load("res\\batalha\\interferencia.png");coracao48.setDx(coracao48.getDx() + comecarAnimacaoCoracao);
											if(coracao48.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao48.getImagem() == null ? 1 : (coracao48.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao48.load("res\\batalha\\interferencia.png");coracao49.setDx(coracao49.getDx() + comecarAnimacaoCoracao);
												if(coracao49.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao49.getImagem() == null ? 1 : (coracao49.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao49.load("res\\batalha\\interferencia.png");coracao410.setDx(coracao410.getDx() + comecarAnimacaoCoracao);
													if(coracao410.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0 && TerminouLoopEfeitoInterf != 1) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao410.getImagem() == null ? 1 : (coracao410.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao410.load("res\\batalha\\interferencia.png");animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
				
		}else if(animacaoFileira == 18 || animacaoFileira == 19 ) {
			if(animacaoFileira == 18) {
				coracao510.setDx(coracao510.getDx() + comecarAnimacaoCoracao);
				
				if(coracao510.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
					interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao510.getImagem() == null ? 0 : (coracao510.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao510.getImagem() != null && coracao510.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao510.setImagem(null);} coracao59.setDx(coracao59.getDx() + comecarAnimacaoCoracao);
					if(coracao59.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
						interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao59.getImagem() == null ? 0 : (coracao59.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao59.getImagem() != null && coracao59.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao59.setImagem(null);} coracao58.setDx(coracao58.getDx() + comecarAnimacaoCoracao);
						if(coracao58.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
							interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao58.getImagem() == null ? 0 : (coracao58.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao58.getImagem() != null && coracao58.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao58.setImagem(null);} coracao57.setDx(coracao57.getDx() + comecarAnimacaoCoracao);
							if(coracao57.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
								interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao57.getImagem() == null ? 0 : (coracao57.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao57.getImagem() != null && coracao57.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao57.setImagem(null);} coracao56.setDx(coracao56.getDx() + comecarAnimacaoCoracao);
								if(coracao56.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
									interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao56.getImagem() == null ? 0 : (coracao56.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao56.getImagem() != null && coracao56.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao56.setImagem(null);} coracao55.setDx(coracao55.getDx() + comecarAnimacaoCoracao);
									if(coracao55.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
										interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao55.getImagem() == null ? 0 : (coracao55.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao55.getImagem() != null && coracao55.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao55.setImagem(null);} coracao54.setDx(coracao54.getDx() + comecarAnimacaoCoracao);
										if(coracao54.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
											interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao54.getImagem() == null ? 0 : (coracao54.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao54.getImagem() != null && coracao54.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao54.setImagem(null);} coracao53.setDx(coracao53.getDx() + comecarAnimacaoCoracao);
											if(coracao53.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
												interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao53.getImagem() == null ? 0 : (coracao53.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao53.getImagem() != null && coracao53.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao53.setImagem(null);} coracao52.setDx(coracao52.getDx() + comecarAnimacaoCoracao);
												if(coracao52.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
													interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao52.getImagem() == null ? 0 : (coracao52.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0)); if(coracao52.getImagem() != null && coracao52.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao52.setImagem(null);} coracao51.setDx(coracao51.getDx() + comecarAnimacaoCoracao);
													if(coracao51.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
														interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao51.getImagem() == null ? 0 : (coracao51.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0));if(coracao51.getImagem() != null && coracao51.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao51.setImagem(null);} zerarDx(); animacaoFileira=19;
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													}if(interferenciasRecebidas[4][vezDoAventureiro] == 0) {animacaoFileira = 19; zerarDx();}
													
			}else if(animacaoFileira == 19) {
				coracao51.setDx(coracao51.getDx() + comecarAnimacaoCoracao);
				
				if(coracao51.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao51.getImagem() == null ? 1 : (coracao51.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao51.load("res\\batalha\\interferencia.png");coracao52.setDx(coracao52.getDx() + comecarAnimacaoCoracao);
					if(coracao52.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao52.getImagem() == null ? 1 : (coracao52.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao52.load("res\\batalha\\interferencia.png");coracao53.setDx(coracao53.getDx() + comecarAnimacaoCoracao);
						if(coracao53.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao53.getImagem() == null ? 1 : (coracao53.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao53.load("res\\batalha\\interferencia.png");coracao54.setDx(coracao54.getDx() + comecarAnimacaoCoracao);
							if(coracao54.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao54.getImagem() == null ? 1 : (coracao54.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao54.load("res\\batalha\\interferencia.png");coracao55.setDx(coracao55.getDx() + comecarAnimacaoCoracao);
								if(coracao55.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao55.getImagem() == null ? 1 : (coracao55.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao55.load("res\\batalha\\interferencia.png");coracao56.setDx(coracao56.getDx() + comecarAnimacaoCoracao);
									if(coracao56.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao56.getImagem() == null ? 1 : (coracao56.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao56.load("res\\batalha\\interferencia.png");coracao57.setDx(coracao57.getDx() + comecarAnimacaoCoracao);
										if(coracao57.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao57.getImagem() == null ? 1 : (coracao57.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao57.load("res\\batalha\\interferencia.png");coracao58.setDx(coracao58.getDx() + comecarAnimacaoCoracao);
											if(coracao58.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao58.getImagem() == null ? 1 : (coracao58.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao58.load("res\\batalha\\interferencia.png");coracao59.setDx(coracao59.getDx() + comecarAnimacaoCoracao);
												if(coracao59.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao59.getImagem() == null ? 1 : (coracao59.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao59.load("res\\batalha\\interferencia.png");coracao510.setDx(coracao510.getDx() + comecarAnimacaoCoracao);
													if(coracao510.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0 && TerminouLoopEfeitoInterf != 1) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao510.getImagem() == null ? 1 : (coracao510.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1)); coracao510.load("res\\batalha\\interferencia.png");animacaoEfeitoConcluido[4] = true;  zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoopEfeitoInterf != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							aciona o efeito de habilidade repetida							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void habilidadeRepetido(int posicaoAventu) {

		efeitoFase.setDx(efeitoFase.getDx() + comecarAnimacaoCoracao);
		
		if(efeitoFase.getDx() >= 20 && efeitoFase.getDx() < 150) {
			txtEfeitoFase.setTexto("- 2");
			efeitoFase.load("res\\batalha\\apelo.png");
			txtEfeitoFase.setY((posicaoAventu == 0 ? campoBatalha1.getY() : (posicaoAventu == 1 ? campoBatalha2.getY() : (posicaoAventu == 2 ? campoBatalha3.getY() : campoBatalha5.getY()))) + 70/2 + 7);
			efeitoFase.setY(txtEfeitoFase.getY() - 17);

		}
		if(efeitoFase.getDx() == 150) {
			 
			efeitoFase.setImagem(null);
			txtEfeitoFase.setTexto(" ");
			valoresInterferencia[0] = matrizAventureiros[3][posicaoAventu]; valoresInterferencia[1] = matrizAventureiros[4][posicaoAventu]; valoresInterferencia[2] = posicaoAventu;
			
			TerminouLoopEfeitoInterf =0;
			apeloRepetido[posicaoAventu] = 3;
			zerarDx();
			
			if(posicaoAventu == 0) {
				interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] + 2;
				animacaoFileira = 10;
			} else if(posicaoAventu == 1) {
				interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] + 2;
				animacaoFileira = 12;
			} else if(posicaoAventu == 2) {
				interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] + 2;
				animacaoFileira = 14;
			} else if(posicaoAventu == 3) {
				interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] + 2;
				animacaoFileira = 16;
			} else if(posicaoAventu == 4) {
				interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] + 2;
				animacaoFileira = 18;
			}
			
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							aciona o efeito de do chefe da fase								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void efeitoChefeDeFase (int posicaoAventu) {

		if((adversario == 0 && matrizAventureiros[4][posicaoAventu] != -1) || (adversario == 1 && matrizAventureiros[4][posicaoAventu] == -1) || (adversario == 2 && matrizAventureiros[7][posicaoAventu] == 1) || (adversario == 3 && matrizAventureiros[4][posicaoAventu] != -1) || (adversario == 4 && matrizAventureiros[4][posicaoAventu] == -1)) {

			int efeitoBoss = (adversario == 0 || adversario == 1? -1 : 1);
			
			efeitoFase.setDx(efeitoFase.getDx() + comecarAnimacaoCoracao);
			
			if(efeitoFase.getDx() >= 20 && efeitoFase.getDx() < 150) {
				
				txtEfeitoFase.setTexto((efeitoBoss == -1 ? "- 1" : "+ 1"));
				efeitoFase.load("res\\batalha\\apelo.png");
				txtEfeitoFase.setY((posicaoAventu == 0 ? campoBatalha1.getY() : (posicaoAventu == 1 ? campoBatalha2.getY() : (posicaoAventu == 2 ? campoBatalha3.getY() : campoBatalha5.getY()))) + 70/2 + 7);
				efeitoFase.setY(txtEfeitoFase.getY() - 17);
			}
			if(efeitoFase.getDx() == 150) {

				efeitoFase.setImagem(null);
				txtEfeitoFase.setTexto(" ");
				valoresInterferencia[0] = matrizAventureiros[3][posicaoAventu]; valoresInterferencia[1] = matrizAventureiros[4][posicaoAventu]; valoresInterferencia[2] = posicaoAventu;
				
					
				efeitoChefeDeFase[posicaoAventu] = 3;
				zerarDx();
				TerminouLoopEfeitoInterf =0;
				
				if(posicaoAventu == 0) {
					if(efeitoBoss < 0) {
						interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - efeitoBoss;
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] - efeitoBoss;
						
						animacaoFileira = 10;
					}else {
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] + efeitoBoss;
						animacaoFileira = 0;
					}
				} else if(posicaoAventu == 1) {
					if(efeitoBoss < 0) {
						interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - efeitoBoss;
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] - efeitoBoss;

						animacaoFileira = 12;
					}else {
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] + efeitoBoss;
						animacaoFileira = 2;
					}
				} else if(posicaoAventu == 2) {
					if(efeitoBoss < 0) {
						interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - efeitoBoss;
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] - efeitoBoss;

						animacaoFileira = 14;
					}else {
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] + efeitoBoss;
						animacaoFileira = 4;
					}
				} else if(posicaoAventu == 3) {
					if(efeitoBoss < 0) {
						interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - efeitoBoss;
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] - efeitoBoss;

						animacaoFileira = 16;
					}else {
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] + efeitoBoss;
						animacaoFileira = 6;
					}
				} else if(posicaoAventu == 4) {
					if(efeitoBoss < 0) {
						interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - efeitoBoss;
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] - efeitoBoss;

						animacaoFileira = 18;
					}else {
						matrizAventureiros[2][vezDoAventureiro] = matrizAventureiros[2][vezDoAventureiro] + efeitoBoss;
						animacaoFileira = 8;
					}
				}
				
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							mexe o medidor de apelos ganhos a batalha						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void mexerMedidorApelos() {
		
		if(animacaoFileira == 20) {
			
			coracao01.setDx(coracao01.getDx() + comecarAnimacaoCoracao);
			
			if(coracao01.getDx() >= intervaloAnimacao) {
				coracao01.setX(campoBatalha1.getX() + 100 + (4 * matrizAventureiros[1][0] < 0? 0 : 4 * matrizAventureiros[1][0]));
				coracao02.setDx(coracao02.getDx() + comecarAnimacaoCoracao);
				if(coracao02.getDx() >= intervaloAnimacao) {
					coracao02.setX(campoBatalha2.getX() + 100 + (4 * matrizAventureiros[1][1] < 0? 0 : 4 * matrizAventureiros[1][1]));
					coracao03.setDx(coracao03.getDx() + comecarAnimacaoCoracao);
					if(coracao03.getDx() >= intervaloAnimacao) {
						coracao03.setX(campoBatalha3.getX() + 100 + (4 * matrizAventureiros[1][2] < 0? 0 : 4 * matrizAventureiros[1][2]));
						coracao04.setDx(coracao04.getDx() + comecarAnimacaoCoracao);
						if(coracao04.getDx() >= intervaloAnimacao) {
							coracao04.setX(campoBatalha4.getX() + 100 + (4 * matrizAventureiros[1][3] < 0? 0 : 4 * matrizAventureiros[1][3]));
							coracao05.setDx(coracao05.getDx() + comecarAnimacaoCoracao);
							if(coracao05.getDx() >= intervaloAnimacao) {
								coracao05.setX(campoBatalha5.getX() + 100 + (4 * matrizAventureiros[1][4] < 0? 0 : 4 * matrizAventureiros[1][4]));
								animacaoFileira = 21; zerarDx();
								
							}}}}}
		}
		System.out.println("total real: " + matrizAventureiros[1][0] + ", " + matrizAventureiros[1][1] + ", " + matrizAventureiros[1][2] + ", " + matrizAventureiros[1][3] + ", " + matrizAventureiros[1][4] + "\n");
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  			organiza os Cães  de acordo com sua performace na rodada da batalha				|
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
