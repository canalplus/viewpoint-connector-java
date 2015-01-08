package fr.viewpoint.client.storage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.viewpoint.client.Configuration;
import fr.viewpoint.client.Indicator;
import fr.viewpoint.client.LiveWrapper;
import fr.viewpoint.client.TechnicalIndicator;
import fr.viewpoint.client.storage.Storage;
import fr.viewpoint.client.storage.impl.rest.AggregateDetail;
import fr.viewpoint.client.storage.impl.rest.AggregateIndicator;

/**
 * Manage to send indicator to Webcare server
 * 
 * -- ONLY MANAGE TECHNICAL INDICATOR
 * 
 * The goal of this storage is to store locally the stored indicators. When the
 * commit will be called the previously stored indicators will be aggregated and
 * send to the REST api
 * 
 * The aggregation mechanism is :
 * 
 * If I store 2 values for the same indicator key the indicators will be
 * aggregated to create an AggregateIndicator version.
 * 
 * At the end for a key the api will be called once
 * 
 * @author UFO
 * 
 */
public class RestStorageImpl implements Storage {

	/**
	 * Log
	 */
	private static final Logger log = LoggerFactory
			.getLogger(RestStorageImpl.class);

	/**
	 * Indicator storage
	 */
	private static Map<String, List<TechnicalIndicator>> indicators = new HashMap<String, List<TechnicalIndicator>>();

	/**
	 * The configuration
	 */
	private Configuration config;

	/**
	 * Default constructor
	 * 
	 * @param config
	 */
	public RestStorageImpl(Configuration config) {
		this.config = config;
	}

	public void store(Indicator indicator, String userId) {
		// In the REST mode the userid is lost
		store(indicator);
	}

	public void store(List<Indicator> indicators, String userId) {
		store(indicators);
	}

	public void store(List<Indicator> indicators, LiveWrapper liveWrapper) {
		store(indicators);
	}


	public void store(Indicator indicator) {
		if (null != indicator && indicator instanceof TechnicalIndicator) {
			TechnicalIndicator wcTec = (TechnicalIndicator) indicator;
			if (indicators.containsKey(wcTec.getName())) {
				indicators.get(wcTec.getName()).add(wcTec);
			} else {
				List<TechnicalIndicator> list = new ArrayList<TechnicalIndicator>();
				list.add(wcTec);
				indicators.put(wcTec.getName(), list);
			}
		}
	}

	public void store(List<Indicator> indicators) {
		if (null != indicators) {
			for (Indicator indicator : indicators) {
				store(indicator);
			}
		}
	}


	public void commit() {

		if (null == config) {
			throw new RuntimeException("Invalid config");
		}

		String serverUri = config.getServerUri();

		if (null == serverUri || "".equals(serverUri)) {
			throw new IllegalArgumentException("serverUri is empty");
		}

		String projectName = config.getProjectName();

		if (null == projectName || "".equals(projectName)) {
			throw new IllegalArgumentException("projectName is empty");
		}

		// Now transform indicator
		List<AggregateIndicator> aggregat = new ArrayList<AggregateIndicator>();
		Date indicatorDate = new Date();

		for (Entry<String, List<TechnicalIndicator>> indicatorEntry : indicators
				.entrySet()) {

			AggregateIndicator ai = new AggregateIndicator();

			long error = 0;
			double avg = 0;
			double min = 0;
			double max = 0;
			List<TechnicalIndicator> indicatorList = indicatorEntry
					.getValue();

			// Iterate over the complete list of indicator
			Map<String, AggregateDetail> errorDetail = new HashMap<String, AggregateDetail>();
			for (TechnicalIndicator indicator : indicatorList) {
				Double value = indicator.getValue();
				
				if(null == value) {
					continue;
				}
				
				avg += value;
				if (indicator.isOk() == false) {
					error++;
				}
				if (max < value) {
					max = value;
				}
				if (min > value) {
					min = value;
				}
				if (null != indicator.getMsg()) {
					if (errorDetail.containsKey(indicator.getMsg())) {
						AggregateDetail ad = errorDetail
								.get(indicator.getMsg());
						ad.setCount(ad.getCount() + 1);
					} else {
						AggregateDetail ad = new AggregateDetail();
						ad.setId(indicator.getMsg());
						ad.setCount(1);
						errorDetail.put(indicator.getMsg(), ad);
					}
				}
			}

			TechnicalIndicator firstIndicator = indicatorList.get(0);

			ai.setType(firstIndicator.getType());
			ai.setCount(indicatorList.size());
			ai.setDate(indicatorDate);
			ai.setErrorCount(error);
			ai.setMax(max);
			ai.setMin(min);
			ai.setProjectName(projectName);
			ai.setProjectKey(indicatorEntry.getKey());
			ai.setDetail(new ArrayList<AggregateDetail>(errorDetail.values()));
			if (ai.getCount() > 0) {
				ai.setAverage(avg / indicatorList.size());
			}

			aggregat.add(ai);
		}

		// Add the auto generated indicators
		List<TechnicalIndicator> autoGeneratedIndicators = config
				.getProjectKeys();
		if (null != autoGeneratedIndicators
				&& autoGeneratedIndicators.size() > 0) {
			for (TechnicalIndicator indicatorThatShouldBePresent : autoGeneratedIndicators) {
				if (!indicators.containsKey(indicatorThatShouldBePresent)) {
					AggregateIndicator ai = new AggregateIndicator();
					ai.setType(indicatorThatShouldBePresent.getType());
					ai.setDate(indicatorDate);
					ai.setCount(0);
					ai.setErrorCount(0);
					ai.setProjectName(projectName);
					ai.setProjectKey(indicatorThatShouldBePresent.getName());
					aggregat.add(ai);
				}
			}
		}

		if (aggregat.size() == 0) {
			return;
		}

		// Call the agregat rest API
		RestStorage rest = new AggregatedRestImpl(config);
		for (AggregateIndicator indic : aggregat) {
			if (log.isDebugEnabled()) {
				log.debug("Aggregated indicator " + indic.toString());
			}
			rest.store(indic);
		}

		// Then send everything
		rest.commit();
	}


	public void close() {
		return;
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

	public void rawStore(String rawContent) {
		// Not implemented
	}

	public void rawStore(List<String> rawContent) {
		// Not implemented
	}
}
