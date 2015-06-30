package com.nasa.mission.model.state;

import com.nasa.mission.erros.ExecutionErrors;
import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Position;

public class WestState implements State {

	@Override
	public void move(Context context) {
		context.getRover().setPosition(newPostion(context));
		context.setState(this);
	}
	
	@Override
	public boolean isBoundaryLimit(Context context) throws MissionException {
		if ( newPostion(context).getX() >= Plateau.HORIZONTAL_MIN)
			return false;
		throw new MissionException(ExecutionErrors.CANNOT_MOVE_BOUNDARY_LIMIT);
	}

	@Override
	public Position newPostion (Context context){
		Position p = context.getRover().getPosition();
		return new Position(p.getX() - 1, p.getY(), p.getLetter());
	}
}
