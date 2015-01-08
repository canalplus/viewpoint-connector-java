package fr.viewpoint.client;

import java.util.Date;

/**
 * Define a business indicator skeleton
 * 
 * @author jpoirier
 * 
 */
public abstract class BusinessIndicator extends Indicator {

	/**
	 * An id to identify which system is at the origin
	 */
	private String origin;

	/**
	 * Default constructor
	 * 
	 * @param origin
	 *            The system at the origin
	 * @param subType
	 *            The system business indicator type
	 * @param date
	 *            The indicator date
	 */
	public BusinessIndicator(String origin, String name, Date date) {
		super(IndicatorType.BUSINESS, name, date);
		this.origin = origin;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
