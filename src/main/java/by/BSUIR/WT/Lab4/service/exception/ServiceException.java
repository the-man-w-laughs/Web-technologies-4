package by.BSUIR.WT.Lab4.service.exception;

public class ServiceException  extends Exception{

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
