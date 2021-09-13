package batalha_de_Espetaculos;

import java.awt.Color;

import javax.swing.JFrame;

public class Container extends JFrame{
	
	Escolha_de_personagem tela = new Escolha_de_personagem();
	
	public Container() {
		add(tela);
		setTitle("Escolha de Personagem");
		setSize(1234 + 14, 640 + 39);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//this.setResizable(false);
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
    	tela.KeyPressed(evt);
    }
	
	private void formKeyReleased(java.awt.event.KeyEvent evt) {     
		tela.KeyReleased(evt);
	}
	
	public static void main (String []args) {
		new Container();
		
	}
	
	

}
