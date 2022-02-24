package batalha_de_Espetaculos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Container extends JFrame{
	
	String caminho = System.getProperty("user.dir").substring(0, (System.getProperty("user.dir").length() - 52)) + "Batalha_de_Espetaculos_-_Fim_Dos_Tempos_-_Tormenta20\\"; //System.getProperty("user.dir") + "\\Batalha_de_Espetaculos_-_Fim_Dos_Tempos_-_Tormenta20\\";
	
	Menu menu = new Menu(false, caminho);

	Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();
	 
	public Container() {
		
		add(menu);
		setTitle("Menu");
		
		this.setExtendedState(MAXIMIZED_BOTH); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(d.width + 10, d.height + 10));
		setLocation(-3, 0);
		this.setResizable(false);
		setBackground(Color.BLACK);
		setVisible(true);
		
		addKeyListener(new java.awt.event.KeyAdapter() {
    		public void keyPressed(java.awt.event.KeyEvent evt) {
        		formKeyPressed(evt);
    		}
    		
    		public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
    		
    	});
	}

	private void formKeyPressed(java.awt.event.KeyEvent evt) {
    	menu.KeyPressed(evt);
    }
	
	private void formKeyReleased(java.awt.event.KeyEvent evt) {     
		menu.KeyReleased(evt);
	}
	
	public static void main (String []args) {
		new Container();
		
	}

}
