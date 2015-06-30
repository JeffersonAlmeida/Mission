package com.nasa.mission.model.state;

import static com.nasa.mission.util.RoverComparator.POSITION_X_SORT;
import static com.nasa.mission.util.RoverComparator.POSITION_Y_SORT;
import static com.nasa.mission.util.RoverComparator.getComparator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.nasa.mission.erros.ExecutionErrors;
import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Position;
import com.nasa.mission.model.Rover;
import com.nasa.mission.util.CopyArray;

/**
 * @author Jefferson
 * 
 * The context used in the State Design Pattern
 */
public class Context {
	
	private State state;
	private Rover rover;
	private Plateau plateau;
	private AtomicBoolean free;
	private int ocupiedBy;
	
	public Context(Plateau plateau){
		super();
		ocupiedBy = -1;
		this.plateau = plateau;
		state = null;
		free = new AtomicBoolean();
	}
	
	/**
	 * Verify if the rover can move to the requested position. 
	 * It must be free and inside plateau region.
	 * 
	 * @return true if it can move. Otherwise, false.
	 * @throws MissionException
	 */
	public boolean canIMove() throws MissionException{
		return !state.isBoundaryLimit(this) && isPositionFree();
	}

	private boolean isPositionFree() throws MissionException {
		Position position = state.newPostion(this);
		if (isPositionFree(position)){
			return true;
		}
		String details = "Position Ocupied by Rover Number: " + ocupiedBy;
		throw new MissionException(ExecutionErrors.CANNOT_MOVE_BUSY_POSITION, details); 
	}
	
	private boolean isPositionFree(Position a){
		free.getAndSet(true);
		boolean ok = true;
		List<Rover> rovers = getPlateau().getRovers();
		List<Rover> copy = CopyArray.copy(rovers);
		// It make the search easier
		Collections.sort(copy, getComparator(POSITION_X_SORT, POSITION_Y_SORT));
		Iterator<Rover> i = rovers.iterator();
		if (rovers.size() > 1){
			while (ok && i.hasNext()){
				Rover r = i.next(); 
				Position b = r.getPosition();
				ok = (b.getX() > a.getX()) ? false : (b.getX() < a.getX()) ? true : isVerticalPositionEqualToo(a, b, free) ;
				// Don't forget to inform nasa engineers what rover is in that position.
				if (!free.get())
					ocupiedBy  = r.getId();
			}
		}
		return free.get();
	}
	
	/*if horizontals and verticals positions are equals.
	 *  Bingo, the position is NOT free. Search done!*/
	private boolean isVerticalPositionEqualToo(Position a, Position b, AtomicBoolean free) {
		boolean ok =  (b.getY() == a.getY()) ? free.getAndSet(false) :
			(b.getY() > a.getY() ? false : true );
		return (!free.get()) ? false : ok;
		
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public Rover getRover() {
		return rover;
	}
	public void setRover(Rover rover) {
		this.rover = rover;
	}
	public Plateau getPlateau() {
		return plateau;
	}
}
