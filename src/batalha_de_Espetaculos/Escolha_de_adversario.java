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
import java.io.IOException;
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
import batalha_de_Espetaculos.Salvar;

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
	
	
	private Image fundo;
	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1120, -12);

	private int contEngranagem1 = 1;
	private boolean contEngranagem2;
	
	private String caminho;
	
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
	
	private boolean ativarBoss = false;
	
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
	
	private Icones_interativos iconeBoss = new Icones_interativos(510, 70);
	private Icones_interativos iconeIgnis = new Icones_interativos(2600, iconeBoss.getY());
	private Icones_interativos iconeAyla = new Icones_interativos(248, iconeIgnis.getY() - 10);
	private Icones_interativos iconeRexthor = new Icones_interativos(1420, iconeIgnis.getY());
	private Icones_interativos iconeKiki = new Icones_interativos(824, iconeIgnis.getY());
	private Icones_interativos iconeArius = new Icones_interativos(2020, iconeIgnis.getY() - 10);
	
	private int contTeclaBatalha = 1;
	private boolean animarIconeAventureiro = false;
	
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
	private Icones_interativos bntSimDialogo = new Icones_interativos(1234/2 - 484/2, 640 - 50 - 40);
	private Icones_interativos bntNaoDialogo  = new Icones_interativos(bntSimDialogo.getX() + 370, bntSimDialogo.getY());

	
	private Texto txtDialogoLn1 = new Texto(100, 640 - 185 + 15, " ");
	private Texto txtDialogoLn2 = new Texto(txtDialogoLn1.getX(), 640 - 185 + 50, " ");
	private Texto txtDialogoLn3 = new Texto(txtDialogoLn1.getX(), 640 - 185 + 85, " ");
	private Texto txtDialogoLn4 = new Texto(txtDialogoLn1.getX(), 640 - 185 + 120, " ");
	
	private int contDialogo = 0;
	private boolean mudaCorLn4 = false;
	private Random aleatorioTibar = new Random();
	private boolean tibarCoroa = true;
	private boolean bntSimNao = true;
	private int ativarAnimacaoVelaAyla = 0;
	private int contAnimacaoVelaAyla = 0;
	private int contAnimacaoVandinha = 0;
	
	// ------------------------ imagens de estrelas para batalhas vencidas ------------------------------
	
	private boolean [] derrotados = {false, false, false, false, false}; //{true, true, true, true, true}; // 
	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------
	
	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 110, dialogoAviso.getY() + 180);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(bntSimDialogoAviso.getX() + 370, bntSimDialogoAviso.getY());
	
	private Texto txtDialogoAviso = new Texto(dialogoAviso.getX() + 110, 548/2 - 16 - 40, " ");
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
	private Icones_interativos imgMenSalve2 = new Icones_interativos(0, imgMenSalve.getY());
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
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private int contTempo = 0;
	
	private Timer timer;
	
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
		
		ImageIcon referencia = new ImageIcon(caminho + "res\\fundo0.png");
		fundo = referencia.getImage();
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
		
		// ------------------------------------------- Controles ---------------------------------------------

		teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda1.png");
		teclaDireita.load(caminho + "res\\Teclado\\setaDireita1.png");
		
		teclaZ.setImagem(null);

		//-----------------------------------------------------------------------------------------------
	
		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		txtDialogoAviso2.setFonte(new Font("Arial", Font.PLAIN, 28));
		
		txtDialogoLn1.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn1.setCorTexto(new Color (235, 230, 233));
		txtDialogoLn2.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn3.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn4.setCorTexto(new Color (239, 22, 109));
		txtDialogoLn4.setFonte(new Font("Arial", Font.PLAIN, 24));
		
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
		
		timer = new Timer(30, this);
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
		
		imgMenSalve2.setX(-0);
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
					
			dialogoAviso.load(caminho + "res\\mensagem aviso\\dialogo.png");
			bntSimDialogoAviso.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogoAviso.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			bntSimNaoDialgoAviso = true;
			
			txtDialogoAviso.setTexto("Se você voltar perderá o seu progreço.");
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
				
			} else {
				
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
			
			txtDialogoAviso.setTexto("Se você voltar perderá o seu progreço.");
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
	
						if(camada4Atual == -516 || camada4Atual == -1112 || camada4Atual == -1708 || camada4Atual == -2292 || camada4Atual == -2876 || (ativarBoss == true && camada4Atual == -3424) || camada4Atual == -3936) {
							teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
							contTeclaBatalha --;
							
						} else if(camada4Atual == -128 || camada4Atual == -716 || camada4Atual == -1312 || camada4Atual == -1896 || camada4Atual == -2488 || (ativarBoss == true && camada4Atual == -3076) || camada4Atual == -3548){
							teclaZ.setImagem(null);
							contTeclaBatalha --;
						}
			
						if(ativarBoss == false && (camada4Atual == -3424 || camada4Atual == -3076)) {
							contTeclaBatalha --;
						}
						
						if(contTeclaBatalha == -1) {contTeclaBatalha = 13;}
												
						if(Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) < 4) {
							if((camada4Atual > -9 && camada4Atual < 0) || (camada4Atual > -516 && camada4Atual < -128) || (camada4Atual > -1112 && camada4Atual < -716) || (camada4Atual > -1708 && camada4Atual < -1312) || (camada4Atual > -2292 && camada4Atual < -1896) || (camada4Atual > -2876 && camada4Atual < -2488) || (camada4Atual > -3424 && camada4Atual < -3076) || (camada4Atual > -3936 && camada4Atual < -3548)) {
								if(!(ativarBoss == false && camada4Atual > -3424 && camada4Atual < -3076)) {
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
						if(camada4Atual == -3500 || (ativarBoss == true && camada4Atual == -3028) || camada4Atual == -2436 || camada4Atual == -1852 || camada4Atual == -1264 || camada4Atual == -672 || camada4Atual == -84) {
							teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
							contTeclaBatalha ++;
							
						} else if(camada4Atual == -3896 || (ativarBoss == true && camada4Atual == -3396) || camada4Atual == -2836 || camada4Atual == -2248 || camada4Atual == -1664 || camada4Atual == -1068 || camada4Atual == -476){
							teclaZ.setImagem(null);
							contTeclaBatalha ++;
						}
						
						if(ativarBoss == false && (camada4Atual == -3028 || camada4Atual == -3396)) {
							contTeclaBatalha ++;
						}
											
						if(contTeclaBatalha == 14) {contTeclaBatalha = 0;}
						
						if(Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4))) > 4) {
							if((camada4Atual > -3896 && camada4Atual < -3500) || (camada4Atual > -3396 && camada4Atual < -3028) || (camada4Atual > -2836 && camada4Atual < -2436) || (camada4Atual > -2248 && camada4Atual < -1852) || (camada4Atual > -1664 && camada4Atual < -1264) || (camada4Atual > -1068 && camada4Atual < -672) || (camada4Atual > -476 && camada4Atual < -84)) {
								
								if(!(ativarBoss == false && camada4Atual > -3396 && camada4Atual < -3028)) {
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
			else if(codigo == KeyEvent.VK_Z && teclaZ.getImagem() != null && mostrarMenu == false && dialogoAviso.getImagem() == null && saveAviso.getImagem() == null && ((contTeclaBatalha == 4 && (derrotados[2] == true ? (contDialogo == 0) : (contDialogo < 4))) || (contTeclaBatalha == 8 && (derrotados[0] == true ? (contDialogo == 0) : (contDialogo < 2))) || (contTeclaBatalha == 2 && (derrotados[3] == true ? (contDialogo == 0) : (contDialogo < 2  || (contDialogo < 3 && bntSimNao == false)))) || (contTeclaBatalha == 0 && (derrotados[1] == true ? (contDialogo == 0) : (contDialogo < 5))) || (contTeclaBatalha == 6 && (derrotados[4] == true ? (contDialogo == 0) : (contDialogo < 3))) || (contTeclaBatalha == 12 && contDialogo < 1))) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				sombreadorDialogo.load(caminho + "res\\sombreador.png");
				barraDeDialogo.load(caminho + "res\\escolhaDeAdversario\\barraDeDialogo.png");
				contDialogo++;
				
				
				// rexthor
				
				switch (contTeclaBatalha) {
					case 4: // Rexthor
						objetoDeFundo1.setY(-40); objetoDeFundo1.setX(30);
						objetoDeFundo2.setY(-40); objetoDeFundo2.setX(30);
						objetoDeFundo3.setY(-40); objetoDeFundo3.setX(30);
						
						if(derrotados[2] == true && contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 400/2 - 30);
							imagemDoDialogo.setY(70);
							
							txtDialogoLn2.setX(300);
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\rexthor2.png");
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\vandinha1.png");
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
							
							txtDialogoLn2.setX(516);
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\rexthor1.png");
							objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\vandinha1.png");
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
							txtDialogoLn2.setX(100);
							
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
							bntSimDialogo.setY(530);
							
							bntNaoDialogo.setX(1234/2 - 464/2 + 370);
							bntNaoDialogo.setY(530);
							
							txtDialogoLn1.setX(470);
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
							txtDialogoLn1.setX(100);
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
							
							txtDialogoLn2.setX(210);
							
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
							txtDialogoLn2.setX(445);
							
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
							
							txtDialogoLn2.setX(365);
							
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
							
							txtDialogoLn4.setX(1234 - 534);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[0][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[0][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[0][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[0][3]);
						} else if(contDialogo == 2) {
							txtDialogoLn2.setX(1234/2 - 270/2);
							
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
							
							txtDialogoLn2.setX(100);
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[4][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[4][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[4][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[4][3]);
							
						} else if(contDialogo == 5) {
							txtDialogoLn4.setX(100);
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
							
							txtDialogoLn2.setX(330);

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
							txtDialogoLn2.setX(1234/2 - 490/2);
							
							txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[1][0]);
							txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[1][1]);
							txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[1][2]);
							txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[1][3]);
							
							bntSimDialogo.load(caminho + "res\\mensagem aviso\\bntSim1.png");
							bntNaoDialogo.load(caminho + "res\\mensagem aviso\\bntNao2.png");
							
						} else if(contDialogo == 3) {
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							txtDialogoLn2.setX(100);
							
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
						imagemDoDialogo.setX(1234/2 - 400/2);
						imagemDoDialogo.setY(10);
						
						if(derrotados[0] == true || derrotados[1] == true || derrotados[2] == true || derrotados[3] == true || derrotados[4] == true) {
							
							txtDialogoLn1.setX(184);
							txtDialogoLn2.setX(296);
							txtDialogoLn4.setX(290);
							
							imagemDoDialogo.load(caminho + "res\\escolhaDeAdversario\\cadeadoGrande1.png");
							
							txtDialogoLn1.setTexto("Parabéns!!! você conseguiu vencer os 5 Cães das Colinas, infelizmente o jogo não");
							txtDialogoLn2.setTexto("esta completo ainda e você terá que esperar um pouco mais.");
							txtDialogoLn3.setTexto(" ");
							txtDialogoLn4.setTexto("Muito obrigado por ter jogado e espero que tenha se divertido.");
							
							estrelaFim1.load(caminho + "res\\Creditos\\estrela3.png");
							estrelaFim2.load(caminho + "res\\Creditos\\estrela4.png");
							
						}
					 break;
				}
			}
			
			// ------------------------ manda para a tela de batalha ----------------------- /
			else if(codigo == KeyEvent.VK_Z  && mostrarMenu == false && dialogoAviso.getImagem() == null && bntSimNao == true && barraDeDialogo.getImagem() != null) {
				
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
			
			
			System.out.println("veio");
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
		
		bntSimDialogo.setY(640 - 50 - 40);
		bntNaoDialogo.setY(640 - 50 - 40);
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
			ativarBoss = true;
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		
		// ------------------------------------------- fundo ---------------------------------------------

		graficos.drawImage(camada6.getImagem(), camada6.getX(), camada6.getY(), this);
		
		graficos.drawImage(camada51.getImagem(), camada51.getX(), camada51.getY(), this);
		graficos.drawImage(camada52.getImagem(), camada52.getX(), camada52.getY(), this);
		
		graficos.drawImage(camada41.getImagem(), camada41.getX(), camada41.getY(), this);
		graficos.drawImage(camada42.getImagem(), camada42.getX(), camada42.getY(), this);
		
		graficos.drawImage(camada11.getImagem(), camada11.getX(), camada11.getY(), this);

		graficos.drawImage(camada21.getImagem(), camada21.getX(), camada21.getY(), this);
		graficos.drawImage(camada22.getImagem(), camada22.getX(), camada22.getY(), this);
		
		// ------------------------------------------- Controles ---------------------------------------------

		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getX(), teclaEsquerda.getY(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getX(), teclaDireita.getY(), this);
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getX(), teclaZ.getY(), this);
		
		//------------------------------------ icones ----------------------------------------------

		graficos.drawImage(iconeBoss.getImagem(), iconeBoss.getX(), iconeBoss.getY(), this);
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getX(), iconeIgnis.getY(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getX(), iconeAyla.getY(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getX(), iconeRexthor.getY(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getX(), iconeKiki.getY(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getX(), iconeArius.getY(), this);
		
		// -------------------------------- aventureiro ---------------------------------------------

		graficos.drawImage(iconeAventureiro.getImagem(), iconeAventureiro.getX(), iconeAventureiro.getY(), this);
		graficos.drawImage(camada31.getImagem(), camada31.getX(), camada31.getY(), this);
		graficos.drawImage(iconeSombraAventureiro.getImagem(), iconeSombraAventureiro.getX(), iconeSombraAventureiro.getY(), this);

		//----------------------------------------------------------------------------------
		
		graficos.drawImage(sombreadorDialogo.getImagem(), sombreadorDialogo.getX(), sombreadorDialogo.getY(), this);
		graficos.drawImage(objetoDeFundo3.getImagem(), objetoDeFundo3.getX(), objetoDeFundo3.getY(), this);
		graficos.drawImage(objetoDeFundo2.getImagem(), objetoDeFundo2.getX(), objetoDeFundo2.getY(), this);
		graficos.drawImage(objetoDeFundo1.getImagem(), objetoDeFundo1.getX(), objetoDeFundo1.getY(), this);
		graficos.drawImage(imagemDoDialogo.getImagem(), imagemDoDialogo.getX(), imagemDoDialogo.getY(), this);
		graficos.drawImage(barraDeDialogo.getImagem(), barraDeDialogo.getX(), barraDeDialogo.getY(), this);
		
		graficos.setColor(txtDialogoLn1.getCorTexto());
		
		tl1 = new TextLayout(txtDialogoLn1.getTexto(), txtDialogoLn1.getFonte(), frc);
		tl2 = new TextLayout(txtDialogoLn2.getTexto(), txtDialogoLn2.getFonte(), frc);
		tl3 = new TextLayout(txtDialogoLn3.getTexto(), txtDialogoLn3.getFonte(), frc);
		tl4 = new TextLayout(txtDialogoLn4.getTexto(), txtDialogoLn4.getFonte(), frc);
		
		tl1.draw(graficos, txtDialogoLn1.getX(), txtDialogoLn1.getY());
		tl2.draw(graficos, txtDialogoLn2.getX(), txtDialogoLn2.getY());
		tl3.draw(graficos, txtDialogoLn3.getX(), txtDialogoLn3.getY());
		
		if(mudaCorLn4 == true) {graficos.setColor(txtDialogoLn4.getCorTexto());}
		tl4.draw(graficos, txtDialogoLn4.getX(), txtDialogoLn4.getY());
		if(mudaCorLn4 == true) {graficos.setColor(txtDialogoLn1.getCorTexto());}
		
		graficos.drawImage(bntSimDialogo.getImagem(), bntSimDialogo.getX(), bntSimDialogo.getY(), this);
		graficos.drawImage(bntNaoDialogo.getImagem(), bntNaoDialogo.getX(), bntNaoDialogo.getY(), this);
		graficos.drawImage(estrelaFim1.getImagem(), estrelaFim1.getX(), estrelaFim1.getY(), this);
		graficos.drawImage(estrelaFim2.getImagem(), estrelaFim2.getX(), estrelaFim2.getY(), this);
		
		graficos.drawImage(imgMenSalve.getImagem(), imgMenSalve.getX(), imgMenSalve.getY(), this);
		
		graficos.setColor(txtSalvar.getCorTexto());
		tl7 = new TextLayout(txtSalvar.getTexto(), txtSalvar.getFonte(), frc);
		tl7.draw(graficos, txtSalvar.getX(), txtSalvar.getY());
		
		graficos.drawImage(imgMenSalve2.getImagem(), imgMenSalve.getX(), imgMenSalve.getY(), this);
		graficos.drawImage(sombreadorSaveAviso.getImagem(), sombreadorSaveAviso.getX(), sombreadorSaveAviso.getY(), this);
		graficos.drawImage(saveAviso.getImagem(), saveAviso.getX(), saveAviso.getY(), this);

		graficos.setColor(txtSaveAviso.getCorTexto());
		tl8 = new TextLayout(txtSaveAviso.getTexto(), txtSaveAviso.getFonte(), frc);
		tl8.draw(graficos, txtSaveAviso.getX(), txtSaveAviso.getY());
		
		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getX(), sombreadorMenu.getY(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getX(), fundoMenu.getY(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getX(), bntMenu.getY(), this);
		graficos.drawImage(bntManual.getImagem(), bntManual.getX(), bntManual.getY(), this);
		graficos.drawImage(bntVoltar.getImagem(),  bntVoltar.getX(),  bntVoltar.getY(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getX(), bntCreditos.getY(), this);

		
		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getX(), dialogoAviso.getY(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getX(), bntSimDialogoAviso.getY(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getX(), bntNaoDialogoAviso.getY(), this);
		
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl5 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl6 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso2.getFonte(), frc);
		
		tl5.draw(graficos, txtDialogoAviso.getX(), txtDialogoAviso.getY());
		tl6.draw(graficos, txtDialogoAviso2.getX(), txtDialogoAviso2.getY());
		
		
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getX(), engrenagem1.getY(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getX(), engrenagem2.getY(), this);

		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		
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
			animarVandinha();
		}
		
		repaint();
	}
	
	public void animarAventureiroParado() {
		
		int num = Integer.parseInt((iconeAventureiro.getReferencia() + "").substring(((iconeAventureiro.getReferencia() + "").length() - 5), ((iconeAventureiro.getReferencia() + "").length() - 4)));
		
		if(aventureiro != 1 && aventureiro != 3) {
			
			if(contTempo % 70 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 2 ? "rexthor" : "arius")) + (num < 5 ? "1" : "6") + ".png");
			} else if((contTempo - 35) % 70 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\" + (aventureiro == 0 ? "ignis" : (aventureiro == 2 ? "rexthor" : "arius")) + (num < 5 ? "0" : "5") + ".png");
			}
		
		}else if(aventureiro == 3) {
			
			if(contTempo % 20 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\kiki" + (num < 5 ? "1" : "6") + ".png");
			}else if((contTempo - 10) % 20 == 0) {
					iconeAventureiro.load(caminho + "res\\Bonequinho\\kiki" + (num < 5 ? "0" : "5") + ".png");
			}
			
		} else {
			
			if(contTempo % 30 == 0 || (contTempo - 10) % 30 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (num < 5 ? "1" : "6") + ".png");
				iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (num < 5 ? "11" : "15") + ".png");
				iconeAventureiro.setY(iconeAventureiro.getY() - 7);
				
			} else if((contTempo - 5) % 30 == 0 || (contTempo - 14) % 30 == 0 || (contTempo - 18) % 30 == 0 || (contTempo - 22) % 30 == 0 || (contTempo - 26) % 30 == 0) {
				iconeAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (num < 5 ? "0" : "5") + ".png");
				iconeSombraAventureiro.load(caminho + "res\\Bonequinho\\ayla" + (num < 5 ? "10" : "14") + ".png");
				
				if ((contTempo - 5) % 30 == 0) {
					iconeAventureiro.setY(iconeAventureiro.getY() - 2);
				} else {
					iconeAventureiro.setY(iconeAventureiro.getY() + 4);
				}
			} 
			
		}
		
	}
	
	public void animarteclado() {
		
		if(contTempo % 13 == 0) {
			
			animarApertoTecla = !animarApertoTecla;
			teclaEsquerda.setX(teclaEsquerda.getX() + (animarApertoTecla == false ? 5 : -5));
			teclaDireita.setX(teclaDireita.getX() + (animarApertoTecla == false ? -5 : 5));
			
			if(teclaZ.getImagem() != null) {
				teclaZ.load(caminho + "res\\Teclado\\teclaZ" + (animarApertoTecla == false ? "1" : "2") + ".png");
			}
			
		}
		

		if(iconeIgnis.getImagem() != null || iconeAyla.getImagem() != null || iconeRexthor.getImagem() != null || iconeKiki.getImagem() != null || iconeArius.getImagem() != null) {
			
			if(contTempo % 5 == 0) {
				iconeIgnis.setY(iconeIgnis.getY() + (animarIconeAventureiro == false ? 1 : -1));
				iconeAyla.setY(iconeAyla.getY() + (animarIconeAventureiro == false ? 1 : -1));
				iconeRexthor.setY(iconeRexthor.getY() + (animarIconeAventureiro == false ? 1 : -1));
				iconeKiki.setY(iconeKiki.getY() + (animarIconeAventureiro == false ? 1 : -1));
				iconeArius.setY(iconeArius.getY() + (animarIconeAventureiro == false ? 1 : -1));
			}
			
			if(contTempo %30 == 0) {
				animarIconeAventureiro = !animarIconeAventureiro;
			}
		}
		
	}
	
	public void animarSave() {
		
		contMenSalvar++;
		
		if(resutado == 1) {
			
			if(contMenSalvar > 0 && contMenSalvar < 36) {
				imgMenSalve.setX(imgMenSalve.getX() + 7);
				imgMenSalve2.setX(imgMenSalve2.getX() + 7);
				
				if(contMenSalvar > 11 && contMenSalvar % 2 == 0 && contConteudoSalvar < 11) {
					contConteudoSalvar ++;
					txtSalvar.setTexto(conteudoSalvar.substring(0,contConteudoSalvar));
				}
				
			}
			
			if(contMenSalvar == 30) {
				imgMenSalve2.load(caminho + "res\\mensagem aviso\\salvar3.png");
			} else if(contMenSalvar == 34) {
				imgMenSalve2.setImagem(null);
			}
			
			if(contMenSalvar == 70) {
				imgMenSalve2.load(caminho + "res\\mensagem aviso\\salvar3.png");
			}
			
			if(contMenSalvar == 72) {
				imgMenSalve2.load(caminho + "res\\mensagem aviso\\salvar2.png");
				contConteudoSalvar --;
			}
			

			if(contMenSalvar > 72 && contMenSalvar < 122) {
				imgMenSalve.setX(imgMenSalve.getX() - 7);
				imgMenSalve2.setX(imgMenSalve2.getX() - 7);
				
				if(contMenSalvar > 75 && contMenSalvar % 2 == 0 && contConteudoSalvar > 0) {
					txtSalvar.setTexto(conteudoSalvar.substring(0,contConteudoSalvar --));
				}
				
				if(contConteudoSalvar == 0) {
					txtSalvar.setTexto(" ");
				}
			}
			if(contMenSalvar > 121) {
				imgMenSalve.setImagem(null);
				contMenSalvar = -1;
			}
			
		} else {
			if(contMenSalvar == 100) {
				sombreadorSaveAviso.setImagem(null);
				saveAviso.setImagem(null);
				txtSaveAviso.setTexto(" ");
				contMenSalvar = -1;
			}
		}
		
	}
	
	public void animarVelas() {
		
		contAnimacaoVelaAyla ++;
		
		if(ativarAnimacaoVelaAyla == 1 && contAnimacaoVelaAyla%2 == 0) {
			objetoDeFundo1.setY(objetoDeFundo1.getY() - 1);
			objetoDeFundo2.setY(objetoDeFundo2.getY() + 1);
		} else if(ativarAnimacaoVelaAyla == 2 && contAnimacaoVelaAyla%2 == 0) {
			objetoDeFundo1.setY(objetoDeFundo1.getY() + 1);
			objetoDeFundo2.setY(objetoDeFundo2.getY() - 1);
		}
		
		if(objetoDeFundo1.getY() <= 60) {
			ativarAnimacaoVelaAyla = 2;
		} else if(objetoDeFundo1.getY() >= 80) {
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
	
	public void animarVandinha() {
		
		if(contAnimacaoVandinha == 21) {contAnimacaoVandinha = 0;}
		else {contAnimacaoVandinha ++;}
		
		if(contAnimacaoVandinha == 4 || contAnimacaoVandinha == 11 || contAnimacaoVandinha == 18) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\vandinha2.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco1.png");
		} else if(contAnimacaoVandinha == 7) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\vandinha3.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco2.png");
		} else if(contAnimacaoVandinha == 14) {
			objetoDeFundo1.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\vandinha1.png");
			objetoDeFundo2.load(caminho + "res\\escolhaDeAdversario\\Rexthor\\saco2.png");
		} 
	
	}
	
	
}
