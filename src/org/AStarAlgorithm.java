package org;

import java.util.ArrayList;

public class AStarAlgorithm {

	private ArrayList<City> discovered = new ArrayList<City>();
	private ArrayList<City> visited = new ArrayList<City>();
	private ArrayList<City> cities;

	private double[] gScoresFromRoot;
	private double[] hScoresToTarget;
	private double[] fScores;

	public AStarAlgorithm(ArrayList<City> cities, double[] gScoresFromRoot, double[] fScores,
			double[] hScoresToTarget) {
		this.cities = cities;
		this.gScoresFromRoot = gScoresFromRoot;
		this.hScoresToTarget = hScoresToTarget;
		this.fScores = fScores;
	}

	public void findMinPath(City start, City target) {
		discovered.add(start);
		gScoresFromRoot[start.getCityId()] = 0;
		fScores[start.getCityId()] = hScoresToTarget[start.getCityId()];

		City current;
		while (!discovered.isEmpty()) {
			current = findMinFScore();
			System.out.println("New current found: " + current.getCityId());

			if (current.getCityId() == target.getCityId()) {
				System.out.println("bitti");
				break;
			}
			System.out.println("Maximum discovered queue size: " + discovered.size());
			discovered.remove(current);
			System.out.println("Removed: " + current.getCityId() + " from discovered list");
			visited.add(current);
			System.out.println("Added: " + current.getCityId() + " to the visited list");

			for (Path path : current.getPathList()) {
				if (visited.contains(path.getConnectedCity())) {
				} else {
					double tempGScore = gScoresFromRoot[current.getCityId()] + path.getDistance();
					if (!discovered.contains(path.getConnectedCity())) {
						discovered.add(path.getConnectedCity());
						System.out.println("Added: " + path.getConnectedCity().getCityId() + " to the discovered list");
					}
					gScoresFromRoot[path.getConnectedCity().getCityId()] = tempGScore;
					fScores[path.getConnectedCity().getCityId()] = gScoresFromRoot[path.getConnectedCity().getCityId()]
							+ hScoresToTarget[path.getConnectedCity().getCityId()];
					System.out.println("New Fscore for: " + path.getConnectedCity().getCityId() + " and new fscore= G:"
							+ gScoresFromRoot[path.getConnectedCity().getCityId()] + " + H:"
							+ hScoresToTarget[path.getConnectedCity().getCityId()]);

				}
			}
		}
	}

	public City findMinFScore() {
		double minFScore = 9999.0;
		int minCityID = 0;

		for (City city : discovered) {
			System.out.println("city ID: " + city.getCityId() + " Min F Score: " + fScores[city.getCityId()]);
			if (fScores[city.getCityId()] < minFScore) {
				minFScore = fScores[city.getCityId()];
				minCityID = city.getCityId();
				System.out.println("Min city ID: " + minCityID + " Min F Score: " + minFScore);
			}
		}

		return cities.get(minCityID);
	}

}
