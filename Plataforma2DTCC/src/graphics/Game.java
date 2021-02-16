package graphics;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	public JFrame jframe;
	private static int WIDTH = 160;
	private static int HEIGHT = 120;
	private static int SCALE = 3;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
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
	}

	public synchronized void tick() {
		
	}
	
	public synchronized void render() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
