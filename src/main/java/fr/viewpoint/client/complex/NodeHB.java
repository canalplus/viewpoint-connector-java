package fr.viewpoint.client.complex;

import java.util.Date;

public class NodeHB implements Complex {

	//
	// --: HEART BEAT :--
	//
	/**
	 * The server name
	 */
	private String hbServerName;

	/**
	 * The app version
	 */
	private String hbVersion;

	/**
	 * The app build
	 */
	private String hbBuild;

	/**
	 * The client code source location
	 */
	private String hbClientLocation;

	/**
	 * The started Date
	 */
	private Date hbStartedDate;

	/**
	 * The execution report
	 */
	private WcExecutionReport hbReport;

	public NodeHB() {
		super();
	}

	/**
	 * @return the hbServerName
	 */
	public String getHbServerName() {
		return hbServerName;
	}

	/**
	 * @param hbServerName
	 *            the hbServerName to set
	 */
	public void setHbServerName(String hbServerName) {
		this.hbServerName = hbServerName;
	}

	/**
	 * @return the hbVersion
	 */
	public String getHbVersion() {
		return hbVersion;
	}

	/**
	 * @param hbVersion
	 *            the hbVersion to set
	 */
	public void setHbVersion(String hbVersion) {
		this.hbVersion = hbVersion;
	}

	/**
	 * @return the hbBuild
	 */
	public String getHbBuild() {
		return hbBuild;
	}

	/**
	 * @param hbBuild
	 *            the hbBuild to set
	 */
	public void setHbBuild(String hbBuild) {
		this.hbBuild = hbBuild;
	}

	/**
	 * @return the hbClientLocation
	 */
	public String getHbClientLocation() {
		return hbClientLocation;
	}

	/**
	 * @param hbClientLocation
	 *            the hbClientLocation to set
	 */
	public void setHbClientLocation(String hbClientLocation) {
		this.hbClientLocation = hbClientLocation;
	}

	/**
	 * @return the hbStartedDate
	 */
	public Date getHbStartedDate() {
		return hbStartedDate;
	}

	/**
	 * @param hbStartedDate
	 *            the hbStartedDate to set
	 */
	public void setHbStartedDate(Date hbStartedDate) {
		this.hbStartedDate = hbStartedDate;
	}

	/**
	 * @return the hbReport
	 */
	public WcExecutionReport getHbReport() {
		return hbReport;
	}

	/**
	 * @param hbReport
	 *            the hbReport to set
	 */
	public void setHbReport(WcExecutionReport hbReport) {
		this.hbReport = hbReport;
	}

	public static class WcExecutionReport {

		public enum WcExecutionReportStatus {
			SUCCESS, WARN, ERROR
		};

		private Date executionDate;
		private WcExecutionReportStatus status;
		private String message;

		public WcExecutionReport() {
			super();
		}

		/**
		 * 
		 * @param executionDate
		 * @param status
		 * @param message
		 */
		public WcExecutionReport(Date executionDate,
				WcExecutionReportStatus status, String message) {
			super();
			this.executionDate = executionDate;
			this.status = status;
			this.message = message;
		}

		/**
		 * @return the executionDate
		 */
		public Date getExecutionDate() {
			return executionDate;
		}

		/**
		 * @param executionDate
		 *            the executionDate to set
		 */
		public void setExecutionDate(Date executionDate) {
			this.executionDate = executionDate;
		}

		/**
		 * @return the status
		 */
		public WcExecutionReportStatus getStatus() {
			return status;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setStatus(WcExecutionReportStatus status) {
			this.status = status;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message
		 *            the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "WcExecutionReport [executionDate=" + executionDate
					+ ", status=" + status + ", message=" + message + "]";
		}
	}
}
