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

public class Escolha_de_adversario extends JPanel implements ActionListener {
	
	/* ---------------------------------------------------------------------------------------- \
	|  Escolha_de_adversario é a tela a onde os jogadores escolheram qual dos cães das colinas  |
	|  iram jogar contra.																		|
	\ ---------------------------------------------------------------------------------------- */

	Batalha tela3;
	private Escolha_de_personagem paginaAnterior;
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	
	private int redimLarg, redimAlt;
	
	// ------------------------ imagens dos ícones aventureiros ------------------------------
	
	private Icones_interativos iconeBoss = new Icones_interativos(510, 16);
	private Icones_interativos iconeIgnis = new Icones_interativos(240, 200);
	private Icones_interativos iconeAyla = new Icones_interativos(785, 200);
	private Icones_interativos iconeRexthor = new Icones_interativos(45, 400);
	private Icones_interativos iconeKiki = new Icones_interativos(500, 400);
	private Icones_interativos iconeArius = new Icones_interativos(980, 400);
	
	private int contTeclaBatalha = 0;
	
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
	private Icones_interativos bntSimDialogo = new Icones_interativos(1234/2 - 484/2, 640 - 50 - 40);
	private Icones_interativos bntNaoDialogo  = new Icones_interativos(1234/2 - 484/2 + 370, 640 - 50 - 40);

	
	private Texto txtDialogoLn1 = new Texto(100, 640 - 185 + 15, " ");
	private Texto txtDialogoLn2 = new Texto(100, 640 - 185 + 50, " ");
	private Texto txtDialogoLn3 = new Texto(100, 640 - 185 + 85, " ");
	private Texto txtDialogoLn4 = new Texto(100, 640 - 185 + 120, " ");
	
	private int contDialogo = 0;
	private boolean mudaCorLn4 = false;
	private Random aleatorioTibar = new Random();
	private boolean tibarCoroa = true;
	private boolean bntSimNao = true;
	
	// ------------------------ imagens de estrelas para batalhas vencidas ------------------------------

	private Icones_interativos vencido0 = new Icones_interativos(240 + 100 - 31, 200 - 20);
	private Icones_interativos vencido1 = new Icones_interativos(785 + 100 - 31, 200 + 15);
	private Icones_interativos vencido2 = new Icones_interativos(45 + 100 - 31, 400 - 26);
	private Icones_interativos vencido3 = new Icones_interativos(500 + 100 - 31, 400 - 38);
	private Icones_interativos vencido4 = new Icones_interativos(980 + 100 - 31, 400 - 18);
	
	boolean [] derrotados = {false, false, false, false, false};
	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------

	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(1234/2 - 706/2 + 110, 640/2 - 278/2 + 190);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(1234/2 - 706/2 + 480, 640/2 - 278/2 + 190);
	
	
	private Texto txtDialogoAviso = new Texto(1234/2 - 706/2 + 110, 548/2 - 28, " ");
	private Texto txtDialogoAviso2 = new Texto(1234/2 - 706/2 + 250, 548/2 + 52, " ");
	
	private Boolean bntSimNaoDialgoAviso = true;
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(41,66);
	private Icones_interativos bntRegras = new Icones_interativos(41,202);
	private Icones_interativos bntVoltar = new Icones_interativos(41,338);
	
	private boolean mostrarMenu = false;
	private int contMenu = 0;
	
	// -------------------- imagens de decoração do diálogo com o boss ---------------------
	
	private Icones_interativos estrelaFim1 = new Icones_interativos(10, 413);
	private Icones_interativos estrelaFim2 = new Icones_interativos(1234 - 196, 412);

	//---------------------------------------------------------------------------------------
	
	private int aventureiro; // numero do aventureiro escolido anteriormente
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6;
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private Timer timer;
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	
	public Escolha_de_adversario(int numAventureiro, Escolha_de_personagem PaginaAnterior) {
		
		aventureiro = numAventureiro;
		this.paginaAnterior = PaginaAnterior;
		
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\escolhaDeAdversario\\fundo.png");
		
		contorno.load("res\\contorno.png");
		
		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		txtDialogoAviso2.setFonte(new Font("Arial", Font.PLAIN, 28));
		
		txtDialogoLn1.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn1.setCorTexto(new Color (235, 230, 233));
		txtDialogoLn2.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn3.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn4.setCorTexto(new Color (239, 22, 109));
		txtDialogoLn4.setFonte(new Font("Arial", Font.PLAIN, 24));
		
		iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado.png");
		iconeIgnis.load("res\\escolhaDeAdversario\\ignis.png");
		iconeAyla.load("res\\escolhaDeAdversario\\ayla.png");
		iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
		iconeKiki.load("res\\escolhaDeAdversario\\kiki.png");
		iconeArius.load("res\\escolhaDeAdversario\\arius.png");
		
		mostrarEstrela();
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									volta para tela anterior								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntVoltar(int codigo) {
		if(dialogoAviso.getImagem() == null && codigo == KeyEvent.VK_Z) {
			
			dialogoAviso.load("res\\dialogo.png");
			bntSimDialogoAviso.load("res\\bntSim.png");
			bntNaoDialogoAviso.load("res\\bntNao2.png");
			bntSimNaoDialgoAviso = true;
			
			txtDialogoAviso.setTexto("Se você voltar perderá o seu progreço.");
			txtDialogoAviso2.setTexto("Deseja continuar?");
			
		}else if ((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoAviso.getImagem() != null) {
		
			bntSimNaoDialgoAviso = !bntSimNaoDialgoAviso;
			bntSimDialogoAviso.load("res\\bntSim" + (bntSimNaoDialgoAviso == true ? "" : "2") + ".png");
			bntNaoDialogoAviso.load("res\\bntNao" + (bntSimNaoDialgoAviso == true ? "2" : "") + ".png");
		
		}else if(codigo == KeyEvent.VK_Z  || codigo == KeyEvent.VK_X && dialogoAviso.getImagem() != null) {
			
			if(bntSimNaoDialgoAviso == false || codigo == KeyEvent.VK_X) {
				dialogoAviso.setImagem(null);
				bntSimDialogoAviso.load(null);
				bntNaoDialogoAviso.load(null);
				
				txtDialogoAviso.setTexto(" ");
				txtDialogoAviso2.setTexto(" ");
			}
			else {
				JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(paginaAnterior);
		        janelaPrincipal.setTitle("Escolha de Personagem");
		        janelaPrincipal.revalidate();
			}
		}
	}
	
	public void limpaIconesAdversarios () {
		iconeIgnis.load("res\\escolhaDeAdversario\\ignis.png");
		iconeAyla.load("res\\escolhaDeAdversario\\ayla.png");
		iconeRexthor.load("res\\escolhaDeAdversario\\rexthor.png");
		iconeKiki.load("res\\escolhaDeAdversario\\kiki.png");
		iconeArius.load("res\\escolhaDeAdversario\\arius.png");

		if(derrotados[0] != false && derrotados[1] != false && derrotados[2] != false && derrotados[3] != false && derrotados[4] != false) {
			iconeBoss.load("res\\escolhaDeAdversario\\BoxDestrancado.png");
		}else {
			iconeBoss.load("res\\escolhaDeAdversario\\BoxTrancado.png");
		}
	}
	/* ---------------------------------------------------------------------------------------- \
	|  							dispara quando as teclas são  pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		if(janela != null && janela.getTitle() == "Escolha de Adversário") {
			int codigo = tecla.getKeyCode();
			
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
				
				bntMenu.load("res\\bntMenu1.png");
				bntRegras.load("res\\bntRegras1.png");
				bntVoltar.load("res\\bntVoltar1.png");
				
				switch (contMenu) {
					case 0:
						bntMenu.load("res\\bntMenu2.png");
						break;
					case 1:
						bntRegras.load("res\\bntRegras2.png");
						break;
					case 2:
						bntVoltar.load("res\\bntVoltar2.png");
						break;
				}
				
			// ---------- encaminha para a função que controla o botão voltar do menu --------- \
			}else if(mostrarMenu == true && contMenu == 2) {
				dialogoBntVoltar(codigo);
				
			// ---------- muda a seleção dos ícones dos personagens no mapa para esquerda e direita --------- \
			} else if((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() == null) {
				
				if(codigo == KeyEvent.VK_LEFT) {
					if(contTeclaBatalha == 0) {contTeclaBatalha = 4;} 
					else if(contTeclaBatalha == 5) {contTeclaBatalha = 1;}
					else{contTeclaBatalha --;}
					
				} else if(codigo == KeyEvent.VK_RIGHT) {
					if(contTeclaBatalha == 4) {contTeclaBatalha = 0;} 
					else if(contTeclaBatalha == 5) {contTeclaBatalha = 3;}
					else{contTeclaBatalha ++;}
				}
				
				limpaIconesAdversarios ();
				
				switch (contTeclaBatalha) {
					case 0:
						iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
					    break;
					case 1:
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis2.png");
						break;
					case 2:
						iconeKiki.load("res\\escolhaDeAdversario\\kiki2.png");
					    break;
					case 3:
						iconeAyla.load("res\\escolhaDeAdversario\\ayla2.png");
					    break;
					case 4:
						iconeArius.load("res\\escolhaDeAdversario\\arius2.png");
					    break;
					case 5:
						if(derrotados[0] != false && derrotados[1] != false && derrotados[2] != false && derrotados[3] != false && derrotados[4] != false) {
							iconeBoss.load("res\\escolhaDeAdversario\\BoxDestrancado2.png");
						}else {
							iconeBoss.load("res\\escolhaDeAdversario\\BoxTrancado2.png");
						}
						break;
				}
			
			// ---------- muda a seleção dos ícones dos personagens no mapa para cima e para baixo --------- \
			} else if((codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) && mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() == null) {
				
				limpaIconesAdversarios ();
				
				if(codigo == KeyEvent.VK_UP) {
					switch (contTeclaBatalha) {
						case 0:
							iconeIgnis.load("res\\escolhaDeAdversario\\ignis2.png");
							contTeclaBatalha = 1;
						    break;
						case 1: case 2: case 3:
							if(derrotados[0] != false && derrotados[1] != false && derrotados[2] != false && derrotados[3] != false && derrotados[4] != false) {
								iconeBoss.load("res\\escolhaDeAdversario\\boxDestrancado2.png");
							}else {
								iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado2.png");
							}
							contTeclaBatalha = 5;
						    break;
						case 4:
							iconeAyla.load("res\\escolhaDeAdversario\\ayla2.png");
							contTeclaBatalha = 3;
						    break;
						case 5:
							iconeKiki.load("res\\escolhaDeAdversario\\kiki2.png");
							contTeclaBatalha = 2;
						    break;
					}
				} else {
					
					switch (contTeclaBatalha) {
						case 0: case 2: case 4:
							if(derrotados[0] != false && derrotados[1] != false && derrotados[2] != false && derrotados[3] != false && derrotados[4] != false){
								iconeBoss.load("res\\escolhaDeAdversario\\boxDesTrancado2.png");
							} else {iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado2.png");}
							contTeclaBatalha = 5;
						    break;
						case 1:
							iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
							contTeclaBatalha = 0;
						    break;
						case 3:
							iconeArius.load("res\\escolhaDeAdversario\\arius2.png");
							contTeclaBatalha = 4;
						    break;
						case 5:
							iconeKiki.load("res\\escolhaDeAdversario\\kiki2.png");
							contTeclaBatalha = 2;
						    break;
					}
				}
			}
			
			// ---------- muda a seleção dos botões sim, não, coroa e sem coroa na barra de diálogo dos cães --------- \
			else if((codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_LEFT) && mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() != null && bntSimDialogo.getImagem() != null) {
				
				if(contTeclaBatalha == 0 && contDialogo == 3) {
					tibarCoroa = !tibarCoroa;
					bntSimDialogo.load("res\\escolhaDeAdversario\\tibarCoroa" + (tibarCoroa == true ? "3" : "2") + ".png");
					bntNaoDialogo.load("res\\escolhaDeAdversario\\tibarSemCoroa" + (tibarCoroa == true ? "2" : "3") + ".png");
					
				} else {
					bntSimNao = !bntSimNao;
					bntSimDialogo.load("res\\bntSim" + (bntSimNao == true? "" : "2") + ".png");
					bntNaoDialogo.load("res\\bntNao" + (bntSimNao == true? "2" : "") + ".png");
					
				}
			}
			
			// --------------- quando o diálogo é fechado ele limpa as imagens e textos --------------- \
			else if(codigo == KeyEvent.VK_Z && mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() != null && bntSimDialogo.getImagem() == null && ((bntSimNao == false && ((contTeclaBatalha == 0 && contDialogo == 2) || (contTeclaBatalha == 1 && contDialogo == 2) || (contTeclaBatalha == 2 && contDialogo == 3) || (contTeclaBatalha == 3 && contDialogo == 3) || (contTeclaBatalha == 4 && contDialogo == 3))) || (contTeclaBatalha == 5 && contDialogo == 1) || (contTeclaBatalha == 0 && contDialogo == 4 && mudaCorLn4 == false))) {
				limparDialogo();
			}
			
			// ----------------------- diálogo com os cães das colinas ------------------------ \
			else if(codigo == KeyEvent.VK_Z && mostrarMenu == false && dialogoAviso.getImagem() == null && ((contTeclaBatalha == 0 && contDialogo < 4) || (contTeclaBatalha == 1 && contDialogo < 2) || (contTeclaBatalha == 2 && (contDialogo < 2  || (contDialogo < 3 && bntSimNao == false))) || (contTeclaBatalha == 3 && contDialogo < 5) || (contTeclaBatalha == 4 && contDialogo < 3) || (contTeclaBatalha == 5 && contDialogo < 1))) {
				
				sombreadorDialogo.load("res\\sombreador.png");
				barraDeDialogo.load("res\\escolhaDeAdversario\\barraDeDialogo.png");
				contDialogo++;
				
				switch (contTeclaBatalha) {
					case 0: // Rexthor
						if(contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 400/2 - 30); imagemDoDialogo.setY(70);
							objetoDeFundo1.setY(90); objetoDeFundo1.setX(30);
							objetoDeFundo2.setY(200); objetoDeFundo2.setX(880);
							
							txtDialogoLn2.setX(516);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemRexthor.png");
							objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoRexthor.png");
							objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoRexthor2.png");
							
							txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[0][0]);
							txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[0][1]);
							txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[0][2]);
							txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[0][3]);
							
							bntSimDialogo.load("res\\bntSim.png");
							bntNaoDialogo.load("res\\bntNao2.png");
							
						} else if(contDialogo == 2){
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							txtDialogoLn2.setX(100);
							
							if(bntSimNao == true){
								imagemDoDialogo.setY(69);
								imagemDoDialogo.setX(1234/2 - 310/2 - 29);
								
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemRexthor2.png");
								
								txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[2][0]);
								txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[2][1]);
								txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[2][2]);
								txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[2][3]);
								
							} else {
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemRexthor3.png");
								
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
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemRexthor2.png");
							
							txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[3][0]);
							txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[3][1]);
							txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[3][2]);
							txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[3][3]);
							
							bntSimDialogo.load("res\\escolhaDeAdversario\\tibarCoroa3.png");
							bntNaoDialogo.load("res\\escolhaDeAdversario\\tibarSemCoroa2.png");
						
						} else if(contDialogo == 4){
							imagemDoDialogo.setY(120);
							imagemDoDialogo.setX(1234/2 - 200/2);
							txtDialogoLn1.setX(100);
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							
							boolean tibar = aleatorioTibar.nextInt(2)== 0 ? true : false;
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\" + (tibar == true ? "tibarCoroa" : "tibarSemCoroa") + ".png");
							
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
					case 1: // Ignis
						if(contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 500/2 - 100);
							objetoDeFundo2.setX(450);
							objetoDeFundo1.setY(20); objetoDeFundo1.setX(20);
							txtDialogoLn2.setX(270);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemIgnis.png");
							objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoIgnis.png");
							objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoIgnis2.png");
							
							txtDialogoLn1.setTexto(ignis.getConteudoEscolhaAdversario()[0][0]);
							txtDialogoLn2.setTexto(ignis.getConteudoEscolhaAdversario()[0][1]);
							txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[0][2]);
							txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[0][3]);
							
							bntSimDialogo.load("res\\bntSim.png");
							bntNaoDialogo.load("res\\bntNao2.png");
							
						} else if(contDialogo == 2){
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							txtDialogoLn2.setX(100);
							
							if(bntSimNao == true){
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemIgnis2.png");
								
								txtDialogoLn1.setTexto(ignis.getConteudoEscolhaAdversario()[1][0]);
								txtDialogoLn2.setTexto(ignis.getConteudoEscolhaAdversario()[1][1]);
								txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[1][2]);
								txtDialogoLn4.setTexto(ignis.getConteudoEscolhaAdversario()[1][3]);
								
								mudaCorLn4 = true;
							} else {
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemIgnis3.png");
								
								txtDialogoLn1.setTexto(ignis.getConteudoEscolhaAdversario()[2][0]);
								txtDialogoLn2.setTexto(ignis.getConteudoEscolhaAdversario()[2][1]);
								txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[2][2]);
								txtDialogoLn4.setTexto(ignis.getConteudoEscolhaAdversario()[2][3]);
							}
							
							
						} 
					    break;
					case 2: // Kiki
						if(contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 300/2);
							
							objetoDeFundo1.setX(20); objetoDeFundo1.setY(100);
							objetoDeFundo2.setX(1234 - 630 - 20); objetoDeFundo2.setY(30);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemKiki.png");
							objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoKiki.png");
							objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoKiki2.png");
							
							txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[0][0]);
							txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[0][1]);
							txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[0][2]);
							txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[0][3]);
							
							bntSimDialogo.load("res\\bntSim.png");
							bntNaoDialogo.load("res\\bntNao2.png");
							
						} else if(contDialogo == 2){
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							
							if(bntSimNao == true){
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemKiki2.png");
								
								txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[3][0]);
								txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[3][1]);
								txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[3][2]);
								txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[3][3]);
								mudaCorLn4 = true;
							} else {
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemKiki3.png");
								
								txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[1][0]);
								txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[1][1]);
								txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[1][2]);
								txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[1][3]);
							}
						} else if(contDialogo == 3 && bntSimNao == false){
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemKiki2.png");
							
							txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[2][0]);
							txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[2][1]);
							txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[2][2]);
							txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[2][3]);
						}
						
					    break;
					case 3: // Ayla
						if(contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 840/2);
							imagemDoDialogo.setY(40);
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemMutuca.png");
							objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoMutuca.png");
							objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoMutuca2.png");
							
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
							
							bntSimDialogo.load("res\\bntSim.png");
							bntNaoDialogo.load("res\\bntNao2.png");
						} else if(contDialogo == 3) {
							
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							
							if(bntSimNao == true) {
								txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[2][0]);
								txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[2][1]);
								txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[2][2]);
								txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[2][3]);
								
							} else {
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemMutuca2.png");
								
								txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[3][0]);
								txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[3][1]);
								txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[3][2]);
								txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[3][3]);
							}
						} else if(contDialogo == 4) {
							imagemDoDialogo.setX(1234/2 - 480/2);
							imagemDoDialogo.setY(40);
							
							objetoDeFundo1.setX(10); objetoDeFundo1.setY(163);
							objetoDeFundo2.setX(1234 - 250 - 10); objetoDeFundo2.setY(160);
							
							txtDialogoLn2.setX(100);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemAyla.png");
							objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoAyla.png");
							objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoAyla2.png");
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[4][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[4][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[4][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[4][3]);
							
						} else if(contDialogo == 5) {
							txtDialogoLn4.setX(100);
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemAyla2.png");	
							
							txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[5][0]);
							txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[5][1]);
							txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[5][2]);
							txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[5][3]);
							
							mudaCorLn4 = true;
						}
					    break;
					case 4: // Arius
						if(contDialogo == 1) {
							objetoDeFundo1.setX(16); objetoDeFundo1.setY(240);
							objetoDeFundo2.setX(1234 - 300 - 20 - 30); objetoDeFundo2.setY(30);
							imagemDoDialogo.setX(1234/2 - 462/2);
							imagemDoDialogo.setY(20);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemArius.png");
							
							objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoArius.png");
							objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoArius2.png");
							
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
							
							bntSimDialogo.load("res\\bntSim.png");
							bntNaoDialogo.load("res\\bntNao2.png");
							
						} else if(contDialogo == 3) {
							bntSimDialogo.setImagem(null);
							bntNaoDialogo.setImagem(null);
							txtDialogoLn2.setX(100);
							
							if(bntSimNao == true) {
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemArius2.png");
								
								txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[2][0]);
								txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[2][1]);
								txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[2][2]);
								txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[2][3]);
								
								mudaCorLn4 = true;
							} else {
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemArius3.png");
								
								txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[3][0]);
								txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[3][1]);
								txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[3][2]);
								txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[3][3]);
							}
						}
						
						
					    break;
					case 5: // Boss
						imagemDoDialogo.setX(1234/2 - 400/2);
						imagemDoDialogo.setY(10);
						
						if(derrotados[0] == false || derrotados[1] == false || derrotados[2] == false || derrotados[3] == false || derrotados[4] == false) {
							
							txtDialogoLn2.setX(470);
							txtDialogoLn3.setX(312);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\cadeadoGrande1.png");
							
							txtDialogoLn1.setTexto(" ");
							txtDialogoLn2.setTexto("Esta batalha está trancada!");
							txtDialogoLn3.setTexto("Vença primeiro os 5 Cães das Colinas antes de prosseguir");
							txtDialogoLn4.setTexto(" ");
						} else {
							txtDialogoLn1.setX(184);
							txtDialogoLn2.setX(296);
							txtDialogoLn4.setX(290);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\cadeadoGrande2.png");
							
							txtDialogoLn1.setTexto("Parabéns!!! você conseguiu vencer os 5 Cães das Colinas, infelizmente o jogo não");
							txtDialogoLn2.setTexto("esta completo ainda e você terá que esperar um pouco mais.");
							txtDialogoLn3.setTexto(" ");
							txtDialogoLn4.setTexto("Muito obrigado por ter jogado e espero que tenha se divertido.");
							
							estrelaFim1.load("res\\escolhaDeAdversario\\estrela2.png");
							estrelaFim2.load("res\\escolhaDeAdversario\\estrela3.png");
							
						}
					 break;
				}
			}
			
			// ------------------------ manda para a tela de batalha ----------------------- /
			else if(codigo == KeyEvent.VK_Z  && mostrarMenu == false && dialogoAviso.getImagem() == null && bntSimNao == true && barraDeDialogo.getImagem() != null) {
				
				int [] organizandoAventureiro = {2, 0, 3, 1, 4, 5};
				
				JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        tela3 = new Batalha(aventureiro, organizandoAventureiro[contTeclaBatalha], this);
		        janelaPrincipal.add(tela3);
		        janelaPrincipal.setTitle("Batalha");
		        janelaPrincipal.revalidate();
		        
		        limparDialogo();
				
		        contTeclaBatalha = 0;
		        limpaIconesAdversarios();
		        iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
			}
		
		// ------------------------ manda para a tela de batalha ----------------------- /
		} else {
			tela3.KeyPressed(tecla);
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
		bntSimDialogo.setImagem(null);
		
		objetoDeFundo1.setX(0); objetoDeFundo1.setY(0);
		objetoDeFundo2.setX(0); objetoDeFundo2.setY(0);
		objetoDeFundo1.setImagem(null);
		objetoDeFundo2.setImagem(null);
		
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
			if(derrotados[0] == true) {vencido0.load("res\\escolhaDeAdversario\\estrela.png");} 
			if(derrotados[1] == true) {vencido1.load("res\\escolhaDeAdversario\\estrela.png");} 
			if(derrotados[2] == true) {vencido2.load("res\\escolhaDeAdversario\\estrela.png");} 
			if(derrotados[3] == true) {vencido3.load("res\\escolhaDeAdversario\\estrela.png");} 
			if(derrotados[4] == true) {vencido4.load("res\\escolhaDeAdversario\\estrela.png");} 

		if(derrotados[0] != false && derrotados[1] != false && derrotados[2] != false && derrotados[3] != false && derrotados[4] != false) {
			iconeBoss.load("res\\escolhaDeAdversario\\boxDestrancado.png");
		}
	}
	
	public void paint(Graphics g) {
		
		redimLarg = this.getWidth()/2 - 1234/2;
		redimAlt = this.getHeight()/2 - 640/2;
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), redimLarg + fundo2.getX(), redimAlt + fundo2.getY(), this);
		
		graficos.drawImage(iconeBoss.getImagem(), redimLarg + iconeBoss.getX(), redimAlt + iconeBoss.getY(), this);
		graficos.drawImage(iconeIgnis.getImagem(), redimLarg + iconeIgnis.getX(),redimAlt + iconeIgnis.getY(), this);
		graficos.drawImage(iconeAyla.getImagem(), redimLarg + iconeAyla.getX(), redimAlt + iconeAyla.getY(), this);
		graficos.drawImage(iconeRexthor.getImagem(), redimLarg + iconeRexthor.getX(), redimAlt + iconeRexthor.getY(), this);
		graficos.drawImage(iconeKiki.getImagem(), redimLarg + iconeKiki.getX(), redimAlt + iconeKiki.getY(), this);
		graficos.drawImage(iconeArius.getImagem(), redimLarg + iconeArius.getX(), redimAlt + iconeArius.getY(), this);
		
		graficos.drawImage(vencido0.getImagem(), redimLarg + vencido0.getX(), redimAlt + vencido0.getY(), this);
		graficos.drawImage(vencido1.getImagem(), redimLarg + vencido1.getX(), redimAlt + vencido1.getY(), this);
		graficos.drawImage(vencido2.getImagem(), redimLarg + vencido2.getX(), redimAlt + vencido2.getY(), this);
		graficos.drawImage(vencido3.getImagem(), redimLarg + vencido3.getX(), redimAlt + vencido3.getY(), this);
		graficos.drawImage(vencido4.getImagem(), redimLarg + vencido4.getX(), redimAlt + vencido4.getY(), this);
		
		graficos.drawImage(sombreadorDialogo.getImagem(), redimLarg + sombreadorDialogo.getX(), redimAlt + sombreadorDialogo.getY(), this);
		graficos.drawImage(objetoDeFundo1.getImagem(), redimLarg + objetoDeFundo1.getX(), redimAlt + objetoDeFundo1.getY(), this);
		graficos.drawImage(objetoDeFundo2.getImagem(), redimLarg + objetoDeFundo2.getX(), redimAlt + objetoDeFundo2.getY(), this);
		graficos.drawImage(imagemDoDialogo.getImagem(), redimLarg + imagemDoDialogo.getX(), redimAlt + imagemDoDialogo.getY(), this);
		graficos.drawImage(barraDeDialogo.getImagem(), redimLarg + barraDeDialogo.getX(), redimAlt + barraDeDialogo.getY(), this);
		
		graficos.setColor(txtDialogoLn1.getCorTexto());
		
		tl1 = new TextLayout(txtDialogoLn1.getTexto(), txtDialogoLn1.getFonte(), frc);
		tl2 = new TextLayout(txtDialogoLn2.getTexto(), txtDialogoLn2.getFonte(), frc);
		tl3 = new TextLayout(txtDialogoLn3.getTexto(), txtDialogoLn3.getFonte(), frc);
		tl4 = new TextLayout(txtDialogoLn4.getTexto(), txtDialogoLn4.getFonte(), frc);
		
		tl1.draw(graficos, redimLarg + txtDialogoLn1.getX(), redimAlt + txtDialogoLn1.getY());
		tl2.draw(graficos, redimLarg + txtDialogoLn2.getX(), redimAlt + txtDialogoLn2.getY());
		tl3.draw(graficos, redimLarg + txtDialogoLn3.getX(), redimAlt + txtDialogoLn3.getY());
		
		if(mudaCorLn4 == true) {graficos.setColor(txtDialogoLn4.getCorTexto());}
		tl4.draw(graficos, redimLarg + txtDialogoLn4.getX(), redimAlt + txtDialogoLn4.getY());
		if(mudaCorLn4 == true) {graficos.setColor(txtDialogoLn1.getCorTexto());}
		
		graficos.drawImage(bntSimDialogo.getImagem(), redimLarg + bntSimDialogo.getX(), redimAlt + bntSimDialogo.getY(), this);
		graficos.drawImage(bntNaoDialogo.getImagem(), redimLarg + bntNaoDialogo.getX(), redimAlt + bntNaoDialogo.getY(), this);
		graficos.drawImage(estrelaFim1.getImagem(), redimLarg + estrelaFim1.getX(), redimAlt + estrelaFim1.getY(), this);
		graficos.drawImage(estrelaFim2.getImagem(), redimLarg + estrelaFim2.getX(), redimAlt + estrelaFim2.getY(), this);
		
		graficos.drawImage(sombreadorMenu.getImagem(), redimLarg + sombreadorMenu.getX(), redimAlt + sombreadorMenu.getY(), this);
		graficos.drawImage(fundoMenu.getImagem(), redimLarg + fundoMenu.getX(), redimAlt + fundoMenu.getY(), this);
		graficos.drawImage(bntMenu.getImagem(), redimLarg + bntMenu.getX(), redimAlt + bntMenu.getY(), this);
		graficos.drawImage(bntRegras.getImagem(), redimLarg + bntRegras.getX(), redimAlt + bntRegras.getY(), this);
		graficos.drawImage(bntVoltar.getImagem(),  redimLarg + bntVoltar.getX(),  redimAlt + bntVoltar.getY(), this);
		
		graficos.drawImage(dialogoAviso.getImagem(), redimLarg + dialogoAviso.getX(), redimAlt + dialogoAviso.getY(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), redimLarg + bntSimDialogoAviso.getX(), redimAlt + bntSimDialogoAviso.getY(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), redimLarg + bntNaoDialogoAviso.getX(), redimAlt + bntNaoDialogoAviso.getY(), this);
		
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl5 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl6 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso2.getFonte(), frc);
		
		tl5.draw(graficos, redimLarg + txtDialogoAviso.getX(), redimAlt + txtDialogoAviso.getY());
		tl6.draw(graficos, redimLarg + txtDialogoAviso2.getX(), redimAlt + txtDialogoAviso2.getY());
		
		graficos.drawImage(contorno.getImagem(), redimLarg + contorno.getX(), redimAlt + contorno.getY(), this);
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
	}
}
