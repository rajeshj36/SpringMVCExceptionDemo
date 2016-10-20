package com.tataelxsi.springdao.exceptions;

public class DataBaseException extends RuntimeException {

	private String exceptionMsg;
	   
	   public DataBaseException(String exceptionMsg) {
	      this.exceptionMsg = exceptionMsg;
	   }
	   
	   public String getExceptionMsg(){
	      return this.exceptionMsg;
	   }
	   
	   public void setExceptionMsg(String exceptionMsg) {
	      this.exceptionMsg = exceptionMsg;
	   }
	   public String toString(){ 
	       return exceptionMsg ;
	    }

	}