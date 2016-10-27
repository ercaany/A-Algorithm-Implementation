package org;

import java.util.ArrayList;

public class Map {
	private int[][] map;

	public Map() {
		map = new int[Application.MAP_ROW][Application.MAP_COLUMN];
		for (int i = 0; i < Application.MAP_ROW; i++) {
			for (int j = 0; j < Application.MAP_COLUMN; j++) {
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
		for (int i = 0; i < Application.MAP_ROW; i++) {
			for (int j = 0; j < Application.MAP_COLUMN; j++) {
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

}
