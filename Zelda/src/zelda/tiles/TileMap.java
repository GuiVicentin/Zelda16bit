package zelda.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import zelda.Game;
import zelda.entities.Camera;
import zelda.sprites.Sprite;

public class TileMap {
	
	private static final int TILE_SIZE = 16;
	private static final BufferedImage[] TILE_SET = {
			Sprite.getSprite(1, 0, 0),
			Sprite.getSprite(1, 0, 1),
			Sprite.getSprite(1, 1, 0),
			Sprite.getSprite(1, 1, 1)
	};
	
	private int currentRow;
	private int currentCol;
	private int numRowToDraw;
	private int numColToDraw;
	
	double x, y;
	
	private Camera camera;
	
	private Random random = new Random();
	private int map[][];
	private int mapSize = 100;
	
	public TileMap(Camera camera) {
		this.camera = camera;
		
		numRowToDraw = Game.HEIGHT / TILE_SIZE + 1;
		numColToDraw = Game.WIDTH / TILE_SIZE + 1;
		
		map = new int[mapSize][mapSize];
		for(int i = 0; i < mapSize; i++) {
			for(int j = 0; j < mapSize; j++) {
				int idx = random.nextInt(TILE_SET.length);
				map[i][j] = idx;
			}
		}
	}
	
	public void tick() {
		currentRow = (int) (camera.getY() / TILE_SIZE);
		currentCol = (int) (camera.getX() / TILE_SIZE);
		
		x = -camera.getX();
		y = -camera.getY();
	}
	
	public void draw(Graphics2D g) {
		
		for(int y = currentRow; y < currentRow + numRowToDraw; y++) {
			
			if(y < 0 || y >= mapSize) {
				break;
			}
			
			for(int x = currentCol; x < currentCol + numColToDraw; x++) {
				
				if(x < 0 || x >= mapSize) {
					break;
				}
				
				g.drawImage(TILE_SET[map[y][x]], (int)this.x + x * TILE_SIZE, (int)this.y + y * TILE_SIZE, null);
			}
		}
	}
}
