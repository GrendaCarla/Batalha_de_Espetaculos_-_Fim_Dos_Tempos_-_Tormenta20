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
	private Creditos telaCreditos;
	
	boolean novoJogo;
	
	JFrame janelaPrincipal;
	
	// ------------------------------------ fundo contorno --------------------------------------
	
	private Icones_interativos fundo = new Icones_interativos(0, 0);
	private Icones_interativos tapaResto = new Icones_interativos(1234/2 - 4936/2, 640/2 - 2560/2);

	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1120, -12);
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private Icones_interativos titulo = new Icones_interativos(1234/2 - 600/2, 25);
	
	private int contEngranagem1 = 1;
	private boolean contEngranagem2;
	
	private String caminho; 
	
	private Timer timer;
	
	// ---------------------------- aventureiros ------------------------------------
	
	private Icones_interativos iconeIgnis = new Icones_interativos(20, 640 - 519);
	private Icones_interativos iconeAyla = new Icones_interativos(iconeIgnis.getX() + 232 + 8, iconeIgnis.getY());
	private Icones_interativos iconeRexthor = new Icones_interativos(iconeAyla.getX() + 232 + 8, iconeIgnis.getY());
	private Icones_interativos iconeKiki = new Icones_interativos(iconeRexthor.getX() + 232 + 8, iconeIgnis.getY());
	private Icones_interativos iconeArius = new Icones_interativos(iconeKiki.getX() + 232 + 8, iconeIgnis.getY());
		
	private int contTeclaAven = 0;
	
	// ------------------------------------------- painel ---------------------------------------------

	private Icones_interativos painel = new Icones_interativos(20, iconeIgnis.getY() - 40);
	private Icones_interativos encanamento = new Icones_interativos(0, 23);

	private Icones_interativos luz1 = new Icones_interativos(painel.getX(), painel.getY() + 12);
	private Icones_interativos luz2 = new Icones_interativos(painel.getX(), painel.getY() + 12);
	private Icones_interativos luz3 = new Icones_interativos(painel.getX(), painel.getY() + 12);
	private Icones_interativos luz4 = new Icones_interativos(painel.getX(), painel.getY() + 12);
	private Icones_interativos luz5 = new Icones_interativos(painel.getX(), painel.getY() + 12);
	
	// ------------------------------------------- Controles ---------------------------------------------
	
	private Icones_interativos teclaEsquerda = new Icones_interativos(20 + 10, 640 - 160);
	private Icones_interativos teclaDireita = new Icones_interativos(1234 - 10 - 20 - 37, teclaEsquerda.getY());
	private Icones_interativos teclaZ = new Icones_interativos(20 + 110, 20 + 36);
	private Icones_interativos teclaX = new Icones_interativos(1234 - 20 - 110 - 37, 20 + 36);
	private Icones_interativos teclaEsc = new Icones_interativos(16, 16);
	
	// -------------------------- imagens e texto do dialogo de personagem ------------------------------
	
	private Icones_interativos sombreadorDialogoPerso = new Icones_interativos(0, 0);
	private Icones_interativos dialogoPersonagem = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimdialogoPersonagem = new Icones_interativos(dialogoPersonagem.getX() + 110, dialogoPersonagem.getY() + 180);
	private Icones_interativos bntNaodialogoPersonagem = new Icones_interativos(bntSimdialogoPersonagem.getX() + 370, bntSimdialogoPersonagem.getY());
	
	private Texto txtdialogoPersonagem = new Texto(1234/2 - 706/2 + 60, 548/2 - 20 - 40, " ");
	private Texto txtdialogoPersonagem2 = new Texto(1234/2 - 706/2 + 60, 548/2 + 20 - 40, " ");
	private Texto txtdialogoPersonagem3 = new Texto(1234/2 - 706/2 + 250, 548/2 + 60 - 40, " ");
	
	private TextLayout tl1, tl2, tl3, tl4, tl5; 
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(18 + 200/2 - 128/2, 80);
	private Icones_interativos bntManual = new Icones_interativos(bntMenu.getX(),bntMenu.getY() + 115);
	private Icones_interativos bntCreditos = new Icones_interativos(bntMenu.getX(), bntManual.getY() + 115);

	// ------------------------ imagens e textos do diálogo de aviso ------------------------------

	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 110, dialogoAviso.getY() + 180);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(bntSimDialogoAviso.getX() + 370, bntSimDialogoAviso.getY());
	
	private Texto txtDialogoAviso = new Texto(dialogoAviso.getX() + 110, 548/2 - 16 - 40, " ");
	private Texto txtDialogoAviso2 = new Texto(dialogoAviso.getX() + 250, 548/2 + 40 - 40, " ");
	
	private Boolean bntSimNaoDialgoAviso = true;
	
	private boolean mostrarMenu = false;
	private int contMenu = 0;

	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	public Escolha_de_personagem(Menu PaginaAnterior, boolean NovoJogo, boolean Engrenagem2, String Caminho) {
		this.telaMenu = PaginaAnterior;
		this.novoJogo = NovoJogo;
		this.caminho = Caminho;
		
		contEngranagem2 = Engrenagem2;
		
		fundo.load(caminho + "res\\EscolhaDePersonagem\\fundo.png");
		tapaResto.load(caminho + "res\\fundo2.png");
		
		engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem1.png");
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");

		titulo.load(caminho + "res\\escolhaDePersonagem\\titulo.png");
		contorno.load(caminho + "res\\contorno.png");
		
		// ---------------------------- imagens dos aventureiros ------------------------------------

		iconeIgnis.load(caminho + "res\\escolhaDePersonagem\\ignis2.png");
		iconeAyla.load(caminho + "res\\escolhaDePersonagem\\ayla1.png");
		iconeRexthor.load(caminho + "res\\escolhaDePersonagem\\rexthor1.png");
		iconeKiki.load(caminho + "res\\escolhaDePersonagem\\kiki1.png");
		iconeArius.load(caminho + "res\\escolhaDePersonagem\\arius1.png");
		
		txtdialogoPersonagem.setFonte(new Font("Arial", Font.PLAIN, 26));
		txtdialogoPersonagem.setCorTexto(new Color (235, 230, 233));
		
		// ------------------------------------------- painel ---------------------------------------------

		painel.load(caminho + "res\\escolhaDePersonagem\\painel.png");
		encanamento.load(caminho + "res\\escolhaDePersonagem\\encanamento.png");
		
		luz1.setImagem(null); luz2.setImagem(null); luz3.setImagem(null);
		luz4.setImagem(null); luz5.setImagem(null);
		
		// ------------------------ imagens e textos do diálogo de aviso ------------------------------

		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		
		// ------------------------------------------- Controles ---------------------------------------------

		teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda1.png");
		teclaDireita.load(caminho + "res\\Teclado\\setaDireita1.png");
		teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
		teclaX.load(caminho + "res\\Teclado\\teclaX1.png");
		teclaEsc.load(caminho + "res\\Teclado\\teclaEsc1.png");
				
		timer = new Timer(1, this);
		timer.start();
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  		 		mexe e muda as imagens dos personagem conforme ganham foco					|
	\ ---------------------------------------------------------------------------------------- */
	public void posicionarPersonagens() {

		iconeIgnis.load(caminho + "res\\escolhaDePersonagem\\ignis1.png");
		iconeAyla.load(caminho + "res\\escolhaDePersonagem\\ayla1.png");
		iconeRexthor.load(caminho + "res\\escolhaDePersonagem\\rexthor1.png");
		iconeKiki.load(caminho + "res\\escolhaDePersonagem\\kiki1.png");
		iconeArius.load(caminho + "res\\escolhaDePersonagem\\arius1.png");
		
		luz1.setImagem(null); luz2.setImagem(null); luz3.setImagem(null);
		luz4.setImagem(null); luz5.setImagem(null);
		luz1.setDx(0);

		switch(contTeclaAven) {
			case 0:
				iconeIgnis.load(caminho + "res\\escolhaDePersonagem\\ignis2.png");
				break;
			case 1:
				iconeAyla.load(caminho + "res\\escolhaDePersonagem\\ayla2.png");
				break;
			case 2:
				iconeRexthor.load(caminho + "res\\escolhaDePersonagem\\rexthor2.png");
				break;
			case 3:
				iconeKiki.load(caminho + "res\\escolhaDePersonagem\\kiki2.png");
				break;
			case 4:
				iconeArius.load(caminho + "res\\escolhaDePersonagem\\arius2.png");
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
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
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
	        telaManual.setTela1(this);
	        janelaPrincipal.add(telaManual);
	        janelaPrincipal.setTitle("Manual1");
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
	        telaCreditos.setTela1(this);
	        janelaPrincipal.add(telaCreditos);
	        janelaPrincipal.setTitle("Creditos1");
	        janelaPrincipal.revalidate();
	        timer.stop();
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
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				mostrarMenu = !mostrarMenu;
				teclaEsc.load(caminho + "res\\Teclado\\teclaEsc2.png");
				
				if(mostrarMenu == true) {
					contMenu = 0;
					sombreadorMenu.load(caminho + "res\\sombreador.png");
					fundoMenu.load(caminho + "res\\Menu secundario\\menu2.png");
					bntMenu.load(caminho + "res\\Menu secundario\\bntMenu2.png");
					bntManual.load(caminho + "res\\Menu secundario\\bntManual1.png");
					bntCreditos.load(caminho + "res\\Menu secundario\\bntCreditos1.png");
					
				} else {
					sombreadorMenu.setImagem(null);
					fundoMenu.setImagem(null);
					bntMenu.setImagem(null);
					bntManual.setImagem(null);
					bntCreditos.setImagem(null);
				}
				
			// --------------- muda a seleção das opções do menu ------------- \
			}else if((codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_UP) && mostrarMenu == true && dialogoAviso.getImagem() == null) {
				
				if(contEngranagem1 == 2) {
					contEngranagem1 = 1;
				} else {contEngranagem1 ++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
				
				if(codigo == KeyEvent.VK_UP) {
					if(contMenu == 0) {contMenu = 2;} else {contMenu --;}
				}else if(codigo == KeyEvent.VK_DOWN) {
					if(contMenu == 2) {contMenu = 0;} else {contMenu ++;}
				}
				
				bntMenu.load(caminho + "res\\Menu secundario\\bntMenu1.png");
				bntManual.load(caminho + "res\\Menu secundario\\bntManual1.png");
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
				}
				
			// ---------- encaminha para a função que controla o botão do menu ---------\
			}else if(mostrarMenu == true && contMenu == 0){
				dialogoBntMenu(codigo);
			
			// ---------- encaminha para a função que controla o botão de Manual do menu ------\
			}else if(mostrarMenu == true && contMenu == 1){
				dialogoBntManual(codigo);
				
			// ---------- encaminha para a função que controla o botão de Manual do menu ------\
			}else if(mostrarMenu == true && contMenu == 2){
				dialogoBntCreditos(codigo);
			
			// ---------- muda a seleção dos personagens para esquerda --------- \
			}else if(codigo == KeyEvent.VK_LEFT && dialogoPersonagem.getImagem() == null && mostrarMenu == false) {
				
				if(contEngranagem1 == 2) {
					contEngranagem1 = 1;
				} else {contEngranagem1 ++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
				
				teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda2.png");
				
				if(contTeclaAven == 0) {contTeclaAven = 4;} else {contTeclaAven --;}
				
				posicionarPersonagens();
					   
					  
			// ---------- muda a seleção dos personagens para direita --------- \
			} else if(codigo == KeyEvent.VK_RIGHT && dialogoPersonagem.getImagem() == null && mostrarMenu == false) {
				
				if(contEngranagem1 == 2) {
					contEngranagem1 = 1;
				} else {contEngranagem1 ++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
				
				teclaDireita.load(caminho + "res\\Teclado\\setaDireita2.png");
				
				if(contTeclaAven == 4) {contTeclaAven = 0;} else {contTeclaAven ++;}
				
				posicionarPersonagens();
			}
			// --------------- chama o dialogo de aviso ao apertar Z -------------- \
			else if(codigo == KeyEvent.VK_Z && dialogoPersonagem.getImagem() == null && mostrarMenu == false) {

				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				teclaZ.load(caminho + "res\\Teclado\\teclaZ2.png");
				
				sombreadorDialogoPerso.load(caminho + "res\\sombreador.png");
				dialogoPersonagem.load(caminho + "res\\mensagem aviso\\dialogo.png");
				bntSimdialogoPersonagem.load(caminho + "res\\mensagem aviso\\bntSim1.png");
				bntNaodialogoPersonagem.load(caminho + "res\\mensagem aviso\\bntNao2.png");
				bntSimNaoDialgoAviso = true;
				
				if(contTeclaAven == 0 || contTeclaAven == 2 || contTeclaAven == 4) {
					txtdialogoPersonagem.setTexto("O aventureiro escolhido nãoo poderá ser trocado");
				} else {
					txtdialogoPersonagem.setTexto("A aventureira escolhida não poderá ser trocada");
				}
				txtdialogoPersonagem2.setTexto("durante o jogo.");
				txtdialogoPersonagem3.setTexto("Deseja continuar?");
			
			// --------------- fecha o dialogo de aviso ao apertar X -------------- \
			}else if(codigo == KeyEvent.VK_X && mostrarMenu == false) {

				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				teclaX.load(caminho + "res\\Teclado\\teclaX2.png");
				
				limparDialogo();
			
			// ------------ muda a seleção das opções do dialogo de aviso ---------- \
			}else if((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoPersonagem.getImagem() != null && mostrarMenu == false) {
				
				if(contEngranagem1 == 2) {
					contEngranagem1 = 1;
				} else {contEngranagem1 ++;}
				
				engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
				bntSimdialogoPersonagem.load(caminho + "res\\mensagem aviso\\bntSim" + (bntSimNaoDialgoAviso == false ? "1" : "2") + ".png");
				bntNaodialogoPersonagem.load(caminho + "res\\mensagem aviso\\bntNao" + (bntSimNaoDialgoAviso == false ? "2" : "1") + ".png");
				bntSimNaoDialgoAviso = (bntSimNaoDialgoAviso == false ? true : false);
			
			// --------------- entra quando pressiona Z no dialogo de aviso ------------- \
			}else if(codigo == KeyEvent.VK_Z && dialogoPersonagem.getImagem() != null && mostrarMenu == false) {
				
				contEngranagem2 = !contEngranagem2;
				engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
				
				teclaZ.load(caminho + "res\\Teclado\\teclaZ2.png");
				
				// --------------- fecha o dialogo de aviso ------------- \
				if(bntSimNaoDialgoAviso == false) {
					limparDialogo();
					
				// ------------ vai para a tela de escolha de adversário ----------- \
				} else {
					int [][] TabelaInteracao = {{0, 0, 0, 0, 0},
										   {0, 0, 0, 0, 0},
										   {0, 0, 0, 0, 0},
										   {0, 0, 0, 0, 0},
										   {0, 0, 0, 0, 0}};
					
			        chamarTela2(contTeclaAven, TabelaInteracao);
			        
			        limparDialogo();
				}
			}
			
		// ---------- encaminha todas as teclas pressionadas para as telas  --------- \
		} else {

			if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Manual1") {
				telaManual.KeyPressed(tecla);
			
			} else if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Creditos1") {
				telaCreditos.KeyPressed(tecla);
				
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
			
			switch (codigo) {
				case KeyEvent.VK_LEFT:
					teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda1.png");
					break;
				case KeyEvent.VK_RIGHT:
					teclaDireita.load(caminho + "res\\Teclado\\setaDireita1.png");
					break;
				case KeyEvent.VK_Z:
					teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
					break;
				case KeyEvent.VK_X:
					teclaX.load(caminho + "res\\Teclado\\teclaX1.png");
					break;
				case KeyEvent.VK_ESCAPE:
					teclaEsc.load(caminho + "res\\Teclado\\teclaEsc1.png");
					break;
			}
			
		} else  {
			tela2.KeyReleased(tecla);
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo.getImagem(), fundo.getRedX(), fundo.getRedY(), fundo.getLarg(), fundo.getAlt(), this);
		
		// ---------------------------- aventureiros ------------------------------------
		
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getRedX(), iconeIgnis.getRedY(), iconeIgnis.getLarg(), iconeIgnis.getAlt(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getRedX(), iconeAyla.getRedY(), iconeAyla.getLarg(), iconeAyla.getAlt(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getRedX(), iconeRexthor.getRedY(), iconeRexthor.getLarg(), iconeRexthor.getAlt(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getRedX(), iconeKiki.getRedY(), iconeKiki.getLarg(), iconeKiki.getAlt(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getRedX(), iconeArius.getRedY(), iconeArius.getLarg(), iconeArius.getAlt(), this);
		
		// ------------------------------------------- painel ---------------------------------------------

		graficos.drawImage(encanamento.getImagem(), encanamento.getRedX(), encanamento.getRedY(), encanamento.getLarg(), encanamento.getAlt(), this);
		graficos.drawImage(painel.getImagem(), painel.getRedX(), painel.getRedY(), painel.getLarg(), painel.getAlt(), this);
		graficos.drawImage(titulo.getImagem(), titulo.getRedX(), titulo.getRedY(), titulo.getLarg(), titulo.getAlt(), this);
		
		graficos.drawImage(luz1.getImagem(), luz1.getRedX(), luz1.getRedY(), luz1.getLarg(), luz1.getAlt(), this);
		graficos.drawImage(luz2.getImagem(), luz2.getRedX(), luz2.getRedY(), luz2.getLarg(), luz2.getAlt(), this);
		graficos.drawImage(luz3.getImagem(), luz3.getRedX(), luz3.getRedY(), luz3.getLarg(), luz3.getAlt(), this);
		graficos.drawImage(luz4.getImagem(), luz4.getRedX(), luz4.getRedY(), luz4.getLarg(), luz4.getAlt(), this);
		graficos.drawImage(luz5.getImagem(), luz5.getRedX(), luz5.getRedY(), luz5.getLarg(), luz5.getAlt(), this);

		// ------------------------------------------- Controles ---------------------------------------------

		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getRedX(), teclaEsquerda.getRedY(), teclaEsquerda.getLarg(), teclaEsquerda.getAlt(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getRedX(), teclaDireita.getRedY(), teclaDireita.getLarg(), teclaDireita.getAlt(), this);
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getRedX(), teclaZ.getRedY(), teclaZ.getLarg(), teclaZ.getAlt(), this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getRedX(), teclaX.getRedY(), teclaX.getLarg(), teclaX.getAlt(), this);

		// -------------------------- imagens e texto do dialogo de personagens ------------------------------
		
		graficos.drawImage(sombreadorDialogoPerso.getImagem(), sombreadorDialogoPerso.getRedX(), sombreadorDialogoPerso.getRedY(), sombreadorDialogoPerso.getLarg(), sombreadorDialogoPerso.getAlt(), this);
		graficos.drawImage(dialogoPersonagem.getImagem(), dialogoPersonagem.getRedX(), dialogoPersonagem.getRedY(), dialogoPersonagem.getLarg(), dialogoPersonagem.getAlt(), this);
		graficos.drawImage(bntSimdialogoPersonagem.getImagem(), bntSimdialogoPersonagem.getRedX(), bntSimdialogoPersonagem.getRedY(), bntSimdialogoPersonagem.getLarg(), bntSimdialogoPersonagem.getAlt(), this);
		graficos.drawImage(bntNaodialogoPersonagem.getImagem(), bntNaodialogoPersonagem.getRedX(), bntNaodialogoPersonagem.getRedY(), bntNaodialogoPersonagem.getLarg(), bntNaodialogoPersonagem.getAlt(), this);

		graficos.setColor(txtdialogoPersonagem.getCorTexto());
		tl1 = new TextLayout(txtdialogoPersonagem.getTexto(), txtdialogoPersonagem.getFonte(), frc);
		tl2 = new TextLayout(txtdialogoPersonagem2.getTexto(), txtdialogoPersonagem.getFonte(), frc);
		tl3 = new TextLayout(txtdialogoPersonagem3.getTexto(), txtdialogoPersonagem.getFonte(), frc);

		tl1.draw(graficos, txtdialogoPersonagem.getRedX(), txtdialogoPersonagem.getRedY());
		tl2.draw(graficos, txtdialogoPersonagem2.getRedX(), txtdialogoPersonagem2.getRedY());
		tl3.draw(graficos, txtdialogoPersonagem3.getRedX(), txtdialogoPersonagem3.getRedY());
		
		// ------------------------------------- imagens do menu ---------------------------------------

		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getRedX(), sombreadorMenu.getRedY(), sombreadorMenu.getLarg(), sombreadorMenu.getAlt(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getRedX(), fundoMenu.getRedY(), fundoMenu.getLarg(), fundoMenu.getAlt(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getRedX(), bntMenu.getRedY(), bntMenu.getLarg(), bntMenu.getAlt(), this);
		graficos.drawImage(bntManual.getImagem(), bntManual.getRedX(), bntManual.getRedY(), bntManual.getLarg(), bntManual.getAlt(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getRedX(), bntCreditos.getRedY(), bntCreditos.getLarg(), bntCreditos.getAlt(), this);

		// ------------------------------------- dialogo do menu ---------------------------------------

		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getRedX(), dialogoAviso.getRedY(), dialogoAviso.getLarg(), dialogoAviso.getAlt(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getRedX(), bntSimDialogoAviso.getRedY(), bntSimDialogoAviso.getLarg(), bntSimDialogoAviso.getAlt(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getRedX(), bntNaoDialogoAviso.getRedY(), bntNaoDialogoAviso.getLarg(), bntNaoDialogoAviso.getAlt(), this);
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl4 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl5 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso.getFonte(), frc);
		
		tl4.draw(graficos, txtDialogoAviso.getRedX(), txtDialogoAviso.getRedY());
		tl5.draw(graficos, txtDialogoAviso2.getRedX(), txtDialogoAviso2.getRedY());
		
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getRedX(), engrenagem1.getRedY(), engrenagem1.getLarg(), engrenagem1.getAlt(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getRedX(), engrenagem2.getRedY(), engrenagem2.getLarg(), engrenagem2.getAlt(), this);
		
		graficos.drawImage(contorno.getImagem(), contorno.getRedX(), contorno.getRedY(), contorno.getLarg(), contorno.getAlt(), this);
		graficos.drawImage(teclaEsc.getImagem(), teclaEsc.getRedX(), teclaEsc.getRedY(), teclaEsc.getLarg(), teclaEsc.getAlt(), this);

		graficos.drawImage(tapaResto.getImagem(), tapaResto.getRedX(), tapaResto.getRedY(), tapaResto.getLarg(), tapaResto.getAlt(), this);
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		animarTitulo();
		
		animarluzes();
		
		repaint();
	}
	
	public void animarTitulo() {
		
		if(titulo.getDx() == 60) {titulo.setDx(1);}
		else {titulo.setDx(titulo.getDx() + 1);}
		
		if(titulo.getDx() % 3 == 0) {
			titulo.setY(titulo.getY() + (titulo.getDx() > 30 ? 1 : -1));
		}
	}

	public void animarluzes() {
		
		if(luz1.getDx() == 24) {luz1.setDx(0);}
		else {luz1.setDx(luz1.getDx() + 1);}
		
		if(luz1.getDx() == 15) {
			(contTeclaAven == 0 ? luz1 : (contTeclaAven == 1 ? luz2 : (contTeclaAven == 2 ? luz3 : (contTeclaAven == 3 ? luz4 : luz5)))).load(caminho + "res\\escolhaDePersonagem\\luz" + (contTeclaAven == 0 ? "3" : (contTeclaAven == 1 ? "6" : (contTeclaAven == 2 ? "9" : (contTeclaAven == 3 ? "12" : "15")))) + ".png");
		} else if(luz1.getDx() == 10) {
			(contTeclaAven == 0 ? luz1 : (contTeclaAven == 1 ? luz2 : (contTeclaAven == 2 ? luz3 : (contTeclaAven == 3 ? luz4 : luz5)))).load(caminho + "res\\escolhaDePersonagem\\luz" + (contTeclaAven == 0 ? "2" : (contTeclaAven == 1 ? "5" : (contTeclaAven == 2 ? "8" : (contTeclaAven == 3 ? "11" : "14")))) + ".png");
		} else if(luz1.getDx() == 5) {
			(contTeclaAven == 0 ? luz1 : (contTeclaAven == 1 ? luz2 : (contTeclaAven == 2 ? luz3 : (contTeclaAven == 3 ? luz4 : luz5)))).load(caminho + "res\\escolhaDePersonagem\\luz" + (contTeclaAven == 0 ? "1" : (contTeclaAven == 1 ? "4" : (contTeclaAven == 2 ? "7" : (contTeclaAven == 3 ? "10" : "13")))) + ".png");
		} else if(luz1.getDx() == 0) {
			(contTeclaAven == 0 ? luz1 : (contTeclaAven == 1 ? luz2 : (contTeclaAven == 2 ? luz3 : (contTeclaAven == 3 ? luz4 : luz5)))).setImagem(null);
		}
	}
	
	public void chamarTela2(int ContTeclaAven, int [][] TabelaInteracao) {
		
		JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
        janelaPrincipal.remove(this);
        tela2 = new Escolha_de_adversario(ContTeclaAven, this, telaMenu, TabelaInteracao, novoJogo, contEngranagem2, caminho);
        janelaPrincipal.add(tela2);
        janelaPrincipal.setTitle("Escolha de Adversário");
        janelaPrincipal.revalidate();
        timer.stop();
	}
	
	public void LimparTela2() {
		tela2 = null;
	}
	

	public void setContEngranagem2(boolean contEngranagem2) {
		this.contEngranagem2 = contEngranagem2;
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");	
		timer.start();
	}
}