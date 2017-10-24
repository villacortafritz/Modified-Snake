package com.subatomgames.snake.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;


public abstract class GameObject {
	protected float x, y; //coordinates of an object
	protected ObjectId id; //tells if an object is a snake, asteroid etc
	protected float dx = 0, dy = 0; //speed 
	protected SnakeDir dir;
	protected Random rand;
	
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
		rand = new Random();
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds(); //for checking collision between objects
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y; 
	}
	
	public float getDX() {
		return dx;
	}
	public float getDY() {
		return dy;
	}
	
	public void setDX(float dx) {
		this.dx = dx;
	}
	public void setDY(float dy) {
		this.dy = dy;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public SnakeDir getDir() {
		return dir;
	}
	public void setDir(SnakeDir d) {
		this.dir = d;
	}

	public Rectangle getBounds(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}
}
