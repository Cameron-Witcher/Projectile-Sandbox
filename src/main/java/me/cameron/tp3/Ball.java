package me.cameron.tp3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

public class Ball {

	double x;
	double y;
	double xi;
	double yi;

	Vector vel;

	int lifetime = 0;
	double g = 0.19;
	double u = 0;
	double theta = 0;
	double f = 0.009;

	LinkedList<Point> mem = new LinkedList<>();

	public Ball(double x, double y) {
		this.x = x;
		this.y = y;
		Random r = new Random();
		vel = new Vector((r.nextInt(2) + 1) + r.nextDouble() * -1, (r.nextInt(2) + 1) + r.nextDouble());
		theta = Math.atan(vel.getY() / vel.getX());
		u = Math.sqrt(Math.pow(vel.getX(), 2) + Math.pow(vel.getY(), 2));
		xi = x;
		yi = y;

	}

	public void setVelocity(Vector v) {
		vel = v;
		theta = Math.atan(vel.getY() / vel.getX());
		u = Math.sqrt(Math.pow(vel.getX(), 2) + Math.pow(vel.getY(), 2));
	}

	public void move() {
//		double ux = vel.getX();
//		double uy = vel.getY()-g*lifetime;
		lifetime = lifetime + 1;
//		x = xi+(lifetime * (u*Math.cos(theta)));
//		y = yi+((u*Math.sin(theta)*lifetime-((1/2)*g*Math.pow(lifetime, 2))));

//		x=xi+lifetime*u*Math.cos(theta);
//		y=yi-lifetime*u*Math.sin(theta)+Math.pow(((0.5)*g*lifetime), 2);

		vel.y = vel.y - (0.095) * g;

			vel.x = vel.x * 0.9999;
//		vel.y = vel.y * 0.9;

		if (x + vel.x >= Main.getWindow().getScreen().getWidth() || x + vel.x <= 0) {
			vel.x = vel.x * -1;
		}

		if (y - vel.y >= Main.getWindow().getScreen().getHeight() || y - vel.y <= 0) {
			vel.y = vel.y * -0.8;
		}

		x = x + vel.getX();
		y = y - vel.getY();
		if (y+1 >= Main.getWindow().getScreen().getHeight())
			vel.x = vel.x * 0.9;

		mem.add(new Point((int) x, (int) y));

		while (mem.size() > 100) {
			mem.removeFirst();
		}

//		y=yi-((u*Math.sin(theta)*lifetime-((1/2)*g*Math.pow(lifetime, 2))));
//		System.out.println(x);
////		
//		x = xi + vel.getX()*lifetime;
//		y = yi - vel.getY()*lifetime - ((1/2)*g*Math.pow(lifetime, 2));

//		x = ux * lifetime * Math.cos(theta);
//		y = uy * lifetime * Math.sin(theta)-((1/2)*g*Math.pow(lifetime, 2));
////		y = uy/100000 * lifetime * Math.sin(theta) - ((1/2)*g*Math.pow(lifetime, 2));

//		x = x + (u * lifetime * Math.cos(theta));
//		y = y + (vel.getY() * lifetime * Math.sin(theta)
//				+ ((1 / 2) * g * Math.pow(lifetime, 2)));

	}

	public void draw(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.GREEN);

		Point lp = null;
		int l = 0;
		for (Point p : mem) {
			if (lp != null) {
				l = l + 1;
				g.setColor(Utils.generateColor(l,0.03));
				g.drawLine(p.x, p.y, lp.x, lp.y);
			}
			
			lp = p;
		}

//		for(int i =0; i!=100;i++) {
//			
//			g.setColor(new Color(g.getColor().getRed(), i, g.getColor().getBlue()));
//			
//			if(lifetime-i < 0) {
//				continue;
//			}
//			
//			g.drawLine(
//					(int)(xi+(lifetime-i)*u*Math.cos(theta)), 
//					(int)(yi-(lifetime-i)*u*Math.sin(theta)+Math.pow(((0.5)*this.g*(lifetime-i)), 2)), 
//					(int)(xi+(lifetime-(i+1))*u*Math.cos(theta)), 
//					(int)(yi-(lifetime-(i+1))*u*Math.sin(theta)+Math.pow(((0.5)*this.g*(lifetime-(i+1))), 2))
//					);
//			
////			g.fillRect(
////					(int)((xi+(lifetime-i)*u*Math.cos(theta))-2.5), 
////					(int)((yi-(lifetime-i)*u*Math.sin(theta)+Math.pow(((0.5)*this.g*(lifetime-i)), 2))-2.5), 
////					5, 
////					5);
//			
//		}
		g.setColor(Utils.generateColor(lifetime, f));

		g.fillOval((int) x - 5, (int) y - 5, 10, 10);
//		g.drawString("Y: " + (u*Math.sin(theta)-Math.pow(((0.5)*this.g*lifetime),2)),(int) x,(int) y-15);
//		g.drawString("X: " + u*Math.cos(theta),(int) x,(int) y-30);

		g.setColor(color);
	}

}
