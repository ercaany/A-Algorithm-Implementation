package org;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Controller {
	public Controller() {

	}

	public ArrayList<City> generateCities() {
		ArrayList<City> cities = new ArrayList<City>();
		Random generator = new Random();
		int x, y;

		while (cities.size() < Application.CITY_COUNT) {
			x = generator.nextInt(Application.MAP_ROW - 10) + 10;
			y = generator.nextInt(Application.MAP_COLUMN - 10) + 10;

			if (!isGenerated(cities, x, y)) {
				cities.add(new City(cities.size(), x, y));
			}
		}
		return cities;
	}

	public boolean isGenerated(ArrayList<City> cities, int x, int y) {
		boolean generated = false;
		for (City city : cities) {
			if (x - 50 <= city.getX() && city.getX() <= x + 50 && y - 50 <= city.getY() && city.getY() <= y + 50) {
				generated = true;
			}
		}
		return generated;
	}

	public void connectCities(ArrayList<City> cities) {
		for (City c1 : cities) {
			for (City c2 : cities) {
				if (c1 != c2) {
					Path path = new Path(c2, airDistance(c1, c2));
					c1.getPathList().add(path);
				}
			}
		}
	}

	// updates the cities after finding MST and adds the roads
	public ArrayList<City> updateCities(ArrayList<City> MST, ArrayList<City> cities) {
		for (City c : cities) {
			c.getPathList().clear();
		}
		double distance;

		for (int i = 0; i < MST.size(); i++) {
			for (City city : cities) {
				if (MST.get(i).getCityId() == city.getCityId()) {
					distance = realDistance(MST.get(i).getPathList().get(0).getDistance());
					MST.get(i).getPathList().get(0).setDistance(distance);
					city.getPathList().add(MST.get(i).getPathList().get(0));
					cities.get(MST.get(i).getPathList().get(0).getConnectedCity().getCityId()).getPathList()
							.add(new Path(city, distance));
				}
			}
		}
		ArrayList<City> afterMST = new ArrayList<City>();
		for (City city : cities) {
			City c = new City(city.getCityId(), city.getX(), city.getY());
			ArrayList<Path> pathList = new ArrayList<Path>();
			for (Path path : city.getPathList()) {
				pathList.add(path);
			}
			c.setPathList(pathList);
			afterMST.add(c);
		}

		return afterMST;
	}

	public ArrayList<City> generateRandomPaths(ArrayList<City> cities, int count) {
		Random generator = new Random();
		int city1, city2;
		int pathCount = 0;
		double distance;

		while (pathCount < count) {
			city1 = generator.nextInt(Application.CITY_COUNT);
			city2 = generator.nextInt(Application.CITY_COUNT);

			if (city1 != city2) {
				distance = realDistance(airDistance(cities.get(city1), cities.get(city2)));
				Path path1 = new Path(cities.get(city1), distance);
				Path path2 = new Path(cities.get(city2), distance);
				cities.get(city1).getPathList().add(path2);
				cities.get(city2).getPathList().add(path1);
				pathCount++;
			}
		}
		ArrayList<City> afterRandomPaths = new ArrayList<City>();
		for (City city : cities) {
			City c = new City(city.getCityId(), city.getX(), city.getY());
			ArrayList<Path> pathList = new ArrayList<Path>();
			for (Path path : city.getPathList()) {
				pathList.add(path);
			}
			c.setPathList(pathList);
			afterRandomPaths.add(c);
		}
		return afterRandomPaths;
	}

	public double airDistance(City city1, City city2) {
		double xDiff = city1.getX() - city2.getX();
		double yDiff = city1.getY() - city2.getY();
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}

	public double[] getGScores() {
		double[] gScores = new double[Application.CITY_COUNT];

		for (int i = 0; i < gScores.length; i++) {
			gScores[i] = 9999.0;
		}
		return gScores;
	}

	public double[] getHScores(City target, ArrayList<City> cities) {
		double[] hScores = new double[Application.CITY_COUNT];

		for (int i = 0; i < cities.size(); i++) {
			hScores[i] = airDistance(cities.get(i), target);
		}
		return hScores;
	}

	public double realDistance(double airDistance) {
		double percent = 30.0;
		return (((airDistance * percent) / 100.0) + airDistance);
	}

	public int readInputFromDialog(String message) {
		int input = -1;
		boolean ready = false;
		while (!ready) {
			try {
				String stringInput = JOptionPane.showInputDialog(message);
				input = Integer.parseInt(stringInput);
				if (input >= 0 && input < Application.CITY_COUNT) {
					ready = true;
				} else {
					ready = false;
				}
			} catch (Exception e) {
				ready = false;
			}
		}
		return input;
	}

}
