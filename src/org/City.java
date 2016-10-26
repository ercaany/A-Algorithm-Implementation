package org;

import java.util.ArrayList;
import java.util.List;

public class City {

	private List<Path> pathList;
	private int cityId;
	private int x;
	private int y;

	public City(int id, int x, int y) {
		this.cityId = id;
		this.x = x;
		this.y = y;
		pathList = new ArrayList<Path>();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public List<Path> getPathList() {
		return pathList;
	}

	public void setPathList(List<Path> pathList) {
		this.pathList = pathList;
	}

}
