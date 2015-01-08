package fr.viewpoint.client;

/**
 * The Service type
 * 
 * Used to measure an exposed service of the software
 * 
 * To measure internal processes use WcFunction
 * 
 * @author JPOIRIER
 * 
 */
public class Service extends Time {

	/**
	 * The request id
	 */
	private String status;

	/**
	 * The method GET / POST / PUT
	 */
	private String method;

	/**
	 * The input value
	 */
	private String input;

	/**
	 * The output value
	 */
	private String output;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            The indicator name
	 */
	public Service(String name) {
		super(name, IndicatorType.SERVICE);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WcService [status=" + status + ", method="
				+ method + ", input=" + input + ", output=" + output + " "
				+ super.toString() + "]";
	}

}
