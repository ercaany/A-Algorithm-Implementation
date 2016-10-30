package org;

import java.util.ArrayList;
import java.util.List;

public class PrimsAlgorithm {
	/*
	 * @param MST: bulunan minimum spanning tree'yi tutan liste
	 * 
	 * @param visited: MST hesaplanırken ziyaret edilen şehirleri tutan liste
	 * 
	 * @param cities: şehirleri tutan liste
	 * 
	 * 
	 */
	private ArrayList<City> MST = new ArrayList<City>();
	private ArrayList<City> visited = new ArrayList<City>();
	private ArrayList<City> cities = new ArrayList<City>();
	private int start;

	public PrimsAlgorithm(int start, ArrayList<City> cities) {
		this.start = start;
		this.cities = cities;
	}

	// minimum spanning tree'yi bulan method
	public ArrayList<City> FindPrimMST() {
		int current = start;// başlangıcın currente atanması
		int totalPath = 1; // toplam yol
		visited.add(cities.get(current)); // başlangıcı ziyaret edilenler
											// listesine ekleme

		while (totalPath < cities.size()) {
			// current'a min path olan şehrin atanması
			City newCity = findMinPath(cities.get(current));
			//
			current = newCity.getPathList().get(0).getConnectedCity().getCityId();
			// ziyaret edildi olarak işaretlenmesi
			visited.add(cities.get(current));
			// şehrin MST'ye eklenmesi
			MST.add(newCity);
			totalPath++;
		}
		return MST;
	}

	// en kısa yolu bulan method
	public City findMinPath(City city) {
		double distance = 500;
		List<Path> minPath = new ArrayList<Path>();
		Path path = null;
		City newCity = new City(city.getCityId(), city.getX(), city.getY());
		// şehrin komşularına sırayla bakarak daha kısa yol varsa bu şehri ve
		// komşusunu path olarak oluşturuyoruz
		for (int i = 0; i < city.getPathList().size(); i++) {
			if (city.getPathList().get(i).getDistance() < distance
					&& !visited.contains(cities.get(city.getPathList().get(i).getConnectedCity().getCityId()))) {
				distance = city.getPathList().get(i).getDistance();
				path = new Path(city.getPathList().get(i).getConnectedCity(), distance);
			}
		}
		// şuana kadar ziyaret edilen şehirleri de kontrol ederek daha kısa olup
		// olmadığını kontrol ediyoruz.
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
		// minimum path'i toplam yola ekleyip devam ediyoruz
		minPath.add(path);
		newCity.setPathList(minPath);
		return newCity;
	}
}
