package zelda.sprites;

import java.awt.image.BufferedImage;

public class Frame {
	// atributos
	private BufferedImage frame;
	
	public Frame(BufferedImage img) {
		this.frame = img;
	}
	
	// acessores
	public BufferedImage getFrame() { return frame; }
	
	// mutadores
	public void setFrame(BufferedImage frame) { this.frame = frame; }
}
