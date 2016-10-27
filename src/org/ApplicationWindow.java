package org;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ApplicationWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private ArrayList<City> cities;

	public ApplicationWindow(ArrayList<City> cities) {
		setSize(1050, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.cities = cities;
	}

	@Override
	public void paint(Graphics graphic) {
		for (City city : cities) {
			graphic.setColor(Color.BLUE);

			graphic.drawOval(city.getX(), city.getY(), 10, 5);
			graphic.fillOval(city.getX(), city.getY(), 10, 5);

			graphic.drawString("Toplam şehir sayısı:" + cities.size(), 100, 680);

			for (Path path : city.getPathList()) {
				graphic.drawLine(city.getX(), city.getY(), path.getConnectedCity().getX(),
						path.getConnectedCity().getY());
			}

		}
	}
}