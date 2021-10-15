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
	JFrame janelaPrincipal;
	boolean novoJogo;
	
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	
	// ------------------------ imagens dos ícones aventureiros ------------------------------
	
	private Icones_interativos iconeBoss = new Icones_interativos(510, 16);
	private Icones_interativos iconeIgnis = new Icones_interativos(240, 200);
	private Icones_interativos iconeAyla = new Icones_interativos(785, iconeIgnis.getY());
	private Icones_interativos iconeRexthor = new Icones_interativos(45, 400);
	private Icones_interativos iconeKiki = new Icones_interativos(500, iconeRexthor.getY());
	private Icones_interativos iconeArius = new Icones_interativos(980, iconeRexthor.getY());
	
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
	private Icones_interativos objetoDeFundo3 = new Icones_interativos(0,0);
	private Icones_interativos objetoDeFundo4 = new Icones_interativos(0,0);
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
	
	// ------------------------ imagens de estrelas para batalhas vencidas ------------------------------

	private Icones_interativos vencido0 = new Icones_interativos(iconeIgnis.getX() + 100 - 49, iconeIgnis.getY() - 40);
	private Icones_interativos vencido1 = new Icones_interativos(iconeAyla.getX() + 100 - 49, iconeAyla.getY() + 10);
	private Icones_interativos vencido2 = new Icones_interativos(iconeRexthor.getX() + 100 - 40, iconeRexthor.getY() - 50);
	private Icones_interativos vencido3 = new Icones_interativos(iconeKiki.getX() + 100 - 50, iconeKiki.getY() - 55);
	private Icones_interativos vencido4 = new Icones_interativos(iconeArius.getX() + 100 - 46, iconeArius.getY() - 40);
	
	
	private boolean [] derrotados = {false, false, false, false, false}; //{true, true, true, true, true}; // 
	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------
	
	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 110, dialogoAviso.getY() + 190);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(dialogoAviso.getX() + 480, bntSimDialogoAviso.getY());
	
	
	private Texto txtDialogoAviso = new Texto(1234/2 - 706/2 + 110, 548/2 - 28, " ");
	private Texto txtDialogoAviso2 = new Texto(1234/2 - 706/2 + 250, 548/2 + 52, " ");
	
	private Boolean bntSimNaoDialgoAviso = true;
	
	// ------------------------------------- imagens do menu ---------------------------------------
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(41,66);
	private Icones_interativos bntManual = new Icones_interativos(bntMenu.getX(),202);
	private Icones_interativos bntVoltar = new Icones_interativos(bntMenu.getX(),338);
	
	private boolean mostrarMenu = false;
	private int contMenu = 0;
	
	// -------------------- imagens de decoração do diálogo com o boss ---------------------
	
	private Icones_interativos estrelaFim1 = new Icones_interativos(10, 413);
	private Icones_interativos estrelaFim2 = new Icones_interativos(1234 - 196, 412);

	// ---------------------------- save ------------------------------------

	Salvar salvar = new Salvar();
	private Icones_interativos imgMenSalve = new Icones_interativos(-242, 34);
	private Icones_interativos imgMenSalve2 = new Icones_interativos(0, 34);
	private Texto txtSalvar = new Texto(84, 90, " ");
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
	
	private Timer timer;
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	
	public Escolha_de_adversario(int numAventureiro, Escolha_de_personagem PaginaAnterior, Menu PaginaMenu, boolean [] Derrotados, boolean NovoJogo) {
		
		aventureiro = numAventureiro;
		this.tela1 = PaginaAnterior;
		this.telaMenu = PaginaMenu;
		this.derrotados = Derrotados;
		this.novoJogo = NovoJogo;
		
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
		
		txtSalvar.setFonte(new Font("Arial", Font.BOLD, 20));
		txtSalvar.setCorTexto(new Color (255, 255, 255));
		
		contMenSalvar = 0;
		SalvarJogo();
		
		timer = new Timer(5, this);
		timer.start();
		
	}
	
	public void setDerrotados(int posicao, boolean derrotados) {
		this.derrotados[posicao] = derrotados;
	}
	
	public boolean[] getDerrotados() {
		return derrotados;
	}
	
	public void SalvarJogo() {
		resutado = salvar.SalvarDados(derrotados, aventureiro);
		contMenSalvar ++;
		
		if(resutado == 1) {
			imgMenSalve.load("res\\EscolhaDeAdversario\\salvar1.png");
			imgMenSalve2.load("res\\EscolhaDeAdversario\\salvar2.png");
			
		} else {
			sombreadorSaveAviso.load("res\\Menu\\sombreador.png");
			saveAviso.load("res\\Menu\\dialogo.png");
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
		if(codigo == KeyEvent.VK_Z ) {
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        janelaPrincipal.add(telaMenu);
	        janelaPrincipal.setTitle("Menu");
	        telaMenu.valorLeituraSave = salvar.LerDados();
	        telaMenu.Restaurar();
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
			
			janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
	        janelaPrincipal.remove(this);
	        telaManual = new Manual();
	        telaManual.setTela2(this);
	        janelaPrincipal.add(telaManual);
	        janelaPrincipal.setTitle("Manual2");
	        janelaPrincipal.revalidate();
	        
		}
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  									volta para tela anterior								|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntVoltar(int codigo) {
		if(dialogoAviso.getImagem() == null && codigo == KeyEvent.VK_Z) {
			
			dialogoAviso.load("res\\Menu\\dialogo.png");
			bntSimDialogoAviso.load("res\\Menu\\bntSim.png");
			bntNaoDialogoAviso.load("res\\Menu\\bntNao2.png");
			bntSimNaoDialgoAviso = true;
			
			txtDialogoAviso.setTexto("Se você voltar perderá o seu progreço.");
			txtDialogoAviso2.setTexto("Deseja continuar?");
			
		}else if ((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoAviso.getImagem() != null) {
		
			bntSimNaoDialgoAviso = !bntSimNaoDialgoAviso;
			bntSimDialogoAviso.load("res\\Menu\\bntSim" + (bntSimNaoDialgoAviso == true ? "" : "2") + ".png");
			bntNaoDialogoAviso.load("res\\Menu\\bntNao" + (bntSimNaoDialgoAviso == true ? "2" : "") + ".png");
		
		}else if(codigo == KeyEvent.VK_Z  || codigo == KeyEvent.VK_X && dialogoAviso.getImagem() != null) {
			
			if(bntSimNaoDialgoAviso == false || codigo == KeyEvent.VK_X) {
				dialogoAviso.setImagem(null);
				bntSimDialogoAviso.load(null);
				bntNaoDialogoAviso.load(null);
				
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
			        telaMenu.valorLeituraSave = salvar.LerDados();
			        telaMenu.chamarTela1(true);
			        telaMenu.Restaurar();
			        janelaPrincipal.revalidate();
			        timer.stop();
			        
		        }
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
				if(codigo == KeyEvent.VK_ESCAPE && dialogoAviso.getImagem() == null ) {
					mostrarMenu = !mostrarMenu;
					
					if(mostrarMenu == true) {
						contMenu = 0;
						sombreadorMenu.load("res\\Menu\\sombreador.png");
						fundoMenu.load("res\\Menu\\menu.png");
						bntMenu.load("res\\Menu\\bntMenu2.png");
						bntManual.load("res\\Menu\\bntManual1.png");
						bntVoltar.load("res\\Menu\\bntVoltar1.png");
						
					} else {
						sombreadorMenu.setImagem(null);
						fundoMenu.setImagem(null);
						bntMenu.setImagem(null);
						bntManual.setImagem(null);
						bntVoltar.setImagem(null);
						
					}
				// ----------------------- muda a seleção das opções do menu -------------------------- \
				}else if((codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) && mostrarMenu == true && dialogoAviso.getImagem() == null) {
					if(codigo == KeyEvent.VK_UP) {
						if(contMenu == 0) {contMenu = 2;} else {contMenu --;}
					}else if(codigo == KeyEvent.VK_DOWN) {
						if(contMenu == 2) {contMenu = 0;} else {contMenu ++;}
					}
					
					bntMenu.load("res\\Menu\\bntMenu1.png");
					bntManual.load("res\\Menu\\bntManual1.png");
					bntVoltar.load("res\\Menu\\bntVoltar1.png");
					
					switch (contMenu) {
						case 0:
							bntMenu.load("res\\Menu\\bntMenu2.png");
							break;
						case 1:
							bntManual.load("res\\Menu\\bntManual2.png");
							break;
						case 2:
							bntVoltar.load("res\\Menu\\bntVoltar2.png");
							break;
					}
					
				// ---------- encaminha para a função que controla o botão voltar do menu --------- \
				}else if(mostrarMenu == true && contMenu == 2) {
					dialogoBntVoltar(codigo);
					
				// ---------- encaminha para a função que controla o botão do Menu do menu ------\
				}else if(mostrarMenu == true && contMenu == 0){
					dialogoBntMenu(codigo);
				
				// ---------- encaminha para a função que controla o botão de Manual do menu ------\
				}else if(mostrarMenu == true && contMenu == 1){
					dialogoBntManual(codigo);
								
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
						bntSimDialogo.load("res\\Menu\\bntSim" + (bntSimNao == true? "" : "2") + ".png");
						bntNaoDialogo.load("res\\Menu\\bntNao" + (bntSimNao == true? "2" : "") + ".png");
						
					}
				}
				
				// --------------- quando o diálogo é fechado ele limpa as imagens e textos --------------- \
				else if(codigo == KeyEvent.VK_Z && mostrarMenu == false && dialogoAviso.getImagem() == null && barraDeDialogo.getImagem() != null && ((bntSimDialogo.getImagem() != null ? (bntSimNao == false && ((derrotados[2] == true  && contTeclaBatalha == 0  && contDialogo == 1) || (derrotados[0] == true  && contTeclaBatalha == 1  && contDialogo == 1) || (derrotados[3] == true  && contTeclaBatalha == 2  && contDialogo == 1) || (derrotados[1] == true  && contTeclaBatalha == 3  && contDialogo == 1) || (derrotados[4] == true  && contTeclaBatalha == 4  && contDialogo == 1)))  : (bntSimNao == false && ((contTeclaBatalha == 0 && contDialogo == 2) || (contTeclaBatalha == 1 && contDialogo == 2) || (contTeclaBatalha == 2 && contDialogo == 3) || (contTeclaBatalha == 3 && contDialogo == 3) || (contTeclaBatalha == 4 && contDialogo == 3))) || (contTeclaBatalha == 5 && contDialogo == 1)|| (contTeclaBatalha == 0 && contDialogo == 4 && mudaCorLn4 == false)))) {
					limparDialogo();
				}
				
				// ----------------------- diálogo com os cães das colinas ------------------------ \
				else if(codigo == KeyEvent.VK_Z && mostrarMenu == false && dialogoAviso.getImagem() == null && saveAviso.getImagem() == null && imgMenSalve.getImagem() == null && ((contTeclaBatalha == 0 && (derrotados[2] == true ? (contDialogo == 0) : (contDialogo < 4))) || (contTeclaBatalha == 1 && (derrotados[0] == true ? (contDialogo == 0) : (contDialogo < 2))) || (contTeclaBatalha == 2 && (derrotados[3] == true ? (contDialogo == 0) : (contDialogo < 2  || (contDialogo < 3 && bntSimNao == false)))) || (contTeclaBatalha == 3 && (derrotados[1] == true ? (contDialogo == 0) : (contDialogo < 5))) || (contTeclaBatalha == 4 && (derrotados[4] == true ? (contDialogo == 0) : (contDialogo < 3))) || (contTeclaBatalha == 5 && contDialogo < 1))) {
					
					sombreadorDialogo.load("res\\Menu\\sombreador.png");
					barraDeDialogo.load("res\\escolhaDeAdversario\\barraDeDialogo.png");
					contDialogo++;
					
					switch (contTeclaBatalha) {
						case 0: // Rexthor
							if(derrotados[2] == true && contDialogo == 1) {
								imagemDoDialogo.setY(69);
								imagemDoDialogo.setX(1234/2 - 310/2 - 29);
								objetoDeFundo1.setY(90); objetoDeFundo1.setX(40);
								objetoDeFundo2.setY(0); objetoDeFundo2.setX(900);
								
								txtDialogoLn2.setX(300);
								
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemRexthor2.png");
								objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoRexthor3.png");
								objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoRexthor4.png");
								
								txtDialogoLn1.setTexto(rexthor.getConteudoEscolhaAdversario()[6][0]);
								txtDialogoLn2.setTexto(rexthor.getConteudoEscolhaAdversario()[6][1]);
								txtDialogoLn3.setTexto(rexthor.getConteudoEscolhaAdversario()[6][2]);
								txtDialogoLn4.setTexto(rexthor.getConteudoEscolhaAdversario()[6][3]);
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
							} else if(contDialogo == 1) {
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
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
								
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
							if(contDialogo == 1 && derrotados[0] == true) {
								imagemDoDialogo.setX(1234/2 - 500/2 - 100);
								objetoDeFundo2.setX(450);
								
								txtDialogoLn2.setX(210);
								
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemIgnis.png");
								objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoIgnis2.png");
								
								txtDialogoLn1.setTexto(ignis.getConteudoEscolhaAdversario()[3][0]);
								txtDialogoLn2.setTexto(ignis.getConteudoEscolhaAdversario()[3][1]);
								txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[3][2]);
								txtDialogoLn3.setTexto(ignis.getConteudoEscolhaAdversario()[3][3]);
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
								
							} else if(contDialogo == 1) {
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
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
								
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
							if(contDialogo == 1 && derrotados[3] == true) {
								imagemDoDialogo.setX(1234/2 - 300/2);
								
								objetoDeFundo1.setX(20); objetoDeFundo1.setY(100);
								objetoDeFundo2.setX(1234 - 630 - 20); objetoDeFundo2.setY(30);
								txtDialogoLn2.setX(445);
								
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemKiki4.png");
								objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoKiki.png");
								objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoKiki2.png");
								
								txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[4][0]);
								txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[4][1]);
								txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[4][2]);
								txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[4][3]);
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
								
							} else if(contDialogo == 1) {
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
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
								
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
							if(contDialogo == 1 && derrotados[1] == true) {
								imagemDoDialogo.setX(1234/2 - 480/2);
								imagemDoDialogo.setY(34);
								
								objetoDeFundo1.setX(50); objetoDeFundo1.setY(60);
								objetoDeFundo2.setX(50); objetoDeFundo2.setY(50);
								objetoDeFundo3.setX(1234 - 250 - 50); objetoDeFundo3.setY(64);
								objetoDeFundo4.setX(1234 - 250 - 50); objetoDeFundo4.setY(54);
								
								ativarAnimacaoVelaAyla = 1;
								
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemAyla3.png");
								objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoAyla10.png");
								objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoAyla11.png");
								objetoDeFundo3.load("res\\escolhaDeAdversario\\objetoAyla20.png");
								objetoDeFundo4.load("res\\escolhaDeAdversario\\objetoAyla21.png");
								
								txtDialogoLn2.setX(365);
								
								txtDialogoLn1.setTexto(ayla.getConteudoEscolhaAdversario()[6][0]);
								txtDialogoLn2.setTexto(ayla.getConteudoEscolhaAdversario()[6][1]);
								txtDialogoLn3.setTexto(ayla.getConteudoEscolhaAdversario()[6][2]);
								txtDialogoLn4.setTexto(ayla.getConteudoEscolhaAdversario()[6][3]);
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
							}else if(contDialogo == 1) {
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
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
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
								
								objetoDeFundo1.setX(50); objetoDeFundo1.setY(60);
								objetoDeFundo2.setX(50); objetoDeFundo2.setY(50);
								objetoDeFundo3.setX(1234 - 250 - 50); objetoDeFundo3.setY(64);
								objetoDeFundo4.setX(1234 - 250 - 50); objetoDeFundo4.setY(54);
								
								ativarAnimacaoVelaAyla = 1;
								
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemAyla.png");
								
								objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoAyla10.png");
								objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoAyla11.png");
								objetoDeFundo3.load("res\\escolhaDeAdversario\\objetoAyla20.png");
								objetoDeFundo4.load("res\\escolhaDeAdversario\\objetoAyla21.png");
								
								txtDialogoLn2.setX(100);
								
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
							if(contDialogo == 1 && derrotados[4] == true) {
								imagemDoDialogo.setX(1234/2 - 462/2);
								imagemDoDialogo.setY(20);
								objetoDeFundo1.setX(16); objetoDeFundo1.setY(240);
								objetoDeFundo2.setX(1234 - 300 - 20 - 30); objetoDeFundo2.setY(30);
								
								txtDialogoLn2.setX(330);
	
								imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemArius.png");
								
								objetoDeFundo1.load("res\\escolhaDeAdversario\\objetoArius.png");
								objetoDeFundo2.load("res\\escolhaDeAdversario\\objetoArius2.png");
								
								txtDialogoLn1.setTexto(arius.getConteudoEscolhaAdversario()[4][0]);
								txtDialogoLn2.setTexto(arius.getConteudoEscolhaAdversario()[4][1]);
								txtDialogoLn3.setTexto(arius.getConteudoEscolhaAdversario()[4][2]);
								txtDialogoLn4.setTexto(arius.getConteudoEscolhaAdversario()[4][3]);
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
							} else if(contDialogo == 1) {
								
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
								
								bntSimDialogo.load("res\\Menu\\bntSim.png");
								bntNaoDialogo.load("res\\Menu\\bntNao2.png");
								
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
					
					janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
			        janelaPrincipal.remove(this);
			        tela3 = new Batalha(aventureiro, organizandoAventureiro[contTeclaBatalha], this, telaMenu);
			        janelaPrincipal.add(tela3);
			        janelaPrincipal.setTitle("Batalha");
			        janelaPrincipal.revalidate();
			        
			        limparDialogo();
					
			        contTeclaBatalha = 0;
			        limpaIconesAdversarios();
			        iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
				}
			
			
		} else{
			// ------------------------ manda para a tela de Manual ----------------------- /
			if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Manual2") {
				telaManual.KeyPressed(tecla);
			} else {
				tela3.KeyPressed(tecla);
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
		objetoDeFundo4.setX(0); objetoDeFundo4.setY(0);
		objetoDeFundo1.setImagem(null); objetoDeFundo2.setImagem(null);
		objetoDeFundo3.setImagem(null); objetoDeFundo4.setImagem(null);
		
		ativarAnimacaoVelaAyla = 0;
		
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
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		
		graficos.drawImage(iconeBoss.getImagem(), iconeBoss.getX(), iconeBoss.getY(), this);
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getX(), iconeIgnis.getY(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getX(), iconeAyla.getY(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getX(), iconeRexthor.getY(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getX(), iconeKiki.getY(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getX(), iconeArius.getY(), this);
		
		graficos.drawImage(vencido0.getImagem(), vencido0.getX(), vencido0.getY(), this);
		graficos.drawImage(vencido1.getImagem(), vencido1.getX(), vencido1.getY(), this);
		graficos.drawImage(vencido2.getImagem(), vencido2.getX(), vencido2.getY(), this);
		graficos.drawImage(vencido3.getImagem(), vencido3.getX(), vencido3.getY(), this);
		graficos.drawImage(vencido4.getImagem(), vencido4.getX(), vencido4.getY(), this);
		
		graficos.drawImage(sombreadorDialogo.getImagem(), sombreadorDialogo.getX(), sombreadorDialogo.getY(), this);
		graficos.drawImage(objetoDeFundo1.getImagem(), objetoDeFundo1.getX(), objetoDeFundo1.getY(), this);
		graficos.drawImage(objetoDeFundo2.getImagem(), objetoDeFundo2.getX(), objetoDeFundo2.getY(), this);
		graficos.drawImage(objetoDeFundo3.getImagem(), objetoDeFundo3.getX(), objetoDeFundo3.getY(), this);
		graficos.drawImage(objetoDeFundo4.getImagem(), objetoDeFundo4.getX(), objetoDeFundo4.getY(), this);
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
		
		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getX(), dialogoAviso.getY(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getX(), bntSimDialogoAviso.getY(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getX(), bntNaoDialogoAviso.getY(), this);
		
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl5 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl6 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso2.getFonte(), frc);
		
		tl5.draw(graficos, txtDialogoAviso.getX(), txtDialogoAviso.getY());
		tl6.draw(graficos, txtDialogoAviso2.getX(), txtDialogoAviso2.getY());
		
		
		
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		animarSave();
		
		if(ativarAnimacaoVelaAyla > 0) {
			animarVelas();
		}
		
		repaint();
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
				imgMenSalve2.load("res\\EscolhaDeAdversario\\salvar3.png");
			} else if(contMenSalvar == 34) {
				imgMenSalve2.setImagem(null);
			}
			
			if(contMenSalvar == 70) {
				imgMenSalve2.load("res\\EscolhaDeAdversario\\salvar3.png");
			}
			
			if(contMenSalvar == 72) {
				imgMenSalve2.load("res\\EscolhaDeAdversario\\salvar2.png");
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
			}
			
		} else {
			if(contMenSalvar == 100) {
				sombreadorSaveAviso.setImagem(null);
				saveAviso.setImagem(null);
				txtSaveAviso.setTexto(" ");
			}
		}
		
	}
	
	public void animarVelas() {
		
		contAnimacaoVelaAyla ++;
		
		if(ativarAnimacaoVelaAyla == 1 && contAnimacaoVelaAyla%2 == 0) {
			objetoDeFundo1.setY(objetoDeFundo1.getY() - 1);
			objetoDeFundo3.setY(objetoDeFundo3.getY() - 1);
			objetoDeFundo2.setY(objetoDeFundo2.getY() + 1);
			objetoDeFundo4.setY(objetoDeFundo4.getY() + 1);
		} else if(ativarAnimacaoVelaAyla == 2 && contAnimacaoVelaAyla%2 == 0) {
			objetoDeFundo1.setY(objetoDeFundo1.getY() + 1);
			objetoDeFundo3.setY(objetoDeFundo3.getY() + 1);
			objetoDeFundo2.setY(objetoDeFundo2.getY() - 1);
			objetoDeFundo4.setY(objetoDeFundo4.getY() - 1);
		}
		
		if(objetoDeFundo1.getY() == 60) {
			ativarAnimacaoVelaAyla = 2;
		} else if(objetoDeFundo1.getY() == 80) {
			ativarAnimacaoVelaAyla = 1;
		} 
		
	}
	
	public void chamarTela1() {
		
		janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
        janelaPrincipal.remove(this);
        janelaPrincipal.add(tela1);
        janelaPrincipal.setTitle("Escolha de Personagem");
        tela1.LimparTela2();
        janelaPrincipal.revalidate();
        
       
	}
	
	public void LimparTela3() {
		tela3 = null;
	}
	
}
