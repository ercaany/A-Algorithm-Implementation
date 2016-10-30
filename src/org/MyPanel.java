package org;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	private ArrayList<City> cities;
	private ArrayList<City> firstMap;
	private ArrayList<City> afterRandomPaths;
	private ArrayList<City> toDraw;
	private ArrayList<City> MST;
	private ArrayList<City> minPath;
	private ArrayList<City> drawMinPath;

	private ArrayList<Integer> queue;
	private ArrayList<Integer> toDrawQ;

	private int start;
	private int target;
	private int drawStart;
	private int drawTarget;
	private int totalPop;
	private int drawTotalPop;

	public MyPanel(ArrayList<City> cities, int start, int target) {
		this.setPreferredSize(new Dimension(1100, 700));
		addKeyListener(this);
		this.toDraw = cities;
		this.cities = cities;
		this.start = start;
		this.target = target;
		this.drawStart = start;
		this.drawTarget = target;
		this.queue = new ArrayList<Integer>();
		this.toDrawQ = queue;
		this.drawTotalPop = -1;
		this.minPath = new ArrayList<City>();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	@Override
	public void paint(Graphics graphic) {
		graphic.clearRect(0, 0, 1100, 700);
		graphic.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 10));

		for (City city : toDraw) {
			graphic.drawOval(city.getX(), city.getY(), 8, 8);
			graphic.setColor(Color.RED);
			graphic.drawString(String.valueOf(city.getCityId()), city.getX(), city.getY());
			if (city.getCityId() == drawStart) {
				graphic.setColor(Color.GREEN);
			} else if (city.getCityId() == drawTarget) {
				graphic.setColor(Color.ORANGE);
			} else {
				graphic.setColor(Color.BLUE);
			}
			graphic.fillOval(city.getX(), city.getY(), 8, 8);

			for (Path path : city.getPathList()) {
				graphic.setColor(Color.decode("#39DF87"));
				graphic.drawLine(city.getX() + 4, city.getY() + 4, path.getConnectedCity().getX() + 4,
						path.getConnectedCity().getY() + 4);
				graphic.setColor(Color.BLACK);
				graphic.drawString(String.valueOf((int) path.getDistance()),
						(city.getX() + path.getConnectedCity().getX()) / 2,
						(city.getY() + path.getConnectedCity().getY()) / 2);
			}
		}
		if (drawMinPath != null) {
			for (int i = 0; i < drawMinPath.size() - 1; i++) {
				graphic.drawOval(drawMinPath.get(i).getX(), drawMinPath.get(i).getY(), 8, 8);
				graphic.setColor(Color.MAGENTA);
				graphic.drawLine(drawMinPath.get(i).getX() + 4, drawMinPath.get(i).getY() + 4,
						drawMinPath.get(i + 1).getX() + 4, drawMinPath.get(i + 1).getY() + 4);
			}
		}

		graphic.setColor(Color.BLACK);
		graphic.drawString("Start:", 10, 520);
		graphic.setColor(Color.GREEN);
		graphic.drawOval(70, 510, 10, 10);
		graphic.fillOval(70, 510, 10, 10);
		graphic.setColor(Color.BLACK);
		graphic.drawString("Finish:", 10, 540);
		graphic.setColor(Color.ORANGE);
		graphic.drawOval(70, 530, 10, 10);
		graphic.fillOval(70, 530, 10, 10);
		graphic.setColor(Color.BLACK);
		graphic.drawString("Min Path:", 10, 560);
		graphic.setColor(Color.MAGENTA);
		graphic.drawOval(70, 550, 10, 10);
		graphic.fillOval(70, 550, 10, 10);

		graphic.setColor(Color.BLACK);
		graphic.drawString("c: Initial Map", 10, 580);
		graphic.drawString("m: Map with MST", 10, 600);
		graphic.drawString("r: Map with Random Paths", 10, 620);
		graphic.drawString("a: Map with A* Min Path", 10, 640);
		if (drawTotalPop != -1) {
			graphic.drawString("Total Pop: " + drawTotalPop, 10, 660);
		} else {
			graphic.drawString("Total Pop: ", 10, 660);
		}
		graphic.drawString("Queue:", 10, 680);
		for (int i = 0; i < toDrawQ.size(); i++) {
			graphic.drawString(toDrawQ.get(i).toString(), 60 + i * 20, 680);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == 'm') {
			toDraw = MST;
			drawStart = -1;
			drawTarget = -1;
			toDrawQ = new ArrayList<Integer>();
			drawTotalPop = -1;
			drawMinPath = new ArrayList<City>();
			repaint();
		} else if (c == 'c') {
			toDraw = firstMap;
			drawStart = -1;
			drawTarget = -1;
			toDrawQ = new ArrayList<Integer>();
			drawTotalPop = -1;
			drawMinPath = new ArrayList<City>();
			repaint();
		} else if (c == 'r') {
			toDraw = afterRandomPaths;
			drawStart = start;
			drawTarget = target;
			toDrawQ = queue;
			drawTotalPop = totalPop;
			drawMinPath = new ArrayList<City>();
			repaint();
		} else if (c == 'a') {
			toDraw = cities;
			drawStart = start;
			drawTarget = target;
			toDrawQ = queue;
			drawTotalPop = totalPop;
			drawMinPath = minPath;
			repaint();
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	public ArrayList<City> getMST() {
		return MST;
	}

	public void setMST(ArrayList<City> mST) {
		MST = mST;
	}

	public ArrayList<City> getAfterRandomPaths() {
		return afterRandomPaths;
	}

	public void setAfterRandomPaths(ArrayList<City> afterRandomPaths) {
		this.afterRandomPaths = afterRandomPaths;
	}

	public ArrayList<City> getFirstMap() {
		return firstMap;
	}

	public void setFirstMap(ArrayList<City> firstMap) {
		this.firstMap = firstMap;
	}

	public ArrayList<Integer> getQueue() {
		return queue;
	}

	public void setQueue(ArrayList<Integer> queue) {
		this.queue = queue;
	}

	public int getTotalPop() {
		return totalPop;
	}

	public void setTotalPop(int totalPop) {
		this.totalPop = totalPop;
	}

	public ArrayList<City> getMinPath() {
		return minPath;
	}

	public void setMinPath(ArrayList<City> minPath) {
		this.minPath = minPath;
	}

}
