package com.subatomgames.snake.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Random;
import com.subatomgames.snake.framework.KeyInput;
import com.subatomgames.snake.framework.ObjectId;
import com.subatomgames.snake.objects.Food;
import com.subatomgames.snake.objects.Snake;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7152883641356745258L;
	public boolean running = false;
	private Thread mainThread;
	private Menu menu;
	
	private static int seconds = 15;
	
	public static int HEIGHT, WIDTH;
	
	public enum STATE{
		Menu,
		Game,
		Instructions
	};
	
	public STATE gameState = STATE.Menu;
	
	Handler handler;
	
	private static Random rand = new Random();
	
	public static boolean paused = false;
	
	private void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		handler = new Handler();
		//hud = new HUD();
		menu = new Menu(this);
		this.addKeyListener(menu);
		handler.createLevel();
		if(gameState == STATE.Game) {
			gameConstruct();
		}
		
	}
	
	public void gameConstruct() {
		Food f = new Food((rand.nextInt(950-100+1) + 100) - ((rand.nextInt(950-100+1) + 100) % 20), ((rand.nextInt(750-100+1) + 100) - ((rand.nextInt(750-100+1) + 100) % 20)), handler, ObjectId.Food);
		handler.addObject(new Snake(200, 100, handler, ObjectId.Snake));
		handler.addObject(f);
		
		new Thread(new Runnable() {
			//@SuppressWarnings("static-access")
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					countdown();
					handler.createObstacles();
					
					try {
						Thread.sleep(15 * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start() {
		if (running) return;
		
		running = true;
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public synchronized void stop(){
        try{
            mainThread.join();
            running = false;
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 15.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
					
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}	
		}
		stop();
	}
	
	private void tick() { 
			handler.tick();
		if(gameState == STATE.Game) {
 
		}
		else if(gameState == STATE.Menu) {
			menu.tick();
			if (Menu.dummy) stop();
		}
		
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\background.png");
		g.drawImage(img1, 0,0, null);
		g.drawImage(img1, 500,0, null);
		handler.render(g);
		if(gameState == STATE.Game) {
			setTimer(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.Instructions) {
			menu.render(g);
		}
		
		//////////////////////////////////////////
		g.dispose();
		bs.show();
	}

	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var < min)
			return var = min;
		else
			return var;
		}
		
	public static int[] spawnFood() {
		int x[] = new int[2];
		do {
			x[0] = (rand.nextInt(900-130+1) + 130);
			x[1] = (rand.nextInt(660-150+1) + 150);
		} 
		while ((x[1] > 200 && x[1] < 440) && (x[0] > 400 && x[0] < 600));
			if (x[0] % 20 != 0) x[0] -= (x[0] % 20);
			if (x[1] % 20 != 0) x[1] -= (x[1] % 20);
			return x;
			
		}
	
	public void countdown() {
		new Thread() {
			public void run() {
				if (seconds > 0) {
					while (seconds > 0) {
						try {
							Thread.sleep(1000);
							seconds--;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					seconds = 15;
				}
				else seconds = 15;
				System.out.println(seconds);
			}
		}.start();
	}
	public void setTimer(Graphics g) {
		Font f3 = new Font("Comic Sans MS",1,30);
		g.setFont(f3);
		g.setColor(Color.white);
		g.drawString(Integer.toString(seconds), Game.WIDTH/2-50, 90);
	}
	
	
	public Handler getHandler() {
		return handler;
	}
	
	
	public static void main(String []args) {
		new Window(1100, 800, "SnakeImproved", new Game());
	}

}
