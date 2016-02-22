package zelda.entities;

public class Entity {
	// variáveis
	protected boolean alive;
	protected double x, y;
	protected double velX, velY;
	
	// metodos para acessar os atributos
	public boolean isAlive() { return alive; }
	public double getX() { return x; }
	public double getY() { return y; }
	public double getVelX() { return velX; }
	public double getVelY() { return velY; }
	
	// metodos para alterar atributos
	public void setAlive(boolean alive) { this.alive = alive; }
	public void setX(double value) { x = value; }
	public void setY(double value) { y = value; }
	public void setVelX(double value) { velX = value; }
	public void setVelY(double value) { velY = value; }
	
	public void incX(double i) { x += i; }
	public void incY(double i) { y += i; }
	public void incVelX(double i) { velX += i; }
	public void incVelY(double i) { velY += i; }
	
	public Entity() {
		alive = false;
		x = y = 0.0;
		velX = velY = 0.0;
	}
}
