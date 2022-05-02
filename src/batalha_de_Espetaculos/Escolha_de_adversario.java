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

	
	private Texto txtDialogoLn1 = new Texto(100, 640 - 185 + 26, " ");
	private Texto txtDialogoLn2 = new Texto(txtDialogoLn1.getX(), txtDialogoLn1.getY() + 32, " ");
	private Texto txtDialogoLn3 = new Texto(txtDialogoLn1.getX(), txtDialogoLn2.getY() + 32, " ");
	private Texto txtDialogoLn4 = new Texto(txtDialogoLn1.getX(), txtDialogoLn3.getY() + 32, " ");
	
	private int contDialogo = 0;
	private boolean mudaCorLn4 = false;
	private Random aleatorioTibar = new Random();
	private boolean tibarCoroa = true;
	private boolean bntSimNao = true;
	private int ativarAnimacaoVelaAyla = 0;
	private int contAnimacaoJosefine = 0;
	
	// ------------------------ imagens de estrelas para batalhas vencidas ------------------------------
	
	private boolean [] derrotados = {false, false, false, false, false}; //{true, true, true, true, true}; // 
	
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
	
	public Escolha_de_adversario(int numAventureiro, Escolha_de_personagem PaginaAnterior, Menu PaginaMenu, boolean [] Derrotados, boolean NovoJogo, boolean Engrenagem2, String Caminho) {
		contEngranagem2 = Engrenagem2;
		this.caminho = Caminho;

		aventureiro = numAventureiro;
		this.tela1 = PaginaAnterior;
		this.telaMenu = PaginaMenu;
		this.derrotados = Derrotados;
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
	
	public void setDerrotados(int posicao, boolean derrotados) {
		this.derrotados[posicao] = derrotados;
	}
	
	public boolean[] getDerrotados() {
		return derrotados;
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
		
		
		resutado = salvar.SalvarDados(derrotados, aventureiro, caminho);
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
				
				System.out.println(contTeclaBatalha);
								
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
					bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao" + (bntSimNao == true? "2" : "1") + ".png");
					
				}
			}
			
			// --------------- quando o diálogo é fechado ele limpa as imagens e textos --------------- \
			else if(codigo == KeyEvent.VK_Z && mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() != null && ((bntSimDialogo.getImagem() != null ? (bntSimNao == false && ((derrotados[2] == true  && contTeclaBatalha == 4  && contDialogo == 1) || (derrotados[0] == true  && contTeclaBatalha == 8  && contDialogo == 1) || (derrotados[3] == true  && contTeclaBatalha == 2  && contDialogo == 1) || (derrotados[1] == true  && contTeclaBatalha == 0  && contDialogo == 1) || (derrotados[4] == true  && contTeclaBatalha == 6  && contDialogo == 1)))  : (bntSimNao == false && ((contTeclaBatalha == 4 && contDialogo == 2) || (contTeclaBatalha == 8 && contDialogo == 2) || (contTeclaBatalha == 2 && contDialogo == 3) || (contTeclaBatalha == 0 && contDialogo == 3) || (contTeclaBatalha == 6 && contDialogo == 3))) || (contTeclaBatalha == 12 && contDialogo == 1)|| (contTeclaBatalha == 4 && contDialogo == 4 && mudaCorLn4 == false)))) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				limparDialogo();
			}
			
			// ----------------------- diálogo com os cães das colinas ------------------------ \
			else if(codigo == KeyEvent.VK_Z && teclaZ.getImagem() != null && mostrarMenu == false && dialogoAviso.getImagem() == null && saveAviso.getImagem() == null && ativarBoss != 2 && ((contTeclaBatalha == 4 && (derrotados[2] == true ? (contDialogo == 0) : (contDialogo < 4))) || (contTeclaBatalha == 8 && (derrotados[0] == true ? (contDialogo == 0) : (contDialogo < 2))) || (contTeclaBatalha == 2 && (derrotados[3] == true ? (contDialogo == 0) : (contDialogo < 2  || (contDialogo < 3 && bntSimNao == false)))) || (contTeclaBatalha == 0 && (derrotados[1] == true ? (contDialogo == 0) : (contDialogo < 5))) || (contTeclaBatalha == 6 && (derrotados[4] == true ? (contDialogo == 0) : (contDialogo < 3))) || (contTeclaBatalha == 12 && contDialogo < 1))) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				if(contTeclaBatalha != 12 || ativarBoss == 3) {
					sombreadorDialogo.load(caminho + "res\\sombreador.png");
					barraDeDialogo.load(caminho + "res\\escolhaDeAdversario\\barraDeDialogo.png");
					contDialogo++;
				}
				
				
				// rexthor
				
				switch (contTeclaBatalha) {
					case 4: // Rexthor
						objetoDeFundo1.setY(-40); objetoDeFundo1.setX(30);
						objetoDeFundo2.setY(-40); objetoDeFundo2.setX(30);
						objetoDeFundo3.setY(-40); objetoDeFundo3.setX(30);
						
						if(derrotados[2] == true && contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 400/2 - 30);
							imagemDoDialogo.setY(70);
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\rexthor2.png");
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josefine1.png");
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
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josefine1.png");
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
							
							boolean tibar = aleatorioTibar.nextInt(2)== 0 ? true : false;
							
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
							
						}
						
					    break;
					case 8: // Ignis
						objetoDeFundo2.setX(0); objetoDeFundo2.setY(20);
						objetoDeFundo1.setY(20); objetoDeFundo1.setX(20);
						
						if(contDialogo == 1 && derrotados[0] == true) {
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
							
							
						} 
					    break;
					case 2: // Kiki
						objetoDeFundo1.setX(-20); objetoDeFundo1.setY(-40);
						objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Kiki\\objeto1.png");
						
						if(contDialogo == 1 && derrotados[3] == true) {
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
						}
						
					    break;
					case 0: // Ayla
						if(contDialogo == 1 && derrotados[1] == true) {
							logoAyla = true;
									
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(34);
							
							objetoDeFundo1.setY(60); objetoDeFundo2.setY(50);
							objetoDeFundo1.setX(7); objetoDeFundo2.setX(7);
							objetoDeFundo3.setY(12); objetoDeFundo3.setY(40);
							
							ativarAnimacaoVelaAyla = 1;
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla3.png");
							
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto1.png");
							objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto2.png");
							objetoDeFundo3.load(caminho + "res\\escolhaDeAdversario\\Ayla\\objeto3.png");
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[6][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[6][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[6][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[6][3]);
							
							bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
							bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
						}else if(contDialogo == 1) {
							
							imagemDoDialogo.setX(0);
							imagemDoDialogo.setY(0);
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca1.png");
							
							txtDialogoLn4.setX(1234 - 524);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[0][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[0][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[0][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[0][3]);
						} else if(contDialogo == 2) {
							
							txtDialogoLn4.setX(100);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[1][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[1][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[1][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[1][3]);
							
							bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
							bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
						} else if(contDialogo == 3) {
							
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							
							if(bntSimNao == true) {
								txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[2][0]);
								txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[2][1]);
								txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[2][2]);
								txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[2][3]);
								
							} else {
								imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\mutuca2.png");
								
								txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[3][0]);
								txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[3][1]);
								txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[3][2]);
								txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[3][3]);
							}
						} else if(contDialogo == 4) {
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
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[4][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[4][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[4][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[4][3]);
							
						} else if(contDialogo == 5) {
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Ayla\\ayla2.png");	
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[5][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[5][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[5][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[5][3]);
							
							mudaCorLn4 = true;
						}
					    break;
					case 6: // Arius
						
						objetoDeFundo1.setX(10); objetoDeFundo1.setY(-60);
						objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Arius\\objeto1.png");
						
						if(contDialogo == 1 && derrotados[4] == true) {
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
						}
						
					    break;
					case 12: // Boss
						
						if(ativarBoss == 3) {
							imagemDoDialogo.setX(1234/2 - 400/2);
							imagemDoDialogo.setY(10);
							
							if(derrotados[0] == true || derrotados[1] == true || derrotados[2] == true || derrotados[3] == true || derrotados[4] == true) {
								
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
							
							}
						} else {
							ativarBoss = 2;
						}
						
						break;
				}
			}
			
			// ------------------------ manda para a tela de batalha ----------------------- /
			else if(codigo == KeyEvent.VK_Z  && mostrarMenu == false && dialogoAviso.getImagem() == null && bntSimNao == true && barraDeDialogo.getImagem() != null && contMenSalvar == -1) {
				
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
		
		txtDialogoLn1.setTexto(" ");
		txtDialogoLn2.setTexto(" ");
		txtDialogoLn3.setTexto(" ");
		txtDialogoLn4.setTexto(" ");
		txtDialogoLn4.setX(100);
		txtDialogoLn3.setX(100);
		txtDialogoLn2.setX(100);
		txtDialogoLn1.setX(100);
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
	
		if(derrotados[0] == true) {
			iconeIgnis.load(caminho + "res\\Insignia\\simbolo ignis.png");
		} 
		if(derrotados[1] == true) {
			iconeAyla.load(caminho + "res\\Insignia\\simbolo ayla.png");
		} 
		if(derrotados[2] == true) {
			iconeRexthor.load(caminho + "res\\Insignia\\simbolo rexthor.png");
		} 
		if(derrotados[3] == true) {
			iconeKiki.load(caminho + "res\\Insignia\\simbolo kiki.png");
		} 
		if(derrotados[4] == true) {
			iconeArius.load(caminho + "res\\Insignia\\simbolo arius.png");
		} 
			
		if(derrotados[0] == true && derrotados[1] == true && derrotados[2] == true && derrotados[3] == true && derrotados[4] == true) {
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
			animarJosefine();
		}
		
		if(logoAyla == true) {
			MostrarLogo();
		}
		
		if(ativarBoss == 2) {
			descerPeixe();
		}
		
		repaint();
	}
	
	public void MostrarLogo() {
		
		if(contLogoAyla == 80) {contLogoAyla = 0;} else {contLogoAyla ++;}
		
		
		if(contLogoAyla == 20) {
			ArrayList<Integer>  mylist = new ArrayList<Integer>();
			mylist.add(1); mylist.add(2);
			Collections.shuffle(mylist);
			
			imgLogoAyla.load(caminho + "res\\EscolhaDeAdversario\\Ayla\\logo" + (mylist.get(0)) + ".png");
			
		} else if (contLogoAyla == 28) {
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
	
	public void animarJosefine() {
		
		if(contAnimacaoJosefine == 19) {contAnimacaoJosefine = 0;}
		else {contAnimacaoJosefine ++;}
		
		if(contAnimacaoJosefine == 3 || contAnimacaoJosefine == 9 || contAnimacaoJosefine == 16) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josefine2.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco1.png");
		} else if(contAnimacaoJosefine == 5) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josefine3.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco2.png");
		} else if(contAnimacaoJosefine == 12) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\Josefine1.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco2.png");
		} 
	
	}
	
	public void descerPeixe() {
		
		if(iconeBoss.getImagem() == null) {
			iconeBoss.load(caminho + "res\\Insignia\\peixe voador.png");
			teclaZ.setImagem(null);
			
		} else if(iconeBoss.getY() < 24) {
			iconeBoss.setY(iconeBoss.getY() + 2);
			
			
			fundo.setX(fundo.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			tapaResto.setX(tapaResto.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			engrenagem1.setX(engrenagem1.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			engrenagem2.setX(engrenagem2.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));

			contorno.setX(contorno.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			camada11.setX(camada11.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			camada21.setX(camada21.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			camada22.setX(camada22.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			camada31.setX(camada31.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			camada41.setX(camada41.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			camada42.setX(camada42.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			camada51.setX(camada51.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			camada52.setX(camada52.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			camada6.setX(camada6.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			iconeAventureiro.setX(iconeAventureiro.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			teclaEsquerda.setX(teclaEsquerda.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			teclaDireita.setX(teclaDireita.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			
			teclaZ.setX(teclaZ.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));

			iconeBoss.setX(iconeBoss.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			iconeIgnis.setX(iconeIgnis.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			iconeAyla.setX(iconeAyla.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			iconeRexthor.setX(iconeRexthor.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			iconeKiki.setX(iconeKiki.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
			iconeArius.setX(iconeArius.getX() + (iconeBoss.getY() < -170 || iconeBoss.getY() > -4 ? (iconeBoss.getY() % 2 == 0 ? 2 : -2) : (iconeBoss.getY() % 2 == 0 ? 4 : -4)));
		
			
		} else {
			teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
			ativarBoss = 3;
		}
		
	}
	
	
}