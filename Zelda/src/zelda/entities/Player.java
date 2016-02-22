package zelda.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import zelda.input.Keyboard;
import zelda.sprites.Frame;
import zelda.sprites.Sprite;

public class Player extends Entity {
	
	// imagens
	private static final int player_size = 16;
	private static final Frame static_up = new Frame(Sprite.getSprite(0, 1, 1));
	private static final Frame static_down = new Frame(Sprite.getSprite(0, 0, 1));
	private static final Frame static_left = new Frame(Sprite.getSprite(0, 3, 1));
	private static final Frame static_right = new Frame(Sprite.getSprite(0, 2, 1));
	
	// atual
	private Frame currentFrame;
	
	//game 
	private Camera camera;
	
	// movimentação 
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean moving;
	private double speed;
	
	// colisor
	private boolean showBounds;
	
	public Player() {
		currentFrame = static_down;
		down = true;
		moving = false;
		speed = 2.5;
		
		x = 200;
		y = 200;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	public void tick(Keyboard keys) {
		
		moving = false;
		handleEvents(keys);
		
		
		if(up) {
			velX = 0;
			velY = -speed;
		} 
		if(down) {
			velX = 0;
			velY = speed;
		}
		if(left) {
			velX = -speed;
			velY = 0;
		} 
		if(right) {
			velX = speed;
			velY = 0;
		}
		
		if(moving) {
			x += velX;
			y += velY;
		}
		
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(currentFrame.getFrame(), (int)(x - camera.getX()), (int)(y - camera.getY()), null);
		
		if(showBounds) {
			//mostra posicao
			g.setColor(Color.WHITE);
			g.drawString("Player: X = " + x + ", Y = " + y, 20, 20);
			g.drawString("Camera: X = " + camera.getX() + ", Y = " + camera.getY(), 20, 35);
			
			//mostra colisor
			g.setColor(Color.RED);
			g.draw(getBounds());
		}
	}
	
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int) (x - camera.getX()), (int) (y - camera.getY()), player_size, player_size);
		return r;
	}

	private void handleEvents(Keyboard keys) {
		
		if(keys.keyDown(KeyEvent.VK_UP)) {
			up = true;
			down = left = right = false;
			moving = true;
			currentFrame = static_up;
		}
		if(keys.keyDown(KeyEvent.VK_DOWN)) {
			down = true;
			up = left = right = false;
			moving = true;
			currentFrame = static_down;
		}
		if(keys.keyDown(KeyEvent.VK_LEFT)) {
			left = true;
			up = down = right = false;
			moving = true;
			currentFrame = static_left;
		}
		if(keys.keyDown(KeyEvent.VK_RIGHT)) {
			right = true;
			up = down = left = false;
			moving = true;
			currentFrame = static_right;
		}
		
		// mostrar colisor
		if(keys.keyDownOnce(KeyEvent.VK_SPACE)) {
			showBounds = !showBounds;
		}
	}

}
