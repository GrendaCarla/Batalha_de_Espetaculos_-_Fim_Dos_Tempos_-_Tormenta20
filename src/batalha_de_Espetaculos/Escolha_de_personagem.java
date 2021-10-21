package batalha_de_Espetaculos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
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

public class Escolha_de_personagem extends JPanel implements ActionListener {
	
	/* ---------------------------------------------------------------------------------------- \
	|  Escolha_de_personagem é a tela a onde os jogadores escolheram qual dos cães das colinas  |
	|  iram jogar durante todo o jogo.															|
	\ ---------------------------------------------------------------------------------------- */

	private Escolha_de_adversario tela2;
	private Manual telaManual;
	private Menu telaMenu;
	boolean novoJogo;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1096, -9);

	
	private int contEngranagem = 1;
	private boolean contEngranagem2;

	private Timer timer;
	
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	private Icones_interativos titulo = new Icones_interativos(1234/2 - 511/2, 20 + 50);
	
	// ---------------------------- aventureiros ------------------------------------
	
	private Icones_interativos iconeIgnis = new Icones_interativos(20 - 8, 640 - 20 - 454);
	private Icones_interativos iconeAyla = new Icones_interativos(20 + 240, 640 - 20 - 454 + 8);
	private Icones_interativos iconeRexthor = new Icones_interativos(20 + 240 + 240, 640 - 20 - 454 + 8);
	private Icones_interativos iconeKiki = new Icones_interativos(20 + 240 + 240 + 240, 640 - 20 - 454 + 8);
	private Icones_interativos iconeArius = new Icones_interativos(20 + 240 + 240 + 240 + 240, 640 - 20 - 454 + 8);
	
	private Icones_interativos luzAven = new Icones_interativos(11, 82);
	private int contTeclaAven = 0;
	
	// ------------------------------------------- Controles ---------------------------------------------
	
	private Icones_interativos teclaEsquerda = new Icones_interativos(20 + 10, 640 - 20 - 42 - 20);
	private Icones_interativos teclaDireita = new Icones_interativos(1234 - 10 - 20 - 37, 640 - 20 - 42 - 20);
	private Icones_interativos teclaZ = new Icones_interativos(20 + 110,20 + 36);
	private Icones_interativos teclaX = new Icones_interativos(1234 - 20 - 110 - 37, 20 + 36);
	private Icones_interativos teclaEsc = new Icones_interativos(1234 - 20 - 55 + 2, 20 - 4);
	
	// -------------------------- imagens e texto do dialogo de personagem ------------------------------
	
	private Icones_interativos sombreadorDialogoPerso = new Icones_interativos(0, 0);
	private Icones_interativos dialogoPersonagem = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimdialogoPersonagem = new Icones_interativos(dialogoPersonagem.getX() + 110, dialogoPersonagem.getY() + 190);
	private Icones_interativos bntNaodialogoPersonagem = new Icones_interativos(bntSimdialogoPersonagem.getX() + 370, bntSimdialogoPersonagem.getY());
	
	private Texto txtdialogoPersonagem = new Texto(1234/2 - 706/2 + 60, 548/2 - 28 - 40, " ");
	private Texto txtdialogoPersonagem2 = new Texto(1234/2 - 706/2 + 60, 548/2 + 12 - 40, " ");
	private Texto txtdialogoPersonagem3 = new Texto(1234/2 - 706/2 + 250, 548/2 + 52 - 40, " ");
	
	private TextLayout tl1, tl2, tl3, tl4, tl5; 
	private Boolean botaoSimNaoDialogo = true;
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,16);
	private Icones_interativos bntMenu = new Icones_interativos(18 + 200/2 - 128/2,80);
	private Icones_interativos bntManual = new Icones_interativos(bntMenu.getX(),bntMenu.getY() + 120);
	private Icones_interativos bntCreditos = new Icones_interativos(bntMenu.getX(), bntManual.getY() + 120);

	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------

	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 110, dialogoAviso.getY() + 190);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(bntSimDialogoAviso.getX() + 370, bntSimDialogoAviso.getY());
	
	
	private Texto txtDialogoAviso = new Texto(dialogoAviso.getX() + 110, 548/2 - 28 - 40, " ");
	private Texto txtDialogoAviso2 = new Texto(dialogoAviso.getX() + 250, 548/2 + 52 - 40, " ");
	
	private Boolean bntSimNaoDialgoAviso = true;
	
	private boolean mostrarMenu = false;
	private int contMenu = 0;

	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	public Escolha_de_personagem(Menu PaginaAnterior, boolean NovoJogo, boolean Engrenagem2) {
		this.telaMenu = PaginaAnterior;
		this.novoJogo = NovoJogo;
		
		contEngranagem2 = Engrenagem2;
		
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\fundo.png");
		engrenagem1.load("res\\engrenagem1.png");

		engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");

		titulo.load("res\\escolhaDePersonagem\\texto.png");
		contorno.load("res\\contorno.png");
		
		// ---------------------------- imagens dos aventureiros ------------------------------------

		iconeIgnis.load("res\\escolhaDePersonagem\\ignis2.png");
		iconeAyla.load("res\\escolhaDePersonagem\\ayla.png");
		iconeRexthor.load("res\\escolhaDePersonagem\\rexthor.png");
		iconeKiki.load("res\\escolhaDePersonagem\\kiki.png");
		iconeArius.load("res\\escolhaDePersonagem\\arius.png");
		
		luzAven.load("res\\escolhaDePersonagem\\luzIgnis.png");
		
		txtdialogoPersonagem.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtdialogoPersonagem.setCorTexto(new Color (235, 230, 233));
		txtdialogoPersonagem2.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtdialogoPersonagem3.setFonte(new Font("Arial", Font.PLAIN, 28));
		
		// ------------------------ imagens e textos do diálogo de aviso ------------------------------

		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		txtDialogoAviso2.setFonte(new Font("Arial", Font.PLAIN, 28));
		
		// ------------------------------------------- Controles ---------------------------------------------

		teclaEsquerda.load("res\\Menu\\setaEsquerda.png");
		teclaDireita.load("res\\Menu\\setaDireita.png");
		teclaZ.load("res\\Menu\\teclaZ.png");
		teclaX.load("res\\Menu\\teclaX.png");
		teclaEsc.load("res\\Menu\\teclaEsc.png");
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  		 		mexe e muda as imagens dos personagem conforme ganham foco					|
	\ ---------------------------------------------------------------------------------------- */
	public void posicionarPersonagens(int personagemPerdeFoco) {
		
		switch(personagemPerdeFoco) {
			case 0:
				iconeIgnis.load("res\\escolhaDePersonagem\\ignis.png");
				iconeIgnis.setX(20);
				iconeIgnis.setY(640 - 20 - 454 + 8);
				break;
			case 1:
				iconeAyla.load("res\\escolhaDePersonagem\\ayla.png");
				iconeAyla.setX(20 + 240);
				iconeAyla.setY(640 - 20 - 454 + 8);
				break;
			case 2:
				iconeRexthor.load("res\\escolhaDePersonagem\\rexthor.png");
				iconeRexthor.setX(20 + 240 + 240);
				iconeRexthor.setY(640 - 20 - 454 + 8);
				break;
			case 3:
				iconeKiki.load("res\\escolhaDePersonagem\\kiki.png");
				iconeKiki.setX(20 + 240 + 240 + 240);
				iconeKiki.setY(640 - 20 - 454 + 8);
				break;
			case 4:
				iconeArius.load("res\\escolhaDePersonagem\\arius.png");
				iconeArius.setX(20 + 240 + 240 + 240 + 240);
				iconeArius.setY(640 - 20 - 454 + 8);
				break;
		}
			
		switch(contTeclaAven) {
			case 0:
				iconeIgnis.load("res\\escolhaDePersonagem\\ignis2.png");
				iconeIgnis.setX(20 - 8);
				iconeIgnis.setY(640 - 20 - 454);
				titulo.load("res\\escolhaDePersonagem\\texto.png");
				luzAven.setX(11); luzAven.setY(82);
				luzAven.load("res\\escolhaDePersonagem\\luzIgnis.png");
				break;
			case 1:
				iconeAyla.load("res\\escolhaDePersonagem\\ayla2.png");
				iconeAyla.setX(20 + 240 - 8);
				iconeAyla.setY(640 - 20 - 454);
				titulo.load("res\\escolhaDePersonagem\\texto2.png");
				luzAven.setX(98); luzAven.setY(82);
				luzAven.load("res\\escolhaDePersonagem\\luzAyla.png");
				break;
			case 2:
				iconeRexthor.load("res\\escolhaDePersonagem\\rexthor2.png");
				iconeRexthor.setX(20 + 240 + 240 - 8);
				iconeRexthor.setY(640 - 20 - 454);
				titulo.load("res\\escolhaDePersonagem\\texto.png");
				luzAven.setX(338); luzAven.setY(82);
				luzAven.load("res\\escolhaDePersonagem\\luzRexthor.png");
				break;
			case 3:
				iconeKiki.load("res\\escolhaDePersonagem\\kiki2.png");
				iconeKiki.setX(20 + 240 + 240 + 240 - 8);
				iconeKiki.setY(640 - 20 - 454);
				titulo.load("res\\escolhaDePersonagem\\texto2.png");
				luzAven.setX(578); luzAven.setY(82);
				luzAven.load("res\\escolhaDePersonagem\\luzKiki.png");
				break;
			case 4:
				iconeArius.load("res\\escolhaDePersonagem\\arius2.png");
				iconeArius.setX(20 + 240 + 240 + 240 + 240 - 8);
				iconeArius.setY(640 - 20 - 454);
				titulo.load("res\\escolhaDePersonagem\\texto.png");
				luzAven.setX(769); luzAven.setY(82);
				luzAven.load("res\\escolhaDePersonagem\\luzArius.png");
				break;
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									Vai para a tela de menu									|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntMenu(int codigo) {
		// ------------------------ manda para a tela do menu ----------------------- /
		
		if(dialogoAviso.getImagem() == null && codigo == KeyEvent.VK_Z) {
			

			contEngranagem2 = !contEngranagem2;
			engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			dialogoAviso.load("res\\Menu\\dialogo.png");
			bntSimDialogoAviso.load("res\\Menu\\bntSim.png");
			bntNaoDialogoAviso.load("res\\Menu\\bntNao2.png");
			botaoSimNaoDialogo = true;
			
			txtDialogoAviso.setTexto("Se você voltar perderá o seu progreço.");
			txtDialogoAviso2.setTexto("Deseja continuar?");
			
		}else if ((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoAviso.getImagem() != null) {
		
			if(contEngranagem == 2) {
				contEngranagem = 1;
			} else {contEngranagem ++;}
			
			engrenagem1.load("res\\engrenagem" + contEngranagem + ".png");
			
			botaoSimNaoDialogo = !botaoSimNaoDialogo;
			bntSimDialogoAviso.load("res\\Menu\\bntSim" + (botaoSimNaoDialogo == true ? "" : "2") + ".png");
			bntNaoDialogoAviso.load("res\\Menu\\bntNao" + (botaoSimNaoDialogo == true ? "2" : "") + ".png");
		
		}else if(codigo == KeyEvent.VK_Z  || codigo == KeyEvent.VK_X && dialogoAviso.getImagem() != null) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			if(botaoSimNaoDialogo == false || codigo == KeyEvent.VK_X) {
				
				dialogoAviso.setImagem(null);
				bntSimDialogoAviso.load(null);
				bntNaoDialogoAviso.load(null);
				
				txtDialogoAviso.setTexto(" ");
				txtDialogoAviso2.setTexto(" ");
			}
			else {
				
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(telaMenu);
		        janelaPrincipal.setTitle("Menu");
		        telaMenu.Restaurar();
		        telaMenu.LimparTela1();
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
			engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        telaManual = new Manual(contEngranagem2);
	        telaManual.setTela1(this);
	        janelaPrincipal.add(telaManual);
	        janelaPrincipal.setTitle("Manual1");
	        janelaPrincipal.revalidate();
	        
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  		 					dispara quando as teclas são  pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);

		if(janela != null && janela.getTitle() == "Escolha de Personagem") {
			int codigo = tecla.getKeyCode();
			
			// -------------------- abre e fecha o menu -------------------- \
			if(codigo == KeyEvent.VK_ESCAPE && dialogoAviso.getImagem() == null ) {

				contEngranagem2 = !contEngranagem2;
				engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				mostrarMenu = !mostrarMenu;
				teclaEsc.load("res\\Menu\\teclaEsc2.png");
				
				if(mostrarMenu == true) {
					contMenu = 0;
					sombreadorMenu.load("res\\Menu\\sombreador.png");
					fundoMenu.load("res\\Menu\\menu.png");
					bntMenu.load("res\\Menu\\bntMenu2.png");
					bntManual.load("res\\Menu\\bntManual1.png");
					bntCreditos.load("res\\Menu\\bntCreditos1.png");
					
				} else {
					sombreadorMenu.setImagem(null);
					fundoMenu.setImagem(null);
					bntMenu.setImagem(null);
					bntManual.setImagem(null);
					bntCreditos.setImagem(null);
				}
			// --------------- muda a seleção das opções do menu ------------- \
			}else if((codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_UP) && mostrarMenu == true && dialogoAviso.getImagem() == null) {
				
				if(contEngranagem == 2) {
					contEngranagem = 1;
				} else {contEngranagem ++;}
				
				engrenagem1.load("res\\engrenagem" + contEngranagem + ".png");
				
				if(codigo == KeyEvent.VK_UP) {
					if(contMenu == 0) {contMenu = 2;} else {contMenu --;}
				}else if(codigo == KeyEvent.VK_DOWN) {
					if(contMenu == 2) {contMenu = 0;} else {contMenu ++;}
				}
				
				bntMenu.load("res\\Menu\\bntMenu1.png");
				bntManual.load("res\\Menu\\bntManual1.png");
				bntCreditos.load("res\\Menu\\bntCreditos1.png");
				
				switch (contMenu) {
					case 0:
						bntMenu.load("res\\Menu\\bntMenu2.png");
						break;
					case 1:
						bntManual.load("res\\Menu\\bntManual2.png");
						break;
					case 2:
						bntCreditos.load("res\\Menu\\bntCreditos2.png");
						break;
				}
				
			// ---------- encaminha para a função que controla o botão do Menu do menu ------\
			}else if(mostrarMenu == true && contMenu == 0){
				dialogoBntMenu(codigo);
			
			// ---------- encaminha para a função que controla o botão de Manual do menu ------\
			}else if(mostrarMenu == true && contMenu == 1){
				dialogoBntManual(codigo);
						
			// ---------- muda a seleção dos personagens para esquerda --------- \
			}else if(codigo == KeyEvent.VK_LEFT && dialogoPersonagem.getImagem() == null && mostrarMenu == false) {
				
				
				if(contEngranagem == 2) {
					contEngranagem = 1;
				} else {contEngranagem ++;}
				
				engrenagem1.load("res\\engrenagem" + contEngranagem + ".png");
				
				teclaEsquerda.load("res\\Menu\\setaEsquerda2.png");
				
				if(contTeclaAven == 0) {contTeclaAven = 4;} else {contTeclaAven --;}
				
				switch (contTeclaAven) {
					case 0:
						posicionarPersonagens(1);
					    break;
					case 1:
						posicionarPersonagens(2);
					    break;
					case 2:
						posicionarPersonagens(3);
					    break;
					case 3:
						posicionarPersonagens(4);
					    break;
					case 4:
						posicionarPersonagens(0);
					    break;
				}	  
				
			// ---------- muda a seleção dos personagens para direita --------- \
			} else if(codigo == KeyEvent.VK_RIGHT && dialogoPersonagem.getImagem() == null && mostrarMenu == false) {
				
				if(contEngranagem == 2) {
					contEngranagem = 1;
				} else {contEngranagem ++;}
				
				engrenagem1.load("res\\engrenagem" + contEngranagem + ".png");
				
				teclaDireita.load("res\\Menu\\setaDireita2.png");
				
				if(contTeclaAven == 4) {contTeclaAven = 0;} else {contTeclaAven ++;}
				
				switch (contTeclaAven) {
					case 0:
						posicionarPersonagens(4);
					    break;
					case 1:
						posicionarPersonagens(0);
					    break;
					case 2:
						posicionarPersonagens(1);
					    break;
					case 3:
						posicionarPersonagens(2);
					    break;
					case 4:
						posicionarPersonagens(3);
					    break;
				}
			}
			// --------------- chama o dialogo de aviso ao apertar Z -------------- \
			else if(codigo == KeyEvent.VK_Z && dialogoPersonagem.getImagem() == null && mostrarMenu == false) {

				contEngranagem2 = !contEngranagem2;
				engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				teclaZ.load("res\\Menu\\teclaZ2.png");
				
				sombreadorDialogoPerso.load("res\\Menu\\sombreador.png");
				dialogoPersonagem.load("res\\Menu\\dialogo.png");
				bntSimdialogoPersonagem.load("res\\Menu\\bntSim.png");
				bntNaodialogoPersonagem.load("res\\Menu\\bntNao2.png");
				bntSimNaoDialgoAviso = true;
				
				if(contTeclaAven == 0 || contTeclaAven == 2 || contTeclaAven == 4) {
					txtdialogoPersonagem.setTexto("O aventureiro escolhido não poderá ser trocado");
				} else {
					txtdialogoPersonagem.setTexto("A aventureira escolhida não poderá ser trocada");
				}
				txtdialogoPersonagem2.setTexto("durante o jogo.");
				txtdialogoPersonagem3.setTexto("Deseja continuar?");
			
			// --------------- fecha o dialogo de aviso ao apertar X -------------- \
			}else if(codigo == KeyEvent.VK_X && mostrarMenu == false) {

				contEngranagem2 = !contEngranagem2;
				engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				teclaX.load("res\\Menu\\teclaX2.png");
				
				limparDialogo();
			
			// ------------ muda a seleção das opções do dialogo de aviso ---------- \
			}else if((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoPersonagem.getImagem() != null && mostrarMenu == false) {
				
				if(contEngranagem == 2) {
					contEngranagem = 1;
				} else {contEngranagem ++;}
				
				engrenagem1.load("res\\engrenagem" + contEngranagem + ".png");
				
				dialogoPersonagem.load("res\\Menu\\dialogo.png");
				bntSimdialogoPersonagem.load("res\\Menu\\bntSim" + (bntSimNaoDialgoAviso == false ? "" : "2") + ".png");
				bntNaodialogoPersonagem.load("res\\Menu\\bntNao" + (bntSimNaoDialgoAviso == false ? "2" : "") + ".png");
				bntSimNaoDialgoAviso = (bntSimNaoDialgoAviso == false ? true : false);
			
			// --------------- entra quando pressiona Z no dialogo de aviso ------------- \
			}else if(codigo == KeyEvent.VK_Z && dialogoPersonagem.getImagem() != null && mostrarMenu == false) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				teclaZ.load("res\\Menu\\teclaZ2.png");
				
				// --------------- fecha o dialogo de aviso ------------- \
				if(bntSimNaoDialgoAviso == false) {
					limparDialogo();
					
				// ------------ vai para a tela de escolha de adversário ----------- \
				} else {
					boolean [] derrotados = {false, false, false, false, false};
					
			        chamarTela2(contTeclaAven, derrotados);
			        
			        limparDialogo();
				}
			}
			
		// ---------- encaminha todas as teclas pressionadas para as telas  --------- \
		} else {

			if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Manual1") {
				telaManual.KeyPressed(tecla);
			
			} else {
				
				tela2.KeyPressed(tecla);
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  		 				limpa as imagens e textos do dialogo de aviso						|
	\ ---------------------------------------------------------------------------------------- */
	public void limparDialogo() {
		
		sombreadorDialogoPerso.setImagem(null);
		dialogoPersonagem.setImagem(null);
		bntSimdialogoPersonagem.setImagem(null);
		bntNaodialogoPersonagem.setImagem(null);
		txtdialogoPersonagem.setTexto(" ");
		txtdialogoPersonagem2.setTexto(" ");
		txtdialogoPersonagem3.setTexto(" ");
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  					muda as imagens das teclas enquanto pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void KeyReleased (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);

		if(janela != null && janela.getTitle() == "Escolha de Personagem") {
			
			int codigo = tecla.getKeyCode();
			
			if(codigo == KeyEvent.VK_LEFT) {
				teclaEsquerda.load("res\\Menu\\setaEsquerda.png");
			}
			else if(codigo == KeyEvent.VK_RIGHT) {
				teclaDireita.load("res\\Menu\\setaDireita.png");
			}
			else if(codigo == KeyEvent.VK_Z) {
				teclaZ.load("res\\Menu\\teclaZ.png");
			}
			else if(codigo == KeyEvent.VK_X) {
				teclaX.load("res\\Menu\\teclaX.png");
			}
			else if(codigo == KeyEvent.VK_ESCAPE) {
				teclaEsc.load("res\\Menu\\teclaEsc.png");
			}
			
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		graficos.drawImage(titulo.getImagem(), titulo.getX(), titulo.getY(), this);
		
		// ---------------------------- aventureiros ------------------------------------
		
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getX(), iconeIgnis.getY(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getX(), iconeAyla.getY(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getX(), iconeRexthor.getY(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getX(), iconeKiki.getY(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getX(), iconeArius.getY(), this);
		
		graficos.drawImage(luzAven.getImagem(), luzAven.getX(), luzAven.getY(), this);
		
		// ------------------------------------------- Controles ---------------------------------------------

		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getX(), teclaEsquerda.getY(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getX(), teclaDireita.getY(), this);
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getX(), teclaZ.getY(), this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getX(), teclaX.getY(), this);
		
		// -------------------------- imagens e texto do dialogo de personagens ------------------------------
		
		graficos.drawImage(sombreadorDialogoPerso.getImagem(), sombreadorDialogoPerso.getX(), sombreadorDialogoPerso.getY(), this);
		graficos.drawImage(dialogoPersonagem.getImagem(), dialogoPersonagem.getX(), dialogoPersonagem.getY(), this);
		graficos.drawImage(bntSimdialogoPersonagem.getImagem(), bntSimdialogoPersonagem.getX(), bntSimdialogoPersonagem.getY(), this);
		graficos.drawImage(bntNaodialogoPersonagem.getImagem(), bntNaodialogoPersonagem.getX(), bntNaodialogoPersonagem.getY(), this);
		
		graficos.setColor(txtdialogoPersonagem.getCorTexto());
		tl1 = new TextLayout(txtdialogoPersonagem.getTexto(), txtdialogoPersonagem.getFonte(), frc);
		tl2 = new TextLayout(txtdialogoPersonagem2.getTexto(), txtdialogoPersonagem2.getFonte(), frc);
		tl3 = new TextLayout(txtdialogoPersonagem3.getTexto(), txtdialogoPersonagem3.getFonte(), frc);
		
		tl1.draw(graficos, txtdialogoPersonagem.getX(), txtdialogoPersonagem.getY());
		tl2.draw(graficos, txtdialogoPersonagem2.getX(), txtdialogoPersonagem2.getY());
		tl3.draw(graficos, txtdialogoPersonagem3.getX(), txtdialogoPersonagem3.getY());
		
		// ------------------------------------- imagens do menu ---------------------------------------

		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getX(), sombreadorMenu.getY(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getX(), fundoMenu.getY(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getX(), bntMenu.getY(), this);
		graficos.drawImage(bntManual.getImagem(), bntManual.getX(), bntManual.getY(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getX(), bntCreditos.getY(), this);

		// ------------------------------------- dialogo do menu ---------------------------------------

		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getX(), dialogoAviso.getY(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getX(), bntSimDialogoAviso.getY(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getX(), bntNaoDialogoAviso.getY(), this);
		
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl4 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl5 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso2.getFonte(), frc);
		
		tl4.draw(graficos, txtDialogoAviso.getX(), txtDialogoAviso.getY());
		tl5.draw(graficos, txtDialogoAviso2.getX(), txtDialogoAviso2.getY());
		
		
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getX(), engrenagem1.getY(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getX(), engrenagem2.getY(), this);
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		graficos.drawImage(teclaEsc.getImagem(), teclaEsc.getX(), teclaEsc.getY(), this);
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
	}
	
	public void chamarTela2(int ContTeclaAven, boolean [] Derrotados) {
		
		JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
        janelaPrincipal.remove(this);
        tela2 = new Escolha_de_adversario(ContTeclaAven, this, telaMenu, Derrotados, novoJogo, contEngranagem2);
        janelaPrincipal.add(tela2);
        janelaPrincipal.setTitle("Escolha de Adversário");
        janelaPrincipal.revalidate();
	}
	
	public void LimparTela2() {
		tela2 = null;
	}
	

	public void setContEngranagem2(boolean contEngranagem2) {
		this.contEngranagem2 = contEngranagem2;
		engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");	
	}
}
