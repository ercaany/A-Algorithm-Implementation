package org;

import java.util.ArrayList;
import java.util.List;

public class PrimsAlgorithm {

	private ArrayList<City> MST = new ArrayList<City>();
	private ArrayList<City> visited = new ArrayList<City>();
	private ArrayList<City> cities = new ArrayList<City>();
	private int start;

	public PrimsAlgorithm(int start, ArrayList<City> cities) {
		this.start = start;
		this.cities = cities;
	}

	public ArrayList<City> FindPrimMST() {
		int current = start;
		int totalPath = 1;
		visited.add(cities.get(current));

		while (totalPath < cities.size()) {
			City newCity = findMinPath(cities.get(current));
			current = newCity.getPathList().get(0).getConnectedCity().getCityId();
			visited.add(cities.get(current));
			MST.add(newCity);
			totalPath++;
		}
		return MST;
	}

	public City findMinPath(City city) {
		double distance = 500;
		List<Path> minPath = new ArrayList<Path>();
		Path path = null;
		City newCity = new City(city.getCityId(), city.getX(), city.getY());

		for (int i = 0; i < city.getPathList().size(); i++) {
			if (city.getPathList().get(i).getDistance() < distance
					&& !visited.contains(cities.get(city.getPathList().get(i).getConnectedCity().getCityId()))) {
				distance = city.getPathList().get(i).getDistance();
				path = new Path(city.getPathList().get(i).getConnectedCity(), distance);
			}
		}

		for (City visitedCity : visited) {
			for (int i = 0; i < visitedCity.getPathList().size(); i++) {
				if (visitedCity.getPathList().get(i).getDistance() < distance && !visited
						.contains(cities.get(visitedCity.getPathList().get(i).getConnectedCity().getCityId()))) {
					newCity = new City(visitedCity.getCityId(), visitedCity.getX(), visitedCity.getY());
					distance = visitedCity.getPathList().get(i).getDistance();
					path = new Path(visitedCity.getPathList().get(i).getConnectedCity(), distance);
				}
			}
		}

		minPath.add(path);
		newCity.setPathList(minPath);
		return newCity;
	}
}
