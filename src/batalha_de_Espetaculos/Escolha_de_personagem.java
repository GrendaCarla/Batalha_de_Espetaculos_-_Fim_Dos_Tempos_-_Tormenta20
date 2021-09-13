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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import batalha_de_Espetaculos.Modelo.Icones_interativos;
import batalha_de_Espetaculos.Modelo.Texto;

public class Escolha_de_personagem extends JPanel implements ActionListener {

	Escolha_de_adversario tela2;
	
	private Image fundo;
	private Icones_interativos texto;
	private Icones_interativos iconeIgnis;
	private Icones_interativos iconeAyla;
	private Icones_interativos iconeRexthor;
	private Icones_interativos iconeKiki;
	private Icones_interativos iconeArius;
	
	private Icones_interativos luz;
	
	private Icones_interativos contorno;
	
	private Icones_interativos teclaEsquerda;
	private Icones_interativos teclaDireita;
	private Icones_interativos teclaZ;
	private Icones_interativos teclaX;
	private Icones_interativos teclaEsc;
	
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7; 
	
	private Icones_interativos dialogo;
	private Icones_interativos bntSim;
	private Icones_interativos bntNao;
	
	private Icones_interativos sombreadorDialogo = new Icones_interativos(0, 0);
	Texto txtMenVoltar = new Texto(1234/2 - 706/2 + 60, 548/2 - 28, " ");
	Texto txtMenVoltar2 = new Texto(1234/2 - 706/2 + 60, 548/2 + 12, " ");
	Texto txtMenVoltar3 = new Texto(1234/2 - 706/2 + 250, 548/2 + 52, " ");
	
	private int contTecla = 0;
	private Boolean botaoSimNao = true;
	
	private Icones_interativos sombreadorMenu = new Icones_interativos(0, 0);
	private Icones_interativos fundoMenu = new Icones_interativos(16,16);
	private Icones_interativos bntMenu = new Icones_interativos(18 + 200/2 - 128/2,80);
	private Icones_interativos bntRegras = new Icones_interativos(18 + 200/2 - 128/2,80 + 140);
	
	
	boolean mostrarMenu = false;
	int contMenu = 0;
	
	private Timer timer;
	
	public Escolha_de_personagem() {
		ImageIcon referencia = new ImageIcon("res\\fundoPreto.png");
		fundo = referencia.getImage();
		
		texto = new Icones_interativos(1234/2 - 511/2,20 + 50);
		texto.load("res\\escolhaDePersonagem\\texto.png");
		
		iconeIgnis = new Icones_interativos(20 - 8, 640 - 20 - 454);
		iconeIgnis.load("res\\escolhaDePersonagem\\ignis2.png");
		iconeAyla = new Icones_interativos(20 + 240, 640 - 20 - 454 + 8);
		iconeAyla.load("res\\escolhaDePersonagem\\ayla.png");
		iconeRexthor = new Icones_interativos(20 + 240 + 240, 640 - 20 - 454 + 8);
		iconeRexthor.load("res\\escolhaDePersonagem\\rexthor.png");
		iconeKiki = new Icones_interativos(20 + 240 + 240 + 240, 640 - 20 - 454 + 8);
		iconeKiki.load("res\\escolhaDePersonagem\\kiki.png");
		iconeArius = new Icones_interativos(20 + 240 + 240 + 240 + 240, 640 - 20 - 454 + 8);
		iconeArius.load("res\\escolhaDePersonagem\\arius.png");
		
		
		luz = new Icones_interativos(11, 82);
		luz.load("res\\escolhaDePersonagem\\luzIgnis.png");
		
		contorno = new Icones_interativos(0, 0);
		contorno.load("res\\contorno.png");
		
		teclaEsquerda = new Icones_interativos(20 + 10, 640 - 20 - 42 - 20);
		teclaEsquerda.load("res\\escolhaDePersonagem\\setaEsquerda.png");
		teclaDireita = new Icones_interativos(1234 - 10 - 20 - 37, 640 - 20 - 42 - 20);
		teclaDireita.load("res\\escolhaDePersonagem\\setaDireita.png");
		teclaZ = new Icones_interativos(20 + 60,20 + 35);
		teclaZ.load("res\\escolhaDePersonagem\\teclaZ.png");
		teclaX = new Icones_interativos(1234 - 20 - 60 - 37, 20 + 35);
		teclaX.load("res\\escolhaDePersonagem\\teclaX.png");
		teclaEsc = new Icones_interativos(1234 - 20 - 55 + 2, 20 - 4);
		teclaEsc.load("res\\escolhaDePersonagem\\teclaEsc.png");
		
		dialogo = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2);
		bntSim = new Icones_interativos(1234/2 - 706/2 + 110, 640/2 - 278/2 + 190);
		bntNao = new Icones_interativos(1234/2 - 706/2 + 480, 640/2 - 278/2 + 190);
		
		txtMenVoltar.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtMenVoltar.setCorTexto(new Color (235, 230, 233));
		txtMenVoltar2.setFonte(new Font("Arial", Font.PLAIN, 28));
		txtMenVoltar3.setFonte(new Font("Arial", Font.PLAIN, 28));
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);

		if(janela != null && janela.getTitle() == "Escolha de Personagem") {
			int codigo = tecla.getKeyCode();
			
			if(codigo == KeyEvent.VK_ESCAPE) {
				mostrarMenu = !mostrarMenu;
				teclaEsc.load("res\\escolhaDePersonagem\\teclaEsc2.png");
				
				if(mostrarMenu == true) {
					contMenu = 0;
					sombreadorMenu.load("res\\sombreador.png");
					fundoMenu.load("res\\menu.png");
					bntMenu.load("res\\bntMenu2.png");
					bntRegras.load("res\\bntRegras1.png");
					
				} else {
					sombreadorMenu.load("");
					fundoMenu.load("");
					bntMenu.load("");
					bntRegras.load("");
				}
			}else if(codigo == KeyEvent.VK_UP && mostrarMenu == true) {
				if(contMenu == 0) {
					contMenu = 1;
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
						bntMenu.load("res\\bntMenu1.png");
						break;
				}
			}else if(codigo == KeyEvent.VK_DOWN && mostrarMenu == true) {
				if(contMenu == 1) {
					contMenu = 0;
				} else {
					contMenu ++;
				}
				
				switch (contMenu) {
					case 0:
						bntMenu.load("res\\bntMenu2.png");
						bntRegras.load("res\\bntRegras1.png");
						break;
					case 1:
						bntRegras.load("res\\bntRegras2.png");
						bntMenu.load("res\\bntMenu1.png");
						break;
				}
			}else if(codigo == KeyEvent.VK_LEFT && dialogo.getImagem() == null && mostrarMenu == false) {
				
				teclaEsquerda.load("res\\escolhaDePersonagem\\setaEsquerda2.png");
				if(contTecla == 0) {
					contTecla = 4;
				} else {
					contTecla --;
				}
				
				switch (contTecla) {
					case 0:
						iconeAyla.setX(20 + 240);
						iconeAyla.setY(640 - 20 - 454 + 8);
						iconeAyla.load("res\\escolhaDePersonagem\\ayla.png");
						
						iconeIgnis.setX(20 - 8);
						iconeIgnis.setY(640 - 20 - 454);
						iconeIgnis.load("res\\escolhaDePersonagem\\ignis2.png");
						texto.load("res\\escolhaDePersonagem\\texto.png");
						
						luz.setX(11); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzIgnis.png");
					    break;
					    
					case 1:
						iconeRexthor.setX(20 + 240 + 240);
						iconeRexthor.setY(640 - 20 - 454 + 8);
						iconeRexthor.load("res\\escolhaDePersonagem\\rexthor.png");
						
						iconeAyla.setX(20 + 240 - 8);
						iconeAyla.setY(640 - 20 - 454);
						iconeAyla.load("res\\escolhaDePersonagem\\ayla2.png");
						texto.load("res\\escolhaDePersonagem\\texto2.png");
						
						luz.setX(98); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzAyla.png");
					    break;
					    
					case 2:
						iconeKiki.setX(20 + 240 + 240 + 240);
						iconeKiki.setY(640 - 20 - 454 + 8);
						iconeKiki.load("res\\escolhaDePersonagem\\kiki.png");
						
						iconeRexthor.setX(20 + 240 + 240 - 8);
						iconeRexthor.setY(640 - 20 - 454);
						iconeRexthor.load("res\\escolhaDePersonagem\\rexthor2.png");
						texto.load("res\\escolhaDePersonagem\\texto.png");
						
						luz.setX(338); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzRexthor.png");
					    break;
					    
					case 3:
						iconeArius.setX(20 + 240 + 240 + 240 + 240);
						iconeArius.setY(640 - 20 - 454 + 8);
						iconeArius.load("res\\escolhaDePersonagem\\arius.png");
						
						iconeKiki.setX(20 + 240 + 240 + 240 - 8);
						iconeKiki.setY(640 - 20 - 454);
						iconeKiki.load("res\\escolhaDePersonagem\\kiki2.png");
						texto.load("res\\escolhaDePersonagem\\texto2.png");
						
						luz.setX(578); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzKiki.png");
					    break;
					    
					case 4:
						iconeIgnis.setX(20);
						iconeIgnis.setY(640 - 20 - 454 + 8);
						iconeIgnis.load("res\\escolhaDePersonagem\\ignis.png");
						
						iconeArius.setX(20 + 240 + 240 + 240 + 240 - 8);
						iconeArius.setY(640 - 20 - 454);
						iconeArius.load("res\\escolhaDePersonagem\\arius2.png");
						texto.load("res\\escolhaDePersonagem\\texto.png");
						
						luz.setX(769); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzArius.png");
					    break;
				}
					  
			}
			else if(codigo == KeyEvent.VK_RIGHT && dialogo.getImagem() == null && mostrarMenu == false) {
				teclaDireita.load("res\\escolhaDePersonagem\\setaDireita2.png");
				
				if(contTecla == 4) {
					contTecla = 0;
				} else {
					contTecla ++;
				}
				
				switch (contTecla) {
					case 0:
						iconeArius.setX(20 + 240 + 240 + 240 + 240);
						iconeArius.setY(640 - 20 - 454 + 8);
						iconeArius.load("res\\escolhaDePersonagem\\arius.png");
						
						iconeIgnis.setX(20 - 8);
						iconeIgnis.setY(640 - 20 - 454);
						iconeIgnis.load("res\\escolhaDePersonagem\\ignis2.png");
						texto.load("res\\escolhaDePersonagem\\texto.png");
						
						luz.setX(11); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzIgnis.png");
					    break;
					    
					case 1:
						iconeIgnis.setX(20);
						iconeIgnis.setY(640 - 20 - 454 + 8);
						iconeIgnis.load("res\\escolhaDePersonagem\\ignis.png");
						
						iconeAyla.setX(20 + 240 - 8);
						iconeAyla.setY(640 - 20 - 454);
						iconeAyla.load("res\\escolhaDePersonagem\\ayla2.png");
						texto.load("res\\escolhaDePersonagem\\texto2.png");
						
						luz.setX(98); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzAyla.png");
					    break;
					    
					case 2:
						iconeAyla.setX(20 + 240);
						iconeAyla.setY(640 - 20 - 454 + 8);
						iconeAyla.load("res\\escolhaDePersonagem\\ayla.png");
						
						iconeRexthor.setX(20 + 240 + 240 - 8);
						iconeRexthor.setY(640 - 20 - 454);
						iconeRexthor.load("res\\escolhaDePersonagem\\rexthor2.png");
						texto.load("res\\escolhaDePersonagem\\texto.png");
						
						luz.setX(338); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzRexthor.png");
					    break;
					    
					case 3:
						iconeRexthor.setX(20 + 240 + 240);
						iconeRexthor.setY(640 - 20 - 454 + 8);
						iconeRexthor.load("res\\escolhaDePersonagem\\rexthor.png");
					
						iconeKiki.setX(20 + 240 + 240 + 240 - 8);
						iconeKiki.setY(640 - 20 - 454);
						iconeKiki.load("res\\escolhaDePersonagem\\kiki2.png");
						texto.load("res\\escolhaDePersonagem\\texto2.png");
						
						luz.setX(578); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzKiki.png");
					    break;
					    
					case 4:
						iconeKiki.setX(20 + 240 + 240 + 240);
						iconeKiki.setY(640 - 20 - 454 + 8);
						iconeKiki.load("res\\escolhaDePersonagem\\kiki.png");
						
						iconeArius.setX(20 + 240 + 240 + 240 + 240 - 8);
						iconeArius.setY(640 - 20 - 454);
						iconeArius.load("res\\escolhaDePersonagem\\arius2.png");
						texto.load("res\\escolhaDePersonagem\\texto.png");
						
						luz.setX(769); luz.setY(82);
						luz.load("res\\escolhaDePersonagem\\luzArius.png");
					    break;
				}
			}
			else if(codigo == KeyEvent.VK_Z && dialogo.getImagem() == null && mostrarMenu == false) {
				teclaZ.load("res\\escolhaDePersonagem\\teclaZ2.png");
				
				sombreadorDialogo.load("res\\sombreador.png");
				dialogo.load("res\\dialogo.png");
				bntSim.load("res\\bntSim.png");
				bntNao.load("res\\bntNao2.png");
				botaoSimNao = true;
				
				if(contTecla == 0 || contTecla == 2 || contTecla == 4) {
					txtMenVoltar.setTexto("O aventureiro escolido não poderá ser trocado");
					txtMenVoltar2.setTexto("durante o jogo.");
					txtMenVoltar3.setTexto("Deseja continuar?");
				} else {
					txtMenVoltar.setTexto("A aventureira escolida não poderá ser trocada");
					txtMenVoltar2.setTexto("durante o jogo.");
					txtMenVoltar3.setTexto("Deseja continuar?");
				}
			}else if(codigo == KeyEvent.VK_X && mostrarMenu == false) {
				teclaX.load("res\\escolhaDePersonagem\\teclaX2.png");
				txtMenVoltar.setTexto(" ");
				txtMenVoltar2.setTexto(" ");
				txtMenVoltar3.setTexto(" ");
				sombreadorDialogo.load("");
				dialogo.setImagem(null);
				bntSim.setImagem(null);
				bntNao.setImagem(null);
				
			}else if((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogo.getImagem() != null && botaoSimNao == false && mostrarMenu == false) {
				dialogo.load("res\\dialogo.png");
				bntSim.load("res\\bntSim.png");
				bntNao.load("res\\bntNao2.png");
				botaoSimNao = true;
				
			}else if((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogo.getImagem() != null && botaoSimNao == true && mostrarMenu == false) {
				dialogo.load("res\\dialogo.png");
				bntSim.load("res\\bntSim2.png");
				bntNao.load("res\\bntNao.png");
				botaoSimNao = false;
				
			}else if(codigo == KeyEvent.VK_Z && dialogo.getImagem() != null && mostrarMenu == false) {
				teclaZ.load("res\\escolhaDePersonagem\\teclaZ2.png");
				
				if(botaoSimNao == false) {
					txtMenVoltar.setTexto(" ");
					txtMenVoltar2.setTexto(" ");
					txtMenVoltar3.setTexto(" ");
					sombreadorDialogo.load("");
					dialogo.setImagem(null);
					bntSim.setImagem(null);
					bntNao.setImagem(null);
				}
				else {
					int [] derrotados = {};
					JFrame janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
			        janelaPrincipal.remove(this);
			        tela2 = new Escolha_de_adversario(contTecla, this);
			        janelaPrincipal.add(tela2);
			        janelaPrincipal.setTitle("Escolha de Adversário");
			        janelaPrincipal.revalidate();
			        
			        txtMenVoltar.setTexto(" ");
					txtMenVoltar2.setTexto(" ");
					txtMenVoltar3.setTexto(" ");
					txtMenVoltar.setTexto(" ");
					sombreadorDialogo.load("");
					dialogo.setImagem(null);
					bntSim.setImagem(null);
					bntNao.setImagem(null);
				}
			}
		} else {
			tela2.KeyPressed(tecla);
		}
	}
	
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
		} else {
			tela2.KeyReleased(tecla);
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(texto.getImagem(),texto.getX(),texto.getY(), this);
		graficos.drawImage(iconeIgnis.getImagem(), iconeIgnis.getX(),iconeIgnis.getY(), this);
		graficos.drawImage(iconeAyla.getImagem(), iconeAyla.getX(), iconeAyla.getY(), this);
		graficos.drawImage(iconeRexthor.getImagem(), iconeRexthor.getX(), iconeRexthor.getY(), this);
		graficos.drawImage(iconeKiki.getImagem(), iconeKiki.getX(), iconeKiki.getY(), this);
		graficos.drawImage(iconeArius.getImagem(), iconeArius.getX(), iconeArius.getY(), this);
		
		
		graficos.drawImage(luz.getImagem(), luz.getX(), luz.getY(), this);
		
		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getX(), teclaEsquerda.getY(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getX(), teclaDireita.getY(), this);
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getX(), teclaZ.getY(), this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getX(), teclaX.getY(), this);
		
		graficos.drawImage(sombreadorDialogo.getImagem(), sombreadorDialogo.getX(), sombreadorDialogo.getY(), this);
		graficos.drawImage(dialogo.getImagem(), dialogo.getX(), dialogo.getY(), this);
		graficos.drawImage(bntSim.getImagem(), bntSim.getX(), bntSim.getY(), this);
		graficos.drawImage(bntNao.getImagem(), bntNao.getX(), bntNao.getY(), this);
		
		graficos.setColor( txtMenVoltar.getCorTexto());
		tl3 = new TextLayout(txtMenVoltar.getTexto(), txtMenVoltar.getFonte(), frc);
		tl3.draw(graficos, txtMenVoltar.getX(), txtMenVoltar.getY());
		tl6 = new TextLayout(txtMenVoltar2.getTexto(), txtMenVoltar2.getFonte(), frc);
		tl6.draw(graficos, txtMenVoltar2.getX(), txtMenVoltar2.getY());
		tl7 = new TextLayout(txtMenVoltar3.getTexto(), txtMenVoltar3.getFonte(), frc);
		tl7.draw(graficos, txtMenVoltar3.getX(), txtMenVoltar3.getY());
		
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
