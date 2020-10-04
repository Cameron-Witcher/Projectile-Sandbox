package me.cameron.tp3;

import java.awt.Color;
import java.awt.Graphics;
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

	public Ball(double x, double y) {
		this.x = x;
		this.y = y;
		Random r = new Random();
		vel = new Vector((r.nextInt(2)+1)+r.nextDouble(), (r.nextInt(2)+1)+r.nextDouble());
		theta =Math.atan(vel.getY() / vel.getX());
		u = Math.sqrt(Math.pow(vel.getX(), 2) + Math.pow(vel.getY(), 2));
		xi = x;
		yi = y;
		
	}
	
	public void setVelocity(Vector v) {
		vel = v;
		theta =Math.atan(vel.getY() / vel.getX());
		u = Math.sqrt(Math.pow(vel.getX(), 2) + Math.pow(vel.getY(), 2));
	}

	public void move() {
//		double ux = vel.getX();
//		double uy = vel.getY()-g*lifetime;
		lifetime = lifetime + 1;
//		x = xi+(lifetime * (u*Math.cos(theta)));
//		y = yi+((u*Math.sin(theta)*lifetime-((1/2)*g*Math.pow(lifetime, 2))));
		
		x=xi+lifetime*u*Math.cos(theta);
		y=yi-lifetime*u*Math.sin(theta)+Math.pow(((0.5)*g*lifetime), 2);
		
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
		g.setColor(Color.RED);
		for(int i =0; i!=100;i++) {
			
			g.setColor(new Color(g.getColor().getRed(), i, g.getColor().getBlue()));
			
			if(lifetime-i < 0) {
				continue;
			}
			
			g.drawLine(
					(int)(xi+(lifetime-i)*u*Math.cos(theta)), 
					(int)(yi-(lifetime-i)*u*Math.sin(theta)+Math.pow(((0.5)*this.g*(lifetime-i)), 2)), 
					(int)(xi+(lifetime-(i+1))*u*Math.cos(theta)), 
					(int)(yi-(lifetime-(i+1))*u*Math.sin(theta)+Math.pow(((0.5)*this.g*(lifetime-(i+1))), 2))
					);
			
//			g.fillRect(
//					(int)((xi+(lifetime-i)*u*Math.cos(theta))-2.5), 
//					(int)((yi-(lifetime-i)*u*Math.sin(theta)+Math.pow(((0.5)*this.g*(lifetime-i)), 2))-2.5), 
//					5, 
//					5);
			
		}
		g.setColor(Utils.generateColor(lifetime,f));
		
		
		g.fillOval((int)x-5, (int)y-5, 10, 10);
//		g.drawString("Y: " + (u*Math.sin(theta)-Math.pow(((0.5)*this.g*lifetime),2)),(int) x,(int) y-15);
//		g.drawString("X: " + u*Math.cos(theta),(int) x,(int) y-30);
		
		g.setColor(color);
	}

}
