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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

public class Escolha_de_adversario extends JPanel implements ActionListener {
	
	/* ---------------------------------------------------------------------------------------- \
	|  Escolha_de_adversario é a tela a onde os jogadores escolheram qual dos cães das colinas  |
	|  iram jogar contra.																		|
	\ ---------------------------------------------------------------------------------------- */

	private Escolha_de_personagem tela1;
	private Batalha tela3;
	private Manual telaManual;
	private Menu telaMenu;
	private Creditos telaCreditos;

	JFrame janelaPrincipal;
	boolean novoJogo;
	
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
	
	// ------------------------------------------- fundo ---------------------------------------------

	private Icones_interativos camada11 = new Icones_interativos(0, -10);
	
	private Icones_interativos camada21 = new Icones_interativos(0, -10);
	private Icones_interativos camada22 = new Icones_interativos(5992, camada21.getY());

	private Icones_interativos camada31 = new Icones_interativos(0, -10);

	private Icones_interativos camada41 = new Icones_interativos(0, -10);
	private Icones_interativos camada42 = new Icones_interativos(-3999, camada41.getY());
	
	private Icones_interativos camada51 = new Icones_interativos(0, -10);
	private Icones_interativos camada52 = new Icones_interativos(1992, camada51.getY());
	
	private Icones_interativos camada6 = new Icones_interativos(0, -10);
	
	// -------------------------------- aventureiro ---------------------------------------------

	private Icones_interativos iconeAventureiro = new Icones_interativos(1234/2 - 300/2, 166);
	private Icones_interativos iconeSombraAventureiro = new Icones_interativos(1234/2 - 300/2 + 20, 270);
	
	private boolean movimentoAventureiro = false;
	private boolean respiracaoAventureiro = true;
	
	// ------------------------------------------- Ativação direcional ---------------------------------------------
	
	private boolean cima = false;
	private boolean baixo = false;
	private boolean esquerda = false;
	private boolean direita = false;
	
	// ------------------------------------------- Controles ---------------------------------------------

	private Icones_interativos teclaEsquerda = new Icones_interativos(40, 270);
	private Icones_interativos teclaDireita = new Icones_interativos(1234 - teclaEsquerda.getX() - 40, teclaEsquerda.getY());
	private Icones_interativos teclaZ = new Icones_interativos(camada41.getX() + 300, 135); 
	
	private boolean animarApertoTecla = false;
	
	// ------------------------ imagens dos ícones aventureiros ------------------------------
	
	private Icones_interativos iconeBoss = new Icones_interativos(510, -300);
	private Icones_interativos iconeIgnis = new Icones_interativos(2600, 70);
	private Icones_interativos iconeAyla = new Icones_interativos(248, iconeIgnis.getY() - 10);
	private Icones_interativos iconeRexthor = new Icones_interativos(1420, iconeIgnis.getY());
	private Icones_interativos iconeKiki = new Icones_interativos(824, iconeIgnis.getY());
	private Icones_interativos iconeArius = new Icones_interativos(2020, iconeIgnis.getY() - 10);

	private int ativarBoss = 0;
	private int contTeclaBatalha = 1;
	private boolean animarIconeAventureiro = false;
	
	private int contLogoAyla = 0;
	private boolean logoAyla = false;
	
	private Icones_interativos imgLogoAyla = new Icones_interativos(0, 0);

	
	// ------------------------ informações dos aventureiros ------------------------------
	
	private Ignis ignis = new Ignis();
	private Ayla ayla = new Ayla();
	private Rexthor rexthor = new Rexthor();
	private Kiki kiki = new Kiki();
	private Arius arius = new Arius();
	
	// ------------------------ imagens e texto do diálogo com os cães ------------------------------
	
	private Icones_interativos sombreadorDialogo = new Icones_interativos(0, 0);
	private Icones_interativos barraDeDialogo = new Icones_interativos(0, 640 - 220);
	private Icones_interativos imagemDoDialogo = new Icones_interativos(1234/2 - 248/2, 20);
	private Icones_interativos objetoDeFundo1 = new Icones_interativos(0,0);
	private Icones_interativos objetoDeFundo2 = new Icones_interativos(0,0);
	private Icones_interativos objetoDeFundo3 = new Icones_interativos(0,0);
	private Icones_interativos bntSimDialogo = new Icones_interativos(1234/2 - 484/2, 540);
	private Icones_interativos bntNaoDialogo  = new Icones_interativos(bntSimDialogo.getX() + 370, bntSimDialogo.getY());

	
	private Texto txtDialogoLn1 = new Texto(80, 640 - 185 + 26, " ");
	private Texto txtDialogoLn2 = new Texto(txtDialogoLn1.getX(), txtDialogoLn1.getY() + 32, " ");
	private Texto txtDialogoLn3 = new Texto(txtDialogoLn1.getX(), txtDialogoLn2.getY() + 32, " ");
	private Texto txtDialogoLn4 = new Texto(txtDialogoLn1.getX(), txtDialogoLn3.getY() + 32, " ");
	
	private boolean encerrarDialogo = false;
	private boolean tibar = false;
	private int contDialogo = 0;
	private boolean mudaCorLn4 = false;
	private Random aleatorioTibar = new Random();
	private boolean tibarCoroa = true;
	private boolean bntSimNao = true;
	private int ativarAnimacaoVelaAyla = 0;
	private int contAnimacaoJosephine = 0;
	private boolean moverFundo = false;
	
	// ------------------------ imagens de estrelas para batalhas vencidas ------------------------------
	
	private int [][] TabelaInteracao = {{0, 0, 0, 0, 0},
									    {0, 0, 0, 0, 0},
									    {0, 0, 0, 0, 0},
									    {0, 0, 0, 0, 0},
									    {0, 0, 0, 0, 0}};
	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------
	
	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 110, dialogoAviso.getY() + 180);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(bntSimDialogoAviso.getX() + 370, bntSimDialogoAviso.getY());
	
	private Texto txtDialogoAviso = new Texto(dialogoAviso.getX() + 70, 548/2 - 16 - 40, " ");
	private Texto txtDialogoAviso2 = new Texto(dialogoAviso.getX() + 250, 548/2 + 40 - 40, " ");
	
	private Boolean bntSimNaoDialgoAviso = true;
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16, 1);
	private Icones_interativos bntMenu = new Icones_interativos(18 + 200/2 - 128/2, 80);
	private Icones_interativos bntManual = new Icones_interativos(bntMenu.getX(), bntMenu.getY() + 115);
	private Icones_interativos bntCreditos = new Icones_interativos(bntMenu.getX(), bntManual.getY() + 115);
	private Icones_interativos bntVoltar = new Icones_interativos(bntMenu.getX(), bntCreditos.getY() + 115);


	private boolean mostrarMenu = false;
	private int contMenu = 0;
	
	// -------------------- imagens de decoração do diálogo com o boss ---------------------
	
	private Icones_interativos estrelaFim1 = new Icones_interativos(10, 413);
	private Icones_interativos estrelaFim2 = new Icones_interativos(1234 - 196, 412);

	// ---------------------------- save ------------------------------------

	Salvar salvar = new Salvar();
	private Icones_interativos imgMenSalve = new Icones_interativos(-242, 56);
	private Icones_interativos imgMenSalve2 = new Icones_interativos(imgMenSalve.getX(), imgMenSalve.getY());
	private Texto txtSalvar = new Texto(84, imgMenSalve.getY() + 56, " ");
	private String  conteudoSalvar = "Jogo salvo!";
	
	private int contMenSalvar = 0;
	private int contConteudoSalvar = 0;
	int resutado;
	private Icones_interativos sombreadorSaveAviso = new Icones_interativos(0, 0);
	private Icones_interativos saveAviso = new Icones_interativos(1234/2 - 706/2, 100);
	private Texto txtSaveAviso = new Texto(470, saveAviso.getY() + 140, " ");
	
	//---------------------------------------------------------------------------------------
	
	private int aventureiro; // numero do aventureiro escolido anteriormente
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7, tl8;
	
	private int contTempo = 0;
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	
	public Escolha_de_adversario(int numAventureiro, Escolha_de_personagem PaginaAnterior, Menu PaginaMenu, int [][] TabelaInteracao, boolean NovoJogo, boolean Engrenagem2, String Caminho) {
		contEngranagem2 = Engrenagem2;
		this.caminho = Caminho;

		aventureiro = numAventureiro;
		this.tela1 = PaginaAnterior;
		this.telaMenu = PaginaMenu;
		this.TabelaInteracao = TabelaInteracao;
		this.novoJogo = NovoJogo;
		
		fundo.load(caminho + "res\\fundo0.png");
		tapaResto.load(caminho + "res\\fundo2.png");
		
		engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem1.png");
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");

		contorno.load(caminho + "res\\contorno.png");
		
		// ------------------------------------------- fundo ---------------------------------------------

		camada11.load(caminho + "res\\Fundo bonequinho\\camada0.png");

		camada21.load(caminho + "res\\Fundo bonequinho\\camada2.png");
		camada22.load(caminho + "res\\Fundo bonequinho\\camada2.png");
		
		camada31.load(caminho + "res\\Fundo bonequinho\\camada3.png");
		
		camada41.load(caminho + "res\\Fundo bonequinho\\camada5.png");
		camada42.load(caminho + "res\\Fundo bonequinho\\camada5.png");
		
		camada51.load(caminho + "res\\Fundo bonequinho\\camada6.png");
		camada52.load(caminho + "res\\Fundo bonequinho\\camada6.png");
		
		camada6.load(caminho + "res\\Fundo bonequinho\\camada7.png");
		
		// -------------------------------- aventureiro ---------------------------------------------
		
		iconeAventureiro.setDx(7);
		
		switch(aventureiro) {
			case 0:
				iconeAventureiro.setY(154);
				iconeAventureiro.setX(1234/2 - 300/2 - 60);
				iconeAventureiro.load(caminho + "res\\Bonequinho\\ignis0.png");
				break;
			case 1:
				iconeAventureiro.setY(160);
				iconeAventureiro.setDy(160);
				iconeAventureiro.setX(1234/2 - 300/2 + 30);
				iconeAventureiro.load(caminho + "res\\Bonequinho\\ayla0.png");
				iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla10.png");
				break;
			case 2:
				iconeAventureiro.setY(182);
				iconeAventureiro.setX(1234/2 - 300/2);
				iconeAventureiro.load(caminho + "res\\Bonequinho\\rexthor0.png");
				break;
			case 3:
				iconeAventureiro.setY(170);
				iconeAventureiro.setX(1234/2 - 300/2 - 30);
				iconeAventureiro.load(caminho + "res\\Bonequinho\\kiki0.png");
				break;
			default:
				iconeAventureiro.setY(166);
				iconeAventureiro.setX(1234/2 - 300/2 - 10);
				iconeAventureiro.load(caminho + "res\\Bonequinho\\arius0.png");
				break;
		}
		
		imgLogoAyla.setImagem(null);
		
		// ------------------------------------------- Controles ---------------------------------------------

		teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda1.png");
		teclaDireita.load(caminho + "res\\Teclado\\setaDireita1.png");
		
		teclaZ.setImagem(null);

		//-----------------------------------------------------------------------------------------------
	
		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 27));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		
		txtDialogoLn1.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn1.setCorTexto(new Color (235, 230, 233));
		txtDialogoLn4.setCorTexto(new Color (239, 22, 109));
		
		iconeBoss.setImagem(null);
		iconeIgnis.setImagem(null);
		iconeAyla.setImagem(null);
		iconeRexthor.setImagem(null);
		iconeKiki.setImagem(null);
		iconeArius.setImagem(null);
		
		limparMovimento();
		
		mostrarEstrela();
		
		txtSalvar.setFonte(new Font("Arial", Font.BOLD, 20));
		txtSalvar.setCorTexto(new Color (255, 255, 255));
		
		SalvarJogo();
		
		timer = new Timer(1, this);
		timer.start();
		
	}
	
	public void setTabelaInteracao(int linha, int coluna, int resultado) {
		this.TabelaInteracao[linha][coluna] = resultado;
	}
	
	public int [][] getTabelaInteracao() {
		return TabelaInteracao;
	}
	
	public void SalvarJogo() {
		
		imgMenSalve.setX(-242);
		imgMenSalve.setY(56);
		
		imgMenSalve2.setX(imgMenSalve.getX());
		imgMenSalve2.setY(imgMenSalve.getY());
		
		txtSalvar.setX(84);
		txtSalvar.setY(imgMenSalve.getY() + 56);
		txtSalvar.setTexto(" ");
		
		conteudoSalvar = "Jogo salvo!";
		
		contMenSalvar = 0;
		contConteudoSalvar = 0;
		txtSaveAviso.setX(470);
		txtSaveAviso.setY(saveAviso.getY() + 140);
		txtSaveAviso.setTexto(" ");
		
		
		resutado = salvar.SalvarDados(TabelaInteracao, aventureiro, caminho);
		contMenSalvar ++;
		
		if(resutado == 1) {
			imgMenSalve.load(caminho + "res\\mensagem aviso\\salvar1.png");
			imgMenSalve2.load(caminho + "res\\mensagem aviso\\salvar2.png");
			
		} else {
			sombreadorSaveAviso.load(caminho + "res\\sombreador.png");
			saveAviso.load(caminho + "res\\mensagem aviso\\dialogo.png");
			txtSaveAviso.setTexto("Erro ao salvar!!!");
			
			txtSaveAviso.setFonte(new Font("Arial", Font.PLAIN, 38));
			txtSaveAviso.setCorTexto(new Color (235, 230, 233));
		
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
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        janelaPrincipal.add(telaMenu);
	        janelaPrincipal.setTitle("Menu");
	        telaMenu.valorLeituraSave = salvar.LerDados(caminho);
	        telaMenu.Restaurar();
	        telaMenu.setContEngranagem2(contEngranagem2);
	        janelaPrincipal.revalidate();
	        timer.stop();
		
		}
		
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									Vai para a tela de Manual								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntManual(int codigo) {
		// ------------------------ manda para a tela de Manual ----------------------- /
		if(codigo == KeyEvent.VK_Z ) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        telaManual = new Manual(contEngranagem2, caminho);
	        telaManual.setTela2(this);
	        janelaPrincipal.add(telaManual);
	        janelaPrincipal.setTitle("Manual2");
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
	        telaCreditos.setTela2(this);
	        janelaPrincipal.add(telaCreditos);
	        janelaPrincipal.setTitle("Creditos2");
	        janelaPrincipal.revalidate();
	        timer.stop();

		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									volta para tela anterior								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntVoltar(int codigo) {
		if(dialogoAviso.getImagem() == null && codigo == KeyEvent.VK_Z) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			dialogoAviso.load(caminho + "res\\mensagem aviso\\dialogo.png");
			bntSimDialogoAviso.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogoAviso.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			bntSimNaoDialgoAviso = true;
			
			txtDialogoAviso.setTexto("Se você voltar perderá o progreço nesse save.");
			txtDialogoAviso2.setTexto("Deseja continuar?");
			
		}else if ((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoAviso.getImagem() != null) {
		
			if(contEngranagem1== 2) {
				contEngranagem1= 1;
			} else {contEngranagem1++;}
			
			engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1+ ".png");
			
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
			}
			else {
				
				if(novoJogo ==  true) {
					chamarTela1();
		        
				}else{
					janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
			        janelaPrincipal.remove(this);
			        janelaPrincipal.add(telaMenu);
			        janelaPrincipal.setTitle("Menu");
			        telaMenu.valorLeituraSave = salvar.LerDados(caminho);
			        telaMenu.chamarTela1(true);
			        telaMenu.Restaurar();
			        telaMenu.setContEngranagem2(contEngranagem2);
			        janelaPrincipal.revalidate();
			        timer.stop();
			        
		        }
			}
		}
	}
	
	
	public void animarMovimentoAventureiro(boolean direcao) {

		if(aventureiro != 1) {
			
			iconeAventureiro.setDx(iconeAventureiro.getDx() + (movimentoAventureiro == false ? 1 : -1));
			
			if(iconeAventureiro.getDx() == 0 || iconeAventureiro.getDx() == 10 || iconeAventureiro.getDx() == 20){
				
				iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 2 ? "rexthor" : (aventureiro == 3 ? "kiki" : "arius"))) + (((iconeAventureiro.getDx() + 20)/10) + (direcao == false ? 0 : 5)) + ".png");
				
				if(iconeAventureiro.getDx() == 20 || iconeAventureiro.getDx() == 0) {movimentoAventureiro = !movimentoAventureiro;}
			}
			
		} else {
			
			iconeAventureiro.setDx(iconeAventureiro.getDx() + 1);
			
			if(iconeAventureiro.getDx() == 50 || iconeAventureiro.getDx() == 30){
				
				iconeAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (direcao == false ? 4 : 9) + ".png");
				iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (direcao == false ? 13 : 17) + ".png");
				iconeAventureiro.setY(iconeAventureiro.getY() - 12);
							
			} else if(iconeAventureiro.getDx() == 40 || iconeAventureiro.getDx() == 24 || iconeAventureiro.getDx() == 18 || iconeAventureiro.getDx() == 12 || iconeAventureiro.getDx() == 6) {
				
				iconeAventureiro.load(caminho + "res\\Bonequinho\\ayla" + ((iconeAventureiro.getDx() == 40 || iconeAventureiro.getDx() == 24 || iconeAventureiro.getDx() == 12 ? 3 : 2) + (direcao == false ? 0 : 5)) + ".png");
				iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (direcao == false ? 12 : 16) + ".png");
			
			}
			
			if(iconeAventureiro.getDx() == 27 || iconeAventureiro.getDx() == 24 || iconeAventureiro.getDx() == 21 || iconeAventureiro.getDx() == 18 || iconeAventureiro.getDx() == 15 || iconeAventureiro.getDx() == 12 || iconeAventureiro.getDx() == 9 || iconeAventureiro.getDx() == 6 || iconeAventureiro.getDx() == 3) {
				iconeAventureiro.setY(iconeAventureiro.getY() + 4);
				
			} else if (iconeAventureiro.getDx() == 42 || iconeAventureiro.getDx() == 44 || iconeAventureiro.getDx() == 46 || iconeAventureiro.getDx() == 48) {
				
				iconeAventureiro.setY(iconeAventureiro.getY() - 3);
			} 
			
			
			if(iconeAventureiro.getDx() == 60) {
				iconeAventureiro.setDx(0);
			}
			
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  							dispara quando as teclas são  pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		if(janela != null && janela.getTitle() == "Escolha de Adversário") {
			int codigo = tecla.getKeyCode();
			
			// -------------------- capturar teclado-------------------- \
			
			if(mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() == null) {
				if(codigo == KeyEvent.VK_UP) {
					cima = true;
				} else if(codigo == KeyEvent.VK_DOWN) {
					baixo = true;
				} else if(codigo == KeyEvent.VK_LEFT) {
					esquerda = true;
				} else if(codigo == KeyEvent.VK_RIGHT) {
					direita = true;
				}
			}
						
			// -------------------- abre e fecha o menu -------------------- \
			if(codigo == KeyEvent.VK_ESCAPE && dialogoAviso.getImagem() == null ) {
				
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
			// ----------------------- muda a seleção das opções do menu -------------------------- \
			}else if((codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) && mostrarMenu == true && dialogoAviso.getImagem() == null) {
				
				if(contEngranagem1== 2) {
					contEngranagem1= 1;
				} else {contEngranagem1++;}
				
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
				
			
			// ---------- encaminha para a função que controla o botão do Menu do menu ------\
			}else if(mostrarMenu == true && contMenu == 0){
				dialogoBntMenu(codigo);
			
			// ---------- encaminha para a função que controla o botão de Manual do menu ------\
			}else if(mostrarMenu == true && contMenu == 1){
				dialogoBntManual(codigo);
			
				// ---------- encaminha para a função que controla o botão de Manual do menu ------\
			}else if(mostrarMenu == true && contMenu == 2){
				dialogoBntCreditos(codigo);
			
			// ---------- encaminha para a função que controla o botão voltar do menu --------- \
			}else if(mostrarMenu == true && contMenu == 3) {
				dialogoBntVoltar(codigo);
			
			// ---------- faz o aventureiro andar horizontalmente --------- \
			} else if(mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() == null && (esquerda == true || direita == true)) {
				
				if(contEngranagem1== 2) {
					contEngranagem1= 1;
				} else {contEngranagem1++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
							
				if(camada42.getX() == 0) { camada41.setX(esquerda == true ? -4000 : 4000);}
				else if(camada41.getX() == 0) { camada42.setX(esquerda == true ? -4000 : 4000);}
				
				int camada4Atual = (camada41.getX() < camada42.getX() ? camada41.getX() : camada42.getX());
												
				if(esquerda == true && direita == true) {
					
					pararMovimento();
					
				} else {
					respiracaoAventureiro = false;
					
					if(esquerda == true) {
								
						teclaZ.setX(camada4Atual + (camada4Atual > -520 ? 885 : (camada4Atual > -1116 ? 1480 : (camada4Atual > -1712 ? 2080 : (camada4Atual > -2296 ? 2660 : (camada4Atual > -2880 ? 3250 : (camada4Atual > -3428 ? 3837 : (camada4Atual >= -4000 ? 4300: 0))))))));
						
						if(camada4Atual == -516 || camada4Atual == -1112 || camada4Atual == -1708 || camada4Atual == -2292 || camada4Atual == -2876 || ((ativarBoss == 1 || ativarBoss == 3) && camada4Atual == -3424) || camada4Atual == -3936) {
							if(camada4Atual != -2876 && ativarBoss != 2) {teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");}
							contTeclaBatalha --;
							
						} else if(camada4Atual == -128 || camada4Atual == -716 || camada4Atual == -1312 || camada4Atual == -1896 || camada4Atual == -2488 || ((ativarBoss == 1 || ativarBoss == 3) && camada4Atual == -3076) || camada4Atual == -3548){
							teclaZ.setImagem(null);
							contTeclaBatalha --;
						}
			
						if((ativarBoss == 0 || ativarBoss == 2) && (camada4Atual == -3424 || camada4Atual == -3076)) {
							contTeclaBatalha --;
						}
						
						if(contTeclaBatalha == -1) {contTeclaBatalha = 13;}
												
						if(Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) < 4) {
							if((camada4Atual > -516 && camada4Atual < -128) || (camada4Atual > -1112 && camada4Atual < -716) || (camada4Atual > -1708 && camada4Atual < -1312) || (camada4Atual > -2292 && camada4Atual < -1896) || (camada4Atual > -2876 && camada4Atual < -2488) || (camada4Atual > -3424 && camada4Atual < -3076) || (camada4Atual > -3936 && camada4Atual < -3548)) {
								if(!((ativarBoss == 0 || ativarBoss == 2)  && camada4Atual > -3424 && camada4Atual < -3076)) {
									teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
								}
								
								if(!((camada4Atual > -3896 && camada4Atual < -3500) || (camada4Atual > -3396 && camada4Atual < -3028) || (camada4Atual > -2836 && camada4Atual < -2436) || (camada4Atual > -2248 && camada4Atual < -1852) || (camada4Atual > -1664 && camada4Atual < -1264) || (camada4Atual > -1068 && camada4Atual < -672) || (camada4Atual > -476 && camada4Atual < -84))) {
									contTeclaBatalha --;
								}
							} else if(camada4Atual != -128 && camada4Atual != -716 && camada4Atual != -1312 && camada4Atual != -1896 && camada4Atual != -2488 && camada4Atual != -3076 && camada4Atual != -3548) {
								teclaZ.setImagem(null);
								
								if((camada4Atual > -3896 && camada4Atual < -3500) || (camada4Atual > -3396 && camada4Atual < -3028) || (camada4Atual > -2836 && camada4Atual < -2436) || (camada4Atual > -2248 && camada4Atual < -1852) || (camada4Atual > -1664 && camada4Atual < -1264) || (camada4Atual > -1068 && camada4Atual < -672) || (camada4Atual > -476 && camada4Atual < -84)) {
									contTeclaBatalha --;
								}
							}
						}
						
						camada21.setX((camada21.getX() > 1234 ? camada22.getX() - 5992 : camada21.getX() + 6));
						camada22.setX((camada22.getX() > 1234 ? camada21.getX() - 5992 : camada22.getX() + 6));
						
						camada31.setX(camada41.getX() < camada42.getX() ? camada21.getX() : camada22.getX());
						
						camada41.setX(camada41.getX() + 4);
						camada42.setX(camada42.getX() + 4);
						
						iconeBoss.setX((camada41.getX() > 2000 || camada41.getX() < -3000 ? camada42.getX() : camada41.getX()) - 400);
						iconeIgnis.setX(camada4Atual + 2600 + 4);
						iconeAyla.setX((camada41.getX() > 2000 || camada41.getX() < -3000 ? camada42.getX() : camada41.getX()) + 244 + 4);
						iconeRexthor.setX(camada4Atual + 1420 + 4);
						iconeKiki.setX((camada41.getX() > 2000 || camada41.getX() < -3000 ? camada42.getX() : camada41.getX()) + 824);
						iconeArius.setX(camada4Atual + 2020 + 4);
						
						camada51.setX((camada51.getX() > 1234 ? camada52.getX() - 1992 : camada51.getX() + 1));
						camada52.setX((camada52.getX() > 1234 ? camada51.getX() - 1992 : camada52.getX() + 1));
								
						if((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)).equals("5") || (iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)).equals("6") || (iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)).equals("0") || (iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)).equals("1")) {
							
							iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 1 ? "ayla" : (aventureiro == 2 ? "rexthor" : (aventureiro == 3 ? "kiki" : "arius")))) + "7.png");
							if(aventureiro == 1) {
								iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla16.png");
								iconeAventureiro.setY(iconeAventureiro.getY() - 40);
							}
						}
						
						animarMovimentoAventureiro(true);
					
					}
					
					if(direita == true) {
												
						teclaZ.setX(camada4Atual + (camada4Atual < -3496 ? 4292 : (camada4Atual < -3024 ? 3830 : (camada4Atual < -2432 ? 3242 : (camada4Atual < -1848 ? 2652 : (camada4Atual < -1260 ? 2074 : (camada4Atual < -668 ? 1472 : (camada4Atual < -80 ? 878 : 0))))))));
						
						//faz a tecla Z aparecer quando o personagem chega na montainha
						if(camada4Atual == -3500 || ((ativarBoss == 1 || ativarBoss == 3) && camada4Atual == -3028) || camada4Atual == -2436 || camada4Atual == -1852 || camada4Atual == -1264 || camada4Atual == -672 || camada4Atual == -84) {
							if(camada4Atual != -2436 && ativarBoss != 2) {teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");}
							contTeclaBatalha ++;
							
						} else if(camada4Atual == -3896 || ((ativarBoss == 1 || ativarBoss == 3) && camada4Atual == -3396) || camada4Atual == -2836 || camada4Atual == -2248 || camada4Atual == -1664 || camada4Atual == -1068 || camada4Atual == -476){
							teclaZ.setImagem(null);
							contTeclaBatalha ++;
						}
						
						if((ativarBoss == 0 || ativarBoss == 2) && (camada4Atual == -3028 || camada4Atual == -3396)) {
							contTeclaBatalha ++;
						}
											
						if(contTeclaBatalha == 14) {contTeclaBatalha = 0;}
						
						if(Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) > 4) {
							if((camada4Atual > -3896 && camada4Atual < -3500) || (camada4Atual > -3396 && camada4Atual < -3028) || (camada4Atual > -2836 && camada4Atual < -2436) || (camada4Atual > -2248 && camada4Atual < -1852) || (camada4Atual > -1664 && camada4Atual < -1264) || (camada4Atual > -1068 && camada4Atual < -672) || (camada4Atual > -476 && camada4Atual < -84)) {
								
								if(!((ativarBoss == 0 || ativarBoss == 2) && camada4Atual > -3396 && camada4Atual < -3028)) {
									teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
								}
									
								if(!((camada4Atual > -9 && camada4Atual < 0) || (camada4Atual > -516 && camada4Atual < -128) || (camada4Atual > -1112 && camada4Atual < -716) || (camada4Atual > -1708 && camada4Atual < -1312) || (camada4Atual > -2292 && camada4Atual < -1896) || (camada4Atual > -2876 && camada4Atual < -2488) || (camada4Atual > -3424 && camada4Atual < -3076) || (camada4Atual > -3936 && camada4Atual < -3548))) {
									contTeclaBatalha ++;
								}
							} else if(camada4Atual != -3896 && camada4Atual != -3396 && camada4Atual != -2836 && camada4Atual != -2248 && camada4Atual != -1664 && camada4Atual != -1068 && camada4Atual != -476) {
								teclaZ.setImagem(null);
								
								if((camada4Atual > -9 && camada4Atual < 0) || (camada4Atual > -516 && camada4Atual < -128) || (camada4Atual > -1112 && camada4Atual < -716) || (camada4Atual > -1708 && camada4Atual < -1312) || (camada4Atual > -2292 && camada4Atual < -1896) || (camada4Atual > -2876 && camada4Atual < -2488) || (camada4Atual > -3424 && camada4Atual < -3076) || (camada4Atual > -3936 && camada4Atual < -3548)) {
									contTeclaBatalha ++;
								}
							}
						}
						
						camada21.setX((camada21.getX() < -6000 ? camada22.getX() + 5992 : camada21.getX() - 6));
						camada22.setX((camada22.getX() <= -6000 ? camada21.getX() + 5992 : camada22.getX() - 6));
						
						camada31.setX(camada41.getX() < camada42.getX() ? camada21.getX() : camada22.getX());
						
						camada41.setX(camada41.getX() - 4);
						camada42.setX(camada42.getX() - 4);
						
						iconeBoss.setX((camada41.getX() > 2000 || camada41.getX() < -3000 ? camada42.getX() : camada41.getX()) - 400 );
						iconeIgnis.setX(camada4Atual + 2600 - 4);
						iconeAyla.setX((camada41.getX() > 2000 || camada41.getX() < -3000 ? camada42.getX() : camada41.getX()) + 248 );
						iconeRexthor.setX(camada4Atual + 1420 - 4);
						iconeKiki.setX((camada41.getX() > 2000 || camada41.getX() < -3000 ? camada42.getX() : camada41.getX()) + 824 );
						iconeArius.setX(camada4Atual + 2020 - 4);
						
						camada51.setX((camada51.getX() <= -2000 ? camada52.getX() + 1992 : camada51.getX() - 1));
						camada52.setX((camada52.getX() <= -2000 ? camada51.getX() + 1992 : camada52.getX() - 1));
						
						if((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)).equals("0") || (iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)).equals("1") || (iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)).equals("5") || (iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)).equals("6")) {
							
							iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 1 ? "ayla" : (aventureiro == 2 ? "rexthor" : (aventureiro == 3 ? "kiki" : "arius")))) + "2.png");
							if(aventureiro == 1) {
								iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla12.png");
								iconeAventureiro.setY(iconeAventureiro.getY() - 40);
							}
						}
						
						animarMovimentoAventureiro(false);
						
					}
				}
				

				
			// ---------- faz a ayla voar verticalmnte --------- \
			}
			
			if(mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() == null && (cima == true || baixo == true) && !(cima == true && baixo == true) && aventureiro == 1) {
				
				if(contEngranagem1== 2) {
					contEngranagem1= 1;
				} else {contEngranagem1++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1+ ".png");
				
				iconeAventureiro.setDy(iconeAventureiro.getY());
				
				if(cima == true && iconeAventureiro.getY() > 20) {
					iconeAventureiro.setY(iconeAventureiro.getY() - 2);
				}
				
				if(baixo == true && iconeAventureiro.getY() < 240) {
					
					if(esquerda == false && direita == false){
						iconeAventureiro.setY(iconeAventureiro.getY() + 2);
						
					}else if(iconeAventureiro.getY() < 220) {
						iconeAventureiro.setY(iconeAventureiro.getY() + 2);
					}
				}	
			}
			
			// ---------- muda a seleção dos botões sim, não, coroa e sem coroa na barra de diálogo dos cães --------- \
			else if((codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_LEFT) && mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() != null && bntSimDialogo.getImagem() != null) {
				
				if(contEngranagem1== 2) {
					contEngranagem1= 1;
				} else {contEngranagem1++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1+ ".png");
				
				if(contTeclaBatalha == 4 && contDialogo == 3) {
					tibarCoroa = !tibarCoroa;
					bntSimDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\tibarCoroa" + (tibarCoroa == true ? "3" : "2") + ".png");
					bntNaoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\tibarSemCoroa" + (tibarCoroa == true ? "2" : "3") + ".png");
				
				} else {
					bntSimNao = !bntSimNao;
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim" + (bntSimNao == true? "1" : "2") + ".png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bnt" + (aventureiro == 1 && contTeclaBatalha == 0 && TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 1 ?"Sim" : "Nao") + (bntSimNao == true? "2" : "1") + ".png");
				}
			}
			
			// --------------- quando o diálogo é fechado ele limpa as imagens e textos --------------- \
			else if(codigo == KeyEvent.VK_Z && mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() != null && encerrarDialogo == true && ((bntSimNao == false && !(aventureiro == 1 && contTeclaBatalha == 0 && TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 1)) || (contTeclaBatalha == 4 && bntSimNao == true && tibar != tibarCoroa))) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				limparDialogo();
			}
			
			// ----------------------- diálogo com os cães das colinas ------------------------ \
			else if(codigo == KeyEvent.VK_Z && teclaZ.getImagem() != null && mostrarMenu == false && dialogoAviso.getImagem() == null && saveAviso.getImagem() == null && ativarBoss != 2 && encerrarDialogo == false && moverFundo == false) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				if(contTeclaBatalha != 12 || ativarBoss == 3) {
					sombreadorDialogo.load(caminho + "res\\sombreador.png");
					barraDeDialogo.load(caminho + "res\\escolhaDeAdversario\\barraDeDialogo.png");
					contDialogo++;
				}
				
				System.out.println(contTeclaBatalha);
				switch (contTeclaBatalha) {
					case 4: // Rexthor
						MostrarDialogoRexthor();
					    break;
					case 8: // Ignis
						MostrarDialogoIgnis();
					    break;
					case 2: // Kiki
						MostrarDialogoKiki();
					    break;
					case 0: // Ayla
						MostrarDialogoAyla();
					    break;
					case 6: // Arius
						MostrarDialogoArius();
					    break;
					case 12: // Boss
						
						if(ativarBoss == 3) {
							imagemDoDialogo.setX(1234/2 - 400/2);
							imagemDoDialogo.setY(10);
							
							if(TabelaInteracao[0][1] != 0 || TabelaInteracao[1][1] != 0 || TabelaInteracao[2][1] != 0 || TabelaInteracao[3][1] != 0 || TabelaInteracao[4][1] != 0) {
								
								txtDialogoLn1.setX(184);
								txtDialogoLn2.setX(296);
								txtDialogoLn4.setX(290);
								
								imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\cadeadoGrande1.png");
								
								txtDialogoLn1.setTexto("Parabéns!!! você conseguiu vencer os 5 Cães das Colinas, infelizmente o jogo não");
								txtDialogoLn2.setTexto("está completo ainda e você terá que esperar um pouco mais.");
								txtDialogoLn3.setTexto(" ");
								txtDialogoLn4.setTexto("Muito obrigado por ter jogado e espero que tenha se divertido.");
								
								estrelaFim1.load(caminho + "res\\Creditos\\estrela3.png");
								estrelaFim2.load(caminho + "res\\Creditos\\estrela4.png");
								
								ativarBoss = 1;
								bntSimNao = false;
								encerrarDialogo = true;
							}
						} else {
							ativarBoss = 2;
						}
						
						break;
				}
			}
			
			// ------------------------ manda para a tela de batalha ----------------------- /
			else if(codigo == KeyEvent.VK_Z  && mostrarMenu == false && dialogoAviso.getImagem() == null && encerrarDialogo == true && (bntSimNao == true || (aventureiro == 1 && contTeclaBatalha == 0 && TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 1)) && barraDeDialogo.getImagem() != null && contMenSalvar == -1) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				int [] organizandoAventureiro = {1, 3, 2, 4, 0, 5};
				
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        tela3 = new Batalha(aventureiro, organizandoAventureiro[(contTeclaBatalha == 0 ? 0 : contTeclaBatalha/2)], this, telaMenu, contEngranagem2, caminho);
		        janelaPrincipal.add(tela3);
		        janelaPrincipal.setTitle("Batalha");
		        janelaPrincipal.revalidate();
		        timer.stop();
		        
		        limparDialogo();
				
		        contTeclaBatalha = 0;
			}
			
			
		} else{
			// ------------------------ manda para a tela de Manual ----------------------- /
			if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Manual2") {
				telaManual.KeyPressed(tecla);
			
			} else if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Creditos2") {
				telaCreditos.KeyPressed(tecla);
				
			} else {
				tela3.KeyPressed(tecla);
			}
		}
		
	}
	
	
	public void KeyReleased (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);

		if(janela != null && janela.getTitle() == "Escolha de Adversário") {
			
			int codigo = tecla.getKeyCode();
			
			if(mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() == null) {
				
				if(codigo == KeyEvent.VK_UP) {
					cima = false;
				} else if(codigo == KeyEvent.VK_DOWN) {
					baixo = false;
				} else if(codigo == KeyEvent.VK_LEFT) {
					esquerda = false;
				} else if(codigo == KeyEvent.VK_RIGHT) {
					direita = false;
				}
				
				if(esquerda == false || direita == false) {
					
					pararMovimento();
					
				}
			}
		}
	}
	
	public void limparMovimento() {
		
		cima = false;
		baixo = false;
		esquerda = false;
		direita = false;

	}
	
	public void pararMovimento() {
		
		if(!(Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) == 0 
		    || Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) == 1
		    || Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) == 5
		    || Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) == 6)) {
			
			
			iconeAventureiro.setDx(7);
			movimentoAventureiro = false;
			respiracaoAventureiro = true;
			
			if(iconeAventureiro.getDy() < 20 && aventureiro == 1) {
				iconeAventureiro.setY(20);
			} else if(iconeAventureiro.getDy() > 240  && aventureiro == 1){
				iconeAventureiro.setY(240);
			} else if(aventureiro == 1){
				iconeAventureiro.setY(iconeAventureiro.getDy());
			}
			
			if(Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) < 5) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 1 ? "ayla" : (aventureiro == 2 ? "rexthor" : (aventureiro == 3 ? "kiki" : "arius")))) + "0.png");
				
				if(aventureiro == 1) {
					iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla10.png");
				}
			} else {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 1 ? "ayla" : (aventureiro == 2 ? "rexthor" : (aventureiro == 3 ? "kiki" : "arius")))) + "5.png");
				
				if(aventureiro == 1) {
					iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla14.png");
				}
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  				limpa as informações e imagens dos diálogos com os cães						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void limparDialogo() {
		
		encerrarDialogo = false;
		
		sombreadorDialogo.setImagem(null);
		barraDeDialogo.setImagem(null);
		imagemDoDialogo.setImagem(null);
		imagemDoDialogo.setY(20); imagemDoDialogo.setX(1234/2 - 248/2);
		bntSimDialogo.setImagem(null);
		bntNaoDialogo.setImagem(null);
		
		objetoDeFundo1.setX(0); objetoDeFundo1.setY(0);
		objetoDeFundo2.setX(0); objetoDeFundo2.setY(0);
		objetoDeFundo3.setX(0); objetoDeFundo3.setY(0);
		objetoDeFundo1.setImagem(null); objetoDeFundo2.setImagem(null);
		objetoDeFundo3.setImagem(null);
		
		ativarAnimacaoVelaAyla = 0;
		
		limparMovimento();
		
		txtDialogoLn1.setTexto(" "); txtDialogoLn1.setX(80); txtDialogoLn1.setY(640 - 185 + 26);
		txtDialogoLn2.setTexto(" "); txtDialogoLn2.setX(80); txtDialogoLn2.setY(txtDialogoLn1.getY() + 32);
		txtDialogoLn3.setTexto(" "); txtDialogoLn3.setX(80); txtDialogoLn3.setY(txtDialogoLn2.getY() + 32);
		txtDialogoLn4.setTexto(" "); txtDialogoLn4.setX(80); txtDialogoLn4.setY(txtDialogoLn3.getY() + 32);
		
		mudaCorLn4 = false;
		
		contDialogo = 0;
		estrelaFim1.setImagem(null);
		estrelaFim2.setImagem(null);

		bntSimNao = true;
		tibarCoroa = true;
		
		bntSimDialogo.setY(540);
		bntNaoDialogo.setY(540);
		
		logoAyla = false;
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  	quando o jogador vence alguma batalha estrelas aparecem para marcar essa vitória		|
	\ ---------------------------------------------------------------------------------------- */
	
	public void mostrarEstrela() {
	
		if(TabelaInteracao[0][1] != 0) {
			iconeIgnis.load(caminho + "res\\Insignia\\simbolo ignis.png");
		} 
		if(TabelaInteracao[1][1] != 0) {
			iconeAyla.load(caminho + "res\\Insignia\\simbolo ayla.png");
		} 
		if(TabelaInteracao[2][1] != 0) {
			iconeRexthor.load(caminho + "res\\Insignia\\simbolo rexthor.png");
		} 
		if(TabelaInteracao[3][1] != 0) {
			iconeKiki.load(caminho + "res\\Insignia\\simbolo kiki.png");
		} 
		if(TabelaInteracao[4][1] != 0) {
			iconeArius.load(caminho + "res\\Insignia\\simbolo arius.png");
		} 
			
		if(TabelaInteracao[0][1] != 0 && TabelaInteracao[1][1] != 0 && TabelaInteracao[2][1] != 0 && TabelaInteracao[3][1] != 0 && TabelaInteracao[4][1] != 0) {
			ativarBoss = 1;
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo.getImagem(), fundo.getRedX(), fundo.getRedY(), fundo.getLarg(), fundo.getAlt(), this);
		
		// ------------------------------------------- fundo ---------------------------------------------

		graficos.drawImage(camada6.getImagem(), camada6.getRedX(), camada6.getRedY(), camada6.getLarg(), camada6.getAlt(), this);

		graficos.drawImage(camada51.getImagem(), camada51.getRedX(), camada51.getRedY(), camada51.getLarg(), camada51.getAlt(), this);
		graficos.drawImage(camada52.getImagem(), camada52.getRedX(), camada52.getRedY(), camada52.getLarg(), camada52.getAlt(), this);
		
		graficos.drawImage(camada41.getImagem(), camada41.getRedX(), camada41.getRedY(), camada41.getLarg(), camada41.getAlt(), this);
		graficos.drawImage(camada42.getImagem(), camada42.getRedX(), camada42.getRedY(), camada42.getLarg(), camada42.getAlt(), this);

		graficos.drawImage(camada11.getImagem(), camada11.getRedX(), camada11.getRedY(), camada11.getLarg(), camada11.getAlt(), this);

		graficos.drawImage(camada21.getImagem(), camada21.getRedX(), camada21.getRedY(), camada21.getLarg(), camada21.getAlt(), this);
		graficos.drawImage(camada22.getImagem(), camada22.getRedX(), camada22.getRedY(), camada22.getLarg(), camada22.getAlt(), this);
		
		// ------------------------------------------- Controles ---------------------------------------------

		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getRedX(), teclaEsquerda.getRedY(), teclaEsquerda.getLarg(), teclaEsquerda.getAlt(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getRedX(), teclaDireita.getRedY(), teclaDireita.getLarg(), teclaDireita.getAlt(), this);
		
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getRedX(), teclaZ.getRedY(), teclaZ.getLarg(), teclaZ.getAlt(), this);

		//------------------------------------ icones ----------------------------------------------

		graficos.drawImage(iconeBoss.getImagem(), iconeBoss.getRedX(), iconeBoss.getRedY(), iconeBoss.getLarg(), iconeBoss.getAlt(), this);
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getRedX(), iconeIgnis.getRedY(), iconeIgnis.getLarg(), iconeIgnis.getAlt(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getRedX(), iconeAyla.getRedY(), iconeAyla.getLarg(), iconeAyla.getAlt(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getRedX(), iconeRexthor.getRedY(), iconeRexthor.getLarg(), iconeRexthor.getAlt(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getRedX(), iconeKiki.getRedY(), iconeKiki.getLarg(), iconeKiki.getAlt(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getRedX(), iconeArius.getRedY(), iconeArius.getLarg(), iconeArius.getAlt(), this);
		
		// -------------------------------- aventureiro ---------------------------------------------

		graficos.drawImage(iconeAventureiro.getImagem(), iconeAventureiro.getRedX(), iconeAventureiro.getRedY(), iconeAventureiro.getLarg(), iconeAventureiro.getAlt(), this);
		graficos.drawImage(camada31.getImagem(), camada31.getRedX(), camada31.getRedY(), camada31.getLarg(), camada31.getAlt(), this);
		graficos.drawImage(iconeSombraAventureiro.getImagem(), iconeSombraAventureiro.getRedX(), iconeSombraAventureiro.getRedY(), iconeSombraAventureiro.getLarg(), iconeSombraAventureiro.getAlt(), this);

		//----------------------------------------------------------------------------------
		
		graficos.drawImage(sombreadorDialogo.getImagem(), sombreadorDialogo.getRedX(), sombreadorDialogo.getRedY(), sombreadorDialogo.getLarg(), sombreadorDialogo.getAlt(), this);
		graficos.drawImage(objetoDeFundo3.getImagem(), objetoDeFundo3.getRedX(), objetoDeFundo3.getRedY(), objetoDeFundo3.getLarg(), objetoDeFundo3.getAlt(), this);
		graficos.drawImage(objetoDeFundo2.getImagem(), objetoDeFundo2.getRedX(), objetoDeFundo2.getRedY(), objetoDeFundo2.getLarg(), objetoDeFundo2.getAlt(), this);
		graficos.drawImage(objetoDeFundo1.getImagem(), objetoDeFundo1.getRedX(), objetoDeFundo1.getRedY(), objetoDeFundo1.getLarg(), objetoDeFundo1.getAlt(), this);
		graficos.drawImage(imagemDoDialogo.getImagem(), imagemDoDialogo.getRedX(), imagemDoDialogo.getRedY(), imagemDoDialogo.getLarg(), imagemDoDialogo.getAlt(), this);
		graficos.drawImage(barraDeDialogo.getImagem(), barraDeDialogo.getRedX(), barraDeDialogo.getRedY(), barraDeDialogo.getLarg(), barraDeDialogo.getAlt(), this);

		graficos.setColor(txtDialogoLn1.getCorTexto());
		
		tl1 = new TextLayout(txtDialogoLn1.getTexto(), txtDialogoLn1.getFonte(), frc);
		tl2 = new TextLayout(txtDialogoLn2.getTexto(), txtDialogoLn1.getFonte(), frc);
		tl3 = new TextLayout(txtDialogoLn3.getTexto(), txtDialogoLn1.getFonte(), frc);
		tl4 = new TextLayout(txtDialogoLn4.getTexto(), txtDialogoLn1.getFonte(), frc);

		tl1.draw(graficos, txtDialogoLn1.getRedX(), txtDialogoLn1.getRedY());
		tl2.draw(graficos, txtDialogoLn2.getRedX(), txtDialogoLn2.getRedY());
		tl3.draw(graficos, txtDialogoLn3.getRedX(), txtDialogoLn3.getRedY());

		if(mudaCorLn4 == true) {graficos.setColor(txtDialogoLn4.getCorTexto());}
		tl4.draw(graficos, txtDialogoLn4.getRedX(), txtDialogoLn4.getRedY());
		if(mudaCorLn4 == true) {graficos.setColor(txtDialogoLn1.getCorTexto());}
		
		graficos.drawImage(bntSimDialogo.getImagem(), bntSimDialogo.getRedX(), bntSimDialogo.getRedY(), bntSimDialogo.getLarg(), bntSimDialogo.getAlt(), this);
		graficos.drawImage(bntNaoDialogo.getImagem(), bntNaoDialogo.getRedX(), bntNaoDialogo.getRedY(), bntNaoDialogo.getLarg(), bntNaoDialogo.getAlt(), this);

		graficos.drawImage(estrelaFim1.getImagem(), estrelaFim1.getRedX(), estrelaFim1.getRedY(), estrelaFim1.getLarg(), estrelaFim1.getAlt(), this);
		graficos.drawImage(estrelaFim2.getImagem(), estrelaFim2.getRedX(), estrelaFim2.getRedY(), estrelaFim2.getLarg(), estrelaFim2.getAlt(), this);

		graficos.drawImage(imgLogoAyla.getImagem(), imgLogoAyla.getRedX(), imgLogoAyla.getRedY(), imgLogoAyla.getLarg(), imgLogoAyla.getAlt(), this);

		graficos.drawImage(imgMenSalve.getImagem(), imgMenSalve.getRedX(), imgMenSalve.getRedY(), imgMenSalve.getLarg(), imgMenSalve.getAlt(), this);

		graficos.setColor(txtSalvar.getCorTexto());
		tl7 = new TextLayout(txtSalvar.getTexto(), txtSalvar.getFonte(), frc);
		tl7.draw(graficos, txtSalvar.getRedX(), txtSalvar.getRedY());
		
		graficos.drawImage(imgMenSalve2.getImagem(), imgMenSalve2.getRedX(), imgMenSalve2.getRedY(), imgMenSalve2.getLarg(), imgMenSalve2.getAlt(), this);
		graficos.drawImage(sombreadorSaveAviso.getImagem(), sombreadorSaveAviso.getRedX(), sombreadorSaveAviso.getRedY(), sombreadorSaveAviso.getLarg(), sombreadorSaveAviso.getAlt(), this);
		graficos.drawImage(saveAviso.getImagem(), saveAviso.getRedX(), saveAviso.getRedY(), saveAviso.getLarg(), saveAviso.getAlt(), this);

		graficos.setColor(txtSaveAviso.getCorTexto());
		tl8 = new TextLayout(txtSaveAviso.getTexto(), txtSaveAviso.getFonte(), frc);
		tl8.draw(graficos, txtSaveAviso.getRedX(), txtSaveAviso.getRedY());
		
		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getRedX(), sombreadorMenu.getRedY(), sombreadorMenu.getLarg(), sombreadorMenu.getAlt(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getRedX(), fundoMenu.getRedY(), fundoMenu.getLarg(), fundoMenu.getAlt(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getRedX(), bntMenu.getRedY(), bntMenu.getLarg(), bntMenu.getAlt(), this);
		graficos.drawImage(bntManual.getImagem(), bntManual.getRedX(), bntManual.getRedY(), bntManual.getLarg(), bntManual.getAlt(), this);
		graficos.drawImage(bntVoltar.getImagem(), bntVoltar.getRedX(), bntVoltar.getRedY(), bntVoltar.getLarg(), bntVoltar.getAlt(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getRedX(), bntCreditos.getRedY(), bntCreditos.getLarg(), bntCreditos.getAlt(), this);

		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getRedX(), dialogoAviso.getRedY(), dialogoAviso.getLarg(), dialogoAviso.getAlt(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getRedX(), bntSimDialogoAviso.getRedY(), bntSimDialogoAviso.getLarg(), bntSimDialogoAviso.getAlt(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getRedX(), bntNaoDialogoAviso.getRedY(), bntNaoDialogoAviso.getLarg(), bntNaoDialogoAviso.getAlt(), this);

		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl5 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl6 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso.getFonte(), frc);

		tl5.draw(graficos, txtDialogoAviso.getRedX(), txtDialogoAviso.getRedY());
		tl6.draw(graficos, txtDialogoAviso2.getRedX(), txtDialogoAviso2.getRedY());

		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getRedX(), engrenagem1.getRedY(), engrenagem1.getLarg(), engrenagem1.getAlt(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getRedX(), engrenagem2.getRedY(), engrenagem2.getLarg(), engrenagem2.getAlt(), this);

		graficos.drawImage(contorno.getImagem(), contorno.getRedX(), contorno.getRedY(), contorno.getLarg(), contorno.getAlt(), this);
		graficos.drawImage(tapaResto.getImagem(), tapaResto.getRedX(), tapaResto.getRedY(), tapaResto.getLarg(), tapaResto.getAlt(), this);
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		contTempo++;
		
		if(contMenSalvar != -1) {
			animarSave();
		}
		
		animarteclado();
		
		if(ativarAnimacaoVelaAyla > 0) {
			animarVelas();
		}
		
		if(respiracaoAventureiro == true) {
			animarAventureiroParado();
		}
		
		if(barraDeDialogo.getImagem() != null && contTeclaBatalha == 4) {
			animarJosephine();
		}
		
		if(logoAyla == true) {
			MostrarLogo();
		}
		
		if(ativarBoss == 2) {
			descerPeixe();
		}
		
		if(moverFundo == true){
			MoverFundoDireita();
		}
		
		repaint();
	}
	
	public void MoverFundoDireita() {
		objetoDeFundo1.setX(objetoDeFundo1.getX() + 48);
		imagemDoDialogo.setX(imagemDoDialogo.getX() + 88);
		
		if(objetoDeFundo1.getX() >= 300) {
			moverFundo = false;
		}
	}
	
	public void MostrarLogo() {
		
		if(contLogoAyla == 200) {contLogoAyla = 0;} else {contLogoAyla ++;}
		
		
		if(contLogoAyla == 80) {
			ArrayList<Integer>  mylist = new ArrayList<Integer>();
			mylist.add(1); mylist.add(2);
			Collections.shuffle(mylist);
			
			imgLogoAyla.load(caminho + "res\\EscolhaDeAdversario\\Ayla\\logo" + (mylist.get(0)) + ".png");
			
		} else if (contLogoAyla == 84) {
			imgLogoAyla.setImagem(null);
		}
	}
	
	public void animarAventureiroParado() {
		
		int num = Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)));
		
		if(aventureiro != 1 && aventureiro != 3) {
			
			if(contTempo % 50 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 2 ? "rexthor" : "arius")) + (num < 5 ? "1" : "6") + ".png");
			} else if((contTempo - 15) % 50 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 2 ? "rexthor" : "arius")) + (num < 5 ? "0" : "5") + ".png");
			}
		
		}else if(aventureiro == 3) {
			
			if(contTempo % 10 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\kiki" + (num < 5 ? "1" : "6") + ".png");
			}else if((contTempo - 3) % 10 == 0) {
					iconeAventureiro.load(caminho + "res\\Bonequinho\\kiki" + (num < 5 ? "0" : "5") + ".png");
			}
			
		} else {
			
			if(contTempo % 30 == 0 || (contTempo - 8) % 30 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (num < 5 ? "1" : "6") + ".png");
				iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (num < 5 ? "11" : "15") + ".png");
				
			} else if((contTempo - 3) % 30 == 0 || (contTempo - 11) % 30 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (num < 5 ? "0" : "5") + ".png");
				iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (num < 5 ? "10" : "14") + ".png");
				
			} 
			
			if((contTempo - 1) % 30 == 0 || (contTempo - 9) % 30 == 0 || (contTempo + 1) % 30 == 0 || (contTempo - 7) % 30 == 0) {
				iconeAventureiro.setY(iconeAventureiro.getY() - 6);
			} else if((contTempo - 29) % 30 == 0 || (contTempo - 28) % 30 == 0 || (contTempo -27) % 30 == 0 || (contTempo - 26) % 30 == 0 ||
					  (contTempo - 25) % 30 == 0 || (contTempo - 24) % 30 == 0 || (contTempo -22) % 30 == 0 || (contTempo - 19) % 30 == 0 ||
					  (contTempo - 16) % 30 == 0 || (contTempo - 13) % 30 == 0 || (contTempo -5) % 30 == 0 || (contTempo - 7) % 30 == 0) {
				iconeAventureiro.setY(iconeAventureiro.getY() + 2);
			}
			
		}
		
	}
	
	public void animarteclado() {
		
		if(contTempo % 10 == 0) {
			
			animarApertoTecla = !animarApertoTecla;
			teclaEsquerda.setX(teclaEsquerda.getX() + (animarApertoTecla == false ? 5 : -5));
			teclaDireita.setX(teclaDireita.getX() + (animarApertoTecla == false ? -5 : 5));
			
			if(teclaZ.getImagem() != null) {
				teclaZ.load(caminho + "res\\Teclado\\teclaZ" + (animarApertoTecla == false ? "1" : "2") + ".png");
			}
			
		}
		

		if(iconeIgnis.getImagem() != null || iconeAyla.getImagem() != null || iconeRexthor.getImagem() != null || iconeKiki.getImagem() != null || iconeArius.getImagem() != null) {
			
			if(contTempo % 2 == 0) {
				
				iconeBoss.setY(iconeBoss.getY() + (animarIconeAventureiro == false ? 1 : -1));
				
				iconeIgnis.setY(iconeIgnis.getY() + (animarIconeAventureiro == false ? 1 : -1));
				iconeAyla.setY(iconeAyla.getY() + (animarIconeAventureiro == false ? 1 : -1));
				iconeRexthor.setY(iconeRexthor.getY() + (animarIconeAventureiro == false ? 1 : -1));
				iconeKiki.setY(iconeKiki.getY() + (animarIconeAventureiro == false ? 1 : -1));
				iconeArius.setY(iconeArius.getY() + (animarIconeAventureiro == false ? 1 : -1));
			}
			
			if(contTempo %20 == 0) {
				animarIconeAventureiro = !animarIconeAventureiro;
			}
		}
		
	}
	
	public void animarSave() {
		
		contMenSalvar++;
		
		if(resutado == 1) {
			
			if(contMenSalvar > 0 && contMenSalvar < 16) {
				imgMenSalve.setX(imgMenSalve.getX() + 15);
				imgMenSalve2.setX(imgMenSalve2.getX() + 15);
				
				if(contMenSalvar > 5  && contConteudoSalvar < 11) {
					contConteudoSalvar = (contConteudoSalvar == 7 ? 10 : contConteudoSalvar + 1);
					txtSalvar.setTexto(conteudoSalvar.substring(0,contConteudoSalvar));
				}
			}
			
			if(contMenSalvar == 13) {
				imgMenSalve2.load(caminho + "res\\mensagem aviso\\salvar3.png");
			} else if(contMenSalvar == 15) {
				imgMenSalve2.setImagem(null);
			}
			
			if(contMenSalvar == 25) {
				imgMenSalve2.load(caminho + "res\\mensagem aviso\\salvar3.png");
			}
			
			if(contMenSalvar == 28) {
				imgMenSalve2.load(caminho + "res\\mensagem aviso\\salvar2.png");
				contConteudoSalvar --;
			}
			

			if(contMenSalvar > 26 && contMenSalvar < 42) {
				imgMenSalve.setX(imgMenSalve.getX() - 15);
				imgMenSalve2.setX(imgMenSalve2.getX() - 15);
				
				if(contMenSalvar > 26 && contConteudoSalvar > 0) {
					contConteudoSalvar = contConteudoSalvar + (contConteudoSalvar == 7 ? -2 : 0);
					txtSalvar.setTexto(conteudoSalvar.substring(0,contConteudoSalvar --));
				}
				
				if(contConteudoSalvar == 0) {
					txtSalvar.setTexto(" ");
				}
			}
			if(contMenSalvar > 42) {
				imgMenSalve.setImagem(null);
				contMenSalvar = -1;
			}
			
		}
		
	}
	
	public void animarVelas() {
	
		objetoDeFundo1.setY(objetoDeFundo1.getY() + (ativarAnimacaoVelaAyla == 1 ? 1 : -1));
		objetoDeFundo2.setY(objetoDeFundo2.getY() + (ativarAnimacaoVelaAyla == 1 ? -1 : 1));
	
		
		if(objetoDeFundo1.getY() == 86) {
			ativarAnimacaoVelaAyla = 2;
		} else if(objetoDeFundo1.getY() == 70) {
			ativarAnimacaoVelaAyla = 1;
		} 
		
	}
	
	public void chamarTela1() {
		
		janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
        janelaPrincipal.remove(this);
        janelaPrincipal.add(tela1);
        janelaPrincipal.setTitle("Escolha de Personagem");
        tela1.setContEngranagem2(contEngranagem2);
        tela1.LimparTela2();
        janelaPrincipal.revalidate();
        timer.stop();
	}
	
	public void LimparTela3() {
		tela3 = null;
	}
	

	public void setContEngranagem2(boolean contEngranagem2) {
		this.contEngranagem2 = contEngranagem2;
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");	
		timer.start();
	}
	
	public void setNumAdversarioAnterior(int NumAdversarioAnterior) {
		this.contTeclaBatalha = (NumAdversarioAnterior == 0 ? 8 : (NumAdversarioAnterior == 1 ? 0 : (NumAdversarioAnterior == 2 ? 4 : (NumAdversarioAnterior == 3 ? 2 : (NumAdversarioAnterior == 4 ? 6 : 12)))));
	}
	
	public void animarJosephine() {
		
		if(contAnimacaoJosephine == 19) {contAnimacaoJosephine = 0;}
		else {contAnimacaoJosephine ++;}
		
		if(contAnimacaoJosephine == 3 || contAnimacaoJosephine == 9 || contAnimacaoJosephine == 16) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josephine2.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco1.png");
		} else if(contAnimacaoJosephine == 5) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josephine3.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco2.png");
		} else if(contAnimacaoJosephine == 12) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josephine1.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco2.png");
		} 
	
	}
	
	public void descerPeixe() {
		
		if(iconeBoss.getImagem() == null) {
			iconeBoss.load(caminho + "res\\Insignia\\peixe voador.png");
			teclaZ.setImagem(null);
			
		} else if(iconeBoss.getY() < 24) {
			iconeBoss.setY(iconeBoss.getY() + 2);
			
			
			iconeBoss.setDy(iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 5 : -5));
			
			
			fundo.setX(fundo.getX() + iconeBoss.getDy());
			tapaResto.setX(tapaResto.getX() + iconeBoss.getDy());
			
			engrenagem1.setX(engrenagem1.getX() + iconeBoss.getDy());
			engrenagem2.setX(engrenagem2.getX() + iconeBoss.getDy());
			contorno.setX(contorno.getX() + iconeBoss.getDy());
			
			camada11.setX(camada11.getX() + iconeBoss.getDy());
			camada21.setX(camada21.getX() + iconeBoss.getDy());
			camada22.setX(camada22.getX() + iconeBoss.getDy());
			camada31.setX(camada31.getX() + iconeBoss.getDy());
			camada41.setX(camada41.getX() + iconeBoss.getDy());
			camada42.setX(camada42.getX() + iconeBoss.getDy());
			camada51.setX(camada51.getX() + iconeBoss.getDy());
			camada52.setX(camada52.getX() + iconeBoss.getDy());
			camada6.setX(camada6.getX() + iconeBoss.getDy());
			
			iconeAventureiro.setX(iconeAventureiro.getX() + iconeBoss.getDy());
			
			teclaEsquerda.setX(teclaEsquerda.getX() + iconeBoss.getDy());
			teclaDireita.setX(teclaDireita.getX() + iconeBoss.getDy());
			teclaZ.setX(teclaZ.getX() + iconeBoss.getDy());

			iconeBoss.setX(iconeBoss.getX() + iconeBoss.getDy());
			iconeIgnis.setX(iconeIgnis.getX() + iconeBoss.getDy());
			iconeAyla.setX(iconeAyla.getX() + iconeBoss.getDy());
			iconeRexthor.setX(iconeRexthor.getX() + iconeBoss.getDy());
			iconeKiki.setX(iconeKiki.getX() + iconeBoss.getDy());
			iconeArius.setX(iconeArius.getX() + iconeBoss.getDy());
		
			
		} else {
			teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
			ativarBoss = 3;
		}
		
	}
	
	// ------------------------------ dialogo com os cães das colinas -------------------------
	public void MostrarDialogoRexthor() {
		
		objetoDeFundo1.setY(-40); objetoDeFundo1.setX(30);
		objetoDeFundo2.setY(-40); objetoDeFundo2.setX(30);
		objetoDeFundo3.setY(-40); objetoDeFundo3.setX(30);
		
		if(contDialogo == 1 && TabelaInteracao[2][1] != 0) {
			imagemDoDialogo.setX(1234/2 - 400/2 - 30);
			imagemDoDialogo.setY(70);
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\rexthor2.png");
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josephine1.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco1.png");
			objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\objeto1.png");
			
			txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[6][0]);
			txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[6][1]);
			txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[6][2]);
			txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[6][3]);
			
			bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			
		} else if(contDialogo == 1) {
			imagemDoDialogo.setX(1234/2 - 400/2 - 30); imagemDoDialogo.setY(70);
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\rexthor1.png");
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josephine1.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco1.png");
			objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\objeto1.png");
			
			txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[0][0]);
			txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[0][1]);
			txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[0][2]);
			txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[0][3]);
			
			bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			
		} else if(contDialogo == 2){
			bntSimDialogo.setImagem(null);
			bntNaoDialogo.setImagem(null);
			
			if(bntSimNao == true){
				imagemDoDialogo.setX(1234/2 - 400/2 - 30);
				imagemDoDialogo.setY(70);
				
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\rexthor2.png");
				
				txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[2][0]);
				txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[2][1]);
				txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[2][2]);
				txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[2][3]);
				
			} else {
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\rexthor3.png");
				
				txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[1][0]);
				txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[1][1]);
				txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[1][2]);
				txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[1][3]);
				
				encerrarDialogo = true;
			
			}
		}  else if(contDialogo == 3){
			bntSimDialogo.setX(1234/2 - 464/2);
			bntSimDialogo.setY(520);
			
			bntNaoDialogo.setX(1234/2 - 464/2 + 370);
			bntNaoDialogo.setY(520);
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\rexthor2.png");
			
			txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[3][0]);
			txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[3][1]);
			txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[3][2]);
			txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[3][3]);
			
			bntSimDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\tibarCoroa3.png");
			bntNaoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\tibarSemCoroa2.png");
		
		} else if(contDialogo == 4){
			imagemDoDialogo.setY(70);
			imagemDoDialogo.setX(1234/2 - 300/2);
			bntSimDialogo.setImagem(null);
			bntNaoDialogo.setImagem(null);
			
			tibar = aleatorioTibar.nextInt(2)== 0 ? true : false;
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\" + (tibar == true ? "tibarCoroa" : "tibarSemCoroa") + "1.png");
			
			if(tibarCoroa == tibar){
				txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[4][0]);
				txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[4][1]);
				txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[4][2]);
				txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[4][3]);

				mudaCorLn4 = true;
			} else {
				txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[5][0]);
				txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[5][1]);
				txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[5][2]);
				txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[5][3]);
			}
			
			encerrarDialogo = true;
			
		}
		
	}
	
	public void MostrarDialogoIgnis() {
		
		objetoDeFundo2.setX(0); objetoDeFundo2.setY(20);
		objetoDeFundo1.setY(20); objetoDeFundo1.setX(20);
		
		if(contDialogo == 1 && TabelaInteracao[0][1] != 0) {
			imagemDoDialogo.setX(1234/2 - 500/2 - 100);
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ignis\\ignis1.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ignis\\objeto2.png");
			
			txtDialogoLn1.setTexto(ignis.getConteudoEscolhaAdversario()[3][0]);
			txtDialogoLn2.setTexto(ignis.getConteudoEscolhaAdversario()[3][1]);
			txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[3][2]);
			txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[3][3]);
			
			bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			
		} else if(contDialogo == 1) {
			imagemDoDialogo.setX(1234/2 - 500/2 - 100);
			
			txtDialogoLn2.setX(270);
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ignis\\ignis1.png");
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ignis\\objeto1.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ignis\\objeto2.png");
			
			txtDialogoLn1.setTexto(ignis.getConteudoEscolhaAdversario()[0][0]);
			txtDialogoLn2.setTexto(ignis.getConteudoEscolhaAdversario()[0][1]);
			txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[0][2]);
			txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[0][3]);
			
			bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			
		} else if(contDialogo == 2){
			bntSimDialogo.setImagem(null);
			bntNaoDialogo.setImagem(null);
			txtDialogoLn2.setX(100);
			
			if(bntSimNao == true){
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ignis\\ignis2.png");
				
				txtDialogoLn1.setTexto(ignis.getConteudoEscolhaAdversario()[1][0]);
				txtDialogoLn2.setTexto(ignis.getConteudoEscolhaAdversario()[1][1]);
				txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[1][2]);
				txtDialogoLn4.setTexto(ignis.getConteudoEscolhaAdversario()[1][3]);
				
				mudaCorLn4 = true;
			} else {
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ignis\\ignis3.png");
				
				txtDialogoLn1.setTexto(ignis.getConteudoEscolhaAdversario()[2][0]);
				txtDialogoLn2.setTexto(ignis.getConteudoEscolhaAdversario()[2][1]);
				txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[2][2]);
				txtDialogoLn4.setTexto(ignis.getConteudoEscolhaAdversario()[2][3]);
			}
			
			encerrarDialogo = true;
		} 
	}

	public void MostrarDialogoKiki() {
		
		objetoDeFundo1.setX(-20); objetoDeFundo1.setY(-40);
		objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Kiki\\objeto1.png");
		
		if(contDialogo == 1 && TabelaInteracao[3][1] != 0) {
			imagemDoDialogo.setX(1234/2 - 300/2);
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Kiki\\kiki4.png");
			
			txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[4][0]);
			txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[4][1]);
			txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[4][2]);
			txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[4][3]);
			
			bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			
		} else if(contDialogo == 1) {
			imagemDoDialogo.setX(1234/2 - 300/2);
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Kiki\\kiki1.png");
			
			txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[0][0]);
			txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[0][1]);
			txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[0][2]);
			txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[0][3]);
			
			bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			
		} else if(contDialogo == 2){
			bntSimDialogo.setImagem(null);
			bntNaoDialogo.setImagem(null);
			
			if(bntSimNao == true){
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Kiki\\kiki5.png");
				
				txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[3][0]);
				txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[3][1]);
				txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[3][2]);
				txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[3][3]);
				mudaCorLn4 = true;
				
				encerrarDialogo = true;
			} else {
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Kiki\\kiki3.png");
				
				txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[1][0]);
				txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[1][1]);
				txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[1][2]);
				txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[1][3]);
			}
		} else if(contDialogo == 3 && bntSimNao == false){
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Kiki\\kiki2.png");
			
			txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[2][0]);
			txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[2][1]);
			txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[2][2]);
			txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[2][3]);
			
			encerrarDialogo = true;
		}
		
	}

	public void MostrarDialogoAyla() {
		
		if(TabelaInteracao[0][1] == 3 && TabelaInteracao[1][1] == 3 && TabelaInteracao[2][1] == 3 && TabelaInteracao[3][1] == 3 && TabelaInteracao[4][1] == 3) {
			
			if(contDialogo == 1) {
				
				imagemDoDialogo.setX(1234/2 - 480/2);
				imagemDoDialogo.setY(40);
				
				objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
				objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
				objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
				
				ativarAnimacaoVelaAyla = 1;
				
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
				
				objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
				objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
				objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
				
				txtDialogoLn2.setY(txtDialogoLn2.getY() - 4); txtDialogoLn3.setY(txtDialogoLn3.getY() - 8);
				
				txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[72][0]);
				txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[72][1]);
				txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[72][2]);
				txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[72][3]);
				
			} else if(contDialogo == 2) {
				
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
				
				txtDialogoLn2.setY(txtDialogoLn2.getY() + 8);
				
				txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[73][0]);
				txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[73][1]);
				txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[73][2]);
				txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[73][3]);
				
			} else if(contDialogo == 3) {
				
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
				
				//txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
				
				txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[74][0]);
				txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[74][1]);
				txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[74][2]);
				txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[74][3]);
				
				bntSimNao = false;
				encerrarDialogo = true;
			}
		
		// se a personagem escolhida for a Ayla
		} else if(aventureiro == 1) {
			// se for a primeira interação
			if(TabelaInteracao[1][0] == 0) {
				
				if(contDialogo == 1) {
					
					objetoDeFundo1.setX(0);
					objetoDeFundo1.setY(0);
					imagemDoDialogo.setX(-600);
					imagemDoDialogo.setY(30);
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla4.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[0][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[0][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[0][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[0][3]);
					
				} else if(contDialogo == 2) {
										
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[1][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[1][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[1][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[1][3]);
					
					moverFundo = true;
					
				} else if(contDialogo == 3) {
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() - 4);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[2][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[2][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[2][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[2][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
				
				} else if(contDialogo == 4) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 4); txtDialogoLn3.setY(txtDialogoLn3.getY() - 4);
					
					if(bntSimNao == true) {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[3][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[3][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[3][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[3][3]);
						
						mudaCorLn4 = true;
					} else {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[4][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[4][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[4][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[4][3]);
					}
					
					TabelaInteracao[1][0] = 1;
					salvar.SalvarDados(TabelaInteracao, aventureiro, caminho);
					encerrarDialogo = true;
					
				}
			// se o jogador dessidir não batalhar na primeira interação
			} else if(TabelaInteracao[1][0] == 1 && TabelaInteracao[1][4] == 0) {
				
				if(contDialogo == 1) {
					logoAyla = true;
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[5][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[5][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[5][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[5][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
				
				} else if(contDialogo == 2) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					if(bntSimNao == true) {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
						
						txtDialogoLn3.setY(txtDialogoLn3.getY() - 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[6][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[6][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[6][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[6][3]);
						
						mudaCorLn4 = true;
					} else {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[7][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[7][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[7][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[7][3]);
						
					}
					
					encerrarDialogo = true;
				}
			// se o jogador dessistiu <= 5 vezes na ultima batalha com a ayla
			} else if(TabelaInteracao[1][4] == 3 && TabelaInteracao[1][3] <= 5) {
				
				if(contDialogo == 1) {
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[8][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[8][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[8][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[8][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
				
				} else if(contDialogo == 2) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
					
					if(bntSimNao == true) {
						
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
						
						txtDialogoLn3.setY(txtDialogoLn3.getY() - 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[9][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[9][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[9][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[9][3]);
						
						mudaCorLn4 = true;
					} else {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[10][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[10][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[10][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[10][3]);
						
					}
					
					encerrarDialogo = true;
				}
			// se o jogador dessistiu a 6ª vez na ultima batalha com a ayla
			} else if(TabelaInteracao[1][4] == 3 && TabelaInteracao[1][3] == 6) {
				
				if(contDialogo == 1) {
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
					 
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[11][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[11][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[11][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[11][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
				
				} else if(contDialogo == 2) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					txtDialogoLn3.setY(txtDialogoLn3.getY() - 4);
					
					if(bntSimNao == true) {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[12][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[12][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[12][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[12][3]);
						
						mudaCorLn4 = true;
					} else {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[13][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[13][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[13][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[13][3]);
						
					}
					
					encerrarDialogo = true;
				}
			// se jogagor perdeu na última luta com 0 vitórias
			} else if(TabelaInteracao[1][4] == 2 && TabelaInteracao[1][1] == 0) {
				
				if(contDialogo == 1) {
					logoAyla = true;
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 8);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[14][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[14][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[14][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[14][3]);
					
				}else if(contDialogo == 2) {
					
					txtDialogoLn3.setY(txtDialogoLn3.getY() - 4);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[15][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[15][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[15][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[15][3]);
					
				}else if(contDialogo == 3) {
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() - 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 4);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[16][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[16][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[16][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[16][3]);
					
				}else if(contDialogo == 4) {
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[17][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[17][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[17][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[17][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
				
				} else if(contDialogo == 5) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
					
					if(bntSimNao == true) {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[18][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[18][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[18][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[18][3]);
						
						mudaCorLn4 = true;
					} else {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[19][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[19][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[19][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[19][3]);
					}
					
					encerrarDialogo = true;
				}
			// 1º vitória na última luta
			} else if(TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 1) {
				
				if(contDialogo == 1) {
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() - 6);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[20][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[20][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[20][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[20][3]);
					
				} else if(contDialogo == 2) {
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 6);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[21][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[21][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[21][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[21][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntSim2.png");
				
				} else if(contDialogo == 3) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[22][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[22][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[22][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[22][3]);
					
					mudaCorLn4 = true;
					encerrarDialogo = true;
				}
			
				// perdeu na última luta, mas tem 1 vitória
			} else if(TabelaInteracao[1][4] == 2 && TabelaInteracao[1][1] == 1) {
				
				if(contDialogo == 1) {
					logoAyla = true;
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 2); txtDialogoLn3.setY(txtDialogoLn3.getY() + 4);
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[23][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[23][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[23][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[23][3]);
					
				} else if(contDialogo == 2) {
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 2); txtDialogoLn3.setY(txtDialogoLn3.getY() - 8);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[24][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[24][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[24][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[24][3]);
					
				} else if(contDialogo == 3) {
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() - 6);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[25][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[25][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[25][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[25][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
				
				} else if(contDialogo == 4) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					if(bntSimNao == true) {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[26][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[26][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[26][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[26][3]);
						
						mudaCorLn4 = true;
					} else {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[27][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[27][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[27][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[27][3]);
					}
					
					encerrarDialogo = true;
				}
			
				// 2º vitória na última luta
			} else if(TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 2) {
				
				if(contDialogo == 1) {
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() - 4); txtDialogoLn3.setY(txtDialogoLn3.getY() - 8);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[28][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[28][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[28][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[28][3]);
					
				} else if(contDialogo == 2) {
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() + 8); txtDialogoLn3.setY(txtDialogoLn3.getY() + 8);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[29][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[29][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[29][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[29][3]);
					
				} else if(contDialogo == 3) {
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[30][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[30][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[30][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[30][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
				
				} else if(contDialogo == 4) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
					
					if(bntSimNao == true) {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[31][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[31][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[31][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[31][3]);
						
						mudaCorLn4 = true;
					} else {
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[32][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[32][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[32][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[32][3]);
					}
					
					encerrarDialogo = true;
				}
			// derrota na última luta com 2 vitórias
			} else if(TabelaInteracao[1][4] == 2 && TabelaInteracao[1][1] == 2) {
				
				if(contDialogo == 1) {
					logoAyla = true;
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() - 2); txtDialogoLn3.setY(txtDialogoLn3.getY() + 4);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[33][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[33][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[33][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[33][3]);
					
				} else if(contDialogo == 2) {
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[34][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[34][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[34][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[34][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
					
				} else if(contDialogo == 3) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					if(bntSimNao == true) {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[35][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[35][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[35][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[35][3]);
						
						mudaCorLn4 = true;
					} else {
						
						txtDialogoLn2.setY(txtDialogoLn2.getY() + 6);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[36][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[36][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[36][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[36][3]);
					}
					
					encerrarDialogo = true;
				}
			// 3º vitória na última luta
			} else if(TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 3) {
				
				if(contDialogo == 1) {
					
					imagemDoDialogo.setX(1234/2 - 480/2);
					imagemDoDialogo.setY(40);
					
					objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
					objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
					objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
					
					ativarAnimacaoVelaAyla = 1;
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
					
					objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
					objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
					objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
					
					txtDialogoLn2.setY(txtDialogoLn2.getY() - 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 4);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[37][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[37][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[37][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[37][3]);
					
				} else if(contDialogo == 2) {
					
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[38][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[38][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[38][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[38][3]);
					
					bntSimNao = false;
					encerrarDialogo = true;
				}
			}
		// se a personagem escolhida [[NÃO]] for a Ayla
		} else {
			
			// se o jogador não tiver ganhado 3 vezes
			if(TabelaInteracao[1][1] != 3) {
				if(contDialogo == 1) {
					
					imagemDoDialogo.setX(0);
					imagemDoDialogo.setY(0);
					imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");
					
					txtDialogoLn4.setX(1234 - 524);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[39][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[39][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[39][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[39][3]);
					
				} else if(contDialogo == 2) {
					
					txtDialogoLn4.setX(80);
					
					txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[40][0]);
					txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[40][1]);
					txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[40][2]);
					txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[40][3]);
					
					bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
					
				} else if(contDialogo == 3) {
					bntSimDialogo.setImagem(null);
					bntNaoDialogo.setImagem(null);
					
					if(bntSimNao == true) {
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[41][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[41][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[41][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[41][3]);
						
					} else {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca2.png");
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[42][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[42][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[42][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[42][3]);
						
						if(TabelaInteracao[1][0] == 0) {
							encerrarDialogo = true;
							TabelaInteracao[1][0] = 1;
							salvar.SalvarDados(TabelaInteracao, aventureiro, caminho);
						}
					}
				}
				
			// se o jogador ja tiver ganhado 3 vezes
			} else if(TabelaInteracao[1][1] == 3 && contDialogo < 4) {
					
				imagemDoDialogo.setX(0);
				imagemDoDialogo.setY(0);
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");
				
				txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
				
				txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[69][0]);
				txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[69][1]);
				txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[69][2]);
				txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[69][3]);
				
				contDialogo = 3;
					
			} else if(contDialogo >= 4) {
				
				// se for a primeira interação
				if(TabelaInteracao[1][0] == 0) {
				
					if(contDialogo == 4) {
						imagemDoDialogo.setX(1234/2 - 480/2);
						imagemDoDialogo.setY(40);
						
						objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
						objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
						objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
						
						ativarAnimacaoVelaAyla = 1;
						
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
						
						objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
						objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
						objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
						
						txtDialogoLn2.setY(txtDialogoLn2.getY() + 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 8);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[43][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[43][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[43][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[43][3]);
						
					} else if(contDialogo == 5) {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");	
						
						txtDialogoLn3.setY(txtDialogoLn3.getY() - 8);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[44][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[44][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[44][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[44][3]);
						
						mudaCorLn4 = true;
						
						encerrarDialogo = true;
						TabelaInteracao[1][0] = 1;
						salvar.SalvarDados(TabelaInteracao, aventureiro, caminho);
					}
				
				// se teve o primeiro contato, mas não batalhou
				} else if(TabelaInteracao[1][0] == 1 && TabelaInteracao[1][4] == 0) {
					
					if(bntSimNao == true) {
						
						if(contDialogo == 4) {
							logoAyla = true;
							
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() + 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 8);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[43][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[43][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[43][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[43][3]);
							
						} else if(contDialogo == 5) {
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");	
							
							txtDialogoLn3.setY(txtDialogoLn3.getY() - 8);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[44][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[44][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[44][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[44][3]);
							
							mudaCorLn4 = true;
							encerrarDialogo = true;
						}
						
					} else {
						
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");	
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[45][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[45][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[45][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[45][3]);
						
						encerrarDialogo = true;
					}
				
				// se desistiu <= 5 no meio da última luta 
				} else if(TabelaInteracao[1][4] == 3 && TabelaInteracao[1][3] <= 5) {
					
					if(bntSimNao == true) {
						
						if(contDialogo == 4) {
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() - 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 4);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[46][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[46][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[46][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[46][3]);
							
						} else if(contDialogo == 5) {
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() + 8); txtDialogoLn3.setY(txtDialogoLn3.getY() - 8);
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");	
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[47][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[47][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[47][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[47][3]);
							
							mudaCorLn4 = true;
							encerrarDialogo = true;
						}
						
					} else {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");	
						txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[48][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[48][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[48][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[48][3]);
						
						encerrarDialogo = true;
					}
				
				// se dessistiu a 6ª vez na ultima batalha
				} else if(TabelaInteracao[1][4] == 3 && TabelaInteracao[1][3] == 6) {
					
					if(bntSimNao == true) {
						
						if(contDialogo == 4) {
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[49][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[49][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[49][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[49][3]);
							
						} else if(contDialogo == 5) {
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() + 4); txtDialogoLn3.setY(txtDialogoLn3.getY() - 4);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[50][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[50][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[50][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[50][3]);
							
							mudaCorLn4 = true;
							encerrarDialogo = true;
						}
						
					} else {
						
						txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[51][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[51][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[51][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[51][3]);
						
						encerrarDialogo = true;
					}
					// perdeu na última luta com 0 vitórias
				} else if(TabelaInteracao[1][4] == 2 && TabelaInteracao[1][1] == 0) {
					
					if(bntSimNao == true) {
						
						if(contDialogo == 4) {
							logoAyla = true;
							
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() - 4);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[52][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[52][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[52][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[52][3]);
							
						} else if(contDialogo == 5) {
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
							txtDialogoLn2.setY(txtDialogoLn2.getY() + 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 4);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[53][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[53][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[53][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[53][3]);
							
						} else if(contDialogo == 6) {
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[54][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[54][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[54][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[54][3]);
							
							mudaCorLn4 = true;
							encerrarDialogo = true;
						}
						
					} else {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");	
						txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[55][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[55][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[55][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[55][3]);
						
						encerrarDialogo = true;
					}
					
				// 1º vitória na última luta
				} else if(TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 1) {
					
					if(bntSimNao == true || (bntSimNao == false && contDialogo == 6)) {
						if(contDialogo == 4) {
							
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() - 6);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[56][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[56][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[56][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[56][3]);
							
						} else if(contDialogo == 5) {
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() + 6);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[57][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[57][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[57][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[57][3]);
							
							bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
							bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntSim2.png");
						
						} else if(contDialogo == 6) {
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[58][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[58][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[58][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[58][3]);
							
							mudaCorLn4 = true;
							encerrarDialogo = true;
						}
						
					} else {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[59][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[59][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[59][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[59][3]);
						
						encerrarDialogo = true;
					}
					// perdeu na última luta, mas tem 1 vitória
				} else if(TabelaInteracao[1][4] == 2 && TabelaInteracao[1][1] == 1) {
					
					if(bntSimNao == true) {
						
						if(contDialogo == 4) {
							logoAyla = true;
							
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() + 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 2);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[60][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[60][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[60][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[60][3]);
							
						} else if(contDialogo == 5) {
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
							txtDialogoLn3.setY(txtDialogoLn3.getY() - 4);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[61][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[61][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[61][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[61][3]);
							
							mudaCorLn4 = true;
							encerrarDialogo = true;
						}
						
					} else {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");	
						txtDialogoLn2.setY(txtDialogoLn2.getY() - 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[62][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[62][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[62][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[62][3]);
						
						encerrarDialogo = true;
					}
					// 2º vitória na última luta
				} else if(TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 2) {
					
					if(bntSimNao == true) {
						
						if(contDialogo == 4) {
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() + 4); txtDialogoLn3.setY(txtDialogoLn3.getY() + 2);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[63][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[63][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[63][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[63][3]);
						
						} else if(contDialogo == 5) {
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[64][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[64][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[64][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[64][3]);
							
						} else if(contDialogo == 6) {
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[65][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[65][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[65][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[65][3]);
							
							mudaCorLn4 = true;
							encerrarDialogo = true;
						}
						
					} else {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");	
						txtDialogoLn2.setY(txtDialogoLn2.getY() - 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[66][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[66][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[66][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[66][3]);
						
						encerrarDialogo = true;
					}
					// perdeu na última luta, mas tem 2 vitória
				} else if(TabelaInteracao[1][4] == 2 && TabelaInteracao[1][1] == 2) {
					
					if(bntSimNao == true) {
						
						if(contDialogo == 4) {
							logoAyla = true;
							
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn2.setY(txtDialogoLn2.getY() - 4);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[67][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[67][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[67][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[67][3]);
							
							mudaCorLn4 = true;
							encerrarDialogo = true;
						}
						
					} else {
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");	
						txtDialogoLn2.setY(txtDialogoLn2.getY() + 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[68][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[68][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[68][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[68][3]);
						
						encerrarDialogo = true;
					}
					// 3º vitória na última luta
				} else if(TabelaInteracao[1][4] == 1 && TabelaInteracao[1][1] == 3) {
					
					if(contDialogo == 4) {
						imagemDoDialogo.setX(1234/2 - 480/2);
						imagemDoDialogo.setY(40);
						
						objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
						objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
						objetoDeFundo3.setX(12); objetoDeFundo3.setY(40);
						
						ativarAnimacaoVelaAyla = 1;
						
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");
						
						objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
						objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
						objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
						
						txtDialogoLn2.setY(txtDialogoLn2.getY() - 8); txtDialogoLn3.setY(txtDialogoLn3.getY() + 4);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[70][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[70][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[70][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[70][3]);
					
					} else if(contDialogo == 5) {
						
						imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla1.png");
						
						txtDialogoLn2.setY(txtDialogoLn2.getY() + 2); txtDialogoLn3.setY(txtDialogoLn3.getY() - 8);
						
						txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[71][0]);
						txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[71][1]);
						txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[71][2]);
						txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[71][3]);
						
						bntSimNao = false;
						encerrarDialogo = true;
					}
						
				}
			}
		}
	}
	
	public void MostrarDialogoArius() {
		objetoDeFundo1.setX(10); objetoDeFundo1.setY(-60);
		objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Arius\\objeto1.png");
		
		if(contDialogo == 1 && TabelaInteracao[4][1] != 0) {
			imagemDoDialogo.setX(1234/2 - 462/2);
			imagemDoDialogo.setY(20);

			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Arius\\arius1.png");
			
			txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[4][0]);
			txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[4][1]);
			txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[4][2]);
			txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[4][3]);
			
			bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
		
		} else if(contDialogo == 1) {
			
			imagemDoDialogo.setX(1234/2 - 462/2);
			imagemDoDialogo.setY(20);
			
			imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Arius\\arius1.png");
			
			txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[0][0]);
			txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[0][1]);
			txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[0][2]);
			txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[0][3]);
			
		} else if(contDialogo == 2) {
			
			txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[1][0]);
			txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[1][1]);
			txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[1][2]);
			txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[1][3]);
			
			bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			
		} else if(contDialogo == 3) {
			bntSimDialogo.setImagem(null);
			bntNaoDialogo.setImagem(null);
			
			if(bntSimNao == true) {
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Arius\\arius2.png");
				
				txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[2][0]);
				txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[2][1]);
				txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[2][2]);
				txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[2][3]);
				
				mudaCorLn4 = true;
			} else {
				imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Arius\\arius3.png");
				
				txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[3][0]);
				txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[3][1]);
				txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[3][2]);
				txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[3][3]);
			}
			
			encerrarDialogo = true;
		}
	}
	
}