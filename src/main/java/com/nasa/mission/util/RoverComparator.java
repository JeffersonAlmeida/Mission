package com.nasa.mission.util;

import java.util.Comparator;

import com.nasa.mission.model.Rover;

public enum RoverComparator implements Comparator<Rover>{

	/**
	 * Compare Rovers by the X position paramenter
	 */
	POSITION_X_SORT{
		@Override
		public int compare(Rover r1, Rover r2) {
			return Integer.valueOf(r1.getPosition().getX()).compareTo(r2.getPosition().getX());
		}
	},
	
	/**
	 * Compare Rovers by the Y position paramenter
	 */
	POSITION_Y_SORT{
		@Override
		public int compare(Rover r1, Rover r2) {
			return Integer.valueOf(r1.getPosition().getY()).compareTo(r2.getPosition().getY());
		}
	};
	
	/**
	 * Sort the rovers list using two different Comparators.
	 * First order by POSITION_X_SORT and then by POSITION_Y_SORT
	 * 
	 * This strategy makes rovers search easier 
	 * 
	 * @param comparators: RoverComparators to be applied
	 * @return Returns a Comparator object to be used in Collections.sort()
	 * 
	 * @see Collections.sort();
	 */
	 public static Comparator<Rover> getComparator(final RoverComparator... comparators) {
		 return new Comparator<Rover>(){
			@Override
			public int compare(Rover r1, Rover r2) {
				for (RoverComparator rc : comparators){
					int result = rc.compare(r1, r2);
					if (result != 0) {
                        return result;
                    } 
				}
				return 0;
			}
		 };
	 }
}
