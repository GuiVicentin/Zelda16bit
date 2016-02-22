package zelda.entities;

import zelda.Game;

public class Camera extends Entity{
	
	private Player player;
	private double lerp = 0.1;
	
	public Camera(Player player) {
		this.player = player;
	}

	public void tick() {
		x = x + (((player.getX()- Game.WIDTH/2) - x) * lerp);
		y = y + (((player.getY() - Game.HEIGHT/2) - y) * lerp);
		
		if(x < 0) {
			x = 0;
		}
		else if(x > 1600 - Game.WIDTH) {
			x = 1600 - Game.WIDTH;
		}
		
		if(y < 0) {
			y = 0;
		}
		else if(y > 1600 - Game.HEIGHT) {
			y = 1600 - Game.WIDTH;
		}
	}
}
