package com.nasa.mission.erros;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.nasa.mission.util.Constants;

/**
 * @author Jefferson
 * 
 * Separation of Concerns:
 * 
 * ExecutionErrors define error messages in executionErros.properties
 * and uses a singleton instance to access her "strings"
 * 
 * When you need to change the error message.
 * This property file is the only place you have to go.
 */
public enum ExecutionErrors implements IError {
	
	CANNOT_MOVE_BOUNDARY_LIMIT {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	},
	
	CANNOT_MOVE_BUSY_POSITION {
		@Override
		public String getMessage() {
			return getInstance().getProperty(this.name());
		}
	};
	
	/**
	 * Singleton Design Pattern
	 * 
	 * Ensuring Properties has only one instance,
	 * and provide a global point of access to it.
	 */
	private static Properties instance = null;
	public static Properties getInstance() {
		if(instance == null) {
			String propFileName = Constants.EXECUTION_ERRORS;
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
