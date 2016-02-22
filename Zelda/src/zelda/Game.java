package zelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import zelda.entities.Camera;
import zelda.entities.Player;
import zelda.input.Keyboard;
import zelda.tiles.TileMap;

public class Game implements Runnable {
	
	// dimensões do jogo
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	
	// Janela e a tela que vai como conteúdo da janela
	private JFrame frame;
	private Canvas canvas;
	
	// Thread do jogo
	private Thread thread;
	private boolean running;
	
	// BufferStrategy permite multiplos buffers
	private BufferStrategy bs;
	private Graphics2D g;
	
	// Keyboard
	private Keyboard keys;
	
	//player
	public Player player = new Player();
	
	//camera
	public Camera camera = new Camera(player);
	
	//Tilemap
	private TileMap map = new TileMap(camera);
	
	
	public Game(String title) {
		// cria a janela
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// criando a tela
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.BLACK);

		// atribuindo valore a janela
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// atribuindo conteudo a jalena
		frame.getContentPane().add(canvas);
		frame.pack();
		
		// criando bufferStrategy
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		
		running = false;
		
		init();
	}
	
	private void init() {
		//adicionando teclado ao game
		keys = new Keyboard();
		canvas.addKeyListener(keys);
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		//set player and tile camera
		player.setCamera(camera);
	}
	
	public synchronized void start() {
		if(running) 
			return;
		
		//iniciando nova thread caso jogo nao esteja rodando
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) 
			return;
		
		// finalizando jogo caso esteja rodando
		try {
			running = false;
			thread.join();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		// medicao de tempo
		long startTime;
		long endTime;
		long nsPerFrame = (long)(10E9 / 60.0);
		long timer = System.currentTimeMillis();
		
		int frameCount = 0;
		
		while(running) {
			// tempo inicial
			startTime = System.nanoTime();
			
			//loop principal do jogo
			frameCount++;
			tick();
			renderFrame();
			 
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println(frameCount + " fps");
				frameCount = 0;
				timer += 1000;
			}
			
			//tempo final 
			endTime = System.nanoTime();
			long sleepMilis = nsPerFrame - (endTime - startTime);
			
			//espera um tempo
			sleep((long)(sleepMilis / 10E6));
		}
	}
	
	private void tick() {
		// faz leitura das teclas pressionadas
		keys.poll();
		
		// atualiza player
		player.tick(keys);
		
		//camera
		camera.tick();
		
		//atualiza mapa
		map.tick();
	}
	
	private void render() {
		
		map.draw(g);
		
		player.draw(g);
	}
	
	private void renderFrame() {
		// enquanto receber contúdo do BufferStrategy
		do {
			do {
				// pegando graphics do bs
				g = (Graphics2D) bs.getDrawGraphics();
				
				//limpa tela
				g.clearRect(0, 0, WIDTH, HEIGHT);
				
				// metodo render para desenhar na tela
				render();
				
				//liberando objeto graphics
				g.dispose();
				
			} while(bs.contentsRestored());
			
			// mostra back buffer
			bs.show();
			
		} while(bs.contentsLost());
	}
	
	private void sleep(long time) {
		//validando o tempo
		if(time < 0)
			return;
		
		// pausa thread pot time milisegundos
		try {
			Thread.sleep(time);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
