package com.subatomgames.snake.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.subatomgames.snake.window.Handler;

public class KeyInput extends KeyAdapter{

	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
	
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);			
			if (temp.getId() == ObjectId.Snake) {
				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
					if (temp.getDir() != SnakeDir.DOWN) {
						temp.setDX(0);
						temp.setDY(-1);
						temp.setDir(SnakeDir.UP);
					}
				}
				else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					if (temp.getDir() != SnakeDir.RIGHT) {
						temp.setDX(-1);
						temp.setDY(0);
						temp.setDir(SnakeDir.LEFT);
					}
					
				}
				else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
					if (temp.getDir() != SnakeDir.UP) {
						temp.setDX(0);
						temp.setDY(1);
						temp.setDir(SnakeDir.DOWN);
					}
				}
				else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					if (temp.getDir() != SnakeDir.LEFT) {
						temp.setDX(1);
						temp.setDY(0);
						temp.setDir(SnakeDir.RIGHT);
					}
				}
			}
		}
	}
	

}
