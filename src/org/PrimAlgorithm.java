package org;

import java.util.ArrayList;
import java.util.List;

public class PrimAlgorithm {

	private List<City> MST = new ArrayList<City>();
	private int[] visited = new int[10];
	private int start;

	public PrimAlgorithm(int start, int size) {
		this.start = start;
		for (int i = 0; i < 10; i++) {
			visited[i] = 0;
		}
	}

	public List<City> FindPrimMST(List<City> cities) {
		int current = start;
		int totalPath = 1;
		visited[current] = 1;

		while (totalPath < cities.size()) {
			City newCity = findMinPath(cities.get(current));
			visited[current] = 1;
			current = newCity.getPathList().get(0).getConnectedCity().getCityId();
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
					&& visited[city.getPathList().get(i).getConnectedCity().getCityId()] != 1) {
				distance = city.getPathList().get(i).getDistance();
				path = new Path(city.getPathList().get(i).getConnectedCity(), distance);
			}
		}
		minPath.add(path);
		newCity.setPathList(minPath);
		return newCity;
	}
}
