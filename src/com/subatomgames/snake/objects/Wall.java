package com.subatomgames.snake.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;

import com.subatomgames.snake.framework.GameObject;
import com.subatomgames.snake.framework.ObjectId;
import com.subatomgames.snake.window.Game;

public class Wall extends GameObject{
	
private int height, width; //height and width of a block
	
	
	public Wall(float x, float y, int width, int height, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.height = height;
		this.width = width;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//height = sizeRand.nextInt(50 - 20 + 1);
		//width = sizeRand.nextInt(50 - 20 + 1);
		//g.setColor(Color.white);
		//g.drawRect((int)x, (int)y, width, height);
		
		if(this.width > this.height) {
			if (x == 0 && y == 0) {
				Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\top_wall.png");
				g.drawImage(img1, (int)x, (int)y, null);
			}
			else {
				Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\bottom_wall.png");
				g.drawImage(img1, (int)x, (int)y, null);
			}
			
		}
		if(this.width < this.height) {
			if (x == 0 && y == 0) {
				Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\right_wall.png");
				g.drawImage(img1, (int)x, (int)y, null);
			}
			else {
				Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\left_wall.png");
				g.drawImage(img1, (int)x, (int)y, null);
			}
			
		}
		Image img2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\corner_wall.png");
		g.drawImage(img2, 0, 0, null);
		g.drawImage(img2, 0,  Game.HEIGHT-50, null);
		g.drawImage(img2, Game.WIDTH-50, 0, null);
		g.drawImage(img2, Game.WIDTH-50, Game.HEIGHT-50, null);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(getBounds());
		//g2d.draw(getBoundsRight());
		//g2d.draw(getBoundsLeft());
		//g2d.draw(getBoundsTop());
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, width, height);
	}
}
