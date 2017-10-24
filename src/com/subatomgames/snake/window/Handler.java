package com.subatomgames.snake.window;

import java.awt.Graphics;
import java.util.LinkedList;
import com.subatomgames.snake.framework.GameObject;
import com.subatomgames.snake.framework.ObjectId;
import com.subatomgames.snake.objects.Asteroid;
import com.subatomgames.snake.objects.Wall;

public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	//this method creates a wall around the game frame
	public void createLevel() {
		addObject(new Wall(0, 0, Game.WIDTH, 50, ObjectId.Wall));					//top
		addObject(new Wall(0, 0, 50, Game.HEIGHT, ObjectId.Wall));					//left
		addObject(new Wall(0, Game.HEIGHT - 50, Game.WIDTH, 50, ObjectId.Wall));	//bottom
		addObject(new Wall(Game.WIDTH - 50, 0, 50, Game.HEIGHT, ObjectId.Wall));	//right
	}
	
	
	public void createObstacles() {
		addObject(new Asteroid(440, 400, 40, 40, this, ObjectId.Asteroid));
		addObject(new Asteroid(480, 400, 40, 40, this, ObjectId.Asteroid));
		addObject(new Asteroid(520, 400, 40, 40, this, ObjectId.Asteroid));
		addObject(new Asteroid(560, 400, 40, 40, this, ObjectId.Asteroid));
		
		addObject(new Asteroid(440, 260, 40, 40, this, ObjectId.Asteroid));
		addObject(new Asteroid(480, 260, 40, 40, this, ObjectId.Asteroid));
		addObject(new Asteroid(520, 260, 40, 40, this, ObjectId.Asteroid));
		addObject(new Asteroid(560, 260, 40, 40, this, ObjectId.Asteroid));
	}
	
	public void removeAllObjects() {
		for (int i = 0; i < object.size(); i++) {
			removeObject(object.get(i))
;		}
	}
	
	public void printAllObjects() {
		System.out.println(object);
	}
}
