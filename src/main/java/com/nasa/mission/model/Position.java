package com.nasa.mission.model;

public class Position {
	
	private int x;
	private int y;
	private Compass letter;
	
	public Position(int x, int y, Compass letter) {
		super();
		this.x = x;
		this.y = y;
		this.letter = letter;
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
	public Compass getLetter() {
		return letter;
	}
	public void setLetter(Compass letter) {
		this.letter = letter;
	}
	@Override
	public String toString() {
		return "" + x + " " + y + " " + letter + "";
	}
}
