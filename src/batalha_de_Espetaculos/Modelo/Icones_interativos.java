package batalha_de_Espetaculos.Modelo;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Icones_interativos {
	private int x,y, dx, dy, redX, redY;
	private Image imagem;
	private ImageIcon referencia;
	private int larg, alt;
	
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension d = tk.getScreenSize();
	private double tamanhoTela = (d.width >= 1234*3 && d.height >= 640*3 ? 3 : (d.width >= 1234*2.92 && d.height >= 640*2.92 ? 2.92 : (
			  d.width >= 1234*2.8 && d.height >= 640*2.8 ? 2.8 : (d.width >= 1234*2.72 && d.height >= 640*2.72 ? 2.72 : (
			  d.width >= 1234*2.6 && d.height >= 640*2.6 ? 2.6 : (d.width >= 1234*2.52 && d.height >= 640*2.52 ? 2.52 : (
			  d.width >= 1234*2.4 && d.height >= 640*2.4 ? 2.4 : (d.width >= 1234*2.32 && d.height >= 640*2.32 ? 2.32 : (
			  d.width >= 1234*2.2 && d.height >= 640*2.2 ? 2.2 : (d.width >= 1234*2.12 && d.height >= 640*2.12 ? 2.12 : (
			  d.width >= 1234*2 && d.height >= 640*2 ? 2 : (d.width >= 1234*1.92 && d.height >= 640*1.92 ? 1.92 : (
			  d.width >= 1234*1.8 && d.height >= 640*1.8 ? 1.8 : (d.width >= 1234*1.72 && d.height >= 640*1.72 ? 1.72 : (
			  d.width >= 1234*1.6 && d.height >= 640*1.6 ? 1.6 : (d.width >= 1234*1.52 && d.height >= 640*1.52 ? 1.52 : (
			  d.width >= 1234*1.4 && d.height >= 640*1.4 ? 1.4 : (d.width >= 1234*1.32 && d.height >= 640*1.32 ? 1.32 : (
			  d.width >= 1234*1.2 && d.height >= 640*1.2 ? 1.2 : (d.width >= 1234*1.12 && d.height >= 640*1.12 ? 1.12 : (
			  d.width >= 1234*1 && d.height >= 640*1 ? 1 : (d.width >= 1234*0.92 && d.height >= 640*0.92 ? 0.92 : (
			  d.width >= 1234*0.8 && d.height >= 640*0.8 ? 0.8 : (d.width >= 1234*0.72 && d.height >= 640*0.72 ? 0.72 : (
			  d.width >= 1234*0.6 && d.height >= 640*0.6 ? 0.6 : 0.52)))))))))))))))))))))))));
	
	private int redimLarg = d.width/2 - (int)Math.ceil((1234/2) * tamanhoTela);
	private int redimAlt = d.height/2 - (int)Math.ceil((640/2) * tamanhoTela) - 16;
	
	public Icones_interativos(int posicaoX, int posicaoY) {
		x = posicaoX;
		y = posicaoY;
		redX = redimLarg + (int)Math.ceil(posicaoX * tamanhoTela);
		redY = redimAlt + (int)Math.ceil(posicaoY * tamanhoTela);
	}
	
	public void load(String imageIcone) {
		referencia = new ImageIcon(imageIcone);
		imagem = referencia.getImage();
		
		File imagem2 = new File(imageIcone);
		
		BufferedImage img;
		try {
			img = ImageIO.read(imagem2);
			larg = (int)Math.ceil(img.getWidth() * tamanhoTela);
			alt = (int)Math.ceil(img.getHeight() * tamanhoTela);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getLarg() {
		return larg;
	}

	public int getAlt() {
		return alt;
	}
	
	public void setLarg(int larg) {
		this.larg = larg;
	}
	
	public void setAlt(int alt) {
		this.alt = alt;
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
		redX = redimLarg + (int)Math.ceil(x * tamanhoTela);
	}

	public void setY(int y) {
		this.y = y;
		redY = redimAlt + (int)Math.ceil(y * tamanhoTela);
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
	
	public int getRedX() {
		return redX;
	}
	
	public int getRedY() {
		return redY;
	}
	
	public double getTamanhoTela() {
		return tamanhoTela;
	}
	
}
