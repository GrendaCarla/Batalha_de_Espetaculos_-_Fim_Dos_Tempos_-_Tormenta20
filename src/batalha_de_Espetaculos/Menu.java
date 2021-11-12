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

public class Menu extends JPanel implements ActionListener {
	
	/* ---------------------------------------------------------------------------------------- \
	|  Menu é a tela inicial onde escolhem começar um novo jogo ou continuar algum, tambem		|
	|  pode acessar as manual e os créditos.													|
	\ ---------------------------------------------------------------------------------------- */
	
	private Escolha_de_personagem tela1;
	private Escolha_de_adversario tela2;
	private Manual telaManual;
	private Creditos telaCreditos;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1130, -12);
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private int contEngranagem1 = 1;
	private boolean contEngranagem2;
	
	private Timer timer;
	
	// ------------------------------------------- Controles ---------------------------------------------
	
	private Icones_interativos teclaEsquerda = new Icones_interativos(1234 - 180, 640 - 130);
	private Icones_interativos teclaBaixo = new Icones_interativos(teclaEsquerda.getX() + 40, teclaEsquerda.getY() + 46);
	private Icones_interativos teclaDireita = new Icones_interativos(teclaBaixo.getX() + 46, teclaEsquerda.getY());
	private Icones_interativos teclaCima = new Icones_interativos(teclaBaixo.getX(), teclaBaixo.getY() - 86);
	private Icones_interativos teclaZ = new Icones_interativos(20 + 50,640 - 20 - 60);
	private Icones_interativos teclaX = new Icones_interativos(teclaZ.getX() + 60, teclaZ.getY());
	private Icones_interativos teclaEsc = new Icones_interativos(16, 16);
	
	// ---------------------------- opções do menu ------------------------------------
	
	private Icones_interativos bntContinuar = new Icones_interativos(1234/2 - 158/2, 160);
	private Icones_interativos bntNovoJogo = new Icones_interativos(bntContinuar.getX(), bntContinuar.getY() + 90);
	private Icones_interativos bntManual = new Icones_interativos(bntContinuar.getX(), bntNovoJogo.getY() + 90);
	private Icones_interativos bntCreditos = new Icones_interativos(bntContinuar.getX(), bntManual.getY() + 90);
	
	private int contOpcoes;
	
	// ---------------------------- save ------------------------------------
	
	private Icones_interativos sombreadorDialogoAviso = new Icones_interativos(0, 0);
	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 100);
	
	private Texto txtDialogoAviso = new Texto(460, dialogoAviso.getY() + 140, " ");
	
	private TextLayout tl1;
	
	private Salvar salvar = new Salvar();
	int valorLeituraSave;
	private int contTempoMensagemErro = 0;
	
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	
	public Menu (boolean Engrenagem2) {
		contEngranagem2 = Engrenagem2;
		
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\Menu\\fundo.png");
		engrenagem1.load("res\\engrenagem1.png");		
		contorno.load("res\\contorno.png");
		
		engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");

		valorLeituraSave = salvar.LerDados();
		
		// ------------------------------------------- Controles ---------------------------------------------

		teclaEsquerda.load("res\\Menu\\setaEsquerda.png");
		teclaDireita.load("res\\Menu\\setaDireita.png");
		teclaCima.load("res\\Menu\\setaCima.png");
		teclaBaixo.load("res\\Menu\\setaBaixo.png");
		
		teclaZ.load("res\\Menu\\teclaZ.png");
		teclaX.load("res\\Menu\\teclaX.png");
		teclaEsc.load("res\\Menu\\teclaEsc.png");
		
		// ---------------------------- save ------------------------------------

		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 38));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		
		Restaurar();
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void MostrarMensagemDeErro() {
		
		contTempoMensagemErro ++;
		
		if(contTempoMensagemErro > 0 && contTempoMensagemErro < 140) {
			sombreadorDialogoAviso.load("res\\Menu\\sombreador.png");
			dialogoAviso.load("res\\Menu\\dialogo.png");
			txtDialogoAviso.setTexto("Save corrompido!!!");
		} else {
			sombreadorDialogoAviso.setImagem(null);
			dialogoAviso.setImagem(null);
			txtDialogoAviso.setTexto(" ");
		}
	}
	
	public void Restaurar() {
		
		if(valorLeituraSave == 0) {
			bntContinuar.load("res\\Menu\\bntContinuar2.png");
			bntNovoJogo.load("res\\Menu\\bntNovoJogo1.png");
			contOpcoes = 0;
		} else {
			bntContinuar.load("res\\Menu\\bntContinuar1.png");
			bntNovoJogo.load("res\\Menu\\bntNovoJogo2.png");
			contOpcoes = 1;
		}
		
		
		bntManual.load("res\\Menu\\bntManual3.png");
		bntCreditos.load("res\\Menu\\bntCreditos3.png");
		
		
	}

	
	/* ---------------------------------------------------------------------------------------- \
	|  		 					dispara quando as teclas são  pressionadas						|
	\ ---------------------------------------------------------------------------------------- */
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		if(janela != null && janela.getTitle() == "Menu") {
			int codigo = tecla.getKeyCode();
			
			if(dialogoAviso.getImagem() == null) {
				
				// -------------------- muda a seleção das opções -------------------- \
				if(codigo == KeyEvent.VK_LEFT) {
					teclaEsquerda.load("res\\Menu\\setaEsquerda2.png");
	
				}else if(codigo == KeyEvent.VK_RIGHT) {
					teclaDireita.load("res\\Menu\\setaDireita2.png");
	
				}else if(codigo == KeyEvent.VK_X) {
					teclaX.load("res\\Menu\\teclaX2.png");
	
				}else if(codigo == KeyEvent.VK_ESCAPE) {
					teclaEsc.load("res\\Menu\\teclaEsc2.png");
					
				}else if(codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN){
					
					if(contEngranagem1 == 2) {
						contEngranagem1 = 1;
					} else {contEngranagem1 ++;}
					
					engrenagem1.load("res\\engrenagem" + contEngranagem1 + ".png");
					
					if(codigo == KeyEvent.VK_UP) {
						if(contOpcoes == 1 && valorLeituraSave != 0) {
							contOpcoes = 3;
						}else if(contOpcoes == 0) {
							contOpcoes = 3;
						} else {contOpcoes --;}
						
						teclaCima.load("res\\Menu\\setaCima2.png");
						
					}else if(codigo == KeyEvent.VK_DOWN) {
						if(contOpcoes == 3 && valorLeituraSave != 0) {
							contOpcoes = 1;
						}else if(contOpcoes == 3) {
							contOpcoes = 0;
						} else {contOpcoes ++;}
						
						teclaBaixo.load("res\\Menu\\setaBaixo2.png");
						
					}
					
					bntContinuar.load("res\\Menu\\bntContinuar1.png");
					bntNovoJogo.load("res\\Menu\\bntNovoJogo1.png");
					bntManual.load("res\\Menu\\bntManual3.png");
					bntCreditos.load("res\\Menu\\bntCreditos3.png");
					
					switch (contOpcoes) {
						case 0:
							bntContinuar.load("res\\Menu\\bntContinuar2.png");
							break;
						case 1:
							bntNovoJogo.load("res\\Menu\\bntNovoJogo2.png");
							break;
						case 2:
							bntManual.load("res\\Menu\\bntManual4.png");
							break;
						case 3:
							bntCreditos.load("res\\Menu\\bntCreditos4.png");
							break;
					}
				} else if(codigo == KeyEvent.VK_Z && contOpcoes == 0) {
					
					contEngranagem2 = !contEngranagem2;
					engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
					
					teclaZ.load("res\\Menu\\teclaZ2.png");
					
					chamarTela1(false);
					
				} else if(codigo == KeyEvent.VK_Z && contOpcoes == 1) {
					
					contEngranagem2 = !contEngranagem2;
					engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
					
					teclaZ.load("res\\Menu\\teclaZ2.png");
	
					chamarTela1(true);
			        
				} else if(codigo == KeyEvent.VK_Z && contOpcoes == 2) {
					
					contEngranagem2 = !contEngranagem2;
					engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
					
					teclaZ.load("res\\Menu\\teclaZ2.png");
	
					janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
			        janelaPrincipal.remove(this);
			        telaManual = new Manual(contEngranagem2);
			        
			        telaManual.setTelaMenu(this);
			        
			        janelaPrincipal.add(telaManual);
			        janelaPrincipal.setTitle("ManualM");
			        janelaPrincipal.revalidate();
			       
				} else if(codigo == KeyEvent.VK_Z && contOpcoes == 3) {
					
					contEngranagem2 = !contEngranagem2;
					engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
					
					teclaZ.load("res\\Menu\\teclaZ2.png");
	
					janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
			        janelaPrincipal.remove(this);
			        telaCreditos = new Creditos(contEngranagem2);
			        
			        telaCreditos.setTelaMenu(this);
			        
			        janelaPrincipal.add(telaCreditos);
			        janelaPrincipal.setTitle("CreditosM");
			        janelaPrincipal.revalidate();
			      
				} else if(codigo == KeyEvent.VK_Z) {
					teclaZ.load("res\\Menu\\teclaZ2.png");
				}
			}
		}else {
			if(janelaPrincipal != null && (janelaPrincipal.getTitle() == "Escolha de Personagem" || janelaPrincipal.getTitle() == "Escolha de Adversário"  || janelaPrincipal.getTitle() == "Batalha"
					|| janelaPrincipal.getTitle() == "Manual1" || janelaPrincipal.getTitle() == "Manual2" || janelaPrincipal.getTitle() == "Manual3" 
					|| janelaPrincipal.getTitle() == "Creditos1" || janelaPrincipal.getTitle() == "Creditos2" || janelaPrincipal.getTitle() == "Creditos3")) {
				
				tela1.KeyPressed(tecla);
				
			} else if(janelaPrincipal != null && janelaPrincipal.getTitle() == "ManualM") {
				telaManual.KeyPressed(tecla);
			
			} else if(janelaPrincipal != null && janelaPrincipal.getTitle() == "CreditosM") {
				telaCreditos.KeyPressed(tecla);
				
			} else if(janelaPrincipal == null) {
				tela2.KeyPressed(tecla);
			}
			
		}
			
	}
	
	public void KeyReleased (java.awt.event.KeyEvent tecla){
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		int codigo = tecla.getKeyCode();
		
		if(!(janela != null && janela.getTitle() == "Menu")) {
			
			if(janelaPrincipal != null && (janelaPrincipal.getTitle() == "Escolha de Personagem" || janelaPrincipal.getTitle() == "Escolha de Adversário"  || janelaPrincipal.getTitle() == "Batalha" || janelaPrincipal.getTitle() == "Manual1" || janelaPrincipal.getTitle() == "Manual2" || janelaPrincipal.getTitle() == "Manual3")) {
				tela1.KeyReleased(tecla);
				
			} else if(janelaPrincipal != null && janelaPrincipal.getTitle() == "ManualM") {
				telaManual.KeyReleased(tecla);
				
			}
		} else {
			if(codigo == KeyEvent.VK_LEFT) {
				teclaEsquerda.load("res\\Menu\\setaEsquerda.png");

			}else if(codigo == KeyEvent.VK_RIGHT) {
				teclaDireita.load("res\\Menu\\setaDireita.png");

			}else if(codigo == KeyEvent.VK_UP) {
				teclaCima.load("res\\Menu\\setaCima.png");
				
			} else if(codigo == KeyEvent.VK_DOWN) {
				teclaBaixo.load("res\\Menu\\setaBaixo.png");
				
			} else if(codigo == KeyEvent.VK_Z) {
				teclaZ.load("res\\Menu\\teclaZ.png");
				
			}else if(codigo == KeyEvent.VK_X) {
				teclaX.load("res\\Menu\\teclaX.png");
				
			}else if(codigo == KeyEvent.VK_ESCAPE) {
				teclaEsc.load("res\\Menu\\teclaEsc.png");
			}
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX(), fundo2.getY(), this);
		
		// ---------------------------- opções do menu ------------------------------------
		
		graficos.drawImage(bntContinuar.getImagem(), bntContinuar.getX(), bntContinuar.getY(), this);
		graficos.drawImage(bntNovoJogo.getImagem(), bntNovoJogo.getX(), bntNovoJogo.getY(), this);
		graficos.drawImage(bntManual.getImagem(), bntManual.getX(), bntManual.getY(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getX(), bntCreditos.getY(), this);
		
		// ------------------------------------------- Controles ---------------------------------------------
		
		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getX(), teclaEsquerda.getY(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getX(), teclaDireita.getY(), this);
		graficos.drawImage(teclaCima.getImagem(), teclaCima.getX(), teclaCima.getY(), this);
		graficos.drawImage(teclaBaixo.getImagem(), teclaBaixo.getX(), teclaBaixo.getY(), this);
		
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getX(), teclaZ.getY(), this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getX(), teclaX.getY(), this);
		
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getX(), engrenagem1.getY(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getX(), engrenagem2.getY(), this);
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		
		graficos.drawImage(teclaEsc.getImagem(), teclaEsc.getX(), teclaEsc.getY(), this);
		
		// ---------------------------- save ------------------------------------

		graficos.drawImage(sombreadorDialogoAviso.getImagem(), sombreadorDialogoAviso.getX(), sombreadorDialogoAviso.getY(), this);
		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getX(), dialogoAviso.getY(), this);
		
		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl1 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		
		tl1.draw(graficos, txtDialogoAviso.getX(), txtDialogoAviso.getY());
		
		
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(valorLeituraSave != 0 && valorLeituraSave != -1 && contTempoMensagemErro < 141) {
			MostrarMensagemDeErro();
		}
		
		repaint();
		
	}
	
	
	public void chamarTela1(boolean NovoJogo) {
		
		janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
        janelaPrincipal.remove(this);
        tela1 = new Escolha_de_personagem(this, NovoJogo, contEngranagem2);
        janelaPrincipal.add(tela1);
        janelaPrincipal.setTitle("Escolha de Personagem");
        
        if(NovoJogo == false) {
        	salvar.LerDados();
        	tela1.chamarTela2(salvar.getAventureiro(), salvar.getVitorias());
        }
        
        janelaPrincipal.revalidate();
	}
	
	public void LimparTela1() {
		tela1 = null;
	}

	public void setContEngranagem2(boolean contEngranagem2) {
		this.contEngranagem2 = contEngranagem2;
		engrenagem2.load("res\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");	
	}
	
}
