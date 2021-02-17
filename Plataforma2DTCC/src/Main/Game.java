package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import Entitys.Entity;
import Entitys.Player;
import graphics.Spritsheet;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	public JFrame jframe;
	private static int WIDTH = 160;
	private static int HEIGHT = 120;
	private static int SCALE = 3;
	
	private Thread thread;
	private boolean isRunning = true;
	
	private BufferedImage background;
	private List<Entity> entidades;
	public Spritsheet spritsheet;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entidades = new ArrayList<Entity>();
		spritsheet = new Spritsheet("/spriteSheet.png");
		Player player = new Player(0,0,16,16,spritsheet.getSprite(0,0,16,16));
		entidades.add(player);
	}

	public void initFrame() {
		jframe = new JFrame("TCC");
		jframe.add(this);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		for(int i =0; i < entidades.size(); i++) {
			Entity entidade = entidades.get(i);
			entidade.tick();
		}
	}
	
	public void render() {
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = background.getGraphics();
		g.setColor(new Color(20, 20, 20));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i =0; i < entidades.size(); i++) {
			Entity entidade = entidades.get(i);
			entidade.render(g);
		}
		
		g = buffer.getDrawGraphics();
		g.drawImage(background, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		buffer.show();
	}
	
	@Override
	public void run() {
		long LastTime = System.nanoTime();
		double amountOfTicks = 60.0f;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {	
			long now = System.nanoTime();
			delta += ( now - LastTime ) / ns;
			LastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			    }
				if(System.currentTimeMillis() - timer >= 1000){
					//System.out.println("FPS: "+ frames);
					frames = 0;
					timer+=1000;
				}
			}
		stop();
	}

}
