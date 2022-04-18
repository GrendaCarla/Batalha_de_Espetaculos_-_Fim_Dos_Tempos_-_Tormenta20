package batalha_de_Espetaculos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
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
	private Salvar save;
	
	JFrame janelaPrincipal;
	
	// ------------------------------------ fundo contorno --------------------------------------
	
	private Icones_interativos fundo = new Icones_interativos(0, 0);
	private Icones_interativos tapaResto = new Icones_interativos(1234/2 - 4936/2, 640/2 - 2560/2);

	private Icones_interativos engrenagem1 = new Icones_interativos(-18, -8);
	private Icones_interativos engrenagem2 = new Icones_interativos(1120, -12);
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private Icones_interativos titulo = new Icones_interativos(1234/2 - 530/2, 20);
	
	private int contEngranagem1 = 1;
	private boolean contEngranagem2;
	
	private String caminho; 
	
	private Timer timer;
	
	// ------------------------------------------- animação ---------------------------------------------

	private int contAnimacao = 0;
	private int contAnimacaoAyla = 0;
	
	private boolean sentidoAnimacao = false;
	private boolean sentidoAnimacaoAyla = false;

	private Icones_interativos ignis1 = new Icones_interativos(26, 128);
	private Icones_interativos ignis2 = new Icones_interativos(1226, 128);
	private Icones_interativos kikiAriusRexthor = new Icones_interativos(0, 166);
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
	
	// ------------------------ imagens e textos do diálogo de aviso ------------------------------
	
	private Icones_interativos sombreadorDialogoAviso = new Icones_interativos(0, 0);
	private Icones_interativos dialogoAviso = new Icones_interativos(1234/2 - 706/2, 640/2 - 278/2 - 40);
	private Icones_interativos bntSimDialogoAviso = new Icones_interativos(dialogoAviso.getX() + 110, dialogoAviso.getY() + 180);
	private Icones_interativos bntNaoDialogoAviso  = new Icones_interativos(bntSimDialogoAviso.getX() + 370, bntSimDialogoAviso.getY());
	
	private Texto txtDialogoAviso = new Texto(dialogoAviso.getX() + 140, 548/2 - 50, " ");
	private Texto txtDialogoAviso2 = new Texto(dialogoAviso.getX() + 250, txtDialogoAviso.getY() + 50, " ");
	
	private Boolean bntSimNaoDialgoAviso = true;

	// ---------------------------- save ------------------------------------
	
	private TextLayout tl1, tl2;
	
	private Salvar salvar = new Salvar();
	int valorLeituraSave;
	private int contTempoMensagemErro = 0;
	
	/* ---------------------------------------------------------------------------------------- \
	|  							coloca as informações iniciais									|
	\ ---------------------------------------------------------------------------------------- */
	
	public Menu (boolean Engrenagem2, String Caminho) {
		contEngranagem2 = Engrenagem2;
		this.caminho = Caminho;
		
		fundo.load(caminho + "res\\fundo0.png");
		tapaResto.load(caminho + "res\\fundo2.png");
		
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

		txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 26));
		txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
		
		Restaurar();
		
		timer = new Timer(1, this); 
		timer.start();
	}
	
	public void MostrarMensagemDeErro() {
		
		contTempoMensagemErro ++;
		
		if(contTempoMensagemErro == 1) {
			txtDialogoAviso = new Texto(dialogoAviso.getX() + 180, 548/2 + 10, " ");
			
			txtDialogoAviso.setFonte(new Font("Arial", Font.PLAIN, 38));
			txtDialogoAviso.setCorTexto(new Color (235, 230, 233));
			
			sombreadorDialogoAviso.load(caminho + "res\\sombreador.png");
			dialogoAviso.load(caminho + "res\\mensagem aviso\\dialogo.png");
			txtDialogoAviso.setTexto("Save corrompido!!!");
			save.ApagarDados(caminho);
			
		} else if(contTempoMensagemErro == 100){
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
		
		dialogoAviso.setImagem(null);
		sombreadorDialogoAviso.setImagem(null);
		bntSimDialogoAviso.setImagem(null);
		bntNaoDialogoAviso.setImagem(null);
		
		txtDialogoAviso.setTexto(" ");
		txtDialogoAviso2.setTexto(" ");
		
	}

	/* ---------------------------------------------------------------------------------------- \
	|  									aviso novo jogo											|
	\ ---------------------------------------------------------------------------------------- */
	
	public void dialogoBntNovoJogo(int codigo) {

		if(dialogoAviso.getImagem() == null && codigo == KeyEvent.VK_Z) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			sombreadorDialogoAviso.load(caminho + "res\\sombreador.png");
			dialogoAviso.load(caminho + "res\\mensagem aviso\\dialogo.png");
			bntSimDialogoAviso.load(caminho + "res\\mensagem aviso\\bntSim1.png");
			bntNaoDialogoAviso.load(caminho + "res\\mensagem aviso\\bntNao2.png");
			bntSimNaoDialgoAviso = true;
			
			txtDialogoAviso.setTexto("O progresso anterior será apagado.");
			txtDialogoAviso2.setTexto("Deseja continuar?");
			
		}else if ((codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) && dialogoAviso.getImagem() != null) {
		
			if(contEngranagem1== 2) {
				contEngranagem1= 1;
			} else {contEngranagem1++;}
			
			engrenagem1.load(caminho + "res\\Engrenagens\\engrenagem" + contEngranagem1+ ".png");
			
			bntSimNaoDialgoAviso = !bntSimNaoDialgoAviso;
			bntSimDialogoAviso.load(caminho + "res\\mensagem aviso\\bntSim" + (bntSimNaoDialgoAviso == true ? "1" : "2") + ".png");
			bntNaoDialogoAviso.load(caminho + "res\\mensagem aviso\\bntNao" + (bntSimNaoDialgoAviso == true ? "2" : "1") + ".png");
		
		}else if(codigo == KeyEvent.VK_Z  || codigo == KeyEvent.VK_X && dialogoAviso.getImagem() != null) {
			
			contEngranagem2 = !contEngranagem2;
			engrenagem2.load(caminho + "res\\Engrenagens\\engrenagem" + (contEngranagem2 == false ? "3" : "4") + ".png");
			
			if(bntSimNaoDialgoAviso == false || codigo == KeyEvent.VK_X) {
				sombreadorDialogoAviso.setImagem(null);
				dialogoAviso.setImagem(null);
				bntSimDialogoAviso.setImagem(null);
				bntNaoDialogoAviso.setImagem(null);
				
				txtDialogoAviso.setTexto(" ");
				txtDialogoAviso2.setTexto(" ");
			}
			else {
				
				chamarTela1(true);
			}
		}
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
							dialogoBntNovoJogo(codigo);
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
				
			} else if(dialogoAviso.getImagem() != null) {
				dialogoBntNovoJogo(codigo);
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
		
		// ------------------------------------------- fundo ---------------------------------------------

		graficos.drawImage(fundo.getImagem(), fundo.getRedX(), fundo.getRedY(), fundo.getLarg(), fundo.getAlt(), this);
		
		graficos.drawImage(camada6.getImagem(), camada6.getRedX(), camada6.getRedY(), camada6.getLarg(), camada6.getAlt(), this);
		
		graficos.drawImage(camada51.getImagem(), camada51.getRedX(), camada51.getRedY(), camada51.getLarg(), camada51.getAlt(), this);
		graficos.drawImage(camada52.getImagem(), camada52.getRedX(), camada52.getRedY(), camada52.getLarg(), camada52.getAlt(), this);
		
		graficos.drawImage(camada41.getImagem(), camada41.getRedX(), camada41.getRedY(), camada41.getLarg(), camada41.getAlt(), this);
		graficos.drawImage(camada42.getImagem(), camada42.getRedX(), camada42.getRedY(), camada42.getLarg(), camada42.getAlt(), this);
		
		graficos.drawImage(camada11.getImagem(), camada11.getRedX(), camada11.getRedY(), camada11.getLarg(), camada11.getAlt(), this);

		graficos.drawImage(camada21.getImagem(), camada21.getRedX(), camada21.getRedY(), camada21.getLarg(), camada21.getAlt(), this);
		graficos.drawImage(camada22.getImagem(), camada22.getRedX(), camada22.getRedY(), camada22.getLarg(), camada22.getAlt(), this);
		
		graficos.drawImage(titulo.getImagem(), titulo.getRedX(), titulo.getRedY(), titulo.getLarg(), titulo.getAlt(), this);

		// ------------------------------------------- animação ---------------------------------------------

		graficos.drawImage(ignis1.getImagem(), ignis1.getRedX(), ignis1.getRedY(), ignis1.getLarg(), ignis1.getAlt(), this);
		graficos.drawImage(ignis2.getImagem(), ignis2.getRedX(), ignis2.getRedY(), ignis2.getLarg(), ignis2.getAlt(), this);
		
		graficos.drawImage(camada32.getImagem(), camada32.getRedX(), camada32.getRedY(), camada32.getLarg(), camada32.getAlt(), this);
		
		graficos.drawImage(kikiAriusRexthor.getImagem(), kikiAriusRexthor.getRedX(), kikiAriusRexthor.getRedY(), kikiAriusRexthor.getLarg(), kikiAriusRexthor.getAlt(), this);
		
		graficos.drawImage(ayla.getImagem(), ayla.getRedX(), ayla.getRedY(), ayla.getLarg(), ayla.getAlt(), this);
		graficos.drawImage(sombraAyla.getImagem(), sombraAyla.getRedX(), sombraAyla.getRedY(), sombraAyla.getLarg(), sombraAyla.getAlt(), this);

		graficos.drawImage(camada31.getImagem(), camada31.getRedX(), camada31.getRedY(), camada31.getLarg(), camada31.getAlt(), this);

		// ---------------------------- opções do menu ------------------------------------
		
		graficos.drawImage(bntContinuar.getImagem(), bntContinuar.getRedX(), bntContinuar.getRedY(), bntContinuar.getLarg(), bntContinuar.getAlt(), this);
		graficos.drawImage(bntNovoJogo.getImagem(), bntNovoJogo.getRedX(), bntNovoJogo.getRedY(), bntNovoJogo.getLarg(), bntNovoJogo.getAlt(), this);
		graficos.drawImage(bntManual.getImagem(), bntManual.getRedX(), bntManual.getRedY(), bntManual.getLarg(), bntManual.getAlt(), this);
		graficos.drawImage(bntCreditos.getImagem(), bntCreditos.getRedX(), bntCreditos.getRedY(), bntCreditos.getLarg(), bntCreditos.getAlt(), this);
		
		graficos.drawImage(camada12.getImagem(), camada12.getRedX(), camada12.getRedY(), camada12.getLarg(), camada12.getAlt(), this);
				
		// ------------------------------------------- Controles ---------------------------------------------
		
		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getRedX(), teclaEsquerda.getRedY(), teclaEsquerda.getLarg(), teclaEsquerda.getAlt(), this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getRedX(), teclaDireita.getRedY(), teclaDireita.getLarg(), teclaDireita.getAlt(), this);
		graficos.drawImage(teclaCima.getImagem(), teclaCima.getRedX(), teclaCima.getRedY(), teclaCima.getLarg(), teclaCima.getAlt(), this);
		graficos.drawImage(teclaBaixo.getImagem(), teclaBaixo.getRedX(), teclaBaixo.getRedY(), teclaBaixo.getLarg(), teclaBaixo.getAlt(), this);
		
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getRedX(), teclaZ.getRedY(), teclaZ.getLarg(), teclaZ.getAlt(), this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getRedX(), teclaX.getRedY(), teclaX.getLarg(), teclaX.getAlt(), this);
		
		graficos.drawImage(engrenagem1.getImagem(), engrenagem1.getRedX(), engrenagem1.getRedY(), engrenagem1.getLarg(), engrenagem1.getAlt(), this);
		graficos.drawImage(engrenagem2.getImagem(), engrenagem2.getRedX(), engrenagem2.getRedY(), engrenagem2.getLarg(), engrenagem2.getAlt(), this);
		graficos.drawImage(contorno.getImagem(), contorno.getRedX(), contorno.getRedY(), contorno.getLarg(), contorno.getAlt(), this);
		
		graficos.drawImage(teclaEsc.getImagem(), teclaEsc.getRedX(), teclaEsc.getRedY(), teclaEsc.getLarg(), teclaEsc.getAlt(), this);
		
		// ---------------------------- save ------------------------------------

		graficos.drawImage(sombreadorDialogoAviso.getImagem(), sombreadorDialogoAviso.getRedX(), sombreadorDialogoAviso.getRedY(), sombreadorDialogoAviso.getLarg(), sombreadorDialogoAviso.getAlt(), this);
		graficos.drawImage(dialogoAviso.getImagem(), dialogoAviso.getRedX(), dialogoAviso.getRedY(), dialogoAviso.getLarg(), dialogoAviso.getAlt(), this);
		graficos.drawImage(bntSimDialogoAviso.getImagem(), bntSimDialogoAviso.getRedX(), bntSimDialogoAviso.getRedY(), bntSimDialogoAviso.getLarg(), bntSimDialogoAviso.getAlt(), this);
		graficos.drawImage(bntNaoDialogoAviso.getImagem(), bntNaoDialogoAviso.getRedX(), bntNaoDialogoAviso.getRedY(), bntNaoDialogoAviso.getLarg(), bntNaoDialogoAviso.getAlt(), this);

		graficos.setColor(txtDialogoAviso.getCorTexto());
		tl1 = new TextLayout(txtDialogoAviso.getTexto(), txtDialogoAviso.getFonte(), frc);
		tl2 = new TextLayout(txtDialogoAviso2.getTexto(), txtDialogoAviso.getFonte(), frc);
		
		tl1.draw(graficos, txtDialogoAviso.getRedX(), txtDialogoAviso.getRedY());
		tl2.draw(graficos, txtDialogoAviso2.getRedX(), txtDialogoAviso2.getRedY());
		
		graficos.drawImage(tapaResto.getImagem(), tapaResto.getRedX(), tapaResto.getRedY(), tapaResto.getLarg(), tapaResto.getAlt(), this);
		
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		
		if(janela != null && janela.getTitle() == "Menu") {

			if(valorLeituraSave != 0 && valorLeituraSave != -1 && contTempoMensagemErro < 141) {
				MostrarMensagemDeErro();
			}
			
			Animar();
		}
		
		repaint();
		
	}
	
	public void Animar() {
		
		// ------------------------------------------- fundo ---------------------------------------------

		camada21.setX((camada21.getX() <= -6000 ? camada22.getX() + 5985 : camada21.getX() - 15));
		camada22.setX((camada22.getX() <= -6000 ? camada21.getX() + 5985 : camada22.getX() - 15));
		
		camada31.setX(camada21.getX() < -20 ? camada21.getX() : camada22.getX());
		camada32.setX(camada21.getX() < -20 ? camada21.getX() : camada22.getX());

		camada41.setX((camada41.getX() <= -4000 ? camada42.getX() + 3996 : camada41.getX() - 4));
		camada42.setX((camada42.getX() <= -4000 ? camada41.getX() + 3996 : camada42.getX() - 4));
		
		camada51.setX((camada51.getX() <= -2000 ? camada52.getX() + 1998 : camada51.getX() - 2));
		camada52.setX((camada52.getX() <= -2000 ? camada51.getX() + 1998 : camada52.getX() - 2));
		
		// ----------------------------------------------------------------------------------------

		
		ignis1.setX( (ignis1.getX() <= -300 ? ignis2.getX() + 1200 : ignis1.getX() - 1));
		ignis2.setX( (ignis2.getX() <= -300 ? ignis1.getX() + 1200 : ignis2.getX() - 1));

		if(sentidoAnimacao == true) {contAnimacao --;} else {contAnimacao++;}
		
		if(contAnimacao == 0 || contAnimacao == 4) {sentidoAnimacao = !sentidoAnimacao;}
		
		if(contAnimacao % 2 == 0) {
			
			ignis1.load(caminho + "res\\Bonequinho\\ignis" + (contAnimacao == 4 ? 4 : (contAnimacao == 2 ? 3 : 2)) + ".png");
			ignis2.load(caminho + "res\\Bonequinho\\ignis" + (contAnimacao == 4 ? 4 : (contAnimacao == 2 ? 3 : 2)) + ".png");

			kikiAriusRexthor.load(caminho + "res\\Bonequinho\\kiki arius rexthor" + (contAnimacao == 4 ? 4 : (contAnimacao == 2 ? 3 : 2)) + ".png");
		}
		
		// ------------------------------------------ ayla ---------------------------------

		
		if(sentidoAnimacaoAyla == true) {contAnimacaoAyla --;} else {contAnimacaoAyla++;}
		
		if(contAnimacaoAyla == 0 || contAnimacaoAyla == 21) {sentidoAnimacaoAyla = !sentidoAnimacaoAyla;}
		
		if( contAnimacaoAyla % 3 == 0) {
			ayla.load(caminho + "res\\Bonequinho\\ayla" + (contAnimacaoAyla == 21 || contAnimacaoAyla == 15 ? 4 : (contAnimacaoAyla == 18 || contAnimacaoAyla == 12 || contAnimacaoAyla == 6 || contAnimacaoAyla == 0 ? 3 : 2)) + ".png");
			sombraAyla.load(caminho + "res\\Bonequinho\\ayla" + (contAnimacaoAyla == 21 || contAnimacaoAyla == 15 ? 13 : 12) + ".png");
		}
		//10 + 8 + 3 + 12 = 33     8 + 6 = 15
		if((sentidoAnimacaoAyla == true && contAnimacaoAyla <= 12) || (sentidoAnimacaoAyla == false && contAnimacaoAyla <= 14)) {
			ayla.setY(ayla.getY() + (contAnimacaoAyla == 1 || contAnimacaoAyla == 2 ? 2 : (contAnimacaoAyla == 0 ? 3 : 1)));
			
			if(contAnimacaoAyla % 2 == 0) {
				ayla.setX(ayla.getX() - (contAnimacaoAyla == 0 ? 2 : 1));
				sombraAyla.setX(sombraAyla.getX() -(contAnimacaoAyla == 0 ? 2 : 1));	
			}
			//9 x 3 = 27     9
		} else if((sentidoAnimacaoAyla == false && contAnimacaoAyla >= 15 && contAnimacaoAyla <= 17) || (sentidoAnimacaoAyla == true && contAnimacaoAyla >= 19 && contAnimacaoAyla <= 21) || (sentidoAnimacaoAyla == true && contAnimacaoAyla >= 13 && contAnimacaoAyla <= 15)) {
			ayla.setY(ayla.getY() - 3);
			
			ayla.setX(ayla.getX() + 1);
			sombraAyla.setX(sombraAyla.getX() + 1);
		}
		// 3 + 3 = 6   -> 33      6    -> 15
		if((sentidoAnimacaoAyla == false && contAnimacaoAyla >= 18 && contAnimacaoAyla <= 20) || (sentidoAnimacaoAyla == true && contAnimacaoAyla >= 16 && contAnimacaoAyla <= 18)) {
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
