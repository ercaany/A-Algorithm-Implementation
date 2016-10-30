package org;

import java.util.ArrayList;
import java.util.HashMap;

public class AStarAlgorithm {
	/*
	 * @param discovered: kuyruk olarak kullanılan liste. bulunan şehirler bu
	 * diziye eklenir
	 * 
	 * @param visited: değerlendirilen şehirleri bulunduran liste. buradaki
	 * şehirlere bir daha bakılmıyor
	 * 
	 * @param cities: tüm şehirlerin bulunduğu liste
	 * 
	 * @param gScoresFromRoot: şehirlerin G skorları tutan dizi
	 * 
	 * @param hScoresToTarget: şehirlerin H skorları tutan dizi
	 * 
	 * @param fScores: şehirlerin f skorlarını tutan dizi
	 * 
	 * @param cameFrom: şehirlerin nereden geldiğini tutan hash map
	 * 
	 * @param maxQueueSize: kuyruğun ulaştığı maksimum boyut
	 * 
	 * @param totalPopFromQueue: kuyruktan yapılan toplam
	 */
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
		// başlangıç şehrinin kuyruğa eklenmesi
		discovered.add(start);
		// başlangıç şehrinin g skorunu hesaplama
		gScoresFromRoot[start.getCityId()] = 0;
		// başlangıç şehrinin f skorunu hesaplama
		fScores[start.getCityId()] = hScoresToTarget[start.getCityId()];

		City current;
		// kuyruk boş değilken dön
		while (!discovered.isEmpty()) {
			// current değişkenine min f skorlu şehri atıyoruz
			current = findMinFScore();
			// eğer bu şehir hedef şehir ise, döngüyü bitir.
			if (current.getCityId() == target.getCityId()) {
				discovered.remove(current);
				break;
			} else {
				// şehri kuyruktan sil
				discovered.remove(current);
				// şehri değerlendirildi listesine ekle
				visited.add(current);
				// bu şehrin tüm komşuları için
				for (Path path : current.getPathList()) {
					// eğer bu şehir değerlendirilmediyse
					if (visited.contains(path.getConnectedCity())) {
					} else {
						double tempGScore = gScoresFromRoot[current.getCityId()] + path.getDistance();
						// eğer bu şehir kuyrukta değilse
						if (!(discovered.contains(path.getConnectedCity()))) {
							// kuyruğa ekle
							discovered.add(path.getConnectedCity());
							// maximum kuyruk boyutu denetle ve hesapla
							if (discovered.size() > maxQueueSize) {
								maxQueueSize = discovered.size();
							}
						}
						// komşunun geldiği yere şuanki şehri ata
						cameFrom.put(path.getConnectedCity(), current);
						// g skorunu hesapla ve ata
						gScoresFromRoot[path.getConnectedCity().getCityId()] = tempGScore;
						// f skorunu hesapla ve ata
						fScores[path.getConnectedCity()
								.getCityId()] = gScoresFromRoot[path.getConnectedCity().getCityId()]
										+ hScoresToTarget[path.getConnectedCity().getCityId()];
					}
				}
			}
		}
		// döngü bittikten sonra oluşan min pathi döndür
		ArrayList<City> minPath = new ArrayList<City>();
		City current1 = target;
		minPath.add(current1);
		while (cameFrom.containsKey(current1)) {
			current1 = cameFrom.get(current1);
			minPath.add(current1);
		}
		return minPath;
	}

	// en düşük f skora sahip olan şehri döndüren method
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
