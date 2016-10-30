package org;

public class Path {
	/*
	 * @param connectedCity: bu path'e sahip olan şehrin komşusu
	 * 
	 * @param distance: bu iki şehir arasındaki mesafe:
	 * 
	 */
	private City connectedCity;
	private double distance;

	public Path(City city, double distance) {
		this.connectedCity = city;
		this.distance = distance;
	}

	public City getConnectedCity() {
		return connectedCity;
	}

	public void setConnectedCity(City connectedCity) {
		this.connectedCity = connectedCity;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}