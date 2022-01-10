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
	|  Batalha é a tela a onde o jogo acontece.													|
	\ ---------------------------------------------------------------------------------------- */
	
	private Escolha_de_adversario tela2;
	private Manual telaManual;
	private Menu telaMenu;
	private Creditos telaCreditos;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1130, -12);
	
	private int tamanhoContorno = 20;
	private Icones_interativos parabenizacaoVencedor;
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private int contEngranagem1 = 1;
	private boolean contEngranagem2;

	private int contTempoApelo = 0;
	private int contTempoInter = 10;
	
	private String caminho;
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(18 + 200/2 - 128/2, 80);
	private Icones_interativos bntManual = new Icones_interativos(bntMenu.getX(), bntMenu.getY() + 120);
	private Icones_interativos bntCreditos = new Icones_interativos(bntMenu.getX(), bntManual.getY() + 120);
	private Icones_interativos bntVoltar = new Icones_interativos(bntMenu.getX(), bntCreditos.getY() + 120);

	private boolean mostrarMenu = false;
	private int contMenu = 0;
	private Salvar salvar = new Salvar();
	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------

	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(1234/2 - 706/2 + 110, dialogoAviso.getY() + 190);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(bntSimDialogoAviso.getX() + 370, bntSimDialogoAviso.getY());
	
	
	private Texto txtDialogoAviso = new Texto(dialogoAviso.getX() + 110, 548/2 - 28 - 40, " ");
	private Texto txtDialogoAviso2 = new Texto(dialogoAviso.getX() + 250, 548/2 + 52 - 40, " ");
	

	
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
	
	private String [][] gifApelos = {ignis.getApelosEInterferencias(), ayla.getApelosEInterferencias(), rexthor.getApelosEInterferencias(), kiki.getApelosEInterferencias(), arius.getApelosEInterferencias()};
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
	//quantidade interferência q recebem de cada um  ignis             ayla            rexthor           kiki            arius
	private int [][] interferenciasRecebidas = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
	private int [] pontosRodada = {0, 0, 0, 0, 0};
	private int [] pontosAtuaisDaRodada = {0, 0, 0, 0, 0};
	//										ignis              ayla            rexthor           kiki            arius
	private int [][] numeroDosAtaques = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
	private int [] pontosTotais = {0, 0, 0, 0, 0};
	
	private int contEtapasBatalha = 0; // conta em que rodada esta > começa com 1 e vai ate o 5
	
	
	private int aventureiro;
	private int adversario;
	private int posicaoAventureiro;
	int vezDoAventureiro = 0;
	
	
	private int [] efeitoChefeDeFase = {0, 0, 0, 0, 0}; // 3 estados, 0 entrou no pos1,1 entrou no else , 2 ta la mas nao pode entrar
	private int [] efeitoApeloRepetido = {0, 0, 0, 0, 0}; // 3 estados, 0 entrou no pos1,1 entrou no else , 2 ta la mas nao pode entrar
	private int [] efeitoInterferencias = {0, 0, 0, 0, 0}; // 3 estados, 0 entrou no pos1,1 entrou no else , 2 ta la mas nao pode entrar

	private Random aleatorioHabAdver = new Random();
	private int [] arrayAleatorioHabAdver = {aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4), aleatorioHabAdver.nextInt(4)};

	// ------------------------ divisões da tela de batalha -------------------------
	
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
		
	// ------------------------------------------- animação ----------------------------------------

	private Icones_interativos animacaoObj1 = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
	private Icones_interativos animacaoObj2 = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);

	
	private int intervaloAnimacao = 10;
	private int intervaloAnimacaoGif = 240;
	
	private int comecarAnimacaoCoracao = 0;
	private int animacaoFileira = -1;

	// --------------------------------- campo batalha e habilidades usadas -----------------------------------------
	
	private Texto txtEfeitoFase = new Texto(tamanhoContorno + 700, campoBatalha1.getY() + 70/2 + 7, " ");
	private Icones_interativos efeitoFase = new Icones_interativos(txtEfeitoFase.getX() + 30, txtEfeitoFase.getY() - 17);
	
	private Icones_interativos iconeCampoBatalha1 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha1.getY() + 4);
	private Icones_interativos iconeCampoBatalha2 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha2.getY() + 4);
	private Icones_interativos iconeCampoBatalha3 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha3.getY() + 4);
	private Icones_interativos iconeCampoBatalha4 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha4.getY() + 4);
	private Icones_interativos iconeCampoBatalha5 = new Icones_interativos(campoBatalha1.getX() + 27, campoBatalha5.getY() + 4);
		
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
	
	private Icones_interativos painel4 = new Icones_interativos(campoBatalha1.getX() - 8, campoBatalha1.getY() - 4);
	private Icones_interativos seletorAventureiro = new Icones_interativos(iconeCampoBatalha1.getX(), iconeCampoBatalha1.getY() - 1);
	
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
	
	// ----------------------- itens relacionado com Descrição -----------------------------------

	private Texto textoDescricao1, textoDescricao2, textoDescricao3, textoDescricao4, textoDescricao5;
	
	private Icones_interativos painel3 = new Icones_interativos(descricao.getX(), descricao.getY());
	
	// ------------------------------------------- d20 ----------------------------------------
	//                            Rexthor           Arius
	private int [][] dados = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
	
	private Icones_interativos imgDado1 = new Icones_interativos(tamanhoContorno + 760/2 - 578/2, tamanhoContorno + 30);
	private Icones_interativos imgDado2 = new Icones_interativos(imgDado1.getX() + 120, imgDado1.getY());
	private Icones_interativos imgDado3 = new Icones_interativos(imgDado2.getX() + 120, imgDado1.getY());
	private Icones_interativos imgDado4 = new Icones_interativos(imgDado3.getX() + 120, imgDado1.getY());
	private Icones_interativos imgDado5 = new Icones_interativos(imgDado4.getX() + 120, imgDado1.getY());
	
	// ---------------------------------- animação inicial --------------------------------
	
	private Icones_interativos fundoAnimacao = new Icones_interativos(tamanhoContorno, tamanhoContorno + 2);
	private Icones_interativos iconeIgnis = new Icones_interativos(animacao.getX() -248, animacao.getY());
	private Icones_interativos iconeAyla = new Icones_interativos(animacao.getX(), animacao.getY() -248);
	private Icones_interativos iconeRexthor = new Icones_interativos(animacao.getX(), animacao.getY());
	private Icones_interativos iconeKiki = new Icones_interativos(animacao.getX(), animacao.getY() + 248);
	private Icones_interativos iconeArius = new Icones_interativos(animacao.getX() + 248, animacao.getY());
	
	private boolean comecarAnimacaoInicio = false;
	private boolean animarAnimacao = false;
	private boolean inverterAnimacao = false;
	// ------------------------------------------------------------------
	
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7, tl8, tl15, tl16, tl17, tl19, tl20, tl21;
	
	private Timer timer;
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
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
		
		ImageIcon referencia = new ImageIcon(caminho + "res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load(caminho + "res\\fundo.png");
		engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem1.png");
		
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
		
		parabenizacaoVencedor = new Icones_interativos(16, 16);
		
		animacao.setImagem(null);
		
		// ------------------------ textos do diálogo de aviso ------------------------------		
		
		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		txtDialogoAviso2.setFonte(new Font("Arial", Font.PLAIN, 28));
		
		// ------------------------ divisões da tela de batalha -------------------------

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
		seletorAventureiro.load(caminho + "res\\batalha\\seletorAventureiro.png");
		
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
		
		nomeApelo1.setFonte(new Font("Arial", Font.PLAIN, 20));
		nomeApelo1.setCorTexto((aventureiro == 0 ? (new Color (255, 60, 0)) : (aventureiro == 1 ? (new Color (255, 0, 191)) : (aventureiro == 2 ? (new Color (0, 134, 255)) : (aventureiro == 3 ? (new Color (0, 255, 141)) : (new Color (255, 0, 38)))))));
		nomeApelo2.setFonte(new Font("Arial", Font.PLAIN, 20));
		nomeApelo2.setCorTexto(new Color (235, 148, 150));
		nomeApelo3.setFonte(new Font("Arial", Font.PLAIN, 20));
		nomeApelo3.setCorTexto(new Color (198, 96, 134));
		nomeApelo4.setFonte(new Font("Arial", Font.PLAIN, 20));
		
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
		InterferenciaQuantidade.setFonte(apeloQuantidade.getFonte());

		painel2.load(caminho + "res\\batalha\\painel2.png");
		
		itensDoApelo();
		
		// ----------------------- itens relacionado com Descrição -----------------------------------

		textoDescricao1 = new Texto(descricao.getX() + 20, descricao.getY() + 30, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][0]);
		textoDescricao2 = new Texto(textoDescricao1.getX(), textoDescricao1.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][1]);
		textoDescricao3 = new Texto(textoDescricao2.getX(), textoDescricao2.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][2]);
		textoDescricao4 = new Texto(textoDescricao3.getX(), textoDescricao3.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][3]);
		textoDescricao5 = new Texto(textoDescricao4.getX(), textoDescricao4.getY() + 28, ConteudoDescricao[aventureiro == 0 ? 0 : (aventureiro == 1 ? 4 : (aventureiro == 2 ? 8 : (aventureiro == 3 ? 12 : 16)))][4]);

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
		
		painel3.load(caminho + "res\\batalha\\painel3.png");
		
		// ---------------------------------- animação inicial --------------------------------
		
		fundoAnimacao.load(caminho + "res\\batalha\\animacao\\fundo1.png");
		iconeIgnis.load(caminho + "res\\batalha\\animacao\\ignis1.png");		
		iconeAyla.load(caminho + "res\\batalha\\animacao\\ayla1.png");	
		iconeRexthor.load(caminho + "res\\batalha\\animacao\\rexthor1.png");
		iconeKiki.load(caminho + "res\\batalha\\animacao\\kiki1.png");	
		iconeArius.load(caminho + "res\\batalha\\animacao\\arius1.png");	
		iconeIgnis.setDx(0);
		comecarAnimacaoInicio = true;
		
		// ------------------------------------------------------------------------
		
		timer = new Timer(5, this);
		timer.start();
		
	}
	
	public void animarInicio() {
	
		iconeIgnis.setX(iconeIgnis.getX() + 8);
		iconeAyla.setY(iconeAyla.getY() + 8);
		iconeKiki.setY(iconeKiki.getY() - 8);
		iconeArius.setX(iconeArius.getX() - 8);
		
		if(iconeIgnis.getX() == animacao.getX()) {
			comecarAnimacaoInicio = false;
			animarAnimacao = true;
		}
	
	}
	
	public void animarAventureiros() {
		
		iconeIgnis.setDx(iconeIgnis.getDx() + (inverterAnimacao == false ? 1 : -1));
		
		if(iconeIgnis.getDx() == 0 || iconeIgnis.getDx() == 20 || iconeIgnis.getDx() == 40 || iconeIgnis.getDx() == 60) {
			iconeIgnis.load(caminho + "res\\batalha\\animacao\\ignis2.png");
		} else if(iconeIgnis.getDx() == 10 || iconeIgnis.getDx() == 30 || iconeIgnis.getDx() == 50) {
			iconeIgnis.load(caminho + "res\\batalha\\animacao\\ignis3.png");
		} else if(iconeIgnis.getDx() == 70) {
			iconeIgnis.load(caminho + "res\\batalha\\animacao\\ignis1.png");
		}
		
		if(iconeIgnis.getDx() == 80 || iconeIgnis.getDx() == 0) {
			inverterAnimacao = !inverterAnimacao;
		}
		
		//--------------------------------------------------------------------------------
		
		iconeKiki.setDx(iconeKiki.getDx() + 1);
		
		if(iconeKiki.getDx() % 16 == 0) {
			iconeKiki.load(caminho + "res\\batalha\\animacao\\kiki1.png");
		} else if(iconeKiki.getDx() % 8 == 0) {
			iconeKiki.load(caminho + "res\\batalha\\animacao\\kiki2.png");
		}
		
		//--------------------------------------------------------------------------------
		
		iconeArius.setDx(iconeArius.getDx() + 1);
		
		if(iconeArius.getDx() % 20 == 0) {
			iconeArius.load(caminho + "res\\batalha\\animacao\\arius1.png");
			iconeRexthor.load(caminho + "res\\batalha\\animacao\\rexthor1.png");
		} else if(iconeArius.getDx() % 10 == 0) {
			iconeArius.load(caminho + "res\\batalha\\animacao\\arius2.png");
			iconeRexthor.load(caminho + "res\\batalha\\animacao\\rexthor2.png");
		}
		
		//--------------------------------------------------------------------------------
		
		iconeAyla.setDx(iconeAyla.getDx() + 1);
		
		if(iconeAyla.getDx() == 5 || iconeAyla.getDx() == 10 || iconeAyla.getDx() == 15) {
			iconeAyla.setY(iconeAyla.getY() + 2);
			
		} else if(iconeAyla.getDx() == 20 || iconeAyla.getDx() == 25 || iconeAyla.getDx() == 30) {
			iconeAyla.setY(iconeAyla.getY() - 2);
			
		}
		
		if(iconeAyla.getDx() % 14 == 0 ) {
			iconeAyla.load(caminho + "res\\batalha\\animacao\\ayla1.png");
		} else if(iconeAyla.getDx() % 7 == 0) {
			iconeAyla.load(caminho + "res\\batalha\\animacao\\ayla2.png");
		} 
		
		if(iconeAyla.getDx() == 30) {
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
	|  						coloca as imagens dos Cães na ordem correta							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void repintarCampoBatalha() {
		
		for(int i=0; i<5; i++) {
			(i == 0 ? iconeCampoBatalha1 : (i == 1 ? iconeCampoBatalha2 : (i == 2 ? iconeCampoBatalha3 : (i == 3 ? iconeCampoBatalha4 : iconeCampoBatalha5)))).load(caminho + "res\\batalha\\" + nomeAventureiro[ordemAventRodada[i]] + "\\iconeCampoBatalha.png");
		}
		
		seletorAventureiro.setY((posicaoAventureiro == 0 ? iconeCampoBatalha1.getY() : (posicaoAventureiro == 1 ? iconeCampoBatalha2.getY() : (posicaoAventureiro == 2 ? iconeCampoBatalha3.getY() : (posicaoAventureiro == 3 ? iconeCampoBatalha4.getY() : iconeCampoBatalha5.getY())))) - 1);		
	}
	
	/* ---------------------------------------------------------------------------------------- \
   	|  								organiza os Cães antes da batalha							|
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
			
			txtDialogoAviso.setTexto("Se você voltar a luta será encerrada.");
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
	|  									Vai para a tela de Créditos								|
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
			
			txtDialogoAviso.setTexto("Se você voltar a luta será encerrada.");
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
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        janelaPrincipal.add(tela2);
	        janelaPrincipal.setTitle("Escolha de Adversário");
	        tela2.LimparTela3();
	        tela2.setContEngranagem2(contEngranagem2);
	        tela2.setNumAdversarioAnterior(adversario);
	        janelaPrincipal.revalidate();
	        timer.stop();
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  						mostra a quantidade de apelo e interferência						|
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
	|  							dispara quando as teclas são  pressionadas						|
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
					fundoMenu.load(caminho + "res\\Menu secundario\\menu.png");
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
				
			// ----------------------- muda a seleção das opções do menu -------------------------- \
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
				
			
			// ---------- encaminha para a função que controla o botão menu do menu --------- \
			}else if(mostrarMenu == true && contMenu == 0) {
				dialogoBntMenu(codigo);
			
			// ---------- encaminha para a função que controla o botão manual do menu --------- \
			}else if(mostrarMenu == true && contMenu == 1) {
				dialogoBntManual(codigo);
			
			// ---------- encaminha para a função que controla o botão creditos do menu --------- \
			}else if(mostrarMenu == true && contMenu == 2) {
				dialogoBntCreditos(codigo);
				
			// ---------- encaminha para a função que controla o botão voltar do menu --------- \
			}else if(mostrarMenu == true && contMenu == 3) {
				dialogoVoltar(codigo);
								
			// ---------- muda a seleção dos ícones dos personagens no mapa para cima e para baixo --------- \
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
	
				// --------------------------- pega todas as informações iniciais e coloca as informações de cada cão --------------------
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
				
			// --------------------- termina a parabenizarão para a escolha de adversário ------------------
			} else if(codigo == KeyEvent.VK_Z && contEtapasBatalha == 6 ) {
			
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela2);
		        janelaPrincipal.setTitle("Escolha de Adversário");
		        
		        if(ordemAventRodada[0] == aventureiro) {
	        		if(tela2.getDerrotados()[adversario] == false) {
	        			tela2.setDerrotados(adversario, true);
	        		}
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
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		
		// ------------------------ divisões da tela de batalha -------------------------
		
		graficos.drawImage(fundoAnimacao.getImagem(), fundoAnimacao.getX(), fundoAnimacao.getY(), this);
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getX(), iconeIgnis.getY(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getX(), iconeAyla.getY(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getX(), iconeRexthor.getY(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getX(), iconeArius.getY(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getX(), iconeKiki.getY(), this);
		
		// --------------------------------------------- animação ---------------------------------------------
		
		graficos.drawImage(animacao.getImagem(), animacao.getX(), animacao.getY(), this);
		graficos.drawImage(animacaoObj1.getImagem(), animacaoObj1.getX(), animacaoObj1.getY(), this);
		graficos.drawImage(animacaoObj2.getImagem(), animacaoObj2.getX(), animacaoObj2.getY(), this);
		
		// ----------------------------------------------------------------------------------------
		
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
		graficos.setColor(txtEfeitoFase.getCorTexto());
		tl8.draw(graficos, txtEfeitoFase.getX(), txtEfeitoFase.getY());
	    
	    graficos.drawImage(efeitoFase.getImagem(), efeitoFase.getX(), efeitoFase.getY(), this);
		
		graficos.drawImage(iconeCampoBatalha1.getImagem(), iconeCampoBatalha1.getX(), iconeCampoBatalha1.getY(), this);
		graficos.drawImage(iconeCampoBatalha2.getImagem(), iconeCampoBatalha2.getX(), iconeCampoBatalha2.getY(), this);
		graficos.drawImage(iconeCampoBatalha3.getImagem(), iconeCampoBatalha3.getX(), iconeCampoBatalha3.getY(), this);
		graficos.drawImage(iconeCampoBatalha4.getImagem(), iconeCampoBatalha4.getX(), iconeCampoBatalha4.getY(), this);
		graficos.drawImage(iconeCampoBatalha5.getImagem(), iconeCampoBatalha5.getX(), iconeCampoBatalha5.getY(), this);
		
		graficos.drawImage(painel4.getImagem(), painel4.getX(), painel4.getY(), this);
		graficos.drawImage(seletorAventureiro.getImagem(), seletorAventureiro.getX(), seletorAventureiro.getY(), this);
		
		graficos.drawImage(luzCampoBatalha1.getImagem(), luzCampoBatalha1.getX(), luzCampoBatalha1.getY(), this);
		graficos.drawImage(luzCampoBatalha2.getImagem(), luzCampoBatalha2.getX(), luzCampoBatalha2.getY(), this);
		graficos.drawImage(luzCampoBatalha3.getImagem(), luzCampoBatalha3.getX(), luzCampoBatalha3.getY(), this);
		graficos.drawImage(luzCampoBatalha4.getImagem(), luzCampoBatalha4.getX(), luzCampoBatalha4.getY(), this);
		graficos.drawImage(luzCampoBatalha5.getImagem(), luzCampoBatalha5.getX(), luzCampoBatalha5.getY(), this);


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
				
		// --------------------------------- nome habilidades -----------------------------------------

	    tl1 = new TextLayout(nomeApelo1.getTexto(), nomeApelo1.getFonte(), frc);
	    tl2 = new TextLayout(nomeApelo2.getTexto(), nomeApelo2.getFonte(), frc);
	    tl3 = new TextLayout(nomeApelo3.getTexto(), nomeApelo3.getFonte(), frc);
	    tl4 = new TextLayout(nomeApelo4.getTexto(), nomeApelo4.getFonte(), frc);
	    
	    graficos.setColor(selecaoNomeHab == 0 ? nomeApelo1.getCorTexto() : (nomeHabAnterior == 0 ? nomeApelo3.getCorTexto() : nomeApelo2.getCorTexto()));
	    tl1.draw(graficos, nomeApelo1.getX(), nomeApelo1.getY());
	    graficos.setColor(selecaoNomeHab == 1 ? nomeApelo1.getCorTexto() : (nomeHabAnterior == 1 ? nomeApelo3.getCorTexto() : nomeApelo2.getCorTexto()));
	    tl2.draw(graficos, nomeApelo2.getX(), nomeApelo2.getY());
	    graficos.setColor(selecaoNomeHab == 2 ? nomeApelo1.getCorTexto() : (nomeHabAnterior == 2 ? nomeApelo3.getCorTexto() : nomeApelo2.getCorTexto()));
	    tl3.draw(graficos, nomeApelo3.getX(), nomeApelo3.getY());
	    graficos.setColor(selecaoNomeHab == 3 ? nomeApelo1.getCorTexto() : (nomeHabAnterior == 3 ? nomeApelo3.getCorTexto() : nomeApelo2.getCorTexto()));
	    tl4.draw(graficos, nomeApelo4.getX(), nomeApelo4.getY());
	    graficos.setColor(Color.BLACK);
	    
		graficos.drawImage(painel1.getImagem(), painel1.getX(), painel1.getY(), this);

		// ----------------------- itens relacionado com Apelo -----------------------------------
		
	    tl6 = new TextLayout(apeloQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);
	    tl7 = new TextLayout(InterferenciaQuantidade.getTexto(), InterferenciaQuantidade.getFonte(), frc);

	    graficos.setColor(apeloQuantidade.getCorTexto());
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
		
		graficos.drawImage(painel2.getImagem(), painel2.getX(), painel2.getY(), this);
		
		graficos.drawImage(tipoDoApelo.getImagem(), tipoDoApelo.getX(), tipoDoApelo.getY(), this);
		
		// ----------------------- itens relacionado com Descrição -----------------------------------
	    tl5 = new TextLayout(textoDescricao1.getTexto(), textoDescricao1.getFonte(), frc);
	    tl15 = new TextLayout(textoDescricao2.getTexto(), textoDescricao2.getFonte(), frc);
	    tl16 = new TextLayout(textoDescricao3.getTexto(), textoDescricao3.getFonte(), frc);
	    tl17 = new TextLayout(textoDescricao4.getTexto(), textoDescricao4.getFonte(), frc);
	    tl19 = new TextLayout(textoDescricao5.getTexto(), textoDescricao5.getFonte(), frc);
	    
	    graficos.setColor(textoDescricao1.getCorTexto());
	    tl5.draw(graficos, textoDescricao1.getX(), textoDescricao1.getY());
	    tl15.draw(graficos, textoDescricao2.getX(), textoDescricao2.getY());
	    tl16.draw(graficos, textoDescricao3.getX(), textoDescricao3.getY());
	    tl17.draw(graficos, textoDescricao4.getX(), textoDescricao4.getY());
	    graficos.setColor(textoDescricao5.getCorTexto());
	    tl19.draw(graficos, textoDescricao5.getX(), textoDescricao5.getY());
	    graficos.setColor(Color.BLACK);
	    
		graficos.drawImage(painel3.getImagem(), painel3.getX(), painel3.getY(), this);
	    
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
		graficos.drawImage(bntManual.getImagem(), bntManual.getX(), bntManual.getY(), this);
		graficos.drawImage(bntVoltar.getImagem(),  bntVoltar.getX(),  bntVoltar.getY(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getX(), bntCreditos.getY(), this);

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
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getX(), engrenagem1.getY(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getX(), engrenagem2.getY(), this);
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);

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
		
		// --------------------- começa a colocar as interferências ---------------------
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
		
		// -------------- organiza os valores das posições de acordo com a performance  na batalha ----------------
		if(animacaoFileira == 22) {
			OrganizarValores();
		}
		
		// -------------- organiza os cães de acordo com a performance  na batalha ----------------
		if(animacaoFileira == 23) {
			OrganizarCampos();
		}
		
		// -------------- mostra a parabenizarão no final da batalha ----------------
		if(animacaoFileira == 24) {

			vencedor();
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

			animacao.setDx(animacao.getDx() + comecarAnimacaoCoracao);

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
				animacaoObj1.setImagem(null);
				animacaoObj2.setImagem(null);
				animacao.setX(tamanhoContorno);
				animacao.setY(tamanhoContorno + 2);
				animacao.setImagem(null);				
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
	|  								mostra as interferências ganhas								|
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
	|  							coloca os efeitos nos cães										|
	\ ---------------------------------------------------------------------------------------- */
	
	public void interferirNosAdversarios() {
		//(-1: sem efeito, 0: todos acima, 1: todos abaixo, 2: um acima, 3: primeiro)
		
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
			
			imgDado1.setDx(imgDado1.getDx() + 1);
			
			if(imgDado1.getDx() < 110) {
				imgDado1.load(caminho + "res\\batalha\\" + (ordemAventRodada[vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][0] + ".png");
				imgDado2.load(caminho + "res\\batalha\\" + (ordemAventRodada[vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][1] + ".png");
				imgDado3.load(caminho + "res\\batalha\\" + (ordemAventRodada[vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][2] + ".png");
				imgDado4.load(caminho + "res\\batalha\\" + (ordemAventRodada[vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][3] + ".png");
				imgDado5.load(caminho + "res\\batalha\\" + (ordemAventRodada[vezDoAventureiro] == 2 ? "Rexthor" : "Arius") + "\\dados\\" + dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][4] + ".png");
			
			} else if(imgDado1.getDx() == 110) {
				imgDado1.setImagem(null);
				imgDado2.setImagem(null);
				imgDado3.setImagem(null);
				imgDado4.setImagem(null);
				imgDado5.setImagem(null);
				imgDado1.setDx(0);
				

				if((ordemAventRodada[vezDoAventureiro] == 2 && (dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][0] == 20 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][1] == 20 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][2] == 20 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][3] == 20 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][4] == 20)) || (ordemAventRodada[vezDoAventureiro] == 4 && (dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][0] == 5 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][1] == 5 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][2] == 5 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][3] == 5 || dados[ordemAventRodada[vezDoAventureiro] == 2 ? 0 : 1][4] == 5))) {
					
					valorApelo[ordemAventRodada[vezDoAventureiro]] = valorInterferencia[ordemAventRodada[vezDoAventureiro]];
					animacaoFileira = vezDoAventureiro * 2;
				}
				
				efeitoInterferencias[ordemAventRodada[vezDoAventureiro]] ++;
				
			}
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
		
		System.out.println("reorganização: " +  ordemAventRodada[0] + " " +  ordemAventRodada[1] + " " +  ordemAventRodada[2] + " " +  ordemAventRodada[3] + " " +  ordemAventRodada[4]);

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
	
		System.out.println("reorganização: " +  ordemAventRodada[0] + " " +  ordemAventRodada[1] + " " +  ordemAventRodada[2] + " " +  ordemAventRodada[3] + " " +  ordemAventRodada[4]);
		
		animacaoFileira = 23;
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  			organiza os Cães  de acordo com sua performance na rodada da batalha			|
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
			
			parabenizacaoVencedor.load(caminho + "res\\batalha\\" + nomeAventureiro[ordemAventRodada[0]] + (ordemAventRodada[0] == aventureiro ? "\\vencedor.png" : "\\perdedor.png") );
			
			coracao12.setDx(coracao12.getDx() + comecarAnimacaoCoracao);
			if(coracao11.getDx() >= intervaloAnimacao * 5) {
				zerarDx();
				comecarAnimacaoCoracao = 0;
				contEtapasBatalha = 6;
			}	
		}
	}
	
	
	/* ---------------------------------------------------------------------------------------- \
	|  								aciona as animações das habilidades							|
	\ ---------------------------------------------------------------------------------------- */
	
	public void gifApresentacao(int avent, int numApelo) {

		if(avent == 0) {
			if(gifApelos[0][numApelo] == "apelo3") {

				if(animacao.getDx() == 20) {
					animacao.load(caminho + "res\\batalha\\Ignis\\animacao\\apelo3\\0.png");
				
				} else if(animacao.getDx() == 90) {
					animacao.load(caminho + "res\\batalha\\fundoAnimacao1.png");
					animacaoObj1.setX(tamanhoContorno + 300);
					animacaoObj2.setX(tamanhoContorno - 300);
					animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\apelo3\\2.png");
					animacaoObj2.load(caminho + "res\\batalha\\Ignis\\animacao\\apelo3\\1.png");
				}
				if(animacao.getDx() >= 90 && animacao.getDx() <= 119) {
					animacaoObj1.setX(animacaoObj1.getX() - 10);
					animacaoObj2.setX(animacaoObj2.getX() + 10);
					
				} else if(animacao.getDx() >= 140 && animacao.getDx() % 20 == 0) {
					
					if(animacao.getDx() == 140) {
						animacaoObj2.load(caminho + "res\\batalha\\losango.png");
						animacaoObj1.setX(tamanhoContorno);
						animacao.load(caminho + "res\\batalha\\fundoAnimacao1.png");

					}else if(animacao.getDx() == 160) {
						animacao.setY(-126);
						animacao.setX(760/2 - 1130/2 + 50);
						animacao.load(caminho + "res\\batalha\\fundoAnimacao2.png");
						
					}else if(animacao.getDx() == 200) {
						animacao.setY(-30);
						animacao.setX(760/2 - 1130/2 + 30);
						animacao.load(caminho + "res\\batalha\\fundoAnimacao2.png");
					}
					
					animacaoObj1.load(caminho + "res\\batalha\\Ignis\\animacao\\apelo3\\" + (animacao.getDx()/20 - 3) + ".png");
				
				}
			}
			
		}else if(avent == 1) {
			animacao.load(caminho + "res\\batalha\\Ayla\\animacao\\" + (numApelo == 0? gifApelos[1][0] : (numApelo == 1? gifApelos[1][1] : (numApelo == 2? gifApelos[1][2] : gifApelos[1][3]))) + ".gif");
		}else if(avent == 2) {
			animacao.load(caminho + "res\\batalha\\Rexthor\\animacao\\" + (numApelo == 0? gifApelos[2][0] : (numApelo == 1? gifApelos[2][1] : (numApelo == 2? gifApelos[2][2] : gifApelos[2][3]))) + ".gif");
		}else if(avent == 3) {
			animacao.load(caminho + "res\\batalha\\Kiki\\animacao\\" + (numApelo == 0? gifApelos[3][0] : (numApelo == 1? gifApelos[3][1] : (numApelo == 2? gifApelos[3][2] : gifApelos[3][3]))) + ".gif");
		}else if(avent == 4) {
			animacao.load(caminho + "res\\batalha\\Arius\\animacao\\" + (numApelo == 0? gifApelos[4][0] : (numApelo == 1? gifApelos[4][1] : (numApelo == 2? gifApelos[4][2] : gifApelos[4][3]))) + ".gif");
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
	
	
	
}
