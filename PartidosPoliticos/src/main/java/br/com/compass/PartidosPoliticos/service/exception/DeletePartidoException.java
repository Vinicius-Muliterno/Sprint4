package br.com.compass.PartidosPoliticos.service.exception;

public class DeletePartidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DeletePartidoException(String msg) {
		super(msg);
	}
}