package com.nasa.mission.model.state;

import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Position;

/**
 * @author Jefferson
 * 
 * State Design Pattern
 * Allow an object to alter its behavior when its internal state changes.
 * 
 * Each state is responsible for implementing his own way of exploring the plateau.
 */
public interface State {
	
	/**
	 * The rover moves on the land according to his compass letter
	 * 
	 * NorthState: (x y N) => (x y+1 N)
	 * SouthState: (x y S) => (x y-1 S)
	 * EastState: (x y E) => (x+1 y E)
	 * WetState: (x y W) => (x-1 y W)
	 * 
	 * @param context
	 * @throws MissionException
	 */
    void move (Context context) throws MissionException;
    
    /**
     * Each state knows what is his limit on the land.
     * 
     * @param context
     * @return
     * @throws MissionException
     */
    boolean isBoundaryLimit (Context context) throws MissionException;
    
    /**
     * Each state knows what is the next step and how to do that.
     * 
     * @param context
     * @return
     */
    Position newPostion (Context context);
}
