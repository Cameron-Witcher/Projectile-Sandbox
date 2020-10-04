package me.cameron.tp3;

import java.awt.Color;
import java.awt.Graphics;

public class Utils {

	public static Color generateColor(int seed) {
		return generateColor(seed, 0.003);
	}

	public static Color generateColor(int seed, double frequency) {
		int red = (int) (Math.sin(frequency * seed + 0) * 127 + 128);
		int green = (int) (Math.sin(frequency * seed + 2 * Math.PI / 3) * 127 + 128);
		int blue = (int) (Math.sin(frequency * seed + 4 * Math.PI / 3) * 127 + 128);
		if (red > 255)
			red = 255;
		if (green > 255)
			green = 255;
		if (blue > 255)
			blue = 255;

		return new Color(red, green, blue);
	}

	public static void drawOutlineString(String string, int x, int y, Color text, Color outline, Graphics g) {
		drawOutlineString(string, x, y, text, outline, false, g);
	}

	public static void drawOutlineString(String string, int x, int y, Color text, Color outline, boolean extraBold,
			Graphics g) {
		g.setColor(outline);
		g.drawString(string, x, y);
		g.drawString(string, x - 1, y);
		g.drawString(string, x + 1, y);
		g.drawString(string, x, y - 1);
		g.drawString(string, x, y + 1);
		if (extraBold) {
			g.drawString(string, x + 1, y + 1);
			g.drawString(string, x + 1, y - 1);
			g.drawString(string, x - 1, y + 1);
			g.drawString(string, x - 1, y - 1);
		}

		g.setColor(text);
		g.drawString(string, x, y);
	}

}
