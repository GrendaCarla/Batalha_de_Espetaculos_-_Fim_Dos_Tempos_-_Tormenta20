package batalha_de_Espetaculos;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {
	
	private AudioInputStream audioInputStream;
	private String caminho;
	private Clip audio1;
	private Clip audio2;
	private Clip voz;
	private Clip efeito1;
	private Clip fogos;
	private Clip clickFita;

	
	public Audio(String Caminho) {
		this.caminho = Caminho;
		
		try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(caminho + "res\\Audio\\trilhaFimDosTempos.wav").getAbsoluteFile());
	        audio1 = AudioSystem.getClip();
	        audio1.open(audioInputStream);
	        audio1.start();
	        audio1.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch (Exception ex) {
	        System.out.println("Erro ao executar SOM!");
	        ex.printStackTrace();
	    }
		
		try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(caminho + "res\\Audio\\fogos.wav").getAbsoluteFile());
	        fogos = AudioSystem.getClip();
	        fogos.open(audioInputStream);
	        
	    } catch (Exception ex) {
	        System.out.println("Erro ao executar SOM!");
	        ex.printStackTrace();
	    }
		
		try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(caminho + "res\\Audio\\sombrio.wav").getAbsoluteFile());
	        audio2 = AudioSystem.getClip();
	        audio2.open(audioInputStream);
	        
	    } catch (Exception ex) {
	        System.out.println("Erro ao executar SOM!");
	        ex.printStackTrace();
	    }
	}
	
	
	public void tocarApresentacao(String nome) {
		
		try {
			
			if(voz != null) {
				voz.stop();
			}
			
	        audioInputStream = AudioSystem.getAudioInputStream(new File(nome).getAbsoluteFile());
	        voz = AudioSystem.getClip();
	        voz.open(audioInputStream);
	        voz.start();
	        voz.loop(0);
	        
	    } catch (Exception ex) {
	        System.out.println("Erro ao executar SOM!");
	        ex.printStackTrace();
	    }
	
	}
	
	public void tocarEfeito1(String nome) {
		try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(nome).getAbsoluteFile());
	        efeito1 = AudioSystem.getClip();
	        efeito1.open(audioInputStream);
	        efeito1.start();
	        efeito1.loop(0);
	        
	    } catch (Exception ex) {
	        System.out.println("Erro ao executar SOM!");
	        ex.printStackTrace();
	    }
	}
	
	public void pausarEfeito1() {
		efeito1.close();
	}
	
	public void tocarFogos() {
		fogos.start();
		fogos.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void pausarFogos() {
		fogos.close();
	}
	
	public void tocarAudio2() {
		audio2.start();
		audio2.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void pausarAudio2() {
		audio2.stop();
	}
	
	public void pausarVoz() {
		try {
			
			if(voz != null) {
				voz.stop();
			}
			
	    } catch (Exception ex) {
	        System.out.println("Erro ao executar SOM!");
	        ex.printStackTrace();
	    }
	}

	public void pausarAudio1() {
		audio1.stop();
	}
	
	public void despausarAudio1() {
		audio1.loop(Clip.LOOP_CONTINUOUSLY);
		audio1.start();
	}

	public void tocarClicFita() {
		try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(caminho + "res\\Audio\\toca-fitas.wav").getAbsoluteFile());
	        clickFita = AudioSystem.getClip();
	        clickFita.open(audioInputStream);
	        clickFita.loop(0);
			clickFita.start();
	        
	    } catch (Exception ex) {
	        System.out.println("Erro ao executar SOM!");
	        ex.printStackTrace();
	    }
		
        
	}
}
