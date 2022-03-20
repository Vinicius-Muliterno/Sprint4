package br.com.compass.PartidosPoliticos.service.exception;

public class MethodArgumentNotValidException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public MethodArgumentNotValidException(String msg) {
		super(msg);
	}

	public Object getFieldError() {
		// TODO Auto-generated method stub
		return null;
	}
}
