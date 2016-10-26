package org;

import java.util.ArrayList;

public class Map {
	private int[][] map;
	private final int size = 10;

	public Map() {
		map = new int[10][10];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i][j] = -1;
			}
		}
	}

	public void initializeMap(ArrayList<City> cities) {
		for (City c : cities) {
			map[c.getX()][c.getY()] = c.getCityId();
		}
	}

	public void printMap() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] != -1) {
					if (map[i][j] < 10) {
						System.out.print("C0" + map[i][j] + " ");
					} else {
						System.out.print("C" + map[i][j] + " ");
					}
				} else {
					System.out.print("--- ");
				}
			}
			System.out.println("\n");
		}
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public int getSize() {
		return size;
	}

}
