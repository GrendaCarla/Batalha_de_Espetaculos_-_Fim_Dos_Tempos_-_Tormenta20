package batalha_de_Espetaculos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
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
	Batalha tela3;
	private Escolha_de_personagem paginaAnterior;
	
	private Image fundo;
	private Icones_interativos contorno;
	private Icones_interativos fundo2;
	private Icones_interativos iconeBoss;
	private Icones_interativos iconeIgnis;
	private Icones_interativos iconeAyla;
	private Icones_interativos iconeRexthor;
	private Icones_interativos iconeKiki;
	private Icones_interativos iconeArius;
	
	private Ignis ignis = new Ignis();
	private Ayla ayla = new Ayla();
	private Rexthor rexthor = new Rexthor();
	private Kiki kiki = new Kiki();
	private Arius arius = new Arius();
	
	private Icones_interativos barraDeDialogo;
	private Icones_interativos imagemDoDialogo;
	private Icones_interativos objetoDeFundo1;
	private Icones_interativos objetoDeFundo2;
	
	Texto txtDialogoLn1 = new Texto(100, 640 - 185 + 15, " ");
	Texto txtDialogoLn2 = new Texto(100, 640 - 185 + 50, " ");
	Texto txtDialogoLn3 = new Texto(100, 640 - 185 + 85, " ");
	Texto txtDialogoLn4 = new Texto(100, 640 - 185 + 120, " ");
	
	private Icones_interativos vencido0;
	private Icones_interativos vencido1;
	private Icones_interativos vencido2;
	private Icones_interativos vencido3;
	private Icones_interativos vencido4;
	
	private int contTecla = 0;
	private int aventureiro;
	int [] derrotados = {-1, -1, -1, -1, -1};
	private boolean ativarBox = false;
	private int contDialogo = 0;
	private boolean mudaCorLn4 = false;
	private boolean coroa = true;
	private Random aleatorio = new Random();
	
	private TextLayout tl1, tl2, tl3, tl4, tl8;
	
	private Icones_interativos sombreadorDialogo = new Icones_interativos(0, 0);
	private Icones_interativos dialogo = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2);
	
	private Icones_interativos bntSimDialogo = new Icones_interativos(1234/2 - 484/2, 640 - 50 - 40);
	private Icones_interativos bntNaoDialogo  = new Icones_interativos(1234/2 - 484/2 + 370, 640 - 50 - 40);

	Texto txtMenVoltar = new Texto(1234/2 - 706/2 + 110, 548/2 - 28, " ");
	Texto txtMenVoltar2 = new Texto(1234/2 - 706/2 + 250, 548/2 + 52, " ");
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,0);
	private Icones_interativos bntMenu = new Icones_interativos(41,66);
	private Icones_interativos bntRegras = new Icones_interativos(41,202);
	private Icones_interativos bntVoltar = new Icones_interativos(41,338);
	
	private Icones_interativos bntSimMenu = new Icones_interativos(1234/2 - 706/2 + 110, 640/2 - 278/2 + 190);
	private Icones_interativos bntNaoMenu  = new Icones_interativos(1234/2 - 706/2 + 480, 640/2 - 278/2 + 190);
	
	private Icones_interativos estrelaFim1 = new Icones_interativos(10, 413);
	private Icones_interativos estrelaFim2 = new Icones_interativos(1234 - 196, 412);

	
	private Boolean botaoSimNao = true;
	private Boolean botaoSimNaoMenu = true;
	boolean mostrarMenu = false;
	int contMenu = 0;
	
	private Timer timer;
	
	public Escolha_de_adversario(int numAventureiro, Escolha_de_personagem PaginaAnterior) {
		aventureiro = numAventureiro;
		this.paginaAnterior = PaginaAnterior;
		
		ImageIcon referencia = new ImageIcon("res\\fundoPreto.png");
		fundo = referencia.getImage();
		
		contorno = new Icones_interativos(0, 0);
		contorno.load("res\\contorno.png");
		
		fundo2 = new Icones_interativos(0,0);
		fundo2.load("res\\escolhaDeAdversario\\fundo.png");
		
		txtMenVoltar.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtMenVoltar.setCorTexto(new Color (235, 230, 233));
		txtMenVoltar2.setFonte(new Font("Arial", Font.PLAIN, 28));
		
		txtDialogoLn1.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn1.setCorTexto(new Color (235, 230, 233));
		txtDialogoLn2.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn3.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtDialogoLn4.setCorTexto(new Color (239, 22, 109));
		txtDialogoLn4.setFonte(new Font("Arial", Font.PLAIN, 24));
		
		iconeBoss = new Icones_interativos(510, 16);
		iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado.png");
		iconeIgnis = new Icones_interativos(240, 200);
		iconeIgnis.load("res\\escolhaDeAdversario\\ignis.png");
		iconeAyla = new Icones_interativos(785, 200);
		iconeAyla.load("res\\escolhaDeAdversario\\ayla.png");
		iconeRexthor = new Icones_interativos(45, 400);
		iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
		iconeKiki = new Icones_interativos(500, 400);
		iconeKiki.load("res\\escolhaDeAdversario\\kiki.png");
		iconeArius = new Icones_interativos(980, 400);
		iconeArius.load("res\\escolhaDeAdversario\\arius.png");
		
		barraDeDialogo = new Icones_interativos(0, 640 - 220);
		imagemDoDialogo = new Icones_interativos(1234/2 - 248/2, 20);
		objetoDeFundo1 = new Icones_interativos(0,0);
		objetoDeFundo2 = new Icones_interativos(0,0);
		
		
		vencido0 = new Icones_interativos(240 + 100 - 31, 200 - 20);
		vencido1 = new Icones_interativos(785 + 100 - 31, 200 + 15);
		vencido2 = new Icones_interativos(45 + 100 - 31, 400 - 26);
		vencido3 = new Icones_interativos(500 + 100 - 31, 400 - 38);
		vencido4 = new Icones_interativos(980 + 100 - 31, 400 - 18);
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void dialogoVoltar(int codigo) {
		if(dialogo.getImagem() == null && codigo == KeyEvent.VK_Z) {
			
			dialogo.load("res\\dialogo.png");
			bntSimMenu.load("res\\bntSim.png");
			bntNaoMenu.load("res\\bntNao2.png");
			botaoSimNaoMenu = true;
			
			txtMenVoltar.setTexto("Se você voltar perderá o seu progreço.");
			txtMenVoltar2.setTexto("Deseja continuar?");
			
		}else if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT && dialogo.getImagem() != null) {
			dialogo.load("res\\dialogo.png");
			botaoSimNaoMenu = !botaoSimNaoMenu;
			
			if(botaoSimNaoMenu == true) {
				bntSimMenu.load("res\\bntSim.png");
				bntNaoMenu.load("res\\bntNao2.png");
			}else {
				bntSimMenu.load("res\\bntSim2.png");
				bntNaoMenu.load("res\\bntNao.png");
			}
			
		}else if(codigo == KeyEvent.VK_Z  || codigo == KeyEvent.VK_X && dialogo.getImagem() != null) {
			
			if(botaoSimNaoMenu == false || codigo == KeyEvent.VK_X) {
				dialogo.setImagem(null);
				bntSimMenu.load(null);
				bntNaoMenu.load(null);
				
				txtMenVoltar.setTexto(" ");
				txtMenVoltar2.setTexto(" ");
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
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		if(janela != null && janela.getTitle() == "Escolha de Adversário") {
			int codigo = tecla.getKeyCode();
			
			if(codigo == KeyEvent.VK_ESCAPE && dialogo.getImagem() == null) {
				mostrarMenu = !mostrarMenu;
				
				if(mostrarMenu == true) {
					contMenu = 0;
					sombreadorMenu.load("res\\sombreador.png");
					fundoMenu.load("res\\menu.png");
					bntMenu.load("res\\bntMenu2.png");
					bntRegras.load("res\\bntRegras1.png");
					bntVoltar.load("res\\bntVoltar1.png");
					
				} else {
					sombreadorMenu.load("");
					fundoMenu.load("");
					bntMenu.load("");
					bntRegras.load("");
					bntVoltar.load("");
					
				}
			}else if(codigo == KeyEvent.VK_UP && mostrarMenu == true && dialogo.getImagem() == null) {
				if(contMenu == 0) {
					contMenu = 2;
				} else {
					contMenu --;
				}
				
				switch (contMenu) {
					case 0:
						bntMenu.load("res\\bntMenu2.png");
						bntRegras.load("res\\bntRegras1.png");
						break;
					case 1:
						bntRegras.load("res\\bntRegras2.png");
						bntVoltar.load("res\\bntVoltar1.png");
						break;
					case 2:
						bntVoltar.load("res\\bntVoltar2.png");
						bntMenu.load("res\\bntMenu1.png");
						break;
				}
			}else if(codigo == KeyEvent.VK_DOWN && mostrarMenu == true &&  dialogo.getImagem() == null) {
				if(contMenu == 2) {
					contMenu = 0;
				} else {
					contMenu ++;
				}
				
				switch (contMenu) {
					case 0:
						bntMenu.load("res\\bntMenu2.png");
						bntVoltar.load("res\\bntVoltar1.png");
						break;
					case 1:
						bntRegras.load("res\\bntRegras2.png");
						bntMenu.load("res\\bntMenu1.png");
						break;
					case 2:
						bntVoltar.load("res\\bntVoltar2.png");
						bntRegras.load("res\\bntRegras1.png");
						break;
				}
			}else if(((codigo == KeyEvent.VK_Z || codigo == KeyEvent.VK_X) && mostrarMenu == true && bntVoltar.getReferencia().toString() == "res\\bntVoltar2.png" ) || dialogo.getImagem() != null) {
				dialogoVoltar(codigo);
			
			} else if(codigo == KeyEvent.VK_LEFT && mostrarMenu == false && dialogo.getImagem() == null && barraDeDialogo.getImagem() == null) {
				
				if(contTecla == 0) {
					contTecla = 4;
				} else if(contTecla !=5){
					contTecla --;
				}
				
				switch (contTecla) {
					case 0:
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis.png");
						iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
					    break;
					case 1:
						iconeKiki.load("res\\escolhaDeAdversario\\kiki.png");
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis2.png");
						break;
					case 2:
						iconeAyla.load("res\\escolhaDeAdversario\\ayla.png");
						iconeKiki.load("res\\escolhaDeAdversario\\kiki2.png");
					    break;
					case 3:
						iconeArius.load("res\\escolhaDeAdversario\\arius.png");
						iconeAyla.load("res\\escolhaDeAdversario\\ayla2.png");
					    break;
					case 4:
						iconeRexthor.load("res\\escolhaDeAdversario\\rexthor.png");
						iconeArius.load("res\\escolhaDeAdversario\\arius2.png");
					    break;
					case 5:
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis2.png");
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false) {
							iconeBoss.load("res\\escolhaDeAdversario\\BoxDestrancado.png");
						}else {
							iconeBoss.load("res\\escolhaDeAdversario\\BoxTrancado.png");
						}
						
						contTecla = 1;
						break;
				}
					  
			}
			else if(codigo == KeyEvent.VK_RIGHT && mostrarMenu == false && dialogo.getImagem() == null && barraDeDialogo.getImagem() == null) {
				
				if(contTecla == 4) {
					contTecla = 0;
				} else if(contTecla != 5){
					contTecla ++;
				}
				
				switch (contTecla) {
					case 0:
						iconeArius.load("res\\escolhaDeAdversario\\arius.png");
						iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
					    break;
					case 1:
						iconeRexthor.load("res\\escolhaDeAdversario\\rexthor.png");
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis2.png");
					    break;
					case 2:
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis.png");
						iconeKiki.load("res\\escolhaDeAdversario\\kiki2.png");
					    break;
					case 3:
						iconeKiki.load("res\\escolhaDeAdversario\\kiki.png");
						iconeAyla.load("res\\escolhaDeAdversario\\ayla2.png");
					    break;
					case 4:
						iconeAyla.load("res\\escolhaDeAdversario\\ayla.png");
						iconeArius.load("res\\escolhaDeAdversario\\arius2.png");
					    break;
					case 5:
						iconeAyla.load("res\\escolhaDeAdversario\\ayla2.png");
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false) {
							iconeBoss.load("res\\escolhaDeAdversario\\BoxDestrancado.png");
						}else {
							iconeBoss.load("res\\escolhaDeAdversario\\BoxTrancado.png");
						}
						
						contTecla = 3;
						break;
				}
			}
			else if(codigo == KeyEvent.VK_UP && mostrarMenu == false && dialogo.getImagem() == null && barraDeDialogo.getImagem() == null) {
				switch (contTecla) {
					case 0:
						iconeRexthor.load("res\\escolhaDeAdversario\\rexthor.png");
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis2.png");
						contTecla = 1;
					    break;
					case 1:
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis.png");
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false){
							iconeBoss.load("res\\escolhaDeAdversario\\boxDesTrancado2.png");
						} else {iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado2.png");}
						
						contTecla = 5;
					    break;
					case 2:
						iconeKiki.load("res\\escolhaDeAdversario\\kiki.png");
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false){
							iconeBoss.load("res\\escolhaDeAdversario\\boxDesTrancado2.png");
						} else {iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado2.png");}
						
						contTecla = 5;
					    break;
					case 3:
						iconeAyla.load("res\\escolhaDeAdversario\\ayla.png");
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false){
							iconeBoss.load("res\\escolhaDeAdversario\\boxDesTrancado2.png");
						} else {iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado2.png");}
						
						contTecla = 5;
					    break;
					case 4:
						iconeArius.load("res\\escolhaDeAdversario\\arius.png");
						iconeAyla.load("res\\escolhaDeAdversario\\ayla2.png");
						contTecla = 3;
					    break;
					case 5:
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false) {
							iconeBoss.load("res\\escolhaDeAdversario\\boxDestrancado.png");
						}else {
							iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado.png");
						}
						
						iconeKiki.load("res\\escolhaDeAdversario\\kiki2.png");
						contTecla = 2;
					    break;
				}
			}
			else if(codigo == KeyEvent.VK_DOWN && mostrarMenu == false && dialogo.getImagem() == null && barraDeDialogo.getImagem() == null) {
				switch (contTecla) {
					case 0:
						iconeRexthor.load("res\\escolhaDeAdversario\\rexthor.png");
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false){
							iconeBoss.load("res\\escolhaDeAdversario\\boxDesTrancado2.png");
						} else {iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado2.png");}
						
						contTecla = 5;
					    break;
					case 1:
						iconeIgnis.load("res\\escolhaDeAdversario\\ignis.png");
						iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
						contTecla = 0;
					    break;
					case 2:
						iconeKiki.load("res\\escolhaDeAdversario\\kiki.png");
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false){
							iconeBoss.load("res\\escolhaDeAdversario\\boxDesTrancado2.png");
						} else {iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado2.png");}
						
						contTecla = 5;
					    break;
					case 3:
						iconeAyla.load("res\\escolhaDeAdversario\\ayla.png");
						iconeArius.load("res\\escolhaDeAdversario\\arius2.png");
						contTecla = 4;
					    break;
					case 4:
						iconeArius.load("res\\escolhaDeAdversario\\arius.png");
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false){
							iconeBoss.load("res\\escolhaDeAdversario\\boxDesTrancado2.png");
						} else {iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado2.png");}
						
						contTecla = 5;
					    break;
					case 5:
						if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1 && ativarBox == false) {
							iconeBoss.load("res\\escolhaDeAdversario\\boxDestrancado.png");
						}else {
							iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado.png");
						}
						
						iconeKiki.load("res\\escolhaDeAdversario\\kiki2.png");
						contTecla = 2;
					    break;
				}
			}
			else if((codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_LEFT) && mostrarMenu == false && dialogo.getImagem() == null && barraDeDialogo.getImagem() != null && bntSimDialogo.getImagem() != null) {
				
				if(contTecla == 0 && contDialogo == 3) {
					coroa = !coroa;
					
					if(coroa == true) {
						bntSimDialogo.load("res\\escolhaDeAdversario\\tibarCoroa3.png");
						bntNaoDialogo.load("res\\escolhaDeAdversario\\tibarSemCoroa2.png");
					} else {
						bntSimDialogo.load("res\\escolhaDeAdversario\\tibarCoroa2.png");
						bntNaoDialogo.load("res\\escolhaDeAdversario\\tibarSemCoroa3.png");
					}
				} else {
					botaoSimNao = !botaoSimNao;
				
					if(botaoSimNao == true) {
						bntSimDialogo.load("res\\bntSim.png");
						bntNaoDialogo.load("res\\bntNao2.png");
					} else {
						bntSimDialogo.load("res\\bntSim2.png");
						bntNaoDialogo.load("res\\bntNao.png");
					}
				}
			}
			else if(codigo == KeyEvent.VK_Z && (botaoSimNao == false || (contTecla == 0 && contDialogo == 4 && mudaCorLn4 == false) || (contTecla == 5 && contDialogo == 1)) && mostrarMenu == false && dialogo.getImagem() == null && barraDeDialogo.getImagem() != null && bntSimDialogo.getImagem() == null  && ((contTecla == 0 && contDialogo == 2 && botaoSimNao == false) || (contTecla == 0 && contDialogo == 4 && mudaCorLn4 == false) || (contTecla == 1 && contDialogo == 2) || (contTecla == 2 && contDialogo == 3) || (contTecla == 3 && contDialogo == 3) || (contTecla == 4 && contDialogo == 3) || (contTecla == 1 && contDialogo == 3) || (contTecla == 5 && contDialogo == 1))) {
				limparDialogo();
			}
			else if(codigo == KeyEvent.VK_Z && mostrarMenu == false && dialogo.getImagem() == null && ((contDialogo < 3 && !((contTecla == 2 && botaoSimNao == true) || contTecla == 1)) || (contTecla == 0 && contDialogo == 3) || (contTecla == 3 && ( contDialogo == 3 || contDialogo == 4)) || (contTecla == 2 && contDialogo < 2)  || (contTecla == 1 && contDialogo < 2))) {
				
				sombreadorDialogo.load("res\\sombreador.png");
				barraDeDialogo.load("res\\escolhaDeAdversario\\barraDeDialogo.png");
				contDialogo++;
				
				switch (contTecla) {
					case 0:
						if(contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 400/2 - 30);
							imagemDoDialogo.setY(70);
							objetoDeFundo1.setY(90);
							objetoDeFundo1.setX(30);
							objetoDeFundo2.setY(200);
							objetoDeFundo2.setX(880);
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
							
							if(botaoSimNao == true){
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
							
							boolean tibar = aleatorio.nextInt(2)== 0 ? true : false;
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\" + (tibar == true ? "tibarCoroa" : "tibarSemCoroa") + ".png");
							
							if(coroa == tibar){
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
					case 1:
						if(contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 500/2 - 100);
							objetoDeFundo2.setX(450);
							objetoDeFundo1.setY(20);
							objetoDeFundo1.setX(20);
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
							
							if(botaoSimNao == true){
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
					case 2:
						if(contDialogo == 1) {
							imagemDoDialogo.setX(1234/2 - 248/2);
							
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
							
							if(botaoSimNao == true){
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
						} else if(contDialogo == 3 && botaoSimNao == false){
							imagemDoDialogo.load("res\\escolhaDeAdversario\\imagemKiki2.png");
							
							txtDialogoLn1.setTexto(kiki.getConteudoEscolhaAdversario()[2][0]);
							txtDialogoLn2.setTexto(kiki.getConteudoEscolhaAdversario()[2][1]);
							txtDialogoLn3.setTexto(kiki.getConteudoEscolhaAdversario()[2][2]);
							txtDialogoLn4.setTexto(kiki.getConteudoEscolhaAdversario()[2][3]);
						}
						
					    break;
					case 3:
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
							
							if(botaoSimNao == true) {
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
					case 4:
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
							
							if(botaoSimNao == true) {
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
					case 5:
						imagemDoDialogo.setX(1234/2 - 400/2);
						imagemDoDialogo.setY(10);
						
						if(derrotados[0] != 1 || derrotados[1] != 1 || derrotados[2] != 1 || derrotados[3] != 1 || derrotados[4] != 1) {
							
							txtDialogoLn2.setX(470);
							txtDialogoLn3.setX(312);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\cadeadoGrande1.png");
							
							txtDialogoLn1.setTexto(" ");
							txtDialogoLn2.setTexto("Esta batalha está trancada!");
							txtDialogoLn3.setTexto("Vença primeiro os 5 Cães das Colinas antes de proceguir");
							txtDialogoLn4.setTexto(" ");
						} else {
							txtDialogoLn1.setX(184);
							txtDialogoLn2.setX(296);
							txtDialogoLn4.setX(290);
							
							imagemDoDialogo.load("res\\escolhaDeAdversario\\cadeadoGrande2.png");
							
							txtDialogoLn1.setTexto("Parabéns!!! você conseguil vencer os 5 Cães das Colinas, infelismente o jogo não");
							txtDialogoLn2.setTexto("esta completo ainda e você terá que esperar um pouco mais.");
							txtDialogoLn3.setTexto(" ");
							txtDialogoLn4.setTexto("Muito obrigado por ter jogado e espero que tenha se divertido.");
							
							estrelaFim1.load("res\\escolhaDeAdversario\\estrela2.png");
							estrelaFim2.load("res\\escolhaDeAdversario\\estrela3.png");
							
						}
					 break;
				}
			
				
			}
			else if(codigo == KeyEvent.VK_Z && contTecla != 5 && mostrarMenu == false && dialogo.getImagem() == null && botaoSimNao == true && barraDeDialogo.getImagem() != null) {
				
				int [] organizandoAventureiro = {2, 0, 3, 1, 4, 5};
				
				JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        tela3 = new Batalha(aventureiro, organizandoAventureiro[contTecla], this);
		        janelaPrincipal.add(tela3);
		        janelaPrincipal.setTitle("Batalha");
		        janelaPrincipal.revalidate();
		        
		        limparDialogo();
				
		        contTecla = 0;
				iconeBoss.load("res\\escolhaDeAdversario\\boxTrancado.png");
				iconeIgnis.load("res\\escolhaDeAdversario\\ignis.png");
				iconeAyla.load("res\\escolhaDeAdversario\\ayla.png");
				iconeRexthor.load("res\\escolhaDeAdversario\\rexthor2.png");
				iconeKiki.load("res\\escolhaDeAdversario\\kiki.png");
				iconeArius.load("res\\escolhaDeAdversario\\arius.png");
			}
			
			
		} else {
			tela3.KeyPressed(tecla);
		}
	}
	
	public void KeyReleased (java.awt.event.KeyEvent tecla){
		
	}
	
	public void limparDialogo() {
		sombreadorDialogo.setImagem(null);
		barraDeDialogo.setImagem(null);
		imagemDoDialogo.setImagem(null);
		imagemDoDialogo.setY(20);
		imagemDoDialogo.setX(1234/2 - 248/2);
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
		bntSimDialogo.setImagem(null);
		bntNaoDialogo.setImagem(null);
		contDialogo = 0;
		estrelaFim1.setImagem(null);
		estrelaFim2.setImagem(null);
		botaoSimNao = true;
		coroa = true;
		mudaCorLn4 = false;
		
		bntSimDialogo.setY(640 - 50 - 40);
		bntNaoDialogo.setY(640 - 50 - 40);
	}
	
	
	public void mostrarEstrela() {
		for(int i=0; i<5; i++) {
			if(derrotados[i] == 0) {
				vencido0.load("res\\escolhaDeAdversario\\estrela.png");
			} else if(derrotados[i] == 1) {
				vencido1.load("res\\escolhaDeAdversario\\estrela.png");
			} else if(derrotados[i] == 2) {
				vencido2.load("res\\escolhaDeAdversario\\estrela.png");
			} else if(derrotados[i] == 3) {
				vencido3.load("res\\escolhaDeAdversario\\estrela.png");
			} else if(derrotados[i] == 4) {
				vencido4.load("res\\escolhaDeAdversario\\estrela.png");
			}
		}
		
		if(derrotados[0] != -1 && derrotados[1] != -1 && derrotados[2] != -1 && derrotados[3] != -1 && derrotados[4] != -1) {
			iconeBoss.load("res\\escolhaDeAdversario\\boxDestrancado.png");
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(),fundo2.getY(), this);
		graficos.drawImage(iconeBoss.getImagem(), iconeBoss.getX(), iconeBoss.getY(), this);
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getX(),iconeIgnis.getY(), this);
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
		
		graficos.drawImage(sombreadorMenu.getImagem(), sombreadorMenu.getX(), sombreadorMenu.getY(), this);
		graficos.drawImage(fundoMenu.getImagem(), fundoMenu.getX(), fundoMenu.getY(), this);
		graficos.drawImage(bntMenu.getImagem(), bntMenu.getX(), bntMenu.getY(), this);
		graficos.drawImage(bntRegras.getImagem(), bntRegras.getX(), bntRegras.getY(), this);
		graficos.drawImage(bntVoltar.getImagem(),  bntVoltar.getX(),  bntVoltar.getY(), this);
		
		
		graficos.drawImage(dialogo.getImagem(), dialogo.getX(), dialogo.getY(), this);
		graficos.drawImage(bntSimMenu.getImagem(), bntSimMenu.getX(), bntSimMenu.getY(), this);
		graficos.drawImage(bntNaoMenu.getImagem(), bntNaoMenu.getX(), bntNaoMenu.getY(), this);
		
		
		graficos.setColor( txtMenVoltar.getCorTexto());
		tl3 = new TextLayout(txtMenVoltar.getTexto(), txtMenVoltar.getFonte(), frc);
		tl8 = new TextLayout(txtMenVoltar2.getTexto(), txtMenVoltar2.getFonte(), frc);
		
		tl3.draw(graficos, txtMenVoltar.getX(), txtMenVoltar.getY());
		tl8.draw(graficos, txtMenVoltar2.getX(), txtMenVoltar2.getY());
		
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
