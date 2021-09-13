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
	private Escolha_de_adversario paginaAnterior;
	
	private Icones_interativos contorno;
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(41,66);
	private Icones_interativos bntRegras = new Icones_interativos(41,202);
	private Icones_interativos bntVoltar = new Icones_interativos(41,338);
	
	private Texto txtMenu = new Texto(41 + 46, 66 + 36, " ");
	private Texto txtRegras = new Texto(41 + 34, 202 + 36, " ");
	private Texto txtVoltar = new Texto(41 + 34, 338 + 36, " ");
	
	private Icones_interativos dialogo = new Icones_interativos(1080/2 - 380/2, 540/2 - 200/2);
	private Texto txtMenVoltSim = new Texto(1080/2 - 380/2 + 82, 540/2 + 58, " ");
	private Texto txtMenVoltNao = new Texto(1080/2 - 380/2 + 256, 540/2 + 58, " ");
	private Texto txtMenVoltar = new Texto(1080/2 - 380/2 + 60, 540/2 - 40, " ");
	
	private Boolean botaoSimNao = true;
	boolean mostrarMenu = false;
	int contMenu = 0;
	
	private Ignis ignis = new Ignis();
	private Ayla ayla = new Ayla();
	private Rexthor rexthor = new Rexthor();
	private Kiki kiki = new Kiki();
	private Arius arius = new Arius();

	private Image fundo;
	
	int intervaloAnimacao = 15;
	int intervaloAnimacaoGif = 200;
	int [] dados = {0, 0, 0, 0, 0};
	boolean vezDados = false;
	
	private int comecarAnimacaoCoracao = 0;
	private int animacaoFileira = -1;
	
	boolean gif = false;
	boolean mudarEscolha = false;

	private Random aleatorio = new Random();
	int [] arrayAleatorio = {aleatorio.nextInt(4), aleatorio.nextInt(4), aleatorio.nextInt(4), aleatorio.nextInt(4), aleatorio.nextInt(4)};
	
	private Icones_interativos animacao;
	private Icones_interativos menos2Coracoes;
	private Icones_interativos apelo;
	private Icones_interativos campoBatalha1, campoBatalha2, campoBatalha3, campoBatalha4, campoBatalha5;
	private Icones_interativos descricao;
	private Icones_interativos descricaoAnimacao;
	private Icones_interativos nomeHabilidade1, nomeHabilidade2, nomeHabilidade3, nomeHabilidade4;
	private Icones_interativos dado1, dado2, dado3, dado4, dado5;
	
	private Icones_interativos coracao01, coracao02, coracao03, coracao04, coracao05;
	private Icones_interativos coracao11, coracao21, coracao31, coracao41, coracao51, coracao61, coracao71, coracao81, coracao91, coracao101;
	private Icones_interativos coracao12, coracao22, coracao32, coracao42, coracao52, coracao62, coracao72, coracao82, coracao92, coracao102;
	private Icones_interativos coracao13, coracao23, coracao33, coracao43, coracao53, coracao63, coracao73, coracao83, coracao93, coracao103;
	private Icones_interativos coracao14, coracao24, coracao34, coracao44, coracao54, coracao64, coracao74, coracao84, coracao94, coracao104;
	private Icones_interativos coracao15, coracao25, coracao35, coracao45, coracao55, coracao65, coracao75, coracao85, coracao95, coracao105;
	
	private Icones_interativos vencedor;
	
	private Texto nomeApelo1, nomeApelo2, nomeApelo3, nomeApelo4, apeloQuantidade, InterferenciaQuantidade;
	private Texto textoDescricao1, textoDescricao2, textoDescricao3, textoDescricao4;
	private Texto nomeAvent1, nomeAvent2, nomeAvent3, nomeAvent4, nomeAvent5;
	
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7, tl8, tl9, tl10, tl11, tl12, tl13, tl14, tl15, tl16, tl17, tl18, tl19, tl20, tl21, tl22, tl23, tl24;
	
	private int contTecla = 0;
	private int contZ = 0;
	
	private String [] nomeAventureiro = {"Ignis", "Ayla", "Rexthor", "Kiki", "Arius"};
	
	//vetor 0 é o apelo, vetor 1 é a interferencia, 2 é quem ele acerta (0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro, -1: sem efeito)
	private int [][] apeloIgnis = ignis.getValores();
	private int [][] apeloAyla = ayla.getValores();
	private int [][] apeloRexthor = rexthor.getValores();
	private int [][] apeloKiki = kiki.getValores();
	private int [][] apeloArius = arius.getValores();
	
	private String [][] apelosEInterferencias = {ignis.getApelosEInterferencias(), ayla.getApelosEInterferencias(), rexthor.getApelosEInterferencias(), kiki.getApelosEInterferencias(), arius.getApelosEInterferencias()};
	private String [][] gifApelos = {ignis.getGifApelos(), ayla.getGifApelos(), rexthor.getGifApelos(), kiki.getGifApelos(), arius.getGifApelos()};
	private String [][] NomeApelos = {ignis.getNomeApelos(), ayla.getNomeApelos(), rexthor.getNomeApelos(), kiki.getNomeApelos(), arius.getNomeApelos()};
	private String [][] ConteudoDescricao = {ignis.getConteudoDescricao(0), ignis.getConteudoDescricao(1), ignis.getConteudoDescricao(2), ignis.getConteudoDescricao(3), 
											 ayla.getConteudoDescricao(0), ayla.getConteudoDescricao(1), ayla.getConteudoDescricao(2), ayla.getConteudoDescricao(3), 
											 rexthor.getConteudoDescricao(0), rexthor.getConteudoDescricao(1), rexthor.getConteudoDescricao(2), rexthor.getConteudoDescricao(3),
											 kiki.getConteudoDescricao(0), kiki.getConteudoDescricao(1), kiki.getConteudoDescricao(2), kiki.getConteudoDescricao(3),
											arius.getConteudoDescricao(0), arius.getConteudoDescricao(1), arius.getConteudoDescricao(2), arius.getConteudoDescricao(3)};

	
	//                                               ignis             ayla            rexthor           kiki            arius
	private int [][] interferenciasRecebidas = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};// quantidade de interferencia q recebem de cada um

							    //posição aventu,    apelo atual      apelo gerado       dano dado     efeito do dano    pontos atuiais  poder anterior usado  tipo do ataque
	int [][] matrizAventureiros = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {-1, -1, -1, -1, -1}, {0, 0, 0, 0, 0}}; 
	
	int [] apeloRepetido = {0, 0, 0, 0, 0};
	boolean [] animacaoEfeitoConcluido = {false, false, false, false, false};
	boolean [] somaDeApelo = {false, false, false, false, false};
	
	int TerminouLoop = 0;
	 //       dano, efeito, posicaoNaLista
	int [] valoresInterferencia = {-1,-1,-1};
	
	int [] efeitoChefeDeFase = {0, 0, 0 ,0 ,0};
	private int aventureiro;
	private int adversario;
	private int posicaoAventureiro;
	int vezDoAventureiro = 0;
	int [] danoEfeito4 = {0, 0, 0, 0, 0};
	int Efeito4 = 0;
			     // rex , arius
	int [] Efeito6 = {0, 0};
	
	private Timer timer;
	
	public Batalha(int numAventureiro, int numAdversario, Escolha_de_adversario PaginaAnterior) {
		this.paginaAnterior = PaginaAnterior;
		
		aventureiro = numAventureiro;
		adversario = numAdversario;
		arrumarListaAventureiros();
		
		contorno = new Icones_interativos(0, 0);
		contorno.load("res\\contorno.png");
		
		ImageIcon referencia = new ImageIcon("res\\fundoPreto.png");
		fundo = referencia.getImage();
		
		animacao = new Icones_interativos(16,16);
		animacao.load("res\\batalha\\animacao.png");
		descricaoAnimacao = new Icones_interativos(16, 16 + 244);
		descricaoAnimacao.load("res\\batalha\\descricaoAnimacao.png");
		
		txtMenu.setFonte(new Font("Arial", Font.PLAIN, 20));
		txtRegras.setFonte(new Font("Arial", Font.PLAIN, 20));
		txtVoltar.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		txtMenVoltSim.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtMenVoltNao.setFonte(new Font("Arial", Font.PLAIN, 18));
		txtMenVoltar.setFonte(new Font("Arial", Font.PLAIN, 30));
		
		vencedor = new Icones_interativos(16, 16);
		
		campoBatalha1 = new Icones_interativos(16 + 676 + 4, 16);
		campoBatalha2 = new Icones_interativos(16 + 676 + 4, 16 + 60 + 4);
		campoBatalha3 = new Icones_interativos(16 + 676 + 4, 16 + 60 + 4 + 60 + 4);
		campoBatalha4 = new Icones_interativos(16 + 676 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4);
		campoBatalha5 = new Icones_interativos(16 + 676 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4);
		
		dado1 = new Icones_interativos(16 + 54, 16 + 68);
		dado2 = new Icones_interativos(16 + 54 + 120, 16 + 68);
		dado3 = new Icones_interativos(16 + 54 + 120 + 120, 16 + 68);
		dado4 = new Icones_interativos(16 + 54 + 120 + 120 + 120, 16 + 68);
		dado5 = new Icones_interativos(16 + 54 + 120 + 120 + 120 + 120, 16 + 68);
		
		nomeAvent1 = new Texto(16 + 676 + 4 + 11, 16 + 4 + 10, nomeAventureiro[matrizAventureiros[0][0]]);
		nomeAvent2 = new Texto(16 + 676 + 4 + 11, 16 + 4 + 60 + 4 + 10, nomeAventureiro[matrizAventureiros[0][1]]);
		nomeAvent3 = new Texto(16 + 676 + 4 + 11, 16 + 4 + 60 + 4 + 60 + 4 + 10, nomeAventureiro[matrizAventureiros[0][2]]);
		nomeAvent4 = new Texto(16 + 676 + 4 + 11, 16 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 10, nomeAventureiro[matrizAventureiros[0][3]]);
		nomeAvent5 = new Texto(16 + 676 + 4 + 11, 16 + 4 + 60 + 4 + 60 + 4 + 60 + 4  + 60 + 4 + 10, nomeAventureiro[matrizAventureiros[0][4]]);
		nomeAvent1.setFonte(new Font("Arial", Font.BOLD, 15));
		nomeAvent2.setFonte(new Font("Arial", Font.BOLD, 15));
		nomeAvent3.setFonte(new Font("Arial", Font.BOLD, 15));
		nomeAvent4.setFonte(new Font("Arial", Font.BOLD, 15));
		nomeAvent5.setFonte(new Font("Arial", Font.BOLD, 15));
		
		repintarCampoBatalha();
		
		coracao11 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 4); coracao21 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4, 16 + 4); coracao31 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4, 16 + 4); coracao41 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 4); coracao51 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 4); coracao61 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 4);
		coracao71 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 4); coracao81 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 4); coracao91 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 4); coracao101 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 4);
		coracao12 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 60 + 4 + 4); coracao22 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4, 16 + 60 + 4 + 4); coracao32 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 4); coracao42 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 4); coracao52 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 4); coracao62 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 4);
		coracao72 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 4); coracao82 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 4); coracao92 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 4); coracao102 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 4);
		coracao13 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 60 + 4 + 60 + 4 + 4); coracao23 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4, 16 + 60 + 4+ 60 + 4 + 4); coracao33 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 4); coracao43 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 4); coracao53 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 4); coracao63 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 4);
		coracao73 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 4); coracao83 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 4); coracao93 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 4); coracao103 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 4);
		coracao14 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao24 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4, 16 + 60 + 4+ 60 + 4 + 60 + 4 + 4); coracao34 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao44 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao54 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao64 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4);
		coracao74 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao84 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao94 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao104 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 4);
		coracao15 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao25 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4, 16 + 60 + 4+ 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao35 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao45 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao55 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao65 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4);
		coracao75 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao85 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao95 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4); coracao105 = new Icones_interativos(16 + 676 + 4 + 85 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4 + 21 + 4, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 4);
		
		coracao11.load("res\\batalha\\coracaoVazio.png");coracao21.load("res\\batalha\\coracaoVazio.png");
		coracao31.load("res\\batalha\\coracaoVazio.png");coracao41.load("res\\batalha\\coracaoVazio.png");
		coracao51.load("res\\batalha\\coracaoVazio.png");coracao61.load("res\\batalha\\coracaoVazio.png");
		coracao71.load("res\\batalha\\coracaoVazio.png");coracao81.load("res\\batalha\\coracaoVazio.png");
		coracao91.load("res\\batalha\\coracaoVazio.png");coracao101.load("res\\batalha\\coracaoVazio.png");
		coracao12.load("res\\batalha\\coracaoVazio.png");coracao22.load("res\\batalha\\coracaoVazio.png");
		coracao32.load("res\\batalha\\coracaoVazio.png");coracao42.load("res\\batalha\\coracaoVazio.png");
		coracao52.load("res\\batalha\\coracaoVazio.png");coracao62.load("res\\batalha\\coracaoVazio.png");
		coracao72.load("res\\batalha\\coracaoVazio.png");coracao82.load("res\\batalha\\coracaoVazio.png");
		coracao92.load("res\\batalha\\coracaoVazio.png");coracao102.load("res\\batalha\\coracaoVazio.png");
		coracao13.load("res\\batalha\\coracaoVazio.png");coracao23.load("res\\batalha\\coracaoVazio.png");
		coracao33.load("res\\batalha\\coracaoVazio.png");coracao43.load("res\\batalha\\coracaoVazio.png");
		coracao53.load("res\\batalha\\coracaoVazio.png");coracao63.load("res\\batalha\\coracaoVazio.png");
		coracao73.load("res\\batalha\\coracaoVazio.png");coracao83.load("res\\batalha\\coracaoVazio.png");
		coracao93.load("res\\batalha\\coracaoVazio.png");coracao103.load("res\\batalha\\coracaoVazio.png");
		coracao14.load("res\\batalha\\coracaoVazio.png");coracao24.load("res\\batalha\\coracaoVazio.png");
		coracao34.load("res\\batalha\\coracaoVazio.png");coracao44.load("res\\batalha\\coracaoVazio.png");
		coracao54.load("res\\batalha\\coracaoVazio.png");coracao64.load("res\\batalha\\coracaoVazio.png");
		coracao74.load("res\\batalha\\coracaoVazio.png");coracao84.load("res\\batalha\\coracaoVazio.png");
		coracao94.load("res\\batalha\\coracaoVazio.png");coracao104.load("res\\batalha\\coracaoVazio.png");
		coracao15.load("res\\batalha\\coracaoVazio.png");coracao25.load("res\\batalha\\coracaoVazio.png");
		coracao35.load("res\\batalha\\coracaoVazio.png");coracao45.load("res\\batalha\\coracaoVazio.png");
		coracao55.load("res\\batalha\\coracaoVazio.png");coracao65.load("res\\batalha\\coracaoVazio.png");
		coracao75.load("res\\batalha\\coracaoVazio.png");coracao85.load("res\\batalha\\coracaoVazio.png");
		coracao95.load("res\\batalha\\coracaoVazio.png");coracao105.load("res\\batalha\\coracaoVazio.png");
		
		coracao01 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 38);
		coracao01.load("res\\batalha\\apelo.png");
		coracao02 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 60 + 4 + 38);
		coracao02.load("res\\batalha\\apelo.png");
		coracao03 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 60 + 4 + 60 + 4 + 38);
		coracao03.load("res\\batalha\\apelo.png");
		coracao04 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 38);
		coracao04.load("res\\batalha\\apelo.png");
		coracao05 = new Icones_interativos(16 + 676 + 4 + 85, 16 + 60 + 4 + 60 + 4 + 60 + 4 + 60 + 4 + 38);
		coracao05.load("res\\batalha\\apelo.png");
		
		nomeHabilidade1 = new Icones_interativos(16, 16 + 244 + 72 + 4);
		nomeHabilidade1.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
		nomeHabilidade2 = new Icones_interativos(16, 16 + 244 + 72 + 4 + 47);
		nomeHabilidade2.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidade.png");
		nomeHabilidade3 = new Icones_interativos(16, 16 + 244 + 72 + 4 + 47 + 47);
		nomeHabilidade3.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidade.png");
		nomeHabilidade4 = new Icones_interativos(16, 16 + 244 + 72 + 4 + 47 + 47 + 47);
		nomeHabilidade4.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidade.png");
		
		apelo = new Icones_interativos(16 + 300 + 4, 16 + 244 + 72 + 4);
		apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][0] + ".png");
		descricao = new Icones_interativos(16 + 300 + 4, 16 + 244 + 72 + 4 + 56 + 4);
		descricao.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\descricao.png");
		
		nomeApelo1 = new Texto(16 + 20, 16 + 244 + 72 + 4 + 30, NomeApelos[aventureiro][0]);
		nomeApelo2 = new Texto(16 + 20, 16 + 244 + 72 + 4 + 47 + 30, NomeApelos[aventureiro][1]);
		nomeApelo3 = new Texto(16 + 20, 16 + 244 + 72 + 4 + 47 + 47 + 30, NomeApelos[aventureiro][2]);
		nomeApelo4 = new Texto(16 + 20, 16 + 244 + 72 + 4 + 47 + 47 + 47 + 30, NomeApelos[aventureiro][3]);
		
		textoDescricao1 = new Texto(16 + 300 + 4 + 20, 16 + 244 + 72 + 4 + 56 + 4 + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
		textoDescricao1.setFonte(new Font("Arial", Font.PLAIN, 20));
		textoDescricao2 = new Texto(16 + 300 + 4 + 20, 16 + 244 + 72 + 4 + 56 + 4 + 28 + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
		textoDescricao2.setFonte(new Font("Arial", Font.PLAIN, 20));
		textoDescricao3 = new Texto(16 + 300 + 4 + 20, 16 + 244 + 72 + 4 + 56 + 4 + 28 + 28 + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
		textoDescricao3.setFonte(new Font("Arial", Font.PLAIN, 20));
		textoDescricao4 = new Texto(16 + 300 + 4 + 20, 16 + 244 + 72 + 4 + 56 + 4 + 28 + 28 + 28 + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
		textoDescricao4.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		apeloQuantidade = new Texto(6 + 300 + 4 + 170, 16 + 244 + 72 + 4 + 24, "Apelo:");
		apeloQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		InterferenciaQuantidade = new Texto(6 + 300 + 4 + 170, 16 + 244 + 72 + 4 + 48, "Interferencia:");
		InterferenciaQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		menos2Coracoes = new Icones_interativos(16 + 676 - 48 - 4, 16 + 4);
		
		timer = new Timer(5, this);
		timer.start();
		
		
	}
	
	public void dialogoVoltar(int codigo) {
		if(dialogo.getImagem() == null && codigo == KeyEvent.VK_Z) {
			dialogo.load("res\\dialogoSim.png");
			botaoSimNao = true;
			txtMenVoltSim.setTexto("Sim");
			txtMenVoltNao.setTexto("Não");
			txtMenVoltar.setTexto("Você deseja voltar?");
			
		} else if(dialogo.getImagem() != null && codigo == KeyEvent.VK_X) {
			dialogo.setImagem(null);
			txtMenVoltSim.setTexto(" ");
			txtMenVoltNao.setTexto(" ");
			txtMenVoltar.setTexto(" ");
			
		}else if (botaoSimNao == false && (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT)) {
			dialogo.load("res\\dialogoSim.png");
			botaoSimNao = true;
			
		}else if (botaoSimNao == true && (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT)) {
			dialogo.load("res\\dialogoNao.png");
			botaoSimNao = false;
			
		}else if(codigo == KeyEvent.VK_Z && dialogo.getImagem() != null) {
			
			if(botaoSimNao == false) {
				dialogo.setImagem(null);
				
				txtMenVoltSim.setTexto(" ");
				txtMenVoltNao.setTexto(" ");
				txtMenVoltar.setTexto(" ");
			}
			else {
				JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(paginaAnterior);
		        janelaPrincipal.setTitle("Escolha de Adversário");
		        janelaPrincipal.revalidate();
			}
		}
		
		
	}
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		Efeito6[0] = 0; Efeito6[1] = 0;
		
		if(codigo == KeyEvent.VK_ESCAPE &&  dialogo.getImagem() == null) {
			mostrarMenu = !mostrarMenu;
			
			if(mostrarMenu == true) {
				contMenu = 0;
				fundoMenu.load("res\\menu.png");
				bntMenu.load("res\\bntMenu2.png");
				bntRegras.load("res\\bntMenu1.png");
				 bntVoltar.load("res\\bntMenu1.png");
				
				txtMenu.setTexto("MENU");
				txtRegras.setTexto("REGRAS");
				txtVoltar.setTexto("VOLTAR");
			} else {
				fundoMenu.load("");
				bntMenu.load("");
				bntRegras.load("");
				 bntVoltar.load("");
				
				txtMenu.setTexto(" ");
				txtRegras.setTexto(" ");
				txtVoltar.setTexto(" ");
			}
		}else if(codigo == KeyEvent.VK_UP && mostrarMenu == true && dialogo.getImagem() == null) {
			if(contMenu == 0) {
				contMenu = 2;
			} else {
				contMenu --;
			}
			
			switch (contMenu) {
				case 0:
					bntMenu.load("res\\bntMenu2.png");
					bntRegras.load("res\\bntMenu1.png");
					break;
				case 1:
					bntRegras.load("res\\bntMenu2.png");
					bntVoltar.load("res\\bntMenu1.png");
					break;
				case 2:
					bntVoltar.load("res\\bntMenu2.png");
					bntMenu.load("res\\bntMenu1.png");
					break;
			}
		}else if(codigo == KeyEvent.VK_DOWN && mostrarMenu == true &&  dialogo.getImagem() == null) {
			if(contMenu == 2) {
				contMenu = 0;
			} else {
				contMenu ++;
			}
			
			switch (contMenu) {
				case 0:
					bntMenu.load("res\\bntMenu2.png");
					bntVoltar.load("res\\bntMenu1.png");
					break;
				case 1:
					bntRegras.load("res\\bntMenu2.png");
					bntMenu.load("res\\bntMenu1.png");
					break;
				case 2:
					bntVoltar.load("res\\bntMenu2.png");
					bntRegras.load("res\\bntMenu1.png");
					break;
			}
		}else if(((codigo == KeyEvent.VK_Z || codigo == KeyEvent.VK_X) && mostrarMenu == true && bntVoltar.getReferencia().toString() == "res\\bntMenu2.png" ) || dialogo.getImagem() != null) {
			dialogoVoltar(codigo);
		}else if(codigo == KeyEvent.VK_UP && comecarAnimacaoCoracao == 0 && mostrarMenu == false && dialogo.getImagem() == null) {
			
			if(contTecla == 0) {
				contTecla = 3;
			} else {
				contTecla --;
			}
			
			switch (contTecla) {
				case 0:
					nomeHabilidade2.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (matrizAventureiros[6][posicaoAventureiro] == 1 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
					nomeHabilidade1.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
					apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][0] + ".png");
					
					textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
					textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
					textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
					textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
					
				    break;
				    
				case 1:
					nomeHabilidade3.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (matrizAventureiros[6][posicaoAventureiro] == 2 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
					nomeHabilidade2.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
					apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][1] + ".png");
					
					textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 1 : (aventureiro == 1 ? 5 : (aventureiro == 2 ? 9 : (aventureiro == 3 ? 13 : 17)))][0]);
					textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 1 : (aventureiro == 1 ? 5 : (aventureiro == 2 ? 9 : (aventureiro == 3 ? 13 : 17)))][1]);
					textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 1 : (aventureiro == 1 ? 5 : (aventureiro == 2 ? 9 : (aventureiro == 3 ? 13 : 17)))][2]);
					textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 1 : (aventureiro == 1 ? 5 : (aventureiro == 2 ? 9 : (aventureiro == 3 ? 13 : 17)))][3]);
				    
					break;
				    
				case 2:
					nomeHabilidade4.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (matrizAventureiros[6][posicaoAventureiro] == 3 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
					nomeHabilidade3.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
					apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][2] + ".png");
					
					textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 2 : (aventureiro == 1 ? 6 : (aventureiro == 2 ? 10 : (aventureiro == 3 ? 14 : 18)))][0]);
					textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 2 : (aventureiro == 1 ? 6 : (aventureiro == 2 ? 10 : (aventureiro == 3 ? 14 : 18)))][1]);
					textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 2 : (aventureiro == 1 ? 6 : (aventureiro == 2 ? 10 : (aventureiro == 3 ? 14 : 18)))][2]);
					textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 2 : (aventureiro == 1 ? 6 : (aventureiro == 2 ? 10 : (aventureiro == 3 ? 14 : 18)))][3]);
				    
				    break;
				    
				case 3:
					nomeHabilidade1.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (matrizAventureiros[6][posicaoAventureiro] == 0 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
					nomeHabilidade4.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
					apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][3] + ".png");

					textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 3 : (aventureiro == 1 ? 7 : (aventureiro == 2 ? 11 : (aventureiro == 3 ? 15 : 19)))][0]);
					textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 3 : (aventureiro == 1 ? 7 : (aventureiro == 2 ? 11 : (aventureiro == 3 ? 15 : 19)))][1]);
					textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 3 : (aventureiro == 1 ? 7 : (aventureiro == 2 ? 11 : (aventureiro == 3 ? 15 : 19)))][2]);
					textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 3 : (aventureiro == 1 ? 7 : (aventureiro == 2 ? 11 : (aventureiro == 3 ? 15 : 19)))][3]);
				    
				    break;
			}
				  
		}
		else if(codigo == KeyEvent.VK_DOWN && comecarAnimacaoCoracao == 0 && mostrarMenu == false && dialogo.getImagem() == null) {
			
			if(contTecla == 3) {
				contTecla = 0;
			} else {
				contTecla ++;
			}
			
			
			switch (contTecla) {
				case 0:
					nomeHabilidade4.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (matrizAventureiros[6][posicaoAventureiro] == 3 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
					nomeHabilidade1.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
					apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][0] + ".png");
					
					textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
					textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
					textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
					textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
					
				    break;
				    
				case 1:
					nomeHabilidade1.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (matrizAventureiros[6][posicaoAventureiro] == 0 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
					nomeHabilidade2.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
					apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][1] + ".png");
					
					textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 1 : (aventureiro == 1 ? 5 : (aventureiro == 2 ? 9 : (aventureiro == 3 ? 13 : 17)))][0]);
					textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 1 : (aventureiro == 1 ? 5 : (aventureiro == 2 ? 9 : (aventureiro == 3 ? 13 : 17)))][1]);
					textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 1 : (aventureiro == 1 ? 5 : (aventureiro == 2 ? 9 : (aventureiro == 3 ? 13 : 17)))][2]);
					textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 1 : (aventureiro == 1 ? 5 : (aventureiro == 2 ? 9 : (aventureiro == 3 ? 13 : 17)))][3]);
				    
				    break;
				    
				case 2:
					nomeHabilidade2.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (matrizAventureiros[6][posicaoAventureiro] == 1 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
					nomeHabilidade3.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
					apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][2] + ".png");
					
					textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 2 : (aventureiro == 1 ? 6 : (aventureiro == 2 ? 10 : (aventureiro == 3 ? 14 : 18)))][0]);
					textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 2 : (aventureiro == 1 ? 6 : (aventureiro == 2 ? 10 : (aventureiro == 3 ? 14 : 18)))][1]);
					textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 2 : (aventureiro == 1 ? 6 : (aventureiro == 2 ? 10 : (aventureiro == 3 ? 14 : 18)))][2]);
					textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 2 : (aventureiro == 1 ? 6 : (aventureiro == 2 ? 10 : (aventureiro == 3 ? 14 : 18)))][3]);
				    
				    break;
				    
				case 3:
					nomeHabilidade3.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (matrizAventureiros[6][posicaoAventureiro] == 2 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
					nomeHabilidade4.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
					apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][3] + ".png");
					
					textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 3 : (aventureiro == 1 ? 7 : (aventureiro == 2 ? 11 : (aventureiro == 3 ? 15 : 19)))][0]);
					textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 3 : (aventureiro == 1 ? 7 : (aventureiro == 2 ? 11 : (aventureiro == 3 ? 15 : 19)))][1]);
					textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 3 : (aventureiro == 1 ? 7 : (aventureiro == 2 ? 11 : (aventureiro == 3 ? 15 : 19)))][2]);
					textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 3 : (aventureiro == 1 ? 7 : (aventureiro == 2 ? 11 : (aventureiro == 3 ? 15 : 19)))][3]);
				    
				    break;
			}
		}
		else if(codigo == KeyEvent.VK_Z && contZ < 5 && comecarAnimacaoCoracao == 0 && mostrarMenu == false && dialogo.getImagem() == null) {
			contZ++;
			mudarEscolha = false;
			
			arrayAleatorio[0] = aleatorio.nextInt(4); arrayAleatorio[1] = aleatorio.nextInt(4); arrayAleatorio[2] = aleatorio.nextInt(4); arrayAleatorio[3] = aleatorio.nextInt(4); arrayAleatorio[4] = aleatorio.nextInt(4);
			
			// ------------------------------- soma pontos ---------------------------------
			
			for(int i=0; i<5;i++) {
				if(matrizAventureiros[0][i] == 0) {
					matrizAventureiros[7][i] = apeloIgnis[3][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[5][i] = apeloIgnis[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloIgnis[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[3][i] = apeloIgnis[1][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[4][i] = apeloIgnis[2][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla);
					
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
					matrizAventureiros[7][i] = apeloAyla[3][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[5][i] = apeloAyla[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloAyla[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[3][i] = apeloAyla[1][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[4][i] = apeloAyla[2][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla);

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
					matrizAventureiros[7][i] = apeloRexthor[3][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[5][i] = apeloRexthor[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloRexthor[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[3][i] = apeloRexthor[1][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[4][i] = apeloRexthor[2][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla);

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
					matrizAventureiros[7][i] = apeloKiki[3][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[5][i] = apeloKiki[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloKiki[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[3][i] = apeloKiki[1][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[4][i] = apeloKiki[2][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla);

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
					matrizAventureiros[7][i] = apeloArius[3][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[5][i] = apeloArius[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla] + matrizAventureiros[5][i];
					matrizAventureiros[2][i] = apeloArius[0][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[3][i] = apeloArius[1][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					matrizAventureiros[4][i] = apeloArius[2][i != posicaoAventureiro ? arrayAleatorio[i] : contTecla];
					apeloRepetido[i] = (matrizAventureiros[6][i] == (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla) ? 1 : 0);
					matrizAventureiros[6][i] = (i != posicaoAventureiro ? arrayAleatorio[i] : contTecla);

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
	
		} else if(codigo == KeyEvent.VK_Z && contZ == 6 ) {
		
			JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        janelaPrincipal.add(paginaAnterior);
	        janelaPrincipal.setTitle("Escolha de Adversário");
	        
	        if(matrizAventureiros[0][0] == aventureiro) {
	        	for(int i=0; i<5; i++) {
	        		if(paginaAnterior.derrotados[i] == -1 || paginaAnterior.derrotados[i] == adversario) {
	        			paginaAnterior.derrotados[i] = adversario;
	        			break;
	        		}
	        	}
	        }
	        
	        paginaAnterior.mostrarEstrela();
	        janelaPrincipal.revalidate();
		
		}
	}

	public void interferirNosAdversarios(int dano, int efeito, int posicaoNaLista) {//(-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro)
		
		valoresInterferencia[0] = dano; valoresInterferencia[1] = efeito; valoresInterferencia[2] = posicaoNaLista;
	
		System.out.println("esta retornando");
		
		if(efeito == 0 && posicaoNaLista > 0 && TerminouLoop == 0) { //0: todos acima
			for(int i2=0; i2<5; i2++) {
				if(animacaoEfeitoConcluido[i2] == true) {i2++;}
				if(animacaoEfeitoConcluido[posicaoNaLista-1] == true && TerminouLoop == 0) {
					TerminouLoop = 1;
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
			
		} else if(efeito == 1 && posicaoNaLista < 4 && TerminouLoop == 0) { //1: todos abaixo
			for(int i3=0; i3<5; i3++) {
				if(animacaoEfeitoConcluido[i3] == true) {i3++;}
				if(animacaoEfeitoConcluido[4] == true && TerminouLoop == 0) {
					TerminouLoop = 1;
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
			
		} else if(efeito == 2 && posicaoNaLista -1 >= 0  && TerminouLoop == 0) { //2: um acima,
			if(animacaoEfeitoConcluido[posicaoNaLista -1] == true && TerminouLoop == 0) {
				animacaoEfeitoConcluido[0]=false;
				animacaoEfeitoConcluido[1]=false;
				animacaoEfeitoConcluido[2]=false;
				animacaoEfeitoConcluido[3]=false;
				animacaoEfeitoConcluido[4]=false;
				TerminouLoop = 1; 
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
		} 
		else if(efeito == 3 && posicaoNaLista != 0  && TerminouLoop == 0) { //3: primeiro,
			if(animacaoEfeitoConcluido[0] == true && TerminouLoop == 0) {
				animacaoEfeitoConcluido[0]=false;
				animacaoEfeitoConcluido[1]=false;
				animacaoEfeitoConcluido[2]=false;
				animacaoEfeitoConcluido[3]=false;
				animacaoEfeitoConcluido[4]=false;
				TerminouLoop = 1; 
				vezDoAventureiro = vezDoAventureiro +1;
				animacaoFileira = vezDoAventureiro * 2;
				if(vezDoAventureiro == 5) {vezDoAventureiro = 0; animacaoFileira = 20;}
			}else {
				interferenciasRecebidas[0][posicaoNaLista] = dano;
				animacaoFileira = 10;
			}
		//4: zera seus pontos negativos,
		}else if(efeito == 4 && ((posicaoNaLista == 0 ? coracao11 : (posicaoNaLista == 1 ? coracao12 : (posicaoNaLista == 2 ? coracao13 : (posicaoNaLista == 3 ? coracao14 : coracao15)))).getReferencia().toString() == "res\\batalha\\interferencia.png")) { 

			matrizAventureiros[2][posicaoNaLista] = 0 - (danoEfeito4[posicaoNaLista]);
			
			System.out.println("restaurar: " + matrizAventureiros[2][posicaoNaLista]);

			switch (posicaoNaLista) {
				case 0: animacaoFileira = 0; break;
				case 1: animacaoFileira = 2; break;
				case 2: animacaoFileira = 4; break;
				case 3: animacaoFileira = 6; break;
				case 4: animacaoFileira = 8; break;
			}
			
		}else if(efeito == 6 && (Efeito6[matrizAventureiros[0][posicaoNaLista] == 2 ? 0 : 1] == 0)) { //6: jogar d20
			Efeito6[matrizAventureiros[0][posicaoNaLista] == 2 ? 0 : 1] = 1;

			dados[0] = 0; dados[1] = 0; dados[2] = 0; dados[3] = 0; dados[4] = 0;
			int temporario;
			int conta = 0;
			
			dados[0]= aleatorio.nextInt(20)+1; 
			
			for(int i=0; i<4; i++) {
				conta = 0;
				
				while(conta == 0) {
					temporario = aleatorio.nextInt(20)+1;
					
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
			
		} else if(efeito == 5 && TerminouLoop == 0) { //5: um acima e um abaixo,
			if((posicaoNaLista -1 >= 0 ? animacaoEfeitoConcluido[posicaoNaLista -1] == true : true) && (posicaoNaLista + 1 <= 4 ? animacaoEfeitoConcluido[posicaoNaLista +1] == true : true) && TerminouLoop == 0) {
				
				TerminouLoop = 1; 
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
		} 
		else if(TerminouLoop == 0 || (efeito == 4 && ((posicaoNaLista == 0 ? coracao11 : (posicaoNaLista == 1 ? coracao12 : (posicaoNaLista == 2 ? coracao13 : (posicaoNaLista == 3 ? coracao14 : coracao15)))).getReferencia().toString() == "res\\batalha\\coracaoVazio.png"))){ // -1 nenhum

			animacaoEfeitoConcluido[0]=false;
			animacaoEfeitoConcluido[1]=false;
			animacaoEfeitoConcluido[2]=false;
			animacaoEfeitoConcluido[3]=false;
			animacaoEfeitoConcluido[4]=false;
			TerminouLoop = 1;
			Efeito4 =0;
			vezDoAventureiro = vezDoAventureiro +1;
			animacaoFileira = vezDoAventureiro*2;
			if(vezDoAventureiro == 5) {vezDoAventureiro = 0; animacaoFileira = 20;}
			
			System.out.println(vezDoAventureiro);
		}
	}
	
	public void repintarCampoBatalha() {
		System.out.println("posição do aventureiro : " + posicaoAventureiro);
		campoBatalha1.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][0]] + "\\" + (posicaoAventureiro == 0 ? "campoBatalhaAventureiro.png" : "campoBatalha.png"));
		campoBatalha2.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][1]] + "\\" + (posicaoAventureiro == 1 ? "campoBatalhaAventureiro.png" : "campoBatalha.png"));
		campoBatalha3.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][2]] + "\\" + (posicaoAventureiro == 2 ? "campoBatalhaAventureiro.png" : "campoBatalha.png"));
		campoBatalha4.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][3]] + "\\" + (posicaoAventureiro == 3 ? "campoBatalhaAventureiro.png" : "campoBatalha.png"));
		campoBatalha5.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][4]] + "\\" + (posicaoAventureiro == 4 ? "campoBatalhaAventureiro.png" : "campoBatalha.png"));
		
		nomeAvent1.setTexto(nomeAventureiro[matrizAventureiros[0][0]]);
		nomeAvent2.setTexto(nomeAventureiro[matrizAventureiros[0][1]]);
		nomeAvent3.setTexto(nomeAventureiro[matrizAventureiros[0][2]]);
		nomeAvent4.setTexto(nomeAventureiro[matrizAventureiros[0][3]]);
		nomeAvent5.setTexto(nomeAventureiro[matrizAventureiros[0][4]]);
		
	}
	
	public void apagarCoracoes() {
	
			coracao11.load("res\\batalha\\coracaoVazio.png"); coracao21.load("res\\batalha\\coracaoVazio.png"); coracao31.load("res\\batalha\\coracaoVazio.png"); coracao41.load("res\\batalha\\coracaoVazio.png"); coracao51.load("res\\batalha\\coracaoVazio.png");
			coracao61.load("res\\batalha\\coracaoVazio.png"); coracao71.load("res\\batalha\\coracaoVazio.png"); coracao81.load("res\\batalha\\coracaoVazio.png"); coracao91.load("res\\batalha\\coracaoVazio.png"); coracao101.load("res\\batalha\\coracaoVazio.png");		
			coracao12.load("res\\batalha\\coracaoVazio.png"); coracao22.load("res\\batalha\\coracaoVazio.png"); coracao32.load("res\\batalha\\coracaoVazio.png"); coracao42.load("res\\batalha\\coracaoVazio.png"); coracao52.load("res\\batalha\\coracaoVazio.png");
			coracao62.load("res\\batalha\\coracaoVazio.png"); coracao72.load("res\\batalha\\coracaoVazio.png"); coracao82.load("res\\batalha\\coracaoVazio.png"); coracao92.load("res\\batalha\\coracaoVazio.png"); coracao102.load("res\\batalha\\coracaoVazio.png");
			coracao13.load("res\\batalha\\coracaoVazio.png"); coracao23.load("res\\batalha\\coracaoVazio.png"); coracao33.load("res\\batalha\\coracaoVazio.png"); coracao43.load("res\\batalha\\coracaoVazio.png"); coracao53.load("res\\batalha\\coracaoVazio.png");
			coracao63.load("res\\batalha\\coracaoVazio.png"); coracao73.load("res\\batalha\\coracaoVazio.png"); coracao83.load("res\\batalha\\coracaoVazio.png"); coracao93.load("res\\batalha\\coracaoVazio.png"); coracao103.load("res\\batalha\\coracaoVazio.png");
			coracao14.load("res\\batalha\\coracaoVazio.png"); coracao24.load("res\\batalha\\coracaoVazio.png"); coracao34.load("res\\batalha\\coracaoVazio.png"); coracao44.load("res\\batalha\\coracaoVazio.png"); coracao54.load("res\\batalha\\coracaoVazio.png");
			coracao64.load("res\\batalha\\coracaoVazio.png"); coracao74.load("res\\batalha\\coracaoVazio.png"); coracao84.load("res\\batalha\\coracaoVazio.png"); coracao94.load("res\\batalha\\coracaoVazio.png"); coracao104.load("res\\batalha\\coracaoVazio.png");
			coracao15.load("res\\batalha\\coracaoVazio.png"); coracao25.load("res\\batalha\\coracaoVazio.png"); coracao35.load("res\\batalha\\coracaoVazio.png"); coracao45.load("res\\batalha\\coracaoVazio.png"); coracao55.load("res\\batalha\\coracaoVazio.png");
			coracao65.load("res\\batalha\\coracaoVazio.png"); coracao75.load("res\\batalha\\coracaoVazio.png"); coracao85.load("res\\batalha\\coracaoVazio.png"); coracao95.load("res\\batalha\\coracaoVazio.png"); coracao105.load("res\\batalha\\coracaoVazio.png");
	}
	
	public void arrumarListaAventureiros() {
		
		if(aventureiro == adversario) {
			matrizAventureiros[0][0] = aventureiro;
			matrizAventureiros[0][4] = adversario != 0 ? adversario - 1 : 4;
			matrizAventureiros[0][1] = adversario + 1 < 5 ? adversario + 1 : adversario + 1 - 5 ;
			matrizAventureiros[0][2] = adversario + 2 < 5 ? adversario + 2 : adversario + 2 - 5 ;
			matrizAventureiros[0][3] = adversario + 3 < 5 ? adversario + 3 : adversario + 3 - 5 ;
			posicaoAventureiro = 0;
		}
		else {
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
		graficos.drawImage(animacao.getImagem(), animacao.getX(), animacao.getY(), this);
		graficos.drawImage(apelo.getImagem(), apelo.getX(), apelo.getY(), this);
		
		graficos.drawImage(campoBatalha1.getImagem(), campoBatalha1.getX(), campoBatalha1.getY(), this);
		graficos.drawImage(campoBatalha2.getImagem(), campoBatalha2.getX(), campoBatalha2.getY(), this);
		graficos.drawImage(campoBatalha3.getImagem(), campoBatalha3.getX(), campoBatalha3.getY(), this);
		graficos.drawImage(campoBatalha4.getImagem(), campoBatalha4.getX(), campoBatalha4.getY(), this);
		graficos.drawImage(campoBatalha5.getImagem(), campoBatalha5.getX(), campoBatalha5.getY(), this);
		
		graficos.drawImage(dado1.getImagem(), dado1.getX(), dado1.getY(), this);
		graficos.drawImage(dado2.getImagem(), dado2.getX(), dado2.getY(), this);
		graficos.drawImage(dado3.getImagem(), dado3.getX(), dado3.getY(), this);
		graficos.drawImage(dado4.getImagem(), dado4.getX(), dado4.getY(), this);
		graficos.drawImage(dado5.getImagem(), dado5.getX(), dado5.getY(), this);
		
		graficos.drawImage(descricao.getImagem(), descricao.getX(), descricao.getY(), this);
		graficos.drawImage(descricaoAnimacao.getImagem(), descricaoAnimacao.getX(), descricaoAnimacao.getY(), this);
		
		graficos.drawImage(nomeHabilidade1.getImagem(), nomeHabilidade1.getX(), nomeHabilidade1.getY(), this);
		graficos.drawImage(nomeHabilidade2.getImagem(), nomeHabilidade2.getX(), nomeHabilidade2.getY(), this);
		graficos.drawImage(nomeHabilidade3.getImagem(), nomeHabilidade3.getX(), nomeHabilidade3.getY(), this);
		graficos.drawImage(nomeHabilidade4.getImagem(), nomeHabilidade4.getX(), nomeHabilidade4.getY(), this);
		
		graficos.drawImage(coracao01.getImagem(), coracao01.getX(), coracao01.getY(), this);
		graficos.drawImage(coracao02.getImagem(), coracao02.getX(), coracao02.getY(), this);
		graficos.drawImage(coracao03.getImagem(), coracao03.getX(), coracao03.getY(), this);
		graficos.drawImage(coracao04.getImagem(), coracao04.getX(), coracao04.getY(), this);
		graficos.drawImage(coracao05.getImagem(), coracao05.getX(), coracao05.getY(), this);
		
		graficos.drawImage(coracao11.getImagem(), coracao11.getX(), coracao11.getY(), this); graficos.drawImage(coracao21.getImagem(), coracao21.getX(), coracao21.getY(), this); graficos.drawImage(coracao31.getImagem(), coracao31.getX(), coracao31.getY(), this); graficos.drawImage(coracao41.getImagem(), coracao41.getX(), coracao41.getY(), this); graficos.drawImage(coracao51.getImagem(), coracao51.getX(), coracao51.getY(), this); graficos.drawImage(coracao61.getImagem(), coracao61.getX(), coracao61.getY(), this); graficos.drawImage(coracao71.getImagem(), coracao71.getX(), coracao71.getY(), this); graficos.drawImage(coracao81.getImagem(), coracao81.getX(), coracao81.getY(), this); graficos.drawImage(coracao91.getImagem(), coracao91.getX(), coracao91.getY(), this); graficos.drawImage(coracao101.getImagem(), coracao101.getX(), coracao101.getY(), this);
		graficos.drawImage(coracao12.getImagem(), coracao12.getX(), coracao12.getY(), this); graficos.drawImage(coracao22.getImagem(), coracao22.getX(), coracao22.getY(), this); graficos.drawImage(coracao32.getImagem(), coracao32.getX(), coracao32.getY(), this); graficos.drawImage(coracao42.getImagem(), coracao42.getX(), coracao42.getY(), this); graficos.drawImage(coracao52.getImagem(), coracao52.getX(), coracao52.getY(), this); graficos.drawImage(coracao62.getImagem(), coracao62.getX(), coracao62.getY(), this); graficos.drawImage(coracao72.getImagem(), coracao72.getX(), coracao72.getY(), this); graficos.drawImage(coracao82.getImagem(), coracao82.getX(), coracao82.getY(), this); graficos.drawImage(coracao92.getImagem(), coracao92.getX(), coracao92.getY(), this); graficos.drawImage(coracao102.getImagem(), coracao102.getX(), coracao102.getY(), this);
		graficos.drawImage(coracao13.getImagem(), coracao13.getX(), coracao13.getY(), this); graficos.drawImage(coracao23.getImagem(), coracao23.getX(), coracao23.getY(), this); graficos.drawImage(coracao33.getImagem(), coracao33.getX(), coracao33.getY(), this); graficos.drawImage(coracao43.getImagem(), coracao43.getX(), coracao43.getY(), this); graficos.drawImage(coracao53.getImagem(), coracao53.getX(), coracao53.getY(), this); graficos.drawImage(coracao63.getImagem(), coracao63.getX(), coracao63.getY(), this); graficos.drawImage(coracao73.getImagem(), coracao73.getX(), coracao73.getY(), this); graficos.drawImage(coracao83.getImagem(), coracao83.getX(), coracao83.getY(), this); graficos.drawImage(coracao93.getImagem(), coracao93.getX(), coracao93.getY(), this); graficos.drawImage(coracao103.getImagem(), coracao103.getX(), coracao103.getY(), this);
		graficos.drawImage(coracao14.getImagem(), coracao14.getX(), coracao14.getY(), this); graficos.drawImage(coracao24.getImagem(), coracao24.getX(), coracao24.getY(), this); graficos.drawImage(coracao34.getImagem(), coracao34.getX(), coracao34.getY(), this); graficos.drawImage(coracao44.getImagem(), coracao44.getX(), coracao44.getY(), this); graficos.drawImage(coracao54.getImagem(), coracao54.getX(), coracao54.getY(), this); graficos.drawImage(coracao64.getImagem(), coracao64.getX(), coracao64.getY(), this); graficos.drawImage(coracao74.getImagem(), coracao74.getX(), coracao74.getY(), this); graficos.drawImage(coracao84.getImagem(), coracao84.getX(), coracao84.getY(), this); graficos.drawImage(coracao94.getImagem(), coracao94.getX(), coracao94.getY(), this); graficos.drawImage(coracao104.getImagem(), coracao104.getX(), coracao104.getY(), this);
		graficos.drawImage(coracao15.getImagem(), coracao15.getX(), coracao15.getY(), this); graficos.drawImage(coracao25.getImagem(), coracao25.getX(), coracao25.getY(), this); graficos.drawImage(coracao35.getImagem(), coracao35.getX(), coracao35.getY(), this); graficos.drawImage(coracao45.getImagem(), coracao45.getX(), coracao45.getY(), this); graficos.drawImage(coracao55.getImagem(), coracao55.getX(), coracao55.getY(), this); graficos.drawImage(coracao65.getImagem(), coracao65.getX(), coracao65.getY(), this); graficos.drawImage(coracao75.getImagem(), coracao75.getX(), coracao75.getY(), this); graficos.drawImage(coracao85.getImagem(), coracao85.getX(), coracao85.getY(), this); graficos.drawImage(coracao95.getImagem(), coracao95.getX(), coracao95.getY(), this); graficos.drawImage(coracao105.getImagem(), coracao105.getX(), coracao105.getY(), this);
		
	    tl1 = new TextLayout(nomeApelo1.getTexto(), nomeApelo1.getFonte(), frc);
	    tl2 = new TextLayout(nomeApelo2.getTexto(), nomeApelo2.getFonte(), frc);
	    tl3 = new TextLayout(nomeApelo3.getTexto(), nomeApelo3.getFonte(), frc);
	    tl4 = new TextLayout(nomeApelo4.getTexto(), nomeApelo4.getFonte(), frc);
	    
	    tl5 = new TextLayout(textoDescricao1.getTexto(), textoDescricao1.getFonte(), frc);
	    tl15 = new TextLayout(textoDescricao2.getTexto(), textoDescricao2.getFonte(), frc);
	    tl16 = new TextLayout(textoDescricao3.getTexto(), textoDescricao3.getFonte(), frc);
	    tl17 = new TextLayout(textoDescricao4.getTexto(), textoDescricao4.getFonte(), frc);
	    
	    tl6 = new TextLayout(apeloQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);
	    tl7 = new TextLayout(InterferenciaQuantidade.getTexto(), InterferenciaQuantidade.getFonte(), frc);
	    
	    tl8 = new TextLayout(nomeAvent1.getTexto(), nomeAvent1.getFonte(), frc);
	    tl9 = new TextLayout(nomeAvent2.getTexto(), nomeAvent2.getFonte(), frc);
	    tl10 = new TextLayout(nomeAvent3.getTexto(), nomeAvent3.getFonte(), frc);
	    tl11 = new TextLayout(nomeAvent4.getTexto(), nomeAvent4.getFonte(), frc);
	    tl12 = new TextLayout(nomeAvent5.getTexto(), nomeAvent5.getFonte(), frc);
	    
	    graficos.setColor(nomeApelo1.getCorTexto());
	    tl1.draw(graficos, nomeApelo1.getX(), nomeApelo1.getY());
	    graficos.setColor(nomeApelo2.getCorTexto());
	    tl2.draw(graficos, nomeApelo2.getX(), nomeApelo2.getY());
	    graficos.setColor(nomeApelo3.getCorTexto());
	    tl3.draw(graficos, nomeApelo3.getX(), nomeApelo3.getY());
	    graficos.setColor(nomeApelo4.getCorTexto());
	    tl4.draw(graficos, nomeApelo4.getX(), nomeApelo4.getY());
	    graficos.setColor(Color.BLACK);
	    
	    tl5.draw(graficos, textoDescricao1.getX(), textoDescricao1.getY());
	    tl15.draw(graficos, textoDescricao2.getX(), textoDescricao2.getY());
	    tl16.draw(graficos, textoDescricao3.getX(), textoDescricao3.getY());
	    tl17.draw(graficos, textoDescricao4.getX(), textoDescricao4.getY());
	    
	    tl6.draw(graficos, apeloQuantidade.getX(), apeloQuantidade.getY());
	    tl7.draw(graficos, InterferenciaQuantidade.getX(), InterferenciaQuantidade.getY());
	    
	    tl8.draw(graficos, nomeAvent1.getX(), nomeAvent1.getY());
	    tl9.draw(graficos, nomeAvent2.getX(), nomeAvent2.getY());
	    tl10.draw(graficos, nomeAvent3.getX(), nomeAvent3.getY());
	    tl11.draw(graficos, nomeAvent4.getX(), nomeAvent4.getY());
	    tl12.draw(graficos, nomeAvent5.getX(), nomeAvent5.getY());
	    
	    graficos.drawImage(menos2Coracoes.getImagem(), menos2Coracoes.getX(), menos2Coracoes.getY(), this);
	    
		graficos.drawImage(vencedor.getImagem(), vencedor.getX(), vencedor.getY(), this);
		
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getX(), fundoMenu.getY(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getX(), bntMenu.getY(), this);
		graficos.drawImage(bntRegras.getImagem(), bntRegras.getX(), bntRegras.getY(), this);
		graficos.drawImage( bntVoltar.getImagem(),  bntVoltar.getX(),  bntVoltar.getY(), this);
		
		tl13 = new TextLayout(txtMenu.getTexto(), txtMenu.getFonte(), frc);
		tl15 = new TextLayout(txtRegras.getTexto(), txtRegras.getFonte(), frc);
		tl16 = new TextLayout(txtVoltar.getTexto(), txtVoltar.getFonte(), frc);
		
		tl13.draw(graficos, txtMenu.getX(), txtMenu.getY());
		tl15.draw(graficos, txtRegras.getX(), txtRegras.getY());
		tl16.draw(graficos, txtVoltar.getX(), txtVoltar.getY());
		
		graficos.drawImage(dialogo.getImagem(), dialogo.getX(), dialogo.getY(), this);
		tl17 = new TextLayout(txtMenVoltSim.getTexto(), txtMenVoltSim.getFonte(), frc);
		tl18 = new TextLayout(txtMenVoltNao.getTexto(), txtMenVoltNao.getFonte(), frc);
		tl19 = new TextLayout(txtMenVoltar.getTexto(), txtMenVoltar.getFonte(), frc);
		
		tl17.draw(graficos, txtMenVoltSim.getX(), txtMenVoltSim.getY());
		tl18.draw(graficos, txtMenVoltNao.getX(), txtMenVoltNao.getY());
		tl19.draw(graficos, txtMenVoltar.getX(), txtMenVoltar.getY());
		
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);

		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(vezDados == true) {
			aparecerDados();
		}else if(animacaoFileira != -1) {
			aparecerCoracoes();
		}
		
		if(animacaoFileira >= 10 && animacaoFileira < 20) {
			sumirCoracoes();
		}
		
		if(animacaoFileira == 20) {
			mexerCoracoes();
		}
		
		if(animacaoFileira == 21) {
			OrganizarCampos();
		}
		
		if(animacaoFileira == 22) {
			vencedor();
		}
		
		repaint();
		
	}
	
	public void gifApresentacao(int avent, int numApelo) {
		if(gif == false) {
			gif = true;
			
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
	
	public void aparecerDados() {
		dado1.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[0] + ".png");
		dado2.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[1] + ".png");
		dado3.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[2] + ".png");
		dado4.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[3] + ".png");
		dado5.load("res\\batalha\\" + (matrizAventureiros[0][vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[4] + ".png");
		
		dado1.setDx(dado1.getDx() + comecarAnimacaoCoracao);
		
		if(dado1.getDx() > 150) {
			dado1.load(""); dado2.load(""); dado3.load(""); dado4.load(""); dado5.load("");
			dado1.setDx(0);
			vezDados = false;
		}
	}
	
	public void aparecerCoracoes() {
			
		if(animacaoFileira == 0 || animacaoFileira == 1 ) {
			if(animacaoFileira == 0 && (matrizAventureiros[2][0] > 0 || matrizAventureiros[4][0] == 4 || matrizAventureiros[4][0] == 6)) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao101.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoop =0;
					
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][0], 0 != posicaoAventureiro ? arrayAleatorio[0] : contTecla);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao101.setDx(coracao101.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0  && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
				
				
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
						
					if(coracao101.getDx() >= intervaloAnimacao && coracao101.getImagem() != null && matrizAventureiros[2][0] > 0) {
						matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao101.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao101.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao101.load("res\\batalha\\coracaoVazio.png");} coracao91.setDx(coracao91.getDx() + comecarAnimacaoCoracao);
						if(coracao91.getDx() >= intervaloAnimacao && coracao91.getImagem() != null && matrizAventureiros[2][0] > 0) {
							matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao91.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao91.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao91.load("res\\batalha\\coracaoVazio.png");} coracao81.setDx(coracao81.getDx() + comecarAnimacaoCoracao);
							if(coracao81.getDx() >= intervaloAnimacao && coracao81.getImagem() != null && matrizAventureiros[2][0] > 0) {
								matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao81.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao81.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao81.load("res\\batalha\\coracaoVazio.png");} coracao71.setDx(coracao71.getDx() + comecarAnimacaoCoracao);
								if(coracao71.getDx() >= intervaloAnimacao && coracao71.getImagem() != null && matrizAventureiros[2][0] > 0) {
									matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao71.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao71.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao71.load("res\\batalha\\coracaoVazio.png");} coracao61.setDx(coracao61.getDx() + comecarAnimacaoCoracao);
									if(coracao61.getDx() >= intervaloAnimacao && coracao61.getImagem() != null && matrizAventureiros[2][0] > 0) {
										matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao61.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao61.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao61.load("res\\batalha\\coracaoVazio.png");} coracao51.setDx(coracao51.getDx() + comecarAnimacaoCoracao);
										if(coracao51.getDx() >= intervaloAnimacao && coracao51.getImagem() != null && matrizAventureiros[2][0] > 0) {
											matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao51.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao51.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao51.load("res\\batalha\\coracaoVazio.png");} coracao41.setDx(coracao41.getDx() + comecarAnimacaoCoracao);
											if(coracao41.getDx() >= intervaloAnimacao && coracao41.getImagem() != null && matrizAventureiros[2][0] > 0) {
												matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao41.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao41.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao41.load("res\\batalha\\coracaoVazio.png");} coracao31.setDx(coracao31.getDx() + comecarAnimacaoCoracao);
												if(coracao31.getDx() >= intervaloAnimacao && coracao31.getImagem() != null && matrizAventureiros[2][0] > 0) {
													matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao31.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao31.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao31.load("res\\batalha\\coracaoVazio.png");} coracao21.setDx(coracao21.getDx() + comecarAnimacaoCoracao);
													if(coracao21.getDx() >= intervaloAnimacao && coracao21.getImagem() != null && matrizAventureiros[2][0] > 0) {
														matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao21.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao21.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao21.load("res\\batalha\\coracaoVazio.png");} coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
														if(coracao11.getDx() >= intervaloAnimacao && coracao11.getImagem() != null && matrizAventureiros[2][0] > 0) {
															matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao11.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao11.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao11.load("res\\batalha\\coracaoVazio.png");} animacaoFileira=1; zerarDx();
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

				gif= false;
				animacao.setDx(0);
						
				if(coracao11.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao11.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao11.load("res\\batalha\\apelo.png"); coracao21.setDx(coracao21.getDx() + comecarAnimacaoCoracao);
					if(coracao21.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao21.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao21.load("res\\batalha\\apelo.png");coracao31.setDx(coracao31.getDx() + comecarAnimacaoCoracao);
						if(coracao31.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao31.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao31.load("res\\batalha\\apelo.png");coracao41.setDx(coracao41.getDx() + comecarAnimacaoCoracao);
							if(coracao41.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao41.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao41.load("res\\batalha\\apelo.png");coracao51.setDx(coracao51.getDx() + comecarAnimacaoCoracao);
								if(coracao51.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao51.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao51.load("res\\batalha\\apelo.png");coracao61.setDx(coracao61.getDx() + comecarAnimacaoCoracao);
									if(coracao61.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao61.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao61.load("res\\batalha\\apelo.png");coracao71.setDx(coracao71.getDx() + comecarAnimacaoCoracao);
										if(coracao71.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao71.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao71.load("res\\batalha\\apelo.png");coracao81.setDx(coracao81.getDx() + comecarAnimacaoCoracao);
											if(coracao81.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao81.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao81.load("res\\batalha\\apelo.png");coracao91.setDx(coracao91.getDx() + comecarAnimacaoCoracao);
												if(coracao91.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) {matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao91.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao91.load("res\\batalha\\apelo.png");coracao101.setDx(coracao101.getDx() + comecarAnimacaoCoracao);
													if(coracao101.getDx() >= intervaloAnimacao && matrizAventureiros[2][0] > 0) { matrizAventureiros[2][0] = matrizAventureiros[2][0] - (coracao101.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao101.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else {interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
													}if(matrizAventureiros[2][0] == 0 && TerminouLoop != 1 ) { if(apeloRepetido[vezDoAventureiro] == 1) {apeloRepetido(0);} else if(efeitoChefeDeFase[0] == 1) {efeitoChefeDeFase(0);} else{interferirNosAdversarios(matrizAventureiros[3][0], matrizAventureiros[4][0], 0); zerarDx();}}
			}
				
		}else if(animacaoFileira == 2 || animacaoFileira == 3 ) {
			if(animacaoFileira == 2) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao102.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoop =0;
					
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][1], 1 != posicaoAventureiro ? arrayAleatorio[1] : contTecla);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao102.setDx(coracao102.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
				
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
					
					if(coracao102.getDx() >= intervaloAnimacao && coracao102.getImagem() != null && matrizAventureiros[2][1] > 0) {
						matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao102.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao102.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao102.load("res\\batalha\\coracaoVazio.png");} coracao92.setDx(coracao92.getDx() + comecarAnimacaoCoracao);
						if(coracao92.getDx() >= intervaloAnimacao && coracao92.getImagem() != null && matrizAventureiros[2][1] > 0) {
							matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao92.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao92.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao92.load("res\\batalha\\coracaoVazio.png");} coracao82.setDx(coracao82.getDx() + comecarAnimacaoCoracao);
							if(coracao82.getDx() >= intervaloAnimacao && coracao82.getImagem() != null && matrizAventureiros[2][1] > 0) {
								matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao82.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao82.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao82.load("res\\batalha\\coracaoVazio.png");} coracao72.setDx(coracao72.getDx() + comecarAnimacaoCoracao);
								if(coracao72.getDx() >= intervaloAnimacao && coracao72.getImagem() != null && matrizAventureiros[2][1] > 0) {
									matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao72.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao72.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao72.load("res\\batalha\\coracaoVazio.png");} coracao62.setDx(coracao62.getDx() + comecarAnimacaoCoracao);
									if(coracao62.getDx() >= intervaloAnimacao && coracao62.getImagem() != null && matrizAventureiros[2][1] > 0) {
										matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao62.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao62.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao62.load("res\\batalha\\coracaoVazio.png");} coracao52.setDx(coracao52.getDx() + comecarAnimacaoCoracao);
										if(coracao52.getDx() >= intervaloAnimacao && coracao52.getImagem() != null && matrizAventureiros[2][1] > 0) {
											matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao52.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao52.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao52.load("res\\batalha\\coracaoVazio.png");} coracao42.setDx(coracao42.getDx() + comecarAnimacaoCoracao);
											if(coracao42.getDx() >= intervaloAnimacao && coracao42.getImagem() != null && matrizAventureiros[2][1] > 0) {
												matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao42.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao42.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao42.load("res\\batalha\\coracaoVazio.png");} coracao32.setDx(coracao32.getDx() + comecarAnimacaoCoracao);
												if(coracao32.getDx() >= intervaloAnimacao && coracao32.getImagem() != null && matrizAventureiros[2][1] > 0) {
													matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao32.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao32.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao32.load("res\\batalha\\coracaoVazio.png");} coracao22.setDx(coracao22.getDx() + comecarAnimacaoCoracao);
													if(coracao22.getDx() >= intervaloAnimacao && coracao22.getImagem() != null && matrizAventureiros[2][1] > 0) {
														matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao22.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao22.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao22.load("res\\batalha\\coracaoVazio.png");} coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
														if(coracao12.getDx() >= intervaloAnimacao && coracao12.getImagem() != null && matrizAventureiros[2][1] > 0) {
															matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao12.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao12.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao12.load("res\\batalha\\coracaoVazio.png");} zerarDx(); animacaoFileira=3;
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
				coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
				
				gif= false; animacao.setDx(0);
				
				if(coracao12.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao12.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao12.load("res\\batalha\\apelo.png"); coracao22.setDx(coracao22.getDx() + comecarAnimacaoCoracao);
					if(coracao22.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao22.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao22.load("res\\batalha\\apelo.png");coracao32.setDx(coracao32.getDx() + comecarAnimacaoCoracao);
						if(coracao32.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao32.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao32.load("res\\batalha\\apelo.png");coracao42.setDx(coracao42.getDx() + comecarAnimacaoCoracao);
							if(coracao42.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao42.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao42.load("res\\batalha\\apelo.png");coracao52.setDx(coracao52.getDx() + comecarAnimacaoCoracao);
								if(coracao52.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao52.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao52.load("res\\batalha\\apelo.png");coracao62.setDx(coracao62.getDx() + comecarAnimacaoCoracao);
									if(coracao62.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao62.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao62.load("res\\batalha\\apelo.png");coracao72.setDx(coracao72.getDx() + comecarAnimacaoCoracao);
										if(coracao72.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao72.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao72.load("res\\batalha\\apelo.png");coracao82.setDx(coracao82.getDx() + comecarAnimacaoCoracao);
											if(coracao82.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao82.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao82.load("res\\batalha\\apelo.png");coracao92.setDx(coracao92.getDx() + comecarAnimacaoCoracao);
												if(coracao92.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao92.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao92.load("res\\batalha\\apelo.png");coracao102.setDx(coracao102.getDx() + comecarAnimacaoCoracao);
													if(coracao102.getDx() >= intervaloAnimacao && matrizAventureiros[2][1] > 0) {matrizAventureiros[2][1] = matrizAventureiros[2][1] - (coracao102.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao102.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
													}if(matrizAventureiros[2][1] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(1);} else if(efeitoChefeDeFase[1] == 1) {efeitoChefeDeFase(1);} else{interferirNosAdversarios(matrizAventureiros[3][1], matrizAventureiros[4][1], 1); zerarDx();}}
			}
				
		}else if(animacaoFileira == 4 || animacaoFileira == 5 ) {

			if(animacaoFileira == 4) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao103.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoop =0;
	
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][2], 2 != posicaoAventureiro ? arrayAleatorio[2] : contTecla);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao103.setDx(coracao103.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
				
				
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
				
					if(coracao103.getDx() >= intervaloAnimacao && coracao103.getImagem() != null && matrizAventureiros[2][2] > 0) {
						matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao103.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao103.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao103.load("res\\batalha\\coracaoVazio.png");} coracao93.setDx(coracao93.getDx() + comecarAnimacaoCoracao);
						if(coracao93.getDx() >= intervaloAnimacao && coracao93.getImagem() != null && matrizAventureiros[2][2] > 0) {
							matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao93.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao93.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao93.load("res\\batalha\\coracaoVazio.png");} coracao83.setDx(coracao83.getDx() + comecarAnimacaoCoracao);
							if(coracao83.getDx() >= intervaloAnimacao && coracao83.getImagem() != null && matrizAventureiros[2][2] > 0) {
								matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao83.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao83.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao83.load("res\\batalha\\coracaoVazio.png");} coracao73.setDx(coracao73.getDx() + comecarAnimacaoCoracao);
								if(coracao73.getDx() >= intervaloAnimacao && coracao73.getImagem() != null && matrizAventureiros[2][2] > 0) {
									matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao73.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao73.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao73.load("res\\batalha\\coracaoVazio.png");} coracao63.setDx(coracao63.getDx() + comecarAnimacaoCoracao);
									if(coracao63.getDx() >= intervaloAnimacao && coracao63.getImagem() != null && matrizAventureiros[2][2] > 0) {
										matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao63.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao63.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao63.load("res\\batalha\\coracaoVazio.png");} coracao53.setDx(coracao53.getDx() + comecarAnimacaoCoracao);
										if(coracao53.getDx() >= intervaloAnimacao && coracao53.getImagem() != null && matrizAventureiros[2][2] > 0) {
											matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao53.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao53.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao53.load("res\\batalha\\coracaoVazio.png");} coracao43.setDx(coracao43.getDx() + comecarAnimacaoCoracao);
											if(coracao43.getDx() >= intervaloAnimacao && coracao43.getImagem() != null && matrizAventureiros[2][2] > 0) {
												matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao43.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao43.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao43.load("res\\batalha\\coracaoVazio.png");} coracao33.setDx(coracao33.getDx() + comecarAnimacaoCoracao);
												if(coracao33.getDx() >= intervaloAnimacao && coracao33.getImagem() != null && matrizAventureiros[2][2] > 0) {
													matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao33.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao33.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao33.load("res\\batalha\\coracaoVazio.png");} coracao23.setDx(coracao23.getDx() + comecarAnimacaoCoracao);
													if(coracao23.getDx() >= intervaloAnimacao && coracao23.getImagem() != null && matrizAventureiros[2][2] > 0) {
														matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao23.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao23.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao23.load("res\\batalha\\coracaoVazio.png");} coracao13.setDx(coracao13.getDx() + comecarAnimacaoCoracao);
														if(coracao13.getDx() >= intervaloAnimacao && coracao13.getImagem() != null && matrizAventureiros[2][2] > 0) {
															matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao13.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao13.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao13.load("res\\batalha\\coracaoVazio.png");} zerarDx(); animacaoFileira=5;
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
				coracao13.setDx(coracao13.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
				
				gif= false; animacao.setDx(0);
				
				if(coracao13.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao13.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao13.load("res\\batalha\\apelo.png"); coracao23.setDx(coracao23.getDx() + comecarAnimacaoCoracao);
					if(coracao23.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao23.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao23.load("res\\batalha\\apelo.png");coracao33.setDx(coracao33.getDx() + comecarAnimacaoCoracao);
						if(coracao33.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao33.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao33.load("res\\batalha\\apelo.png");coracao43.setDx(coracao43.getDx() + comecarAnimacaoCoracao);
							if(coracao43.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao43.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao43.load("res\\batalha\\apelo.png");coracao53.setDx(coracao53.getDx() + comecarAnimacaoCoracao);
								if(coracao53.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao53.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao53.load("res\\batalha\\apelo.png");coracao63.setDx(coracao63.getDx() + comecarAnimacaoCoracao);
									if(coracao63.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao63.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao63.load("res\\batalha\\apelo.png");coracao73.setDx(coracao73.getDx() + comecarAnimacaoCoracao);
										if(coracao73.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao73.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao73.load("res\\batalha\\apelo.png");coracao83.setDx(coracao83.getDx() + comecarAnimacaoCoracao);
											if(coracao83.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao83.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao83.load("res\\batalha\\apelo.png");coracao93.setDx(coracao93.getDx() + comecarAnimacaoCoracao);
												if(coracao93.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao93.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao93.load("res\\batalha\\apelo.png");coracao103.setDx(coracao103.getDx() + comecarAnimacaoCoracao);
													if(coracao103.getDx() >= intervaloAnimacao && matrizAventureiros[2][2] > 0) {matrizAventureiros[2][2] = matrizAventureiros[2][2] - (coracao103.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao103.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
													}if(matrizAventureiros[2][2] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(2);} else if(efeitoChefeDeFase[2] == 1) {efeitoChefeDeFase(2);} else{interferirNosAdversarios(matrizAventureiros[3][2], matrizAventureiros[4][2], 2); zerarDx();}}
			}
				
		}else if(animacaoFileira == 6 || animacaoFileira == 7 ) {
			if(animacaoFileira == 6) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao104.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoop =0;
					
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][3], 3 != posicaoAventureiro ? arrayAleatorio[3] : contTecla);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao104.setDx(coracao104.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
				
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
				
					if(coracao104.getDx() >= intervaloAnimacao && coracao104.getImagem() != null && matrizAventureiros[2][3] > 0) {
						matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao104.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao104.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao104.load("res\\batalha\\coracaoVazio.png");} coracao94.setDx(coracao94.getDx() + comecarAnimacaoCoracao);
						if(coracao94.getDx() >= intervaloAnimacao && coracao94.getImagem() != null && matrizAventureiros[2][3] > 0) {
							matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao94.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao94.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao94.load("res\\batalha\\coracaoVazio.png");} coracao84.setDx(coracao84.getDx() + comecarAnimacaoCoracao);
							if(coracao84.getDx() >= intervaloAnimacao && coracao84.getImagem() != null && matrizAventureiros[2][3] > 0) {
								matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao84.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao84.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao84.load("res\\batalha\\coracaoVazio.png");} coracao74.setDx(coracao74.getDx() + comecarAnimacaoCoracao);
								if(coracao74.getDx() >= intervaloAnimacao && coracao74.getImagem() != null && matrizAventureiros[2][3] > 0) {
									matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao74.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao74.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao74.load("res\\batalha\\coracaoVazio.png");} coracao64.setDx(coracao64.getDx() + comecarAnimacaoCoracao);
									if(coracao64.getDx() >= intervaloAnimacao && coracao64.getImagem() != null && matrizAventureiros[2][3] > 0) {
										matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao64.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao64.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao64.load("res\\batalha\\coracaoVazio.png");} coracao54.setDx(coracao54.getDx() + comecarAnimacaoCoracao);
										if(coracao54.getDx() >= intervaloAnimacao && coracao54.getImagem() != null && matrizAventureiros[2][3] > 0) {
											matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao54.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao54.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao54.load("res\\batalha\\coracaoVazio.png");} coracao44.setDx(coracao44.getDx() + comecarAnimacaoCoracao);
											if(coracao44.getDx() >= intervaloAnimacao && coracao44.getImagem() != null && matrizAventureiros[2][3] > 0) {
												matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao44.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao44.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao44.load("res\\batalha\\coracaoVazio.png");} coracao34.setDx(coracao34.getDx() + comecarAnimacaoCoracao);
												if(coracao34.getDx() >= intervaloAnimacao && coracao34.getImagem() != null && matrizAventureiros[2][3] > 0) {
													matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao34.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao34.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao34.load("res\\batalha\\coracaoVazio.png");} coracao24.setDx(coracao24.getDx() + comecarAnimacaoCoracao);
													if(coracao24.getDx() >= intervaloAnimacao && coracao24.getImagem() != null && matrizAventureiros[2][3] > 0) {
														matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao24.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao24.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao24.load("res\\batalha\\coracaoVazio.png");} coracao14.setDx(coracao14.getDx() + comecarAnimacaoCoracao);
														if(coracao14.getDx() >= intervaloAnimacao && coracao14.getImagem() != null && matrizAventureiros[2][3] > 0) {
															matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao14.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao14.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao14.load("res\\batalha\\coracaoVazio.png");} zerarDx(); animacaoFileira=7;
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
				coracao14.setDx(coracao14.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
				
				gif= false; animacao.setDx(0);
				
				if(coracao14.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao14.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao14.load("res\\batalha\\apelo.png"); coracao24.setDx(coracao24.getDx() + comecarAnimacaoCoracao);
					if(coracao24.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao24.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao24.load("res\\batalha\\apelo.png");coracao34.setDx(coracao34.getDx() + comecarAnimacaoCoracao);
						if(coracao34.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao34.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao34.load("res\\batalha\\apelo.png");coracao44.setDx(coracao44.getDx() + comecarAnimacaoCoracao);
							if(coracao44.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao44.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao44.load("res\\batalha\\apelo.png");coracao54.setDx(coracao54.getDx() + comecarAnimacaoCoracao);
								if(coracao54.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao54.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao54.load("res\\batalha\\apelo.png");coracao64.setDx(coracao64.getDx() + comecarAnimacaoCoracao);
									if(coracao64.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao64.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao64.load("res\\batalha\\apelo.png");coracao74.setDx(coracao74.getDx() + comecarAnimacaoCoracao);
										if(coracao74.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao74.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao74.load("res\\batalha\\apelo.png");coracao84.setDx(coracao84.getDx() + comecarAnimacaoCoracao);
											if(coracao84.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao84.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao84.load("res\\batalha\\apelo.png");coracao94.setDx(coracao94.getDx() + comecarAnimacaoCoracao);
												if(coracao94.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao94.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao94.load("res\\batalha\\apelo.png");coracao104.setDx(coracao104.getDx() + comecarAnimacaoCoracao);
													if(coracao104.getDx() >= intervaloAnimacao && matrizAventureiros[2][3] > 0) {matrizAventureiros[2][3] = matrizAventureiros[2][3] - (coracao104.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao104.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
													}if(matrizAventureiros[2][3] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(3);} else if(efeitoChefeDeFase[3] == 1) {efeitoChefeDeFase(3);} else{interferirNosAdversarios(matrizAventureiros[3][3], matrizAventureiros[4][3], 3); zerarDx();}}
			}
				
		}else if(animacaoFileira == 8 || animacaoFileira == 9 ) {
			if(animacaoFileira == 8) {
				
				if(efeitoChefeDeFase[vezDoAventureiro] == 3 || apeloRepetido[vezDoAventureiro] == 3) {
					coracao105.setDx(intervaloAnimacao);
				}else {
					
					TerminouLoop =0;
					
					if(matrizAventureiros[4][vezDoAventureiro] == 4 && animacao.getDx() == 0) {Efeito4 ++;}
					
					
					animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
					
					if(animacao.getDx() >= 20 && (matrizAventureiros[4][vezDoAventureiro] == 4 ? Efeito4 == 1 : true)) {
						gifApresentacao(matrizAventureiros[0][4], 4 != posicaoAventureiro ? arrayAleatorio[4] : contTecla);
					}
					
					if(animacao.getDx() >= intervaloAnimacaoGif || (matrizAventureiros[4][vezDoAventureiro] == 4 && Efeito4 == 3)) {
						coracao105.setDx(coracao105.getDx() + comecarAnimacaoCoracao);
						animacao.load("res\\batalha\\animacao.png");
						if(Efeito4 ==1) {Efeito4 ++;}
						
						if(matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 0 && animacao.getDx() >= intervaloAnimacaoGif) {
							interferirNosAdversarios(matrizAventureiros[3][vezDoAventureiro], matrizAventureiros[4][vezDoAventureiro], vezDoAventureiro);	
						}
					}
				}
					
				if((matrizAventureiros[4][vezDoAventureiro] == 6 && Efeito6[matrizAventureiros[0][vezDoAventureiro] == 2 ? 0 : 1] == 1) || matrizAventureiros[4][vezDoAventureiro] != 6) {
					
					if(coracao105.getDx() >= intervaloAnimacao && coracao105.getImagem() != null && matrizAventureiros[2][4] > 0) {
						matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao105.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao105.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao105.load("res\\batalha\\coracaoVazio.png");} coracao95.setDx(coracao95.getDx() + comecarAnimacaoCoracao);
						if(coracao95.getDx() >= intervaloAnimacao && coracao95.getImagem() != null && matrizAventureiros[2][4] > 0) {
							matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao95.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao95.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao95.load("res\\batalha\\coracaoVazio.png");} coracao85.setDx(coracao85.getDx() + comecarAnimacaoCoracao);
							if(coracao85.getDx() >= intervaloAnimacao && coracao85.getImagem() != null && matrizAventureiros[2][4] > 0) {
								matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao85.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao85.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao85.load("res\\batalha\\coracaoVazio.png");} coracao75.setDx(coracao75.getDx() + comecarAnimacaoCoracao);
								if(coracao75.getDx() >= intervaloAnimacao && coracao75.getImagem() != null && matrizAventureiros[2][4] > 0) {
									matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao75.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao75.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao75.load("res\\batalha\\coracaoVazio.png");} coracao65.setDx(coracao65.getDx() + comecarAnimacaoCoracao);
									if(coracao65.getDx() >= intervaloAnimacao && coracao65.getImagem() != null && matrizAventureiros[2][4] > 0) {
										matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao65.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao65.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao65.load("res\\batalha\\coracaoVazio.png");} coracao55.setDx(coracao55.getDx() + comecarAnimacaoCoracao);
										if(coracao55.getDx() >= intervaloAnimacao && coracao55.getImagem() != null && matrizAventureiros[2][4] > 0) {
											matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao55.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao55.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao55.load("res\\batalha\\coracaoVazio.png");} coracao45.setDx(coracao45.getDx() + comecarAnimacaoCoracao);
											if(coracao45.getDx() >= intervaloAnimacao && coracao45.getImagem() != null && matrizAventureiros[2][4] > 0) {
												matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao45.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao45.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao45.load("res\\batalha\\coracaoVazio.png");} coracao35.setDx(coracao35.getDx() + comecarAnimacaoCoracao);
												if(coracao35.getDx() >= intervaloAnimacao && coracao35.getImagem() != null && matrizAventureiros[2][4] > 0) {
													matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao35.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao35.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao35.load("res\\batalha\\coracaoVazio.png");} coracao25.setDx(coracao25.getDx() + comecarAnimacaoCoracao);
													if(coracao25.getDx() >= intervaloAnimacao && coracao25.getImagem() != null && matrizAventureiros[2][4] > 0) {
														matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao25.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao25.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao25.load("res\\batalha\\coracaoVazio.png");} coracao15.setDx(coracao15.getDx() + comecarAnimacaoCoracao);
														if(coracao15.getDx() >= intervaloAnimacao && coracao15.getImagem() != null && matrizAventureiros[2][4] > 0) {
															matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao15.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 1 : 0); if(coracao15.getReferencia().toString() == "res\\batalha\\interferencia.png") {coracao15.load("res\\batalha\\coracaoVazio.png");} zerarDx(); animacaoFileira=9;
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
				coracao15.setDx(coracao15.getDx() + comecarAnimacaoCoracao);
				if(efeitoChefeDeFase[vezDoAventureiro] == 3) {efeitoChefeDeFase[vezDoAventureiro] = 0;}
				if(apeloRepetido[vezDoAventureiro] == 3) {apeloRepetido[vezDoAventureiro] = 0;}
				
				gif= false; animacao.setDx(0);
				
				if(coracao15.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao15.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao15.load("res\\batalha\\apelo.png"); coracao25.setDx(coracao25.getDx() + comecarAnimacaoCoracao);
					if(coracao25.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao25.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao25.load("res\\batalha\\apelo.png");coracao35.setDx(coracao35.getDx() + comecarAnimacaoCoracao);
						if(coracao35.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao35.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao35.load("res\\batalha\\apelo.png");coracao45.setDx(coracao45.getDx() + comecarAnimacaoCoracao);
							if(coracao45.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao45.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao45.load("res\\batalha\\apelo.png");coracao55.setDx(coracao55.getDx() + comecarAnimacaoCoracao);
								if(coracao55.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao55.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao55.load("res\\batalha\\apelo.png");coracao65.setDx(coracao65.getDx() + comecarAnimacaoCoracao);
									if(coracao65.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao65.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao65.load("res\\batalha\\apelo.png");coracao75.setDx(coracao75.getDx() + comecarAnimacaoCoracao);
										if(coracao75.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao75.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao75.load("res\\batalha\\apelo.png");coracao85.setDx(coracao85.getDx() + comecarAnimacaoCoracao);
											if(coracao85.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao85.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao85.load("res\\batalha\\apelo.png");coracao95.setDx(coracao95.getDx() + comecarAnimacaoCoracao);
												if(coracao95.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao95.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao95.load("res\\batalha\\apelo.png");coracao105.setDx(coracao105.getDx() + comecarAnimacaoCoracao);
													if(coracao105.getDx() >= intervaloAnimacao && matrizAventureiros[2][4] > 0) {matrizAventureiros[2][4] = matrizAventureiros[2][4] - (coracao105.getReferencia().toString() == "res\\batalha\\coracaoVazio.png" ? 1 : 0); coracao105.load("res\\batalha\\apelo.png"); if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
													}if(matrizAventureiros[2][4] == 0) { if(apeloRepetido[vezDoAventureiro] == 1 ) {apeloRepetido(4);} else if(efeitoChefeDeFase[4] == 1) {efeitoChefeDeFase(4);} else{interferirNosAdversarios(matrizAventureiros[3][4], matrizAventureiros[4][4], 4); zerarDx();}}
			}
		}
	}
	
	public void zerarDx() {
		coracao01.setDx(0);coracao02.setDx(0);coracao03.setDx(0);coracao04.setDx(0);coracao05.setDx(0);
		coracao101.setDx(0);coracao91.setDx(0);coracao81.setDx(0);coracao71.setDx(0);coracao61.setDx(0);
		coracao51.setDx(0);coracao41.setDx(0);coracao31.setDx(0);coracao21.setDx(0);coracao11.setDx(0);
		coracao102.setDx(0);coracao92.setDx(0);coracao82.setDx(0);coracao72.setDx(0);coracao62.setDx(0);
		coracao52.setDx(0);coracao42.setDx(0);coracao32.setDx(0);coracao22.setDx(0);coracao12.setDx(0);
		coracao103.setDx(0);coracao93.setDx(0);coracao83.setDx(0);coracao73.setDx(0);coracao63.setDx(0);
		coracao53.setDx(0);coracao43.setDx(0);coracao33.setDx(0);coracao23.setDx(0);coracao13.setDx(0);
		coracao104.setDx(0);coracao94.setDx(0);coracao84.setDx(0);coracao74.setDx(0);coracao64.setDx(0);
		coracao54.setDx(0);coracao44.setDx(0);coracao34.setDx(0);coracao24.setDx(0);coracao14.setDx(0);
		coracao105.setDx(0);coracao95.setDx(0);coracao85.setDx(0);coracao75.setDx(0);coracao65.setDx(0);
		coracao55.setDx(0);coracao45.setDx(0);coracao35.setDx(0);coracao25.setDx(0);coracao15.setDx(0);
		menos2Coracoes.setDx(0);
	}
	

	public void sumirCoracoes() {
		
		if((animacaoFileira == 10 || animacaoFileira == 11) && vezDoAventureiro != 5) {
			if(animacaoFileira == 10) {
				coracao101.setDx(coracao101.getDx() + comecarAnimacaoCoracao);
				
				if(coracao101.getDx() >= intervaloAnimacao && coracao101.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
					interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao101.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao101.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao101.load("res\\batalha\\coracaoVazio.png");} coracao91.setDx(coracao91.getDx() + comecarAnimacaoCoracao);
					if(coracao91.getDx() >= intervaloAnimacao && coracao91.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
						interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao91.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao91.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao91.load("res\\batalha\\coracaoVazio.png");} coracao81.setDx(coracao81.getDx() + comecarAnimacaoCoracao);
						if(coracao81.getDx() >= intervaloAnimacao && coracao81.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
							interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao81.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao81.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao81.load("res\\batalha\\coracaoVazio.png");} coracao71.setDx(coracao71.getDx() + comecarAnimacaoCoracao);
							if(coracao71.getDx() >= intervaloAnimacao && coracao71.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
								interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao71.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao71.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao71.load("res\\batalha\\coracaoVazio.png");} coracao61.setDx(coracao61.getDx() + comecarAnimacaoCoracao);
								if(coracao61.getDx() >= intervaloAnimacao && coracao61.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
									interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao61.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao61.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao61.load("res\\batalha\\coracaoVazio.png");} coracao51.setDx(coracao51.getDx() + comecarAnimacaoCoracao);
									if(coracao51.getDx() >= intervaloAnimacao && coracao51.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
										interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao51.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao51.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao51.load("res\\batalha\\coracaoVazio.png");}  coracao41.setDx(coracao41.getDx() + comecarAnimacaoCoracao);
										if(coracao41.getDx() >= intervaloAnimacao && coracao41.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
											interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao41.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao41.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao41.load("res\\batalha\\coracaoVazio.png");} coracao31.setDx(coracao31.getDx() + comecarAnimacaoCoracao);
											if(coracao31.getDx() >= intervaloAnimacao && coracao31.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
												interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao31.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao31.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao31.load("res\\batalha\\coracaoVazio.png");} coracao21.setDx(coracao21.getDx() + comecarAnimacaoCoracao);
												if(coracao21.getDx() >= intervaloAnimacao && coracao21.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
													interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao21.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao21.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao21.load("res\\batalha\\coracaoVazio.png");} coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
													if(coracao11.getDx() >= intervaloAnimacao && coracao11.getImagem() != null && interferenciasRecebidas[0][vezDoAventureiro] > 0) {
														interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao11.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao11.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao11.load("res\\batalha\\coracaoVazio.png");} zerarDx(); animacaoFileira=11;
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
				
				if(coracao11.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao11.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao11.load("res\\batalha\\interferencia.png"); coracao21.setDx(coracao21.getDx() + comecarAnimacaoCoracao);
					if(coracao21.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao21.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao21.load("res\\batalha\\interferencia.png");coracao31.setDx(coracao31.getDx() + comecarAnimacaoCoracao);
						if(coracao31.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao31.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao31.load("res\\batalha\\interferencia.png");coracao41.setDx(coracao41.getDx() + comecarAnimacaoCoracao);
							if(coracao41.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao41.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao41.load("res\\batalha\\interferencia.png");coracao51.setDx(coracao51.getDx() + comecarAnimacaoCoracao);
								if(coracao51.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao51.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao51.load("res\\batalha\\interferencia.png");coracao61.setDx(coracao61.getDx() + comecarAnimacaoCoracao);
									if(coracao61.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao61.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao61.load("res\\batalha\\interferencia.png");coracao71.setDx(coracao71.getDx() + comecarAnimacaoCoracao);
										if(coracao71.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao71.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao71.load("res\\batalha\\interferencia.png");coracao81.setDx(coracao81.getDx() + comecarAnimacaoCoracao);
											if(coracao81.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao81.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao81.load("res\\batalha\\interferencia.png");coracao91.setDx(coracao91.getDx() + comecarAnimacaoCoracao);
												if(coracao91.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao91.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao91.load("res\\batalha\\interferencia.png");coracao101.setDx(coracao101.getDx() + comecarAnimacaoCoracao);
													if(coracao101.getDx() >= intervaloAnimacao && interferenciasRecebidas[0][vezDoAventureiro] > 0 && TerminouLoop != 1) {interferenciasRecebidas[0][vezDoAventureiro] = interferenciasRecebidas[0][vezDoAventureiro] - (coracao101.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao101.load("res\\batalha\\interferencia.png");
																										animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[0][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[0] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 1;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
				
		}else if(animacaoFileira == 12 || animacaoFileira == 13 ) {
			if(animacaoFileira == 12) {
				coracao102.setDx(coracao102.getDx() + comecarAnimacaoCoracao);
				
				if(coracao102.getDx() >= intervaloAnimacao && coracao102.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
					interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao102.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao102.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao102.load("res\\batalha\\coracaoVazio.png");} coracao92.setDx(coracao92.getDx() + comecarAnimacaoCoracao);
					if(coracao92.getDx() >= intervaloAnimacao && coracao92.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
						interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao92.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao92.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao92.load("res\\batalha\\coracaoVazio.png");} coracao82.setDx(coracao82.getDx() + comecarAnimacaoCoracao);
						if(coracao82.getDx() >= intervaloAnimacao && coracao82.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
							interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao82.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao82.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao82.load("res\\batalha\\coracaoVazio.png");}  coracao72.setDx(coracao72.getDx() + comecarAnimacaoCoracao);
							if(coracao72.getDx() >= intervaloAnimacao && coracao72.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
								interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao72.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao72.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao72.load("res\\batalha\\coracaoVazio.png");} coracao62.setDx(coracao62.getDx() + comecarAnimacaoCoracao);
								if(coracao62.getDx() >= intervaloAnimacao && coracao62.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
									interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao62.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao62.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao62.load("res\\batalha\\coracaoVazio.png");} coracao52.setDx(coracao52.getDx() + comecarAnimacaoCoracao);
									if(coracao52.getDx() >= intervaloAnimacao && coracao52.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
										interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao52.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao52.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao52.load("res\\batalha\\coracaoVazio.png");}  coracao42.setDx(coracao42.getDx() + comecarAnimacaoCoracao);
										if(coracao42.getDx() >= intervaloAnimacao && coracao42.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
											interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao42.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao42.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao42.load("res\\batalha\\coracaoVazio.png");}  coracao32.setDx(coracao32.getDx() + comecarAnimacaoCoracao);
											if(coracao32.getDx() >= intervaloAnimacao && coracao32.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
												interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao32.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao32.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao32.load("res\\batalha\\coracaoVazio.png");} coracao22.setDx(coracao22.getDx() + comecarAnimacaoCoracao);
												if(coracao22.getDx() >= intervaloAnimacao && coracao22.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
													interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao22.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao22.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao22.load("res\\batalha\\coracaoVazio.png");} coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
													if(coracao12.getDx() >= intervaloAnimacao && coracao12.getImagem() != null && interferenciasRecebidas[1][vezDoAventureiro] > 0) {
														interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao12.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao12.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao12.load("res\\batalha\\coracaoVazio.png");} zerarDx(); animacaoFileira=13;
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
				coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
				
				if(coracao12.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao12.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao12.load("res\\batalha\\interferencia.png"); coracao22.setDx(coracao22.getDx() + comecarAnimacaoCoracao);
					if(coracao22.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao22.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao22.load("res\\batalha\\interferencia.png");coracao32.setDx(coracao32.getDx() + comecarAnimacaoCoracao);
						if(coracao32.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao32.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao32.load("res\\batalha\\interferencia.png");coracao42.setDx(coracao42.getDx() + comecarAnimacaoCoracao);
							if(coracao42.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao42.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao42.load("res\\batalha\\interferencia.png");coracao52.setDx(coracao52.getDx() + comecarAnimacaoCoracao);
								if(coracao52.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao52.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao52.load("res\\batalha\\interferencia.png");coracao62.setDx(coracao62.getDx() + comecarAnimacaoCoracao);
									if(coracao62.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao62.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao62.load("res\\batalha\\interferencia.png");coracao72.setDx(coracao72.getDx() + comecarAnimacaoCoracao);
										if(coracao72.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao72.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao72.load("res\\batalha\\interferencia.png");coracao82.setDx(coracao82.getDx() + comecarAnimacaoCoracao);
											if(coracao82.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao82.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao82.load("res\\batalha\\interferencia.png");coracao92.setDx(coracao92.getDx() + comecarAnimacaoCoracao);
												if(coracao92.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao92.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao92.load("res\\batalha\\interferencia.png");coracao102.setDx(coracao102.getDx() + comecarAnimacaoCoracao);
													if(coracao102.getDx() >= intervaloAnimacao && interferenciasRecebidas[1][vezDoAventureiro] > 0 && TerminouLoop != 1) {interferenciasRecebidas[1][vezDoAventureiro] = interferenciasRecebidas[1][vezDoAventureiro] - (coracao102.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao102.load("res\\batalha\\interferencia.png");animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[1][vezDoAventureiro] == 0 && TerminouLoop != 1) {animacaoEfeitoConcluido[1] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 3;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
				
		}else if(animacaoFileira == 14 || animacaoFileira == 15 ) {
			if(animacaoFileira == 14) {
				coracao103.setDx(coracao103.getDx() + comecarAnimacaoCoracao);
				
				if(coracao103.getDx() >= intervaloAnimacao && coracao103.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
					interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao103.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao103.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao103.load("res\\batalha\\coracaoVazio.png");} coracao93.setDx(coracao93.getDx() + comecarAnimacaoCoracao);
					if(coracao93.getDx() >= intervaloAnimacao && coracao93.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
						interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao93.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao93.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao93.load("res\\batalha\\coracaoVazio.png");} coracao83.setDx(coracao83.getDx() + comecarAnimacaoCoracao);
						if(coracao83.getDx() >= intervaloAnimacao && coracao83.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
							interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao83.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao83.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao83.load("res\\batalha\\coracaoVazio.png");}  coracao73.setDx(coracao73.getDx() + comecarAnimacaoCoracao);
							if(coracao73.getDx() >= intervaloAnimacao && coracao73.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
								interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao73.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao73.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao73.load("res\\batalha\\coracaoVazio.png");}  coracao63.setDx(coracao63.getDx() + comecarAnimacaoCoracao);
								if(coracao63.getDx() >= intervaloAnimacao && coracao63.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
									interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao63.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao63.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao63.load("res\\batalha\\coracaoVazio.png");} coracao53.setDx(coracao53.getDx() + comecarAnimacaoCoracao);
									if(coracao53.getDx() >= intervaloAnimacao && coracao53.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
										interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao53.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao53.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao53.load("res\\batalha\\coracaoVazio.png");} coracao43.setDx(coracao43.getDx() + comecarAnimacaoCoracao);
										if(coracao43.getDx() >= intervaloAnimacao && coracao43.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
											interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao43.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao43.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao43.load("res\\batalha\\coracaoVazio.png");}  coracao33.setDx(coracao33.getDx() + comecarAnimacaoCoracao);
											if(coracao33.getDx() >= intervaloAnimacao && coracao33.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
												interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao33.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao33.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao33.load("res\\batalha\\coracaoVazio.png");}  coracao23.setDx(coracao23.getDx() + comecarAnimacaoCoracao);
												if(coracao23.getDx() >= intervaloAnimacao && coracao23.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
													interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao23.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao23.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao23.load("res\\batalha\\coracaoVazio.png");} coracao13.setDx(coracao13.getDx() + comecarAnimacaoCoracao);
													if(coracao13.getDx() >= intervaloAnimacao && coracao13.getImagem() != null && interferenciasRecebidas[2][vezDoAventureiro] > 0) {
														interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao13.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao13.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao13.load("res\\batalha\\coracaoVazio.png");}  zerarDx(); animacaoFileira=15;
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
				coracao13.setDx(coracao13.getDx() + comecarAnimacaoCoracao);
				
				if(coracao13.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao13.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao13.load("res\\batalha\\interferencia.png"); coracao23.setDx(coracao23.getDx() + comecarAnimacaoCoracao);
					if(coracao23.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao23.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao23.load("res\\batalha\\interferencia.png");coracao33.setDx(coracao33.getDx() + comecarAnimacaoCoracao);
						if(coracao33.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao33.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao33.load("res\\batalha\\interferencia.png");coracao43.setDx(coracao43.getDx() + comecarAnimacaoCoracao);
							if(coracao43.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao43.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao43.load("res\\batalha\\interferencia.png");coracao53.setDx(coracao53.getDx() + comecarAnimacaoCoracao);
								if(coracao53.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao53.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao53.load("res\\batalha\\interferencia.png");coracao63.setDx(coracao63.getDx() + comecarAnimacaoCoracao);
									if(coracao63.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao63.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao63.load("res\\batalha\\interferencia.png");coracao73.setDx(coracao73.getDx() + comecarAnimacaoCoracao);
										if(coracao73.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao73.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao73.load("res\\batalha\\interferencia.png");coracao83.setDx(coracao83.getDx() + comecarAnimacaoCoracao);
											if(coracao83.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao83.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao83.load("res\\batalha\\interferencia.png");coracao93.setDx(coracao93.getDx() + comecarAnimacaoCoracao);
												if(coracao93.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao93.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao93.load("res\\batalha\\interferencia.png");coracao103.setDx(coracao103.getDx() + comecarAnimacaoCoracao);
													if(coracao103.getDx() >= intervaloAnimacao && interferenciasRecebidas[2][vezDoAventureiro] > 0 && TerminouLoop != 1) {interferenciasRecebidas[2][vezDoAventureiro] = interferenciasRecebidas[2][vezDoAventureiro] - (coracao103.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao103.load("res\\batalha\\interferencia.png");animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[2][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[2] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 5;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
				
		}else if(animacaoFileira == 16 || animacaoFileira == 17 ) {
			if(animacaoFileira == 16) {
				coracao104.setDx(coracao104.getDx() + comecarAnimacaoCoracao);
				
				if(coracao104.getDx() >= intervaloAnimacao && coracao104.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
					interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao104.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao104.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao104.load("res\\batalha\\coracaoVazio.png");}  coracao94.setDx(coracao94.getDx() + comecarAnimacaoCoracao);
					if(coracao94.getDx() >= intervaloAnimacao && coracao94.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
						interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao94.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao94.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao94.load("res\\batalha\\coracaoVazio.png");} coracao84.setDx(coracao84.getDx() + comecarAnimacaoCoracao);
						if(coracao84.getDx() >= intervaloAnimacao && coracao84.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
							interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao84.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao84.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao84.load("res\\batalha\\coracaoVazio.png");} coracao74.setDx(coracao74.getDx() + comecarAnimacaoCoracao);
							if(coracao74.getDx() >= intervaloAnimacao && coracao74.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
								interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao74.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao74.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao74.load("res\\batalha\\coracaoVazio.png");}  coracao64.setDx(coracao64.getDx() + comecarAnimacaoCoracao);
								if(coracao64.getDx() >= intervaloAnimacao && coracao64.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
									interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao64.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao64.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao64.load("res\\batalha\\coracaoVazio.png");} coracao54.setDx(coracao54.getDx() + comecarAnimacaoCoracao);
									if(coracao54.getDx() >= intervaloAnimacao && coracao54.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
										interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao54.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao54.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao54.load("res\\batalha\\coracaoVazio.png");}  coracao44.setDx(coracao44.getDx() + comecarAnimacaoCoracao);
										if(coracao44.getDx() >= intervaloAnimacao && coracao44.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
											interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao44.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao44.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao44.load("res\\batalha\\coracaoVazio.png");}  coracao34.setDx(coracao34.getDx() + comecarAnimacaoCoracao);
											if(coracao34.getDx() >= intervaloAnimacao && coracao34.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
												interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao34.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao34.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao34.load("res\\batalha\\coracaoVazio.png");} coracao24.setDx(coracao24.getDx() + comecarAnimacaoCoracao);
												if(coracao24.getDx() >= intervaloAnimacao && coracao24.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
													interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao24.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao24.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao24.load("res\\batalha\\coracaoVazio.png");} coracao14.setDx(coracao14.getDx() + comecarAnimacaoCoracao);
													if(coracao14.getDx() >= intervaloAnimacao && coracao14.getImagem() != null && interferenciasRecebidas[3][vezDoAventureiro] > 0) {
														interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao14.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao14.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao14.load("res\\batalha\\coracaoVazio.png");} zerarDx(); animacaoFileira=17;
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
				coracao14.setDx(coracao14.getDx() + comecarAnimacaoCoracao);
				
				if(coracao14.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao14.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao14.load("res\\batalha\\interferencia.png"); coracao24.setDx(coracao24.getDx() + comecarAnimacaoCoracao);
					if(coracao24.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao24.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao24.load("res\\batalha\\interferencia.png");coracao34.setDx(coracao34.getDx() + comecarAnimacaoCoracao);
						if(coracao34.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao34.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao34.load("res\\batalha\\interferencia.png");coracao44.setDx(coracao44.getDx() + comecarAnimacaoCoracao);
							if(coracao44.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao44.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao44.load("res\\batalha\\interferencia.png");coracao54.setDx(coracao54.getDx() + comecarAnimacaoCoracao);
								if(coracao54.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao54.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao54.load("res\\batalha\\interferencia.png");coracao64.setDx(coracao64.getDx() + comecarAnimacaoCoracao);
									if(coracao64.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao64.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao64.load("res\\batalha\\interferencia.png");coracao74.setDx(coracao74.getDx() + comecarAnimacaoCoracao);
										if(coracao74.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao74.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao74.load("res\\batalha\\interferencia.png");coracao84.setDx(coracao84.getDx() + comecarAnimacaoCoracao);
											if(coracao84.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao84.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao84.load("res\\batalha\\interferencia.png");coracao94.setDx(coracao94.getDx() + comecarAnimacaoCoracao);
												if(coracao94.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao94.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao94.load("res\\batalha\\interferencia.png");coracao104.setDx(coracao104.getDx() + comecarAnimacaoCoracao);
													if(coracao104.getDx() >= intervaloAnimacao && interferenciasRecebidas[3][vezDoAventureiro] > 0 && TerminouLoop != 1) {interferenciasRecebidas[3][vezDoAventureiro] = interferenciasRecebidas[3][vezDoAventureiro] - (coracao104.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao104.load("res\\batalha\\interferencia.png");animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[3][vezDoAventureiro] == 0 && TerminouLoop != 1) { animacaoEfeitoConcluido[3] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 7;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
				
		}else if(animacaoFileira == 18 || animacaoFileira == 19 ) {
			if(animacaoFileira == 18) {
				coracao105.setDx(coracao105.getDx() + comecarAnimacaoCoracao);
				
				if(coracao105.getDx() >= intervaloAnimacao && coracao105.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
					interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao105.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao105.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao105.load("res\\batalha\\coracaoVazio.png");} coracao95.setDx(coracao95.getDx() + comecarAnimacaoCoracao);
					if(coracao95.getDx() >= intervaloAnimacao && coracao95.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
						interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao95.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao95.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao95.load("res\\batalha\\coracaoVazio.png");} coracao85.setDx(coracao85.getDx() + comecarAnimacaoCoracao);
						if(coracao85.getDx() >= intervaloAnimacao && coracao85.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
							interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao85.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao85.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao85.load("res\\batalha\\coracaoVazio.png");} coracao75.setDx(coracao75.getDx() + comecarAnimacaoCoracao);
							if(coracao75.getDx() >= intervaloAnimacao && coracao75.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
								interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao75.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao75.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao75.load("res\\batalha\\coracaoVazio.png");} coracao65.setDx(coracao65.getDx() + comecarAnimacaoCoracao);
								if(coracao65.getDx() >= intervaloAnimacao && coracao65.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
									interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao65.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao65.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao65.load("res\\batalha\\coracaoVazio.png");} coracao55.setDx(coracao55.getDx() + comecarAnimacaoCoracao);
									if(coracao55.getDx() >= intervaloAnimacao && coracao55.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
										interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao55.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao55.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao55.load("res\\batalha\\coracaoVazio.png");}  coracao45.setDx(coracao45.getDx() + comecarAnimacaoCoracao);
										if(coracao45.getDx() >= intervaloAnimacao && coracao45.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
											interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao45.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao45.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao45.load("res\\batalha\\coracaoVazio.png");}  coracao35.setDx(coracao35.getDx() + comecarAnimacaoCoracao);
											if(coracao35.getDx() >= intervaloAnimacao && coracao35.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
												interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao35.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao35.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao35.load("res\\batalha\\coracaoVazio.png");} coracao25.setDx(coracao25.getDx() + comecarAnimacaoCoracao);
												if(coracao25.getDx() >= intervaloAnimacao && coracao25.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
													interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao25.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0); if(coracao25.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao25.load("res\\batalha\\coracaoVazio.png");} coracao15.setDx(coracao15.getDx() + comecarAnimacaoCoracao);
													if(coracao15.getDx() >= intervaloAnimacao && coracao15.getImagem() != null && interferenciasRecebidas[4][vezDoAventureiro] > 0) {
														interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao15.getReferencia().toString() == "res\\batalha\\apelo.png" ? 1 : 0);if(coracao15.getReferencia().toString() != "res\\batalha\\interferencia.png") {coracao15.load("res\\batalha\\coracaoVazio.png");} zerarDx(); animacaoFileira=19;
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
				coracao15.setDx(coracao15.getDx() + comecarAnimacaoCoracao);
				
				if(coracao15.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao15.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao15.load("res\\batalha\\interferencia.png");coracao25.setDx(coracao25.getDx() + comecarAnimacaoCoracao);
					if(coracao25.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao25.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao25.load("res\\batalha\\interferencia.png");coracao35.setDx(coracao35.getDx() + comecarAnimacaoCoracao);
						if(coracao35.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao35.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao35.load("res\\batalha\\interferencia.png");coracao45.setDx(coracao45.getDx() + comecarAnimacaoCoracao);
							if(coracao45.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao45.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao45.load("res\\batalha\\interferencia.png");coracao55.setDx(coracao55.getDx() + comecarAnimacaoCoracao);
								if(coracao55.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao55.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao55.load("res\\batalha\\interferencia.png");coracao65.setDx(coracao65.getDx() + comecarAnimacaoCoracao);
									if(coracao65.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao65.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao65.load("res\\batalha\\interferencia.png");coracao75.setDx(coracao75.getDx() + comecarAnimacaoCoracao);
										if(coracao75.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao75.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao75.load("res\\batalha\\interferencia.png");coracao85.setDx(coracao85.getDx() + comecarAnimacaoCoracao);
											if(coracao85.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao85.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao85.load("res\\batalha\\interferencia.png");coracao95.setDx(coracao95.getDx() + comecarAnimacaoCoracao);
												if(coracao95.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao95.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao95.load("res\\batalha\\interferencia.png");coracao105.setDx(coracao105.getDx() + comecarAnimacaoCoracao);
													if(coracao105.getDx() >= intervaloAnimacao && interferenciasRecebidas[4][vezDoAventureiro] > 0 && TerminouLoop != 1) {interferenciasRecebidas[4][vezDoAventureiro] = interferenciasRecebidas[4][vezDoAventureiro] - (coracao105.getReferencia().toString() == "res\\batalha\\interferencia.png" ? 0 : 1); coracao105.load("res\\batalha\\interferencia.png");animacaoEfeitoConcluido[4] = true;  zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
												}if(interferenciasRecebidas[4][vezDoAventureiro] == 0 && TerminouLoop != 1 ) {animacaoEfeitoConcluido[4] = true; zerarDx(); if(apeloRepetido[vezDoAventureiro] == 3) {animacaoFileira = 9;} else{interferirNosAdversarios(valoresInterferencia[0], valoresInterferencia[1], valoresInterferencia[2]);}}
			}
		}
	}
	
	public void apeloRepetido(int posicaoAventu) {

		menos2Coracoes.setDx(menos2Coracoes.getDx() + comecarAnimacaoCoracao);
		
		if(menos2Coracoes.getDx() >= 20 && menos2Coracoes.getDx() < 150) {
			menos2Coracoes.load("res\\batalha\\menos2Coracoes.png");
			menos2Coracoes.setY(16 + (64 * posicaoAventu) + 20);
		}
		if(menos2Coracoes.getDx() == 150) {
			 
			menos2Coracoes.load("");
			valoresInterferencia[0] = matrizAventureiros[3][posicaoAventu]; valoresInterferencia[1] = matrizAventureiros[4][posicaoAventu]; valoresInterferencia[2] = posicaoAventu;
			
			TerminouLoop =0;
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
	
	public void efeitoChefeDeFase (int posicaoAventu) {

		if((adversario == 0 && matrizAventureiros[4][posicaoAventu] != -1) || (adversario == 1 && matrizAventureiros[4][posicaoAventu] == -1) || (adversario == 2 && matrizAventureiros[7][posicaoAventu] == 1) || (adversario == 3 && matrizAventureiros[4][posicaoAventu] != -1) || (adversario == 4 && matrizAventureiros[4][posicaoAventu] == -1)) {

			int efeitoBoss = (adversario == 0 || adversario == 1? -1 : 1);
			
			menos2Coracoes.setDx(menos2Coracoes.getDx() + comecarAnimacaoCoracao);
			
			if(menos2Coracoes.getDx() >= 20 && menos2Coracoes.getDx() < 150) {
				menos2Coracoes.load("res\\batalha\\" + (efeitoBoss == -1 ? "menos1Coracao" : "mais1Coracao") + ".png");
				menos2Coracoes.setY(16 + (64 * posicaoAventu) + 20);
				
			}
			if(menos2Coracoes.getDx() == 150) {

				menos2Coracoes.load("");
				valoresInterferencia[0] = matrizAventureiros[3][posicaoAventu]; valoresInterferencia[1] = matrizAventureiros[4][posicaoAventu]; valoresInterferencia[2] = posicaoAventu;
				
					
				efeitoChefeDeFase[posicaoAventu] = 3;
				zerarDx();
				TerminouLoop =0;
				
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
	
	public void mexerCoracoes() {
		
		if(animacaoFileira == 20) {
			
			coracao01.setDx(coracao01.getDx() + comecarAnimacaoCoracao);
			
			if(coracao01.getDx() >= intervaloAnimacao) {
				coracao01.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][0] < 0? 0 : 4 * matrizAventureiros[1][0]));
				coracao02.setDx(coracao02.getDx() + comecarAnimacaoCoracao);
				if(coracao02.getDx() >= intervaloAnimacao) {
					coracao02.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][1] < 0? 0 : 4 * matrizAventureiros[1][1]));
					coracao03.setDx(coracao03.getDx() + comecarAnimacaoCoracao);
					if(coracao03.getDx() >= intervaloAnimacao) {
						coracao03.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][2] < 0? 0 : 4 * matrizAventureiros[1][2]));
						coracao04.setDx(coracao04.getDx() + comecarAnimacaoCoracao);
						if(coracao04.getDx() >= intervaloAnimacao) {
							coracao04.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][3] < 0? 0 : 4 * matrizAventureiros[1][3]));
							coracao05.setDx(coracao05.getDx() + comecarAnimacaoCoracao);
							if(coracao05.getDx() >= intervaloAnimacao) {
								coracao05.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][4] < 0? 0 : 4 * matrizAventureiros[1][4]));
								animacaoFileira = 21; zerarDx();
								
							}}}}}
		}
		System.out.println("total real: " + matrizAventureiros[1][0] + ", " + matrizAventureiros[1][1] + ", " + matrizAventureiros[1][2] + ", " + matrizAventureiros[1][3] + ", " + matrizAventureiros[1][4] + "\n");
	}
	
	
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
			coracao01.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][0] < 0? 0 : 4 * matrizAventureiros[1][0]));
			coracao02.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][1] < 0? 0 : 4 * matrizAventureiros[1][1]));
			coracao03.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][2] < 0? 0 : 4 * matrizAventureiros[1][2]));
			coracao04.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][3] < 0? 0 : 4 * matrizAventureiros[1][3]));
			coracao05.setX(16 + 676 + 4 + 85 + (4 * matrizAventureiros[1][4] < 0? 0 : 4 * matrizAventureiros[1][4]));
			
			System.out.println("total  5 : " + matrizAventureiros[5][0] + " " + matrizAventureiros[5][1] + " " + matrizAventureiros[5][2] + " " + matrizAventureiros[5][3] + " " + matrizAventureiros[5][4]);

			posicaoAventureiro = (matrizAventureiros[0][0] == aventureiro ? 0 : (matrizAventureiros[0][1] == aventureiro ? 1 : (matrizAventureiros[0][2] == aventureiro ? 2 : (matrizAventureiros[0][3] == aventureiro ? 3 : 4)))); comecarAnimacaoCoracao = 0;
			matrizAventureiros[5][0] = 0; matrizAventureiros[5][1] = 0; matrizAventureiros[5][2] = 0; matrizAventureiros[5][3] = 0; matrizAventureiros[5][4] = 0;
			danoEfeito4[0]=0; danoEfeito4[1]=0; danoEfeito4[2]=0; danoEfeito4[3]=0; danoEfeito4[4]=0;
			System.out.println("total  5 : " + matrizAventureiros[5][0] + " " + matrizAventureiros[5][1] + " " + matrizAventureiros[5][2] + " " + matrizAventureiros[5][3] + " " + matrizAventureiros[5][4]);

			zerarDx();
			
			repintarCampoBatalha();
			
			if(contZ == 5) {
				vezDoAventureiro=0;
				animacaoFileira = 22;
				
			} else {
				animacaoFileira = -1;
				vezDoAventureiro=0;
			}
		}
		
		if(mudarEscolha == false) {
			mudarEscolha = true;
			
			nomeHabilidade2.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (contTecla == 1 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade3.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (contTecla == 2 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade4.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + (contTecla == 3 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade1.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\nomeHabilidadeSelecionado.png");
			nomeApelo1.setCorTexto((contTecla == 0 ? Color.GRAY : Color.BLACK));
			nomeApelo2.setCorTexto((contTecla == 1 ? Color.GRAY : Color.BLACK));
			nomeApelo3.setCorTexto((contTecla == 2 ? Color.GRAY : Color.BLACK));
			nomeApelo4.setCorTexto((contTecla == 3 ? Color.GRAY : Color.BLACK));
			
			contTecla =0;
		}
		apelo.load("res\\batalha\\" + nomeAventureiro[aventureiro] + "\\" + apelosEInterferencias[aventureiro][0] + ".png");
		
		textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
		textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
		textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
		textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
		
	}
	
	public void vencedor() {
		
		comecarAnimacaoCoracao =1;
		coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
		
		if(coracao11.getDx() >= intervaloAnimacao * 5) {
			
			vencedor.load("res\\batalha\\" + nomeAventureiro[matrizAventureiros[0][0]] + (matrizAventureiros[0][0] == aventureiro ? "\\vencedor.png" : "\\perdedor.png") );
			
			coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
			if(coracao11.getDx() >= intervaloAnimacao * 5) {
				zerarDx();
				comecarAnimacaoCoracao =0;
				contZ =6;
			}
				
		}
	}
	
}
