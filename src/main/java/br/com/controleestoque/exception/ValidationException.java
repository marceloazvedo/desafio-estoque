package br.com.controleestoque.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -5974410036773083690L;
	
	private String field;
	private String value;

	public ValidationException(String message){
		super(message);
	}
	
	public ValidationException(String message, String field, String value){
		super(message);
		setField(field);
		setValue(value);
	}
	
	public ValidationException(Exception e){
		super(e);
	}
	
	public ValidationException(String message, Exception e, String field, String value){
		super(message, e);
		setField(field);
		setValue(value);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
