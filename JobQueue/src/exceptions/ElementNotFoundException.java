package exceptions;

public class ElementNotFoundException extends RuntimeException {
	public ElementNotFoundException(String message) {
		super("The element was not found in " + message);
	}
}
