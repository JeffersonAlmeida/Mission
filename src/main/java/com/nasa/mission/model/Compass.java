package com.nasa.mission.model;

public enum Compass{
	
	N {
		@Override
		public Compass right() {
			return E;
		}
		@Override
		public Compass left() {
			return W;
		}
	},		
	
	E {
		@Override
		public Compass right() {
			return S;
		}
		@Override
		public Compass left() {
			return N;
		}
	},
	
	S {
		@Override
		public Compass right() {
			return W;
		}
		@Override
		public Compass left() {
			return E;
		}
	},
	
	W {
		@Override
		public Compass right() {
			return N;
		}
		@Override
		public Compass left() {
			return S;
		}
	};
	
	/**
	 * Change Rovers' directions to the right
	 * @return a Compass Letter
	 */
	public abstract Compass right();
	
	/**
	 * Change Rovers' directions to the left
	 * @return a Compass Letter
	 */
	public abstract Compass left();
	
	public static final String options() {
		Compass[] array = Compass.values();
		String options = "";
		for (Compass c: array)
			options += " " + c.toString() ;
		return options;
	}
	
	
}
