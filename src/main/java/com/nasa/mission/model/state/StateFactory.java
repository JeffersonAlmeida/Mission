package com.nasa.mission.model.state;

import com.nasa.mission.model.Compass;

public class StateFactory {
	public static State build(Compass c){
		State state = null;
		switch (c) {
		case N:
			state = new NorthState();
			break;
		case S:
			state = new SouthState();
			break;
		case E:
			state = new EastState();
			break;
		case W:
			state = new WestState();
			break;
		default:
			break;
		}
		return state;
	}
}
