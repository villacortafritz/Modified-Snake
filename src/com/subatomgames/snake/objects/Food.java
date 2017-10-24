package com.subatomgames.snake.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;
import com.subatomgames.snake.framework.GameObject;
import com.subatomgames.snake.framework.ObjectId;
import com.subatomgames.snake.window.Handler;

public class Food extends GameObject{
	
	int randX, randY;
	private final int FOOD_SIZE = 20;
	Handler handler;
	
	public Food(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		//collision();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\apple.png");
		g.drawImage(img1, (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, FOOD_SIZE, FOOD_SIZE);
	}
	
	public void collision() {
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ObjectId.Snake) {
				if (temp.getBounds().intersects(this.getBounds())) {
					
					do {
						randX = (rand.nextInt(900-150+1) + 150);
						randY = (rand.nextInt(660-150+1) + 150);
					} while ((randX == getX() && randY == getY()));
					
					if (randX % 20 != 0) randX -= (randX % 20);
					if (randY % 20 != 0) randY -= (randY % 20);
					setX(randX);
					setY(randY);
					//System.out.println("From food collision: " + x + ", " + y + " intersects " + temp.getX() + ", " + temp.getY() + " from snake");
				}
			}
			if (temp.getId() == ObjectId.Asteroid) { //if food spawns on asteroid, it will spawn again until it lands to the right spot
				if (temp.getBounds().intersects(this.getBounds())) {
					Asteroid droid = (Asteroid) temp;
					if (!droid.getIsMoving()) {
						do {
							randX = (rand.nextInt(900-150+1) + 150);
							randY = (rand.nextInt(660-150+1) + 150);
						} while ((randX == x && randY == y));
							
						if (randX % 20 != 0) randX -= (randX % 20);
						if (randY % 20 != 0) randY -= (randY % 20);
						setX(randX);
						setY(randY);
					}
				}
			}
		}
	}
}
