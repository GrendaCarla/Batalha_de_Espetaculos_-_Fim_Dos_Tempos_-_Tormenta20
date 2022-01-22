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

public class Creditos extends JPanel implements ActionListener {
	
	private Escolha_de_personagem tela1;
	private Escolha_de_adversario tela2;
	private Batalha tela3;
	private Menu telaMenu;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);

	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1120, -12);

	private boolean contEngranagem2;

	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7, tl8, tl9, tl10, tl11, tl12, tl13;
	
	private int contTempo = 0;
	private boolean mudaImagem = false;
	
	private String caminho; 
	
	private Timer timer;
	
	/* ------------------------------- Créditos ---------------------------------*/
	
	private Texto txtLn1 = new Texto(1234/2 - 70, 80, "CRÉDITOS");
	private Texto txtLn2 = new Texto(170, txtLn1.getY() + 60, "Leonel Caldela / @leonelcaldela                                                                         Mutuca / Mestre");
	private Texto txtLn3 = new Texto(txtLn2.getX(), txtLn2.getY() + 30, "Schaeppi / @Schaeppi                                                                                                 Ignis Crae");
	private Texto txtLn4 = new Texto(txtLn2.getX(), txtLn3.getY() + 30, "Karen Soarele / @karensoarele                                                         Aylarianna \"Ayla\" Purpúrea");
	private Texto txtLn5 = new Texto(txtLn2.getX(), txtLn4.getY() + 30, "Rex / @Rex2099                                                                                                             Rexthor");
	private Texto txtLn6 = new Texto(txtLn2.getX(), txtLn5.getY() + 30, "Katiucha Barcelos / @Katiucha                                                              Kir'zanaath \"Kiki\" Odello");
	private Texto txtLn7 = new Texto(txtLn2.getX(), txtLn6.getY() + 30, "Guilherme Dei Svaldi / @guilhermesvaldi                                          Arius Gorgonius Dubitatius");

	private Texto txtLn8 = new Texto(1234/2 - 148, txtLn7.getY() + 52, "Tormenta20 - Fim dos Tempos");
	
	private Texto txtLn9 = new Texto(txtLn2.getX() - 30, txtLn8.getY() + 58, "Gren / @LembreDePiscar                      Desenvolvedor / Programador / Ilustrador / Designer de Som");

	
	private Texto txtLn10 = new Texto(96, txtLn9.getY() + 80, "Esse jogo é um fangame da Campanha de RPG Fim dos Tempos, ou seja, o mundo e os personagens não são");
	private Texto txtLn11 = new Texto(txtLn10.getX() + 16, txtLn10.getY() + 30, "de minha autoria e os criadores não são responsáveis pelas bobagens criadas a partir de sua obra.");
	
	private Texto txtLn12 = new Texto(141, txtLn11.getY() + 50, "E quero fazer um agradecimento super especial para todos que acompanham a mesa, porque muitas");
	private Texto txtLn13 = new Texto(txtLn12.getX() + 110, txtLn12.getY() + 30, "das melhores coisas da campanha vieram da interação e criatividade de vocês.");

	/* ------------------------------- enfeite ---------------------------------*/

	private Icones_interativos aventureiro1 = new Icones_interativos(56, 60);
	private Icones_interativos aventureiro2 = new Icones_interativos(1234 - aventureiro1.getX() - 86, aventureiro1.getY());
	private Icones_interativos aventureiro3 = new Icones_interativos(aventureiro1.getX(), aventureiro1.getY() + 86 + 20);
	private Icones_interativos aventureiro4 = new Icones_interativos(aventureiro2.getX(), aventureiro3.getY());
	private Icones_interativos aventureiro5 = new Icones_interativos(aventureiro1.getX(), aventureiro3.getY() + 86 + 20);
	private Icones_interativos aventureiro6 = new Icones_interativos(aventureiro2.getX(), aventureiro5.getY());

	private Icones_interativos estrelaFim1 = new Icones_interativos(10, 410);
	private Icones_interativos estrelaFim2 = new Icones_interativos(1234 - 196, 409);
	
	private Icones_interativos estrelaTitulo1 = new Icones_interativos(438, 32);
	private Icones_interativos estrelaTitulo2 = new Icones_interativos(1234 - estrelaTitulo1.getX() - 134, estrelaTitulo1.getY());

	
	public Creditos(boolean Engrenagem2, String Caminho) {
		contEngranagem2 = Engrenagem2;
		this.caminho = Caminho;
		
		ImageIcon referencia = new ImageIcon(caminho + "res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load(caminho + "res\\Manual\\fundo.png");
		
		engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem1.png");
		
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");

		contorno.load(caminho + "res\\contorno.png");
		
		/* ------------------------------- Créditos ---------------------------------*/
		
		txtLn1.setFonte(new Font("Arial", Font.PLAIN, 27));
		txtLn1.setCorTexto(new Color (255, 255, 255));
		
		txtLn2.setFonte(new Font("Arial", Font.PLAIN, 21));
		txtLn2.setCorTexto(new Color (23, 0, 51));

		txtLn3.setFonte(new Font("Arial", Font.PLAIN, 21));
		txtLn4.setFonte(new Font("Arial", Font.PLAIN, 21));
		txtLn5.setFonte(new Font("Arial", Font.PLAIN, 21));
		txtLn6.setFonte(new Font("Arial", Font.PLAIN, 21));
		txtLn7.setFonte(new Font("Arial", Font.PLAIN, 21));
		
		txtLn8.setCorTexto(new Color (207, 1, 43));
		txtLn8.setFonte(new Font("Arial", Font.BOLD, 21));
		txtLn9.setFonte(new Font("Arial", Font.PLAIN, 21));
		txtLn10.setFonte(new Font("Arial", Font.PLAIN, 21));
		txtLn11.setFonte(new Font("Arial", Font.PLAIN, 21));
		txtLn12.setFonte(new Font("Arial", Font.PLAIN, 21));
		
		txtLn12.setCorTexto(new Color (239, 1, 97));
		txtLn13.setFonte(new Font("Arial", Font.PLAIN, 21));
		
		/* ------------------------------- enfeite ---------------------------------*/
		
		aventureiro1.load(caminho + "res\\Creditos\\leonel1.png");
		aventureiro2.load(caminho + "res\\Creditos\\schaeppi2.png");
		aventureiro3.load(caminho + "res\\Creditos\\karen2.png");
		aventureiro4.load(caminho + "res\\Creditos\\rex1.png");
		aventureiro5.load(caminho + "res\\Creditos\\katiucha1.png");
		aventureiro6.load(caminho + "res\\Creditos\\guilherme2.png");
		
		estrelaTitulo1.load(caminho + "res\\Creditos\\estrela1.png");
		estrelaTitulo2.load(caminho + "res\\Creditos\\estrela2.png");
		
		estrelaFim1.load(caminho + "res\\Creditos\\estrela3.png");
		estrelaFim2.load(caminho + "res\\Creditos\\estrela4.png");


		timer = new Timer(5, this);
		timer.start();
	}
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_X) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			if(tela3 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela3);
		        janelaPrincipal.setTitle("Batalha");
		        tela3.setContEngranagem2(contEngranagem2);
		        janelaPrincipal.revalidate();
		        timer.stop();
				
			} else if(tela2 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela2);
		        janelaPrincipal.setTitle("Escolha de Adversário");
		        tela2.setContEngranagem2(contEngranagem2);
		        janelaPrincipal.revalidate();
		        timer.stop();
				
			} else if(tela1 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela1);
		        janelaPrincipal.setTitle("Escolha de Personagem");
		        tela1.setContEngranagem2(contEngranagem2);
		        janelaPrincipal.revalidate();
		        timer.stop();
		        
			} else if(telaMenu != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(telaMenu);
		        janelaPrincipal.setTitle("Menu");
		        telaMenu.setContEngranagem2(contEngranagem2);
		        janelaPrincipal.revalidate();
		        timer.stop();
				
			}
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		
		/* ------------------------------- Créditos ---------------------------------*/

		tl1 = new TextLayout(txtLn1.getTexto(), txtLn1.getFonte(), frc);
		tl2 = new TextLayout(txtLn2.getTexto(), txtLn2.getFonte(), frc);
		tl3 = new TextLayout(txtLn3.getTexto(), txtLn3.getFonte(), frc);
		tl4 = new TextLayout(txtLn4.getTexto(), txtLn4.getFonte(), frc);
		tl5 = new TextLayout(txtLn5.getTexto(), txtLn5.getFonte(), frc);
		tl6 = new TextLayout(txtLn6.getTexto(), txtLn6.getFonte(), frc);
		tl7 = new TextLayout(txtLn7.getTexto(), txtLn7.getFonte(), frc);
		tl8 = new TextLayout(txtLn8.getTexto(), txtLn8.getFonte(), frc);
		tl9 = new TextLayout(txtLn9.getTexto(), txtLn9.getFonte(), frc);
		tl10 = new TextLayout(txtLn10.getTexto(), txtLn10.getFonte(), frc);
		tl11 = new TextLayout(txtLn11.getTexto(), txtLn11.getFonte(), frc);
		tl12 = new TextLayout(txtLn12.getTexto(), txtLn12.getFonte(), frc);
		tl13 = new TextLayout(txtLn13.getTexto(), txtLn13.getFonte(), frc);

	    graficos.setColor(txtLn1.getCorTexto());
		tl1.draw(graficos, txtLn1.getX(), txtLn1.getY());
		tl2.draw(graficos, txtLn2.getX(), txtLn2.getY());
		tl3.draw(graficos, txtLn3.getX(), txtLn3.getY());
		tl4.draw(graficos, txtLn4.getX(), txtLn4.getY());
		tl5.draw(graficos, txtLn5.getX(), txtLn5.getY());
		tl6.draw(graficos, txtLn6.getX(), txtLn6.getY());
		tl7.draw(graficos, txtLn7.getX(), txtLn7.getY());
		
		graficos.setColor(txtLn8.getCorTexto());
		tl8.draw(graficos, txtLn8.getX(), txtLn8.getY());
		
		graficos.setColor(txtLn1.getCorTexto());
		tl9.draw(graficos, txtLn9.getX(), txtLn9.getY());
		tl10.draw(graficos, txtLn10.getX(), txtLn10.getY());
		tl11.draw(graficos, txtLn11.getX(), txtLn11.getY());
		
		graficos.setColor(txtLn12.getCorTexto());
		tl12.draw(graficos, txtLn12.getX(), txtLn12.getY());
		tl13.draw(graficos, txtLn13.getX(), txtLn13.getY());
		
		/* ------------------------------- enfeite ---------------------------------*/

		graficos.drawImage(aventureiro1.getImagem(), aventureiro1.getX(), aventureiro1.getY(), this);
		graficos.drawImage(aventureiro2.getImagem(), aventureiro2.getX(), aventureiro2.getY(), this);
		graficos.drawImage(aventureiro3.getImagem(), aventureiro3.getX(), aventureiro3.getY(), this);
		graficos.drawImage(aventureiro4.getImagem(), aventureiro4.getX(), aventureiro4.getY(), this);
		graficos.drawImage(aventureiro5.getImagem(), aventureiro5.getX(), aventureiro5.getY(), this);
		graficos.drawImage(aventureiro6.getImagem(), aventureiro6.getX(), aventureiro6.getY(), this);
		
		graficos.drawImage(estrelaFim1.getImagem(), estrelaFim1.getX(), estrelaFim1.getY(), this);
		graficos.drawImage(estrelaFim2.getImagem(), estrelaFim2.getX(), estrelaFim2.getY(), this);
		graficos.drawImage(estrelaTitulo1.getImagem(), estrelaTitulo1.getX(), estrelaTitulo1.getY(), this);
		graficos.drawImage(estrelaTitulo2.getImagem(), estrelaTitulo2.getX(), estrelaTitulo2.getY(), this);
		

		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getX(), engrenagem1.getY(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getX(), engrenagem2.getY(), this);
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		contTempo ++;
		AnimarImagens();
		
		repaint();
		
	}
	
	public void AnimarImagens() {
		
		if(contTempo % 24 == 0) {
			mudaImagem = !mudaImagem;
			
			aventureiro1.load(caminho + "res\\Creditos\\leonel" + (mudaImagem == false ? 1 : 2) + ".png");
			aventureiro2.load(caminho + "res\\Creditos\\schaeppi" + (mudaImagem == false ? 2 : 1) + ".png");
			aventureiro3.load(caminho + "res\\Creditos\\karen" + (mudaImagem == false ? 2 : 1) + ".png");
			aventureiro4.load(caminho + "res\\Creditos\\rex" + (mudaImagem == false ? 1 : 2) + ".png");
			aventureiro5.load(caminho + "res\\Creditos\\katiucha" + (mudaImagem == false ? 1 : 2) + ".png");
			aventureiro6.load(caminho + "res\\Creditos\\guilherme" + (mudaImagem == false ? 2 : 1) + ".png");
		}

	}
	
	public void setTelaMenu(Menu menu) {
		this.telaMenu = menu;
	}

	public void setTela2(Escolha_de_adversario tela2) {
		this.tela2 = tela2;
	}

	public void setTela1(Escolha_de_personagem tela1) {
		this.tela1 = tela1;
	}

	public void setTela3(Batalha tela3) {
		this.tela3 = tela3;
	}

}
