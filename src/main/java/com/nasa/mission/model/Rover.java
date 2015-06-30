package com.nasa.mission.model;

import java.util.List;

public class Rover {
	
	private static int count = 0;
	
	private int id;
	private Position position;
	private List<Instruction> instructions;
	
	public Rover(Position position, List<Instruction> instructions) {
		this.position = position;
		this.instructions = instructions;
		count++;
		id = count;
	}

	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position.setX(position.getX());
		this.position.setY(position.getY());
		this.position.setLetter(position.getLetter());
	}
	
	public void left() {
		position.setLetter(position.getLetter().left());
	}
	
	public void right() {
		position.setLetter(position.getLetter().right());
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Rover " + id + ": " + position + " - instructions = " + instructions
				+ "";
	}

	public String position() {
		return "Rover: " + position;
	}
}
