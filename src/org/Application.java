package org;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class Application {

	public static final int MAP_ROW = 990;
	public static final int MAP_COLUMN = 490;
	public static final int CITY_COUNT = 100;
	public static final int RANDOM_PATH_COUNT = 100;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Controller controller = new Controller();
		Map map = new Map();

		ArrayList<City> cities = controller.generateCities();
		map.initializeMap(cities);

		ArrayList<City> firstMap = new ArrayList<City>();
		for (City city : cities) {
			City c = new City(city.getCityId(), city.getX(), city.getY());
			firstMap.add(c);
		}

		JFrame f = new JFrame();
		MyPanel p = new MyPanel(cities, -1, -1);
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

		p.setFirstMap(firstMap);

		int start = controller.readInputFromDialog(
				"Please enter the city number of the start point(between 0-" + (Application.CITY_COUNT - 1));
		int target = controller.readInputFromDialog(
				"Please enter the city number of the target point(between 0-" + (Application.CITY_COUNT - 1));

		p.setStart(start);
		p.setTarget(target);

		controller.connectCities(cities);

		double[] gScoresFromRoot = controller.getGScores();
		double[] fScores = controller.getGScores();
		double[] hScoresToTarget = controller.getHScores(cities.get(target), cities);

		PrimsAlgorithm primsAlgorithm = new PrimsAlgorithm(start, cities);
		ArrayList<City> MST = primsAlgorithm.FindPrimMST();

		ArrayList<City> afterMST = controller.updateCities(MST, cities);
		p.setMST(afterMST);

		ArrayList<City> afterRandomPaths = controller.generateRandomPaths(cities, RANDOM_PATH_COUNT);
		p.setAfterRandomPaths(afterRandomPaths);

		long startTime = System.nanoTime();

		AStarAlgorithm aStar = new AStarAlgorithm(cities, gScoresFromRoot, fScores, hScoresToTarget);
		ArrayList<City> minPath = aStar.findMinPath(cities.get(start), cities.get(target));

		long endTime = System.nanoTime();
		long diffInMilli = endTime - startTime;

		System.out.println(diffInMilli);
		System.out.println(aStar.getMaxQueueSize());
		p.setMinPath(minPath);
		p.setCities(cities);

		for (City c : aStar.getDiscovered()) {
			p.getQueue().add(c.getCityId());
			p.setTotalPop(aStar.getTotalPopFromQueue());
		}

		input.close();

	}
}
