package org;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

	public static final int MAP_ROW = 1000;
	public static final int MAP_COLUMN = 650;
	public static final int CITY_COUNT = 60;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Controller controller = new Controller();
		Map map = new Map();

		ArrayList<City> cities = controller.generateCities();
		map.initializeMap(cities);

		System.out.println("Now enter the city number of the start point:");
		int start = input.nextInt();
		// System.out.println("Now enter the city number of the target point:");
		// int target = input.nextInt();

		controller.connectCities(cities);

		PrimAlgorithm primsAlgorithm = new PrimAlgorithm(start, cities);
		ArrayList<City> MST = primsAlgorithm.FindPrimMST();

		controller.updateCities(MST, cities);
		controller.generateRandomPaths(cities, 0);

		ApplicationWindow window = new ApplicationWindow(cities);
		window.paint(null);

		input.close();
	}
}
