package com.nasa.mission.exceptions;

import com.nasa.mission.erros.IError;

public class MissionException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private IError error;
	private String details;
	
	public MissionException(IError error) {
		this.error = error;
		details = "";
	}
	
	public MissionException(IError error, String details) {
		this(error);
		this.details = details + "\n";
	}

	public void handle (){
		System.out.println("\t     " + error.getMessage());
		if (details != null){
			System.out.println("\t     " + details);
		}
	}
	public void setDetails(String details) {
		this.details += details + "\n";
	}

	public IError getError() {
		return error;
	}
	
	@Override
	public String getMessage() {
		return error.getMessage();
	}
}
