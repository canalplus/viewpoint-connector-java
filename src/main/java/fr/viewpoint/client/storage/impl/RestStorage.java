package fr.viewpoint.client.storage.impl;

import fr.viewpoint.client.storage.impl.rest.AggregateIndicator;

/**
 * Manage to save indicator to Viewpoint System
 * 
 * @author UFO
 * 
 */
public interface RestStorage {

	/**
	 * Add a new aggregated indicator
	 * 
	 * @param indicator
	 *            The indicator to send throught the REST API
	 */
	public void store(AggregateIndicator indicator);

	/**
	 * Perform REST call
	 */
	public void commit();

}