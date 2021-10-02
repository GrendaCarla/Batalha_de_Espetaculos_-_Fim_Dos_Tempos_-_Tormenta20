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
	private Regras telaRegras;
	private Menu telaMenu;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	private Timer timer;
	
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	private Icones_interativos titulo = new Icones_interativos(1234/2 - 511/2, 20 + 50);
	
	// ---------------------------- imagens dos aventureiros ------------------------------------
	
	private Icones_interativos iconeIgnis = new Icones_interativos(20 - 8, 640 - 20 - 454);
	private Icones_interativos iconeAyla = new Icones_interativos(20 + 240, 640 - 20 - 454 + 8);
	private Icones_interativos iconeRexthor = new Icones_interativos(20 + 240 + 240, 640 - 20 - 454 + 8);
	private Icones_interativos iconeKiki = new Icones_interativos(20 + 240 + 240 + 240, 640 - 20 - 454 + 8);
	private Icones_interativos iconeArius = new Icones_interativos(20 + 240 + 240 + 240 + 240, 640 - 20 - 454 + 8);
	
	private Icones_interativos luzAven = new Icones_interativos(11, 82);
	private int contTeclaAven = 0;
	
	// ---------------------------- imagens dos botões do teclado --------------------------------
	
	private Icones_interativos teclaEsquerda = new Icones_interativos(20 + 10, 640 - 20 - 42 - 20);
	private Icones_interativos teclaDireita = new Icones_interativos(1234 - 10 - 20 - 37, 640 - 20 - 42 - 20);
	private Icones_interativos teclaZ = new Icones_interativos(20 + 60,20 + 35);
	private Icones_interativos teclaX = new Icones_interativos(1234 - 20 - 60 - 37, 20 + 35);
	private Icones_interativos teclaEsc = new Icones_interativos(1234 - 20 - 55 + 2, 20 - 4);
	
	// -------------------------- imagens e texto do dialogo de aviso ------------------------------
	
	private Icones_interativos sombreadorDialogoAviso = new Icones_interativos(0, 0);
	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 110, dialogoAviso.getY() + 190);
	private Icones_interativos bntNaoDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 480, bntSimDialogoAviso.getY());
	
	private Texto txtDialogoAviso = new Texto(1234/2 - 706/2 + 60, 548/2 - 28, " ");
	private Texto txtDialogoAviso2 = new Texto(1234/2 - 706/2 + 60, 548/2 + 12, " ");
	private Texto txtDialogoAviso3 = new Texto(1234/2 - 706/2 + 250, 548/2 + 52, " ");
	
	private TextLayout tl1, tl2, tl3; 
	private Boolean botaoSimNaoDialogo = true;
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,16);
	private Icones_interativos bntMenu = new Icones_interativos(18 + 200/2 - 128/2,80);
	private Icones_interativos bntRegras = new Icones_interativos(18 + 200/2 - 128/2,80 + 140);
	
	boolean mostrarMenu = false;
	boolean contMenu = false;

	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	public Escolha_de_personagem(Menu PaginaAnterior) {
		this.telaMenu = PaginaAnterior;
		
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\fundo.png");
		
		titulo.load("res\\escolhaDePersonagem\\texto.png");
		contorno.load("res\\contorno.png");
		
		iconeIgnis.load("res\\escolhaDePersonagem\\ignis2.png");
		iconeAyla.load("res\\escolhaDePersonagem\\ayla.png");
		iconeRexthor.load("res\\escolhaDePersonagem\\rexthor.png");
		iconeKiki.load("res\\escolhaDePersonagem\\kiki.png");
		iconeArius.load("res\\escolhaDePersonagem\\arius.png");
		
		luzAven.load("res\\escolhaDePersonagem\\luzIgnis.png");
		
		teclaEsquerda.load("res\\escolhaDePersonagem\\setaEsquerda.png");
		teclaDireita.load("res\\escolhaDePersonagem\\setaDireita.png");
		teclaZ.load("res\\escolhaDePersonagem\\teclaZ.png");
		teclaX.load("res\\escolhaDePersonagem\\teclaX.png");
		teclaEsc.load("res\\escolhaDePersonagem\\teclaEsc.png");
		
		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		txtDialogoAviso2.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtDialogoAviso3.setFonte(new Font("Arial", Font.PLAIN, 28));
		
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
		if(codigo == KeyEvent.VK_Z ) {
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        janelaPrincipal.add(telaMenu);
	        janelaPrincipal.setTitle("Menu");
	        telaMenu.Restaurar();
	        janelaPrincipal.revalidate();
	        
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									Vai para a tela de Regras								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntRegras(int codigo) {
		// ------------------------ manda para a tela de regras ----------------------- /
		if(codigo == KeyEvent.VK_Z ) {
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        telaRegras = new Regras();
	        telaRegras.setTela1(this);
	        janelaPrincipal.add(telaRegras);
	        janelaPrincipal.setTitle("Regras1");
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
			if(codigo == KeyEvent.VK_ESCAPE) {
				mostrarMenu = !mostrarMenu;
				teclaEsc.load("res\\escolhaDePersonagem\\teclaEsc2.png");
				
				if(mostrarMenu == true) {
					contMenu = false;
					sombreadorMenu.load("res\\sombreador.png");
					fundoMenu.load("res\\menu.png");
					bntMenu.load("res\\bntMenu2.png");
					bntRegras.load("res\\bntRegras1.png");
					
				} else {
					sombreadorMenu.setImagem(null);
					fundoMenu.setImagem(null);
					bntMenu.setImagem(null);
					bntRegras.setImagem(null);
				}
			// --------------- muda a seleção das opções do menu ------------- \
			}else if((codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_UP) && mostrarMenu == true) {
				
				contMenu = !contMenu;
				bntMenu.load("res\\bntMenu" + (contMenu == false ? "2" : "1") + ".png");
				bntRegras.load("res\\bntRegras" + (contMenu == false ? "1" : "2") + ".png");
		
			// ---------- encaminha para a função que controla o botão do Menu do menu ------\
			}else if(mostrarMenu == true && contMenu == false){
				dialogoBntMenu(codigo);
			
			// ---------- encaminha para a função que controla o botão de Regras do menu ------\
			}else if(mostrarMenu == true && contMenu == true){
				dialogoBntRegras(codigo);
						
			// ---------- muda a seleção dos personagens para esquerda --------- \
			}else if(codigo == KeyEvent.VK_LEFT && dialogoAviso.getImagem() == null && mostrarMenu == false) {
				
				teclaEsquerda.load("res\\escolhaDePersonagem\\setaEsquerda2.png");
				
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
			} else if(codigo == KeyEvent.VK_RIGHT && dialogoAviso.getImagem() == null && mostrarMenu == false) {
				
				teclaDireita.load("res\\escolhaDePersonagem\\setaDireita2.png");
				
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
			else if(codigo == KeyEvent.VK_Z && dialogoAviso.getImagem() == null && mostrarMenu == false) {
				teclaZ.load("res\\escolhaDePersonagem\\teclaZ2.png");
				
				sombreadorDialogoAviso.load("res\\sombreador.png");
				dialogoAviso.load("res\\dialogo.png");
				bntSimDialogoAviso.load("res\\bntSim.png");
				bntNaoDialogoAviso.load("res\\bntNao2.png");
				botaoSimNaoDialogo = true;
				
				if(contTeclaAven == 0 || contTeclaAven == 2 || contTeclaAven == 4) {
					txtDialogoAviso.setTexto("O aventureiro escolhido não poderá ser trocado");
				} else {
					txtDialogoAviso.setTexto("A aventureira escolhida não poderá ser trocada");
				}
				txtDialogoAviso2.setTexto("durante o jogo.");
				txtDialogoAviso3.setTexto("Deseja continuar?");
			
			// --------------- fecha o dialogo de aviso ao apertar X -------------- \
			}else if(codigo == KeyEvent.VK_X && mostrarMenu == false) {
				teclaX.load("res\\escolhaDePersonagem\\teclaX2.png");
				
				limparDialogo();
			
			// ------------ muda a seleção das opções do dialogo de aviso ---------- \
			}else if((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoAviso.getImagem() != null && mostrarMenu == false) {
				
				dialogoAviso.load("res\\dialogo.png");
				bntSimDialogoAviso.load("res\\bntSim" + (botaoSimNaoDialogo == false ? "" : "2") + ".png");
				bntNaoDialogoAviso.load("res\\bntNao" + (botaoSimNaoDialogo == false ? "2" : "") + ".png");
				botaoSimNaoDialogo = (botaoSimNaoDialogo == false ? true : false);
			
			// --------------- entra quando pressiona Z no dialogo de aviso ------------- \
			}else if(codigo == KeyEvent.VK_Z && dialogoAviso.getImagem() != null && mostrarMenu == false) {
				teclaZ.load("res\\escolhaDePersonagem\\teclaZ2.png");
				
				// --------------- fecha o dialogo de aviso ------------- \
				if(botaoSimNaoDialogo == false) {
					limparDialogo();
					
				// ------------ vai para a tela de escolha de adversário ----------- \
				} else {
					JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
			        janelaPrincipal.remove(this);
			        tela2 = new Escolha_de_adversario(contTeclaAven, this, telaMenu);
			        janelaPrincipal.add(tela2);
			        janelaPrincipal.setTitle("Escolha de Adversário");
			        janelaPrincipal.revalidate();
			        
			        limparDialogo();
				}
			}
			
		// ---------- encaminha todas as teclas pressionadas para as telas  --------- \
		} else {
			if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Regras1") {
				telaRegras.KeyPressed(tecla);
			
			} else {
				tela2.KeyPressed(tecla);
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  		 				limpa as imagens e textos do dialogo de aviso						|
	\ ---------------------------------------------------------------------------------------- */
	public void limparDialogo() {
		
		sombreadorDialogoAviso.setImagem(null);
		dialogoAviso.setImagem(null);
		bntSimDialogoAviso.setImagem(null);
		bntNaoDialogoAviso.setImagem(null);
		txtDialogoAviso.setTexto(" ");
		txtDialogoAviso2.setTexto(" ");
		txtDialogoAviso3.setTexto(" ");
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  					muda as imagens das teclas enquanto pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void KeyReleased (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);

		if(janela != null && janela.getTitle() == "Escolha de Personagem") {
			
			int codigo = tecla.getKeyCode();
			
			if(codigo == KeyEvent.VK_LEFT) {
				teclaEsquerda.load("res\\escolhaDePersonagem\\setaEsquerda.png");
			}
			else if(codigo == KeyEvent.VK_RIGHT) {
				teclaDireita.load("res\\escolhaDePersonagem\\setaDireita.png");
			}
			else if(codigo == KeyEvent.VK_Z) {
				teclaZ.load("res\\escolhaDePersonagem\\teclaZ.png");
			}
			else if(codigo == KeyEvent.VK_X) {
				teclaX.load("res\\escolhaDePersonagem\\teclaX.png");
			}
			else if(codigo == KeyEvent.VK_ESCAPE) {
				teclaEsc.load("res\\escolhaDePersonagem\\teclaEsc.png");
			}
			
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		graficos.drawImage(titulo.getImagem(), titulo.getX(), titulo.getY(), this);
		
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getX(), iconeIgnis.getY(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getX(), iconeAyla.getY(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getX(), iconeRexthor.getY(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getX(), iconeKiki.getY(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getX(), iconeArius.getY(), this);
		
		graficos.drawImage(luzAven.getImagem(), luzAven.getX(), luzAven.getY(), this);
		
		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getX(), teclaEsquerda.getY(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getX(), teclaDireita.getY(), this);
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getX(), teclaZ.getY(), this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getX(), teclaX.getY(), this);
		
		graficos.drawImage(sombreadorDialogoAviso.getImagem(), sombreadorDialogoAviso.getX(), sombreadorDialogoAviso.getY(), this);
		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getX(), dialogoAviso.getY(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getX(), bntSimDialogoAviso.getY(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getX(), bntNaoDialogoAviso.getY(), this);
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl1 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl2 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso2.getFonte(), frc);
		tl3 = new TextLayout(txtDialogoAviso3.getTexto(), txtDialogoAviso3.getFonte(), frc);
		
		tl1.draw(graficos, txtDialogoAviso.getX(), txtDialogoAviso.getY());
		tl2.draw(graficos, txtDialogoAviso2.getX(), txtDialogoAviso2.getY());
		tl3.draw(graficos, txtDialogoAviso3.getX(), txtDialogoAviso3.getY());
		
		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getX(), sombreadorMenu.getY(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getX(), fundoMenu.getY(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getX(), bntMenu.getY(), this);
		graficos.drawImage(bntRegras.getImagem(), bntRegras.getX(), bntRegras.getY(), this);
		
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		graficos.drawImage(teclaEsc.getImagem(), teclaEsc.getX(), teclaEsc.getY(), this);
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
	}
}
