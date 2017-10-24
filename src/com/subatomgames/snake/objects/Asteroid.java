package com.subatomgames.snake.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import com.subatomgames.snake.framework.GameObject;
import com.subatomgames.snake.framework.ObjectId;
import com.subatomgames.snake.window.Game;
import com.subatomgames.snake.window.Handler;

public class Asteroid extends GameObject{
	private int []direction = {1, -1};
	private int height, width; 
	private Handler handler;
	private Timer delay;
	
	private boolean isMoving; //true when an asteroid moves, false if doesn't
	
	public Asteroid(float x, float y, int width, int height, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.delay = new Timer();
		dx = (rand.nextFloat() + 5) * direction[rand.nextInt(2)];
		dy = (rand.nextFloat() + 5) * direction[rand.nextInt(2)];
		// TODO Auto-generated constructor stub
		this.height = height;
		this.width = width;
		this.isMoving = false;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		//the collision algorithm against the wall
		//delays the movement of platforms in 30 seconds
		delay.schedule(new TimerTask() {
			@Override
			public void run() {
				isMoving = true;
				//dx += 1;
				//dy += 1;
				// TODO Auto-generated method stub
				
				if (x + width >= (Game.WIDTH-60) || x - 60 <= 0) {
					dx = -dx;
					if (dx < 10) {
						if (dx > 0) dx += 0.5;
						else if (dx < 0) dx -= 0.5;
					}
				}
					
				if (y + height >= (Game.HEIGHT-60) || y - 60 <= 0) {
					dy = -dy;
					if (dy < 10) {
						if (dy > 0) dy += 0.5;
						else if (dy < 0) dy -= 0.5;
					}
				}
				//collision();	
				x += dx;
				y += dy;
			}
			
			
		}, 15 * 1000);

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//g.setColor(Color.white);
		//g.drawRect((int)x, (int)y, width, height);
		/*Graphics2D g2d = (Graphics2D) g;
		g2d.draw(getBounds());
		g2d.setColor(Color.blue);*/
		
		Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\fireball.png");
		g.drawImage(img1, (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ObjectId.Wall) {
				if (getBounds().intersects(temp.getBounds())) {
					if (this.getDX() > 0 || this.getDX() < 0) dx = -dx;
					if (this.getDY() > 0 || this.getDY() < 0) dy = -dy;
				}
			}
		}
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
}
