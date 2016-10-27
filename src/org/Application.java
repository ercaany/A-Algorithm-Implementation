package org;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

	public static final int MAP_SIZE = 600;
	public static final int MAP_COLUMN = 600;
	public static final int CITY_COUNT = 100;

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

		// System.out.println("Minimum Spanning Tree: ");
		// for (int i = 0; i < MST.size(); i++) {
		// System.out.println("Şehir id = " + MST.get(i).getCityId() + " komşu
		// id:"
		// + MST.get(i).getPathList().get(0).getConnectedCity().getCityId() + "
		// uzaklık: "
		// + MST.get(i).getPathList().get(0).getDistance());
		// }

		controller.updateCities(MST, cities); // resetting all paths and
												// creating new roads using MST
		controller.generateRandomPaths(cities, 25);

		ApplicationWindow window = new ApplicationWindow(cities);

		window.paint(null);
		// System.out.println("Road List: ");
		// for (City city : cities) {
		// for (Path path : city.getPathList()) {
		// System.out.println("Road = from: " + city.getCityId() + " to: " +
		// path.getConnectedCity().getCityId()
		// + " distance: " + path.getDistance());
		// }
		// }

		input.close();
	}
}
