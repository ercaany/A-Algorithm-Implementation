package org;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ApplicationWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<City> cities;

	public ApplicationWindow(ArrayList<City> cities) {
		setTitle("A Star");
		setSize(960, 960);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.cities = cities;
	}

	@Override
	public void paint(Graphics g) {
		for (City city : cities) {
			// g.setColor(Color.GREEN);
			g.drawOval(city.getX(), city.getY(), 5, 5);
			g.fillOval(city.getX(), city.getY(), 5, 5);

			for (Path path : city.getPathList()) {
				g.drawLine(city.getX(), city.getY(), path.getConnectedCity().getX(), path.getConnectedCity().getY());
			}

		}
	}

}