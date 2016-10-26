package org;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
	public Controller() {

	}

	public ArrayList<City> generateCities() {
		ArrayList<City> cities = new ArrayList<City>();
		Random generator = new Random();
		int x, y;

		while (cities.size() < 10) {
			x = generator.nextInt(10);
			y = generator.nextInt(10);

			if (!isGenerated(cities, x, y)) {
				cities.add(new City(cities.size(), x, y));
			}
		}
		return cities;
	}

	public boolean isGenerated(ArrayList<City> cities, int x, int y) {
		boolean generated = false;
		for (City city : cities) {
			if (city.getX() == x && city.getY() == y) {
				generated = true;
			}
		}
		return generated;
	}

	public void calculateAirDistances(ArrayList<City> cities) {
		int xDiff, yDiff;
		for (City c : cities) {
			for (City c2 : cities) {
				if (c != c2) {
					xDiff = c.getX() - c2.getX();
					yDiff = c.getY() - c2.getY();
					double airDistance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
					Path path = new Path(c2, airDistance);
					c.getPathList().add(path);
				}
			}
		}
	}
}
