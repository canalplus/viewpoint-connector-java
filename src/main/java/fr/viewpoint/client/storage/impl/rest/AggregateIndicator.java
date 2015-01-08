package fr.viewpoint.client.storage.impl.rest;

import java.util.Date;
import java.util.List;

import fr.viewpoint.client.IndicatorType;

public class AggregateIndicator {
	private Date date;

	private double average;

	private long count;

	private long errorCount;

	private double min;

	private double max;

	private String projectName;

	private String projectKey;

	private IndicatorType type;

	private List<AggregateDetail> detail;

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * @param average
	 *            the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the errorCount
	 */
	public long getErrorCount() {
		return errorCount;
	}

	/**
	 * @param errorCount
	 *            the errorCount to set
	 */
	public void setErrorCount(long errorCount) {
		this.errorCount = errorCount;
	}

	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectKey
	 */
	public String getProjectKey() {
		return projectKey;
	}

	/**
	 * @param projectKey
	 *            the projectKey to set
	 */
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	/**
	 * @return the type
	 */
	public IndicatorType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(IndicatorType type) {
		this.type = type;
	}

	/**
	 * @return the detail
	 */
	public List<AggregateDetail> getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(List<AggregateDetail> detail) {
		this.detail = detail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AggregateIndicator [date=" + date + ", average=" + average
				+ ", count=" + count + ", errorCount=" + errorCount + ", min="
				+ min + ", max=" + max + ", projectName=" + projectName
				+ ", projectKey=" + projectKey + ", type=" + type + ", detail="
				+ detail + "]";
	}
	
	
}