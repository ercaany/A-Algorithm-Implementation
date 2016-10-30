package org;

import java.util.ArrayList;
import java.util.HashMap;

public class AStarAlgorithm {

	private ArrayList<City> discovered = new ArrayList<City>();
	private ArrayList<City> visited = new ArrayList<City>();
	private ArrayList<City> cities;

	private double[] gScoresFromRoot;
	private double[] hScoresToTarget;
	private double[] fScores;

	private HashMap<City, City> cameFrom = new HashMap<City, City>();
	private int maxQueueSize;
	private int totalPopFromQueue;

	public AStarAlgorithm(ArrayList<City> cities, double[] gScoresFromRoot, double[] fScores,
			double[] hScoresToTarget) {
		this.cities = cities;
		this.gScoresFromRoot = gScoresFromRoot;
		this.hScoresToTarget = hScoresToTarget;
		this.fScores = fScores;
		this.maxQueueSize = 0;
		this.totalPopFromQueue = 0;
	}

	public ArrayList<City> findMinPath(City start, City target) {
		discovered.add(start);
		gScoresFromRoot[start.getCityId()] = 0;
		fScores[start.getCityId()] = hScoresToTarget[start.getCityId()];

		City current;
		while (!discovered.isEmpty()) {
			current = findMinFScore();

			if (current.getCityId() == target.getCityId()) {
				discovered.remove(current);
				break;
			} else {

				discovered.remove(current);
				visited.add(current);

				for (Path path : current.getPathList()) {
					if (visited.contains(path.getConnectedCity())) {
					} else {
						double tempGScore = gScoresFromRoot[current.getCityId()] + path.getDistance();
						if (!(discovered.contains(path.getConnectedCity()))) {
							discovered.add(path.getConnectedCity());
							if (discovered.size() > maxQueueSize) {
								maxQueueSize = discovered.size();
							}
						}
						cameFrom.put(path.getConnectedCity(), current);
						gScoresFromRoot[path.getConnectedCity().getCityId()] = tempGScore;
						fScores[path.getConnectedCity()
								.getCityId()] = gScoresFromRoot[path.getConnectedCity().getCityId()]
										+ hScoresToTarget[path.getConnectedCity().getCityId()];
					}
				}
			}
		}
		ArrayList<City> minPath = new ArrayList<City>();
		City current1 = target;
		minPath.add(current1);
		while (cameFrom.containsKey(current1)) {
			current1 = cameFrom.get(current1);
			minPath.add(current1);
		}
		return minPath;
	}

	public City findMinFScore() {
		double minFScore = 9999.0;
		int minCityID = 0;

		for (City city : discovered) {
			if (fScores[city.getCityId()] < minFScore) {
				minFScore = fScores[city.getCityId()];
				minCityID = city.getCityId();
			}
		}
		totalPopFromQueue++;
		return cities.get(minCityID);
	}

	public ArrayList<City> getDiscovered() {
		return discovered;
	}

	public void setDiscovered(ArrayList<City> discovered) {
		this.discovered = discovered;
	}

	public ArrayList<City> getVisited() {
		return visited;
	}

	public void setVisited(ArrayList<City> visited) {
		this.visited = visited;
	}

	public int getMaxQueueSize() {
		return maxQueueSize;
	}

	public void setMaxQueueSize(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
	}

	public int getTotalPopFromQueue() {
		return totalPopFromQueue;
	}

	public void setTotalPopFromQueue(int totalPopFromQueue) {
		this.totalPopFromQueue = totalPopFromQueue;
	}

	public void printCameFrom() {

	}

}
