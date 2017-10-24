package com.subatomgames.snake.window;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Window {
	
	
	public Window(int width, int height, String name, Game game) {
		
		JFrame frame = new JFrame();     
		game.setPreferredSize(new Dimension(width, height));
		game.setMaximumSize(new Dimension(width, height));
		game.setMinimumSize(new Dimension(width, height));
		
		//JFrame frame = new JFrame(name);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setBackground(Color.YELLOW);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
		
	}
}
