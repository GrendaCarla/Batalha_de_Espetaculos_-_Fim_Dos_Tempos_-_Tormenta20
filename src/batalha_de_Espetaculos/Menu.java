package batalha_de_Espetaculos;

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

public class Menu extends JPanel implements ActionListener {
	
	/* ---------------------------------------------------------------------------------------- \
	|  Menu é a tela inicial onde escolhem começar um novo jogo ou continuar algum, tambem		|
	|  pode acessar as regras e os créditos.													|
	\ ---------------------------------------------------------------------------------------- */
	
	private Escolha_de_personagem tela1;
	private Regras telaRegras;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	private Timer timer;
	
	// ---------------------------- imagens das opções do menu ------------------------------------
	
	private Icones_interativos bntContinuar = new Icones_interativos(500, 200);
	private Icones_interativos bntNovoJogo = new Icones_interativos(bntContinuar.getX(), bntContinuar.getY() + 80);
	private Icones_interativos bntRegras = new Icones_interativos(bntContinuar.getX(), bntNovoJogo.getY() + 80);
	private Icones_interativos bntCreditos = new Icones_interativos(bntContinuar.getX(), bntRegras.getY() + 80);
	
	private int contOpcoes;
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	
	public Menu () {
	
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\Menu\\fundo.png");
		
		Restaurar();
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void Restaurar() {

		bntContinuar.load("res\\Menu\\bntContinuar2.png");
		bntNovoJogo.load("res\\Menu\\bntNovoJogo1.png");
		bntRegras.load("res\\Menu\\bntRegras1.png");
		bntCreditos.load("res\\Menu\\bntCreditos1.png");
		
		contOpcoes = 0;
	}
	
	/* ---------------------------------------------------------------------------------------- \
	|  		 					dispara quando as teclas são  pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		if(janela != null && janela.getTitle() == "Menu") {
			int codigo = tecla.getKeyCode();
			
			// -------------------- muda a seleção das opções -------------------- \
			if(codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN){
				
				if(codigo == KeyEvent.VK_UP) {
					if(contOpcoes == 0) {contOpcoes = 3;} else {contOpcoes --;}
				}else if(codigo == KeyEvent.VK_DOWN) {
					if(contOpcoes == 3) {contOpcoes = 0;} else {contOpcoes ++;}
				}
				
				bntContinuar.load("res\\Menu\\bntContinuar1.png");
				bntNovoJogo.load("res\\Menu\\bntNovoJogo1.png");
				bntRegras.load("res\\Menu\\bntRegras1.png");
				bntCreditos.load("res\\Menu\\bntCreditos1.png");
				
				switch (contOpcoes) {
					case 0:
						bntContinuar.load("res\\Menu\\bntContinuar2.png");
						break;
					case 1:
						bntNovoJogo.load("res\\Menu\\bntNovoJogo2.png");
						break;
					case 2:
						bntRegras.load("res\\Menu\\bntRegras2.png");
						break;
					case 3:
						bntCreditos.load("res\\Menu\\bntCreditos2.png");
						break;
				}
			} else if(codigo == KeyEvent.VK_Z && contOpcoes == 1) {
				
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        tela1 = new Escolha_de_personagem(this);
		        janelaPrincipal.add(tela1);
		        janelaPrincipal.setTitle("Escolha de Personagem");
		        janelaPrincipal.revalidate();
		        
			} else if(codigo == KeyEvent.VK_Z && contOpcoes == 2) {
				
				janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
		        janelaPrincipal.remove(this);
		        telaRegras = new Regras();
		        
		        telaRegras.setTelaMenu(this);
		        
		        janelaPrincipal.add(telaRegras);
		        janelaPrincipal.setTitle("RegrasM");
		        janelaPrincipal.revalidate();
		        
			}
			
		}else {
			
			if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Escolha de Personagem" || janelaPrincipal.getTitle() == "Escolha de Adversário"  || janelaPrincipal.getTitle() == "Batalha" || janelaPrincipal.getTitle() == "Regras1" || janelaPrincipal.getTitle() == "Regras2" || janelaPrincipal.getTitle() == "Regras3") {
				tela1.KeyPressed(tecla);
				
			} else if(janelaPrincipal != null && janelaPrincipal.getTitle() == "RegrasM") {
				telaRegras.KeyPressed(tecla);
				
			}
			
		}
			
	}
	
	public void KeyReleased (java.awt.event.KeyEvent tecla){

		if(janelaPrincipal != null && janelaPrincipal.getTitle() == "Escolha de Personagem") {
			tela1.KeyReleased(tecla);
			
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		
		graficos.drawImage(bntContinuar.getImagem(), bntContinuar.getX(), bntContinuar.getY(), this);
		graficos.drawImage(bntNovoJogo.getImagem(), bntNovoJogo.getX(), bntNovoJogo.getY(), this);
		graficos.drawImage(bntRegras.getImagem(), bntRegras.getX(), bntRegras.getY(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getX(), bntCreditos.getY(), this);
		
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
		
	}

}
