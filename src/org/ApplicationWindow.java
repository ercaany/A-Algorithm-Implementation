package org;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ApplicationWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private int start;
	private int target;
	private ArrayList<City> cities;

	public ApplicationWindow(ArrayList<City> cities, int start, int target) {
		setSize(700, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.cities = cities;
		this.start = start;
		this.target = target;
	}

	@Override
	public void paint(Graphics graphic) {
		for (City city : cities) {
			graphic.setFont(new Font("TimesRoman", Font.PLAIN, 14));

			graphic.drawOval(city.getX(), city.getY(), 8, 8);
			graphic.setColor(Color.RED);
			graphic.drawString(String.valueOf(city.getCityId()), city.getX(), city.getY());
			if (city.getCityId() == start) {
				graphic.setColor(Color.GREEN);
			} else if (city.getCityId() == target) {
				graphic.setColor(Color.CYAN);
			} else {
				graphic.setColor(Color.BLUE);
			}
			graphic.fillOval(city.getX(), city.getY(), 8, 8);
			graphic.setColor(Color.BLACK);

			for (Path path : city.getPathList()) {
				graphic.drawLine(city.getX() + 4, city.getY() + 4, path.getConnectedCity().getX() + 4,
						path.getConnectedCity().getY() + 4);
				graphic.drawString(String.valueOf((int) path.getDistance()),
						(city.getX() + path.getConnectedCity().getX()) / 2,
						(city.getY() + path.getConnectedCity().getY()) / 2);
			}
		}
	}
}