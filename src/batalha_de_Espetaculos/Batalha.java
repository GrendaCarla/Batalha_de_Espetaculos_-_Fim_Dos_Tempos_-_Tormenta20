package batalha_de_Espetaculos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.Random;

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
import batalha_de_Espetaculos.Modelo.Icones_interativos;
import batalha_de_Espetaculos.Modelo.Texto;

public class Batalha extends JPanel implements ActionListener {
	
	/* ---------------------------------------------------------------------------------------- \
	|  Batalha ? a tela a onde o jogo acontece.													|
	\ ---------------------------------------------------------------------------------------- */
	
	private Escolha_de_adversario tela2;
	private Manual telaManual;
	private Menu telaMenu;
	private Creditos telaCreditos;
	
	JFrame janelaPrincipal;
	
	// ------------------------------------ fundo contorno --------------------------------------

	private Icones_interativos fundo = new Icones_interativos(0, 0);
	private Icones_interativos tapaResto = new Icones_interativos(1234/2 - 4936/2, 640/2 - 2560/2);

	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1120, -12);
	private Icones_interativos contorno = new Icones_interativos(0, 0);
		
	private int contEngranagem1 = 1;
	private boolean contEngranagem2;
	
	private String caminho; 
	
	private Timer timer;

	private int tamanhoContorno = 20;
	
	private Icones_interativos parabenizacaoVencedor = new Icones_interativos(0, 0);
	private Icones_interativos itemParabenizacaoVencedor1 = new Icones_interativos(0, -380);
	private Icones_interativos itemParabenizacaoVencedor2 = new Icones_interativos(0, 0);
	private Icones_interativos itemParabenizacaoVencedor3 = new Icones_interativos(0, 0);

	private boolean aparecerVencedor = false;
	private int contVencedor = 0;
	private int contFogos = 0;
	boolean mudaMontanhas = false;

	private int contTempoApelo = 0;
	private int contTempoInter = 10;
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(18 + 200/2 - 128/2, 80);
	private Icones_interativos bntManual = new Icones_interativos(bntMenu.getX(), bntMenu.getY() + 115);
	private Icones_interativos bntCreditos = new Icones_interativos(bntMenu.getX(), bntManual.getY() + 115);
	private Icones_interativos bntVoltar = new Icones_interativos(bntMenu.getX(), bntCreditos.getY() + 115);

	private boolean mostrarMenu = false;
	private int contMenu = 0;
	private Salvar salvar = new Salvar();
	
	// ------------------------ imagens e textos do di?logo de aviso ------------------------------

	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 110, dialogoAviso.getY() + 180);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(bntSimDialogoAviso.getX() + 370, bntSimDialogoAviso.getY());
	
	private Texto txtDialogoAviso = new Texto(dialogoAviso.getX() + 110, 548/2 - 16 - 40, " ");
	private Texto txtDialogoAviso2 = new Texto(dialogoAviso.getX() + 250, 548/2 + 40 - 40, " ");
	
	private Boolean bntSimNaoDialgoAviso = true;
	
	// ------------------------ informa??es dos aventureiros ------------------------------
	
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
	
	private int [][] gifApelos = {ignis.getGifApelos(), ayla.getGifApelos(), rexthor.getGifApelos(), kiki.getGifApelos(), arius.getGifApelos()};
	private String [][] NomeApelos = {ignis.getNomeApelos(), ayla.getNomeApelos(), rexthor.getNomeApelos(), kiki.getNomeApelos(), arius.getNomeApelos()};
	private String [][] ConteudoDescricao = {ignis.getConteudoDescricao(0), ignis.getConteudoDescricao(1), ignis.getConteudoDescricao(2), ignis.getConteudoDescricao(3), 
											 ayla.getConteudoDescricao(0), ayla.getConteudoDescricao(1), ayla.getConteudoDescricao(2), ayla.getConteudoDescricao(3), 
											 rexthor.getConteudoDescricao(0), rexthor.getConteudoDescricao(1), rexthor.getConteudoDescricao(2), rexthor.getConteudoDescricao(3),
											 kiki.getConteudoDescricao(0), kiki.getConteudoDescricao(1), kiki.getConteudoDescricao(2), kiki.getConteudoDescricao(3),
											arius.getConteudoDescricao(0), arius.getConteudoDescricao(1), arius.getConteudoDescricao(2), arius.getConteudoDescricao(3)};

	private int [] ordemAventVerdadeira = {0, 0, 0, 0, 0};
	private int [] ordemAventRodada = {0, 0, 0, 0, 0};
	private int [] valorApelo = {0, 0, 0, 0, 0};
	private int [] valorInterferencia = {0, 0, 0, 0, 0};
	private int [] tipoInterferencia = {-1, -1, -1, -1, -1};
	private int [] tipoAtaque = {0, 0, 0, 0, 0};
	//quantidade interfer?ncia q recebem de cada um  ignis             ayla            rexthor           kiki            arius
	private int [][] interferenciasRecebidas = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
	private int [] pontosRodada = {0, 0, 0, 0, 0};
	private int [] pontosAtuaisDaRodada = {0, 0, 0, 0, 0};
	//										ignis              ayla            rexthor           kiki            arius
	private int [][] numeroDosAtaques = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
	private int [] pontosTotais = {0, 0, 0, 0, 0};
	
	private int contEtapasBatalha = 0; // conta em que rodada esta > come?a com 1 e vai ate o 5
	
	
	private int aventureiro;
	private int adversario;
	private int posicaoAventureiro;
	int vezDoAventureiro = 0;
	
	
	private int [] efeitoChefeDeFase = {0, 0, 0, 0, 0}; // 3 estados, 0 entrou no pos1,1 entrou no else , 2 ta la mas nao pode entrar
	private int [] efeitoApeloRepetido = {0, 0, 0, 0, 0}; // 3 estados, 0 entrou no pos1,1 entrou no else , 2 ta la mas nao pode entrar
	private int [] efeitoInterferencias = {0, 0, 0, 0, 0}; // 3 estados, 0 entrou no pos1,1 entrou no else , 2 ta la mas nao pode entrar

	private Random aleatorioHabAdver = new Random();
	private int [] arrayAleatorioHabAdver = {aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4)};

	// ------------------------ divis?es da tela de batalha -------------------------
	
	private Icones_interativos animacao = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
	
	private Icones_interativos campoBatalha1 = new Icones_interativos(tamanhoContorno + 760 + 4, tamanhoContorno + 2 + 4);
	private Icones_interativos campoBatalha2 = new Icones_interativos(campoBatalha1.getX(), campoBatalha1.getY() + 70 + 4);
	private Icones_interativos campoBatalha3 = new Icones_interativos(campoBatalha1.getX(), campoBatalha2.getY() + 70 + 4);
	private Icones_interativos campoBatalha4 = new Icones_interativos(campoBatalha1.getX(), campoBatalha3.getY() + 70 + 4);
	private Icones_interativos campoBatalha5 = new Icones_interativos(campoBatalha1.getX(), campoBatalha4.getY() + 70 + 4);
		
	private Icones_interativos nomeHabilidade1 = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2 + 366 + 6);
	private Icones_interativos nomeHabilidade2 = new Icones_interativos(tamanhoContorno, nomeHabilidade1.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade3 = new Icones_interativos(tamanhoContorno, nomeHabilidade2.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade4 = new Icones_interativos(tamanhoContorno, nomeHabilidade3.getY() + 54 + 4);
	
	private Icones_interativos apelo = new Icones_interativos(nomeHabilidade1.getX() + 330 + 4, tamanhoContorno + 2 + 366 + 6);
	
	private Icones_interativos descricao = new Icones_interativos(apelo.getX(), apelo.getY() + 54);
		
	// ------------------------------------------- anima??o ----------------------------------------

	private Icones_interativos animacaoObj1 = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
	private Icones_interativos animacaoObj2 = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
	
	private Icones_interativos animacaoObj3 = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
	private Icones_interativos animacaoObj4 = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
	
	private int intervaloAnimacao = 6; //10
	private int intervaloAnimacaoGif = 140; //240
	
	private int comecarAnimacaoCoracao = 0;
	private int animacaoFileira = -1;
	
	private int contMovimentoAyla = 0;

	// --------------------------------- campo batalha e habilidades usadas -----------------------------------------
	
	private Texto txtEfeitoFase = new Texto(tamanhoContorno + 700, campoBatalha1.getY() + 70/2 + 7, " ");
	private Icones_interativos efeitoFase = new Icones_interativos(txtEfeitoFase.getX() + 30, txtEfeitoFase.getY() - 17);
	
	private Icones_interativos iconeCampoBatalha1 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha1.getY() + 3);
	private Icones_interativos iconeCampoBatalha2 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha2.getY() + 3);
	private Icones_interativos iconeCampoBatalha3 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha3.getY() + 3);
	private Icones_interativos iconeCampoBatalha4 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha4.getY() + 3);
	private Icones_interativos iconeCampoBatalha5 = new Icones_interativos(campoBatalha1.getX() + 25, campoBatalha5.getY() + 3);
		
	// cora??es que mede o total de apelo e interfer?ncia de todas as rodadas 
	private Icones_interativos coracao01 = new Icones_interativos(campoBatalha1.getX() + 116, campoBatalha1.getY() + 70/2);
	private Icones_interativos coracao02 = new Icones_interativos(campoBatalha2.getX() + 116, campoBatalha2.getY() + 70/2);
	private Icones_interativos coracao03 = new Icones_interativos(campoBatalha3.getX() + 116, campoBatalha3.getY() + 70/2);
	private Icones_interativos coracao04 = new Icones_interativos(campoBatalha4.getX() + 116, campoBatalha4.getY() + 70/2);
	private Icones_interativos coracao05 = new Icones_interativos(campoBatalha5.getX() + 116, campoBatalha5.getY() + 70/2);
	
	// cora??es que mede o apelo e interfer?ncia da rodada
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
	
	private Icones_interativos painel4 = new Icones_interativos(campoBatalha1.getX() - 8, campoBatalha1.getY() - 4);
	
	private Icones_interativos luzCampoBatalha1 = new Icones_interativos(campoBatalha1.getX() + 88, campoBatalha1.getY() + 12);
	private Icones_interativos luzCampoBatalha2 = new Icones_interativos(campoBatalha2.getX() + 88, campoBatalha2.getY() + 12);
	private Icones_interativos luzCampoBatalha3 = new Icones_interativos(campoBatalha3.getX() + 88, campoBatalha3.getY() + 12);
	private Icones_interativos luzCampoBatalha4 = new Icones_interativos(campoBatalha4.getX() + 88, campoBatalha4.getY() + 12);
	private Icones_interativos luzCampoBatalha5 = new Icones_interativos(campoBatalha5.getX() + 88, campoBatalha5.getY() + 12);

	// ----------------------- itens relacionado com nome habilidade -----------------------------------

	private Texto nomeApelo1, nomeApelo2, nomeApelo3, nomeApelo4;
	
	private boolean atualizarNomeHabili = false;
	private int selecaoNomeHab = 0;
	private int nomeHabAnterior = 4;
	
	private Icones_interativos painel1 = new Icones_interativos(tamanhoContorno - 2, tamanhoContorno + 2 + 365);
	
	// ----------------------- itens relacionado com Apelo -----------------------------------
	
	private Icones_interativos tipoDoApelo = new Icones_interativos(apelo.getX() + 19, apelo.getY() + 2);
	
	private Texto apeloQuantidade = new Texto(apelo.getX() + 90, apelo.getY() + 54/2 + 8, "Apelo:");
	
	private Icones_interativos apeloApelo1 = new Icones_interativos(apeloQuantidade.getX() + 66, apelo.getY() + 54/2 - 8);
	private Icones_interativos apeloApelo2 = new Icones_interativos(apeloApelo1.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo3 = new Icones_interativos(apeloApelo2.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo4 = new Icones_interativos(apeloApelo3.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo5 = new Icones_interativos(apeloApelo4.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo6 = new Icones_interativos(apeloApelo5.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo7 = new Icones_interativos(apeloApelo6.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo8 = new Icones_interativos(apeloApelo7.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo9 = new Icones_interativos(apeloApelo8.getX() + 27, apeloApelo1.getY());
	private Icones_interativos apeloApelo10 = new Icones_interativos(apeloApelo9.getX() + 27, apeloApelo1.getY());
	
	private Texto InterferenciaQuantidade = new Texto(apeloQuantidade.getX() + 348, apeloQuantidade.getY(), "Interferencia:");
	
	private Icones_interativos apeloInterf1 = new Icones_interativos(InterferenciaQuantidade.getX() + 127, apelo.getY() + 54/2 - 10);
	private Icones_interativos apeloInterf2 = new Icones_interativos(apeloInterf1.getX() + 27, apeloInterf1.getY());
	private Icones_interativos apeloInterf3 = new Icones_interativos(apeloInterf2.getX() + 27, apeloInterf1.getY());
	private Icones_interativos apeloInterf4 = new Icones_interativos(apeloInterf3.getX() + 27, apeloInterf1.getY());
	private Icones_interativos apeloInterf5 = new Icones_interativos(apeloInterf4.getX() + 27, apeloInterf1.getY());
	private Icones_interativos apeloInterf6 = new Icones_interativos(apeloInterf5.getX() + 27, apeloInterf1.getY());
	private Icones_interativos apeloInterf7 = new Icones_interativos(apeloInterf6.getX() + 27, apeloInterf1.getY());
	private Icones_interativos apeloInterf8 = new Icones_interativos(apeloInterf7.getX() + 27, apeloInterf1.getY());
	private Icones_interativos apeloInterf9 = new Icones_interativos(apeloInterf8.getX() + 27, apeloInterf1.getY());
	private Icones_interativos apeloInterf10 = new Icones_interativos(apeloInterf9.getX() + 27, apeloInterf1.getY());
	
	private Icones_interativos painel2 = new Icones_interativos(apelo.getX(), apelo.getY() - 2);
	
	// ----------------------- itens relacionado com Descri??o -----------------------------------

	private Texto textoDescricao1, textoDescricao2, textoDescricao3, textoDescricao4, textoDescricao5;
	
	private Icones_interativos painel3 = new Icones_interativos(descricao.getX(), descricao.getY());
	
	// ------------------------------------------- d20 ----------------------------------------
	//                            Rexthor           Arius
	private int [][] dados = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
	
	private Icones_interativos imgDado1 = new Icones_interativos(tamanhoContorno + 550, tamanhoContorno + 400);
	private Icones_interativos imgDado2 = new Icones_interativos(tamanhoContorno + 640, tamanhoContorno + 270);
	private Icones_interativos imgDado3 = new Icones_interativos(tamanhoContorno + 870, tamanhoContorno + 200);
	private Icones_interativos imgDado4 = new Icones_interativos(tamanhoContorno + 860, tamanhoContorno + 470);
	private Icones_interativos imgDado5 = new Icones_interativos(tamanhoContorno + 1050, tamanhoContorno + 330);
	
	// ---------------------------------- anima??o inicial --------------------------------
	
	private Icones_interativos fundoAnimacao = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
	private Icones_interativos iconeIgnis = new Icones_interativos(animacao.getX() -310, animacao.getY());
	private Icones_interativos iconeAyla = new Icones_interativos(animacao.getX(), animacao.getY() -310);
	private Icones_interativos iconeRexthor = new Icones_interativos(animacao.getX(), animacao.getY());
	private Icones_interativos iconeKiki = new Icones_interativos(animacao.getX(), animacao.getY() + 310);
	private Icones_interativos iconeArius = new Icones_interativos(animacao.getX() + 310, animacao.getY());
	
	private boolean comecarAnimacaoInicio = false;
	private boolean animarAnimacao = false;
	private boolean inverterAnimacao = false;
		
	// ------------------------------------------------------------------
	
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7, tl8, tl15, tl16, tl17, tl19, tl20, tl21;
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informa??es iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	
	public Batalha(int numAventureiro, int numAdversario, Escolha_de_adversario PaginaAnterior, Menu PaginaMenu, boolean Engrenagem2, String Caminho) {
		
		this.tela2 = PaginaAnterior;
		aventureiro = numAventureiro;
		adversario = numAdversario;
		this.telaMenu = PaginaMenu;
		contEngranagem2 = Engrenagem2;
		this.caminho = Caminho;

		arrumarListaAventureiros();
		
		contorno.load(caminho + "res\\contorno.png");
		tapaResto.load(caminho + "res\\fundo2.png");
		fundo.load(caminho + "res\\fundo0.png");
		engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem1.png");
		
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
		
		animacao.setImagem(null);
		
		// ------------------------ textos do di?logo de aviso ------------------------------		
		
		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 27));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		
		// ------------------------ divis?es da tela de batalha -------------------------

		campoBatalha1.load(caminho + "res\\batalha\\campoBatalha.png");
		campoBatalha2.load(caminho + "res\\batalha\\campoBatalha.png");
		campoBatalha3.load(caminho + "res\\batalha\\campoBatalha.png");
		campoBatalha4.load(caminho + "res\\batalha\\campoBatalha.png");
		campoBatalha5.load(caminho + "res\\batalha\\campoBatalha.png");
		
		nomeHabilidade1.load(caminho + "res\\batalha\\" + nomeAventureiro[aventureiro]+ "\\nomeHabilidadeSelecionado.png");
		nomeHabilidade2.load(caminho + "res\\batalha\\nomeHabilidade.png");
		nomeHabilidade3.load(caminho + "res\\batalha\\nomeHabilidade.png");
		nomeHabilidade4.load(caminho + "res\\batalha\\nomeHabilidade.png");
		
		apelo.load(caminho + "res\\batalha\\infoApelo.png");

		descricao.load(caminho + "res\\batalha\\descricao.png");
				
		// ---------------------- itens relacionados com campo de batalha --------------------------
		
		repintarCampoBatalha();
		
		luzCampoBatalha1.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha2.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha3.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha4.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		luzCampoBatalha5.load(caminho + "res\\batalha\\luzCampoBatalha1.png");
		
		apagarCoracoes();
		coracao01.load(caminho + "res\\batalha\\apelo.png");
		coracao02.load(caminho + "res\\batalha\\apelo.png");
		coracao03.load(caminho + "res\\batalha\\apelo.png");
		coracao04.load(caminho + "res\\batalha\\apelo.png");
		coracao05.load(caminho + "res\\batalha\\apelo.png");
		
		painel4.load(caminho + "res\\batalha\\painel4.png");
		
		txtEfeitoFase.setCorTexto(Color.white);
		
		// ----------------------- itens relacionado com nome habilidade -----------------------------------
		
		nomeApelo1 = new Texto(nomeHabilidade1.getX() + 28, nomeHabilidade1.getY() + 54/2 + 5, NomeApelos[aventureiro][0]);
		nomeApelo2 = new Texto(nomeHabilidade2.getX() + 28, nomeHabilidade2.getY() + 54/2 + 5, NomeApelos[aventureiro][1]);
		nomeApelo3 = new Texto(nomeHabilidade3.getX() + 28, nomeHabilidade3.getY() + 54/2 + 5, NomeApelos[aventureiro][2]);
		nomeApelo4 = new Texto(nomeHabilidade4.getX() + 28, nomeHabilidade4.getY() + 54/2 + 5, NomeApelos[aventureiro][3]);
		
		painel1.load(caminho + "res\\batalha\\painel1.png");
		
		nomeApelo1.setFonte(new Font("Arial", Font.PLAIN, 18));
		nomeApelo1.setCorTexto((aventureiro == 0 ? (new Color (255, 60, 0)) : (aventureiro == 1 ? (new Color (255, 0, 191)) : (aventureiro == 2 ? (new Color (0, 134, 255)) : (aventureiro == 3 ? (new Color (0, 255, 141)) : (new Color (255, 0, 38)))))));
		nomeApelo2.setCorTexto(new Color (235, 148, 150));
		nomeApelo3.setCorTexto(new Color (198, 96, 134));
		
		// ----------------------- itens relacionado com Apelo -----------------------------------
		
		for(int i=0; i<4; i++) {
			switch (aventureiro) {
				case 0:
					tipoAtaque[i] = apeloIgnis[3][i];
					break;
				case 1:
					tipoAtaque[i] = apeloAyla[3][i];
					break;
				case 2:
					tipoAtaque[i] = apeloRexthor[3][i];
					break;
				case 3:
					tipoAtaque[i] = apeloKiki[3][i];
					break;
				case 4:
					tipoAtaque[i] = apeloArius[3][i];
					break;
			}
		}
				
		apeloQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		apeloQuantidade.setCorTexto(new Color (240, 148, 150));

		painel2.load(caminho + "res\\batalha\\painel2.png");
		
		itensDoApelo();
		
		// ----------------------- itens relacionado com Descri??o -----------------------------------

		textoDescricao1 = new Texto(descricao.getX() + 20, descricao.getY() + 30, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
		textoDescricao2 = new Texto(textoDescricao1.getX(), textoDescricao1.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
		textoDescricao3 = new Texto(textoDescricao2.getX(), textoDescricao2.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
		textoDescricao4 = new Texto(textoDescricao3.getX(), textoDescricao3.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
		textoDescricao5 = new Texto(textoDescricao4.getX(), textoDescricao4.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][4]);

		textoDescricao1.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		textoDescricao1.setCorTexto(new Color (239, 182, 202));
		textoDescricao2.setCorTexto(textoDescricao1.getCorTexto());
		textoDescricao3.setCorTexto(textoDescricao1.getCorTexto());
		textoDescricao4.setCorTexto(textoDescricao1.getCorTexto()); 
		textoDescricao5.setCorTexto(new Color (165, 1, 67));
		
		painel3.load(caminho + "res\\batalha\\painel3.png");
		
		// ---------------------------------- anima??o inicial --------------------------------
		
		fundoAnimacao.load(caminho + "res\\batalha\\animacao\\fundo1.png");
		iconeIgnis.load(caminho + "res\\batalha\\animacao\\ignis1.png");		
		iconeAyla.load(caminho + "res\\batalha\\animacao\\ayla1.png");	
		iconeRexthor.load(caminho + "res\\batalha\\animacao\\rexthor1.png");
		iconeKiki.load(caminho + "res\\batalha\\animacao\\kiki1.png");	
		iconeArius.load(caminho + "res\\batalha\\animacao\\arius1.png");	
		iconeIgnis.setDx(0);
		comecarAnimacaoInicio = true;
		
		// ------------------------------------------------------------------------
		
		timer = new Timer(1, this);
		timer.start();
		
	}
	
	public void animarInicio() {
	
		iconeIgnis.setX(iconeIgnis.getX() + 10);
		iconeAyla.setY(iconeAyla.getY() + 10);
		iconeKiki.setY(iconeKiki.getY() - 10);
		iconeArius.setX(iconeArius.getX() - 10);
		
		if(iconeIgnis.getX() == animacao.getX()) {
			comecarAnimacaoInicio = false;
			animarAnimacao = true;
		}
	
	}
	
	public void animarAventureiros() {
		
		iconeIgnis.setDx(iconeIgnis.getDx() + (inverterAnimacao == false ? 1 : -1));
		
		if(iconeIgnis.getDx() == 0 || iconeIgnis.getDx() == 16 || iconeIgnis.getDx() == 32 || iconeIgnis.getDx() == 48) {
			iconeIgnis.load(caminho + "res\\batalha\\animacao\\ignis2.png");
		} else if(iconeIgnis.getDx() == 8 || iconeIgnis.getDx() == 24 || iconeIgnis.getDx() == 40) {
			iconeIgnis.load(caminho + "res\\batalha\\animacao\\ignis3.png");
		} else if(iconeIgnis.getDx() == 56) {
			iconeIgnis.load(caminho + "res\\batalha\\animacao\\ignis1.png");
		}
		
		if(iconeIgnis.getDx() == 64 || iconeIgnis.getDx() == 0) {
			inverterAnimacao = !inverterAnimacao;
		}
		
		//--------------------------------------------------------------------------------
		
		iconeKiki.setDx(iconeKiki.getDx() + 1);
		
		if(iconeKiki.getDx() % 6 == 0) {
			iconeKiki.load(caminho + "res\\batalha\\animacao\\kiki1.png");
		} else if(iconeKiki.getDx() % 3 == 0) {
			iconeKiki.load(caminho + "res\\batalha\\animacao\\kiki2.png");
		}
		
		//--------------------------------------------------------------------------------
		
		iconeArius.setDx(iconeArius.getDx() + 1);
		
		if(iconeArius.getDx() % 18 == 0) {
			iconeArius.load(caminho + "res\\batalha\\animacao\\arius1.png");
			iconeRexthor.load(caminho + "res\\batalha\\animacao\\rexthor1.png");
		} else if(iconeArius.getDx() % 9 == 0) {
			iconeArius.load(caminho + "res\\batalha\\animacao\\arius2.png");
			iconeRexthor.load(caminho + "res\\batalha\\animacao\\rexthor2.png");
		}
		
		//--------------------------------------------------------------------------------
		
		iconeAyla.setDx(iconeAyla.getDx() + 1);
		
		if(iconeAyla.getDx() == 4 || iconeAyla.getDx() == 8 || iconeAyla.getDx() == 12) {
			iconeAyla.setY(iconeAyla.getY() + 4);
			
		} else if(iconeAyla.getDx() == 16 || iconeAyla.getDx() == 20 || iconeAyla.getDx() == 24) {
			iconeAyla.setY(iconeAyla.getY() - 4);
			
		}
		
		if(iconeAyla.getDx() % 12 == 0 ) {
			iconeAyla.load(caminho + "res\\batalha\\animacao\\ayla1.png");
		} else if(iconeAyla.getDx() % 6 == 0) {
			iconeAyla.load(caminho + "res\\batalha\\animacao\\ayla2.png");
		} 
		
		if(iconeAyla.getDx() == 24) {
			iconeAyla.setDx(0);
		}
		
		
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						apaga os apelos adquiridos no campo de batalha						|
	\ ---------------------------------------------------------------------------------------- */
	
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
	
	public void mostrarCoracoes() {
		coracao11.load(caminho + "res\\batalha\\interferencia.png"); coracao12.load(caminho + "res\\batalha\\interferencia.png"); coracao13.load(caminho + "res\\batalha\\interferencia.png"); coracao14.load(caminho + "res\\batalha\\interferencia.png"); coracao15.load(caminho + "res\\batalha\\interferencia.png");
		coracao16.load(caminho + "res\\batalha\\interferencia.png"); coracao17.load(caminho + "res\\batalha\\interferencia.png"); coracao18.load(caminho + "res\\batalha\\interferencia.png"); coracao19.load(caminho + "res\\batalha\\interferencia.png"); coracao110.load(caminho + "res\\batalha\\interferencia.png");
		
		coracao21.load(caminho + "res\\batalha\\interferencia.png"); coracao22.load(caminho + "res\\batalha\\interferencia.png"); coracao23.load(caminho + "res\\batalha\\interferencia.png"); coracao24.load(caminho + "res\\batalha\\interferencia.png"); coracao25.load(caminho + "res\\batalha\\interferencia.png");
		coracao26.load(caminho + "res\\batalha\\interferencia.png"); coracao27.load(caminho + "res\\batalha\\interferencia.png"); coracao28.load(caminho + "res\\batalha\\interferencia.png"); coracao29.load(caminho + "res\\batalha\\interferencia.png"); coracao210.load(caminho + "res\\batalha\\interferencia.png");
		
		coracao31.load(caminho + "res\\batalha\\interferencia.png"); coracao32.load(caminho + "res\\batalha\\interferencia.png"); coracao33.load(caminho + "res\\batalha\\interferencia.png"); coracao34.load(caminho + "res\\batalha\\interferencia.png"); coracao35.load(caminho + "res\\batalha\\interferencia.png");
		coracao36.load(caminho + "res\\batalha\\interferencia.png"); coracao37.load(caminho + "res\\batalha\\interferencia.png"); coracao38.load(caminho + "res\\batalha\\interferencia.png"); coracao39.load(caminho + "res\\batalha\\interferencia.png"); coracao310.load(caminho + "res\\batalha\\interferencia.png");
		
		coracao41.load(caminho + "res\\batalha\\interferencia.png"); coracao42.load(caminho + "res\\batalha\\interferencia.png"); coracao43.load(caminho + "res\\batalha\\interferencia.png"); coracao44.load(caminho + "res\\batalha\\interferencia.png"); coracao45.load(caminho + "res\\batalha\\interferencia.png");
		coracao46.load(caminho + "res\\batalha\\interferencia.png"); coracao47.load(caminho + "res\\batalha\\interferencia.png"); coracao48.load(caminho + "res\\batalha\\interferencia.png"); coracao49.load(caminho + "res\\batalha\\interferencia.png"); coracao410.load(caminho + "res\\batalha\\interferencia.png");
		
		coracao51.load(caminho + "res\\batalha\\interferencia.png"); coracao52.load(caminho + "res\\batalha\\interferencia.png"); coracao53.load(caminho + "res\\batalha\\interferencia.png"); coracao54.load(caminho + "res\\batalha\\interferencia.png"); coracao55.load(caminho + "res\\batalha\\interferencia.png");
		coracao56.load(caminho + "res\\batalha\\interferencia.png"); coracao57.load(caminho + "res\\batalha\\interferencia.png"); coracao58.load(caminho + "res\\batalha\\interferencia.png"); coracao59.load(caminho + "res\\batalha\\interferencia.png"); coracao510.load(caminho + "res\\batalha\\interferencia.png");
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						coloca as imagens dos C?es na ordem correta							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void repintarCampoBatalha() {
		
		for(int i=0; i<5; i++) {
			(i == 0 ? iconeCampoBatalha1 : (i == 1 ? iconeCampoBatalha2 : (i == 2 ? iconeCampoBatalha3 : (i == 3 ? iconeCampoBatalha4 : iconeCampoBatalha5)))).load(caminho + "res\\batalha\\" + nomeAventureiro[ordemAventRodada[i]] + "\\iconeCampoBatalha1.png");
		}
		
		(posicaoAventureiro == 0 ? iconeCampoBatalha1 : (posicaoAventureiro == 1 ? iconeCampoBatalha2 : (posicaoAventureiro == 2 ? iconeCampoBatalha3 : (posicaoAventureiro == 3 ? iconeCampoBatalha4 : iconeCampoBatalha5)))).load(caminho + "res\\batalha\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 1 ? "ayla" : (aventureiro == 2 ? "rexthor" : (aventureiro == 3 ? "kiki" : "arius")))) + "\\iconeCampoBatalha2.png");
	}
	
	/* ---------------------------------------------------------------------------------------- \
   	|  								organiza os C?es antes da batalha							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void arrumarListaAventureiros() {
		
		
		if(aventureiro == adversario) {
			ordemAventRodada[0] = aventureiro;
			ordemAventRodada[1] = adversario + 1 < 5 ? adversario + 1 : adversario + 1 - 5 ;
			ordemAventRodada[2] = adversario + 2 < 5 ? adversario + 2 : adversario + 2 - 5 ;
			ordemAventRodada[3] = adversario + 3 < 5 ? adversario + 3 : adversario + 3 - 5 ;
			ordemAventRodada[4] = adversario != 0 ? adversario - 1 : 4;
			posicaoAventureiro = 0;
			
		} else {
			int adv = adversario;
			int ave = aventureiro;
			
			for(int i=0; i<4; i++) {
				ordemAventRodada[i] = adv;
				
				adv = adv + 1 < 5 ? adv +1 : adv + 1 - 5;
				if(adv == ave) {
					adv = adv+1 < 5 ? adv+1 : adv+1 -5;
				}
			}
			
			ordemAventRodada[4] = ave;
			posicaoAventureiro = 4;
		}		
		
		for(int i=0; i<5; i++) {
			ordemAventVerdadeira[i] = (ordemAventRodada[0] == i ? 0 : (ordemAventRodada[1] == i ? 1 :  (ordemAventRodada[2] == i ? 2 : (ordemAventRodada[3] == i ? 3 :  4))));
		}
	}

	
	
	/* ---------------------------------------------------------------------------------------- \
	|  									Vai para a tela de menu									|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntMenu(int codigo) {
		// ------------------------ manda para a tela do menu ----------------------- /
		
		if(dialogoAviso.getImagem() == null && codigo == KeyEvent.VK_Z) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			dialogoAviso.load(caminho + "res\\mensagem aviso\\dialogo.png");
			bntSimDialogoAviso.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogoAviso.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			bntSimNaoDialgoAviso = true;
			
			txtDialogoAviso.setTexto("Se voc? voltar a luta ser? encerrada.");
			txtDialogoAviso2.setTexto("Deseja continuar?");
			
		}else if ((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoAviso.getImagem() != null) {
		
			if(contEngranagem1 == 2) {
				contEngranagem1 = 1;
			} else {contEngranagem1 ++;}
			
			engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
			
			bntSimNaoDialgoAviso = !bntSimNaoDialgoAviso;
			bntSimDialogoAviso.load(caminho + "res\\mensagem aviso\\bntSim" + (bntSimNaoDialgoAviso == true ? "1" : "2") + ".png");
			bntNaoDialogoAviso.load(caminho + "res\\mensagem aviso\\bntNao" + (bntSimNaoDialgoAviso == true ? "2" : "1") + ".png");
		
		}else if(codigo == KeyEvent.VK_Z  || codigo == KeyEvent.VK_X && dialogoAviso.getImagem() != null) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			if(bntSimNaoDialgoAviso == false || codigo == KeyEvent.VK_X) {
				dialogoAviso.setImagem(null);
				bntSimDialogoAviso.setImagem(null);
				bntNaoDialogoAviso.setImagem(null);
				
				txtDialogoAviso.setTexto(" ");
				txtDialogoAviso2.setTexto(" ");
				
			} else {
				
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(telaMenu);
		        janelaPrincipal.setTitle("Menu");
		        
		        tela2.setTabelaInteracao(adversario, 3, tela2.getTabelaInteracao()[adversario][3] + 1);
		        tela2.setTabelaInteracao(adversario, 4, 3);
		        
		        salvar.SalvarDados(tela2.getTabelaInteracao(), aventureiro, caminho);
		        
		        telaMenu.setContEngranagem2(contEngranagem2);
		        telaMenu.valorLeituraSave = salvar.LerDados(caminho);
		        telaMenu.Restaurar();
		        janelaPrincipal.revalidate();
		        timer.stop();
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									Vai para a tela de Manual								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntManual(int codigo) {
		// ------------------------ manda para a tela de manual ----------------------- /
		if(codigo == KeyEvent.VK_Z ) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        telaManual = new Manual(contEngranagem2, caminho);
	        
	        telaManual.setTela3(this);
	        
	        janelaPrincipal.add(telaManual);
	        janelaPrincipal.setTitle("Manual3");
	        janelaPrincipal.revalidate();
	        timer.stop();
	        
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									Vai para a tela de Cr?ditos								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntCreditos(int codigo) {

		if(codigo == KeyEvent.VK_Z ) {
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        telaCreditos = new Creditos(contEngranagem2, caminho);
	        telaCreditos.setTela3(this);
	        janelaPrincipal.add(telaCreditos);
	        janelaPrincipal.setTitle("Creditos3");
	        janelaPrincipal.revalidate();
	        timer.stop();
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									volta para tela anterior								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoVoltar(int codigo) {
		if(dialogoAviso.getImagem() == null && codigo == KeyEvent.VK_Z) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			dialogoAviso.load(caminho + "res\\mensagem aviso\\dialogo.png");
			bntSimDialogoAviso.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogoAviso.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			bntSimNaoDialgoAviso = true;
			
			txtDialogoAviso.setTexto("Se voc? voltar a luta ser? encerrada.");
			txtDialogoAviso2.setTexto("Deseja continuar?");
			
		}else if ((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoAviso.getImagem() != null) {
			
			if(contEngranagem1 == 2) {
				contEngranagem1 = 1;
			} else {contEngranagem1 ++;}
			
			engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
			
			bntSimNaoDialgoAviso = !bntSimNaoDialgoAviso;
			
			bntSimDialogoAviso.load(caminho + "res\\mensagem aviso\\bntsim" + (bntSimNaoDialgoAviso == true ? "1" : "2") + ".png");
			bntNaoDialogoAviso.load(caminho + "res\\mensagem aviso\\bntnao" + (bntSimNaoDialgoAviso == true ? "2" : "1") + ".png");
			
		} else if(dialogoAviso.getImagem() != null && (codigo == KeyEvent.VK_X || (codigo == KeyEvent.VK_Z && bntSimNaoDialgoAviso == false))) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			dialogoAviso.setImagem(null);
			bntSimDialogoAviso.setImagem(null);
			bntNaoDialogoAviso.setImagem(null);
			
			txtDialogoAviso.setTexto(" ");
			txtDialogoAviso2.setTexto(" ");
			
		
		}else if(codigo == KeyEvent.VK_Z && dialogoAviso.getImagem() != null) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
	        tela2.setTabelaInteracao(adversario, 3, tela2.getTabelaInteracao()[adversario][3] + 1);
	        tela2.setTabelaInteracao(adversario, 4, 3);
	        salvar.SalvarDados(tela2.getTabelaInteracao(), aventureiro, caminho);
	        
			if(tela2.getTabelaInteracao()[aventureiro][3] > 6) {
		        janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(telaMenu);
		        janelaPrincipal.setTitle("Menu");
		        
		        telaMenu.setContEngranagem2(contEngranagem2);
		        telaMenu.valorLeituraSave = salvar.LerDados(caminho);
		        telaMenu.Restaurar();
			
			}else {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela2);
		        janelaPrincipal.setTitle("Escolha de Advers?rio");
		        
		        tela2.LimparTela3();
		        tela2.setContEngranagem2(contEngranagem2);
		        tela2.setNumAdversarioAnterior(adversario);
			}
	        
	        
	        janelaPrincipal.revalidate();
	        timer.stop();
	        
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						mostra a quantidade de apelo e interfer?ncia						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void itensDoApelo() {
		
		tipoDoApelo.load(caminho + "res\\batalha\\" + ((aventureiro == 0 ? apeloIgnis[3][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[3][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[3][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[3][selecaoNomeHab] : apeloArius[3][selecaoNomeHab])))) == 0 ? "tipoDoApelo1.png" : "tipoDoApelo2.png"));
		
		apeloApelo1.setImagem(null); apeloApelo2.setImagem(null); apeloApelo3.setImagem(null); apeloApelo4.setImagem(null); apeloApelo5.setImagem(null);
		apeloApelo6.setImagem(null); apeloApelo7.setImagem(null); apeloApelo8.setImagem(null); apeloApelo9.setImagem(null); apeloApelo10.setImagem(null);
		
		apeloInterf1.setImagem(null); apeloInterf2.setImagem(null); apeloInterf3.setImagem(null); apeloInterf4.setImagem(null); apeloInterf5.setImagem(null);
		apeloInterf6.setImagem(null); apeloInterf7.setImagem(null); apeloInterf8.setImagem(null); apeloInterf9.setImagem(null); apeloInterf10.setImagem(null);
		
		if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 1) {
			apeloApelo1.load(caminho + "res\\batalha\\apelo.png");
			if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 2) {
				apeloApelo2.load(caminho + "res\\batalha\\apelo.png");
				if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 3) {
					apeloApelo3.load(caminho + "res\\batalha\\apelo.png");
					if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 4) {
						apeloApelo4.load(caminho + "res\\batalha\\apelo.png");
						if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 5) {
							apeloApelo5.load(caminho + "res\\batalha\\apelo.png");
							if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 6) {
								apeloApelo6.load(caminho + "res\\batalha\\apelo.png");
								if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 7) {
									apeloApelo7.load(caminho + "res\\batalha\\apelo.png");
									if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 8) {
										apeloApelo8.load(caminho + "res\\batalha\\apelo.png");
										if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 9) {
											apeloApelo9.load(caminho + "res\\batalha\\apelo.png");
											if((aventureiro == 0 ? apeloIgnis[0][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[0][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[0][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[0][selecaoNomeHab] : apeloArius[0][selecaoNomeHab])))) >= 10) {
												apeloApelo10.load(caminho + "res\\batalha\\apelo.png");
		}}}}}}}}}}
		
		if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 1) {
			apeloInterf1.load(caminho + "res\\batalha\\interferencia.png");
			if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 2) {
				apeloInterf2.load(caminho + "res\\batalha\\interferencia.png");
				if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 3) {
					apeloInterf3.load(caminho + "res\\batalha\\interferencia.png");
					if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 4) {
						apeloInterf4.load(caminho + "res\\batalha\\interferencia.png");
						if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 5) {
							apeloInterf5.load(caminho + "res\\batalha\\interferencia.png");
							if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 6) {
								apeloInterf6.load(caminho + "res\\batalha\\interferencia.png");
								if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 7) {
									apeloInterf7.load(caminho + "res\\batalha\\interferencia.png");
									if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 8) {
										apeloInterf8.load(caminho + "res\\batalha\\interferencia.png");
										if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 9) {
											apeloInterf9.load(caminho + "res\\batalha\\interferencia.png");
											if((aventureiro == 0 ? apeloIgnis[1][selecaoNomeHab] : (aventureiro == 1 ? apeloAyla[1][selecaoNomeHab] : (aventureiro == 2 ? apeloRexthor[1][selecaoNomeHab] : (aventureiro == 3 ? apeloKiki[1][selecaoNomeHab] : apeloArius[1][selecaoNomeHab])))) >= 10) {
												apeloInterf10.load(caminho + "res\\batalha\\interferencia.png");
		}}}}}}}}}}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							dispara quando as teclas s?o  pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		if(janela != null && janela.getTitle() == "Batalha") {
			
			int codigo = tecla.getKeyCode();
			
			// -------------------- abre e fecha o menu -------------------- \
			if(codigo == KeyEvent.VK_ESCAPE && dialogoAviso.getImagem() == null) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				mostrarMenu = !mostrarMenu;
				
				if(mostrarMenu == true) {
					contMenu = 0;
					sombreadorMenu.load(caminho + "res\\sombreador.png");
					fundoMenu.load(caminho + "res\\Menu secundario\\menu1.png");
					bntMenu.load(caminho + "res\\Menu secundario\\bntMenu2.png");
					bntManual.load(caminho + "res\\Menu secundario\\bntManual1.png");
					bntVoltar.load(caminho + "res\\Menu secundario\\bntVoltar1.png");
					bntCreditos.load(caminho + "res\\Menu secundario\\bntCreditos1.png");
					
				} else {
					sombreadorMenu.setImagem(null);
					fundoMenu.setImagem(null);
					bntMenu.setImagem(null);
					bntManual.setImagem(null);
					bntVoltar.setImagem(null);
					bntCreditos.setImagem(null);

				}
				
			// ----------------------- muda a sele??o das op??es do menu -------------------------- \
			}else if((codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) && mostrarMenu == true && dialogoAviso.getImagem() == null) {
				
				if(contEngranagem1 == 2) {
					contEngranagem1 = 1;
				} else {contEngranagem1 ++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
				
				if(codigo == KeyEvent.VK_UP) {
					if(contMenu == 0) {contMenu = 3;} else {contMenu --;}
				}else if(codigo == KeyEvent.VK_DOWN) {
					if(contMenu == 3) {contMenu = 0;} else {contMenu ++;}
				}
				
				bntMenu.load(caminho + "res\\Menu secundario\\bntMenu1.png");
				bntManual.load(caminho + "res\\Menu secundario\\bntManual1.png");
				bntVoltar.load(caminho + "res\\Menu secundario\\bntVoltar1.png");
				bntCreditos.load(caminho + "res\\Menu secundario\\bntCreditos1.png");

				switch (contMenu) {
					case 0:
						bntMenu.load(caminho + "res\\Menu secundario\\bntMenu2.png");
						break;
					case 1:
						bntManual.load(caminho + "res\\Menu secundario\\bntManual2.png");
						break;
					case 2:
						bntCreditos.load(caminho + "res\\Menu secundario\\bntCreditos2.png");
						break;
					case 3:
						bntVoltar.load(caminho + "res\\Menu secundario\\bntVoltar2.png");
						break;
				}
				
			
			// ---------- encaminha para a fun??o que controla o bot?o menu do menu --------- \
			}else if(mostrarMenu == true && contMenu == 0) {
				dialogoBntMenu(codigo);
			
			// ---------- encaminha para a fun??o que controla o bot?o manual do menu --------- \
			}else if(mostrarMenu == true && contMenu == 1) {
				dialogoBntManual(codigo);
			
			// ---------- encaminha para a fun??o que controla o bot?o creditos do menu --------- \
			}else if(mostrarMenu == true && contMenu == 2) {
				dialogoBntCreditos(codigo);
				
			// ---------- encaminha para a fun??o que controla o bot?o voltar do menu --------- \
			}else if(mostrarMenu == true && contMenu == 3) {
				dialogoVoltar(codigo);
								
			// ---------- muda a sele??o dos ?cones dos personagens no mapa para cima e para baixo --------- \
			} else if((codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) && mostrarMenu == false && dialogoAviso.getImagem() == null && comecarAnimacaoCoracao == 0) {
				
				if(contEngranagem1 == 2) {
					contEngranagem1 = 1;
				} else {contEngranagem1 ++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
				
				if(codigo == KeyEvent.VK_UP) {
					if(selecaoNomeHab == 0) {selecaoNomeHab = 3;} 
					else{selecaoNomeHab --;}
					
				} else if(codigo == KeyEvent.VK_DOWN) {
					if(selecaoNomeHab == 3) {selecaoNomeHab = 0;} 
					else{selecaoNomeHab ++;}
				}
				
				itensDoApelo();
				
				nomeHabilidade1.load(caminho + "res\\batalha\\" + (nomeHabAnterior == 0 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
				nomeHabilidade2.load(caminho + "res\\batalha\\" + (nomeHabAnterior == 1 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
				nomeHabilidade3.load(caminho + "res\\batalha\\" + (nomeHabAnterior == 2 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
				nomeHabilidade4.load(caminho + "res\\batalha\\" + (nomeHabAnterior == 3 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
				
				switch (selecaoNomeHab) {
				case 0:
					nomeHabilidade1.load(caminho + "res\\batalha\\" + nomeAventureiro[aventureiro]+ "\\" + (nomeHabAnterior == 0 ? "nomeHabilidadeUsadaSelecionada.png" : "nomeHabilidadeSelecionado.png"));
				    break;
				case 1:
					nomeHabilidade2.load(caminho + "res\\batalha\\" + nomeAventureiro[aventureiro]+ "\\" + (nomeHabAnterior == 1 ? "nomeHabilidadeUsadaSelecionada.png" : "nomeHabilidadeSelecionado.png"));
					break;
				case 2:
					nomeHabilidade3.load(caminho + "res\\batalha\\" + nomeAventureiro[aventureiro]+ "\\" + (nomeHabAnterior == 2 ? "nomeHabilidadeUsadaSelecionada.png" : "nomeHabilidadeSelecionado.png"));
				    break;
				case 3:
					nomeHabilidade4.load(caminho + "res\\batalha\\" + nomeAventureiro[aventureiro]+ "\\" + (nomeHabAnterior == 3 ? "nomeHabilidadeUsadaSelecionada.png" : "nomeHabilidadeSelecionado.png"));
				    break;
				}
				
				textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][0]);
				textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][1]);
				textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][2]);
				textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][3]);
				textoDescricao5.setTexto(ConteudoDescricao[aventureiro == 0 ? selecaoNomeHab : (aventureiro == 1 ? selecaoNomeHab + 4 : (aventureiro == 2 ? selecaoNomeHab + 8 : (aventureiro == 3 ? selecaoNomeHab + 12 : selecaoNomeHab + 16)))][4]);
	
			// ------------------------------- seleciona a habilidade ---------------------------------
			} else if(codigo == KeyEvent.VK_Z && contEtapasBatalha < 5 && comecarAnimacaoCoracao == 0 && mostrarMenu == false && dialogoAviso.getImagem() == null && comecarAnimacaoInicio == false) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				contEtapasBatalha++;
				atualizarNomeHabili = false;
				
				arrayAleatorioHabAdver[0] = aleatorioHabAdver.nextInt(4); arrayAleatorioHabAdver[1] = aleatorioHabAdver.nextInt(4); arrayAleatorioHabAdver[2] = aleatorioHabAdver.nextInt(4); arrayAleatorioHabAdver[3] = aleatorioHabAdver.nextInt(4); arrayAleatorioHabAdver[4] = aleatorioHabAdver.nextInt(4);
				
				// ------------------------------- soma pontos ---------------------------------
	
				// --------------------------- pega todas as informa??es iniciais e coloca as informa??es de cada c?o --------------------
				for(int i=0; i<5; i++) {
				
					// ------------------------------- limpar variavel ---------------------------------

					efeitoChefeDeFase[ordemAventRodada[i]] = 0;
					efeitoApeloRepetido[ordemAventRodada[i]] = 0;
					efeitoInterferencias[ordemAventRodada[i]] = 0;
					
					// ------------------------------- ----------------------------------------------

					
					ordemAventRodada[i] = (i == ordemAventVerdadeira[0] ? 0 : (i == ordemAventVerdadeira[1] ? 1  : (i == ordemAventVerdadeira[2] ? 2  : (i == ordemAventVerdadeira[3] ? 3  : 4))));
					
					valorApelo[ordemAventRodada[i]] = (ordemAventRodada[i] == 0 ? apeloIgnis : (ordemAventRodada[i] == 1 ? apeloAyla : (ordemAventRodada[i] == 2 ? apeloRexthor : (ordemAventRodada[i] == 3 ? apeloKiki : apeloArius))))[0][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					valorInterferencia[ordemAventRodada[i]] = (ordemAventRodada[i] == 0 ? apeloIgnis : (ordemAventRodada[i] == 1 ? apeloAyla : (ordemAventRodada[i] == 2 ? apeloRexthor : (ordemAventRodada[i] == 3 ? apeloKiki : apeloArius))))[1][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					
					tipoInterferencia[ordemAventRodada[i]] = (ordemAventRodada[i] == 0 ? apeloIgnis : (ordemAventRodada[i] == 1 ? apeloAyla : (ordemAventRodada[i] == 2 ? apeloRexthor : (ordemAventRodada[i] == 3 ? apeloKiki : apeloArius))))[2][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];
					tipoAtaque[ordemAventRodada[i]] = (ordemAventRodada[i] == 0 ? apeloIgnis : (ordemAventRodada[i] == 1 ? apeloAyla : (ordemAventRodada[i] == 1 ? apeloRexthor : (ordemAventRodada[i] == 1 ? apeloKiki : apeloArius))))[3][i != posicaoAventureiro ? arrayAleatorioHabAdver[i] : selecaoNomeHab];

					pontosRodada[ordemAventRodada[i]] = valorApelo[ordemAventRodada[i]];
										
					numeroDosAtaques[contEtapasBatalha -1][ordemAventRodada[i]] = (ordemAventRodada[i] != aventureiro ? arrayAleatorioHabAdver[ordemAventRodada[i]] : selecaoNomeHab);
					
					
					
				// ------------------ coloca o dano de -2 no apelo atual da rodada se a habilidade for repetida -------------
					if(contEtapasBatalha - 1 > 0 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[i]])) {
						
						if(contEtapasBatalha - 1 == 4 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[i]])
								&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[i]])
								&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 4][ordemAventRodada[i]])
								&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 5][ordemAventRodada[i]])) {
							pontosRodada[ordemAventRodada[i]] = pontosRodada[ordemAventRodada[i]] - 8;
							
						} else if(contEtapasBatalha - 1 == 3 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[i]])
								&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[i]])
								&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 4][ordemAventRodada[i]])) {
							pontosRodada[ordemAventRodada[i]] = pontosRodada[ordemAventRodada[i]] - 6;
							
						} else if(contEtapasBatalha - 1 == 2 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[i]])
								&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[i]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[i]])) {
							pontosRodada[ordemAventRodada[i]] = pontosRodada[ordemAventRodada[i]] - 4;
							
						} else {
							pontosRodada[ordemAventRodada[i]] = pontosRodada[ordemAventRodada[i]] - 2;
						}
						
					}
					
					
				// ------------- coloca o dano de -1 no apelo atual da rodada se a habilidade conflitar com o efeitodo chefe da fase ----------------
					if((adversario == 0 && tipoInterferencia[ordemAventRodada[i]] != -1) || (adversario == 1 && tipoInterferencia[ordemAventRodada[i]] == -1) || (adversario == 2 && tipoAtaque[ordemAventRodada[i]] == 1) || (adversario == 3 && tipoInterferencia[ordemAventRodada[i]] != -1) || (adversario == 4 && tipoInterferencia[ordemAventRodada[i]] == -1)) {
						
						if(adversario == 0 || adversario == 1) {
							pontosRodada[ordemAventRodada[i]] --;
						}
						else {
							pontosRodada[ordemAventRodada[i]] ++;
						}
					}
				
				}
				
				for(int i=0; i<5; i++) {
					// --------------------------- subtrair efeito ----------------------------------
					
					//0: todos acima
					if(tipoInterferencia[ordemAventRodada[i]] == 0) {
						
						for(int i2=0; i2<4; i2++) {
							if(i > i2) {
								pontosRodada[ordemAventRodada[i2]] = pontosRodada[ordemAventRodada[i2]] - valorInterferencia[ordemAventRodada[i]];
							} 
						}
					}
					
					
					//1: todos abaixo
					else if(tipoInterferencia[ordemAventRodada[i]] == 1) {
						
						for(int i2=1; i2<5; i2++) {
							if(i < i2) {
								pontosRodada[ordemAventRodada[i2]] = pontosRodada[ordemAventRodada[i2]] - valorInterferencia[ordemAventRodada[i]];
							} 
						}
					}
					
					//2: um acima,
					else if(tipoInterferencia[ordemAventRodada[i]] == 2 ) {
					
						if(i > 0) {
							pontosRodada[ordemAventRodada[i - 1]] = pontosRodada[ordemAventRodada[i - 1]] - valorInterferencia[ordemAventRodada[i]];
						}
					}
					
					//3: primeiro,
					else if(tipoInterferencia[ordemAventRodada[i]] == 3 ) {

						if(i > 0) {
							pontosRodada[ordemAventRodada[0]] = pontosRodada[ordemAventRodada[0]] - valorInterferencia[ordemAventRodada[i]];
						}
					}
					
					//4: zera seus pontos negativos,
					if(tipoInterferencia[ordemAventRodada[i]] == 4 && pontosRodada[ordemAventRodada[i]] < 0) {
						pontosRodada[ordemAventRodada[i]] = 0;
					}
					
					//5: um acima e um abaixo,
					if(tipoInterferencia[ordemAventRodada[i]] == 5 ) {
						if(i > 0) {
							pontosRodada[ordemAventRodada[i - 1]] = pontosRodada[ordemAventRodada[i - 1]] - valorInterferencia[ordemAventRodada[i]];
							    
						}
						
						if(i < 4) {
							pontosRodada[ordemAventRodada[i + 1]] = pontosRodada[ordemAventRodada[i + 1]] - valorInterferencia[ordemAventRodada[i]];
							           
						}
					}
					
					//6: d20
					if(tipoInterferencia[ordemAventRodada[i]] == 6) {
						
						int temporario;
						
						dados[ordemAventRodada[i] == 2 ? 0 : 1][0] = aleatorioHabAdver.nextInt(20)+1; 
						
						for(int i2=0; i2<4; i2++) {
							while(true) {
								temporario = aleatorioHabAdver.nextInt(20)+1;
								
								if(temporario != dados[ordemAventRodada[i] == 2 ? 0 : 1][0] && temporario != dados[ordemAventRodada[i] == 2 ? 0 : 1][1] && temporario != dados[ordemAventRodada[i] == 2 ? 0 : 1][2] && temporario != dados[ordemAventRodada[i] == 2 ? 0 : 1][3]) {
									dados[ordemAventRodada[i] == 2 ? 0 : 1][i2+1]= temporario;
									break;
								}
							}
						}
						
						System.out.println("valor dados: " + dados[ordemAventRodada[i] == 2 ? 0 : 1][0] + " " + dados[ordemAventRodada[i] == 2 ? 0 : 1][1] + " " + dados[ordemAventRodada[i] == 2 ? 0 : 1][2] + " " + dados[ordemAventRodada[i] == 2 ? 0 : 1][3] + " " + dados[ordemAventRodada[i] == 2 ? 0 : 1][4]);
						
						if((ordemAventRodada[i] == 2 && (dados[ordemAventRodada[i] == 2 ? 0 : 1][0] == 20 || dados[ordemAventRodada[i] == 2 ? 0 : 1][1] == 20 || dados[ordemAventRodada[i] == 2 ? 0 : 1][2] == 20 || dados[ordemAventRodada[i] == 2 ? 0 : 1][3] == 20 || dados[ordemAventRodada[i] == 2 ? 0 : 1][4] == 20)) || (ordemAventRodada[i] == 4 && (dados[ordemAventRodada[i] == 2 ? 0 : 1][0] == 5 || dados[ordemAventRodada[i] == 2 ? 0 : 1][1] == 5 || dados[ordemAventRodada[i] == 2 ? 0 : 1][2] == 5 || dados[ordemAventRodada[i] == 2 ? 0 : 1][3] == 5 || dados[ordemAventRodada[i] == 2 ? 0 : 1][4] == 5))) {
							pontosRodada[ordemAventRodada[i]] = pontosRodada[ordemAventRodada[i]]  + valorInterferencia[ordemAventRodada[i]]; 
	
						}
					}
				}
							
				// --------------------------- soma o apelo atual ----------------------------------
	
				pontosTotais[0] = (pontosTotais[0] + (pontosRodada[0] > 10 ? 10 : (pontosRodada[0] < -10 ? -10 : pontosRodada[0])) < 0 ? 0 : (pontosTotais[0] + (pontosRodada[0] > 10 ? 10 : (pontosRodada[0] < -10 ? -10 : pontosRodada[0]))));
				pontosTotais[1] = (pontosTotais[1] + (pontosRodada[1] > 10 ? 10 : (pontosRodada[1] < -10 ? -10 : pontosRodada[1])) < 0 ? 0 : (pontosTotais[1] + (pontosRodada[1] > 10 ? 10 : (pontosRodada[1] < -10 ? -10 : pontosRodada[1]))));
				pontosTotais[2] = (pontosTotais[2] + (pontosRodada[2] > 10 ? 10 : (pontosRodada[2] < -10 ? -10 : pontosRodada[2])) < 0 ? 0 : (pontosTotais[2] + (pontosRodada[2] > 10 ? 10 : (pontosRodada[2] < -10 ? -10 : pontosRodada[2]))));
				pontosTotais[3] = (pontosTotais[3] + (pontosRodada[3] > 10 ? 10 : (pontosRodada[3] < -10 ? -10 : pontosRodada[3])) < 0 ? 0 : (pontosTotais[3] + (pontosRodada[3] > 10 ? 10 : (pontosRodada[3] < -10 ? -10 : pontosRodada[3]))));
				pontosTotais[4] = (pontosTotais[4] + (pontosRodada[4] > 10 ? 10 : (pontosRodada[4] < -10 ? -10 : pontosRodada[4])) < 0 ? 0 : (pontosTotais[4] + (pontosRodada[4] > 10 ? 10 : (pontosRodada[4] < -10 ? -10 : pontosRodada[4]))));
				
				
				
				System.out.println("ordem pessoa: " + ordemAventRodada[0] + "  " + ordemAventRodada[1] + "  " + ordemAventRodada[2] + "  " + ordemAventRodada[3] + "  " + ordemAventRodada[4]);
				System.out.println("ordem comparacao: " + ordemAventVerdadeira[0] + "  " + ordemAventVerdadeira[1] + "  " + ordemAventVerdadeira[2] + "  " + ordemAventVerdadeira[3] + "  " + ordemAventVerdadeira[4]);
				System.out.println("valor apelo:  " +valorApelo[0] + "  " + valorApelo[1] + "  " + valorApelo[2] + "  " + valorApelo[3] + "  " + valorApelo[4]);
				System.out.println("valor interferencia:   " + valorInterferencia[0] + "  " + valorInterferencia[1] + "  " + valorInterferencia[2] + "  " + valorInterferencia[3] + "  " + valorInterferencia[4]);
				System.out.println("tipo interferencia:   " + tipoInterferencia[0] + "  " + tipoInterferencia[1] + "  " + tipoInterferencia[2] + "  " + tipoInterferencia[3] + "  " + tipoInterferencia[4]);
				System.out.println("Pontos da partida?: " +pontosRodada[0] + "  " + pontosRodada[1] + "  " + pontosRodada[2] + "  " + pontosRodada[3] + "  " + pontosRodada[4]);
				System.out.println("total:  " +pontosTotais[0] + "  " + pontosTotais[1] + "  " + pontosTotais[2] + "  " + pontosTotais[3] + "  " + pontosTotais[4] + "\n");
	
				for(int a=0; a<5; a++) {
					System.out.println("interferencia: " + interferenciasRecebidas[a][0] + "  " + interferenciasRecebidas[a][1] + "  " + interferenciasRecebidas[a][2] + "  " + interferenciasRecebidas[a][3] + "  " + interferenciasRecebidas[a][4]);
				}
				
				for(int a=0; a<5; a++) {
					System.out.println("ataques: " + numeroDosAtaques[a][0] + "  " + numeroDosAtaques[a][1] + "  " + numeroDosAtaques[a][2] + "  " + numeroDosAtaques[a][3] + "  " + numeroDosAtaques[a][4]);
				}
				
				
				System.out.println("etapa da batalha:  " + contEtapasBatalha);

				
				comecarAnimacaoCoracao = 1;
				animacaoFileira = 0;
				
			// --------------------- termina a parabenizar?o para a escolha de advers?rio ------------------
			} else if(codigo == KeyEvent.VK_Z && contEtapasBatalha == 6 ) {
			
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela2);
		        janelaPrincipal.setTitle("Escolha de Advers?rio");
		        
		        if(ordemAventRodada[0] == aventureiro) {
		        	tela2.setTabelaInteracao(adversario, 1, tela2.getTabelaInteracao()[adversario][1] + 1);
		        	tela2.setTabelaInteracao(adversario, 4, 1);
		        	
		        } else {
		        	tela2.setTabelaInteracao(adversario, 2, tela2.getTabelaInteracao()[adversario][2] + 1);
		        	tela2.setTabelaInteracao(adversario, 4, 2);
		        }
	        	
		        tela2.mostrarEstrela();
		        tela2.LimparTela3();
		        tela2.setContEngranagem2(contEngranagem2);
		        tela2.SalvarJogo();
		        janelaPrincipal.revalidate();
		        timer.stop();
			
			} 
		}else{
			// ------------------------ manda para a tela de Manual ----------------------- /
			if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Manual3") {
				telaManual.KeyPressed(tecla);
			
			// ------------------------ manda para a tela de Manual ----------------------- /
			} else if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Creditos3") {
				telaCreditos.KeyPressed(tecla);
			}
		}
	}
	
	
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo.getImagem(), fundo.getRedX(), fundo.getRedY(), fundo.getLarg(), fundo.getAlt(), this);

		// ------------------------ divis?es da tela de batalha -------------------------
		
		graficos.drawImage(fundoAnimacao.getImagem(), fundoAnimacao.getRedX(), fundoAnimacao.getRedY(), fundoAnimacao.getLarg(), fundoAnimacao.getAlt(), this);
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getRedX(), iconeIgnis.getRedY(), iconeIgnis.getLarg(), iconeIgnis.getAlt(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getRedX(), iconeAyla.getRedY(), iconeAyla.getLarg(), iconeAyla.getAlt(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getRedX(), iconeRexthor.getRedY(), iconeRexthor.getLarg(), iconeRexthor.getAlt(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getRedX(), iconeArius.getRedY(), iconeArius.getLarg(), iconeArius.getAlt(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getRedX(), iconeKiki.getRedY(), iconeKiki.getLarg(), iconeKiki.getAlt(), this);
		
		// --------------------------------------------- anima??o ---------------------------------------------
		
		graficos.drawImage(animacao.getImagem(), animacao.getRedX(), animacao.getRedY(), animacao.getLarg(), animacao.getAlt(), this);
		graficos.drawImage(animacaoObj1.getImagem(), animacaoObj1.getRedX(), animacaoObj1.getRedY(), animacaoObj1.getLarg(), animacaoObj1.getAlt(), this);
		graficos.drawImage(animacaoObj2.getImagem(), animacaoObj2.getRedX(), animacaoObj2.getRedY(), animacaoObj2.getLarg(), animacaoObj2.getAlt(), this);
		graficos.drawImage(animacaoObj3.getImagem(), animacaoObj3.getRedX(), animacaoObj3.getRedY(), animacaoObj3.getLarg(), animacaoObj3.getAlt(), this);
		graficos.drawImage(animacaoObj4.getImagem(), animacaoObj4.getRedX(), animacaoObj4.getRedY(), animacaoObj4.getLarg(), animacaoObj4.getAlt(), this);

		// ----------------------------------------------------------------------------------------
		
		graficos.drawImage(campoBatalha1.getImagem(), campoBatalha1.getRedX(), campoBatalha1.getRedY(), campoBatalha1.getLarg(), campoBatalha1.getAlt(), this);
		graficos.drawImage(campoBatalha2.getImagem(), campoBatalha2.getRedX(), campoBatalha2.getRedY(), campoBatalha2.getLarg(), campoBatalha2.getAlt(), this);
		graficos.drawImage(campoBatalha3.getImagem(), campoBatalha3.getRedX(), campoBatalha3.getRedY(), campoBatalha3.getLarg(), campoBatalha3.getAlt(), this);
		graficos.drawImage(campoBatalha4.getImagem(), campoBatalha4.getRedX(), campoBatalha4.getRedY(), campoBatalha4.getLarg(), campoBatalha4.getAlt(), this);
		graficos.drawImage(campoBatalha5.getImagem(), campoBatalha5.getRedX(), campoBatalha5.getRedY(), campoBatalha5.getLarg(), campoBatalha5.getAlt(), this);
		
		graficos.drawImage(nomeHabilidade1.getImagem(), nomeHabilidade1.getRedX(), nomeHabilidade1.getRedY(), nomeHabilidade1.getLarg(), nomeHabilidade1.getAlt(), this);
		graficos.drawImage(nomeHabilidade2.getImagem(), nomeHabilidade2.getRedX(), nomeHabilidade2.getRedY(), nomeHabilidade2.getLarg(), nomeHabilidade2.getAlt(), this);
		graficos.drawImage(nomeHabilidade3.getImagem(), nomeHabilidade3.getRedX(), nomeHabilidade3.getRedY(), nomeHabilidade3.getLarg(), nomeHabilidade3.getAlt(), this);
		graficos.drawImage(nomeHabilidade4.getImagem(), nomeHabilidade4.getRedX(), nomeHabilidade4.getRedY(), nomeHabilidade4.getLarg(), nomeHabilidade4.getAlt(), this);
		
		graficos.drawImage(apelo.getImagem(), apelo.getRedX(), apelo.getRedY(), apelo.getLarg(), apelo.getAlt(), this);
		
		graficos.drawImage(descricao.getImagem(), descricao.getRedX(), descricao.getRedY(), descricao.getLarg(), descricao.getAlt(), this);

		// --------------------------------- campo batalha e habilidades usadas -----------------------------------------
		tl8 = new TextLayout(txtEfeitoFase.getTexto(), txtEfeitoFase.getFonte(), frc);

		graficos.setColor(txtEfeitoFase.getCorTexto());
		tl8.draw(graficos, txtEfeitoFase.getRedX(), txtEfeitoFase.getRedY());
	    
	    graficos.drawImage(efeitoFase.getImagem(), efeitoFase.getRedX(), efeitoFase.getRedY(), efeitoFase.getLarg(), efeitoFase.getAlt(), this);
		
		graficos.drawImage(iconeCampoBatalha1.getImagem(), iconeCampoBatalha1.getRedX(), iconeCampoBatalha1.getRedY(), iconeCampoBatalha1.getLarg(), iconeCampoBatalha1.getAlt(), this);
		graficos.drawImage(iconeCampoBatalha2.getImagem(), iconeCampoBatalha2.getRedX(), iconeCampoBatalha2.getRedY(), iconeCampoBatalha2.getLarg(), iconeCampoBatalha2.getAlt(), this);
		graficos.drawImage(iconeCampoBatalha3.getImagem(), iconeCampoBatalha3.getRedX(), iconeCampoBatalha3.getRedY(), iconeCampoBatalha3.getLarg(), iconeCampoBatalha3.getAlt(), this);
		graficos.drawImage(iconeCampoBatalha4.getImagem(), iconeCampoBatalha4.getRedX(), iconeCampoBatalha4.getRedY(), iconeCampoBatalha4.getLarg(), iconeCampoBatalha4.getAlt(), this);
		graficos.drawImage(iconeCampoBatalha5.getImagem(), iconeCampoBatalha5.getRedX(), iconeCampoBatalha5.getRedY(), iconeCampoBatalha5.getLarg(), iconeCampoBatalha5.getAlt(), this);
		
		graficos.drawImage(painel4.getImagem(), painel4.getRedX(), painel4.getRedY(), painel4.getLarg(), painel4.getAlt(), this);
		
		graficos.drawImage(luzCampoBatalha1.getImagem(), luzCampoBatalha1.getRedX(), luzCampoBatalha1.getRedY(), luzCampoBatalha1.getLarg(), luzCampoBatalha1.getAlt(), this);
		graficos.drawImage(luzCampoBatalha2.getImagem(), luzCampoBatalha2.getRedX(), luzCampoBatalha2.getRedY(), luzCampoBatalha2.getLarg(), luzCampoBatalha2.getAlt(), this);
		graficos.drawImage(luzCampoBatalha3.getImagem(), luzCampoBatalha3.getRedX(), luzCampoBatalha3.getRedY(), luzCampoBatalha3.getLarg(), luzCampoBatalha3.getAlt(), this);
		graficos.drawImage(luzCampoBatalha4.getImagem(), luzCampoBatalha4.getRedX(), luzCampoBatalha4.getRedY(), luzCampoBatalha4.getLarg(), luzCampoBatalha4.getAlt(), this);
		graficos.drawImage(luzCampoBatalha5.getImagem(), luzCampoBatalha5.getRedX(), luzCampoBatalha5.getRedY(), luzCampoBatalha5.getLarg(), luzCampoBatalha5.getAlt(), this);


		graficos.drawImage(coracao01.getImagem(), coracao01.getRedX(), coracao01.getRedY(), coracao01.getLarg(), coracao01.getAlt(), this);
		graficos.drawImage(coracao02.getImagem(), coracao02.getRedX(), coracao02.getRedY(), coracao02.getLarg(), coracao02.getAlt(), this);
		graficos.drawImage(coracao03.getImagem(), coracao03.getRedX(), coracao03.getRedY(), coracao03.getLarg(), coracao03.getAlt(), this);
		graficos.drawImage(coracao04.getImagem(), coracao04.getRedX(), coracao04.getRedY(), coracao04.getLarg(), coracao04.getAlt(), this);
		graficos.drawImage(coracao05.getImagem(), coracao05.getRedX(), coracao05.getRedY(), coracao05.getLarg(), coracao05.getAlt(), this);
		
		graficos.drawImage(coracao11.getImagem(), coracao11.getRedX(), coracao11.getRedY(), coracao11.getLarg(), coracao11.getAlt(), this);
		graficos.drawImage(coracao12.getImagem(), coracao12.getRedX(), coracao12.getRedY(), coracao12.getLarg(), coracao12.getAlt(), this);
		graficos.drawImage(coracao13.getImagem(), coracao13.getRedX(), coracao13.getRedY(), coracao13.getLarg(), coracao13.getAlt(), this);
		graficos.drawImage(coracao14.getImagem(), coracao14.getRedX(), coracao14.getRedY(), coracao14.getLarg(), coracao14.getAlt(), this);
		graficos.drawImage(coracao15.getImagem(), coracao15.getRedX(), coracao15.getRedY(), coracao15.getLarg(), coracao15.getAlt(), this);
		graficos.drawImage(coracao16.getImagem(), coracao16.getRedX(), coracao16.getRedY(), coracao16.getLarg(), coracao16.getAlt(), this);
		graficos.drawImage(coracao17.getImagem(), coracao17.getRedX(), coracao17.getRedY(), coracao17.getLarg(), coracao17.getAlt(), this);
		graficos.drawImage(coracao18.getImagem(), coracao18.getRedX(), coracao18.getRedY(), coracao18.getLarg(), coracao18.getAlt(), this);
		graficos.drawImage(coracao19.getImagem(), coracao19.getRedX(), coracao19.getRedY(), coracao19.getLarg(), coracao19.getAlt(), this);
		graficos.drawImage(coracao110.getImagem(), coracao110.getRedX(), coracao110.getRedY(), coracao110.getLarg(), coracao110.getAlt(), this);
		
		graficos.drawImage(coracao21.getImagem(), coracao21.getRedX(), coracao21.getRedY(), coracao21.getLarg(), coracao21.getAlt(), this);
		graficos.drawImage(coracao22.getImagem(), coracao22.getRedX(), coracao22.getRedY(), coracao22.getLarg(), coracao22.getAlt(), this);
		graficos.drawImage(coracao23.getImagem(), coracao23.getRedX(), coracao23.getRedY(), coracao23.getLarg(), coracao23.getAlt(), this);
		graficos.drawImage(coracao24.getImagem(), coracao24.getRedX(), coracao24.getRedY(), coracao24.getLarg(), coracao24.getAlt(), this);
		graficos.drawImage(coracao25.getImagem(), coracao25.getRedX(), coracao25.getRedY(), coracao25.getLarg(), coracao25.getAlt(), this);
		graficos.drawImage(coracao26.getImagem(), coracao26.getRedX(), coracao26.getRedY(), coracao26.getLarg(), coracao26.getAlt(), this);
		graficos.drawImage(coracao27.getImagem(), coracao27.getRedX(), coracao27.getRedY(), coracao27.getLarg(), coracao27.getAlt(), this);
		graficos.drawImage(coracao28.getImagem(), coracao28.getRedX(), coracao28.getRedY(), coracao28.getLarg(), coracao28.getAlt(), this);
		graficos.drawImage(coracao29.getImagem(), coracao29.getRedX(), coracao29.getRedY(), coracao29.getLarg(), coracao29.getAlt(), this);
		graficos.drawImage(coracao210.getImagem(), coracao210.getRedX(), coracao210.getRedY(), coracao210.getLarg(), coracao210.getAlt(), this);
		
		graficos.drawImage(coracao31.getImagem(), coracao31.getRedX(), coracao31.getRedY(), coracao31.getLarg(), coracao31.getAlt(), this);
		graficos.drawImage(coracao32.getImagem(), coracao32.getRedX(), coracao32.getRedY(), coracao32.getLarg(), coracao32.getAlt(), this);
		graficos.drawImage(coracao33.getImagem(), coracao33.getRedX(), coracao33.getRedY(), coracao33.getLarg(), coracao33.getAlt(), this);
		graficos.drawImage(coracao34.getImagem(), coracao34.getRedX(), coracao34.getRedY(), coracao34.getLarg(), coracao34.getAlt(), this);
		graficos.drawImage(coracao35.getImagem(), coracao35.getRedX(), coracao35.getRedY(), coracao35.getLarg(), coracao35.getAlt(), this);
		graficos.drawImage(coracao36.getImagem(), coracao36.getRedX(), coracao36.getRedY(), coracao36.getLarg(), coracao36.getAlt(), this);
		graficos.drawImage(coracao37.getImagem(), coracao37.getRedX(), coracao37.getRedY(), coracao37.getLarg(), coracao37.getAlt(), this);
		graficos.drawImage(coracao38.getImagem(), coracao38.getRedX(), coracao38.getRedY(), coracao38.getLarg(), coracao38.getAlt(), this);
		graficos.drawImage(coracao39.getImagem(), coracao39.getRedX(), coracao39.getRedY(), coracao39.getLarg(), coracao39.getAlt(), this);
		graficos.drawImage(coracao310.getImagem(), coracao310.getRedX(), coracao310.getRedY(), coracao310.getLarg(), coracao310.getAlt(), this);
		
		graficos.drawImage(coracao41.getImagem(), coracao41.getRedX(), coracao41.getRedY(), coracao41.getLarg(), coracao41.getAlt(), this);
		graficos.drawImage(coracao42.getImagem(), coracao42.getRedX(), coracao42.getRedY(), coracao42.getLarg(), coracao42.getAlt(), this);
		graficos.drawImage(coracao43.getImagem(), coracao43.getRedX(), coracao43.getRedY(), coracao43.getLarg(), coracao43.getAlt(), this);
		graficos.drawImage(coracao44.getImagem(), coracao44.getRedX(), coracao44.getRedY(), coracao44.getLarg(), coracao44.getAlt(), this);
		graficos.drawImage(coracao45.getImagem(), coracao45.getRedX(), coracao45.getRedY(), coracao45.getLarg(), coracao45.getAlt(), this);
		graficos.drawImage(coracao46.getImagem(), coracao46.getRedX(), coracao46.getRedY(), coracao46.getLarg(), coracao46.getAlt(), this);
		graficos.drawImage(coracao47.getImagem(), coracao47.getRedX(), coracao47.getRedY(), coracao47.getLarg(), coracao47.getAlt(), this);
		graficos.drawImage(coracao48.getImagem(), coracao48.getRedX(), coracao48.getRedY(), coracao48.getLarg(), coracao48.getAlt(), this);
		graficos.drawImage(coracao49.getImagem(), coracao49.getRedX(), coracao49.getRedY(), coracao49.getLarg(), coracao49.getAlt(), this);
		graficos.drawImage(coracao410.getImagem(), coracao410.getRedX(), coracao410.getRedY(), coracao410.getLarg(), coracao410.getAlt(), this);
		
		graficos.drawImage(coracao51.getImagem(), coracao51.getRedX(), coracao51.getRedY(), coracao51.getLarg(), coracao51.getAlt(), this);
		graficos.drawImage(coracao52.getImagem(), coracao52.getRedX(), coracao52.getRedY(), coracao52.getLarg(), coracao52.getAlt(), this);
		graficos.drawImage(coracao53.getImagem(), coracao53.getRedX(), coracao53.getRedY(), coracao53.getLarg(), coracao53.getAlt(), this);
		graficos.drawImage(coracao54.getImagem(), coracao54.getRedX(), coracao54.getRedY(), coracao54.getLarg(), coracao54.getAlt(), this);
		graficos.drawImage(coracao55.getImagem(), coracao55.getRedX(), coracao55.getRedY(), coracao55.getLarg(), coracao55.getAlt(), this);
		graficos.drawImage(coracao56.getImagem(), coracao56.getRedX(), coracao56.getRedY(), coracao56.getLarg(), coracao56.getAlt(), this);
		graficos.drawImage(coracao57.getImagem(), coracao57.getRedX(), coracao57.getRedY(), coracao57.getLarg(), coracao57.getAlt(), this);
		graficos.drawImage(coracao58.getImagem(), coracao58.getRedX(), coracao58.getRedY(), coracao58.getLarg(), coracao58.getAlt(), this);
		graficos.drawImage(coracao59.getImagem(), coracao59.getRedX(), coracao59.getRedY(), coracao59.getLarg(), coracao59.getAlt(), this);
		graficos.drawImage(coracao510.getImagem(), coracao510.getRedX(), coracao510.getRedY(), coracao510.getLarg(), coracao510.getAlt(), this);
				
		// --------------------------------- nome habilidades -----------------------------------------

		tl1 = new TextLayout(nomeApelo1.getTexto(), nomeApelo1.getFonte(), frc);
		tl2 = new TextLayout(nomeApelo2.getTexto(), nomeApelo1.getFonte(), frc);
		tl3 = new TextLayout(nomeApelo3.getTexto(), nomeApelo1.getFonte(), frc);
		tl4 = new TextLayout(nomeApelo4.getTexto(), nomeApelo1.getFonte(), frc);

	    
	    graficos.setColor(selecaoNomeHab == 0 ? nomeApelo1.getCorTexto() : (nomeHabAnterior == 0 ? nomeApelo3.getCorTexto() : nomeApelo2.getCorTexto()));
	    tl1.draw(graficos, nomeApelo1.getRedX(), nomeApelo1.getRedY());
	    graficos.setColor(selecaoNomeHab == 1 ? nomeApelo1.getCorTexto() : (nomeHabAnterior == 1 ? nomeApelo3.getCorTexto() : nomeApelo2.getCorTexto()));
	    tl2.draw(graficos, nomeApelo2.getRedX(), nomeApelo2.getRedY());
	    graficos.setColor(selecaoNomeHab == 2 ? nomeApelo1.getCorTexto() : (nomeHabAnterior == 2 ? nomeApelo3.getCorTexto() : nomeApelo2.getCorTexto()));
	    tl3.draw(graficos, nomeApelo3.getRedX(), nomeApelo3.getRedY());
	    graficos.setColor(selecaoNomeHab == 3 ? nomeApelo1.getCorTexto() : (nomeHabAnterior == 3 ? nomeApelo3.getCorTexto() : nomeApelo2.getCorTexto()));
	    tl4.draw(graficos, nomeApelo4.getRedX(), nomeApelo4.getRedY());
	    graficos.setColor(Color.BLACK);
	    
		graficos.drawImage(painel1.getImagem(), painel1.getRedX(), painel1.getRedY(), painel1.getLarg(), painel1.getAlt(), this);

		// ----------------------- itens relacionado com Apelo -----------------------------------
		
		tl6 = new TextLayout(apeloQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);
		tl7 = new TextLayout(InterferenciaQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);

	    graficos.setColor(apeloQuantidade.getCorTexto());
	    tl6.draw(graficos, apeloQuantidade.getRedX(), apeloQuantidade.getRedY());
	    tl7.draw(graficos, InterferenciaQuantidade.getRedX(), InterferenciaQuantidade.getRedY());
	    
		graficos.drawImage(apeloApelo1.getImagem(), apeloApelo1.getRedX(), apeloApelo1.getRedY(), apeloApelo1.getLarg(), apeloApelo1.getAlt(), this);
		graficos.drawImage(apeloApelo2.getImagem(), apeloApelo2.getRedX(), apeloApelo2.getRedY(), apeloApelo2.getLarg(), apeloApelo2.getAlt(), this);
		graficos.drawImage(apeloApelo3.getImagem(), apeloApelo3.getRedX(), apeloApelo3.getRedY(), apeloApelo3.getLarg(), apeloApelo3.getAlt(), this);
		graficos.drawImage(apeloApelo4.getImagem(), apeloApelo4.getRedX(), apeloApelo4.getRedY(), apeloApelo4.getLarg(), apeloApelo4.getAlt(), this);
		graficos.drawImage(apeloApelo5.getImagem(), apeloApelo5.getRedX(), apeloApelo5.getRedY(), apeloApelo5.getLarg(), apeloApelo5.getAlt(), this);
		graficos.drawImage(apeloApelo6.getImagem(), apeloApelo6.getRedX(), apeloApelo6.getRedY(), apeloApelo6.getLarg(), apeloApelo6.getAlt(), this);
		graficos.drawImage(apeloApelo7.getImagem(), apeloApelo7.getRedX(), apeloApelo7.getRedY(), apeloApelo7.getLarg(), apeloApelo7.getAlt(), this);
		graficos.drawImage(apeloApelo8.getImagem(), apeloApelo8.getRedX(), apeloApelo8.getRedY(), apeloApelo8.getLarg(), apeloApelo8.getAlt(), this);
		graficos.drawImage(apeloApelo9.getImagem(), apeloApelo9.getRedX(), apeloApelo9.getRedY(), apeloApelo9.getLarg(), apeloApelo9.getAlt(), this);
		graficos.drawImage(apeloApelo10.getImagem(), apeloApelo10.getRedX(), apeloApelo10.getRedY(), apeloApelo10.getLarg(), apeloApelo10.getAlt(), this);
		
		graficos.drawImage(apeloInterf1.getImagem(), apeloInterf1.getRedX(), apeloInterf1.getRedY(), apeloInterf1.getLarg(), apeloInterf1.getAlt(), this);
		graficos.drawImage(apeloInterf2.getImagem(), apeloInterf2.getRedX(), apeloInterf2.getRedY(), apeloInterf2.getLarg(), apeloInterf2.getAlt(), this);
		graficos.drawImage(apeloInterf3.getImagem(), apeloInterf3.getRedX(), apeloInterf3.getRedY(), apeloInterf3.getLarg(), apeloInterf3.getAlt(), this);
		graficos.drawImage(apeloInterf4.getImagem(), apeloInterf4.getRedX(), apeloInterf4.getRedY(), apeloInterf4.getLarg(), apeloInterf4.getAlt(), this);
		graficos.drawImage(apeloInterf5.getImagem(), apeloInterf5.getRedX(), apeloInterf5.getRedY(), apeloInterf5.getLarg(), apeloInterf5.getAlt(), this);
		graficos.drawImage(apeloInterf6.getImagem(), apeloInterf6.getRedX(), apeloInterf6.getRedY(), apeloInterf6.getLarg(), apeloInterf6.getAlt(), this);
		graficos.drawImage(apeloInterf7.getImagem(), apeloInterf7.getRedX(), apeloInterf7.getRedY(), apeloInterf7.getLarg(), apeloInterf7.getAlt(), this);
		graficos.drawImage(apeloInterf8.getImagem(), apeloInterf8.getRedX(), apeloInterf8.getRedY(), apeloInterf8.getLarg(), apeloInterf8.getAlt(), this);
		graficos.drawImage(apeloInterf9.getImagem(), apeloInterf9.getRedX(), apeloInterf9.getRedY(), apeloInterf9.getLarg(), apeloInterf9.getAlt(), this);
		graficos.drawImage(apeloInterf10.getImagem(), apeloInterf10.getRedX(), apeloInterf10.getRedY(), apeloInterf10.getLarg(), apeloInterf10.getAlt(), this);
		
		graficos.drawImage(painel2.getImagem(), painel2.getRedX(), painel2.getRedY(), painel2.getLarg(), painel2.getAlt(), this);
		
		graficos.drawImage(tipoDoApelo.getImagem(), tipoDoApelo.getRedX(), tipoDoApelo.getRedY(), tipoDoApelo.getLarg(), tipoDoApelo.getAlt(), this);
		
		// ----------------------- itens relacionado com Descri??o -----------------------------------
		tl5 = new TextLayout(textoDescricao1.getTexto(), textoDescricao1.getFonte(), frc);
		tl15 = new TextLayout(textoDescricao2.getTexto(), textoDescricao1.getFonte(), frc);
		tl16 = new TextLayout(textoDescricao3.getTexto(), textoDescricao1.getFonte(), frc);
		tl17 = new TextLayout(textoDescricao4.getTexto(), textoDescricao1.getFonte(), frc);
		tl19 = new TextLayout(textoDescricao5.getTexto(), textoDescricao1.getFonte(), frc);

	    
	    graficos.setColor(textoDescricao1.getCorTexto());
	    tl5.draw(graficos, textoDescricao1.getRedX(), textoDescricao1.getRedY());
	    tl15.draw(graficos, textoDescricao2.getRedX(), textoDescricao2.getRedY());
	    tl16.draw(graficos, textoDescricao3.getRedX(), textoDescricao3.getRedY());
	    tl17.draw(graficos, textoDescricao4.getRedX(), textoDescricao4.getRedY());
	    graficos.setColor(textoDescricao5.getCorTexto());
	    tl19.draw(graficos, textoDescricao5.getRedX(), textoDescricao5.getRedY());
	    graficos.setColor(Color.BLACK);
	    
		graficos.drawImage(painel3.getImagem(), painel3.getRedX(), painel3.getRedY(), painel3.getLarg(), painel3.getAlt(), this);
	    
		// ------------------------------------------- d20 ----------------------------------------
		graficos.drawImage(imgDado1.getImagem(), imgDado1.getRedX(), imgDado1.getRedY(), imgDado1.getLarg(), imgDado1.getAlt(), this);
		graficos.drawImage(imgDado2.getImagem(), imgDado2.getRedX(), imgDado2.getRedY(), imgDado2.getLarg(), imgDado2.getAlt(), this);
		graficos.drawImage(imgDado3.getImagem(), imgDado3.getRedX(), imgDado3.getRedY(), imgDado3.getLarg(), imgDado3.getAlt(), this);
		graficos.drawImage(imgDado4.getImagem(), imgDado4.getRedX(), imgDado4.getRedY(), imgDado4.getLarg(), imgDado4.getAlt(), this);
		graficos.drawImage(imgDado5.getImagem(), imgDado5.getRedX(), imgDado5.getRedY(), imgDado5.getLarg(), imgDado5.getAlt(), this);
		
		// -----------------------------------------------------------------------------------------------
		graficos.drawImage(parabenizacaoVencedor.getImagem(), parabenizacaoVencedor.getRedX(), parabenizacaoVencedor.getRedY(), parabenizacaoVencedor.getLarg(), parabenizacaoVencedor.getAlt(), this);
		graficos.drawImage(itemParabenizacaoVencedor3.getImagem(), itemParabenizacaoVencedor3.getRedX(), itemParabenizacaoVencedor3.getRedY(), itemParabenizacaoVencedor3.getLarg(), itemParabenizacaoVencedor3.getAlt(), this);
		graficos.drawImage(itemParabenizacaoVencedor1.getImagem(), itemParabenizacaoVencedor1.getRedX(), itemParabenizacaoVencedor1.getRedY(), itemParabenizacaoVencedor1.getLarg(), itemParabenizacaoVencedor1.getAlt(), this);
		graficos.drawImage(itemParabenizacaoVencedor2.getImagem(), itemParabenizacaoVencedor2.getRedX(), itemParabenizacaoVencedor2.getRedY(), itemParabenizacaoVencedor2.getLarg(), itemParabenizacaoVencedor2.getAlt(), this);
		
		// ------------------------------------- imagens do menu ---------------------------------------
		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getRedX(), sombreadorMenu.getRedY(), sombreadorMenu.getLarg(), sombreadorMenu.getAlt(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getRedX(), fundoMenu.getRedY(), fundoMenu.getLarg(), fundoMenu.getAlt(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getRedX(), bntMenu.getRedY(), bntMenu.getLarg(), bntMenu.getAlt(), this);
		graficos.drawImage(bntManual.getImagem(), bntManual.getRedX(), bntManual.getRedY(), bntManual.getLarg(), bntManual.getAlt(), this);
		graficos.drawImage(bntVoltar.getImagem(),  bntVoltar.getRedX(),  bntVoltar.getRedY(), bntVoltar.getLarg(), bntVoltar.getAlt(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getRedX(), bntCreditos.getRedY(), bntCreditos.getLarg(), bntCreditos.getAlt(), this);

		// ------------------------ imagens e textos do di?logo de aviso ------------------------------
		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getRedX(), dialogoAviso.getRedY(), dialogoAviso.getLarg(), dialogoAviso.getAlt(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getRedX(), bntSimDialogoAviso.getRedY(), bntSimDialogoAviso.getLarg(), bntSimDialogoAviso.getAlt(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getRedX(), bntNaoDialogoAviso.getRedY(), bntNaoDialogoAviso.getLarg(), bntNaoDialogoAviso.getAlt(), this);
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl20 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl21 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso.getFonte(), frc);

		
		tl20.draw(graficos, txtDialogoAviso.getRedX(), txtDialogoAviso.getRedY());
		tl21.draw(graficos, txtDialogoAviso2.getRedX(), txtDialogoAviso2.getRedY());
		
		// -----------------------------------------------------------------------------------------------
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getRedX(), engrenagem1.getRedY(), engrenagem1.getLarg(), engrenagem1.getAlt(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getRedX(), engrenagem2.getRedY(), engrenagem2.getLarg(), engrenagem2.getAlt(), this);
		graficos.drawImage(contorno.getImagem(), contorno.getRedX(), contorno.getRedY(), contorno.getLarg(), contorno.getAlt(), this);
		graficos.drawImage(tapaResto.getImagem(), tapaResto.getRedX(), tapaResto.getRedY(), tapaResto.getLarg(), tapaResto.getAlt(), this);

		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(comecarAnimacaoInicio == true) {
			animarInicio();
		}
		
		if(animarAnimacao == true) {
			animarAventureiros();
		}
		
		if(animacaoFileira != -1 && animacaoFileira < 10) {
			aparecerCoracoes();
		}
		
		// --------------------- come?a a colocar as interfer?ncias ---------------------
		if(animacaoFileira >= 10 && animacaoFileira < 20) {
			sumirCoracoes();
		}
		
		// -------------- mexe o medidor de ganho de apelo das etapa da batalha ----------------
		if(animacaoFileira == 20) {
			posApelo();
		}
		
		// -------------- mexe o medidor de ganho de apelo das etapa da batalha ----------------
		if(animacaoFileira == 21) {
			mexerMedidorApelos();
		}
		
		// -------------- organiza os valores das posi??es de acordo com a performance  na batalha ----------------
		if(animacaoFileira == 22) {
			OrganizarValores();
		}
		
		// -------------- organiza os c?es de acordo com a performance  na batalha ----------------
		if(animacaoFileira == 23) {
			OrganizarCampos();
		}
		
		// -------------- mostra a parabenizar?o no final da batalha ----------------
		if(animacaoFileira == 24) {
			vencedor();
		}
		
		if(aparecerVencedor == true) {
			animarVencedor();
		}
		
		repaint();
		
	}
	
	
	/* ---------------------------------------------------------------------------------------- \
	|  									mostra os apelos ganhos									|
	\ ---------------------------------------------------------------------------------------- */
	
	public void aparecerCoracoes() {
		
		if(animacaoFileira == 0 || animacaoFileira % 2 == 0) {
			contTempoApelo = 0;
			
			//------------------------------------- gif --------------------------------------------
			
			if((tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 6 || tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 4) && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] == 1) {
				coracao110.setDx(coracao110.getDx() + comecarAnimacaoCoracao);
			} else {
				animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);
			}

			if(animacao.getDx() == 20 && efeitoChefeDeFase[ordemAventRodada[vezDoAventureiro]] == 0) {
				acenderLuzAventureiro();
			}
			
			if(animacao.getDx() >= 20 && efeitoChefeDeFase[ordemAventRodada[vezDoAventureiro]] == 0) {
				animarAnimacao = false;
				gifApresentacao(ordemAventRodada[vezDoAventureiro], (vezDoAventureiro != posicaoAventureiro ? arrayAleatorioHabAdver[ordemAventRodada[vezDoAventureiro]] : selecaoNomeHab));
			}
			
			if(animacao.getDx() >= intervaloAnimacaoGif || efeitoChefeDeFase[ordemAventRodada[vezDoAventureiro]] == 1) {
				coracao110.setDx(coracao110.getDx() + comecarAnimacaoCoracao);
				animarAnimacao = true;
				animacaoObj1.setX(tamanhoContorno); animacaoObj1.setY(tamanhoContorno + 2);
				animacaoObj2.setX(tamanhoContorno); animacaoObj2.setY(tamanhoContorno + 2);
				animacaoObj3.setX(tamanhoContorno); animacaoObj3.setY(tamanhoContorno + 2);
				animacaoObj4.setX(tamanhoContorno); animacaoObj4.setY(tamanhoContorno + 2);
				animacao.setX(tamanhoContorno); animacao.setY(tamanhoContorno + 2);
				animacaoObj1.setImagem(null); animacaoObj2.setImagem(null);
				animacaoObj3.setImagem(null); animacaoObj4.setImagem(null);animacao.setImagem(null);	
				
							
			}
		
			
			//------------------------------------- limpa interferecia --------------------------------------------
			
			if(coracao110.getDx() > 0) {
				if(valorApelo[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] > 0 && pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] < 0) {
					
					if(contTempoInter < 10) {
						(contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11)))))))).setDx((contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11)))))))).getDx() + comecarAnimacaoCoracao);
					}
					
					if((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getDx() >= intervaloAnimacao) {
						
						if(animacaoFileira == 0) {
								(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -10 ? coracao110 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -9 ? coracao19 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -8 ? coracao18 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -7 ? coracao17 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -6 ? coracao16 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -5 ? coracao15 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -4 ? coracao14 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -3 ? coracao13 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -2 ? coracao12 : coracao11))))))))).load(caminho + "res\\batalha\\losango.png");
						} else if(animacaoFileira == 2) {			
								(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -10 ? coracao210 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -9 ? coracao29 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -8 ? coracao28 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -7 ? coracao27 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -6 ? coracao26 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -5 ? coracao25 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -4 ? coracao24 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -3 ? coracao23 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -2 ? coracao22 : coracao21))))))))).load(caminho + "res\\batalha\\losango.png");
						} else if(animacaoFileira == 4) {	
								(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -10 ? coracao310 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -9 ? coracao39 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -8 ? coracao38 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -7 ? coracao37 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -6 ? coracao36 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -5 ? coracao35 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -4 ? coracao34 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -3 ? coracao33 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -2 ? coracao32 : coracao31))))))))).load(caminho + "res\\batalha\\losango.png");
						} else if(animacaoFileira == 6) {	
								(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -10 ? coracao410 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -9 ? coracao49 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -8 ? coracao48 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -7 ? coracao47 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -6 ? coracao46 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -5 ? coracao45 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -4 ? coracao44 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -3 ? coracao43 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -2 ? coracao42 : coracao41))))))))).load(caminho + "res\\batalha\\losango.png");
						} else if(animacaoFileira == 8) {	
								(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -10 ? coracao510 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -9 ? coracao59 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -8 ? coracao58 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -7 ? coracao57 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -6 ? coracao56 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -5 ? coracao55 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -4 ? coracao54 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -3 ? coracao53 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] == -2 ? coracao52 : coracao51))))))))).load(caminho + "res\\batalha\\losango.png");
						}

						valorApelo[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] = valorApelo[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] - 1;
						pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] = pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira == 0 ? 0 : animacaoFileira/2)]] + 1;

						contTempoInter --;
					}
				} else {
					animacaoFileira ++;
					zerarDx();
				}
			}
		} else if(animacaoFileira == 1 || (animacaoFileira - 1) % 2 == 0) {
			contTempoInter = 10;
			animacao.setDx(0);
			
			//------------------------------------- aparece apelos  --------------------------------------------
			if(valorApelo[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]] > 0) {
				
				(contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).setDx((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getDx() + comecarAnimacaoCoracao);

				if((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getDx() >= intervaloAnimacao) {

					if(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]] < 10) {
						if(animacaoFileira == 1) {
							(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 0 ? coracao11 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 1 ? coracao12 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 2 ? coracao13 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 3 ? coracao14 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 4 ? coracao15 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 5 ? coracao16 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 6 ? coracao17 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 7 ? coracao18 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 8 ? coracao19 : coracao110))))))))).load(caminho + "res\\batalha\\apelo.png");
						} else if(animacaoFileira == 3) {
							(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 0 ? coracao21 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 1 ? coracao22 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 2 ? coracao23 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 3 ? coracao24 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 4 ? coracao25 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 5 ? coracao26 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 6 ? coracao27 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 7 ? coracao28 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 8 ? coracao29 : coracao210))))))))).load(caminho + "res\\batalha\\apelo.png");
						} else if(animacaoFileira == 5) {	
							(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 0 ? coracao31 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 1 ? coracao32 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 2 ? coracao33 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 3 ? coracao34 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 4 ? coracao35 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 5 ? coracao36 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 6 ? coracao37 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 7 ? coracao38 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 8 ? coracao39 : coracao310))))))))).load(caminho + "res\\batalha\\apelo.png");
						} else if(animacaoFileira == 7) {
							(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 0 ? coracao41 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 1 ? coracao42 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 2 ? coracao43 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 3 ? coracao44 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 4 ? coracao45 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 5 ? coracao46 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 6 ? coracao47 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 7 ? coracao48 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 8 ? coracao49 : coracao410))))))))).load(caminho + "res\\batalha\\apelo.png");
						} else if(animacaoFileira == 9) {		
							(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 0 ? coracao51 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 1 ? coracao52 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 2 ? coracao53 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 3 ? coracao54 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 4 ? coracao55 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 5 ? coracao56 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 6 ? coracao57 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 7 ? coracao58 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]]== 8 ? coracao59 : coracao510))))))))).load(caminho + "res\\batalha\\apelo.png");
						}
						
						pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]] = pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]] + 1;
					}
					
					valorApelo[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]] = valorApelo[ordemAventRodada[(animacaoFileira - 1 == 0 ? 0 : (animacaoFileira - 1) /2)]] - 1;
					contTempoApelo ++;
				}
				
			} else {
				zerarDx();
				animacaoFileira = 20;
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
	|  								mostra as interfer?ncias ganhas								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void sumirCoracoes() {
		//------------------------------------- 1 --------------------------------------------
		if(animacaoFileira - 10 == 0 || (animacaoFileira - 10) % 2 == 0) {
			contTempoApelo = 0;
			
			
			//------------------------------------- limpa apelo --------------------------------------------
			
			if(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] > 0 && interferenciasRecebidas[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]][ordemAventRodada[vezDoAventureiro]] > 0) {
				
				(contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).setDx((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getDx() + comecarAnimacaoCoracao);

				if((contTempoInter== 10 ? coracao110 : (contTempoInter== 9 ? coracao19 : (contTempoInter== 8 ? coracao18 : (contTempoInter== 7 ? coracao17 : (contTempoInter== 6 ? coracao16 : (contTempoInter== 5 ? coracao15 : (contTempoInter== 4 ? coracao14 : (contTempoInter== 3 ? coracao13 : (contTempoInter== 2 ? coracao12 : coracao11))))))))).getDx() >= intervaloAnimacao) {
					
					if(animacaoFileira == 10) {
						(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 10 ? coracao110 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 9 ? coracao19 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 8 ? coracao18 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 7 ? coracao17 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 6 ? coracao16 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 5 ? coracao15 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 4 ? coracao14 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 3 ? coracao13 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 2 ? coracao12 : coracao11))))))))).load(caminho + "res\\batalha\\losango.png");
					} else if(animacaoFileira == 12) {
						(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 10 ? coracao210 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 9 ? coracao29 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 8 ? coracao28 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 7 ? coracao27 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 6 ? coracao26 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 5 ? coracao25 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 4 ? coracao24 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 3 ? coracao23 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 2 ? coracao22 : coracao21))))))))).load(caminho + "res\\batalha\\losango.png");
					} else if(animacaoFileira == 14) {
						(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 10 ? coracao310 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 9 ? coracao39 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 8 ? coracao38 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 7 ? coracao37 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 6 ? coracao36 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 5 ? coracao35 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 4 ? coracao34 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 3 ? coracao33 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 2 ? coracao32 : coracao31))))))))).load(caminho + "res\\batalha\\losango.png");
					} else if(animacaoFileira == 16) {
						(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 10 ? coracao410 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 9 ? coracao49 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 8 ? coracao48 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 7 ? coracao47 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 6 ? coracao46 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 5 ? coracao45 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 4 ? coracao44 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 3 ? coracao43 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 2 ? coracao42 : coracao41))))))))).load(caminho + "res\\batalha\\losango.png");
					} else if(animacaoFileira == 18) {
						(pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 10 ? coracao510 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 9 ? coracao59 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 8 ? coracao58 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 7 ? coracao57 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 6 ? coracao56 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 5 ? coracao55 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 4 ? coracao54 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 3 ? coracao53 : (pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] == 2 ? coracao52 : coracao51))))))))).load(caminho + "res\\batalha\\losango.png");
					}
					
					interferenciasRecebidas[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]][ordemAventRodada[vezDoAventureiro]] = interferenciasRecebidas[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]][ordemAventRodada[vezDoAventureiro]] - 1;
					pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] = pontosAtuaisDaRodada[ordemAventRodada[(animacaoFileira - 10 == 0 ? 0 : (animacaoFileira - 10)/2)]] - 1;

					contTempoInter --;
				}
				
			} else {

				animacaoFileira ++;
				contTempoInter = 10;
				zerarDx();
			}
			
			
		}else if(animacaoFileira - 11 == 0 || (animacaoFileira - 11) % 2 == 0) {
			contTempoInter = 10;

			//------------------------------------- aparece interferencia  --------------------------------------------
			
			if(interferenciasRecebidas[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]][ordemAventRodada[vezDoAventureiro]] > 0) {
				
				(contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).setDx((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getDx() + comecarAnimacaoCoracao);
				
				if((contTempoApelo== 0 ? coracao11 : (contTempoApelo== 1 ? coracao12 : (contTempoApelo== 2 ? coracao13 : (contTempoApelo== 3 ? coracao14 : (contTempoApelo== 4 ? coracao15 : (contTempoApelo== 5 ? coracao16 : (contTempoApelo== 6 ? coracao17 : (contTempoApelo== 7 ? coracao18 : (contTempoApelo== 8 ? coracao19 : coracao110))))))))).getDx() >= intervaloAnimacao && interferenciasRecebidas[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]][ordemAventRodada[vezDoAventureiro]] > 0) {
					
					if(pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] > -10) {
						if(animacaoFileira == 11) {
							(pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == 0 ? coracao11 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -1 ? coracao12 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -2 ? coracao13 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -3 ? coracao14 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -4 ? coracao15 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -5 ? coracao16 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -6 ? coracao17 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -7 ? coracao18 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -8 ? coracao19 : coracao110))))))))).load(caminho + "res\\batalha\\interferencia.png");
						} else if(animacaoFileira == 13) {
							(pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == 0 ? coracao21 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -1 ? coracao22 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -2 ? coracao23 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -3 ? coracao24 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -4 ? coracao25 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -5 ? coracao26 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -6 ? coracao27 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -7 ? coracao28 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -8 ? coracao29 : coracao210))))))))).load(caminho + "res\\batalha\\interferencia.png");
						} else if(animacaoFileira == 15) {
							(pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == 0 ? coracao31 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -1 ? coracao32 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -2 ? coracao33 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -3 ? coracao34 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -4 ? coracao35 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -5 ? coracao36 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -6 ? coracao37 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -7 ? coracao38 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -8 ? coracao39 : coracao310))))))))).load(caminho + "res\\batalha\\interferencia.png");
						} else if(animacaoFileira == 17) {
							(pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == 0 ? coracao41 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -1 ? coracao42 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -2 ? coracao43 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -3 ? coracao44 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -4 ? coracao45 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -5 ? coracao46 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -6 ? coracao47 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -7 ? coracao48 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -8 ? coracao49 : coracao410))))))))).load(caminho + "res\\batalha\\interferencia.png");
						} else if(animacaoFileira == 19) {
							(pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == 0 ? coracao51 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -1 ? coracao52 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -2 ? coracao53 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -3 ? coracao54 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -4 ? coracao55 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -5 ? coracao56 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -6 ? coracao57 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -7 ? coracao58 : (pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] == -8 ? coracao59 : coracao510))))))))).load(caminho + "res\\batalha\\interferencia.png");
						}
						
						pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] = pontosAtuaisDaRodada[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]] - 1;
					}
					
					interferenciasRecebidas[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]][ordemAventRodada[vezDoAventureiro]] = interferenciasRecebidas[ordemAventRodada[animacaoFileira - 11 == 0 ? 0 : (animacaoFileira - 11)/2]][ordemAventRodada[vezDoAventureiro]] - 1;
					contTempoApelo ++;
				}
				
			} else{
				zerarDx();
				animacaoFileira = 20;
			}
		}
	}
	
	
	/* ---------------------------------------------------------------------------------------- \
	|  			                   	diz o que fazer apos colocar os apelos						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void posApelo() {

		if(efeitoChefeDeFase[ordemAventRodada[vezDoAventureiro]] == 0 && (((adversario == 0 || adversario == 3) && tipoInterferencia[ordemAventRodada[vezDoAventureiro]] != -1) || ((adversario == 1 || adversario == 4) && tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == -1) || (adversario == 2 && tipoAtaque[ordemAventRodada[vezDoAventureiro]] == 1))) {

			efeitoChefeDeFase();
			
		} else if(efeitoApeloRepetido[ordemAventRodada[vezDoAventureiro]] == 0 && (contEtapasBatalha - 1 == 0 ? false : (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[vezDoAventureiro]]))) {

			habilidadeRepetido();
			
		} else if((tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 0 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 4)
			   || (tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 1 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 5)
			   || (tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 2 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 1 && vezDoAventureiro > 0)
			   || (tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 3 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 1 && vezDoAventureiro > 0)
			   || (tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 4 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 1 && pontosAtuaisDaRodada[ordemAventRodada[vezDoAventureiro]] < 0)
			   || (tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 5 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 2)
			   || (tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 6 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 1)) {
		
			interferirNosAdversarios();
			
		} else if(efeitoChefeDeFase[ordemAventRodada[vezDoAventureiro]] == 1 || !(((adversario == 0 || adversario == 3) && tipoInterferencia[ordemAventRodada[vezDoAventureiro]] != -1) || ((adversario == 1 || adversario == 4) && tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == -1) || (adversario == 2 && tipoAtaque[ordemAventRodada[vezDoAventureiro]] == 1))
				|| efeitoApeloRepetido[ordemAventRodada[vezDoAventureiro]] == 1 || !(contEtapasBatalha - 1 == 0 ? false : (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[vezDoAventureiro]]))
				|| (!(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 0 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 4)
				&&  !(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 1 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 5)
				&&  !(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 2 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 1 && vezDoAventureiro > 0)
				&&  !(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 3 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 1 && vezDoAventureiro > 0)
				&&  !(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 4 && pontosAtuaisDaRodada[ordemAventRodada[vezDoAventureiro]] < 0 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 1 )
				&&  !(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 5 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 2)
				&&  !(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 6 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] < 1))){
			
			animacaoFileira = (vezDoAventureiro == 0? 0 : vezDoAventureiro * 2) + 2;
			
			efeitoChefeDeFase[ordemAventRodada[vezDoAventureiro]] = 2;
			efeitoApeloRepetido[ordemAventRodada[vezDoAventureiro]] = 2;
			efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] = 5;
			
			vezDoAventureiro ++;
			
			if(vezDoAventureiro == 5) {
				acenderLuzAventureiro();
				animacaoFileira = 21;
				vezDoAventureiro = 0;
			}
		}
	}
	
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca os efeitos nos c?es										|
	\ ---------------------------------------------------------------------------------------- */
	
	public void interferirNosAdversarios() {
		//(-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro,
		// 4: zera seus pontos negativos, 5: um acima e um abaixo, 6: jogar d20)
		
		// ------------------ 0: todos acima ------------------------------
		if(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 0) {
			
			if(vezDoAventureiro > efeitoInterferencias[ordemAventRodada[vezDoAventureiro]]) {
				
				interferenciasRecebidas[ordemAventRodada[efeitoInterferencias[ordemAventRodada[vezDoAventureiro]]]][ordemAventRodada[vezDoAventureiro]] = valorInterferencia[ordemAventRodada[vezDoAventureiro]];
				animacaoFileira = 10 + 2 * efeitoInterferencias[ordemAventRodada[vezDoAventureiro]];	
				
			}
			
			efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ++;
			
		// ----------------------------- 1: todos abaixo ---------------------
		} else if(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 1) {
			
			if(vezDoAventureiro < efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ) {
				
				interferenciasRecebidas[ordemAventRodada[efeitoInterferencias[ordemAventRodada[vezDoAventureiro]]]][ordemAventRodada[vezDoAventureiro]] = valorInterferencia[ordemAventRodada[vezDoAventureiro]];
				animacaoFileira = 10 + 2 * efeitoInterferencias[ordemAventRodada[vezDoAventureiro]];	
				
			}
			
			efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ++;
			
			
		// ----------------------------- 2: um acima ----------------------------------
		} else if(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 2) {
			
			interferenciasRecebidas[ordemAventRodada[vezDoAventureiro - 1]][ordemAventRodada[vezDoAventureiro]] = valorInterferencia[ordemAventRodada[vezDoAventureiro]];
			animacaoFileira = 10 + 2 * (vezDoAventureiro - 1);
			efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ++;
			
		// ---------------------------- 3: primeiro ---------------------------------
		} else if(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 3) {

			interferenciasRecebidas[ordemAventRodada[0]][ordemAventRodada[vezDoAventureiro]] = valorInterferencia[ordemAventRodada[vezDoAventureiro]];
			animacaoFileira = 10;
			efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ++;
			
		// --------------------- 4: zera seus pontos negativos ---------------------
		}else if(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 4) { 
			
			valorApelo[ordemAventRodada[vezDoAventureiro]] = 0 - pontosAtuaisDaRodada[ordemAventRodada[vezDoAventureiro]];
			animacaoFileira = vezDoAventureiro * 2;
			efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ++;
			
		// -------------------- 5: um acima e um abaixo -----------------------------
		} else if(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 5) {
			
			if(vezDoAventureiro > 0 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] == 0) {
				interferenciasRecebidas[ordemAventRodada[vezDoAventureiro - 1]][ordemAventRodada[vezDoAventureiro]] = valorInterferencia[ordemAventRodada[vezDoAventureiro]];
				animacaoFileira = 10 + 2 * (vezDoAventureiro - 1);
				
			} else if(vezDoAventureiro < 4 && efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] == 1) {
				
				interferenciasRecebidas[ordemAventRodada[vezDoAventureiro + 1]][ordemAventRodada[vezDoAventureiro]] = valorInterferencia[ordemAventRodada[vezDoAventureiro]];
				animacaoFileira = 10 + 2 * (vezDoAventureiro + 1);
				
			}
						
			efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ++;
			
						
		// ------------------------------- 6: jogar d20 ----------------------------------
		}else if(tipoInterferencia[ordemAventRodada[vezDoAventureiro]] == 6) {
			
			if((ordemAventRodada[vezDoAventureiro] == 2 && (dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][0] == 20 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][1] == 20 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][2] == 20 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][3] == 20 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][4] == 20)) || (ordemAventRodada[vezDoAventureiro] == 4 && (dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][0] == 5 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][1] == 5 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][2] == 5 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][3] == 5 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][4] == 5))) {
				
				valorApelo[ordemAventRodada[vezDoAventureiro]] = valorInterferencia[ordemAventRodada[vezDoAventureiro]];
				animacaoFileira = vezDoAventureiro * 2;
			}
			
			efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ++;
			
		}
	}
	
	
	
	/* ---------------------------------------------------------------------------------------- \
	|  							aciona o efeito de habilidade repetida							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void habilidadeRepetido() {

		efeitoFase.setDx(efeitoFase.getDx() + comecarAnimacaoCoracao);
		
		if(efeitoFase.getDx() == 21) {
			
			if(contEtapasBatalha - 1 == 4 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 4][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 5][ordemAventRodada[vezDoAventureiro]])) {
				txtEfeitoFase.setTexto("-8");
				
			} else if(contEtapasBatalha - 1 == 3 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 4][ordemAventRodada[vezDoAventureiro]])) {
				txtEfeitoFase.setTexto("-6");
				
			} else if(contEtapasBatalha - 1 == 2 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[vezDoAventureiro]])) {
				txtEfeitoFase.setTexto("-4");
				
			} else {
				txtEfeitoFase.setTexto("-2");
			}
			
			efeitoFase.load(caminho + "res\\batalha\\apelo.png");
			txtEfeitoFase.setY((vezDoAventureiro == 0 ? campoBatalha1.getY() : (vezDoAventureiro == 1 ? campoBatalha2.getY() : (vezDoAventureiro == 2 ? campoBatalha3.getY() : (vezDoAventureiro == 3 ? campoBatalha4.getY() : campoBatalha5.getY())))) + 70/2 + 7);
			efeitoFase.setY(txtEfeitoFase.getY() - 17);
			
		} else if(efeitoFase.getDx() == 60) {

			efeitoFase.setImagem(null);
			txtEfeitoFase.setTexto(" ");
			
			if(contEtapasBatalha - 1 == 4 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 4][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 5][ordemAventRodada[vezDoAventureiro]])) {
				interferenciasRecebidas[ordemAventRodada[vezDoAventureiro]][ordemAventRodada[vezDoAventureiro]] = 8;
				
			} else if(contEtapasBatalha - 1 == 3 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 4][ordemAventRodada[vezDoAventureiro]])) {
				interferenciasRecebidas[ordemAventRodada[vezDoAventureiro]][ordemAventRodada[vezDoAventureiro]] = 6;
				
			} else if(contEtapasBatalha - 1 == 2 && (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 2][ordemAventRodada[vezDoAventureiro]])
					&& (numeroDosAtaques[contEtapasBatalha - 1][ordemAventRodada[vezDoAventureiro]] == numeroDosAtaques[contEtapasBatalha - 3][ordemAventRodada[vezDoAventureiro]])) {
				interferenciasRecebidas[ordemAventRodada[vezDoAventureiro]][ordemAventRodada[vezDoAventureiro]] = 4;
				
			} else {
				interferenciasRecebidas[ordemAventRodada[vezDoAventureiro]][ordemAventRodada[vezDoAventureiro]] = 2;
			}
			
			efeitoApeloRepetido[ordemAventRodada[vezDoAventureiro]] = 1;
			animacaoFileira = (vezDoAventureiro == 0? 0 : vezDoAventureiro * 2) + 10;	
			
			zerarDx();
		}
	}
	
	
	
	/* ---------------------------------------------------------------------------------------- \
	|  							aciona o efeito de do chefe da fase								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void efeitoChefeDeFase () {

		efeitoFase.setDx(efeitoFase.getDx() + comecarAnimacaoCoracao);
		
		if(efeitoFase.getDx() == 21) {
			
			txtEfeitoFase.setTexto(adversario == 0 || adversario == 1 ? "-1" : "+1");
			efeitoFase.load(caminho + "res\\batalha\\apelo.png");
			txtEfeitoFase.setY((vezDoAventureiro == 0 ? campoBatalha1.getY() : (vezDoAventureiro == 1 ? campoBatalha2.getY() : (vezDoAventureiro == 2 ? campoBatalha3.getY() : (vezDoAventureiro == 3 ? campoBatalha4.getY() : campoBatalha5.getY())))) + 70/2 + 7);
			efeitoFase.setY(txtEfeitoFase.getY() - 17);
			
		} else if(efeitoFase.getDx() == 60) {

			efeitoFase.setImagem(null);
			txtEfeitoFase.setTexto(" ");
			
			if(adversario == 0 || adversario == 1) {
				interferenciasRecebidas[ordemAventRodada[vezDoAventureiro]][ordemAventRodada[vezDoAventureiro]] = 1;
				animacaoFileira = (vezDoAventureiro == 0? 0 : vezDoAventureiro * 2) + 10;	
			}
			else {
				valorApelo[ordemAventRodada[vezDoAventureiro]] = 1;
				animacaoFileira = (vezDoAventureiro == 0? 0 : vezDoAventureiro * 2);
			}
			
			efeitoChefeDeFase[ordemAventRodada[vezDoAventureiro]] = 1;
			zerarDx();
		}
	
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							mexe o medidor de apelos ganhos da batalha						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void mexerMedidorApelos() {
		
		(coracao05.getDx() > 0 ? coracao05 : (coracao04.getDx() > 0 ? coracao04 : (coracao03.getDx() > 0 ? coracao03 : (coracao02.getDx() > 0 ? coracao02 : coracao01)))).setDx((coracao05.getDx() > 0 ? coracao05 : (coracao04.getDx() > 0 ? coracao04 : (coracao03.getDx() > 0 ? coracao03 : (coracao02.getDx() > 0 ? coracao02 : coracao01)))).getDx() + comecarAnimacaoCoracao);

		if((coracao05.getDx() > 0 ? coracao05 : (coracao04.getDx() > 0 ? coracao04 : (coracao03.getDx() > 0 ? coracao03 : (coracao02.getDx() > 0 ? coracao02 : coracao01)))).getDx() >= intervaloAnimacao ) {
			(coracao05.getDx() >= intervaloAnimacao ? coracao05 : (coracao04.getDx() >= intervaloAnimacao ? coracao04 : (coracao03.getDx() >= intervaloAnimacao ? coracao03 : (coracao02.getDx() >= intervaloAnimacao ? coracao02 : coracao01)))).setX((coracao05.getDx() >= intervaloAnimacao ? campoBatalha5 : (coracao04.getDx() >= intervaloAnimacao ? campoBatalha4 : (coracao03.getDx() >= intervaloAnimacao ? campoBatalha3 : (coracao02.getDx() >= intervaloAnimacao ? campoBatalha2 : campoBatalha1)))).getX() + 116 + (5 * pontosTotais[ordemAventRodada[(coracao05.getDx() >= intervaloAnimacao ? 4 : (coracao04.getDx() >= intervaloAnimacao ? 3 : (coracao03.getDx() >= intervaloAnimacao ? 2 : (coracao02.getDx() >= intervaloAnimacao ? 1 : 0))))]] < 0? 0 : 5 * pontosTotais[ordemAventRodada[(coracao05.getDx() >= intervaloAnimacao ? 4 : (coracao04.getDx() >= intervaloAnimacao ? 3 : (coracao03.getDx() >= intervaloAnimacao ? 2 : (coracao02.getDx() >= intervaloAnimacao ? 1 : 0))))]]));
			
			(coracao04.getDx() >= intervaloAnimacao ? coracao05 : (coracao03.getDx() >= intervaloAnimacao ? coracao04 : (coracao02.getDx() >= intervaloAnimacao ? coracao03 : coracao02))).setDx((coracao04.getDx() >= intervaloAnimacao ? coracao05 : (coracao03.getDx() >= intervaloAnimacao ? coracao04 : (coracao02.getDx() >= intervaloAnimacao ? coracao03 : coracao02))).getDx() + 1);
			
			if(coracao05.getDx() >= intervaloAnimacao) {
				animacaoFileira = 22;
				zerarDx();
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  			organiza os Valores de acordo com sua performance na rodada da batalha			|
	\ ---------------------------------------------------------------------------------------- */
	
	public void  OrganizarValores() {

		int [] listaProvisoria = new int [5];
		int [] listaProvisoriaTotal = new int [5];
					
		for(int i=0; i<5; i++) {
			listaProvisoria[i] = ordemAventRodada[i];
			listaProvisoriaTotal[i] = pontosTotais[i];
		}
		
		System.out.println("reorganiza??o: " +  ordemAventRodada[0] + " " +  ordemAventRodada[1] + " " +  ordemAventRodada[2] + " " +  ordemAventRodada[3] + " " +  ordemAventRodada[4]);

		for(int i2=0; i2<5; i2++) {
			for(int i=0; i<5; i++) {
				if(listaProvisoriaTotal[listaProvisoria[i]] >= listaProvisoriaTotal[listaProvisoria[0]] && listaProvisoriaTotal[listaProvisoria[i]] >= listaProvisoriaTotal[listaProvisoria[1]] && listaProvisoriaTotal[listaProvisoria[i]] >= listaProvisoriaTotal[listaProvisoria[2]] && listaProvisoriaTotal[listaProvisoria[i]] >= listaProvisoriaTotal[listaProvisoria[3]] && listaProvisoriaTotal[listaProvisoria[i]] >= listaProvisoriaTotal[listaProvisoria[4]]) {
					ordemAventRodada[i2] = listaProvisoria[i];
					listaProvisoriaTotal[listaProvisoria[i]] = -100;
					break;
				}
			}	
		}
				
		for(int i=0; i<5; i++) {
			ordemAventVerdadeira[i] = (ordemAventRodada[0] == i ? 0 : (ordemAventRodada[1] == i ? 1 :  (ordemAventRodada[2] == i ? 2 : (ordemAventRodada[3] == i ? 3 :  4))));
		}
	
		System.out.println("reorganiza??o: " +  ordemAventRodada[0] + " " +  ordemAventRodada[1] + " " +  ordemAventRodada[2] + " " +  ordemAventRodada[3] + " " +  ordemAventRodada[4]);
		
		animacaoFileira = 23;
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  			organiza os C?es  de acordo com sua performance na rodada da batalha			|
	\ ---------------------------------------------------------------------------------------- */
	
	public void OrganizarCampos() {

		coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
		
		if(coracao11.getDx() >= intervaloAnimacao * 2) {
			apagarCoracoes();
			coracao01.setX(campoBatalha1.getX() + 116 + (5 * pontosTotais[ordemAventRodada[0]] < 0? 0 : 5 * pontosTotais[ordemAventRodada[0]]));
			coracao02.setX(campoBatalha2.getX() + 116 + (5 * pontosTotais[ordemAventRodada[1]] < 0? 0 : 5 * pontosTotais[ordemAventRodada[1]]));
			coracao03.setX(campoBatalha3.getX() + 116 + (5 * pontosTotais[ordemAventRodada[2]] < 0? 0 : 5 * pontosTotais[ordemAventRodada[2]]));
			coracao04.setX(campoBatalha4.getX() + 116 + (5 * pontosTotais[ordemAventRodada[3]] < 0? 0 : 5 * pontosTotais[ordemAventRodada[3]]));
			coracao05.setX(campoBatalha5.getX() + 116 + (5 * pontosTotais[ordemAventRodada[4]] < 0? 0 : 5 * pontosTotais[ordemAventRodada[4]]));
			
			System.out.println("total  5 : " + pontosRodada[ordemAventRodada[0]] + " " + pontosRodada[ordemAventRodada[1]] + " " + pontosRodada[ordemAventRodada[2]] + " " + pontosRodada[ordemAventRodada[3]] + " " + pontosRodada[ordemAventRodada[4]]);

			//----------------------------------------------------------------------------
			comecarAnimacaoCoracao = 0;
			for(int i=0; i<5; i++) {
				if(ordemAventRodada[i] == aventureiro) {
					posicaoAventureiro = i;
				}
				
				valorApelo[i] = 0;
				valorInterferencia[i] = 0;
				tipoInterferencia[i] = -1;
				tipoAtaque[i] = 0;
				
				for(int i2=0; i2<5; i2++) {
					interferenciasRecebidas[i][i2] = 0;
				}
				
				pontosRodada[i] = 0;
				pontosAtuaisDaRodada[i] = 0;
				

			}
			
			//----------------------------------------------------------------------------

			zerarDx();
			
			repintarCampoBatalha();
			
			if(contEtapasBatalha == 5) {
				vezDoAventureiro=0;
				animacaoFileira = 24;
				
			} else {
				animacaoFileira = -1;
				vezDoAventureiro=0;
			}
		}
		
		if(atualizarNomeHabili == false) {
			atualizarNomeHabili = true;
			
			nomeHabAnterior = selecaoNomeHab;
			
			nomeHabilidade2.load(caminho + "res\\batalha\\" + (nomeHabAnterior == 1 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade3.load(caminho + "res\\batalha\\" + (nomeHabAnterior == 2 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade4.load(caminho + "res\\batalha\\" + (nomeHabAnterior == 3 ? "nomeHabilidadeUsada.png" : "nomeHabilidade.png"));
			nomeHabilidade1.load(caminho + "res\\batalha\\" + nomeAventureiro[aventureiro]+ "\\" + (nomeHabAnterior == 0 ? "nomeHabilidadeUsadaSelecionada.png" : "nomeHabilidadeSelecionado.png"));
			
			selecaoNomeHab =0;
		}
		
		itensDoApelo();
		
		textoDescricao1.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
		textoDescricao2.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
		textoDescricao3.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
		textoDescricao4.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
		textoDescricao5.setTexto(ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][4]);

	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						determina o vencedor da batalha e o parabeniza						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void vencedor() {
		comecarAnimacaoCoracao =1;
		coracao11.setDx(coracao11.getDx() + comecarAnimacaoCoracao);
		
		if(coracao11.getDx() >= intervaloAnimacao * 5) {
			
			aparecerVencedor = true;
			
			coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
			if(coracao11.getDx() >= intervaloAnimacao * 5) {
				zerarDx();
				comecarAnimacaoCoracao = 0;
				contEtapasBatalha = 6;
			}	
		}
	}
	
	
	/* ---------------------------------------------------------------------------------------- \
	|  								aciona as anima??es das habilidades							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void gifApresentacao(int avent, int numApelo) {

		// ------------------------------------------ IGNIS ----------------------------------------
		if(avent == 0 && gifApelos[0][numApelo] == 1) {
			
			if(animacao.getDx() >= 2 && animacao.getDx() <= 48 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\0.png");
			
			} else if(animacao.getDx() >= 5 && animacao.getDx() <= 48 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\1.png");
			
			} else if(animacao.getDx() == 50) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\2.png");
				
			} else if(animacao.getDx() == 56) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\3.png");
				
			} else if(animacao.getDx() >= 66 && animacao.getDx() <= 78 && animacao.getDx() % 6 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\4.png");
				
			} else if(animacao.getDx() >= 63 && animacao.getDx() <= 81 && animacao.getDx() % 3 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\5.png");
		
			} else if(animacao.getDx() >= 84 && animacao.getDx() <= 114 && animacao.getDx() % 6 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\6.png");
				
			} else if(animacao.getDx() >= 87 && animacao.getDx() <= 111 && animacao.getDx() % 3 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\7.png");
			
			}  else if(animacao.getDx() >= 116 && animacao.getDx() <= 140 && animacao.getDx() % 4 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\8.png");
			
			} else if(animacao.getDx() >= 118 && animacao.getDx() <= 138 && animacao.getDx() % 2 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\9.png");
				
			}
				
		} else if(avent == 0 && gifApelos[0][numApelo] == 2) {
			
			if(animacao.getDx() >= 6 && animacao.getDx() <= 39 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\0.png");
			
			} else if(animacao.getDx() >= 0 && animacao.getDx() <= 39 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\1.png");
			
			} else if(animacao.getDx() == 40 ) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\2.png");
				animacaoObj1.setY(- 200);
				
			} else if(animacao.getDx() > 43 && animacao.getDx() <= 70) {
				animacaoObj1.setY(animacaoObj1.getY() - 10);
			
			} else if(animacao.getDx() > 70 && animacao.getDx() < 82) {
				animacaoObj1.setY(animacaoObj1.getY() + 10);
				
			} else if(animacao.getDx() == 82) {
				animacaoObj1.setY(tamanhoContorno);
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\3.png");
				
			} else if(animacao.getDx() >= 90 && animacao.getDx() <= 96 && animacao.getDx() % 6 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\4.png");
			
			} else if(animacao.getDx() >= 93 && animacao.getDx() <= 99 && animacao.getDx() % 3 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\5.png");
				
			} else if(animacao.getDx() == 102) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\6.png");
			
			} else if(animacao.getDx() == 105) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\7.png");
			
			} else if(animacao.getDx() >= 108 && animacao.getDx() <= 126 && animacao.getDx() % 6 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\8.png");
				if(animacao.getDx() == 108) {animacaoObj1.setY(- 200);}
			
			} else if(animacao.getDx() >= 111 && animacao.getDx() <= 123 && animacao.getDx() % 3 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\9.png");
				
			} else if(animacao.getDx() == 129) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\10.png");
			}
			
			if(animacao.getDx() >= 108 && animacao.getDx() <= 126) {
				animacaoObj1.setY(animacaoObj1.getY() - 10);
			}
				
		} else if(avent == 0 && gifApelos[0][numApelo] == 3) {
			
			if(animacao.getDx() >= 2 && animacao.getDx() < 60 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\0.png");
			
			} else if(animacao.getDx() >= 5 && animacao.getDx() < 60 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\1.png");
			
			} else if(animacao.getDx() == 60) {
				animacao.load(caminho + "res\\batalha\\fundoAnimacao1.png");
				animacaoObj1.setX(tamanhoContorno + 300);
				animacaoObj2.setX(tamanhoContorno - 300);
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\3.png");
				animacaoObj2.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\2.png");
			}
			if(animacao.getDx() >= 60 && animacao.getDx() <= 84) {
				animacaoObj1.setX(animacaoObj1.getX() - 12);
				animacaoObj2.setX(animacaoObj2.getX() + 12);
				
			} else if(animacao.getDx() >= 90 && animacao.getDx() % 10 == 0) {
				
				if(animacao.getDx() == 90) {
					animacaoObj2.setImagem(null);
					animacaoObj1.setX(tamanhoContorno);
					animacao.load(caminho + "res\\batalha\\fundoAnimacao1.png");

				}else if(animacao.getDx() == 100) {
					animacao.setY(-126);
					animacao.setX(760/2 - 1130/2 + 50);
					animacao.load(caminho + "res\\batalha\\fundoAnimacao2.png");
					
				}else if(animacao.getDx() == 120) {
					animacao.setY(-30);
					animacao.setX(760/2 - 1130/2 + 30);
					animacao.load(caminho + "res\\batalha\\fundoAnimacao2.png");
				}
				
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\" + (animacao.getDx()/10 - 4) + ".png");
			
			}
		
		} else if(avent == 0 && gifApelos[0][numApelo] == 4) {
			
			if(animacao.getDx() >= 2 && animacao.getDx() <= 48 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\0.png");
			
			} else if(animacao.getDx() >= 5 && animacao.getDx() <= 48 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\1.png");
			
			} else if(animacao.getDx() == 50) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\fundo1.png");
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\2.png");
			
			}else if(animacao.getDx() >= 56 && animacao.getDx() <= 81) {
					animacaoObj1.setLarg(animacaoObj1.getLarg() + 8);
					animacaoObj1.setAlt(animacaoObj1.getAlt() + 4);
					animacao.setLarg(animacaoObj1.getLarg() + 8);
					animacao.setAlt(animacaoObj1.getAlt() + 4);
					
					animacaoObj1.setX(animacaoObj1.getX() - 4);
					animacaoObj1.setY(animacaoObj1.getY() - 2);
					animacao.setX(animacaoObj1.getX() - 4);
					animacao.setY(animacaoObj1.getY() - 2);
					
			} else if(animacao.getDx() == 82) {
			
				animacao = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
				animacao.load(caminho + "res\\batalha\\fundoAnimacao2.png");
				
				animacaoObj1 = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\4.png");
				animacao.setDx(82);

			} else if(animacao.getDx() == 87) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\5.png");
			
			} else if(animacao.getDx() >= 92 && animacao.getDx() <= 104 && animacao.getDx() % 4 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\6.png");
			
			} else if(animacao.getDx() >= 94 && animacao.getDx() <= 106 && animacao.getDx() % 2 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\7.png");
			
			} else if(animacao.getDx() == 110) {
				animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\fundo1.png");
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\2.png");
				
			} else if(animacao.getDx() == 120) {
				animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\" + (gifApelos[0][numApelo]) + "\\9.png");
				
			}
				
		
		}
		// ------------------------------------------ AYLA ----------------------------------------
		
		else if(avent == 1 && gifApelos[1][numApelo] == 1) {

			if(animacao.getDx() == 20) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\0.png");
				animacaoObj1.setX(200);
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\2.png");
				animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\3.png");
			
			} else if(animacao.getDx() >= 21 && animacao.getDx() <= 48) {
				animacaoObj1.setX(animacaoObj1.getX() - 2);
				
				if(animacao.getDx() % 6 == 0) {
					animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\0.png");
				} else if(animacao.getDx() % 3 == 0) {
					animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\1.png");
				}
			
			} else if(animacao.getDx() == 49) {
				animacaoObj1.setImagem(null);
				animacaoObj2.setImagem(null);
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\4.png");
			
			} else if(animacao.getDx() >= 54 && animacao.getDx() <= 60 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\5.png");
			
			} else if(animacao.getDx() >= 51 && animacao.getDx() <= 63 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\6.png");
			
			} else if(animacao.getDx() >= 66 && animacao.getDx() <= 78 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\7.png");
			
			} else if(animacao.getDx() >= 69 && animacao.getDx() <= 75 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\8.png");
			
			} else if(animacao.getDx() >= 84 && animacao.getDx() <= 90 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\9.png");
			
			} else if(animacao.getDx() >= 81 && animacao.getDx() <= 93 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\10.png");
			
			} else if(animacao.getDx() >= 96 && animacao.getDx() <= 108 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\11.png");
			
			} else if(animacao.getDx() >= 99 && animacao.getDx() <= 105 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\12.png");
			
			} else if(animacao.getDx() >= 108 && animacao.getDx() <= 126 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\13.png");
			
			} else if(animacao.getDx() >= 111 && animacao.getDx() <= 123 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\14.png");
			
			} else if(animacao.getDx() == 129 || animacao.getDx() == 138) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\15.png");
			
			} else if(animacao.getDx() == 132) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\16.png");
			
			}
			
		}
		else if(avent == 1 && gifApelos[1][numApelo] == 6) {

			if(animacao.getDx() == 20) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\0.png");
				animacaoObj1.setX(200);
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\2.png");
				animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\3.png");
			
			} else if(animacao.getDx() >= 21 && animacao.getDx() <= 48) {
				animacaoObj1.setX(animacaoObj1.getX() - 2);
				
				if(animacao.getDx() % 6 == 0) {
					animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\0.png");
				} else if(animacao.getDx() % 3 == 0) {
					animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\1.png");
				}
			
			} else if(animacao.getDx() == 49) {
				animacao.setX(-40);
				animacao.setY(-90);
				animacao.load(caminho + "res\\batalha\\fundoAnimacao2.png");
				animacaoObj1.setX(tamanhoContorno + 200);
				animacaoObj2.setX(tamanhoContorno - 200);
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\5.png");
				animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\4.png");
			}
			if(animacao.getDx() >= 49 && animacao.getDx() <= 63) {
				animacaoObj1.setX(animacaoObj1.getX() - 12);
				animacaoObj2.setX(animacaoObj2.getX() + 12);
			
			} else if(animacao.getDx() == 68) {
				animacaoObj1.setImagem(null);
				animacaoObj2.setImagem(null);
				animacao.setX(tamanhoContorno);
				animacao.setY(tamanhoContorno + 2);
				animacaoObj1.setX(tamanhoContorno);
				animacaoObj1.setY(tamanhoContorno + 2);
				animacaoObj2.setX(tamanhoContorno);
				animacaoObj2.setY(tamanhoContorno + 2);
			}
			
			if(animacao.getDx() >= 68 && animacao.getDx() <= 76 && animacao.getDx() % 4 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\" + (animacao.getDx()/4 == 17 ? 8 : (animacao.getDx()/4 == 18 ? 9 : 10)) + ".png");
			
			} else if(animacao.getDx() >= 78 && animacao.getDx() <= 102 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\11.png");
				
			} else if(animacao.getDx() >= 81 && animacao.getDx() <= 105 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\12.png");
			}
			
			if(animacao.getDx() >= 72 && animacao.getDx() <= 106 && animacao.getDx() % 8 == 0) {
				animacaoObj1.setY(animacaoObj1.getY() + 4);
				
			} else if(animacao.getDx() >= 74 && animacao.getDx() <= 106 && animacao.getDx() % 4 == 0) {
				animacaoObj1.setY(animacaoObj1.getY() - 4);
			}
			
			if(animacao.getDx() >= 72 && animacao.getDx() <= 106 && animacao.getDx() % 4 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\6.png");
				
			} else if(animacao.getDx() >= 74 && animacao.getDx() <= 106 && animacao.getDx() % 2 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\7.png");
			}
			
			if(animacao.getDx() >= 110 && animacao.getDx() <= 140 && animacao.getDx() % 8 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\13.png");
				
			} else if(animacao.getDx() >= 106 && animacao.getDx() <= 140 && animacao.getDx() % 4 == 0) {
				animacaoObj1.setImagem(null);
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\14.png");
			}
			
			
		} else if(avent == 1 && gifApelos[1][numApelo] == 8) {

			if(animacao.getDx() == 20) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\0.png");
				animacaoObj1.setX(200);
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\2.png");
				animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\3.png");
			
			} else if(animacao.getDx() >= 21 && animacao.getDx() <= 48) {
				animacaoObj1.setX(animacaoObj1.getX() - 2);
				
				if(animacao.getDx() % 6 == 0) {
					animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\0.png");
				} else if(animacao.getDx() % 3 == 0) {
					animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\1.png");
				}
			
			} else if(animacao.getDx() == 49) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\28.png");
				animacaoObj1.setX(tamanhoContorno + 695);
				animacaoObj1.setY(animacaoObj1.getY() + 44);
				animacaoObj2.setX(tamanhoContorno + 650);
				animacaoObj2.setY(animacaoObj2.getY() - 50);
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\4.png");
				animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\ayla7.png");
			}
			
			if(animacao.getDx() >= 50 && animacao.getDx() <= 102 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\" + (animacao.getDx() <= 60 ? 28 : (animacao.getDx() <= 66 ? 30 :(animacao.getDx() <= 72 ? 32 : (animacao.getDx() <= 75 ? 34 : (animacao.getDx() <= 81 ? 36 : (animacao.getDx() <= 84 ? 38 : (animacao.getDx() <= 87 ? 40 : (animacao.getDx() <= 93 ? 42 : 44)))))))) + ".png");
				
			} else if(animacao.getDx() >= 50 && animacao.getDx() <= 102 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\" + (animacao.getDx() <= 60 ? 29 : (animacao.getDx() <= 66 ? 31 :(animacao.getDx() <= 72 ? 33 : (animacao.getDx() <= 75 ? 35 : (animacao.getDx() <= 81 ? 37 : (animacao.getDx() <= 84 ? 39 : (animacao.getDx() <= 87 ? 41 : (animacao.getDx() <= 93 ? 43 : 44)))))))) + ".png");
			}
			
			if(animacao.getDx() >= 49 && animacao.getDx() <= 102) {
				
				animacaoObj1.setX(animacaoObj1.getX() - 16);
				animacaoObj2.setX(animacaoObj2.getX() - 16);	
				
				contMovimentoAyla ++;
								
				if(contMovimentoAyla == 16 || contMovimentoAyla == 10 || contMovimentoAyla == 26 || contMovimentoAyla == 32 || contMovimentoAyla == 36){
					animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\ayla9.png");
								
				} else if(contMovimentoAyla == 6 || contMovimentoAyla == 2 || contMovimentoAyla == 22 || contMovimentoAyla == 26 || contMovimentoAyla == 36) {
					animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\ayla7.png");
				
				} else if(contMovimentoAyla == 14 || contMovimentoAyla == 8 || contMovimentoAyla == 4 || contMovimentoAyla == 20 || contMovimentoAyla == 24 || contMovimentoAyla == 30 || contMovimentoAyla == 34 || contMovimentoAyla == 40) {
					animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\ayla8.png");
				}
				
				
				if ((contMovimentoAyla >= 7 && contMovimentoAyla <= 12) || (contMovimentoAyla >= 19 && contMovimentoAyla <= 24) || (contMovimentoAyla >= 31 && contMovimentoAyla <= 36)) {
					animacaoObj2.setY(animacaoObj2.getY() + 4);
					animacaoObj1.setY(animacaoObj1.getY() + 4);
					
				} else if ((contMovimentoAyla >= 1 && contMovimentoAyla <= 6) || (contMovimentoAyla >= 13 && contMovimentoAyla <= 18) || (contMovimentoAyla >= 25 && contMovimentoAyla <= 30) || (contMovimentoAyla >= 37 && contMovimentoAyla <= 40)) {
					animacaoObj2.setY(animacaoObj2.getY() - 4);
					animacaoObj1.setY(animacaoObj1.getY() - 4);
				}
				
				if(contMovimentoAyla == 40){
					animacaoObj2.setY(animacaoObj2.getY() + 16);
					animacaoObj1.setY(animacaoObj1.getY() + 16);
					contMovimentoAyla = 0;
				}
			}
			
			if(animacao.getDx() >= 52 && animacao.getDx() <= 58) {
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\" + (animacao.getDx() == 52 ? 5 : (animacao.getDx() == 53 ? 6 : (animacao.getDx() == 54 ? 7 : (animacao.getDx() == 55 ? 8 : (animacao.getDx() == 56 ? 9 : (animacao.getDx() == 57 ? 10 : (animacao.getDx() == 58 ? 11 : 12))))))) + ".png");
			
			} else if(animacao.getDx() >= 50 && animacao.getDx() <= 94 && animacao.getDx() % 2 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\12.png");
			
			} else if(animacao.getDx() >= 59 && animacao.getDx() <= 93) {
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\13.png");
			
			}
			
			if(animacao.getDx() == 58) {
				animacaoObj4.setY(animacaoObj4.getY() + 110);
				animacaoObj4.setX(animacaoObj4.getX() - 140);
				animacaoObj3.setY(animacaoObj4.getY() + 94);
				animacaoObj3.setX(animacaoObj4.getX() - 64);
				animacaoObj3.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\14.png");
				animacaoObj4.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\ayla2.png");
			
			} else if(animacao.getDx() >= 58 && animacao.getDx() <= 102) {
				animacaoObj3.setX(animacaoObj3.getX() + 20);
				animacaoObj4.setX(animacaoObj4.getX() + 20);	
				
				
				if(contMovimentoAyla == 16 || contMovimentoAyla == 10 || contMovimentoAyla == 26 || contMovimentoAyla == 32 || contMovimentoAyla == 36){
					animacaoObj4.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\ayla4.png");
								
				} else if(contMovimentoAyla == 6 || contMovimentoAyla == 2 || contMovimentoAyla == 22 || contMovimentoAyla == 26 || contMovimentoAyla == 36) {
					animacaoObj4.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\ayla2.png");
				
				} else if(contMovimentoAyla == 14 || contMovimentoAyla == 8 || contMovimentoAyla == 4 || contMovimentoAyla == 20 || contMovimentoAyla == 24 || contMovimentoAyla == 30 || contMovimentoAyla == 34 || contMovimentoAyla == 40) {
					animacaoObj4.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\ayla3.png");
				}
				
				
				if ((contMovimentoAyla >= 7 && contMovimentoAyla <= 12) || (contMovimentoAyla >= 19 && contMovimentoAyla <= 24) || (contMovimentoAyla >= 31 && contMovimentoAyla <= 36)) {
					animacaoObj4.setY(animacaoObj4.getY() + 4);
					animacaoObj3.setY(animacaoObj3.getY() + 4);
					
				} else if ((contMovimentoAyla >= 1 && contMovimentoAyla <= 6) || (contMovimentoAyla >= 13 && contMovimentoAyla <= 18) || (contMovimentoAyla >= 25 && contMovimentoAyla <= 30) || (contMovimentoAyla >= 37 && contMovimentoAyla <= 40)) {
					animacaoObj4.setY(animacaoObj4.getY() - 4);
					animacaoObj3.setY(animacaoObj3.getY() - 4);

				}
				
				if(contMovimentoAyla == 39){
					animacaoObj4.setY(animacaoObj4.getY() + 12);
					animacaoObj3.setY(animacaoObj3.getY() + 12);

				}
			}
			
			if(animacao.getDx() >= 58 && animacao.getDx() <= 64 && animacao.getDx() % 2 == 0) {
				animacaoObj3.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\" + (animacao.getDx() == 58 ? 15 : (animacao.getDx() == 59 ? 16 : (animacao.getDx() == 60 ? 17 : (animacao.getDx() == 61 ? 18 : (animacao.getDx() == 62 ? 19 : (animacao.getDx() == 63 ? 20 : (animacao.getDx() == 64 ? 21 : 22))))))) + ".png");
			
			} else if(animacao.getDx() >= 66 && animacao.getDx() <= 94 && animacao.getDx() % 2 == 0) {
				animacaoObj3.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\22.png");
			
			} else if(animacao.getDx() >= 65 && animacao.getDx() <= 93) {
				animacaoObj3.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\23.png");
			}
			
			if(animacao.getDx() == 102) {
				animacaoObj1.setImagem(null);animacaoObj2.setImagem(null); animacaoObj3.setImagem(null);	animacaoObj4.setImagem(null);
				animacaoObj1.setX(tamanhoContorno); animacaoObj1.setY(tamanhoContorno + 2);
				animacaoObj2.setX(tamanhoContorno); animacaoObj2.setY(tamanhoContorno + 2);
				animacaoObj3.setX(tamanhoContorno); animacaoObj3.setY(tamanhoContorno + 2);
				animacaoObj4.setX(tamanhoContorno); animacaoObj4.setY(tamanhoContorno + 2);
			}
			
			if(animacao.getDx() >= 102 && animacao.getDx() <= 120 && animacao.getDx() % 2 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\" + (animacao.getDx() == 102 || animacao.getDx() == 106 || animacao.getDx() == 110 || animacao.getDx() == 114 || animacao.getDx() == 118 ? 24 : 25) + ".png");
				
			} else if(animacao.getDx() == 122) {
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\26.png");
				
			} else if(animacao.getDx() == 124) {
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\27.png");
				animacaoObj2.setY(animacaoObj2.getY() - 28);
				animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\45.png");
				
			} else if(animacao.getDx() >= 126 && animacao.getDx() <= 140) {
				animacaoObj2.setY(animacaoObj2.getY() + 2);
				contMovimentoAyla = 0;
			}
			
		}else if(avent == 1 && gifApelos[1][numApelo] == 4) {

			if(animacao.getDx() == 20) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\0.png");
				animacaoObj1.setX(200);
				animacaoObj1.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\2.png");
				animacaoObj2.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\3.png");
			
			} else if(animacao.getDx() >= 21 && animacao.getDx() <= 48) {
				animacaoObj1.setX(animacaoObj1.getX() - 2);
				
				if(animacao.getDx() % 6 == 0) {
					animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\0.png");
				} else if(animacao.getDx() % 3 == 0) {
					animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\1.png");
				}
			
			} else if(animacao.getDx() == 49) {
				animacaoObj1.setImagem(null);
				animacaoObj2.setImagem(null);
				
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\4.png");
				animacao.setLarg((int)Math.ceil(760 * animacao.getTamanhoTela()));
				animacao.setAlt((int)Math.ceil(380 * animacao.getTamanhoTela()));
				
				animacao.setX(tamanhoContorno);
				animacao.setY(tamanhoContorno + 2); 
				
			}else if(animacao.getDx() >= 50 && animacao.getDx() < 99 && (animacao.getDx() % 30 == 0 || animacao.getDx() % 30 == 4)) {
				animacao.setImagem((new ImageIcon(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\6.png")).getImage());
				
			} else if(animacao.getDx() >= 50 && animacao.getDx() < 99 && animacao.getDx() % 4 == 0) {
				animacao.setImagem((new ImageIcon(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\5.png")).getImage());
				
			} else if(animacao.getDx() >= 50 && animacao.getDx() < 99 && (animacao.getDx() % 2 == 0 || animacao.getDx() % 30 == 3)) {
				animacao.setImagem((new ImageIcon(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\4.png")).getImage());
			}
			
			if(animacao.getDx() >= 52 && animacao.getDx() < 99) {

				animacao.setLarg(animacao.getLarg() + 4);
				animacao.setAlt(animacao.getAlt() + 2);
				
				animacao.setX(animacao.getX() - 1);
				
			} else if(animacao.getDx() == 99) {
				animacao.setX(tamanhoContorno);
				animacao.setY(tamanhoContorno + 2); 
			}
			
			if(animacao.getDx() >= 99 && animacao.getDx() <= 140 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (gifApelos[1][numApelo]) + "\\" + (animacao.getDx() == 99 || animacao.getDx() == 108 || animacao.getDx() == 117 || animacao.getDx() == 126 || animacao.getDx() == 135 ? 7 : (animacao.getDx() == 102 || animacao.getDx() == 111 || animacao.getDx() == 120 || animacao.getDx() == 129 || animacao.getDx() == 138 ? 8 : 9)) + ".png");
			}
			
		}
		
		// ------------------------------------------ KIKI ----------------------------------------
		
		else if(avent == 3 && gifApelos[3][numApelo] == 1) {
			
			if(animacao.getDx() >= 0 && animacao.getDx() <= 48 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\0.png");
			
			} else if(animacao.getDx() >= 0 && animacao.getDx() <= 48 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\1.png");
			
			} else if(animacao.getDx() >= 50 && animacao.getDx() <= 70 && animacao.getDx() % 4 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\3.png");
				
			} else if(animacao.getDx() >= 50 && animacao.getDx() <= 70 && animacao.getDx() % 2 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\2.png");
			
			} else if(animacao.getDx() == 72) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\4.png");
				
			} else if(animacao.getDx() >= 74 && animacao.getDx() <= 124 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\5.png");
				
			} else if(animacao.getDx() >= 74 && animacao.getDx() <= 124 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\4.png");
			}
			
			if(animacao.getDx() == 90) {
				animacaoObj1.setY(-200);
				animacaoObj1.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\8.png");
			
			} else if(animacao.getDx() >= 90 && animacao.getDx() <= 125) {
				animacaoObj1.setY(animacaoObj1.getY() + 6);
			
			} else if(animacao.getDx() == 126) {
				animacaoObj1.setImagem(null);
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\6.png");
			
			} else if(animacao.getDx() == 127) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\7.png");
			}
			
		} else if(avent == 3 && gifApelos[3][numApelo] == 7) {
			
			if(animacao.getDx() >= 0 && animacao.getDx() <= 48 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\0.png");
			
			} else if(animacao.getDx() >= 0 && animacao.getDx() <= 48 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\1.png");
			
			} else if(animacao.getDx() == 52 || animacao.getDx() == 58) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\" + (animacao.getDx() == 52 ? 2 : 3) + ".png");
				
			} else if(animacao.getDx() >= 66 && animacao.getDx() <= 90 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\" + (animacao.getDx() == 66 || animacao.getDx() == 78 || animacao.getDx() == 90 ? 4 : (animacao.getDx() == 69 || animacao.getDx() == 81 ? 5 : (animacao.getDx() == 72 || animacao.getDx() == 84 ? 6 : 7))) + ".png");
				
			} else if(animacao.getDx() >= 94 && animacao.getDx() <= 110 && animacao.getDx() % 2 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\" + (animacao.getDx() == 94 ? 8 : (animacao.getDx() == 96 || animacao.getDx() == 100 || animacao.getDx() == 104 || animacao.getDx() == 108 ? 9 : 10)) + ".png");
			
			} else if(animacao.getDx() >= 114 && animacao.getDx() <= 120 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\" + (animacao.getDx() == 114 ? 11 : (animacao.getDx() == 117 ? 12 : 13)) + ".png");
			
			} else if(animacao.getDx() >= 123 && animacao.getDx() <= 130 && animacao.getDx() % 2 == 0) {
					animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\15.png");
				
			} else if(animacao.getDx() >= 123 && animacao.getDx() <= 130) {
					animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\14.png");
				
			} else if(animacao.getDx() == 131) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\16.png");
			
			}
		
		} else if(avent == 3 && gifApelos[3][numApelo] == 4) {
			
			if(animacao.getDx() >= 0 && animacao.getDx() <= 48 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\0.png");
			
			} else if(animacao.getDx() >= 0 && animacao.getDx() <= 48 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\1.png");
			
			} else if(animacao.getDx() == 52 || animacao.getDx() == 58 || animacao.getDx() == 64) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\" + (animacao.getDx() == 52 ? 2 : (animacao.getDx() == 58 ? 3 : 4)) + ".png");
				
			} else if(animacao.getDx() >= 66 && animacao.getDx() <= 87 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\5.png");
				
			} else if(animacao.getDx() >= 66 && animacao.getDx() <= 87 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\6.png");
			
			} else if(animacao.getDx() == 90) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\7.png");
				animacaoObj1.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\9.png");
				animacaoObj2.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\10.png");
			
			} else if(animacao.getDx() >= 93 && animacao.getDx() <= 123 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\7.png");
				
			} else if(animacao.getDx() >= 93 && animacao.getDx() <= 123 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\8.png");
			}
			
			if(animacao.getDx() >= 93 && animacao.getDx() <= 123) {
				animacaoObj1.setY(animacaoObj1.getY() + 8);
				
			} else if(animacao.getDx() == 124) {
				animacaoObj1.setImagem(null);
				animacaoObj2.setImagem(null);
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\11.png");
				
			} else if(animacao.getDx() == 128) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\12.png");
			
			} else if(animacao.getDx() == 132) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\13.png");
			
			}  else if(animacao.getDx() == 136) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\14.png");
			}
			
		} else if(avent == 3 && gifApelos[3][numApelo] == 2) {
			
			if(animacao.getDx() >= 0 && animacao.getDx() <= 48 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\0.png");
			
			} else if(animacao.getDx() >= 0 && animacao.getDx() <= 48 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\1.png");
			
			} else if(animacao.getDx() == 52) {
				animacaoObj1.setY(animacaoObj1.getY() + 30);
				animacaoObj3.setY(animacaoObj3.getY() + 30);
				animacaoObj2.setY(animacaoObj2.getY() + 62);
				
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\3.png");
				animacaoObj1.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\5.png");
				animacaoObj2.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\6.png");
				animacaoObj3.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\4.png");
				animacaoObj4.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\2.png");
			
			} else if(animacao.getDx() > 55 && animacao.getDx() <= 66) {
				animacaoObj1.setY(animacaoObj1.getY() - 3);
				animacaoObj3.setY(animacaoObj3.getY() - 3);
				animacaoObj2.setY(animacaoObj2.getY() - 3);
			}
			
			if(animacao.getDx() > 63 && animacao.getDx() <= 74) {
				animacaoObj2.setY(animacaoObj2.getY() - 3);
			}
			
			if(animacao.getDx() >= 72 && animacao.getDx() <= 90 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\7.png");
				animacaoObj2.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\11.png");
				animacaoObj4.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\9.png");
			
			} else if(animacao.getDx() >= 72 && animacao.getDx() <= 90 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\8.png");
				animacaoObj2.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\6.png");
				animacaoObj4.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\10.png");
			
			} else if(animacao.getDx() == 93) {
				animacaoObj2.setImagem(null);
				animacaoObj4.setImagem(null);
				
				animacaoObj1.setX(tamanhoContorno); animacaoObj1.setY(tamanhoContorno + 2);
				animacaoObj2.setX(tamanhoContorno + 20); animacaoObj2.setY(230);
				animacaoObj3.setX(tamanhoContorno - 20); animacaoObj3.setY(tamanhoContorno + 2);
				
				animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\12.png");
				animacaoObj1.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\13.png");
				animacaoObj3.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\15.png");
			
			} else if(animacao.getDx() >= 97 && animacao.getDx() <= 118) {
				animacao.setLarg(animacao.getLarg() + 22); animacao.setAlt(animacao.getAlt() + 11);
				animacaoObj1.setLarg(animacao.getLarg()); animacaoObj1.setAlt(animacao.getAlt());
				animacaoObj3.setLarg(animacao.getLarg()); animacaoObj3.setAlt(animacao.getAlt());
			}
			
			if(animacao.getDx() == 114) {
				animacaoObj2.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\14.png");
				animacaoObj2.setLarg(animacao.getLarg());
				animacaoObj2.setAlt(animacao.getAlt());
				
			} else if(animacao.getDx() > 115 && animacao.getDx() <= 140) {
				animacaoObj2.setY(animacaoObj2.getY() + (animacao.getDx() <= 127 || (animacao.getDx() >= 138) ? -20 : (animacao.getDx() >= 130 && animacao.getDx() <= 134 ? 22 : 0)));
				animacaoObj2.setX(animacaoObj2.getX() + ((animacao.getDx() >= 126 && animacao.getDx() <= 130) ? 22 : (animacao.getDx() <= 134 || animacao.getDx() > 138 ? 0 : -22)));
				
				if(animacao.getDx() >= 119) { animacaoObj1.setX(animacaoObj1.getX() + (animacao.getDx() % 2 == 0 ? 5 : -5));}
			}
			
			if(animacao.getDx() == 132) {
				animacaoObj1.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (gifApelos[3][numApelo]) + "\\16.png");
				animacaoObj1.setLarg(animacao.getLarg()); animacaoObj1.setAlt(animacao.getAlt());
			}
			
			if(animacao.getDx() >= 119 && animacao.getDx() <= 140) {
				animacao.setLarg(animacao.getLarg() + 8); animacao.setAlt(animacao.getAlt() + 4);
				animacaoObj1.setLarg(animacao.getLarg()); animacaoObj1.setAlt(animacao.getAlt());
				animacaoObj3.setLarg(animacao.getLarg()); animacaoObj3.setAlt(animacao.getAlt());
			}
			
		}
		
		// ------------------------------------------ ARIUS ----------------------------------------
		
		else if(avent == 4 && gifApelos[4][numApelo] == 3) {
			
			if(animacao.getDx() == 20) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\0.png");
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\1.png");
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\2.png");
				animacaoObj3.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\4.png");
			
			} else if(animacao.getDx() >= 21 && animacao.getDx() <= 57) {
				animacaoObj1.setY(animacaoObj1.getY() - 1);
				
				if(animacao.getDx() % 6 == 0) {
					animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\2.png");
				} else if(animacao.getDx() % 3 == 0) {
					animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\3.png");
				}
				
				animacaoObj3.setY(animacaoObj3.getY() + (animacao.getDx() % 2 == 0 && ((animacao.getDx() >= 22 && animacao.getDx() <= 28) || (animacao.getDx() >= 46)) ? 2 : (animacao.getDx() % 2 == 0 && (animacao.getDx() >= 30 && animacao.getDx() <= 44) ? -2 : 0)));
			
			} else if(animacao.getDx() == 58) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\5.png");
				animacaoObj1.setImagem(null); animacaoObj1.setY(animacao.getY());
				animacaoObj2.setImagem(null);
				animacaoObj3.setImagem(null);
				
			} else if(animacao.getDx() >= 61 && animacao.getDx() <= 72) {
				if(animacao.getDx() == 61) {
					animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\6.png");
				} else if(animacao.getDx() == 62) {
					animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\7.png");
				} else if(animacao.getDx() == 64) {
					animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\8.png");
				} else if(animacao.getDx() == 65) {
					animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\9.png");
				} else if(animacao.getDx() == 67) {
					animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\10.png");
				} else if(animacao.getDx() == 68) {
					animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\11.png");
				} else if(animacao.getDx() == 70) {
					animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\12.png");
				} else if(animacao.getDx() == 72) {
					animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\13.png");
				}
			} else if(animacao.getDx() >= 74 && animacao.getDx() <= 84 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\14.png");
			
			} else if(animacao.getDx() >= 74 && animacao.getDx() <= 84 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\15.png");
			
			} else if(animacao.getDx() >= 86 && animacao.getDx() <= 102 && animacao.getDx() % 4 == 0) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\16.png");
			
			} else if(animacao.getDx() >= 86 && animacao.getDx() <= 102 && animacao.getDx() % 2 == 0) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\17.png");
			
			} else if(animacao.getDx() == 105) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\18.png");
				
			} else if(animacao.getDx() >= 111 && animacao.getDx() <= 140 && animacao.getDx() % 6 == 0) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\20.png");
			
			} else if(animacao.getDx() >= 111 && animacao.getDx() <= 140 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\19.png");
			}
			
		} else if(avent == 4 && gifApelos[4][numApelo] == 6) {
			
			if(animacao.getDx() == 20) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\0.png");
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\1.png");
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\2.png");
				animacaoObj3.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\4.png");
			
			} else if(animacao.getDx() >= 21 && animacao.getDx() <= 58) {
				animacaoObj1.setY(animacaoObj1.getY() - 1);
				
				if(animacao.getDx() % 6 == 0) {
					animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\2.png");
				} else if(animacao.getDx() % 3 == 0) {
					animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\3.png");
				}
				
				animacaoObj3.setY(animacaoObj3.getY() + (animacao.getDx() % 2 == 0 && ((animacao.getDx() >= 22 && animacao.getDx() <= 28) || (animacao.getDx() >= 46)) ? 2 : (animacao.getDx() % 2 == 0 && (animacao.getDx() >= 30 && animacao.getDx() <= 44) ? -2 : 0)));
			
			} else if(animacao.getDx() == 59) {
				animacaoObj1.setX(tamanhoContorno); animacaoObj1.setY(tamanhoContorno + 2);
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\5.png");
				animacaoObj2.setImagem(null);
				animacaoObj3.setImagem(null);
			
			} else if(animacao.getDx() >= 60 && animacao.getDx() <= 92) {
				animacao.setLarg(animacao.getLarg() + 4);
				animacao.setAlt(animacao.getAlt() + 2);
				
				if(animacao.getDx() % 4 == 0) {
					animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\5.png");
					
				} else if(animacao.getDx() % 2 == 0) {
					animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\6.png");
				}
				
				animacaoObj1.setLarg(animacao.getLarg());
				animacaoObj1.setAlt(animacao.getAlt());
				
				animacaoObj1.setX(animacao.getX());
				animacaoObj1.setY(animacao.getY());
				
			} else if(animacao.getDx() == 94) {
				animacao.setX(tamanhoContorno); animacao.setY(tamanhoContorno + 2);
				animacaoObj1.setImagem(null);
				animacaoObj1.setX(tamanhoContorno); animacaoObj1.setY(tamanhoContorno + 2);
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\7.png");
			
			} else if(animacao.getDx() == 96) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\8.png");
				imgDado1.load(caminho + "res\\batalha\\Arius\\dados\\" + dados[1][0] + ".png");
				imgDado2.load(caminho + "res\\batalha\\Arius\\dados\\" + dados[1][1] + ".png");
				imgDado3.load(caminho + "res\\batalha\\Arius\\dados\\" + dados[1][2] + ".png");
				imgDado4.load(caminho + "res\\batalha\\Arius\\dados\\" + dados[1][3] + ".png");
				imgDado5.load(caminho + "res\\batalha\\Arius\\dados\\" + dados[1][4] + ".png");
				
			} else if(animacao.getDx() == 140) {
				imgDado1.setImagem(null);
				imgDado2.setImagem(null);
				imgDado3.setImagem(null);
				imgDado4.setImagem(null);
				imgDado5.setImagem(null);
			}
			
		} else if(avent == 4 && gifApelos[4][numApelo] == 7) {
			
			if(animacao.getDx() == 20) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\0.png");
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\1.png");
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\2.png");
				animacaoObj3.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\4.png");
			
			} else if(animacao.getDx() >= 21 && animacao.getDx() <= 59) {
				animacaoObj1.setY(animacaoObj1.getY() - 1);
				
				if(animacao.getDx() % 6 == 0) {
					animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\2.png");
				} else if(animacao.getDx() % 3 == 0) {
					animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\3.png");
				}
				
				animacaoObj3.setY(animacaoObj3.getY() + (animacao.getDx() % 2 == 0 && ((animacao.getDx() >= 22 && animacao.getDx() <= 28) || (animacao.getDx() >= 46)) ? 2 : (animacao.getDx() % 2 == 0 && (animacao.getDx() >= 30 && animacao.getDx() <= 44) ? -2 : 0)));
			
			} else if(animacao.getDx() == 60) {
				animacaoObj1.setY(animacao.getY());
				animacaoObj1.setX(tamanhoContorno + 200);
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\5.png");
				animacao.load(caminho + "res\\batalha\\fundoAnimacao1.png");
				animacaoObj2.setImagem(null);
				animacaoObj3.setImagem(null);
				
			} else if(animacao.getDx() >= 61 && animacao.getDx() <= 123 && animacao.getDx() % 2 == 0) {
				animacaoObj1.setX(animacaoObj1.getX() -10);
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + 
				(gifApelos[4][numApelo]) + "\\" + (Integer. parseInt((animacao.getDx()+ "").substring((animacao.getDx()+"").length() - 1, (animacao.getDx()+"").length())) == 0 ? 5 : 
				(Integer. parseInt((animacao.getDx()+ "").substring((animacao.getDx()+"").length() - 1, (animacao.getDx()+"").length())) == 2 ? 6 : 
				(Integer. parseInt((animacao.getDx()+ "").substring((animacao.getDx()+"").length() - 1, (animacao.getDx()+"").length())) == 4 ? 7 : 
				(Integer. parseInt((animacao.getDx()+ "").substring((animacao.getDx()+"").length() - 1, (animacao.getDx()+"").length())) == 6 ? 8 : 9)))) + ".png");
			
			} else if(animacao.getDx() >= 124 && animacao.getDx() <= 128 && animacao.getDx() % 2 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\" + (animacao.getDx() == 124 ? 10 : (animacao.getDx() == 126 ? 11 : 12)) + ".png");
			
			} else if(animacao.getDx() >= 130 && animacao.getDx() <= 140 && (animacao.getDx() - 1) % 3 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\" + (animacao.getDx() == 130 ? 13 : (animacao.getDx() == 133 ? 14 : 15)) + ".png");
			}
			
		
		} else if(avent == 4 && gifApelos[4][numApelo] == 4) {
			
			if(animacao.getDx() == 20) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\0.png");
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\1.png");
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\2.png");
				animacaoObj3.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\4.png");
			
			} else if(animacao.getDx() >= 21 && animacao.getDx() <= 56) {
				animacaoObj1.setY(animacaoObj1.getY() - 1);
				
				if(animacao.getDx() % 6 == 0) {
					animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\2.png");
				} else if(animacao.getDx() % 3 == 0) {
					animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\3.png");
				}
				
				animacaoObj3.setY(animacaoObj3.getY() + (animacao.getDx() % 2 == 0 && ((animacao.getDx() >= 22 && animacao.getDx() <= 28) || (animacao.getDx() >= 46)) ? 2 : (animacao.getDx() % 2 == 0 && (animacao.getDx() >= 30 && animacao.getDx() <= 44) ? -2 : 0)));
			
			} else if(animacao.getDx() == 57) {
				animacaoObj1.setY(animacao.getY());
				animacaoObj1.setX(tamanhoContorno + 130);
				animacao.load(caminho + "res\\batalha\\fundoAnimacao1.png");
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\5.png");
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\7.png");
				animacaoObj3.setImagem(null);
				
			} else if(animacao.getDx() >= 60 && animacao.getDx() <= 81 && animacao.getDx() % 6 == 0) {
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\8.png");
				
			} else if(animacao.getDx() >= 60 && animacao.getDx() <= 81 && animacao.getDx() % 3 == 0) {
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\7.png");
			}
			
			if(animacao.getDx() >= 60 && animacao.getDx() <= 81) {
				animacaoObj1.setX(animacaoObj1.getX() - 6);
			
			} else if(animacao.getDx() == 84) {
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\6.png");
				
			} else if(animacao.getDx() == 86) {
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\18.png");
			
			}else if(animacao.getDx() == 93) {
				animacaoObj1.setImagem(null); animacaoObj1.setX(tamanhoContorno); animacaoObj1.setY(tamanhoContorno + 2);
				animacaoObj2.setImagem(null); animacaoObj2.setX(tamanhoContorno); animacaoObj2.setY(tamanhoContorno + 2);
			}
			
			if(animacao.getDx() >= 93 && animacao.getDx() <= 102 && animacao.getDx() % 3 == 0) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\"  + (animacao.getDx() == 93 ? 9 : (animacao.getDx() == 96 ? 10 : (animacao.getDx() == 99 ? 11 : 12))) + ".png");
			
			}else if(animacao.getDx() == 105) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\13.png");
				animacaoObj1.setY(-320);
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\14.png");
			
			} else if(animacao.getDx() >= 106 && animacao.getDx() <= 113) {
				animacaoObj1.setY(animacaoObj1.getY() + 40);
			
			} else if(animacao.getDx() == 114) {
				animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\17.png");
				animacaoObj1.setY(tamanhoContorno + 2);
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\16.png");
				animacaoObj2.setImagem(null);
				
			} else if(animacao.getDx() >= 116 && animacao.getDx() <= 132 && animacao.getDx() % 8 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\15.png");
				
			} else if(animacao.getDx() >= 116 && animacao.getDx() <= 132 && animacao.getDx() % 4 == 0) {
				animacaoObj1.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\16.png");
				
			} else if(animacao.getDx() == 136) {
				animacaoObj2.load(caminho + "res\\batalha\\Arius\\animacao\\" + (gifApelos[4][numApelo]) + "\\19.png");
			}
			
		}
		
		else if(avent == 2) {
			animacao.load(caminho + "res\\batalha\\Rexthor\\animacao\\" + (numApelo == 0? gifApelos[2][0] : (numApelo == 1? gifApelos[2][1] : (numApelo == 2? gifApelos[2][2] : gifApelos[2][3]))) + ".gif");
		}
	
	}

	public void setContEngranagem2(boolean contEngranagem2) {
		this.contEngranagem2 = contEngranagem2;
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");	
		timer.start();
	}
	
	public void acenderLuzAventureiro() {
		
		luzCampoBatalha1.load(caminho + "res\\batalha\\" + ( vezDoAventureiro != 0 ? "luzCampoBatalha1.png" : (ordemAventRodada[vezDoAventureiro] == 0 ? "\\Ignis\\" : (ordemAventRodada[vezDoAventureiro] == 1 ? "\\Ayla\\" : (ordemAventRodada[vezDoAventureiro] == 2 ? "\\Rexthor\\" : (ordemAventRodada[vezDoAventureiro] == 3 ? "\\Kiki\\" : "\\Arius\\"))))  + "luzCampoBatalha2.png"));
		luzCampoBatalha2.load(caminho + "res\\batalha\\" + ( vezDoAventureiro != 1 ? "luzCampoBatalha1.png" : (ordemAventRodada[vezDoAventureiro] == 0 ? "\\Ignis\\" : (ordemAventRodada[vezDoAventureiro] == 1 ? "\\Ayla\\" : (ordemAventRodada[vezDoAventureiro] == 2 ? "\\Rexthor\\" : (ordemAventRodada[vezDoAventureiro] == 3 ? "\\Kiki\\" : "\\Arius\\"))))  + "luzCampoBatalha2.png"));
		luzCampoBatalha3.load(caminho + "res\\batalha\\" + ( vezDoAventureiro != 2 ? "luzCampoBatalha1.png" : (ordemAventRodada[vezDoAventureiro] == 0 ? "\\Ignis\\" : (ordemAventRodada[vezDoAventureiro] == 1 ? "\\Ayla\\" : (ordemAventRodada[vezDoAventureiro] == 2 ? "\\Rexthor\\" : (ordemAventRodada[vezDoAventureiro] == 3 ? "\\Kiki\\" : "\\Arius\\"))))  + "luzCampoBatalha2.png"));
		luzCampoBatalha4.load(caminho + "res\\batalha\\" + ( vezDoAventureiro != 3 ? "luzCampoBatalha1.png" : (ordemAventRodada[vezDoAventureiro] == 0 ? "\\Ignis\\" : (ordemAventRodada[vezDoAventureiro] == 1 ? "\\Ayla\\" : (ordemAventRodada[vezDoAventureiro] == 2 ? "\\Rexthor\\" : (ordemAventRodada[vezDoAventureiro] == 3 ? "\\Kiki\\" : "\\Arius\\"))))  + "luzCampoBatalha2.png"));
		luzCampoBatalha5.load(caminho + "res\\batalha\\" + ( vezDoAventureiro != 4 ? "luzCampoBatalha1.png" : (ordemAventRodada[vezDoAventureiro] == 0 ? "\\Ignis\\" : (ordemAventRodada[vezDoAventureiro] == 1 ? "\\Ayla\\" : (ordemAventRodada[vezDoAventureiro] == 2 ? "\\Rexthor\\" : (ordemAventRodada[vezDoAventureiro] == 3 ? "\\Kiki\\" : "\\Arius\\"))))  + "luzCampoBatalha2.png"));
		
	}
	
	public void animarVencedor() {
		contVencedor ++;
		
		if(ordemAventRodada[0] == aventureiro) {
			
			if(contFogos == 23){contFogos = 1;}else{contFogos ++;}
			
			itemParabenizacaoVencedor1.setY(0);
			
			if(contVencedor % 4 == 0) {
				parabenizacaoVencedor.load(caminho + "res\\batalha\\" + nomeAventureiro[ordemAventRodada[0]] + "\\vencedor1.png");
				itemParabenizacaoVencedor3.load(caminho + "res\\batalha\\estrelas1.png");
				
			} else if(contVencedor % 2 == 0) {
				parabenizacaoVencedor.load(caminho + "res\\batalha\\" + nomeAventureiro[ordemAventRodada[0]] + "\\vencedor2.png");
				itemParabenizacaoVencedor3.load(caminho + "res\\batalha\\estrelas2.png");
			}
			
			itemParabenizacaoVencedor1.load(caminho + "res\\batalha\\fogos\\fogos" + (contFogos * 2) + ".png");
			itemParabenizacaoVencedor2.load(caminho + "res\\batalha\\fogos\\fogos" + (contFogos * 2 - 1) + ".png");
		
		} else if(contVencedor <= 40){
			
			parabenizacaoVencedor.load(caminho + "res\\batalha\\" + nomeAventureiro[ordemAventRodada[0]] + "\\perdedor1.png");
			
			itemParabenizacaoVencedor1.load(caminho + "res\\batalha\\sombreadoPerdedor1.png");
			itemParabenizacaoVencedor1.setY(itemParabenizacaoVencedor1.getY() + 8);
		}
		
	}
	
	
}