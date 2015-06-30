package com.nasa.mission.model;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	
	public static final int HORIZONTAL_MIN = 0;
	public static final int VERTICAL_MIN = 0;
	
	public static final int HORIZONTAL_MAX = 1000;
	public static final int VERTICAL_MAX = 1000;
	
	private List<Rover> rovers;
	private int horizontalMax;
	private int verticalMax;
	private int capacity;
	
	public Plateau(int horizontalMax, int verticalMax) {
		this.horizontalMax = horizontalMax;
		this.verticalMax = verticalMax;
		setCapacity( horizontalMax * verticalMax );
		rovers = new ArrayList<Rover>();
	}
	
	public List<Rover> getRovers() {
		return rovers;
	}
	
	public int getHorizontalMax() {
		return horizontalMax;
	}

	public int getVerticalMax() {
		return verticalMax;
	}

	public int capacity() {
		return capacity;
	}

	private void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void receiveRovers(List<Rover> rovers) {
		this.rovers = rovers;
	}

}
