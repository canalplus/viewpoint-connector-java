package fr.viewpoint.client;

/**
 * Specify the error type of an error (or success)
 * 
 * @author jpoirier
 * 
 */
public enum IndicatorErrorType {

	/**
	 * No error. Null object pattern impl
	 */
	SUCCESS(0),

	/**
	 * Functionnal
	 */
	FUNCTIONAL(1),

	/**
	 * Technical
	 */
	TECHNICAL(2);

	private final int value;

	/**
	 * 
	 * @param value
	 */
	private IndicatorErrorType(int value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

}
