package batalha_de_Espetaculos.Modelo;

import java.awt.Color;
import java.awt.Font;

public class Texto {
	private int x,y;
	private String texto;
	private Font fonte = new Font("Arial", Font.PLAIN, 22);
	private Color corTexto = Color.BLACK;
	
	public Texto(int posicaoX, int posicaoY, String texto) {
		this.x = posicaoX;
		this.y = posicaoY;
		this.texto = texto;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Font getFonte() {
		return fonte;
	}

	public void setFonte(Font fonte) {
		this.fonte = fonte;
	}

	public Color getCorTexto() {
		return corTexto;
	}

	public void setCorTexto(Color corTexto) {
		this.corTexto = corTexto;
	}

	
}
