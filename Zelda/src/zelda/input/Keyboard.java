package zelda.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	// número de teclas suportadas no jogo
	private static final int NUM_KEYS = 256;
	
	//contem se as teclas estao pressionadas e quantas vezes
	private boolean[] keys;
	private int[] polled;
	
	public Keyboard() {
		// criar vetores
		keys = new boolean[NUM_KEYS];
		polled = new int[NUM_KEYS];
	}
	
	public void poll() {
		// verificar as teclas apertadas
		for(int i = 0; i < keys.length; i++) {
			if(keys[i]) {
				polled[i]++;
			} else {
				polled[i] = 0;
			}
		}
	}
	
	public boolean keyDown(int keyCode) {
		// retorna caso a tecla tenha esteja precionada
		return polled[keyCode] > 0;
	}
	
	public boolean keyDownOnce(int keyCode) {
		//retorna caso a tecla tenha sido precionada uma unica vez
		return polled[keyCode] == 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// atribuindo true a tecla precionada
		int keyCode = e.getKeyCode();
		if(keyCode >= 0 && keyCode < NUM_KEYS) {
			keys[keyCode] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// atribuindo false a tecla solta
		int keyCode = e.getKeyCode();
		if(keyCode >= 0 && keyCode < NUM_KEYS) {
			keys[keyCode] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
