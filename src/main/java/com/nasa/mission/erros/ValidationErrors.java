package com.nasa.mission.erros;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.util.Constants;

/**
 * @author Jefferson
 * 
 * Separation of Concerns:
 * 
 * ValidationErrors define error messages in validationsErros.properties
 * and uses a singleton instance to access her "strings"
 * 
 * When you need to change the error message.
 * This property file is the only place you have to go.
 */
public enum ValidationErrors implements IError {

	MISSING_PLATEAU_PARAMETER {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	FILE_MAY_BE_EMPTY {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	MISSING_POSITION {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	NOT_VALID_INSTRUCTION {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	MISSING_INSTRUCTION {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	POSITION_X_TOO_BIG{
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	POSITION_Y_TOO_BIG {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	NOT_VALID_LETTER {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name()); 
		}
	},
	
	MISSING_LETTER {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	PLATEAU_X_MUST_BE_A_NUMBER {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	PLATEAU_Y_MUST_BE_A_NUMBER {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	PLATEAU_X_TOO_SMALL {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name()) + Plateau.VERTICAL_MIN;
		}
	},
	
	PLATEAU_X_TOO_BIG {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name()) + Plateau.HORIZONTAL_MAX;
		}
	},
	
	PLATEAU_Y_TOO_SMALL {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name()) + Plateau.VERTICAL_MIN;
		}
	},
	
	PLATEAU_Y_TOO_BIG {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name()) + Plateau.VERTICAL_MAX;
		}
	},
	
	POSITION_X_MUST_BE_A_NUMBER {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	POSITION_Y_MUST_BE_A_NUMBER {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	POSITION_X_TOO_SMALL {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	POSITION_Y_TOO_SMALL {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	NOT_ENOUGH_SPACE {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	BUSY_POSTION_ON_LANDING {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	FILE_NOT_FOUND {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	};
	
	
	private static Properties instance = null;
	public static Properties getInstance() {
		if(instance == null) {
			String propFileName = Constants.VALIDATION_ERRORS;
			File file = new File(propFileName);
			FileInputStream fileInput;
			instance = new Properties();
			try {
				fileInput = new FileInputStream(file);
				instance.load(fileInput);
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
}
