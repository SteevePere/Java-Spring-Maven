package com.cours.ebenus.maven.ebenus.dao.exception;


@SuppressWarnings("serial")
public class EbenusException extends RuntimeException {

	
	private int code;

	
    public EbenusException(int code) {
    
    	super();
        this.code = code;
    }

    
    public EbenusException(String message, int code) {
    
    	super(message);
        this.code = code;
    }

    
    public EbenusException(Throwable cause, int code) {
    
    	super(cause);
        this.code = code;
    }

    
    public EbenusException(String message, Throwable cause, int code) {
    
    	super(message, cause);
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    
}