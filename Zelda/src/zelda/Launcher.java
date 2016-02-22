package zelda;

public class Launcher {
	
	// primeiro método chamado pelo programa
	public static void main(String[] args) {
		//inicia novo jogo
		Game game = new Game("Z E L D A");
		game.start();
	}

}
