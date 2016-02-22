package zelda.sprites;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sprite {

	//atributos 
	public static final int player_sheet = 0;
	public static final int tiles_sheet = 1;
	
	private static final String[] path = {"player_sheet", "overworld_sheet"};
	
	private static BufferedImage[] sprite_sheets = {null, null};
	private static final int GRID_SIZE = 16;
	
	// carrega um sprite e retorna
	public static BufferedImage loadSprite(String filename) {
		BufferedImage sprite = null;
		
		try {
			sprite = ImageIO.read(new File("res/" + filename + ".png"));
		} catch(Exception e) {
			System.out.println("Nao foi possivel carregar" + filename);
			e.printStackTrace();
		}
		
		return sprite;
	}
	
	// retorna sprite do player se nao existir carrega 
	public static BufferedImage getSprite(int idx, int xGrid, int yGrid) {
		if(sprite_sheets[idx] == null) {
			sprite_sheets[idx] = loadSprite(path[idx]);
		}
		
		return sprite_sheets[idx].getSubimage(yGrid * GRID_SIZE, xGrid * GRID_SIZE, GRID_SIZE, GRID_SIZE);
	}
}
