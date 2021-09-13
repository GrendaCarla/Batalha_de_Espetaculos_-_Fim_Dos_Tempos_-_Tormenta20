package batalha_de_Espetaculos.Modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icones_interativos {
	private int x,y, dx, dy;
	private Image imagem;
	private ImageIcon referencia;
	
	public Icones_interativos(int posicaoX, int posicaoY) {
		this.x = posicaoX;
		this.y = posicaoY;
	}
	
	public void load(String imageIcone) {
		referencia = new ImageIcon(imageIcone);
		imagem = referencia.getImage();
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

	public ImageIcon getReferencia() {
		return referencia;
	}

	public void setReferencia(ImageIcon referencia) {
		this.referencia = referencia;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	
	
}
