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

public class Manual extends JPanel implements ActionListener {
	
	private Escolha_de_personagem tela1;
	private Escolha_de_adversario tela2;
	private Batalha tela3;
	private Menu telaMenu;
	
	JFrame janelaPrincipal;
	
	private Image fundo;
	private Icones_interativos fundo2 = new Icones_interativos(0, 0);
	private Timer timer;
	private Icones_interativos contorno = new Icones_interativos(0, 0);
	
	private TextLayout tl1, tl2, tl3, tl4, tl5, tl6, tl7, tl8, tl9, tl10, tl11, tl12, tl13,
	tl14,  tl15, tl16, tl17, tl18, tl19, tl20, tl21, tl22, tl23, tl24, tl25, tl26;
	
	/* ------------------------------- Barra de rolagem ---------------------------------*/
	
	private int rolagemTela = 0;
	
	private Icones_interativos bntBarraBaixo = new Icones_interativos(1180, 590);
	private Icones_interativos bntBarraCima = new Icones_interativos(1180, 32);
	
	
	/*
	 Controles
	 batalha:
	  5 turnos
	  ataques
	  apelo e interferencia
	  descrição apelo
	  
	  campo de batalha ordem de jogada, apelos ganhos e barra apelo
	  
	  interferencia de chefe
	  interferencia por ataque repetido
	  
	 */
	
	/* ------------------------------- Controles ---------------------------------*/
	
	private Icones_interativos teclaEsquerda = new Icones_interativos(268, 104);
	private Icones_interativos teclaBaixo = new Icones_interativos(teclaEsquerda.getX() + 40, teclaEsquerda.getY() + 46);
	private Icones_interativos teclaDireita = new Icones_interativos(teclaBaixo.getX() + 46, teclaEsquerda.getY());
	private Icones_interativos teclaCima = new Icones_interativos(teclaBaixo.getX(), teclaBaixo.getY() - 86);
	private Icones_interativos teclaZ = new Icones_interativos(teclaDireita.getX() + 186, teclaDireita.getY());
	private Icones_interativos teclaX = new Icones_interativos(teclaZ.getX() + 200, teclaZ.getY());
	private Icones_interativos teclaEsc = new Icones_interativos(teclaX.getX() + 190, teclaX.getY());

	
	private Texto txtLn1 = new Texto(60, 80, "Controles");
	private Texto txtLn2 = new Texto(270, 220, "Direcionais");
	private Texto txtLn3 = new Texto(txtLn2.getX() + 230, txtLn2.getY() - 30, "Selecionar");
	private Texto txtLn4 = new Texto(txtLn3.getX() + 228, txtLn3.getY(), "Voltar");
	private Texto txtLn5 = new Texto(txtLn4.getX() + 201, txtLn4.getY(), "Menu");
	
	private int contTempoControles = 0;
	
	/* ------------------------------- Batalha ---------------------------------*/
	
	private Texto txtLn6 = new Texto(60, 300, "Batalhas");
	private Texto txtLn7 = new Texto(90, txtLn6.getY() + 80, "* As batalhas são divididas em cinco turnos.");
	private Texto txtLn8 = new Texto(txtLn7.getX(), txtLn7.getY() + 44, "* Em cada turno você escolhe uma apresentação para ganhar");
	private Texto txtLn9 = new Texto(txtLn7.getX() + 8, txtLn8.getY() + 25, " pontos com a platéia ou prejudicar seus adversários.");
	
		/* ------------------------------- Nome apelo ---------------------------------*/

	private Icones_interativos nomeHabilidade1 = new Icones_interativos(1234 - 330 - 80, 300);
	private Icones_interativos nomeHabilidade2 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade1.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade3 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade2.getY() + 54 + 4);
	private Icones_interativos nomeHabilidade4 = new Icones_interativos(nomeHabilidade1.getX(), nomeHabilidade3.getY() + 54 + 4);
	
	private int contTempoNomeApelo = 0;
	private Texto nomeApelo1, nomeApelo2, nomeApelo3, nomeApelo4;
	
		/* ------------------------------- info apelo ---------------------------------*/

	private Texto txtLn10 = new Texto(txtLn7.getX(), 590, "* Os apelos são pontos ganhos pelas apresentações. Quem obtem mais apelos na rodada fica em");
	private Texto txtLn11 = new Texto(txtLn7.getX() + 14, txtLn10.getY() + 25, "1º lugar no próximo turno e quem obtem mais apelos durante toda a batalha ganha a partida.");

	private Texto txtLn12 = new Texto(txtLn7.getX(), txtLn11.getY() + 44, "* As interferencias são pontos negativos usados nos seus adversários para faze-los perderem");
	private Texto txtLn13 = new Texto(txtLn7.getX() + 14, txtLn12.getY() + 25, "os apelos ganhados no turno atual ou nos anteriores.");

	private Icones_interativos apelo = new Icones_interativos(1234/2 - 860/2, 730);

	private Icones_interativos tipoDoApelo = new Icones_interativos(apelo.getX() + 8, apelo.getY() + 4);
	
	private Texto apeloQuantidade = new Texto(apelo.getX() + 136, apelo.getY() + 54/2 + 8, "Apelo:");
	
	private Icones_interativos apeloApelo1 = new Icones_interativos(apeloQuantidade.getX() + 65, apelo.getY() + 54/2 - 5);
	private Icones_interativos apeloApelo2 = new Icones_interativos(apeloApelo1.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo3 = new Icones_interativos(apeloApelo2.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo4 = new Icones_interativos(apeloApelo3.getX() + 25, apeloApelo1.getY());
	private Icones_interativos apeloApelo5 = new Icones_interativos(apeloApelo4.getX() + 25, apeloApelo1.getY());
	
	private Texto InterferenciaQuantidade = new Texto(apeloQuantidade.getX() + 330, apeloQuantidade.getY(), "Interferencia:");
	
	private Icones_interativos apeloInterf1 = new Icones_interativos(InterferenciaQuantidade.getX() + 126, apelo.getY() + 54/2 - 5);
	private Icones_interativos apeloInterf2 = new Icones_interativos(apeloInterf1.getX() + 25, apeloInterf1.getY());
	
	private int contTempoInfoApelo = 0;
	
		// ----------------------- Descrição -----------------------------------

	private Texto txtLn14 = new Texto(txtLn7.getX(), 850, "* As descrições das apresentações são puramente enrrolações. A unica coisa importante é a ultima linha colorida,");
	private Texto txtLn15 = new Texto(txtLn7.getX() + 14, txtLn14.getY() + 25, "nela é descrito o efeito das apresentações que possuem interferencias.");	
	
	private Icones_interativos descricao = new Icones_interativos(1234/2 - 860/2, txtLn15.getY() + 40);
	
	private Texto textoDescricao1, textoDescricao2, textoDescricao3, textoDescricao4, textoDescricao5;
	
	private int contTempoDescricao = 1;
	private String conteudoDescricao = "Com sua espetacular e refinada técnica de propagar historias particulares"
			+ " você é capaz deretirar o primeiro colocado do seu pedestal causando uma alta interferência nele."
			+ "Este poder afeta o primeiro colocado.";
	
	
	
	
	public Manual () {
		ImageIcon referencia = new ImageIcon("res\\fundo0.png");
		fundo = referencia.getImage();
		fundo2.load("res\\Manual\\fundo.png");
		contorno.load("res\\contorno.png");
		
		/* ------------------------------- Barra de rolagem ---------------------------------*/

		bntBarraBaixo.load("res\\Manual\\teclaBaixo.png");
		bntBarraCima.load("res\\Manual\\teclaCima.png");
		
		/* ------------------------------- Controles ---------------------------------*/
		
		teclaEsquerda.load("res\\Menu\\setaEsquerda.png");
		teclaDireita.load("res\\Menu\\setaDireita.png");
		teclaCima.load("res\\Menu\\setaCima.png");
		teclaBaixo.load("res\\Menu\\setaBaixo.png");
		
		teclaZ.load("res\\Menu\\teclaZ.png");
		teclaX.load("res\\Menu\\teclaX.png");
		teclaEsc.load("res\\Menu\\teclaEsc.png");
		
		txtLn1.setFonte(new Font("Arial", Font.BOLD, 30));
		txtLn1.setCorTexto(new Color (235, 230, 233));
		txtLn2.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn3.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn4.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn5.setFonte(new Font("Arial", Font.PLAIN, 24));
		
		/* ------------------------------- Batalha ---------------------------------*/
		
		nomeHabilidade1.load("res\\batalha\\nomeHabilidadeSelecionado.png");
		nomeHabilidade2.load("res\\batalha\\nomeHabilidade.png");
		nomeHabilidade3.load("res\\batalha\\nomeHabilidade.png");
		nomeHabilidade4.load("res\\batalha\\nomeHabilidade.png");
		
		nomeApelo1 = new Texto(nomeHabilidade1.getX() + 20, nomeHabilidade1.getY() + 54/2 + 8, "Tapa em puristas");
		nomeApelo2 = new Texto(nomeHabilidade2.getX() + 20, nomeHabilidade2.getY() + 54/2 + 8, "Ignis bonitão");
		nomeApelo3 = new Texto(nomeHabilidade3.getX() + 20, nomeHabilidade3.getY() + 54/2 + 8, "Provocação petulante");
		nomeApelo4 = new Texto(nomeHabilidade4.getX() + 20, nomeHabilidade4.getY() + 54/2 + 8, "Meca-Rito");
		
		txtLn6.setFonte(new Font("Arial", Font.BOLD, 30));
		txtLn7.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn8.setFonte(new Font("Arial", Font.PLAIN, 24));
		txtLn9.setFonte(new Font("Arial", Font.PLAIN, 24));
		
		/* ------------------------------- info apelo ---------------------------------*/
		
		txtLn10.setFonte(new Font("Arial", Font.PLAIN, 24));
		
		apelo.load("res\\batalha\\infoApelo.png");

		apeloQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		InterferenciaQuantidade.setFonte(new Font("Arial", Font.PLAIN, 20));
		
		tipoDoApelo.load("res\\batalha\\tipoDoApelo1.png");
		
		apeloApelo1.load("res\\batalha\\apelo.png");
		apeloApelo2.load("res\\batalha\\apelo.png");
		apeloApelo3.load("res\\batalha\\apelo.png");
		apeloApelo4.load("res\\batalha\\apelo.png");
		apeloApelo5.load("res\\batalha\\apelo.png");
		
		apeloInterf1.load("res\\batalha\\interferencia.png");
		apeloInterf2.load("res\\batalha\\interferencia.png");
		
		// ----------------------- Descrição -----------------------------------

		descricao.load("res\\batalha\\descricao.png");

		textoDescricao1 = new Texto(descricao.getX() + 20, descricao.getY() + 40, " ");
		textoDescricao2 = new Texto(textoDescricao1.getX(), textoDescricao1.getY() + 28, " ");
		textoDescricao3 = new Texto(textoDescricao2.getX(), textoDescricao2.getY() + 28, " ");
		textoDescricao4 = new Texto(textoDescricao3.getX(), textoDescricao3.getY() + 28, " ");
		textoDescricao5 = new Texto(textoDescricao4.getX() + 248, textoDescricao4.getY() + 18, "Este poder afeta o primeiro colocado.");

		textoDescricao1.setFonte(new Font("Arial", Font.PLAIN, 20));
		textoDescricao2.setFonte(textoDescricao1.getFonte());
		textoDescricao3.setFonte(textoDescricao1.getFonte());
		textoDescricao4.setFonte(textoDescricao1.getFonte());
		textoDescricao5.setFonte(textoDescricao1.getFonte());
		
		textoDescricao1.setCorTexto(new Color (235, 230, 233));
		textoDescricao5.setCorTexto(new Color (189, 0, 4));
		
		
		
		
		
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void AnimarControles() {
		contTempoControles ++;
		
		if(contTempoControles == 121) {
			contTempoControles = 0;
		}
		
		if(contTempoControles % 10 == 0) {
			teclaEsquerda.load("res\\Menu\\setaEsquerda.png");
			teclaDireita.load("res\\Menu\\setaDireita.png");
			teclaCima.load("res\\Menu\\setaCima.png");
			teclaBaixo.load("res\\Menu\\setaBaixo.png");
			teclaZ.load("res\\Menu\\teclaZ.png");
			teclaX.load("res\\Menu\\teclaX.png");
			teclaEsc.load("res\\Menu\\teclaEsc.png");
		}
		
		if(contTempoControles == 15) {
			teclaEsquerda.load("res\\Menu\\setaEsquerda2.png");
		} else if(contTempoControles == 30) {
			teclaDireita.load("res\\Menu\\setaDireita2.png");
		} else if(contTempoControles == 45) {
			teclaCima.load("res\\Menu\\setaCima2.png");
		} else if(contTempoControles == 60) {
			teclaBaixo.load("res\\Menu\\setaBaixo2.png");
		} else if(contTempoControles == 75) {
			teclaZ.load("res\\Menu\\teclaZ2.png");
		} else if(contTempoControles == 90) {
			teclaX.load("res\\Menu\\teclaX2.png");
		} else if(contTempoControles == 105) {
			teclaEsc.load("res\\Menu\\teclaEsc2.png");
		}
		
	}
	
	public void AnimarNomeApelo() {
		contTempoNomeApelo ++;
		
		if(contTempoNomeApelo == 100) {
			contTempoNomeApelo = 0;
		}
		
		if(contTempoNomeApelo == 0 || contTempoNomeApelo == 25 || contTempoNomeApelo == 50 || contTempoNomeApelo == 75) {
			nomeHabilidade1.load("res\\batalha\\nomeHabilidade.png");
			nomeHabilidade2.load("res\\batalha\\nomeHabilidade.png");
			nomeHabilidade3.load("res\\batalha\\nomeHabilidade.png");
			nomeHabilidade4.load("res\\batalha\\nomeHabilidade.png");
		}
		
		if(contTempoNomeApelo == 0) {
			nomeHabilidade1.load("res\\batalha\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 25) {
			nomeHabilidade2.load("res\\batalha\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 50) {
			nomeHabilidade3.load("res\\batalha\\nomeHabilidadeSelecionado.png");
		} else if(contTempoNomeApelo == 75) {
			nomeHabilidade4.load("res\\batalha\\nomeHabilidadeSelecionado.png");
		}
		
	}
	
	public void AnimarInfoApelo() {
		contTempoInfoApelo ++;
		
		if(contTempoInfoApelo == 80) {
			contTempoInfoApelo = 0;
		}
		
		
		if(contTempoInfoApelo == 0 || contTempoInfoApelo == 40 || contTempoInfoApelo == 80) {
			apeloApelo1.load("res\\batalha\\apelo.png");
			apeloApelo2.load("res\\batalha\\apelo.png");
			apeloApelo3.load("res\\batalha\\apelo.png");
			apeloApelo4.load("res\\batalha\\apelo.png");
			apeloApelo5.load("res\\batalha\\apelo.png");
			
			apeloInterf1.load("res\\batalha\\interferencia.png");
			apeloInterf2.load("res\\batalha\\interferencia.png");
		}
		

		if(contTempoInfoApelo == 20) {
			apeloApelo1.load("res\\batalha\\apelo.png");
			apeloApelo2.setImagem(null);
			apeloApelo3.load("res\\batalha\\apelo.png");
			apeloApelo4.setImagem(null);
			apeloApelo5.load("res\\batalha\\apelo.png");
			
			apeloInterf1.load("res\\batalha\\interferencia.png");
			apeloInterf2.setImagem(null);
		}
		
		if(contTempoInfoApelo == 60) {
			apeloApelo1.setImagem(null);
			apeloApelo2.load("res\\batalha\\apelo.png");
			apeloApelo3.setImagem(null);
			apeloApelo4.load("res\\batalha\\apelo.png");
			apeloApelo5.setImagem(null);
			
			apeloInterf1.setImagem(null); 
			apeloInterf2.load("res\\batalha\\interferencia.png");
		}
		
	}
	
	public void AnimarDescricao() {
		contTempoDescricao ++;
		
		if(contTempoDescricao == 250) {
			contTempoDescricao = -18;
			textoDescricao1.setTexto(" ");
			textoDescricao2.setTexto(" ");
		}
		
		if(contTempoDescricao > 0 && contTempoDescricao < 90) {
			textoDescricao1.setTexto(conteudoDescricao.substring(0, contTempoDescricao));
		} else if(contTempoDescricao > 0 && contTempoDescricao < 171) {
			textoDescricao2.setTexto(conteudoDescricao.substring(89, contTempoDescricao));
		}
		
	}
	
	public void KeyPressed (java.awt.event.KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_DOWN) {
			rolagemTela = rolagemTela - 4;
			bntBarraBaixo.load("res\\Manual\\teclaBaixo2.png");
			
		}else if(codigo == KeyEvent.VK_UP && rolagemTela < 0) {
			rolagemTela = rolagemTela + 4;
			bntBarraCima.load("res\\Manual\\teclaCima2.png");

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
	
	public void KeyReleased (java.awt.event.KeyEvent tecla){
		bntBarraBaixo.load("res\\Manual\\teclaBaixo.png");
		bntBarraCima.load("res\\Manual\\teclaCima.png");
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
		
		/* ------------------------------- Controles ---------------------------------*/
		
		graficos.drawImage(teclaEsquerda.getImagem(), teclaEsquerda.getX(), teclaEsquerda.getY() + rolagemTela, this);
		graficos.drawImage(teclaDireita.getImagem(), teclaDireita.getX(), teclaDireita.getY() + rolagemTela, this);
		graficos.drawImage(teclaCima.getImagem(), teclaCima.getX(), teclaCima.getY() + rolagemTela, this);
		graficos.drawImage(teclaBaixo.getImagem(), teclaBaixo.getX(), teclaBaixo.getY() + rolagemTela, this);
		
		graficos.drawImage(teclaZ.getImagem(), teclaZ.getX(), teclaZ.getY() + rolagemTela, this);
		graficos.drawImage(teclaX.getImagem(), teclaX.getX(), teclaX.getY() + rolagemTela, this);
		graficos.drawImage(teclaEsc.getImagem(), teclaEsc.getX(), teclaEsc.getY() + rolagemTela, this);
		
		graficos.setColor(txtLn1.getCorTexto());
		tl1 = new TextLayout(txtLn1.getTexto(), txtLn1.getFonte(), frc);
		tl2 = new TextLayout(txtLn2.getTexto(), txtLn2.getFonte(), frc);
		tl3 = new TextLayout(txtLn3.getTexto(), txtLn3.getFonte(), frc);
		tl4 = new TextLayout(txtLn4.getTexto(), txtLn4.getFonte(), frc);
		tl5 = new TextLayout(txtLn5.getTexto(), txtLn5.getFonte(), frc);
		
		tl1.draw(graficos, txtLn1.getX(), txtLn1.getY() + rolagemTela);
		tl2.draw(graficos, txtLn2.getX(), txtLn2.getY() + rolagemTela);
		tl3.draw(graficos, txtLn3.getX(), txtLn3.getY() + rolagemTela);
		tl4.draw(graficos, txtLn4.getX(), txtLn4.getY() + rolagemTela);
		tl5.draw(graficos, txtLn5.getX(), txtLn5.getY() + rolagemTela);
		
		/* ------------------------------- Batalha ---------------------------------*/
		
		tl6 = new TextLayout(txtLn6.getTexto(), txtLn6.getFonte(), frc);
		tl7 = new TextLayout(txtLn7.getTexto(), txtLn7.getFonte(), frc);
		tl8 = new TextLayout(txtLn8.getTexto(), txtLn8.getFonte(), frc);
		tl9 = new TextLayout(txtLn9.getTexto(), txtLn9.getFonte(), frc);
		
		tl6.draw(graficos, txtLn6.getX(), txtLn6.getY() + rolagemTela);
		tl7.draw(graficos, txtLn7.getX(), txtLn7.getY() + rolagemTela);
		tl8.draw(graficos, txtLn8.getX(), txtLn8.getY() + rolagemTela);
		tl9.draw(graficos, txtLn9.getX(), txtLn9.getY() + rolagemTela);		
		
		graficos.drawImage(nomeHabilidade1.getImagem(), nomeHabilidade1.getX(), nomeHabilidade1.getY() + rolagemTela, this);
		graficos.drawImage(nomeHabilidade2.getImagem(), nomeHabilidade2.getX(), nomeHabilidade2.getY() + rolagemTela, this);
		graficos.drawImage(nomeHabilidade3.getImagem(), nomeHabilidade3.getX(), nomeHabilidade3.getY() + rolagemTela, this);
		graficos.drawImage(nomeHabilidade4.getImagem(), nomeHabilidade4.getX(), nomeHabilidade4.getY() + rolagemTela, this);
		
		tl10 = new TextLayout(nomeApelo1.getTexto(), nomeApelo1.getFonte(), frc);
	    tl11 = new TextLayout(nomeApelo2.getTexto(), nomeApelo2.getFonte(), frc);
	    tl12 = new TextLayout(nomeApelo3.getTexto(), nomeApelo3.getFonte(), frc);
	    tl13 = new TextLayout(nomeApelo4.getTexto(), nomeApelo4.getFonte(), frc);
	    
	    graficos.setColor(nomeApelo1.getCorTexto());
	    tl10.draw(graficos, nomeApelo1.getX(), nomeApelo1.getY() + rolagemTela);
	    tl11.draw(graficos, nomeApelo2.getX(), nomeApelo2.getY() + rolagemTela);
	    tl12.draw(graficos, nomeApelo3.getX(), nomeApelo3.getY() + rolagemTela);
	    tl13.draw(graficos, nomeApelo4.getX(), nomeApelo4.getY() + rolagemTela);
	    graficos.setColor(txtLn1.getCorTexto());
		
		/* ------------------------------- info apelo ---------------------------------*/
	    
	    tl14 = new TextLayout(txtLn10.getTexto(), txtLn10.getFonte(), frc);
	    tl15 = new TextLayout(txtLn11.getTexto(), txtLn11.getFonte(), frc);
	    tl16 = new TextLayout(txtLn12.getTexto(), txtLn12.getFonte(), frc);
	    tl17 = new TextLayout(txtLn13.getTexto(), txtLn13.getFonte(), frc);
	    
		tl14.draw(graficos, txtLn10.getX(), txtLn10.getY() + rolagemTela);
		tl15.draw(graficos, txtLn11.getX(), txtLn11.getY() + rolagemTela);
		tl16.draw(graficos, txtLn12.getX(), txtLn12.getY() + rolagemTela);
		tl17.draw(graficos, txtLn13.getX(), txtLn13.getY() + rolagemTela);
		
		graficos.drawImage(apelo.getImagem(), apelo.getX(), apelo.getY() + rolagemTela, this);
		graficos.drawImage(tipoDoApelo.getImagem(), tipoDoApelo.getX(), tipoDoApelo.getY() + rolagemTela, this);
		
	    tl18 = new TextLayout(apeloQuantidade.getTexto(), apeloQuantidade.getFonte(), frc);
	    tl19 = new TextLayout(InterferenciaQuantidade.getTexto(), InterferenciaQuantidade.getFonte(), frc);

	    tl18.draw(graficos, apeloQuantidade.getX(), apeloQuantidade.getY() + rolagemTela);
	    tl19.draw(graficos, InterferenciaQuantidade.getX(), InterferenciaQuantidade.getY() + rolagemTela);
	    
		graficos.drawImage(apeloApelo1.getImagem(), apeloApelo1.getX(), apeloApelo1.getY() + rolagemTela, this);
		graficos.drawImage(apeloApelo2.getImagem(), apeloApelo2.getX(), apeloApelo2.getY() + rolagemTela, this);
		graficos.drawImage(apeloApelo3.getImagem(), apeloApelo3.getX(), apeloApelo3.getY() + rolagemTela, this);
		graficos.drawImage(apeloApelo4.getImagem(), apeloApelo4.getX(), apeloApelo4.getY() + rolagemTela, this);
		graficos.drawImage(apeloApelo5.getImagem(), apeloApelo5.getX(), apeloApelo5.getY() + rolagemTela, this);
		
		graficos.drawImage(apeloInterf1.getImagem(), apeloInterf1.getX(), apeloInterf1.getY() + rolagemTela, this);
		graficos.drawImage(apeloInterf2.getImagem(), apeloInterf2.getX(), apeloInterf2.getY() + rolagemTela, this);
	
		// ----------------------- Descrição -----------------------------------

		tl20 = new TextLayout(txtLn14.getTexto(), txtLn14.getFonte(), frc);
	    tl21 = new TextLayout(txtLn15.getTexto(), txtLn15.getFonte(), frc);
	    
	    tl20.draw(graficos, txtLn14.getX(), txtLn14.getY() + rolagemTela);
	    tl21.draw(graficos, txtLn15.getX(), txtLn15.getY() + rolagemTela);
	    
		graficos.drawImage(descricao.getImagem(), descricao.getX(), descricao.getY() + rolagemTela, this);

		tl22 = new TextLayout(textoDescricao1.getTexto(), textoDescricao1.getFonte(), frc);
	    tl23 = new TextLayout(textoDescricao2.getTexto(), textoDescricao2.getFonte(), frc);
	    tl24 = new TextLayout(textoDescricao3.getTexto(), textoDescricao3.getFonte(), frc);
	    tl25 = new TextLayout(textoDescricao4.getTexto(), textoDescricao4.getFonte(), frc);
	    tl26 = new TextLayout(textoDescricao5.getTexto(), textoDescricao5.getFonte(), frc);
	    
	    graficos.setColor(textoDescricao1.getCorTexto());
	    tl22.draw(graficos, textoDescricao1.getX(), textoDescricao1.getY() + rolagemTela);
	    tl23.draw(graficos, textoDescricao2.getX(), textoDescricao2.getY() + rolagemTela);
	    tl24.draw(graficos, textoDescricao3.getX(), textoDescricao3.getY() + rolagemTela);
	    tl25.draw(graficos, textoDescricao4.getX(), textoDescricao4.getY() + rolagemTela);
	    graficos.setColor(textoDescricao5.getCorTexto());
	    tl26.draw(graficos, textoDescricao5.getX(), textoDescricao5.getY() + rolagemTela);
	    graficos.setColor(Color.BLACK);
		
		/* ------------------------------- Barra de rolagem ---------------------------------*/

		graficos.drawImage(bntBarraBaixo.getImagem(), bntBarraBaixo.getX(), bntBarraBaixo.getY(), this);
		graficos.drawImage(bntBarraCima.getImagem(), bntBarraCima.getX(), bntBarraCima.getY(), this);
		
		
		
		
		graficos.drawImage(contorno.getImagem(), contorno.getX(), contorno.getY(), this);
		
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		AnimarControles();
		AnimarNomeApelo();
		AnimarInfoApelo();
		AnimarDescricao();
		
		repaint();
		
	}
}
