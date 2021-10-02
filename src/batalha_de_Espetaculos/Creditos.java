package batalha_de_Espetaculos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import batalha_de_Espetaculos.Modelo.Icones_interativos;

public class Creditos extends JPanel implements ActionListener {
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	
	
	public Creditos() {
	
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\Creditos\\fundo.png");
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
