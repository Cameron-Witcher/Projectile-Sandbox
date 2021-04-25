package me.cameron.tp3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int DELAY = 15;
	Timer timer;
	Random random;
	boolean pause = false;

	LinkedList<Ball> balls = new LinkedList<>();
	List<Ball> balls__remove = new ArrayList<>();

	double cx = 0;
	double cy = 0;
	
	double mx = 0;
	double my = 0;
	double mx1 = 0;
	double my1 = 0;
	
	int l = 0;

	public Screen() {
		setBackground(Color.WHITE);
		timer = new Timer(DELAY, this);
		timer.start();
		random = new Random();
		MouseListener mouseListener = new MouseListener();
		addKeyListener(new TAdapter());
		addMouseMotionListener(mouseListener);
		addMouseListener(mouseListener);
		setFocusable(true);
		requestFocusInWindow();
		load();

	}

	public void load() {
		try {
		for (int i = 0; i != 10; i++)
			balls.add(new Ball(new Random().nextInt(getWidth()), new Random().nextInt(getHeight())));
		}catch(Exception ex) {
			for (int i = 0; i != 5; i++)
				balls.add(new Ball(new Random().nextInt(500), new Random().nextInt(500)));
			}
	}

	public void unload() {
		balls.clear();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (pause)
			return;
		super.paintComponent(g);
		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawLine((int)mx, (int)my, (int)mx1, (int)my1);
		for (Ball ball : balls) {
			ball.move();
			ball.draw(g);
		}
		l = l+1;
		
		int h = g.getFontMetrics().getHeight();
		
		Utils.drawOutlineString("Balls Size: " + balls.size(), 4, h, Utils.generateColor(l), Color.BLACK, g);
		double p = Math.sqrt(Math.pow(Math.abs(mx-mx1)/50, 2) + Math.pow(Math.abs(my-my1)/50, 2));
		Utils.drawOutlineString("Magnitude: " + p, 4, h*2, Utils.generateColor(l), Color.BLACK, g);

		while (balls.size() > 100) {
			balls.removeFirst();
		}

//		for (Ball ball : balls__remove) {
//			balls.add(ball);
//		}

		g.dispose();
	}

	private void reset() {
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_R) {
				load();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				pause = !pause;
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				pause = true;
				unload();
//				load();
				pause = false;
			}

		}
	}

	private class MouseListener extends MouseAdapter {
		
		

		@Override
		public void mouseReleased(MouseEvent e) {
//			System.out.println(getWidth());
//			for(int i =0;i!=getWidth()/10;i++) {
//				Ball ball = new Ball(i*10,50);
//				ball.setVelocity(new Vector(0,0));
//				balls.add(ball);
//			}
//			
			Ball ball = new Ball(mx,my);
			ball.setVelocity(new Vector((e.getX()-mx)/50, (my-e.getY())/50));
//			ball.setVelocity(new Vector(0, 0));
			balls.add(ball);
			
			mx=0;
			my=0;
			mx1=0;
			my1=0;
		}
		

		@Override
		public void mousePressed(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			
			if(mx == 0 && my == 0)
				return;

			 mx1 = e.getX();
			 my1 = e.getY();

		}
	}

	public void pause() {
		pause = true;
	}

}
