package org;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Controller controller = new Controller();
		Map map = new Map();

		ArrayList<City> cities = controller.generateCities();
		map.initializeMap(cities);
		map.printMap();

		System.out.println("Now enter the city number of the start point:");
		int start = input.nextInt();
		// System.out.println("Now enter the city number of the target point:");
		// int target = input.nextInt();

		controller.calculateAirDistances(cities);

		PrimAlgorithm pa = new PrimAlgorithm(start, cities);

		List<City> MST = pa.FindPrimMST();
		System.out.println("Minimum Spanning Tree: ");
		for (int i = 0; i < MST.size(); i++) {
			System.out.println("Şehir id = " + MST.get(i).getCityId() + " komşu id:"
					+ MST.get(i).getPathList().get(0).getConnectedCity().getCityId() + " uzaklık: "
					+ MST.get(i).getPathList().get(0).getDistance());
		}

		input.close();
	}
}
