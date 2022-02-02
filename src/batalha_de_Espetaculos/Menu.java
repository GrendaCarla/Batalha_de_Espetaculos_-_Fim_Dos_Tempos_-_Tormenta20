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
import java.util.*;

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
	
	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1120, -12);
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private Icones_interativos titulo = new Icones_interativos(1234/2 - 530/2, 20);
	
	private int contEngranagem1 = 1;
	private boolean contEngranagem2;
	
	private String caminho; 
	
	private Timer timer;
	
	// ------------------------------------------- animação ---------------------------------------------

	private int contAnimacao = 6;
	private int contAnimacaoAyla = 0;
	
	private boolean sentidoAnimacao = false;
	private boolean sentidoAnimacaoAyla = false;

	private Icones_interativos ignis1 = new Icones_interativos(26, 128);
	private Icones_interativos ignis2 = new Icones_interativos(1226, 128);
	
	private Icones_interativos kikiAriusRexthor = new Icones_interativos(10, 166);
	
	private Icones_interativos ayla = new Icones_interativos(900, 50);
	private Icones_interativos sombraAyla = new Icones_interativos(ayla.getX() - 20, 290);


	// ------------------------------------------- fundo ---------------------------------------------

	private Icones_interativos camada11 = new Icones_interativos(0, -10);
	private Icones_interativos camada12 = new Icones_interativos(0, -10);
	
	private Icones_interativos camada21 = new Icones_interativos(0, -10);
	private Icones_interativos camada22 = new Icones_interativos(5992, camada21.getY());

	private Icones_interativos camada31 = new Icones_interativos(0, -10);
	private Icones_interativos camada32 = new Icones_interativos(0, -10);

	private Icones_interativos camada41 = new Icones_interativos(0, -10);
	private Icones_interativos camada42 = new Icones_interativos(3992, camada41.getY());
	
	private Icones_interativos camada51 = new Icones_interativos(0, -10);
	private Icones_interativos camada52 = new Icones_interativos(1992, camada51.getY());
	
	private Icones_interativos camada6 = new Icones_interativos(0, -10);
	
	
	// ------------------------------------------- Controles ---------------------------------------------
	
	private Icones_interativos teclaEsquerda = new Icones_interativos(1234 - 166, 640 - 116);
	private Icones_interativos teclaBaixo = new Icones_interativos(teclaEsquerda.getX() + 40, teclaEsquerda.getY() + 46);
	private Icones_interativos teclaDireita = new Icones_interativos(teclaBaixo.getX() + 46, teclaEsquerda.getY());
	private Icones_interativos teclaCima = new Icones_interativos(teclaBaixo.getX(), teclaBaixo.getY() - 86);
	private Icones_interativos teclaZ = new Icones_interativos(20 + 20, 640 - 20 - 60);
	private Icones_interativos teclaX = new Icones_interativos(teclaZ.getX() + 60, teclaZ.getY());
	private Icones_interativos teclaEsc = new Icones_interativos(16, 16);
	
	// ---------------------------- opções do menu ------------------------------------
	
	private Icones_interativos bntContinuar = new Icones_interativos(1234/2 - ((159 * 4) + 120)/2, 528);
	private Icones_interativos bntNovoJogo = new Icones_interativos(bntContinuar.getX() + 159 + 40, bntContinuar.getY());
	private Icones_interativos bntManual = new Icones_interativos(bntNovoJogo.getX() + 159 + 40, bntContinuar.getY());
	private Icones_interativos bntCreditos = new Icones_interativos(bntManual.getX() + 159 + 36, bntContinuar.getY());
	
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
	
	public Menu (boolean Engrenagem2, String Caminho) {
		contEngranagem2 = Engrenagem2;
		this.caminho = Caminho;
		
		ImageIcon referencia = new ImageIcon(caminho + "res\\fundo0.png");
		fundo = referencia.getImage();
		
		
		/*try {
			BufferedImage imagem;
			imagem = ImageIO.read(new File(caminho + "res\\Engrenagens\\engrenagem3.png"));
			imagemMenor = imagem.getScaledInstance(96, 96, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */   
		
	    
		
		engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem1.png");		
		contorno.load(caminho + "res\\contorno.png");
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
		
		titulo.load(caminho + "res\\Menu principal\\titulo.png");

		valorLeituraSave = salvar.LerDados(caminho);
		
		// ------------------------------------------- animação ---------------------------------------------

		ignis1.load(caminho + "res\\Bonequinho\\ignis2.png");
		ignis2.load(caminho + "res\\Bonequinho\\ignis2.png");
		kikiAriusRexthor.load(caminho + "res\\Bonequinho\\kiki arius rexthor2.png");
		ayla.load(caminho + "res\\Bonequinho\\ayla2.png");
		sombraAyla.load(caminho + "res\\Bonequinho\\ayla12.png");
		
		// ------------------------------------------- fundo ---------------------------------------------

		camada11.load(caminho + "res\\Fundo bonequinho\\camada0.png");
		camada12.load(caminho + "res\\Menu principal\\camada1.png");

		camada21.load(caminho + "res\\Fundo bonequinho\\camada2.png");
		camada22.load(caminho + "res\\Fundo bonequinho\\camada2.png");
		
		camada31.load(caminho + "res\\Fundo bonequinho\\camada3.png");
		camada32.load(caminho + "res\\Fundo bonequinho\\camada4.png");
		
		camada41.load(caminho + "res\\Fundo bonequinho\\camada5.png");
		camada42.load(caminho + "res\\Fundo bonequinho\\camada5.png");
		
		camada51.load(caminho + "res\\Fundo bonequinho\\camada6.png");
		camada52.load(caminho + "res\\Fundo bonequinho\\camada6.png");
		
		camada6.load(caminho + "res\\Fundo bonequinho\\camada7.png");
		
		// ------------------------------------------- Controles ---------------------------------------------

		teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda1.png");
		teclaDireita.load(caminho + "res\\Teclado\\setaDireita1.png");
		teclaCima.load(caminho + "res\\Teclado\\setaCima1.png");
		teclaBaixo.load(caminho + "res\\Teclado\\setaBaixo1.png");
		
		teclaZ.load(caminho + "res\\Teclado\\teclaZ1.png");
		teclaX.load(caminho + "res\\Teclado\\teclaX1.png");
		teclaEsc.load(caminho + "res\\Teclado\\teclaEsc1.png");
		
		// ---------------------------- save ------------------------------------

		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 38));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		
		Restaurar();
		
		timer = new Timer(50, this); 
		timer.start();
	}
	
	public void MostrarMensagemDeErro() {
		
		contTempoMensagemErro ++;
		
		if(contTempoMensagemErro > 0 && contTempoMensagemErro < 140) {
			sombreadorDialogoAviso.load(caminho + "res\\sombreador.png");
			dialogoAviso.load(caminho + "res\\mensagem aviso\\dialogo.png");
			txtDialogoAviso.setTexto("Save corrompido!!!");
		} else {
			sombreadorDialogoAviso.setImagem(null);
			dialogoAviso.setImagem(null);
			txtDialogoAviso.setTexto(" ");
		}
	}
	
	public void Restaurar() {
		
		if(valorLeituraSave == 0) {
			bntContinuar.load(caminho + "res\\Menu principal\\bntContinuar2.png");
			bntNovoJogo.load(caminho + "res\\Menu principal\\bntNovoJogo1.png");
			contOpcoes = 0;
		} else {
			bntContinuar.load(caminho + "res\\Menu principal\\bntContinuar1.png");
			bntNovoJogo.load(caminho + "res\\Menu principal\\bntNovoJogo2.png");
			contOpcoes = 1;
		}
		
		bntManual.load(caminho + "res\\Menu principal\\bntManual1.png");
		bntCreditos.load(caminho + "res\\Menu principal\\bntCreditos1.png");
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
				if(codigo == KeyEvent.VK_UP) {
					teclaCima.load(caminho + "res\\Teclado\\setaCima2.png");
	
				}else if(codigo == KeyEvent.VK_DOWN) {
					teclaBaixo.load(caminho + "res\\Teclado\\setaBaixo2.png");
	
				}else if(codigo == KeyEvent.VK_X) {
					teclaX.load(caminho + "res\\Teclado\\teclaX2.png");
	
				}else if(codigo == KeyEvent.VK_ESCAPE) {
					teclaEsc.load(caminho + "res\\Teclado\\teclaEsc2.png");
					
				}else if(codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT){
					
					if(contEngranagem1 == 2) {
						contEngranagem1 = 1;
					} else {contEngranagem1 ++;}
					
					engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1 + ".png");
					
					if(codigo == KeyEvent.VK_LEFT) {
						if(contOpcoes == 1 && valorLeituraSave != 0) {
							contOpcoes = 3;
						}else if(contOpcoes == 0) {
							contOpcoes = 3;
						} else {contOpcoes --;}
						
						teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda2.png");
						
					}else if(codigo == KeyEvent.VK_RIGHT) {
						if(contOpcoes == 3 && valorLeituraSave != 0) {
							contOpcoes = 1;
						}else if(contOpcoes == 3) {
							contOpcoes = 0;
						} else {contOpcoes ++;}
						
						teclaDireita.load(caminho + "res\\Teclado\\setaDireita2.png");
						
					}
					
					bntContinuar.load(caminho + "res\\Menu principal\\bntContinuar1.png");
					bntNovoJogo.load(caminho + "res\\Menu principal\\bntNovoJogo1.png");
					bntManual.load(caminho + "res\\Menu principal\\bntManual1.png");
					bntCreditos.load(caminho + "res\\Menu principal\\bntCreditos1.png");
					
					switch (contOpcoes) {
						case 0:
							bntContinuar.load(caminho + "res\\Menu principal\\bntContinuar2.png");
							break;
						case 1:
							bntNovoJogo.load(caminho + "res\\Menu principal\\bntNovoJogo2.png");
							break;
						case 2:
							bntManual.load(caminho + "res\\Menu principal\\bntManual2.png");
							break;
						case 3:
							bntCreditos.load(caminho + "res\\Menu principal\\bntCreditos2.png");
							break;
					}
				} else if(codigo == KeyEvent.VK_Z) {
					
					contEngranagem2 = !contEngranagem2;
					engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
					
					switch (contOpcoes) {
						case 0:
							chamarTela1(false);
							break;
						case 1:
							chamarTela1(true);
							break;
						case 2:
							janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
					        janelaPrincipal.remove(this);
					        telaManual = new Manual(contEngranagem2, caminho);
					        
					        telaManual.setTelaMenu(this);
					        
					        janelaPrincipal.add(telaManual);
					        janelaPrincipal.setTitle("ManualM");
					        janelaPrincipal.revalidate();
					        break;
						case 3:
							janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
					        janelaPrincipal.remove(this);
					        telaCreditos = new Creditos(contEngranagem2, caminho);
					        
					        telaCreditos.setTelaMenu(this);
					        
					        janelaPrincipal.add(telaCreditos);
					        janelaPrincipal.setTitle("CreditosM");
					        janelaPrincipal.revalidate();
					        break;
					}
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
			
			switch (codigo) {
				case KeyEvent.VK_LEFT:
					teclaEsquerda.load(caminho + "res\\Teclado\\setaEsquerda1.png");
					break;
				case KeyEvent.VK_RIGHT:
					teclaDireita.load(caminho + "res\\Teclado\\setaDireita1.png");
					break;
				case KeyEvent.VK_UP:
					teclaCima.load(caminho + "res\\Teclado\\setaCima1.png");
					break;
				case KeyEvent.VK_DOWN:
					teclaBaixo.load(caminho + "res\\Teclado\\setaBaixo1.png");
					break;
				case KeyEvent.VK_X:
					teclaX.load(caminho + "res\\Teclado\\teclaX1.png");
					break;
				case KeyEvent.VK_ESCAPE:
					teclaEsc.load(caminho + "res\\Teclado\\teclaEsc1.png");
					break;
			}
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		FontRenderContext frc = graficos.getFontRenderContext();
		
		graficos.drawImage(fundo, 0, 0, null);
		
		// ------------------------------------------- fundo ---------------------------------------------

		graficos.drawImage(camada6.getImagem(), camada6.getX(), camada6.getY(), this);
		
		graficos.drawImage(camada51.getImagem(), camada51.getX(), camada51.getY(), this);
		graficos.drawImage(camada52.getImagem(), camada52.getX(), camada52.getY(), this);
		
		graficos.drawImage(camada41.getImagem(), camada41.getX(), camada41.getY(), this);
		graficos.drawImage(camada42.getImagem(), camada42.getX(), camada42.getY(), this);
		
		graficos.drawImage(camada11.getImagem(), camada11.getX(), camada11.getY(), this);

		graficos.drawImage(camada21.getImagem(), camada21.getX(), camada21.getY(), this);
		graficos.drawImage(camada22.getImagem(), camada22.getX(), camada22.getY(), this);
		
		graficos.drawImage(titulo.getImagem(), titulo.getX(), titulo.getY(), this);

		// ------------------------------------------- animação ---------------------------------------------

		graficos.drawImage(ignis1.getImagem(), ignis1.getX(), ignis1.getY(), this);
		graficos.drawImage(ignis2.getImagem(), ignis2.getX(), ignis2.getY(), this);
		
		graficos.drawImage(camada32.getImagem(), camada32.getX(), camada32.getY(), this);
		
		graficos.drawImage(kikiAriusRexthor.getImagem(), kikiAriusRexthor.getX(), kikiAriusRexthor.getY(), this);
		
		graficos.drawImage(ayla.getImagem(), ayla.getX(), ayla.getY(), this);
		graficos.drawImage(sombraAyla.getImagem(), sombraAyla.getX(), sombraAyla.getY(), this);

		graficos.drawImage(camada31.getImagem(), camada31.getX(), camada31.getY(), this);

		// ---------------------------- opções do menu ------------------------------------
		
		graficos.drawImage(bntContinuar.getImagem(), bntContinuar.getX(), bntContinuar.getY(), this);
		graficos.drawImage(bntNovoJogo.getImagem(), bntNovoJogo.getX(), bntNovoJogo.getY(), this);
		graficos.drawImage(bntManual.getImagem(), bntManual.getX(), bntManual.getY(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getX(), bntCreditos.getY(), this);
		
		graficos.drawImage(camada12.getImagem(), camada12.getX(), camada12.getY(), this);
				
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
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		if(valorLeituraSave != 0 && valorLeituraSave != -1 && contTempoMensagemErro < 141) {
			MostrarMensagemDeErro();
		}
		
		if(janela != null && janela.getTitle() == "Menu") {
			Animar();
		}
		
		repaint();
		
	}
	
	public void Animar() {
		
		// ------------------------------------------- fundo ---------------------------------------------

		camada21.setX((camada21.getX() <= -6000 ? camada22.getX() + 5992 : camada21.getX() - 6));
		camada22.setX((camada22.getX() <= -6000 ? camada21.getX() + 5992 : camada22.getX() - 6));
		
		camada31.setX(camada21.getX() < -20 ? camada21.getX() : camada22.getX());
		camada32.setX(camada21.getX() < -20 ? camada21.getX() : camada22.getX());

		camada41.setX((camada41.getX() <= -4000 ? camada42.getX() + 3992 : camada41.getX() - 3));
		camada42.setX((camada42.getX() <= -4000 ? camada41.getX() + 3992 : camada42.getX() - 3));
		
		camada51.setX((camada51.getX() <= -2000 ? camada52.getX() + 1992 : camada51.getX() - 1));
		camada52.setX((camada52.getX() <= -2000 ? camada51.getX() + 1992 : camada52.getX() - 1));
		
		// ----------------------------------------------------------------------------------------

		
		ignis1.setX( (ignis1.getX() <= -300 ? ignis2.getX() + 1200 : ignis1.getX() - 1));
		ignis2.setX( (ignis2.getX() <= -300 ? ignis1.getX() + 1200 : ignis2.getX() - 1));

		
		if(sentidoAnimacao == true) {contAnimacao --;} else {contAnimacao++;}
		
		if(contAnimacao == 4 || contAnimacao == 12) {sentidoAnimacao = !sentidoAnimacao;}
		
		if(contAnimacao % 4 == 0) {
			
			ignis1.load(caminho + "res\\Bonequinho\\ignis" + (contAnimacao == 12 ? 4 : (contAnimacao == 8 ? 3 : 2)) + ".png");
			ignis2.load(caminho + "res\\Bonequinho\\ignis" + (contAnimacao == 12 ? 4 : (contAnimacao == 8 ? 3 : 2)) + ".png");

			kikiAriusRexthor.load(caminho + "res\\Bonequinho\\kiki arius rexthor" + (contAnimacao == 12 ? 4 : (contAnimacao == 8 ? 3 : 2)) + ".png");
		}
		
		// ------------------------------------------ ayla ---------------------------------

		
		if(sentidoAnimacaoAyla == true) {contAnimacaoAyla --;} else {contAnimacaoAyla++;}
		
		if(contAnimacaoAyla == 0 || contAnimacaoAyla == 20) {sentidoAnimacaoAyla = !sentidoAnimacaoAyla;}
		
		if(contAnimacaoAyla == 0 || contAnimacaoAyla % 5 == 0) {
			ayla.load(caminho + "res\\Bonequinho\\ayla" + (contAnimacaoAyla == 15 ? 4 : (contAnimacaoAyla == 10 || contAnimacaoAyla == 0 ? 3 : 2)) + ".png");
			sombraAyla.load(caminho + "res\\Bonequinho\\ayla" + (contAnimacaoAyla == 15 ? 12 : 13) + ".png");
		}
		
		if(contAnimacaoAyla >= 0 && contAnimacaoAyla <= 10) {
			ayla.setY(ayla.getY() + 1);
			
			if(contAnimacaoAyla <= 6) {
				ayla.setX(ayla.getX() - 1);
				sombraAyla.setX(sombraAyla.getX() - 1);	
			}
			
		} else if((sentidoAnimacaoAyla == true && contAnimacaoAyla >= 12 && contAnimacaoAyla <= 15) || (sentidoAnimacaoAyla == false && contAnimacaoAyla >= 15 && contAnimacaoAyla <= 18)) {
			ayla.setY(ayla.getY() - 2);
			ayla.setX(ayla.getX() + 1);
			
			sombraAyla.setX(sombraAyla.getX() + 1);
		}
		
		if(contAnimacaoAyla >= 18) {
			ayla.setY(ayla.getY() - 1);
			ayla.setX(ayla.getX() + 1);
			
			sombraAyla.setX(sombraAyla.getX() + 1);
		}
		
	}
	
	
	public void chamarTela1(boolean NovoJogo) {
		
		janelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
        janelaPrincipal.remove(this);
        tela1 = new Escolha_de_personagem(this, NovoJogo, contEngranagem2, caminho);
        janelaPrincipal.add(tela1);
        janelaPrincipal.setTitle("Escolha de Personagem");
        
        if(NovoJogo == false) {
        	salvar.LerDados(caminho);
        	tela1.chamarTela2(salvar.getAventureiro(), salvar.getVitorias());
        }
        
        janelaPrincipal.revalidate();
	}
	
	public void LimparTela1() {
		tela1 = null;
	}

	public void setContEngranagem2(boolean contEngranagem2) {
		this.contEngranagem2 = contEngranagem2;
		engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");	
	}
	
}
