package fr.viewpoint.client;

public class Ws extends Time {

	/**
	 * The method name
	 * 
	 */
	private String methodName;

	/**
	 * The used endpoint
	 * 
	 */
	private String endpoint;

	/**
	 * The request response status
	 * 
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
	public Ws(String name) {
		super(name, IndicatorType.WEB_SERVICE);
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            The indicator name
	 * @param methodName
	 *            The called method name
	 * @param endpoint
	 *            The called endpoint
	 */
	public Ws(String name, String methodName, String endpoint) {
		super(name, IndicatorType.WEB_SERVICE);
		this.methodName = methodName;
		this.endpoint = endpoint;
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            The indicator name
	 * @param methodName
	 *            The called method name
	 * @param endpoint
	 *            The called endpoint
	 * @param status
	 *            The status (HTTP RETURNED CODE)
	 * @param method
	 *            The Http method like value
	 * @param input
	 *            The input value
	 * @param output
	 *            The output value
	 */
	public Ws(String name, String methodName, String endpoint, String status,
			String method, String input, String output) {
		super(name, IndicatorType.WEB_SERVICE);
		this.methodName = methodName;
		this.endpoint = endpoint;
		this.status = status;
		this.method = method;
		this.input = input;
		this.output = output;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
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
}
