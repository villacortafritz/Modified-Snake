package com.subatomgames.snake.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import com.subatomgames.snake.framework.GameObject;
import com.subatomgames.snake.framework.ObjectId;
import com.subatomgames.snake.framework.SnakeDir;
import com.subatomgames.snake.window.Game;
import com.subatomgames.snake.window.Handler;

public class Snake extends GameObject{
	
	private ArrayList<Point> snakePoints = new ArrayList<Point>();
	
	public int lenSnake;
	public boolean ateFood = false;
	public boolean isHit = false;
	public boolean hitItself = false;
	
	public static boolean snakeRuns = false;
	
	private int xDir = 0;
	private int yDir = 0;
	private SnakeDir snakeDir;
	private Point last;
	private Handler handler;
	
	private final int BLOCK_SIZE = 20;
	
	public Snake(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub

		this.handler = handler;
		
		lenSnake = 5; //default size is 5
		
		snakePoints.add(new Point((int)x,(int)y));
		
		for (int i = 1; i < lenSnake; i++) {
			snakePoints.add(new Point((int)x - i * BLOCK_SIZE,(int)y));
		}
		setDX(1);
		setDY(0);
		setDir(SnakeDir.RIGHT);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		//moves the snake to its desired direction
				//snakeRuns = true;
				if (lenSnake != 1) {
					collision();
					// TODO Auto-generated method stub
					xDir = (int) dx;
					yDir = (int) dy;
					Point temp = snakePoints.get(0);
					last = snakePoints.get(snakePoints.size() - 1);
					Point newStart = new Point((int)temp.getX() + xDir * BLOCK_SIZE,(int)temp.getY() + yDir * BLOCK_SIZE);
					
					for(int i = snakePoints.size()-1; i > 0; i--) {
						snakePoints.set(i,snakePoints.get(i-1));
					}	
					snakePoints.set(0, newStart);	
					
					
					
						if (ateFood == true) {
							try {
								snakePoints.add(last);
								lenSnake++;
							} catch(Exception e) {
								
							}		
							ateFood = false;
						}
						
						if (isHit == true) {
								removeBody(this.lenSnake - 1);
								lenSnake--;
								isHit = false;
						}
					}
				else {
					System.exit(1);
				}
					
				
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		int i = 0;
		
		for (Point p: snakePoints) {
			if (i == 0) {
				//g.setColor(Color.white);
				x = (int)p.getX();
				y = (int)p.getY();
				if (getDir() == SnakeDir.DOWN) {
					Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\head_bottom.png");
					g.drawImage(img1, (int)x, (int)y, null);
				}
				else if(getDir() == SnakeDir.LEFT) {
					Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\head_left.png");
					g.drawImage(img1, (int)x, (int)y, null);
				}
				else if(getDir() == SnakeDir.RIGHT) {
					Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\head_right.png");
					g.drawImage(img1, (int)x, (int)y, null);
				}
				else if(getDir() == SnakeDir.UP) {
					Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\head_top.png");
					g.drawImage(img1, (int)x, (int)y, null);
				}
			}
			else {
				g.setColor(Color.red);
				Image img2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\body.png");
				g.drawImage(img2, (int)p.getX(), (int)p.getY(), null);
			}
			//g.drawRect((int)p.getX(), (int)p.getY(), BLOCK_SIZE, BLOCK_SIZE);
			
			i++;
		}
		
		
		
		
	}
	
	

	public Rectangle getBounds(int x, int y) {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);
		
	}

	
	public void setDir(SnakeDir s) {
		snakeDir = s;
	}
	
	public SnakeDir getDir() {
		return snakeDir;
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);

			
			if (temp.getId() == ObjectId.Asteroid) {
				if(temp.getBounds().intersects(getBounds())) {
					//TODO collision detection MUST EXTEND LENGTH UPON COLLISION
					Asteroid roid = (Asteroid) temp;
					if (roid.getIsMoving()) {
						isHit = true;
					}
					else {
						System.exit(1);
					}
					
				}
			}
			
			if (temp.getId() == ObjectId.Wall) {
				if(temp.getBounds().intersects(getBounds())) {
					//TODO collision detection MUST EXTEND LENGTH UPON COLLISION
					System.exit(1);
				}
			}
			
			if (temp.getId() == ObjectId.Food) {
				if (temp.getBounds().intersects(getBounds())) {
					int lol[] = Game.spawnFood();
					ateFood = true;
					temp.setX(lol[0]);
					temp.setY(lol[1]);
					
				}
			}
		}
	}
	
	public int getXOfHead() {
		return (int) getX();
		
	}
	
	public int getYOfHead() {
		return (int) getY();
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return getBounds(this.getXOfHead(), this.getYOfHead());
	}
	
	public void removeBody(int index) {
		Iterator<Point> iter = snakePoints.iterator();
		int i = 0;
		while (iter.hasNext()) {
		    Point str = iter.next();

		    if (i == index)
		        iter.remove();
		    i++;
		}
	}

}
