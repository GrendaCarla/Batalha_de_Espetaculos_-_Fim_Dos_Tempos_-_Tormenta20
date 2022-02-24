package batalha_de_Espetaculos.Modelo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class Texto {
	private int x, y, redX, redY;
	private String texto;
	private Font fonte = new Font("Arial", Font.PLAIN, 22);
	private Color corTexto = Color.BLACK;
	
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension d = tk.getScreenSize();
	private double tamanhoTela = (d.width >= 1234*3 && d.height >= 640*3 ? 3 : (d.width >= 1234*2.9 && d.height >= 640*2.9 ? 2.9 : (
			  d.width >= 1234*2.8 && d.height >= 640*2.8 ? 2.8 : (d.width >= 1234*2.7 && d.height >= 640*2.7 ? 2.7 : (
			  d.width >= 1234*2.6 && d.height >= 640*2.6 ? 2.6 : (d.width >= 1234*2.5 && d.height >= 640*2.5 ? 2.5 : (
			  d.width >= 1234*2.4 && d.height >= 640*2.4 ? 2.4 : (d.width >= 1234*2.3 && d.height >= 640*2.3 ? 2.3 : (
			  d.width >= 1234*2.2 && d.height >= 640*2.2 ? 2.2 : (d.width >= 1234*2.1 && d.height >= 640*2.1 ? 2.1 : (
			  d.width >= 1234*2 && d.height >= 640*2 ? 2 : (d.width >= 1234*1.9 && d.height >= 640*1.9 ? 1.9 : (
			  d.width >= 1234*1.8 && d.height >= 640*1.8 ? 1.8 : (d.width >= 1234*1.7 && d.height >= 640*1.7 ? 1.7 : (
			  d.width >= 1234*1.6 && d.height >= 640*1.6 ? 1.6 : (d.width >= 1234*1.5 && d.height >= 640*1.5 ? 1.5 : (
			  d.width >= 1234*1.4 && d.height >= 640*1.4 ? 1.4 : (d.width >= 1234*1.3 && d.height >= 640*1.3 ? 1.3 : (
			  d.width >= 1234*1.2 && d.height >= 640*1.2 ? 1.2 : (d.width >= 1234*1.1 && d.height >= 640*1.1 ? 1.1 : (
			  d.width >= 1234*1 && d.height >= 640*1 ? 1 : (d.width >= 1234*0.9 && d.height >= 640*0.9 ? 0.9 : (
			  d.width >= 1234*0.8 && d.height >= 640*0.8 ? 0.8 : (d.width >= 1234*0.7 && d.height >= 640*0.7 ? 0.7 : (
			  d.width >= 1234*0.6 && d.height >= 640*0.6 ? 0.6 : 0.5)))))))))))))))))))))))));
	
	private int redimLarg = d.width/2 - (int)Math.ceil((1234/2) * tamanhoTela);
	private int redimAlt = d.height/2 - (int)Math.ceil((640/2) * tamanhoTela) - 16;
	
	public Texto(int posicaoX, int posicaoY, String texto) {
		this.x = posicaoX;
		this.y = posicaoY;
		this.texto = texto;
		redX = redimLarg + (int)Math.ceil(posicaoX * tamanhoTela);
		redY = redimAlt + (int)Math.ceil(posicaoY * tamanhoTela);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
		redX = redimLarg + (int)Math.ceil(x * tamanhoTela);
	}

	public void setY(int y) {
		this.y = y;
		redY = redimAlt + (int)Math.ceil(y * tamanhoTela);
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
		this.fonte = new Font("Arial", Font.PLAIN, (int)Math.ceil(fonte.getSize()* tamanhoTela + tamanhoTela));
	}

	public Color getCorTexto() {
		return corTexto;
	}

	public void setCorTexto(Color corTexto) {
		this.corTexto = corTexto;
	}

	public int getRedX() {
		return redX;
	}
	
	public int getRedY() {
		return redY;
	}
}
