package fr.viewpoint.client;

/**
 * Same as service but for a non exposed function. Service should be used for an
 * public interface of the software Function should be used for a private part
 * of code called by the software itself
 * 
 * @author jpoirier
 * 
 */
public class Function extends Time {

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
	public Function(String name) {
		super(name, IndicatorType.FUNCTION);
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
		return "WcFunction [input=" + input + ", output=" + output + "]";
	}
}
