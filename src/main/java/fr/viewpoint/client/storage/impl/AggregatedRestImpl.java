package fr.viewpoint.client.storage.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.viewpoint.client.Configuration;
import fr.viewpoint.client.storage.StorageException;
import fr.viewpoint.client.storage.impl.rest.AggregateIndicator;
import fr.viewpoint.utils.rest.Client;

public class AggregatedRestImpl implements RestStorage {

	/**
	 * Log
	 */
	private static final Logger log = LoggerFactory
			.getLogger(AggregatedRestImpl.class);

	/**
	 * Indicator storage
	 */
	private List<AggregateIndicator> indicators = new ArrayList<AggregateIndicator>();

	/**
	 * The configuration
	 */
	private Configuration config;

	/**
	 * Constructor
	 * 
	 * @param config
	 *            The config
	 */
	public AggregatedRestImpl(Configuration config) {
		this.config = config;
	}

	/**
	 * Add a new aggregated indicator
	 * 
	 * @param indicator
	 *            The indicator to send throught the REST API
	 */
	public void store(AggregateIndicator indicator) {

		if (null == indicator.getProjectName()
				|| "".equals(indicator.getProjectName())) {
			throw new IllegalArgumentException("No value for projectName");
		}
		if (null == indicator.getProjectKey()
				|| "".equals(indicator.getProjectKey())) {
			throw new IllegalArgumentException("No value for projectKey");
		}

		indicators.add(indicator);
	}

	/**
	 * Perform REST call
	 */
	public void commit() {

		if (null == config) {
			throw new RuntimeException("Invalid config");
		}

		String serverUri = config.getServerUri();

		if (null == serverUri || "".equals(serverUri)) {
			throw new IllegalArgumentException("serverUri is empty");
		}

		if (indicators.size() == 0) {
			return;
		}

		Client restClient = null;
		if (null == config.getRestConfig()) {
			restClient = new Client();
		} else {
			restClient = new Client(config.getRestConfig());
		}

		try {
			PublishResponse response = restClient.doPost(new URL(serverUri),
					(Object) indicators, PublishResponse.class);

			if (log.isDebugEnabled()) {
				log.debug("Response " + response);
			}

			if (null == response
					|| StatusResponseCode.KO == response.returnCode) {
				if (null == response)
					throw new StorageException(
							"Unable to store indicators. No response received");
				else
					throw new StorageException("Unable to store indicators ["
							+ response.message + "]");

			}

			indicators.clear();

		} catch (MalformedURLException e) {
			throw new StorageException(e);
		}
	}

	private static enum StatusResponseCode {
		OK, KO
	}

	private static class PublishResponse {
		public StatusResponseCode returnCode;
		public String message;

		@SuppressWarnings("unused")
		public PublishResponse() {
			super();
		}

		@SuppressWarnings("unused")
		public PublishResponse(StatusResponseCode returnCode, String message) {
			super();
			this.returnCode = returnCode;
			this.message = message;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "PublishResponse [returnCode=" + returnCode + ", message="
					+ message + "]";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Indicators=" + indicators;
	}

}
