package com.subatomgames.snake.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.subatomgames.snake.window.Game.STATE;

public class Menu implements KeyListener{
	
	private Game game;
	Handler handler;
	
	public static boolean dummy = false;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void render(Graphics g){
		if(game.gameState == STATE.Menu) {
			Font f1 = new Font("century gothic",1,100);
			Font f2 = new Font("century gothic",1,30);
			g.setFont(f1);
			g.setColor(Color.white);
			Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\menu.png");
			g.drawImage(img1,100 ,100 , null);
			g.setFont(f2);
			g.drawString("PRESS SPACE TO START", 390, 645);
		}
		else if(game.gameState == STATE.Instructions) {
			
			Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Fritz Villacorta\\Desktop\\SnakeVSnake\\SnakeVSnake\\src\\com\\subatomgames\\snake\\images\\directions.png");
			g.drawImage(img1,300 ,200 , null);
			
			Font f1 = new Font("Londrina Outline",1,80);
			Font f2 = new Font("century gothic",1,30);
			g.setFont(f1);
			g.setColor(Color.white);
			g.drawString("INSTRUCTIONS", 360, 200);
			g.setFont(f2);
			g.drawString("Quit", 520, 640);
			g.drawRect(400, 600, 300, 60);
		}
	}
	
	public void tick() {
	
	}

	@Override
	public void keyPressed(KeyEvent a) {
		// TODO Auto-generated method stub
		if(a.getKeyCode()==KeyEvent.VK_SPACE){
			game.gameState = STATE.Game;
			
			game.gameConstruct();
      	}
		else if(a.getKeyCode()==KeyEvent.VK_ESCAPE) {
			System.exit(1);
			if(game.gameState == STATE.Game) {
				System.out.println("fucker");
				game.gameState = STATE.Menu;
				//handler.removeAllObjects();
				//game.gameConstruct();
				dummy = true;
				return;
			}
			else if(game.gameState == STATE.Menu) {
				System.exit(1);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
