package batalha_de_Espetaculos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Container extends JFrame{
	
	String caminho = System.getProperty("user.dir").substring(0, (System.getProperty("user.dir").length() - 52));   //System.getProperty("user.dir") + "\\";
	Menu menu = new Menu(false, caminho);
	 
	public Container() {
		add(menu);
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1234 + 14, 640 + 37);
		setMinimumSize(new Dimension(1234 + 14, 640 + 37));
		setLocationRelativeTo(null);
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
