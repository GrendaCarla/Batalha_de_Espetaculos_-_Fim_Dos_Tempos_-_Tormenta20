package batalha_de_Espetaculos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import batalha_de_Espetaculos.Modelo.Icones_interativos;
import batalha_de_Espetaculos.Modelo.Texto;

public class Regras extends JPanel implements ActionListener {
	
	private Escolha_de_personagem tela1;
	private Escolha_de_adversario tela2;
	private Batalha tela3;
	private Menu telaMenu;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	
	private Icones_interativos img1 = new Icones_interativos(0, 0);
	private Icones_interativos img2 = new Icones_interativos(0, 0);
	private Icones_interativos img3 = new Icones_interativos(0, 0);
	private Icones_interativos img4 = new Icones_interativos(0, 0);
	
	private Texto txtLn1 = new Texto(0, 0, " ");
	private Texto txtLn2 = new Texto(0, 0, " ");
	private Texto txtLn3 = new Texto(0, 0, " ");
	private Texto txtLn4 = new Texto(0, 0, " ");
	private Texto txtLn5 = new Texto(0, 0, " ");
	
	public Regras () {
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\Regras\\fundo.png");
	}
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_DOWN) {
			
			
		}else if(codigo == KeyEvent.VK_UP) {
			
			
		}else if(codigo == KeyEvent.VK_X) {
			
			if(tela3 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela3);
		        janelaPrincipal.setTitle("Batalha");
		        janelaPrincipal.revalidate();
				
			} else if(tela2 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela2);
		        janelaPrincipal.setTitle("Escolha de Adversário");
		        janelaPrincipal.revalidate();
				
			} else if(tela1 != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(tela1);
		        janelaPrincipal.setTitle("Escolha de Personagem");
		        janelaPrincipal.revalidate();
		        
			} else if(telaMenu != null) {
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        janelaPrincipal.add(telaMenu);
		        janelaPrincipal.setTitle("Menu");
		        janelaPrincipal.revalidate();
				
			}
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

	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
		
	}
}
